package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;

import java.util.ArrayList;

public interface AsyncResponse {
    void processFinish(ArrayList<PeopleResults> outputList);
}
