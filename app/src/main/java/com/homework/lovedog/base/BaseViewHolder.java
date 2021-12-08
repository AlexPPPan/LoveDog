package com.homework.lovedog.base;


import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    protected SparseArray<View> views;
    protected BaseRecyclerViewAdapter.OnItemEventListener mOnItemEventListener;
    protected View mItemView;

    public BaseViewHolder(View itemView, BaseRecyclerViewAdapter.OnItemEventListener listener) {
        super(itemView);
        mOnItemEventListener = listener;
        views = new SparseArray<View>();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        mItemView = itemView;
    }

    private <V extends View> V findView(int id) {
        View view = views.get(id);
        if(view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (V)view;
    }

    public View getItemView() {
        return mItemView;
    }

    public View getView(int id) {
        return findView(id);
    }

    public TextView getTextView(int id){
        return findView(id);
    }

    public Button getButton(int viewId) {
        return findView(viewId);
    }

    public RadioButton getRadioButton(int viewId) {
        return findView(viewId);
    }

    public Switch getSwitch(int viewId){
        return findView(viewId);
    }

    public ImageView getImageView(int id) {
        return findView(id);
    }

    public CheckBox getCheckBox(int viewId) {
        return findView(viewId);
    }

    public void setBackgroundColor(int color) {
        mItemView.setBackgroundColor(color);
    }

    @Override
    public void onClick(View view) {
        if(mOnItemEventListener != null) {
            mOnItemEventListener.onItemClick(view, getLayoutPosition());
        }
    }


    @Override
    public boolean onLongClick(View view) {
        if(mOnItemEventListener != null) {
            mOnItemEventListener.onItemLongClick(view, getLayoutPosition());
        }
        return false;
    }
}
