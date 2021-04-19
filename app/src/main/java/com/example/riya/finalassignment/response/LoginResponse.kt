package com.example.riya.finalassignment.response

import com.example.riya.finalassignment.entity.Consumer

data class LoginResponse (
    val success : Boolean? = null,
    val token : String? = null,
    val data:String?=null,
    val userData:Consumer?=null,
    val id : String?=null
)