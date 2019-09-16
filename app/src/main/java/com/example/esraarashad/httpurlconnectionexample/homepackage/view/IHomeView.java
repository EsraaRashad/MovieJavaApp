package com.example.esraarashad.httpurlconnectionexample.homepackage.view;

import android.view.View;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import org.json.JSONException;
import java.util.ArrayList;

public interface IHomeView {

     void notifyChangesInAdapter(MyAdapter adapter);
     void getAsyncPopularObj();
     void getAsyncSearch(String text);
     void getOnLoadMoreData(int pageNum);
     void setRecyclerViewAndAdapter( ArrayList<PeopleResults> peopleList);
     void setToastErrMsg(JSONException e);
}
