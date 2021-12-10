package com.homework.lovedog.presenter

import com.homework.lovedog.base.BasePresenter
import com.homework.lovedog.base.IBaseView
import com.homework.lovedog.model.MainModel

class MainPresenter(val view :IBaseView) : BasePresenter(){
    val model: MainModel = MainModel(view.getViewLifecycleOwner())



}