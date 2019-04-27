package com.task.Server.caiwu.noncore;

import java.util.List;
import java.util.Map;

import com.task.entity.caiwu.noncore.EnergyConsumption;
import com.task.entity.caiwu.noncore.NonCorePayable;
import com.task.entity.caiwu.noncore.PayableType;

/**
 * 非主营业务应付接口
 * @author jhh
 *
 */
public interface NoncorePaybaleServer {
	/**
	 * 添加应付
	 * @param nonCorePayable
	 * @return
	 */
	public String saveNoncorePayable(NonCorePayable nonCorePayable);
	
	/**
	 * 根据ID获取应付对象
	 * @param id
	 * @return
	 */
	public NonCorePayable getNoncroePayableById(Integer id);
	/**
	 * 更新应付对象
	 * @param nonCorePayable
	 * @return
	 */
	public String updateNoncore(NonCorePayable nonCorePayable);
	/**
	 * 确认金额
	 * @param nonCorePayable
	 * @return
	 */
	public String querenNoncore(NonCorePayable nonCorePayable);
	/**
	 * 获取列表
	 * @param nonCorePayable
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findNoncorePayableList(NonCorePayable nonCorePayable, String startDate, String endDate,
			Integer cpage, Integer pageSize,String tag);
	/**
	 * 删除应付对象
	 * @param id
	 * @return
	 */
	public String deleteNoncorePayable(Integer id);

	/**
	 * 删除能耗信息
	 * @param id
	 * @return
	 */
	public String deleteEner(Integer id);
	
	/**
	 * 获取能耗信息
	 * @param id
	 * @return
	 */
	public EnergyConsumption getEnerByid(Integer id);
	
	/**
	 * 修改能耗信息
	 * @param consumption
	 * @return
	 */
	public String updateEner(EnergyConsumption consumption);
	/**
	 * 添加能耗信息
	 * @param consumption
	 * @return
	 */
	public EnergyConsumption saveEnergyConsumption(EnergyConsumption consumption);
	/**
	 * 分页查询
	 * @param energyConsumption
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findEnergyConsumptionList(
			EnergyConsumption energyConsumption, int parseInt, int pageSize);
	
	/**
	 * 添加应付类型
	 * @param startDate
	 * @return
	 */
	public String savePayableType(PayableType startDate);
	
	/**
	 * id获得应付类型
	 * @param id
	 * @return
	 */
	public PayableType getPayableTypeid(Integer id);
	/**
	 * 获得所有应付类型
	 * @return
	 */
	public List findPayable();
	/**
	 * 获得所有应付类型，页面下拉
	 * @return
	 */
	public String findPayableType();

	/**
	 * 查看应付类型
	 * @param id
	 * @param cpage
	 * @param pageSize
	 * @return
	 */
	Map<Integer, Object> getUserPaybaleMap(Integer id, int cpage, int pageSize);

	/**
	 * 绑定应付类型
	 * @param id
	 * @param checkboxs
	 * @return
	 */
	public String linkUserPapy(Integer id, int[] checkboxs);
	
}
