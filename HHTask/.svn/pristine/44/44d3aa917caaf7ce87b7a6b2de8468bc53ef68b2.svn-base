package com.task.Server;

import java.util.List;

import com.task.entity.Mentionrecord;
import com.task.entity.MinBalance;
import com.task.entity.Tijiang;

public interface TijingServer {

	//添加提奖表信息
	public boolean savetijing(Tijiang tijing);
	//根据月份查询提奖计价表信息
	public List showtijiang(String date,int pageNo, int pageSize);
	//根据月份查询提奖计价表总数
	public int findtijiangcount(String date);
	//添加提奖记录表
	public  boolean saveMentionrecord(Mentionrecord mentionrecord);
	//根据开始时间和结束查询出朗特系列 12030100S03
	public List findltjianhao(String setDate,String endDate );
	//根据开始时间和结束查询出朗特系列   12030200S03
	public List findltjianhao2(String setDate,String endDate );
	//根据开始时间和结束查询出朗特系列  2870026401/E
	public List findltjianhao3(String setDate,String endDate );
	//增加最小余额表信息
	public boolean saveMinBalance(MinBalance minBalance);
	//根据月份查询出相对最小的余额
	public List findMinBalance(String setDate);
	//根据开始时间查询出提奖记录表当中是否有相同的月份
	public List findyuefen(String setDate);
	//根据件号查询出单价计价表
	public List findjijiang();
	//根据月份生成EXCEL
	public List finddateExcel(String date);
	//根据月份查看所对应的入库记录
	public List findDateGoodStore(String setDate,String endDatetime,int pageNo, int pageSize);
	//根据月份查看所对应的入库记录 总数
	public int getDateGoodStoreCount(String setDate,String endDatetime);
	//根据件号查询出所对应的入库信息
	public List findjianhao(String jianhao,String setDate,String endDatetime ,int pageNo, int pageSize);
	//根据件号查询出所对应的入库信息  总数
	public int getjianhaoCout(String jianhao,String setDate,String endDatetime);
	//添加试制奖金
	public boolean addShizhiJiangjin(Tijiang tijing);
	//查询试制
	public Object[] queryShizhijiangjin(Tijiang tijing,Integer cpage,Integer Pagesize);
	//修改试制奖 
	public boolean updateShizhi(Tijiang tijing);
	//获取一条试制
	public Tijiang getShizhi(Integer id);
	public boolean deleteShizhijiangjin(Tijiang tijing);
	/****
	 * 根据月份查看所对应的入库记录 总数
	 * @param jianhao
	 * @param pichi
	 * @param jiezhiDate
	 * @return
	 */
	Float getDateGSCount(String jianhao, String pichi, String jiezhiDate);
}
