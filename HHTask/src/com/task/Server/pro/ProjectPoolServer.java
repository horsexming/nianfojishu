package com.task.Server.pro;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.DeptNumber;
import com.task.entity.project.ProjectManageYfpd;
import com.task.entity.project.ProjectManageyf;
import com.task.entity.project.ProjectManageyfAgree;
import com.task.entity.project.ProjectManageyfEr;
import com.task.entity.project.ProjectManageyfUser;
import com.task.entity.project.ProjectPool;
import com.task.entity.project.YfUser;

public interface ProjectPoolServer {
	
	/**
	 * 添加项目池
	 * @param projectPool
	 * @return
	 */
	String addPool(ProjectPool projectPool);

	/**
	 * 删除项目池
	 * @param id
	 * @return
	 */
	String delProjectPool(Integer id);
	
	Object[] findPoolByCondition(ProjectPool projectPool, int parseInt,
			int pageSize, String pageStatus);
	/**
	 * 通过条件获取个人项目
	 * @param projectManageyf
	 * @param parseInt
	 * @param pageSize
	 * @param pageStatus
	 * @return
	 */
	Object[] findselfProjectmanageYf(ProjectManageyf projectManageyf, int parseInt,
			int pageSize, String pageStatus);
	
	/**
	 * 根据项目池id查找项目池和主项目
	 * @param poolId
	 * @return
	 */
	ProjectPool getProjectPoolById(Integer poolId);
	
	/**
	 * 编辑项目池
	 * @param projectPool
	 * @return
	 */
	String editPool(ProjectPool projectPool);
	/**
	 * 获得项目指派人
	 * @param 项目id
	 * @return 指派人List
	 */
	List<ProjectManageyfUser> getAssignUserList(Integer id);
	
	
	/**
	 * 添加项目指派
	 * @return
	 */
	Object[] projectManageryfAssign(ProjectManageyfUser yfUser);

	/**
	 * 取消指派
	 */
	String cancelAssion(Integer pmyfId);
	
	/**
	 * 根据id查找研发项目
	 * @param id
	 * @return
	 */
	ProjectManageyf getProjectManageyfById(Integer id);
	
	/**
	 * 查看别人的评分
	 *  @param id 自己要评分的项目id
	 * @return 其他评分列表
	 */
	List<ProjectManageyf> getOtherPMyf(Integer id);
	
	/**
	 * 保存分数
	 */
	String saveProjectStore(ProjectManageyf projectManageyf);
	
	/**
	 * 根据项目rootId查找所有的子项目
	 */
	public List<ProjectManageyf> findProjectManageyfByRootId(Integer rootId);
	
	/**
	 * 根据root复杂查询信息
	 * @param rootId
	 * @return
	 */
	 List<ProjectManageyf> findProjectwhereByRootId(Integer rootId);
	/**
	 * 提交评分是否同意结果
	 */
	public String submitExamineResult(ProjectManageyfAgree agree);
	
	/**
	 * 查找评分已确认人列表
	 */
	public List<ProjectManageyfAgree> findProjectManageyfAgrees(Integer projectId); 
	
	/**
	 * 修改研发项目或添加子项目
	 * @param projectManageyf
	 * @return
	 */
	Object[] saveAndUpdateYf(ProjectManageyf projectManageyf);
	
	/**
	 * 删除子项目
	 * @param id
	 * @return
	 */
	String delProject(Integer id,String pageStatus);
	
	/**
	 * 查找所有部门
	 * @param rootId
	 * @return
	 */
	List<DeptNumber> findDeptByRootId(Integer rootId);
	
	/**
	 * 根据部门id获得所有人员
	 * @return
	 */
	List getUsersByDeptId(Integer id,String ids);
	/**
	 * 添加参与人
	 * @param userId
	 * @param yfProjectId
	 * @return
	 */
	String[] addProjectPlayers(Integer userId,Integer yfProjectId);
	
	/**
	 * 直接指派参与人
	 */
	String directBindPlayers(YfUser yfUser,Integer nowId,Integer weight);
	
	/**
	 * 取消项目参与人
	 * @param yfUser
	 * @return
	 */
	String cancalBindPlayers(YfUser yfUser);
	/**
	 * 根据项目id获得未参与人列表
	 * @param projectId
	 * @return
	 */
	List<ProjectManageyfUser> findprojectYfUserByProId(Integer rootId,Integer id);
	
	/**
	 * 取消项目参与人
	 * @param userId
	 * @param yfProjectId
	 * @return
	 */
	String cancelPlayers(Integer erId,Integer yfProjectId);
	
	/**
	 * 根据项目id查找项目人员表
	 * @param projectId
	 * @return
	 */
	ProjectManageyfUser getProjectYfUserByprojectId(Integer projectId);
	
	/**
	 * 候选项目
	 * @param projectId
	 * @return
	 */
	String chooseSubProject(Integer projectId);
	
	/**
	 * 填报项目进度
	 * @param projectManageyf
	 * @return
	 */
	String fillSchedule(ProjectManageyf projectManageyf);
	
	/**
	 * 根据项目id查找所有报选项目人员
	 * @param projectId
	 * @return
	 */
	List<ProjectManageyfEr> selectChooseList(Integer projectId,String status);
	/**
	 * 审批候选项目负责人
	 */
	String examineProject(ProjectManageyfEr er,Integer projectId,Integer weight);
	
