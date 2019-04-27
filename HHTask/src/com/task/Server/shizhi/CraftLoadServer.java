package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.CraftComplexity;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.shizhi.SkillScore;
import com.task.entity.shizhi.SkillType;


public interface CraftLoadServer {
	/**
	 *按条件分页查询工艺负荷系数 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findCraftLoadsByCondition(CraftLoad craftLoad, int pageNo, int pageSize);	
	/**
	 * 通过id获取工艺负荷系数对象
	 * @param id
	 * @return
	 */
	public CraftLoad getById(Integer id);
	
	/**
	 * 查询所有工艺负荷系数
	 * @return
	 */
	public List<CraftLoad> findAll();
	/**
	 * 获取所有的工艺复杂系数
	 * @return
	 */
    List<CraftComplexity>  findCraftComplexityAll();
	/**
	 * 获取所有的加工难点系数
	 * @return
	 */
    List<ProProcessDifficulty> findProProcessDifficultyAll();
    
	/**
	 * 通过工艺负荷的id获取其工艺复杂系数和加工难点系数的列表
	 * @param id
	 * @return
	 */
	public Map<Integer, Object> getScoreMap(Integer id);
	/**
	 * 修改craftLoad信息
	 * @return
	 */
	public boolean update(CraftLoad craftLoad);
	/**
	 * 通过id删除工艺负荷对象
	 * @param id
	 * @return
	 */
	public boolean deleteById(Integer id);
	/**
	 * 根据工艺复杂系数或加工难点系数对象id获取技能列表
	 * @param flag
	 * @param id
	 * @return
	 */
	public List<SkillType> getSkills(String flag, Integer id);
	/**
	 * 更具技能对象id获取技能系数列表
	 * @param flag
	 * @param id
	 * @return
	 */
	public List<SkillScore> getSkillScores(Integer id);
	
}
