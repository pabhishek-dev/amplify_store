package com.abhi.trackinfo.broadcast

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity

class BroadcastActivity: ComponentActivity() {

    lateinit var airplaneModeReceiver: AirplaneModeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = IntentFilter("android.intent.action.AIRPLANE_MODE")

        airplaneModeReceiver = AirplaneModeReceiver()
        registerReceiver(airplaneModeReceiver, intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airplaneModeReceiver)
    }
}
