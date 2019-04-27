package com.task.Server.pro;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.project.BaomiOperateLog;
import com.task.entity.project.ProjectManage;
import com.task.entity.project.ProjectTime;

/***
 * 项目管理(立项)
 * 
 * @author 刘培
 * 
 */
public interface ProjectManageServer {
	/***
	 * 添加立项信息
	 * 
	 * @param projectManage
	 * @throws Exception
	 */
	void addProjectManage(ProjectManage projectManage, File[] attachment,
			String[] attachmentFileName,String[] otherName, int[] userIds) throws Exception;

	/***
	 * 删除立项信息
	 * 
	 * @param projectManage
	 */
	void delProjectManage(Integer id);

	/***
	 * 修改立项信息
	 * 
	 * @param projectManage
	 */
	void updateProjectManage(ProjectManage projectManage,File[] attachment,
				String[] attachmentFileName,String[] otherName,int[] userIds);

	/***
	 * 查询立项信息
	 * 
	 * @param id
	 *            主键id
	 */
	ProjectManage afindProjectManage(Integer id);

	/***
	 * 查询立项信息(条件查询、分页)
	 * 
	 * @param projectManage
	 */
	Object[] findPMByCondition(ProjectManage projectManage, int pageNo,
			int pageSize,String status);

	/***
	 * 修改
	 * 
	 * @param projectManage
	 */
	void update(ProjectManage projectManage);

	/***
	 * 查询所有待核算的项目
	 * 
	 * @return
	 */
	List<ProjectManage> findHsPro();

	/**
	 * 指派各部门填报录入时间
	 * 
	 * @param proTime
	 */
	void updateProTime(ProjectTime proTime);

	/***
	 * 查询项目对应的时间表
	 * 
	 * @param proId
	 * @return
	 */
	List afindDeptProTime(Integer proId);

	/***
	 * 根据项目id以及类别编号查询时间表并完成该类别
	 * 
	 * @param proId
	 *            项目id
	 * @param pageStatus
	 *            类别编号
	 */
	Map<Integer, Object> updateProTimeForFinal(Integer qpId, String pageStatus);

	/***
	 * 项目立项信息审批
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Object[] findPMForAudit(int pageNo, int pageSize);

	/***
	 * 查询所有的项目信息
	 */
	List findAllProMan();
	/**
	 * 删除文件
	 */
	boolean delwenjian(Integer id);
	/**
	 * 查看保密日志
	 * @param baomiOperateLog
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Map<Integer, Object> findBmlogByCondition(BaomiOperateLog baomiOperateLog,
			int parseInt, int pageSize);
	/**
	 * 弥补保密日志
	 */
	void mibuBaomi();

}
