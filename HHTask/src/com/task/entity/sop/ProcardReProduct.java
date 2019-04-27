package com.task.entity.sop;
/**
 * 生产件返修单(ta_sop_w_ProcardReProduct)
 * @author txb
 *
 */
public class ProcardReProduct  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;//
	private String sbNumber;//设变单号
	private String fxNumber;//返修单号
	private String source;//来源
	private Integer ptbbApplyId;//设变申请id
	private Integer procardId;//生产件id
	private String markId;//件号
	private String tuhao;//图号
	private String specification;//规格
	private String unit;//单位
	private String wgType;//物料类别
	private String proName;//名称
	private String banbenNumber;//版本号
	private Integer banci;//版次
	private String procardStyle;//零件类型
	private String kgliao;//供料属性
	private String selfCard;//生产批次
	private String orderNumber;//订单号
	private Float fxCount;//报废数量
	private Integer processId;//工序Id(返修单上挂工序，不是返修工序)
	private Integer processNo;//工序号
	private String processName;//工序名称
	private String rootMarkId;//总成件号
	private String rootSelfCard;//总成批次
	private String status;//状态 待处理,文件上传,生产,完成
	private String addTime;//添加时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getPtbbApplyId() {
		return ptbbApplyId;
	}
	public void setPtbbApplyId(Integer ptbbApplyId) {
		this.ptbbApplyId = ptbbApplyId;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
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
	public String getBanbenNumber() {
		return banbenNumber;
	}
	public void setBanbenNumber(String banbenNumber) {
		this.banbenNumber = banbenNumber;
	}
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
	}
	public String getProcardStyle() {
		return procardStyle;
	}
	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}
	public String getKgliao() {
		return kgliao;
	}
	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public Float getFxCount() {
		return fxCount;
	}
	public void setFxCount(Float fxCount) {
		this.fxCount = fxCount;
	}
	public Integer getProcessNo() {
		return processNo;
	}
	public void setProcessNo(Integer processNo) {
		this.processNo = processNo;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getRootMarkId() {
		return rootMarkId;
	}
	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}
	public String getRootSelfCard() {
		return rootSelfCard;
	}
	public void setRootSelfCard(String rootSelfCard) {
		this.rootSelfCard = rootSelfCard;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public String getTuhao() {
		return tuhao;
	}
	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
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
	public String getWgType() {
		return wgType;
	}
	public void setWgType(String wgType) {
		this.wgType = wgType;
	}
	public String getFxNumber() {
		return fxNumber;
	}
	public void setFxNumber(String fxNumber) {
		this.fxNumber = fxNumber;
	}
	public String getSbNumber() {
		return sbNumber;
	}
	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}
	
	
}