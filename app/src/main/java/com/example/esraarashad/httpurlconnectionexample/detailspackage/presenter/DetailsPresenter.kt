package com.example.esraarashad.httpurlconnectionexample.detailspackage.presenter

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.IModelDetails
import com.example.esraarashad.httpurlconnectionexample.detailspackage.view.IViewDetails

class DetailsPresenter(private val iViewDetails: IViewDetails, private val iModelDetails: IModelDetails) {
    var id: Int = 0

    fun getProfiles(id:Int) {
        iModelDetails.getPopularData(id){
            iViewDetails.setmRecyclerViewAndmyAdapter(it)
        }
    }
}
