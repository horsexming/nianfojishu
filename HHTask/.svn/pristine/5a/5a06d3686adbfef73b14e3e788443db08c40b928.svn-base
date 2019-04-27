package com.task.ServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IOutLibService;
import com.task.entity.Consuming;
import com.task.entity.FanghuOutLib;
import com.task.entity.OutLib;
import com.task.entity.Store;
import com.task.entity.Users;
import com.task.entity.sop.fhyp.FanghuYongpin;
import com.task.util.Util;

public class OutLibServiceImpl implements IOutLibService {

	private TotalDao totalDao;

	@Override
	public void add(OutLib ou) {
		// TODO Auto-generated method stub
		totalDao.save(ou);
	}

	@Override
	public void del(OutLib ou) {
		// TODO Auto-generated method stub
		totalDao.delete(ou);
	}

	@Override
	public OutLib getOutLibById(int id) {
		// TODO Auto-generated method stub
		return (OutLib) totalDao.getObjectById(OutLib.class, id);
	}

	/**
	 * 根据员工卡号获取领取信息
	 * 
	 * @param cardId
	 * @return
	 */
	@Override
	public Object[] scanCardLingyong(String cardId) {
		String hqluser = "from Users where cardId=?";
		Users user = (Users) totalDao.getObjectByCondition(hqluser, cardId);
		if (user!=null) {
			String hqlFH = " from FanghuOutLib where cardNum=?";
			List<FanghuOutLib> listFH = totalDao.query(hqlFH, cardId);
			List listStore = new ArrayList();
			for (FanghuOutLib fo : listFH) {
				String matename = fo.getMatetag();
				String format = fo.getFormat();
				String lastlingyong = fo.getLastLingyongTime();// "yyyy-MM-dd yyyy"
				int lingyongCircle = fo.getLingyongCircle();// 零用周期
				boolean tag = false;
				if (null != lastlingyong && !"".equals(lastlingyong)) {
					// 当前时间减去上次时间大于零用周期，可以为真
					String curTime = Util.getDateTime();
					Calendar nowDate = Calendar.getInstance(), oldDate = Calendar
					.getInstance();
					nowDate.setTime(Util.StringToDate(curTime,
					"yyyy-MM-dd HH:mm:ss"));// 当前时间
					oldDate.setTime(Util.StringToDate(lastlingyong,
					"yyyy-MM-dd HH:mm:ss"));// 设置为开始时间
					long timeNow = nowDate.getTimeInMillis();
					long timeOld = oldDate.getTimeInMillis();
					float timess = timeNow - timeOld;
					float allYear = timess / (1000 * 60 * 60 * 24);// 化为年
					if (allYear > lingyongCircle*365) {
						tag = true;
					}
				} else {
					tag = true;
				}
				if (tag) {// 为真
					String hql = " from Store where matetag=? and format=? and curAmount>0";
					List lic = totalDao.query(hql, matename, format);
					if (lic.size() > 0) {
						listStore.addAll(lic);
					}
				}
			}
			return new Object[] { listStore, user.getId() };
		}
		return new Object[] { null, 0 };
	}

