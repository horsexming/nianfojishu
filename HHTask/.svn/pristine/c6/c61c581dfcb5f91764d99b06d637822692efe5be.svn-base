package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.GroupShiZhi;

public interface GroupShiZhiServer {
	/**
	 *按条件分页查询组别 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findGroupShiZhisByCondition(GroupShiZhi groupShiZhi, int pageNo, int pageSize);	
	/**
	 * 通过id获取组别对象
	 * @param id
	 * @return
	 */
	public GroupShiZhi getById(Integer id);
	/**
	 * 添加组别对象
	 * @param GroupShiZhi
	 * @return
	 */
	public boolean add(GroupShiZhi groupShiZhi);
	/**
	 * 修改组别
	 * @param GroupShiZhi
	 * @return
	 */
	public boolean update(GroupShiZhi groupShiZhi);
    /**
     *通过id 删除组别对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有组别
	 * @return
	 */
	public List<GroupShiZhi> findAll();

}
