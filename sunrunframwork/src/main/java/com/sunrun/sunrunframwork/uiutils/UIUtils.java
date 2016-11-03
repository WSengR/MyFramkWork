package com.sunrun.sunrunframwork.uiutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sunrun.sunrunframwork.R;
import com.sunrun.sunrunframwork.app.BaseApplication;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.utils.EmptyDeal;
import com.sunrun.sunrunframwork.utils.PathDeal;
import com.sunrun.sunrunframwork.weight.wq.imageloader.MulitImageAct;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @作者: Wang'sr
 * @时间: 2016/10/31
 * @功能描述: UI总父类
 */

public class UIUtils {

    /*******************************   showLoadDialog ************************************/
    /**
     * 显示加载对话框
     *
     * @param context
     * @param msg
     *            消息信息
     */
    public static void showLoadDialog(Context context, String msg) {
       UIAlertDialogUtil.getInstences().showLoadDialog(context, msg);
    }
    /**
     * 取消加载对话框
     */
    public static synchronized void cancelLoadDialog() {
        UIAlertDialogUtil.getInstences().dismiss();
    }



    /*******************************   Toast ************************************/

    /**
     * 显示短Toast
     *
     * @param msg
     */
    public static void shortM(Object msg) {
        if (msg instanceof BaseBean) {
            if (EmptyDeal.isEmpy(((BaseBean) msg).msg)) {
                msg = ((BaseBean) msg).data;
            } else {
                msg = ((BaseBean) msg).msg;
            }
        }
        Toast.makeText(BaseApplication.getInstance(), String.valueOf(msg),
                Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * 显示长Toast
     *
     * @param msg
     */
    public static void longM(Object msg) {
        if (msg instanceof BaseBean) {
            if (EmptyDeal.isEmpy(((BaseBean) msg).msg)) {
                msg = ((BaseBean) msg).data;
            } else {
                msg = ((BaseBean) msg).msg;
            }
        }
        Toast.makeText(BaseApplication.getInstance(), String.valueOf(msg),
                Toast.LENGTH_LONG).show();
    }

    // 显示字符串资源的提示信息
    public static void longM(int strId) {
        Toast.makeText(BaseApplication.getInstance(), strId, Toast.LENGTH_LONG)
                .show();
    }

    public static void shortM(int strId) {
        Toast.makeText(BaseApplication.getInstance(), strId, Toast.LENGTH_SHORT)
                .show();
    }

    /*******************************   裁剪图片      ************************************/
    /**
     * 调用拍照
     *
     * @param activity
     * @return
     */
    public static Uri startCamera(Activity activity) {
        Uri cameraUri = null;
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File file = new File(path, System.currentTimeMillis() + ".jpg");
            cameraUri = Uri.fromFile(file);
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        }
        activity.startActivityForResult(intentFromCapture, 1);
        return cameraUri;
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public static void startPhotoZoom(Activity activity, Uri uri, int W, int H) {
        Log.d("weiquan ", "图片剪裁");
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            String url= PathDeal.getPath(activity, uri);
            intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
        }else{
            intent.setDataAndType(uri, "image/*");
        }
        //intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", W);
        intent.putExtra("outputY", H);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, 0);
    }
    /**
     * 选择图片
     *
     * @param activity
     */
    public static void selectPhoto(Activity activity) {
        Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        Log.d("weiquan ", "相册");
        activity.startActivityForResult(intentFromGallery, 2);
    }


    /**
     * 选择图片,多选
     *
     * @param activity
     */
    public static void selectMuitPhoto(Activity activity, Intent intent) {
        intent.setClass(activity, MulitImageAct.class);
        Log.d("weiquan ", "相册");
        activity.startActivityForResult(intent, 3);
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public static void startPhotoZoom(Activity activity, int aspectX,
                                      int aspectY, Uri uri) {
        Log.d("weiquan ", "图片剪裁");
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            String url=PathDeal.getPath(activity,uri);
            intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
        }else{
            intent.setDataAndType(uri, "image/*");
        }
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        // intent.putExtra("outputX", W);
        // intent.putExtra("outputY", H);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, 0);
    }




