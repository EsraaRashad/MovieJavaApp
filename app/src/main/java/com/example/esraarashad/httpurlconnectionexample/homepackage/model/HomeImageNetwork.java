package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.esraarashad.httpurlconnectionexample.R;
import com.example.esraarashad.httpurlconnectionexample.homepackage.controller.HomeController;
import com.example.esraarashad.httpurlconnectionexample.homepackage.controller.HomeImageController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeImageNetwork {

    private String imagesUrl;
    private HomeController homeImageController;
    URL imgUrl = null;
    //Bitmap bpImg = null;
    InputStream inputStream=null;

    public HomeImageNetwork(HomeController homeController) {
        this.homeImageController=homeController;
        imagesUrl="https://image.tmdb.org/t/p/w500/";
    }

    public class AsyncTaskImage extends AsyncTask<String,Void, Bitmap> {

        public AsyncTaskImage(ImageView imageView) {
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap image = homeImageController.getImageHttpConnection(strings[0]);
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
                homeImageController.getImageViewFromAdapter().setImageBitmap(bitmap);
                //imageView.setImageBitmap(bitmap);
            }else{
                homeImageController.getImageResource();
               // imageView.setImageResource(R.drawable.ic_launcher_background);
            }
        }
    }

    public void asyncImageTask(){
        new AsyncTaskImage(homeImageController.getImageViewFromAdapter()).execute(imagesUrl);
    }

}
