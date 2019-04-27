package com.task.Server.shizhi;

import java.util.Map;

import com.task.entity.shizhi.ProcessSopTemp;


public interface ProcessSopTempServer {
    /**
     * 分页查询
     * @param processSopTemp
     * @param parseInt
     * @param pageSize
     * @return
     */
	Map<Integer, Object> findPocessSopTempsByCondition(
			ProcessSopTemp processSopTemp, int parseInt, int pageSize);
    /**
     * 通过id获取试制工序模板对象
     * @param id
     * @return
     */
	ProcessSopTemp getById(Integer id);
	/**
	 * 修改各个系数
	 * @param processSopTemp
	 * @param updateAll
	 * @return
	 */
	boolean update(ProcessSopTemp processSopTemp,String updateAll);
	/**
	 * 删除试制工序模板
	 * @param processSopTemp
	 * @return
	 */
	boolean delete(ProcessSopTemp processSopTemp);
	/**
	 * 更新所有工序模板
	 * @return
	 */
	boolean updateAll();
	/**
	 * 通过试制工序模板的id获取其工艺复杂系数和加工难点系数的列表
	 * @param id
	 * @return
	 */
	Map<Integer, Object> getScoreMap(Integer id);

}
