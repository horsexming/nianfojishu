package com.task.Server.peb;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.peb.PebBanzuJiegou;
import com.task.entity.peb.PebBorrowAndLendLog;
import com.task.entity.peb.PebProduction;
import com.task.entity.peb.PebProductionBanjin;
import com.task.entity.peb.PebUsers;
import com.task.entity.peb.SubTeam;

public interface ProductEBServer {
	
	/**
	 * 导入开始使用的简报信息
	 * @param attachment
	 * @param attachmentFileName
	 * @param pageStatus
	 * @return
	 */
	String importData(File attachment , String attachmentFileName,String pageStatus);
	
//	/**
//	 * 添加产品下线
//	 * @param pebProduction
//	 * @param pageStatus
//	 * @return
//	 */
//	String addPebProduction(PebProduction pebProduction, String pageStatus);
	
	/**
	 * 产品下线列表
	 * @param pebProduction
	 * @param pageNo
	 * @param pageSize
	 * @param pageStatus
	 * @return
	 */
	Map<String, Object> findProductionByCon(PebProduction pebProduction,Integer pageNo,Integer pageSize,String pageStatus);
	
//	/**
//	 * 添加人事信息
//	 * @param pebUsers
//	 * @param pageStatus
//	 * @return
//	 */
//	String addPebUsers(PebUsers pebUsers,String pageStatus);
	
	/**
	 * 人事信息列表
	 * @param pebUsers
	 * @param pageNo
	 * @param pageSize
	 * @param pageStatus
	 * @return
	 */
	Map<String, Object> findPebUsersByCon(PebUsers pebUsers,Integer pageNo,Integer pageSize,String pageStatus);
	
	/**
	 * 车间简报
	 * @param banzu
	 * @param pageStatus
	 * @return
	 */
	List showCheJian(String banzu,String pageStatus);
	
	/**
	 * 获得班组点评
	 * @param banzu
	 * @return
	 */
	List<String> getDianPingList(String banzu);
	/**
	 * 车间效率简报
	 * @param pageStatus
	 * @return
	 */
	List showPebJianBao(String pageStatus);
	
	/**
	 * 添加子厂
	 * @param subTeam
	 * @return
	 */
	String addSubTeam(SubTeam subTeam);
	
	/**
	 * 子厂列表
	 * @param subTeam
	 * @param pageNo
	 * @param pageSize
	 * @param pageStatus
	 * @return
	 */
	Map<String, Object> findSubTeamByCon(SubTeam subTeam,Integer pageNo,Integer pageSize,String pageStatus);
	
	/**
	 * 删除子厂
	 * @param subTeam
	 * @param pageStatus
	 * @return
	 */
	String deleteSubTeam(SubTeam subTeam,String pageStatus);
	
	PebProduction getPebProductionById(Integer id);
	PebUsers getPebUsersById(Integer id);
	SubTeam getSubTeamById(Integer id);
	
	String addOrUpdatePebProduction(PebProduction pebProduction,String pageStatus);
	
	String addOrUpdatePebUsers(PebUsers pebUsers,String pageStatus);
	String updateSubTeam(SubTeam subTeam,String pageStatus);
	/**
	 * 设置日人均生产日目标
	 */
	String settingTarget(PebUsers pebUsers,Integer goYear,Integer goMonth,Integer goDay,Integer toYear,Integer toMonth,Integer toDay);
	
	/**
	 * pageStatus=null获取所有的装配班组名称
	 * pageStatus=bjProcess,获取所有的工序名称
	 * @param pageStatus
	 * @return
	 */
	List findWorkShopList(String pageStatus);
	
	String addPebBorrowAndLendLog(PebBorrowAndLendLog log,String pageStatus);
	
	Map<String, Object> findProductionBanjin(PebProductionBanjin banjin,Integer pageNo,Integer pageSize,String pageStatus);
	
	PebProductionBanjin findBanjinById(Integer id,String pageStatus);
	
