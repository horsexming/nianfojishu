package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.AnnualLeave;
import com.task.entity.AskForLeave;
import com.task.entity.Overtime;

public interface IAnnualLeaveService {
	void add(AnnualLeave annual);
	void update(AnnualLeave annual);
	void delete(AnnualLeave annual);
	AnnualLeave getAnnualLeaveById(Integer id);
	void daochuAll(String tag);//导出年休换休数据
	Object[] findAnnualLeaveCondition(Map map,Integer pageNo, Integer pageSize);
	void batchAddAnnualLeave();
	Object[] ByCode(String jobNum,Integer pageNo, Integer pageSize);
	Object[] listAnnualLeave(AnnualLeave al, Integer parseInt, Integer pageSize,String pagestatus);
	AnnualLeave BynameNianxiu(String code);
	List ByAskForLeaveCarId(String cardId);
	Object[] findAnnualLeaveCondition1(Map map, Integer parseInt, Integer pageSize);
	Object[] ByCodehuanxiu(String jobNum, Integer parseInt, Integer pageSize);
	Object[] listhuanxiu(AnnualLeave al, Integer parseInt, Integer pageSize);
	void gengxinhuanxiu();
	void gengxinhuanxiu(String code);//算换休
	Object[] mingxijiaban(String jobNum, Overtime askForLeave, Integer parseInt,
			Integer pageSize);
	List listhuanxiumingxi(String cardId);
	/**
	 * 根据工号查询所有换休加班记录
	 * @param code
	 * @return
	 */
	List overTimeList(String code);
	
	AnnualLeave BynameHuanxiu(String code);
	void qinkongdept();
}
