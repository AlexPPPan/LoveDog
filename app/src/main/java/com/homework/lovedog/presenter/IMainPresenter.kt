package com.homework.lovedog.presenter


interface IMainPresenter {

    fun queryDogList(allFresh:Boolean)

    fun getDogDetail(petId:Int)
}