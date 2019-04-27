package com.task.ServerImpl.caiwu;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.caiwu.DocumentWordServer;
import com.task.entity.caiwu.DocumentWord;

public class DocumentWordServerImpl implements DocumentWordServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public String add(DocumentWord dw) {
		if(dw!=null){
			String hql = "from DocumentWord where name=?";
			DocumentWord dw1 =	(DocumentWord) totalDao.getObjectByCondition(hql, dw.getName());
			if(dw1 == null){
				return totalDao.save(dw)+"";
			}else{
				return 	dw.getName()+"已添加过，无需重复添加!";
			}
		}
		return null;
	}

	@Override
	public boolean del(DocumentWord dw) {
		if(dw!=null){
			return totalDao.delete(dw);
		}
		return false;
	}

	@Override
	public boolean fanjinyong(Integer id) {
		if(id!=null && id>0){
			DocumentWord dw =(DocumentWord) totalDao.get(DocumentWord.class, id);
		}
		return false;
	}

	@Override
	public List<DocumentWord> findAll() {
		
		return null;
	}

	@Override
	public List<DocumentWord> findAllBypage(int pageNo, int pageSize) {
		
		return null;
	}

	@Override
	public Map<Integer, Object> findByCondition(DocumentWord dw, int pageNo,
			int pageSize) {
		
		return null;
	}

	@Override
	public DocumentWord findMoneyTypeById(Integer id) {
		
		return null;
	}

	@Override
	public int getcont() {
		
		return 0;
	}

	@Override
	public boolean jinyong(Integer id) {
		
		return false;
	}

	@Override
	public boolean update(DocumentWord dw) {
		
		return false;
	}

}