    /**
     * 创建对话框
     *
     * @param context
     * @param dialogView
     *            对话框展示的视图
     * @return
     */
    public static Dialog createDialog(Context context, View dialogView) {
        return createDialog(context, dialogView, true);
    }
    /**
     * 创建对话框
     *
     * @param context
     * @param outCancel
     *            是否可触摸外部取消
     * @param msg
     *            消息内容
     * @param ok
     *            确认按钮监听
     * @param cancel
     *            取消按钮监听
     * @return
     */
    public static Dialog createDialog(Context context, boolean outCancel,
                                      String msg, View.OnClickListener ok, View.OnClickListener cancel) {
        View dialogView = View.inflate(context, R.layout.dialog_alert_, null);
        if (msg != null)
            ((TextView) dialogView.findViewById(R.id.msg)).setText(msg);
        dialogView.findViewById(R.id.submit).setOnClickListener(ok);
        dialogView.findViewById(R.id.cancel).setOnClickListener(cancel);
        return createDialog(context, dialogView, outCancel);
    }

    public static Dialog createDialog(Context context, View dialogView,
                                      boolean outCancel) {
        return createDialog(context, dialogView, 0, 0, outCancel);
    }

    public static Dialog createDialog(Context context, View dialogView,
                                      boolean outCancel, int dialogStyle) {
        return createDialog(context, dialogView, dialogStyle, 0, outCancel);
    }

    public static Dialog createDialog(Context context, View dialogView,
                                      int animStyle, boolean outCancel) {
        return createDialog(context, dialogView, 0, animStyle, outCancel);
    }
    public static Dialog createDialog(Context context, View dialogView,
                                      int dialogStyle, int animStyle, boolean outCancel) {
        if (dialogView == null)
            return null;
        final Dialog dialog = new Dialog(context,
                dialogStyle == 0 ? R.style.dialog : dialogStyle);
        dialog.getWindow().setWindowAnimations(
                animStyle == 0 ? R.style.dialogWindowAnim : animStyle);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(outCancel);
        dialog.show();
        if (outCancel) {
            View out = dialogView.findViewById(R.id.out);
            if (out != null)
                out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        }
        return dialog;
    }

    /*******************************   保存图片到本地      ************************************/
    /**
     * Save Bitmap to a file.保存图片到SD卡。
     *
     * @param bitmap
     * @param file
     * @return error message if the saving is failed. null if the saving is
     *         successful.
     * @throws IOException
     */
    public static boolean saveBitmapToFile(Bitmap bitmap, String _file) {
        return saveBitmap(bitmap, _file, 100);
    }

    private static boolean saveBitmap(Bitmap bitmap, String _file, int quality) {
        BufferedOutputStream os = null;
        try {
            File file = new File(_file);
            // String _filePath_file.replace(File.separatorChar +
            // file.getName(), "");
            int end = _file.lastIndexOf(File.separator);
            String _filePath = _file.substring(0, end);
            File filePath = new File(_filePath);
            if (!filePath.exists()) {// 路径不存在时尝试创建路径
                filePath.mkdirs();
            }
            file.createNewFile();
            os = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, os);
            return true;
        } catch (IOException e) {
            Log.e("-->", e.getMessage() + "  " + _file);
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    Log.e("-->", e.getMessage(), e);
                }
            }
        }
        return false;
    }

    /**
     * Save Bitmap to a file.保存图片到SD卡。
     *
     * @param bitmap
     * @param file
     * @return error message if the saving is failed. null if the saving is
     *         successful.
     * @throws IOException
     */
    public static String saveBitmapToFile(String aimpath, String _file,
                                          int quality) {
        File file = new File(_file);
        if (file.exists() && file.length() != 0)
            return _file;
        if (saveBitmap(BitmapFactory.decodeFile(aimpath), _file, quality)) { return _file; }
        return aimpath;
    }

    /**
     * 读取资源目录下的图片
     *
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap getBitmapForasses(Context context, String uri) {
        try {
            Bitmap bm = BitmapFactory.decodeStream(context.getAssets()
                    .open(uri));
            return bm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] getBitmapWH(Bitmap bitmap) {
        return new int[]
                { bitmap.getWidth(), bitmap.getHeight() };
    }

    /*******************************   像素转换     ************************************/

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param scale   （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(DisplayMetrics metrics, float pxValue) {
        final float scale = metrics.density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param scale    （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(DisplayMetrics metrics, float dipValue) {
        final float scale = metrics.density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dip2px(Context context,int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(DisplayMetrics metrics, float pxValue) {
        final float fontScale = metrics.scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(DisplayMetrics metrics, float spValue) {
        final float fontScale = metrics.scaledDensity;
        return (int) (spValue * fontScale + 0.5f);

    }

}
