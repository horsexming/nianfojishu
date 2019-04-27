package com.task.Server;


import java.util.List;
import java.util.Map;

import com.task.entity.DJNR;
import com.task.entity.Machine;

public interface DJNRServer {
	
	public boolean add(DJNR djnr);
	public Map<Integer, Object> findDJNRByCondition(DJNR DJNR,
			int pageNo, int pageSize);
	public boolean update(DJNR djnr);
	
	public List<DJNR> findAllDJNR(int pageNo, int pageSize);
	
	public DJNR finddjnrbyid(DJNR djnr);
	//根據 設備ID得到所多應得點檢內容
	public List<DJNR> getdjnrbyId(Integer id);
	public List<DJNR> getdjnrbyId1(Integer id);
		
	public boolean bdmachine(DJNR djnr);
	//根据设备Id得到绑定该内容的设备;
	public List<Machine> findbdmachine(Integer id);
	//根据设备Id得到未绑定该内容的设备;
	public List<Machine> findmachine(Integer id);
	boolean del(DJNR djnr);
	
	
}
