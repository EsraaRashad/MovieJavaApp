package com.example.esraarashad.httpurlconnectionexample.detailspackage.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.esraarashad.httpurlconnectionexample.detailspackage.presenter.DetailsPresenter
import com.example.esraarashad.httpurlconnectionexample.fullimagepackage.view.ImageDetailsActivity
import com.example.esraarashad.httpurlconnectionexample.R
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.DetailsModel
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles
import java.util.ArrayList

class DetailsActivity : AppCompatActivity(), IViewDetails {
    private var nameText: TextView? = null
    private var adultText: TextView? = null
    private var profileImage: ImageView? = null
    private var mGridLayoutManager: GridLayoutManager? = null
    private var path = ""
    private var id: Int = 0
    // we will place the list of data here
    private var myAdapter: DetailsAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    private var detailsPresenter: DetailsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        nameText = findViewById(R.id.text_name)
        adultText = findViewById(R.id.text_adult)
        profileImage = findViewById(R.id.detail_img)
        mRecyclerView = findViewById(R.id.recycler_view)
        mGridLayoutManager = GridLayoutManager(this@DetailsActivity, 2)
        mRecyclerView!!.layoutManager = mGridLayoutManager as RecyclerView.LayoutManager?
        detailsPresenter = DetailsPresenter(this, DetailsModel())
        profileImage!!.setOnClickListener {
            val intentToDetails = Intent(this@DetailsActivity, ImageDetailsActivity::class.java)
            intentToDetails.putExtra("Image", path)
            startActivity(intentToDetails)
        }

        //URL For Profiles:
        //https://api.themoviedb.org/3/person/{person_id}/images?api_key=fba1791e7e4fb5ada6afc4d9e80550a0
        val intent = intent
        if (intent != null) {
            val name = intent.getStringExtra("name")
            val adult = intent.getBooleanExtra("adult", false)
            path = intent.getStringExtra("profile_path")
            id = intent.getIntExtra("id", 1)
            nameText!!.text = name
            adultText!!.text = "For Adult :$adult"
            getId(id)
            if (profileImage != null) {
                Glide.with(this).load(path)
                        .apply(RequestOptions()
                                .override(400, 400))
                        .into(profileImage!!)
            } else {
                Glide.with(this).load(R.drawable.ic_launcher_background).into(profileImage!!)
            }
        }
        asyncProfiles(id)
    }

    override fun getId(id: Int) {
        detailsPresenter!!.id = id
    }


    override fun setmRecyclerViewAndmyAdapter(profilesList: ArrayList<Profiles>) {
        myAdapter = DetailsAdapter(this@DetailsActivity, profilesList)
        mRecyclerView!!.adapter = myAdapter
        notifyChangesInAdapter(myAdapter!!)
    }

    override fun notifyChangesInAdapter(adapter: DetailsAdapter) {
        adapter.notifyDataSetChanged()
    }

    override fun asyncProfiles(id: Int) {
        detailsPresenter!!.getProfiles(id)
    }
}

