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

class ComicAdapter (
    val medList: MutableList<Comicss>,
    val context: Context
): RecyclerView.Adapter<ComicAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var Imges: ImageView
        var Name: TextView
        var value: TextView
        var cart: Button

        init {
            Imges = itemView.findViewById(R.id.item_image)
            Name = itemView.findViewById(R.id.name)
            value = itemView.findViewById(R.id.value)
            cart = itemView.findViewById(R.id.cart)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.comicview_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return medList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val med = medList[position]
        holder.Name.text = med.ComicssName
        holder.value.text = med.ComicssPrice
        var image = ServiceBuilder.loadImagePath()
        image += med.ComicssImage
        image = image.replace("\\", "/")

        Glide.with(context)
                .load(image)
                .into(holder.Imges)

        holder.cart.setOnClickListener() {
            val intent = Intent(context, AddToCartActivity::class.java)
            intent.putExtra("data", med)
            context.startActivity(intent)
        }

    }
}