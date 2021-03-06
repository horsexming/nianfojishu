package com.task.entity.caiwu.baobiao;

import java.io.Serializable;

public class ProfitSheet implements Serializable{
	/**
	 * 利润表；（ta_baobiao_ProfitSheet）
	 */
	private Integer id;
	private String months;//月份（yyyy-MM）
	/**
	 * 一、营业收入
	 */
	private Double ywsr;//营业收入
	private Double zyywsr;//主营业务收入
	private Double qtywsr;//其他业务收入
	private Double ywcb;//营业成本
	private Double zyywcb;//主营业务成本
	private Double qtywcb;//其他业务成本
	private Double ywsjandfj;//营业税金及附加
	private Double xsfy;//销售费用
	private Double glfy;//管理费用
	private Double ywzdf;//业务招待费
	private Double yjykff;//研究与开发费
	private Double cwfy;//财务费用
	private Double lxzc;//利息支出
	private Double lxsr;//利息收入
	private Double hdjss;//  汇兑净损失（汇兑净收益以“－”）
	private Double zcjzss;//资产减值损失
	private Double gyzbdsy;//公允值变动收益
	private Double tzsy;//投资收益
	private Double lyqyAndhyqysy;//其中：对联营企业和合营企业的投资收益
	/**\
	 * 营业利润（亏损以“－”号填列）
	 */
	//加：
	private Double ywlr;// 营业利润
	private Double ywsr1;//    加：营业外收入 
	private Double fldzcdl;//非流动资产处置利得
	private Double fhbzcdl;// 非货币性资产交换利得（非货币性交易收益）
	private Double zfbz;//政府补助（补贴收入）
	private Double zwczdl;//债务重组利得
	//  减：营业外支出
	private Double ywzc;//营业外支出
	private Double fldzcss;//非流动资产处置损失
	private Double fhbzcss;//非货币性资产交换损失（非货币性交易损失）
	private Double zwczss;//债务重组损失
	/**
	 * 三、利润总额（亏损总额以“－”号填列）
	 */
	private Double lrze;//利润总额;
	private Double sdsfy;// 减：所得税费用
	private Double wqrtzss;//    加：＃* 未确认的投资损失
	/**
	 * 净利润(净利润（净亏损以“－”号填列）)
	 */
	private Double jlr;//净利润;
	private Double littlegdsy;//  减：* 少数股东损益
	/**
	 * 五、归属于母公司所有者的净利润
	 */
	private Double parentCompanyLR;//五、归属于母公司所有者的净利润
	/**
	 * 六、每股收益：
	 */
	private Double jbmgsy;//基本每股收益
	private Double xsmgsy;//稀释每股收益
	
