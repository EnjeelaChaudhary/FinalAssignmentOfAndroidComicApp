package com.example.riya.finalassignment.repository

import com.example.riya.finalassignment.api.ConsumerAPI
import com.example.riya.finalassignment.api.MyApiRequest
import com.example.riya.finalassignment.api.ServiceBuilder
import com.example.riya.finalassignment.entity.Consumer
import com.example.riya.finalassignment.response.GetProfileResponse
import com.example.riya.finalassignment.response.LoginResponse
import com.example.riya.finalassignment.response.LogoutResponse
import com.example.riya.finalassignment.response.UpdatePurchaserResponse

class CustomerRepository: MyApiRequest() {
    val myApi = ServiceBuilder.buildService(ConsumerAPI::class.java)

    suspend fun registerUser(consumer: Consumer): LoginResponse {
        return apiRequest {
            myApi.registerUser(consumer)
        }
    }

    suspend fun checkUser(username: String, password: String): LoginResponse {
        return apiRequest {
            myApi.checkUser(username, password)
        }
    }

        suspend fun getCitizen(): GetProfileResponse {
        return apiRequest {
            myApi.getProfile(
                    ServiceBuilder.token!!,
            )
        }
    }


    suspend fun updateUser(_id : String, consumer: Consumer) : UpdatePurchaserResponse {
        return apiRequest {
            myApi.updateUser(ServiceBuilder.token!!, _id, consumer)
        }
    }
//    suspend fun uploadImage(Cemail: String, body: MultipartBody.Part)
//            : ImageResponse {
//        return apiRequest {
//            myApi.uploadImage(ServiceBuilder.token!!,Cemail, body)
//        }
//    }
    suspend fun logout(): LogoutResponse {
        return apiRequest {
            myApi.logout(ServiceBuilder.token!!)
        }
    }
}