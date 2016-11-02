package com.sunrun.sunrunframwork.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sunrun.sunrunframwork.utils.LogUtils;
import com.sunrun.sunrunframwork.view.ToastFactory;

import org.apache.http.Header;


/**
 * 网络请求封装类利用 Handler回调实现
 *
 * @author Ren
 */
public class HttpModeBase {

    // Ctrl+Shift+X小写转大写

    /* 导航 */
    public static final int ID_DAOHANG = 0x00001;
    /* benner */
    public static final int ID_Benner = 0x00002;
    /* 主题部分 */
    public static final int ID_BODY = 0x00003;

    /**
     * 采用AsyncHttpClient的Get方式进行实现
     *
     * @param mContext 上下文对象
     * @param TypeID   类型ID
     * @param URl      请求URL
     *                 <p/>
     *                 回调
     */
    public static void doGet(final Context mContext, final int TypeID,
                             final String URl, final Handler HttpCallback) {
        /**
         * 成功处理的方法 statusCode:响应的状态码; headers:相应的头信息 比如 响应的时间，响应的服务器 ;
         * responseBody:响应内容的字节
         */
        HttpUtils.doGet(UrlBase.bodyHttp + URl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                LogUtils.e("Http", statusCode + "URl:" + URl);
                if (statusCode == 200) {
                    String resule = new String(responseBody);
                    Message msg = new Message();
                    msg.what = TypeID;
                    msg.obj = resule;
                    HttpCallback.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                LogUtils.e("Http", statusCode + "" + "URl:" + URl);
                ToastFactory.getToast(mContext, "请求失败" + statusCode).show();
                error.printStackTrace();// 把错误信息打印出轨迹来
            }
        });

    }

    /**
     * 采用AsyncHttpClient的Post方式进行实现
     */
    public void getRegister2(final Context mContext, final String URl) {
        // 创建请求参数的封装的对象
        RequestParams params = new RequestParams();
        params.put("cellphone", "13088888000");
        params.put("password", "123456");
        // cellphone=13088888000&password=123456
        /**
         * 成功处理的方法 statusCode:响应的状态码; headers:相应的头信息 比如 响应的时间，响应的服务器 ;
         * responseBody:响应内容的字节
         */
        HttpUtils.doPost(URl, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                LogUtils.e("Http", statusCode + "URl" + URl);
                if (statusCode == 200) {
                    String resule = new String(responseBody);
                    ToastFactory.getToast(mContext, "成功" + resule).show();
                }
            }

            /**
             * 失败处理的方法 error：响应失败的错误信息封装到这个异常对象中
             */
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                LogUtils.e("Http", statusCode + "URl" + URl);
                ToastFactory.getToast(mContext, "请求失败" + statusCode).show();
                error.printStackTrace();// 把错误信息打印出轨迹来

            }
        });

    }
}
