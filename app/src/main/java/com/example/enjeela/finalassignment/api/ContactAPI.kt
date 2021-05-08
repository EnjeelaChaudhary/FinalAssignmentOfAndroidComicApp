package com.example.enjeela.finalassignment.api


import com.example.enjeela.finalassignment.entity.Contact
import com.example.enjeela.finalassignment.response.ContactResponse


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactAPI {
    @POST("user/contact")
    suspend fun contactUser(
        @Body contact: Contact
    ): Response<ContactResponse>

}