package com.homework.lovedog.activity

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.homework.lovedog.base.BaseActivity
import com.homework.lovedog.bean.DogInfo
import com.homework.lovedog.databinding.ActivityDogInfoLayoutBinding
import com.homework.lovedog.utils.StringFormatUtils


class DogInfoActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityDogInfoLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDogInfoLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.backRtv.setOnClickListener(this)
        initData()
    }


    private fun initData() {
        val bundle = intent.extras
        val dogInfo = bundle?.getParcelable<DogInfo>("dog_info")
        dogInfo?.apply {
            Glide.with(baseContext).load(dogInfo.imageURLStr).into(viewBinding.headIconRiv)
            viewBinding.titleTv.text = StringFormatUtils.defaultValueFormat(engName, "")
            viewBinding.nameTv.text = StringFormatUtils.defaultValueFormat(engName, name)
            viewBinding.nationTv.text = StringFormatUtils
                .defaultValueFormat("Nation:$enNation", "Nation:$nation")
            viewBinding.lifeTv.text = StringFormatUtils
                .defaultValueFormat("Lift:$enLife", "Lift:$life")

        }


    }

    override fun onClick(p0: View?) {
        when (p0) {
            viewBinding.backRtv -> finish()
        }
    }

    override fun fragmentLayoutId(): Int {
        return viewBinding.dogContentLl.id
    }
}