package com.sunrun.sunrunframwork.pay.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.sunrun.sunrunframwork.http.utils.Logger;
import com.sunrun.sunrunframwork.pay.PayConfig;


/**
 * @阿里支付帮助类
 */
public class AlipayUtils {
	Activity mContext;
	Handler handler;
	public AlipayUtils(Activity mContext) {
		this.mContext=mContext;
		handler=new Handler();
	}
	public void requestPay(){
		
	}
	public void requestPay(String orderNo,String shopName,String shopDestcipt,String total,String notifyUrl,final Callback callback){
		String orderInfo = getOrderInfo(orderNo,shopName, shopDestcipt, total,notifyUrl);

		/**
		 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
		 */
		Logger.E("1 " + orderInfo);
		String sign = sign(orderInfo);
		
		Logger.E("3 "+new String(sign));
		
		try {
			/**
			 * 仅需对sign 做URL编码
			 */
			sign = URLEncoder.encode(sign, "UTF-8");
			
			Logger.E("4 "+new String(sign));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 完整的符合支付宝参数规范的订单信息
		 */
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(mContext);
				// 调用支付接口，获取支付结果
			
				String result = alipay.pay(payInfo, true);
				
				PayResult payResult = new PayResult(result);
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				
				Logger.E(result);
				Logger.E(payInfo);
				try {
					Logger.E(URLDecoder.decode(payInfo, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String resultStatus = payResult.getResultStatus();
			final	Message msg = new Message();
				msg.what = PayConfig.SDK_PAY_FLAG;
				msg.obj = resultStatus;
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						callback.handleMessage(msg);						
					}
				});
				
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}
	
	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(mContext);
		String version = payTask.getVersion();
		Toast.makeText(mContext, version, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	private String getOrderInfo(String orderNo,String subject, String body, String price,String notifyUrl) {


		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PayConfig.PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + PayConfig.SELLER + "\"";

		orderNo=orderNo==null?getOutTradeNo():orderNo;
		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		notifyUrl=notifyUrl==null?PayConfig.ALI_NOTIFY_URL:notifyUrl;
		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + notifyUrl + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		
		

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	private String sign(String content) {
		return SignUtils.sign(content,PayConfig. RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}
}
