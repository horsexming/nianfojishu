package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.ProductPrice;
import com.task.entity.ProductProcess;
import com.task.entity.SpareParts;
import com.task.entity.TaSopGongwei;

public interface ProductPriceServer {
	public Object[] queryProductPrice(ProductPrice priceProduct,
			Integer cpage,Integer pageSize);//查询所有总成件号信息
	public boolean saveProduct(ProductPrice priceProduct);//添加总成件号
	public ProductPrice getProductPrice(Integer id);//获取单个总成件号信息
	public boolean deleteProductPrice(Integer id);//删除单个总成件号
	public boolean updateProductPrice(ProductPrice priceProduct);//修改总成件号信息
	public List findSparePartsById(Integer id);//根据Id查询所有零件
	public boolean saveSpareParts(SpareParts spareParts,Integer id);//添加零件信息
	public Integer deleteSpareParts(Integer id);//删除零件信息
	public SpareParts getOneSpareParts(Integer id);//获取零租金啊
	public Integer updateSpareParts(SpareParts spareParts);//修改零件信息
	public List findProductProcessById(Integer id);//根据Id查询工序
	public SpareParts getSpareParets(Integer id);//根据I获取零件信息
	public String findGongwei(String str);//查找工位信息
	public String findShebeiCode(String gongwei);//根据工位查找设备编号
	public TaSopGongwei findGongweiAndOth(String gognweihao,String shebeiCode);//获取工位信息
	public boolean saveProductProcess(ProductProcess productProcess,Integer id);//添加工序
	public ProductProcess getProductProcess(Integer id);//获取单个工序信息
	public boolean updateProductProcee(ProductProcess productProcess);//修改工序信息
	public boolean deleteProductProces(ProductProcess productProcess);//删除工序
	public String findUserName(String code);//根据工号查询员工信息
	public boolean jisuanDanjianBonus(Integer[] peoductPriceId);//计算单价计价奖
	/**
	 * 
	 * @param startDate  试算开始日期
	 * @param endDate 试算截止日期
	 * @param peoductPriceId 试算的件号ID集合
	 * @return
	 */
	public boolean shizhijisuan(String startDate,String endDate,Integer[] peoductPriceId);
	/**
	 * @Title: trial
	 * @Description: 试算数据
	 * @param id 成品ID
	 * @return String 前台数据
	 */
	String trial(Integer id);
	
	String packageProduct(Integer id,Double mentioningAwardPrice);
	/**
	 * @Title: trialMentioningAwardPrice
	 * @Description: 试算
	 * @param id
	 * @return Map  
	 * @throws
	 */
	Map trialMentioningAwardPrice(Integer id);
	String packageData(Integer id,Map map);
	Double getBonus(Integer id);
	
	
	// 通过件号查询总成
	public ProductPrice findProductPriceByMkId(String mkId);
	
	
	public List findGongwei1(String tag);
	
	
}
