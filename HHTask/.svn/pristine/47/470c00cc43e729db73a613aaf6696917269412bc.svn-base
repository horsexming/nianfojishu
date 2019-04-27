package com.task.Server.barandqr;

import java.util.List;
import java.util.Map;

import com.task.entity.barandqr.AirMachine;
import com.task.entity.barandqr.AirProduct;
import com.task.entity.barandqr.AirtightLog;

public interface AirtightLogServer {

	public String addBarCode(AirtightLog pageAirtightLog);
    /**
     * 分页显示气密记录
     * @param airtightLog
     * @param parseInt
     * @param pageSize
     * @return
     */
	public Map<Integer, Object> findAirtightLogsByCondition(
			AirtightLog airtightLog, int parseInt, int pageSize);
	public AirProduct getAirProductByCode(String content);
	/**
	 * 获取气密测试的件号
	 * @return
	 */
	public List<String> getProductMarkId();
	/**
	 * 获取当前选定的气密产品
	 * @return
	 */
	public AirProduct getChecked();
	public boolean checkMarkId(String markId);
	public Long getTestTimeByMarkId(String markId);
	/**
	 * 分页显示气密产品
	 * @param airProduct
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findAirProductsByCondition(
			AirProduct airProduct, int pageNo, int pageSize);
	/**
	 * 添加气密产品
	 * @param airProduct
	 * @return
	 */
	public String addProduct(AirProduct airProduct);
	/**
	 * 修改气密产品
	 * @param airProduct
	 * @return
	 */
	public String updateProduct(AirProduct airProduct);
	/**
	 * 通过id获取气密产品
	 * @param id
	 * @return
	 */
	public AirProduct getProductById(Integer id);
	/**
	 * 通过id删除气密产品
	 * @param id
	 * @return
	 */
	public boolean deleteProduct(Integer id);
	/**
	 * 通过id 删除日志
	 * @param id
	 * @return
	 */
	public boolean deleteLog(Integer id);
	/**
	 * 分页显示气密机器
	 * @param airMachine
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findAirMachinesByCondition(
			AirMachine airMachine, int parseInt, int pageSize);
	/**
	 * 添加气密机器
	 * @param airMachine
	 * @return
	 */
	public boolean addMachine(AirMachine airMachine);
	/**
	 * 修改气密机器信息
	 * @param airMachine
	 * @return
	 */
	public boolean updateMachine(AirMachine airMachine);
	/**
	 * 通过Id删除机器
	 * @param id
	 * @return
	 */
	public boolean deleteMachine(Integer id);
	/**
	 * 通过id获取气密机器
	 * @param id
	 * @return
	 */
	public AirMachine getAirMachieById(Integer id);
	/**
	 * 
	 * @param markId
	 * @return
	 */
	public AirProduct getAirProductByMarkId(String markId);
	public Map<Integer, Object> findAirtightLogsByCondition1(
			AirtightLog airtightLog, int parseInt, int pageSize);

}
