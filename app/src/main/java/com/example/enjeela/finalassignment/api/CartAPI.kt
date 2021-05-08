package com.example.enjeela.finalassignment.api

import com.example.enjeela.finalassignment.entity.Cart
import com.example.enjeela.finalassignment.response.CartResponse
import com.example.enjeela.finalassignment.response.GetCartResponse
import retrofit2.Response
import retrofit2.http.*

interface CartAPI {


    @POST("cart/add")
    suspend fun addCart(
            @Header("Authorization") token:String,
            @Body cart: Cart
    ): Response<CartResponse>

    @GET("cart")
    suspend fun getCart(
        @Header("Authorization") token:String,
    ):Response<GetCartResponse>

}