package com.example.enjeela.finalassignment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.example.enjeela.finalassignment.api.ServiceBuilder
import com.example.enjeela.finalassignment.channel.NotificationChannels
import com.example.enjeela.finalassignment.entity.Cart
import com.example.enjeela.finalassignment.entity.Comicss
import com.example.enjeela.finalassignment.repository.CartRepository
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddToCartActivity : AppCompatActivity(),View.OnClickListener{
    private lateinit var itemImage:ImageView
    private lateinit var txtName:TextView
    private lateinit var txtPrice:TextView
    private lateinit var txtAvailability:TextView
    private lateinit var txtDesc:TextView
    private lateinit var addtocart:ExtendedFloatingActionButton
    var image=""
    var data=""

 var addcart: Comicss? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)

        itemImage = findViewById(R.id.itemImage)
        txtName = findViewById(R.id.txtName)
        txtPrice = findViewById(R.id.txtPrice)
        txtAvailability = findViewById(R.id.txtAvailability)
        txtDesc = findViewById(R.id.txtDesc)
        addtocart = findViewById(R.id.addtocart)

        addtocart.setOnClickListener(this)

          addcart=intent.getParcelableExtra("data")
        if (addcart!=null){
            image= ServiceBuilder.loadImagePath()
            image+= addcart?.ComicssImage
            ServiceBuilder.image=addcart?.ComicssImage.toString()
            Glide.with(this)
                    .load(image)
                    .into(itemImage )
            txtName.setText(addcart?.ComicssName)
            txtPrice.setText(addcart?.ComicssPrice)
            txtAvailability.setText(addcart?.ComicssAvailable)
            txtDesc.setText(addcart?.ComicssDesc)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addtocart->{
                addToCart()
                AddProductsNotification()
            }
        }
    }

    private fun addToCart() {
        Toast.makeText(this, "path ${ServiceBuilder.image}", Toast.LENGTH_SHORT).show()

        val ComicName=txtName.text.toString()
        val ComicImage=ServiceBuilder.image
        val ComicPrice=txtPrice.text.toString()
        val ComicAvailable=txtAvailability.text.toString()
        val ComicDesc=txtDesc.text.toString()
        val username="${ServiceBuilder.username}"

        val cart= Cart(ProductName = ComicName, ProductImage = ComicImage,ProductPrice  =ComicPrice,ProductAvailable = ComicAvailable,ProductDesc = ComicDesc)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cartRepository= CartRepository()
                val response =cartRepository.addcart(cart)
                if (response.success==true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddToCartActivity, "Added", Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddToCartActivity, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
    }
    private fun AddProductsNotification(){
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannels = NotificationChannels( this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Add Comicss")
            .setContentText("Comicss added succesfully")
            .setColor(Color.BLUE)
            .build()
        notificationManager.notify(1, notification)
    }
}