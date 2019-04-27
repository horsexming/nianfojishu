package com.tast.entity.zhaobiao;

import java.io.Serializable;
import java.util.Set;

import com.task.entity.fin.budget.DeptMonthBudget;

/*
 * 招标信息表
 */

public class ZhaobiaoXi implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer t1;		// 模版id
	private String t2;		// 数量
	private String t3;		// 单位
	private String t4;		// 预计金额
	private String t5;		// 规格要求
	private String t6;   	// 物料简介
	private String t7;		//状态
	private String t8;		//交付时间
	private Integer t9;         // 预算id
	private Integer t10;		//zhaobiao的ID
	private Float t11;		// 财务选择回款方式
	
	private Float lilv;//年利率
	private Set<Zhtoubiao> zhtoubiaos;
	
	private Integer jihuaId;
	
	private  Float  chengzhong;//实际称重
	private  Float  danjia;//投标单价
	private  Float  zongjine;//该材料的总金额（实际称重*单价）
	
	
	// private Zhaobiao zhaobiao;
	private Zhmoban zhmoban;
	private DeptMonthBudget deptMonthBudget;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getT1() {
		return t1;
	}

	public void setT1(Integer t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}

	public String getT4() {
		return t4;
	}

	public void setT4(String t4) {
		this.t4 = t4;
	}

	public String getT5() {
		return t5;
	}

	public void setT5(String t5) {
		this.t5 = t5;
	}

	public String getT6() {
		return t6;
	}

	public void setT6(String t6) {
		this.t6 = t6;
	}

	public String getT7() {
		return t7;
	}

	public void setT7(String t7) {
		this.t7 = t7;
	}

	public String getT8() {
		return t8;
	}

	public void setT8(String t8) {
		this.t8 = t8;
	}

	public Integer getT9() {
		return t9;
	}

	public void setT9(Integer t9) {
		this.t9 = t9;
	}

	public Integer getT10() {
		return t10;
	}

	public void setT10(Integer t10) {
		this.t10 = t10;
	}

	public Zhmoban getZhmoban() {
		return zhmoban;
	}

	public void setZhmoban(Zhmoban zhmoban) {
		this.zhmoban = zhmoban;
	}

	public DeptMonthBudget getDeptMonthBudget() {
		return deptMonthBudget;
	}

	public void setDeptMonthBudget(DeptMonthBudget deptMonthBudget) {
		this.deptMonthBudget = deptMonthBudget;
	}

	public Set<Zhtoubiao> getZhtoubiaos() {
		return zhtoubiaos;
	}

	public void setZhtoubiaos(Set<Zhtoubiao> zhtoubiaos) {
		this.zhtoubiaos = zhtoubiaos;
	}

	

	public Float getLilv() {
		return lilv;
	}

	public void setLilv(Float lilv) {
		this.lilv = lilv;
	}

	public Float getT11() {
		return t11;
	}

	public void setT11(Float t11) {
		this.t11 = t11;
	}

	public Integer getJihuaId() {
		return jihuaId;
	}

	public void setJihuaId(Integer jihuaId) {
		this.jihuaId = jihuaId;
	}

	public Float getChengzhong() {
		return chengzhong;
	}

	public void setChengzhong(Float chengzhong) {
		this.chengzhong = chengzhong;
	}

	public Float getDanjia() {
		return danjia;
	}

	public void setDanjia(Float danjia) {
		this.danjia = danjia;
	}

	public Float getZongjine() {
		return zongjine;
	}

	public void setZongjine(Float zongjine) {
		this.zongjine = zongjine;
	}

}
