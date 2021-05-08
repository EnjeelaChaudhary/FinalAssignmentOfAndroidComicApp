package com.example.enjeela.finalassignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.enjeela.finalassignment.fragments.AboutUsFragment
import com.example.enjeela.finalassignment.fragments.ConnectwithusFragment
import com.example.enjeela.finalassignment.fragments.ContactUsFragment

class Pageadapter(fm : FragmentManager, val fragmentCount : Int):
    FragmentPagerAdapter(fm) {
    private val fragmentTitleList = mutableListOf( "About us","Contact us")
//    private val mFragmentList= ArrayList<Fragment>()
//    private val mFragmentTitle = ArrayList<String>()




    override fun getItem(position: Int): Fragment {
        when(position){
//            0-> return AboutUsFragment()
            0-> return ConnectwithusFragment()
            1-> return ContactUsFragment()

            else -> return AboutUsFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }
//    fun addFragment(fragment: Fragment,title:String){
//        mFragmentList.add(fragment)
//        mFragmentTitle.add(title)
//    }
    override fun getCount(): Int =fragmentCount

}