package com.qq.weixin.mp.aes;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.task.util.QyAccessToken;
import com.task.util.WeiXinUtil;

public class WeiXinUnloginAction {
	private String signature;
	private String msg_signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	public void qyhuidiao() {
		WXBizMsgCrypt wxcpt;
		try {
//			try {
//				msg_signature = URLDecoder.decode(msg_signature, "utf-8");
//				timestamp = URLDecoder.decode(timestamp, "utf-8");
//				nonce = URLDecoder.decode(nonce, "utf-8");
//				echostr = URLDecoder.decode(echostr, "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			wxcpt = new WXBizMsgCrypt(WeiXinUtil.qyToken,
					WeiXinUtil.EncodingAESKey,
					QyAccessToken.CorpID);
			String result = wxcpt.VerifyURL(msg_signature, timestamp, nonce,
					echostr);
			// 验证URL成功，将sEchoStr返回
			HttpServletResponse response = ServletActionContext
			.getResponse();
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	response.getWriter().write(result);
	response.getWriter().close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getMsg_signature() {
		return msg_signature;
	}
	public void setMsg_signature(String msgSignature) {
		msg_signature = msgSignature;
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
	
	
}
