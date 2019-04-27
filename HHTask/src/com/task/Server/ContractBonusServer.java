package com.task.Server;

import java.util.List;

import com.task.entity.ContractBonus;
import com.task.entity.ContractBonusReceive;

/***
 * 承包奖金总额Server层
 * 
 * @author 刘培
 * 
 */
public interface ContractBonusServer {

	/***
	 * 添加承包奖金总额信息
	 * 
	 * @param ContractBonus
	 *            承包奖金总额对象
	 * @return true/false
	 */
	public boolean addContractBonus(ContractBonus contractBonus, int userId);

	/***
	 * 根据用户id和月份查询某用户某月是否存在承包奖金总额信息
	 * 
	 * @param userId
	 *            用户id
	 * @param bonusMouth
	 *            月份(如果为空则默认是上个月)
	 * @param bonusStatus
	 *            承包总额状态(为空则为查询所有)
	 * @return ContractBonus
	 */
	public ContractBonus findCBByMouthAndDept(int userId, String bonusMouth,
			String bonusStatus);

	/***
	 * 查询所有+条件查询+分页
	 * 
	 * @param contractBonus
	 *            查询对象
	 * @param pageNo
	 *            页数
	 * @param pageSize
	 *            每页的大小
	 * @param status
	 *            是否审核
	 * @return 封装了集合和数量的数组
	 */
	public Object[] findAllContractBonus(ContractBonus contractBonus,
			int pageNo, int pageSize, String status);

	/***
	 * 审核承包奖金总额
	 * 
	 * @param auditStatus
	 *            审核状态(agree/back)即同意/打回
	 * @param contractBonusId
	 *            所需审核的总额id 数组
	 * @return String类型 审核消息
	 */
	public String auditContractBonus(String auditStatus, int[] contractBonusId);

	/***
	 * 根据id查询承包奖金总额信息
	 * 
	 * @param id
	 *            承包奖金总额id
	 * @return ContractBonus 承包奖金总额对象
	 */
	public ContractBonus findContractBonusById(int id);

	/***
	 * 修改承包奖金分配
	 * 
	 * @param contractBonus
	 *            奖金分配类
	 * @return boolean
	 */
	public boolean updateContractBonus(ContractBonus contractBonus);

	/***
	 * 删除承包奖金分配
	 * 
	 * @param contractBonus
	 *            奖金分配类
	 * @return boolean
	 */
	public boolean delContractBonus(ContractBonus contractBonus);

	/***
	 * 根据功能名称是"添加所属成员"的功能所绑定的人员
	 * 
	 * @return 用户集合
	 */
	@SuppressWarnings("unchecked")
	public List findUserBuFunction(String functiongName);

	/***
	 * 根据登录用户id查询出其所属成员
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findTeammembersByUserId(int userId);

	/***
	 * 添加领取信息
	 * 
	 * @param cbr
	 * @return
	 */
	public boolean addReceiveMessage(ContractBonusReceive cbr);

	/***
	 * 通过部留信息Id查询领取信息
	 * 
	 * @param cbId
	 * @return
	 */
	public ContractBonusReceive findCbr(int cbId);

	/***
	 * 根据登录用户id以及所属分组查询出其所属成员
	 * 
	 * @param userId
	 * @return
	 */
	public List findTeammembersByUserId(int userId, int groupId);

	public boolean updateExamBonus(Integer[] detailSelect, String tag);

	public Object[] findExamList(int parseInt, int pageSize);
}
