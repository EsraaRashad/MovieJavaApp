package com.example.esraarashad.httpurlconnectionexample.homepackage.model

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults

import java.util.ArrayList

interface AsyncResponse {
    fun processFinish(outputList: ArrayList<PeopleResults>)
}
