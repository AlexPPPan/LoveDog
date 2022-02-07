package com.homework.lovedog.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.homework.lovedog.R
import com.homework.lovedog.base.BaseViewHolder
import com.homework.lovedog.base.SimpleRecyclerViewAdapter
import com.homework.lovedog.bean.DogItem
import com.homework.lovedog.databinding.ItemMainDogListLayoutBinding
import com.homework.lovedog.utils.StringFormatUtils


class MainDogListAdapter(context: Context?, data: MutableList<DogItem>?) :
        SimpleRecyclerViewAdapter<DogItem>(context, data, R.layout.item_main_dog_list_layout) {


    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: BaseViewHolder, t: DogItem?, position: Int) {
       val viewBinding = ItemMainDogListLayoutBinding.bind(holder.itemView)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(holder.itemView,position)
        }
        viewBinding.apply {
            t?.apply {
                Glide.with(mContext).load(coverURL).placeholder(R.drawable.dog_icon)
                        .error(R.drawable.dog_icon).into(dogImageIv)
                dogNameTv.text = StringFormatUtils.defaultValueFormat(engName,"Dog")
                dogPriceTv.text = "Price:￥${StringFormatUtils.defaultValueFormat(price,"")}"
            }

        }
    }

    private lateinit var onItemClickListener: OnItemClickListener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }
    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }


}