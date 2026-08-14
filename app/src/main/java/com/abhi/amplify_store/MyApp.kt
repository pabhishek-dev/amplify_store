package com.abhi.amplify_store

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.abhi.amplify_store.di.ImageLoadEntryPoint
import com.abhi.amplify_store.services.MyServiceManager
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApp : Application(), SingletonImageLoader.Factory {

    val myServiceManager = MyServiceManager()

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        // Can track entire app lifecycle process not restricted to single screen
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifeCycleObserver())
    }

    open class AppLifeCycleObserver: DefaultLifecycleObserver {
        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
        }
    }

    class MyActivityCallBacks: Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(
            activity: Activity,
            savedInstanceState: Bundle?
        ) {
            TODO("Not yet implemented")
        }

        override fun onActivityDestroyed(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityPaused(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityResumed(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivitySaveInstanceState(
            activity: Activity,
            outState: Bundle
        ) {
            TODO("Not yet implemented")
        }

        override fun onActivityStarted(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityStopped(activity: Activity) {
            TODO("Not yet implemented")
        }

    }

    override fun newImageLoader(context: Context): ImageLoader {
        val okHttpClient = EntryPointAccessors.fromApplication(
            context,
            ImageLoadEntryPoint::class.java
        ).getOkHttpClient()
        return ImageLoader.Builder(context)
            .components {
                add(OkHttpNetworkFetcherFactory(okHttpClient))
            }
            .build()
    }
}
