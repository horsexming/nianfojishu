package com.task.entity.jxkh;

import java.io.Serializable;

/**
 *二线部门绩效考核
 *部门职责确定表:(ta_DeptUsersDuty)
 *1.确定部门为一线部门还是二线部门，
 *即哪几个部门需从中提取绩效奖金，
 *哪几个部门需计算绩效奖金。
 *2.确定需计算奖金的人员。
 * @author wxf
 */
public class DeptUsersDuty implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String deptName;//部门名称
	private String leader;//部门负责人
	private Integer oneOrTwo;//一线还是二线;(1表示一线提取奖金。2表示，二线计算奖金部门)
	
	//后续关联职级表。4~5 一起排名 6~8一起排名
	private String rank;//职位(经理(3)-->>4(副总监)-->>总监(5)-->>--副厂长(6)-->>厂长(7)--总助(8)-->>副总经理（9）-->>总经理(10))
	private Integer rankNo;//职级(3(经理)-->>4(副总监)-->>5(总监)-->>6(副厂长)-->>7(厂长)-->>8(总助)-->>9（副总经理）-->>总经理(10))
	private Integer isJiaoXiao;//负责人是否需要计算绩效(0表示不需要，1表示需要)
	private Integer isdeptFenPei;//是否需要部门分配;(0表示不需要，1表示需要) 关联部门分配
	private Integer isZzPj;//是否需要制造评价(0表示不需要，1表示需要) 关联制造评价
	private Integer isZzXl;//是否需要填写增长效率(0表示不需要，1表示需要) 关联各车间增长效率
	private Integer isbmzmb;//是否需要部门长目标(0表示不需要，1表示需要) 关联部门长目标
	private Integer isbmzZlh;//是否需要部门长周例会(0表示不需要，1表示需要) 关联部门长周例会
	private Integer isgsx;//是否改善项(0表示不需要，1表示需要) 关联改善项(内容);
	private Integer isWeiWaiJieGou;//是否关联委外结构比（0表示不需要，1表示需要） 关联收入
	
	private Double postCoefficient;// 岗位系数
	
	private String addTime;//添加时间
	private String addUserName;//添加人
	
	
	public DeptUsersDuty() {
		super();
	}
	public DeptUsersDuty(String deptName, String leader, Integer oneOrTwo,
			String rank, Integer rankNo, Integer isJiaoXiao,
			Integer isdeptFenPei, Integer isZzPj, Integer isZzXl) {
		super();
		this.deptName = deptName;
		this.leader = leader;
		this.oneOrTwo = oneOrTwo;
		this.rank = rank;
		this.rankNo = rankNo;
		this.isJiaoXiao = isJiaoXiao;
		this.isdeptFenPei = isdeptFenPei;
		this.isZzPj = isZzPj;
		this.isZzXl = isZzXl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public Integer getOneOrTwo() {
		return oneOrTwo;
	}
	public void setOneOrTwo(Integer oneOrTwo) {
		this.oneOrTwo = oneOrTwo;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Integer getRankNo() {
		return rankNo;
	}
	public void setRankNo(Integer rankNo) {
		this.rankNo = rankNo;
	}
	public Integer getIsJiaoXiao() {
		return isJiaoXiao;
	}
	public void setIsJiaoXiao(Integer isJiaoXiao) {
		this.isJiaoXiao = isJiaoXiao;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public Integer getIsdeptFenPei() {
		return isdeptFenPei;
	}
	public void setIsdeptFenPei(Integer isdeptFenPei) {
		this.isdeptFenPei = isdeptFenPei;
	}
	public Integer getIsZzPj() {
		return isZzPj;
	}
	public void setIsZzPj(Integer isZzPj) {
		this.isZzPj = isZzPj;
	}
	public Integer getIsZzXl() {
		return isZzXl;
	}
	public void setIsZzXl(Integer isZzXl) {
		this.isZzXl = isZzXl;
	}
	public Integer getIsbmzmb() {
		return isbmzmb;
	}
	public void setIsbmzmb(Integer isbmzmb) {
		this.isbmzmb = isbmzmb;
	}
	public Integer getIsbmzZlh() {
		return isbmzZlh;
	}
	public void setIsbmzZlh(Integer isbmzZlh) {
		this.isbmzZlh = isbmzZlh;
	}
	public Integer getIsgsx() {
		return isgsx;
	}
	public void setIsgsx(Integer isgsx) {
		this.isgsx = isgsx;
	}
	public Double getPostCoefficient() {
		return postCoefficient;
	}
	public void setPostCoefficient(Double postCoefficient) {
		this.postCoefficient = postCoefficient;
	}
	public Integer getIsWeiWaiJieGou() {
		return isWeiWaiJieGou;
	}
	public void setIsWeiWaiJieGou(Integer isWeiWaiJieGou) {
		this.isWeiWaiJieGou = isWeiWaiJieGou;
	}
	
	
}