package com.example.enjeela.finalassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.enjeela.finalassignment.fragments.*
import com.google.android.material.navigation.NavigationView
import hotchemi.android.rate.AppRate


class NavHomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toolbar: Toolbar
    private lateinit var drawer_layout: DrawerLayout
    private lateinit var nav_view: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppRate.with(this)
            .setInstallDays(3)
            .setLaunchTimes(3)
            .setRemindInterval(3)
            .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
        // Inflate
        setContentView(R.layout.activity_nav_home)
        nav_view = findViewById(R.id.nav_view)
        drawer_layout = findViewById(R.id.drawer_layout)

        nav_view.setNavigationItemSelectedListener(this)

        val toogle = ActionBarDrawerToggle(
                this, drawer_layout, findViewById(R.id.toolbar),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toogle)
        toogle.isDrawerIndicatorEnabled = true
        toogle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, HomeFragment())
                addToBackStack(null)
                commit()
            }
            nav_view.setCheckedItem(R.id.nav_home)


        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, HomeFragment())
                    addToBackStack(null)
                    commit()
                }
            }
            R.id.nav_profile -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, ProfileFragment())
                    addToBackStack(null)
                    commit()
                }
            }
            R.id.nav_about -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, AboutUsFragment())
                    addToBackStack(null)
                    commit()
                }
            }
            R.id.nav_contact -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, ContactUsFragment())
                    addToBackStack(null)
                    commit()
                }
            }
            R.id.nav_cart -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, CartFragment())
                    addToBackStack(null)
                    commit()
                }
            }
//            R.id.nav_checkout -> {
//                supportFragmentManager.beginTransaction().apply {
//                    replace(R.id.fragment_container, CheckoutActivity())
//                    addToBackStack(null)
//                    commit()
//                }
//
//            }
//            R.id.nav_safety -> {
//                supportFragmentManager.beginTransaction().apply {
//                    replace(R.id.fragment_container, SafetyProductFragment())
//                    addToBackStack(null)
//                    commit()
//                }
//
//            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}