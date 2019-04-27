package com.task.Server.iao;

import java.util.List;

import com.task.entity.iao.LeaveInform;

public interface InformServer {
	public LeaveInform findByCode(String code , String fuck1);
	
	public boolean save(LeaveInform leaveInform);
	
	public List getAllList(int pageNo, int pageSize,String key);
	
	public Integer getcount(String key);
	
	public int getshu(String key);
	
	public LeaveInform findById(int code , String fuck1);
	
	public LeaveInform findById(int code);
	
	public void delete(LeaveInform leaveInform);
}
