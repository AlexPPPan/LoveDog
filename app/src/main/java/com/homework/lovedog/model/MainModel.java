package com.homework.lovedog.model;


import com.homework.lovedog.base.BaseModel;
import com.homework.lovedog.base.BaseRsp;
import com.homework.lovedog.bean.RspDogListByKeyword;
import com.homework.lovedog.service.IService;
import com.rxjava.rxlife.RxLife;

import androidx.lifecycle.LifecycleOwner;
import io.reactivex.functions.Consumer;
import rxhttp.wrapper.param.RxHttp;

public class MainModel  extends BaseModel {
    public MainModel(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    public void dogDetail(int page, int pageSize, String keyword, Consumer<RspDogListByKeyword> onNext, Consumer<Throwable> onError){
        RxHttp.postForm(IService.DOG_API)
            .add("apiKey",IService.API_KEY)
            .add("page",page)
            .add("pageSize",pageSize)
            .add("keyword",keyword)
            .asObject(RspDogListByKeyword.class)
            .as(RxLife.asOnMain(mLifecycleOwner))
            .subscribe(onNext,onError);
    }
}
