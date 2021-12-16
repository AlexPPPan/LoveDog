package com.homework.lovedog.bean

import com.homework.lovedog.base.BaseRsp

data class RspDogList(val result: BaseDogFamilyList<DogList>?) : BaseRsp()
