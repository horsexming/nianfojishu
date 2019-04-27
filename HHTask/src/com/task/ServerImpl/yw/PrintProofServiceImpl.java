package com.task.ServerImpl.yw;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.yw.IPrintProofService;
import com.task.entity.PrintProof;

/**
 * @Title: PrintProofServiceImpl.java
 * @Package com.task.ServerImpl.yw
 * @Description: 付款凭证实现类
 * @author 曾建森
 * @date 2012-11-8 下午04:15:47
 * @version V1.0
 */
public class PrintProofServiceImpl implements IPrintProofService {
	
	private static String[] strNature = {"1预付","2中间付款","3余款","4质保金","5借款","6冲账","7其他"}; //性质
	private static String[] strWay = {"银行","汇票","汇兑","支票","贷记","现金","其他"}; //方式
	private static String[] strConditions = {"即付","TT30天","TT60天","TT90天","TT120天","TT120天以上","其他条件说明"}; //条件
	private static String[] strSituation = {"总额","已支付","本次应付","累计支付","余额"};//情况
	private static String[] strCategory = {"1总务性采购","2原材料采购","3工程设备类采购","5其他类采购请说明"}; //类别
	private static Map storeNature = new HashMap(); //性质
	private static Map storeWay = new HashMap();   //方式
	private static Map storeConditions = new HashMap(); //付款条件
	private static Map storeSituation = new HashMap(); //付款情况
	private static Map storeCategory = new HashMap(); //类别
	
	static{
		for(int i = 0;i<strNature.length;i++){
			storeNature.put("n"+i,strNature[i]);
		}
		for(int i = 0;i<strWay.length;i++){
			storeWay.put("w"+i, strWay[i]);
		}
		for(int i = 0;i<strConditions.length;i++){
			storeConditions.put("c"+i, strConditions[i]);
		}
		for(int i = 0;i<strSituation.length;i++){
			storeSituation.put("s"+i, strSituation[i]);
		}
		for(int i = 0;i<strCategory.length;i++){
			storeCategory.put("ca"+i,strCategory[i]);
		}
	}
	
	private TotalDao totalDao;
	/**
	 * @Title: add
	 * @Description: 添加付款凭证
	 * @param @param p 
	 * @return void
	 * @throws
	 */
	public void add(PrintProof p) {
		totalDao.save(p);
	}
	/**
	 * @Title: update
	 * @Description: 修改付款凭证
	 * @param @param p 
	 * @return void
	 * @throws
	 */
	public void update(PrintProof p) {
		totalDao.update(p);
	}
	/**
	 * @Title: getPrintProofById
	 * @Description: 根据ID查找付款凭证
	 * @param @param id
	 * @param @return 
	 * @return PrintProof
	 * @throws
	 */
	public PrintProof getPrintProofById(int id) {
		return (PrintProof) totalDao.getObjectById(PrintProof.class, id);
	}
	/**
	 * @Title: queryAllPrintProof
	 * @Description: 查询所有总经理审核的付款凭证
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAllPrintProof(int pageNo,int pageSize) {
		String hql = "from PrintProof p where p.status = '审核'";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);	
		int count =	totalDao.getCount(hql);
		for(int i = 0;i<list.size();i++){
			PrintProof pp = (PrintProof) list.get(i);
			pp.setNature(storeNature.get(pp.getNature()).toString());
			pp.setWay(storeWay.get(pp.getWay()).toString());
			pp.setSituation(storeSituation.get(pp.getSituation()).toString());
			pp.setConditions(storeConditions.get(pp.getConditions()).toString());
			pp.setCategory(storeCategory.get(pp.getCategory()).toString());
		}
		return new Object[]{list,count};
	}
	/**
	 * @Title: queryAgreeProof
	 * @Description: 查询所有审核通过的付款凭证
	 * @param @param pageNo
	 * @param @param pageSize
	 * @param @return 
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAgreeProof(int pageNo, int pageSize) {
		String hql = "from PrintProof p where p.status = 'Agree'";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);	
		int count =	totalDao.getCount(hql);
		for(int i = 0;i<list.size();i++){
			PrintProof pp = (PrintProof) list.get(i);
			pp.setNature(storeNature.get(pp.getNature()).toString());
			pp.setWay(storeWay.get(pp.getWay()).toString());
			pp.setSituation(storeSituation.get(pp.getSituation()).toString());
			pp.setConditions(storeConditions.get(pp.getConditions()).toString());
			pp.setCategory(storeCategory.get(pp.getCategory()).toString());
		}
		return new Object[]{list,count};
	}
	/**
	 * @Title: queryGoBackProof
	 * @Description: 查询所有审核不通过的付款凭证
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryGoBackProof(int pageNo, int pageSize) {
		String hql = "from PrintProof p where p.status = 'goBack'";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);	
		int count =	totalDao.getCount(hql);
		for(int i = 0;i<list.size();i++){
			PrintProof pp = (PrintProof) list.get(i);
			pp.setNature(storeNature.get(pp.getNature()).toString());
			pp.setWay(storeWay.get(pp.getWay()).toString());
			pp.setSituation(storeSituation.get(pp.getSituation()).toString());
			pp.setConditions(storeConditions.get(pp.getConditions()).toString());
			pp.setCategory(storeCategory.get(pp.getCategory()).toString());
		}
		return new Object[]{list,count};
	}
	/**
	 * @Title: getMaxId
	 * @Description: 查询最大
	 * @return void
	 * @throws
	 */
	public String getDeptNumber(String deptId){
		String hql = "select max(nature) from PrintProof";
		List list = totalDao.query(hql, null);
		StringBuffer str = new StringBuffer();
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		str.append(year + deptId + month);
		if(list != null && list.size() > 0 && list.get(0) != null){
			String val = (String) list.get(0);
			
		}else{
			return null;
		}
		return null;
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
}
