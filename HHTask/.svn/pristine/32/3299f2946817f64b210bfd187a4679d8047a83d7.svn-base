package com.task.ServerImpl.android.processpush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.android.processpush.OneMachineServer;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.entity.android.processpush.OneMachine;
import com.task.entity.onemark.OneLight;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessInforReceiveLog;
import com.task.entity.sop.ProcessTemplateFile;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class OneMachineServerImpl implements OneMachineServer {
	private final static String gxtzPath = "/upload/file/processTz";

	private TotalDao totalDao;

	@Override
	public String addOneMachine(OneMachine machine) {
		// TODO Auto-generated method stub
		
		if (machine != null) {
			machine.setAddTime(Util.getDateTime());
			if (totalDao.save(machine))
				return "添加成功！";
			else
				return "添加失败！";
		}
		return "对象为空，添加成功！";
	}

	@Override
	public OneMachine byIdOneMachine(Integer id) {
		// TODO Auto-generated method stub
		return (OneMachine) totalDao.getObjectById(OneMachine.class, id);
	}

	@Override
	public String deleteOneMachine(Integer id) {
		// TODO Auto-generated method stub
		OneMachine obje = byIdOneMachine(id);
		if (obje != null) {
			obje.setOneLights(null);
			if (totalDao.delete(obje))
				return "删除成功！";
			else
				return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Map<Integer, Object> findOneMachine(OneMachine machine, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		if (machine == null) {
			machine = new OneMachine();
		}
		String hql = totalDao.criteriaQueries(machine, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(0, list);
		map.put(1, count);
		return map;
	}

	@Override
	public String updateOneMachine(OneMachine machine) {
		// TODO Auto-generated method stub
		OneMachine machine2 = byIdOneMachine(machine.getId());
		if (machine2 != null) {
			BeanUtils.copyProperties(machine, machine2, new String[] { "id",
					"addTime", "taSopGongweis", "oneLights" });
			machine2.setUpdateTime(Util.getDateTime());
			if (totalDao.update(machine2))
				return "修改成功！";
			else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String binDingMachine(OneMachine machine, Integer[] taGwId) {
		// TODO Auto-generated method stub
		if (machine != null) {
			Set<TaSopGongwei> usersSet = new HashSet<TaSopGongwei>();// 用来存储最终要的绑定工位
			// Set<TaSopGongwei> moreSet = new HashSet<TaSopGongwei>();//
			// 用来存储相对之前增加的绑定工位
			// Set<TaSopGongwei> lessSet = new HashSet<TaSopGongwei>();//
			// 用来存储相对之前减少的绑定工位
			OneMachine machine2 = (OneMachine) totalDao.getObjectById(
					OneMachine.class, machine.getId());
			// Set<TaSopGongwei> haduserSet = machine2.getTaSopGongweis();
			if (taGwId != null && taGwId.length > 0) {
				for (int i = 0; i < taGwId.length; i++) {
					TaSopGongwei gongwei = (TaSopGongwei) totalDao
							.getObjectById(TaSopGongwei.class, taGwId[i]);// 查询工位
					if (gongwei != null) {
						usersSet.add(gongwei);
					} else {
						System.out.println("null");
					}
				}
			}
			// for (TaSopGongwei ta4 : haduserSet) {
			// if (!usersSet.contains(ta4)) {// 页面传过来的不包含说明页面新减少了这个对象的绑定
			// lessSet.add(ta4);
			// }
			// }
			// for (TaSopGongwei ta5 : usersSet) {
			// if (!haduserSet.contains(ta5)) {// 原来的的不包含说明页面新增加了这个对象的绑定
			// moreSet.add(ta5);
			// }
			// }
			machine2.setTaSopGongweis(usersSet);
			if (totalDao.update(machine2)) {
				return "绑定成功！";
			}
		}
		return "绑定失败！";
	}

	@Override
	public List<TaSopGongwei> findAllBangGw(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from TaSopGongwei where id in (select t.id from TaSopGongwei t join t.machines om where om.id=?) and gongweihao is not null and gongweihao <> '' order by gongweihao";
		return totalDao.query(hql, id);
	}

	@Override
	public Object[] findAllTagw(Integer id, int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		// select u.id from TaSopGongwei u join u.pushs f where
		// f.id="+ pushId + "
		String hql = "from TaSopGongwei where id not in (select t.id from TaSopGongwei t join t.machines om where om.id=?) and gongweihao is not null and gongweihao <> '' order by gongweihao";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize, id);
		int count = totalDao.getCount(hql, id);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public Object[] findMaIdByCondition(TaSopGongwei taSopGongwei,
			int parseInt, int pageSize, Integer id) {
		// TODO Auto-generated method stub
		if (taSopGongwei == null) {
			taSopGongwei = new TaSopGongwei();
		}
		String hql = totalDao.criteriaQueries(taSopGongwei, null);
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public List findProcardListByGwCard(String gongwei) {
		// TODO Auto-generated method stub gongweiList
		String hql = "from Procard where jihuoStatua='激活' and rootId in "
				+ "(select p.id from Procard p join p.processPeopleSet u"
				+ " ) and "
				+ " (hascount>=0)"
				+ " and id in "
				+ "(select pi.procard.id from ProcessInfor pi where status not in ('完成') and gongwei in ("
				+ gongwei + ") )" + " and status in ('已发料','领工序')";
		hql += " order by selfCard,procardTime";
		List<Procard> list = totalDao.query(hql);

		for (Procard procard : list) {
			Procard rootProcard = (Procard) totalDao.getObjectById(
					Procard.class, procard.getRootId());
			procard.setRootMarkId(rootProcard.getMarkId());
			procard.setRootSelfCard(rootProcard.getSelfCard());

		}
		return list;
	}

	public List findProcardListByGwAndCard(String gongwei) {
		// TODO Auto-generated method stub gongweicardLing
		Users users = Util.getLoginUser();
		if (users != null) {
			String hql = "from Procard where jihuoStatua='激活' and rootId in "
					+ "(select p.id from Procard p join p.processPeopleSet u where u.userId = "
					+ "(select id from Users where cardId= ? ) ) and "
					+ " (hascount>=0)"
					+ " and id in "
					+ "(select pi.procard.id from ProcessInfor pi where status not in ('完成') and gongwei in ("
					+ gongwei
					+ ") and pi.processName in (select p.processName from ProcessGzstore p join p.users u where u.cardId=?) )"
					+ " and status in ('已发料','领工序')";
			hql += " order by selfCard,procardTime";
			List<Procard> list = totalDao
					.query(hql, "0001522466", "0001522466");

			for (Procard procard : list) {
				Procard rootProcard = (Procard) totalDao.getObjectById(
						Procard.class, procard.getRootId());
				procard.setRootMarkId(rootProcard.getMarkId());
				procard.setRootSelfCard(rootProcard.getSelfCard());

			}
			return list;
		}
		return null;
	}

	@Override
	public OneMachine byIp(String ip) {
		// TODO Auto-generated method stub
		String hql = "from OneMachine where ipAddress=?";
		OneMachine oneMachine = (OneMachine) this.totalDao
				.getObjectByCondition(hql, ip);
		return oneMachine;
	}

	@Override
	public List findPushgongweiById(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from TaSopGongwei ta join ta.machines om where om.id=?";
		List list = this.totalDao.query(hql, id);
		return list;
	}

	@Override
	public List tuzhiLj(String ip) {
		// TODO Auto-generated method stub
		List<ProcessTemplateFile> list = totalDao
				.query(
						" from ProcessTemplateFile pfile where pfile.type='工艺规范' and pfile.status='默认' "
								+ " and pfile.processNO in(select distinct process.processNO  from ProcessInfor process"
								+ " where pfile.processName=process.processName and pfile.markId=process.procard.markId and pfile.banci=process.procard.banci and process.status='已领' and process.gongwei in"
								+ "(select t.gongweihao from TaSopGongwei t join t.machines m where m.ipAddress =?)) ",
						ip);
		List<String> list2 = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (ProcessTemplateFile file : list) {
				String url = gxtzPath + "/" + file.getMonth() + "/"
						+ file.getFileName();
				list2.add(url);
			}
		}
		return list2;
	}

	@Override
	public String gongw(String ip) {
		// TODO Auto-generated method stub
		String hql = "select distinct t.gongweihao from TaSopGongwei t join t.machines m where m.ipAddress =?)";
		List<String> list = totalDao.query(hql, ip);
		if (list != null && list.size() > 0) {
			return "'"
					+ list.toString().replaceAll("\\[", "").replaceAll("\\]",
							"").replaceAll(",", "','").replaceAll(" ", "")
					+ "'";
		} else
			return "";
	}

	@Override
	public List<Map<String, Object>> findgognxuInfor(String gongwei, String tag) {
		// TODO Auto-generated method stub
		String hql = "";
		Users userss = Util.getLoginUser();
		if (userss != null && tag != null && !"one".equals(tag)) {
			hql = "from ProcessInfor where gongwei in ("
					+ gongwei
					+ ") and status = '已领' and productStyle='自制' and (dataStatus is null or dataStatus!='删除') and userCardId like ('"
					+ userss.getCardId() + "')";
		} else {
			hql = "from ProcessInfor where gongwei in (" + gongwei
					+ ") and status = '已领' and (dataStatus is null or dataStatus!='删除') and productStyle='自制'";
		}
		List<Map<String, Object>> listAll = new ArrayList<Map<String, Object>>();
		List processInforList = totalDao.findAllByPage(hql, 1, 6);
		if (processInforList != null && processInforList.size() > 0) {
			for (int j = 0; j < processInforList.size(); j++) {
				Map<String, Object> map = new HashMap<String, Object>();
				ProcessInfor infor = (ProcessInfor) processInforList.get(j);
				ProcessInforReceiveLog pg = null;
				if ("初始".equals(infor.getStatus())) {
					pg = new ProcessInforReceiveLog();
					pg.setGxstatus("初始");
					pg.setFk_processInforId(infor.getId());
				} else if (!"完成".equals(infor.getStatus())) {
					if(userss!=null){
						String hql_pg = " from ProcessInforReceiveLog where usercodes like '%"
							+ userss.getCode()
							+ "%' and fk_processInforId = '"
							+ infor.getId() + "'" + " and  status='领取' ";
						pg = (ProcessInforReceiveLog) totalDao
						.getObjectByCondition(hql_pg);
						if (pg != null) {
							pg.setGxstatus("已领");
						} else {
							pg = new ProcessInforReceiveLog();
							pg.setGxstatus("待领");
						}
					}
				}
				infor.setPg(pg);
//				if(j==4){
//					System.out.println(infor.getId()+" ********************8");
//				}
				Float needTime=0F;
				if(infor.getOpshebeijiepai()==null){
					infor.setOpshebeijiepai(3F);
				}
				if(infor.getOpcaozuojiepai()==null){
					infor.setOpcaozuojiepai(3F);
				}
				if(infor.getGzzhunbeijiepai()==null){
					infor.setGzzhunbeijiepai(3F);
				}
				if(infor.getGzzhunbeicishu()==null){
					infor.setGzzhunbeicishu(3F);
				}
				try {
					needTime = (infor.getApplyCount() - infor
							.getSubmmitCount())
							* (infor.getOpshebeijiepai()
									+ infor.getOpcaozuojiepai() + infor
									.getGzzhunbeijiepai()
									* infor.getGzzhunbeicishu());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (needTime == null) {
					needTime = 0F;
				}
				String nfd = Util.getCalendarModified(
						infor.getFirstApplyDate(), 13, needTime.intValue());
				// 如果不存在完成时间，则强制设置为今天的下班时间
				if (nfd == null || "".equals(nfd)) {
					infor.setSubmitDate(Util.getDateTime("yyyy-MM-dd")
							+ " 16:30:00");
				} else {
					infor.setSubmitDate(nfd);
				}
				map.put("processInfor", infor);
				// 获取头像链接
				// 获取领取人工号和姓名
				String[] code = null;
//				String[] cardId = null;
				if (infor.getUsercodes() != null
						&& infor.getUsernames() != null
						&& !"".equals(infor.getUsercodes())
						&& !"".equals(infor.getUsernames())) {
					code = infor.getUsercodes().split(",");// 可能为空？
//					cardId = infor.getUserCardId().split(",");
					List<Users> userslist = new ArrayList<Users>();
					for (int i = 0; i < code.length; i++) {
						try {
							Users users = (Users) totalDao.getObjectByCondition(
									"from Users where code = ?",
									code[i]);
							if (users != null) {
								userslist.add(users);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					map.put("users", userslist);
				} else {
					map.put("users", null);
				}
				Integer banci = infor.getProcard().getBanci();//版次
				String addSql = null;
				if(banci==null||banci==0){
					addSql = " and (banci is null  or banci =0)";
				}else{
					addSql = " and banci is not null and banci ="+banci;
				}
				String addSql2=null;
				if(infor.getProcard().getProductStyle().equals("批产")){
					addSql2 = " and (productStyle is null or productStyle !='试制') ";
				}else{
					addSql2 = " and productStyle ='试制' and glId="+infor.getId();
				}
				List<ProcessTemplateFile> list = totalDao
						.query(
								"from ProcessTemplateFile where processNO = ? and processName = ? and type = '工艺规范' and markId = ? "+addSql2+addSql,
								infor.getProcessNO(), infor.getProcessName(),
								infor.getProcard().getMarkId());
				map.put("tuzhi", list);
				List<ProcessTemplateFile> list1 = totalDao
						.query(
								"from ProcessTemplateFile where markId = ? and type = '3D文件' and processNo is null "+addSql2+addSql,
								infor.getProcard().getMarkId());
				map.put("tdtuzhi", list1);
				listAll.add(map);
			}
		} else {
			return null;
		}
		return listAll;
	}

	@Override
	public List findquexiantuzhi(String gongwei) {
		// TODO Auto-generated method stub
		String hql = "from ProcessInfor where gongwei in (" + gongwei
				+ ") and status = '已领'";
		List processInforList = totalDao.query(hql);
		if (processInforList != null && processInforList.size() > 0) {
			ArrayList<ProcessTemplateFile> arrayList = new ArrayList<ProcessTemplateFile>();
			for (int j = 0; j < processInforList.size(); j++) {
				ProcessInfor infor = (ProcessInfor) processInforList.get(j);
				Integer banci = infor.getProcard().getBanci();//版次
				String addSql = null;
				if(banci==null||banci==0){
					addSql = " and (banci is null  or banci =0)";
				}else{
					addSql = " and banci is not null and banci ="+banci;
				}
				String addSql2=null;
				if(infor.getProcard().getProductStyle().equals("批产")){
					addSql2 = " and (productStyle is null or productStyle !='试制') ";
				}else{
					addSql2 = " and productStyle ='试制' ";
				}
				List<ProcessTemplateFile> list = totalDao
						.query(
								// and type = '缺陷图纸'
								"from ProcessTemplateFile where processNO = ? and processName = ? and markId = ? and type = '缺陷图纸' "+addSql2+addSql,
								infor.getProcessNO(), infor.getProcessName(),
								infor.getProcard().getMarkId());
				if (list != null && list.size() > 0) {
					for (ProcessTemplateFile processTemplateFile : list) {// "upload/file/processTz/"+processTemplateFile.getMonth()+"/"+processTemplateFile.getFileName()
						arrayList.add(processTemplateFile);
					}
				}
				// List<ProcessTemplateFile> list1 = totalDao
				// .query(
				// "from ProcessTemplateFile where markId = ? and type = '3D文件' and processNo is null ",
				// infor.getProcard().getMarkId());
			}
			return arrayList;
		}
		return null;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return Util.getLoginUser().getName();
	}

	@Override
	public String binDingOneLight(Integer onemach, Integer[] taOLId) {
		// TODO Auto-generated method stub
		if (onemach != null && onemach > 0) {
			Set<OneLight> usersSet = new HashSet<OneLight>();// 用来存储最终要的绑定工位
			// Set<OneLight> moreSet = new HashSet<OneLight>();//用来存储相对之前增加的绑定工位
			// Set<OneLight> lessSet = new HashSet<OneLight>();//用来存储相对之前减少的绑定工位
			OneMachine machine2 = (OneMachine) totalDao.getObjectById(
					OneMachine.class, onemach);
			// Set<OneLight> haduserSet = machine2.getOneLights();//用来存储之前绑定的灯
			if (taOLId != null && taOLId.length > 0) {
				for (int i = 0; i < taOLId.length; i++) {
					OneLight OneLight1 = (OneLight) totalDao.getObjectById(
							OneLight.class, taOLId[i]);// 查询工位
					if (OneLight1 != null) {
						usersSet.add(OneLight1);
					} else {
						System.out.println("null");
					}
				}
			}
			// for (OneLight ta4 : haduserSet) {
			// if (!usersSet.contains(ta4)) {// 页面传过来的不包含说明页面新减少了这个对象的绑定
			// lessSet.add(ta4);
			// }
			// }
			// for (OneLight ta5 : usersSet) {
			// if (!haduserSet.contains(ta5)) {// 原来的的不包含说明页面新增加了这个对象的绑定
			// moreSet.add(ta5);
			// }
			// }
			machine2.setOneLights(usersSet);
			if (totalDao.update(machine2)) {
				return "绑定成功！";
			}
		}
		return "绑定失败！";
	}

	@Override
	public List<OneLight> findAllBangLight(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from OneLight where machiness.id = ? order by id desc";
		return totalDao.query(hql, id);
	}

	@Override
	public Object[] findAllOneLight(OneLight oneLight, int parseInt,
			int pageSize) {
		// TODO Auto-generated method stub
		if (oneLight == null) {
			oneLight = new OneLight();
		}
		String hql = totalDao.criteriaQueries(oneLight, null);
		hql += " and machiness.id = null and accessEquipment.id = null and (aceIs is null or aceIs <> '是')";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 查询待检码
	 * 
	 * @return
	 */
	@Override
	public List findDjm() {
		Users loginUser = Util.getLoginUser();
		if (loginUser != null) {
			String hql = "from LogoStickers where code = '"
					+ loginUser.getCode() + "' and isPrint='NO'";
			// String hql =
			// "from LogoStickers where isPrint='NO' and id >12350";
			List list = totalDao.query(hql);
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return null;
	}

	@Override
	public Integer oneMachineCount() {
		// TODO Auto-generated method stub
		return totalDao.getCount("from OneMachine");
	}
}
