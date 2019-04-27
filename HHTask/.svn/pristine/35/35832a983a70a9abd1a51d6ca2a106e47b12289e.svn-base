package com.task.Server.oa;

import java.util.List;

import com.task.entity.Oabusiness;
import com.task.entity.Oareimbursement;
import com.task.entity.Storage;

/***
 * 采购报账
 * 
 * @author 贾辉辉
 * 
 */
public interface OAreimBursementServer {
	/**
	 * 查询可以报账的入库记录
	 * 
	 * @param storage
	 *            条件查询对象
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] findStorageBaoZhang(Storage storage, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag);

	/***
	 * 查找报账记录 或可打印记录 总经理付款审核记录，财务付款审核记录，以及查看历史
	 * 
	 * @param oaReimBursement
	 *            报账查询对象
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 *            查询动作表示
	 * @param powerTag
	 *            权限标识 tag="print" && powerTag="shSD" 打印可采购 tag="exam" &&
	 *            powerTag="manager" 总经理审核 tag="history" && powerTag="manager"
	 *            总经理审核历史记录 tag="exam" && powerTag="shFD" 财务付款审核 tag="history"
	 *            && powerTag="shFD" 财务付款审核历史记录
	 * @return
	 */
	Object[] OAReimBursementList(Oareimbursement oaReimBursement,
			String startDate, String endDate, Integer cpage, Integer pageSize,
			String tag, String powerTag);

	/**
	 * 返回选择的Storage 列表
	 * 
	 * @param storageSelect
	 *            Storage的id数组
	 * @return
	 */
	Object[] findSelectedStorage(Integer[] storageSelect);

	/**
	 * 条件查询可报账的入库明细，下拉前台传过来的字段
	 * 
	 * @param tag
	 *            前台下拉字段属性
	 * @param powerTag
	 *            备用字段
	 * @return
	 */
	String findOASelect(String tag, String powerTag);

	/**
	 * 保存报账信息
	 * 
	 * @param oaReimBursement
	 *            报账对象
	 * @param storageSelect
	 *            Storage ID数组
	 * @return
	 */
	List saveBaozhang(Oareimbursement oaReimBursement, Integer[] storageSelect);

	/**
	 * 打印或查看报账单信息
	 * 
	 * @param id
	 *            报账表ID 返回报账明细list Business
	 * @return
	 */
	Object[] findBusinesByOBSId(Integer id);

	/**
	 * 报账下拉处理
	 * 
	 * @param tag
	 *            下拉属性
	 * @param powerTag
	 *            权限
	 * @return
	 */
	String selectItem(String tag, String powerTag);

	/**
	 * 打印更新报账状态 由可打印改为可付款
	 * 
	 * @param id
	 * @return
	 */
	boolean updateOBSById(Integer id);

	/**
	 * 查看报账明细表
	 * 
	 * @param business
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 *            //操作 exam 审核
	 * @param powerTag
	 *            //操起权限 manganger ，shFD ,shMD
	 * @return
	 */
	Object[] findOABusinessList(Oabusiness business, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag,
			String powerTag);

	/**
	 * 查找Business 下拉
	 * 
	 * @param tag
	 * @param powerTag
	 * @return
	 */
	String selectBusinessItem(String tag, String powerTag);

	/**
	 * 筛选
	 * 
	 * @param id
	 *            报账ID
	 * @return
	 */
	boolean updateSelectShaixuang(Integer id);

	/**
	 * 审核比价
	 * 
	 * @param id
	 * @return
	 */
	List findSameProductPrice(Integer id, String tag);

	/**
	 * 审核操作
	 * 
	 * @param id
	 *            business id
	 * @param tag
	 *            审核意见
	 * @param powerTag
	 *            权限
	 * @return 报账状态更改 返回list 报账状态为更改 返回报账id
	 */
	Object[] examBusiness(Integer id, String tag, String powerTag);

	/**
	 * 批量审核操作
	 * 
	 * @param id
	 *            business id
	 * @param tag
	 *            审核意见
	 * @param powerTag
	 *            权限
	 * @return 报账状态更改 返回list 报账状态为更改 返回报账id
	 */
	Object[] examlotBusiness(Integer[] ids, String tag, String powerTag);

	/**
	 * 打印或查看报账单信息
	 * 
	 * @param business
	 *            找张历史对象
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 *            (expor:导出，list：查询)
	 * @return
	 */
	Object[] findBusList(Oabusiness business, String startDate, String endDate,
			Integer cpage, Integer pageSize, String tag);

	/**
	 * 查找预报账历史记录
	 * 
	 * @param storage
	 *            条件查询对象
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] findPreBaoZhang(Storage storage, String startDate, String endDate,
			Integer cpage, Integer pageSize, String tag);

	/**
	 * 预报账审批
	 * 
	 * @param storageSelect
	 *            ，选择的预报账入库记录数组
	 * @param tag
	 *            审批状态（ok：同意；ng：打回）
	 * @return
	 */
	boolean updateStorageBZStatus(Integer[] storageSelect, String tag);

	/***
	 * 查询待付款的凭证(超过90天)
	 * 
	 * @return
	 */
	List findMaturityOabur();

}
