package com.example.esraarashad.httpurlconnectionexample.PopularPeopleModel;

import java.util.ArrayList;

public class PeopleResults {
    private String gender;

    private String known_for_department;

    private String popularity;

    private ArrayList<Known_for> known_for;

    private String name;

    private String profile_path;

    private int id;

    private Boolean adult;

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getKnown_for_department ()
    {
        return known_for_department;
    }

    public void setKnown_for_department (String known_for_department)
    {
        this.known_for_department = known_for_department;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

    public ArrayList<Known_for> getKnown_for ()
    {
        return known_for;
    }

    public void setKnown_for (ArrayList<Known_for> known_for)
    {
        this.known_for = known_for;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getProfile_path ()
    {
        return profile_path;
    }

    public void setProfile_path (String profile_path)
    {
        this.profile_path = profile_path;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public Boolean getAdult ()
    {
        return adult;
    }

    public void setAdult (Boolean adult)
    {
        this.adult = adult;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [gender = "+gender+", known_for_department = "+known_for_department+", popularity = "+popularity+", known_for = "+known_for+", name = "+name+", profile_path = "+profile_path+", id = "+id+", adult = "+adult+"]";
    }
}
