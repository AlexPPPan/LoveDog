package com.homework.lovedog

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.lovedog.activity.ui.PhotoActivity
import com.homework.lovedog.adapter.MainDogListAdapter
import com.homework.lovedog.bean.DogList
import com.homework.lovedog.databinding.ActivityMainBinding
import com.homework.lovedog.dbmanager.DogInfoDbManager
import com.homework.lovedog.presenter.MainPresenter
import com.homework.lovedog.utils.MMKVUtils
import com.homework.lovedog.utils.NewFileUtils
import com.homework.lovedog.view.IMainView
import com.leaf.library.StatusBarUtil
import com.permissionx.guolindev.PermissionX
import java.io.File

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
        StatusBarUtil.setColor(
            this,
            ResourcesCompat.getColor(resources, R.color.white, null)
        )
        StatusBarUtil.setDarkMode(this)
        adapter = if (!::adapter.isInitialized) {
            MainDogListAdapter(this, dogList)

        } else {
            adapter
        }

        adapter?.setOnItemClickListener(object : MainDogListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                var intent: Intent = Intent(this@MainActivity, PhotoActivity::class.java)
                intent.putExtra("url", adapter.getDatas().get(position).coverURL)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int) {


            }
        })
        mViewBinding.dogMainList.adapter = adapter
        mMainPresenter = if (!::mMainPresenter.isInitialized) {
            MainPresenter(this)
        } else {
            mMainPresenter
        }
        mViewBinding.refreshLayout.setOnLoadMoreListener {
            mMainPresenter.queryDogList(false)
        }
        mViewBinding.refreshLayout.setOnRefreshListener {
            mMainPresenter.queryDogList(true)
        }
        PermissionX.init(this).permissions(
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "Core fundamental are based on these permissions",
                    "OK",
                    "Cancel"
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "You need to allow necessary permissions in Settings manually",
                    "OK",
                    "Cancel"
                )
            }
            .request { allGranted, _, deniedList ->
                if (allGranted) {
                    val file =
                        NewFileUtils.getExternalStorageDir(
                            getContext(),
                            "",
                            "/test/glide/"
                        )
                    if (!file.exists())
                        file.mkdir()
                    val dbFile = NewFileUtils.getExternalStorageDir(getContext(), "", "DB")
                    if (!dbFile.exists())
                        dbFile.mkdir()
                    MMKVUtils.saveDbPath(dbFile.path)
                    adapter.clearData()
                    mMainPresenter.queryDogList(true)
                } else {
                    Toast.makeText(
                        this,
                        "These permissions are denied: $deniedList",
                        Toast.LENGTH_LONG
                    ).show()
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