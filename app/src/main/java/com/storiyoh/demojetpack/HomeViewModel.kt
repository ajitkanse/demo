package com.storiyoh.demojetpack

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.ahmedabdelmeged.pagingwithrxjava.kotlin.data.datasource.UsersDataSource
import com.ahmedabdelmeged.pagingwithrxjava.kotlin.data.datasource.UsersDataSourceFactory
import com.storiyoh.demojetpack.data.api.NewEpisodeRes
import com.storiyoh.demojetpack.data.repository.Repository

import com.storiyoh.demojetpack.ui.datasource.NetworkState
import com.storiyoh.demojetpack.util.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable


class HomeViewModel(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    fun showDataFromApi(): Single<NewEpisodeRes> = repository.getDataFromApi()
        .compose(schedulerProvider.getSchedulersForSingle()).doAfterSuccess {
            Log.d("MainActivity1", it.toString())

        }

    var userList: LiveData<PagedList<NewEpisodeRes.Data.Item>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 15

    private val sourceFactory: UsersDataSourceFactory

    init {
        sourceFactory = UsersDataSourceFactory(compositeDisposable, repository)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        userList = LivePagedListBuilder<Long, NewEpisodeRes.Data.Item>(sourceFactory, config).build()

    }

    fun retry() {
        sourceFactory.usersDataSourceLiveData.value!!.retry()
    }

    fun refresh() {
        sourceFactory.usersDataSourceLiveData.value!!.invalidate()
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<UsersDataSource, NetworkState>(
        sourceFactory.usersDataSourceLiveData, { it.networkState })

    fun getRefreshState(): LiveData<NetworkState> = Transformations.switchMap<UsersDataSource, NetworkState>(
        sourceFactory.usersDataSourceLiveData, { it.initialLoad })

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
