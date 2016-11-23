package com.example.wsr.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wsr.myapplication.R;
import com.example.wsr.myapplication.view.GradlePrassView;
import com.sunrun.sunrunframwork.view.ToastFactory;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/4
 * @功能描述:
 */

public class GradlePrassDialogUtil {
    private Dialog gradlePrassDialog;
    GradlePrassView gpView1;
    GradlePrassView gpView2;
    GradlePrassView gpView3;
    GradlePrassView gpView4;
    GradlePrassView gpView5;
    Context mContext;

    public GradlePrassDialogUtil(Context context) {
        this.mContext = context;
    }

    /**
     * @param context
     * @return
     */
    public Dialog showGradlePragress(Context context) {
        if (gradlePrassDialog != null)
            gradlePrassDialog.cancel();
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.diagle_graded, null);

        gpView1 = (GradlePrassView) rootView.findViewById(R.id.gpView1);
        gpView2 = (GradlePrassView) rootView.findViewById(R.id.gpView2);
        gpView3 = (GradlePrassView) rootView.findViewById(R.id.gpView3);
        gpView4 = (GradlePrassView) rootView.findViewById(R.id.gpView4);
        gpView5 = (GradlePrassView) rootView.findViewById(R.id.gpView5);
        gpView1.setViewText("第一");
        gpView2.setViewText("第二");
        gpView3.setViewText("第仨");
        gpView4.setViewText("第四");
        gpView5.setViewText("第五个");
        gpView1.statrImgViewAnim(onAnimationFinish, true, 2000);
        gradlePrassDialog = new Dialog(context, com.sunrun.sunrunframwork.R.style.dialog);
        gradlePrassDialog.setCanceledOnTouchOutside(false);
        gradlePrassDialog.setContentView(rootView);
        gradlePrassDialog.show();
        gradlePrassDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.e("wsr", "gradlePrassDialog + onDismiss");
                stopAnim();
            }
        });

        gradlePrassDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
                {
                    stopAnim();
                    Log.e("wsr", "KEYCODE_BACK");
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });

;
        return gradlePrassDialog;
    }

    GradlePrassView.OnAnimationFinish onAnimationFinish = new GradlePrassView.OnAnimationFinish() {
        @Override
        public void AnimationFinish(GradlePrassView gradlePrassView) {

            switch (gradlePrassView.getId()) {
                case R.id.gpView1:
                    boolean isAutoNext = true;
                    if (onBackGradleStub != null) {
                        isAutoNext = onBackGradleStub.backGradleStub(gradlePrassView, gpView2);
                    }
                    gpView2.statrImgViewAnim(this, isAutoNext, 2000);
                    ToastFactory.getToast(mContext, "gpView1").show();
                    break;
                case R.id.gpView2:
                    isAutoNext = true;
                    if (onBackGradleStub != null) {
                        isAutoNext = onBackGradleStub.backGradleStub(gradlePrassView, gpView2);
                    }
                    gpView3.statrImgViewAnim(this, isAutoNext, 2000);
                    ToastFactory.getToast(mContext, "gpView2").show();
                    break;
                case R.id.gpView3:
                    isAutoNext = true;
                    if (onBackGradleStub != null) {
                        isAutoNext = onBackGradleStub.backGradleStub(gradlePrassView, gpView2);
                    }
                    gpView4.statrImgViewAnim(this, isAutoNext, 2000);
                    ToastFactory.getToast(mContext, "gpView3").show();
                    break;
                case R.id.gpView4:
                    isAutoNext = true;
                    if (onBackGradleStub != null) {
                        isAutoNext = onBackGradleStub.backGradleStub(gradlePrassView, gpView5);
                    }
                    gpView5.statrImgViewAnim(this, isAutoNext, 2000);
                    ToastFactory.getToast(mContext, "gpView4").show();
                    break;
                case R.id.gpView5:
                    if (onBackGradleStub != null) {
                         onBackGradleStub.lastAnimFinash(gradlePrassView);
                    }
                    break;
            }
        }
    };




    OnBackGradleStub onBackGradleStub;
    public void setOnBackGradleStub(OnBackGradleStub onBackGradleStub) {
        this.onBackGradleStub = onBackGradleStub;
    }
    public interface OnBackGradleStub {
        boolean backGradleStub(GradlePrassView currView, GradlePrassView nextGradlePrassView);
        boolean lastAnimFinash(GradlePrassView currView);
    }

    public void stopAnim() {
        gpView1.clearAnimation();
        gpView2.clearAnimation();
        gpView3.clearAnimation();
        gpView4.clearAnimation();
        gpView5.clearAnimation();
    }

    public void dismissDialog(){
        gradlePrassDialog.dismiss();
    }
}