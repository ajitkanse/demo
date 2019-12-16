package com.storiyoh.demojetpack.di.module

import com.storiyoh.demojetpack.MainActivityViewModel
import com.storiyoh.demojetpack.data.repository.Repository
import com.storiyoh.demojetpack.util.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideViewModel(repository: Repository,
                         schedulerProvider: SchedulerProvider
    )
            = MainActivityViewModel(repository, schedulerProvider)
}