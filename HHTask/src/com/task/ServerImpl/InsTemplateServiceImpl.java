package com.task.ServerImpl;

import java.util.ArrayList;
import com.task.entity.sop.ProcessTemplateFile;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.InsTemplateService;
import com.task.entity.android.InsRecord;
import com.task.entity.android.InsRecordScope;
import com.task.entity.android.InsScope;
import com.task.entity.android.InsTemplate;
import com.task.entity.android.OsTemplate;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.ProcardTemplate;
import com.task.util.Util;

public class InsTemplateServiceImpl implements InsTemplateService {
	private TotalDao totalDao;

	@Override
	public boolean add(InsTemplate t) {
		Date d = new Date();
		t.setCreateDate(d);
		t.setScope(new HashSet<InsScope>(t.getScopes()));
		return totalDao.save(t);
	}

	@Override
	public Object[] list(OsTemplate t, Integer rows, Integer size, String status) {
		// String hql = "from InsTemplate order by id desc";
		// List list = totalDao.findAllByPage(hql, rows, size);
		// int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		// return o;
		if (t == null) {
			t = new OsTemplate();
		}
		String hql = totalDao.criteriaQueries(t, null, null);
		if ("".equals("ty")) {
			hql += " and ispublic = '是' order by id desc";
		} else {
			hql += " and zhonglei = '巡检' order by id desc";
		}
		List list = totalDao.findAllByPage(hql, rows, size);
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

	@Override
	public List<InsTemplate> getData() {
		String hql = "from InsTemplate order by id desc";
		List<InsTemplate> list = totalDao.find(hql);
		for (InsTemplate insTemplate : list) {
			Set<InsScope> l = insTemplate.getScope();
			for (InsScope insScope : l) {
				insScope.getId();
			}
		}
		return list;
	}

	@Override
	public InsTemplate get(InsTemplate t) {
		InsTemplate t1 = (InsTemplate) totalDao.get(t.getClass(), t.getId());
		for (InsScope s : t1.getScope()) {
			s.getId();
		}
		return t1;
	}

	@Override
	public void update(InsTemplate t) {
		InsTemplate t1 = (InsTemplate) totalDao.get(t.getClass(), t.getId());
		t.setScope(new HashSet<InsScope>(t.getScopes()));
		Set<InsScope> set = t.getScope();
		if (set != null) {
			for (InsScope insScope : set) {
				totalDao.delete(insScope);
			}
		}
		BeanUtils.copyProperties(t, t1, new String[] { "id", "createDate" });
		totalDao.update(t1);
	}

	@Override
	public void updateTest() {
		String hql = "SELECT productType, partNumber, processNumber, gwNumber FROM Templatem GROUP BY productType, partNumber, processNumber, gwNumber)";
		List list = totalDao.createQuerySelect(hql, null);
		List<Object[]> l = list;
		for (Object[] object : l) {
			InsTemplate t1 = new InsTemplate();
			t1.setProductType(object[0].toString());
			t1.setPartNumber(object[1].toString());
			t1.setProcessNumber(object[2].toString());
			t1.setWorkStation(object[3].toString());
			t1.setCreateDate(new Date());
			totalDao.save(t1);
		}
	}

	@Override
	public void delete(InsTemplate t) {
		String hql = "from InsRecord where root.id = ?";
		List<InsRecord> list = totalDao.find(hql, new Object[] { t.getId() });

		for (InsRecord insRecord : list) {
			totalDao.delete(insRecord);
			for (InsRecordScope rs : insRecord.getRecordScope()) {
				totalDao.delete(rs);
			}
		}
		totalDao.delete(t);
	}

	@Override
	public Map<Integer, Object> findInsTemplateByCondition(InsTemplate t,
			int pageNo, int pageSize) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		if (t == null) {
			t = new InsTemplate();
		}
		String hql = totalDao.criteriaQueries(t, null);
		int count = totalDao.getCount(hql);
		List<InsTemplate> list = (List<InsTemplate>) totalDao.findAllByPage(hql
				+ " order by id desc", pageNo, pageSize);
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public List<String> findAllmarkidlist(String zhonglei) {
		String sql = "";
		/* SQLServer */
//		String str = " and id IN ( SELECT fk_pricesstid FROM ta_sop_w_ProcessTemplate WHERE processNO is not null )";
//		 sql = "SELECT  markId  from ta_sop_w_ProcardTemplate WHERE  1=1 and markId is not null  AND markId <>  ''"+
//		 " AND markId IN ( select C.markId  from  (select process.processno pno,procard.markId markId from ta_sop_w_ProcessTemplate process join ta_sop_w_ProcardTemplate procard on process.fk_pricesstid=procard.id "+
//		 " GROUP BY process.processno ,procard.markId )C where convert(varchar(20),C.pno)+C.markId  NOT in( "+
//		 " select A.pno+A.markId from  (select convert(varchar(20),process.processno) pno,procard.markId markId from ta_sop_w_ProcessTemplate process join ta_sop_w_ProcardTemplate procard on process.fk_pricesstid=procard.id "+
//		 " GROUP BY process.processno ,procard.markId )A join  (select gongxuNum pno,partnumber markId  from ta_m_OsTemplate where  zhonglei = '巡检'  GROUP BY gongxuNum,partnumber "+
//		 " )B on A.pno=B.pno and A.markId = B.markId)  ) "+str+" group by markId" ;
	/* mysql*/
		String str = " and id IN ( SELECT fk_pricesstid FROM ta_sop_w_ProcessTemplate WHERE processNO is not null )";
		sql = "SELECT  markId  from ta_sop_w_ProcardTemplate WHERE  1=1 and markId is not null  AND markId <>  ''"
				+ " AND markId IN ( select C.markId  from  (select process.processno pno,procard.markId markId from ta_sop_w_ProcessTemplate process join ta_sop_w_ProcardTemplate procard on process.fk_pricesstid=procard.id "
				+ " GROUP BY process.processno ,procard.markId )C where CONCAT(C.pno,C.markId)  NOT in( "
				+ " select CONCAT(A.pno,A.markId) from  (select process.processno pno,procard.markId markId from ta_sop_w_ProcessTemplate process join ta_sop_w_ProcardTemplate procard on process.fk_pricesstid=procard.id "
				+ " GROUP BY process.processno ,procard.markId )A join  (select gongxuNum pno,partnumber markId  from ta_m_OsTemplate where  zhonglei = '巡检'  GROUP BY gongxuNum,partnumber "
				+ " )B on concat(A.pno,'')=concat(B.pno,'') and A.markId = B.markId)  ) "
				+ str + " group by markId";
		if("外购件检验".equals(zhonglei)){
			sql = "SELECT markId FROM ta_sop_w_ProcardTemplate WHERE  markId not IN (SELECT  partNumber FROM ta_m_OsTemplate WHERE (zhonglei = '外购件检验' or zhonglei is null) and partNumber is not null )   AND markId is not null and markId <> ''  GROUP BY markId  ";
		}else if("辅料".equals(zhonglei)){
			sql = "SELECT wlcode FROM ta_oa_appDetailTemplate WHERE  markId not IN (SELECT  partNumber FROM ta_m_OsTemplate WHERE zhonglei = '辅料'  and partNumber is not null )   AND wlcode is not null and wlcode <> ''  GROUP BY wlcode  ";
		}

		List<Map> list = totalDao.findBySql(sql);
		List<String> markIdList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (Map map : list) {
				String str1 = map.get("markId").toString();
				markIdList.add(str1);
			}
		}

		// markIdList = totalDao.find(hql);

		return markIdList;
	}

