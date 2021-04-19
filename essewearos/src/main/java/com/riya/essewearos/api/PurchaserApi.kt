package com.riya.essewearos.api

import com.riya.essewearos.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface PurchaserApi {
    //Register purchaser/user

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>


}