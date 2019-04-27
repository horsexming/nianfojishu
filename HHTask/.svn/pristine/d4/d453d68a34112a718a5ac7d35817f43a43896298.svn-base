package com.task.Server;

import java.util.Map;

import com.task.entity.UserFacialFeatures;
import com.task.entity.UserFacialInfor;
import com.task.entity.Users;

/**
 * 用户对接信息表接口
 * @author Li_Cong
 * @Date 2017-11-20
 */

public interface UserFacialInforServer{
	/**
	 * 批量添加
	 * @param UserFacialInfor
	 * @return
	 */
	public void addUser();
	/**
	 * 批量添加
	 * @param UserFacialInfor
	 * @return
	 */
	public void addHSUser();
	/**
	 * 添加
	 * @param UserFacialInfor
	 * @return
	 */
	public String selectBinAdd(String code);
	
	/**
	 * 根据工号查询用户对接信息表是否存在
	 * @param code
	 * @return
	 */
	public UserFacialInfor ByCodeUserFacialInfor(String code);
	/**
	 * 根据id 查询用户对接信息表是否存在
	 * @param code
	 * @return
	 */
	UserFacialInfor ByCodeUserFacialInfor(Integer id);
	
	/**
	 * 修改 UserFacialInfor
	 * @param users
	 * @return
	 */
	public String updateUserFacialInfor(Users users);
	/**
	 * 分页查询对接信息记录
	 * @return
	 */
	public Object[] findUserFacialInfor(UserFacialInfor infor, int pageNo, int pageSize);
	
	/**
	 * 删除对接信息
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String deleteUserFacialInfor(Integer id);// 删除

	

	/************************************面部特征信息******************************************/
	
	/**
	 * 添加
	 * @author Li_Cong
	 * @param facialFeatures 
	 * @return
	 */
	public String addUserFacialFeatures(UserFacialFeatures facialFeatures);// 添加

	/**
	 * 修改
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String updateUserFacialFeatures(UserFacialFeatures facialFeatures);// 修改

	/**
	 * 删除
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String deleteUserFacialFeatures(Integer id);// 删除

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public UserFacialFeatures byIdUserFacialFeatures(Integer id);// 根据id得到对象

	/**
	 * 分页查询
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Object[] findUserFacialFeatures(UserFacialFeatures facialFeatures, int pageNo, int pageSize);// 分页查询对象
	
	/**
	 * 打卡类型
	 * @param facialFeatures 
	 * @param equipmentId 面部识别设备唯一标识
	 */
	public String addAttendanceInfor(String userNo, String openDoorType,String equipmentId);
	
	/**
	 * 打卡
	 * @param userFeatures 面部识别设备唯一标识
	 * @param equipmentId 设备
	 */
	public UserFacialFeatures addAttendanceInfor(String userFeatures,String equipmentId);
	
	/**
	 * 得到所有待下发信息
	 * @param aceId 设备ID
	 * @return
	 */
	public Map<String, Object> pindList(String aceId, String type);
	
}
