package com.example.riya.finalassignment.api


import com.example.riya.finalassignment.entity.Contact
import com.example.riya.finalassignment.response.ContactResponse


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactAPI {
    @POST("user/contact")
    suspend fun contactUser(
        @Body contact: Contact
    ): Response<ContactResponse>

}