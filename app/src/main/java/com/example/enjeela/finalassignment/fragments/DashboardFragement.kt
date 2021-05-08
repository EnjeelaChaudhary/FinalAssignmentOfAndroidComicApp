package com.example.enjeela.finalassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enjeela.finalassignment.R
import com.example.enjeela.finalassignment.adapter.ComicAdapter
import com.example.enjeela.finalassignment.adapter.AvailableComicAdapter
import com.example.enjeela.finalassignment.api.ServiceBuilder
import com.example.enjeela.finalassignment.model.ComicModel
import com.example.enjeela.finalassignment.model.availablecomicModel
import com.example.enjeela.finalassignment.repository.ComicssRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class DashboardFragement : Fragment() {
    //    private var lstpopular = ArrayList<PopularCategoryModel>()
    private lateinit var recyclerview_comic: RecyclerView
    private val comiclist = ArrayList<ComicModel>()

    private lateinit var recyclerview_safety: RecyclerView
    private val safetylist = ArrayList<availablecomicModel>()

    private lateinit var cart: TextView
    private lateinit var tvmed: TextView
    private lateinit var tvsafety: TextView


//    private lateinit var sensorManager: SensorManager
//    private var lightSensor: Sensor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerview_comic = view.findViewById(R.id.recyclerview_medicine)

        tvmed = view.findViewById(R.id.tvmed)
        tvsafety = view.findViewById(R.id.tvsafety)


        recyclerview_safety = view.findViewById(R.id.recyclerview_safety)
//        recycleView_frozen = view.findViewById(R.id.recycleView_frozen)
//        recycleView_fruit = view.findViewById(R.id.recycleView_fruit)
//        recycleView_veg = view.findViewById(R.id.recycleView_veg)


        ServiceBuilder.medicine = tvsafety.text.toString()
        loadMedicine()
        ServiceBuilder.safety = tvmed.text.toString()
        LoadSafety()



        return view
    }

//    // adding
//    private fun checkSensor(): Boolean {
//        var flag = true
//        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null) {
//            flag = false
//        }
//        return flag
//    }
//
//    private fun lightSensor() {
//        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        if (!checkSensor())
//            return
//        else {
//            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
//            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }
//    }
//
//    override fun onSensorChanged(event: SensorEvent?) {
//        val values = event!!.values[0]
//        if (lightSensor!!.type == Sensor.TYPE_LIGHT) {
//            if (values <= 50) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }
//        }
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//    }

    private fun LoadSafety() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository = ComicssRepository()
                val response = productRepository.getComic()
                val lstProduct = response.alldata!!
                withContext(Dispatchers.Main) {

                    recyclerview_safety.adapter =
                        lstProduct?.let { AvailableComicAdapter(it, requireContext()) }
                    recyclerview_safety.layoutManager =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }


        }
    }

    private fun loadMedicine() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository = ComicssRepository()
                val response = productRepository.getsafety()
                val lstProduct = response.alldata!!
                withContext(Dispatchers.Main) {

                    recyclerview_comic.adapter =
                        lstProduct?.let { ComicAdapter(it, requireContext()) }
                    recyclerview_comic.layoutManager =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }


    }
}


