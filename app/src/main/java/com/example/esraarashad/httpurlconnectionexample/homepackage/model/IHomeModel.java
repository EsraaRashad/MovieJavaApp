package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

import org.json.JSONException;

public interface IHomeModel {
     void asyncOnLoadMore(int page);
     void asyncPopularObject();
     void getRecyclerViewAndAdapter();
     void getToastErrMsg(JSONException e);
}
