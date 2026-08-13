package com.abhi.amplify_store.workmanager

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import timber.log.Timber
import kotlin.time.Duration.Companion.milliseconds

class BackgroundTask : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .build()


        // 1. Create a OneTimeWorkRequest for your SyncWorker
        val syncWorkRequest = OneTimeWorkRequestBuilder<SyncWorker>()
          //  .setConstraints(myConstraints) Enable to make constraints applicable
            .build()

        // 2. Enqueue the request with WorkManager
        WorkManager.getInstance(applicationContext).enqueue(syncWorkRequest)

        setContent {

        }
    }
}

class SyncWorker(
    context: Context,
    parameters: WorkerParameters
) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        return try {
            Timber.d("")
            delay(10000.milliseconds)
            Timber.d("Found data Success!!!")
            Result.success()
        } catch (e: Exception) {
            Timber.d("Failed $e")
            Result.failure()
        }
    }
}
