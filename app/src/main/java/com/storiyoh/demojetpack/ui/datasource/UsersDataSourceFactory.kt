package com.ahmedabdelmeged.pagingwithrxjava.kotlin.data.datasource


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.storiyoh.demojetpack.data.api.NewEpisodeRes
import com.storiyoh.demojetpack.data.repository.Repository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ahmed Abd-Elmeged on 2/20/2018.
 *
 * A simple data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
class UsersDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                             private val repository: Repository
)
    : DataSource.Factory<Long, NewEpisodeRes.Data.Item>() {

    val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Long, NewEpisodeRes.Data.Item> {
        val usersDataSource = UsersDataSource(repository, compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

}
