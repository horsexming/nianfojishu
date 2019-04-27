package com.task.Server.pro;

import java.util.List;
import java.util.Map;

import com.task.entity.project.ProjectMaterial;
import com.task.entity.project.ProjectMaterialOrder;



/***
 * 研发项目材料
 * 
 * @author txb
 * 
 */
public interface ProjectMaterialServer {
	/**
	 *按条件分页查询项目材料
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findProjectOrderPartsByCondition(ProjectMaterial projectMaterial, int pageNo, int pageSize);	
	/**
	 * 通过id获取项目材料对象
	 * @param id
	 * @return
	 */
	public ProjectMaterial getById(Integer id);
	/**
	 * 添加项目材料对象
	 * @param ProjectOrderPart
	 * @return
	 */
	public boolean add(ProjectMaterial projectMaterial);
	/**
	 * 修改项目材料
	 * @param ProjectOrderPart
	 * @return
	 */
	public boolean update(ProjectMaterial projectMaterial);
    /**
     *通过id 删除项目材料对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有项目材料
	 * @return
	 */
	public List<ProjectMaterial> findAll();
	/**
	 * 查询所有未审批或者被打回的项目需求单
	 * @return
	 */
	public List<ProjectMaterialOrder> findProjectMaterialOrderAll();
	/**
	 * 根据项目需求单id查找项目需求单
	 * @param id
	 * @return
	 */
	public ProjectMaterialOrder getProjectMaterialOrderById(Integer id);
	/**
	 * 根据项目需求单材料id查看是否被修改
	 * @param id
	 * @return
	 */
	public boolean canBeChange(Integer id);
	/**
	 * 根据材料id查找项目试制材料
	 * @param id
	 * @return
	 */
	public ProjectMaterial getMaterialById(Integer id);
	

}
