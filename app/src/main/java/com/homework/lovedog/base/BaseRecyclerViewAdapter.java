package com.homework.lovedog.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerViewAdapter<T, Hodler extends BaseViewHolder>
        extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected int[] mItemLayoutResIds;
    protected OnItemEventListener mOnItemEventListener = null;

    public BaseRecyclerViewAdapter(Context context, List<T> datas, int[] itemLayoutResIds) {
        if (datas != null) {
            mDatas = datas;
        } else {
            mDatas = new ArrayList<T>();
        }
        mContext = context;
        mItemLayoutResIds = itemLayoutResIds;
        mInflater = LayoutInflater.from(mContext);
    }

    public BaseRecyclerViewAdapter(Context context, List<T> datas, int itemLayoutResId) {
        if (datas != null) {
            mDatas = datas;
        } else {
            mDatas = new ArrayList<T>();
        }
        mContext = context;
        mItemLayoutResIds = new int[1];
        mItemLayoutResIds[0] = itemLayoutResId;
        mInflater = LayoutInflater.from(mContext);
    }

    public BaseRecyclerViewAdapter(Context context, int layoutResId) {
        this(context, null, layoutResId);
    }

    public interface OnItemEventListener {
        void onItemClick(View view, int position);
        boolean onItemLongClick(View view, int position);
    }

    public void setItemEventListener(OnItemEventListener listener) {
        mOnItemEventListener = listener;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View view = mInflater.inflate(mItemLayoutResId, null, false);
        // 这里需要把parent 给传递过去, 否则在layout中设置的 match 没有用, 自动使用 wrap_content

        View view = mInflater.inflate(mItemLayoutResIds[viewType], parent, false);
        return new BaseViewHolder(view, mOnItemEventListener);
    }

    public BaseViewHolder onCreateViewHolder(View view) {

        //View view = mInflater.inflate(mItemLayoutResId, null, false);
        // 这里需要把parent 给传递过去, 否则在layout中设置的 match 没有用, 自动使用 wrap_content
        return new BaseViewHolder(view, mOnItemEventListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T t = getItem(position);

        bindViewHolder(holder, t, position);
    }

    public abstract void bindViewHolder(BaseViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        if (mDatas == null)
            return 0;
        return mDatas.size();
    }

    public T getItem(int position) {
        if (position >= mDatas.size())
            return null;
        return mDatas.get(position);
    }

    public List<T> getDatas() {

        return mDatas;
    }

    public void clearData() {
        for (Iterator it = mDatas.iterator(); it.hasNext(); ) {
            T t = (T) it.next();
            int position = mDatas.indexOf(t);
            it.remove();
            notifyItemRemoved(position);
        }
    }

    public void addData(List<T> datas) {
        addData(0, datas);
    }

    public void addData(int positon, List<T> datas) {
        if (datas != null && datas.size() > 0) {
            for(T t:datas) {
                mDatas.add(positon, t);
                notifyItemInserted(positon++);
            }
        }
    }

    public void removeData(int position , List<T>datas) {
        mDatas.removeAll(datas);
        notifyItemRangeRemoved(position, datas.size());
    }

    public void refreshData(List<T> list){

        if(list !=null && list.size()>0){
            clearData();
            int size = list.size();
            for (int i=0;i<size;i++){
                mDatas.add(i,list.get(i));
                notifyItemInserted(i);
            }
        }
    }

    public void loadMoreData(List<T> list){
        if(list !=null && list.size()>0){
            int size = list.size();
            int begin = mDatas.size();
            for (int i=0;i<size;i++){
                mDatas.add(list.get(i));
                notifyItemInserted(i+begin);
            }
        }

    }
}
