package com.example.esraarashad.httpurlconnectionexample.detailspackage.view

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.MyAdapter

import java.util.ArrayList

interface IViewDetails {
    fun getId(id: Int)
    fun setmRecyclerViewAndmyAdapter(profilesList: ArrayList<Profiles>)
    fun notifyChangesInAdapter(adapter: DetailsAdapter)
    fun asyncProfiles(id: Int)
}
