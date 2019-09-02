package com.example.esraarashad.httpurlconnectionexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {
    private TextView nameText;
    private TextView adultText;
    private ImageView profileImage=null;
    private URL imgUrl = null;
    private Bitmap bpImg = null;
    private InputStream inputStream=null;
    String path= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameText= findViewById(R.id.text_name);
        adultText= findViewById(R.id.text_adult);
        profileImage= findViewById(R.id.detail_img);

        Intent intent = getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            Boolean adult = intent.getBooleanExtra("adult",false);
            path = intent.getStringExtra("profile_path");

            nameText.setText(name);
            adultText.setText("For Adult :" +adult);
        }
        try {
            profileImage.setImageBitmap(new AsyncImage(profileImage).execute(path).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

}

