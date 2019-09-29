package com.example.esraarashad.httpurlconnectionexample.detailspackage.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.esraarashad.httpurlconnectionexample.fullimagepackage.view.ImageDetailsActivity
import com.example.esraarashad.httpurlconnectionexample.R
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles
import java.util.ArrayList

class DetailsAdapter(private val mContext: Context, private val profilesArrayList: ArrayList<Profiles>) : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    private var profilesObject: Profiles? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.recyclerview_details_layout, viewGroup, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(detailsViewHolder: DetailsViewHolder, i: Int) {
        profilesObject = profilesArrayList[i]
        if (detailsViewHolder.imgDetail != null) {
            Glide.with(mContext).load("https://image.tmdb.org/t/p/w500/" + profilesObject!!.file_path)
                    .apply(RequestOptions()
                            .override(500, 500))
                    .into(detailsViewHolder.imgDetail!!)
        } else {
            Glide.with(mContext).load(R.drawable.ic_launcher_background).into(detailsViewHolder.imgDetail!!)
        }
        detailsViewHolder.bind(profilesObject!!)
    }

    override fun getItemCount(): Int {
        return profilesArrayList.size
    }

    inner class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gridLinearLayout: LinearLayout
        var imgDetail: ImageView? = null

        init {
            imgDetail = itemView.findViewById(R.id.img_details)
            gridLinearLayout = itemView.findViewById(R.id.gridLinearLayout)
        }

         fun bind(profileResult: Profiles) {
            itemView.setOnClickListener {
                val intentToDetails = Intent(mContext, ImageDetailsActivity::class.java)
                intentToDetails.putExtra("Image", "https://image.tmdb.org/t/p/w500/" + profileResult.file_path)
                mContext.startActivity(intentToDetails)
            }
        }
    }
}
