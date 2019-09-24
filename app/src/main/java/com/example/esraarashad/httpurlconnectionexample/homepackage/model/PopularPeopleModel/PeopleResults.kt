package com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel

import java.util.ArrayList

class PeopleResults {
    var gender: String? = null

    var known_for_department: String? = null

    var popularity: String? = null

    var known_for: ArrayList<Known_for>? = null

    var name: String? = null

    var profile_path: String? = null

    var id: Int = 0

    var adult: Boolean? = null

    override fun toString(): String {
        return "ClassPojo [gender = $gender, known_for_department = $known_for_department, popularity = $popularity, known_for = $known_for, name = $name, profile_path = $profile_path, id = $id, adult = $adult]"
    }
}
