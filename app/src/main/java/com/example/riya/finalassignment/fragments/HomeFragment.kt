package com.example.riya.finalassignment.fragments

import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.riya.finalassignment.LoginActivity
import com.example.riya.finalassignment.R


class HomeFragment : Fragment() , SensorEventListener {
    private lateinit var btnprofile: Button
    private lateinit var btncomic: Button
    private lateinit var btncontact: Button
    private lateinit var btnabout: Button
    private lateinit var btnaddtocart: Button
    private lateinit var btnhome: Button
    private var sensor: Sensor? = null
    private lateinit var sensorManager: SensorManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        sensorManager = context?.getSystemService(SENSOR_SERVICE) as SensorManager

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        btnprofile = view.findViewById(R.id.btnprofile)
//        btnmedicine = view.findViewById(R.id.btnmedicine)
        btncontact = view.findViewById(R.id.btncontact)
        btnabout = view.findViewById(R.id.btnabout)
        btnaddtocart = view.findViewById(R.id.btnaddtocart)
        btnhome = view.findViewById(R.id.btnhome)

        if (!checkSensor())
            return view
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        btnprofile.setOnClickListener {
            val btnclick = ProfileFragment()
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, btnclick)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            }
        btncontact.setOnClickListener {
            val btnclick = ContactUsFragment()
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, btnclick)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        btnabout.setOnClickListener {
            val btnclick = AboutUsFragment()
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, btnclick)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }
btnhome.setOnClickListener {
    val btnclick = DashboardFragement()
    val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.fragment_container, btnclick)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()

}

        return view
    }



    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null) {
            flag = false
        }
        return flag
    }

    companion object {


    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[1]
        if (values < -2){
        val intent = Intent(
            context,
            ProfileFragment::class.java
        )
        startActivity(intent)
    }
        else if (values > 2) {
            val intent = Intent(
                context,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}