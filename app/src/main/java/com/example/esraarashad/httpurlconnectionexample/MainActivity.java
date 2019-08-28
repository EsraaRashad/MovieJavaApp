package com.example.esraarashad.httpurlconnectionexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button dataButton;
    private TextView dataTextView;
    private Button okHttpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataButton= findViewById(R.id.dataBtn);
        dataTextView= findViewById(R.id.textViewData);
        okHttpBtn=findViewById(R.id.okHttpButton);
        okHttpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoOkHttpActivity = new Intent(MainActivity.this,OkHttpActivity.class);
                startActivity(gotoOkHttpActivity);
            }
        });
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // we will pass the link
                new JSONTask().execute("https://api.themoviedb.org/3/movie/550?api_key=fba1791e7e4fb5ada6afc4d9e80550a0");
            }
        });

    }

    public class JSONTask extends AsyncTask<String,String,String> {

        private HttpURLConnection httpURLConnection;
        private BufferedReader bufferedReader;
        private URL url;
        @Override
        protected String doInBackground(String... urls) {
            httpURLConnection= null;
            bufferedReader= null;

            try {
                url=new URL(urls[0]);
                httpURLConnection=(HttpURLConnection) url.openConnection();
//                //we will define the method
//                httpURLConnection.setRequestMethod("GET");
                //after the following step we will connect directly to the server
                httpURLConnection.connect();
                //we will store stream data into inputStream object:
                InputStream inputStream= httpURLConnection.getInputStream();
                //the buffered reader will help me to read the stream
                //by using the bufferedReader we can read our stream data line by line
                bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
                //we use stringBuffer object to append our data to the line string
                StringBuffer stringBuffer=new StringBuffer();
                String line="";

                //while loop to append data:
                while ((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line + "\n");
                }
//                if(stringBuffer.length() == 0)
//                    return null;
                //if we have data we will return it
                return stringBuffer.toString();

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }finally {
                // here we will close the bufferedReader and the httpURLConnection
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
//                if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dataTextView.setText(result);
            Log.i("jsonData",result);
        }
    }
}
