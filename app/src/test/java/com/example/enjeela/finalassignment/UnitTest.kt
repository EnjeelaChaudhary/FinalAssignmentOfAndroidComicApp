package com.example.enjeela.finalassignment

import com.example.enjeela.finalassignment.api.ServiceBuilder
import com.example.enjeela.finalassignment.entity.Consumer
import com.example.enjeela.finalassignment.entity.Contact
import com.example.enjeela.finalassignment.repository.CartRepository
import com.example.enjeela.finalassignment.repository.ContactRepository
import com.example.enjeela.finalassignment.repository.CustomerRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
private lateinit var customerRepository: CustomerRepository
private lateinit var contactRepository: ContactRepository
private lateinit var cartRepository: CartRepository

class UnitTest {

    @Test
fun logincheck() = runBlocking {
    customerRepository= CustomerRepository()
        val response = customerRepository.checkUser("riya","11")
        val expectedResult= true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun checkRegister()= runBlocking {
        val consumer= Consumer( fullname = "Riya Pandey", username = "reeya", address = "Lazimpat",
            contact = "9812367",email = "yina@gmail.com", password = "reeya")
        customerRepository= CustomerRepository()
        val response=customerRepository.registerUser(consumer)
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun checkProfile() = runBlocking {
        customerRepository= CustomerRepository()
        ServiceBuilder.token="Bearer " +customerRepository.checkUser("reeya","reeya").token
        val response=customerRepository.getCitizen()
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun checkCart() = runBlocking {
        cartRepository= CartRepository()
        customerRepository= CustomerRepository()
        ServiceBuilder.token="Bearer " +customerRepository.checkUser("reeya","reeya").token
        val response=cartRepository.getCart()
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun checkContact()= runBlocking {
        val contact=Contact( fullname = "riya pandey",
            email = "riu99@gmail.com", phone="98765",message="this is for products")
        contactRepository= ContactRepository()
        val response=contactRepository.contactUser(contact)
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }





}