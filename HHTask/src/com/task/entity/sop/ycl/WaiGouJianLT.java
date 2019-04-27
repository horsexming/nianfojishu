package com.task.entity.sop.ycl;

import java.io.Serializable;

/**
 * 
 * @author 王晓飞  外购件L/T (ta_WaiGouJianLT)
 *	用于下单时关联的天数
 */
public class WaiGouJianLT implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer ltdengji;//L/T 等级
	private Integer ltuse;//LT使用
	private Integer ltadd;//LT+?
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLtdengji() {
		return ltdengji;
	}
	public void setLtdengji(Integer ltdengji) {
		this.ltdengji = ltdengji;
	}
	public Integer getLtuse() {
		return ltuse;
	}
	public void setLtuse(Integer ltuse) {
		this.ltuse = ltuse;
	}
	public Integer getLtadd() {
		return ltadd;
	}
	public void setLtadd(Integer ltadd) {
		this.ltadd = ltadd;
	}
	
	
	

}
