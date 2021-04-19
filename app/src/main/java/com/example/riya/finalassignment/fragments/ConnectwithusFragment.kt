package com.example.riya.finalassignment.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.riya.finalassignment.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import java.util.*


class ConnectwithusFragment : Fragment() {

    private lateinit var tvgoogle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {



        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view =    inflater.inflate(R.layout.fragment_connectwithus, container, false)


      val adsElement = Element()
       val aboutPage: View = AboutPage(context)
                .isRTL(false)
                .setDescription(" This is full package of Comics For you as your choice you can buy and read the comics. " +
                        "We are physically available in BalajuHeight-Kathmandu. You can direct to us using goolge map given " +
                        "a trust and won it.")
                .addItem(Element().setTitle("Esse- A Pharmacy for you"))
                .addGroup("CONNECT WITH US!")
                .addEmail("Comic@gmail.com ")
                .addWebsite(" website/")
                .addYoutube("blabla.com")
                .addPlayStore("com.example.esse")
                .addItem(createCopyright())
                .create()

        return (aboutPage);



    }
    private fun createCopyright(): Element {
        val copyright = Element()
        @SuppressLint("DefaultLocale") val copyrightString = String.format(
                "Copyright %d by Your Name",
                Calendar.getInstance()[Calendar.YEAR]
        )
        copyright.setTitle(copyrightString)
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER)
        copyright.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                    context,
                    copyrightString,
                    Toast.LENGTH_SHORT
            ).show()
        })
        return copyright
    }



    companion object {

    }
}


