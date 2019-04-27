package com.task.Server.renshi;

import java.util.List;
import java.util.Map;

import com.task.entity.renshi.Dimission_Handover;

/**
 * 人事离职交接单
 * @author 李聪
 *
 */
public interface Dimission_HandoverServer {
	
	/**
	 * 添加离职交接单对象
	 * @param Dimission_Handover
	 * @return
	 */
	public boolean addDimission_Handover(Dimission_Handover dimission_Handover);
	
	/**
	 * 通过id删除离职交接单对象
	 * @param Dimission_Handover
	 * @return
	 */
	public boolean deleteDimission_Handover(Integer id);
	
	/**
	 * 修改离职交接单对象
	 * @param Dimission_Handover
	 * @return
	 */
	public boolean updateDimission_Handover(Dimission_Handover dimission_Handover);
	
	/**
	 * 通过id查询离职交接单对象
	 * @param Dimission_Handover
	 * @return
	 */
	public Dimission_Handover getDimission_HandoverById(Integer id);
	
	/**
	 * 查询所有离职交接单对象
	 * @param Dimission_Handover
	 * @return
	 */
	public List<Dimission_Handover> findAll();
	
	/**
	 * 按条件分页查询离职交接单
	 * @param Dimission_Handover 对象
	 * @param pageNo 起始页
	 * @param pageSize 每页数量
	 * @return
	 */
	public Map<Integer, Object> findDimission_HandoversByCondition(Dimission_Handover dimission_Handover, int pageNo, int pageSize);
	
	//查询登陆人交接单对象
	public Map<Integer, Object> findDimission_HandoversBycodeCondition(Dimission_Handover dimission_Handover, int pageNo, int pageSize);
	
}
