package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView

import java.util.ArrayList

class HomePresenter(private val view: IHomeView, private val model: IHomeModel) {
    private var list: ArrayList<PeopleResults>? = null

    init {
        list = ArrayList()
    }

    fun getAsyncPop(page: Int){
        model.getPopularData(page){
            view.setRecyclerViewAndAdapter(it)
        }
    }

    fun asyncSearch(text: String) {
        model.asyncSearchModel(text){
            view.setRecyclerViewAndAdapter(list!!)
        }
    }

}
