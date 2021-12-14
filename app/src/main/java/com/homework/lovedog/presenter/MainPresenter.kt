package com.homework.lovedog.presenter

import com.homework.lovedog.base.BasePresenter
import com.homework.lovedog.base.IBaseView
import com.homework.lovedog.bean.RspDogList
import com.homework.lovedog.model.MainModel
import com.homework.lovedog.view.IMainView

class MainPresenter(val view: IMainView) : BasePresenter(), IMainPresenter {
    val model: MainModel = MainModel(view.getViewLifecycleOwner())
    var page = 1
    var pageSize = 30;


    override fun queryDogList(allFresh: Boolean) {
        model.queryDogList(page,pageSize,{rsp:RspDogList->

        },{


        })

    }


}