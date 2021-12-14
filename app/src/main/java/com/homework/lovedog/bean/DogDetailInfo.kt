package com.homework.lovedog.bean


data class DogDetailInfo(
        val petID: Int, val name: String, val engName: String, val character: String,
        val nation: String, val easyOfDisease: String, val life: String, val price: String, val
        des: String,val feature:String ,val characterFeature :String ,val careKnowledge :String ,
        val feedPoints:String ,val imageURL: MutableList<String>
)
