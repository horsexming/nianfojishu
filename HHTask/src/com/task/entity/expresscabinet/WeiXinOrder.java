/**
 * 
 */
package com.task.entity.expresscabinet;

import java.io.Serializable;

/**
 * @author Administrator 快递柜微信扫码支付订单记录表ta_mj_weixinorder
 */
public class WeiXinOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; // 记录id
	private String appId; // 公众账号(等同企业微信corpid)
	private String mch_id; // 商户号
	private String transaction_id; // 微信的订单号(建议优先使用)
	private String out_trade_no; // 商户订单号(自己系统生成的)
	private String nonce_str; // 随机字符串
	private String sign; // 签名
	private String sign_type; // 签名类型

	private String keyId; // 商户key
	private String spbill_create_ip; // 获取发起设备(快递柜)ip
	private String userId; // 在商户下的用户唯一id(暂时用不到)
	private String openId; // 在商户下的用户唯一id
	private String is_subscribe; // 是否关注公众号(Y关注/N未关注)
	private Float order_price; // 金额
	private String productId; // 商品编号
	private String body; // 商品名称
	private String urlCode; // 二维码字符串
	private String notify_url; // 回调接口
	private String trade_type; // 支付方式
	private String startTime; // 交易发起日期(yyyy-dd-mm)
	private String startTimesfm; // 交易发起日期(yyyy-dd-mm hh:mm:ss)
	private String tradingStatus; // 交易状态
	private String quCodes; // 验证码(取)

	public WeiXinOrder() {
		super();
	}

	public WeiXinOrder(Integer id, String appId, String mchId, String transactionId, String outTradeNo, String nonceStr,
			String sign, String signType, String keyId, String spbillCreateIp, String userId, Float orderPrice,
			String productId, String body, String urlCode, String notifyUrl, String tradeType, String startTime,
			String tradingStatus, String quCodes, String openId, String is_subscribe) {
		super();
		this.id = id;
		this.appId = appId;
		mch_id = mchId;
		transaction_id = transactionId;
		out_trade_no = outTradeNo;
		nonce_str = nonceStr;
		this.sign = sign;
		sign_type = signType;
		this.keyId = keyId;
		spbill_create_ip = spbillCreateIp;
		this.userId = userId;
		order_price = orderPrice;
		this.productId = productId;
		this.body = body;
		this.urlCode = urlCode;
		notify_url = notifyUrl;
		trade_type = tradeType;
		this.startTime = startTime;
		this.tradingStatus = tradingStatus;
		this.quCodes = quCodes;
		this.openId = openId;
		this.is_subscribe = is_subscribe;
	}

	public String getQuCodes() {
		return quCodes;
	}

	public void setQuCodes(String quCodes) {
		this.quCodes = quCodes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String signType) {
		sign_type = signType;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbillCreateIp) {
		spbill_create_ip = spbillCreateIp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String isSubscribe) {
		is_subscribe = isSubscribe;
	}

	public Float getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Float orderPrice) {
		order_price = orderPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String tradeType) {
		trade_type = tradeType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTimesfm() {
		return startTimesfm;
	}

	public void setStartTimesfm(String startTimesfm) {
		this.startTimesfm = startTimesfm;
	}

	public String getTradingStatus() {
		return tradingStatus;
	}

	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mchId) {
		mch_id = mchId;
	}

}
