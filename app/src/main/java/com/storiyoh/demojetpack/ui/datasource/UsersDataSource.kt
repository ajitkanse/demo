package com.ahmedabdelmeged.pagingwithrxjava.kotlin.data.datasource


import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.storiyoh.demojetpack.data.api.NewEpisodeRes
import com.storiyoh.demojetpack.data.repository.Repository
import com.storiyoh.demojetpack.ui.datasource.NetworkState
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class UsersDataSource(
        private val repository: Repository,
        private val compositeDisposable: CompositeDisposable)
    : ItemKeyedDataSource<Long, NewEpisodeRes.Data.Item>() {

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ }, { throwable -> /*Timber.e(throwable.message)*/ }))
        }
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<NewEpisodeRes.Data.Item>) {
        // update network states.
        // we also provide an initial load state to the listeners so that the UI can know when the
        // very first list is loaded.
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        //get the initial users from the api
        compositeDisposable.add(repository.getDataFromApi().subscribe({ users ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(users.data.items)
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadInitial(params, callback) })
            val error = NetworkState.error("throwable.message")
            // publish the error
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<NewEpisodeRes.Data.Item>) {
        // set network value to loading.
        networkState.postValue(NetworkState.LOADING)

        //get the users from the api after id
        compositeDisposable.add(repository.getDataFromApi().subscribe({ users ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(users.data.items)
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadAfter(params, callback) })
            // publish the error
            networkState.postValue(NetworkState.error("throwable.message"))
        }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<NewEpisodeRes.Data.Item>) {
        // ignored, since we only ever append to our initial load
    }

    override fun getKey(item: NewEpisodeRes.Data.Item): Long {
        return item.id.toLong()
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

}