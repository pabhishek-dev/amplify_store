package com.abhi.amplify_store.dataStore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import kotlin.time.Duration.Companion.milliseconds

class SimpleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storage = SimpleStorage(this)

        lifecycleScope.launch {
            storage.saveUsername("Abhishek")
        }
        runBlocking {
            delay(5000.milliseconds)
        }

        lifecycleScope.launch {
            storage.userNameFlow.collect { name ->
                Timber.d("Current username is: $name")
            }
        }
    }
}
