package com.example.riya.finalassignment.api


import com.example.riya.finalassignment.response.GetComicssResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ComicssAPI {

    @GET("comic/all/{ComicType}")
    suspend fun getComic(
        @Path("ComicType") ComicssType: String
    ): Response<GetComicssResponse>

    @GET("comic/all/{ComicType}")
    suspend fun getNoodle(
        @Path("ComicType") ComicssType: String
    ): Response<GetComicssResponse>

    @GET("comic/all")
    suspend fun getAllComic(
    ): Response<GetComicssResponse>
}