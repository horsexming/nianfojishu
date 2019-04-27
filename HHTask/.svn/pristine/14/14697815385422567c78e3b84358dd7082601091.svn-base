package com.task.Server.menjin;

import java.util.Map;

import com.task.entity.menjin.Access;

public interface AccessServer {
	/**
	 * 接收车牌
	 * @return
	 */
//	public String ReceivingCards();
	/**
	 * 开门
	 * @return
	 */
//	public boolean openDoor(String ip);
	public String oneOpen();
	public String oneOpenXin();
	/**
	 * 添加门禁记录
	 * @return
	 */
	public String addAccess();
	/**
	 * 修改门禁记录
	 * @return
	 */
	public boolean updateAccess(Access access);
	/**
	 * 删除门禁记录
	 * @return
	 */
	public boolean deleteAccess(Access access);
	/**
	 * 分页查询门禁记录
	 * @return
	 */
	public Map<Integer, Object> findAccessByCondition(Access access, int pageNo, int pageSize);
	/**
	 * 根据车牌和获取时间以及（进出）类型来查找是否有允许进出记录 如果有 则返回对象
	 * @param carPai 车牌
	 * @param carTime 获取时间
	 * @param outIn 进出
	 * @return Access 来访对象
	 */
	public Access findOneAccess(String carPai, String carTime, String outIn);
	/**
	 * 判断车牌能否开门流程
	 * @param informartion 车牌信息
	 */
	public void carPaiVerifyImpl(String informartion);
}
