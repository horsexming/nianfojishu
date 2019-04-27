package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Integral;
import com.task.entity.Integralsource;
import com.task.entity.Price;
import com.task.entity.XiaoFei;
import com.task.entity.sop.BuHeGePin;
public interface IntegralServerDao {
	/**
	 * 添加积分项
	 */
	public String addIntegral(Integral integral);
	/**
	 * 删除积分项
	 */
	public boolean delIntegral(Integral integral);
	/**
	 * 修改积分项
	 */
	public String updateIntegral(Integral integral);
	/**
	 * 显示所有积分项（分页）
	 */
	public List<Integral> findAllByPage(int pageNo, int pageSize);
	/**
	 * 条件查询
	 */
	public Map<Integer, Object> findIntegralByCondition(Integral integral,int pageNo, int pageSize);
	/**		
	 * 显示所有积分项
	 */
	public List<Integral> fIndAll();
	/**
	 * 根据id查询积分项
	 */
	public Integral findIntegralbyid(Integer id);
	/**
	 * 获得总数量
	 */
	public int getcont();
	/**
	 * 查看个人消费记录;
	 */
	public List<XiaoFei> getXiaoFeis(Integral integral);
	/**
	 * 查看个人增加积分记录;
	 */
	public List<Integralsource> getiIntegralsources(Integral integral);
	/**
	 * 添加积分项;
	 */
//	public String addIntegral(Integer jifen,Integer userId , String laiyuan);
	/**
	 * @throws Exception 
	 * 
	 * 
	 */
	public  void xhjf2(String start,Integer userId) throws Exception;
	/**
	 * 拉黑
	 */
	public boolean laheiIntegral(Integer id);
	
}
