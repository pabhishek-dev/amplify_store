package com.abhi.trackinfo.presentation.utils.network_handler

import android.content.Context
import androidx.lifecycle.LifecycleOwner

interface NetworkStateListener {

    fun observeNetworkState(context: Context, lifecycle: LifecycleOwner)
}
