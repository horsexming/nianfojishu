package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.project.ProjectManage;
import com.task.entity.shizhi.BonusLoad;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.Cusimportance;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.ProductivityLoad;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.TryMake;
import com.task.entity.sop.Procard;
import com.task.entity.vo.shizhivo.ProTryMakeScoreVo;


public interface ProTryMakeScoreServer {
	/**
	 *按条件分页查询项目试制评分系数 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findProTryMakrScoresByCondition(ProTryMakeScore proTryMakeScore, int pageNo, int pageSize,int status,String markId);	
	/**
	 * 通过id获取项目试制评分系数对象
	 * @param id
	 * @return
	 */
	public ProTryMakeScore getById(Integer id);
	/**
	 * 查询所有项目试制评分系数
	 * @return
	 */
	public List<ProTryMakeScore> findAll();
    
  
	/**
	 * 通过项目试制评分的id获取其试制的列表
	 * @param id
	 * @return
	 */
	public List<TryMake> getTryMakeMap(Integer id);
	/**
	 * 通过id删除项目试制评分对象
	 * @param id
	 * @return
	 */
	public boolean deleteById(Integer id);
	/**
	 * 找到所有的项目需求单
	 * @return
	 */
	public List<ProjectOrder> findProjectOrderAll();
	/**
	 * 找到所有的工艺负荷系数
	 * @return
	 */
	public List<CraftLoad> findCraftLoadAll();
	/**
	 * 找到所有的产能负荷系数
	 * @return
	 */
	public List<ProductivityLoad> findProductivityLoadAll();
	/**
	 * 找到所有的奖金负荷系数
	 * @return
	 */
	public List<BonusLoad> findBonusLoadAll();
	/**
	 * 找到所有的客户重要系数
	 * @return
	 */
	public List<Cusimportance> findCusimportanceAll();
	/**
	 * 通过试制id获取试制对象
	 * @param id
	 * @return
	 */
	public TryMake getTryMakeById(Integer id);
	/**
	 * 修改试制对象
	 * @param tryMake
	 * @return
	 */
	public boolean updateTryMake(TryMake tryMake);
	/**
	 * 修改项目试制评审对象
	 * @param proTryMakeScore
	 * @return
	 */
	public boolean updatePro(ProTryMakeScore proTryMakeScore);
	/**
	 * 添加
	 * @param proTryMakeScore
	 * @return
	 */
	public boolean add(ProTryMakeScore proTryMakeScore);
	/**
	 * 得到所有客户的名称
	 * @return
	 */
	public List<String> getAllCusName();
	/**
	 * 得到所有分组的名称
	 * @return
	 */
	public List<String> getAllGroupName();
	/**
	 * 通过项目名字获取该项目在不同月的各零件的占比和奖金额
	 * @param proName
	 * @return
	 */
	public List<ProTryMakeScoreVo> getAllBouns(String proName);
//	/**
//	 * 修改各零件占比
//	 */
//	public void updateRate();
//	/**
//	 * 通过项目需求单设置项目试制评审零件的入库数量
//	 * @param projectOrder
//	 */
//	public void updateInputNum(ProjectOrder projectOrder);
	/**
	 * 通过月份计算该月的所有的产生项目订单的项目试制评审零件的各种占比和月奖金额
	 * @return
	 */
	public boolean SetTryMakeRateByMonth(String month);
	/**
	 * 获取所有的已同意的项目
	 * @return
	 */
	public List<ProjectManage> getAllProjectManage();
	/**
	 * 通过项目的Id获取项目
	 * @param id
	 * @return
	 */
	public ProjectManage getProjectManageById(Integer id);
	/**
	 * 获取某个月的项目试制状态
	 * @param month
	 * @return
	 */
	public Map<Integer, Object> updateLoadAndgetApprovalOrPrint(String month);
	/**
	 * 申请试制奖金
	 * @param month
	 * @return
	 */
	public boolean addApprovalOneMonth(String month) throws Exception;
	//弥补客户重要系数
	public void addCusimportance();
	/**
	 * 通过试制零件获取生产流水卡
	 * @param id
	 * @return
	 */
	public List<Procard> getprocardByTrymakeId(Integer id);
	/**
	 * 获取该月人员的试制奖金
	 * @param month
	 * @return
	 */
	public List getUserBonus(String month);
	/**
	 *  核实该月的试制奖金是否已经申请
	 * @param month
	 * @return
	 */
	public boolean checkTryMakeApproval(String month);
	/**
	 * 生成月份试制奖金分配
	 * @param month
	 * @return
	 */
	public boolean addUserBonus(String month);
	/**
	 * 该月份的试制奖金是否已经分配
	 * @param month
	 * @return
	 */
	public boolean hasDistributionBonus(String month);


	
}
