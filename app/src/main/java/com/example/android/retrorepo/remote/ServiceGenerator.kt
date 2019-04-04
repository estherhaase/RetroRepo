package com.example.android.retrorepo.remote

import com.example.android.retrorepo.tools.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val apiUrl = Constants.BASE_URL

    private val gson = GsonBuilder().setLenient().create()

    private val builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(apiUrl)

    private var retrofit = builder.build()

    private val client = OkHttpClient.Builder()

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    fun <S> createService(serviceClass: Class<S>): S {
        if (!client.interceptors().contains(loggingInterceptor)) {
            client.addInterceptor(loggingInterceptor)

            builder.client(client.build())
            retrofit = builder.build()
        }

        return retrofit.create(serviceClass)
    }
}
