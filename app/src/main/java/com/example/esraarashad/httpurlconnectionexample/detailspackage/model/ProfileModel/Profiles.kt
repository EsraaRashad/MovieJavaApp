package com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel

class Profiles {
    var file_path: String? = null

    var aspect_ratio: String? = null

    var vote_average: String? = null

    var width: String? = null

    var iso_639_1: String? = null

    var vote_count: String? = null

    var height: String? = null

    override fun toString(): String {
        return "ClassPojo [file_path = $file_path, aspect_ratio = $aspect_ratio, vote_average = $vote_average, width = $width, iso_639_1 = $iso_639_1, vote_count = $vote_count, height = $height]"
    }
}
