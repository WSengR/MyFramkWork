package com.sunrun.sunrunframwork.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.sunrun.sunrunframwork.R;

/**
 * 图片加载选项工具类(自行根据需要添加更多)
 * 
 * @author cnsunrun
 * 
 */
public class ImageLoadOptions {
	/**
	 * 获取配置(不带缓存)
	 * 
	 * @return
	 */
	public static DisplayImageOptions getNoCacheOption() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(false)
				.bitmapConfig(Bitmap.Config.RGB_565).cacheOnDisc(false).build();
		return options;
	}
	/**
	 * 获取配置(不带缓存)
	 * 
	 * @return
	 */
	public static DisplayImageOptions getNoCacheCirleOption() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(false)
		.displayer(new RoundedBitmapDisplayer(45))
		.bitmapConfig(Bitmap.Config.RGB_565).cacheOnDisc(false).build();
		return options;
	}

	/**
	 * 获取默认的选项配置
	 * 
	 * @return
	 */
	public static DisplayImageOptions getDefaultOption() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading(false)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				.cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
				.cacheOnDisc(true).build();
		return options;
	}

	/**
	 * 获取默认的选项配置 ImageScaleType.EXACTLY
	 * 
	 * @return
	 */
	
	public static DisplayImageOptions getHeadImgOption(int idEmptyDrawable,int idDefDrawable) {
		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.resetViewBeforeLoading(true)
		.showImageForEmptyUri(idEmptyDrawable)
		.showImageOnLoading(idDefDrawable)
		.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(true)
		.bitmapConfig(Bitmap.Config.RGB_565).cacheOnDisc(true).build();
		return options;
	}
	public static DisplayImageOptions getCusOption(int resDraw) {
		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.resetViewBeforeLoading(true)
		.showImageForEmptyUri(resDraw)
		.showImageOnLoading(resDraw)
		.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(true)
		.bitmapConfig(Bitmap.Config.RGB_565).cacheOnDisc(true).build();
		return options;
	}
	

	/**
	 * 获取默认的选项配置 ImageScaleType.EXACTLY
	 * 
	 * @return
	 */
	public static DisplayImageOptions getExactlyOption() {

		DisplayImageOptions options = new DisplayImageOptions.Builder()
//				.resetViewBeforeLoading(true)
				.showImageOnLoading(R.drawable.new_nopic)
//				.displayer(new FadeInBitmapDisplayer(200))
				.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(true)
				.bitmapConfig(Bitmap.Config.RGB_565).cacheOnDisc(true).build();
		return options;
	}

	/**
	 * 获取默认的选项配置 ImageScaleType.EXACTLY
	 * 
	 * @return
	 */
	public static DisplayImageOptions getExactlyOption_Y() {

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading(true)
				.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new RoundedBitmapDisplayer(90)).cacheOnDisc(true)
				.build();
		return options;
	}

	/**
	 * ImageLoader默认配置
	 */
	public static ImageLoaderConfiguration getImageLoaderConfiguration(
			android.content.Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.memoryCache(new WeakMemoryCache())
				.memoryCacheSize((int) (2 * 1024 * 1024))
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.build();
		return config;
	}
}
