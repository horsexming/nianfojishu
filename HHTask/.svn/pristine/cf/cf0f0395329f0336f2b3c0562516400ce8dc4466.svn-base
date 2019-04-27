package com.task.Server.parking;

import java.util.List;
import java.util.Map;
import com.task.entity.parking.ParkSpace;
import com.task.entity.parking.ParkSpaceUseInfor;
import com.task.entity.parking.SimCarkTel;


public interface ParkSpaceServer {
	/**
	 * 添加车位记录
	 * @return
	 */
	public String addParkSpace(ParkSpace parkSpace);
	/**
	 * 修改车位记录
	 * @return
	 */
	public String updateAccess(ParkSpace parkSpace, Integer id);
	
	/**
	 * 
	 * @param parkSpace 车位信息
	 * @param id 开关指令
	 * @param userid 用户ID
	 * @param userName 用户姓名
	 * @param userCode 用户工号
	 * @return
	 */
	public String updateAccessPhone(ParkSpace parkSpace, Integer id, String userid, String userName, String userCode);
	/**
	 * 
	 * @param parkSpace 车位信息
	 * @param id 开关指令
	 * @param Tel 手机号码
	 * @return
	 */
	public String updateAccessTelPhone(ParkSpace parkSpace, Integer id);
	/**
	 * 修改1车位记录
	 * @return
	 */
	public String updateAccess1(ParkSpace parkSpace);
	/**
	 * 删除车位记录
	 * @return
	 */
	public String deleteAccess(ParkSpace parkSpace);
	/**
	 * 分页查询车位记录
	 * @return
	 */
	public Map<Integer, Object> findParkSpaceByCondition(ParkSpace parkSpace, int pageNo, int pageSize, String tag);
	/**
	 * 分页查询车位使用记录
	 * +
	 * @return
	 */
	public Map<Integer, Object> findParkSpaceUseInforByCondition(ParkSpaceUseInfor parkSpaceUseInfor, int pageNo, int pageSize);
	/**
	 * 根据id查询车位对象
	 * @param integer
	 * @return
	 */
	public ParkSpace ByidParkSpace(Integer integer);
	/**
	 * 根据blueaddress查询车位对象
	 * @param integer
	 * @return ParkSpace
	 */
	public ParkSpace ByblueaddressParkSpace(String address, String name);
	/**
	 * 根据bianhao查询车位对象
	 * @param integer
	 * @return ParkSpace
	 */
	public ParkSpace ByblueaddressParkSpace(String parkNum);
	/**
	 * 根据blueaddress列表
	 * @param integer
	 * @return ParkSpace
	 */
	public List ByblueaddressList(String address, String tel);
	/**
	 * 根据blueaddress列表
	 * @param integer
	 * @return ParkSpace
	 */
	public List ByblueaddressList();
	/************************************电话号码管理方法*****************************************/
	/**
	 * 添加SIM卡Id记录
	 * @return
	 */
	public String addSimCark(String simCark);
	/**
	 * 根据手机号码和SIM编号生成验证码
	 * @return
	 */
	public String addSecurity(SimCarkTel simCarkTel);
	/**
	 * 添加SIM卡记录
	 * @return
	 */
	public String addSimCarkTel(SimCarkTel simCarkTel);
	/**
	 * 修改SIM卡记录
	 * @return
	 */
	public String updateSimCarkTel(SimCarkTel simCarkTel, Integer id);
	
	/**
	 * 分页查询SIM卡记录
	 * @return
	 */
	public Map<Integer, Object> findParkSimCarkTelndition(SimCarkTel simCarkTel, int pageNo, int pageSize, String tag);
	/**
	 * 根据id查询SIM卡对象
	 * @param integer
	 * @return
	 */
	public SimCarkTel ByidSimCarkTel(Integer integer);
	/**
	 * 根据SIMid查询SIM卡对象
	 * @param String
	 * @return
	 */
	public SimCarkTel ByidSimCarkTel(String simId);
}
