package com.task.entity.gongyi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.task.entity.sop.ProcessTemplate;
/***
 * 工艺规程（表名:ta_sop_GongyiGuicheng）
 * @author 陈曦
 *
 */
public class GongyiGuicheng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//命名id numb name date type status  remarks 是基本的 其他用
	private Integer id;
	private String numb;//工艺规程编号
	private String xingbie;//型别
	private Integer jianId;//件ID
	private String jianNumb;//件号
	private String jianName;//件名
	private Integer pageTotal;//工几页
	
	private String bianzhiName;//编制
	private Integer bianzhiId;//编制ID
	private Date bianzhiDate;//编制时间
	
	private String jiaoduiName;//校对
	private Integer jiaoduiId;//校对ID
	private Date jiaoduiDate;//校对时间
	
	private String shenheName;//审核
	private Integer shenheId;//审核ID
	private Date shenheDate;//审核时间
	
	private String pizhunName;//批准
	private Integer pizhunId;//批准ID
	private Date pizhunDate;//批准时间
	
	private Date fachuDate;//发出日期
	private String banci;//版次
	private String cuntuNumb;//存图号
	private Integer createUserId;//创建人ID
	private String createUserName;//创建人姓名
	private Date createDate;//创建时间
	
	//会签
	private Integer jiagongId;//加工ID
	private String jiagongName;//加工
	private Date jiagongDate;//加工时间
	private Integer pinzhiId;//品质ID
	private String pinzhiName;//品质
	private Date pinzhiDate;//品质时间
	/**索引号*/
	private String suoyinNumb;
	/**更改单号*/
	private String danNumb;
	/**签名*/
	private String qianming;
	/**日期*/
	private Date qianmingDate;
	
	private Integer epId;//流程ID
	private String status;//审批状态(待编制,已编制,已校对,已审核,已批准)
	
	private Integer score;
	private Integer totalScore;
	/**历史版本的模板记录  版次每变更一次添加一个版本历史记录*/
	private Integer parentId;
	
	/**工艺规程类型  试纸 和批产*/
	private String gongyiGuichengType;
	private List<ProcessTemplate> processList;//工序集合
	/**获取客户端参数*/
	private String params;
	private String qpRootId;//由报价bom生成时的报价bom的rootId
	private String procardStyle;//对应报价系统的卡片类型
	private String isGys;//是否为供应商(null或者no表示不是数字表示供应商id为表ZhUser的id)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	public String getXingbie() {
		return xingbie;
	}
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	public String getJianNumb() {
		return jianNumb;
	}
	public void setJianNumb(String jianNumb) {
		this.jianNumb = jianNumb;
	}
	public String getJianName() {
		return jianName;
	}
	public void setJianName(String jianName) {
		this.jianName = jianName;
	}
	public Integer getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}
	public String getBianzhiName() {
		return bianzhiName;
	}
	public void setBianzhiName(String bianzhiName) {
		this.bianzhiName = bianzhiName;
	}
	public String getJiaoduiName() {
		return jiaoduiName;
	}
	public void setJiaoduiName(String jiaoduiName) {
		this.jiaoduiName = jiaoduiName;
	}
	public String getShenheName() {
		return shenheName;
	}
	public void setShenheName(String shenheName) {
		this.shenheName = shenheName;
	}
	public String getPizhunName() {
		return pizhunName;
	}
	public void setPizhunName(String pizhunName) {
		this.pizhunName = pizhunName;
	}
	public Date getFachuDate() {
		return fachuDate;
	}
	public void setFachuDate(Date fachuDate) {
		this.fachuDate = fachuDate;
	}
	public String getBanci() {
		return banci;
	}
	public void setBanci(String banci) {
		this.banci = banci;
	}
	public String getCuntuNumb() {
		return cuntuNumb;
	}
	public void setCuntuNumb(String cuntuNumb) {
		this.cuntuNumb = cuntuNumb;
	}
	public Integer getJianId() {
		return jianId;
	}
	public void setJianId(Integer jianId) {
		this.jianId = jianId;
	}
	public List<ProcessTemplate> getProcessList() {
		return processList;
	}
	public void setProcessList(List<ProcessTemplate> processList) {
		this.processList = processList;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getSuoyinNumb() {
		return suoyinNumb;
	}
	public void setSuoyinNumb(String suoyinNumb) {
		this.suoyinNumb = suoyinNumb;
	}
	public String getDanNumb() {
		return danNumb;
	}
	public void setDanNumb(String danNumb) {
		this.danNumb = danNumb;
	}
	public String getQianming() {
		return qianming;
	}
	public void setQianming(String qianming) {
		this.qianming = qianming;
	}
	public Date getQianmingDate() {
		return qianmingDate;
	}
	public void setQianmingDate(Date qianmingDate) {
		this.qianmingDate = qianmingDate;
	}
	public Integer getBianzhiId() {
		return bianzhiId;
	}
	public void setBianzhiId(Integer bianzhiId) {
		this.bianzhiId = bianzhiId;
	}
	public Date getBianzhiDate() {
		return bianzhiDate;
	}
	public void setBianzhiDate(Date bianzhiDate) {
		this.bianzhiDate = bianzhiDate;
	}
	public Integer getJiaoduiId() {
		return jiaoduiId;
	}
	public void setJiaoduiId(Integer jiaoduiId) {
		this.jiaoduiId = jiaoduiId;
	}
	public Date getJiaoduiDate() {
		return jiaoduiDate;
	}
	public void setJiaoduiDate(Date jiaoduiDate) {
		this.jiaoduiDate = jiaoduiDate;
	}
	public Integer getShenheId() {
		return shenheId;
	}
	public void setShenheId(Integer shenheId) {
		this.shenheId = shenheId;
	}
	public Date getShenheDate() {
		return shenheDate;
	}
	public void setShenheDate(Date shenheDate) {
		this.shenheDate = shenheDate;
	}
	public Integer getPizhunId() {
		return pizhunId;
	}
	public void setPizhunId(Integer pizhunId) {
		this.pizhunId = pizhunId;
	}
	public Date getPizhunDate() {
		return pizhunDate;
	}
	public void setPizhunDate(Date pizhunDate) {
		this.pizhunDate = pizhunDate;
	}
	public Integer getJiagongId() {
		return jiagongId;
	}
	public void setJiagongId(Integer jiagongId) {
		this.jiagongId = jiagongId;
	}
	public String getJiagongName() {
		return jiagongName;
	}
	public void setJiagongName(String jiagongName) {
		this.jiagongName = jiagongName;
	}
	public Date getJiagongDate() {
		return jiagongDate;
	}
	public void setJiagongDate(Date jiagongDate) {
		this.jiagongDate = jiagongDate;
	}
	public Integer getPinzhiId() {
		return pinzhiId;
	}
	public void setPinzhiId(Integer pinzhiId) {
		this.pinzhiId = pinzhiId;
	}
	public String getPinzhiName() {
		return pinzhiName;
	}
	public void setPinzhiName(String pinzhiName) {
		this.pinzhiName = pinzhiName;
	}
	public Date getPinzhiDate() {
		return pinzhiDate;
	}
	public void setPinzhiDate(Date pinzhiDate) {
		this.pinzhiDate = pinzhiDate;
	}
	public String getParams() {
		if(params!=null){
			return params.replace("\\t", "").replace("\\r","").replace("\\n","").replace("\\f","").replace("\\","");
		}
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getGongyiGuichengType() {
		return gongyiGuichengType;
	}
	public void setGongyiGuichengType(String gongyiGuichengType) {
		this.gongyiGuichengType = gongyiGuichengType;
	}
	public String getQpRootId() {
		return qpRootId;
	}
	public void setQpRootId(String qpRootId) {
		this.qpRootId = qpRootId;
	}
	public String getProcardStyle() {
		return procardStyle;
	}
	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}
	public String getIsGys() {
		return isGys;
	}
	public void setIsGys(String isGys) {
		this.isGys = isGys;
	}
	
}
