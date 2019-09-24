package com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel

import java.util.ArrayList

class ProfilePojo {
    var profiles: ArrayList<Profiles>? = null

    var id: Int = 0

    override fun toString(): String {
        return "ClassPojo [profiles = $profiles, id = $id]"
    }
}
