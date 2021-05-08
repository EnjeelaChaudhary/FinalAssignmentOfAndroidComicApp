package com.example.enjeela.finalassignment.response

import com.example.enjeela.finalassignment.model.CartModel


data class GetCartResponse(
        val success : Boolean? = null,
        val data : MutableList<CartModel>? = null
)