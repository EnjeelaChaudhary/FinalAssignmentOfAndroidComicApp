package com.example.riya.finalassignment.api

import com.example.riya.finalassignment.entity.Consumer
import com.example.riya.finalassignment.response.GetProfileResponse
import com.example.riya.finalassignment.response.LoginResponse
import com.example.riya.finalassignment.response.LogoutResponse
import com.example.riya.finalassignment.response.UpdatePurchaserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConsumerAPI {
    //Register user
    @POST("user/register")
    suspend fun registerUser(
        @Body consumer: Consumer
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
        @Field("username") username:String,
        @Field("password") password:String
    ): Response<LoginResponse>

    @GET("logout")
    suspend fun logout(@Header("Authorization")
                       token: String):Response<LogoutResponse>


    @PUT("consumer/update/{id}")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body consumer: Consumer
    ) : Response<UpdatePurchaserResponse>


//    @GET("logout")
//    suspend fun logout(
//            @Header("Authorization") token: String
//    ):Response<LogoutResponse>

    @POST("/profile")
    suspend fun getProfile(
            @Header("Authorization") token: String
    ):Response<GetProfileResponse>
//
//    @Multipart
//    @PUT("user/image/:usernmae")
//    suspend fun uploadImage(
//            @Header("Authorization") token: String,
//            @Path("username") username: String,
//            @Part file: MultipartBody.Part
//    ): Response<ImageResponse>


}
