package com.abhi.amplify_store.services.foreground

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.abhi.amplify_store.MyApp
import com.abhi.amplify_store.R
import com.abhi.amplify_store.services.MyServiceManager
import com.abhi.amplify_store.services.ServiceActivity
import timber.log.Timber

/*
* Foreground Service
* */

class MyForegroundService: Service() {

    private lateinit var myServiceManager: MyServiceManager

    private val CHANNEL_ID = "1000"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Timber.d("Foreground Service Create")
        myServiceManager = (application as MyApp).myServiceManager
        super.onCreate()
    }

    override fun onDestroy() {
        Timber.d("Foreground Service Destroy")
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val shouldStart = intent?.hasExtra("EXTRA_COMMAND_START")?: true
        val shouldStop = intent?.hasExtra("EXTRA_COMMAND_STOP")?: false

        if(shouldStart){
            Timber.d("EXTRA_COMMAND_START")

            // Fix: Pass the mandatory service type for targetSDK 37 (e.g., DATA_SYNC)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startForeground(
                    1,
                    newNotification(),
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC // Must match Manifest!
                )
            } else {
                startForeground(1, newNotification())
            }

            Timber.d("onStartCommand: ForegroundService")

            myServiceManager.stateFore.value = MyServiceManager.ForeState.STARTED
        }
        if(shouldStop) {
            Timber.d("EXTRA_COMMAND_STOP")
            stopSelf()
            return START_NOT_STICKY
        }

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun newNotification(): Notification {
        val intent = Intent(this, ServiceActivity::class.java)

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        createServiceNotificationChannel()

        return Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("FS Notification title")
            .setContentText("FS Notification text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createServiceNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "FS Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val manager = getSystemService(NotificationManager::class.java)
        manager?.createNotificationChannel(channel)
    }
}
