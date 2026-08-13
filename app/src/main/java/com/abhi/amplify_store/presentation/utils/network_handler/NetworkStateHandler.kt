package com.abhi.amplify_store.presentation.utils.network_handler

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner

class NetworkStateHandler: NetworkStateListener {
    override fun observeNetworkState(
        context: Context,
        lifecycle: LifecycleOwner
    ) {
        Log.d("MyApp", "ObserveNetworkState: Check")
    }
}
