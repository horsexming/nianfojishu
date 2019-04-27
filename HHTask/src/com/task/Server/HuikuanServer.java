package com.task.Server;

import java.io.File;
import java.util.List;

import com.task.entity.Sell;
import com.task.entity.TaHkBackMoney;
import com.task.entity.TaHkHkInvoice;
import com.task.entity.TaHkHuikuan;
import com.task.entity.TaHkPartBackMoney;
import com.task.entity.TaHkSellSta;
import com.task.entity.Users;
import com.task.entity.fin.budget.SubBudgetRate;

public interface HuikuanServer {
	public Object[] queryNoteSell(Sell sell,String startDate,String endDate,int pageNo, int pageSize);//获取出库记录
	
	public Integer findCou(Sell sell,String startDate,String endDate);//获得总数
	public List selectSellResult(Integer[] sellId);//获得选择出库记录的分类汇总
	public String findNoteId();//系统生成开票通知单编号
	public List addStaResult(TaHkHuikuan taHk);//返回添加报账记录明细
	public Object[] findshowNoteDetail();
	public boolean updateHk(TaHkHuikuan hk);
	public boolean saveHk(TaHkHuikuan hk,List<TaHkSellSta> hkset,
			File[] attachment,String[] attachmentFileName,String tag);//添加开票信息
	public List findNoteExam(TaHkHuikuan hk,TaHkSellSta tahkSellSta,String startDate,String endDate,
			int pageNo, int pageSize,String ifTag);//审批和查询
	public Integer findNoteExamCou(TaHkHuikuan hk,TaHkSellSta tahkSellSta,String startDate,String endDate,String ifTag);
	public String updateIders(Integer id,String tag,String idea);
	/***
	 * 保存送货单号	 
	 * @param tahk
	 * @param str
	 * @return
	 */
	public List findhkSta(TaHkHuikuan tahk,String str);
	
	public boolean saveSendNum(String sendNum, List<TaHkSellSta> hkset);
	//查询送货单号记录
	public List<TaHkSellSta> querySta(TaHkSellSta sta, String startDate, String endDate,
			int pageNo, int pageSize);
	public Integer findStaCou(TaHkSellSta sta);
	//根据送货单号获得送货明细列表
	public List<TaHkSellSta> selectStaResult(Integer[] sellId);
	public Object getObj(Integer id);
	//添加发票信息
	public String saveInvoice(TaHkHuikuan hk,List<TaHkSellSta> hkset,List<TaHkHkInvoice> invoiceArr,File[] attachment, String[] attachmentFileName,String[] attachmentStatus);
	public boolean deleteHK(Integer id);//删除回款记录	
	//删除送货单记录
	public boolean deleteSellSta(Integer id);
	//查询多个文件的地址
	public List findMoreFile(TaHkHuikuan hk);
	public List updateselectAlert();
	public List selectComp();
	public boolean saveBackMon(TaHkHuikuan taHk,TaHkBackMoney backMoney,File[] attachment, String[] attachmentFileName);//添加回款记录
	public String selectPrice(String markId,String isTax);//查询价格
	public String saveShoppingCard(Integer id);//添加到任务栏
	public List queryShopping(TaHkSellSta tahkSellSta,String startDate,String endDate);//管理任务栏
	public String deleteShopping(Integer id);//删除任务栏中的送货单任务
	public boolean updateInvoice(TaHkHuikuan hk,List<TaHkSellSta> hkset,List<TaHkHkInvoice> invoiceArr,
			File[] attachment, String[] attachmentFileName,String[] attachmentStatus);//重新开票
	//总经理强制关闭回款或打回重开发票
	public boolean updateCompulsory(TaHkHuikuan hk,String tag);
	
	/**
	 * 获得单个明细
	 * @param id
	 * @return
	 */
	public TaHkSellSta gethkSta(Integer id);
	/**
	 * 修改单个明细信息
	 * @param taHkSellSta
	 * @return
	 */	
	public boolean updateHKSta(TaHkSellSta taHkSellSta);

	public Object[] findExamList(int parseInt, int pageSize);

	public boolean updateExamOADetail(Integer[] detailSelect, String tag);
	/***
	 * 导出
	 * @param startDate
	 * @param endDate
	 */
	public void exportExcel(String startDate, String endDate);

	/***
	 * 查询件号是否存在
	 * @param hkset
	 * @return
	 */
	public String findPriceByPartNumber(List<TaHkSellSta> hkset);

	public TaHkHuikuan findtahk(TaHkHuikuan taHk);
    /**
     * 核对开票数量列表
     * @param taHk
     * @param tahkSellSta
     * @param startDate
     * @param endDate
     * @param parseInt
     * @param pageSize
     * @return
     */
	public List<TaHkHuikuan> findUncheckList(TaHkHuikuan taHk,
			TaHkSellSta tahkSellSta, String startDate, String endDate,
			int parseInt, int pageSize);
    /**
     * 展示申请数量和开票数量明显
     * @param taHk
     * @param tahkSellSta
     * @param startDate
     * @param endDate
     * @return
     */
	public int findUncheckcount(TaHkHuikuan taHk, TaHkSellSta tahkSellSta,
			String startDate, String endDate);
   /**
    * 核对开票数量
    * @param taHk
    * @param hkset
 * @param attachmentFileName 
 * @param attachment 
    * @return
    */
	public String checkBillCount(TaHkHuikuan taHk, List<TaHkSellSta> hkset, File[] attachment, String[] attachmentFileName);

   public List<TaHkSellSta> showKaiPiaoDetail(Integer id, String orderNum);
/**
 * 根据发票的明细的id 和 订单号查找对应的回款单
 * @param id
 * @param outOrderNumber
 * @return
 */
public List<TaHkBackMoney> showBackMoney(Integer id, String orderNum);
/**
 * 根据回款单号和订单号或者发票号和订单号和件号查询回款明细
 * @param id
 * @param outOrderNumber
 * @param markId
 * @return
 */
public List<TaHkPartBackMoney> showBackMoneyDatail(Integer id,
		String orderNum, String markId);
/**
 * 根据订单号和件号查询发票
 * @param outOrderNumber
 * @param markId
 * @return
 */
public List<TaHkHuikuan> showKaiPiaoDan(String outOrderNumber, String markId);
/**
 * 计算回款期限
 */
public void calculateLastTime();
/**
 * 通过发票id或者产品id查到对应的发票信息
 * @param id
 * @param tag
 * @return
 */
public List<TaHkHuikuan> findFaPiao(Integer id, String tag);


/**
 * 根据当前登录人查出他所绑定的银行账户;
 */

public List<SubBudgetRate> findBankSub(Users user);
}
