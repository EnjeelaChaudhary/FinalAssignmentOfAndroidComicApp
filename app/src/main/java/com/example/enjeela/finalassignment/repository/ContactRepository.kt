package com.example.enjeela.finalassignment.repository

import com.example.enjeela.finalassignment.api.ContactAPI
import com.example.enjeela.finalassignment.api.MyApiRequest
import com.example.enjeela.finalassignment.api.ServiceBuilder
import com.example.enjeela.finalassignment.entity.Contact
import com.example.enjeela.finalassignment.response.ContactResponse


class ContactRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(ContactAPI::class.java)

    suspend fun contactUser(contact: Contact) : ContactResponse {
        return apiRequest {
            myApi.contactUser(contact)
        }
    }
}