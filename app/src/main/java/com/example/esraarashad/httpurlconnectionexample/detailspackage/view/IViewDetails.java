package com.example.esraarashad.httpurlconnectionexample.detailspackage.view;

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.MyAdapter;

import java.util.ArrayList;

public interface IViewDetails {
    void getId(int id);
    void setmRecyclerViewAndmyAdapter(ArrayList<Profiles> profilesList);
    void notifyChangesInAdapter(DetailsAdapter adapter);
    void asyncProfiles();
}
