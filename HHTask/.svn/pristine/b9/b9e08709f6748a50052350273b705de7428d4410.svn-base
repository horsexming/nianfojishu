package com.task.Server.ess;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.Goods;
import com.task.entity.ess.WasteDisponsal;
import com.task.entity.ess.WasteDisponsalTotal;

/**
 * 报废品处理
 * @author wcy
 */
public interface WasteDisponseService {
	
	/**
	 * 生成报废单
	 * @param goodsList
	 * 		goodsId  报销ID
	 * 		num  	 报销ID的数量
	 * @return 执行结果
	 */
	public boolean createGeneratorManage(List<WasteDisponsal> wasteList,WasteDisponsalTotal total);
	
	/**
	 * 再次生成报废单
	 * @param goodsList
	 * @return
	 */
	public boolean reapplywd(List<WasteDisponsal> goodsList,WasteDisponsalTotal total);
	
	
	
	/**
	 * 显示审批的报废单
	 * 根据查询条件
	 * @return
	 */
	public List<WasteDisponsalTotal> showwdtList(int pageNo, int pageSize,String proposer,String strDate,String endDate,String tag);
	
	/**
	 * 查询单个报废单
	 * @param id
	 * @return
	 */
	public List<WasteDisponsal> findwdListByTotalId(Integer id);
	
	/**
	 * 根据epId查找报废单
	 */
	public WasteDisponsalTotal getWDTByEpId(Integer epId);
	/**
	 * 根据totalId获得报废表
	 * @param totalId
	 * @return 报废表
	 */
	public WasteDisponsalTotal getwdtById(Integer totalId);
	
	public List<WasteDisponsal> findWasteDisponsals(Integer totalId);
	/**
	 * 删除报废数据
	 */
	public boolean deletewdt(Integer totalId);
	
	/**
	 * 删除详细
	 * @param id
	 * @return
	 */
	public boolean deleteDisposal(Integer id);
	
	/**
	 * 修改报废品
	 *//*
	public boolean updatewdt(Integer totalId,List<WasteDisponsal> wdList);*/
	
	/**
	 * 根据goodsId查找剩余库存数量
	 */
	public Float getGoodsCurquantity(Integer goodsId);
	
	/**
	 * 打印and签名
	 * @param id
	 * @return
	 */
	public Map<Integer, Object> findPayExecutionNode(Integer id);
	
	/**
	 * 根据id查找Goods
	 * @param goodsId
	 * @return
	 */
	public Goods getGoodsByGoodsId(Integer goodsId);
	
	/**
	 * 添加废品单详细
	 * @param wasteDisponsal
	 * @param goodsId
	 * @return
	 */
	public String addWasteDisponse(WasteDisponsal wasteDisponsal,Integer goodsId);
	
	public List<WasteDisponsal> showWasteApply();
	
	public String updatePrice(List<WasteDisponsal> list,File[] attachment,String[] attachmentFileName);

	/**
	 * 生成报废单的总数
	 * @return
	 */
	int showWDCount(String proposer, String strDate, String endDate, String tag);
	
	List<WasteDisponsalTotal> findNotPriceAndEP();
	List<WasteDisponsalTotal> findBePriceNotEp();
}
