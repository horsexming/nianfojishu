package com.task.ServerImpl.dmltry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.dmltry.DmltryAppFilesServer;
import com.task.entity.dmltry.DmlAppFileUrl;
import com.task.entity.dmltry.DmltryAppFiles;
import com.task.util.Util;


@SuppressWarnings("unchecked")
public class DmltryAppFilesServerimpl implements DmltryAppFilesServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 查询所有
	 */

	@Override
	public List<DmltryAppFiles> selAll() {
		List<DmltryAppFiles> all = totalDao.query("from DmltryAppFiles");
		return all;
	}

	/**
	 * 添加
	 */
	@Override
	public String addDmltryAppFiles(DmltryAppFiles dmltryAppFiles) {
		
		//totalDao.save(dmltryAppFiles);
		dmltryAppFiles.setAddTime(Util.getDateTime());
		boolean b=totalDao.save(dmltryAppFiles);
		if(b){
			return "true";
		}else {
			return "添加失败！";
		}
	}
	
	/**
	 * 根据id查询
	 */
	public DmltryAppFiles selidDmltryAppFiles(Integer id){
		//String hql = "from InterviewQuizzes where interviewLog.id=?";
		//List all = totalDao.query(hql, id);
		Object	o =totalDao.getObjectById(DmltryAppFiles.class, id);
		return	(DmltryAppFiles) o;
	}

	/**
	 * 修改
	 */
	@Override
	public String updateDmltryAppFiles(DmltryAppFiles dmltryAppFiles) {

		dmltryAppFiles.setUpDateTime(Util.getDateTime());
		boolean add=totalDao.update(dmltryAppFiles);
		if (add) {
			return	"true";
		}else {
			return  "添加失败";
		}

	}
	
	
	
	
	
	/**
	 * 删除
	 */
	@Override
	public String delteDmltryAppFiles(Integer id) {
		DmltryAppFiles appFiles = selidDmltryAppFiles(id);
			boolean update=totalDao.delete(appFiles);
			if (update) {
				return	"true";
			}else {
				return  "添加失败";
			}

	}

	@Override
	public Map<Integer, Object> fenyandtj(DmltryAppFiles dmltryAppFiles, int pageNo, int pageSize) {
		if (dmltryAppFiles == null) {
			dmltryAppFiles = new DmltryAppFiles();
		}
		String hql = null;
		
		hql = totalDao.criteriaQueries(dmltryAppFiles, null);
		hql += " order by id desc";
		List<DmlAppFileUrl> listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}


}
