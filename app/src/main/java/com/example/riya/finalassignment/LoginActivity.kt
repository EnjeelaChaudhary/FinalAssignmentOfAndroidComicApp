package com.example.riya.finalassignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.riya.finalassignment.api.ServiceBuilder
import com.example.riya.finalassignment.repository.CustomerRepository
import com.google.android.material.snackbar.Snackbar

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class LoginActivity : AppCompatActivity() {

    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var RegisterHere: TextView
    private lateinit var linearLayout: LinearLayout


    var username=""
    var password=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)


        inputUsername = findViewById(R.id.inputUsername)
        inputPassword = findViewById(R.id.inputPassword)
        btnLogin = findViewById(R.id.btnLogin)
        RegisterHere = findViewById(R.id.RegisterHere)



        btnLogin.setOnClickListener {
            login()
        }
        RegisterHere.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }


    }


    private fun login() {
        username = inputUsername.text.toString()
        password = inputPassword.text.toString()

        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
        Toast.makeText(
                this@LoginActivity,
                "Username and password saved",

                Toast.LENGTH_SHORT
        ).show()
        Toast.makeText(
                this@LoginActivity,
                "Login Sucessfully!!!",

                Toast.LENGTH_SHORT
        )

        try {
            CoroutineScope(Dispatchers.IO).launch {
                val repository = CustomerRepository()
                val response = repository.checkUser(username, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer ${response.token}"
//                    ServiceBuilder.username = "${response.data}"
//                    ServiceBuilder.userData=response.userData!!
//                    ServiceBuilder.id = response.id

//                    saveUsernamePassword()
                    startActivity(
                            Intent(
                                    this@LoginActivity,
                                    NavHomeActivity::class.java
                            )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                                Snackbar.make(
                                        linearLayout,
                                        "Invalid credentials",
                                        Snackbar.LENGTH_LONG
                                )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }
            }
        } catch (ex: IOException) {

            Toast.makeText(
                    this@LoginActivity,
                    ex.toString(),
                    Toast.LENGTH_SHORT
            ).show()

        }


    }



}






//    private fun getSharedPref(){
//        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
//        val username = sharedPref.getString("username","")
//        val password = sharedPref.getString("password","")
//        Toast.makeText(this, "Username:$username and password : $password", Toast.LENGTH_SHORT).show()
//    }




