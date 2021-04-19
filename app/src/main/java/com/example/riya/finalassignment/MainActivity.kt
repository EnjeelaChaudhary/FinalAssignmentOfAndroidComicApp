package com.example.riya.finalassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var btnprofile: Button

    private lateinit var btnAvailableComic:Button
    private lateinit var btnabout:Button
    private lateinit var btnaddtocart:Button
    private lateinit var btnwishlist:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnprofile = findViewById(R.id.btnprofile)

        btnabout = findViewById(R.id.btnabout)
        btnaddtocart = findViewById(R.id.btnaddtocart)
        btnwishlist = findViewById(R.id.btnwishlist)

        btnAvailableComic=findViewById(R.id.btnAvailableComic)




//        btnabout.setOnClickListener{
//            val intent = Intent(this, AboutUs::class.java)
//            startActivity(intent)
//        }

//        btnmedicine.setOnClickListener{
//            val intent = Intent(this, ViewMedicineActivity::class.java)
//            startActivity(intent)
//        }

    }
}