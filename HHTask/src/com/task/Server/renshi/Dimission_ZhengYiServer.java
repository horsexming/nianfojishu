package com.task.Server.renshi;

import java.util.List;
import java.util.Map;

import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_ZhengYi;

/**
 * 人事离职申请单
 * @author 李聪
 *
 */
public interface Dimission_ZhengYiServer {
	
	/**
	 * 添加离职争议单对象
	 * @param Dimission_ZhengYi
	 * @return
	 */
	public String addDimission_ZhengYi(DimissionLog dimissionLog, Dimission_ZhengYi dimission_ZhengYi);
	
	/**
	 * 通过id删除离职争议单对象
	 * @param Dimission_ZhengYi
	 * @return
	 */
	public boolean deleteDimission_ZhengYi(Integer id);
	
	/**
	 * 修改离职争议单对象
	 * @param Dimission_ZhengYi
	 * @return 
	 * @return
	 */
	public boolean updateDimission_ZhengYi(Dimission_ZhengYi dimission_ZhengYi);
	
	/**
	 * 通过id查询离职争议单对象
	 * @param Dimission_ZhengYi
	 * @return
	 */
	public Dimission_ZhengYi getDimission_ZhengYiById(Integer id);
	
	/**
	 * 查询所有离职争议单对象
	 * @param Dimission_ZhengYi
	 * @return
	 */
	public List<Dimission_ZhengYi> findAll();
	
	/**
	 * 按条件分页查询离职争议单
	 * @param Dimission_ZhengYi 对象
	 * @param pageNo 起始页
	 * @param pageSize 每页数量
	 * @return
	 */
	public Map<Integer, Object> findDimission_ZhengYisByCondition(Dimission_ZhengYi dimission_ZhengYi, int pageNo, int pageSize);
	
}
