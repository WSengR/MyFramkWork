package com.sunrun.sunrunframwork.adapter;

import java.util.List;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author WQ 可选择的适配器
 * @param <T>数据源
 * @选择模式切换后需要手动刷新适配器
 */
public abstract class SelectableAdapter<T> extends ComplexEventAdapter<T> {
	public final static int RADIO = 0x2100;
	public final static int MULTISELECT = 0x2101;
	protected int selectMode = RADIO;
	protected SparseBooleanArray selectList = null;
	protected int selectIndex = -1;
	protected int selectedNum = 0;

	public SelectableAdapter(Context context, int layoutId) {
		super(context, layoutId);
		// TODO Auto-generated constructor stub
		selectMode(RADIO);
	}

	public int getSelectPosition() {
		return selectIndex;
	}

	public int getSelectCount() {
		return selectedNum;
	}

	public T getSelectItem() {
		return getItem(selectIndex);
	}

	public SelectableAdapter(Context context, List<T> data, int res) {
		super(context, data, res);
		// TODO Auto-generated constructor stub
		selectMode(RADIO);
	}

	public SelectableAdapter(Context context, List<T> data,
			AdapterView<?> adapterView, int res) {
		super(context, data, res);
		// TODO Auto-generated constructor stub
		selectMode(RADIO);
		adapterView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				select(position);
			}
		});
	}

	public void select(int position) {
		if (selectMode == RADIO) {
			selectIndex = position;
			selectedNum = 1;
		} else {
			boolean isSelected = !isSelected(position);
			selectedNum = isSelected ? selectedNum + 1 : selectedNum - 1;
			selectList.put(itemId(position), isSelected);
		}
		notifyDataSetChanged();
	}

	public boolean isSelected(int position) {
		return selectMode == RADIO ? selectIndex == position : selectList
				.get(itemId(position));
	}

	public void selectAll(boolean isSelect) {
		// TODO Auto-generated method stub
		if (selectMode == MULTISELECT) {
			for (int i = 0; i < mData.size(); i++)
				selectList.put(itemId(i), isSelect);
			selectedNum = getCount();
		} else if (!isSelect) {
			selectIndex = -1;
			selectedNum = 0;
		}
		notifyDataSetChanged();
	}

	/**
	 * 选择模式
	 */

	public void selectMode(int mode) {
		if (mode == RADIO || mode == MULTISELECT) {
			this.selectMode = mode;
			selectedNum = 0;
			if (selectMode == MULTISELECT)
				selectList = new SparseBooleanArray(mData.size());
		} else ;

	}

	/**
	 * 获得数据源hashCode
	 * 
	 * @param position
	 * @return
	 */
	private int itemId(int position) {
		if (mData == null || position >= mData.size()
				|| mData.get(position) == null)
			return -1;
		return mData.get(position).hashCode();
	}
}
