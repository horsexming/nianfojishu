package com.task.ServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IAlsoService;
import com.task.entity.Also;
import com.task.entity.Borrow;
import com.task.entity.Storage;
import com.task.entity.Store;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class AlsoServiceImpl implements IAlsoService {

	private TotalDao totalDao;
	private static final String ALREADYGIVEBACK = "已归还";

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void add(Also al) {
		// TODO Auto-generated method stub
		totalDao.save(al);
	}

	@Override
	public void del(Also al) {
		// TODO Auto-generated method stub
		Store st = al.getStore();
		Borrow bo = al.getBorrow();
		bo.setGiveBackNum(bo.getGiveBackNum() + al.getProcessQuantity());
		st.setCurAmount(st.getCurAmount() - al.getProcessQuantity());
		bo.setState("未归还");
		bo.getAlsos().remove(al);
		al.setStore(null);
		al.setBorrow(null);
		totalDao.update(bo);
		totalDao.delete(al);
	}

	@Override
	public Also getAlsoById(int id) {
		// TODO Auto-generated method stub
		return (Also) totalDao.getObjectById(Also.class, id);
	}

	@Override
	public boolean update(Also al) {
		// TODO Auto-generated method stub
		return totalDao.update(al);
	}

	@Override
	public Object[] queryAlso(Map map, int pageNo, int pageSize) {
		String hql = "from Also a where 1=1 ";
		if (map != null) {
			if (map.get("dept") != null)
				hql += " and a.dept like '%" + map.get("dept") + "%'";
			if (map.get("cardId") != null)
				hql += " and a.cardNum like '%" + map.get("cardId") + "%'";
			if (map.get("person") != null)
				hql += " and a.peopleName like '%" + map.get("person") + "%'";
			if (map.get("name") != null)
				hql += " and a.name like '%" + map.get("name") + "%'";
			if (map.get("standard") != null)
				hql += " and a.format like '%" + map.get("standard") + "%'";
			if (map.get("number") != null)
				hql += " and a.number like '%" + map.get("number") + "%'";
			if (map.get("pieceNum") != null)
				hql += " and a.processPieceNum like '%" + map.get("pieceNum")
						+ "%'";
			if (map.get("storehouse") != null)
				hql += " and a.storehouse like '%" + map.get("storehouse")
						+ "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (a.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += " order by a.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	@Override
	public void exportExcel(Map map) {
		String hql = "from Also a where 1=1 ";
		if (map != null) {
			if (map.get("dept") != null)
				hql += " and a.dept like '%" + map.get("dept") + "%'";
			if (map.get("cardId") != null)
				hql += " and a.cardNum like '%" + map.get("cardId") + "%'";
			if (map.get("person") != null)
				hql += " and a.peopleName like '%" + map.get("person") + "%'";
			if (map.get("number") != null)
				hql += " and a.number like '%" + map.get("number") + "%'";
			if (map.get("storehouse") != null)
				hql += " and a.storehouse like '%" + map.get("storehouse")
						+ "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (a.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += " order by a.id desc";
		List list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("归还".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("归还数据", 0);
			ws.setColumnView(0, 18);
			ws.setColumnView(1, 14);
			ws.setColumnView(2, 16);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			ws.setColumnView(10, 18);
			ws.addCell(new Label(0, 0, "卡号"));
			ws.addCell(new Label(1, 0, "借主"));
			ws.addCell(new Label(2, 0, "部门"));
			ws.addCell(new Label(3, 0, "编号"));
			ws.addCell(new Label(4, 0, "名称"));
			ws.addCell(new Label(5, 0, "规格"));
			ws.addCell(new Label(6, 0, "加工件号"));
			ws.addCell(new Label(7, 0, "单位"));
			ws.addCell(new Label(8, 0, "数量"));
			ws.addCell(new Label(9, 0, "仓库"));
			ws.addCell(new Label(10, 0, "归还时间"));
			ws.addCell(new Label(11, 0, "加工数量"));
			for (int i = 0; i < list.size(); i++) {
				Also al = (Also) list.get(i);
				ws.addCell(new Label(0, i + 1, al.getCardNum()));
				ws.addCell(new Label(1, i + 1, al.getPeopleName()));
				ws.addCell(new Label(2, i + 1, al.getDept()));
				ws.addCell(new Label(3, i + 1, al.getNumber()));
				ws.addCell(new Label(4, i + 1, al.getName()));
				ws.addCell(new Label(5, i + 1, al.getFormat()));
				ws.addCell(new Label(6, i + 1, al.getProcessPieceNum()));
				ws.addCell(new Label(7, i + 1, al.getUnit()));
				ws.addCell(new jxl.write.Number(8, i + 1, al.getNum()));
				ws.addCell(new Label(9, i + 1, al.getStorehouse()));
				ws.addCell(new Label(10, i + 1, al.getDate() != null ? al
						.getDate().toString() : ""));
				ws.addCell(new jxl.write.Number(11, i + 1, al
						.getProcessQuantity() != null ? al.getProcessQuantity()
						: 0));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 归还物品
	 */
	public String alsoGoods(Also al) {
		String msg = "";
		if (al.getProcessQuantity() == 0) {
			msg = "归还失败!数量不能等于0";
			return msg;
		}
		Borrow bo = (Borrow) totalDao.getObjectById(Borrow.class, al.getId());
		if (bo != null) {
			if (bo.getGiveBackNum() >= al.getProcessQuantity()) { // 当前应归还数量大于归还数量
				Also newAl = new Also();
				BeanUtils.copyProperties(al, newAl, new String[] { "id" });
				if (bo.getGiveBackNum() - al.getProcessQuantity() == 0) {
					bo.setState(ALREADYGIVEBACK);
				}
				bo.setGiveBackNum(bo.getGiveBackNum() - al.getProcessQuantity());
				if (bo.getStore() == null) {
					Store newStore = new Store();
					newStore.setAppDept(bo.getDept());
					newStore.setStartDate(bo.getDate());
					newStore.setMatetag(bo.getMatetag());
					newStore.setFormat(bo.getFormat());
					newStore.setStorehouse(bo.getStorehouse());
					newStore.setNumber(bo.getNumber());
					newStore.setUnit(bo.getUnit());
					newStore.setMix(bo.getMix());
					newStore.setClassify("可借用");
					newStore.setCurAmount(0F);
					bo.setStore(newStore);

					// 按照正常流程，需要将该不正常流程加入入库表的正常流程中。。。。。。。。。。。。。。。
					Storage storage = new Storage();
					storage.setDept(bo.getDept());
					storage.setDate(bo.getDate());
					storage.setMatetag(bo.getMatetag());
					storage.setFormat(bo.getFormat());
					storage.setStorehouse(bo.getStorehouse());
					storage.setNumber(bo.getNumber());
					storage.setUnit(bo.getUnit());
					storage.setMix(bo.getMix());
					storage.setClassify("可借用");
					storage.setNum(0F);
					totalDao.save(storage);
				}
				try {
					bo.getStore().setCurAmount(
							bo.getStore().getCurAmount() + al.getProcessQuantity());
				} catch (Exception e) {
					e.printStackTrace();
				}
				newAl.setCqStatus("待存");
				newAl.setBorrow(bo);
				bo.getAlsos().add(newAl);
				newAl.setStore(bo.getStore());
				bo.getStore().getAlsos().add(newAl);
				totalDao.save(newAl);
			} else {
				msg = "归还失败!数量大于借的数量!";
			}
		} else {
			msg = "归还失败!数量大于借的数量!";
		}
		return msg;
	}

	@Override
	public Object[] showCodeAlso(Also al, int parseInt, int pageSize, String tag) {
		// TODO Auto-generated method stub
		if (al == null) al = new Also();
		String hql = totalDao.criteriaQueries(al, null);
		if ("code".equals(tag)) hql += " and cardNum = '"+Util.getLoginUser().getCardId()+"'";
		hql += " and cqStatus in ('待存','存中') order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

}
