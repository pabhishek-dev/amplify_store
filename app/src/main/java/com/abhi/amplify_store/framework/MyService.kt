package com.abhi.amplify_store.framework

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyService: Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    private val binder = LocalBinder()

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun getRandomNumber(): Int = (0..100).random()
}
