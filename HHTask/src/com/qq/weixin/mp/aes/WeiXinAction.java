package com.qq.weixin.mp.aes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONException;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qq.weixin.Server.WeiXinServer;
import com.task.util.AccessToken;
import com.task.util.HttpRequest;
import com.task.util.HttpResponse;
import com.task.util.MKUtil;
import com.task.util.QyAccessToken;
import com.task.util.Sha1Util;
import com.task.util.WeiXinUtil;

public class WeiXinAction extends ActionSupport {
	private WeiXinServer weiXinServer;
	private String signature;
	private String msg_signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private String rePayUrl = "https://api.weixin.qq.com/pay/genprepay?accesss_toaken=";

	

	public void maintest() {
		String token = QyAccessToken.getAccessToken();
		MKUtil.writeJSON(token);
	}

	public void opFirst() {
		String result = null;
		String[] arr = new String[] { "tangxiaobin", timestamp, nonce };
		// 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// 进行sha1加密
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(content.toString().getBytes());
			result = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// System.out.println("signature:" + signature);
		// System.out.println("result:" + result);
		// 将signature转换成大写
		String signatureUpperCase = signature.toUpperCase();
		// 判断signature值与生成的result值是否相等
		if (signatureUpperCase.equals(result)) {
			MKUtil.writeJSON(true);
		} else {
			MKUtil.writeJSON(false);

		}
	}

	/**
	 * 生成预支付订单
	 */
	public void makeRepayOrder() {
		// 当前时间 yyyyMMddHHmmss
		String currTime = WeiXinUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = WeiXinUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		String out_trade_no = strReq;
		// 创建一个http请求
		HttpRequest request = new HttpRequest();
		// 设置参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", AccessToken.getAccessToken());
		params.put("openid", "");
		params.put("noncestr", "");
		params.put("package", "");
		params.put("timestamp", "");
		params.put("app_signature", "");
		params.put("sign_method", "sha1");
		HttpResponse result = null;
		// 发起https请求
		try {
			result = request.sendHttpsGet(rePayUrl
					+ AccessToken.getAccessToken(), params);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(result);
		// 返回数据转成对象，得到access_token
		try {
			Gson gson = new Gson();
			// AccessTokenJason atj=gson.fromJson(result.getDataString(),
			// AccessTokenJason.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 设置access_token有效时间
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des.toUpperCase();
	}

	public WeiXinServer getWeiXinServer() {
		return weiXinServer;
	}

	public void setWeiXinServer(WeiXinServer weiXinServer) {
		this.weiXinServer = weiXinServer;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String getMsg_signature() {
		return msg_signature;
	}

	public void setMsg_signature(String msgSignature) {
		msg_signature = msgSignature;
	}

	public String getRePayUrl() {
		return rePayUrl;
	}

	public void setRePayUrl(String rePayUrl) {
		this.rePayUrl = rePayUrl;
	}

}
