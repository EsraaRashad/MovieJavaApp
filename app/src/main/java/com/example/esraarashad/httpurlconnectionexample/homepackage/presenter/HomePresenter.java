package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView;

import java.util.ArrayList;

public class HomePresenter {
    private IHomeModel model;
    private IHomeView view;
    private ArrayList<PeopleResults> list;

    public HomePresenter(IHomeView view, IHomeModel model) {
        this.view = view;
        this.model=model;
    }

//    public String  getResult(){
//        String result=model.sendResponse();
//       return result;
//    }

    public ArrayList<PeopleResults> getPeopleListFromModel(){

        return model.returnListForRecyclerViewAndAdapter();
    }

    public void updatePage(int page){
        model.incrementPage(page);
    }

    public void asyncOnLoadMorePages(){
        model.asyncOnLoadMore();
    }
    public void asyncPopular() {
        model.asyncPopularObject();
        model.returnListForRecyclerViewAndAdapter();
        view.setRecyclerViewAndAdapter();
    }

    public void setList(ArrayList<PeopleResults> peopleList) {
        list.addAll(peopleList);
    }


//    public void getRecyclerViewAndAdapter(){
//        view.setRecyclerViewAndAdapter();
//    }

}
