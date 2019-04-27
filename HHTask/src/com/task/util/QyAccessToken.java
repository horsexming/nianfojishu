package com.task.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.qq.weixin.mp.aes.AccessTokenJason;

/**
 * 获取企业access_token类
 * 调用getAccessToken()方法
 * @author Administrator
 *
 */
public class QyAccessToken {
	
	public static String CorpID="wxefc6de6b94ef9bfc";
	public static String Secret="CfD7peGIQeKET394Erqj6WfbRtgx6uF-oji58AKrNA-w8nH6hZfcnxsH7M2dlayh";
	//单例模式
	private static QyAccessToken accessToken=null;
	//access_token值
	private String access_token;
	//有效时间戳
	private long endTime;
	//私有构造方法
	private QyAccessToken(){}
	
	/**
	 * 获取access_token值
	 * @return access_token
	 */
	public static String getAccessToken(){
		if(accessToken==null){
			accessToken=new QyAccessToken();
			accessToken.makeAccessToken();
		}
		long nowTime=System.currentTimeMillis();
		if(nowTime>accessToken.endTime){
			accessToken.makeAccessToken();
		}
//		System.out.println(accessToken.access_token);
		return accessToken.access_token;
	}
	/**
	 * 从微信服务器获取access_token值
	 */
	private void makeAccessToken() {
		//创建一个http请求
		HttpRequest request=new HttpRequest();
		//设置参数
		Map<String,String> params=new HashMap<String,String>();
//		params.put("grant_type", "client_credential");
		params.put("corpid", CorpID);
		params.put("corpsecret", Secret);
		HttpResponse result = null;
		//发起https请求
		try {
			result=request.sendHttpsGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken", params);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(result);
		//返回数据转成对象，得到access_token
		try{
			Gson gson=new Gson();
			AccessTokenJason atj=gson.fromJson(result.getDataString(), AccessTokenJason.class);
			access_token=atj.getAccess_token();
		}catch (Exception e) {
			e.printStackTrace();
		}
		//设置access_token有效时间
		endTime=System.currentTimeMillis()+7100*1000;
	}
	
	public static void setAppInfo(String appid,String appsecret) {
		CorpID=appid;
		Secret=appsecret;
		if(accessToken==null){
			accessToken=new QyAccessToken();
		}
		accessToken.makeAccessToken();
	}
}
