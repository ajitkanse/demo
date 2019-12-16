package com.storiyoh.demojetpack.base

import android.app.Activity
import android.app.Application
import androidx.multidex.MultiDex
import com.storiyoh.demojetpack.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class ApplicationBase : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector:
            DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        
        MultiDex.install(this)

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector



    /*
    Implemented
    - MVVM
    - Kotlin
    - RxJava
    - Dagger2
    - Data Binding
    - Navigation Controller

    ToDo
    - Material Design
    - Room
    - Security
      - Encrypted Shared Preferences
      - graddle properties
      - SSL Pinning
      - R8/Progurd
    - Coroutine
    - Extension Function/inline function/Lamda function
    - Unit Testing
    */
}