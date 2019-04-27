package com.task.ServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IScrapService;
import com.task.entity.Borrow;
import com.task.entity.Scrap;
import com.task.entity.Storage;
import com.task.entity.Store;

public class ScrapServiceImpl implements IScrapService {

	private TotalDao totalDao;
	private static final String ALREADYGIVEBACK = "已归还";

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void add(Scrap scr) {
		// TODO Auto-generated method stub
		totalDao.save(scr);
	}

	@Override
	public void del(Scrap scr) {
		totalDao.delete(scr);
	}

	@Override
	public Scrap getScrapById(int id) {
		// TODO Auto-generated method stub
		return (Scrap) totalDao.getObjectById(Scrap.class, id);
	}

	@Override
	public Object[] queryScrap(Map map, int pageNo, int pageSize) {
		String hql = "from Scrap s where 1=1 ";
		if (map != null) {
			if (map.get("number") != null)
				hql += " and s.number like '%" + map.get("number") + "%'";
			if (map.get("standard") != null)
				hql += " and s.format like '%" + map.get("standard") + "%'";
			if (map.get("name") != null)
				hql += " and s.matetag like '%" + map.get("name") + "%'";
			if (map.get("peopleName") != null)
				hql += " and s.username like '%" + map.get("peopleName") + "%'";
		}
		hql += " order by s.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	@Override
	public void exportExcel(Map map) {
		String hql = "from Scrap s where 1=1 ";
		if (map != null) {
			if (map.get("name") != null)
				hql += " and s.matetag like '%" + map.get("name") + "%'";
			if (map.get("peopleName") != null)
				hql += " and s.username like '%" + map.get("peopleName") + "%'";
		}
		hql += " order by s.id desc";
		List list = totalDao.findAllByPage(hql, 1, 16);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("报废".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("报废数据", 0);
			ws.setColumnView(0, 18);
			ws.setColumnView(1, 24);
			ws.setColumnView(2, 24);
			ws.setColumnView(6, 20);
			ws.setColumnView(7, 20);
			ws.setColumnView(8, 20);
			ws.setColumnView(9, 30);
			ws.addCell(new Label(0, 0, "编号"));
			ws.addCell(new Label(1, 0, "名称"));
			ws.addCell(new Label(2, 0, "规格"));
			ws.addCell(new Label(3, 0, "数量"));
			ws.addCell(new Label(4, 0, "责任人"));
			ws.addCell(new Label(5, 0, "部门"));
			ws.addCell(new Label(6, 0, "报废时间"));
			ws.addCell(new Label(7, 0, "责任人意见"));
			ws.addCell(new Label(8, 0, "损坏原因"));
			ws.addCell(new Label(9, 0, "备注"));
			for (int i = 0; i < list.size(); i++) {
				Scrap scr = (Scrap) list.get(i);
				ws.addCell(new Label(0, i + 1, scr.getNumber()));
				ws.addCell(new Label(1, i + 1, scr.getMatetag()));
				ws.addCell(new Label(2, i + 1, scr.getFormat()));
				ws.addCell(new jxl.write.Number(3, i + 1, scr.getAmount()));
				ws.addCell(new Label(4, i + 1, scr.getUsername()));
				ws.addCell(new Label(5, i + 1, scr.getDept()));
				ws.addCell(new Label(6, i + 1, scr.getBadDate().toString()));
				ws.addCell(new Label(7, i + 1, scr.getBadView()));
				ws.addCell(new Label(8, i + 1, scr.getMore1()));
				ws.addCell(new Label(9, i + 1, scr.getMore2()));
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
	 * 还的情况下 报废
	 */
	@Override
	public String alsoScrap(Scrap scr) {
		String msg = "";
		if (scr.getAmount() == 0) {
			msg = "报废失败!数量不能等于0";
			return msg;
		}
		Borrow bo = (Borrow) totalDao.getObjectById(Borrow.class, scr.getId());
		if (bo != null) {
			if (bo.getGiveBackNum() >= scr.getAmount()) { // 当前应归还数量大于归还数量
				Store st = bo.getStore();
				Scrap newSc = new Scrap();
				BeanUtils.copyProperties(scr, newSc, new String[] { "id" });
				if (bo.getGiveBackNum() - scr.getAmount() == 0) {
					bo.setState(ALREADYGIVEBACK);
				}
				bo.setGiveBackNum(bo.getGiveBackNum() - scr.getAmount());

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
					newStore.setTotal(0F);
					bo.setStore(newStore);
					st = newStore;

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
					st.setTotal(st.getTotal() - scr.getAmount());
				} catch (Exception e) {
					e.printStackTrace();
				}
				bo.getScraps().add(newSc);
				newSc.setBorrow(bo);
				st.getScraps().add(newSc);
				newSc.setStore(st);
				if (st.getId() != null) {
					totalDao.update(st);
				} else {
					totalDao.save(st);
				}
				totalDao.save(newSc);
			} else {
				msg = "报废失败!数量大于借的数量!";
			}
		} else {
			msg = "报废失败!数量大于借的数量!";
		}
		return msg;
	}

	@Override
	public String add(Scrap scr, Integer id) {
		Store store = getStoreById(id);
		String msg = "";
		if (store.getCurAmount() >= scr.getAmount()) {
			store.setCurAmount(store.getCurAmount() - scr.getAmount());
			store.setTotal(store.getTotal() - scr.getAmount());
			scr.setStore(store);
			store.getScraps().add(scr);
			totalDao.save(scr);
			totalDao.update(store);
		} else {
			msg = "报废失败!当前可报废数量为: " + store.getCurAmount();
		}
		return msg;
	}

	@Override
	public String update(Scrap scr) {
		String msg = "";
		Scrap oldScr = getScrapById(scr.getId());
		if (oldScr.getState() == 0) {
			if (scr.getAmount() != oldScr.getAmount()) {
				return msg = "该记录不能修改!该记录是出借记录,数量不能修改!";
			}
		}
		Store sto = oldScr.getStore();
		float amount = sto.getCurAmount() + oldScr.getAmount();
		float total = sto.getTotal() + oldScr.getAmount();
		if (amount >= scr.getAmount()) {
			sto.setCurAmount(amount - scr.getAmount());
			sto.setTotal(total - scr.getAmount());
			BeanUtils.copyProperties(scr, oldScr, new String[] { "id", "store",
					"state" });
			msg = "修改成功!";
			totalDao.update(oldScr);
		} else {
			msg = "修改失败!修改数量大于当前库存数量!当前库存量为:" + sto.getCurAmount();
		}
		return msg;
	}

	@Override
	public Store getStoreById(Integer id) {
		return (Store) totalDao.getObjectById(Store.class, id);
	}

	@Override
	public void delScrapById(Integer id) {
		Scrap sc = getScrapById(id);
		Store sto = sc.getStore();
		if (sc.getState() == 0) {
			sto.setTotal(sto.getTotal() + sc.getAmount());
			Borrow bo = sc.getBorrow();
			bo.setGiveBackNum(bo.getGiveBackNum() + sc.getAmount());
			bo.setState("未归还");
			bo.getScraps().remove(sc);
			sc.setBorrow(null);
			totalDao.update(bo);
		} else {
			sto.setTotal(sto.getTotal() + sc.getAmount());
			sto.setCurAmount(sto.getCurAmount() + sc.getAmount());
		}
		sto.getMaintains().remove(sc);
		sc.setStore(null);
		del(sc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void piliangbaofei() {
		// TODO Auto-generated method stub
		List<Store> list = totalDao.query("from Store where more = '报废'");
		System.out.println(list.size());
		StringBuffer buffer = new StringBuffer();
		for (Store store : list) {
			buffer.append(""+store.getId()+",");
//			try {
//				Scrap scr = new Scrap();
//				scr.setBadDate(new Date());
//				scr.setMore1("报废");
//				scr.setState(1);
//				scr.setNumber(store.getNumber());
//				scr.setMatetag(store.getMatetag());
//				scr.setFormat(store.getFormat());
//				scr.setUsername("管理员");
//				scr.setBadView("同意");
//				scr.setAmount(store.getCurAmount().intValue());
//				store.setCurAmount(store.getCurAmount() - scr.getAmount());
//				store.setTotal(store.getTotal() - scr.getAmount());
//				scr.setStore(store);
//				store.getScraps().add(scr);
//				totalDao.save2(scr);
//				totalDao.update2(store);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				System.out.println(store.getId()+"))))))))))))))))))))))))");
//				e.printStackTrace();
//			}
		}
		System.out.println("ss:"+buffer.toString());
	}

}
