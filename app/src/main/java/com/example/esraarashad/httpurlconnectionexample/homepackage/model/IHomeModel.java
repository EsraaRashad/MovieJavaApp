package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;

import org.json.JSONException;

import java.util.ArrayList;

public interface IHomeModel {
     void asyncOnLoadMore();
     void asyncPopularObject();
     void getRecyclerViewAndAdapter();
     void getToastErrMsg(JSONException e);
     Boolean onTaskCompleted(String response);
     ArrayList<PeopleResults> returnListForRecyclerViewAndAdapter();
     HomeDataNetwork.JSONTask[] asyncSearch(String text);
     String sendResponse();

     void incrementPage(int page);
     int returnIncrementedPage();
}
