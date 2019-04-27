package com.task.Server.caiwu.noncore;

import java.util.List;

import com.task.entity.caiwu.noncore.FinancialReceipts;
import com.task.entity.caiwu.noncore.NonCoreReceivables;
import com.task.entity.caiwu.noncore.NonCoreReceivablesDetail;

/**
 * 非主营业务应收接口
 * @author 贾辉辉
 *
 */
@SuppressWarnings("unchecked")
public interface NoncoreReceServer {
	/**
	 * 添加应收合同和协议
	 * @param nonCoreReceive
	 * @return
	 */
	public String saveNoncoreReceve(NonCoreReceivables nonCoreReceive);
	/**
	 * 获取应收合同或协议(id)
	 * @param id
	 * @return
	 */
	public NonCoreReceivables getNoncoreReveById(Integer id);
	/**
	 * 更新应收对象
	 * @param nonCoreReceive
	 * @return
	 */
	public String updateNoncoreRecevc(NonCoreReceivables nonCoreReceive);
	/**
	 * 删除一条应收对象
	 * @param id
	 * @return
	 */
	public String deleteNoncorereceiveById(Integer id);
	
	/**
	 * 查询所有总表Android
	 */
	public List findNonCoreReceivables(String tag);
	/**
	 * 获取应收对象列表
	 * @param nonCoreReceive
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findNoncoreReceiveList(NonCoreReceivables nonCoreReceive, String startDate, String endDate,
			Integer cpage, Integer pageSize,String tag);
	
	public Object[] findNonCoreReceivablesDetail(NonCoreReceivablesDetail nonCoreReceivablesDetail, Integer id,
			Integer cpage, Integer pageSize,String tag);
	
	public Object[] findFinancialReceipts(FinancialReceipts financialReceipts,
			Integer cpage, Integer pageSize);
	
	public List findNonCoreReceivablesDetailCai(String s);
	
	public List findNonCoreReceivablesDetailwsc();
	/**
	 * 后面内容为应收明细操作
	 */
	/**
	 * 保存应收明细
	 */
	public String saveReceveDetail(NonCoreReceivables nonCoreReceive, NonCoreReceivablesDetail nonCoreReceiveables);
	/**
	 * 更新应收单条明细
	 * @param nonCoreReceiveables
	 * @return
	 */
	public String updateReceiveDetail(NonCoreReceivablesDetail nonCoreReceiveables);
	
	
	public String updateReceiveDetailCai(NonCoreReceivablesDetail nonCoreReceiveables);
	/**
	 * 获取应收明细对象
	 * @param id
	 * @return
	 */
	public NonCoreReceivablesDetail getNoncoreReceiveDetail(Integer id);
	/**
	 * 根据id删除应收明细
	 * @param id
	 * @return
	 */
	public String deleteNoncoreReceiveDetailById(Integer id);
	/**
	 * 获取应收明细列表
	 * @param nonCoreReceiveables
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findNoncoreReceiveDetailList(NonCoreReceivablesDetail nonCoreReceiveables, String startDate, String endDate,
			Integer cpage, Integer pageSize,String tag);
	
	/**
	 * 确认加章后文件
	 * @return
	 */
	public String toaddNon(Integer id);
	
	/**
	 * 
	 * @param coreReceivablesDetail
	 * @return
	 */
	public String update2(NonCoreReceivablesDetail coreReceivablesDetail);
	/**
	 * 临时处理
	 */
	public void Text();
	
	public void jisuanNCR();
	
	/**
	 * 待确认
	 * @param nonc
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] findNonCoreReceivablesDetailDqr(NonCoreReceivablesDetail nonc,
			Integer cpage, Integer pageSize, String tag);
	/**
	 * 确认金额
	 * @param nonCoreReceivablesDetail
	 * @return
	 */
	public String updateReceiveDetailqr(
			NonCoreReceivablesDetail nonCoreReceivablesDetail);
	/**
	 * 取消
	 * @param nonCoreReceivablesDetail
	 * @return
	 */
	public String updateReceiveDetailqx(
			NonCoreReceivablesDetail nonCoreReceivablesDetail);
}
