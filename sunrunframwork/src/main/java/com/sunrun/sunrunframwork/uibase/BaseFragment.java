package com.sunrun.sunrunframwork.uibase;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.sunrun.sunrunframwork.app.BaseApplication;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.NAction;
import com.sunrun.sunrunframwork.http.NetRequestHandler;
import com.sunrun.sunrunframwork.http.NetServer;
import com.sunrun.sunrunframwork.http.cache.NetSession;
import com.sunrun.sunrunframwork.http.utils.Logger;
import com.sunrun.sunrunframwork.http.utils.UIUpdateHandler;
import com.sunrun.sunrunframwork.uiutils.UIUtils;


/**
 * @fragment 基类, 用于初始化一些公共的东西, 使得Fragment更好使用
 */
public abstract class BaseFragment extends Fragment implements
        NetRequestHandler, UIUpdateHandler {
    protected FragmentActivity mAct;
    protected NetServer mNServer;
    protected Activity that;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        that = mAct = getActivity();
        BaseApplication.getInstance().addFragment(this);
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化网络请求模块,由子类在有需要时调用
     */
    protected void initNetServer() {
        if (mNServer == null)
            mNServer = new NetServer(mAct, this);
    }

    @Override
    public void requestAsynGet(NAction action) {
        initNetServer();
        mNServer.requestAsynGet(action);

    }

    @Override
    public void useCache(boolean useCache) {
        initNetServer();
        mNServer.useCache(useCache);
    }

    @Override
    public void requestAsynPost(NAction action) {
        initNetServer();
        mNServer.requestAsynPost(action);
    }

    @Override
    public void cancelRequest(int requestCode) {
        if (mNServer != null)
            mNServer.cancelRequest(requestCode);
    }

    @Override
    public void cancelAllRequest() {
        if (mNServer != null)
            mNServer.cancelAllRequest();
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {

    }

    @Override
    public void nofityUpdate(int requestCode, float progress) {

    }

    @Override
    public void dealData(int requestCode, BaseBean bean) {

    }

    @Override
    public void loadFaild(int requestCode, BaseBean bean) {

    }

    @Override
    public void emptyData(int requestCode, BaseBean bean) {

    }

    @Override
    public void requestStart() {

    }

    @Override
    public void requestCancel() {
        UIUtils.cancelLoadDialog();
    }

    @Override
    public void requestFinish() {
        UIUtils.cancelLoadDialog();
    }


    @Override
    public NetSession getSession() {
        return NetSession.instance(mAct);
    }

    @Override
    public void onDestroyView() {
        cancelAllRequest();
        BaseApplication.getInstance().removeFragment(this);
        Logger.E("视图销毁 " + this);
        that = null;
        super.onDestroyView();
    }

    protected void onBack() {
        finish();
    }

    public void finish() {
        if (mAct != null)
            mAct.finish();
    }

    /**
     * 切换Fragment
     *
     * @param toFragmentClass
     */
    public synchronized void turnToFragment(
            Class<? extends Fragment> toFragmentClass, int layId) {
        try {
            turnToFragment(toFragmentClass.newInstance(), layId);
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ;
    }

    /**
     * 切换Fragment
     *
     * @param toFragmentClass
     */
    public synchronized void turnToFragment(Fragment toFragmentClass, int layId) {
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().replace(layId, toFragmentClass)
                .commitAllowingStateLoss();
    }
}
