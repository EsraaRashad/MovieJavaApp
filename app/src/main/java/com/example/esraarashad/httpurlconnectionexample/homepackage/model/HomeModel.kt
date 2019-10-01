package com.example.esraarashad.httpurlconnectionexample.homepackage.model

import android.telecom.Call
import android.util.Log
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.MyPeoplePojo
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.retrofit.ApiClient
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class HomeModel : IHomeModel ,AsyncResponse{

    private  var peopleList: ArrayList<PeopleResults>
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
    }

    override fun asyncSearchModel(text: String ,callback: (ArrayList<PeopleResults>)->Unit) {
        homeNetwork?.requestSearchUrl(searchUrl,text)
        caller = callback
    }

    override fun asyncOnLoadMorePages(page: Int ,callback: (ArrayList<PeopleResults>)->Unit) {
        homeNetwork?.requestLoadMore(defaultURL,page)
        caller = callback
    }

    override fun getPopularData(page: Int) {
        val call: retrofit2.Call<MyPeoplePojo> = ApiClient.getClient.getData(page)
        call.enqueue(object : Callback<MyPeoplePojo> {
            override fun onFailure(call: retrofit2.Call<MyPeoplePojo>, t: Throwable) {
                Log.e("callApi Error :", t.toString())
            }

            override fun onResponse(call: retrofit2.Call<MyPeoplePojo>, response: Response<MyPeoplePojo>) {
                if (response.isSuccessful){
                    val result =  response.body()?.results!!.toString()
                    val popObj = JSONObject(result)
                    val jArray = popObj.getJSONArray("results")
                    // Extract data from json and store into ArrayList as class objects
                    for (i in 0 until jArray.length()) {
                        val json_data = jArray.getJSONObject(i)
                        val peopleResults = PeopleResults()
                        peopleResults.name = json_data.getString("name")
                        peopleResults.adult = json_data.getBoolean("adult")
                        peopleResults.profile_path = json_data.getString("profile_path")
                        peopleResults.id = json_data.getInt("id")
                        peopleList.add(peopleResults)
                    }

                }
            }
        })
    }

}
