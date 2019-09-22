package com.example.esraarashad.httpurlconnectionexample.detailspackage.model;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.view.DetailsActivity;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.view.DetailsAdapter;

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
    private HttpURLConnection httpURLConnection;
    private BufferedReader bufferedReader;
    private URL url;
    // we will place the list of data here
    private ArrayList<Profiles> profilesList;

    //this class to get the file_path from API
    public class JSONDetailsTask extends AsyncTask<String, String, String> {
        private ApiResponse apiResponseDelegate;
        public JSONDetailsTask(ApiResponse apiResponse) {
            this.apiResponseDelegate=apiResponse;
        }

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
                apiResponseDelegate.onProcessFinished(profilesList);
//                myAdapter= new DetailsAdapter( DetailsActivity.this,profilesList);
//                mRecyclerView.setAdapter(myAdapter);
//                myAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
