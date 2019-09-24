package com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel

import java.util.ArrayList

class MyPeoplePojo {
    var page: String? = null

    var total_pages: String? = null

    var results: ArrayList<PeopleResults>? = null

    var total_results: String? = null

    override fun toString(): String {
        return "ClassPojo [page = $page, total_pages = $total_pages, results = $results, total_results = $total_results]"
    }
}
