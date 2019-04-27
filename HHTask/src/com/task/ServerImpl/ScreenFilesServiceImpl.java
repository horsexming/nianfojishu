package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.ScreenFilesService;
import com.task.entity.Screen;
import com.task.entity.ScreenFiles;
import com.task.entity.TaSopGongwei;

public class ScreenFilesServiceImpl implements ScreenFilesService {
	
	private TotalDao totalDao;
	
	@Override
	public List<Map> autoFile(Integer id) {
		List<String> gongweis = new ArrayList<String>();
		Screen s = (Screen) totalDao.get(Screen.class, id);
		for (TaSopGongwei taSopGongwei : s.getGongweis()) {
			gongweis.add(taSopGongwei.getGongweihao());
		}
		
		String sql2 = "select c.id, p.proname,p.markid,c.processName,c.usercodes,c.usernames, c.gongwei from ta_sop_w_ProcessInfor c  inner join ta_sop_w_procard p on  c.fk_procardid=p.id and c.status=:status where c.gongwei in (:gongwei)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", "已领");
		params.put("gongwei",gongweis );
		List<Map> list1 = totalDao.findBySql(sql2, params);
		return list1;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void add(List<ScreenFiles> files) {
		for (int i = 0; i < files.size(); i++) {
			totalDao.save(files.get(i));
		}
	}

	@Override
	public List<ScreenFiles> getFilesByScreen(Integer id) {
		String hql = "from ScreenFiles where screen.id = ?";
		List<ScreenFiles> ls = totalDao.find(hql, new Object[]{id});
		for (int i = 0; i < ls.size(); i++) {
			ls.get(i).getProcess().getProcessName();
		}
		
		return ls;
	}
	
	public List<ScreenFiles> getFilesForAndroid(Integer id){
		List<String> gongweis = new ArrayList<String>();
		Screen s = (Screen) totalDao.get(Screen.class, id);
		for (TaSopGongwei taSopGongwei : s.getGongweis()) {
			gongweis.add(taSopGongwei.getGongweihao());
		}
		
		String sql2 = "select c.id from ta_sop_w_ProcessInfor c  inner join ta_sop_w_procard p on  c.fk_procardid=p.id and c.status=:status where c.gongwei in (:gongwei)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", "已领");
		params.put("gongwei",gongweis );
		List<Map> list1 = totalDao.findBySql(sql2, params);
		
		List<Integer> l1 = new ArrayList<Integer>();
		for (Map map : list1) {
			l1.add(Integer.parseInt(map.get("id").toString()));
		}
		
		String hql = "from ScreenFiles where process.id in (:id)";
		Map<String, Object> mm = new HashMap<String, Object>();
		mm.put("id", l1);
		List<ScreenFiles> ls = totalDao.find(hql,mm);
		
		for (ScreenFiles sfs : ls) {
			sfs.setPartNumber(sfs.getProcess().getProcard().getMarkId());
			sfs.setName(sfs.getProcess().getProcard().getProName());
			sfs.setProcessName(sfs.getProcess().getProcessName());
		}
		return ls;
	}
	

	@Override
	public List<ScreenFiles> getFiles(Integer id) {
		String hql = "from ScreenFiles where process.id = ?";
		return totalDao.find(hql, new Object[]{id});
	}

	@Override
	public void delete(Integer id) {
		ScreenFiles sf = new ScreenFiles();
		sf.setId(id);
		totalDao.delete(sf);
	}

}
