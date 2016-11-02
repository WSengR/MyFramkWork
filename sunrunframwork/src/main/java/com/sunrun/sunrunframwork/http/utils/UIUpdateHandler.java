package com.sunrun.sunrunframwork.http.utils;

import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.cache.NetSession;

/**
 * 界面更新接口
 *
 * @author cnsunrun
 */
public interface UIUpdateHandler {
    /**
     * 界面更新通知
     *
     * @param requestCode 请求码
     * @param bean	请求结果
     */
    public void nofityUpdate(int requestCode, BaseBean bean);

    /**
     * 界面更新通知,用于更新进度
     *
     * @param requestCode 请求码
     * @param progress
     *            网络请求进度
     */
    public void nofityUpdate(int requestCode, float progress);

    /**
     * 结果数据的处理(用来根据消息状态进行不同的提示即可)
     *
     * @param requestCode 请求码
     * @param bean 请求结果
     */
    public void dealData(int requestCode, BaseBean bean);

    /**
     * 数据载入失败时回调方法
     */
    public void loadFaild(int requestCode, BaseBean bean);

    /**
     * 无数据时回调方法
     */
    public void emptyData(int requestCode, BaseBean bean);
    public void requestFinish();
    public void requestCancel();
    public void requestStart();
    /**
     * 获取缓存对象
     *
     * @return 网络缓存对象
     */
    public NetSession getSession();

}