	/**
	 * 保存或提交审批项目进度
	 */
	String saveOrSubmitSchedule(ProjectManageyf projectManageyf ,String tag);
	
	/**
	 * 审批子项目
	 * @param projectManageyf
	 * @param result 审批结果 同意or打回
	 * @return
	 */
	String examineSubProject(ProjectManageyf projectManageyf,String result);
	
	/**
	 * 根据项目id查找参与人列表
	 */
	List<ProjectManageyfUser> getUserListByProId(Integer proId);
	
	/**
	 * 取消指派项目中的某个人
	 */
	String cancelBindPlayers(YfUser yfUser);
	
	/**
	 * 项目个人完成情况分数查看
	 */
	List<ProjectManageyf> getStoreResult()throws Exception;
	
	/**
	 * 个人研发项目汇总
	 */
	List<ProjectManageyf> selfYfProjectStore(String pageStatus) throws Exception;
	
	/**
	 * 根据项目id获得项目的占比金额人数
	 * @param projectManageyf
	 * @return
	 */
	public ProjectManageyf getProStoreByProId(ProjectManageyf projectManageyf)throws Exception;
	
	/**
	 * 根据项目id查找所有子项目
	 */
	List<ProjectManageyf> getSubListById(Integer proId);
	/**
	 * 根据父项目id获取下层项目分数百分比
	 * @return
	 */
	String getProPercentnm(Integer proId,Integer store,String tag) throws Exception;
	
	/**
	 * 根据项目ID获取项目金额
	 */
	Double getProMoneyByProId(ProjectManageyf projectManageyf) throws Exception;
	
	/**
	 * 根据项目id查找项目人员明细
	 * @param proId
	 * @return
	 */
	List<ProjectManageYfpd> getProyfpdByProId(Integer proId);
	
	/**
	 * 总项目汇总
	 */
	List<ProjectManageyf> projectManageResult();
	
	/**
	 * 子项目汇总
	 */
	List<ProjectManageyf> selectSubPro(Integer id);

	/**
	 * 发送消息提醒
	 */
	void sendProjectManageYfInfo();
	
	/**
	 * 提交延期及说明
	 */
	String submitForPostpone(ProjectManageyf projectManageyf,String pageStatus);
	
	/**
	 * 审批延期申请
	 */
	String examineForPostone(Integer proId,String status);
	
	/**
	 * 主项目提交审批文件
	 * @param id
	 * @param attachments
	 * @param attachmentNames 
	 * @return
	 */
	String finalSubmitFile(ProjectManageyf projectManageyf,List<File> attachments, List<String> attachmentNames);
	
	/**
	 * 按项目池汇总
	 */
	public List<ProjectManageYfpd> summarizingPool();
	
	/**
	 * 按主项目汇总
	 */
	public List<ProjectManageYfpd> summarizingRootPro();
	
	/**
	 * 按年份汇总
	 */
	public List<ProjectManageYfpd> summarizingYear();
	
	/**
	 * 按月份汇总
	 */
	public List<ProjectManageYfpd> summarizingMonth();
	
	/**
	 * 按人员汇总
	 * @return
	 */
	List<ProjectManageYfpd> personnelSummary();

	/**
	 * 查询user参与的所有项目
	 * @param userId
	 * @return
	 */
	List<ProjectManageyf> showSelfProject(Integer userId);

	/**
	 * 查询user在项目池中参与的所有项目
	 * @param userId
	 * @param id
	 * @return
	 */
	List<ProjectManageyf> showSelfProjectAndPool(Integer userId, Integer poolId);
	
	/**
	 * 查询user在主项目中参与的所有项目
	 * @param userId
	 * @param id
	 * @return
	 */
	List<ProjectManageyf> showSelfProjectByRoot(Integer userId, Integer rootId);
	
	/**
	 * 根据IDS删除其他主项目
	 * @param ids
	 * @return true or 异常
	 */
	String delProjectByIds(String ids)throws Exception;
	
	/**
	 * 查找某项目未指派人员
	 * @param proId
	 * @param tag
	 * @return
	 */
	List<ProjectManageyfEr> findUnErList(Integer proId,String pageStatus);
	
	/**
	 * 使用projectManageyfEr 直接指派参与人
	 */
	String zhipaiPlayers(Integer id,Integer userId,Integer weight,String pageStatus);
	
	/**
	 * 
	 */
	ProjectManageyfEr getProjectManageyfErById(Integer id,String pageStatus);
	
	/**
	 * 查询所有的项目文档
	 */
	Map<String, Object> searchProFile(ProjectManageyf pro,Integer pageSize,Integer pageNo,String pageStatus);

	/**
	 * 查看权限绑定
	 * @param id
	 * @param erList
	 * @param ids
	 * @return
	 */
	String xqBind(int id, List<ProjectManageyfEr> erList, String ids);
	
	
	/**
	 * 根据人员获取开始时间到结束时间内的参与项目信息
	 * @param userId
	 * @param startTime
	 * @param dateTime
	 * @param pageStatus
	 * @return
	 */
	List<ProjectManageyf> findselfProjectmanageYfByDateDiff(Integer userId,String startTime,String dateTime,String pageStatus);
}
