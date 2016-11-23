package com.example.wsr.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wsr.myapplication.R;
import com.example.wsr.myapplication.quest.BaseQuestStart;
import com.example.wsr.myapplication.utils.GradlePrassDialogUtil;
import com.example.wsr.myapplication.utils.StartIntent;
import com.example.wsr.myapplication.view.GradlePrassView;
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

public class MyActivity extends BaseActivity implements GradlePrassView.OnAnimationFinish, GradlePrassDialogUtil.OnBackGradleStub {

    @Bind(R.id.tv_show)
    TextView tvShow;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.iv_rotate_view)
    ImageView ivRotateView;
    @Bind(R.id.gpView1)
    GradlePrassView gpView1;
    @Bind(R.id.gpView2)
    GradlePrassView gpView2;
    @Bind(R.id.gpView3)
    GradlePrassView gpView3;
    @Bind(R.id.gpView4)
    GradlePrassView gpView4;
    @Bind(R.id.gpView5)
    GradlePrassView gpView5;

    GradlePrassDialogUtil gradlePrassDialogUtil;
    @Bind(R.id.btn_test1)
    Button btnTest1;
    @Bind(R.id.btn_test2)
    Button btnTest2;
    @Bind(R.id.btn_test3)
    Button btnTest3;
    @Bind(R.id.btn_test4)
    Button btnTest4;
    @Bind(R.id.btn_test5)
    Button btnTest5;

    @Override
    protected void initView() {

        setContentView(R.layout.ui_mylayout);
        ButterKnife.bind(this);

        gradlePrassDialogUtil = new GradlePrassDialogUtil(this);
//        ivRotateView.startAnimation(getRotateAnim());

        gpView1.setViewText("第一");
        gpView2.setViewText("第二");
        gpView3.setViewText("第仨");
        gpView4.setViewText("第四");
        gpView5.setViewText("第五个");
        gpView1.statrImgViewAnim(this, true, 2000);

        btnTest2.setSelected(true);
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


//    public Animation getRotateAnim() {
//        Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
//        LinearInterpolator lin = new LinearInterpolator();
//        rotateAnim.setInterpolator(lin);
//        return rotateAnim;
//    }

    public void upimg() {
        Intent intent = new Intent(that, PhotoSelActivity.class);
        intent.putExtra("W", 200);
        intent.putExtra("H", 200);
        startActivityForResult(intent, 1024);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("wsr", "requestCode" + requestCode + "   ");
        if (requestCode == 1024 && resultCode == Activity.RESULT_OK) {
            File file = new File(data.getStringExtra(PhotoSelActivity.RESULT));
            ivIcon.setImageBitmap(BitmapFactory.decodeFile(file
                    .getAbsolutePath()));

        }
    }


    @OnClick({R.id.tv_show, R.id.iv_icon, R.id.gpView1, R.id.gpView2, R.id.gpView3, R.id.gpView4, R.id.gpView5, R.id.btn_test4, R.id.btn_test5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_show:
                BaseQuestStart.bannerQuest(this);
                upimg();
                break;
            case R.id.iv_icon:
                StartIntent.startTestPageActivity(this);
                break;
            case R.id.gpView1:
                gradlePrassDialogUtil.showGradlePragress(this);
                gradlePrassDialogUtil.setOnBackGradleStub(this);
                break;
            case R.id.gpView2:
                break;
            case R.id.gpView3:
                break;
            case R.id.gpView4:
                break;
            case R.id.gpView5:
                break;
            case R.id.btn_test4:
                break;
            case R.id.btn_test5:
                break;
        }
    }

    @Override
    public void AnimationFinish(GradlePrassView gradlePrassView) {
        switch (gradlePrassView.getId()) {
            case R.id.gpView1:
                ToastFactory.getToast(this, "gpView1").show();
                gpView2.statrImgViewAnim(this, true, 2000);
                break;
            case R.id.gpView2:
                gpView3.statrImgViewAnim(this, true, 2000);
                ToastFactory.getToast(this, "gpView2").show();
                break;
            case R.id.gpView3:
                gpView4.statrImgViewAnim(this, true, 2000);
                ToastFactory.getToast(this, "gpView3").show();
                break;
            case R.id.gpView4:
                gpView5.statrImgViewAnim(this, false, 2000);
                ToastFactory.getToast(this, "gpView4").show();
                break;
            case R.id.gpView5:
                ToastFactory.getToast(this, "gpView5").show();
                break;
        }
    }


    @Override
    public boolean backGradleStub(GradlePrassView currView, GradlePrassView nextGradlePrassView) {

        switch (currView.getId()) {
            case R.id.gpView1:
                Log.e("wsr", "backGradleStub  " + "1");
                return true;
            case R.id.gpView2:
                Log.e("wsr", "backGradleStub  " + "2");
                return true;
            case R.id.gpView3:
                Log.e("wsr", "backGradleStub  " + "3");
                return true;
            case R.id.gpView4:
                Log.e("wsr", "backGradleStub  " + "4");
                return false;


        }
        return false;
    }

    @Override
    public boolean lastAnimFinash(GradlePrassView currView) {
        Log.e("wsr", "lastAnimFinash");
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}