package com.task.Server.checktype;

import java.util.Map;

import com.task.entity.checktype.CheckType;


public interface CheckTypeServer {
	public CheckType findById(Integer id);
	
	public boolean save(CheckType checkType);
	
	public boolean delete(CheckType checkType);
	
	public boolean update(CheckType checkType);
	
	public Map<Integer, Object> findAll(CheckType checkType, int pageNo, int pageSize);
	
	public Map<Integer, Object> findAll1(CheckType checkType, int pageNo, int pageSize,String type);
	
	
}
