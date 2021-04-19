package com.example.riya.finalassignment.repository

import com.example.riya.finalassignment.api.ContactAPI
import com.example.riya.finalassignment.api.MyApiRequest
import com.example.riya.finalassignment.api.ServiceBuilder
import com.example.riya.finalassignment.entity.Contact
import com.example.riya.finalassignment.response.ContactResponse


class ContactRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(ContactAPI::class.java)

    suspend fun contactUser(contact: Contact) : ContactResponse {
        return apiRequest {
            myApi.contactUser(contact)
        }
    }
}