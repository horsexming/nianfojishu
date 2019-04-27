package com.task.entity.jxkh;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 分配情况表:(ta_FenPeiQingKuang)
 * @author wxf
 *各种数据汇总计算。
 */
public class FenPeiQingKuang implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dept;//部门
	private Double fenpeizjx;//可分配总绩效(元) (salesAmount>=5000万?所有对应的各车间提取金额总和,即:WorkShopTiQu:buMenTqMoney:0)
	private String months;//月份
	
	
	/**
	 * 参数
	 */
	private Double k;//分配系数(总经办提供)
	private Integer c;//定岗人数(可由 ZbSjZk:sjrsmubiao 获取)
	private Integer b;//实际人数(可由 ZbSjZk:sjrszhibiao 获取)
	
	private Double d1;//产值系数（同 部门长考核）
	private Double d2;//结构比(目标达成1.1分，未达成 0.9； <=目标为达成)
	private Double d3;//委外比(目标达成1.1分，未达成 0.9； <=目标为达成)
	private Double z;//制造评价(由 ZhiZaoPingJia:xiShu获取)
	private Double salesAmount;//本月销售额;//（由 ZbSjZk:zczzhibiao(总经办部门) 获取）
	private Set<WorkShopTiQu> wstq;//各部门从各车间提取情况表;
	private List<WorkShopTiQu> wstqList;//各部门从各车间提取情况表;
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
	public Double getFenpeizjx() {
		return fenpeizjx;
	}
	public void setFenpeizjx(Double fenpeizjx) {
		this.fenpeizjx = fenpeizjx;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public Double getK() {
		return k;
	}
	public void setK(Double k) {
		this.k = k;
	}
	public Integer getC() {
		return c;
	}
	public void setC(Integer c) {
		this.c = c;
	}
	public Integer getB() {
		return b;
	}
	public void setB(Integer b) {
		this.b = b;
	}
	public Double getD1() {
		return d1;
	}
	public void setD1(Double d1) {
		this.d1 = d1;
	}
	public Double getD2() {
		return d2;
	}
	public void setD2(Double d2) {
		this.d2 = d2;
	}
	public Double getD3() {
		return d3;
	}
	public void setD3(Double d3) {
		this.d3 = d3;
	}
	public Double getZ() {
		return z;
	}
	public void setZ(Double z) {
		this.z = z;
	}
	public Double getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}
	public Set<WorkShopTiQu> getWstq() {
		return wstq;
	}
	public void setWstq(Set<WorkShopTiQu> wstq) {
		this.wstq = wstq;
	}
	public List<WorkShopTiQu> getWstqList() {
		return wstqList;
	}
	public void setWstqList(List<WorkShopTiQu> wstqList) {
		this.wstqList = wstqList;
	}
	
	
	
}
