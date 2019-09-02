package com.example.esraarashad.httpurlconnectionexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>{
    private Context mContext;
    private int[] mPlaceList;
//    ImageView imgDetail =null;

    public DetailsAdapter(Context mContext , int[] mPlaceList) {
        this.mContext = mContext;
        this.mPlaceList = mPlaceList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_details_layout,
                viewGroup, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder detailsViewHolder, int i) {
        detailsViewHolder.imgDetail.setImageResource(mPlaceList[i]);
        detailsViewHolder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPlaceList.length;
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder{
        ImageView imgDetail ;
        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDetail = itemView.findViewById(R.id.img_details);
        }
    }
}
