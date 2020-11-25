package io.renren.modules.app.utils;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import io.renren.modules.app.utils.RestSMS.SysConfig;
import io.renren.modules.app.utils.RestSMS.client.AbsRestClient;
import io.renren.modules.app.utils.RestSMS.client.JsonReqClient;

public class SmsUtil {
	
	private static String Sid;
	private static String AppId;
	private static String Token;
	private static String Templateid;
	public static String baseUri;
	
	static{
		if(Sid==null) {
			Sid=SysConfig.getInstance().getProperty("sms.Sid");
		}
		if(AppId==null) {
			AppId=SysConfig.getInstance().getProperty("sms.AppId");
		}
		if(Token==null) {
			Token=SysConfig.getInstance().getProperty("sms.Token");
		}
		if(Templateid==null) {
			Templateid=SysConfig.getInstance().getProperty("sms.Templateid");
		}
		if(baseUri==null) {
			baseUri=SysConfig.getInstance().getProperty("baseUri");
		}
	}
	
	@Value(value = "${sms.Sid}")
	public static String getSid() {
		return Sid;
	}

	public static void setSid(String sid) {
		Sid = sid;
	}

	public static String getAppId() {
		return AppId;
	}
	@Value(value = "${sms.AppId}")
	public static void setAppId(String appId) {
		AppId = appId;
	}

	public static String getToken() {
		return Token;
	}
	@Value(value = "${sms.Token}")
	public static void setToken(String token) {
		Token = token;
	}

	public static String getTemplateid() {
		return Templateid;
	}
	@Value(value = "${sms.Templateid}")
	public static void setTemplateid(String templateid) {
		Templateid = templateid;
	}

	static AbsRestClient InstantiationRestAPI() {
		return new JsonReqClient();
	}
	
	public static boolean sendSms(String mobile,String param,HttpServletRequest request) {
		try {
			String result=InstantiationRestAPI().sendSms(Sid, Token, AppId, Templateid, param, mobile, "");
			JSONObject object=JSONObject.parseObject(result);
			int code=object.getIntValue("code");
			int count=object.getIntValue("count");
			if(code==0&&count>0) {
				return true;
			}else {
				String msg=object.getString("msg");
				System.err.println("发送短信错误=========="+msg+"\n"+result);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String smsRandom() {
		int code=(int)((Math.random()*9+1)*100000);
		return code+"";
	}

}
