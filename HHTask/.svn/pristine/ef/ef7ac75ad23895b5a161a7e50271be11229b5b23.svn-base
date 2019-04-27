package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.BoardService;
import com.task.entity.Board;
import com.task.entity.Vboard;
import com.task.util.ClobUtil;

public class BoardServiceImpl implements BoardService {
	private TotalDao totalDao;

	@Override
	public void add(Vboard v) {
		Board t = new Board();
		BeanUtils.copyProperties(v, t, new String[]{"content"});
		t.setContent(ClobUtil.getClob(v.getContent()));
		totalDao.save(t);
	}

	@Override
	public void delete(Integer id) {
		totalDao.delete(totalDao.get(Board.class, id));
	}

	@Override
	public Vboard get(Integer id) {
		Board t = (Board) totalDao.get(Board.class, id);
		Vboard v = new Vboard();
		BeanUtils.copyProperties(t, v, new String[]{"content"});
		v.setContent(ClobUtil.getString(t.getContent()));
		return v;
	}

	@Override
	public List<Vboard> list() {
		List<Board> boards = totalDao.find("from Board");
		List<Vboard> list = new ArrayList<Vboard>();
		for (Board vboard : boards) {
			Vboard v = new Vboard();
			BeanUtils.copyProperties(vboard, v, new String[]{"content"});
			v.setContent(ClobUtil.getString(vboard.getContent()));
			list.add(v);
		}
		return list;
	}

	@Override
	public void update(Vboard v) {
		Board t = (Board) totalDao.get(Board.class, v.getId());
		//BeanUtils.copyProperties(v, t, new String[]{"content"});
		
		String name=v.getName();
		Integer scrnId=v.getScrnId();
		String content=v.getContent();
		if(name!=null && !"".equals(name)){
			t.setName(name);
		}
		if(scrnId!=null){
			t.setScrnId(scrnId);
		}
		if(content!=null && !"".equals(content)){
			t.setContent(ClobUtil.getClob(v.getContent()));
		}
		totalDao.update(t);
	}
	
	public Object[] findBoardAllByscrnId(Integer id){
		String hql="from Board b where 1=1";
		if(id!=null){
			hql+=" and b.scrnId="+id;
		}
		hql+=" order by b.id desc";
		List<Board> boards = totalDao.find(hql);
		List<Vboard> list = new ArrayList<Vboard>();
		for (Board vboard : boards) {
			Vboard v = new Vboard();
			BeanUtils.copyProperties(vboard, v, new String[]{"content"});
			v.setContent(ClobUtil.getString(vboard.getContent()));
			list.add(v);
		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	//获得看板内容集合
	public Object[] findBoardAllByparentId(Integer parentId){
		String hql="from Board b where 1=1";
		if(parentId!=null){
			hql+=" and b.parentId="+parentId;
		}
		hql+=" order by b.createDate asc";
		List<Board> boards = totalDao.find(hql);
		List<Vboard> list = new ArrayList<Vboard>();
		for (Board vboard : boards) {
			Vboard v = new Vboard();
			BeanUtils.copyProperties(vboard, v, new String[]{"content"});
			v.setContent(ClobUtil.getString(vboard.getContent()));
			list.add(v);
		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	//获得看板内容集合 分页
	public Object[] findBoardAllByparentId(Vboard board,int  pageNo,int pageSize){
		String hql="from Board b where 1=1";
		Integer parentId=board.getParentId();
		if(parentId!=null){
			hql+=" and b.parentId="+parentId;
		}
		hql+=" order by b.createDate asc";
		List<Board> boards = totalDao.find(hql,pageNo,pageSize);
		List<Vboard> list = new ArrayList<Vboard>();
		for (Board vboard : boards) {
			Vboard v = new Vboard();
			BeanUtils.copyProperties(vboard, v, new String[]{"content"});
			v.setContent(ClobUtil.getString(vboard.getContent()));
			list.add(v);
		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	//获得标题集合
	public Object[] findTitleAllByscrnId(Integer scrnId){
		String hql="from Board b where 1=1";
		if(scrnId!=null){
			hql+=" and b.scrnId="+scrnId;
		}
		hql+=" order by b.createDate desc";
		List<Board> boards = totalDao.find(hql);
		List<Vboard> list = new ArrayList<Vboard>();
		for (Board vboard : boards) {
			Vboard v = new Vboard();
			BeanUtils.copyProperties(vboard, v, new String[]{"content"});
			v.setContent(ClobUtil.getString(vboard.getContent()));
			list.add(v);
		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	//获得标题集合 分页
	public Object[] findTitleAllByscrnId(Vboard board,int  pageNo,int pageSize){
		String hql="from Board b where 1=1";
		Integer scrnId=board.getScrnId();
		if(scrnId!=null){
			hql+=" and b.scrnId="+scrnId;
		}
		hql+=" order by b.createDate desc";
		List<Board> boards = totalDao.find(hql,pageNo,pageSize);
		List<Vboard> list = new ArrayList<Vboard>();
		for (Board vboard : boards) {
			Vboard v = new Vboard();
			BeanUtils.copyProperties(vboard, v, new String[]{"content"});
			v.setContent(ClobUtil.getString(vboard.getContent()));
			list.add(v);
		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
