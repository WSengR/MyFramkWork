package com.sunrun.sunrunframwork.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrun.sunrunframwork.R;
import com.sunrun.sunrunframwork.http.utils.Logger;


/**
 * 下拉列表popwindow的适配器
 */
public class Pop_Adapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> list;
	LayoutInflater inflater;
	private int clickTemp = 0; // 标识选择的Item

	public int getClickTemp() {
		return clickTemp;
	}

	public T getSelectItem() {
		if (clickTemp == -1)
			return null;
		return list.get(clickTemp);
	}

	public Pop_Adapter(Context context, List<T> list) {
		this.mContext = context;
		this.list = list;
		this.inflater = LayoutInflater.from(mContext);

	}

	public void setSeclection(int position) {
		clickTemp = position;
		notifyDataSetChanged();
	}

	/**
	 * 刷新
	 * 
	 * @param list
	 */
	public void isRefresh(List<T> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public T getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_pop, null);
		}
		holder.paixu_tv = (TextView) convertView.findViewById(R.id.paixu_tv);
		holder.paixu_img = (ImageView) convertView.findViewById(R.id.paixu_img);
		holder.limiteLine = convertView.findViewById(R.id.limiteLine);
		holder.paixu_tv.setText(list.get(position).toString());
		if (position == getCount() - 1) {
			holder.limiteLine.setVisibility(View.GONE);
		} else {
			holder.limiteLine.setVisibility(View.VISIBLE);
		}
		if (clickTemp == position) {
			holder.paixu_tv.setTextColor(mContext.getResources().getColor(R.color.main));
			holder.paixu_img.setVisibility(View.VISIBLE);
		} else {

			holder.paixu_tv.setTextColor(Color.BLACK);
			holder.paixu_img.setVisibility(View.GONE);
		}
		Logger.E("" + position + "  " + clickTemp);

		return convertView;
	}

	class ViewHolder {
		TextView paixu_tv;
		ImageView paixu_img;
		View limiteLine;

	}

}
