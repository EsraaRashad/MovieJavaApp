package com.example.esraarashad.httpurlconnectionexample.retrofit

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_KEY = "fba1791e7e4fb5ada6afc4d9e80550a0"
    private const val PARAM_API_KEY = "api_key"
    const val PARAM_PAGE = "page"
    const val POPULAR_PEOPLE_URL = "person/popular?"
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val PROFILE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
    const val PARAM_QUERY = "query"
    const val SEARCH_PERSON = "search/person?"

//    var searchUrl = "https://api.themoviedb.org/3/search/person?api_key=fba1791e7e4fb5ada6afc4d9e80550a0&query="
    val getClient : ApiCall

    get() {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                    val url = request.url
                    val newUrl = url.newBuilder()
                            .addQueryParameter(PARAM_API_KEY, API_KEY)
                            .build()
                    val builder = request.newBuilder()
                    builder.url(newUrl)

                    chain.proceed(builder.build())

                }
                .addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(ApiCall::class.java)
    }
}