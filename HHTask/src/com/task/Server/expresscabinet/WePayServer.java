/**
 * 
 */
package com.task.Server.expresscabinet;
import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.expresscabinet.Charging;
import com.task.entity.expresscabinet.Courier;
import com.task.entity.expresscabinet.CourierCompany;
import com.task.entity.expresscabinet.WeiXinOrder;
/**
 * @author 梁盼
 *
 */
public interface WePayServer {
	
	/**
	 * 调用微信统一支付接口-梁盼-2018年12月29日11:18:24
	 * @param userId  用户id
	 * @param productId  商品id
	 * @return
	 */
	public String weixinPay(String userId,String productId,String order_price,String body);
	
	
	/**
	 * 快递柜收费标准查询
	 */
	public Map<Integer, Object> selectCharg(Charging charging,Integer pageIndex,Integer pageSize);
	
	/**
	 * 新增快递柜类型标准
	 */
	public boolean insertCharg(Charging charging);
	
	/**
	 * 按id查询收费标准对象
	 */
	public Charging selectCg(Charging charging);
	
	/**
	 * 修改快递柜收费标准
	 */
	public boolean updateCharg(Charging charging);
	/**
	 * 删除指定快递柜类型标准
	 */
	public boolean deleteCharg(Charging charging);
	
	/**
	 * 新增预订单信息
	 */
	public boolean insertWeiXinOrder(WeiXinOrder weixinOrder);
	
	/**
	 * 修改预订单信息
	 */
	public boolean updateWeiXinOrder(WeiXinOrder weixinOrder);
	
	/**
	 * 查询单个预订单信息
	 */
	public WeiXinOrder selectWinXinOrder(WeiXinOrder weixinOrder);
	
	/**
	 * 查询预订单集合信息
	 */
	public List<WeiXinOrder> selectWeixinOrderList(WeiXinOrder weixinOrder);
	
	/**
	 * 快递员手机号验证在数据中是否存在(不存在就发送验证码让其进行注册)
	 */
	public boolean selectCourier(String phoneNumber);
	
	/**
	 * 快递员记录新增
	 */
	public boolean insertCourier(Courier courier);
	
	/**
	 * 修改快递员信息状态
	 */
	public boolean updateCourier(Courier courier);
		
	/**
	 * 验证收件人手机号是否在数据中存在
	 */
	public Users selectUsers();
	
	
	/**
	 * 查询所有快递公司
	 */
	public List<CourierCompany> selectCouCompany();
	
    /**
     * 查询快递员录入的信息
     */
	public Courier selectEpIdCourier(Integer couId);

}
