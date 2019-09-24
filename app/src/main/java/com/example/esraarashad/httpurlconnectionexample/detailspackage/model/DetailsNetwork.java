package com.example.esraarashad.httpurlconnectionexample.detailspackage.model;

import android.os.AsyncTask;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;

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

public class DetailsNetwork implements IModelDetails {

    // we will place the list of data here
    private static ArrayList<Profiles> profilesList;
    private static Boolean isLoading = false;
    private static HttpURLConnection httpURLConnection;
    private static BufferedReader bufferedReader;
    private static URL url;
    private String defaultImagesUrl;
    private String imagesApiKeyUrl;

    public DetailsNetwork() {
        profilesList=new ArrayList<>();
        defaultImagesUrl="https://api.themoviedb.org/3/person/";
        imagesApiKeyUrl="/images?api_key=fba1791e7e4fb5ada6afc4d9e80550a0";
    }

    @Override
    public String getDefaultImagesUrl() {
        return defaultImagesUrl;
    }

    @Override
    public String getImagesApiKeyUrl() {
        return imagesApiKeyUrl;
    }


    //this class to get the file_path from API
    public static class JSONDetailsTask extends AsyncTask<String, String, String> {
        public ApiResponse apiResponseDelegate =null;


        public JSONDetailsTask(ApiResponse apiResponse) {
            apiResponseDelegate=apiResponse;
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
            apiResponseDelegate.onProcessFinished(getJsonData(result));
        }
    }
    public static ArrayList<Profiles> getJsonData(String result){
//        profilesList=new ArrayList<>();
        try {
            JSONObject profileObject = new JSONObject(result);
            JSONArray jArray = profileObject.getJSONArray("profiles");

            // Extract data from json and store into ArrayList as class objects
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                Profiles peopleProfile=new Profiles();
                peopleProfile.setFile_path(json_data.getString("file_path"));
                profilesList.add(peopleProfile);

                System.out.println("first element into profileList: "+profilesList.get(0));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return profilesList;
    }

    public static String getHttpConnection(String urls){
//        httpURLConnection = null;
//        bufferedReader = null;
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
