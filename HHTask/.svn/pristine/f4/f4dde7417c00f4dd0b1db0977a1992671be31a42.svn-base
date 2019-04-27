package com.task.Server.fin;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.GoodsStore;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.fin.BaoxiaoDan;
import com.task.entity.fin.BaoxiaoDetail;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.payee.Payee;

/**
 * 报销单server
 * @author jhh
 *
 */
public interface BaoXiaoDanServer {
	/**
	 * 添加报销单
	 * @param baoxiaodan
	 * @return
	 */
	public boolean saveBaoXiaoDan(BaoxiaoDan baoxiaodan,List<BaoxiaoDetail> listDetail, Float money1, String money2);
	/**
	 * 添加查询报销单
	 * @param baoxiaodan
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findBaoXiaoDan(BaoxiaoDan baoxiaodan, String startDate, String endDate,
			Integer cpage, Integer pageSize,String tag);
	/**
	 * 更新报销单
	 * @param baoxiaodan
	 * @param listDetail
	 * @return
	 */
	public boolean updateBaoXiaoDan(BaoxiaoDan baoxiaodan,List<BaoxiaoDetail> listDetail,String money2,Float money1,Float oldmoney);
	/**
	 * 根据ID获取报销单对象
	 * @param id
	 * @return
	 */
	public BaoxiaoDan getBaoXiaoDanById(Integer id);
	/**
	 * 删除报销单
	 * @param id
	 * @return
	 */
	public boolean deleteBaoXiaoDan(Integer id);
	/**
	 * 数据汇总
	 * @param dept 报销部门
	 * @param baoxiaoStyle 报销类型
	 * @param startDate  开始时间
	 * @param endDate 截止时间
	 * @param cpage 当前页
	 * @param pageSize 页面大小
	 * @return
	 */
	public Object[] findSumBaoXiaoDan(BaoxiaoDan baoxiaodan,String startDate, String endDate,
			Integer cpage, Integer pageSize);
	/**
	 * 查找报销类型
	 * @return
	 */
	public String findBaoXiaoStyle(String tag);
	/**
	 * 导出数据
	 * @param tag 标识
	 */
	public void exportExcel(BaoxiaoDan baoxiaodan,String startDate, String endDate,String tag);
	/**
	 * 查找子类
	 * @param tag
	 * @return
	 */
	public String findchildClass(String tag);
	/**
	 * 查询所有报销明细
	 * @param baoxiaodan
	 * @param detail
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findBaoXiaoDanDetail(BaoxiaoDan baoxiaodan,BaoxiaoDetail detail,String startDate,String endDate,
			Integer cpage,Integer pageSize,String tag);
	/**
	 * 导出报销明细数据
	 * @param baoxiaodan
	 * @param detail
	 * @param startDate
	 * @param endDate
	 * @param tag
	 */
	public void exportDetailExcel(BaoxiaoDan baoxiaodan,BaoxiaoDetail detail,String startDate,String endDate,String tag);
	/**计算报销月份*/
	String getPlanMonth();
	/**
	 * 根据报销类型选择部门
	 * @param tag 本部门，多部门
	 * @param planMonth 月份
	 * @return
	 */
	String findchildDept(String tag,String planMonth);
	/**
	 * 根据部门查找下拉的预算科目
	 * @param tag
	 * @param planMonth
	 * @return
	 */
	List findchildSubjects(String tag,String planMonth);
	/***
	 * 比较预算金额与报销金额大小
	 * @param id 预算ID
	 * @param money
	 * @return
	 */
	String compareBudgetCount(Integer id,Float money);
	/**
	 * 根据ID查找部门月度预算
	 * @param id
	 * @return
	 */
	DeptMonthBudget getdeptMonthBudgetById(Integer id);
	/**
	 * 查找单条明细
	 * @param id
	 * @return
	 */
	BaoxiaoDetail getbxDetailById(Integer id);
	/**
	 * 查找跨部门确认记录
	 * @param baoxiaodan
	 * @param detail
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] findOtherDeptBXD(BaoxiaoDan baoxiaodan,BaoxiaoDetail detail,String startDate,String endDate,
			Integer cpage,Integer pageSize,String tag);
	/***
	 * 跨部门确认查询
	 * @param id
	 * @param tag
	 * @return
	 */
	boolean updateDetailById(Integer id,String tag);
	/***
	 * 部门确认下拉
	 * @param tag
	 * @return
	 */
	String findDeptConfirm(String tag);
	/***
	 * 财务确认
	 * @param id
	 */
	public void updateXiaoDan(Integer id);
	/***
	 * 查找入库表件号
	 * @return
	 */
	public String findjianhao();
	/***
	 * 根据件号查询批次
	 * @param tag
	 * @return
	 */
	public List findpici(String tag);
	/***
	 * 根据件号和批次查询数量
	 * @param tag
	 * @param goodsStoreLots
	 * @return
	 */
	public GoodsStore findshuliang(String tag, String goodsStoreLots);
	/***
	 * 添加生产性报销单
	 * @param baoxiaodan
	 * @param listDetail
	 * @param money1
	 * @param money2
	 * @return
	 */
	public boolean saveBaoXiaoDan1(BaoxiaoDan baoxiaodan,
			List<BaoxiaoDetail> listDetail, Float money1, String money2,String tag,List<Integer> ids);
	/***
	 * 查询管理报销单(生产)
	 * @param baoxiao
	 * @param startDate
	 * @param endDate
	 * @param parseInt
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Object[] findBaoXiaoDan1(BaoxiaoDan baoxiao, String startDate,
			String endDate, int parseInt, int pageSize, String tag);
	/***
	 * 更新报销单(生产)
	 * @param baoxiaodan
	 * @param listDetail
	 * @param money2
	 * @param money1
	 * @param oldmoney
	 * @return
	 */
	public boolean updateBaoXiaoDan1(BaoxiaoDan baoxiaodan,
			List<BaoxiaoDetail> listDetail, String money2, Float money1,
			Float oldmoney);
	/***
	 * 导出
	 * @param baoxiaodan
	 * @param startDate
	 * @param endDate
	 * @param tag
	 */
	public void exportExcel1(BaoxiaoDan baoxiaodan, String startDate,
			String endDate, String tag);
	public Object[] findBaoXiaoDan2(BaoxiaoDan baoxiaodan2, String startDate,
			String endDate, int parseInt, int pageSize, String tag);
	public void exportExcel2(BaoxiaoDan baoxiaodan, String startDate,
			String endDate, String tag);
	public void updateXiaoDan2(Integer id);
	/**
	 * 报销科目
	 * @return
	 */
	public List findchildSubjects2(String tag);
	public List findchildSubjects3(String tag);
	/**
	 * 查找要报销的零件
	 * @param tag
	 * @return
	 */
	public List findjianhao2(String tag);
	/**
	 * 查询外委供应商
	 * @param tag
	 * @return
	 */
	public List findWaiweiGys(String tag);
	/**
	 * 通过供应商查找外委订单
	 * @param id
	 * @return
	 */
	public List findpiWaiwei(String id);
	/**
	 * 通过供应商查找外购订单
	 * @param id
	 * @return
	 */
	public List findpiWaigou(String id);
	/**
	 * 查询外购供应商
	 * @param tag
	 * @return
	 */
	public List findWaigouGys(String tag);
	/**
	 * 查询非主营业务
	 * @param tag
	 * @return
	 */
	public List findfzyBusiness(String tag);
	/**
	 * 根据收费类型查询非主营业务应付信息
	 * @param tag
	 * @return
	 */
	public List findfzykm(String tag);
	
	public Map<Integer, Object> findAll(Payee payee, int pageNo, int pageSize);
	public String save(Payee payee);
	public String importFile(File importFile);
	public Payee findOneById(Integer id);
	public Payee findByName(String name);
	public List findkmTree();
}
