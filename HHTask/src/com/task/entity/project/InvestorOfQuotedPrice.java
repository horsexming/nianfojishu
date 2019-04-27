package com.task.entity.project;
/**
 * 投资名单 ta_pro_InvestorOfQuotedPrice
 * @author txb
 *
 */
public class InvestorOfQuotedPrice implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;//
	private Integer investorId;
	private Integer qpId;
	private String role;//成员,组长
	private String addTime;
	private Integer zzId;//本人推选的组长Id(投资人和零件中间表Id即此表对应的id)
	private Integer selectedCount;//被选中的次数
	private Integer zanCount;//被点赞次数（页面使用）
	private Integer tocaoCount;//被吐槽次数(页面使用)
	private String name;//名字（页面使用）
	private String code;//工号（页面使用）
	private String dept;//部门（页面使用）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}
	public Integer getQpId() {
		return qpId;
	}
	public void setQpId(Integer qpId) {
		this.qpId = qpId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getZzId() {
		return zzId;
	}
	public void setZzId(Integer zzId) {
		this.zzId = zzId;
	}
	public Integer getSelectedCount() {
		return selectedCount;
	}
	public void setSelectedCount(Integer selectedCount) {
		this.selectedCount = selectedCount;
	}
	public Integer getZanCount() {
		return zanCount;
	}
	public void setZanCount(Integer zanCount) {
		this.zanCount = zanCount;
	}
	public Integer getTocaoCount() {
		return tocaoCount;
	}
	public void setTocaoCount(Integer tocaoCount) {
		this.tocaoCount = tocaoCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
	
	
	
	
}
