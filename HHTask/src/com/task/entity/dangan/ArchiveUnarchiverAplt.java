package com.task.entity.dangan;

import java.io.Serializable;



/**
 * 存取档案申请
 * @author Li_Cong 2016-03-08
 * 表名 ta_da_ArchiveUnarchiverAplt
 * 申请档案=>生成进出验证码=>记录进出时间
 */
public class ArchiveUnarchiverAplt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cqType;//存取类型
	private String daNum;//档案编号
	private String daName;//档案名称
	private Integer daId;//档案Id
	private Integer sealId;//印章申请Id
	private Integer receipId;//付款申请明细Id
	private String inCodes;//进门验证码
	private String openTime;//打开时间
	private String cardId;//卡号
	private String useType;//使用状态(未使用/已失效)
	private String fileName;//档案文件名
	private Integer daGuiId;//档案柜ID
	private String daGuihao;//档案柜编号
	private String daGuiposition;//档案柜号（对比的开门指令）
	private DangAn dangan;//对应档案进出申请
	private String addTime;//添加时间
	private String shixiaoTime;//失效时间
	private String ace_Ip;// 设备Ip


//	get set 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReceipId() {
		return receipId;
	}
	public void setReceipId(Integer receipId) {
		this.receipId = receipId;
	}
	public Integer getSealId() {
		return sealId;
	}
	public void setSealId(Integer sealId) {
		this.sealId = sealId;
	}
	public Integer getDaId() {
		return daId;
	}
	public void setDaId(Integer daId) {
		this.daId = daId;
	}
	public String getAce_Ip() {
		return ace_Ip;
	}
	public void setAce_Ip(String aceIp) {
		ace_Ip = aceIp;
	}
	public String getCqType() {
		return cqType;
	}
	public void setCqType(String cqType) {
		this.cqType = cqType;
	}
	public String getDaNum() {
		return daNum;
	}
	public void setDaNum(String daNum) {
		this.daNum = daNum;
	}
	public String getDaName() {
		return daName;
	}
	public void setDaName(String daName) {
		this.daName = daName;
	}
	public String getInCodes() {
		return inCodes;
	}
	public void setInCodes(String inCodes) {
		this.inCodes = inCodes;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public DangAn getDangan() {
		return dangan;
	}
	public void setDangan(DangAn dangan) {
		this.dangan = dangan;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDaGuiposition() {
		return daGuiposition;
	}
	public void setDaGuiposition(String daGuiposition) {
		this.daGuiposition = daGuiposition;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getDaGuihao() {
		return daGuihao;
	}
	public void setDaGuihao(String daGuihao) {
		this.daGuihao = daGuihao;
	}
	public Integer getDaGuiId() {
		return daGuiId;
	}
	public void setDaGuiId(Integer daGuiId) {
		this.daGuiId = daGuiId;
	}
	public String getShixiaoTime() {
		return shixiaoTime;
	}
	public void setShixiaoTime(String shixiaoTime) {
		this.shixiaoTime = shixiaoTime;
	}

	
}
