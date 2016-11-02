package com.sunrun.sunrunframwork.uibase;

import java.util.List;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.sunrun.sunrunframwork.utils.PagingHelp;
import com.sunrun.sunrunframwork.utils.Pagingable;
import com.sunrun.sunrunframwork.weight.pulltorefresh.PullToRefreshBase;


/**
 * @可刷新与分页的Activity基类
 * @param <T>
 *            数据源类型
 */
@SuppressWarnings(
{ "unchecked", "rawtypes" })
public abstract class PagingActivity<T> extends BaseActivity implements Pagingable<T> {
	protected List<T> mData = null;
	protected BaseAdapter mAdapter;
	PullToRefreshBase pullListView;
	PagingHelp pagingHelp=new PagingHelp<>(this);
	public int getPageSize() {
		return pagingHelp.getPageSize();
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
	}

	public void setPageSize(int pageSize) {
		pagingHelp.setPageSize(pageSize);
	}

	public int getCurPage() {
		return pagingHelp.getCurPage();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadCurrentPage();
	}

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


	@Override
	protected void initView() {
		
	}

	/**
	 * 加载数据,子类实现,并需要调用 setDataToView方法来将数据填充到视图
	 * 
	 * @param curPage
	 *            当前页数
	 */
	public abstract void loadData(int curPage);

	/**
	 * 获取适配器
	 * 
	 * @param mData
	 * @return
	 */
	public abstract BaseAdapter getAdapter(List<T> mData);


	/**
	 * 处理标题双击事件
	 */
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

	public boolean canLoad(List<T> data) {
		return pagingHelp.canLoad(data);
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

	public void nextPage() {
		pagingHelp.nextPage();
	}

	public void reshPage() {
		pagingHelp.reshPage();
	}

	public synchronized BaseAdapter setDataToView(List<T> mData, AdapterView listView) {
		mAdapter = pagingHelp.setDataToView(mData, listView);
		this.mData=pagingHelp.getmData();
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
