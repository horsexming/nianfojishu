package com.task.entity.sop;

import java.util.List;

/**
 * 设变关联连零件(ta_sop_w_ProcardTemplateAboutsb)
 * @author txb
 *
 */
public class ProcardTemplateAboutsb  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer sbApplyId;//设变单id
	private String sbNumber;//设变单号
	private String procardStyle;//零件类型
	private String productStyle;//生产类型
	private String markId;//件号
	private String proName;//名称
	private String banben;//版本
	private Integer banci;
	private String wgType;//物料类别
	private String kgliao;//客供属性
	private String specification;//规格
	private String unit;// 单位
	private String tuhao;//图号
	private Float yongliao;//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
	
	private Float zaizhiCount;//在制数量
	private Float zaituCount;//在途数量
	private Float zaikuCount;//库存数量
	private Float daizhiCount;//呆滞数量
	
	//页面显示
	private String zaizhicltype;//在制处理方案
	private String zaitucltype;//在途处理方案
	private String zaikucltype;//库存处理方案
	private String daizhicltype;//呆滞处理方案
	
	private List<ProcardTemplateAboutsbDetail> ptasbdList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSbApplyId() {
		return sbApplyId;
	}
	public void setSbApplyId(Integer sbApplyId) {
		this.sbApplyId = sbApplyId;
	}
	public String getSbNumber() {
		return sbNumber;
	}
	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}
	public String getProductStyle() {
		return productStyle;
	}
	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getBanben() {
		return banben;
	}
	public void setBanben(String banben) {
		this.banben = banben;
	}
	
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
	}
	public String getWgType() {
		return wgType;
	}
	public void setWgType(String wgType) {
		this.wgType = wgType;
	}
	public String getKgliao() {
		return kgliao;
	}
	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getTuhao() {
		return tuhao;
	}
	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}
	public Float getYongliao() {
		return yongliao;
	}
	public void setYongliao(Float yongliao) {
		this.yongliao = yongliao;
	}
	public List<ProcardTemplateAboutsbDetail> getPtasbdList() {
		return ptasbdList;
	}
	public void setPtasbdList(List<ProcardTemplateAboutsbDetail> ptasbdList) {
		this.ptasbdList = ptasbdList;
	}
	public String getProcardStyle() {
		return procardStyle;
	}
	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}
	public Float getZaizhiCount() {
		return zaizhiCount;
	}
	public void setZaizhiCount(Float zaizhiCount) {
		this.zaizhiCount = zaizhiCount;
	}
	public Float getZaituCount() {
		return zaituCount;
	}
	public void setZaituCount(Float zaituCount) {
		this.zaituCount = zaituCount;
	}
	public Float getZaikuCount() {
		return zaikuCount;
	}
	public void setZaikuCount(Float zaikuCount) {
		this.zaikuCount = zaikuCount;
	}
	public Float getDaizhiCount() {
		return daizhiCount;
	}
	public void setDaizhiCount(Float daizhiCount) {
		this.daizhiCount = daizhiCount;
	}
	public String getZaizhicltype() {
		return zaizhicltype;
	}
	public void setZaizhicltype(String zaizhicltype) {
		this.zaizhicltype = zaizhicltype;
	}
	public String getZaitucltype() {
		return zaitucltype;
	}
	public void setZaitucltype(String zaitucltype) {
		this.zaitucltype = zaitucltype;
	}
	public String getZaikucltype() {
		return zaikucltype;
	}
	public void setZaikucltype(String zaikucltype) {
		this.zaikucltype = zaikucltype;
	}
	public String getDaizhicltype() {
		return daizhicltype;
	}
	public void setDaizhicltype(String daizhicltype) {
		this.daizhicltype = daizhicltype;
	}
	
	
	
	
}
