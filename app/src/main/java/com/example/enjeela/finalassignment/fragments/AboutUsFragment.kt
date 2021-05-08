package com.example.enjeela.finalassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.enjeela.finalassignment.R
import com.example.enjeela.finalassignment.adapter.Pageadapter
import com.google.android.material.tabs.TabLayout

class AboutUsFragment : Fragment() {
    private lateinit var viewpager: ViewPager
    private lateinit var tablayout: TabLayout
//    private val myContext = AboutUsFragment()


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      val view= inflater.inflate(R.layout.fragment_about_us, container, false)
        viewpager=view.findViewById(R.id.viewpager)
        tablayout=view.findViewById(R.id.tablayout)
        return view

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()

    }

    private fun configureTopNavigation(){
        viewpager.adapter = Pageadapter(childFragmentManager,2)
        viewpager.offscreenPageLimit = 2
        tablayout.setupWithViewPager(viewpager)

    }
}