package com.task.Server.sop;

import java.util.List;
import java.util.Map;

import com.task.entity.Price;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.DefectOfType;

/**
 * 不合格品类型管理Server层；
 * 
 */
public interface BeHeGePinServer {
	/**
	 * 添加不合格品
	 */
	public String AddBuGePin(BuHeGePin bhgp);

	/**
	 * 删除不合格品
	 */
	// public boolean DelBuHeGePin(int id);
	public boolean DelBuHeGePin(BuHeGePin buhegepin);

	/**
	 * 根据id查询不合格品
	 * 
	 */
	public BuHeGePin FindBuHeGePinByid(int id);

	/**
	 * 查询所有不合格品（分页）
	 * 
	 */
	List<BuHeGePin> FindAllBuHeGePin(int pageNo, int pageSize);

	/**
	 * 根据产品名称查询不合格品
	 * 
	 */
	public List<BuHeGePin> FindBuHeGePin(String name, int pageNo, int pageSize);

	/**
	 * 根据id修改不合格品
	 * 
	 */
	public boolean UpdataBuHeGePin(Object[] obj);

	/**
	 * 获得总数量；
	 */
	/**
	 * 修改不合格品通过对象；
	 */
	public boolean updatebuhegepin1(BuHeGePin buhegepin);

	public int getcont();

	/**
	 * 条件查询；
	 * 
	 */

	public Map<Integer, Object> findBuHeGePinByCondition(BuHeGePin buhegepin,
			int pageNo, int pageSize);

	/**
	 * 查询所有的缺陷类型；
	 * 
	 */
	public List<String> findBuHeGePinType();
	/**
	 * 添加缺陷大类
	 */
	String adddDefectOfType(DefectOfType defType);
	/**
	 * 修改缺陷大类
	 */
	String updateDefectOfType(DefectOfType defType);
	/**
	 * 删除缺陷大类
	 */
	Boolean delDefectOfType(DefectOfType defType);
	/**
	 * 查询所有缺陷大类
	 */ 
	List<DefectOfType> findAllDefTypeList();
	/**
	 * 根据缺陷大类Id查询相关缺陷类型
	 */
	List<BuHeGePin> findbhgpListByDefId(Integer id,String status,BuHeGePin bhg);
	/**
	 * 根据缺陷大类名字查询相关缺陷类型
	 */
	public List<BuHeGePin> findbhgpListByDefName(String name);
	/**
	 * 条件查询缺陷大类
	 */
	Object[] findDefType(DefectOfType defType,String status,Integer pageSize,Integer pageNo);
	/**
	 * 根据Id查询缺陷大类
	 */
	DefectOfType getdefTypeById(Integer id);
	/**
	 * 缺陷大类绑缺陷小类
	 */
	
	String bangdingbhg(Integer id,Integer [] ids);
}
