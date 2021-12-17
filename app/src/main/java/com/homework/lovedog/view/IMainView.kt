package com.homework.lovedog.view

import com.homework.lovedog.base.IBaseView
import com.homework.lovedog.bean.DogList


interface IMainView :IBaseView{
    fun showDogList(dogList: MutableList<DogList>?)
}