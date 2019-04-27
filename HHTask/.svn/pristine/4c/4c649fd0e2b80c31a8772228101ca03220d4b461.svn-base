package com.qq.weixin.mp.aes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.task.util.MKUtil;

public class HuidiaoAction extends ActionSupport {
	public String msg_signature;
	public String timestamp;
	public String nonce;
	public String echostr;

	public void yanzheng() {

		String sToken = "dOuUAz1HfyR4wjybBuQE3z";
		String sCorpID = "wxefc6de6b94ef9bfc";
		String sEncodingAESKey = "3cYUECAEOXih2XNgCWbuIyIMS1AiVdJRorsZO9CmhKV";

		String sEchoStr; // 需要返回的明文
		try {
			try {
				msg_signature = URLDecoder.decode(msg_signature, "utf-8");
				timestamp = URLDecoder.decode(timestamp, "utf-8");
				nonce = URLDecoder.decode(nonce, "utf-8");
				echostr = URLDecoder.decode(
						URLDecoder.decode(echostr, "utf-8"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey,
					sCorpID);
			sEchoStr = wxcpt
					.VerifyURL(msg_signature, timestamp, nonce, echostr);
			try {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(sEchoStr);
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// System.out.println("verifyurl echostr: " + sEchoStr);
			// 验证URL成功，将sEchoStr返回
			// HttpUtils.SetResponse(sEchoStr);
		} catch (Exception e) {
			// 验证URL失败，错误原因请查看异常
			e.printStackTrace();
		}
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
