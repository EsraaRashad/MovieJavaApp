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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
            if (profileImage != null){
                Glide.with(this).load(path)
                        .apply(new RequestOptions()
                                .override(200,200))
                        .into(profileImage);
            }else{
                Glide.with(this).load(R.drawable.ic_launcher_background).into(profileImage);
            }
        }
        detailsPresenter.asyncProfiles();
    }

    @Override
    public void getId(int id) {
        detailsPresenter.setId(id);
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

