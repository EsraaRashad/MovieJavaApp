package com.example.esraarashad.httpurlconnectionexample.detailspackage.model

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles

import java.util.ArrayList

interface ApiResponse {
    fun onProcessFinished(profiles: ArrayList<Profiles>)
}
