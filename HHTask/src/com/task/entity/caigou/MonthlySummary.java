package com.task.entity.caigou;

import java.io.Serializable;

/***
 * 月度汇总表(表名:ta_caigou_MonthlySummary)
 * 
 * @author liupei
 * 
 */
public class MonthlySummary implements Serializable{

	private Integer id;
	private String name;// 名称(到货及时率)
	private String number;// 编号(dh,)
	private String month;// 月份
	private Float completionRate;// 完成率
	private String addTime;// 添加时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Float getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(Float completionRate) {
		this.completionRate = completionRate;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}
