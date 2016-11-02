package com.sunrun.sunrunframwork.common;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @类名: CommonAdapter.java
 * @功能描述: 封装BaseAdapter, 结合ViewHolder精简代码
 * @作者 Wangsr
 * @时间 2015-9-7 下午3:33:13
 * @创建版本 V3.0
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> mDatas;
    protected Activity mContext;
    protected LayoutInflater mInflater;
    protected int layoutId;
    public Activity mAct;
    public Handler handler;

    public List<T> getDatas() {
        return mDatas;
    }

    public void addListData(T t, boolean isNotify) {
        mDatas.add(t);
        if (isNotify) {
            notifyDataSetChanged();
        }
    }

    public void addListData(int location, T t, boolean isNotify) {
        mDatas.add(location, t);
        if (isNotify) {
            notifyDataSetChanged();
        }
    }



    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setListData(List<T> list) {
            mDatas.clear();
            mDatas.addAll(list);
            notifyDataSetChanged();
    }


    public void addListData(List<T> list) {
        if (list != null) {
            mDatas.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addListData(int location, List<T> list) {
        if (list != null) {
            mDatas.addAll(location, list);
            notifyDataSetChanged();
        }
    }

    public void clearListData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void removeListData(int location) {
        mDatas.remove(location);
        notifyDataSetChanged();
    }

    public void removeListData(T t) {
        mDatas.remove(t);
        notifyDataSetChanged();
    }

    public void setChangeData(int position, T t) {
        mDatas.set(position, t);
        notifyDataSetChanged();
    }


    /**
     * @param mAct     上下文
     * @param datas    数据集
     * @param layoutId 布局ID
     */
    public CommonAdapter(Activity mAct, List<T> datas, int layoutId) {
        this.mContext = mAct;
        if(datas!=null){
            this.mDatas = datas;
        }else{
            this.mDatas=new ArrayList<>();
        }
        this.mInflater = LayoutInflater.from(mAct);
        this.layoutId = layoutId;
        this.mAct = mAct;
    }

    @Override
    public int getCount() {
        if (mDatas == null)
            return 0;
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);

        convert(holder, getItem(position), position);

        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t, int position);

}
