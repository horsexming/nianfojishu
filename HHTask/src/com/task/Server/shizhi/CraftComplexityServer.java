package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.CraftComplexity;
import com.task.entity.vo.shizhivo.CraftComplexityVo;



public interface CraftComplexityServer {
	/**
	 *按条件分页查询工艺复杂系数 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findSkillTypesByCondition(CraftComplexity cc, int pageNo, int pageSize);	
	/**
	 * 通过id获取工艺复杂系数对象
	 * @param id
	 * @return
	 */
	public CraftComplexity getById(Integer id);
	/**
	 * 通过id获取工艺复杂系数Vo对象
	 * @param id
	 * @return
	 */
	public CraftComplexityVo getVoById(Integer id);
	/**
	 * 添加工艺复杂系数对象
	 * @param skillType
	 * @return
	 */
	public boolean add(CraftComplexity cc);
	/**
	 * 修改工艺复杂系数
	 * @param skillType
	 * @return
	 */
	public boolean update(CraftComplexity cc);
    /**
     *通过id 删除工艺复杂系数对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有工艺复杂系数
	 * @return
	 */
	public List<CraftComplexity> findAll();
	/**
	 * 根据工艺复杂系数获取其拥有的技能分类和未拥有的技能分类列表的集合
	 * @param integer
	 * @param pageSize 
	 * @param cpage 
	 */
	public Map<Integer, Object> getSkillTypeVosMap(Integer id, Integer cpage, Integer pageSize);
	/**
	 * 根据工艺复杂系数和技能分类的id进行绑定
	 * @param id
	 * @param skillScoreId
	 * @return
	 */
	public boolean linkSkillType(Integer ccDId, int[] skillTypeId);
}