	String addOrUpdatePebProBanjin(PebProductionBanjin banjin,String pageStatus);
	
	//String updatePebProBanjin(PebProductionBanjin banjin,String pageStatus);
	
	List showJianBao(String teamName);
	
	String deletePebProduction(Integer id,String pageStatus);
	//删除钣金下线列表
	String deletePebProBanjin(Integer id,String pageStatus);
	
	//删除人事档案信息表
	String deletePebUsers(Integer id,String pageStatus);
	
	String updatePebProMultpart(PebProductionBanjin banjin,
			Integer goYear,Integer goMonth,Integer goDay,Integer toYear,Integer toMonth,Integer toDay);
	
	//根据负责人获取所在班组
	SubTeam getSubTeamByFuzeren(String userName);
	
	/**
	 * 获取SubTeam所有的最小节点
	 */
	List<SubTeam> getminSubTeam();
	
	/**
	 * 申请借入人数
	 */
	String applyBorrowPerson(PebBorrowAndLendLog log,List<PebBorrowAndLendLog> logList,String pageStatus);

	/**
	 * 人员借用申请记录
	 */
	Map<String, Object> findborrowLogByCon(PebBorrowAndLendLog log, int parseInt, Integer pageSize, String pageStatus);
	
	/**
	 * 导入其他数据
	 * @param attachment
	 * @param attachmentFileName
	 * @param pageStatus
	 * @return
	 */
	String importOtherData(File attachment , String attachmentFileName,String pageStatus);
	
	/**
	 * 获得所有班组结构
	 * @return
	 */
	List<PebBanzuJiegou> findAllPebBanzuJiegou();
	
	/**
	 * 添加班组结构
	 */
	String addPebBanzuJiegou(PebBanzuJiegou jiegou ,String pageStatus);
	
	/**
	 * 修改班组结构
	 */
	String updatePebBanzuJiegou(PebBanzuJiegou jiegou ,String pageStatus);
	/**
	 * 删除班组结构
	 */
	String deletePebBanzuJiegou(Integer id ,String pageStatus);
	
	/**
	 * 根据班组结构Id查询对象
	 */
	PebBanzuJiegou getBanzuJiegouById(Integer id);
	
	/**
	 * 删除班组负责人
	 * @param pebBanzuJiegou 
	 * @param id
	 * @return
	 */
	String deletePrincipals(PebBanzuJiegou pebBanzuJiegou, Integer id);
	
	/**
	 * 添加班组和负责人
	 * @param users 
	 */
	String addPrincipals(PebBanzuJiegou pebBanzuJiegou, Users users);

	Users getUsersByCode(String code);

	String deleteBorrowLog(Integer id);
	
	String getScreenNameById(Integer id,String pageStatus);
	
	/**
	 * 未出勤人数申请审批
	 * @param pebUsers
	 * @return
	 */
	String noChuQinApplyAudit(PebUsers pebUsers);
	
	//获得所有的月份列表
	List<String> getAllMonthList();
	
	//根据班组获取班组的月效率增长
	List<String> getMonthZengZhang(String banzu,String pageStatus);
	
	//导出产品下线
	String exportPebProduction(List<PebProduction> list);
	
	//导出工序产品下线
	String exportPebProductionBanjin(List<PebProductionBanjin> list);
	
	//根据班组名称获取子结构列表
	List<SubTeam> getSubTeamsByBanzu(String banzu);

	/**
	 * 导入借入记录
	 * @param logList
	 * @return
	 */
	String exportBorrowLog(List<PebBorrowAndLendLog> logList);

	/**
	 * 导出人事档案
	 * @param pebUsersList
	 * @return
	 */
	String exportPebUsers(List<PebUsers> pebUsersList);

	//批量审批（人员借入借出）
	String auditMultLogList(Integer[] ids, String tag);
	
	//申请取消借用（人员借入借出）
	String unApplyBorrow(Integer id, String remark);
	
	PebBorrowAndLendLog getLogByCriteria(PebBorrowAndLendLog log,String pageStatus);
}