	private ProfitSheet lastYearProfit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public Double getYwsr() {
		return ywsr;
	}
	public void setYwsr(Double ywsr) {
		this.ywsr = ywsr;
	}
	public Double getZyywsr() {
		return zyywsr;
	}
	public void setZyywsr(Double zyywsr) {
		this.zyywsr = zyywsr;
	}
	public Double getQtywsr() {
		return qtywsr;
	}
	public void setQtywsr(Double qtywsr) {
		this.qtywsr = qtywsr;
	}
	public Double getYwcb() {
		return ywcb;
	}
	public void setYwcb(Double ywcb) {
		this.ywcb = ywcb;
	}
	public Double getZyywcb() {
		return zyywcb;
	}
	public void setZyywcb(Double zyywcb) {
		this.zyywcb = zyywcb;
	}
	public Double getQtywcb() {
		return qtywcb;
	}
	public void setQtywcb(Double qtywcb) {
		this.qtywcb = qtywcb;
	}
	public Double getYwsjandfj() {
		return ywsjandfj;
	}
	public void setYwsjandfj(Double ywsjandfj) {
		this.ywsjandfj = ywsjandfj;
	}
	public Double getXsfy() {
		return xsfy;
	}
	public void setXsfy(Double xsfy) {
		this.xsfy = xsfy;
	}
	public Double getGlfy() {
		return glfy;
	}
	public void setGlfy(Double glfy) {
		this.glfy = glfy;
	}
	public Double getYwzdf() {
		return ywzdf;
	}
	public void setYwzdf(Double ywzdf) {
		this.ywzdf = ywzdf;
	}
	public Double getYjykff() {
		return yjykff;
	}
	public void setYjykff(Double yjykff) {
		this.yjykff = yjykff;
	}
	public Double getCwfy() {
		return cwfy;
	}
	public void setCwfy(Double cwfy) {
		this.cwfy = cwfy;
	}
	public Double getLxzc() {
		return lxzc;
	}
	public void setLxzc(Double lxzc) {
		this.lxzc = lxzc;
	}
	public Double getLxsr() {
		return lxsr;
	}
	public void setLxsr(Double lxsr) {
		this.lxsr = lxsr;
	}
	public Double getHdjss() {
		return hdjss;
	}
	public void setHdjss(Double hdjss) {
		this.hdjss = hdjss;
	}
	public Double getZcjzss() {
		return zcjzss;
	}
	public void setZcjzss(Double zcjzss) {
		this.zcjzss = zcjzss;
	}
	public Double getGyzbdsy() {
		return gyzbdsy;
	}
	public void setGyzbdsy(Double gyzbdsy) {
		this.gyzbdsy = gyzbdsy;
	}
	public Double getTzsy() {
		return tzsy;
	}
	public void setTzsy(Double tzsy) {
		this.tzsy = tzsy;
	}
	public Double getLyqyAndhyqysy() {
		return lyqyAndhyqysy;
	}
	public void setLyqyAndhyqysy(Double lyqyAndhyqysy) {
		this.lyqyAndhyqysy = lyqyAndhyqysy;
	}
	public Double getYwsr1() {
		return ywsr1;
	}
	public void setYwsr1(Double ywsr1) {
		this.ywsr1 = ywsr1;
	}
	public Double getFldzcdl() {
		return fldzcdl;
	}
	public void setFldzcdl(Double fldzcdl) {
		this.fldzcdl = fldzcdl;
	}
	public Double getFhbzcdl() {
		return fhbzcdl;
	}
	public void setFhbzcdl(Double fhbzcdl) {
		this.fhbzcdl = fhbzcdl;
	}
	public Double getZfbz() {
		return zfbz;
	}
	public void setZfbz(Double zfbz) {
		this.zfbz = zfbz;
	}
	public Double getZwczdl() {
		return zwczdl;
	}
	public void setZwczdl(Double zwczdl) {
		this.zwczdl = zwczdl;
	}
	public Double getYwzc() {
		return ywzc;
	}
	public void setYwzc(Double ywzc) {
		this.ywzc = ywzc;
	}
	public Double getFldzcss() {
		return fldzcss;
	}
	public void setFldzcss(Double fldzcss) {
		this.fldzcss = fldzcss;
	}
	public Double getFhbzcss() {
		return fhbzcss;
	}
	public void setFhbzcss(Double fhbzcss) {
		this.fhbzcss = fhbzcss;
	}
	public Double getZwczss() {
		return zwczss;
	}
	public void setZwczss(Double zwczss) {
		this.zwczss = zwczss;
	}
	public Double getSdsfy() {
		return sdsfy;
	}
	public void setSdsfy(Double sdsfy) {
		this.sdsfy = sdsfy;
	}
	public Double getWqrtzss() {
		return wqrtzss;
	}
	public void setWqrtzss(Double wqrtzss) {
		this.wqrtzss = wqrtzss;
	}
	public Double getLittlegdsy() {
		return littlegdsy;
	}
	public void setLittlegdsy(Double littlegdsy) {
		this.littlegdsy = littlegdsy;
	}
	public Double getParentCompanyLR() {
		return parentCompanyLR;
	}
	public void setParentCompanyLR(Double parentCompanyLR) {
		this.parentCompanyLR = parentCompanyLR;
	}
	public Double getJbmgsy() {
		return jbmgsy;
	}
	public void setJbmgsy(Double jbmgsy) {
		this.jbmgsy = jbmgsy;
	}
	public Double getXsmgsy() {
		return xsmgsy;
	}
	public void setXsmgsy(Double xsmgsy) {
		this.xsmgsy = xsmgsy;
	}
	public ProfitSheet getLastYearProfit() {
		return lastYearProfit;
	}
	public void setLastYearProfit(ProfitSheet lastYearProfit) {
		this.lastYearProfit = lastYearProfit;
	}
	public Double getYwlr() {
		return ywlr;
	}
	public void setYwlr(Double ywlr) {
		this.ywlr = ywlr;
	}
	public Double getLrze() {
		return lrze;
	}
	public void setLrze(Double lrze) {
		this.lrze = lrze;
	}
	public Double getJlr() {
		return jlr;
	}
	public void setJlr(Double jlr) {
		this.jlr = jlr;
	}
	
	
	
}
