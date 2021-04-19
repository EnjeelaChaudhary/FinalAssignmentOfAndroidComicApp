package com.riya.essewearos

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class DashBoardActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        // Enables Always-on
        setAmbientEnabled()
    }
}