package com.example.esraarashad.httpurlconnectionexample.homepackage.model

import android.os.AsyncTask
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork.Companion.getHttpConnection
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
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

class HomeDataNetwork : IHomeModel {
    override val defaultURL: String
    override val searchUrl: String
    private val delegate: IHomeModel? = null //Call back interface

    init {
        peopleList = ArrayList()
        defaultURL = "https://api.themoviedb.org/3/person/popular?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&language=en-US&page="
        searchUrl = "https://api.themoviedb.org/3/search/person?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&query="
    }

    //    @Override
    //    public JSONTask[] asyncSearch(String text){
    //        JSONTask[] jsonTasks={null};
    //       // jsonTasks[0]= (JSONTask) new JSONTask().execute(searchUrl+text);
    //       return jsonTasks;
    //    }

    class JSONTask(asyncResponse: AsyncResponse) : AsyncTask<String, String, String>() {
        var delegate: AsyncResponse? = null//Call back interface

        init {
            delegate = asyncResponse//Assigning call back interface through constructor
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
            delegate!!.processFinish(getJsonData(result))
        }
    }

    companion object {
        private var isLoading: Boolean? = false

        private lateinit var peopleList: ArrayList<PeopleResults>
        private var httpURLConnection: HttpURLConnection? = null
        private var bufferedReader: BufferedReader? = null
        private var url: URL? = null


        fun getJsonData(result: String): ArrayList<PeopleResults> {
            try {

                val peoplePojo = JSONObject(result)
                val jArray = peoplePojo.getJSONArray("results")

                // Extract data from json and store into ArrayList as class objects
                for (i in 0 until jArray.length()) {
                    val json_data = jArray.getJSONObject(i)
                    val peopleResults = PeopleResults()
                    peopleResults.name = json_data.getString("name")
                    peopleResults.adult = json_data.getBoolean("adult")
                    peopleResults.profile_path = json_data.getString("profile_path")
                    peopleResults.id = json_data.getInt("id")
                    peopleList.add(peopleResults)
                }
                println("first element into peaopleList: " + peopleList[0])

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return peopleList
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
