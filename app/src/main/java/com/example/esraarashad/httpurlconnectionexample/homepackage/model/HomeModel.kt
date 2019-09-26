package com.example.esraarashad.httpurlconnectionexample.homepackage.model

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
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

}
