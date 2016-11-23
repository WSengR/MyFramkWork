package com.example.wsr.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.wsr.myapplication.R;
import com.example.wsr.myapplication.bean.RedAdapter;
import com.example.wsr.myapplication.bean.RedBean;
import com.example.wsr.myapplication.quest.BaseQuestStart;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.utils.Logger;
import com.sunrun.sunrunframwork.uibase.PagingActivity;
import com.sunrun.sunrunframwork.utils.shareutils.OtherLoginUtil;
import com.sunrun.sunrunframwork.utils.shareutils.SharedUtil;
import com.sunrun.sunrunframwork.view.title.BaseTitleLayoutView;
import com.sunrun.sunrunframwork.weight.pulltorefresh.PullToRefreshBase;
import com.sunrun.sunrunframwork.weight.pulltorefresh.PullToRefreshListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.PlatformDb;

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
        prlvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        SharedUtil.showSharenosrs(TestPageActivity.this, null, null, "");
                        Logger.E("SharedUtil");
                        break;
                    case 2:
                        OtherLoginUtil.loginQQ(TestPageActivity.this, new OtherLoginUtil.OnOtherLoginCallback() {
                            @Override
                            public void loginCallback(PlatformDb platDB) {
                                if (platDB != null) {
                                    Log.e("wsr", "loginCallback    PlatformDb" + platDB.toString());
                                }
                                Log.e("wsr", "loginCallback    PlatformDb = null");

                            }
                        });
                        break;
                    case 3:
                        OtherLoginUtil.loginWeChat(TestPageActivity.this, new OtherLoginUtil.OnOtherLoginCallback() {
                            @Override
                            public void loginCallback(PlatformDb platDB) {
                                if (platDB != null) {
                                    Log.e("wsr", "loginCallback    PlatformDb" + platDB.toString());
                                }
                                Log.e("wsr", "loginCallback    PlatformDb = null");

                            }
                        });
                        break;
                    case 4:
                        OtherLoginUtil.loginSinaWeibo(TestPageActivity.this, new OtherLoginUtil.OnOtherLoginCallback() {
                            @Override
                            public void loginCallback(PlatformDb platDB) {
                                if (platDB != null) {
                                    Log.e("wsr", "loginCallback    PlatformDb" + platDB.toString());
                                }
                                Log.e("wsr", "loginCallback    PlatformDb = null");

                            }
                        });
                        break;
                    case 5:
                        OtherLoginUtil.loginSinaWeibo(TestPageActivity.this, new OtherLoginUtil.OnOtherLoginCallback() {
                            @Override
                            public void loginCallback(PlatformDb platDB) {
                                if (platDB != null) {
                                    Log.e("wsr", "loginCallback    PlatformDb" + platDB.toString());
                                }
                                Log.e("wsr", "loginCallback    PlatformDb = null");

                            }
                        });
                        break;
                }
            }
        });

        titleLayoutView.getTvTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtherLoginUtil.loginQQ(TestPageActivity.this, new OtherLoginUtil.OnOtherLoginCallback() {
                    @Override
                    public void loginCallback(PlatformDb platDB) {
                        if (platDB != null) {
                            Log.e("wsr", "loginCallback    PlatformDb" + platDB.toString());
                        }
                        Log.e("wsr", "loginCallback    PlatformDb = null");

                    }
                });
            }
        });

    }

//    public void updataView(int posi, ListView listView, Boolean isCheck) {
//        int visibleFirstPosi = listView.getFirstVisiblePosition();
//        int visibleLastPosi = listView.getLastVisiblePosition();
//        if (posi >= visibleFirstPosi && posi <= visibleLastPosi) {
//            View view = listView.getChildAt(posi - visibleFirstPosi);
////            LocalVideoAdapter.VideoViewHolder videoViewHolder = (LocalVideoAdapter.VideoViewHolder) view.getTag();
////            videoViewHolder.checkBox.setChecked(isCheck);
//        }
//    }

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
