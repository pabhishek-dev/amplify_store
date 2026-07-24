package com.abhi.trackinfo

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.abhi.trackinfo.presentation.home.Home
import com.abhi.trackinfo.presentation.utils.navigation.AppNavGraph
import com.abhi.trackinfo.presentation.utils.network_handler.NetworkStateHandler
import com.abhi.trackinfo.presentation.utils.network_handler.NetworkStateListener
import com.abhi.trackinfo.services.regular.MyService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), NetworkStateListener by NetworkStateHandler() {

    private var myService: MyService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
          //  val binder = service as MyService.LocalBinder
          //  myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()
        }
        observeNetworkState(this, this)
    }
}

@Composable
fun MainApp() {
    val rootNavController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    // Root level navigation host
    AppNavGraph(
        navController = rootNavController,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarHostState,
        onFABClick = { /* Handle global FAB if needed */ }
    )
}

@Preview
@Composable
fun DisplayMain() {
    Home()
}
