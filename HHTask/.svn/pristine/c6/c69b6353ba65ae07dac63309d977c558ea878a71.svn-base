package com.task.Server.pro;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.entity.Users;
import com.task.entity.project.ProjectManage;
import com.task.entity.project.ProjectMaterial;
import com.task.entity.project.ProjectMaterialOrder;



/***
 * 研发项目材料清单
 * 
 * @author 刘培
 * 
 */
public interface ProjectMaterialOrderServer {
	/**
	 *按条件分页查询项目需求单
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findProjectMaterialOrdersByCondition(ProjectMaterialOrder projectMaterialOrder, int pageNo, int pageSize,String sql);	
	/**
	 * 通过id获取项目需求单对象
	 * @param id
	 * @return
	 */
	public ProjectMaterialOrder getById(Integer id);
	/**
	 * 添加项目需求单对象并申请审批
	 * @param ProjectOrder
	 * @return
	 */
	public boolean add(ProjectMaterialOrder projectMaterialOrder,Set<ProjectMaterial> pmSet) throws Exception;
	/**
	 * 修改项目需求单
	 * @param ProjectOrder
	 * @return
	 */
	public boolean update(ProjectMaterialOrder projectMaterialOrder,Set<ProjectMaterial> pmSet);
    /**
     *通过id 删除项目需求单对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有项目需求单
	 * @return
	 */
	public List<ProjectMaterialOrder> findAll();
	/**
	 * 根据项目需求单材料id查看是否被修改
	 * @param id
	 * @return
	 */
	public boolean canBeChange(Integer id);
	/**
	 * 找到所有的项目
	 * @return
	 */
	public List<ProjectManage> findAllProjectManage();
	public List<ProjectMaterial> findMaterialByOrderId(Integer id);
	/**
	 * 查看是否有足够的项目材料可以被领取
	 * @param projectMaterialOrder
	 * @return
	 */
	public Map<Integer, Object> toReceive(Integer id);
	/**
	 * 通过材料单的id领取材料
	 * @param id
	 * @return
	 */
	public boolean receiveMaterial(Integer id,String idshifa,Users user) throws Exception;
	public boolean checkshifacount(String goodsMarkId, String goodsFormat,
			float shifa);
	/**
	 * 通过卡号获取领料人
	 * @param receiveuser
	 * @return
	 */
	public Users getReceiver(String receiveuser);
	public Map<Integer, Object> findbmProjectMaterialOrdersByCondition(
			ProjectMaterialOrder projectMaterialOrder, int parseInt,
			int pageSize, String sql,String isbaomi);
	/**
	 * 导出物料清单
	 * @param id
	 */
	public void exprotqd(Integer id);
}
