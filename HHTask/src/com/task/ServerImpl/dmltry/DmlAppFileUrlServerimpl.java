package com.task.ServerImpl.dmltry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.dmltry.DmlAppFileUrlServer;
import com.task.entity.dmltry.DmlAppFileUrl;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class DmlAppFileUrlServerimpl implements DmlAppFileUrlServer {

	private TotalDao totalDao;
	
	/**
	 * 查询
	 */
	@Override
	public List<DmlAppFileUrl> selDmlAppFileUrl() {
		ActionContext.getContext().getSession().put("DmlAppFileUrl", "dmlAppFileUrl");
		List<DmlAppFileUrl> urls=totalDao.query("from DmlAppFileUrl");
		
		return urls;
	}

	/**
	 * 添加
	 */
	@Override
	public String addDmlAppFileUrl(DmlAppFileUrl dmlAppFileUrl) {
		dmlAppFileUrl.setAddTimeDmlAppFileUrl(Util.getDateTime());
		boolean addb=totalDao.save(dmlAppFileUrl);
		return "true";
	}

	/**
	 * 根据提条件查询id
	 */
	
	@Override
	public DmlAppFileUrl selidDmlAppFileUrl(Integer id) {
		DmlAppFileUrl o=(DmlAppFileUrl) totalDao.getObjectById(DmlAppFileUrl.class, id);
		return o;
	}
	
	public List<DmlAppFileUrl> seleectDmlAppFileUrl(Integer id) {
		List<DmlAppFileUrl> list= totalDao.query("from DmlAppFileUrl where dmltryAppFiles.id=? order by id desc", id);		
		return 	list;		
	}
	
	

	/**
	 * 修改
	 */
	@Override
	public String updateDmlAppFileUrl(DmlAppFileUrl dmlAppFileUrl) {

		dmlAppFileUrl.setUpDateTimeDmlAppFileUrl(Util.getDateTime());
			totalDao.update(dmlAppFileUrl);
				return "true";
	}

	/**
	 * 删除
	 */
	@Override
	public String delteDmlAppFileUrl(Integer id) {
		DmlAppFileUrl urls=selidDmlAppFileUrl(id);
		totalDao.delete(urls);
		//删除删一个对象
		return "true";
	}

	
	
	
	
	
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 分页查询加条件
	 */
	@Override
	public Map<Integer, Object> fenyandtj(DmlAppFileUrl dmlAppFileUrl, int pageNo, int pageSize) {
				if (dmlAppFileUrl == null) {
					dmlAppFileUrl = new DmlAppFileUrl();
				}
				String hql = null;
				hql = totalDao.criteriaQueries(dmlAppFileUrl, null);
				hql += " order by id desc";
				List<DmlAppFileUrl> listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
				int count = totalDao.getCount(hql);
				// Object[] o = { list, count };
				Map<Integer, Object> map = new HashMap<Integer, Object>();
				map.put(1, listInt);
				map.put(2, count);
				return map;		
	}
	
	
	
	/**
	 * 根据名称查找id根据id查找下关联
	 */
	@Override
	public DmlAppFileUrl search(String appFilename) {
		DmlAppFileUrl dmlAppFileUrl=(DmlAppFileUrl)totalDao.getObjectByCondition("from DmlAppFileUrl where dmltryAppFiles.id = (select id from DmltryAppFiles where appFilename =? ) order by id desc",appFilename);		
		return 	dmlAppFileUrl;
	}

}
