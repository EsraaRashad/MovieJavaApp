package com.example.esraarashad.httpurlconnectionexample.basemvp

import com.example.esraarashad.httpurlconnectionexample.retrofit.ApiCall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource{
    companion object {
        val instance = RemoteDataSource()
        const val URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "api_key"
        const val API_KEY_VALUE = "fba1791e7e4fb5ada6afc4d9e80550a0"
        const val POPULAR_PEOPLE_URL = "person/popular"
        const val SEARCH_URL = "search/person"
        const val PARAM_PAGE = "page"
        const val PROFILE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
    }

    private val okHttpClient: OkHttpClient =
            OkHttpClient
                    .Builder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val url = request.url
                        val newUrl= url.newBuilder()
                                .addQueryParameter(API_KEY, API_KEY_VALUE)
                                .build()

                        val builder = request.newBuilder()
                        builder.url(newUrl)

                        chain.proceed(builder.build())
                    }
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build()

    //    Retrofit
    var retrofit: Retrofit? =
            Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    val service: ApiCall = retrofit!!.create(ApiCall::class.java)
}