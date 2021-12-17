package com.homework.lovedog.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.homework.lovedog.R
import com.homework.lovedog.base.BaseViewHolder
import com.homework.lovedog.base.SimpleRecyclerViewAdapter
import com.homework.lovedog.bean.DogList
import com.homework.lovedog.databinding.ItemMainDogListLayoutBinding
import com.homework.lovedog.utils.StringFormatUtils
import com.squareup.picasso.Picasso


class MainDogListAdapter(context: Context?, datas: MutableList<DogList>?) :
        SimpleRecyclerViewAdapter<DogList>(context, datas, R.layout.item_main_dog_list_layout) {
    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: BaseViewHolder, t: DogList?, position: Int) {
       val viewBinding = ItemMainDogListLayoutBinding.bind(holder.itemView)

        viewBinding.apply {
            t?.apply {
                Glide.with(mContext).load(coverURL).placeholder(R.drawable.dog_icon)
                        .error(R.drawable.dog_icon).into(dogImageIv)
                dogNameTv.text = StringFormatUtils.defaultValueFormat(engName,"Dog")
                dogPriceTv.text = "Price:￥${StringFormatUtils.defaultValueFormat(price,"")}"
            }

        }
    }
}