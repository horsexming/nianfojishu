package com.task.entity.jxkh;

import java.io.Serializable;

/**
 * 
 * @author 王晓飞 收入情况表:(ta_Income)
 *
 */

public class InCome implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dept;//部门
	private String name;//姓名
	private String post;//职务
	private Double nowsalary;//现薪资
	private Double deptaverage;//部门人均
	private Double postfactor;//岗位系数
	private Double jixiao;//绩效考核
	private Double heji;//合计
	private Double wagesCoefficient;//工资系数
	private Double jieGouBi;//结构比
	private Double weiwaibi;//委外比
	private Double d_value;//差值
	private Double zzIncome;//最终收入
	private Double shouruHeJi;//收入合计
	
	
	private String months;//
	private String addUsersName;//
	private String addTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Double getNowsalary() {
		return nowsalary;
	}
	public void setNowsalary(Double nowsalary) {
		this.nowsalary = nowsalary;
	}
	public Double getDeptaverage() {
		return deptaverage;
	}
	public void setDeptaverage(Double deptaverage) {
		this.deptaverage = deptaverage;
	}
	public Double getPostfactor() {
		return postfactor;
	}
	public void setPostfactor(Double postfactor) {
		this.postfactor = postfactor;
	}
	public Double getJixiao() {
		return jixiao;
	}
	public void setJixiao(Double jixiao) {
		this.jixiao = jixiao;
	}
	public Double getHeji() {
		return heji;
	}
	public void setHeji(Double heji) {
		this.heji = heji;
	}
	public Double getWagesCoefficient() {
		return wagesCoefficient;
	}
	public void setWagesCoefficient(Double wagesCoefficient) {
		this.wagesCoefficient = wagesCoefficient;
	}
	public Double getJieGouBi() {
		return jieGouBi;
	}
	public void setJieGouBi(Double jieGouBi) {
		this.jieGouBi = jieGouBi;
	}
	public Double getWeiwaibi() {
		return weiwaibi;
	}
	public void setWeiwaibi(Double weiwaibi) {
		this.weiwaibi = weiwaibi;
	}
	public Double getD_value() {
		return d_value;
	}
	public void setD_value(Double dValue) {
		d_value = dValue;
	}
	public Double getZzIncome() {
		return zzIncome;
	}
	public void setZzIncome(Double zzIncome) {
		this.zzIncome = zzIncome;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getAddUsersName() {
		return addUsersName;
	}
	public void setAddUsersName(String addUsersName) {
		this.addUsersName = addUsersName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Double getShouruHeJi() {
		return shouruHeJi;
	}
	public void setShouruHeJi(Double shouruHeJi) {
		this.shouruHeJi = shouruHeJi;
	}
	

	

}
