package com.storiyoh.demojetpack.data.api

import com.storiyoh.demojetpack.util.Constants.Companion.apiKey
import com.storiyoh.demojetpack.util.Constants.Companion.auth_token
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {


    @Headers("Accept: " + "application/json",
        "Authorization: $auth_token",
        "api-key: $apiKey",
        "res-enc: " + "yes")
    @POST("marketplace/dashboard/new_episodes")
    fun getNewEpisodes(@Query(value = "lang", encoded = true) lang: String,
                       @Query(value = "noofrecords", encoded = true) noofrecords: Int,
                       @Query(value = "paginate", encoded = true) paginate: String,
                       @Query(value = "page", encoded = true) page: Int,
                       @Query(value = "country_id", encoded = true) country_id: Int,
                       @Query(value = "currency_id", encoded = true) currency_id: Int): Single<NewEpisodeRes>


}