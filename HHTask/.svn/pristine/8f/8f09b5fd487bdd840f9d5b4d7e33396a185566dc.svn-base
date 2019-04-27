package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.android.InsRecord;
import com.task.entity.android.OsRecord;
import com.task.entity.android.OsRecordScope;
import com.task.entity.android.OsScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.gzbj.ProcessAndMeasuring;
import com.task.entity.sop.BreakSubmit;
import com.task.entity.sop.ProcessInfor;
import com.tast.entity.zhaobiao.Waigoujianpinci;
import com.tast.entity.zhaobiao.WaigoujianpinciZi;

public interface InsRecordService {
	
	/**
	 * 添加
	 * @param list
	 */
	public void add(List<InsRecord> list);

	/**
	 * 查看
	 * @param record
	 * @return
	 */
	public List<InsRecord> get(InsRecord record);

	public Object[] list(Integer size, Integer page);
	/**
	 * 获取可巡检的工序
	 * @return
	 */
	public Map<Integer, Object> getToXjList(ProcessInfor processInfor, String startDate,
			String endDate, int cpage, int pageSize);

	public ProcessInfor getProcessById(Integer id,BreakSubmit breaksubmit);

	public List<OsScope> getcheckList(Integer id);

	public OsTemplate updateOsTemplate(Integer id);

	public String xJProcess(ProcessInfor processInfor,
			List<OsRecord> osRecordList,Float jcCount,BreakSubmit breaksubmit);
	
	/**
	 * 获取所有的巡检标准
	 */
	 List<Waigoujianpinci>  findAllxjbzlist();
	 /**
	  * 巡检模板绑定巡检标准
	  */
	 String Ostbdxjbz(OsTemplate ost);
	 /**
	  * 根据工序信息Id获得应当巡检的数量
	  */
	 WaigoujianpinciZi findxunjianpici(ProcessInfor processInfor,OsTemplate osTemplate);
	 
	 /**
	  * 根据Id获得提交不合格品记录
	  */
	 BreakSubmit findbreaksubmitById(Integer id);
	 
	 
	 Object[]  getOsTemplate(Integer breakId);
	 
	 /**
		 * 根据工序名查询所绑定的量、检具信息
		 */
		List<ProcessAndMeasuring> pamListByProcessName(String processNames);
	 
	
}
