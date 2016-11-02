package com.sunrun.sunrunframwork.utils;

import java.util.List;

import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.sunrun.sunrunframwork.weight.pulltorefresh.PullToRefreshBase;

/**
 * 分页操作接口,建立分页页面到具体实现间的桥梁
 * @author WQ 上午10:51:26
 * @param <T>
 */
public interface Pagingable<T>{
		/**
		 * 是否首次加载
		 * @return
		 */
		public boolean isFirst();
		/**
		 * 设置下拉上拉监听
		 * @param pull
		 */
		public void setPullListener(PullToRefreshBase pull);
		/**
		 * 下一页
		 */
		public void nextPage();
		/**
		 * 刷新页面,第一页刷新,会清空后续数据
		 */
		public void reshPage();
		/**
		 * 加载指定页数的数据
		 * @param page
		 */
		public void loadData(int page);
		/**
		 * 加载当前页数的数据
		 */
		public void loadCurrentPage(int page);
		public void loadCurrentPage();
		/**
		 * 设置数据到列表中,开发者请求完成后手动调用
		 * @param mData
		 * @param listView
		 * @return
		 */
		public BaseAdapter  setDataToView(List<T> mData, AdapterView listView);
		/**
		 * 是否可以加载更多
		 * @param mData
		 * @return
		 */
		public boolean canLoad(List<T> mData);
		/**
		 * 获取适配器
		 * @param mData
		 * @return
		 */
		public abstract BaseAdapter getAdapter(List<T> mData);
		/**
		 * 加载完成
		 */
		public void Loadfinish();
}