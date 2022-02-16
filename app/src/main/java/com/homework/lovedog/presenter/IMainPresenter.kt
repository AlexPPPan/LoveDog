package com.homework.lovedog.presenter

import com.homework.lovedog.bean.DogItem


interface IMainPresenter {

    fun queryDogList(allFresh:Boolean)

    fun getDogDetail(dogItem: DogItem)
}