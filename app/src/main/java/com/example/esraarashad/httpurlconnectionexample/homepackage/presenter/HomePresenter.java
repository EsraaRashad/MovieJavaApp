package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView;

import java.util.ArrayList;

public class HomePresenter {
    private HomeDataNetwork homeDataNetwork;
    private IHomeView view;
    private ArrayList<PeopleResults> list;

    public HomePresenter(IHomeView view) {
        this.view = view;
        this.homeDataNetwork = new HomeDataNetwork();
       // list=new ArrayList<>();
    }

    public ArrayList<PeopleResults> getPeopleListFromModel( ArrayList<PeopleResults> list){

        return homeDataNetwork.returnListForRecyclerViewAndAdapter();
    }

    public void asyncPopular() {
        homeDataNetwork.asyncPopularObject();
    }

    public void setList(ArrayList<PeopleResults> peopleList) {
        list.addAll(peopleList);
    }


//    public void getRecyclerViewAndAdapter(){
//        view.setRecyclerViewAndAdapter();
//    }

}
