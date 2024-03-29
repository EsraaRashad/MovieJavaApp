package com.example.esraarashad.httpurlconnectionexample.PopularPeopleModel;

import java.util.ArrayList;

public class MyPeoplePojo {
    private String page;

    private String total_pages;

    private ArrayList<PeopleResults> results;

    private String total_results;

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    public ArrayList<PeopleResults>  getResults ()
    {
        return results;
    }

    public void setResults (ArrayList<PeopleResults>  results)
    {
        this.results = results;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [page = "+page+", total_pages = "+total_pages+", results = "+results+", total_results = "+total_results+"]";
    }
}
