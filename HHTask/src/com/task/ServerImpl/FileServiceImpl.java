package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.entity.ZhFile;

public class FileServiceImpl implements FileService {
	private TotalDao totalDao;

	@Override
	public void delete(ZhFile file) {
		totalDao.delete(file);
	}

	@Override
	public void save(ZhFile file) {
		totalDao.save(file);
	}

	@Override
	public List<ZhFile> getList(String fid, String mid) {
		String hql="from ZhFile where fid= ? and mid= ? order by creattime desc";
		List<ZhFile> list = totalDao.query(hql, fid, mid);
		return list;
	}
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}



}
