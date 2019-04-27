package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.SkillScore;

public interface SkillScoreServer {
	/**
	 *按条件分页查询技能系数 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findSkillScoresByCondition(SkillScore skiilScore, int pageNo, int pageSize);	
	/**
	 * 通过id获取技能系数对象
	 * @param id
	 * @return
	 */
	public SkillScore getById(Integer id);
	/**
	 * 添加技能系数对象
	 * @param skillScore
	 * @return
	 */
	public boolean add(SkillScore skillScore);
	/**
	 * 修改技能系数
	 * @param skillScore
	 * @return
	 */
	public boolean update(SkillScore skillScore);
    /**
     *通过id 删除技能系数对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有技能系数
	 * @return
	 */
	public List<SkillScore> findAll();

}
