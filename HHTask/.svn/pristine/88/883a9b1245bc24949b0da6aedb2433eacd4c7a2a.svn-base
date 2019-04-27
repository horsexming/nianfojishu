package com.task.ServerImpl;

import java.io.File;
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
import com.task.Server.IBorrowService;
import com.task.entity.Also;
import com.task.entity.Borrow;
import com.task.entity.Store;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class BorrowServiceImpl implements IBorrowService {

	private TotalDao totalDao;

	@Override
	public void add(Borrow bor) {
		// TODO Auto-generated method sreturn null;tub
		totalDao.save(bor);
	}

	@Override
	public void del(Borrow bor) {
		// TODO Auto-generated method stub
		Store st = bor.getStore();
		if (bor.getState().equals("未归还")) {
			st.setCurAmount(bor.getGiveBackNum() + st.getCurAmount());
		}
		st.getBorrows().remove(bor);
		bor.setStore(null);
		totalDao.delete(bor);
		totalDao.update(st);
	}

	@Override
	public Borrow getBorrowById(int id) {
		// TODO Auto-generated method stub
		return (Borrow) totalDao.getObjectById(Borrow.class, id);
	}

	@Override
	public boolean update(Borrow bor) {
		// TODO Auto-generated method stub
		return totalDao.update(bor);
	}

	@Override
	public Object[] queryBorrow(Map map, int pageNo, int pageSize) {
		String hql = "from Borrow b where 1=1 ";
		if (map != null) {
			if (map.get("dept") != null)
				hql += " and b.dept like '%" + map.get("dept") + "%'";
			if (map.get("cardId") != null)
				hql += " and b.cardNum like '%" + map.get("cardId") + "%'";
			if (map.get("person") != null)
				hql += " and b.peopleName like '%" + map.get("person") + "%'";
			if (map.get("name") != null)
				hql += " and b.matetag like '%" + map.get("name") + "%'";
			if (map.get("standard") != null)
				hql += " and b.format like '%" + map.get("standard") + "%'";
			if (map.get("number") != null)
				hql += " and b.number like '%" + map.get("number") + "%'";
			if (map.get("pieceNum") != null)
				hql += " and b.processPieceNum like '%" + map.get("pieceNum")
						+ "%'";
			if (map.get("storehouse") != null)
				hql += " and b.storehouse like '%" + map.get("storehouse")
						+ "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (b.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += " order by b.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	@Override
	public void exportExcel(Map map) {
		String hql = "from Borrow b where 1=1 ";
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
					+ new String("出借".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("出借数据", 0);
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
			ws.addCell(new Label(10, 0, "出借时间"));
			for (int i = 0; i < list.size(); i++) {
				Borrow bor = (Borrow) list.get(i);
				ws.addCell(new Label(0, i + 1, bor.getCardNum()));
				ws.addCell(new Label(1, i + 1, bor.getPeopleName()));
				ws.addCell(new Label(2, i + 1, bor.getDept()));
				ws.addCell(new Label(3, i + 1, bor.getNumber()));
				ws.addCell(new Label(4, i + 1, bor.getMatetag()));
				ws.addCell(new Label(5, i + 1, bor.getFormat()));
				ws.addCell(new Label(6, i + 1, bor.getProcessPieceNum()));
				ws.addCell(new Label(7, i + 1, bor.getUnit()));
				ws.addCell(new jxl.write.Number(8, i + 1, bor.getNum()));
				ws.addCell(new Label(9, i + 1, bor.getStorehouse()));
				ws.addCell(new Label(10, i + 1, bor.getDate() != null ? bor
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

	public List<Borrow> queryBrrowByCardNum(String cardNum, String state) {
		String hql = "from Borrow b where b.cardNum = ? and b.state= ?";
		return totalDao.query(hql, cardNum, state);
	}

	/**
	 * 查询所有还没归还完的记录
	 */
	@Override
	public Object[] queryStatistics(Map map, int pageNo, int pageSize) {
		String hql = "from Borrow b where b.state = '未归还' and giveBackNum != 0";
		if (map != null) {
			if (map.get("person") != null)
				hql += " and b.peopleName like '%" + map.get("person") + "%'";
			if (map.get("name") != null)
				hql += " and b.matetag like '%" + map.get("name") + "%'";
			if (map.get("dept") != null)
				hql += " and b.dept like '%" + map.get("dept") + "%'";
			if (map.get("format") != null)
				hql += " and b.format like '%" + map.get("format") + "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (b.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += "  order by date desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void delBorrowById(Integer id) {
		// TODO Auto-generated method stub
		Borrow bor = getBorrowById(id);
		if (bor != null) {
			Store st = bor.getStore();
			if (bor.getState().equals("未归还")) {
				st.setCurAmount(bor.getGiveBackNum() + st.getCurAmount());
			}
			st.getBorrows().remove(bor);
			bor.setStore(null);
			List<Also> l = totalDao.query("from Also a where a.borrow.id = ?",
					bor.getId());
			for (Also a : l) {
				a.setStore(null);
			}
			st.setAlsos(null);
			totalDao.update(st);
			totalDao.delete(bor);
		}
	}

	@Override
	public String exportExcel(Map map,String startTime, String endTime) {
		try {
			String fileName = "" + Util.getDateTime("yyyyMMddhhmmss") + ".xls";
			String excelRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/sheet")
					+ "/sop/" + fileName;
			File target2 = new File(excelRealPath);// 创建文件流获得EXCEL的路径
			// // 生成excel表
			WritableWorkbook book = Workbook.createWorkbook(target2);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("在途统计汇总 ", 0);
			Label label = new Label(0, 0, "借用人");
			Label label1 = new Label(1, 0, "部门");
			Label label2 = new Label(2, 0, "物品编号");
			Label label3 = new Label(3, 0, "物品名称");
			Label label4 = new Label(4, 0, "规格");
			Label label5 = new Label(5, 0, "单位");
			Label label6 = new Label(6, 0, "应归还量");
			Label label7 = new Label(7, 0, "借物时间");
			Label label8 = new Label(8, 0, "状态");
			sheet.setColumnView(2, 15);// 设置第2行的宽度
			sheet.setColumnView(3, 20);// 设置第3行的宽度
			sheet.setColumnView(4, 15);// 设置第4行的宽度
			sheet.setColumnView(7, 20);// 设置第7行的宽度
			sheet.addCell(label);
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			sheet.addCell(label7);
			sheet.addCell(label8);
			List excellist = new ArrayList();
			/*if (startTime == null && endTime == null) {
				String hql = "from Borrow ";
				excellist = totalDao.query(hql);
			} else {*/
				
				//String hql = "from Borrow  where date>=? and date<=? and state = '未归还'  order by date  ";
				
				//String hql = "from Borrow  where date between ? and ? and state = '未归还'  order by date desc ";
				//excellist = totalDao.query(hql, Util.StringToDate(
				//		startTime, "yyyy-MM-dd"), Util.StringToDate(endTime,
				//		"yyyy-MM-dd"));
				String hql = "from Borrow b where b.state = '未归还' and giveBackNum != 0";
				if (map != null) {
					if (map.get("person") != null && !"".equals(map.get("person")))
						hql += " and b.peopleName like '%" + map.get("person") + "%'";
					if (map.get("name") != null && !"".equals(map.get("name")))
						hql += " and b.matetag like '%" + map.get("name") + "%'";
					if (map.get("dept") != null && !"".equals(map.get("dept")))
						hql += " and b.dept like '%" + map.get("dept") + "%'";
					if (map.get("format") != null && !"".equals(map.get(map.get("format"))))
						hql += " and b.format like '%" + map.get("format") + "%'";
					if (map.get("startTime") != null && map.get("endTime") != null && !"".equals(map.get("startTime")) && !"".equals(map.get(map.get("endTime"))))
						hql += " and (b.date between '" + map.get("startTime")
								+ "' and '" + map.get("endTime") + "')";
				//}
				hql += "  order by date desc";
				excellist = totalDao.query(hql);
			}
			if (excellist != null && excellist.size() > 0) {
				for (int i = 0; i < excellist.size(); i++) {
					Borrow borrow = (Borrow) excellist.get(i);
					Label label10 = new Label(0, i + 1, borrow.getPeopleName());
					Label label11 = new Label(1, i + 1, borrow.getDept());
					Label label12 = new Label(2, i + 1, borrow.getNumber());
					Label label13 = new Label(3, i + 1, borrow.getMatetag());
					Label label14 = new Label(4, i + 1, borrow.getFormat());
					Label label15 = new Label(5, i + 1, borrow.getUnit());
					Label label16 = new Label(6, i + 1, borrow.getGiveBackNum()
							+ "");
					Label label17 = new Label(7, i + 1, Util.DateToString(
							borrow.getDate(), "yyyy-MM-dd HH:mm:ss"));
					Label label18 = new Label(8, i + 1, borrow.getState());
					sheet.addCell(label10);
					sheet.addCell(label11);
					sheet.addCell(label12);
					sheet.addCell(label13);
					sheet.addCell(label14);
					sheet.addCell(label15);
					sheet.addCell(label16);
					sheet.addCell(label17);
					sheet.addCell(label18);
				}
			}

			book.write();// 写入数据
			book.close();// 关闭文件
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "no";

	}

	@Override
	public Object[] showCodeBorrow(Borrow bo, Integer cpage,Integer pageSize,String tag) {
		// TODO Auto-generated method stub
		if (bo == null) bo = new Borrow();
		String hql = totalDao.criteriaQueries(bo, null);
		if ("code".equals(tag)) hql += " and cardNum = '"+Util.getLoginUser().getCardId()+"'";
		hql += " and cqStatus in ('待取','取中') order by id desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

}
