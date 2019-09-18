package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;

import org.json.JSONException;

import java.util.ArrayList;

public interface IHomeModel {
     //void setListForRecyclerViewAndAdapter(ArrayList<PeopleResults> list);
     //ArrayList<PeopleResults> returnListForRecyclerViewAndAdapter();
     HomeDataNetwork.JSONTask[] asyncSearch(String text);
     String getDefaultURL();
     String getSearchUrl();
}
