package com.example.esraarashad.httpurlconnectionexample.homepackage.model.retrofit

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {
    @GET(ApiClient.POPULAR_PEOPLE_URL)
    fun getData(@Query(ApiClient.PARAM_PAGE) page:String): Call<PeopleResults>

    @GET(ApiClient.SEARCH_PERSON)
    fun search(@Query(ApiClient.PARAM_QUERY) text: String): Call<PeopleResults>
}