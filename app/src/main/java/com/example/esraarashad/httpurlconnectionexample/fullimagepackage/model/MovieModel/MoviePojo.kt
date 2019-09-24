package com.example.esraarashad.httpurlconnectionexample.fullimagepackage.model.MovieModel

import java.util.ArrayList

class MoviePojo {
    var page: String? = null

    var total_pages: String? = null

    var results: ArrayList<Results>? = null

    var total_results: String? = null

    override fun toString(): String {
        return "ClassPojo [page = $page, total_pages = $total_pages, results = $results, total_results = $total_results]"
    }
}
