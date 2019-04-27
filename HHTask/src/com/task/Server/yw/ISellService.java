package com.task.Server.yw;

import java.util.Map;

import com.task.entity.Sell;

/**
 * @Title: ISellService.java
 * @Package com.task.Server.yw
 * @Description: TODO 出库明细
 * @author 曾建森
 * @date 2012-10-31 下午12:46:50
 * @version V1.0
 */
public interface ISellService {
	/**
	 * @Title: getSellById
	 * @Description: 根据ID查询出库明细
	 * @param @param id
	 * @param @return 
	 * @return Sell
	 * @throws
	 */
	public Sell getSellById(int id);
	/**
	 * @Title: querySellByBusinessId
	 * @Description: 查询当前业务ID中没有的业务
	 * @param @param id
	 * @return Object[]
	 * @throws
	 */
	public Object[] querySellByBusinessIdAndCondition(Map map,int id,int pageNo,int Pagezi);
}
