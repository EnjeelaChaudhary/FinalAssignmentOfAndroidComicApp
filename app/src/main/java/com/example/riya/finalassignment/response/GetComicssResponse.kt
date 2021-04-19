package com.example.riya.finalassignment.response

import com.example.riya.finalassignment.entity.Comicss

data class GetComicssResponse(
    val success : Boolean? = null,
    val data : MutableList<Comicss>? = null,
    val alldata:MutableList<Comicss>?=null
)