package com.homework.lovedog.model;


import com.homework.lovedog.base.BaseModel;
import com.homework.lovedog.bean.RspDogDetailInfo;
import com.homework.lovedog.bean.RspDogList;
import com.homework.lovedog.service.IService;
import com.rxjava.rxlife.RxLife;

import androidx.lifecycle.LifecycleOwner;
import io.reactivex.functions.Consumer;
import rxhttp.wrapper.param.RxHttp;

public class MainModel  extends BaseModel {
    public MainModel(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    public void queryDogListByKeyword(int page, int pageSize, String keyword, Consumer<RspDogList> onNext, Consumer<Throwable> onError){
        RxHttp.postForm(IService.QUERY_DOG_LIST_BY_KEYWORD)
            .add("apiKey",IService.API_KEY)
            .add("page",page)
            .add("pageSize",pageSize)
            .add("keyword",keyword)
            .asObject(RspDogList.class)
            .as(RxLife.asOnMain(mLifecycleOwner))
            .subscribe(onNext,onError);
    }

    public void queryDogList(int page, int pageSize,Consumer<RspDogList> onNext,
                             Consumer<Throwable> onError){
        RxHttp.postForm(IService.QUERY_DOG_LIST)
            .add("apiKey",IService.API_KEY)
            .add("page",page)
            .add("pageSize",pageSize)
            .asObject(RspDogList.class)
            .as(RxLife.asOnMain(mLifecycleOwner))
            .subscribe(onNext,onError);
    }


    public void queryDogInfo(int petID,Consumer<RspDogDetailInfo> onNext,
                             Consumer<Throwable> onError){
        RxHttp.postForm(IService.QUERY_DOG_INFO)
            .add("apiKey",IService.API_KEY)
            .add("petID",petID)
            .asObject(RspDogDetailInfo.class)
            .as(RxLife.asOnMain(mLifecycleOwner))
            .subscribe(onNext,onError);
    }


}
