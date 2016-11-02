package com.example.wsr.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.wsr.myapplication.bean.BennerBean;
import com.example.wsr.myapplication.bean.ButtomAdapter;
import com.example.wsr.myapplication.bean.RedAdapter;
import com.example.wsr.myapplication.bean.RedBean;
import com.example.wsr.myapplication.bean.SpecialBean;
import com.example.wsr.myapplication.utils.StartIntent;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.sunrun.sunrunframwork.animation.viewpagetransformer.DepthPageTransformer;
import com.sunrun.sunrunframwork.net.UrlBase;
import com.sunrun.sunrunframwork.utils.LogUtils;
import com.example.wsr.myapplication.view.PracticeButtomButton;
import com.sunrun.sunrunframwork.weight.MMGridView;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_sousuo)
    ImageView ivSousuo;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.gv_special)
    MMGridView gvSpecial;
    @Bind(R.id.gv_image)
    MMGridView gvImage;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;


    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher)
            .cacheInMemory(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    AsyncHttpClient client = new AsyncHttpClient();
    @Bind(R.id.topLin)
    LinearLayout topLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Log.e("wsr", "BuildConfig.MAX_UPLOAD_COUNT " + BuildConfig.MAX_UPLOAD_COUNT);
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);

        getBennerData();
        getBodyData();
        getDaohangData();

    }

    private void setBanner(List<BennerBean.InfoBean> info) {
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, info)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置翻页的效果，不需要翻页效果可用不设
//        .setPageTransformer(new DepthPageTransformer());    //集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
        convenientBanner.setManualPageable(false);//设置不能手动影响
    }

    public class LocalImageHolderView implements Holder<BennerBean.InfoBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, BennerBean.InfoBean data) {
            ImageLoader.getInstance().displayImage(data.getImage(), imageView, options);
            Log.e("wsr", "Image" + data.getImage());
        }
    }


    @OnClick({R.id.tv_title, R.id.iv_sousuo, R.id.image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                StartIntent.startMyActivity(this);
                break;
            case R.id.iv_sousuo:
                break;
            case R.id.image:
                break;
        }
    }

    public void getBennerData() {
        client.get(UrlBase.bannerURl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtils.e("Http", statusCode + "URl:" + UrlBase.bannerURl);
                if (statusCode == 200) {
                    String resule = new String(responseBody);
                    LogUtils.e("Http", "Date " + resule);
                    convenientBanner.startTurning(5000);

                    setBanner(BennerBean.getData(resule).getInfo());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtils.e("Http", "失败");
                error.printStackTrace();// 把错误信息打印出轨迹来
            }
        });
    }

    public void getBodyData() {
        client.get(UrlBase.bodyURL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtils.e("Http", statusCode + "URl:" + UrlBase.bodyURL);
                if (statusCode == 200) {
                    String resule = new String(responseBody);
                    LogUtils.e("Http", "Date " + resule);
                    gvSpecial.setAdapter(new RedAdapter(MainActivity.this, RedBean.getData(resule).getInfo().getLable_result()));
                    gvImage.setAdapter(new ButtomAdapter(MainActivity.this, RedBean.getData(resule).getInfo().getTalent_result()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtils.e("Http", "失败");
                error.printStackTrace();// 把错误信息打印出轨迹来
            }
        });
    }

    public void getDaohangData() {
        client.get(UrlBase.daoHangURL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtils.e("Http", statusCode + "URl:" + UrlBase.bodyURL);
                if (statusCode == 200) {
                    String resule = new String(responseBody);
                    LogUtils.e("Http", "Date " + "getDaohangData+------" + resule);
                    addTopButton(SpecialBean.getData(resule).getInfo());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtils.e("Http", "失败");
                error.printStackTrace();// 把错误信息打印出轨迹来
            }
        });
//        addTopButton(SpecialBean.getData().getInfo());
    }


    /**
     * 添加顶部导航栏
     */
    List<PracticeButtomButton> buttonList = new ArrayList<PracticeButtomButton>();

    public void addTopButton(List<SpecialBean.InfoBean> info) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        for (int i = 0; i < info.size(); i++) {
            final PracticeButtomButton topButton = new PracticeButtomButton(this);
            topButton.setLayoutParams(lp);
            topButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!topButton.isSelect()) {
                        for (int i = 0; i < buttonList.size(); i++) {
                            buttonList.get(i).setIsSelect(false);
                        }
                        topButton.setIsSelect(true);
                    }
                }
            });
            topButton.setButtonText(info.get(i).getTitle());
            topButton.setIsSelect(false);
            buttonList.add(topButton);
            topLin.addView(topButton, lp);
        }
        buttonList.get(0).setIsSelect(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        convenientBanner.stopTurning();
    }

    @Override
    protected void onResume() {
        super.onResume();
        convenientBanner.startTurning(5000);
    }
}
