package com.riya.essewearos

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.riya.essewearos.api.ServiceBuilder
import com.riya.essewearos.repository.PurchaserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : WearableActivity() {
private lateinit var  username:EditText
private lateinit var password:EditText
private lateinit var btnlogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById(R.id.username)
        password =findViewById(R.id.password)
        btnlogin= findViewById(R.id.btnlogin)


        btnlogin.setOnClickListener{
            login()
        }


        // Enables Always-on
        setAmbientEnabled()
    }

    private fun login() {
        val username = username.text.toString()
        val password = password.text.toString()




        try {
            CoroutineScope(Dispatchers.IO).launch {
                val repository = PurchaserRepository()
                val response = repository.checkUser(username, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer ${response.token}"
                    withContext(Dispatchers.Main) {

                        Toast.makeText(this@MainActivity, "Logged in", Toast.LENGTH_SHORT).show()
                    }
                    startActivity(
                        Intent(
                            this@MainActivity,
                            DashBoardActivity::class.java
                        )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {

                        Toast.makeText(this@MainActivity, "Invalid Login/passowrd", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        catch (ex: Exception) {

            Toast.makeText(
                this,"Login error",
                Toast.LENGTH_SHORT
            ).show()

        }

    }
}