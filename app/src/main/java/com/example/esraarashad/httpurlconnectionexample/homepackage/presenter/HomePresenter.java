package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.AsyncResponse;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView;

import java.util.ArrayList;

public class HomePresenter {
    private IHomeModel model;
    private IHomeView view;
    private ArrayList<PeopleResults> list;
    private AsyncResponse response;

    public HomePresenter(IHomeView view, IHomeModel model) {
        this.view = view;
        this.model=model;
    }

    public void updatePage(int page){
        page++;
        asyncOnLoadMorePages(page);
    }

    public void asyncOnLoadMorePages(int page){
        HomeDataNetwork.JSONTask callApi=new HomeDataNetwork.JSONTask(response=new AsyncResponse() {

            @Override
            public void processFinish(ArrayList<PeopleResults> outputList) {
                view.setRecyclerViewAndAdapter(outputList);
            }
        });
        callApi.execute(model.getDefaultURL()+page);
    }
    public void asyncPopular() {

        HomeDataNetwork.JSONTask callApi=new HomeDataNetwork.JSONTask(response=new AsyncResponse() {

            @Override
            public void processFinish(ArrayList<PeopleResults> outputList) {
                view.setRecyclerViewAndAdapter(outputList);
              //  list.addAll(outputList);
            }
        });
        callApi.execute(model.getDefaultURL());

    }

    public ArrayList<PeopleResults> getList(){
        return list;
    }


}
