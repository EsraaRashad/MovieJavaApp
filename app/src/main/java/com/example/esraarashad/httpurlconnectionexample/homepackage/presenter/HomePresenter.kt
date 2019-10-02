package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter

import com.example.esraarashad.httpurlconnectionexample.basemvp.BasePresenter
import com.example.esraarashad.httpurlconnectionexample.homepackage.HomeContract
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView

import java.util.ArrayList

class HomePresenter( view: HomeContract.IHomeView?,val model: HomeContract.IHomeModel) :
        BasePresenter <HomeContract.IHomeView,HomeContract.IHomeModel> (view,model) {

    private var page = 1
    private var list: ArrayList<PeopleResults> = ArrayList()

    override fun onViewReady() {
        getAsyncPop()
    }

    fun getAsyncPop(){
        view!!.showLoading()
        model.getPopularData(page){
            view!!.hideLoading()
            view!!.setRecyclerViewAndAdapter(it)
        }
    }

    fun asyncSearch(text: String) {
        model.asyncSearchModel(text){
            view!!.setRecyclerViewAndAdapter(it)
        }
    }

    fun loadNextPage(){
        page++
        getAsyncPop()
    }

    fun pullToRefresh(){
        page = 1
        getAsyncPop()
    }

    fun loadNextSearchPage(searchStr: String){
        page++
        asyncSearch(searchStr)
    }

}
