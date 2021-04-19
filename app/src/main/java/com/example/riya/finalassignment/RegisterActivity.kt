package com.example.riya.finalassignment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.riya.finalassignment.channel.NotificationChannels
import com.example.riya.finalassignment.entity.Consumer
import com.example.riya.finalassignment.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    private lateinit var fullname:EditText
    private lateinit var email:EditText
    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var address:EditText
    private lateinit var contact:EditText
    private lateinit var btnRegister:Button
    private lateinit var loginhere:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        fullname = findViewById(R.id.fullname)
        email = findViewById(R.id.email)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        address = findViewById(R.id.address)
        contact = findViewById(R.id.contact)
      loginhere = findViewById(R.id.loginhere)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener{
            if (fullname.text.isNullOrBlank() && email.text.isNullOrBlank() && username.text.isNullOrBlank() && password.text.isNullOrBlank()){
                Toast.makeText(this,"Please fill the required Fields!!", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,"${username.text} is logged in!",Toast.LENGTH_SHORT).show()
            }
            adduser()
            RegisteredNotification()

        }
        loginhere.setOnClickListener {
          startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun adduser(){
        val fullname = fullname.text.toString()
        val email = email.text.toString()
        val username = username.text.toString()
        val password = password.text.toString()
        val address = address.text.toString()
        val contact = contact.text.toString()



        val consumer = Consumer(fullname = fullname, username = username,
            address = address, contact = contact, email = email, password = password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val custRepository = CustomerRepository()
                val response = custRepository.registerUser(consumer)
                if (response.success == true) {

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegisterActivity, "Registered Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    }
                }
            }
            catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegisterActivity, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
    private fun RegisteredNotification(){
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannels = NotificationChannels( this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Registered")
            .setContentText("Welcome to Comic Family!!!")
            .setColor(Color.BLUE)
            .build()
        notificationManager.notify(1, notification)
    }

}
