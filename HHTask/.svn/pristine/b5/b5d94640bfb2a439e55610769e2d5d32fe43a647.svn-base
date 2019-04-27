package com.task.ServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IConsumingService;
import com.task.entity.Consuming;
import com.task.entity.StorageHistory;
import com.task.entity.Store;
import com.task.entity.VOConsuming;

public class ConsumingServiceImpl implements IConsumingService {

	private TotalDao totalDao;

	@Override
	public void add(Consuming con) {
		// TODO Auto-generated method stub
		totalDao.save(con);
	}

	@Override
	public Consuming getConsumingById(int id) {
		// TODO Auto-generated method stub
		return (Consuming) totalDao.getObjectById(Consuming.class, id);
	}
	/**
	 * 根据storId 和卡号查询最后一次领取记录
	 * @param storeId
	 * @param cardNum
	 * @return
	 */
	public Consuming getConsumingByStoreId(Integer storeId,String  cardNum){
		String hql = "from Consuming c where 1=1 and c.store.id=? and c.cardNum=? order by c.date desc";
		return (Consuming)this.totalDao.getObjectByCondition(hql, new Object[]{storeId, cardNum});
	}
	@Override
	public Object[] queryConsuming(Map map, int pageNo, int pageSize) {
		String hql = "from Consuming c where 1=1 ";
		if (map != null) {
			if (map.get("dept") != null && !"".equals(map.get("dept")))
				hql += " and c.dept like '%" + map.get("dept") + "%'";
			if (map.get("cardId") != null && !"".equals(map.get("cardId")))
				hql += " and c.cardNum like '%" + map.get("cardId") + "%'";
			if (map.get("person") != null && !"".equals(map.get("person")))
				hql += " and c.peopleName like '%" + map.get("person") + "%'";
			if (map.get("matetag") != null && !"".equals(map.get("matetag")))
				hql += " and c.matetag like '%" + map.get("matetag") + "%'";
			if (map.get("format") != null && !"".equals(map.get("format")))
				// System.out.println("========"+map.get("format"));
				hql += " and c.format like '%" + map.get("format") + "%'";
			if (map.get("number") != null && !"".equals(map.get("number")))
				hql += " and c.number like '%" + map.get("number") + "%'";
			if (map.get("pieceNum") != null && !"".equals(map.get("pieceNum")))
				hql += " and c.processPieceNum like '%" + map.get("pieceNum")
						+ "%'";
			if (map.get("storehouse") != null
					&& !"".equals(map.get("storehouse")))
				hql += " and c.storehouse like '%" + map.get("storehouse")
						+ "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (c.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += " order by c.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	@Override
	public void exportExcel(Map map) {
		String hql = "from Consuming b where 1=1 ";
		if (map != null) {
			if (map.get("dept") != null)
				hql += " and b.dept like '%" + map.get("dept") + "%'";
			if (map.get("cardId") != null)
				hql += " and b.cardNum like '%" + map.get("cardId") + "%'";
			if (map.get("person") != null)
				hql += " and b.peopleName like '%" + map.get("person") + "%'";
			if (map.get("number") != null)
				hql += " and b.number like '%" + map.get("number") + "%'";
			if (map.get("storehouse") != null)
				hql += " and b.storehouse like '%" + map.get("storehouse")
						+ "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (b.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += " order by b.id desc";
		List list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("领用".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("领用数据", 0);
			ws.setColumnView(0, 18);
			ws.setColumnView(1, 14);
			ws.setColumnView(2, 16);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			ws.setColumnView(10, 18);
			ws.addCell(new Label(0, 0, "卡号"));
			ws.addCell(new Label(1, 0, "领用人"));
			ws.addCell(new Label(2, 0, "部门"));
			ws.addCell(new Label(3, 0, "编号"));
			ws.addCell(new Label(4, 0, "名称"));
			ws.addCell(new Label(5, 0, "规格"));
			ws.addCell(new Label(6, 0, "单位"));
			ws.addCell(new Label(7, 0, "数量"));
			ws.addCell(new Label(8, 0, "仓库"));
			ws.addCell(new Label(9, 0, "类别"));
			ws.addCell(new Label(10, 0, "出借时间"));
			for (int i = 0; i < list.size(); i++) {
				Consuming con = (Consuming) list.get(i);
				ws.addCell(new Label(0, i + 1, con.getCardNum()));
				ws.addCell(new Label(1, i + 1, con.getPeopleName()));
				ws.addCell(new Label(2, i + 1, con.getDept()));
				ws.addCell(new Label(3, i + 1, con.getNumber()));
				ws.addCell(new Label(4, i + 1, con.getMatetag()));
				ws.addCell(new Label(5, i + 1, con.getFormat()));
				ws.addCell(new Label(6, i + 1, con.getUnit()));
				ws.addCell(new jxl.write.Number(7, i + 1, con.getNum()));
				ws.addCell(new Label(8, i + 1, con.getStorehouse()));
				ws.addCell(new Label(9, i + 1, con.getStore().getParClass()));
				ws.addCell(new Label(10, i + 1, con.getDate() != null ? con
						.getDate().toString() : ""));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void delConsumingById(Integer id) {
		Consuming con = getConsumingById(id);
		
		if (con != null) {
			Store st = con.getStore();
			st.setCurAmount(st.getCurAmount() + con.getNum());
			st.getOutlibs().remove(con.getOut());
			con.getOut().setStore(null);
			st.getConsumings().remove(con);
			con.setStore(null);
			totalDao.update(st);
			totalDao.delete(con);
		}
	}

	/*
	 * 
	 * 批量打印(non-Javadoc)
	 * @see com.task.Server.IConsumingService#printStorage(int[])
	 */
	@Override
	public List printStorage(int[] selected) {
		List<Consuming> l = new ArrayList<Consuming>();
		for (int id : selected) {
			Consuming sh = (Consuming) totalDao.getObjectById(Consuming.class, id);
			l.add(sh);
		}
		return l;
	}

}
