package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.TryMake;
import com.task.entity.sop.ProcardTemplate;

public interface TryMakeServer {
	/**
	 *按条件分页查询试制零件 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findTryMakesByCondition(TryMake tryMake, int pageNo, int pageSize,ProTryMakeScore proTryMakeScore);	
	/**
	 * 通过id获取试制零件对象
	 * @param id
	 * @return
	 */
	public TryMake getById(Integer id);
	/**
	 * 添加试制零件对象
	 * @param TryMake
	 * @return
	 */
	public boolean add(TryMake tryMake);
	/**
	 * 修改试制零件
	 * @param TryMake
	 * @return
	 */
	public boolean update(TryMake tryMake);
    /**
     *通过id 删除试制零件对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有试制零件
	 * @return
	 */
	public List<TryMake> findAll();
	/**
	 *通话零件ID获取零件对象
	 * @return
	 */
	public ProcardTemplate getProcardTemplateById(Integer id);
	/**
	 * 通话项目试制评审ID获取项目试制评审对象
	 * @param id
	 * @return
	 */
	public ProTryMakeScore getProTryMakeScore(Integer id);
	/**
	 * 通话项目试制评审ID获取项目试制评审对象
	 * @param id
	 * @return
	 */
	public ProTryMakeScore getProTryMakeScoreByTmId(Integer id);
	/**
	 * 通话项目试制评审ID获取项目试制评审对象
	 * @param id
	 * @return
	 */
	public Set<TryMake> getTMSetByPTMSId(Integer PtmId);
	/**
	 * 获取所有的项目试制评审对象
	 * @return
	 */
	public List<ProTryMakeScore> findProTryMakeScoreAll();
	/**
	 * 查询所有的总成零件
	 * @return
	 */
	public List<ProcardTemplate> findTotalProcardTemplateAll();
	/**
	 * 判断是否可以修改项目阶段
	 * @param id
	 * @return
	 */
	public boolean ischangeStatus(Integer id);
	/**
	 * 通过项目Id获取其下拥有的总成零件
	 * @param id
	 * @return
	 */
	public List<ProcardTemplate> findPartsByProId(Integer id);
	/**
	 * 弥补产能，工艺和奖金负荷系数
	 */
	public void addLoad();
}
