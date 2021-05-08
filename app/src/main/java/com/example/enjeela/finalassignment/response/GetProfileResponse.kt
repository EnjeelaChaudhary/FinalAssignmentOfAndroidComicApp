package com.example.enjeela.finalassignment.response

import com.example.enjeela.finalassignment.entity.Consumer


data class GetProfileResponse(
    val success:Boolean?=null,
    val profile:ArrayList<Consumer>?=null
)