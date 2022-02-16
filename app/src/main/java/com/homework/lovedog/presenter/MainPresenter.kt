package com.homework.lovedog.presenter

import android.annotation.SuppressLint
import android.os.SystemClock
import android.util.Log
import com.homework.lovedog.base.BasePresenter
import com.homework.lovedog.bean.DogInfo
import com.homework.lovedog.bean.DogItem
import com.homework.lovedog.bean.RspDogList
import com.homework.lovedog.bean.TranslateResult
import com.homework.lovedog.dbmanager.DogInfoDbManager
import com.homework.lovedog.model.MainModel
import com.homework.lovedog.utils.GsonUtils
import com.homework.lovedog.utils.translate.TransApi
import com.homework.lovedog.view.IMainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(val view: IMainView) : BasePresenter(), IMainPresenter {
    private val model: MainModel = MainModel(view.getViewLifecycleOwner())
    private var page = 1
    private var pageSize = 5;
    private val transApi: TransApi = TransApi("20220216001084823", "QGmC2nQM1SykPrgAiLzH")


    override fun queryDogList(allFresh: Boolean) {
        if (allFresh) {
            page = 1
        }
        getDogItemFromDb()
    }

    @SuppressLint("CheckResult")
    private fun getDogItemFromDb() {
        Observable.create<MutableList<DogItem>> {
            if (!it.isDisposed) {
                val dogItemQuery = DogInfoDbManager.queryDogItemList(page, pageSize)
                if (dogItemQuery != null&&dogItemQuery.size>0)
                    it.onNext(dogItemQuery)
                else
                    it.onError(Throwable("get null from db"))
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                page++
                view.showDogList(it)
            }, {
                getDogItemFromNet()
            })
    }

    private fun getDogItemFromNet() {
        model.queryDogList(page, pageSize, { rsp: RspDogList? ->
            if (rsp != null && rsp.isSuccess) {
               val dogItemList = rsp.result?.petFamilyList
                if (dogItemList != null && dogItemList.size > 0){
                    page++
                    formatDogItem(dogItemList)
                }else{
                    view.showDogList(dogItemList)
                }
            } else {
                callbackRspFailure(rsp, view)
            }
        }, {
            requestError(it, view)
            Log.e("TAG", it.message + "")
        })
    }

    @SuppressLint("CheckResult")
    private fun formatDogItem(dogItemList: MutableList<DogItem>){
        Observable.create<MutableList<DogItem>> {
            if (!it.isDisposed){
                dogItemList.forEach { dogItem ->
                    dogItem.apply {
                        engName = translate(engName,name)
                        enPrice = translate(enPrice,price)
                    }
                }
                DogInfoDbManager.saveDogItemList(dogItemList)
                it.onNext(dogItemList)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view.showDogList(it) }
    }

    @SuppressLint("CheckResult")
    override fun getDogDetail(dogItem: DogItem) {
        Observable.create<DogInfo> {
            if (!it.isDisposed) {
                val dogInfoQuery = DogInfoDbManager.queryDogInfo(dogItem.petID)
                if (dogInfoQuery != null) {
                    val dogInfo = dogInfoTranslate(dogInfoQuery)
                    DogInfoDbManager.saveOrUpdateDogInfo(dogInfo)
                    dogInfo.imageURLStr = dogItem.coverURL
                    dogInfo.imageURL = DogInfoDbManager.queryImageUrlList(dogInfo.petID)
                    it.onNext(dogInfo)
                } else {
                    it.onError(Throwable("get null from db"))
                }
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.imageURLStr = dogItem.coverURL
                view.showDogInfo(it)
            }, {
                getDogDetailOnNet(dogItem)
            })
    }

    private fun getDogDetailOnNet(dogItem: DogItem) {
        model.queryDogInfo(dogItem.petID, { rsp ->
            if (rsp != null && rsp.isSuccess && rsp.result != null) {
                formatDogInfo(dogItem, rsp.result)
            } else {
                callbackRspFailure(rsp, view)
            }
        }, {
            requestError(it, view)
            Log.e("TAG", it.message + "")
        })
    }

    @SuppressLint("CheckResult")
    private fun formatDogInfo(dogItem: DogItem, dogInfo: DogInfo) {
        Observable.create<DogInfo> {
            if (!it.isDisposed) {
                val dogInfoTr = dogInfoTranslate(dogInfo)
                DogInfoDbManager.saveOrUpdateDogInfo(dogInfoTr)
                DogInfoDbManager.saveImageUrlList(dogInfoTr.petID, dogInfoTr.imageURL)
                it.onNext(dogInfoTr)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                dogInfo.imageURLStr = dogItem.coverURL
                view.showDogInfo(it)
            }
    }

    private fun dogInfoTranslate(dogInfo: DogInfo): DogInfo {
        return dogInfo.apply {
            engName = translate(engName, name)
            enCharacter = translate(enCharacter, character)
            enNation = translate(enNation, nation)
            enEasyOfDisease = translate(enEasyOfDisease, easyOfDisease)
            enLife = translate(enLife, life)
            enPrice = translate(enPrice, price)
            enDes = translate(enDes, des)
            enFeature = translate(enFeature, feature)
            enCharacterFeature = translate(enCharacterFeature, characterFeature)
            enCareKnowledge = translate(enCareKnowledge, careKnowledge)
            enFeedPoints = translate(enFeedPoints, feedPoints)
        }
    }

    private fun translate(en: String?, cn: String?): String? {
        return if (en.isNullOrEmpty()) {
            SystemClock.sleep(1000)
            val transResultJson = transApi.getTransResult(cn, "zh", "en")
            val transResult = GsonUtils.fromJson(transResultJson, TranslateResult::class.java)
            val transResultList = transResult?.trans_result
            if (transResultList != null && transResultList.size > 0) {
                val stringBuilder = StringBuffer()
                transResultList.forEach {
                    stringBuilder.append(it.dst)
                }
                stringBuilder.toString()
            } else {
                en
            }
        } else en
    }


}