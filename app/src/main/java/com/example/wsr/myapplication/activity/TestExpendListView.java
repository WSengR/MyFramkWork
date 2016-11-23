package com.example.wsr.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.wsr.myapplication.R;
import com.example.wsr.myapplication.bean.exp.ExpGroupListEntity;
import com.example.wsr.myapplication.bean.exp.ExpTestAdapter;
import com.example.wsr.myapplication.view.CustomLoadingView;
import com.sunrun.sunrunframwork.uibase.BaseActivity;
import com.sunrun.sunrunframwork.view.ToastFactory;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/9
 * @功能描述:
 */

public class TestExpendListView extends BaseActivity {

    ExpandableListView expandableListView;
    ExpTestAdapter expTestAdapter;
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3)
    Button btn3;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_test_exp_view);
        expandableListView = (ExpandableListView) findViewById(R.id.elv_test_view);
        expTestAdapter = new ExpTestAdapter(this);

        ArrayList<ExpGroupListEntity> arrayListDatas = new ArrayList<ExpGroupListEntity>();
        for (int i = 0; i < 6; i++) {
            ExpGroupListEntity groupDatas = new ExpGroupListEntity();
            ArrayList<ExpGroupListEntity.childEntity> childListDatas = new ArrayList<ExpGroupListEntity.childEntity>();
            for (int j = 0; j < 8; j++) {
                ExpGroupListEntity.childEntity childData = new ExpGroupListEntity.childEntity();
                childData.setChildTitle("childTitle " + j);
                childListDatas.add(childData);
            }
            groupDatas.setGroupTitle("GroupTitle " + i);
            groupDatas.setChildEntityList(childListDatas);
            arrayListDatas.add(groupDatas);
        }

        expTestAdapter.setListData(arrayListDatas);
        expandableListView.setAdapter(expTestAdapter);

        //expandablelistview的Group点击事件，onGroupClick的返回值false展开，true不展开
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                ToastFactory.getToast(TestExpendListView.this, expTestAdapter.getmListData().get(groupPosition).getGroupTitle()).show();
                return true;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //点击展开的  关闭其他展开的
//                for (int i = 0; i < expTestAdapter.getGroupCount(); i++) {
//                    if (groupPosition != i) {
//                        expandableListView.collapseGroup(i); //关闭
//                    }
//                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                ExpTestAdapter.openAllChild(expandableListView);
                ((CustomLoadingView)findViewById(R.id.loadingView)).stop();
                break;
            case R.id.btn2:
                ExpTestAdapter.closeAllChild(expandableListView);
                ((CustomLoadingView)findViewById(R.id.loadingView)).start();
                break;
            case R.id.btn3:
                ExpTestAdapter.openPositionAndCloseOtherChild(expandableListView, 4);
                break;
        }
    }
}
