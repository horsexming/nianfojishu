package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.SkillType;



public interface SkillTypeServer {
	/**
	 *按条件分页查询技能分类 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findSkillTypesByCondition(SkillType skillType, int pageNo, int pageSize);	
	/**
	 * 通过id获取技能分类对象
	 * @param id
	 * @return
	 */
	public SkillType getById(Integer id);
	/**
	 * 添加技能分类对象
	 * @param skillType
	 * @return
	 */
	public boolean add(SkillType skillType);
	/**
	 * 修改技能分类
	 * @param skillType
	 * @return
	 */
	public boolean update(SkillType skillType);
    /**
     *通过id 删除技能分类对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有技能分类
	 * @return
	 */
	public List<SkillType> findAll();
	/**
	 * 根据技能分类获取其拥有的技能系数和未拥有的技能系数列表的集合
	 * @param integer
	 * @param pageSize 
	 * @param cpage 
	 */
	public Map<Integer, Object> getSScoresMap(Integer id, Integer cpage, Integer pageSize);
	/**
	 * 根据技能分类和系数的id进行绑定
	 * @param id
	 * @param skillScoreId
	 * @return
	 */
	public boolean linkSkillScore(Integer skillTypeId, int[] skillScoreId);

}
