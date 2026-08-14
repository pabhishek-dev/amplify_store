package com.abhi.amplify_store.services

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.visible
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.abhi.amplify_store.MyApp
import com.abhi.amplify_store.extensions.startForeService
import com.abhi.amplify_store.extensions.startService

class ServiceActivity : ComponentActivity() {

    private lateinit var myServiceManager: MyServiceManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val application = this.application as MyApp
            myServiceManager = application.myServiceManager
            ServiceScreen(
                onServiceClick = { state -> if(state) this.startService() else this.startForeService()},
                myServiceManager = myServiceManager
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        101
                    )
                }
            }
        }
    }
}

@Composable
fun ServiceScreen(onServiceClick: (Boolean) -> Unit, myServiceManager: MyServiceManager) {

    val currentState by myServiceManager.state.collectAsState()
    val currentForeState by myServiceManager.stateFore.collectAsState()

    val btnText = when (currentState) {
        MyServiceManager.State.NOT_STARTED -> "Service not started"
        MyServiceManager.State.STARTED -> "Service started"
        MyServiceManager.State.STOPPED -> "Service stopped"
    }

    val btnForeText = when (currentForeState) {
        MyServiceManager.ForeState.NOT_STARTED -> "Service not started"
        MyServiceManager.ForeState.STARTED -> "Service started"
        MyServiceManager.ForeState.STOPPED -> "Service stopped"
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(

        ) {

           // VideoPlayer("https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8")

            Button(
                modifier = Modifier
                    .visible(true),
                onClick = {
                    if (currentState == MyServiceManager.State.NOT_STARTED || currentState == MyServiceManager.State.STOPPED)
                        onServiceClick(true)
                }
            ) {
                Text("Regular $btnText")
            }

            Button(
                modifier = Modifier
                    .visible(true),
                onClick = {
                    if (currentForeState == MyServiceManager.ForeState.NOT_STARTED || currentForeState == MyServiceManager.ForeState.STOPPED)
                        onServiceClick(false)
                }
            ) {
                Text("Foreground service $btnForeText")
            }

        }
    }
}

