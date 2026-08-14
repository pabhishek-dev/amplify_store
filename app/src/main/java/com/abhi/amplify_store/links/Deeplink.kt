package com.abhi.amplify_store.links

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.abhi.amplify_store.services.ServiceActivity
import timber.log.Timber

class Deeplink: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleLinkIntent(intent)

        setContent {

        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // CRUCIAL: Update the activity intent configuration data
        setIntent(intent)

        // Case B: App was already open. Handle the incoming link.
        handleLinkIntent(intent)
    }

    fun handleLinkIntent(intent: Intent) {
        val action: String? = intent.action
        val data: Uri? = intent.data

        if (Intent.ACTION_VIEW == action && data != null) {
            val host = data.host
            val path = data.path
            val itemId = data.getQueryParameter("id") // Returns null if not present

            Timber.d("Incoming Link -> Host: $host, Path: $path, ID: $itemId")

            // Route based on what the link actually contains
            if (host == "dashboard" || path == "/services" || itemId != null) {
                navigateToScreen(path, itemId)
            }
        }
    }

    private fun navigateToScreen(path: String?, itemId: String?) {
        Timber.d("Link reached")
        startActivity(Intent(this, ServiceActivity::class.java))
    }

//    To test run this adb -s emulator-5554 shell am start -W -a android.intent.action.VIEW -d "amplify_store://dashboard?id=123
}
