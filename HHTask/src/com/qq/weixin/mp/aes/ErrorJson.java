package com.qq.weixin.mp.aes;
/**
 * 错误返回的json数据对象
 * @author fzc2046
 *
 */
public class ErrorJson {
	//错误代码
	private Integer errcode;
	//错误描述
	private String errmsg;
	
	public ErrorJson(){}
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
