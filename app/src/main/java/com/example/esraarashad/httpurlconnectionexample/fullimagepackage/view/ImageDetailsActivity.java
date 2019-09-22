package com.example.esraarashad.httpurlconnectionexample.fullimagepackage.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.esraarashad.httpurlconnectionexample.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class ImageDetailsActivity extends AppCompatActivity {
    private ImageView fullImageView;
    private URL imgUrl = null;
    private Bitmap bpImg = null;
    private InputStream inputStream=null;
    private String imageString="";
    private String ImagePath="";
    private Uri URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        fullImageView=findViewById(R.id.full_imgView);
        Intent intent = getIntent();
        if (intent != null){
            imageString = intent.getStringExtra("Image");
        }
        if (fullImageView != null){
            Glide.with(this).load(imageString)
                    .apply(new RequestOptions()
                            .override(200,200))
                    .into(fullImageView);
        }else{
            Glide.with(this).load(R.drawable.ic_launcher_background).into(fullImageView);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_id) {
            //Permission for allowing downloading image
            if (ContextCompat.checkSelfPermission(ImageDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {

            }
            else {
                ActivityCompat.requestPermissions(ImageDetailsActivity.this, new String[]
                        { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
            }
            fullImageView.setDrawingCacheEnabled(true);
            //save the image
             MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    fullImageView.getDrawingCache(),
                    imageString,
                    "demo_image"
            );

            Toast.makeText(ImageDetailsActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
