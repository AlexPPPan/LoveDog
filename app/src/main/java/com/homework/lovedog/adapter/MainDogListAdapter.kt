package com.homework.lovedog.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.bumptech.glide.Glide
import com.homework.lovedog.R
import com.homework.lovedog.base.BaseViewHolder
import com.homework.lovedog.base.SimpleRecyclerViewAdapter
import com.homework.lovedog.bean.DogList
import com.homework.lovedog.databinding.ItemMainDogListLayoutBinding
import com.homework.lovedog.utils.StringFormatUtils


class MainDogListAdapter(context: Context?, datas: MutableList<DogList>?) :
        SimpleRecyclerViewAdapter<DogList>(context, datas, R.layout.item_main_dog_list_layout) {
    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: BaseViewHolder, t: DogList?, position: Int) {
       val viewBinding = ItemMainDogListLayoutBinding.bind(holder.itemView)

        viewBinding.apply {
            t?.apply {
                Glide.with(mContext).load(coverURL).into(dogImageIv)
                dogNameTv.text = StringFormatUtils.defaultValueFormat(engName,"Dog")
                dogPriceTv.text = "Price:${StringFormatUtils.defaultValueFormat(price,"")}YUAN"
            }

        }
    }
}