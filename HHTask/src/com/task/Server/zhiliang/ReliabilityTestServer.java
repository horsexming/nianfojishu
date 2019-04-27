package com.task.Server.zhiliang;

import java.util.List;
import java.util.Map;

import com.task.entity.sop.WaigouDailySheet;
import com.task.entity.zhiliang.ReliabilityTestPro;
import com.task.entity.zhiliang.ReliabilityTestRecord;
import com.task.entity.zhiliang.ReliabilityTestSheet;

/**
 * 可靠性测试
 * @author xs-cy
 *
 */
public interface ReliabilityTestServer {
	
	/**
	 * 根据ReliabilityTestSheet.id获取ReliabilityTestSheet
	 * @param id
	 * @return
	 */
	ReliabilityTestSheet getRTSById(Integer id);
	/**
	 * 根据ReliabilityTestPro.id获取ReliabilityTestPro
	 * @param id
	 * @return
	 */
	ReliabilityTestPro getRTPById(Integer id); 
	
	/**
	 * 根据条件查询可靠性测试单
	 * @param sheet
	 * @param tag
	 * @return
	 */
	Object[] queryByCondition(ReliabilityTestSheet sheet,Integer pageNo,Integer pageSize,String tag);
	
	/**
	 * 添加测试项目
	 */
	String saveRTP(ReliabilityTestPro pro);
	
	/**
	 * 根据条件获取测试项目列表
	 */
	Map<Integer,Object> findRTPByCondition(ReliabilityTestPro pro,Integer pageNo,Integer pageSize,String tag);
	
	/**
	 * 删除测试项目
	 * @param id
	 * @return
	 */
	String delRTPById(Integer id);
	
	/**
	 * 添加可靠性测试单
	 */
	String addRTS(ReliabilityTestSheet sheet,List<ReliabilityTestRecord> recordList,String tag);
	
	/**
	 * 修改可靠性测试单
	 */
	String updateRTS(ReliabilityTestSheet sheet,List<ReliabilityTestRecord> recordList,String tag);
	
	/**
	 * 删除可靠性测试单
	 */
	String deleteRTS(Integer id, String tag);
	
	/**
	 * 获取最新的流水单号
	 */
	String getMaxNumber(String tag);
	
	/**
	 * 外购来料日报表
	 * @param id
	 * @return
	 */
	WaigouDailySheet findwgdSheetById(Integer id);
	
	/**
	 * 提交测试结果
	 */
	String submitTestResult(ReliabilityTestSheet sheet,List<ReliabilityTestRecord> recordList,String tag);
}
