package com.abhi.amplify_store.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent?.action !=null && intent.action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){

            var isAirplaneModeOn : Boolean = intent.getBooleanExtra("state", false)
        }
    }
}
