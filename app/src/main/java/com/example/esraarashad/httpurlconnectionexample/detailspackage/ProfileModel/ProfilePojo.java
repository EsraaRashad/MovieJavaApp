package com.example.esraarashad.httpurlconnectionexample.detailspackage.ProfileModel;

import java.util.ArrayList;

public class ProfilePojo {
    private ArrayList<Profiles> profiles;

    private int id;

    public ArrayList<Profiles> getProfiles ()
    {
        return profiles;
    }

    public void setProfiles (ArrayList<Profiles>  profiles)
    {
        this.profiles = profiles;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [profiles = "+profiles+", id = "+id+"]";
    }
}
