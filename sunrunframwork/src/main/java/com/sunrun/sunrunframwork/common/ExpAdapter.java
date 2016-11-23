package com.sunrun.sunrunframwork.common;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/9
 * @功能描述: 抽象父类 (还未深度封装)
 */
public abstract class ExpAdapter<T> extends BaseExpandableListAdapter {

    protected Activity mAct;
    protected LayoutInflater mInflater;
    protected List<T> mListData;


    public ExpAdapter(Activity act) {
        mAct = act;
        mInflater = LayoutInflater.from(mAct);
        mListData = new ArrayList<T>();
    }

    public ExpAdapter(Context context) {
        mListData = new ArrayList<T>();
    }

    public void addListData(T t, boolean isNotify) {
        mListData.add(t);
        if (isNotify) {
            notifyDataSetChanged();
        }
    }

    public void addListData(int location, T t, boolean isNotify) {
        mListData.add(location, t);
        if (isNotify) {
            notifyDataSetChanged();
        }
    }

    public void addListData(T t, boolean isNotify, int position) {
        mListData.add(position, t);
        if (isNotify) {
            notifyDataSetChanged();
        }
    }

    public void setListData(List<T> list) {
        if (mListData != null) {
            mListData.clear();
            mListData.addAll(list);
            notifyDataSetChanged();
        }
    }


    public void addListData(List<T> list) {
        if (list != null) {
            mListData.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addListData(int location, List<T> list) {
        if (list != null) {
            mListData.addAll(location, list);
            notifyDataSetChanged();
        }
    }

    public void clearListData() {
        mListData.clear();
        notifyDataSetChanged();
    }

    public void removeListData(int location) {
        mListData.remove(location);
        notifyDataSetChanged();
    }


    public void removeListData(List<T> location) {
        for (int i = 0; i < location.size(); i++) {
            mListData.remove(location.get(i));
        }
        notifyDataSetChanged();
    }

    public void removeListData(T t) {
        mListData.remove(t);
        notifyDataSetChanged();
    }

    public void setChangeData(int position, T t) {
        mListData.set(position, t);
        notifyDataSetChanged();
    }

    public List<T> getmListData() {
        return mListData;
    }


    /**
     * ================   不长用的   =====================
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 打开所有的
     *
     * @param expandableListView
     */
    public static void openAllChild(ExpandableListView expandableListView) {
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }
    }

    /**
     * 关闭所有的
     *
     * @param expandableListView
     */
    public static void closeAllChild(ExpandableListView expandableListView) {
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.collapseGroup(i);
        }
    }

    /**
     * 打开指定的同时关闭其他的
     *
     * @param expandableListView
     * @param index 最低为0
     */
    public static void openPositionAndCloseOtherChild(ExpandableListView expandableListView, int index) {
        for (int i = 0; i < expandableListView.getCount(); i++) {
            if (index != i) {
                expandableListView.collapseGroup(i); //关闭
            } else {
                expandableListView.expandGroup(index);
            }
        }
    }


}
