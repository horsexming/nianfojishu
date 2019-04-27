package com.task.Server;

import java.util.Map;

import com.task.entity.IPEntity;

public interface IIPEntityService {
	public void add(IPEntity ip);
	public void del(IPEntity ip);
	public void update(IPEntity ip);
	public IPEntity getIPEntityById(int id);
	public Object[] queryIPEntityByCondition(Map map, int pageNo,int pageSize);
	public void delIPEntityById(Integer id);
}
