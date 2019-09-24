package com.example.esraarashad.httpurlconnectionexample.detailspackage.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.esraarashad.httpurlconnectionexample.fullimagepackage.view.ImageDetailsActivity;
import com.example.esraarashad.httpurlconnectionexample.R;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>{
    private Context mContext;
    private ArrayList<Profiles> profilesArrayList;
    private Profiles profilesObject;

    public DetailsAdapter(Context mContext , ArrayList<Profiles> profilesList) {
        this.mContext = mContext;
        this.profilesArrayList=profilesList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_details_layout,viewGroup,false);
        DetailsViewHolder myViewHolder = new DetailsViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailsViewHolder detailsViewHolder, int i) {
        DetailsViewHolder myDetailHolder  = detailsViewHolder;
        profilesObject=profilesArrayList.get(i);
        if (detailsViewHolder.imgDetail != null){
            Glide.with(mContext).load("https://image.tmdb.org/t/p/w500/"+profilesObject.getFile_path())
                    .apply(new RequestOptions()
                            .override(500,500))
                    .into(detailsViewHolder.imgDetail);
        }else{
            Glide.with(mContext).load(R.drawable.ic_launcher_background).into(detailsViewHolder.imgDetail);
        }
        myDetailHolder.bind(profilesObject);
    }

    @Override
    public int getItemCount() {
        return profilesArrayList.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout gridLinearLayout;
        public ImageView imgDetail;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDetail = itemView.findViewById(R.id.img_details);
            gridLinearLayout= itemView.findViewById(R.id.gridLinearLayout);
        }
        private void bind(final Profiles profileResult){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentToDetails=new Intent(mContext, ImageDetailsActivity.class);
                    intentToDetails.putExtra("Image","https://image.tmdb.org/t/p/w500/"+profileResult.getFile_path());
                    mContext.startActivity(intentToDetails);

                }
            });
        }
    }
}
