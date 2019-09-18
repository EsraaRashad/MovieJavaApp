package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

public interface IHomeModel {
     HomeDataNetwork.JSONTask[] asyncSearch(String text);
     String getDefaultURL();
     String getSearchUrl();
}
