package com.example.esraarashad.httpurlconnectionexample.homepackage.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.esraarashad.httpurlconnectionexample.detailspackage.view.DetailsActivity;
import com.example.esraarashad.httpurlconnectionexample.homepackage.controller.HomeController;
import com.example.esraarashad.httpurlconnectionexample.homepackage.controller.HomeImageController;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<PeopleResults> myPeoplePojo ;
    private Context context;
    private PeopleResults peopleResults =null;
    private ImageView imageView=null;
    private URL imgUrl = null;
    private Bitmap bpImg = null;
    private InputStream inputStream=null;
    private HomeController homeController;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context,ArrayList<PeopleResults> myPeoplePojo) {
        this.myPeoplePojo=myPeoplePojo;
        this.context=context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(listItem);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder viewHolder, int i) {
        MyViewHolder myHolder= (MyViewHolder) viewHolder;
        peopleResults =myPeoplePojo.get(i);
        myHolder.nameTextView.setText(peopleResults.getName());
        myHolder.adultTextView.setText("For Adults : "+peopleResults.getAdult());

        Log.i("Adult",peopleResults.getAdult().toString());
        try {
            imageView.setImageBitmap(new AsyncTaskImage(imageView).execute("https://image.tmdb.org/t/p/w500/"+peopleResults.getProfile_path()).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myHolder.bind (peopleResults);
    }

    @Override
    public int getItemCount() {
        return myPeoplePojo.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public TextView adultTextView;
        public LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageViewProfile);
            this.nameTextView = (TextView) itemView.findViewById(R.id.name_text);
            this.adultTextView = (TextView) itemView.findViewById(R.id.adult_text);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);


        }
        private void bind(final PeopleResults peopleResults1){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentToDetails=new Intent(context, DetailsActivity.class);
                    intentToDetails.putExtra("name", peopleResults1.getName());
                    intentToDetails.putExtra("adult", peopleResults1.getAdult());
                    intentToDetails.putExtra("id",peopleResults1.getId());
                    intentToDetails.putExtra("profile_path","https://image.tmdb.org/t/p/w500/"+peopleResults1.getProfile_path());
                    context.startActivity(intentToDetails);

                }
            });
        }
    }

    public class AsyncTaskImage extends AsyncTask<String,Void, Bitmap>{

        public AsyncTaskImage(ImageView imageView) {
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
                imageView.setImageBitmap(bitmap);
            }else{
                imageView.setImageResource(R.drawable.ic_launcher_background);
            }
        }
    }

    public ImageView sendImageView(){
        return imageView;
    }

    public void settingImageResource(){
        imageView.setImageResource(R.drawable.ic_launcher_background);
    }

    public PeopleResults myPeoplePojo(){
        return peopleResults;
    }

}
