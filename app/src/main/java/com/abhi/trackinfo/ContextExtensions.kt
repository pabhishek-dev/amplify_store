package com.abhi.trackinfo

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.abhi.trackinfo.services.foreground.MyForegroundService
import com.abhi.trackinfo.services.regular.MyService


fun Context.startService() {
    val intent = Intent(this, MyService::class.java)
    intent.putExtra("EXTRA_COMMAND_START", true)
    startService(intent)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.startForeService() {
    val intent = Intent(this, MyForegroundService::class.java)
    intent.putExtra("EXTRA_COMMAND_START", true)
    startForegroundService(intent)
}


fun Context.stopService() {
    val intent = Intent(this, MyService::class.java)
    intent.putExtra("EXTRA_COMMAND_STOP", true)
    stopService(intent)
}
