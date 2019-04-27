package com.task.Server;

import java.util.Map;

import com.task.entity.ProductManager;

/**
 * @ClassName: IProductManagerService
 * @Description: 产品接口类
 * @author 曾建森
 * @date 2012-11-23 上午09:46:42
 */
public interface IProductManagerService {
	/**
	 * @Title: add
	 * @Description: 添加产品
	 * @param pm 
	 * @return void  
	 * @throws
	 */
	public void add(ProductManager pm);
	/**
	 * @Title: delProductManagerById
	 * @Description: 根据ID删除产品
	 * @param id 
	 * @return void  
	 * @throws
	 */
	public void delProductManagerById(int id);
	/**
	 * @Title: del
	 * @Description: 删除产品
	 * @param om 
	 * @return void  
	 * @throws
	 */
	public void del(ProductManager pm);
	/**
	 * @Title: update
	 * @Description: 修改产品
	 * @param om 
	 * @return void  
	 * @throws
	 */
	public void update(ProductManager pm);
	/**
	 * @Title: getProductManagerById
	 * @Description: 根据ID获取产品
	 * @param id
	 * @return 
	 * @return ProductManager  
	 * @throws
	 */
	public ProductManager getProductManagerById(int id);
	/**
	 * @Title: queryAllProductManager
	 * @Description: 查询所有产品
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]  
	 * @throws
	 */
	public Object[] queryAllProductManager(int pageNo, int pageSize);
	/**
	 * @Title: queryProductManagerByCondition
	 * @Description: 根据条件查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]  
	 * @throws
	 */
	public Object[] queryProductManagerByCondition(Map map, int pageNo,
			int pageSize);
	/**
	 * @Title: queryDetailByOrderById
	 * @Description: 根本订单ID查询订单明细
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]  
	 * @throws
	 */
	public Object[] queryDetailByOrderById(int id,Integer pageNo,Integer pageSize);
//	/**
//	 * 通过
//	 * @param goodsIds
//	 * @return
//	 */
//	public Map<String, Integer> getGoodsCount(Integer[] goodsIds);
	/**
	 * 添加产品
	 * @param selected
	 * @param num
	 * @param id
	 * @param goodsIds 
	 * @return
	 */
	public boolean addProduct(int[] selected, Float[] num, int id, Integer[] goodsIds);
	/**
	 * 判断是否可以修改数量
	 * @param id
	 * @param orderNum
	 * @return
	 */
	public String checkCanChangeNum(int id, String orderNum);
}
