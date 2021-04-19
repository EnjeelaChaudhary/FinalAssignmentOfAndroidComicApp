package com.riya.essewearos.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    public const val BASE_URL =
        "http://10.0.2.2:90/"

    //    public const val BASE_URL =
//        "http://192.168.137.71:90/"
    var medicine:String?=null
    var safety:String?=null


    var id: String?=null
    var token: String? = null

    private val okHttp = OkHttpClient.Builder()
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()
    //Generic function
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }



}