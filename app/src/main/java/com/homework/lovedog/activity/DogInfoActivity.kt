package com.homework.lovedog.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.homework.lovedog.activity.ui.PhotoActivity
import com.homework.lovedog.base.BaseActivity
import com.homework.lovedog.bean.DogInfo
import com.homework.lovedog.databinding.ActivityDogInfoLayoutBinding
import com.homework.lovedog.utils.StringFormatUtils


class DogInfoActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityDogInfoLayoutBinding
    private var dogInfo: DogInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDogInfoLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.backRtv.setOnClickListener(this)
        viewBinding.headIconRiv.setOnClickListener(this)
        initData()
    }


    private fun initData() {
        val bundle = intent.extras
        dogInfo = bundle?.getParcelable<DogInfo>("dog_info")
        dogInfo?.apply {
            Glide.with(baseContext).load(imageURLStr).into(viewBinding.headIconRiv)
            viewBinding.titleTv.text = StringFormatUtils.defaultValueFormat(engName, "")?:"unknown"
            viewBinding.nameTv.text = StringFormatUtils.defaultValueFormat(engName, name)?:"unknown"
            viewBinding.nationTv.text = StringFormatUtils.defaultValueFormat(enNation, nation)?:"unknown"
            viewBinding.lifeTv.text = StringFormatUtils.defaultValueFormat(enLife, life)?:"unknown"
            viewBinding.characterTv.text =
                StringFormatUtils.defaultValueFormat(enCharacter, character)?:"unknown"
            viewBinding.easyOfDiseaseTv.text = StringFormatUtils
                .defaultValueFormat(enEasyOfDisease, enEasyOfDisease)?:"unknown"
            viewBinding.priceTv.text = StringFormatUtils.defaultValueFormat(enPrice, price)?:"unknown"
            viewBinding.desTv.text = StringFormatUtils.defaultValueFormat(enDes, des)?:"unknown"
            viewBinding.featureTv.text = StringFormatUtils.defaultValueFormat(enFeature, feature)?:"unknown"
            viewBinding.characterFeatureTv.text = StringFormatUtils
                .defaultValueFormat(enCharacterFeature, characterFeature)?:"unknown"
            viewBinding.careKnowledgeTv.text = StringFormatUtils
                .defaultValueFormat(enCareKnowledge, careKnowledge)?:"unknown"
            viewBinding.feedPointsTv.text =
                StringFormatUtils.defaultValueFormat(enFeedPoints, feedPoints)?:"unknown"
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            viewBinding.backRtv -> finish()
            viewBinding.headIconRiv -> {
                val intent = Intent(this@DogInfoActivity, PhotoActivity::class.java)
                intent.putExtra("url", dogInfo?.imageURLStr)
                startActivity(intent)
            }
        }
    }

    override fun fragmentLayoutId(): Int {
        return viewBinding.dogContentLl.id
    }
}