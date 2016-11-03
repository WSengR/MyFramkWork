package com.example.wsr.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;

import com.example.wsr.myapplication.R;
import com.example.wsr.myapplication.bean.RedAdapter;
import com.example.wsr.myapplication.bean.RedBean;
import com.example.wsr.myapplication.quest.BaseQuestStart;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uibase.PagingActivity;
import com.sunrun.sunrunframwork.view.title.BaseTitleLayoutView;
import com.sunrun.sunrunframwork.weight.pulltorefresh.PullToRefreshBase;
import com.sunrun.sunrunframwork.weight.pulltorefresh.PullToRefreshListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/1
 * @功能描述:下拉刷新Activity
 */

public class TestPageActivity extends PagingActivity {

    @Bind(R.id.prlv_test)
    PullToRefreshListView prlvTest;
    int curPage = 0;
    @Bind(R.id.title_layout_view)
    BaseTitleLayoutView titleLayoutView;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.ui_test_pageactivity);
        ButterKnife.bind(this);
        prlvTest.setMode(PullToRefreshBase.Mode.BOTH);
        setPullListener(prlvTest);
    }

    @Override
    public void loadData(int curPage) {
        this.curPage = curPage;
        BaseQuestStart.bannerQuest(this);
    }

    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        super.nofityUpdate(requestCode, bean);
        //必须设置这个 内有ListView与Adapter的方法
        setDataToView(RedBean.getData().getInfo().getLable_result(), prlvTest.getRefreshableView());
        titleLayoutView.setTitleText("CuurPage" + curPage);
        Log.e("wsr", "curPage = " + curPage);
    }

    @Override
    public BaseAdapter getAdapter(List mData) {
        RedAdapter redAdapter = new RedAdapter(TestPageActivity.this, RedBean.getData().getInfo().getLable_result());
        return redAdapter;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
