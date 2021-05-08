package com.example.enjeela.finalassignment.api


import com.example.enjeela.finalassignment.entity.Consumer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    public const val BASE_URL =
            "http://10.0.2.2:90/"
//const val BASE_URL = "http://localhost:90/"
//    public const val BASE_URL =
//        "http://192.168.1.96/"
    var comic:String?=null
    var safety:String?=null

    var image:String?=null
    var token : String? = null

    var username:String?=null
    var medicine: String? = null

     var id: String?=null
    var data:String?=null
    var userData:MutableList<Consumer>?=null
    private val okHttp = OkHttpClient.Builder()

    //Create retrofit builder
    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()

    //Generic function
    fun <T> buildService(serviceType : Class<T>): T{
        return retrofit.create(serviceType)
    }
    fun loadImagePath(): String {
        return  BASE_URL
    }
}