package com.homework.lovedog.activity

import android.os.Bundle
import android.view.View
import com.homework.lovedog.base.BaseActivity
import com.homework.lovedog.databinding.ActivityDogInfoLayoutBinding


class DogInfoActivity : BaseActivity() {

    private lateinit var viewBinding : ActivityDogInfoLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDogInfoLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun onClick(p0: View?) {
        when(p0){
            viewBinding.backRtv->finish()
        }
    }

    override fun fragmentLayoutId(): Int {
        return viewBinding.dogContentLl.id
    }
}