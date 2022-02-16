package com.homework.lovedog.view

import com.homework.lovedog.base.IBaseView
import com.homework.lovedog.bean.DogInfo
import com.homework.lovedog.bean.DogItem


interface IMainView :IBaseView{
    fun showDogList(dogList: MutableList<DogItem>?)

    fun showDogInfo(dogInfo:DogInfo?)
}