package com.homework.lovedog.model;

import com.homework.lovedog.base.BaseModel;
import com.homework.lovedog.bean.RspDogDetailInfo;
import com.homework.lovedog.service.IService;
import com.rxjava.rxlife.RxLife;

import androidx.lifecycle.LifecycleOwner;
import io.reactivex.functions.Consumer;
import rxhttp.wrapper.param.RxHttp;


public class DogInfoModel extends BaseModel {


   public DogInfoModel(LifecycleOwner lifecycleOwner) {
      super(lifecycleOwner);
   }

   public void queryDogInfo(int petID, Consumer<RspDogDetailInfo> onNext,
                            Consumer<Throwable> onError){
      RxHttp.postForm(IService.QUERY_DOG_INFO)
          .add("apiKey",IService.API_KEY)
          .add("petID",petID)
          .asObject(RspDogDetailInfo.class)
          .as(RxLife.asOnMain(mLifecycleOwner))
          .subscribe(onNext,onError);
   }
}