	@Override
	public ProcardTemplate findprocardBymarkId(String markId) {
		String hql = "from ProcardTemplate where markId =? and (dataStatus is null or dataStatus <> '删除')";
		return (ProcardTemplate) totalDao.getObjectByCondition(hql, markId);
	}

	@Override
	public List<String> findprocessNoList(String partNumber) {
		if (partNumber != null && !"".equals(partNumber)) {
			String hql = "select processNO from ProcessTemplate where "
					+ " procardTemplate in (select id from ProcardTemplate where markId ='"
					+ partNumber
					+ "')"
					+ " and processNO not in (select gongxuNum from OsTemplate where partNumber = '"
					+ partNumber
					+ "' and  zhonglei = '巡检' and gongxuNum is not NULL ) group  by processNO ";
			return totalDao.find(hql);
		}
		return null;
	}

	@Override
	public List<ProcessTemplateFile> getjytuzhi(String markId, String gongxuNum) {
		String hql = "  from ProcessTemplateFile where markId=? and processNO = ? and status = '默认'";
		return totalDao.query(hql, markId, Integer.parseInt(gongxuNum));
	}

	@Override
	public String getgongxuName(String markId, String gongxuNum) {
		String hql = "SELECT processName FROM ProcessTemplate WHERE procardTemplate.id IN (SELECT id FROM ProcardTemplate where markId = ?) AND processNO = ?";
		return (String) totalDao.getObjectByCondition(hql, markId, Integer
				.parseInt(gongxuNum));
	}

	@Override
	public List<ProcessTemplateFile> getjytuzhi1(String markId) {
		String hql = "  from ProcessTemplateFile where markId=?  and  processNO is null and status = '默认'";
		return totalDao.query(hql, markId);
	}

}
