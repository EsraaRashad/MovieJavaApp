package com.example.esraarashad.httpurlconnectionexample.homepackage.model

import android.util.Log
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.MyPeoplePojo
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class HomeModel : IHomeModel {
    private  var peopleList: ArrayList<PeopleResults> = ArrayList()

    override fun asyncSearchModel(text: String ,callback: (ArrayList<PeopleResults>)->Unit) {
        val call: Call<MyPeoplePojo> = ApiClient.getClient.search(text)
        call.enqueue(object :Callback<MyPeoplePojo>{
            override fun onFailure(call: Call<MyPeoplePojo>, t: Throwable) {
                Log.e("callSearchApi Error :", t.toString())
            }

            override fun onResponse(call: Call<MyPeoplePojo>, response: Response<MyPeoplePojo>) {
                if (response.isSuccessful){
                    peopleList.addAll(response.body()?.results!!)
                    callback(peopleList)
                    Log.i("onResponse :","responseSucceeded")
                }else {
                    Log.i("onResponse :","responseFailed")
                }
            }
        })
    }

    override fun getPopularData(page: Int ,loadedData: ( ArrayList<PeopleResults>)-> Unit) {
        val call: Call<MyPeoplePojo> = ApiClient.getClient.getData(page)
        call.enqueue(object : Callback<MyPeoplePojo> {
            override fun onFailure(call: Call<MyPeoplePojo>, t: Throwable) {
                Log.e("callApi Error :", t.toString())
            }

            override fun onResponse(call: Call<MyPeoplePojo>, response: Response<MyPeoplePojo>) {
                if (response.isSuccessful){
                    peopleList.addAll(response.body()?.results!!)
                    loadedData(peopleList)
                    Log.i("onResponse :","responseSucceeded")

                }else {
                    Log.i("onResponse :","responseFailed")
                }
            }
        })
    }

}
