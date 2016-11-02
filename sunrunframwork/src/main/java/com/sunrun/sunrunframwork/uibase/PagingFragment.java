package com.sunrun.sunrunframwork.uibase;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ScrollView;


import com.sunrun.sunrunframwork.utils.PagingHelp;
import com.sunrun.sunrunframwork.utils.Pagingable;
import com.sunrun.sunrunframwork.weight.pulltorefresh.PullToRefreshBase;


/**
 * @可刷新与分页的Fragment基类
 * @param <T>
 *            数据源类型
 */
@SuppressWarnings(
{ "unchecked", "rawtypes" })
public abstract class PagingFragment<T> extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, Pagingable<T> {
	protected List<T> mData = null;
	protected BaseAdapter mAdapter;
	protected PullToRefreshBase pullListView;
	protected PagingHelp pagingHelp=new PagingHelp<>(this);

	public int getPageSize() {
		return pagingHelp.getPageSize();
	}

	public void setPageSize(int pageSize) {
		pagingHelp.setPageSize(pageSize);
	}

	public int getCurPage() {
		return pagingHelp.getCurPage();
	}

	public boolean canLoad(List<T> data) {
		return pagingHelp.canLoad(data);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		if (!hidden && mData == null) {
			loadCurrentPage();
			// 异常状态下再次请求数据
		}
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
//		pagingHelp = new PagingHelp(this);
		super.onViewCreated(view, savedInstanceState);
		loadCurrentPage();

	}

//	@Override
//	public boolean doubleClickTitle() {
//		if (pullListView != null) {
//			View reshView = pullListView.getRefreshableView();
//			if (reshView instanceof ScrollView) {
//				((ScrollView) reshView).smoothScrollTo(0, 0);
//			} else if (reshView instanceof AbsListView) {
////				((AbsListView) reshView).smoothScrollToPosition(0);// 平滑滚动到顶部,item较复杂时位置不精确
//				 ((AdapterView) reshView).setSelection(0);
//			}
//		}
//		return super.doubleClickTitle();
//	}

	public abstract void loadData(int curPage);

	public abstract BaseAdapter getAdapter(List<T> mData);

	@Override
	public void requestFinish() {
		Loadfinish();
		super.requestFinish();
	}

	@Override
	public void requestCancel() {
		Loadfinish();
		super.requestCancel();
	}

	public void Loadfinish() {
		pagingHelp.Loadfinish();
	};

	public boolean isFirst() {
		return pagingHelp.isFirst();
	}

	/**
	 * 设置下拉监听
	 * 
	 * @param pullListView
	 */

	public void setPullListener(PullToRefreshBase pullListView) {
		this.pullListView = pullListView;
		pagingHelp.setPullListener(pullListView);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase refreshView) {
		reshPage();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase refreshView) {
		nextPage();
	}

	public void nextPage() {
		pagingHelp.nextPage();
	}

	public void reshPage() {
		pagingHelp.reshPage();
	}

	public synchronized BaseAdapter setDataToView(List<T> mData, AdapterView listView) {
		mAdapter = pagingHelp.setDataToView(mData, listView);
		this.mData = pagingHelp.getmData();
		return mAdapter;
	}

	@Override
	public void loadCurrentPage() {
		pagingHelp.loadCurrentPage();
	}
	@Override
	public void loadCurrentPage(int page) {
		pagingHelp.loadCurrentPage(page);
	}
}
