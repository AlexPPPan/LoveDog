package com.homework.lovedog.base

import java.lang.String

abstract class BasePresenter {

    protected fun callbackRspFailure(rsp: BaseRsp?, view: IBaseView?) {
        if (view != null) {
           if (rsp != null) {
                view.serverErr(String.format("%s(%s)", rsp.desc, rsp.statusCode))
            } else {
                view.serverErr("null data")
            }
        }
    }

    protected fun requestError(throwable: Throwable?, view: IBaseView?) {
        view?.serverErr("invalid network!")
    }
}