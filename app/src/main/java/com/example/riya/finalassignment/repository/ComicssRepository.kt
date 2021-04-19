package com.example.riya.finalassignment.repository

import com.example.riya.finalassignment.api.MyApiRequest
import com.example.riya.finalassignment.api.ComicssAPI
import com.example.riya.finalassignment.api.ServiceBuilder
import com.example.riya.finalassignment.response.GetComicssResponse



class ComicssRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(ComicssAPI::class.java)

    suspend fun getComic() : GetComicssResponse {
        return apiRequest {
            myApi.getNoodle(
                    ServiceBuilder.comic!!
            )
        }
    }
    suspend fun getsafety() : GetComicssResponse {
        return apiRequest {
            myApi.getComic(
                    ServiceBuilder.safety!!
            )
        }
    }


    suspend fun getAllComic() : GetComicssResponse {
        return apiRequest {
            myApi.getAllComic()

        }
    }

}