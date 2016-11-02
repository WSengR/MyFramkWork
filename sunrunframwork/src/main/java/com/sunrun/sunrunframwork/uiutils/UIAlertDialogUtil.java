package com.sunrun.sunrunframwork.uiutils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sunrun.sunrunframwork.R;


/**
 * @version V1.0
 * @作者: Wang'sr
 * @时间: 2016年10月26日
 * @功能描述: Dialog对话框总工具类
 */
public final class UIAlertDialogUtil {
    /**
     * 单例模式
     */
    private static UIAlertDialogUtil UIAlertDialogUtil;
    private Dialog sureDialog;
    private Dialog dialogCamera;
    private Dialog selectColorDiagle;
    private Dialog videoSelectScore;
    private Dialog updateDialog;
    /**
     * 显示正在加载的对话框
     */
    private Dialog LoadDialog;

    private UIAlertDialogUtil() {
    }

    public static UIAlertDialogUtil getInstences() {
        if (UIAlertDialogUtil == null) {
            UIAlertDialogUtil = new UIAlertDialogUtil();
        }
        return UIAlertDialogUtil;
    }

    /**
     * 显示加载对话框
     *
     * @param context
     * @param msg     消息信息
     */
    public  Dialog showLoadDialog(Context context, String msg) {
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


//	/**
//	 * 提示下载
//	 *
//	 * @param context
//	 *            上下文
//	 * @param versionString
//	 *            版本信息
//	 * @param bodyString
//	 *            主题内容
//	 * @param loalUrl
//	 *            下载链接
//	 */
//	public static void showUpdateDialog(final Context context, String versionString, String bodyString, final String loalUrl) {
//		View layoutView = View.inflate(context, R.layout.ui_dialog_update, null);
//		TextView tv1 = (TextView) layoutView.findViewById(R.id.tv1);
//		TextView tvTitle = (TextView) layoutView.findViewById(R.id.tv_title);
//		TextView tvContent = (TextView) layoutView.findViewById(R.id.tv_content);
//		tvTitle.setVisibility(View.GONE);
//		tv1.setVisibility(View.GONE);
//		tvContent.setText(String.format("检测到新版本%s是否更新？", versionString));
//		TextView tvSure = (TextView) layoutView.findViewById(R.id.queren);
//		TextView tvCancel = (TextView) layoutView.findViewById(R.id.quxiao);
//		final Dialog createDialog = UiUtils.createDialog(context, layoutView);
//		TextView tv_version_content = (TextView) layoutView.findViewById(R.id.tv_version_content);
//		tv_version_content.setText(Html.fromHtml(Html.fromHtml(bodyString).toString()));
//		createDialog.show();
//		tvSure.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				loadDiglog(context, loalUrl);
//				createDialog.dismiss();
//
//			}
//		});
//		tvCancel.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				createDialog.dismiss();
//			}
//		});
//
//	}
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
