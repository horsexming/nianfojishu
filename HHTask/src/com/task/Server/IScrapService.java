package com.task.Server;

import java.util.Map;

import com.task.entity.Scrap;
import com.task.entity.Store;

public interface IScrapService {
	void add(Scrap scr);
	void del(Scrap scr);
	String update(Scrap scr);
	Scrap getScrapById(int id);
	Object[] queryScrap(Map map, int pageNo, int pageSize);
	void exportExcel(Map map);
	String alsoScrap(Scrap scr);
	Store getStoreById(Integer id);
	String add(Scrap scr,Integer id);
	void delScrapById(Integer id);
	
	void piliangbaofei();
}
