package com.storiyoh.demojetpack.di.module

import com.storiyoh.demojetpack.ui.HomeFragment
import com.storiyoh.demojetpack.ui.onboarding.login.LoginFragment
import com.storiyoh.demojetpack.ui.onboarding.registration.RegistrationFragment
import com.storiyoh.demojetpack.ui.onboarding.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun bindRegistrationFragment(): RegistrationFragment
}