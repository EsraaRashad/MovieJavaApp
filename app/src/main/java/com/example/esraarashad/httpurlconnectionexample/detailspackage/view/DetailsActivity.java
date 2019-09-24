package com.example.esraarashad.httpurlconnectionexample.detailspackage.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.DetailsNetwork;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.presenter.DetailsPresenter;
import com.example.esraarashad.httpurlconnectionexample.fullimagepackage.view.ImageDetailsActivity;
import com.example.esraarashad.httpurlconnectionexample.R;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements IViewDetails {
    private TextView nameText;
    private TextView adultText;
    private ImageView profileImage;
    private GridLayoutManager mGridLayoutManager;
    private String path= "";
    private int id;
    // we will place the list of data here
    private DetailsAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameText= findViewById(R.id.text_name);
        adultText= findViewById(R.id.text_adult);
        profileImage= findViewById(R.id.detail_img);
        mRecyclerView = findViewById(R.id.recycler_view);
        mGridLayoutManager = new GridLayoutManager(DetailsActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        detailsPresenter= new DetailsPresenter(this, new DetailsNetwork());
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetails=new Intent(DetailsActivity.this, ImageDetailsActivity.class);
                intentToDetails.putExtra("Image",path);
                startActivity(intentToDetails);
            }
        });

         //URL For Profiles:
        //https://api.themoviedb.org/3/person/{person_id}/images?api_key=fba1791e7e4fb5ada6afc4d9e80550a0
        Intent intent = getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            Boolean adult = intent.getBooleanExtra("adult",false);
            path = intent.getStringExtra("profile_path");
            id=intent.getIntExtra("id",1);
            nameText.setText(name);
            adultText.setText("For Adult :" +adult);
            getId(id);
            if (profileImage != null){
                Glide.with(this).load(path)
                        .apply(new RequestOptions()
                                .override(400,400))
                        .into(profileImage);
            }else{
                Glide.with(this).load(R.drawable.ic_launcher_background).into(profileImage);
            }
        }
        asyncProfiles();
    }

    @Override
    public void getId(int id) {
        detailsPresenter.setId(id);
    }


    @Override
    public void setmRecyclerViewAndmyAdapter(ArrayList<Profiles> profilesList){
        myAdapter= new DetailsAdapter( DetailsActivity.this,profilesList);
        mRecyclerView.setAdapter(myAdapter);
        notifyChangesInAdapter(myAdapter);
    }

    @Override
    public void notifyChangesInAdapter(DetailsAdapter adapter) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void asyncProfiles() {
        detailsPresenter.asyncProfiles();
    }
}

