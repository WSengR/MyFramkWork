package com.example.wsr.myapplication.activity;

import com.example.wsr.myapplication.R;
import com.example.wsr.myapplication.view.GifView;
import com.sunrun.sunrunframwork.uibase.BaseActivity;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/14
 * @功能描述:
 */

public class TestGifActivity  extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_test_gif);

        GifView gif1 = (GifView) findViewById(R.id.gif_view);
        // 设置背景gif图片资源
        gif1.setMovieResource(R.raw.test);
        // 设置暂停
        // gif2.setPaused(true);
    }
}
