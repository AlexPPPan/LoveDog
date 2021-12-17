package com.homework.lovedog

import androidx.appcompat.app.AppCompatActivity
import com.homework.lovedog.view.IMainView
import com.homework.lovedog.presenter.MainPresenter
import android.os.Bundle
import com.leaf.library.StatusBarUtil
import com.homework.lovedog.R
import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.lovedog.adapter.MainDogListAdapter
import com.homework.lovedog.bean.DogList
import com.homework.lovedog.databinding.ActivityMainBinding

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
            MainDogListAdapter(this,dogList)
        } else {
            adapter
        }
        mViewBinding.dogMainList.adapter = adapter
        mMainPresenter = if (!::mMainPresenter.isInitialized) {
            MainPresenter(this)
        } else {
            mMainPresenter
        }
    }

    override fun onResume() {
        super.onResume()
        mMainPresenter.queryDogList(true)
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
        adapter.addData(dogList)
    }
}