package com.task.Server;

import java.util.List;

import com.task.entity.InsuranceGold;

/**
 * 五险一金百分比 Server表
 * 
 * @author 刘培
 * 
 */
public interface InsuranceGoldServer {

	public InsuranceGold findInsuranceGoldBylc(String localOrField,
			String cityOrCountryside, String personClass);// 查找百分比通过户籍

	public boolean updateWSByIG(); // 更新工资模版信息

	public boolean addInsuranceGold(InsuranceGold insuranceGold);// 添加百分比

	public boolean CompareValidityDate(InsuranceGold insuranceGold); // 有效时间比较

	public Object[] findAllInsuranceGold(String cpage, int pageSize);// 查询所有保险缴纳比例(分页)

	public boolean delInsuranceGold(InsuranceGold insuranceGold); // 删除保险缴纳比例

	public InsuranceGold findInsuranceGoldById(int id); // 根据Id查询保险缴纳比例

	/***
	 * 查询当前时间有效期内的比例数据
	 * 
	 * @return
	 */
	List findTimeIns();

}
