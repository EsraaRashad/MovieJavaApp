package com.example.esraarashad.httpurlconnectionexample.homepackage.controller;

import android.view.View;
import android.widget.Toast;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork;
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

public class HomeController {

    private HomeDataNetwork homeDataNetwork;
    private HomeActivity homeActivity;
    private ArrayList<PeopleResults>peopleList;
    private HttpURLConnection httpURLConnection;
    private BufferedReader bufferedReader;
    private URL url;
    private String urlFromModel;

    public HomeController() {
        homeDataNetwork= new HomeDataNetwork();
        homeActivity = new HomeActivity();
        peopleList = new ArrayList<>();
       // urlFromModel=homeDataNetwork.sendUrl();
    }

    public String setAsyncPopularObj(){
        String popStringObj=homeDataNetwork.asyncPopularObject();
        return popStringObj;
    }

    public String setAsyncSearch(String text){
        String searchStringObj=homeDataNetwork.asyncSearch(text);
        return searchStringObj;
    }

    public String setOnLoadMoreData(int pageNum){
        String loadMoreData=homeDataNetwork.asyncOnLoadMore(pageNum);
        return loadMoreData;
    }

    public void getJsonData(String result){
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


//            mAdapter = new MyAdapter( HomeActivity.this,peopleList);
//            recyclerView.setAdapter(mAdapter);
//            mAdapter.notifyDataSetChanged();
//            recyclerView.setItemViewCacheSize(20);
//            recyclerView.setDrawingCacheEnabled(true);
//            progressBar.setVisibility(View.GONE);

        } catch (JSONException e) {
            //getToast();
            //Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

//    public  String sendUrlToView(){
//        urlFromModel=homeDataNetwork.sendUrl();
//        return urlFromModel;
//
//    }

    public String getHttpConnection(String urls){
        httpURLConnection = null;
        bufferedReader = null;
        try {

            url = new URL(urls);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            //httpURLConnection.setUseCaches(true);
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
}
