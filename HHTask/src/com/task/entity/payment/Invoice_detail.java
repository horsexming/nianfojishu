package com.task.entity.payment;


/**
 * 发票明细:(ta_Invoice_detail)
 * @author licong
 * 2018-09-04
 */
public class Invoice_detail implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Double dj;// 单价
	private String ggxh;//规格型号
	private String hwmc;//物品名称
	private Float slv;// 税率
	private Float sl;// 数量
	private String dw;// 单位
	private Double je;// 不含税金额
	private Double se;//税额
	private InvoiceCheck invoiceCheck ;//发票信息
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getDj() {
		return dj;
	}
	public void setDj(Double dj) {
		this.dj = dj;
	}
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	public String getHwmc() {
		return hwmc;
	}
	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}
	public Float getSlv() {
		return slv;
	}
	public void setSlv(Float slv) {
		this.slv = slv;
	}
	public Float getSl() {
		return sl;
	}
	public void setSl(Float sl) {
		this.sl = sl;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	public Double getSe() {
		return se;
	}
	public void setSe(Double se) {
		this.se = se;
	}
	public InvoiceCheck getInvoiceCheck() {
		return invoiceCheck;
	}
	public void setInvoiceCheck(InvoiceCheck invoiceCheck) {
		this.invoiceCheck = invoiceCheck;
	}
	
	
}
