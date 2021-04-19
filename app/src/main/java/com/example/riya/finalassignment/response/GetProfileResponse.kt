package com.example.riya.finalassignment.response

import com.example.riya.finalassignment.entity.Consumer


data class GetProfileResponse(
    val success:Boolean?=null,
    val profile:ArrayList<Consumer>?=null
)