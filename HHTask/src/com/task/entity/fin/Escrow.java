package com.task.entity.fin;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 委托付款 
 * ta_fin_escrow
 * @author jhh
 *
 */
public class Escrow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	@FieldMeta(name="收款方")
	private String shoukuanFang;//收款方
	private String payWay;//支付方式
	private String shoukuanyh;//收款银行
	private String shoukuanZhanghao;//收款账号
	@FieldMeta(name="付款用途")
	private String fukuanYongtu;//付款用途
	@FieldMeta(name="应付款金额")
	private Double yingfuJine;//应付款金额
	private Double shifuJine;//实际款金额	
	private String more;//备注
	@FieldMeta(name="申请人")
	private String username;//申请人
	@FieldMeta(name="申请时间")
	private String applyTime;//申请时间
	private String paymentMonth;//支付月份
	private String paymentTime;//支付时间
	private String status;//状态
	@FieldMeta(name="资金使用申请单号")
	private String zijinshiyongNum;//资金使用申请单号
	private Integer zijinshyongID;//资金使用申请id
	private Integer epId;//资金使用申请id
	@FieldMeta(name="被委托方")
	private String bwtCompany;//被委托方
	private String printStatus;//打印状态（是，否（默认））
	private String spTime;//审批时间
	private String sourceEntity;//来源实体类（FundApply,ReceiptLog）
	private Integer monthId;//月度汇总Id(EscrowMonth)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShoukuanFang() {
		return shoukuanFang;
	}
	public void setShoukuanFang(String shoukuanFang) {
		this.shoukuanFang = shoukuanFang;
	}
	public String getShoukuanZhanghao() {
		return shoukuanZhanghao;
	}
	public void setShoukuanZhanghao(String shoukuanZhanghao) {
		this.shoukuanZhanghao = shoukuanZhanghao;
	}
	public String getFukuanYongtu() {
		return fukuanYongtu;
	}
	public void setFukuanYongtu(String fukuanYongtu) {
		this.fukuanYongtu = fukuanYongtu;
	}
	
	public Double getYingfuJine() {
		return yingfuJine;
	}
	public void setYingfuJine(Double yingfuJine) {
		this.yingfuJine = yingfuJine;
	}
	public Double getShifuJine() {
		return shifuJine;
	}
	public void setShifuJine(Double shifuJine) {
		this.shifuJine = shifuJine;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getZijinshiyongNum() {
		return zijinshiyongNum;
	}
	public void setZijinshiyongNum(String zijinshiyongNum) {
		this.zijinshiyongNum = zijinshiyongNum;
	}
	public Integer getZijinshyongID() {
		return zijinshyongID;
	}
	public void setZijinshyongID(Integer zijinshyongID) {
		this.zijinshyongID = zijinshyongID;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public String getBwtCompany() {
		return bwtCompany;
	}
	public void setBwtCompany(String bwtCompany) {
		this.bwtCompany = bwtCompany;
	}
	public String getSpTime() {
		return spTime;
	}
	public void setSpTime(String spTime) {
		this.spTime = spTime;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getShoukuanyh() {
		return shoukuanyh;
	}
	public void setShoukuanyh(String shoukuanyh) {
		this.shoukuanyh = shoukuanyh;
	}
	public String getSourceEntity() {
		return sourceEntity;
	}
	public void setSourceEntity(String sourceEntity) {
		this.sourceEntity = sourceEntity;
	}
	public Integer getMonthId() {
		return monthId;
	}
	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}
	
	
}
