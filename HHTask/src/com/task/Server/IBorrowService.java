package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Borrow;

public interface IBorrowService {
	public void add(Borrow bor);
	public void del(Borrow bor);
	public boolean update(Borrow bor);
	public Borrow getBorrowById(int id);
	public Object[] queryBorrow(Map map, int pageNo, int pageSize);
	public void exportExcel(Map map);
	public List<Borrow> queryBrrowByCardNum(String cardNum,String state);
	void delBorrowById(Integer id);
	Object[] queryStatistics(Map map, int pageNo, int pageSize);
	String exportExcel(Map map, String startTime, String endTime);
	public Object[] showCodeBorrow(Borrow bor, Integer cpage, Integer pageSize,String tag);
}
