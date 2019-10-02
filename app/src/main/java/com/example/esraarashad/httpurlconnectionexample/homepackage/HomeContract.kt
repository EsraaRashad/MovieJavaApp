package com.example.esraarashad.httpurlconnectionexample.homepackage

import com.example.esraarashad.httpurlconnectionexample.basemvp.BaseContract
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.MyPeoplePojo
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.MyAdapter
import io.reactivex.Single
import org.json.JSONException
import java.util.ArrayList

interface HomeContract {
    interface IHomeView :BaseContract.BaseIView{
        fun notifyChangesInAdapter(adapter: MyAdapter)
        fun getAsyncPopularObj()
        fun getAsyncSearch(text: String)
        fun setRecyclerViewAndAdapter(list: ArrayList<PeopleResults>)
        fun setToastErrMsg(e: JSONException)
        fun loadNextPagesSearched(text: String)
    }

    interface IHomeModel :BaseContract.BaseIRepository {
        fun asyncSearchModel(text: String ,callback: (ArrayList<PeopleResults>)->Unit)
        fun getPopularData(page: Int ,loadedData: (ArrayList<PeopleResults>)-> Unit)
        fun getPopData(page: Int):Single<MyPeoplePojo>
    }
}