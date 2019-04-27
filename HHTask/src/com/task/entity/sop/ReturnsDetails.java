package com.task.entity.sop;

import com.task.util.FieldMeta;

/**
 * 
 * @author 王晓飞
 * 退货明细:(ta_sop_w_ReturnsDetails)
 * 和退货单多对一关系；
 *ReturnSingle
 */
public class ReturnsDetails  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	@FieldMeta(name="送货单号")
	private String shOrderNum;//送货单号
	@FieldMeta(name="采购订单号")
	private String cgOrderNum;//采购订单号
	private Integer gysId;//供应商Id
	@FieldMeta(name="供应商名称")
	private String gysName;//供应商名称
	private String gysdizhi;//供应商地址
	private String gysUser;//供应商联系人
	private String gysPhone;//供应商电话
	@FieldMeta(name="件号")
	private String markId;//件号
	@FieldMeta(name="零件名称")
	private String proName;//零件名称
	private String tuhao;//图号
	private String kgliao;//供料属性
	@FieldMeta(name="物料类别")
	private String wgType;//物料类别
	@FieldMeta(name="规格")
	private String specification;// 规格
	private String unit;// 单位
	private String banben;//版本
	private Float hsPrice;// 含税单价
	private Float price;// 不含税单价
	private Double taxprice; // 税率
	private Integer priceId;// 价格id
	@FieldMeta(name="退货数量")
	private Float thNumber;//退货数量
	@FieldMeta(name="检验批次")
	private String examineLot;//检验批次
	private String bldescribe;//检验不良描述
	private Integer dpId;//不良处单Id
	private Integer wgddId;//送货理单Id
	private String status;//（待领、已处理）\
	private String clResult;//处理结果
	private ReturnSingle returnSingle;//退货单;(多对一)
	private String epstatus;//审批状态
	private Integer epId;//
	private String approvalTime;//批准时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getGysName() {
		return gysName;
	}
	public void setGysName(String gysName) {
		this.gysName = gysName;
	}
	public String getGysdizhi() {
		return gysdizhi;
	}
	public void setGysdizhi(String gysdizhi) {
		this.gysdizhi = gysdizhi;
	}
	public String getGysUser() {
		return gysUser;
	}
	public void setGysUser(String gysUser) {
		this.gysUser = gysUser;
	}
	public String getGysPhone() {
		return gysPhone;
	}
	public void setGysPhone(String gysPhone) {
		this.gysPhone = gysPhone;
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
	public String getTuhao() {
		return tuhao;
	}
	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}
	public String getKgliao() {
		return kgliao;
	}
	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}
	public String getWgType() {
		return wgType;
	}
	public void setWgType(String wgType) {
		this.wgType = wgType;
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
	public Float getHsPrice() {
		return hsPrice;
	}
	public void setHsPrice(Float hsPrice) {
		this.hsPrice = hsPrice;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Double getTaxprice() {
		return taxprice;
	}
	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}
	public Integer getPriceId() {
		return priceId;
	}
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	public Float getThNumber() {
		return thNumber;
	}
	public void setThNumber(Float thNumber) {
		this.thNumber = thNumber;
	}
	public String getExamineLot() {
		return examineLot;
	}
	public void setExamineLot(String examineLot) {
		this.examineLot = examineLot;
	}
	public Integer getDpId() {
		return dpId;
	}
	public void setDpId(Integer dpId) {
		this.dpId = dpId;
	}
	public Integer getWgddId() {
		return wgddId;
	}
	public void setWgddId(Integer wgddId) {
		this.wgddId = wgddId;
	}
	public ReturnSingle getReturnSingle() {
		return returnSingle;
	}
	public void setReturnSingle(ReturnSingle returnSingle) {
		this.returnSingle = returnSingle;
	}
	public Integer getGysId() {
		return gysId;
	}
	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShOrderNum() {
		return shOrderNum;
	}
	public void setShOrderNum(String shOrderNum) {
		this.shOrderNum = shOrderNum;
	}
	public String getCgOrderNum() {
		return cgOrderNum;
	}
	public void setCgOrderNum(String cgOrderNum) {
		this.cgOrderNum = cgOrderNum;
	}
	public String getBanben() {
		return banben;
	}
	public void setBanben(String banben) {
		this.banben = banben;
	}
	public String getBldescribe() {
		return bldescribe;
	}
	public void setBldescribe(String bldescribe) {
		this.bldescribe = bldescribe;
	}
	public String getClResult() {
		return clResult;
	}
	public void setClResult(String clResult) {
		this.clResult = clResult;
	}
	public String getEpstatus() {
		return epstatus;
	}
	public void setEpstatus(String epstatus) {
		this.epstatus = epstatus;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	
	
	
	
}
