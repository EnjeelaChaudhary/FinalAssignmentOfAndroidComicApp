package com.example.riya.finalassignment.response

import com.example.riya.finalassignment.model.CartModel


data class GetCartResponse(
        val success : Boolean? = null,
        val data : MutableList<CartModel>? = null
)