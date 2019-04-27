package com.task.Server.bp;

import java.util.List;
import java.util.Map;

import com.task.entity.bp.Detail;
import com.task.entity.bp.Templet;


public interface DetailService {

	Object[] getList(int pageNo, int pageSize) ;

	Detail get(Integer id);

	boolean update(Detail detail, Templet templet);

	/** 增加订单 */
	boolean add(Templet templet, Detail detail);

	List<Templet> getAllRoots();

	boolean delete(Detail detail);

	/**
	 * 跟据一个订单，计算订单所需的配件
	 * @param detail 要计算的订单，只要有id就行
	 * @param templetResult 配件的集合，方便多个订单的使用。
	 * @return 一个计算好配件的订单
	 */
	void getTempletLeafs(Detail detail,Map<String, Templet> leafs,Map<String, Templet> zhongjianjian) ;

	List<Detail> getDetailsFromDetailNumber(String s);

	/** 按月份查计划 */
	List<Detail> listByMonth(String month);

	List<Detail> getDetailsById(List<Detail> details);

	boolean beginProduct(List<Detail> details);

	/** 列出所有需要审核的生产计划 */
	List<Detail> listVerify();

	boolean updateArgee(List<Detail> details);

	boolean updateDisArgee(List<Detail> details);

}
