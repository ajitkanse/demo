package com.storiyoh.demojetpack.di.module

import com.storiyoh.demojetpack.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(ActivityModule::class))
    abstract fun bindMainActivity(): MainActivity


}