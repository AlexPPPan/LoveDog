package com.homework.lovedog

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import com.homework.lovedog.view.IMainView
import com.homework.lovedog.presenter.MainPresenter
import android.os.Bundle
import com.leaf.library.StatusBarUtil
import com.homework.lovedog.R
import android.app.Activity
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.lovedog.adapter.MainDogListAdapter
import com.homework.lovedog.bean.DogList
import com.homework.lovedog.databinding.ActivityMainBinding
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.RequestCallback
import java.io.File
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), IMainView {
    private lateinit var mViewBinding: ActivityMainBinding
    private lateinit var mMainPresenter: MainPresenter
    private lateinit var adapter: MainDogListAdapter
    private val dogList = mutableListOf<DogList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        mViewBinding.dogMainList.layoutManager = LinearLayoutManager(this)

        setContentView(mViewBinding.root)
        StatusBarUtil.setColor(this,
                ResourcesCompat.getColor(resources, R.color.white, null))
        StatusBarUtil.setDarkMode(this)
        adapter = if (!::adapter.isInitialized) {
            MainDogListAdapter(this, dogList)
        } else {
            adapter
        }
        mViewBinding.dogMainList.adapter = adapter
        mMainPresenter = if (!::mMainPresenter.isInitialized) {
            MainPresenter(this)
        } else {
            mMainPresenter
        }
        mViewBinding.refreshLayout.setOnLoadMoreListener {
            mMainPresenter.queryDogList(false)
        }
        mViewBinding.refreshLayout.setOnRefreshListener{
            mMainPresenter.queryDogList(true)
        }
        PermissionX.init(this).permissions(Manifest
                .permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onExplainRequestReason { scope, deniedList ->
                    scope.showRequestReasonDialog(deniedList, "Core fundamental are based on these permissions", "OK", "Cancel")
                }
                .onForwardToSettings { scope, deniedList ->
                    scope.showForwardToSettingsDialog(deniedList, "You need to allow necessary permissions in Settings manually", "OK", "Cancel")
                }
                .request { allGranted, _, deniedList ->
                    if (allGranted) {
                        val file = File("${Environment.getExternalStorageDirectory()}/test/glide/")
                        file.mkdir()
                        adapter.clearData()
                        mMainPresenter.queryDogList(true)
                    } else {
                        Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                    }
                }
    }


    override fun getActivity(): Activity {
        return this
    }

    override fun serverErr(message: String?) {}
    override fun getViewLifecycleOwner(): LifecycleOwner {
        return this
    }

    override fun getContext(): Context {
        return this
    }

    override fun showDogList(dogList: MutableList<DogList>?) {
        mViewBinding.refreshLayout.finishLoadMore()
        mViewBinding.refreshLayout.finishRefresh()
        adapter.loadMoreData(dogList)
    }


}