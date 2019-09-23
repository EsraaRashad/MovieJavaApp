package com.example.esraarashad.httpurlconnectionexample.detailspackage.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.DetailsNetwork;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.presenter.DetailsPresenter;
import com.example.esraarashad.httpurlconnectionexample.fullimagepackage.view.ImageDetailsActivity;
import com.example.esraarashad.httpurlconnectionexample.R;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity implements IViewDetails {
    private TextView nameText;
    private TextView adultText;
    private ImageView profileImage=null;
    private URL imgUrl = null;
    private Bitmap bpImg = null;
    private InputStream inputStream=null;
    private HttpURLConnection httpURLConnection;
    private BufferedReader bufferedReader;
    private URL url;
    private ProgressBar progressBar;
    private GridLayoutManager mGridLayoutManager;
    String path= "";
    int id;
    // we will place the list of data here
    private ArrayList<Profiles> profilesList;
    private DetailsAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameText= findViewById(R.id.text_name);
        adultText= findViewById(R.id.text_adult);
        profileImage= findViewById(R.id.detail_img);
        mRecyclerView = findViewById(R.id.recycler_view);
        mGridLayoutManager = new GridLayoutManager(DetailsActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        detailsPresenter= new DetailsPresenter(this, new DetailsNetwork());
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetails=new Intent(DetailsActivity.this, ImageDetailsActivity.class);
                intentToDetails.putExtra("Image",path);
                startActivity(intentToDetails);
            }
        });

         //URL For Profiles:
        //https://api.themoviedb.org/3/person/{person_id}/images?api_key=fba1791e7e4fb5ada6afc4d9e80550a0
        Intent intent = getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            Boolean adult = intent.getBooleanExtra("adult",false);
            path = intent.getStringExtra("profile_path");
            id=intent.getIntExtra("id",1);
            nameText.setText(name);
            adultText.setText("For Adult :" +adult);
            getId(id);
            try {
                profileImage.setImageBitmap(new AsyncImage(profileImage).execute(path).get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new JSONDetailsTask().execute("https://api.themoviedb.org/3/person/"+id+"/images?api_key=fba1791e7e4fb5ada6afc4d9e80550a0");


    }

    @Override
    public void getId(int id) {
        detailsPresenter.setId(id);
    }

    //this class to get the file_path from API
    public class JSONDetailsTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {

            httpURLConnection = null;
            bufferedReader = null;
            try {
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                String line = "";

                //while loop to append data:
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return (stringBuilder.toString());


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // here we will close the bufferedReader and the httpURLConnection
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }

                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            profilesList = new ArrayList<>();
            try {
                JSONObject profileObject = new JSONObject(result);
                JSONArray jArray = profileObject.getJSONArray("profiles");

                // Extract data from json and store into ArrayList as class objects
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Profiles peopleProfile=new Profiles();
                    peopleProfile.setFile_path(json_data.getString("file_path"));
                    profilesList.add(peopleProfile);
                }
               // setmRecyclerViewAndmyAdapter(profilesList);

            } catch (JSONException e) {
                Toast.makeText(DetailsActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
    public class AsyncImage extends AsyncTask<String,Void, Bitmap> {

        public AsyncImage(ImageView imageView) {
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                imgUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                inputStream = conn.getInputStream();
                bpImg = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bpImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
                profileImage.setImageBitmap(bitmap);
            }else{
                profileImage.setImageResource(R.drawable.ic_launcher_background);
            }
        }
    }

    @Override
    public void setmRecyclerViewAndmyAdapter(ArrayList<Profiles> profilesList){
        myAdapter= new DetailsAdapter( DetailsActivity.this,profilesList);
        mRecyclerView.setAdapter(myAdapter);
        notifyChangesInAdapter(myAdapter);
    }

    @Override
    public void notifyChangesInAdapter(DetailsAdapter adapter) {
        adapter.notifyDataSetChanged();
    }
}

