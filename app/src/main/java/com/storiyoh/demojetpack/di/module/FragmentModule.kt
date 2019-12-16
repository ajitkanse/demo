package com.storiyoh.demojetpack.di.module

import com.storiyoh.demojetpack.HomeViewModel
import com.storiyoh.demojetpack.data.repository.Repository
import com.storiyoh.demojetpack.ui.onboarding.login.LoginViewModel
import com.storiyoh.demojetpack.ui.onboarding.registration.RegistrationViewModel
import com.storiyoh.demojetpack.ui.onboarding.splash.SplashViewModel
import com.storiyoh.demojetpack.util.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideViewModel(repository: Repository,
                         schedulerProvider: SchedulerProvider
    ) = HomeViewModel(repository, schedulerProvider)

    @Provides
    fun provideSplashViewModel(repository: Repository,schedulerProvider: SchedulerProvider)
        =
        SplashViewModel(repository, schedulerProvider)

    @Provides
    fun provideLoginViewModel(repository: Repository,schedulerProvider: SchedulerProvider)
        =
        LoginViewModel(repository, schedulerProvider)

    @Provides
    fun provideRegistrationViewModel(repository: Repository,schedulerProvider: SchedulerProvider)
        = RegistrationViewModel(repository, schedulerProvider)
}