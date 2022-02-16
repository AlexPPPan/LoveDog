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
            viewBinding.titleTv.text = formatValue(engName, "")
            viewBinding.nameTv.text = formatValue(engName, name)
            viewBinding.nationTv.text = formatValue(enNation, nation)
            viewBinding.lifeTv.text = formatValue(enLife, life)
            viewBinding.characterTv.text = formatValue(enCharacter, character)
            viewBinding.easyOfDiseaseTv.text = formatValue(enEasyOfDisease, enEasyOfDisease)
            viewBinding.priceTv.text = formatValue(enPrice, price)
            viewBinding.desTv.text = formatValue(enDes, des)
            viewBinding.featureTv.text = formatValue(enFeature, feature)
            viewBinding.characterFeatureTv.text = formatValue(enCharacterFeature, characterFeature)
            viewBinding.careKnowledgeTv.text = formatValue(enCareKnowledge, careKnowledge)
            viewBinding.feedPointsTv.text = formatValue(enFeedPoints, feedPoints)
        }
    }

    private fun formatValue(valueStr: String?, defaultValue: String?): String {
        return (valueStr ?: defaultValue) ?: "unknown"
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