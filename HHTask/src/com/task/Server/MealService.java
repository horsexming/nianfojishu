package com.task.Server;

import java.util.List;

import com.task.entity.Mealticket;

public interface MealService {
	public void save(Mealticket mealticket);

	//删除
	public  void delete(Mealticket mealticket);

	public  List<Mealticket> getList(int fid);

	public  List getAllList(int pageNo, int pageSize);
	
	public void update(Mealticket mealticket);
	
	public Integer getcount();
	//审核
	public void updatee(Mealticket mealticket);
	
	public int getshu();
	
	public Integer getcount1();
	
	public int getshu1();
	
	public List getPersonList(int pageNo, int pageSize);
	
	public Integer getcount2();
	
	public List getKanList(int pageNo, int pageSize);
	
	public void copy(Mealticket mealticket);
	
	public void addCopy(Mealticket mealticket);
	
	public Integer getcount3();
	
	public List getKanList3(int pageNo, int pageSize);
	
	public void redown (Mealticket mealticket);
	
	public Integer getcount4(String atime,String btime);
	
	public List getKanList4(int pageNo, int pageSize,String atime,String btime);
	
	public int getshu2(String atime,String btime);
	
	public List getList(String atime,String btime);
	
	public List getList();
}