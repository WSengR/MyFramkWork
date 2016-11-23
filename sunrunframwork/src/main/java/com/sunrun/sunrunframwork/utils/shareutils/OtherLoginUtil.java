package com.sunrun.sunrunframwork.utils.shareutils;

import java.util.HashMap;

import android.content.Context;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

import com.sunrun.sunrunframwork.http.utils.JsonDeal;
import com.sunrun.sunrunframwork.http.utils.Logger;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.AHandler;
import com.sunrun.sunrunframwork.utils.AppUtils;

public class OtherLoginUtil {
    public static abstract class BaseActionListener implements
            PlatformActionListener {

        @Override
        public void onCancel(Platform arg0, int arg1) {
            UIUtils.shortM("授权取消");
            UIUtils.cancelLoadDialog();
        }

        @Override
        public void onComplete(final Platform platform, int action,
                               HashMap<String, Object> arg2) {
            Logger.E("授权信息:" + JsonDeal.object2Json(arg2));
            // 授权操作
            UIUtils.cancelLoadDialog();
            if (platform.isAuthValid()) {// 判断授权是否有效
                PlatformDb platDB = platform.getDb();// 获取数平台数据DB
                Logger.E(platDB.exportData());
                // 通过DB获取各种数据

                Logger.E("授权成功:" + platDB.getUserId() + " " + platDB.getToken()
                        + " " + platDB.getUserIcon() + " "
                        + platDB.getUserName());
                platDB.getToken();// 获取授权token 用于验证身份
                platDB.getUserGender();// 用户性别
                platDB.getUserIcon();// 用户头像
                platDB.getUserId();// 用户id
                platDB.getUserName();// 用户昵称
                onComplete(platform, platDB);

            }

            // platform.showUser(null);

        }

        @Override
        public void onError(Platform arg0, int arg1, Throwable arg2) {
            UIUtils.shortM("授权失败");
            UIUtils.cancelLoadDialog();
        }

        public abstract void onComplete(Platform platform, PlatformDb platDB);
    }

    /**
     * 第三方授权登录
     *
     * @param context
     * @param name
     */
    private static void authorize(Context context, String name,
                                 BaseActionListener lis) {
        ShareSDK.initSDK(context);
        final Platform weibo = ShareSDK.getPlatform(name);
        if (!weibo.isClientValid() && Wechat.NAME.equals(name)) {// 如果未安装微信客户端
            if (AppUtils.checkPackage(context, "com.tencent.mm")) {
                UIUtils.shortM("微信已禁止被其他应用启动,请检查相关权限");
            } else {
                UIUtils.shortM("目前您的微信版本过低或未安装微信，需要安装微信才能使用");
            }
            // lis.onError(null, 2, null);
            return;
        }

        UIUtils.showLoadDialog(context, "加载中");
        weibo.setPlatformActionListener(lis);
        weibo.authorize();
        AHandler.runTask(new AHandler.Task() {
            @Override
            public void update() {
                UIUtils.cancelLoadDialog();
            }
        }, 5000);
        // 移除授权
        // weibo.removeAccount(true);
    }

    /**
     * 登录到微信
     *
     * @param context
     */
    public static void loginWeChat(Context context, final OnOtherLoginCallback onOtherLoginCallback) {
        // UiUtils.shortM("微信登录");
        OtherLoginUtil.authorize(context, Wechat.NAME, new BaseActionListener() {

            @Override
            public void onComplete(Platform platform, PlatformDb platDB) {
//                other_login(2, platDB.getUserId(), platDB.getUserName(),
//                        platDB.getUserIcon(), platDB.getUserGender());
                onOtherLoginCallback.loginCallback(platDB);
            }

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                super.onError(arg0, arg1, arg2);
                onOtherLoginCallback.loginCallback(null);
            }

        });

    }

    /**
     * 登录到微博
     *
     * @param context
     */
    public static void loginSinaWeibo(Context context, final OnOtherLoginCallback onOtherLoginCallback) {
        OtherLoginUtil.authorize(context, SinaWeibo.NAME,
                new BaseActionListener() {
                    @Override
                    public void onComplete(Platform platform, PlatformDb platDB) {
                        onOtherLoginCallback.loginCallback(platDB);
//                        other_login(3, platDB.getUserId(),
//                                platDB.getUserName(), platDB.getUserIcon(),
//                                platDB.getUserGender());
                    }

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        super.onError(arg0, arg1, arg2);
                        onOtherLoginCallback.loginCallback(null);
                    }
                });

    }


    public static void loginQQ(Context context, final OnOtherLoginCallback onOtherLoginCallback) {
        // QQ登录授权
        OtherLoginUtil.authorize(context, QQ.NAME, new BaseActionListener() {

            @Override
            public void onComplete(Platform platform, PlatformDb platDB) {
//                other_login(1, platDB.getUserId(), platDB.getUserName(),
//                        dealQQhead(platDB.getUserIcon()),
//                        platDB.getUserGender());
                onOtherLoginCallback.loginCallback(platDB);

            }

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                super.onError(arg0, arg1, arg2);
                onOtherLoginCallback.loginCallback(null);
            }

        });

    }

    /**
     * 登录返回接口   PlatformDb为空的时候证明登录失败
     */
    public interface OnOtherLoginCallback {
        void loginCallback(PlatformDb platDB);
    }
}
