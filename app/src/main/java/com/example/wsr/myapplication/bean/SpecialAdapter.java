package com.example.wsr.myapplication.bean;

import android.app.Activity;
import android.widget.TextView;

import com.example.wsr.myapplication.R;
import com.sunrun.sunrunframwork.common.CommonAdapter;
import com.sunrun.sunrunframwork.common.ViewHolder;

import java.util.List;

/**
 * Created by cnsunrun on 2016/10/18.
 */
public class SpecialAdapter extends CommonAdapter<SpecialBean> {
    /**
     * @param mAct     上下文
     * @param datas    数据集
     */
    public SpecialAdapter(Activity mAct, List<SpecialBean> datas) {
        super(mAct, datas, R.layout.center_item_grid);
    }

    @Override
    public void convert(ViewHolder holder, SpecialBean specialBean, int position) {
        TextView tvTitle= holder.getView(R.id.tv_title);
        tvTitle.setText(specialBean.getInfo().get(position).getTitle());

        TextView textView = holder.getView(R.id.tv_num);
        textView.setText(specialBean.getInfo().get(position).getTitle());
    }
}
