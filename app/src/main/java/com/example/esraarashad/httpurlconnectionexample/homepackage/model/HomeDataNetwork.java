package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.homepackage.presenter.HomePresenter;

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

public class HomeDataNetwork implements IHomeModel{
    private String defaultURL;
    private static Boolean isLoading = false;
    private String searchUrl;

    private static ArrayList<PeopleResults> peopleList;
    private static HttpURLConnection httpURLConnection;
    private static BufferedReader bufferedReader;
    private static URL url;
    private String receivedResponse;
//    private AsyncResponse response;

    HomePresenter homePresenter ;

    private IHomeModel delegate = null; //Call back interface
    private int newPage=1;

    public HomeDataNetwork() {
        peopleList = new ArrayList<>();
        defaultURL="https://api.themoviedb.org/3/person/popular?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page=";
        searchUrl="https://api.themoviedb.org/3/search/person?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&query=";
    }

    public String getDefaultURL(){
        return defaultURL;
    }

    public String getSearchUrl(){
        return searchUrl;
    }

    @Override
    public JSONTask[] asyncSearch(String text){
        JSONTask[] jsonTasks={null};
       // jsonTasks[0]= (JSONTask) new JSONTask().execute(searchUrl+text);
       return jsonTasks;
    }


    // mvc
//    public void asyncOnLoadMore(int page){
//        new JSONTask().execute(defaultURL+page);
//    }
//
//    public void asyncPopularObject(){
//        new JSONTask().execute(defaultURL);
//    }

    public static class JSONTask extends AsyncTask<String, String, String> {
        public AsyncResponse delegate = null;//Call back interface
        public JSONTask(AsyncResponse asyncResponse) {
            delegate = asyncResponse;//Assigning call back interface through constructor
        }
        @Override
        protected void onPreExecute() {
            isLoading=true;
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {

            String gettingData=getHttpConnection(urls[0]);
            return gettingData;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            isLoading=false;

            delegate.processFinish(getJsonData(result));
           // returnListForRecyclerViewAndAdapter();
//            taskCompleted.onTaskCompleted(result);

        }
    }


    public static ArrayList<PeopleResults> getJsonData(String result){
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
           // homePresenter.setList(peopleList);
            System.out.println("first element into peaopleList: "+peopleList.get(0));
           // returnListForRecyclerViewAndAdapter();
         //   setListForRecyclerViewAndAdapter(peopleList);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return peopleList;
    }
//    @Override
//    public ArrayList<PeopleResults> returnListForRecyclerViewAndAdapter(){
//        return this.peopleList;
//    }

    public static String getHttpConnection(String urls){
        httpURLConnection = null;
        bufferedReader = null;
        try {

            url = new URL(urls);
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
}
