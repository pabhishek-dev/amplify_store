package com.abhi.amplify_store.services.regular

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.abhi.amplify_store.MyApp
import com.abhi.amplify_store.services.MyServiceManager
import timber.log.Timber

/*
* Regular Service
* */

class MyService: Service() {

    private lateinit var myServiceManager: MyServiceManager

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Timber.d("Regular Service Create")
        myServiceManager = (application as MyApp).myServiceManager
        super.onCreate()
    }

    override fun onDestroy() {
        Timber.d("Regular Service Destroy")
        myServiceManager.updateState(MyServiceManager.State.STOPPED)
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if(intent?.hasExtra("EXTRA_COMMAND_START")?: true) Timber.d("EXTRA_COMMAND_START")
        if(intent?.hasExtra("EXTRA_COMMAND_STOP")?: true) {
            Timber.d("EXTRA_COMMAND_STOP")
            stopSelf()
        }

        Timber.d("onStartCommand: ")
        myServiceManager.updateState(MyServiceManager.State.STARTED)
        return super.onStartCommand(intent, flags, startId)
    }
}
