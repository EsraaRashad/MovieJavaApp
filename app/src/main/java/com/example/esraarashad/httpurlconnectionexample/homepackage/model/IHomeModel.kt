package com.example.esraarashad.httpurlconnectionexample.homepackage.model

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import java.util.ArrayList

interface IHomeModel {
    fun asyncSearchModel(text: String ,callback: (ArrayList<PeopleResults>)->Unit)
    fun getPopularData(page: Int ,loadedData: (ArrayList<PeopleResults>)-> Unit)
}
