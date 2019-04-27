package com.task.entity.sop;

import java.util.List;

/**
 * 模板版本升级申请明细（表：ta_ProcardTemplateBanBen）
 * @author txb
 *
 */
public class ProcardTemplateBanBen  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer ptId;//Bom模板id
	private Integer fptId;//Bom模板父id
	private String markId;//件号
	private String newmarkId;//件号
	private String rootMarkId;
	private String ywMarkId;//业务件号（总成对外销售使用）
	private String proName;//名称
	private String unit;// 单位(自制)
	private String procardStyle;// 零件类型(总成，组合，外购，自制)
	private String productStyle;// 产品类型(试制，批产)
	private String banBenNumber;//原版本号
	private String newBanBenNumber;//升级版本号
	private Integer banci;//版次
	private Integer newbanci;//新版次
	private String uptype;//版本升级,设变
	private String changeProcess;//是否修改工序
	private String changeTz;//是否修改图纸
	private String xuhao;//序号
	private Float zaizhiCount;//在制品数量
	private Float zaituCount;//在途数量
	private Float kucunCount;//库存数量
	private String addTime;//添加时间
	private String kgliao;//供货属性
	private String remark;//说明
	private String bzStatus;//编制状态（页面显示）
	private String singleSb;//单独设变（是，否 默认否）
	
	
	private String bianzhiName;// 编制人
	private Integer bianzhiId;// 编制ID

	private String jiaoduiName;// 校对人
	private Integer jiaoduiId;// 校对ID

	private String shenheName;// 审核人
	private Integer shenheId;// 审核ID

	private String pizhunName;// 批准人
	private Integer pizhunId;// 批准ID
	

	private List<ProcardTemplateAboutsb> ptasbList;//设变涉及零件主表(页面显示)
	private List<ProcardTemplateAboutsbDetail> ptasbdList;//设变涉及零件明细表(页面显示)
	private ProcardTemplateBanBenApply procardTemplateBanBenApply;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPtId() {
		return ptId;
	}
	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getYwMarkId() {
		return ywMarkId;
	}
	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProcardStyle() {
		return procardStyle;
	}
	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}
	public String getProductStyle() {
		return productStyle;
	}
	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}
	public String getBanBenNumber() {
		return banBenNumber;
	}
	public void setBanBenNumber(String banBenNumber) {
		this.banBenNumber = banBenNumber;
	}
	public String getNewBanBenNumber() {
		return newBanBenNumber;
	}
	public void setNewBanBenNumber(String newBanBenNumber) {
		this.newBanBenNumber = newBanBenNumber;
	}
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
	}
	public Integer getNewbanci() {
		return newbanci;
	}
	public void setNewbanci(Integer newbanci) {
		this.newbanci = newbanci;
	}
	public ProcardTemplateBanBenApply getProcardTemplateBanBenApply() {
		return procardTemplateBanBenApply;
	}
	public void setProcardTemplateBanBenApply(
			ProcardTemplateBanBenApply procardTemplateBanBenApply) {
		this.procardTemplateBanBenApply = procardTemplateBanBenApply;
	}
	public String getRootMarkId() {
		return rootMarkId;
	}
	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}
	public String getUptype() {
		return uptype;
	}
	public void setUptype(String uptype) {
		this.uptype = uptype;
	}
	public String getChangeProcess() {
		return changeProcess;
	}
	public void setChangeProcess(String changeProcess) {
		this.changeProcess = changeProcess;
	}
	public String getChangeTz() {
		return changeTz;
	}
	public void setChangeTz(String changeTz) {
		this.changeTz = changeTz;
	}
	public String getXuhao() {
		return xuhao;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
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
	public Float getKucunCount() {
		return kucunCount;
	}
	public void setKucunCount(Float kucunCount) {
		this.kucunCount = kucunCount;
	}
	public String getKgliao() {
		return kgliao;
	}
	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBzStatus() {
		return bzStatus;
	}
	public void setBzStatus(String bzStatus) {
		this.bzStatus = bzStatus;
	}
	public Integer getFptId() {
		return fptId;
	}
	public void setFptId(Integer fptId) {
		this.fptId = fptId;
	}
	public String getBianzhiName() {
		return bianzhiName;
	}
	public void setBianzhiName(String bianzhiName) {
		this.bianzhiName = bianzhiName;
	}
	public Integer getBianzhiId() {
		return bianzhiId;
	}
	public void setBianzhiId(Integer bianzhiId) {
		this.bianzhiId = bianzhiId;
	}
	public String getJiaoduiName() {
		return jiaoduiName;
	}
	public void setJiaoduiName(String jiaoduiName) {
		this.jiaoduiName = jiaoduiName;
	}
	public Integer getJiaoduiId() {
		return jiaoduiId;
	}
	public void setJiaoduiId(Integer jiaoduiId) {
		this.jiaoduiId = jiaoduiId;
	}
	public String getShenheName() {
		return shenheName;
	}
	public void setShenheName(String shenheName) {
		this.shenheName = shenheName;
	}
	public Integer getShenheId() {
		return shenheId;
	}
	public void setShenheId(Integer shenheId) {
		this.shenheId = shenheId;
	}
	public String getPizhunName() {
		return pizhunName;
	}
	public void setPizhunName(String pizhunName) {
		this.pizhunName = pizhunName;
	}
	public Integer getPizhunId() {
		return pizhunId;
	}
	public void setPizhunId(Integer pizhunId) {
		this.pizhunId = pizhunId;
	}
	public String getSingleSb() {
		return singleSb;
	}
	public void setSingleSb(String singleSb) {
		this.singleSb = singleSb;
	}
	public String getNewmarkId() {
		return newmarkId;
	}
	public void setNewmarkId(String newmarkId) {
		this.newmarkId = newmarkId;
	}
	public List<ProcardTemplateAboutsbDetail> getPtasbdList() {
		return ptasbdList;
	}
	public void setPtasbdList(List<ProcardTemplateAboutsbDetail> ptasbdList) {
		this.ptasbdList = ptasbdList;
	}
	public List<ProcardTemplateAboutsb> getPtasbList() {
		return ptasbList;
	}
	public void setPtasbList(List<ProcardTemplateAboutsb> ptasbList) {
		this.ptasbList = ptasbList;
	}
	
	
	
	
}