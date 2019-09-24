package com.example.esraarashad.httpurlconnectionexample.detailspackage.model

import android.os.AsyncTask
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.DetailsNetwork.Companion.getHttpConnection
import com.example.esraarashad.httpurlconnectionexample.detailspackage.model.ProfileModel.Profiles
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

class DetailsNetwork : IModelDetails {
    override val defaultImagesUrl: String
    override val imagesApiKeyUrl: String

    init {
        profilesList = ArrayList()
        defaultImagesUrl = "https://api.themoviedb.org/3/person/"
        imagesApiKeyUrl = "/images?api_key=fba1791e7e4fb5ada6afc4d9e80550a0"
    }


    //this class to get the file_path from API
    class JSONDetailsTask(apiResponse: ApiResponse) : AsyncTask<String, String, String>() {
        var apiResponseDelegate: ApiResponse? = null


        init {
            apiResponseDelegate = apiResponse
        }

        override fun onPreExecute() {
            isLoading = true
            super.onPreExecute()
        }

        override fun doInBackground(vararg urls: String): String? {

            return getHttpConnection(urls[0])
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            isLoading = false
            apiResponseDelegate!!.onProcessFinished(getJsonData(result))
        }
    }

    companion object {

        // we will place the list of data here
        private lateinit var profilesList: ArrayList<Profiles>
        private var isLoading: Boolean? = false
        private var httpURLConnection: HttpURLConnection? = null
        private var bufferedReader: BufferedReader? = null
        private var url: URL? = null
        fun getJsonData(result: String): ArrayList<Profiles> {
            //        profilesList=new ArrayList<>();
            try {
                val profileObject = JSONObject(result)
                val jArray = profileObject.getJSONArray("profiles")

                // Extract data from json and store into ArrayList as class objects
                for (i in 0 until jArray.length()) {
                    val json_data = jArray.getJSONObject(i)
                    val peopleProfile = Profiles()
                    peopleProfile.file_path = json_data.getString("file_path")
                    profilesList.add(peopleProfile)

                    println("first element into profileList: " + profilesList[0])
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return profilesList
        }

        fun getHttpConnection(urls: String): String? {
            //        httpURLConnection = null;
            //        bufferedReader = null;
            try {

                url = URL(urls)
                httpURLConnection = url!!.openConnection() as HttpURLConnection
                httpURLConnection!!.connect()
                val inputStream = httpURLConnection!!.inputStream
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()
                var line :String?

                do{
                    line =bufferedReader!!.readLine()
                    stringBuilder.append(line)
                }
                //while loop to append data:
                while (line != null)
                return stringBuilder.toString()


            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                // here we will close the bufferedReader and the httpURLConnection
                if (httpURLConnection != null) {
                    httpURLConnection!!.disconnect()
                }

                try {
                    bufferedReader!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            return null
        }
    }
}
