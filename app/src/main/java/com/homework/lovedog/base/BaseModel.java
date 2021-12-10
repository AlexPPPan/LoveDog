package com.homework.lovedog.base;

import androidx.lifecycle.LifecycleOwner;



public class BaseModel {
    protected LifecycleOwner mLifecycleOwner;

    public BaseModel(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }
}
