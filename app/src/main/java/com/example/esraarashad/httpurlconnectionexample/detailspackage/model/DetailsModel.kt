package com.example.esraarashad.httpurlconnectionexample.detailspackage.model

import android.util.Log
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.ProfilePojo
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles
import com.example.esraarashad.httpurlconnectionexample.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class DetailsModel : IModelDetails {

    private var profilesList =ArrayList<Profiles> ()

    override fun getPopularData(id: Int, loadedData: (ArrayList<Profiles>) -> Unit) {
            val call: Call<ProfilePojo> = ApiClient.getClient.getPopularPersonProfiles(id)
            call.enqueue(object : Callback<ProfilePojo> {
                override fun onFailure(call: Call<ProfilePojo>, t: Throwable) {
                    Log.e("callApi Error :", t.toString())
                }

                override fun onResponse(call: Call<ProfilePojo>, response: Response<ProfilePojo>) {
                    if (response.isSuccessful){
                        profilesList.addAll(response.body()?.profiles!!)
                        loadedData(profilesList)
                        Log.i("onResponse :","responseSucceeded")

                    }else {
                        Log.i("onResponse :","responseFailed")
                    }
                }
            })
    }

    //this class to get the file_path from API
//    class JSONDetailsTask(apiResponse: ApiResponse) : AsyncTask<String, String, String>() {
//        var apiResponseDelegate: ApiResponse? = null
//
//
//        init {
//            apiResponseDelegate = apiResponse
//        }
//
//        override fun onPreExecute() {
//            isLoading = true
//            super.onPreExecute()
//        }
//
//        override fun doInBackground(vararg urls: String): String? {
//
//            return getHttpConnection(urls[0])
//        }
//
//        override fun onPostExecute(result: String) {
//            super.onPostExecute(result)
//            isLoading = false
//            apiResponseDelegate!!.onProcessFinished(getJsonData(result))
//        }
//    }

//    companion object {
//
//        // we will place the list of data here
//        private lateinit var profilesList: ArrayList<Profiles>
//        private var isLoading: Boolean? = false
//        private var httpURLConnection: HttpURLConnection? = null
//        private var bufferedReader: BufferedReader? = null
//        private var url: URL? = null
//        fun getJsonData(result: String): ArrayList<Profiles> {
//            //        profilesList=new ArrayList<>();
//            try {
//                val profileObject = JSONObject(result)
//                val jArray = profileObject.getJSONArray("profiles")
//
//                // Extract data from json and store into ArrayList as class objects
//                for (i in 0 until jArray.length()) {
//                    val json_data = jArray.getJSONObject(i)
//                    val peopleProfile = Profiles()
//                    peopleProfile.file_path = json_data.getString("file_path")
//                    profilesList.add(peopleProfile)
//
//                    println("first element into profileList: " + profilesList[0])
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//
//            return profilesList
//        }
//

}
