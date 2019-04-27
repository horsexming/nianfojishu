package com.task.Server;

import java.util.Map;

import com.task.entity.Maintain;
import com.task.entity.VOMaintain;


public interface IMaintainService {
	void add(Maintain ma);
	void del(Maintain ma);
	String update(Maintain ma);
	Maintain getMaintainById(int id);
	Object[] queryMaintain(Map map, int pageNo, int pageSize);
	void exportExcel(Map map);
	String addRelation(VOMaintain vom);
	void delMaintainById(Integer id);
	void repair(Integer id);
}
