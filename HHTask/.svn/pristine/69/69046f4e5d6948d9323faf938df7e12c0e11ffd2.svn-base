package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ScreenContentServer;
import com.task.entity.ScreenContent;
import com.task.util.Util;

public class ScreenContentServerImpl implements ScreenContentServer {
	private TotalDao totalDao;

	public String addScreenContent(ScreenContent screencontent) {
		screencontent.setStatus("visible");
		screencontent.setAddTime(Util.getDateTime());
		screencontent.setName(screencontent.getName());
		String hql = "select number from ScreenContent where number = (select max(number) from ScreenContent)";
		List list = totalDao.query(hql, null);
		int number = 1;
		if (list.size() != 0) {
			number = Integer.parseInt(list.get(0).toString()) + 1;
		}
		screencontent.setNumber(number);
		if (totalDao.save(screencontent)) {
			return "添加成功！";
		}
		return "添加失败！";

	}

	public Integer findlastscreencontentId() {
		String hql = "select id from ScreenContent where id = (select max(id) from ScreenContent)";
		Integer id = (Integer) totalDao.get(hql, null);
		return id;
	}

	public String updateScreenContent(ScreenContent screencontent) {

		ScreenContent s = (ScreenContent) totalDao.get(ScreenContent.class,
				screencontent.getId());
		ScreenContent screencontent2 = getscreencontent(screencontent.getId());
		if (screencontent2 != null) {
		}
		return "不存在该条数据，修改失败!";
	}

	@Override
	public String updateScreenContents(List<ScreenContent> screencontents) {
//		String hql = "from ScreenContent where id=?";
		for (ScreenContent screenContent : screencontents) {
			ScreenContent sct = (ScreenContent) totalDao.getObjectById(
					ScreenContent.class, screenContent.getId());
			sct.setName(screenContent.getName());
			 sct.setNumber(screenContent.getNumber());
			sct.setScreenUrl(screenContent.getScreenUrl());
			totalDao.update(sct);
		}
		return null;
	}

	@Override
	public String deleteScreenContent(ScreenContent screencontent) {
		totalDao.delete(screencontent);
		return null;
	}

	// public String deleteScreenContent(Integer id) {
	//		
	// // String hql="from ScreenContent where id=?";
	// // List<ScreenContent> list=totalDao.query(hql, id);
	// // for (ScreenContent ScreenContent2 : list) {
	// // totalDao.delete(ScreenContent2);
	// // }
	// return null;
	// }

	@Override
	public ScreenContent getscreencontent(Integer id) {
		return (ScreenContent) totalDao.getObjectById(ScreenContent.class, id);
	}

	public String updateScreenContent(ScreenContent screencontent, Integer id) {
		String hql = "from ScreenContent where id=?";
		List<ScreenContent> list = totalDao.query(hql, id);
		for (ScreenContent ScreenContent2 : list) {
			ScreenContent2.setScreenUrl(screencontent.getScreenUrl());
			totalDao.update(ScreenContent2);
		}
		return null;
	}

	@Override
	public String[] findScreenContent(Integer id) {
		// String hql =
		// "select distinct sc.screenUrl from ScreenContent sc join sc.screen se where  se.id =?";
		String hql = "select distinct sc.screenUrl,sc.number from ScreenContent sc join sc.screen se where  se.id =? order by sc.number";
		List list = totalDao.query(hql, id);
		String[] strings = new String[list.size()];
		for (int i = 0, len = list.size(); i < len; i++) {
			Object[] objects = (Object[]) list.get(i);
			strings[i] = (String) objects[0];
			// strings[i]=(String) list.get(i);
		}
		return strings;
	}

	public Object[] ScreenContentList() {
		String hql = "from ScreenContent order by number";
		List<ScreenContent> list = totalDao.query(hql);
		Object[] o = { list };

		return o;
	}

	public Object[] ScreenContentAllList() {
		String hql = "from ScreenContent order by number";
		List<ScreenContent> list = totalDao.query(hql);
		Object[] o = { list };
		return o;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String findScreenName(Integer id) {
		// TODO Auto-generated method stub
		return (String) totalDao.getObjectByCondition("select name from Screen where id=?", id);
	}

	

	@Override
	public String findCompanyEnglishName() {
		String hql="select englishName from CompanyInfo";
		String o=(String)totalDao.getObjectByCondition(hql, null);
		return o;
	}
	


}
