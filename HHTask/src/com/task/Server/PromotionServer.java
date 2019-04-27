package com.task.Server;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.Promotion;



public interface PromotionServer {
	/**
	 * 根据userId查询
	 */ 
	public List<Promotion> findPromotionbyuserId(Integer userId);
	/**
	 * 查询所有（分页）
	 * 
	 */
	List<Promotion> FindAllPromotion( int pageNo, int pageSize);
	
	/**
	 * 条件查询；
	 * 
	 */
	public Map<Integer, Object> findPromotionByCondition(Promotion pn,
			int pageNo, int pageSize);
		
	/**
	 * 获得总条数
	 */
	public int getcont();
	/**
	 * 根据Id查询
	 */
	Promotion findPromotionbyId(Integer id);
		
	/**
	 * 添加
	 */
	public String add(Promotion pn,File[] attachment,
			String[] attachmentFileName, String fatherPartNumber);
	/**
	 * 修改
	 */
	public boolean upadte(Promotion pn,File[] attachment,
			String[] attachmentFileName, String fatherPartNumber);
	/**
	 * 删除
	 */
	public boolean del(Promotion pn);
	
	
	
	
	
	
	
	
}

	
	
	
