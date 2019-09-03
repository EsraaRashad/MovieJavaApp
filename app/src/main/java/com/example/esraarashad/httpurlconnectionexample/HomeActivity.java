package com.example.esraarashad.httpurlconnectionexample;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esraarashad.httpurlconnectionexample.PopularPeopleModel.MyPeoplePojo;
import com.example.esraarashad.httpurlconnectionexample.PopularPeopleModel.PeopleResults;

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

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;
    private boolean isScrolling = false;
    int currentItems,totalItems , scrollOutItems;
    private HttpURLConnection httpURLConnection;
    private BufferedReader bufferedReader;
    private URL url;
    private ProgressBar progressBar;
    private ArrayList<PeopleResults> peopleList;
    private int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar=findViewById(R.id.progress);
        layoutManager=new LinearLayoutManager(HomeActivity.this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    isScrolling=true;
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                currentItems=layoutManager.getChildCount();
//                totalItems=layoutManager.getItemCount();
//                scrollOutItems=layoutManager.findFirstVisibleItemPosition();
//
//                if (isScrolling && (currentItems + scrollOutItems == totalItems)){
//                    isScrolling = false;
//                   // progressBar.setVisibility(View.VISIBLE);
//                    new JSONTask().execute("https://api.themoviedb.org/3/person/popular?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page="+i);
//                    //fetchData();
//                }
//            }
//        });

        new JSONTask().execute("https://api.themoviedb.org/3/person/popular?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page="+i);

    }

//    private void fetchData() {
//        progressBar.setVisibility(View.VISIBLE);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new JSONTask().execute("https://api.themoviedb.org/3/person/popular?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page="+i++);
//                mAdapter.notifyDataSetChanged();
//                progressBar.setVisibility(View.GONE);
//            }
//        },5000);
//    }

    public void requestNewData (int i){

    }


    public class JSONTask extends AsyncTask<String, String, String> {

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
            peopleList = new ArrayList<>();
            try {

                JSONObject peoplePojo = new JSONObject(result);
                JSONArray jArray = peoplePojo.getJSONArray("results");

                // Extract data from json and store into ArrayList as class objects
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    PeopleResults peopleResults=new PeopleResults();
                    peopleResults.setName(json_data.getString("name")) ;
                    peopleResults.setAdult(json_data.getBoolean("adult"));
                    peopleResults.setProfile_path(json_data.getString("profile_path"));
                    peopleResults.setId(json_data.getInt("id"));
                    peopleList.add(peopleResults);
                }

                mAdapter = new MyAdapter( HomeActivity.this,peopleList);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
//                progressBar.setVisibility(View.GONE);

            } catch (JSONException e) {
                Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

            }
        }
    }

