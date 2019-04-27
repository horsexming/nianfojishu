package com.task.Server.zgkh;

import java.util.List;

import com.task.entity.Template;
import com.task.entity.UsersGroup;
import com.task.entity.zgkh.AssessPersonnel;

/***
 * 主管级考核_考核人员Server层
 * 
 * @author 刘培
 * 
 */
public interface AssessPersonnelServer {

	/***
	 * 添加考核人员
	 * 
	 * @param assessPersonnel
	 *            考核人员类
	 * @return
	 */
	boolean addAssessPersonnel(AssessPersonnel assessPersonnel,
			String groupName, String status);

	/***
	 * 通过userId查询该人员是否存在
	 * 
	 * @param userId
	 *            用户id
	 * @return AssessPersonnel
	 */
	AssessPersonnel findAPByUserId(int userId);

	/***
	 * 通过userId查询该人员是否存在分组中
	 * 
	 * @param userId
	 *            用户id
	 * @return AssessPersonnel
	 */
	AssessPersonnel findAPByUserId(int userId, String groupName, String status);

	/***
	 * 通过Id查询该人员
	 * 
	 * @param Id
	 *            id
	 * @return AssessPersonnel
	 */
	AssessPersonnel findAPById(int id);

	/***
	 * 查询所有考核人员信息(分页)
	 * 
	 * @param pageNo
	 *            页数
	 * @param pageSize
	 *            每页的个数
	 * @return Object[count,list]
	 */
	public Object[] findAllAssessPersonnel(int pageNo, int pageSize);

	/***
	 * 删除考核人员
	 * 
	 * @param assessPersonnel
	 *            考核人员对象
	 * @return boolean
	 */
	boolean delAssessPersonnel(AssessPersonnel assessPersonnel);

	/***
	 * 查询没有绑定过考核模版的所有人员
	 * 
	 * @return List<AssessPersonnel>
	 */
	List<AssessPersonnel> findAssessPersonnelForbb(Template template,
			Integer userGroupId);

	/***
	 * 查询未打分的考核人员
	 * 
	 * @return List<AssessPersonnel>
	 */
	List<AssessPersonnel> findAssessPersonnelForScore(String status);

	/**
	 * 查询登录人的所有成员分组
	 * 
	 * @return List<UsersGroup>
	 */
	List<UsersGroup> findUsersGroupByUid();

	/**
	 * 通过id查询成员分组
	 * 
	 * @param id
	 * @return UsersGroup
	 */
	UsersGroup findUsersGroupById(int id);

	/***
	 * 通过分组名称查询个人所属分组
	 * 
	 * @param groupName
	 * @return
	 */
	// UsersGroup findUpByName(String groupName);

	/***
	 * 删除成员分组
	 * 
	 * @param usersGroup
	 *            分组对象
	 * @return boolean
	 */
	boolean delUsersGroup(UsersGroup usersGroup);

	// 清空绑定关系
	public int delUserTemplate(int templateId);

	/***
	 * 通过Id查询该人员以及模版
	 * 
	 * @param id
	 * @return
	 */
	Template findAPAndTemplateById(int id);

	/***
	 * 通过Id和月份查询该人员以及模版
	 * 
	 * @param id
	 * @param month
	 *            月份(如为空则默认上个月)
	 * @return
	 */
	Template findAPAndTemplateById(int id, String month);

	/***
	 * 查询审核组成员
	 * 
	 * @return
	 */
	public Object[] findshAssessPersonnel(AssessPersonnel assessPersonnel,int pageNo, int pageSize,
			String pageStatus,String groupName);

	/***
	 * 查询审核组成员
	 * 
	 * @return
	 */
	List findshAssessPersonnel(Integer groupId, String pageStatus);

	/***
	 * 查询审核组
	 * 
	 * @return
	 */
	List findUsersGroup(String pageStatus);

	/***
	 * 通过分组名称查询个人所属分组(是否属于审核组状态)
	 * 
	 * @param groupName
	 * @return
	 */
	UsersGroup findUpByName(String groupName, String status);
}
