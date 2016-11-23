package com.example.wsr.myapplication.bean.exp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.wsr.myapplication.R;
import com.sunrun.sunrunframwork.common.ExpAdapter;
import com.sunrun.sunrunframwork.view.ToastFactory;
import com.sunrun.sunrunframwork.weight.MMGridView;
import com.sunrun.sunrunframwork.weight.wq.utils.CommonAdapter;
import com.sunrun.sunrunframwork.weight.wq.utils.ViewHolder;

import java.util.List;


public class ExpTestAdapter extends ExpAdapter<ExpGroupListEntity> {

    public ExpTestAdapter(Activity act) {
        super(act);
    }

    @Override
    public int getGroupCount() {
        return mListData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListData.get(groupPosition).getChildEntityList().get(childPosition);
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        GroupViewHoder groupViewHoder = null;
        if (view == null) {
            view = LayoutInflater.from(mAct).inflate(R.layout.iitem_exp_group, null);
            groupViewHoder = new GroupViewHoder();
            groupViewHoder.tvGroutTitle = (TextView) view.findViewById(R.id.tv_group_title);
            groupViewHoder.tvIcon = (TextView) view.findViewById(R.id.tv_group_icon);
            view.setTag(groupViewHoder);
        } else {
            groupViewHoder = (GroupViewHoder) view.getTag();
        }
        groupViewHoder.tvGroutTitle.setText(mListData.get(groupPosition).getGroupTitle());
        //控制右边的图标上下 开关  //这里我用的文字  也可以换成图片
        if (isExpanded) {
            groupViewHoder.tvIcon.setText("↓");
        } else {
            groupViewHoder.tvIcon.setText("↑");
        }
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ClildViewHoder clildViewHoder = null;
        if (view == null) {
            view = LayoutInflater.from(mAct).inflate(R.layout.iitem_exp_child, null);
            clildViewHoder = new ClildViewHoder();
            clildViewHoder.gridView = (MMGridView) view.findViewById(R.id.gv_child_view);
            view.setTag(clildViewHoder);
        } else {
            clildViewHoder = (ClildViewHoder) view.getTag();
        }
        clildViewHoder.gridView.setAdapter(new ChildGridAdapter(mAct, mListData.get(groupPosition).getChildEntityList()));
        clildViewHoder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ToastFactory.getToast(mAct, mListData.get(groupPosition).getChildEntityList().get(position).getChildTitle()).show();

            }
        });

        return view;
    }




    class GroupViewHoder {
        TextView tvGroutTitle;
        TextView tvIcon;
    }

    class ClildViewHoder {
        MMGridView gridView;
    }
    class ChildGridAdapter extends CommonAdapter<ExpGroupListEntity.childEntity> {
        public ChildGridAdapter(Context context, List mDatas) {
            super(context, mDatas, R.layout.iitem_exp_child_view);
        }

        @Override
        public void convert(ViewHolder helper, ExpGroupListEntity.childEntity item) {
            TextView tvTitle = helper.getView(R.id.tv_child_title);
            tvTitle.setText(item.getChildTitle());
        }
    }
}
