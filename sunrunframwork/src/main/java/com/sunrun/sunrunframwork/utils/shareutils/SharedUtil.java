package com.sunrun.sunrunframwork.utils.shareutils;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View.OnClickListener;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


import com.sunrun.sunrunframwork.R;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

public class SharedUtil {

    final public static String DOWN = "http://test.cnsunrun.com/qkn/Home/Public/down.html";

    public static void showSharenosrs(Context context, Object url, String imgUrl, String name) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
        // oks.setNotification(R.drawable.ic_launcher,
        // getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(EmptyDeal.isEmpy(name) ? "来自爱天口语的分享" : name);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(DOWN);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我在爱天上发现了一个很有意思的东西,分享给你");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        if (EmptyDeal.isEmpy(imgUrl)) {
            oks.setImagePath(ic_luncher(context));// 确保SDcard下面存在此张图片
        } else {
            oks.setImageUrl(imgUrl);
            // oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        }
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(DOWN);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        // oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("爱天口语");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(DOWN);

        // 启动分享GUI
        oks.show(context);
    }

    public static void saveIcon(Context context) {
        Bitmap bit = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_launcher);
        String path = ic_luncher(context);
        if (!new File(path).exists())
            UIUtils.saveBitmapToFile(bit, path);
    }

    public static String ic_luncher(Context context) {
        return new File(context.getCacheDir(), "ic_launcher.jpg")
                .getAbsolutePath();
    }


}
