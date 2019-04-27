package com.task.ServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IStoreService;
import com.task.entity.Borrow;
import com.task.entity.Consuming;
import com.task.entity.FanghuOutLib;
import com.task.entity.OaAppDetail;
import com.task.entity.OutLib;
import com.task.entity.StatisticsStore;
import com.task.entity.Storage;
import com.task.entity.Store;
import com.task.entity.Users;
import com.task.entity.VOStore;
import com.task.entity.VOUser;
import com.task.entity.menjin.WareBangGoogs;
import com.task.util.Util;

public class StoreServiceImpl implements IStoreService {

	private TotalDao totalDao;
	private static final String NOTGIVEBACK = "未归还";

	@Override
	public void add(Store store) {
		// TODO Auto-generated method stub
		totalDao.save(store);
	}

	@Override
	public void del(Store store) {
		// TODO Auto-generated method stub
		totalDao.delete(store);
	}

	@Override
	public Store getStoreById(int id) {
		// TODO Auto-generated method stub
		return (Store) totalDao.getObjectById(Store.class, id);
	}

	@Override
	public boolean update(Store store) {
		return totalDao.update(store);
	}

	public int updateStorage(Store store) {
		if (store != null) {
			// 修改入库信息
			String sql = "update Storage set classify=? where fk_store_id=?";
			totalDao.createQueryUpdate(null, sql, store.getClassify(), store
					.getId());

			// 修改借出信息
			String sql2 = "update ta_Borrow set f_format=?, f_name=? where fk_store_id=?";
			totalDao.createQueryUpdate(null, sql2, store.getFormat(), store
					.getMatetag(), store.getId());

		}
		return 0;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] queryStore(Map map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Store s where 1=1 ";
		if (map != null) {
			if (map.get("number") != null)
				hql += " and s.number like '%" + map.get("number") + "%'";
			if (map.get("name") != null)
				hql += " and s.matetag like '%" + map.get("name") + "%'";
			if (map.get("format") != null)
				hql += " and s.format like '%" + map.get("format") + "%'";
			if (map.get("storehouse") != null)
				hql += " and s.storehouse like '%" + map.get("storehouse")
						+ "%'";
			if (map.get("place") != null)
				hql += " and s.place like '%" + map.get("place") + "%'";
		}
		hql += " order by s.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	public void exportExcel(Map map) {
		String hql = "from Store s where 1=1";
		String ku = "";
		if (map != null) {
			if (map.get("number") != null)
				hql += " and s.number like '%" + map.get("number") + "%'";
			if (map.get("name") != null)
				hql += " and s.matetag like '%" + map.get("name") + "%'";
			if (map.get("format") != null)
				hql += " and s.format like '%" + map.get("format") + "%'";
			if (map.get("storehouse") != null){
				hql += " and s.storehouse like '%" + map.get("storehouse") + "%'";
				ku = map.get("storehouse").toString();
			}
			if (map.get("place") != null)
				hql += " and s.place like '%" + map.get("place") + "%'";
		}
		hql += "and total > 0 and curAmount > 0 and total >= curAmount order by s.id desc";
		List list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String((ku+"库存数据").getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet(ku+"库存数据", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 30);
			ws.setColumnView(2, 16);
			ws.setColumnView(8, 24);
			ws.setColumnView(13, 14);
			ws.setColumnView(14, 14);
			ws.setColumnView(15, 60);
			ws.addCell(new Label(0, 0, "编号"));
			ws.addCell(new Label(1, 0, "名称"));
			ws.addCell(new Label(2, 0, "规格"));
			ws.addCell(new Label(3, 0, "库存量"));
			ws.addCell(new Label(4, 0, "总数量"));
			ws.addCell(new Label(5, 0, "单位"));
			ws.addCell(new Label(6, 0, "类别"));
			ws.addCell(new Label(7, 0, "仓库"));
			ws.addCell(new Label(8, 0, "库位"));
			ws.addCell(new Label(9, 0, "车型"));
			ws.addCell(new Label(10, 0, "加工件号"));
			ws.addCell(new Label(11, 0, "加工数量"));
			ws.addCell(new Label(12, 0, "维护周期"));
			ws.addCell(new Label(13, 0, "上次保养"));
			ws.addCell(new Label(14, 0, "上次保检"));
			//ws.addCell(new Label(15, 0, "备注"));
			for (int i = 0; i < list.size(); i++) {
				Store store = (Store) list.get(i);
				ws.addCell(new Label(0, i + 1, store.getNumber()));
				ws.addCell(new Label(1, i + 1, store.getMatetag()));
				ws.addCell(new Label(2, i + 1, store.getFormat()));
				ws
						.addCell(new jxl.write.Number(3, i + 1, store
								.getCurAmount() == null ? 0 : store
								.getCurAmount()));
				ws.addCell(new jxl.write.Number(4, i + 1,
						store.getTotal() == null ? 0 : store.getTotal()));
				ws.addCell(new Label(5, i + 1, store.getUnit()));
				ws.addCell(new Label(6, i + 1, store.getParClass()));
				ws.addCell(new Label(7, i + 1, store.getStorehouse()));
				ws.addCell(new Label(8, i + 1, store.getPlace()));
				ws.addCell(new Label(9, i + 1, store.getCarModel()));
				ws.addCell(new Label(10, i + 1, store.getServerCardId()));
				ws.addCell(new jxl.write.Number(11, i + 1, store
						.getCurworkAmount() == null ? 0 : store
						.getCurworkAmount()));
				ws.addCell(new jxl.write.Number(12, i + 1,
						store.getPeriod() == null ? 0 : store.getPeriod()));
				ws.addCell(new Label(13, i + 1,
						store.getStartDate() != null ? store.getStartDate()
								.toString() : ""));
				ws.addCell(new Label(14, i + 1,
						store.getLastCareDate() != null ? store
								.getLastCareDate().toString() : ""));
				ws.addCell(new Label(15, i + 1, store.getMore()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String lend(VOStore vos, Integer fhid) {
		String msg = "";
		Store store = getStoreById(vos.getId());
		if (vos.getNum() <= store.getCurAmount()) {
			store.setCurAmount(store.getCurAmount() - vos.getNum());
			if (vos.getFormUrl().equals("borrow")) {
				Borrow bor = new Borrow();
				BeanUtils.copyProperties(vos, bor, new String[] { "id" });
				if ("工装".equals(store.getParClass())) {
					WareBangGoogs listb = (WareBangGoogs) totalDao.getObjectByCondition("from WareBangGoogs where fk_store_id = ? and status = '入库'", store.getId());
					if(listb==null){}
					else {
						bor.setCqStatus("待取");
						bor.setWare_id(listb.getFk_ware_id());
						bor.setOa_id(listb.getFk_oadetail_id());
						bor.setWareHouse(store.getPlace());
					}
				}
				bor.setState(NOTGIVEBACK);
				bor.setGiveBackNum(bor.getNum()); // 当前应归还数量
				store.getBorrows().add(bor);
				bor.setStore(store);
				totalDao.save(bor);
				msg = "出借成功!";
			} else if (vos.getFormUrl().equals("consuming")) {
				Consuming con = new Consuming();
				OutLib out = new OutLib();
				BeanUtils.copyProperties(vos, con, new String[] { "id" });
				BeanUtils.copyProperties(vos, out, new String[] { "id", "num" });
				out.setNum(Float.valueOf(vos.getNum()));
				store.getConsumings().add(con);
				con.setStore(store);
				con.setOut(out);
				out.setCon(con);
				store.getOutlibs().add(out);
				out.setStore(store);
				totalDao.save(con);
				// 防护用品领用时间更新
				if (fhid != null && fhid > 0) {
					FanghuOutLib fh = (FanghuOutLib) totalDao.getObjectById(
							FanghuOutLib.class, fhid);
					fh.setLastLingyongTime(Util.getDateTime());
				}

				msg = "领用成功!";
			}
		} else {
			msg = "数量大于当前数量!当前数量为：" + store.getCurAmount();
		}
		return msg;
	}

	@SuppressWarnings("unchecked")
	public VOUser getUserByCardId(String cardId) {
		List<Users> l = totalDao.query("from Users u where u.cardId = ? ",
				cardId);
		if (l != null && l.size() > 0) {
			VOUser u = new VOUser();
			Users users = l.get(0);
			BeanUtils.copyProperties(users, u);
			u.setPicture(users.getPassword().getPicture());
			return u;
		}
		return null;
	}

	@Override
	public void delStoreById(Integer id) {
		Store st = getStoreById(id);
		Iterator<Storage> it = st.getStorages().iterator();
		while (it.hasNext()) {
			Storage sto = it.next();
			Iterator<OaAppDetail> stoIt = sto.getOaDetails().iterator();
			while (stoIt.hasNext()) {
				OaAppDetail oad = stoIt.next();
				oad.setStatus("未入完");
				oad.getStorages().remove(sto);
			}
			sto.setOaDetails(null);
			totalDao.update(sto);
		}
		del(st);
	}

	/**
	 * 外借
	 */
	@Override
	public Object[] queryLoanByCondition(Map map, int pageNo, int pageSize,
			String classification) {
		String hql = "from Store s where s.classify='" + classification
				+ "' and s.curAmount > 0";
		if (map != null) {
			if (map.get("number") != null)
				hql += " and s.number like '%" + map.get("number") + "%'";
			if (map.get("matetag") != null)
				hql += " and s.matetag like '%" + map.get("matetag") + "%'";
			if (map.get("format") != null)
				hql += " and s.format like '%" + map.get("format") + "%'";
		}
		hql += " order by s.startDate desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	/**
	 * 月度统计
	 */
	@Override
	public Object[] monthlyStatistics(Map map, int pageNo, int pageSize) {
		String sql = statisticsSplice(map);
		Session session = totalDao.createSession();
		List l = session.createSQLQuery(sql).setFirstResult(
				(pageNo - 1) * pageSize).setMaxResults(pageSize).list();
		List<StatisticsStore> list = new ArrayList<StatisticsStore>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			StatisticsStore ss = new StatisticsStore();
			ss.setName(obj[0] + "");
			ss.setFormat(obj[1] + "");
			ss.setCategory(obj[2] + "");
			ss.setCurrentNum(Float.parseFloat(obj[3] == null ? "0" : obj[3]
					+ ""));
			ss.setStorageNum(Float.parseFloat(obj[4] == null ? "0" : obj[4]
					+ ""));
			ss.setOutNum(Float.parseFloat(obj[5] == null ? "0" : obj[5] + ""));
			ss.setPrice(Float.parseFloat(obj[6] == null ? "0" : obj[6] + ""));
			list.add(ss);
		}
		String hqlCount = "select count(*) f";
		List coutLis = session.createSQLQuery(hqlCount).list();
		if (list != null && coutLis != null) {
			return new Object[] { list, coutLis.get(0) };
		}
		return null;
	}

	public String statisticsSplice(Map map) {
		String outerSql = "select t.matetag,t.format,t.parClass,t.c3 as '当前',sum(t.c1) as '入库',sum(t.c2) as '出库数',t.c4 as '单价' from ( ";
		String storageSql = " select p.number,p.matetag,p.format,p.parClass,sum(s.categoryNum) as c1,(select sum(o.OutLib_num) from outlib o where o.fk_store_id = p.id) as c2,"
				+ "p.curAmount as c3,s.storage_taxPrice as c4 from store p"
				+ " inner join storage s on s.fk_store_id = p.id";
		if (map != null) {
			if (map.get("startTime") != null && map.get("endTime") != null) {
				storageSql += " where (s.storage_date between '"
						+ map.get("startTime") + "' and '" + map.get("endTime")
						+ "')";
			}
		}
		storageSql += " group by p.number,p.matetag,p.format,p.parClass,p.curAmount,s.storage_taxPrice,p.id) t where 1=1 ";

		String sql = outerSql + storageSql;
		if (map != null) {
			if (map.get("name") != null)
				sql += " and t.matetag like '%" + map.get("name") + "%'";
			if (map.get("parClass") != null)
				sql += " and t.parClass like '%" + map.get("parClass") + "%'";
		}
		sql += " group by t.number,t.matetag,t.format,t.parClass,t.c3,t.c4 ";
		return sql;
	}

	@Override
	public void exportStatisticsExcel(Map map) {
		String sql = statisticsSplice(map);
		List list = totalDao.createSession().createSQLQuery(sql).list();
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("月度数据".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("月度数据", 0);
			ws.setColumnView(0, 24);
			ws.setColumnView(1, 24);
			ws.addCell(new Label(0, 0, "名称"));
			ws.addCell(new Label(1, 0, "规格"));
			ws.addCell(new Label(2, 0, "类别"));
			ws.addCell(new Label(3, 0, "库存量"));
			ws.addCell(new Label(4, 0, "入库量"));
			ws.addCell(new Label(5, 0, "出库量"));
			ws.addCell(new Label(6, 0, "价格"));
			ws.addCell(new Label(7, 0, "上月期初"));

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0] + ""));
				ws.addCell(new Label(1, i + 1, obj[1] + ""));
				ws.addCell(new Label(2, i + 1, obj[2] + ""));
				ws.addCell(new jxl.write.Number(3, i + 1, Float
						.parseFloat(obj[3] == null ? "0" : obj[3] + "")));
				ws.addCell(new jxl.write.Number(4, i + 1, Float
						.parseFloat(obj[4] == null ? "0" : obj[4] + "")));
				ws.addCell(new jxl.write.Number(5, i + 1, Float
						.parseFloat(obj[5] == null ? "0" : obj[5] + "")));
				ws.addCell(new jxl.write.Number(6, i + 1, Float
						.parseFloat(obj[6] == null ? "0" : obj[6] + "")));
				ws
						.addCell(new jxl.write.Number(7, i + 1, Float
								.parseFloat(obj[3] == null ? "0" : obj[3] + "")
								+ Float.parseFloat(obj[5] == null ? "0"
										: obj[5] + "")
								- Float.parseFloat(obj[4] == null ? "0"
										: obj[4] + "")));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 查询防护零用信息
	 * 
	 * @param matetag
	 *            物品名称
	 * @param format
	 *            物品规格
	 * @param userid
	 *            用户id
	 * @return
	 */
	@Override
	public FanghuOutLib findFanghuOutLib(String matetag, String format,
			Integer userid) {
		if (format != null && format.length() > 0 && matetag != null
				&& matetag.length() > 0 && userid != null) {
			String hql = "from  FanghuOutLib where matetag=? and format=? and userId=?";
			return (FanghuOutLib) totalDao.getObjectByCondition(hql, matetag,
					format, userid);
		}
		return null;
	}
}