package com.task.entity.caiwu.baobiao.laozi;

import java.io.Serializable;

/**
 * 
 * @author 王晓飞 项目名:基本情况表(ta_baobiao_laozi_BasicSituation)
 *
 */
public class BasicSituation implements Serializable{
	
	private Integer id;
	private String companyNmae;//公司名称全称
	/**
	 * 月末人数
	 */
	/*从业人员*/
	private Integer cyry;//从业人员数
	private Integer zg;//在岗人员数
	private Integer lwpq;//劳务派遣人数
	//其他从业人员
	private Integer qtcyry;//其他从业人员
	private Integer pyltx;//聘用的离退休人员
	private Integer pywjgat;//聘用的外籍、港澳台人员
	private Integer jz;//非全日制人员、兼职人员、第二职业者
	
	private Integer lgblldgx ;//离岗仍保留劳动关系的人员
	private Integer nbtyey;//内部退养人员
	private Integer lxry;//离休人员
	private Integer txry;//退休人员
	/**
	 * 劳动报酬和生活费累计情况
	 */
	private Double cyryldbc;//从业人员劳动报酬
	private Double zgygzzze;//在岗员工工资总额
	private Double lwpqryldbc;//劳务派遣人员劳动报酬
	/*其他从业
	人员劳动报酬*/
	private Double qtcyldbc;//其他从业人员劳动报酬
	private Double ltxryldbc;//聘用的离退休人员劳动报酬
	private Double wjgatldbc;//聘用的外籍、港澳台人员劳动报酬
	private Double jzldbc;//非全日制人员、兼职人员、第二职业者劳动报酬
	
	private Double lgblldgxshf;//离岗仍保留劳动关系职工的生活费
	private Double nbtyzgshf;//内部退养职工生活费
	private Double lxf;//离休费
	private Double tcwxm;//统筹外项目
	private Double txf;//退休费
	private Double tcwxm_1;//统筹外项目
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyNmae() {
		return companyNmae;
	}
	public void setCompanyNmae(String companyNmae) {
		this.companyNmae = companyNmae;
	}
	public Integer getZg() {
		return zg;
	}
	public void setZg(Integer zg) {
		this.zg = zg;
	}
	public Integer getLwpq() {
		return lwpq;
	}
	public void setLwpq(Integer lwpq) {
		this.lwpq = lwpq;
	}
	public Integer getPyltx() {
		return pyltx;
	}
	public void setPyltx(Integer pyltx) {
		this.pyltx = pyltx;
	}
	public Integer getPywjgat() {
		return pywjgat;
	}
	public void setPywjgat(Integer pywjgat) {
		this.pywjgat = pywjgat;
	}
	public Integer getJz() {
		return jz;
	}
	public void setJz(Integer jz) {
		this.jz = jz;
	}
	public Integer getLgblldgx() {
		return lgblldgx;
	}
	public void setLgblldgx(Integer lgblldgx) {
		this.lgblldgx = lgblldgx;
	}
	public Integer getNbtyey() {
		return nbtyey;
	}
	public void setNbtyey(Integer nbtyey) {
		this.nbtyey = nbtyey;
	}
	public Integer getTxry() {
		return txry;
	}
	public void setTxry(Integer txry) {
		this.txry = txry;
	}
	public Double getZgygzzze() {
		return zgygzzze;
	}
	public void setZgygzzze(Double zgygzzze) {
		this.zgygzzze = zgygzzze;
	}
	public Double getLwpqryldbc() {
		return lwpqryldbc;
	}
	public void setLwpqryldbc(Double lwpqryldbc) {
		this.lwpqryldbc = lwpqryldbc;
	}
	public Double getLtxryldbc() {
		return ltxryldbc;
	}
	public void setLtxryldbc(Double ltxryldbc) {
		this.ltxryldbc = ltxryldbc;
	}
	public Double getWjgatldbc() {
		return wjgatldbc;
	}
	public void setWjgatldbc(Double wjgatldbc) {
		this.wjgatldbc = wjgatldbc;
	}
	public Double getJzldbc() {
		return jzldbc;
	}
	public void setJzldbc(Double jzldbc) {
		this.jzldbc = jzldbc;
	}
	public Double getLgblldgxshf() {
		return lgblldgxshf;
	}
	public void setLgblldgxshf(Double lgblldgxshf) {
		this.lgblldgxshf = lgblldgxshf;
	}
	public Double getNbtyzgshf() {
		return nbtyzgshf;
	}
	public void setNbtyzgshf(Double nbtyzgshf) {
		this.nbtyzgshf = nbtyzgshf;
	}
	public Double getLxf() {
		return lxf;
	}
	public void setLxf(Double lxf) {
		this.lxf = lxf;
	}
	public Double getTcwxm() {
		return tcwxm;
	}
	public void setTcwxm(Double tcwxm) {
		this.tcwxm = tcwxm;
	}
	public Double getTxf() {
		return txf;
	}
	public void setTxf(Double txf) {
		this.txf = txf;
	}
	public Double getTcwxm_1() {
		return tcwxm_1;
	}
	public void setTcwxm_1(Double tcwxm_1) {
		this.tcwxm_1 = tcwxm_1;
	}
	public Integer getCyry() {
		return cyry;
	}
	public void setCyry(Integer cyry) {
		this.cyry = cyry;
	}
	public Integer getQtcyry() {
		return qtcyry;
	}
	public void setQtcyry(Integer qtcyry) {
		this.qtcyry = qtcyry;
	}
	public Double getCyryldbc() {
		return cyryldbc;
	}
	public void setCyryldbc(Double cyryldbc) {
		this.cyryldbc = cyryldbc;
	}
	public Double getQtcyldbc() {
		return qtcyldbc;
	}
	public void setQtcyldbc(Double qtcyldbc) {
		this.qtcyldbc = qtcyldbc;
	}
	public Integer getLxry() {
		return lxry;
	}
	public void setLxry(Integer lxry) {
		this.lxry = lxry;
	}
	
	
	
	
	
	
}
