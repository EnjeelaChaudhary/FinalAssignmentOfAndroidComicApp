package com.example.riya.finalassignment.fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.riya.finalassignment.LoginActivity
import com.example.riya.finalassignment.R
import com.example.riya.finalassignment.api.ServiceBuilder
import com.example.riya.finalassignment.entity.Consumer
import com.example.riya.finalassignment.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest


class ProfileFragment : Fragment() {
    private lateinit var txtName: TextView
    private lateinit var Address: TextView
    private lateinit var txtAddress: TextView
    private lateinit var txtContact: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtUser:TextView
    private lateinit var edit: Button
    private lateinit var logout:Button


    private lateinit var etfullname: EditText
    private lateinit var etusername: EditText
    private lateinit var etcontact: EditText
    private lateinit var etaddress: EditText
    private lateinit var etemail:EditText
    private lateinit var btnSave: Button
    private lateinit var itemImage: ImageView
    private lateinit var btncamera:Button
    lateinit var popAddPost: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=  inflater.inflate(R.layout.fragment_profile, container, false)
        txtName =view.findViewById(R.id.txtName)
        txtAddress=view.findViewById(R.id.txtAddress)
        txtContact=view.findViewById(R.id.txtContact)
        txtEmail=view.findViewById(R.id.txtEmail)
        txtUser=view.findViewById(R.id.txtUser)
        edit = view.findViewById(R.id.edit)
        itemImage = view.findViewById(R.id.itemImage)
        logout = view.findViewById(R.id.logout)

        val data = ServiceBuilder.userData!!
        txtName.text=data[0].fullname
        txtUser.text=data[0].username
        txtAddress.text=data[0].address
        txtContact.text=data[0].contact
        txtEmail.text=data[0].email


        itemImage.setOnClickListener{
            loadPopUpMenu()
        }
        logout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Logout User")
            builder.setMessage("Are you sure you want to logout??")
            builder.setIcon(android.R.drawable.ic_delete)
            builder.setPositiveButton("Yes") { _, _ ->
                logOut()
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }


        popupWindow(view)
        iniPopup(view,data)

        return view

    }


//    private fun getProfile() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val userRepository = CustomerRepository()
//                val response = userRepository.getCitizen()
//                if (response.success == true) {
//                    val listprofile = response.profile
//                    if (listprofile != null) {
//                        withContext(Dispatchers.Main) {
//                            Log.d("Debug:", "Your data:" + listprofile)
//                            txtName.text = listprofile.fullname
//                            txtUser.text = listprofile.username
//                            txtAddress.text = listprofile.address
//                            txtContact.text = listprofile.contact.toString()
//                            txtEmail.text = listprofile.email
//                            username = listprofile.username.toString()
//
////                            val imagePath = ServiceBuilder.loadImagePath() + listprofile[0].photo
////                            Glide.with(this@ProfileFragment)
////                                .load(imagePath)
//////                                    .fitCenter()
////                                .into(itemImage)
//
//                        }
//
//                    }
//
//                }
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        context,
//                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }

    private fun logOut() {
        CoroutineScope(Dispatchers.IO).launch {
            val userRepo = CustomerRepository()
            val response=userRepo.logout()
            if(response.success==true){withContext(Main)
            {activity?.getSharedPreferences("MyPref", MODE_PRIVATE)?.edit()?.clear()?.apply();
                val intent = Intent(context, LoginActivity::class.java);startActivity(intent);}
            }}
    }
//
    private fun loadPopUpMenu() {
        // Load pop up menu
        val popupMenu = context?.let { PopupMenu(it, itemImage) }
        if (popupMenu != null) {
            popupMenu.menuInflater.inflate(R.menu.gallery_camera, popupMenu.menu)
        }
        if (popupMenu != null) {
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menuCamera ->
                        openCamera()
                    R.id.menuGallery ->
                        openGallery()
                }
                true
            }
        }
        if (popupMenu != null) {
            popupMenu.show()
        }
    }

    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl = ""
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }
    override  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = requireActivity().contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                itemImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                itemImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }

    private fun bitmapToFile(bitmap: Bitmap, fileName: String): File? {
        var file: File? = null
        return try {
            file = File(
                requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileName
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }






    private fun popupWindow(root:View) {
        edit = root.findViewById(R.id.edit)
        edit.setOnClickListener {
            popAddPost.show()
        }
    }
    private fun iniPopup(root: View,data:MutableList<Consumer>) {
        popAddPost = Dialog(requireContext())
        popAddPost.setContentView(R.layout.profile_edit)
        popAddPost.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popAddPost.window?.setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        popAddPost.window?.attributes?.gravity = Gravity.CENTER;
        etfullname = popAddPost.findViewById(R.id.etfullname)
        etusername = popAddPost.findViewById(R.id.etusername)
        etaddress = popAddPost.findViewById(R.id.etaddress)
        etemail = popAddPost.findViewById(R.id.etemail)
        etcontact = popAddPost.findViewById(R.id.etcontact)
        btnSave=popAddPost.findViewById(R.id.btnSave)
        etfullname.setText(data[0].fullname)
        etcontact.setText(data[0].contact)
        etusername.setText(data[0].username)
        etemail.setText(data[0].email)
        etaddress.setText(data[0].address)

        btnSave.setOnClickListener {
            updateUser()
            popAddPost.dismiss()
        }

    }

    private fun updateUser() {
        val id=ServiceBuilder.id!!
        Toast.makeText(requireContext(), ""+id, Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = CustomerRepository()
                val user = Consumer(
                    fullname =  etfullname.text.toString(),
                    contact = etcontact.text.toString(),
                    email = etemail.text.toString(),
                    username = etusername.text.toString(),
                    address = etaddress.text.toString()
                )
                val response = userRepository.updateUser(id, user)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        ex.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {


    }
}