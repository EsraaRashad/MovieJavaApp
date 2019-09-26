package com.example.esraarashad.httpurlconnectionexample.homepackage.model

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import java.util.ArrayList

interface IHomeModel {
    //     HomeModel.JSONTask[] asyncSearch(String text);
    val defaultURL: String
    val searchUrl: String
    fun asyncPopularModel(callback: (ArrayList<PeopleResults>)->Unit)
    fun asyncSearchModel(text: String ,callback: (ArrayList<PeopleResults>)->Unit)
    fun asyncOnLoadMorePages(page: Int ,callback: (ArrayList<PeopleResults>)->Unit)
}
