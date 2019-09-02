package com.example.esraarashad.httpurlconnectionexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageDetailsActivity extends AppCompatActivity {
    private ImageView fullImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        fullImageView=findViewById(R.id.full_imgView);
        Bundle mBundle = getIntent().getExtras();
        if(mBundle != null){
            fullImageView.setImageResource(mBundle.getInt("Image"));
        }
    }
}
