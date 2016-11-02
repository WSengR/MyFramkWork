package com.sunrun.sunrunframwork.weight.wq.imageloader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sunrun.sunrunframwork.R;
import com.sunrun.sunrunframwork.http.utils.Logger;
import com.sunrun.sunrunframwork.weight.wq.bean.ImageFloder;


/**
 * 多图选择界面
 * 
 * @author WQ 下午3:07:00
 */
public class MulitImageAct extends Activity implements ListImageDirPopupWindow.OnImageDirSelected {
	private ProgressDialog mProgressDialog;

	/**
	 * 存储文件夹中的图片数量
	 */
	private int mPicsSize;
	/**
	 * 图片数量最多的文件夹
	 */
	private File mImgDir;
	/**
	 * 所有的图片
	 */
	private List<String> mImgs;

	private GridView mGirdView;
	private MyAdapter mAdapter;
	/**
	 * 临时的辅助类，用于防止同一个文件夹的多次扫描
	 */
	private HashSet<String> mDirPaths = new HashSet<String>();

	/**
	 * 扫描拿到所有的图片文件夹
	 */
	private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

	private RelativeLayout mBottomLy;

	private TextView mChooseDir;
	private TextView mImageCount;
	int totalCount = 0;

	private int mScreenHeight;
	// int max,selNum=0;
	private ListImageDirPopupWindow mListImageDirPopupWindow;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mProgressDialog.dismiss();
			// 为View绑定数据
			data2View();
			// 初始化展示文件夹的popupWindw
			initListDirPopupWindw();
		}
	};

	/**
	 * 为View绑定数据
	 */
	private void data2View() {
		if (mImgDir == null) {
			Toast.makeText(getApplicationContext(), "一张图片没扫描到",
					Toast.LENGTH_SHORT).show();
			return;
		}

		mImgs = listImages(mImgDir);
		/**
		 * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
		 */
		mAdapter = new MyAdapter(this, mImgs, R.layout.grid_item,
				mImgDir.getAbsolutePath());
		mGirdView.setAdapter(mAdapter);
		// mImageCount.setText(totalCount + "张");
		updateSelectNum(MyAdapter.mSelectedImage.size() + MyAdapter.selNum);
	};

	/**
	 * 初始化展示文件夹的popupWindw
	 */
	private void initListDirPopupWindw() {
		mListImageDirPopupWindow = new ListImageDirPopupWindow(
				LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
				mImageFloders, LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.list_dir, null));

		mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// 设置背景颜色变暗
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
			}
		});
		// 设置选择文件夹的回调
		mListImageDirPopupWindow.setOnImageDirSelected(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_mulit_pic);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight = outMetrics.heightPixels;

		initView();
		getImages();
		initEvent();

	}

	/**
	 * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
	 */
	private void getImages() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
			return;
		}
		// 显示进度条
		mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

		new Thread(new Runnable() {
			@Override
			public void run() {

				String firstImage = null;

				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = MulitImageAct.this
						.getContentResolver();

				// 只查询jpeg和png的图片
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);

				Log.e("TAG", mCursor.getCount() + "");
				while (mCursor.moveToNext()) {
					// 获取图片的路径
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));

					Log.e("TAG", path);
					// 拿到第一张图片的路径
					if (firstImage == null)
						firstImage = path;
					// 获取该图片的父路径名
					File parentFile = new File(path).getParentFile();
					if (parentFile == null)
						continue;
					String dirPath = parentFile.getAbsolutePath();
					ImageFloder imageFloder = null;
					// 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
					if (mDirPaths.contains(dirPath)) {
						continue;
					} else {
						mDirPaths.add(dirPath);
						// 初始化imageFloder
						imageFloder = new ImageFloder();
						imageFloder.setDir(dirPath);
						List<String>imgs_tmp=listImages(parentFile);
						if(imgs_tmp.size()==0)continue;
						imageFloder.setFirstImagePath(imgs_tmp.size()!=0?(new File(parentFile,imgs_tmp.get(0)).getAbsolutePath()):path);
					}
					String fs[] = parentFile.list(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String filename) {
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg")){
								return new File(dir,filename).length()!=0&&true;
							}
							return false;
						}
					});
					int picSize = fs == null ? 0 : fs.length;
					totalCount += picSize;

					imageFloder.setCount(picSize);
					if(mImageFloders.size()==0){
					mImageFloders.add(imageFloder);
					}else {
						mImageFloders.add(0,imageFloder);
					}

					if (picSize > mPicsSize) {
						mPicsSize = picSize;
						mImgDir = parentFile;
					}
				}
				mCursor.close();

				// 扫描完成，辅助的HashSet也就可以释放内存了
				mDirPaths = null;

				// 通知Handler扫描图片完成
				mHandler.sendEmptyMessage(0x110);

			}
		}).start();

	}

	List<String> selectedImage;

	/**
	 * 初始化View
	 */
	private void initView() {
		MyAdapter.max = getIntent().getIntExtra("max", 9);// 最大张数
		MyAdapter.selNum = Math.abs(getIntent().getIntExtra("selNum", 0)
				- MyAdapter.mSelectedImage.size());// 已选额外图片
		selectedImage = getIntent().getStringArrayListExtra("imgs");// 已选图片
		mGirdView = (GridView) findViewById(R.id.id_gridView);
		mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
		mImageCount = (TextView) findViewById(R.id.id_total_count);
		mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);
		if (selectedImage != null) {
			// MyAdapter.mSelectedImage.clear();
			// MyAdapter.mSelectedImage.addAll(selectedImage);
		}
		// MyAdapter.max=max;//设置最大张数
		// MyAdapter.selNum=selNum-MyAdapter.mSelectedImage.size();//设置最大张数
		updateSelectNum(MyAdapter.selNum - MyAdapter.mSelectedImage.size());
		mImageCount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("imgs", MyAdapter.mSelectedImage);
				setResult(RESULT_OK, intent);
				finish();

			}
		});
	}

	@Override
	public void finish() {
		super.finish();
		MyAdapter.mSelectedImage.clear();
	}

	private void initEvent() {
		/**
		 * 为底部的布局设置点击事件，弹出popupWindow
		 */
		mBottomLy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mListImageDirPopupWindow
						.setAnimationStyle(R.style.anim_popup_dir);
				mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);

				// 设置背景颜色变暗
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = .3f;
				getWindow().setAttributes(lp);
			}
		});
	}
	
	public void updateSelectNum(int num) {
		mImageCount.setEnabled(num != 0);
		int color=getResources().getColor(num == 0?R.color.text3:android.R.color.white);
		mImageCount.setTextColor(color);
		mImageCount.setText(num == 0 ? "完成" : String.format("完成(%d/%d)",
				Math.abs(num), MyAdapter.max));
	}

	public List<String> listImages(File imgDir){
		ArrayList<String>imgs=new ArrayList<>();
		File fs[]=imgDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				if (filename.endsWith(".jpg") || filename.endsWith(".png")
						|| filename.endsWith(".jpeg")
						|| filename.endsWith(".gif")){
					return new File(dir,filename).length()!=0&&true;
				}
				return false;
			}
		});
		if(fs==null)return imgs;
		Arrays.sort(fs, new Comparator<File>() {
			@Override
			public int compare(File lhs, File rhs) {
				return -compareUtil(lhs.lastModified(), rhs.lastModified());
			}
			
			public  int compareUtil(long lhs, long rhs) {
		        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
		    }
		});
		imgs=new ArrayList<>();
		for (File file : fs) {
			Logger.E(file + "  " + file.exists());
			imgs.add(file.getName());
		}
		return imgs;
	}
	
	@Override
	public void selected(ImageFloder floder) {

		mImgDir = new File(floder.getDir());
		mImgs=listImages(mImgDir);
		/**
		 * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
		 */
		mAdapter = new MyAdapter(this, mImgs, R.layout.grid_item,
				mImgDir.getAbsolutePath());

		mGirdView.setAdapter(mAdapter);
		// mAdapter.notifyDataSetChanged();

		mChooseDir.setText(floder.getName());
		mListImageDirPopupWindow.dismiss();
		updateSelectNum(MyAdapter.mSelectedImage.size() + MyAdapter.selNum);
	}

}
