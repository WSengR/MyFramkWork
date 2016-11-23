package com.sunrun.sunrunframwork.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sunrun.sunrunframwork.pay.WXPayUtils;
import com.sunrun.sunrunframwork.uiutils.UIUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;
	public static PayProgressListener listener = null;

	public static void registListener(PayProgressListener listener2) {
		listener = listener2;
	}

	public static void unRegistListener() {
		listener = null;
	}

	public static abstract class PayProgressListener {
		public static final int ORDER_CREATE_SUCCESS = 1;
		public static final int ORDER_CREATE_FAILD = -1;

		/**
		 * 创建预支付订单
		 */
		public void onCreateOrder() {}

		public void onOrderCreated(int result) {}

		public void onStartPay() {
			UIUtils.shortM("启动微信客户端");
		}

		public void onPayFinish(int payresult) {
			if (payresult == 0) {
				// 支付成功
				UIUtils.shortM("支付成功");
			} else if (payresult == -1) {

			} else if (payresult == -2) {
				UIUtils.shortM("支付取消");
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //setContentView(R.layout.pay_result);
		api = WXAPIFactory.createWXAPI(this, WXPayUtils.APP_ID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
		// Logger.E(req);
	}

	@Override
	public void onResp(BaseResp resp) {

		Log.d("BaseResp", "onPayFinish, errCode = " + resp.errCode + "  " + resp.openId + "   " + resp.transaction + "  " + resp);
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {}
		if (listener != null)
			listener.onPayFinish(resp.errCode);
		unRegistListener();
		finish();
	}
}