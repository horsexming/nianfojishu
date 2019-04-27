package com.task.ServerImpl.android.processpush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.android.processpush.PushServer;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.entity.android.processpush.Push;
import com.task.entity.android.pscs.Customer;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.sop.ProcessTemplateFile;
import com.task.util.Util;

public class PushServerImpl implements PushServer {
	private final static String gxtzPath = "/upload/file/processTz";
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * 
	 * 查询工艺规范推送(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#findPush(com.task.entity
	 * .android.processpush.Push, int, int)
	 */
	@Override
	public Object[] findPush(Push push, int pageNo, int pageSize) {
		if (push == null) {
			push = new Push();
		}
		String hql = "";
		hql = totalDao.criteriaQueries(push, null);
		hql = hql + " order by sysdate desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/*
	 * 
	 * 添加设备对应工位(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#addProcessandmeid(com.
	 * task.entity.android.processpush.Push)
	 */

	@Override
	public void addProcessandmeid(Push push) {
		// TODO Auto-generated method stub
		String date1 = Util.getDateTime();
		push.setSysdate(date1);
		// TaSopGongwei taSopGongwei2 = (TaSopGongwei)
		// this.totalDao.getObjectById(TaSopGongwei.class,
		// taSopGongwei.getId());
		// push.setStation(taSopGongwei2.getGongweihao());
		//		
		// Set<TaSopGongwei> tsSEt = push.getTaSopGongweis();
		// if(tsSEt==null){
		// tsSEt=new HashSet<TaSopGongwei>();
		// }
		// tsSEt.add(taSopGongwei2);
		// push.setTaSopGongweis(tsSEt);
		// push.getTaSopGongweis().add(taSopGongwei2);
		this.totalDao.save(push);
	}

	/*
	 * 
	 * 添加设备对应工位(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#addProcessandmeid(com.
	 * task.entity.android.processpush.Push)
	 */

	@Override
	public Push addProcessandmeid1(String meid, String ipAddress,
			String flatNum, String flatStation) {
		// TODO Auto-generated method stub
		Push push = new Push();
		String date1 = Util.getDateTime();
		push.setSysdate(date1);
		push.setMeid(meid);
		push.setIpAddress(ipAddress);
		push.setFlatNum(flatNum);
		push.setFlatStation(flatStation);
		this.totalDao.save(push);
		return push;
	}

	/*
	 * 
	 * 根据编号查询工位和序列号信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#findPushById(java.lang
	 * .Integer)
	 */
	@Override
	public Push findPushById(Integer pushId) {
		return (Push) this.totalDao.getObjectById(Push.class, pushId);

	}

	/*
	 * 
	 * 修改工位和序列号(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#updateProcessandmeid(com
	 * .task.entity.android.processpush.Push)
	 */
	@Override
	public void updateProcessandmeid(Push push) {
		// TODO Auto-generated method stub
		String date1 = Util.getDateTime();
		Push push2 = (Push) this.totalDao.getObjectById(Push.class, push
				.getId());
		push2.setSysdate(date1);
		push2.setMeid(push.getMeid());
		push2.setIpAddress(push.getIpAddress());
		push2.setFlatNum(push.getFlatNum());
		push2.setFlatStation(push.getFlatStation());
		this.totalDao.update(push2);

	}

	/*
	 * 
	 * 删除工位和序列号信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#delPush(java.lang.Integer)
	 */
	@Override
	public void delPush(Integer pushId) {
		Push push = (Push) this.totalDao.getObjectById(Push.class, pushId);
		if (push != null) {
			String sql = "delete ta_push_gongwei where push_id=?";
			totalDao.createQueryUpdate(null, sql, pushId);
		}
		this.totalDao.delete(push);
	}

	/*
	 * 
	 * 工艺规范推送接口(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#android_Push(java.lang
	 * .String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List android_Push(String meid) {
		// 查询出所有在干的件号根据设备序列号
		// String sql =
		// "select distinct(p.markid) as jianNumb from ta_sop_w_ProcessInfor c inner join ta_sop_w_procard p on "
		// +
		// " c.fk_procardid=p.id and  c.status='已领' where c.gongwei in (select gongweihao from ta_sop_gongwei where id in"
		// +
		// "(select gongwei_id from ta_push_gongwei where push_id = (select id from ta_Push where meid=?)))";
		// List jianNumbList = totalDao.createQuerySelect(null, sql, meid);
		// String markids = "";
		// for (int i = 0, len = jianNumbList.size(); i < len; i++) {
		// String markId = (String) jianNumbList.get(i).toString();
		// markids += "'" + markId + "'";
		// if (len - 1 > i) {
		// markids += ",";
		// }
		// }
		// if (jianNumbList != null && jianNumbList.size() > 0) {
		// // 根据件号得到工艺规范
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("markid", jianNumbList);
		// String hql = "select id from GongyiGuicheng g where g.jianNumb in ("
		// + markids + ")";
		// List list = totalDao.query(hql);
		// if (list != null && list.size() > 0) {
		// String ggIds = "";
		// for (int i = 0, len = list.size(); i < len; i++) {
		// Integer ggId = (Integer) list.get(i);
		// ggIds += "" + ggId + "";
		// if (len - 1 > i) {
		// ggIds += ",";
		// }
		// }
		// // 查询在干件号的在干工序对应的工序图纸
		// map.put("gongyiId", list);
		// String hql1 =
		// "select url from GongyiGuichengAffix g where g.processDataId in (select id from ProcessData p where "
		// + "p.gongyiGuichengId in ("
		// + ggIds
		// + ") and gongxuNo in (select distinct processNO from ProcessInfor "
		// +
		// "where procard.id in ( select id from Procard pro where pro.markId in ("
		// + markids
		// +
		// ")) and status='已领' and gongwei in (select t.gongweihao from TaSopGongwei t join t.pushs p where p.meid =?) ) )  and  g.affixType='tupian' ";
		// // select u.id from Users u join u.moduleFunction f where f.id
		// // =?
		// List list1 = totalDao.query(hql1, meid);
		// return list1;
		// }
		// }
		List<ProcessTemplateFile> list = totalDao
				.query(
						" from ProcessTemplateFile pfile where pfile.type='工艺规范' and pfile.processNO in(select distinct process.processNO  from ProcessInfor process"
								+ " where pfile.processName=process.processName and pfile.banci=process.procard.banci and pfile.markId=process.procard.markId and process.status='已领' and process.gongwei in"
								+ "(select t.gongweihao from TaSopGongwei t join t.pushs p where p.meid =?)) ",
						meid);
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

	/*
	 * 
	 * 查询所有工位(non-Javadoc)
	 * 
	 * @see com.task.Server.android.processpush.PushServer#findTaSopGongwei()
	 */
	@Override
	public List findTaSopGongwei() {
		// TODO Auto-generated method stub
		String hql = "from TaSopGongwei";
		List list = this.totalDao.queryList(hql);
		return list;
	}

	@Override
	public List findGwidByname(Integer push_id) {
		String hql = "from TaSopGongwei where id not in(select p.id from TaSopGongwei p join p.pushs g where g.id="
				+ push_id + " ) order by gongweihao";
		return this.totalDao.query(hql);
	}

	/*
	 * 
	 * 查询设备对应工位(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#findPush(com.task.entity
	 * .android.processpush.Push, int, int, java.lang.Integer)
	 */
	@Override
	public Object[] findPush1(TaSopGongwei taSopGongwei, int pageNo,
			int pageSize, Integer pushId) {
		String hql = "from TaSopGongwei where id in(select u.id from TaSopGongwei u join u.pushs f where f.id="
				+ pushId + ")";
		// hql = totalDao.criteriaQueries(push, null);
		// if(pushId!=null){
		// hql = hql + "and  id= "+pushId+"";
		// }
		// hql = hql + " order by sysdate desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 添加其对应的工位
	@Override
	public void addProcessandmeid1(Push push, TaSopGongwei taSopGongwei) {
		TaSopGongwei taSopGongwei2 = (TaSopGongwei) this.totalDao
				.getObjectById(TaSopGongwei.class, taSopGongwei.getId());
		Push push2 = (Push) this.totalDao.getObjectById(Push.class, push
				.getId());
		push2.setStation(taSopGongwei2.getGongweihao());

		Set set = push2.getTaSopGongweis();
		set.add(taSopGongwei2);
		push2.setTaSopGongweis(set);
		this.totalDao.save(push2);
	}

	// 删除工位
	@Override
	public void delGongwei(Integer id, Integer push_id) {
		TaSopGongwei taSopGongwei = (TaSopGongwei) this.totalDao.getObjectById(
				TaSopGongwei.class, id);
		Push push = (Push) this.totalDao.getObjectById(Push.class, push_id);
		// Set<Push> proSet = taSopGongwei.getPushs();
		Set<TaSopGongwei> proSet = push.getTaSopGongweis();
		proSet.remove(taSopGongwei);
		this.totalDao.update(push);
	}

	/*
	 * 
	 * 根据序列号查询(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.processpush.PushServer#findPushBymeid(java.lang
	 * .String)
	 */
	@Override
	public Push findPushBymeid(String meid) {
		// TODO Auto-generated method stub
		String hql = "from Push where meid=?";
		Push push = (Push) this.totalDao.getObjectByCondition(hql, meid);
		return push;
	}

	@Override
	public List findPushgongweiById(Integer id) {
		String hql = "from TaSopGongwei ta  join ta.pushs pu where   pu.id=?";
		List list = this.totalDao.query(hql, id);
		return list;
	}

	@Override
	public List findGwidByname1(Integer id) {
		String hql = "from TaSopGongwei where id in(select u.id from TaSopGongwei u join u.pushs f where f.id=?)";
		return this.totalDao.query(hql, id);
	}

	@Override
	public boolean binDingUsers(Push push, Integer[] gwId) {
		if (push != null) {
			Set<TaSopGongwei> taSopGongweisSet = new HashSet<TaSopGongwei>();
			if (gwId != null && gwId.length > 0) {
				for (int i = 0; i < gwId.length; i++) {
					TaSopGongwei taSopGongwei = (TaSopGongwei) totalDao
							.getObjectById(TaSopGongwei.class, gwId[i]);
					if (push != null) {
						taSopGongweisSet.add(taSopGongwei);
					} else {
						return false;
					}
				}
			}
			push.setTaSopGongweis(taSopGongweisSet);
			boolean bool = totalDao.update(push);
			return bool;
		}
		return false;
	}

}
