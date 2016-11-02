package com.example.wsr.myapplication.bean;

import android.app.Activity;
import android.widget.TextView;

import com.example.wsr.myapplication.MainActivity;
import com.example.wsr.myapplication.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sunrun.sunrunframwork.common.CommonAdapter;
import com.sunrun.sunrunframwork.common.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cnsunrun on 2016/10/18.
 */
public class ButtomAdapter extends CommonAdapter<RedBean.InfoBean.TalentResultBean> {
    /**
     * @param mAct  上下文
     * @param datas 数据集
     */
    public ButtomAdapter(Activity mAct, List<RedBean.InfoBean.TalentResultBean> datas) {
        super(mAct, datas, R.layout.gv_item_image);
    }

    @Override
    public void convert(ViewHolder holder, RedBean.InfoBean.TalentResultBean talentResultBean, int position) {
        CircleImageView circleImageView = holder.getView(R.id.profile_image);
//        circleImageView.setImageResource(R.mipmap.image);
        ImageLoader.getInstance().displayImage(talentResultBean.getIcon(), circleImageView, MainActivity.options);

        TextView tvTitle = holder.getView(R.id.tv_title);
        tvTitle.setText(talentResultBean.getNickname());

        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(talentResultBean.getTalent_txt() == null ? "" : talentResultBean.getTalent_txt().toString());
    }
}