	@Override
	public Object[] queryOutLib(Map map, int pageNo, int pageSize) {
		String hql = "from OutLib o where 1=1 ";
		if (map != null) {
			if (map.get("person") != null)
				hql += " and o.peopleName like '%" + map.get("person") + "%'";
			if (map.get("name") != null)
				hql += " and o.matetag like '%" + map.get("name") + "%'";
			if (map.get("standard") != null)
				hql += " and o.format like '%" + map.get("standard") + "%'";
			if (map.get("storehouse") != null)
				hql += " and o.storehouse like '%" + map.get("storehouse")
						+ "%'";
			if (map.get("category") != null)
				hql += " and o.parClass like '%" + map.get("category") + "%'";
			if (map.get("place") != null)
				hql += " and o.place like '%" + map.get("place") + "%'";
			if (map.get("number") != null)
				hql += " and o.danjuhao like '%" + map.get("number") + "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (o.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += " order by o.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	@Override
	public void exportExcel(Map map) {
		String hql = "from OutLib o where 1=1 ";
		if (map != null) {
			if (map.get("person") != null)
				hql += " and o.person like '%" + map.get("person") + "%'";
			if (map.get("storehouse") != null)
				hql += " and o.storehouse like '%" + map.get("storehouse")
						+ "%'";
			if (map.get("category") != null)
				hql += " and o.parClass like '%" + map.get("category") + "%'";
			if (map.get("place") != null)
				hql += " and o.place like '%" + map.get("place") + "%'";
			if (map.get("number") != null)
				hql += " and o.danjuhao like '%" + map.get("number") + "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (o.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += " order by o.id desc";
		List list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("出库".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("出库数据", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(4, 24);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 12);
			ws.setColumnView(13, 16);
			ws.setColumnView(13, 20);
			ws.addCell(new Label(0, 0, "日期"));
			ws.addCell(new Label(1, 0, "卡号"));
			ws.addCell(new Label(2, 0, "部门"));
			ws.addCell(new Label(3, 0, "使用人"));
			ws.addCell(new Label(4, 0, "品名"));
			ws.addCell(new Label(5, 0, "规格"));
			ws.addCell(new Label(6, 0, "单位"));
			ws.addCell(new Label(7, 0, "仓库"));
			ws.addCell(new Label(8, 0, "类别"));
			ws.addCell(new Label(9, 0, "库位"));
			ws.addCell(new Label(10, 0, "数量"));
			ws.addCell(new Label(11, 0, "价格"));
			ws.addCell(new Label(12, 0, "金额"));
			ws.addCell(new Label(13, 0, "单据号"));
			ws.addCell(new Label(14, 0, "备注"));
			for (int i = 0; i < list.size(); i++) {
				OutLib ou = (OutLib) list.get(i);
				ws.addCell(new Label(0, i + 1, ou.getDate().toString()));
				ws.addCell(new Label(1, i + 1, ou.getCardNum()));
				ws.addCell(new Label(2, i + 1, ou.getDept()));
				ws.addCell(new Label(3, i + 1, ou.getPeopleName()));
				ws.addCell(new Label(4, i + 1, ou.getMatetag()));
				ws.addCell(new Label(5, i + 1, ou.getFormat()));
				ws.addCell(new Label(6, i + 1, ou.getUnit()));
				ws.addCell(new Label(7, i + 1, ou.getStorehouse()));
				ws.addCell(new Label(8, i + 1, ou.getParClass()));
				ws.addCell(new Label(9, i + 1, ou.getPlace()));
				ws.addCell(new jxl.write.Number(10, i + 1, ou.getNum()));
				ws.addCell(new jxl.write.Number(11, i + 1, ou.getPrice()));
				ws.addCell(new jxl.write.Number(12, i + 1, ou.getMoney()));
				ws.addCell(new Label(13, i + 1, ou.getDanjuhao()));
				ws.addCell(new Label(14, i + 1, ou.getMore()));
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
	public boolean update(OutLib ou) {
		// TODO Auto-generated method stub
		return totalDao.update(ou);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void delOutLibById(Integer id) {
		OutLib ol = getOutLibById(id);
		Store st = ol.getStore();
		Consuming con = ol.getCon();
		st.setCurAmount(ol.getNum() + st.getCurAmount());
		st.getOutlibs().remove(ol);
		ol.setStore(null);
		st.getConsumings().remove(ol.getCon());
		ol.getCon().setStore(null);
		totalDao.update(st);
		del(ol);
	}

	/**
	 * 添加劳防用品
	 * 
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuOutLib addFanghuOutLib(FanghuOutLib fanghuOutLib) {
		fanghuOutLib.setNextLingyongcount(1);
		boolean result = totalDao.save(fanghuOutLib);
		if (result) {
			return fanghuOutLib;
		} else {
			return null;
		}
	}

	/**
	 * 删除劳防用品
	 * 
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuOutLib deleteFanghuOutLib(FanghuOutLib fanghuOutLib) {
		boolean result = totalDao.delete(fanghuOutLib);
		if (result) {
			return fanghuOutLib;
		} else {
			return null;
		}
	}

	/**
	 * 更新劳防用品
	 * 
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuOutLib updateFanghuOutLib(FanghuOutLib fanghuOutLib) {
		FanghuOutLib fanghuOutLibTemp = this.getFanghuOutLibById(fanghuOutLib
				.getId());
		if (fanghuOutLibTemp != null) {
			Date date = fanghuOutLib.getDate();// 日期
			String userName = fanghuOutLib.getUserName();// 领用人姓名
			String dept = fanghuOutLib.getDept(); // 部门
			String cardNum = fanghuOutLib.getCardNum(); // 领用卡号

			String matetag = fanghuOutLib.getMatetag();// 物品名称
			String format = fanghuOutLib.getFormat();// 规格尺寸
			String unit = fanghuOutLib.getUnit();// 单位
			int limitCount = fanghuOutLib.getLimitCount();// 领用限量
			int lingyongCircle = fanghuOutLib.getLingyongCircle();// 零用周期
			String lastLingyongTime = fanghuOutLib.getLastLingyongTime();// 上次领用时间
			int userId;// userID
			if (date != null) {
				fanghuOutLibTemp.setDate(date);
			}
			if (userName != null && !"".equals(userName)) {
				fanghuOutLibTemp.setUserName(userName);
			}
			if (dept != null && !"".equals(dept)) {
				fanghuOutLibTemp.setDept(dept);
			}
			if (cardNum != null && !"".equals(cardNum)) {
				fanghuOutLibTemp.setCardNum(cardNum);
			}
			if (matetag != null && !"".equals(matetag)) {
				fanghuOutLibTemp.setMatetag(matetag);
			}
			if (format != null && !"".equals(format)) {
				fanghuOutLibTemp.setFormat(format);
			}
			if (unit != null && !"".equals(unit)) {
				fanghuOutLibTemp.setUnit(unit);
			}
			if (limitCount != 0) {
				fanghuOutLibTemp.setLimitCount(limitCount);
			}
			if (lingyongCircle != 0) {
				fanghuOutLibTemp.setLingyongCircle(lingyongCircle);
			}
			fanghuOutLibTemp.setLastLingyongTime(lastLingyongTime);
			// if(lastLingyongTime!=null && !"".equals(lastLingyongTime)){
			// fanghuOutLibTemp.setLastLingyongTime(lastLingyongTime);
			// }
			boolean result = this.totalDao.update(fanghuOutLibTemp);
			if (result) {
				return fanghuOutLibTemp;
			}
		}
		return null;
	}

	/**
	 * 获得劳防用品
	 * 
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuOutLib getFanghuOutLibById(Integer id) {
		if (id != null) {
			return (FanghuOutLib) totalDao
					.getObjectById(FanghuOutLib.class, id);
		}
		return null;
	}

	/**
	 * 获得劳防用品集合
	 * 
	 * @param fanghuYongpin
	 * @return
	 */
	public Object[] getFanghuOutLibList(FanghuOutLib fanghuOutLib, int pageNo,
			int pageSize) {
		String hql = "from FanghuOutLib f where 1=1";
		if (fanghuOutLib != null) {
			Integer userId = fanghuOutLib.getUserId();
			if (userId != null) {
				hql += " and f.userId=" + userId;
			}
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 查询所有用户
	 * 
	 * @param
	 * @return
	 */
	public Object[] getUserListAll(Users user, int pageNo, int pageSize) {
		// String
		// hql="from Users u where 1=1 and u.onWork ='在职' and u.dept not in('病假','物业','上海明委','上海庆霆不锈钢制品有限公司') and u.id not in(select distinct(f.userid) from fanghuOutLib f ) group by u.dept";
		String hql = "from Users u where 1=1 and u.onWork ='在职' and u.dept not in('病休','病假','物业','上海明委','上海庆霆不锈钢制品有限公司') ";
		if (user != null) {
			String code = user.getCode();
			String name = user.getName();
			String dept = user.getDept();
			if (code != null && !"".equals(code)) {
				hql += " and u.code like '%" + code + "%'";
			}
			if (name != null && !"".equals(name)) {
				hql += " and u.name like '%" + name + "%'";
			}
			if (dept != null && !"".equals(dept)) {
				hql += " and u.dept like '%" + dept + "%'";
			}
		}

		hql += " order by u.dept";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
}
