package com.tast.entity.zhaobiao;

import java.io.Serializable;

/*
 * 外购外协件验收频次规定  子项
 *   ta_WaigoujianpinciZi
 */
public class WaigoujianpinciZi implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// id
	private Integer waigoujianpinciId;//Waigoujianpinci的Id
	private Float caigoushuliang1;//采购数量/批(最小值)
	private Float caigoushuliang2;//采购数量/批（最大值）
	private Float  choujian;///一般抽检数量/件
	private Float  erchoujian;//再次抽检数量/件
	private String	ybCode;//(样本代码);
	private String type;//(巡检、null);
	private Float ac;//合格判定数
	private Float re;//不合格判定数
	private Float ac1;//合格判定数(再次检验时)
	private Float re1;//合格判定数(再次检验时)

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWaigoujianpinciId() {
		return waigoujianpinciId;
	}
	public void setWaigoujianpinciId(Integer waigoujianpinciId) {
		this.waigoujianpinciId = waigoujianpinciId;
	}
	public Float getCaigoushuliang1() {
		return caigoushuliang1;
	}
	public void setCaigoushuliang1(Float caigoushuliang1) {
		this.caigoushuliang1 = caigoushuliang1;
	}
	public Float getCaigoushuliang2() {
		return caigoushuliang2;
	}
	public void setCaigoushuliang2(Float caigoushuliang2) {
		this.caigoushuliang2 = caigoushuliang2;
	}
	public Float getChoujian() {
		return choujian;
	}
	public void setChoujian(Float choujian) {
		this.choujian = choujian;
	}
	public Float getErchoujian() {
		return erchoujian;
	}
	public void setErchoujian(Float erchoujian) {
		this.erchoujian = erchoujian;
	}
	public String getYbCode() {
		return ybCode;
	}
	public void setYbCode(String ybCode) {
		this.ybCode = ybCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getAc() {
		return ac;
	}
	public void setAc(Float ac) {
		this.ac = ac;
	}
	public Float getRe() {
		return re;
	}
	public void setRe(Float re) {
		this.re = re;
	}
	public Float getAc1() {
		return ac1;
	}
	public void setAc1(Float ac1) {
		this.ac1 = ac1;
	}
	public Float getRe1() {
		return re1;
	}
	public void setRe1(Float re1) {
		this.re1 = re1;
	}
	 
	

	
	
}
