package com.task.entity.shizhi;

import java.io.Serializable;
import java.util.Set;

/**
 * 月奖金额
 * 
 * @表名：ta_sk_BonusLoad
 * @author 唐晓斌
 */
public class BonusShiZhi implements Serializable{
	private static final long serialVersionUID = 1L;
     private Integer id;
     private String month;//月份	
     private Float bonus;//奖金额
     
	public BonusShiZhi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Float getBonus() {
		return bonus;
	}
	public void setBonus(Float bonus) {
		this.bonus = bonus;
	}
	
     
}