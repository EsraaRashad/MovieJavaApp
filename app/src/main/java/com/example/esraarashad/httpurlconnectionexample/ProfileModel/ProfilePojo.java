package com.example.esraarashad.httpurlconnectionexample.ProfileModel;

public class ProfilePojo {
    private Profiles[] profiles;

    private int id;

    public Profiles[] getProfiles ()
    {
        return profiles;
    }

    public void setProfiles (Profiles[] profiles)
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
