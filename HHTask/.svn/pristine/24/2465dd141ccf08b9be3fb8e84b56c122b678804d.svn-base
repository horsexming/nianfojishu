package com.task.Server;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.Evaluators;
import com.task.entity.OrderManager;
import com.task.entity.ProductManager;
import com.task.entity.TaHkHuikuan;
import com.task.entity.Users;
import com.task.entity.sop.Procard;
import com.task.entity.sop.YcProduct;
import com.task.entity.sop.YcWaiGouProcrd;
import com.task.entity.sop.YcWeekFePei;

public interface IOrderManagerService {
	/**
	 * @Title: add
	 * @Description: 添加
	 * @param om
	 * @return void
	 * @throws
	 */
	public String add(OrderManager om, List<ProductManager> pmList);

	/**
	 * @Title: delOrderManagerById
	 * @Description: 根据ID删除
	 * @param id
	 * @return void
	 * @throws
	 */
	public void delOrderManagerById(int id);

	/**
	 * @Title: del
	 * @Description: 删除对象
	 * @param om
	 * @return void
	 * @throws
	 */
	public void del(OrderManager om);

	/**
	 * @Title: update
	 * @Description: 修改对象
	 * @param om
	 * @return void
	 * @throws
	 */
	public String update(OrderManager om, String status);

	/**
	 * @Title: getOrderById
	 * @Description: TODO
	 * @param id
	 * @throws Exception
	 * @return OrderManager
	 */
	public OrderManager getOrderById(int id);

	/**
	 * @Title: queryAllOrderManager
	 * @Description: 查询所有订单
	 * @param pageNo
	 * @param pageSize
	 * @throws Exception
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAllOrderManager(int pageNo, int pageSize, Users users);

	/**
	 * @Title: queryOrderManagerByCondition
	 * @Description: 根据条件查询所有订单
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryOrderManagerByCondition(Map map, int pageNo,
			int pageSize, String status, String tag);

	/**
	 * @Title: queryOrderByClientById
	 * @Description: 根据客户 ID查询订单
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryOrderByClientById(int id, int pageNo, int pageSize);

	/**
	 * @Title: updateOr
	 * @Description: 修改对象
	 * @return boolean
	 * @throws
	 */
	public boolean updateOr(OrderManager om);

	public Object[] queryNotConversionOrder(OrderManager om, String beginTime,
			String endTime, Integer customeIdm, int pageNo, int pageSize,
			String tag);

	/***
	 * 通过订单id查询对应内部计划信息
	 * 
	 * @param orderId
	 *            订单id
	 * @return
	 */
	List findInternalOrder(Integer orderId);

	/****
	 * 订单一条龙纠错
	 */
	void updateOrder(OrderManager om);

	/****
	 * 获得当前月和上个月的订单信息
	 * 
	 * @return
	 */
	List findOrderFormMonth();

	List findOrderIdsFormMonth();

	/***
	 * 根据月份查询该月的订单完成率
	 * 
	 * @param month
	 *            月份
	 * @return
	 */
	Float getCompletionrateByMonth(String month);

	/**
	 * 更新订单完成率为空的订单
	 */
	void updateCompletionrate();

	/**
	 * 保证订单编号的唯一性
	 * 
	 * @param orderNum
	 *            订单编号
	 * @param type
	 *            订单编号类型1：内部2：外部
	 * @return
	 */
	public String checkOrderNumber(Integer id, String orderNum,
			String outOrderNum);

	/**
	 * 通过内部订单号获取到对应的发票信息
	 * 
	 * @param outOrderNumber
	 * @return
	 */
	public List<TaHkHuikuan> gettaHkHuikuanList(String orderNum);

	public String getOutNumerByNumber(String orderNum);

	/***
	 * 计算订单成本
	 * 
	 * @param orderId
	 *            订单id
	 * @return
	 */
	void orderChengben(Integer orderId);

	/****
	 * 查询该订单下的对应产品信息
	 * 
	 * @param orderId
	 * @return
	 */
	List findProcardByOrderId(Integer orderId, String flag);

