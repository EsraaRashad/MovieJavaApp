package com.example.esraarashad.httpurlconnectionexample.homepackage.controller;


import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.HomeActivity;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.MyAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HomeController {

    private HomeDataNetwork homeDataNetwork;
    private HomeActivity homeActivity;
    private ArrayList<PeopleResults>peopleList;
    private HttpURLConnection httpURLConnection;
    private BufferedReader bufferedReader;
    private URL url;


    InputStream inputStream=null;
   // private HomeImageNetwork homeImageNetwork;
    private MyAdapter myAdapter;
    private HomeActivity viewActivity;
    private URL imgUrl = null;
   // private Bitmap bpImg = null;

    public HomeController(HomeActivity homeActivity1) {
        peopleList = new ArrayList<>();
        this.homeActivity=homeActivity1;
       // this.homeDataNetwork = new HomeDataNetwork(this);
       // this.homeImageNetwork = new HomeImageNetwork(this);

    }

    public ArrayList<PeopleResults> getPeopleListFromController(){
        return peopleList;
    }

    public void setAsyncPopularObj(){
        homeDataNetwork.asyncPopularObject();
    }

    public void setAsyncSearch(String text){
        homeDataNetwork.asyncSearch(text);
    }

    public void setOnLoadMoreData(int pageNum){
        homeDataNetwork.asyncOnLoadMore();
    }

//    public void getJsonData(String result){
//        try {
//
//            JSONObject peoplePojo = new JSONObject(result);
//            JSONArray jArray = peoplePojo.getJSONArray("results");
//
//            // Extract data from json and store into ArrayList as class objects
//            for (int i = 0; i < jArray.length(); i++) {
//                JSONObject json_data = jArray.getJSONObject(i);
//                PeopleResults peopleResults=new PeopleResults();
//                peopleResults.setName(json_data.getString("name")) ;
//                peopleResults.setAdult(json_data.getBoolean("adult"));
//                peopleResults.setProfile_path(json_data.getString("profile_path"));
//                peopleResults.setId(json_data.getInt("id"));
//                peopleList.add(peopleResults);
//            }
//
//            getRecyclerViewAndAdapter();
//
//        } catch (JSONException e) {
//            getToastErrMsg(e);
//        }
//    }


//    public String getHttpConnection(String urls){
//        httpURLConnection = null;
//        bufferedReader = null;
//        try {
//
//            url = new URL(urls);
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.connect();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder stringBuilder=new StringBuilder();
//            String line = "";
//
//            //while loop to append data:
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//            return (stringBuilder.toString());
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // here we will close the bufferedReader and the httpURLConnection
//            if (httpURLConnection != null) {
//                httpURLConnection.disconnect();
//            }
//
//            try {
//                bufferedReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return null;
//    }
//
//    public void getRecyclerViewAndAdapter(){
//      // homeActivity.setRecyclerViewAndAdapter(peopleList);
//    }
//
//    public void getToastErrMsg(JSONException e){
//        homeActivity.setToastErrMsg(e);
//    }
//
//    public void getLayoutMgrAndItemsOnScroll(){}

//    public Bitmap getImageHttpConnection(String url){
//        try {
//            imgUrl = new URL(url);
//            HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
//            conn.setDoInput(true);
//            conn.connect();
//            inputStream = conn.getInputStream();
//            bpImg = BitmapFactory.decodeStream(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bpImg;
//    }
//
//    public ImageView getImageViewFromAdapter(){
//        return myAdapter.sendImageView();
//    }


//    public void getImageResource(){
//        myAdapter.settingImageResource();
//    }
//
//    public void gettingPeopleResultObj(){
//        myAdapter.myPeoplePojo();
//    }
//
//    public Bitmap getImageHttpConnection(String string) {
//        return null ;
//    }
//
//    public ImageView getImageViewFromAdapter() {
//        return null;
//    }
}
