package com.example.esraarashad.httpurlconnectionexample.detailspackage.presenter;

import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.DetailsNetwork;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.IModelDetails;
import com.example.esraarashad.httpurlconnectionexample.detailspackage.view.IViewDetails;

public class DetailsPresenter {
    private int id;
    private String defaultImagesUrl;
    private String imagesApiKeyUrl;
    private String profilesUrl;
    private IViewDetails iViewDetails;
    private IModelDetails iModelDetails;
    public DetailsPresenter(IViewDetails iDetails, IModelDetails iModelDetails) {
        this.iViewDetails=iDetails;
        this.iModelDetails=iModelDetails;
        defaultImagesUrl="https://api.themoviedb.org/3/person/";
        imagesApiKeyUrl="/images?api_key=fba1791e7e4fb5ada6afc4d9e80550a0";
        profilesUrl=defaultImagesUrl+getId()+imagesApiKeyUrl;
    }

    public void setId(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void asyncProfiles(){}
}
