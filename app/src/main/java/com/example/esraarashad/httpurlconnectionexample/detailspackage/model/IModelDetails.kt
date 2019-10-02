package com.example.esraarashad.httpurlconnectionexample.detailspackage.model

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles

interface IModelDetails {
    fun getPopularData(id: Int ,loadedData: ( ArrayList<Profiles>)-> Unit)
}