	/**
	 * 查询该总成下零件的成本
	 * 
	 * @param id
	 * @return
	 */
	public List<OrderManager> findProcardByRootId(int id);

	/***
	 * 根据订单查询对应生产批次
	 * 
	 * @param orderId
	 * @return
	 */
	List findProcardByOrder(Integer orderId);

	public String removeProduct(int id);

	public String shuaxin(int productId, int priceId);

	/***
	 * 查询待交付的产品信息
	 * 
	 * @return
	 */
	List findDjfProduct();

	/**
	 * 在途订单导入
	 */
	String pladdorder(File addorder);

	/**
	 * 自动获得内部订单编号
	 * 
	 * @return
	 */
	String getorderNum(String type);

	/**
	 * 获取该件号相关备货订单数量
	 * 
	 * @param markId
	 * @return
	 */
	public Float getbfCount(String markId);

	/**
	 * 获取对应零件的备货冲销数据
	 * 
	 * @param markId
	 * @return
	 */
	public List<ProductManager> getBhPmList(String markId,String ywMarkId);

	/**
	 * @Title: getOrderById
	 * @Description: 根据OrderID获取订单成本动态
	 * @param id
	 * @throws Exception
	 * @return OrderManager
	 */
	Object[] getOrderByIdForCb(int id);

	/**
	 * 查询所有ycproduct
	 */
	Object[] findAllYcProduct(YcProduct ycProduct, int pageNo, int pageSize,
			String status);

	/**
	 * 添加预测产品每周预分配量
	 */
	public String addycWeek(Integer id, List<YcWeekFePei> ycweekList);

	/**
	 * 根据预测产品Id查询预分配周;
	 */
	public Object[] findycWeek(Integer id);

	/**
	 *添加外购预分配记录
	 */
	public String addYcWaiGouProcrd(Integer id);

	/**
	 * 根据预测产品Id查询预采购外购件明细
	 */
	Object[] findycwgProcardList(YcWaiGouProcrd ycwgprocard, int pageNo,
			int pageSize, String status);

	/**
	 * 判断是否可以进行申购;
	 */
	public String issqcg(Integer id);

	/**
	 * 根据id查询产品信息
	 */
	public ProductManager findProductManager(Integer id);

	/****
	 * 提交订单进入审核
	 */
	String updateOrderForsubmit(Integer id);
	
	/**
	 * 导出预测产品
	 */
	String exportExcelYc(YcProduct ycproduct);
	
	/**
	 * 导出预测产品
	 */
	String exportExcelYcWg(YcWaiGouProcrd ycwg);
	/**
	 * 正式订单关联备货
	 * @param id 正式订单id
	 * @param ids 备货订单id
	 * @return
	 */
	public String relateBh(int id, String ids);
	ProductManager 	updatejq(Integer id,String paymentDate);
	
	/**
	 * 导出订单信息
	 */
	void exprot(Map map, String flag,String status);
	
	/**
	 * 纠正件号
	 */
	String JiuzhengMarkId(Integer id);
	
	/**
	 * 反审核订单
	 * @param id
	 * @return
	 */
	String fansheng(Integer id);
	/**
	 * 申请取消订单
	 */
	String removeSqProduct(Integer id,Float qxNum);
	/**
	 * 添加评审组别人员
	 */
	String addEvaluators(Evaluators evaluators);
	/**
	 * 查询评审组别人员
	 */
	Object[] findAllEvaluators(Evaluators evaluators,Integer pageSize,Integer pageNo,String status);
	/**
	 * 删除评审组别人员
	 */
	String delEvaluators(Evaluators evaluators);
	/**
	 * 修改评审组别人员
	 */
	String updateEvaluators(Evaluators evaluators);
	/**
	 * 
	 */
	Evaluators  findEvaluatorsById(Integer id);
	/**
	 * 查询所有暂停的生产单
	 */
	Object[] findAllZTProcard(Procard procard,Integer pageNo,Integer pageSize,String status);
	
	/**
	 * 生成评审信息表
	 */
	
	String saveProcardPingSheng(Integer id);
	
	
	
}
