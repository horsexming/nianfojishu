package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.project.QuotedPrice;
import com.task.entity.shizhi.BonusLoad;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.Cusimportance;
import com.task.entity.shizhi.ProductivityLoad;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.ProjectOrderPart;
import com.task.entity.shizhi.TryMake;

public interface ProjectOrderPartServer {
	/**
	 *按条件分页查询项目零件 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findProjectOrderPartsByCondition(ProjectOrderPart projectOrderPart, int pageNo, int pageSize);	
	/**
	 * 通过id获取项目零件对象
	 * @param id
	 * @return
	 */
	public ProjectOrderPart getById(Integer id);
	/**
	 * 添加项目零件对象
	 * @param ProjectOrderPart
	 * @return
	 */
	public boolean add(ProjectOrderPart projectOrderPart);
	/**
	 * 修改项目零件
	 * @param ProjectOrderPart
	 * @return
	 */
	public boolean update(ProjectOrderPart projectOrderPart);
    /**
     *通过id 删除项目零件对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有项目零件
	 * @return
	 */
	public List<ProjectOrderPart> findAll();
	/**
	 * 查询所有未审批或者被打回的项目需求单
	 * @return
	 */
	public List<ProjectOrder> findProjectOrderAll();
	/**
	 * 根据项目需求单id查找项目需求单
	 * @param id
	 * @return
	 */
	public ProjectOrder getProjectOrderById(Integer id);
	/**
	 * 根据项目需求单零件id查看是否被修改
	 * @param id
	 * @return
	 */
	public boolean canBeChange(Integer id);
	/**
	 * 根据零件id查找项目试制零件
	 * @param id
	 * @return
	 */
	public TryMake getTryMakeById(Integer id);
	/**
	 * 根据项目需求单的id获取试制的列表用来选择零件
	 * @param id
	 * @return
	 */
	public List<TryMake> finTryMakeListByPorderId(Integer id);

}
