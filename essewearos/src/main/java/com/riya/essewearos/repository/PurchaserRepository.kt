package com.riya.essewearos.repository

import com.riya.essewearos.api.MyApiRequest
import com.riya.essewearos.api.PurchaserApi
import com.riya.essewearos.api.ServiceBuilder
import com.riya.essewearos.response.LoginResponse

class PurchaserRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(PurchaserApi::class.java)

//    suspend fun registerUser(purchaser: Purchaser) : LoginResponse {
//        return apiRequest {
//            myApi.registerUser(purchaser)
//        }
//    }
    suspend fun checkUser(username : String, password : String): LoginResponse {
        return apiRequest {
            myApi.checkUser(username, password)
        }
    }

//    suspend fun updateUser(_id : String, purchaser: Purchaser) : UpdatePurchaserResponse {
//        return apiRequest {
//            myApi.updateUser(ServiceBuilder.token!!, _id, purchaser)
//        }
//    }
//    //    suspend fun logout(): LogoutResponse{​​​​​​
////        return apiRequest{​​​​​​
////        myApi.logout(ServiceBuilder.token!!)
////        }​​​​​​
////    }​​​​​​
//    suspend fun logout() : LogoutResponse {
//        return apiRequest {
//            myApi.logout(ServiceBuilder.token!!)
//        }
//    }

}