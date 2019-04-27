package com.task.Server.renshi;

import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.DimissionNotice;
import com.task.entity.renshi.Dimission_XieYi;

/**
 * 人事离职终止劳动关系协议单
 * @author 李聪
 *
 */
public interface Dimission_XieYiServer {
	
	/**
	 * 添加离职协议单对象
	 * @param dimissionLog 离职单对象
	 * @param dimission_XieYi 协议对象
	 * @param proContent 条款内容
	 * @return
	 */
	public String addDimission_XieYi(DimissionLog dimissionLog,Users users, Dimission_XieYi dimission_XieYi, String[] proContent, String number);
	
	/**
	 * 通过id删除离职协议单对象
	 * @param Dimission_XieYi
	 * @return
	 */
	public boolean deleteDimission_XieYi(Integer id);
	
	/**
	 * 修改离职协议单对象
	 * @param Dimission_XieYi
	 * @return 
	 * @return
	 */
	public boolean updateDimission_XieYi(Dimission_XieYi dimission_XieYi);
	
	/**
	 * 通过id查询离职协议单对象
	 * @param Dimission_XieYi
	 * @return
	 */
	public Dimission_XieYi getDimission_XieYiById(Integer id);
	
	/**
	 * 查询所有离职协议单对象
	 * @param Dimission_XieYi
	 * @return
	 */
	public List<Dimission_XieYi> findAll();
	
	/**
	 * 按条件分页查询离职协议单
	 * @param Dimission_XieYi 对象
	 * @param pageNo 起始页
	 * @param pageSize 每页数量
	 * @return
	 */
	public Map<Integer, Object> findDimission_XieYisByCondition(Dimission_XieYi dimission_XieYi, int pageNo, int pageSize);
	
	// 根据离职协议Id查询所对应的离职协议条款
	@SuppressWarnings("unchecked")
	public List findProvision(Dimission_XieYi dimission_XieYi);
	
	
	/**
	 * 添加离职通知单对象
	 * 
	 * @param DimissionNotice 测试单对象
	 * @return
	 */
	public String addDimissionNotice(DimissionLog dimissionLog,DimissionNotice dimissionNotice);
	
	/**
	 * 按条件分页查询离职通知单对象
	 * @param DimissionNotice 对象
	 * @param pageNo 起始页
	 * @param pageSize 每页数量
	 * @return
	 */
	public Map<Integer, Object> findDimissionNoticesByCondition(DimissionNotice dimissionNotice, int pageNo, int pageSize);
	
	/**
	 * 修改离职通知单对象
	 * @param DimissionNotice
	 * @return 
	 * @return
	 */
	public boolean updateDimissionNotice(DimissionNotice dimissionNotice);
	/**
	 * 通过id查询离职通知单对象
	 * @param DimissionNotice
	 * @return
	 */
	public DimissionNotice getDimissionNoticeById(Integer id);

	/**
	 * 通过id删除离职协议单对象
	 * @param DimissionNotice
	 * @return
	 */
	public boolean deleteDimissionNotice(Integer id);
	
	/**
	 * 自动增长生成编号
	 * @param numberType 编号类型
	 * @return
	 */
	public String findIncrementNumber(String numberType);

	public Users getUsersById(int id);
}
