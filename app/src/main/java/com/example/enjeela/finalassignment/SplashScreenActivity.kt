package com.example.enjeela.finalassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.enjeela.finalassignment.api.ServiceBuilder
import com.example.enjeela.finalassignment.repository.CustomerRepository
import kotlinx.coroutines.*
import java.io.IOException

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun checkLogin() {
        val sharedPref=getSharedPreferences("MyPref", MODE_PRIVATE)
        val username=sharedPref.getString("username","")
        val password=sharedPref.getString("password","")
        if (username != null && !username.equals("")) {

            startActivity(Intent(this@SplashScreenActivity,NavHomeActivity::class.java))
        }
        else{

            startActivity(Intent(this@SplashScreenActivity,LoginActivity::class.java))
        }
        CoroutineScope(Dispatchers.IO).launch {
            if (username == null && password == null) {
                withContext(Dispatchers.Main) {
                    startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                    finish()
                }
            }
            else {
                try {
                    val repository = CustomerRepository()
                    val response = repository.checkUser(username.toString(), password.toString())
                    if (response.success == true) {
                        ServiceBuilder.token = "Bearer ${response.token}"
                        ServiceBuilder.id="${response.id}"
//                        ServiceBuilder.userData=response.userData!!
                        startActivity(Intent(this@SplashScreenActivity, NavHomeActivity::class.java))
                        finish()
                    }
                }
                catch (ex: IOException) {
                    withContext(Dispatchers.Main) {
                        startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }

    }

}