package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.esraarashad.httpurlconnectionexample.homepackage.controller.HomeController;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.HomeActivity;
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

public class HomeDataNetwork {
    private String defaultURL;
    private HomeController homeControllerModel;
    private MyAdapter mAdapter;
    private HttpURLConnection httpURLConnection;
    private BufferedReader bufferedReader;
    private URL url;
    private ArrayList<PeopleResults> peopleList;
    private Boolean isLoading = false;
    private String jsonTaskString;
    private String searchUrl;

    public HomeDataNetwork() {
        this.homeControllerModel = new HomeController();
        defaultURL="https://api.themoviedb.org/3/person/popular?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page=";
        searchUrl="https://api.themoviedb.org/3/search/person?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&query=";
        //jsonTaskString="";
    }

//    public String sendUrl(){
//        return defaultURL;
//    }

    public String asyncSearch(String text){
        try {
            return new JSONTask().execute(searchUrl+text).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String asyncOnLoadMore(int page){
        try {
            return new JSONTask().execute(defaultURL+page).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String asyncPopularObject(){
        try {
          return new JSONTask().execute(defaultURL).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            isLoading=true;
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {

            String gettingData=homeControllerModel.getHttpConnection(urls[0]);

//            httpURLConnection = null;
//            bufferedReader = null;
//            try {
//
//                url = new URL(urls[0]);
//
//
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.connect();
//                //httpURLConnection.setUseCaches(true);
//                InputStream inputStream = httpURLConnection.getInputStream();
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                StringBuilder stringBuilder=new StringBuilder();
//                String line = "";
//
//                //while loop to append data:
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//                return (stringBuilder.toString());
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                // here we will close the bufferedReader and the httpURLConnection
//                if (httpURLConnection != null) {
//                    httpURLConnection.disconnect();
//                }
//
//                try {
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }

            return gettingData;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            isLoading=false;
            homeControllerModel.getJsonData(result);
//            peopleList = new ArrayList<>();
//            try {
//
//                JSONObject peoplePojo = new JSONObject(result);
//                JSONArray jArray = peoplePojo.getJSONArray("results");
//
//                // Extract data from json and store into ArrayList as class objects
//                for (int i = 0; i < jArray.length(); i++) {
//                    JSONObject json_data = jArray.getJSONObject(i);
//                    PeopleResults peopleResults=new PeopleResults();
//                    peopleResults.setName(json_data.getString("name")) ;
//                    peopleResults.setAdult(json_data.getBoolean("adult"));
//                    peopleResults.setProfile_path(json_data.getString("profile_path"));
//                    peopleResults.setId(json_data.getInt("id"));
//                    peopleList.add(peopleResults);
//                }
//
//
//                mAdapter = new MyAdapter( HomeActivity.this,peopleList);
//                recyclerView.setAdapter(mAdapter);
//                mAdapter.notifyDataSetChanged();
//                recyclerView.setItemViewCacheSize(20);
//                recyclerView.setDrawingCacheEnabled(true);
//                progressBar.setVisibility(View.GONE);
//
//            } catch (JSONException e) {
//                Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_LONG).show();
//            }

        }
    }
}
