package com.example.wsr.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wsr.myapplication.activity.MyActivity;
import com.example.wsr.myapplication.activity.TestExpendListView;
import com.example.wsr.myapplication.activity.TestGifActivity;
import com.example.wsr.myapplication.activity.TestPageActivity;
import com.example.wsr.myapplication.bean.StartActivityBean;
import com.example.wsr.myapplication.utils.StartIntent;
import com.sunrun.sunrunframwork.common.CommonAdapter;
import com.sunrun.sunrunframwork.common.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/14
 * @功能描述:
 */

public class MainListActivity extends Activity {
    @Bind(R.id.lv_all_list_view)
    ListView lvAllListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        ButterKnife.bind(this);

        final ArrayList<StartActivityBean> startActivityBeans = new ArrayList<>();
        startActivityBeans.add(new StartActivityBean("GIFAvtivity", "显示GIF动画", TestGifActivity.class));
        startActivityBeans.add(new StartActivityBean("MainActivcity", "测试主页Activity", MainActivity.class));
        startActivityBeans.add(new StartActivityBean("MyActivity", "测试MyActivity", MyActivity.class));
        startActivityBeans.add(new StartActivityBean("TestPageActivity", "测试TestPageActivity", TestPageActivity.class));
        startActivityBeans.add(new StartActivityBean("TestExpendListView", "测试TestExpendListView", TestExpendListView.class));





        lvAllListView.setAdapter(new ListActivityAdapter(this, startActivityBeans));
        lvAllListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StartIntent.startActivity(MainListActivity.this, startActivityBeans.get(position).getStartAtivity());
            }
        });
    }


    class ListActivityAdapter extends CommonAdapter<StartActivityBean> {
        /**
         * @param mAct  上下文
         * @param datas 数据集
         */
        public ListActivityAdapter(Activity mAct, List<StartActivityBean> datas) {
            super(mAct, datas, R.layout.item_all_activity);
        }

        @Override
        public void convert(ViewHolder holder, StartActivityBean startActivityBean, int position) {
            TextView tvName = holder.getView(R.id.tv_activity_name);
            tvName.setText(startActivityBean.getActivityName());
            TextView tvDis = holder.getView(R.id.tv_dis);
            tvDis.setText(startActivityBean.getAicitivyDis());
        }
    }
}

