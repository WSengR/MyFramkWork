package com.sunrun.sunrunframwork.uiutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UIPopupWindowUtil {
//
//    private static MyPopupWindowUtil myPopupWindowUtil;
//    private PopupWindow popupWindow;
//
//    private MyPopupWindowUtil() {
//    }
//
//    public static MyPopupWindowUtil getInstences() {
//
//        if (myPopupWindowUtil == null) {
//            myPopupWindowUtil = new MyPopupWindowUtil();
//        }
//        return myPopupWindowUtil;
//    }
//
//    View contentView;
//    ProblemTypeAdapter mAdapter;
//
//    public void shouProblemListType(View view, Context mContext, Object o, AdapterView.OnItemClickListener listener) {
//
//        contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_popupwindow_view, null);
//        ListView lvProblem = (ListView) contentView.findViewById(R.id.lv_problem_type);
//        mAdapter = new ProblemTypeAdapter((Activity) mContext);
//        lvProblem.setAdapter(mAdapter);
//        popupWindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setContentView(contentView);
//        popupWindow.setOutsideTouchable(true);
//        ColorDrawable dw = new ColorDrawable(0000000000);
//        popupWindow.setBackgroundDrawable(dw);
//
//        lvProblem.setOnItemClickListener(listener);
//        if (o != null) {
//            mAdapter.setListData((List<Map<String, Object>>) o);
//        }
//        popupWindow.showAsDropDown(view);
//
//    }
//
//    public void shouSelectCity(View view, final Context mContext, Object o, AdapterView.OnItemClickListener listener) {
//        PopupListAdapter popupListAdapter = new PopupListAdapter((Activity) mContext, null);
//        contentView = LayoutInflater.from(mContext).inflate(R.layout.time_dialog, null);
//        TextView mDialogTimeTitle = (TextView) contentView.findViewById(R.id.dialog_time_title);
//        MyListView mDialogTimeLv = (MyListView) contentView.findViewById(R.id.dialog_time_lv);
//        mDialogTimeLv.setAdapter(popupListAdapter);
//        mDialogTimeTitle.setVisibility(View.GONE);
//        popupWindow = new PopupWindow(contentView, UIUtils.getScreenWidth(mContext) / 2, LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setContentView(contentView);
//        popupWindow.setOutsideTouchable(true);
//        ColorDrawable dw = new ColorDrawable(0x90000000);
//        popupWindow.setBackgroundDrawable(dw);
//        mDialogTimeLv.setOnItemClickListener(listener);
//        if (o != null) {
//            popupListAdapter.setListData((ArrayList<String>) o);
//        }
//        popupWindow.showAsDropDown(view);
//    }
//
//
//    class popupWindow extends PopupWindow {
//        public boolean onTouchEvent(MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                dismissPopupWindow();
//                return true;
//            }
//            return false;
//        }
//    }
//
//    public void dismissPopupWindow(PopupWindow popupWindow) {
//        if (popupWindow != null) {
//            popupWindow.dismiss();
//            popupWindow = null;
//        }
//    }
//
//    public void dismissPopupWindow() {
//        if (popupWindow != null) {
//            popupWindow.dismiss();
//            popupWindow = null;
//        }
//    }
//
//    public PopupWindow getPopupWindow() {
//        return popupWindow;
//    }
//
//    public void setPopupWindow(PopupWindow popupWindow) {
//        this.popupWindow = popupWindow;
//    }


}
