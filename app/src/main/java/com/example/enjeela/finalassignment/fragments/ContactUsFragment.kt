package com.example.enjeela.finalassignment.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.enjeela.finalassignment.GoogleMapActivity
import com.example.enjeela.finalassignment.R
import com.example.enjeela.finalassignment.entity.Contact
import com.example.enjeela.finalassignment.repository.ContactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ContactUsFragment : Fragment() {
    private lateinit var tvgoogle: Button


    private lateinit var fullname: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var message: EditText
    private lateinit var btnSubmit:Button

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_contact_us, container, false)
        tvgoogle=view.findViewById(R.id.tvgoogle)

        fullname= view.findViewById(R.id.fullname)
        email= view.findViewById(R.id.email)
        phone= view.findViewById(R.id.phone)
        message= view.findViewById(R.id.message)
        btnSubmit= view.findViewById(R.id.btnSubmit)

        tvgoogle.setOnClickListener {
            startActivity(Intent(context  , GoogleMapActivity::class.java))
        }
        btnSubmit.setOnClickListener{
            adduser()
        }
        return view
    }
    private fun adduser(){
        val fullname = fullname.text.toString()
        val email = email.text.toString()
        val phone = phone.text.toString()
        val message = message.text.toString()



        val contact = Contact(fullname = fullname, email = email, phone=phone, message=message)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val contactRepository = ContactRepository()
                val response = contactRepository.contactUser(contact)
                if (response.success == true) {

                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, " Contact added Successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error while sending!!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    }

