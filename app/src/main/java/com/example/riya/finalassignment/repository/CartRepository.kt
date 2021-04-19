package com.example.riya.finalassignment.repository

import com.example.riya.finalassignment.api.CartAPI
import com.example.riya.finalassignment.api.MyApiRequest
import com.example.riya.finalassignment.api.ServiceBuilder
import com.example.riya.finalassignment.entity.Cart
import com.example.riya.finalassignment.model.CartModel
import com.example.riya.finalassignment.response.CartResponse
import com.example.riya.finalassignment.response.GetCartResponse


class CartRepository : MyApiRequest() {
    val myApi = ServiceBuilder.buildService(CartAPI::class.java)

    suspend fun addcart(cart: Cart): CartResponse {
        return apiRequest {
            myApi.addCart(
                    ServiceBuilder.token!!,
                    cart
            )
        }
    }

    suspend fun getCart(): GetCartResponse {
        return apiRequest {
            myApi.getCart(
                    ServiceBuilder.token!!
            )
        }
    }
}