package com.example.esraarashad.httpurlconnectionexample.homepackage.view

import android.view.View

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import org.json.JSONException
import java.util.ArrayList

interface IHomeView {

    fun notifyChangesInAdapter(adapter: MyAdapter)
    fun getAsyncPopularObj(page: Int)
    fun getAsyncSearch(text: String)
    // void getOnLoadMoreData(int pageNum);
    fun setRecyclerViewAndAdapter(list: ArrayList<PeopleResults>)

    fun setToastErrMsg(e: JSONException)

}
