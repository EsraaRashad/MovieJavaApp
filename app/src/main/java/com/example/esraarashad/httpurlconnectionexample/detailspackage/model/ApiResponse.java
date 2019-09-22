package com.example.esraarashad.httpurlconnectionexample.detailspackage.model;

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles;

import java.util.ArrayList;

public interface ApiResponse {
    void onProcessFinished(ArrayList<Profiles> profiles);
}
