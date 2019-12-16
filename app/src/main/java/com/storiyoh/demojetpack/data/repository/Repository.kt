package com.storiyoh.demojetpack.data.repository

import com.storiyoh.demojetpack.data.api.ApiService
import com.storiyoh.demojetpack.data.api.IpAddress
import com.storiyoh.demojetpack.data.api.NewEpisodeRes
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getDataFromApi(): Single<NewEpisodeRes> = apiService.getNewEpisodes("en", 10, "Yes", 1, 1,1)

}