package com.example.enjeela.finalassignment.repository

import com.example.enjeela.finalassignment.api.CartAPI
import com.example.enjeela.finalassignment.api.MyApiRequest
import com.example.enjeela.finalassignment.api.ServiceBuilder
import com.example.enjeela.finalassignment.entity.Cart
import com.example.enjeela.finalassignment.response.CartResponse
import com.example.enjeela.finalassignment.response.GetCartResponse


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