package com.example.wsr.myapplication.bean;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wsr.myapplication.R;
import com.sunrun.sunrunframwork.common.CommonAdapter;
import com.sunrun.sunrunframwork.common.ViewHolder;

import java.util.List;

/**
 * Created by cnsunrun on 2016/10/18.
 */
public class RedAdapter extends CommonAdapter<RedBean.InfoBean.LableResultBean> {
    /**
     * @param mAct     上下文
     * @param datas    数据集
     */
    public RedAdapter(Activity mAct, List<RedBean.InfoBean.LableResultBean> datas) {
        super(mAct, datas, R.layout.center_item_grid);
    }

    @Override
    public void convert(ViewHolder holder, RedBean.InfoBean.LableResultBean redBean, int position) {
        TextView tvName = holder.getView(R.id.tv_title);
        tvName.setText(redBean.getName());
        TextView tvNum = holder.getView(R.id.tv_num);
        tvNum.setText(redBean.getActivity()+"参加");
        ImageView ivImage= holder.getView(R.id.iv_image);

        int count = position%3;
        switch (count){
            case 0:
                tvName.setTextColor(mAct.getResources().getColor(R.color.color_tv_biaoqian_e));
                ivImage.setImageResource(R.mipmap.biaoqian3);
                break;
            case 1:
                tvName.setTextColor(mAct.getResources().getColor(R.color.color_tv_biaoqian_lan));
                ivImage.setImageResource(R.mipmap.biaoqian1);
                break;
            default:
                tvName.setTextColor(mAct.getResources().getColor(R.color.color_tv_biaoqian_lv));
                ivImage.setImageResource(R.mipmap.biaoqian2);
                break;
        }


    }
}
