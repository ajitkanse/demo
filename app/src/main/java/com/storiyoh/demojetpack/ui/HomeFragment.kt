package com.storiyoh.demojetpack.ui

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.storiyoh.demojetpack.HomeViewModel
import com.storiyoh.demojetpack.base.BaseFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject
import androidx.paging.PageKeyedDataSource
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.LivePagedListBuilder
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Handler
import com.ahmedabdelmeged.pagingwithrxjava.kotlin.adapter.UserAdapter
import com.storiyoh.demojetpack.R
import com.storiyoh.demojetpack.data.api.NewEpisodeRes
import com.storiyoh.demojetpack.data.api.NewEpisodeRes.*
import com.storiyoh.demojetpack.data.api.NewEpisodeRes.Data.*
import com.storiyoh.demojetpack.ui.datasource.NetworkState
import com.storiyoh.demojetpack.ui.datasource.Status
import kotlinx.android.synthetic.main.item_network_state.*
import java.text.SimpleDateFormat
import java.util.*
import org.apache.commons.net.ntp.NTPUDPClient
import java.net.InetAddress


class HomeFragment : BaseFragment() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    @Inject
    lateinit var viewModel: HomeViewModel

    lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("SplashFragment","HomeFragment")

      /*  compositeDisposable.add(
            viewModel.showDataFromApi()
                .subscribeBy(onSuccess = {
                    Log.d("MainActivity", it.toString())
                   // textView.setText("" + it.status)
                  *//*  val myConfig = PagedList.Config.Builder()
                        .setInitialLoadSizeHint(PAGE_SIZE)
                        .setPageSize(PAGE_SIZE)
                        .build()

                    val myList = ArrayList()
                    val provider = StringListProvider(myList)
                    val dataSource = StringDataSource(provider)
                    val pagedStrings = PagedList.Builder<Int, NewEpisodeRes.Data.Item>(it.data.items, myConfig)
                        .setInitialKey(0)
                        .build()*//*


                    //val livePagedList = it.data.items.toLiveData(10,PagedList.(PagedList.BoundaryCallback<Item>));




                }, onError = {
                    Log.d("MainActivity", it.message)
                })
        )*/


        initAdapter()

    }
    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        userAdapter = UserAdapter {
            viewModel.retry()
        }
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = userAdapter
        viewModel.userList.observe(this, androidx.lifecycle.Observer<PagedList<Item>> { userAdapter.submitList(it) })
        viewModel.getNetworkState().observe(this, androidx.lifecycle.Observer<NetworkState> { userAdapter.setNetworkState(it) })


    }

    /**
     * Init swipe to refresh and enable pull to refresh only when there are items in the adapter
     */
 /*   private fun initSwipeToRefresh() {
        viewModel.getRefreshState().observe(this, Observer { networkState ->
            if (userAdapter.currentList != null) {
                if (userAdapter.currentList!!.size > 0) {
                    usersSwipeRefreshLayout.isRefreshing = networkState?.status == NetworkState.LOADING.status
                } else {
                    setInitialLoadingState(networkState)
                }
            } else {
                setInitialLoadingState(networkState)
            }
        })
        usersSwipeRefreshLayout.setOnRefreshListener({ viewModel.refresh() })
    }*/

    /**
     * Show the current network state for the first load when the user list
     * in the adapter is empty and disable swipe to scroll at the first loading
     *
     * @param networkState the new network state
     */
    private fun setInitialLoadingState(networkState: NetworkState?) {
        //error message
        errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
        if (networkState?.message != null) {
            errorMessageTextView.text = networkState.message
        }

        //loading and retry
        retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE

        usersSwipeRefreshLayout.isEnabled = networkState?.status == Status.SUCCESS
        retryLoadingButton.setOnClickListener { viewModel.retry() }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}


