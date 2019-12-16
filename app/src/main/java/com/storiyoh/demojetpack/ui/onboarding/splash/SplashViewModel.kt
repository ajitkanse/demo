package com.storiyoh.demojetpack.ui.onboarding.splash

import androidx.lifecycle.ViewModel
import com.storiyoh.demojetpack.data.repository.Repository
import com.storiyoh.demojetpack.util.SchedulerProvider

class SplashViewModel(
    repository: Repository,
    schedulerProvider: SchedulerProvider
) : ViewModel() {
    // TODO: Implement the ViewModel

    val str :String = "ajit kanse"
}
