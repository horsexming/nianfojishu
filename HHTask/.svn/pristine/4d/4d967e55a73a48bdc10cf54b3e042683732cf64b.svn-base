package com.task.Server;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.task.entity.FanghuOutLib;
import com.task.entity.OutLib;
import com.task.entity.Users;



public interface IOutLibService {
	void add(OutLib ou);
	void del(OutLib ou);
	boolean update(OutLib ou);
	OutLib getOutLibById(int id);
	Object[] queryOutLib(Map map, int pageNo, int pageSize);
	void exportExcel(Map map);
	void delOutLibById(Integer id);
	/**
	 * 添加劳防用品
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuOutLib addFanghuOutLib(FanghuOutLib fanghuOutLib);
	/**
	 * 删除劳防用品
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuOutLib deleteFanghuOutLib(FanghuOutLib fanghuOutLib);
	/**
	 * 更新劳防用品
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuOutLib updateFanghuOutLib(FanghuOutLib fanghuOutLib);
	
	/**
	 * 获得劳防用品
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuOutLib getFanghuOutLibById(Integer id);
	/**
	 * 获得劳防用品集合
	 * @param fanghuYongpin
	 * @return
	 */
	public Object[] getFanghuOutLibList(FanghuOutLib fanghuOutLib,int pageNo,int pageSize);
	
	/**
	 * 查询所有用户
	 * @param 
	 * @return
	 */
	public Object[] getUserListAll(Users user,int pageNo,int pageSize);
	/**
	 * 根据员工卡号获取领取信息
	 * @param cardId
	 * @return
	 */
	Object[] scanCardLingyong(String cardId);
}
