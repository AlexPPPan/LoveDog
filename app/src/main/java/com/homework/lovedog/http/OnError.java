package com.homework.lovedog.http;


import android.text.TextUtils;

import io.reactivex.functions.Consumer;
import rxhttp.wrapper.exception.HttpStatusCodeException;
import rxhttp.wrapper.exception.ParseException;


public interface OnError extends Consumer<Throwable> {

    @Override
    default void accept(Throwable throwable) throws Exception {
        String errorMsg = ExceptionHelper.handleNetworkException(throwable);
        if (throwable instanceof ParseException) {
            String errorCode = throwable.getLocalizedMessage();
            if ("-1".equals(errorCode)) {
                errorMsg = "Request Wrong!,try again";
            } else {
                errorMsg = throwable.getMessage();
                if (TextUtils.isEmpty(errorMsg)) errorMsg = errorCode;
            }
        } else if (throwable instanceof HttpStatusCodeException) {
            String code = throwable.getLocalizedMessage();
            if ("416".equals(code)) {
                errorMsg = "Request Wrong!";
            }
        }
        boolean isConsume = onError(throwable, errorMsg);
        if (!isConsume && !TextUtils.isEmpty(errorMsg)){
//            Tip.show(errorMsg);
        }

    }

    //返回事件是否被消费
    boolean onError(Throwable throwable, String errorMsg) throws Exception;
}
