package com.example.wsr.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wsr.myapplication.R;
import com.example.wsr.myapplication.quest.BaseQuestStart;
import com.example.wsr.myapplication.utils.StartIntent;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.uibase.BaseActivity;
import com.sunrun.sunrunframwork.uibase.PhotoSelActivity;
import com.sunrun.sunrunframwork.view.ToastFactory;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者: Wang'sr
 * @时间: 2016/10/31
 * @功能描述:
 */

public class MyActivity extends BaseActivity {

    @Bind(R.id.tv_show)
    TextView tvShow;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;

    @Override
    protected void initView() {
        setContentView(R.layout.ui_mylayout);
        ButterKnife.bind(this);
    }


    @Override
    public void nofityUpdate(int requestCode, BaseBean bean) {
        super.nofityUpdate(requestCode, bean);
        Log.e("wsr", "requestCode " + requestCode);
        switch (requestCode) {
            case BaseQuestStart.QUEST_BANNER_CODE:
                if (bean.status == 1) {
                    ToastFactory.getToast(this, bean.data.toString()).show();
                }
                break;

            default:

        }


    }


    public void upimg() {
        Intent intent = new Intent(that, PhotoSelActivity.class);
        intent.putExtra("W", 200);
        intent.putExtra("H", 200);
        startActivityForResult(intent, 1024);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("wsr", "requestCode" + requestCode + "   " );
        if (requestCode == 1024 && resultCode == Activity.RESULT_OK) {
            File file = new File(data.getStringExtra(PhotoSelActivity.RESULT));
            ivIcon.setImageBitmap(BitmapFactory.decodeFile(file
                    .getAbsolutePath()));

        }
    }



    @OnClick({R.id.tv_show, R.id.iv_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_show:
                BaseQuestStart.bannerQuest(this);
                upimg();
                break;
            case R.id.iv_icon:
                StartIntent.startTestPageActivity(this);
                break;
        }
    }
}
