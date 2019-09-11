package com.example.esraarashad.httpurlconnectionexample.homepackage.model;

import android.os.AsyncTask;
import com.example.esraarashad.httpurlconnectionexample.homepackage.controller.HomeController;

public class HomeDataNetwork {
    private String defaultURL;
    private HomeController homeControllerModel;
    private Boolean isLoading = false;
    private String searchUrl;

    public HomeDataNetwork(HomeController homeController) {
        this.homeControllerModel=homeController;
        defaultURL="https://api.themoviedb.org/3/person/popular?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page=";
        searchUrl="https://api.themoviedb.org/3/search/person?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&query=";
    }


    public JSONTask[] asyncSearch(String text){
        JSONTask[] jsonTasks={null};
        jsonTasks[0]= (JSONTask) new JSONTask().execute(searchUrl+text);
       return jsonTasks;
    }

    public void asyncOnLoadMore(int page){
        new JSONTask().execute(defaultURL+page);
    }

    public void asyncPopularObject(){
        new JSONTask().execute(defaultURL);
    }

    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            isLoading=true;
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {

            String gettingData=homeControllerModel.getHttpConnection(urls[0]);
            return gettingData;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            isLoading=false;
            homeControllerModel.getJsonData(result);

        }
    }
}
