package com.homework.lovedog.base;

import android.content.Context;

import java.util.List;

public abstract class SimpleRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T, BaseViewHolder> {

    public SimpleRecyclerViewAdapter(Context context, List<T> datas, int itemLayoutResId) {
        super(context, datas, itemLayoutResId);
    }
}
