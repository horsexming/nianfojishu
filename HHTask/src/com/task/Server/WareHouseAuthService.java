package com.task.Server;

import java.util.List;

import com.task.entity.CompetenceType;
import com.task.entity.GoodsStore;
import com.task.entity.Sell;
import com.task.entity.WareHouseAuth;

public interface WareHouseAuthService {

	/**
	 * 添加
	 * 
	 * @param auth
	 */
	public void add(WareHouseAuth auth, String status);

	/**
	 * 批量添加
	 * 
	 * @param auth
	 */
	public void add(List<WareHouseAuth> auth);

	/**
	 * 列表
	 * 
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] getList(WareHouseAuth auth,int parseInt, int pageSize, String status);

	/**
	 * 通过ID拿到对象
	 * 
	 * @param auth
	 * @return
	 */
	public WareHouseAuth get(WareHouseAuth auth);

	/**
	 * 修改
	 * 
	 * @param auth
	 */
	public void update(WareHouseAuth auth);

	/**
	 * 删除
	 * 
	 * @param auth
	 */
	public void delete(WareHouseAuth auth);

	/**
	 * 判断某个人可以出哪些库
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getOut(String code);

	/**
	 * 判断某个人可以出哪些库
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getEdit(String code);
	/**
	 * 判断某个人可以删除库
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getDel(String code);

	/**
	 * 判断某个人是否有查看权限
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getView(String code);

	/**
	 * 判断某个人是否有入库权限
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getIn(String code);

	/**
	 * 判断某个人可以查看那些价格合同
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getShow(String code);

	/***
	 * @param code
	 * @return
	 */
	public List<String> getShow1(String code);

	/**
	 * 判断某个人可以查看那些价格合同上的价格信息
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getPrice(String code);

	/**
	 * 判断某个人可以编辑那些价格
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getUpdate(String code);

	/**
	 * 判断某个人可以在手机查看那些价格
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getPhone(String code);

	/**
	 * 判断某个人是否拥有查看订单的权限
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getDdShow(String code);

	/**
	 * 判断某个人是否拥有查看订单价格的权限
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getDdPrice(String code);

	/**
	 * 判断某个人是否拥有编辑订单的权限
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getDdUpdate(String code);

	/**
	 * 判断某个人可以在手机查看订单
	 * 
	 * @param code
	 * @return
	 */
	public List<String> getDdPhone(String code);

	public List<GoodsStore> printStorage(int[] selected);

	/**
	 * 查询所有的权限组别;
	 */
	List<CompetenceType> AllCompetenceType();

	/**
	 * 
	 */
	void test();

	List<String> ischecked(String quanxian);

	/***
	 * 查询所有仓区
	 * 
	 * @return
	 */
	List findgoodHouselist();
	/**
	 * 添加打印单
	 */
	public Object[] addPrintedOutOrder(List<GoodsStore> gsList,String pagestatus);
	
	public boolean getAuth1(String code);
}
