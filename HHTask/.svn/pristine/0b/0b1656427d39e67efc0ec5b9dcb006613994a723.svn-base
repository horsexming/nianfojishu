package com.task.Server;

import java.util.List;

import com.task.entity.Vboard;

public interface BoardService {
	
	/**
	 * 添加
	 * @param v
	 */
	public void add(Vboard v);//添加发布

	
	public void update(Vboard v);//跟新发布
	
	public void delete(Integer id);//删除
	
	public Vboard get(Integer id);//获得
	
	public List<Vboard> list();//获得集合
	
	public Object[] findBoardAllByparentId(Vboard board,int  pageNo,int pageSize);//获得集合
	
	public Object[] findTitleAllByscrnId(Vboard board,int  pageNo,int pageSize);//获得标题集合
	
	public Object[] findBoardAllByparentId(Integer id);//获得集合
	
	public Object[] findTitleAllByscrnId(Integer id);//获得标题集合
}
