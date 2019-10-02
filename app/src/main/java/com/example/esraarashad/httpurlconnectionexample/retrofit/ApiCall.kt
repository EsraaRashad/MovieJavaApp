package com.example.esraarashad.httpurlconnectionexample.retrofit

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.ProfilePojo
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.MyPeoplePojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCall {
    @GET(ApiClient.POPULAR_PEOPLE_URL)
    fun getData(@Query(ApiClient.PARAM_PAGE) page:Int): Call<MyPeoplePojo>

    @GET(ApiClient.SEARCH_PERSON)
    fun search(@Query(ApiClient.PARAM_QUERY) text: String): Call<MyPeoplePojo>

    @GET("person/{profile_id}/images?")
    fun getPopularPersonProfiles(@Path("profile_id") profileId: Int): Call<ProfilePojo>
}