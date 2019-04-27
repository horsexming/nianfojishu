package com.task.Server;

import java.util.Map;

import com.task.entity.ClientManagement;
import com.task.entity.PieceNum;
import com.task.entity.Price;

/**
 * @ClassName: IPieceNumService
 * @Description: 产品件号接口
 * @author 曾建森
 * @date 2012-11-26 下午02:03:12
 */
public interface IPieceNumService {
	/**
	 * @Title: add
	 * @Description: 添加件号
	 * @param pm 
	 * @return void  
	 * @throws
	 */
	public void add(PieceNum pn);
	/**
	 * @Title: delPieceNumById
	 * @Description: 根据ID删除件号
	 * @param id 
	 * @return void  
	 * @throws
	 */
	public void delPieceNumById(int id);
	/**
	 * @Title: del
	 * @Description: 删除件号
	 * @param om 
	 * @return void  
	 * @throws
	 */
	public void del(PieceNum pn);
	/**
	 * @Title: update
	 * @Description: 修改件号
	 * @param om 
	 * @return void  
	 * @throws
	 */
	public void update(PieceNum pn);
	/**
	 * @Title: getPieceNumById
	 * @Description: 根据ID获取件号
	 * @param id
	 * @return PieceNum  
	 * @throws
	 */
	public Price getPieceNumById(int id);
	/**
	 * @Title: queryAllPieceNum
	 * @Description: 查询所有的件号
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]  
	 * @throws
	 */
	public Object[] queryAllPieceNum(int pageNo, int pageSize,String pagestatus);
	/**
	 * @Title: queryPieceNumCondition
	 * @Description: 根据条件查询所有件号
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]  
	 * @throws
	 */
	public Object[] queryPieceNumCondition(Map map, int pageNo,
			int pageSize,String ddType);
	/**
	 * @Title: querySelectedProduct
	 * @Description: 查询所有选择的产品
	 * @param selected
	 * @return List  
	 * @throws
	 */
	public Object[] querySelectedProduct(int[] selected);
	/**
	 * @Title: getPriceByPieceNum
	 * @Description: 根据件号查询价格
	 * @param num
	 * @return Price  
	 * @throws
	 */
	public Price getPriceByPieceNum(String num);
	
	/**
	 * 根据客户ID 查询客户信息
	 */
	public ClientManagement getClientManagementbyid(Integer id);
	
	public Price getPriceByMarkId(String markId,Map map);
}
