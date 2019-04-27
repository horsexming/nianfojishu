package com.task.Server.caiwu;

import java.util.List;

import com.task.entity.Credentials;
import com.task.entity.caiwu.CaiwuRecharge;
import com.task.entity.caiwu.CarAllowOneApp;
import com.task.entity.caiwu.CarAllowSumApp;

/**
 * 
 * @author 贾辉辉
 *车补申请server
 */
public interface CarAllowServer {
	/**
	 * 添加汇总表
	 * @param carAllowSumApp
	 * @return
	 */
	public boolean saveCarAllow(CarAllowSumApp carAllowSumApp);
	
	/**
	 * 更新申请汇总表
	 * @param carAllowSumApp
	 * @return
	 */
	public boolean updateCarAllow(CarAllowSumApp carAllowSumApp);
	
	/**
	 * 根据ID查找单个申请记录
	 * @param id
	 * @return
	 */
	public CarAllowSumApp getOneCarAllow(Integer id);
	/**
	 * 根据工号查找个人的申请汇总表
	 * @param code
	 * @return
	 */
	public CarAllowSumApp getOneCarAllowByCode(String code);
	/**
	 * 分页显示所有的申请汇总
	 * @param carAllowSumApp
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findsumAppList(CarAllowSumApp carAllowSumApp,
			Integer cpage, Integer pageSize,String tag);
	
	/*
	 * 第二部分操作添加单条申请记录
	 * */
	/**
	 * 添加个人车补申请
	 */
	public boolean saveOneAllowAPP(Integer id,CarAllowOneApp oneAllow);
	/**
	 * 查看个人申请的所有记录
	 * @param oneAllow
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findOneAppList(CarAllowOneApp oneAllow, String startDate, String endDate,
			Integer cpage, Integer pageSize,String tag);
	/**
	 * 删除单个申请记录
	 * @param id
	 * @return
	 */
	public boolean deleteOneapp(Integer id);
	
	/**
	 * 测试删除单个补贴申请
	 * @return
	 */
	public boolean deletesumAllow(Integer id);
	/*
	 * 第三部分操作添加财务充值及报销记录
	 * */
	/**
	 * 添加财务充值或报销
	 */
	public boolean saveRecharge(CaiwuRecharge recharge);
	/**
	 * 查看报销或充值记录
	 * @param recharge
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findRechargeList(CaiwuRecharge recharge, String startDate, String endDate,
			Integer cpage, Integer pageSize,String tag);

	Object[] findoneappHistory(Integer id, Integer cpage, Integer pageSize);

	Object[] findChongzhiHistory(Integer id, Integer cpage, Integer pageSize,
			String tag);
	boolean shezhiXiane(Integer id, CarAllowSumApp carAllowOne);

	List<CarAllowSumApp> findsumList();

	List chongzhi(Integer[] listId, Float[] chongzhi);

	boolean deleteOneHistory(Integer id);

	boolean deleteOneCZHistory(Integer id);

	Credentials getPlatNumber(String code);

	String savenutieApp(Integer id, CaiwuRecharge caiwuRecharge);

	boolean updateSum();
}
