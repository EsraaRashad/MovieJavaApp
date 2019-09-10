package com.example.esraarashad.httpurlconnectionexample.homepackage.controller;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.HomeActivity;

public class HomeController {

    private HomeDataNetwork homeDataNetwork;
    private HomeActivity homeActivity;

    public HomeController() {
        homeDataNetwork= new HomeDataNetwork();
        homeActivity = new HomeActivity();
    }
}
