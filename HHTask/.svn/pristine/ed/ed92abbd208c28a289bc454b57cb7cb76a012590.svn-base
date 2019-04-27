package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.vo.shizhivo.ProProcessDifficultyVo;


public interface ProProcessDifficultyServer {
	/**
	 *按条件分页查询加工难点系数 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findSkillTypesByCondition(ProProcessDifficulty pPD, int pageNo, int pageSize);	
	/**
	 * 通过id获取加工难点系数对象
	 * @param id
	 * @return
	 */
	public ProProcessDifficulty getById(Integer id);
	/**
	 * 通过id获取加工难点系数Vo对象
	 * @param id
	 * @return
	 */
	public ProProcessDifficultyVo getVoById(Integer id);
	/**
	 * 添加加工难点系数对象
	 * @param skillType
	 * @return
	 */
	public boolean add(ProProcessDifficulty pPD);
	/**
	 * 修改加工难点系数
	 * @param skillType
	 * @return
	 */
	public boolean update(ProProcessDifficulty pPD);
    /**
     *通过id 删除加工难点系数对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有加工难点系数
	 * @return
	 */
	public List<ProProcessDifficulty> findAll();
	/**
	 * 根据加工难点系数获取其拥有的技能分类和未拥有的技能分类列表的集合
	 * @param integer
	 * @param  cpage
	 * @param  pageSize
	 */
	public Map<Integer, Object> getSkillTypeVosMap(Integer id, Integer cpage, Integer pageSize);
	/**
	 * 根据加工难点系数和技能分类的id进行绑定
	 * @param id
	 * @param skillScoreId
	 * @return
	 */
	public boolean linkSkillType(Integer pPDId, int[] skillTypeId);

}
