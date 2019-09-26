package com.example.esraarashad.httpurlconnectionexample.homepackage.model

import android.os.AsyncTask
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import org.json.JSONException
import org.json.JSONObject

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

class HomeModel : IHomeModel ,AsyncResponse{
    private lateinit var peopleList: ArrayList<PeopleResults>
    override val defaultURL: String
    override val searchUrl: String
    var homeNetwork :HomeNetwork? = null
    var caller : ( (ArrayList<PeopleResults>)->Unit )? = null
    init {
        homeNetwork = HomeNetwork(this)
        peopleList = ArrayList()
        defaultURL = "https://api.themoviedb.org/3/person/popular?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page="
        searchUrl = "https://api.themoviedb.org/3/search/person?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&query="
    }

    override fun processFinish(outputList: ArrayList<PeopleResults>) {
        peopleList.addAll(outputList)
        caller!!(peopleList)
    }


    override fun asyncPopularModel(callback: (ArrayList<PeopleResults>)->Unit) {
        homeNetwork?.requestURL(defaultURL)
        caller = callback
//        val callApi = HomeNetwork(object : AsyncResponse {
//            override fun processFinish(outputList: ArrayList<PeopleResults>) {
//
//            }
//        })
//        callApi.execute(defaultURL)
    }

    override fun asyncSearchModel(text: String) {
    }

    override fun asyncOnLoadMorePages(page: Int) {
    }





}
