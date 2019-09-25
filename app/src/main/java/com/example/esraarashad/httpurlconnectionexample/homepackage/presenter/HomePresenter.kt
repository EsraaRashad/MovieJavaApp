package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter

import android.util.Log

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.AsyncResponse
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView

import java.util.ArrayList

class HomePresenter(private val view: IHomeView, private val model: IHomeModel) {
    private val list: ArrayList<PeopleResults>? = null
//     var response: AsyncResponse? = null

    fun updatePage(page: Int) {
        var page = page
        page++
        asyncOnLoadMorePages(page)
    }

    fun gettingText(): String {
        return ""
    }

    fun asyncOnLoadMorePages(page: Int) {
        val callApi = HomeDataNetwork.JSONTask(object : AsyncResponse {
            override fun processFinish(outputList: ArrayList<PeopleResults>) {
                view.setRecyclerViewAndAdapter(outputList)
            }
        })
        callApi.execute(model.defaultURL + page)
    }

    fun asyncPopular() {
        val callApi = HomeDataNetwork.JSONTask(object : AsyncResponse {
            override fun processFinish(outputList: ArrayList<PeopleResults>) {
                view.setRecyclerViewAndAdapter(outputList)
            }
        })
        callApi.execute(model.defaultURL)
    }

    fun asyncSearch(text: String) {
        Log.i("newText", text)
        val callApi = HomeDataNetwork.JSONTask(object : AsyncResponse {
            override fun processFinish(outputList: ArrayList<PeopleResults>) {
                view.setRecyclerViewAndAdapter(outputList)
            }
        })
        callApi.execute(model.searchUrl + text)
    }

}
