package com.homework.lovedog.base

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LifecycleOwner


interface IBaseView {

    fun getActivity(): Activity?

    fun serverErr(message: String?)

    fun getViewLifecycleOwner(): LifecycleOwner?

    fun getContext(): Context?

}