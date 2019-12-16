package com.storiyoh.demojetpack

import androidx.lifecycle.ViewModel
import com.storiyoh.demojetpack.data.api.NewEpisodeRes
import com.storiyoh.demojetpack.data.repository.Repository
import com.storiyoh.demojetpack.util.SchedulerProvider
import io.reactivex.Single


class MainActivityViewModel(private val repository: Repository,
                            private val schedulerProvider: SchedulerProvider
) : ViewModel(){

    fun showDataFromApi(): Single<NewEpisodeRes> = repository.getDataFromApi()
        .compose(schedulerProvider.getSchedulersForSingle())
}