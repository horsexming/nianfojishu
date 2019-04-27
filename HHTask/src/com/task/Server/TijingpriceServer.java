package com.task.Server;

import java.util.List;

import com.task.entity.Tijiangprice;

public interface TijingpriceServer {

	// 添加价格
	public boolean addtijingprice(Tijiangprice tijingprice);

	// 查询价格表所有信息
	public List findShouList(int pageNo, int pageSize);

	// 获得所有数量
	public Integer getCount();

	// 根据ID查询所有信息
	public Tijiangprice findtijingpriceById(int id);

	// 修改
	public boolean updateTijingprice(Tijiangprice tijingprice);

	// 删除
	public boolean deleteTijingprice(Tijiangprice tijingprice);

	// 条件查询
	public List findByCondition(Tijiangprice tijingprice,int pageNo, int pageSize);

	// 获得模糊查询的总数量
	public int findByConditioncount(Tijiangprice tijingprice);

	// 根据件号查询相同的开始数量
	public List findname(String findname);
	// 条件查询 入库表指定的时间的数量 和单件计价表的单价 (前)
	public List findshougoodStoreBefore(String setDate, String endDate);

	// 根据指定时间查询出入库表中的数量和件号
	public Object[] findShougoodsStore(String setDate, String endDate);
}
