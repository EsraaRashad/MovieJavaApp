package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView;

import java.util.ArrayList;

import org.json.JSONException;

public class HomePresenter {
    private HomeDataNetwork homeDataNetwork;
    private IHomeView view;

    public HomePresenter(IHomeView view) {
        this.view = view;
        this.homeDataNetwork = new HomeDataNetwork();
    }

    public ArrayList<PeopleResults> getPeopleListFromModel(){
        return homeDataNetwork.returnListForRecyclerViewAndAdapter();
    }

    public void getRecyclerViewAndAdapter(){
        view.setRecyclerViewAndAdapter();
    }
    public void setRecyclerAndAdapterToModel(){}

    public void getToastErrMsg(JSONException e){
        view.setToastErrMsg(e);
    }
}
