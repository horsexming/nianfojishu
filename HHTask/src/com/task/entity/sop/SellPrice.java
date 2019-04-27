package com.task.entity.sop;
/**
 * 
 * table   ta_sellprice
 * @author 聂威
 *
 */
public class SellPrice implements java.io.Serializable{
	private static final long serialVersionUID =1L;
    private int id;
    private String partNumber;// 件号
    private String name;// 名称
    private String hair;//发住地
    private String clientManagement;// 客户
    private String starttime;// 开始时间
	private String endtime;// 结束时间
	private String addtime;//添加时间
	private String contractNumber;// 合同编号
	private String chargePerson;// 负责人
	private Double hsPrice;// 含税价格
	private Double bhsPrice;// 不含税价格
	private String rmarks;// 备注
	private String fileNumber;// 档案号
	private String attachmentName;// 附件名称
	private String status;// 状态(已存档，未存档)
	private String cdStatus;// 存档状态('yes','no')
	private String productCategory;// 产品类别
	private String type;// 型别
	private String produceType;// 生产类型
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getClientManagement() {
		return clientManagement;
	}
	public void setClientManagement(String clientManagement) {
		this.clientManagement = clientManagement;
	}
	public String getHair() {
		return hair;
	}
	public void setHair(String hair) {
		this.hair = hair;
	}

	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}
	public String getStatus() {
		return status;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCdStatus() {
		return cdStatus;
	}
	public void setCdStatus(String cdStatus) {
		this.cdStatus = cdStatus;
	}
	public String getChargePerson() {
		return chargePerson;
	}
	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}
	public Double getHsPrice() {
		return hsPrice;
	}
	public void setHsPrice(Double hsPrice) {
		this.hsPrice = hsPrice;
	}
	public Double getBhsPrice() {
		return bhsPrice;
	}
	public void setBhsPrice(Double bhsPrice) {
		this.bhsPrice = bhsPrice;
	}
	public String getRmarks() {
		return rmarks;
	}
	public void setRmarks(String rmarks) {
		this.rmarks = rmarks;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProduceType() {
		return produceType;
	}
	public void setProduceType(String produceType) {
		this.produceType = produceType;
	}
	
}