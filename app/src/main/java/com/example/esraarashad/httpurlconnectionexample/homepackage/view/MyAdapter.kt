package com.example.esraarashad.httpurlconnectionexample.homepackage.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.esraarashad.httpurlconnectionexample.detailspackage.view.DetailsActivity
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.R
import java.util.ArrayList

class MyAdapter// Provide a suitable constructor (depends on the kind of dataset)
(private val context: Context, private val myPeoplePojo: ArrayList<PeopleResults>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var peopleResults: PeopleResults? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, viewGroup, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(viewHolder: MyAdapter.MyViewHolder, i: Int) {
        peopleResults = myPeoplePojo[i]
        viewHolder.nameTextView.text = peopleResults!!.name
        viewHolder.adultTextView.text = "For Adults : " + peopleResults!!.adult!!


        if (viewHolder.imageView != null) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + peopleResults!!.profile_path)
                    .apply(RequestOptions()
                            .override(200, 200))
                    .into(viewHolder.imageView)
        } else {
            Glide.with(context).load(R.drawable.ic_launcher_background).into(viewHolder.imageView)
        }

        Log.i("Adult", peopleResults!!.adult!!.toString())
        viewHolder.bind(peopleResults!!)
    }

    override fun getItemCount(): Int {
        return myPeoplePojo.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView
        var nameTextView: TextView
        var adultTextView: TextView
        var linearLayout: LinearLayout

        init {

            imageView = itemView.findViewById<View>(R.id.imageViewProfile) as ImageView
            this.nameTextView = itemView.findViewById<View>(R.id.name_text) as TextView
            this.adultTextView = itemView.findViewById<View>(R.id.adult_text) as TextView
            linearLayout = itemView.findViewById<View>(R.id.linearLayout) as LinearLayout
        }

        public fun bind(peopleResults1: PeopleResults) {
            itemView.setOnClickListener {
                val intentToDetails = Intent(context, DetailsActivity::class.java)
                intentToDetails.putExtra("name", peopleResults1.name)
                intentToDetails.putExtra("adult", peopleResults1.adult)
                intentToDetails.putExtra("id", peopleResults1.id)
                intentToDetails.putExtra("profile_path", "https://image.tmdb.org/t/p/w500/" + peopleResults1.profile_path)
                context.startActivity(intentToDetails)
            }
        }
    }
}
