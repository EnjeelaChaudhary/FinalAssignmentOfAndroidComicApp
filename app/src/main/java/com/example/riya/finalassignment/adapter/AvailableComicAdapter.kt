package com.example.riya.finalassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.riya.finalassignment.AddToCartActivity
import com.example.riya.finalassignment.R
import com.example.riya.finalassignment.api.ServiceBuilder
import com.example.riya.finalassignment.entity.Comicss

class AvailableComicAdapter(
    val safetyList: MutableList<Comicss>,
    val context: Context
): RecyclerView.Adapter<AvailableComicAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var Imges: ImageView
        var Name: TextView

        var value: TextView
        var cart: Button

        init {

            Imges=itemView.findViewById(R.id.item_image)

            Name=itemView.findViewById(R.id.name)

            value=itemView.findViewById(R.id.value)
            cart=itemView.findViewById(R.id.cart)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.availablecomic_lauyout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return safetyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val safety = safetyList[position]
//        holder.Name.text = safety.ProductName
//        holder.value.text = safety.ProductPrice
//        var image= ServiceBuilder.loadImagePath()
//        image+=safety.ProductImage
//        Glide.with(context)
//                .load(image)
//                .into(holder.Imges)

        holder. cart.setOnClickListener() {
//            val intent = Intent(context, AddToCartActivity::class.java)
//            intent.putExtra("data",safety)
//            context.startActivity(intent)
        }



    }
}