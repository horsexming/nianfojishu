package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ScreenService;
import com.task.entity.Screen;
import com.task.entity.ScreenContent;
import com.task.entity.TaSopGongwei;
import com.task.entity.printer.Printer;
import com.task.util.Util;

public class ScreenServiceImpl implements ScreenService {

	private TotalDao totalDao;

	@Override
	public void add(Screen viewObj) {
		viewObj.setCreateDateTime(new Date());
		totalDao.save(viewObj);
	}

	@Override
	public List<Screen> list() {
		return totalDao.find("from Screen");
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List<TaSopGongwei> getChild(Screen screen) {
		List<TaSopGongwei> list = new ArrayList<TaSopGongwei>();
		// String sql =
		// "select id, gongweihao from ta_sop_gongwei t where t.f_TaSopGongwei_id = ?";
		// List list1 = totalDao.createQuerySelect(null, sql, new
		// Object[]{screen.getId()});
		// for (int i = 0; i < list1.size(); i++) {
		// TaSopGongwei s = new TaSopGongwei();
		// Object[] objs = (Object[]) list1.get(i);
		// s.setId(Integer.parseInt(objs[0].toString()));
		// s.setGongweihao(objs[1].toString());
		// list.add(s);
		// }
		Screen s = (Screen) totalDao.get(Screen.class, screen.getId());
		for (TaSopGongwei taSopGongwei : s.getGongweis()) {
			list.add(taSopGongwei);
		}
		return list;
	}

	@Override
	public List<ScreenContent> getScreenContent(Screen screen) {
		List<ScreenContent> list = new ArrayList<ScreenContent>();

		Screen s = (Screen) totalDao.get(Screen.class, screen.getId());
		for (ScreenContent screencontent : s.getScreencontents()) {
			list.add(screencontent);
		}
		return list;
	}

	@Override
	public String[] getScreenContentbyid(Integer id) {
		String hql = " from ScreenContent where id in ( select sc.id from ScreenContent sc join sc.screen se where  se.id =?)";
		List<ScreenContent> list = totalDao.query(hql, id);
		String [] strings=new String [list.size()];
		for (int i=0,len=list.size();i<len;i++) {
			strings[i]=(String) list.get(i).getId().toString();
		}
		return strings;
	}

	@Override
	public void addscreencontent(Screen screen) {
		if (screen != null) {
			
			Set<ScreenContent> sctSetNew = new HashSet<ScreenContent>();
			if (screen.getScreencontents() != null) {
				Set<ScreenContent> sctSetPage = screen.getScreencontents();
				for (ScreenContent screenContent : sctSetPage) {
					ScreenContent sct = (ScreenContent) totalDao.getObjectById(
							ScreenContent.class, screenContent.getId());
					sctSetNew.add(sct);
				}
			}
			screen.setModifyDateTime(new Date());
			Screen s = (Screen) totalDao.get(Screen.class, screen.getId());
			s.setScreencontents(sctSetNew);
			totalDao.update(s);
		}

	}
	
	public void addscreencontent2allscreen(Integer screencontentid) {
		String hql="from Screen";
		List<Screen> list=totalDao.query(hql, null);
		for (Screen screen : list) {
			Set<ScreenContent> sctSetNew = new HashSet<ScreenContent>();
			if (screen.getScreencontents() != null) {
				Set<ScreenContent> sctSetPage = screen.getScreencontents();
				for (ScreenContent screenContent : sctSetPage) {
					ScreenContent sct = (ScreenContent) totalDao.getObjectById(
							ScreenContent.class, screenContent.getId());
					sctSetNew.add(sct);
				}
			}
			
			ScreenContent sct2 = (ScreenContent) totalDao.getObjectById(
					ScreenContent.class, screencontentid);
			sctSetNew.add(sct2);
			screen.setScreencontents(sctSetNew);
			totalDao.update(screen);	
		}
	}

	@Override
	public Screen get(Integer id) {
		return (Screen) totalDao.get(Screen.class, id);
	}

	@Override
	public void update(Screen screen) {
		screen.setModifyDateTime(new Date());
		Screen s = (Screen) totalDao.get(Screen.class, screen.getId());
		BeanUtils.copyProperties(screen, s, new String[] { "id",
				"createDateTime", "gongweiList","screencontents" });
		
		totalDao.update(s);
	}

	@Override
	public void delete(Screen screen) {
		if (!totalDao.delete(screen)) {
			throw new RuntimeException("不知道什么原因，请通知管理员");
		}
	}

	@Override
	public List<Screen> getAll() {
		return totalDao.find("from Screen");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> getUsers(Integer id) {
		// String hql =
		// "select c.processNO,p.planOrderId, c.id, p.proName,p.markId, p.filnalCount, c.usercodes,c.usernames, c.gongwei, c.shebeiNo "
		// +
		// " from ProcessInfor c join c.procard p where c.status='已领' and c.gongwei in "
		// +
		// "(select gongweihao from TaSopGongwei where id in (select g.id from Screen s join s.gongweis g  where s.id = ?))";
		//
		// List list = totalDao.query(hql, id);// 得出在干工序的人

		String sql = "select c.processNO, p.planOrderId, c.id, p.proname,p.markid, p.count, c.usercodes,c.usernames, c.gongwei, c.shebeiNo from ta_sop_w_ProcessInfor c  inner join ta_sop_w_procard p on  c.fk_procardid=p.id and  c.status='已领' where c.gongwei in (select gongweihao from ta_sop_gongwei where id in(select gongwei_id from ta_screen_sopgongwei where screen_id = :id))";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Map> list = totalDao.findBySql(sql, params, 1, 10);// 得出在干工序的人
		try {
			for (Map map : list) {
				String sql1 = "select machine_name,machine_status from machine where machine_workPosition = :workStation and machine_no = :equitmentNumber";
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("workStation", map.get("gongwei"));
				params1.put("equitmentNumber", map.get("shebeiNo"));
				List<Map> ll = totalDao.findBySql(sql1, params1);
				if (ll != null && ll.size() > 0) {
					map.put("machineName", ll.get(0).get("machine_name"));
					map.put("machineStatus", ll.get(0).get("machine_status"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/****
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
	public Object[] getUsers(Integer id, int page, int rows) {
		String sql = "select c.processNO,c.processName, p.planOrderId, c.id, p.proname,p.markId, c.totalCount, c.submmitCount, c.applyCount, c.usercodes,c.usernames, c.gongwei, c.shebeiNo,p.selfCard from"
				+ " ta_sop_w_ProcessInfor c  inner join ta_sop_w_procard p on  c.fk_procardid=p.id and  c.status='已领' and  c.productStyle='自制' "
				+ " where c.gongwei in "
				+ "(select gongweihao from ta_sop_gongwei where id in(select gongwei_id from ta_screen_sopgongwei where screen_id = :id))";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Map> list = totalDao.findBySql(sql, params, page, rows);// 得出在干工序的人

		// 查询总数量
		String hql = "from ProcessInfor c join c.procard p where c.status='已领' and c.productStyle='自制' and c.gongwei in "
				+ "(select gongweihao from TaSopGongwei where id in (select g.id from Screen s join s.gongweis g  where s.id = ?))";
		int count = totalDao.getCount(hql, id);
		try {
			for (Map map : list) {
				String sql1 = "select machine_name,machine_status from machine where machine_workPosition = :workStation and machine_no = :equitmentNumber";
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("workStation", map.get("gongwei"));
				params1.put("equitmentNumber", map.get("shebeiNo"));
				List<Map> ll = totalDao.findBySql(sql1, params1);
				if (ll != null && ll.size() > 0) {
					map.put("machineName", ll.get(0).get("machine_name"));
					map.put("machineStatus", ll.get(0).get("machine_status"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object[] { count, list };
	}

	@Override
	public List<Map> getProductionSchedule(Integer id) {
		String sql1 = "select p.planOrderId,p.markid from ta_sop_w_ProcessInfor c  inner join ta_sop_w_procard p on  c.fk_procardid=p.id and  c.status='已领' where c.gongwei in (select gongweihao from ta_sop_gongwei where id in(select gongwei_id from ta_screen_sopgongwei where screen_id = :id))";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Map> map = totalDao.findBySql(sql1, params);
		List ids = new ArrayList();
		List pns = new ArrayList();
		for (Map map2 : map) {
			ids.add(map2.get("planOrderId"));
			pns.add(map2.get("markid"));
		}
		String sql = "select od.f_name, od.f_pieceNum, od.f_num  from ta_crm_internalOrderDetail od where fk_internalorder_id in (:ids) and f_pieceNum in (:pns)";
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("ids", ids);
		params1.put("pns", pns);
		List<Map> map1 = totalDao.findBySql(sql, params1);
		return map1;
	}

	/***
	 * 得到屏幕对应的生产和设备状态数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findSCAndSB(Integer screenId) {
		if (screenId != null) {
			Screen screen = (Screen) totalDao.getObjectById(Screen.class,
					screenId);
			if (screen != null) {
				List scList = new ArrayList();
				// 获得工位总数
				Integer gwSize = screen.getGongweis().size();

				// 查询在生产的工位个数
				String hql_scgwCount = "from ProcessInfor where status='已领' and  productStyle='自制' and gongwei in "
						+ "(select gw.gongweihao from Screen sc join sc.gongweis gw where sc.id=?) group by gongwei";
				//int scgwCount = totalDao.getCount(hql_scgwCount, screenId);
				//int scgwCount = Integer.parseInt(totalDao.getObjectByCondition(hql_scgwCount, screenId).toString());
				int scgwCount =totalDao.query(hql_scgwCount, screenId).size();

				// 查询在生产的工人
				String hql_scgrCount = "select usercodes from ProcessInfor where status='已领' and  productStyle='自制' and gongwei in "
						+ "(select gw.gongweihao from Screen sc join sc.gongweis gw where sc.id=?)";
				List<String> codesList = totalDao
						.query(hql_scgrCount, screenId);
				Map<String, String> nameMap = new HashMap<String, String>();
				if (codesList != null && codesList.size() > 0) {
					for (String codeStr : codesList) {
						String[] codes = codeStr.split(",");
						for (String code : codes) {
							String userCode = nameMap.get(code);
							if (userCode == null) {
								nameMap.put(code, code);
							}
						}
					}
				}
				int scgrCount = nameMap.size();

				// 查询设备总数
				String hql_sbCount = "from Machine where workPosition in "
						+ "(select gw.gongweihao from Screen sc join sc.gongweis gw where sc.id=?)";
				int sbCount = totalDao.getCount(hql_sbCount, screenId);

				// 查询生产中设备
				String hql_scsbCount = "from ProcessInfor where shebeiNo is not null and shebeiNo <>'' and status='已领' and productStyle='自制' and gongwei in "
						+ "(select gw.gongweihao from Screen sc join sc.gongweis gw where sc.id=?) group by shebeiNo";
				//int scsbCount = totalDao.getCount(hql_scsbCount, screenId);
				int scsbCount = totalDao.query(hql_scsbCount, screenId).size();

				/***
				 * 查询工位设备总能耗
				 */
				String hql_bzsbNh = "select sum(allNenghao) from ProcessInforReceiveLog where fk_pirLId in "
						+ "(select id from ProcessInforReceiveLog where status='提交' and fk_pirLId is null )";
				Float bzsbNhM = (Float) totalDao
						.getObjectByCondition(hql_bzsbNh);
				if (bzsbNhM == null) {
					bzsbNhM = 0F;
				}
				/***
				 * 查询本月设备能耗(周一到周日)
				 */
				Util.getNowday();
				hql_bzsbNh += " and firstApplyDate>? and sumitApplyDate<?";
				Float bzsbNhW = (Float) totalDao.getObjectByCondition(
						hql_bzsbNh, Util.getDateTime("yyyy-MM") + "-01", Util
								.getSundayOfThisWeek()
								+ " 23:59:59");
				if (bzsbNhW == null) {
					bzsbNhW = 0F;
				}
				/***
				 * 查询今天设备能耗
				 */
				Float bzsbNhD = (Float) totalDao.getObjectByCondition(
						hql_bzsbNh, Util.getDateTime("yyyy-MM-dd"), Util
								.getDateTime("yyyy-MM-dd")
								+ " 23:59:59");
				if (bzsbNhD == null) {
					bzsbNhD = 0F;
				}
				scList.add(gwSize);// 0
				scList.add(scgwCount);// 1
				scList.add(scgrCount);// 2
				scList.add(sbCount);// 3
				scList.add(scsbCount);// 4
				scList.add(Float.parseFloat(String.format("%.2f", bzsbNhM)));// 5
				scList.add(Float.parseFloat(String.format("%.2f", bzsbNhW)));// 6
				scList.add(Float.parseFloat(String.format("%.2f", bzsbNhD)));// 7

				// 下面开始查询周一到周日的能耗明细

				return scList;

			}
		}

		return null;
	}

	@Override
	public Integer oneScreenCount() {
		// TODO Auto-generated method stub
		return totalDao.getCount("from Screen");
	}
	public Map<Integer, Object> findAll(Printer printer, int pageNo, int pageSize){
		if (printer == null) {
			printer= new Printer();
		}
		String hql = totalDao.criteriaQueries(printer,null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public String addPrinter(Printer printer){
		if(printer!=null){
			String hql = "from Printer where name = ?";
			Printer printer_old = (Printer)totalDao.getObjectByCondition(hql, printer.getName());
			if(printer_old==null){
				printer.setCount(0);
				printer.setAddTime(Util.getDateTime());
				printer.setStatus("");
				if(totalDao.save(printer)){
					return "添加成功";
				}else{
					return "添加失败";
				}
			}else{
				return "该名称的打印机已经存在！";
			}
		}else{
			return "请重新录入信息！";
		}
	}
	
	public String delPrinter(Printer printer){
		if(printer.getId()!=null){
			Printer printer_old = (Printer)totalDao.get(Printer.class, printer.getId());
			if(totalDao.delete(printer_old)){
				return "删除成功";
			}else{
				return "删除失败";
			}
		}else{
			return "数据异常";
		}
	}
	public Printer getPrinterbyId(Printer printer){
		if(printer!=null){
			return (Printer)totalDao.get(Printer.class, printer.getId());
		}else{
			return null;
		}
	}
	public String updatePirnter(Printer printer){
		if(printer!=null){
			String hql = "from Printer where name = ?";
			Printer printer_old = (Printer)totalDao.getObjectByCondition(hql, printer.getName());
			if(printer_old==null){
				Printer printer_zhiqian = (Printer)totalDao.get(Printer.class, printer.getId());
				printer_zhiqian.setName(printer.getName());
				printer_zhiqian.setIp(printer.getIp());
				printer_zhiqian.setPerson(printer.getPerson());
				printer_zhiqian.setWorkPosition(printer.getWorkPosition());
				if(totalDao.update(printer_zhiqian)){
					return "更新成功";
				}else{
					return "更新失败";
				}
			}else{
				return "该名称的打印机已经存在！";
			}
		}else{
			return "请重新录入信息！";
		}
	}
}
