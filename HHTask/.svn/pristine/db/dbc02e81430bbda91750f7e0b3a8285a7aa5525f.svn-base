package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.BonusLoad;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.Cusimportance;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.ProductivityLoad;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.ProjectOrderPart;
import com.task.entity.shizhi.TryMake;
import com.task.entity.vo.shizhivo.ProjectOrderVo;
import com.task.entity.zgkh.AssessPersonnel;



public interface ProjectOrderServer {
	/**
	 *按条件分页查询项目需求单
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findProjectOrdersByCondition(ProjectOrder projectOrder, int pageNo, int pageSize);	
	/**
	 * 通过id获取项目需求单对象
	 * @param id
	 * @return
	 */
	public ProjectOrder getById(Integer id);
	/**
	 * 添加项目需求单对象
	 * @param ProjectOrder
	 * @return
	 */
	public boolean add(ProjectOrder projectOrder);
	/**
	 * 修改项目需求单
	 * @param ProjectOrder
	 * @return
	 */
	public boolean update(ProjectOrder projectOrder);
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
	public List<ProjectOrder> findAll();
	/**
	 * 获取所有的项目试制评审对象
	 * @return
	 */
	public List<ProTryMakeScore> findProTryMakeScoreAll();
	/**
	 * 通过项目试制评审id获取项目试制评审对象
	 * @param id
	 * @return
	 */
	public ProTryMakeScore getProTryMakeScore(Integer id);
	/**
	 * 项目需求单申请审批修改状态
	 * @param projectOrder
	 * @return
	 * @throws Exception 
	 */
	public String addapproval(ProjectOrder projectOrder) throws Exception;
	/**
	 * 通过项目需求单id来找到项目需求单并转化成Vo用来显示
	 * @param id
	 * @return
	 */
	public ProjectOrderVo getProjectOrderVoById(Integer id);
	/**
	 * 试制需求单转试制流水单
	 * @param projectOrder
	 * @return
	 */
	public String addProCard(ProjectOrder projectOrder,List<ProjectOrderPart> partList);
	
//	
//	/**
//	 * 通过月份计算该月的所有的产生项目订单的项目试制评审零件的各种占比和月奖金额
//	 * @return
//	 */
//	public boolean setTryMakeRateByMonth(String month);
//	/**
//	 * 找到所有的工艺负荷系数
//	 * @return
//	 */
//	public List<CraftLoad> findCraftLoadAll();
//	/**
//	 * 找到所有的产能负荷系数
//	 * @return
//	 */
//	public List<ProductivityLoad> findProductivityLoadAll();
//	/**
//	 * 找到所有的奖金负荷系数
//	 * @return
//	 */
//	public List<BonusLoad> findBonusLoadAll();
//	/**
//	 * 找到所有的客户重要系数
//	 * @return
//	 */
//	public List<Cusimportance> findCusimportanceAll();
//	/**
//	 * 通过项目需求单设置项目试制评审零件的入库数量
//	 * @param projectOrder
//	 */
//	public void updateInputNumByDeletPo(ProjectOrder projectOrder);

}
