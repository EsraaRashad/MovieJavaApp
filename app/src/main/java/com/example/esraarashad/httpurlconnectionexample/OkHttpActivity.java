package com.example.esraarashad.httpurlconnectionexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.esraarashad.httpurlconnectionexample.fullimagepackage.model.MovieModel.MoviePojo;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {
    private OkHttpClient okHttpClient= null;
    private TextView okDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        okDataText = findViewById(R.id.ok_data_text);
        okHttpClient = new OkHttpClient();
        //String myUrl = "https://api.themoviedb.org/3/movie/550?api_key=fba1791e7e4fb5ada6afc4d9e80550a0";
        String movieUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page=1";

        Request request = new Request.Builder()
                .url(movieUrl)
                .build();
        // Serialization
        final Gson gson = new Gson();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myresponse= response.body().string();
                    //Deserialization
                    final MoviePojo moviePojo = gson.fromJson(myresponse, MoviePojo.class);
                    final String [] movieResults = new String[moviePojo.getResults().size()];
                    for (int i=0;i<movieResults.length;i++){
                        movieResults[i]= moviePojo.getResults().get(i).getTitle();
                    }
                    //final String json = gson.toJson(moviePojo);
                    OkHttpActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final StringBuffer moviesStringBuffer = new StringBuffer();
                            for (int j=0;j<movieResults.length;j++){
                                okDataText.setText(moviesStringBuffer.append(movieResults[j]+"\n"));
                            }

                        }
                    });
                }
            }
        });
    }
}
