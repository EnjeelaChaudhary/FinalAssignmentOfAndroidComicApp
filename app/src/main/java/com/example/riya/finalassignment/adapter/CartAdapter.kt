package com.example.riya.finalassignment.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.riya.finalassignment.AddToCartActivity
import com.example.riya.finalassignment.CheckoutActivity
import com.example.riya.finalassignment.R
import com.example.riya.finalassignment.api.ServiceBuilder
import com.example.riya.finalassignment.fragments.AboutUsFragment
import com.example.riya.finalassignment.fragments.DashboardFragement
import com.example.riya.finalassignment.fragments.HomeFragment
import com.example.riya.finalassignment.model.CartModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CartAdapter(
        val lstcart: MutableList<CartModel>,
        val context: Context
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
var data=""
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var tvname: TextView
        var tvprice: TextView
        var tvAvailability: TextView
        var tvDesc: TextView
        val btnDelete: Button
        val btncheckout:Button
        var imgProfile:ImageView


        init {

            tvname=itemView.findViewById(R.id.tvname)

            tvprice=itemView.findViewById(R.id.tvprice)
            imgProfile=itemView.findViewById(R.id.imgProfile)
            tvAvailability=itemView.findViewById(R.id.tvAvailability)
            tvDesc=itemView.findViewById(R.id.tvDesc)
            btnDelete=itemView.findViewById(R.id.btnDelete)
            btncheckout=itemView.findViewById(R.id.btncheckout)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lstcart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = lstcart[position]
        holder.tvname.text = cart.ComicName
        holder.tvprice.text = cart.ComicPrice
        holder.tvAvailability.text = cart.ComicAvailable
        holder.tvDesc.text = cart.ComicDesc
        var image= ServiceBuilder.loadImagePath()+cart.ComicImage
        data=cart.ComicImage.toString()
        Toast.makeText(context, "${image}", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "data${data}", Toast.LENGTH_SHORT).show()
        Glide.with(context).load(image).into(holder.imgProfile)


        holder.btnDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Remove Cart!!")
                builder.setMessage("Are you sure you want to remove cart??")
                builder.setIcon(android.R.drawable.ic_delete)
                builder.setPositiveButton("Yes") { _, _ ->
                    lstcart.removeAt(position);
                    notifyDataSetChanged();
                }
                builder.setNegativeButton("No") { _, _ ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        })



        holder.btncheckout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Order Cart!!")
                builder.setMessage("Thankyou for ordering, Keep in touch!!")
                builder.setIcon(android.R.drawable.ic_delete)
                builder.setPositiveButton("Ok") { _, _ ->
                    val intent = Intent(context, AboutUsFragment::class.java)
//                    intent.putExtra("CheckOut Sucessfully!!!", cart)
                    context.startActivity(intent)

                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        })





    }
}