package com.sunrun.sunrunframwork.bean;


import com.sunrun.sunrunframwork.http.utils.DateUtil;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

/**
 * 登录信息
 * 
 * @author WQ 上午10:31:08
 * 
 */
public class LoginInfo extends SerializableBean {
	
	
	private String username;
	
	private String email;
	
	private String mobile;
	
	private String nickname;//昵称
	private String device_no;//设备号(APP端MD5加密后的结果)
	private String exam_time;//考试时间(正式考试)
	private String is_exam;//'是否已进行摸底考试：0：否，1：是',
	
	private String average_score;//'综合得分(平均分)',
	
	private int is_try_out;//'是否已试用：0：否，1：是，2：试用进行中',
	
	private int is_paid_guide;//'首页引导付费弹窗提示：0：未提示，1：已提示'
	
	private String try_start;//'试用开始时间',
	private String try_end;//'试用结束时间'
	private String is_vip;//'是否已付费：0：否，1：是',
	private String tpo_ids;//'已付费tpo id，来源：tpo表 id',
	private String vip_start;//'付费开始时间',
	private String vip_end;//'付费结束时间',
	 private String avatar;//'头像',
	private String password;
	private String sign_key;
	
	private String salt;
	
	

	
	private String open_id_qq;
	
	private String open_id_weixin;
	
	private String open_id_sina;
	
	
	
	

	

	
	private String login_time;
	
	private String login_ip;
	
	private String is_hid;
	
	private String is_del;
	




	public String getSign_key() {
		return sign_key;
	}





	public LoginInfo setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}





	public String getAvatar() {
		return avatar+"?"+System.currentTimeMillis();
	}





	public String getId() {
		return id;
	}





	public String getUsername() {
		return username;
	}





	public String getPassword() {
		return password;
	}





	public String getSalt() {
		return salt;
	}





	public String getDevice_no() {
		return device_no;
	}





	public String getAverage_score() {
		return average_score;
	}





	public String getExam_time() {
		return exam_time;
	}


	public long getExamTime() {
		if(exam_time==null)return 0;
		return DateUtil.getDateByFormat(exam_time, DateUtil.dateFormatYMD).getTime();
	}

	public LoginInfo setExamTime(long time){
		exam_time=DateUtil.getStringByFormat(time, DateUtil.dateFormatYMD);
		return this;
	}



	public String getOpen_id_qq() {
		return open_id_qq;
	}





	public String getOpen_id_weixin() {
		return open_id_weixin;
	}





	public String getOpen_id_sina() {
		return open_id_sina;
	}





	public boolean is_exam() {
		return "1".equals(is_exam);
	}
	





	public int getIs_try_out() {
		return is_try_out;
	}





	public String getTry_start() {
		return try_start;
	}





	public String getTry_end() {
		return try_end;
	}





	public int getIs_paid_guide() {
		return is_paid_guide;
	}





	public String getIs_vip() {
		return is_vip;
	}





	public String getTpo_ids() {
		return tpo_ids;
	}





	public String getVip_start() {
		return vip_start;
	}





	public String getVip_end() {
		return vip_end;
	}





	public String getLogin_time() {
		return login_time;
	}





	public String getLogin_ip() {
		return login_ip;
	}





	public String getIs_hid() {
		return is_hid;
	}





	public String getIs_del() {
		return is_del;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getMobile() {
		return mobile;
	}





	public void setMobile(String mobile) {
		this.mobile = mobile;
	}





	public String getNickname() {
		return nickname;
	}





	public LoginInfo setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
	public LoginInfo setIsExam( String isExam) {
		this.is_exam = isExam;
		return this;
	}


	public boolean isValid() {
		// return uid != null && tokey != null;
		return !EmptyDeal.isEmpy(id);
	}


}
