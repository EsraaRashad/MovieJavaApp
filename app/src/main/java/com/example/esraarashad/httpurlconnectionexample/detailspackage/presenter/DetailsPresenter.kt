package com.example.esraarashad.httpurlconnectionexample.detailspackage.presenter

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ApiResponse
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.DetailsNetwork
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.IModelDetails
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles
import com.example.esraarashad.httpurlconnectionexample.detailspackage.view.IViewDetails

import java.util.ArrayList

class DetailsPresenter(private val iViewDetails: IViewDetails, private val iModelDetails: IModelDetails) {
    var id: Int = 0

    private val profilesUrl: String? = null
    private var apiResponse: ApiResponse? = null

    fun asyncProfiles() {
        val apiCall = DetailsNetwork.JSONDetailsTask(apiResponse = object : ApiResponse {
            override fun onProcessFinished(profiles: ArrayList<Profiles>) {
                iViewDetails.setmRecyclerViewAndmyAdapter(profiles)
            }
        })
        apiCall.execute(iModelDetails.defaultImagesUrl + id + iModelDetails.imagesApiKeyUrl)
    }
}
