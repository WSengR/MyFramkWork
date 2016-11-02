package com.sunrun.sunrunframwork.uibase;

import java.io.File;
import java.util.ArrayList;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

import com.sunrun.sunrunframwork.R;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.sunrun.sunrunframwork.utils.Utils;

/**
 * 图片选择页面
 * 使用方法:
 * 		Intent intent = new Intent(that, PhotoSelActivity.class);
		intent.putExtra("W", 480);
		intent.putExtra("H", 240);
		startActivityForResult(intent, 101);
		不传宽高数据时,会进入剪裁流程
		调用类在onActivityResult中接收图片文件路径
			data.getStringExtra(PhotoSelActivity.RESULT);
 * @author WQ 2015年11月3日
 */
public class PhotoSelActivity extends BaseActivity {
	Uri uri;
	public static final String RESULT = "result";
	Dialog selHead;
	int W, H;
	boolean isMuit=false;
//	int max=0;
	@Override
	protected void initView() {
		W = getIntent().getIntExtra("W", 0);
		H = getIntent().getIntExtra("H", 0);
		isMuit=getIntent().getBooleanExtra("isMuit", false);
//		max=getIntent().getIntExtra("max", 0);
		View dialogView = View.inflate(this, R.layout.picture_select, null);
		selHead = UIUtils.createDialog(this, dialogView, R.style.bottomInWindowAnim, true);
		dialogView.findViewById(R.id.camera).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selHead.dismiss();
				uri = UIUtils.startCamera(PhotoSelActivity.this);
			}
		});
		dialogView.findViewById(R.id.photo_album).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selHead.dismiss();
				if(isMuit){
					//多选模式
					UIUtils.selectMuitPhoto(PhotoSelActivity.this, getIntent());
				}else {
					UIUtils.selectPhoto(PhotoSelActivity.this);
				}
				
			}
		});
		dialogView.findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selHead.cancel();
			}
		});
		dialogView.findViewById(R.id.out).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selHead.cancel();
			}
		});
		selHead.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				dispathResult(RESULT_CANCELED, null);
			}
		});
	}

	private void dispathResult(int resultCode, Object path) {
		Intent data = null;
		if (resultCode == RESULT_OK) {
			data = new Intent();
			if(path instanceof String){
			data.putExtra("result", (String)path);
			}else if(path instanceof ArrayList){
				data.putExtra("result", (ArrayList<String>)path);
			}
		}
		setResult(resultCode, data);
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//
		if(resultCode!=RESULT_OK){
			UIUtils.shortM(R.string.toast_operate_cancel);
			dispathResult(RESULT_CANCELED, null);
			return ;
		}
		if (requestCode == 0 || requestCode == 1 || requestCode == 2 || requestCode==3) {
			Object obj = null;
			if (requestCode == 0 && data == null) {
				UIUtils.shortM(R.string.toast_operate_cancel);
				dispathResult(RESULT_CANCELED, null);
			} else if (requestCode == 1 && data != null) {
				UIUtils.shortM(R.string.toast_operate_cancel);
				dispathResult(RESULT_CANCELED, null);
			} else if (requestCode == 0)// 剪裁图片之后
			{
				obj = data.getParcelableExtra("data");
				Bitmap bm = (Bitmap) obj;
				File file = new File(getCacheDir().getAbsolutePath() + "/"
						+ System.currentTimeMillis() + ".jpg");
				UIUtils.saveBitmapToFile(bm, file.getAbsolutePath());
				uri = Uri.fromFile(file);
				dispathResult(RESULT_OK, file.getAbsolutePath());
			} else if (requestCode == 2)// 照片选择之后
			{
				obj = data == null ? null : data.getData();// 图片地址
				if (obj != null) {
					if (W == 0 || H == 0) {
						dispathResult(RESULT_OK, Utils.getPath(PhotoSelActivity.this, (Uri) obj));
						return;
					}
					UIUtils.startPhotoZoom(PhotoSelActivity.this, (Uri) obj,W, H);// 剪裁图片
				}
			}
			else if(requestCode==3){
				dispathResult(RESULT_OK, data.getStringArrayListExtra("imgs"));
			}
			else if (requestCode == 1) {
				if (uri != null && new File(uri.getEncodedPath()).exists()) {
					if (W == 0 || H == 0) {
						dispathResult(RESULT_OK, Utils.getPath(PhotoSelActivity.this, uri));
						return;
					}
					UIUtils.startPhotoZoom(PhotoSelActivity.this, uri, W, H);// 剪裁图片
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
