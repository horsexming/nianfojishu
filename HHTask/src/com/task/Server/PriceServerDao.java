package com.task.Server;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.AppLiPrice;
import com.task.entity.Price;
import com.task.entity.bargain.BarContract;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.zhuseroffer.ZhuserOffer;
import com.tast.entity.zhaobiao.ZhUser;

public interface PriceServerDao {

	// *************************************************价格类*******************************************************价格类
	public String addPrice(List<Price> priceList, Price price, File[] attachment,
			String[] attachmentFileName, String fatherPartNumber,Integer id);// 添加价格

	public List findAllPrice(int pageNo, int pageSize);// 查询所有价格

	public Map<Integer, Object> findPriceByCondition(Price price, int pageNo, int pageSize,String statue,String tags);// 条件查询

	public String findAttName(String conNumber);// 根据合同编号查找附件名

	public Price findPriceById(int id);// 通过id查询价格
	
	public boolean updatePrice(Price price);
	public boolean deletePrice(Price price);// 删除价格

	public String updatePrice(Price price, File[] attachment,
			String[] attachmentFileName, Price oldPrice);// 更改价格

	public int updateAttachmentName(Price price, String other);// 根据合同编号修改附件名称

	public int getCount();// 获得总数量

	public boolean updateFileName(Price price);// 修改档案号

	/**
	 * 根据件号查询合同信息(外委工序使用)
	 * 
	 * @return
	 */
	Price findPriceByMarkId(String markId);
	/**
	 * 根据件号查询合同信息
	 * 
	 * @return
	 */
	Price findPriceByMarkIdMaxTime(Price price);
	String findMaxfileNumberprice();
	
	/**
	 * 条件查询（有限期内）;
	 */
	public Map<Integer, Object> findPriceByCondition(Map map, int pageNo, int pageSize);
	
	/**
	 * 根据客户ID获得总成销售有限期内的
	 */
	public Map<Integer, Object> findPriceBykehuId(Map<String, String> map,int pageNo,
			int pageSize);
	
	/**
	 * 更改客户Id
	 */
	public void updatePriceKehuId(int[] idArray,Integer kehuId);
	/**
	 * 解除客户Id
	 */
	public boolean jiechuPriceKehuId(Integer id);
	/**
	 * 查询所有的供应商
	 * 
	 */
	public List<ZhUser> findAllZhUser();
	/**
	 * 批量导入外购件
	 */
	String PladdPrice(File addprice,String statue,String type);
	/**
	 * 根据件号 查出规格
	 */
	List<String> findSpecification(String markId);
	/**
	 * 根据件号 查出在价格有效期内的 总采购比例
	 */
	float findsumcgbl(String partNumber);
	/**
	 * 根据件号，名称，规格，查询price
	 */
	Price findeprice(Price price);
	/**
	 * 外购件根据件号联想查询
	 */
	public List<YuanclAndWaigj> getAllNames(YuanclAndWaigj yuanclAndWaigj);
	/**
	 * 检索原材料和外购件信息
	 */
	List<YuanclAndWaigj> searchAllNames(YuanclAndWaigj yuanclAndWaigj);
	/**
	 * 总成外委根据件号联想查询
	 */
	public Object[] getpdtList(ProcardTemplate procardTemplate);
	/**
	 * 根据件号，规格，名称， 查询出对应的工序
	 */
	public List<ProcessTemplate> getgongxunum(ProcardTemplate pt);
	/**
	 * 获取供应商的id和名称供ajax生成供应商下拉列表使用
	 * @return
	 */
	public List<Object[]> findZhUserIdAndName();
	/**
	 * 根据供应商Id 查出供应商信息
	 */
	public ZhUser finfZhuserByid(Integer id);
		
	/**
	 * 得到所有外购的采购合同编号;
	 */
	List<String> getAllHtNum();
	/**根据合同编号，查询合同和对应的所有合同明细
	 */
	BarContract getBarContractByNum(String contract_num);
	/**
	 * 根据priceId 获得某个procard外购件的价格，采购合同，采购执行单，询比议价，
	 * @param id
	 * @return
	 */
	Map<String, Object> getProcardMx(Integer id);
	/**
	 * 根据供应报价Id查询供应商报价信息;
	 */
	ZhuserOffer findZhuserOfferById(Integer id);
	/**
	 * 添加申请流程
	 * @param appLiPrice
	 * @return
	 */
	public String addAppLIPrice(Integer id);
	
	public Map<Integer, Object> findAllAppLiPrice(AppLiPrice appLiPrice,
			int pageNo, int pageSize, String status);
	/**
	 * 根据 供应商编号或者全称查找供应商
	 * @param gys
	 * @return
	 */
	public ZhUser findZhuserBygys(String gys);
	/**
	 * 通过总成件号查询BOM上的总成名称
	 */
	public ProcardTemplate findZCPtName(String markId);
	/**
	 * 获取改网络是否有访问权限
	 * @return
	 */
	public boolean getAccessPermission();
	/**
	 * 导出价格
	 */
	public void exportExcel(Price price,String status);
	
	/**
	 * 供货比例大小判断
	 */
	public String cgblpd(Price price);

	/**
	 * 未归档合同
	 * @param price
	 * @param parseInt
	 * @param pageSize
	 * @param statue
	 * @param tags
	 * @return
	 */
	public Map<Integer, Object> findPriceWeiCun(Price price, int parseInt,
			int pageSize, String statue, String tags);

	/**
	 * 确认柜号
	 * @param price
	 * @return
	 */
	public String querenGuihao(Price price);
	
	/**
	 * 批量把之前有限期内的价格失效掉，只保留最近录入的一条
	 */
	public void loseEfficacyPrice(Price price);
}

