package com.task.entity.caiwu.baobiao;

import java.io.Serializable;

/**
 * wxf 王晓飞 
 * 经济增加值计算表 ： (ta_baobiao_Evact)
 *
 */
public class Evact implements Serializable{
	
	private Integer id;
	/**
	 * 经济增加值
	 */
	private Double jjzjz1;//经济增加值
	private Double jjzjz2;//经济增加值
	/*一、税后净营业利润*/
	private Double  shjyylr1;//税后净营业利润
	private Double  shjyylr2;//税后净营业利润
	private Double jlr1;//净利润
	private Double jlr2;//净利润
	private Double lxzc1;//利息支出
	private Double lxzc2;//利息支出
	private Double yjkfftze1;//研究开发费调整额
	private Double yjkfftze2;//研究开发费调整额
	private Double yjykff1;//其中：研究与开发费
	private Double yjykff2;//其中：研究与开发费
	private Double wxzcyjkf1; //当期确认为无形资产的研究开发支出
	private Double wxzcyjkf2; //当期确认为无形资产的研究开发支出
	private Double sdsL1;//所得税率（25%）
	private Double sdsL2;//所得税率（25%）
	/*二、资本占用*/
	private Double zbzy1;//资本占用
	private Double zbzy2;//资本占用
	
}
