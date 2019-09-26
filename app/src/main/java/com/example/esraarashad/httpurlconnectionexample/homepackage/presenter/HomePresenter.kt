package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter

import android.util.Log

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.AsyncResponse
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeModel
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView

import java.util.ArrayList

class HomePresenter(private val view: IHomeView, private val model: IHomeModel) {
    private var list: ArrayList<PeopleResults>? = null

    init {
        list = ArrayList<PeopleResults>()
    }
    fun updatePage(page: Int) {
        var page = page
        page++
        asyncOnLoadMorePages(page)
    }
    
    fun asyncOnLoadMorePages(page: Int) {
    }

    fun asyncPopular() {
       model.asyncPopularModel {
               list!!.addAll(it)
               if (list != null) {
                   view.setRecyclerViewAndAdapter(list!!)
               }
       }
    }

    fun asyncSearch(text: String) {
    }

}
