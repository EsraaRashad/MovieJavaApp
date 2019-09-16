package com.example.esraarashad.httpurlconnectionexample.homepackage.view;

public interface IHomeView {

     void notifyChangesInAdapter(MyAdapter adapter);

     void getAsyncPopularObj();

     void getAsyncSearch(String text);

     void getOnLoadMoreData(int pageNum);
}
