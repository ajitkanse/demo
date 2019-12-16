package com.storiyoh.demojetpack.di.component

import android.app.Application
import com.storiyoh.demojetpack.base.ApplicationBase
import com.storiyoh.demojetpack.di.module.ActivityBuilder
import com.storiyoh.demojetpack.di.module.AppModule
import com.storiyoh.demojetpack.di.module.FragmentBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    AndroidInjectionModule::class, AppModule::class,
    ActivityBuilder::class,FragmentBuilder::class))

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: ApplicationBase)
}