package com.homework.lovedog.presenter

import android.annotation.SuppressLint
import android.os.SystemClock
import android.util.Log
import com.homework.lovedog.base.BasePresenter
import com.homework.lovedog.bean.DogInfo
import com.homework.lovedog.bean.RspDogList
import com.homework.lovedog.bean.TranslateResult
import com.homework.lovedog.dbmanager.DogInfoDbManager
import com.homework.lovedog.model.MainModel
import com.homework.lovedog.utils.GoogleTranslateUtil
import com.homework.lovedog.utils.GsonUtils
import com.homework.lovedog.utils.translate.TransApi
import com.homework.lovedog.view.IMainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import rxhttp.wrapper.utils.GsonUtil

class MainPresenter(val view: IMainView) : BasePresenter(), IMainPresenter {
   private val model: MainModel = MainModel(view.getViewLifecycleOwner())
   private var page = 1
   private var pageSize = 30;
   private val transApi:TransApi = TransApi("20220209001077870","lamns9ir2ZaDApJU4gdp")



    override fun queryDogList(allFresh: Boolean) {
        if (allFresh) {
            page = 1
        }
        model.queryDogList(page, pageSize, { rsp: RspDogList? ->
            if (rsp != null && rsp.isSuccess) {
                page++
                view.showDogList(rsp.result?.petFamilyList)
            } else {
                callbackRspFailure(rsp, view)
            }
        }, {
            requestError(it, view)
            Log.e("TAG", it.message + "")
        })

    }

    @SuppressLint("CheckResult")
    override fun getDogDetail(petId: Int) {
        Observable.create<DogInfo> {
            if (!it.isDisposed) {
                val dogInfoQuery = DogInfoDbManager.queryDogInfo(petId)
                if (dogInfoQuery != null) {
                    val dogInfo = dogInfoTranslate(dogInfoQuery)
                    DogInfoDbManager.saveOrUpdateDogInfo(dogInfo)
                    dogInfo.imageURL = DogInfoDbManager.queryImageUrlList(dogInfo.petID)
                    it.onNext(dogInfo)
                } else {
                    it.onError(Throwable("get info on net"))
                }
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ dogInfo ->
                view.showDogInfo(dogInfo)
            }, {
                getDogDetailOnNet(petId)
            })
    }

    private fun getDogDetailOnNet(petId: Int) {
        model.queryDogInfo(petId, { rsp ->
            if (rsp != null && rsp.isSuccess && rsp.result != null) {
              formatDogInfo(rsp.result)
            } else {
                callbackRspFailure(rsp, view)
            }
        }, {
            requestError(it, view)
            Log.e("TAG", it.message + "")
        })
    }

    @SuppressLint("CheckResult")
    private fun formatDogInfo(dogInfo: DogInfo){
        Observable.create<DogInfo> {
            if (!it.isDisposed){
                val dogInfoTr = dogInfoTranslate(dogInfo)
                DogInfoDbManager.saveOrUpdateDogInfo(dogInfoTr)
                DogInfoDbManager.saveImageUrlList(dogInfoTr.petID,dogInfoTr.imageURL)
                it.onNext(dogInfoTr)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.showDogInfo(it)
            }
    }

    private fun dogInfoTranslate(dogInfo: DogInfo): DogInfo {
        return dogInfo.apply {
            enCharacter = translate(enCharacter, character)
            SystemClock.sleep(1000)
            enNation = translate(enNation, nation)
            SystemClock.sleep(1000)
            enEasyOfDisease = translate(enEasyOfDisease, easyOfDisease)
            SystemClock.sleep(1000)
            enLife = translate(enLife, life)
            SystemClock.sleep(1000)
            enPrice = translate(enPrice, price)
            SystemClock.sleep(1000)
            enDes = translate(enDes, des)
            SystemClock.sleep(1000)
            enFeature = translate(enFeature, feature)
            SystemClock.sleep(1000)
            enCharacterFeature = translate(enCharacterFeature, characterFeature)
            SystemClock.sleep(1000)
            enCareKnowledge = translate(enCareKnowledge, careKnowledge)
            SystemClock.sleep(1000)
            enFeedPoints = translate(enFeedPoints, feedPoints)
        }
    }

    private fun translate(en: String?, cn: String?): String? {
        return if (en.isNullOrEmpty()) {
            val transResultJson = transApi.getTransResult(cn, "zh", "en")
            val transResult = GsonUtils.fromJson(transResultJson, TranslateResult::class.java)
            val transResultList= transResult?.trans_result
            if (transResultList!=null&&transResultList.size>0){
                val stringBuilder = StringBuffer()
                transResultList.forEach {
                    stringBuilder.append(it.dst)
                }
                stringBuilder.toString()
            }else{
                en
            }
        }
        else en
    }

}