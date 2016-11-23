package com.example.wsr.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wsr.myapplication.view.GradlePrassView;
import com.sunrun.sunrunframwork.R;
import com.sunrun.sunrunframwork.view.ToastFactory;

import butterknife.Bind;


/**
 * @version V1.0
 * @作者: Wang'sr
 * @时间: 2016年10月26日
 * @功能描述: Dialog对话框总工具类
 */
public final class MyAlertDialogUtil {
    /**
     * 单例模式
     */
    private static MyAlertDialogUtil MyAlertDialogUtil;
    private Dialog sureDialog;
    private Dialog dialogCamera;
    private Dialog selectColorDiagle;
    private Dialog videoSelectScore;
    /**
     * 显示正在加载的对话框
     */
    private Dialog LoadDialog;

    private MyAlertDialogUtil() {
    }

    public static MyAlertDialogUtil getInstences() {
        if (MyAlertDialogUtil == null) {
            MyAlertDialogUtil = new MyAlertDialogUtil();
        }
        return MyAlertDialogUtil;
    }

    /**
     * 显示加载对话框
     *
     * @param context
     * @param msg     消息信息
     */
    public Dialog showLoadDialog(Context context, String msg) {
        if (LoadDialog != null)
            LoadDialog.cancel();
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.dialog_loading, null);
        TextView msgText = (TextView) rootView.findViewById(R.id.msg);
        msgText.setText(msg);
        LoadDialog = new Dialog(context, R.style.CustomDialog);
        LoadDialog.setCanceledOnTouchOutside(false);
        LoadDialog.setContentView(rootView);
        LoadDialog.show();
        return LoadDialog;
    }







//
//	/**
//	 * 正在下载的进度框
//	 *
//	 * @param context
//	 *            上下文
//	 * @param lodeUrl
//	 *            下载链接
//	 */
//	public static void loadDiglog(final Context context, final String lodeUrl) {
//		View dialog_download = View.inflate(context, R.layout.ui_dialog_upda, null);
//		final ProgressBar mProgress = (ProgressBar) dialog_download.findViewById(R.id.progress);
//		final TextView tv_total = (TextView) dialog_download.findViewById(R.id.tv_total);
//		final TextView tv_count = (TextView) dialog_download.findViewById(R.id.tv_count);
//		final TextView tv_baifenbi = (TextView) dialog_download.findViewById(R.id.tv_baifenbi);
//		TextView quxiao = (TextView) dialog_download.findViewById(R.id.quxiao);
//		final Dialog createDialog = UiUtils.createDialog(context, dialog_download);
//		createDialog.show();
//		quxiao.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				UpdateUtils.stop();
//				createDialog.dismiss();
//			}
//		});
//
//		UpdateUtils.doUpate(lodeUrl, new UpdateUtils.UpdataeCallbackListener() {
//			@Override
//			public void OnUpdateProgress(final int length, final int count, final int progress) {
////				Log.e("wsr", "load " + length + " length " + count + " count " + progress + "progress");
//				CommentUtil.runOnUIThread(new Runnable() {
//					@Override
//					public void run() {
//						mProgress.setProgress(progress);
//						tv_total.setText(UpdateUtils.bytes2kb(length) + "");
//						tv_count.setText(UpdateUtils.bytes2kb(count) + "");
//						tv_baifenbi.setText(progress + "");
//
//					}
//				});
//			}
//			@Override
//			public void OnUpdateFinish(String saveFileName, final File lodeFile) {
////				Log.e("wsr", "saveFileName");
//				CommentUtil.runOnUIThread(new Runnable() {
//					@Override
//					public void run() {
//						CommentUtil.showSingleToast(context, "已经下载完成");
//						AppUtils.installApk(context, lodeFile);
//						createDialog.dismiss();
//					}
//				});
//
//			}
//		});
//
//	}

    public void dismiss() {
        if (LoadDialog != null && LoadDialog.isShowing()) {
            LoadDialog.dismiss();
            LoadDialog = null;
        }
        if (sureDialog != null) {
            sureDialog.dismiss();
            sureDialog = null;
        }
        if (dialogCamera != null) {
            dialogCamera.dismiss();
            dialogCamera = null;
        }
        if (selectColorDiagle != null) {
            selectColorDiagle.dismiss();
            selectColorDiagle = null;
        }
        if (videoSelectScore != null) {
            videoSelectScore.dismiss();
            videoSelectScore = null;
        }
        System.gc();
    }

    public interface DiagleCallbackListener {
        void OnDiagleCallbackListener(int type, Object data);
    }

}
