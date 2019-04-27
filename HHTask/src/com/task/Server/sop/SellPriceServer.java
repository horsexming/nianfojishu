package com.task.Server.sop;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.Price;
import com.task.entity.sop.SellPrice;

/**
 * 销售价格接口
 * @author 聂威
 *
 */
public interface SellPriceServer {
 /**
  * 添加销售价格
  * @param sellPrice
  * @param attachment
  * @param attachmentFileName
  * @param fatherPartNumber
  * @return
  */
	boolean addSellPrice(SellPrice sellPrice, File[] attachment,
			String[] attachmentFileName, String fatherPartNumber);
 /**
  * 查找所有销售价格
  * @param parseInt
  * @param pageSize
  * @return
  */
	List findAllSellPrice(int parseInt, int pageSize);
 /**
  * 获得总数量
  * @return
  */
	int getCount();
 /**
  * 查询
  * @param sellPrice
  * @param parseInt
  * @param pageSize
  * @return
  */
	Object[] findSellPriceByCondition(SellPrice sellPrice,
			int parseInt, int pageSize);
 /**
  * 通过ID查找销售价格
  * @param id
  * @return
  */
	SellPrice findSellPriceById(int id);

 

/**
 * 删除销售价格	
 * @param sellPrice
 * @return
 */
  boolean deleteSellPrice(SellPrice sellPrice);
/**
 * 修改销售价格
 * @param sellPrice
 * @return
 */
boolean updateSellPrice(SellPrice sellPrice, File[] attachment,
		String[] attachmentFileName, String fatherPartNumber);
/**
 * 获取所有客户名称
 * @return
 */
List<String> findClientNameList();



	

	

}
