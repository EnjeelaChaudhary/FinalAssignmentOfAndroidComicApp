package com.example.enjeela.finalassignment.response

import com.example.enjeela.finalassignment.entity.Comicss

data class GetComicssResponse(
    val success : Boolean? = null,
    val data : MutableList<Comicss>? = null,
    val alldata:MutableList<Comicss>?=null
)