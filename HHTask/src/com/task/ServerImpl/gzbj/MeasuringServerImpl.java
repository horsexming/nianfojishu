package com.task.ServerImpl.gzbj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.gzbj.MeasuringServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.Credentials;
import com.task.entity.Goods;
import com.task.entity.ModuleFunction;
import com.task.entity.Scrap;
import com.task.entity.Store;
import com.task.entity.Users;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.gzbj.Checkrecord;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.gzbj.Measuring;
import com.task.entity.caiwu.baobiao.*;
import com.task.entity.caiwu.baobiao.laozi.BasicSituation;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.util.RtxUtil;
import com.task.util.Util;

@SuppressWarnings( { "unchecked", "unused" })
public class MeasuringServerImpl implements MeasuringServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * 
	 * 检索库存表里的相关数据，像量具表中插入(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#saveMeasuring(com.task.entity.gzbj
	 * .Measuring, int, int)
	 */
	@Override
	public Object[] saveMeasuring(Measuring measuring, int pageNo,
			int pageSize, String status, String tag) {
		if (measuring == null) {
			measuring = new Measuring();
		}
		// 更新数据
		// String hql = "from Store where id1 is not null  ";
		String hql = "from Store where id1 is not null and  id1 not in (select measuring_no from Measuring) ";
		List list = totalDao.query(hql);
		if (list != null && list.size() > 0) {
			// 循环插入量具数据
			for (int i = 0; i < list.size(); i++) {
				try {
					Store store = (Store) list.get(i);
					float cura = store.getTotal();
					int curam = (int) cura;
					if (curam > 0) {
						for (int j = 0; j < curam; j++) {
							Measuring measuring3 = new Measuring();
							BeanUtils.copyProperties(store, measuring3);
							measuring3.setMeasuring_no(store.getId1());// 把本厂编号检索过来
							measuring3.setFk_stoid(store.getId());
							measuring3.setCurAmount(1F);
							measuring3.setCalibrationstate("正常");
							totalDao.save(measuring3);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String sql = "";
		// 分页查询
		if ("报废".equals(measuring.getCalibrationstate())) {
			sql = "select * from ta_Measuring where 1=1 and calibrationstate!='待校检'";// 原态sql语句
		} else {
			sql = "select * from ta_Measuring where 1=1 and calibrationstate='正常' and calibrationstate!='报废'";// 原态sql语句
		}

		if (!"".equals(measuring.getMatetag())
				&& measuring.getMatetag() != null) {
			sql += " and matetag like '%" + measuring.getMatetag() + "%'";
		}
		if (!"".equals(measuring.getMeasuring_no())
				&& measuring.getMeasuring_no() != null) {
			sql += " and measuring_no like '%" + measuring.getMeasuring_no()
					+ "%'";
		}
		if (!"".equals(measuring.getPlace()) && measuring.getPlace() != null) {
			sql += " and place like '%" + measuring.getPlace() + "%'";
		}
		if (!"".equals(measuring.getCalibrationstate())
				&& measuring.getCalibrationstate() != null) {
			sql += " and calibrationstate ='" + measuring.getCalibrationstate()
					+ "'";
		}
		if (!"".equals(measuring.getParClass())
				&& measuring.getParClass() != null) {
			sql += " and parClass like '%" + measuring.getParClass() + "%'";

		}
		if (!"".equals(measuring.getPersonliable())
				&& measuring.getPersonliable() != null) {
			sql += " and Personliable like '%" + measuring.getPersonliable()
					+ "%'";

		}
		if (measuring.getStorehouse() != null
				&& !"".equals(measuring.getStorehouse())) {
			sql += " and storehouse ='" + measuring.getStorehouse() + "' ";
		}
		if (measuring.getId() != null) {
			sql += " and id =" + measuring.getId();
		}
		if ("lj".equals(tag)) {
			sql += " and parClass = '量具'";
		} else if ("jj".equals(tag)) {
			sql += " and parClass = '检具'";
		} else if ("gj".equals(tag)) {
			sql += " and parClass = '工具'";
		} else if ("tzsb".equals(tag)) {
			sql += " and parClass = '特种设备'";
		}
		sql += " order by calibrationTime desc";
		List list1 = totalDao.findBySql(sql, pageNo, pageSize);
		for (Object object : list1) {
			Map<String, Object> map = (Map<String, Object>) object;
			if (map.get("fileName") == null || "".equals(map.get("fileName"))) {
				Checkrecord checkrecord = (Checkrecord) totalDao
						.getObjectByCondition(
								" from Checkrecord where measuring.id = ? order by id desc",
								map.get("id"));
				if (checkrecord != null) {
					map.put("fileName", checkrecord.getFileName());
				}
			}
		}
		int count = totalDao.findBySql(sql).size();// 总行数
		Object[] o = { list1, count };
		return o;
	}

	/*
	 * 
	 * 手动刷新
	 */
	// 更新量具表的所有库存量和库存表里面的统一、
	@Override
	public Object[] saveMeasuring1(Measuring measuring, int pageNo, int pageSize) {
		if (measuring == null) {
			measuring = new Measuring();
		}
		if (measuring != null) {
			String hql1 = "from Store where (matetag like '%卡尺%'  or matetag like '%千分尺%')";
			List list2 = totalDao.query(hql1);
			if (list2 != null && list2.size() > 0) {
				for (int i = 0; i < list2.size(); i++) {
					Store store2 = (Store) list2.get(i);
					String hql3 = "from Measuring where fk_stoid='"
							+ store2.getId() + "'";
					Measuring measuring3 = (Measuring) this.totalDao.get(hql3,
							null);
					measuring3.setCurAmount(store2.getCurAmount());
					this.totalDao.update(measuring3);
				}
			}
		}
		// 分页查询
		String sql = "select * from ta_Measuring where 1=1 and calibrationstate!='待校检' and calibrationstate!='报废'";// 原态sql语句
		List list1 = totalDao.findBySql(sql, pageNo, pageSize);
		int count = totalDao.findBySql(sql).size();// 总行数
		Object[] o = { list1, count };
		return o;
	}

	/*
	 * 
	 * 获得量具集合
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#saveMeasuring(com.task.entity.gzbj
	 * .Measuring, int, int)
	 */
	@Override
	public List getMeasuringList() {
		String hql = "from Measuring";
		List list = totalDao.find(hql);
		return list;
	}

	/*
	 * 
	 * 根据量具编号查询量具信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#findMeasuringById(java.lang.Integer)
	 */
	@Override
	public Measuring findMeasuringById(Integer measuringId) {
		Measuring measuring = (Measuring) this.totalDao.getObjectById(
				Measuring.class, measuringId);
		Checkrecord checkrecord = (Checkrecord) totalDao.getObjectByCondition(
				" from Checkrecord where measuring.id = ? order by id desc",
				measuring.getId());
		if (checkrecord != null) {
			measuring.setFileName(checkrecord.getFileName());
		}
		return measuring;
	}

	/*
	 * 
	 * 更新校准周期(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#updateMeasuringById(com.task.entity
	 * .gzbj.Measuring)
	 */
	@Override
	public void updateMeasuringById(Measuring measuring) {
		// TODO Auto-generated method stub
		// 更新量具表的周期
		String hql = "from Measuring where id=" + measuring.getId();
		Measuring measuring2 = (Measuring) this.totalDao.get(hql, null);
		measuring2.setPeriod(measuring.getPeriod());
		measuring2.setMeasuring_no(measuring.getMeasuring_no());
		if (measuring2.getLastcalibrationTime() == null
				|| "".equals(measuring2.getLastcalibrationTime())) {
			measuring2.setLastcalibrationTime(measuring
					.getLastcalibrationTime());
		}
		// 更新库存表的周期
		String hql1 = "from Store where id=" + measuring2.getFk_stoid();
		Store store = (Store) this.totalDao.get(hql1, null);
		if (store != null) {
			store.setPeriod(measuring.getPeriod());
			this.totalDao.update(store);
		}
		this.totalDao.update(measuring2);
	}

	/*
	 * 
	 * 待校检状态(non-Javadoc)
	 * 
	 * @see com.task.Server.gzbj.MeasuringServer#finddMeasuring()
	 */
	@Override
	public List<Measuring> updatedMeasuring(String status, Measuring measuring,
			String tag) {
		// String hql =
		// " from Measuring where  DATEDIFF(DAY,GETDATE(),nextcalibrationTime)<5 ";//sqlserver
		String hql = "";
		if ("tzsb".equals(tag)) {
			hql = " from Measuring where  DATEDIFF(nextcalibrationTime,CURRENT_DATE)< 60"; // mysql
		} else {
			hql = " from Measuring where  DATEDIFF(nextcalibrationTime,CURRENT_DATE)< 3 " +
					" and calibrationstate <> '报废'"; // mysql
		}
		String time = Util.getDateTime();
		List<Measuring> list = totalDao.query(hql);
		for (Measuring measuring1 : list) {
			measuring1.setCalibrationstate("待校检");
			totalDao.update(measuring1);
		}
		String hql_ = " and calibrationstate='待校检' ";
		if ("preson".equals(status)) {
			hql_ += " and usersIdliable =" + Util.getLoginUser().getId();
		}
		if ("lj".equals(tag)) {
			hql_ += " and parClass = '量具'";
		} else if ("jj".equals(tag)) {
			hql_ += " and parClass = '检具'";
		} else if ("gj".equals(tag)) {
			hql_ += " and parClass = '工具'";
		} else if ("tzsb".equals(tag)) {
			hql_ += " and parClass = '特种设备'";
		}
		if (measuring == null) {
			measuring = new Measuring();
		}
		String hql2 = totalDao.criteriaQueries(measuring, null);
		hql2 += hql_;
		List<Measuring> list2 = totalDao.query(hql2);
		for (Measuring measuring1 : list2) {
			Checkrecord checkrecord = (Checkrecord) totalDao
					.getObjectByCondition(
							" from Checkrecord where measuring.id = ?",
							measuring1.getId());
			if (checkrecord != null) {
				measuring1.setFileName(checkrecord.getFileName());
			}
		}
		return list2;
	}

	/*
	 * 
	 * 校检中(non-Javadoc)
	 * 
	 * @see com.task.Server.gzbj.MeasuringServer#findzMeasuring()
	 */
	@Override
	public List<Measuring> findzMeasuring(String status, Measuring measuring,
			String tag) {
		String hql_ = "";
		if (measuring == null) {
			measuring = new Measuring();
		}
		if ("lj".equals(tag)) {
			hql_ += " and parClass = '量具'";
		} else if ("jj".equals(tag)) {
			hql_ += " and parClass = '检具'";
		} else if ("gj".equals(tag)) {
			hql_ += " and parClass = '工具'";
		} else if ("tzsb".equals(tag)) {
			hql_ += " and parClass = '特种设备'";
		}
		if (measuring == null) {
			measuring = new Measuring();
		}
		String hql = totalDao.criteriaQueries(measuring, null);
		hql += hql_ + " and calibrationstate='校检中' ";
		List<Measuring> list = totalDao.query(hql);
		for (Measuring measuring1 : list) {
			Checkrecord checkrecord = (Checkrecord) totalDao
					.getObjectByCondition(
							" from Checkrecord where measuring.id = ? order by id desc ",
							measuring1.getId());
			if (checkrecord != null) {
				measuring1.setFileName(checkrecord.getFileName());
			}
		}
		return list;
	}

	/*
	 * 
	 * 报废状态(non-Javadoc)
	 * 
	 * @see com.task.Server.gzbj.MeasuringServer#findbMeasuring()
	 */
	@Override
	public List<Measuring> findbMeasuring(String status, Measuring measuring,
			String tag) {
		String hql_ = "";
		if (measuring == null) {
			measuring = new Measuring();
		}
		if ("lj".equals(tag)) {
			hql_ += " and parClass = '量具'";
		} else if ("jj".equals(tag)) {
			hql_ += " and parClass = '检具'";
		} else if ("gj".equals(tag)) {
			hql_ += " and parClass = '工具'";
		} else if ("tzsb".equals(tag)) {
			hql_ += " and parClass = '特种设备'";
		}
		if (measuring == null) {
			measuring = new Measuring();
		}
		String hql = totalDao.criteriaQueries(measuring, null);
		hql += hql_ + " and calibrationstate='报废' ";
		List<Measuring> list = totalDao.query(hql);
		for (Measuring measuring1 : list) {
			Checkrecord checkrecord = (Checkrecord) totalDao
					.getObjectByCondition(
							" from Checkrecord where measuring.id = ?",
							measuring1.getId());
			if (checkrecord != null) {
				measuring1.setFileName(checkrecord.getFileName());
			}
		}
		return list;
	}

	/*
	 * 更新校检状态及向报检记录表中插入数据(状态改为校检中)(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#updateandsaveMeasuring(com.task.
	 * entity.gzbj.Measuring, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void updateandsaveMeasuring(Measuring measuring, String empname,
			String empno, String fileName) {
		String hql = "from Measuring where id=" + measuring.getId();
		Measuring measuring2 = (Measuring) this.totalDao.get(hql, null);
		measuring2.setCalibrationstate("校检中");// 将状态改为校检中
		if (measuring.getPeriod() != null) {
			measuring2.setPeriod(measuring.getPeriod());
		}

		// 向校检记录表中插入数据
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
		// Timestamp timestamp = Timestamp.valueOf(df.format(new
		// java.util.Date()));// 获取系统当前时间
		String timestamp = Util.getDateTime("yyyy-MM-dd");

		// 计算下次校验时间

		if (measuring.getCalibrationTime() != null
				&& measuring.getCalibrationTime().length() > 0) {
			timestamp = measuring.getCalibrationTime();
		}
		measuring2.setCalibrationTime(timestamp);
		String nextTime = "";
		if (measuring2.getPeriod() == 360) {
			nextTime = (Integer.parseInt(timestamp.substring(0, 4)) + 1)
					+ timestamp.substring(4, timestamp.length());
			nextTime = Util.getSpecifiedDayAfter(nextTime, -1);
		} else if (measuring2.getPeriod() == 180) {
			Calendar c = Calendar.getInstance();
			c.setTime(Util.StringToDate(timestamp, "yyyy-MM-dd"));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 6);
			String str = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			System.out.println(str);
			nextTime = Util.getSpecifiedDayAfter(str, -1);
		} else {
			nextTime = Util.getSpecifiedDayAfter(timestamp, measuring2
					.getPeriod());
		}
		measuring2.setNextcalibrationTime(nextTime);
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		Checkrecord checkrecord = new Checkrecord();
		checkrecord.setCalibrationstate("校检中");
		checkrecord.setCalibrationTime("");
		checkrecord.setEmpname(empname);
		checkrecord.setEmpno(empno);
		checkrecord.setReportdate(timestamp.toString());
		checkrecord.setReportpop(loginUser.getName());
		checkrecord.setMeasuring(measuring);
		checkrecord.setFileName(fileName);
		this.totalDao.update(measuring2);
		this.totalDao.save(checkrecord);// 向校检记录表中插入数据

	}

	/*
	 * 
	 * 查看报检信息记录(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#findCheckrecordById(java.lang.Integer
	 * )
	 */
	@Override
	public Checkrecord findCheckrecordById(Integer measuringId) {
		String hql = "from Checkrecord where MeasuringId=" + measuringId;
		Checkrecord checkrecord = (Checkrecord) this.totalDao.get(hql, null);
		return checkrecord;
	}

	/*
	 * 
	 * 处于正常状态时改变量具的校检状态及向校检记录表中插入数据(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#updateandsaveMeasuring1(com.task
	 * .entity.gzbj.Measuring, java.lang.String, java.lang.Integer)
	 */

	/*
	 * public static void main(String[] args) { Long date1 =
	 * Util.StringToDate("2014-07-25 00:00:00",
	 * "yyyy-MM-dd HH:mm:ss").getTime(); float day=12*30.42F; Math.ceil(day);
	 * long dateLong=(long) Math.ceil(day); Long date2 =(dateLong) * (1000 * 60
	 * * 60 * 24)*1L; Long date3 = date1 + date2; DateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // long now =
	 * System.currentTimeMillis(); Calendar calendar = Calendar.getInstance();
	 * calendar.setTimeInMillis(date3);
	 * System.out.println(formatter.format(calendar.getTime())); }
	 */
	private static Calendar calendar = Calendar.getInstance();

	@Override
	public void updateandsaveMeasuring1(Measuring measuring, String empname,
			String empno, String lasttime, Integer period, String fileName)
			throws Exception {
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Timestamp timestamp = Timestamp
		// .valueOf(df.format(new java.util.Date()));// 获取系统当前时间
		// Date date1 = Util.StringToDate(lasttime, "yyyy-MM-dd");
		Measuring measuring2 = (Measuring) this.totalDao.getObjectById(
				Measuring.class, measuring.getId());

		String lastdate = measuring.getLastcalibrationTime();// 获取页面上次校准时间
		String enddate = measuring.getCalibrationTime();// 获取页面校准时间
		String sysdate = Util.getDateTime("yyyy-MM-dd");// 获取系统当前时间
		Date date1;
		String Sdate;
		if (enddate != null && !"".equals(enddate)) {
			Sdate = sysdate;
			sysdate = enddate;
			date1 = Util.StringToDate(sysdate, "yyyy-MM-dd");
			measuring2.setLastcalibrationTime(sysdate);
		} else {
			Sdate = lastdate;
			date1 = Util.StringToDate(lastdate, "yyyy-MM-dd");
		}
		measuring2.setCalibrationstate("正常");// 将状态改为正常
		if (!"".equals(lasttime)) {
			// calendar.setTime(Util.StringToDate(lasttime,"yyyy-MM-dd HH:mm:ss"));
			// calendar.setTime(date1);
			// calendar.add(Calendar.DATE, period * 30);
			// measuring2.setLastcalibrationTime(lasttime);
			float day = period * 30.42F;// 计算天数
			Math.ceil(day);// 四舍五入加以
			int dateLong = (int) Math.ceil(day);// 转化为整数
			Date date2 = Util.getCalendarDate(date1, dateLong);
			// measuring2.setCalibrationTime(Util
			// .DateToString(date2, "yyyy-MM-dd"));
			measuring2.setCalibrationTime(lasttime);
			measuring2.setMeasuring_no(measuring.getMeasuring_no());
		}
		// 计算下次校验时间
		String nextTime = "";
		if (measuring2.getPeriod() == 360) {
			nextTime = (Integer.parseInt(sysdate.substring(0, 4)) + 1)
					+ sysdate.substring(4, sysdate.length());
			nextTime = Util.getSpecifiedDayAfter(nextTime, -1);
		} else if (measuring2.getPeriod() == 180) {
			Calendar c = Calendar.getInstance();
			c.setTime(Util.StringToDate(measuring.getCalibrationTime(),
					"yyyy-MM-dd"));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 6);
			String str = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			System.out.println(str);
			nextTime = Util.getSpecifiedDayAfter(str, -1);
		} else {
			nextTime = Util.getSpecifiedDayAfter(sysdate, measuring2
					.getPeriod());
		}
		measuring2.setNextcalibrationTime(nextTime);
		// 向校检记录表中插入数据
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		Checkrecord checkrecord = new Checkrecord();
		checkrecord.setCalibrationstate("正常");
		checkrecord.setCalibrationTime(lasttime);// 校检时间
		checkrecord.setEmpname(empname);
		checkrecord.setEmpno(empno);
		checkrecord.setReportdate(Sdate);
		checkrecord.setReportpop(loginUser.getName());
		checkrecord.setMeasuring(measuring);
		checkrecord.setFileName(fileName);
		this.totalDao.save(checkrecord);// 插入校检记录表中的信息
		// this.totalDao.update(checkrecord);//更新校检记录表中的信息
		this.totalDao.update(measuring2);

	}

	/*
	 * 
	 * 查看量具明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#findMeasuringdetail(java.lang.Integer
	 * )
	 */
	@Override
	public Measuring findMeasuringdetail(Integer measuringId) {
		// TODO Auto-generated method stub
		Measuring measuring = (Measuring) this.totalDao.getObjectById(
				Measuring.class, measuringId);
		return measuring;
	}

	// 修改其状态，更新库存信息
	@Override
	public void updateMeasuringandstoreById(Integer MeasuringId, Float num,
			String empno) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = Timestamp
				.valueOf(df.format(new java.util.Date()));// 获取系统当前时间
		Float a = 1F;
		String hql = "from Measuring where id=" + MeasuringId;
		Measuring measuring = (Measuring) this.totalDao.get(hql, null);
		measuring.setCalibrationstate("报废");
		if (num == null) {
			num = measuring.getCurAmount();
		}
		measuring.setTotal(num - a);
		this.totalDao.update(measuring);// 更新其状态和库存

		Store store = new Store();
		Measuring measuring2 = (Measuring) this.totalDao.get(hql, null);
		String hql2 = "from Store where id=" + measuring2.getFk_stoid();
		Store store2 = (Store) this.totalDao.get(hql2, null);
		if (store2 != null) {
			store2.setTotal(num - a);
			this.totalDao.update(store2);// 更新store表库存
		}

		// String dept = this.totalDao.getChildHql("dept", empno);
		// String hql3 = "from users where code="+empno;
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String username = loginUser.getName();
		String userdept = loginUser.getDept();

		Scrap scrap = new Scrap();// 报废表
		scrap.setUsername(username);
		scrap.setDept(userdept);
		scrap.setNumber(measuring2.getNumber());
		scrap.setMatetag(measuring2.getMatetag());
		scrap.setFormat(measuring2.getFormat());
		DecimalFormat df1 = new DecimalFormat("#"); // 取整
		scrap.setAmount(Integer.parseInt(df1.format(measuring2.getTotal())
				.toString()));
		scrap.setBadDate(timestamp);
		scrap.setMore1(measuring2.getMore1());
		scrap.setMore2(measuring2.getMore());// 备注
		scrap.setState(1);// 直接报废
		if (store2 != null) {
			scrap.setMix(store2.getMix());
			scrap.setStore(store2);// 外键关联
		}
		this.totalDao.save(scrap);// 保存到报废表
	}

	/*
	 * 
	 * 查看校检记录明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#findCheckcorddetail(java.lang.Integer
	 * )
	 */
	@Override
	public List<Checkrecord> findCheckcorddetail(Integer measuringId) {
		String hql = "from Checkrecord where MeasuringId=" + measuringId;
		List<Checkrecord> list = totalDao.query(hql);
		return list;
	}

	@Override
	public List<Scrap> findScrapdetail(Integer measuringId) {
		Measuring measuring = (Measuring) this.totalDao.getObjectById(
				Measuring.class, measuringId);
		String hql = "from Scrap where fk_store_id=" + measuring.getFk_stoid();
		List<Scrap> list = totalDao.query(hql);
		return list;
	}

	/*
	 * 
	 * 修改量具信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.MeasuringServer#updateandsaveMeasuring1(com.task
	 * .entity.gzbj.Measuring)
	 */
	@Override
	public void updateandsaveMeasuring2(Measuring measuring) {
		Measuring measuring2 = (Measuring) this.totalDao.getObjectById(
				Measuring.class, measuring.getId());
		measuring2.setLastcalibrationTime(measuring.getLastcalibrationTime());
		measuring2.setMeasuring_no(measuring.getMeasuring_no());
		if (measuring2.getCalibrationstate() != null
				&& measuring2.getCalibrationstate().equals("校检中")
				&& (measuring.getCalibrationTime() == null || measuring
						.getCalibrationTime().equals(""))) {
			measuring2.setCalibrationstate("正常");
		}
		measuring2.setCalibrationTime(measuring.getCalibrationTime());
		measuring2.setPeriod(measuring.getPeriod());
		measuring2.setJztype(measuring.getJztype());
		measuring2.setStorehouse(measuring.getStorehouse());
		measuring2.setCodeliable(measuring.getCodeliable());
		measuring2.setPersonliable(measuring.getPersonliable());
		measuring2.setUsersIdliable(measuring.getUsersIdliable());
		measuring2.setPlace(measuring.getPlace());
		measuring2.setCsjd(measuring.getCsjd());
		measuring2.setNumber(measuring.getNumber());
		// 计算下次校验时间
		String timestamp = "";
		if (measuring.getCalibrationTime() != null
				&& measuring.getCalibrationTime().length() > 0) {
			timestamp = measuring.getCalibrationTime();
		}
		measuring2.setCalibrationTime(timestamp);
		String nextTime = "";
		if (measuring2.getPeriod() == 360 && timestamp.length() > 0) {
			nextTime = (Integer.parseInt(timestamp.substring(0, 4)) + 1)
					+ timestamp.substring(4, timestamp.length());
			nextTime = Util.getSpecifiedDayAfter(nextTime, -1);
		} else if (measuring2.getPeriod() == 180) {
			Calendar c = Calendar.getInstance();
			c.setTime(Util.StringToDate(timestamp, "yyyy-MM-dd"));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 6);
			String str = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			System.out.println(str);
			nextTime = Util.getSpecifiedDayAfter(str, -1);
		} else {
			nextTime = Util.getSpecifiedDayAfter(timestamp, measuring2
					.getPeriod());
		}
		measuring2.setCsjd(measuring.getCsjd());
		measuring2.setFileName(measuring.getFileName());
		measuring2.setNextcalibrationTime(nextTime);
		this.totalDao.update(measuring2);
	}

	@Override
	public String addmeasuring(Measuring measuring) {
		if (measuring != null) {
			// 计算下次校验时间
			String nextTime = "";
			boolean bool = isOneMeasuring_no(measuring.getMeasuring_no());
			if (bool) {
				if (measuring.getPeriod() == 360) {
					nextTime = (Integer.parseInt(Util.getDateTime("yyyy")) + 1)
							+ "-" + Util.getDateTime("MM-dd");
					nextTime = Util.getSpecifiedDayAfter(nextTime, -1);
				} else {
					nextTime = Util.getSpecifiedDayAfter(Util
							.getDateTime("yyy-MM-dd"), measuring.getPeriod());
				}
				measuring.setNextcalibrationTime(nextTime);
				measuring.setCalibrationstate("正常");
				return totalDao.save(measuring) + "";
			} else {
				return "已有本厂编号:" + measuring.getMeasuring_no() + "请更换一个再添加";
			}

		}
		return null;
	}

	@Override
	public String daorumeasuring(File measuringfile) {
		String msg = "true";
		boolean flag = true;
		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int i = 0;
		try {
			FileCopyUtils.copy(measuringfile, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}
			StringBuffer strb = new StringBuffer();
			Integer errorCount = 0;// 错误数量
			Integer cfCount = 0;// 重复数量
			Integer successCount = 0;// 成功数量
			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 1) {
				List<Integer> strList = new ArrayList<Integer>();
				for (i = 1; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					if (cells != null && cells.length == 0) {
						continue;
					}
					String a = cells[1].getContents();// 名称
					String b = cells[2].getContents();// 单位
					String c = cells[3].getContents();// 规格
					String d = cells[4].getContents();// 责任人工号
					String e = cells[5].getContents();// 责任人姓名
					String f = cells[6].getContents();// 校准周期
					String g = cells[7].getContents();// 位置
					String k = cells[8].getContents();// 分类 
					String h = cells[9].getContents();// 仓库
					String j = cells[10].getContents();// 最低库存
					String l = cells[11].getContents();// 当前量
					String m = cells[12].getContents();// 合计金额
					String n = cells[13].getContents();// 申报部门
					String o = cells[14].getContents();// 本厂编号
					String q = cells[15].getContents();// 单价
					String s = cells[16].getContents();// 总数
					String t = cells[17].getContents();// 校验时间
					String u = "";
					if (cells.length >= 19) {
						u = cells[18].getContents();// 校验类型
					}
					String p = "";
					if (cells.length >= 20) {
						p = cells[19].getContents();// 测试精度
					}
					Measuring measuring = new Measuring();
					measuring.setMatetag(a);
					measuring.setUnit(b);
					measuring.setFormat(c);
					measuring.setCodeliable(d);
					measuring.setPersonliable(e);
					if (d != null && d.length() > 0) {
						Users user = (Users) totalDao.getObjectByCondition(
								" from Users where code = ? ", d);
						if (user == null) {
							errorCount++;
							strb.append("第" + (i + 1) + "行,没有找到工号为:" + d
									+ "的员工。<br/>");
							continue;
						} else {
							measuring.setUsersIdliable(user.getId());
							measuring.setPersonliable(user.getName());
						}
					} else {
						errorCount++;
						strb.append("第" + (i + 1) + "行,没有填写责任人工号。<br/>");
						continue;
					}
					if (q != null && q.length() > 0 && s != null
							&& s.length() > 0) {
						try {
							Float price = Float.parseFloat(q);
							Float total = Float.parseFloat(s);
							measuring.setPrice(price);
							measuring.setTotal(total);
							measuring.setTotMoney(price * total);
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
					if (f != null && f.length() > 0) {
						measuring.setPeriod(Integer.parseInt(f));
						String nextTime = "";
						if (measuring.getPeriod() == 360) {
							nextTime = (Integer.parseInt(Util
									.getDateTime("yyyy")) + 1)
									+ "-" + Util.getDateTime("MM-dd");
							nextTime = Util.getSpecifiedDayAfter(nextTime, -1);
						} else {
							nextTime = Util.getSpecifiedDayAfter(Util
									.getDateTime("yyy-MM-dd"), measuring
									.getPeriod());
						}
						measuring.setNextcalibrationTime(nextTime);
					} else {
						strb.append("第" + (i + 1) + "行,没有填写校准周期。<br/>");
						errorCount++;
						continue;
					}
					boolean bool = isOneMeasuring_no(o);
					if (!bool) {
						strb.append("第" + (i + 1) + "行,本厂编号" + o + "重复。<br/>");
						cfCount++;
						continue;
					}
					if (j != null && j.length() > 0) {
						measuring.setMinStore(Float.parseFloat(j));
					}
					if (l != null && l.length() > 0) {
						measuring.setCurAmount(Float.parseFloat(l));
					}
					measuring.setPlace(g);
					measuring.setStorehouse(h);
					measuring.setParClass(k);
					// measuring.setTotMoney(Float.parseFloat(m));
					measuring.setAppDept(n);
					measuring.setMeasuring_no(o);
					measuring.setCalibrationstate("正常");
					measuring.setCalibrationTime(t);
					measuring.setJztype(u);
					if (p != null && p.length() > 0) {
						try {
							measuring.setCsjd(p);
						} catch (Exception e2) {
							e2.printStackTrace();
						}

					}
					if (t != null && t.length() > 0) {
						String nextTime = "";
						if (measuring.getPeriod() == 360) {
							nextTime = (Integer.parseInt(t.substring(0, 4)) + 1)
									+ t.substring(4, t.length());
							nextTime = Util.getSpecifiedDayAfter(nextTime, -1);
						} else {
							nextTime = Util.getSpecifiedDayAfter(t, measuring
									.getPeriod());
						}
						nextTime = Util.getSpecifiedDayAfter(t, measuring
								.getPeriod());
						measuring.setNextcalibrationTime(nextTime);
						measuring.setCalibrationstate("正常");
					}
					if (totalDao.save(measuring)) {
						successCount++;
					}
				}

				is.close();// 关闭流
				wk.close();// 关闭工作薄
				msg = "已成功导入" + successCount + "个<br/>失败" + errorCount
						+ "个<br/>重复" + cfCount + "个<br/>失败的行数分别为：<br/>"
						+ strb.toString();
			} else {
				msg = "没有获取到行数";
			}

		} catch (Exception e) {
			msg = "第" + (i + 1) + "行出现异常!";
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public void exportExcel(Measuring measuring) {

		if (measuring == null) {
			measuring = new Measuring();
		}
		String hql = totalDao.criteriaQueries(measuring, null);
		List<Measuring> measuringList = totalDao.query(hql
				+ " order by nextcalibrationTime");
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("量、检具".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("量、检具信息", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(4, 24);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 12);
			ws.setColumnView(13, 16);
			ws.setColumnView(18, 25);
			// 仓库 分类 库存 校准周期(月) 上次校准时间 校准时间 下次校准时间 校准状态 责任人 库存编号 本厂编号
			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "名称"));
			ws.addCell(new Label(2, 0, "规格"));
			ws.addCell(new Label(3, 0, "仓库"));
			ws.addCell(new Label(4, 0, "分类"));
			ws.addCell(new Label(5, 0, "库存"));
			ws.addCell(new Label(6, 0, "校准周期(天)"));
			ws.addCell(new Label(7, 0, "校准时间"));
			ws.addCell(new Label(8, 0, "下次校准时间"));
			ws.addCell(new Label(9, 0, "校准状态"));
			ws.addCell(new Label(10, 0, "校准类型"));
			ws.addCell(new Label(11, 0, "责任人"));
			ws.addCell(new Label(12, 0, "库存编号"));
			ws.addCell(new Label(13, 0, "本厂编号"));
			for (int i = 0; i < measuringList.size(); i++) {
				Measuring measuring1 = (Measuring) measuringList.get(i);
				ws.addCell(new Label(0, i + 1, i + 1 + ""));
				ws.addCell(new Label(1, i + 1, measuring1.getMatetag()));
				ws.addCell(new Label(2, i + 1, measuring1.getFormat()));
				ws.addCell(new Label(3, i + 1, measuring1.getStorehouse()));
				ws.addCell(new Label(4, i + 1, measuring1.getParClass()));
				ws.addCell(new Label(5, i + 1, measuring1.getCurAmount() + ""));
				ws.addCell(new Label(6, i + 1, measuring1.getPeriod() + "（天）"));
				ws
						.addCell(new Label(7, i + 1, measuring1
								.getCalibrationTime()));
				ws.addCell(new Label(8, i + 1, measuring1
						.getNextcalibrationTime()));
				ws
						.addCell(new Label(9, i + 1, measuring1
								.getCalibrationstate()));
				ws.addCell(new Label(10, i + 1, measuring1.getJztype()));
				ws.addCell(new Label(11, i + 1, measuring1.getPersonliable()));
				ws.addCell(new Label(12, i + 1, measuring1.getNumber()));
				ws.addCell(new Label(13, i + 1, measuring1.getMeasuring_no()));
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
	public boolean delmeasuring(Measuring measuring) {
		if (measuring != null) {
			return totalDao.delete(measuring);
		}
		return false;
	}

	// 提醒离校验到期还剩5的的去校验;
	public void sendmsgjiaoyan() {
		// String hql =
		// " from Measuring where  DATEDIFF(DAY,GETDATE(),nextcalibrationTime)<5 ";//sqlserver
		String hql = " from Measuring where  DATEDIFF(nextcalibrationTime,CURRENT_DATE)< 3 and parClass = '量具' and calibrationstate <> '报废' "; // mysql
		List<Measuring> list = totalDao.query(hql);
		int size = 0;
		if (list != null && list.size() > 0) {
			size = list.size();
			for (Measuring measuring : list) {
				measuring.setCalibrationstate("待校检");
				totalDao.update(measuring);
			}
		}
		if (size > 0) {
			AlertMessagesServerImpl.addAlertMessagesxt("量具校准管理", "有" + size
					+ "条，量具校验期已过，请及时校验!", "量具校验提醒");
		}
		String hql1 = " from Measuring where  DATEDIFF(nextcalibrationTime,CURRENT_DATE)< 3 and parClass = '工具' and calibrationstate <> '报废'"; // mysql
		List<Measuring> list1 = totalDao.query(hql1);
		int size1 = 0;
		if (list1 != null && list1.size() > 0) {
			size1 = list1.size();
			for (Measuring measuring : list1) {
				measuring.setCalibrationstate("待校检");
				totalDao.update(measuring);
			}
		}
		if (size1 > 0) {
			AlertMessagesServerImpl.addAlertMessagesxt("工具校准管理", "有" + size1
					+ "条，工具校验期已过，请及时校验!", "工具校验提醒");
		}
		String hql2 = " from Measuring where  DATEDIFF(nextcalibrationTime,CURRENT_DATE)< 3 and parClass = '检具' and calibrationstate <> '报废' "; // mysql
		List<Measuring> list2 = totalDao.query(hql2);
		int size2 = 0;
		if (list2 != null && list2.size() > 0) {
			size2 = list2.size();
			for (Measuring measuring : list2) {
				measuring.setCalibrationstate("待校检");
				totalDao.update(measuring);
			}
		}
		if (size2 > 0) {
			AlertMessagesServerImpl.addAlertMessagesxt("检具校准管理", "有" + size1
					+ "条，检具校验期已过，请及时校验!", "检具校验提醒");
		}
		String hql3 = " from Measuring where  DATEDIFF(nextcalibrationTime,CURRENT_DATE)< 60 and parClass = '特种设备' and alibrationstate <> '报废' "; // mysql
		List<Measuring> list3 = totalDao.query(hql3);
		int size3 = 0;
		if (list3 != null && list3.size() > 0) {
			size3 = list3.size();
			for (Measuring measuring : list3) {
				measuring.setCalibrationstate("待校检");
				totalDao.update(measuring);
			}
		}
		if (size3 > 0) {
			AlertMessagesServerImpl.addAlertMessagesxt("特种设备校准管理", "有" + size1
					+ "条，特种设备校验期已过，请及时校验!", "特种设备校验提醒");
		}

		/********************** 行驶行驶证到期提醒 ***********************/
		String newDate = Util.getSpecifiedDayAfter(Util.getDateTime(), 90);// 得到90天后的日期
		List<Credentials> credentialList1 = totalDao.query(
				"from Credentials where validfor <= ?", newDate);
		if (credentialList1 != null && credentialList1.size() > 0) {
			for (Credentials cre : credentialList1) {
				if ("驾驶证".equals(cre.getCardtype())) {
					RtxUtil.sendNotify(cre.getCode(), cre.getName()
							+ " 您有驾驶证将于3个月内过期，请及时处理!谢谢。", "系统消息", "0", "0");
				} else if ("行驶证".equals(cre.getCardtype())) {
					RtxUtil.sendNotify(cre.getCode(), cre.getName() + " 您有"
							+ cre.getPlatenumber() + "行驶证将于3个月内过期，请及时处理!谢谢。",
							"系统消息", "0", "0");
				}
				if (cre.getCishu() == null)
					cre.setCishu(1);
				else
					cre.setCishu(cre.getCishu() + 1);
				totalDao.update(cre);
			}
		}
		/********************** 库存非CS物料超期检验提醒 ***********************/
		String hql_goods = "from Goods where goodsCurQuantity>0 and "
				+ " DATEDIFF(goodsnexttime,CURRENT_DATE)<=15 "
				+ " and goodsClass in('外协库','外购件库','委外库') and kgliao <> 'CS'"
				+ "order by goodsnexttime desc";
		List<Goods> goodsList = totalDao.find(hql_goods);
		if (goodsList != null && goodsList.size() > 0) {
			AlertMessagesServerImpl.addAlertMessagesxt("库存质检管理", "有"
					+ goodsList.size() + "条库存质检周期将到，请及时检验", "库存质检管理提醒");
		}
		/********************** 库存CS物料超期检验提醒 ***********************/
		String hql_goods0 = "from Goods where goodsCurQuantity>0 and "
				+ " DATEDIFF(goodsnexttime,CURRENT_DATE)<=60 "
				+ " and goodsClass in('外协库','外购件库','委外库') and kgliao = 'CS'"
				+ "order by goodsnexttime desc";
		List<Goods> goodsList0 = totalDao.find(hql_goods0);
		if (goodsList0 != null && goodsList0.size() > 0) {
			AlertMessagesServerImpl.addAlertMessagesxt("库存质检管理(CS)", "有"
					+ goodsList0.size() + "条库存(CS)质检周期将到，请及时检验", "库存质检管理(CS)");
		}
		/********************** 工装报检提醒 ***********************/
		String hql_gz = " from Gzstore where status = '待检验'";
		List<Gzstore> gzList = totalDao.query(hql_gz);
		if (gzList != null && gzList.size() > 0) {
			AlertMessagesServerImpl.addAlertMessagesxt("工装报检周期", "有"
					+ gzList.size() + "条，工装校验次数已过，请及时校验!", "工装报检提醒");
		}
	}

	@Override
	public boolean isOneMeasuring_no(String measuringNo) {
		if (measuringNo != null && measuringNo.length() > 0) {
			String hql = " from Measuring where measuring_no = ? ";
			int count = totalDao.getCount(hql, measuringNo);
			if (count >= 1) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		RtxUtil.sendNotify("helper", " 您有驾驶证将于3个月内过期，请及时处理!谢谢。", "系统消息", "0",
				"0");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.task.Server.gzbj.MeasuringServer#caiwujisun()
	 */
	public void caiwujisun(String months) {
		String companyName = Util.getLoginCompanyInfo().getName();
		Integer today = Integer.parseInt(Util.getDateTime("dd"));
		Integer jihao = (Integer) totalDao
				.getObjectByCondition("select jihao from AccountsDate ");
		// if(jihao!=null && today == jihao){
		if (months == null || months.length() == 0) {
			months = Util.getDateTime("yyyy-MM");
		}
		String lastyearmoths = (Integer.parseInt(Util.getDateTime("yyyy")) - 1)
				+ "-" + Util.getDateTime("MM");
		/**
		 * 资产负债表
		 */
		BalanceSheet balanceSheet = new BalanceSheet();
		balanceSheet.setMonths(months);
		/************************ 流动资产项 *****************************************/
		// 货币资金;
		Double cash1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('库存现金','银行存款','其他货币资金')",
						months);// 货币资金期初额
		Double cash2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('库存现金','银行存款','其他货币资金')",
						months);// 货币资金期末额
		balanceSheet.setCash1(cash1 == null ? 0 : cash1);
		balanceSheet.setCash2(cash2 == null ? 0 : cash2);
		// 交易性金融资产
		Double jyxjrzc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('交易性金融资产') and belongLayer = 1",
						months);// 期初
		Double jyxjrzc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('交易性金融资产') and belongLayer = 1",
						months);// 期末额
		balanceSheet.setJyxjrzc1(jyxjrzc1 == null ? 0 : jyxjrzc1);
		balanceSheet.setJyxjrzc2(jyxjrzc2 == null ? 0 : jyxjrzc2);
		// 应收票据
		Double yspj1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应收票据') and belongLayer = 1",
						months);// 年初
		Double yspj_qichu = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowQichuMoney)-sum(borrowQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('应收票据') and belongLayer = 1",
						months);// 期初
		Double yspj2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应收票据') and belongLayer = 1",
						months);// 期末额
		balanceSheet.setYspj1(yspj1 == null ? 0 : yspj1);
		balanceSheet.setYspj2(yspj2 == null ? 0 : yspj2);
		// 应收账款
		Double yszk1 = 0d;
		Double yszk2 = 0d;
		Double jiefang1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应收账款') and belongLayer = 1",
						months);// 期初
		Double yszk_qichu = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('应收账款') and belongLayer = 1",
						months);// 期初
		yszk_qichu = yszk_qichu == null ? 0 : yszk_qichu;
		Double jiefang2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应收账款') and belongLayer = 1",
						months);// 期末
		Double huaizhang1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " (select id  from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '应收账款' ",
						months);// 期初
		Double huaizhang_qichu = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowQichuMoney)-sum(borrowQichuMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " (select id  from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '应收账款' ",
						months);// 期初
		huaizhang_qichu = huaizhang_qichu == null ? 0 : huaizhang_qichu;
		Double huaizhang2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " ( select id from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '应收账款' ",
						months);// 期末额
		if (jiefang1 != null && huaizhang1 != null) {
			yszk1 = jiefang1 - huaizhang1;
		}
		if (jiefang2 != null && huaizhang2 != null) {
			yszk2 = jiefang2 - huaizhang2;
		}
		yszk_qichu = yszk_qichu - huaizhang_qichu;
		balanceSheet.setYszk1(yszk1);
		balanceSheet.setYszk2(yszk2);
		// 预付账款
		Double yfzk1 = 0d;
		Double yfzk2 = 0d;
		Double yfzk_qichu = 0d;
		Double fu1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('预付账款','应付账款')",
						months);// 年初
		Double fu0 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('预付账款','应付账款')",
						months);// 期初
		fu0 = fu0 == null ? 0 : fu0;
		Double fu2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('预付账款','应付账款')",
						months);// 期末
		Double huaizhang_1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " (select id  from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '预付账款' ",
						months);// 期初
		Double huaizhang_2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " ( select id from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '预付账款' ",
						months);// 期末额
		Double huaizhang_0 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowQichuMoney)-sum(lendQichuMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " ( select id from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '预付账款' ",
						months);// 期末额
		huaizhang_0 = huaizhang_0 == null ? 0 : huaizhang_0;
		if (fu1 != null && huaizhang_1 != null) {
			yfzk1 = fu1 - huaizhang_1;
		}
		if (fu2 != null && huaizhang_2 != null) {
			yfzk2 = fu2 - huaizhang_2;
		}
		yfzk_qichu = fu0 - huaizhang_0;
		balanceSheet.setYfzk1(yfzk1);
		balanceSheet.setYfzk2(yfzk2);
		// 应收利息
		Double yslx1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应收利息') and belongLayer = 1",
						months);// 期初
		Double yslx2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应收利息') and belongLayer = 1",
						months);// 期初
		balanceSheet.setYslx1(yslx1 == null ? 0 : yslx1);
		balanceSheet.setYslx2(yslx2 == null ? 0 : yslx2);
		// 应收股利
		Double ysgl1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应收股利') and belongLayer = 1",
						months);// 期初
		Double ysgl2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应收股利') and belongLayer = 1",
						months);// 期初
		balanceSheet.setYsgl1(ysgl1 == null ? 0 : ysgl1);
		balanceSheet.setYsgl2(ysgl2 == null ? 0 : ysgl2);
		// 其他应收款
		Double qtysk1 = 0d;
		Double qtysk2 = 0d;
		qtysk1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('其他应收款') and belongLayer = 1",
						months);// 期初
		qtysk2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他应收款') and belongLayer = 1",
						months);// 期初
		Double huaizhang_qt1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " (select id from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '其他应收款' ",
						months);// 期初
		Double huaizhang_qt2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " (select id from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '其他应收款' ",
						months);// 期初
		if (qtysk1 != null && huaizhang_qt1 != null) {
			qtysk1 = qtysk1 - huaizhang_qt1;
		}
		if (qtysk2 != null && huaizhang_qt2 != null) {
			qtysk2 = qtysk2 - huaizhang_qt2;
		}
		balanceSheet.setQtysk1(qtysk1);
		balanceSheet.setQtysk2(qtysk2);
		// 存货
		Double ch1 = 0d;
		Double ch2 = 0d;
		Double ch_qichu = 0d;
		ch1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('材料采购','在途物资','原材料','库存商品'"
								+ " ,'周转材料','委托加工物资','生产成本','劳务成本') and belongLayer = 1",
						months);// 期初
		ch_qichu = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowQichuMoney)-sum(lendQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('材料采购','在途物资','原材料','库存商品'"
								+ " ,'周转材料','委托加工物资','生产成本','劳务成本') and belongLayer = 1",
						months);// 期初
		ch_qichu = ch_qichu == null ? 0 : ch_qichu;
		ch2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('材料采购','在途物资','原材料','库存商品'"
								+ " ,'周转材料','委托加工物资','生产成本','劳务成本' ) and belongLayer = 1",
						months);// 期初
		Double chdj1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('存货跌价准备')",
						months);// 期初
		Double chdj0 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowQichuMoney)-sum(lendQichuMoney)) from SubBudgetRate where bookKDate = ? and name in ('存货跌价准备')",
						months);// 期初
		chdj0 = chdj0 == null ? 0 : chdj0;
		Double chdj2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('存货跌价准备')",
						months);// 期初
		if (ch1 != null && chdj1 != null) {
			ch1 = ch1 - chdj1;
		}
		if (ch2 != null && chdj2 != null) {
			ch2 = ch2 - chdj2;
		}
		ch_qichu = ch_qichu - chdj0;
		balanceSheet.setCh1(ch1);
		balanceSheet.setCh2(ch2);
		// 原材料
		Double ycl1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('原材料') and  belongLayer = 1",
						months);// 期初
		ycl1 = ycl1 == null ? 0 : ycl1;
		Double ycl2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('原材料') and  belongLayer = 1",
						months);// 期初
		ycl2 = ycl2 == null ? 0 : ycl2;
		Double ycl_dj1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where  fatherId in (select id from SubBudgetRate where  bookKDate = ? and name in ('存货跌价准备') )  and name IN ('原材料') ",
						months);// 期初
		ycl_dj1 = ycl_dj1 == null ? 0 : ycl_dj1;
		Double ycl_dj2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where  fatherId in (select id from SubBudgetRate where  bookKDate = ? and name in ('存货跌价准备') )  and name IN ('原材料')",
						months);// 期初
		ycl_dj2 = ycl_dj2 == null ? 0 : ycl_dj2;
		Double clcbcy1 = (Double) totalDao
				.getObjectByConditionforDouble(
						" select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate  where bookKDate = ? and name in ('材料成本差异')",
						months);// 期初\
		clcbcy1 = clcbcy1 == null ? 0 : clcbcy1;
		Double clcbcy2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('材料成本差异')",
						months);// 期初
		clcbcy2 = clcbcy2 == null ? 0 : clcbcy2;
		ycl1 = ycl1 + clcbcy1;
		ycl2 = ycl2 + clcbcy2;
		balanceSheet.setYcl1(ycl1);
		balanceSheet.setYcl2(ycl2);
		// 库存商品
		Double kcsp1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('库存商品') and  belongLayer = 1",
						months);// 期初
		kcsp1 = kcsp1 == null ? 0 : kcsp1;
		Double kcsp2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('库存商品') and  belongLayer = 1",
						months);// 期初
		kcsp2 = kcsp2 == null ? 0 : kcsp2;
		Double fcsp1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('发出商品')",
						months);// 期初
		fcsp1 = fcsp1 == null ? 0 : fcsp1;
		Double fcsp2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('发出商品')",
						months);// 期初
		fcsp2 = fcsp2 == null ? 0 : fcsp2;
		kcsp1 = kcsp1 + fcsp1;
		kcsp2 = kcsp2 + fcsp2;
		balanceSheet.setKcsp1(kcsp1 == null ? 0 : kcsp1);
		balanceSheet.setKcsp2(kcsp2 == null ? 0 : kcsp2);
		// 一年内到期的非流动资产
		Double fldzcyears1 = 0d;
		Double fldzcyears2 = 0d;
		balanceSheet.setFldzcyears1(fldzcyears1);
		balanceSheet.setFldzcyears2(fldzcyears2);
		// 其他流动资产
		Double qitaldzc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('其他流动资产')",
						months);// 期初
		Double qitaldzc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他流动资产')",
						months);// 期末
		balanceSheet.setQitaldzc1(qitaldzc1 == null ? 0 : qitaldzc1);
		balanceSheet.setQitaldzc2(qitaldzc2 == null ? 0 : qitaldzc2);
		// 流动资产合计
		Double liudonghj1 = balanceSheet.getCash1()
				+ balanceSheet.getJyxjrzc1() + balanceSheet.getYspj1()
				+ balanceSheet.getYszk1() + balanceSheet.getYfzk1()
				+ balanceSheet.getYslx1() + balanceSheet.getYsgl1()
				+ balanceSheet.getQtysk1() + balanceSheet.getCh1()
				+ balanceSheet.getFldzcyears1() + balanceSheet.getQitaldzc1();
		Double liudonghj2 = balanceSheet.getCash2()
				+ balanceSheet.getJyxjrzc2() + balanceSheet.getYspj2()
				+ balanceSheet.getYszk2() + balanceSheet.getYfzk2()
				+ balanceSheet.getYslx2() + balanceSheet.getYsgl2()
				+ balanceSheet.getQtysk2() + balanceSheet.getCh2()
				+ balanceSheet.getFldzcyears2() + balanceSheet.getQitaldzc2();
		balanceSheet.setLiudonghj1(liudonghj1);
		balanceSheet.setLiudonghj2(liudonghj2);
		/************************ 非流动资产项 *****************************************/
		// 可供出售金融资产
		Double kgcsjrzc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('可供出售金融资产') and belongLayer = 1",
						months);// 期初
		Double kgcsjrzc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('可供出售金融资产') and belongLayer = 1",
						months);// 期末
		balanceSheet.setKgcsjrzc1(kgcsjrzc1 == null ? 0 : kgcsjrzc1);
		balanceSheet.setKgcsjrzc2(kgcsjrzc2 == null ? 0 : kgcsjrzc2);
		// 持有至到期投资
		Double cyzdqtz1 = 0d;
		Double cyzdqtz2 = 0d;
		cyzdqtz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('持有至到期投资') and belongLayer = 1",
						months);// 期初
		cyzdqtz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('持有至到期投资') and belongLayer = 1",
						months);// 期末
		Double cyzb_1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('持有至到期投资减值准备')",
						months);// 期初
		Double cyzb_2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('持有至到期投资减值准备')",
						months);// 期末
		balanceSheet.setCyzdqtz1(cyzdqtz1);
		balanceSheet.setCyzdqtz2(cyzdqtz2);
		// 长期应收款
		Double cqysk1 = 0d;
		Double cqysk2 = 0d;
		cqysk1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('长期应收款') and belongLayer = 1",
						months);// 期初
		cqysk2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('长期应收款') and belongLayer = 1",
						months);// 期末
		Double wsxrzsy1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('未实现融资收益')",
						months);// 期初
		Double wsxrzsy2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('未实现融资收益')",
						months);// 期末
		Double huaizhang_cqys1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " (select id from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '长期应收款' ",
						months);// 期初
		Double huaizhang_cqys2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where subBudgetRate.id in"
								+ " ( select id from SubBudgetRate where name = '坏账准备' and bookKDate = ?) and name = '长期应收款' ",
						months);// 期初
		if (cqysk1 != null && wsxrzsy1 != null && huaizhang_cqys1 != null) {
			cqysk1 = cqysk1 - wsxrzsy1 - huaizhang_cqys1;
		}
		if (cqysk2 != null && wsxrzsy2 != null && huaizhang_cqys2 != null) {
			cqysk2 = cqysk2 - wsxrzsy2 - huaizhang_cqys2;
		}
		balanceSheet.setCqysk1(cqysk1);
		balanceSheet.setCqysk2(cqysk2);
		// 长期股权投资
		Double cqgqtz1 = 0d;
		Double cqgqtz2 = 0d;
		cqgqtz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('长期股权投资') and belongLayer = 1",
						months);// 期初
		cqgqtz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('长期股权投资') and belongLayer = 1",
						months);// 期末
		Double cqysk_jz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('长期股权投资减值准备')",
						months);// 期初
		Double cqysk_jz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('长期股权投资减值准备')",
						months);// 期末
		if (cqgqtz1 != null && cqysk_jz1 != null) {
			cqgqtz1 = cqgqtz1 - cqysk_jz1;
		}
		if (cqgqtz2 != null && cqysk_jz2 != null) {
			cqgqtz2 = cqgqtz2 - cqysk_jz1;
		}
		balanceSheet.setCqgqtz1(cqgqtz1);
		balanceSheet.setCqgqtz2(cqgqtz2);
		// 投资性房地产
		Double tzxfdc1 = 0d;
		Double tzxfdc2 = 0d;
		tzxfdc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('投资性房地产') and belongLayer = 1",
						months);// 期初
		tzxfdc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('投资性房地产') and belongLayer = 1",
						months);// 期末
		balanceSheet.setTzxfdc1(tzxfdc1 == null ? 0 : tzxfdc1);
		balanceSheet.setTzxfdc2(tzxfdc2 == null ? 0 : tzxfdc2);
		// 固定资产原价
		Double gizcyj1 = 0d;
		Double gizcyj2 = 0d;
		gizcyj1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('固定资产') and belongLayer = 1",
						months);// 期初
		gizcyj2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('固定资产') and belongLayer = 1",
						months);// 期末
		balanceSheet.setGizcyj1(gizcyj1 == null ? 0 : gizcyj1);
		balanceSheet.setGizcyj2(gizcyj2 == null ? 0 : gizcyj2);
		// 累计折旧价
		Double zcljzj1 = 0d;
		Double zcljzj2 = 0d;
		zcljzj1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('累计折旧')",
						months);// 期初
		zcljzj2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('累计折旧')",
						months);// 期末
		balanceSheet.setZcljzj1(zcljzj1 == null ? 0 : zcljzj1);
		balanceSheet.setZcljzj2(zcljzj2 == null ? 0 : zcljzj2);
		// 固定资产净值
		balanceSheet.setGdzcjz1(balanceSheet.getGizcyj1()
				- balanceSheet.getZcljzj1());
		balanceSheet.setGdzcjz2(balanceSheet.getGizcyj2()
				- balanceSheet.getZcljzj2());
		// 固定资产减值准备
		Double gdzcjzzb1 = 0d;
		Double gdzcjzzb2 = 0d;
		gdzcjzzb1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('固定资产减值准备')",
						months);// 期初
		gdzcjzzb2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('固定资产减值准备')",
						months);// 期末
		balanceSheet.setGdzcjzzb1(gdzcjzzb1 == null ? 0 : gdzcjzzb1);
		balanceSheet.setGdzcjzzb2(gdzcjzzb2 == null ? 0 : gdzcjzzb2);
		// gdzcje1 固定资产净额
		balanceSheet.setGdzcje1(balanceSheet.getGdzcjz1()
				- balanceSheet.getGdzcjzzb1());
		balanceSheet.setGdzcje2(balanceSheet.getGdzcjz2()
				- balanceSheet.getGdzcjzzb2());
		// zjgc1在建工程
		Double zjgc1 = 0d;
		Double zjgc2 = 0d;
		zjgc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('在建工程')",
						months);// 期初
		zjgc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('在建工程')",
						months);// 期末
		balanceSheet.setZjgc1(zjgc1 == null ? 0 : zjgc1);
		balanceSheet.setZjgc2(zjgc2 == null ? 0 : zjgc2);
		// gchz1工程物资
		Double gchz1 = 0d;
		Double gchz2 = 0d;
		gchz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('工程物资')",
						months);// 期初
		gchz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('工程物资')",
						months);// 期末
		balanceSheet.setGchz1(gchz1 == null ? 0 : gchz1);
		balanceSheet.setGchz2(gchz2 == null ? 0 : gchz2);
		// gdzcql1 固定资产清理
		Double gdzcql1 = 0d;
		Double gdzcql2 = 0d;
		gdzcql1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('固定资产清理')",
						months);// 期初
		gdzcql2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('固定资产清理')",
						months);// 期末
		balanceSheet.setGdzcql1(gdzcql1 == null ? 0 : gdzcql1);
		balanceSheet.setGdzcql2(gdzcql2 == null ? 0 : gdzcql2);
		// wxzc1 无形资产
		Double wxzc1 = 0d;
		Double wxzc2 = 0d;
		wxzc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('无形资产') and belongLayer = 1",
						months);// 期初
		wxzc1 = wxzc1 == null ? 0d : wxzc1;
		wxzc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('无形资产') and belongLayer = 1",
						months);// 期末
		wxzc2 = wxzc2 == null ? 0d : wxzc2;
		// 无形资产减值准备
		Double wxzcjz_1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('无形资产减值准备')",
						months);// 期初
		wxzcjz_1 = wxzcjz_1 == null ? 0d : wxzcjz_1;
		Double wxzcjz_2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('无形资产减值准备')",
						months);// 期初
		wxzcjz_2 = wxzcjz_2 == null ? 0d : wxzcjz_2;
		// 累计摊销
		Double ljtx_1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('累计摊销')",
						months);// 期初
		ljtx_1 = ljtx_1 == null ? 0d : ljtx_1;
		Double ljtx_2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('累计摊销')",
						months);// 期初
		ljtx_2 = ljtx_2 == null ? 0d : ljtx_2;
		balanceSheet.setTdsyq1(wxzc1);
		balanceSheet.setTdsyq2(wxzc2);
		wxzc1 = wxzc1 - wxzcjz_1 - ljtx_1;
		wxzc2 = wxzc2 - wxzcjz_2 - ljtx_2;
		balanceSheet.setWxzc1(wxzc1);
		balanceSheet.setWxzc2(wxzc2);

		// kfzc1 开发支出
		Double kfzc1 = 0d;
		Double kfzc2 = 0d;
		kfzc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('开发支出') ",
						months);// 期初
		kfzc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('开发支出')",
						months);// 期末
		balanceSheet.setKfzc1(kfzc1 == null ? 0 : kfzc1);
		balanceSheet.setKfzc2(kfzc2 == null ? 0 : kfzc2);
		// 商誉shangyu1
		Double shangyu1 = 0d;
		Double shangyu2 = 0d;
		shangyu1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('开发支出')",
						months);// 期初
		shangyu2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('开发支出')",
						months);// 期末
		balanceSheet.setShangyu1(shangyu1 == null ? 0 : shangyu1);
		balanceSheet.setShangyu2(shangyu2 == null ? 0 : shangyu2);
		// cqdtfy1 长期待摊费用
		Double cqdtfy1 = 0d;
		Double cqdtfy2 = 0d;
		cqdtfy1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('长期待摊费用')",
						months);// 期初
		cqdtfy2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('长期待摊费用')",
						months);// 期末
		balanceSheet.setCqdtfy1(cqdtfy1 == null ? 0 : cqdtfy1);
		balanceSheet.setCqdtfy2(cqdtfy2 == null ? 0 : cqdtfy2);
		// dysdsc1 递延所得税资产
		Double dysdsc1 = 0d;
		Double dysdsc2 = 0d;
		dysdsc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('递延所得税资产')",
						months);// 期初
		dysdsc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('递延所得税资产')",
						months);// 期末
		balanceSheet.setDysdsc1(dysdsc1 == null ? 0 : dysdsc1);
		balanceSheet.setDysdsc2(dysdsc2 == null ? 0 : dysdsc2);
		// qtfldzc1 其他非流动资产
		Double qtfldzc1 = 0d;
		Double qtfldzc2 = 0d;
		qtfldzc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('其他非流动资产')",
						months);// 期初
		qtfldzc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他非流动资产')",
						months);// 期末
		balanceSheet.setQtfldzc1(qtfldzc1 == null ? 0 : qtfldzc1);
		balanceSheet.setQtfldzc2(qtfldzc2 == null ? 0 : qtfldzc2);
		// 特准储备物资 tzcbwz1
		Double tzcbwz1 = 0d;
		Double tzcbwz2 = 0d;
		tzcbwz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('特准储备物资')",
						months);// 期初
		tzcbwz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('特准储备物资')",
						months);// 期末
		balanceSheet.setTzcbwz1(tzcbwz1 == null ? 0 : tzcbwz1);
		balanceSheet.setTzcbwz2(tzcbwz2 == null ? 0 : tzcbwz2);
		// 非流动资产合计
		Double fldzchj1 = 0d;
		Double fldzchj2 = 0d;
		fldzchj1 = balanceSheet.getKgcsjrzc1() + balanceSheet.getCyzdqtz1()
				+ balanceSheet.getCqysk1() + balanceSheet.getCqgqtz1()
				+ balanceSheet.getTzxfdc1() + balanceSheet.getGdzcje1()
				+ balanceSheet.getZjgc1() + balanceSheet.getGchz1()
				+ balanceSheet.getGdzcql1() + balanceSheet.getWxzc1()
				+ balanceSheet.getKfzc1() + balanceSheet.getCqdtfy1()
				+ balanceSheet.getShangyu1() + balanceSheet.getDysdsc1()
				+ balanceSheet.getQtfldzc1() + balanceSheet.getQtfldzc1()
				+ balanceSheet.getTzcbwz1();
		fldzchj2 = balanceSheet.getKgcsjrzc2() + balanceSheet.getCyzdqtz2()
				+ balanceSheet.getCqysk2() + balanceSheet.getCqgqtz2()
				+ balanceSheet.getTzxfdc2() + balanceSheet.getGdzcje2()
				+ balanceSheet.getZjgc2() + balanceSheet.getGchz2()
				+ balanceSheet.getGdzcql2() + balanceSheet.getWxzc2()
				+ balanceSheet.getKfzc2() + balanceSheet.getCqdtfy2()
				+ balanceSheet.getShangyu2() + balanceSheet.getDysdsc2()
				+ balanceSheet.getQtfldzc2() + balanceSheet.getQtfldzc2()
				+ balanceSheet.getTzcbwz2();
		balanceSheet.setFldzchj1(fldzchj1);
		balanceSheet.setFldzchj2(fldzchj2);
		/********************************* 流动负债 ***********************************************/
		// dqjk1 短期借款
		Double dqjk1 = 0d;
		Double dqjk2 = 0d;
		dqjk1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('短期借款') and belongLayer = 1",
						months);// 期初
		dqjk2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('短期借款') and belongLayer = 1",
						months);// 期末
		balanceSheet.setDqjk1(dqjk1 == null ? 0 : dqjk1);
		balanceSheet.setDqjk2(dqjk2 == null ? 0 : dqjk2);
		// jyxjrfz1 交易性金融负债
		Double jyxjrfz1 = 0d;
		Double jyxjrfz2 = 0d;
		jyxjrfz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('交易性金融负债') and belongLayer = 1",
						months);// 期初
		jyxjrfz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('交易性金融负债') and belongLayer = 1",
						months);// 期末
		balanceSheet.setJyxjrfz1(jyxjrfz1 == null ? 0 : jyxjrfz1);
		balanceSheet.setJyxjrfz2(jyxjrfz2 == null ? 0 : jyxjrfz2);
		// yfpj1 应付票据
		Double yfpj1 = 0d;
		Double yfpj2 = 0d;
		Double yfpj_qichu = 0d;
		yfpj1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应付票据') and belongLayer = 1",
						months);// 年初
		yfpj_qichu = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowQichuMoney)-sum(lendQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('应付票据') and belongLayer = 1",
						months);// 期初
		yfpj2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应付票据') and belongLayer = 1",
						months);// 期末
		balanceSheet.setYfpj1(yfpj1 == null ? 0 : yfpj1);
		balanceSheet.setYfpj2(yfpj2 == null ? 0 : yfpj2);
		// yingfuZK1 应付账款
		Double yingfuZK1 = 0d;
		Double yingfuZK2 = 0d;
		Double yingfuZK_qichu = 0d;
		yingfuZK1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应付账款') and belongLayer = 1",
						months);// 年初
		yingfuZK_qichu = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('应付账款') and belongLayer = 1",
						months);// 期初
		yingfuZK2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应付账款') and belongLayer = 1",
						months);// 期末
		balanceSheet.setYingfuZK1(yingfuZK1 == null ? 0 : yingfuZK1);
		balanceSheet.setYingfuZK2(yingfuZK2 == null ? 0 : yingfuZK2);
		// yskx1预收款项
		Double yskx1 = 0d;
		Double yskx2 = 0d;
		Double yskx_qichu = 0d;
		yskx1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('预收账款') and belongLayer = 1",
						months);// 年初
		yskx_qichu = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('预收账款') and belongLayer = 1",
						months);// 期初
		yskx_qichu = yskx_qichu == null ? 0 : yskx_qichu;
		yskx2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('预收账款') and belongLayer = 1",
						months);// 期末
		Double yszk1_1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应收账款')",
						months);// 年初
		Double yszk_0 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('应收账款')",
						months);// 期初
		yszk_0 = yszk_0 == null ? 0 : yszk_0;
		Double yszk2_2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应收账款')",
						months);// 期末
		if (yskx1 != null && yingfuZK1 != null) {
			yskx1 += yszk1_1;
		}
		if (yskx2 != null && yingfuZK2 != null) {
			yskx2 += yszk2_2;
		}
		yskx_qichu = yskx_qichu + yszk_0;
		balanceSheet.setYskx1(yskx1 == null ? 0 : yskx1);
		balanceSheet.setYskx2(yskx2 == null ? 0 : yskx2);
		// yfzgxc1 应付职工薪酬 ***
		Double yfzgxc1 = 0d;
		Double yfzgxc2 = 0d;
		Double yfzgxc_qichu = 0d;
		yfzgxc1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(borrowYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('应付职工薪酬') and belongLayer = 1",
						months);// 年初
		yfzgxc_qichu = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowQichuMoney)-sum(lendQichuMoney)) from SubBudgetRate where bookKDate = ? and name in ('应付职工薪酬') and belongLayer = 1",
						months);// 期初
		yfzgxc2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('应付职工薪酬') and belongLayer = 1",
						months);// 期末
		balanceSheet.setYfzgxc1(yfzgxc1 == null ? 0 : yfzgxc1);
		balanceSheet.setYfzgxc2(yfzgxc2 == null ? 0 : yfzgxc2);
		// yjsf1 应交税费
		Double yjsf1 = 0d;
		Double yjsf2 = 0d;
		yjsf1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendYearBegingMoney)-sum(borrowYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应交税费') and belongLayer = 1",
						months);// 期初
		yjsf2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(lendJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应交税费') and belongLayer = 1",
						months);// 期末
		balanceSheet.setYjsf1(yjsf1 == null ? 0 : yjsf1);
		balanceSheet.setYjsf2(yjsf2 == null ? 0 : yjsf2);
		// yflx1 应付利息
		Double yflx1 = 0d;
		Double yflx2 = 0d;
		yflx1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应付利息') and belongLayer = 1",
						months);// 期初
		yflx2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应付利息') and belongLayer = 1",
						months);// 期末
		balanceSheet.setYflx1(yflx1 == null ? 0 : yflx1);
		balanceSheet.setYflx2(yflx2 == null ? 0 : yflx2);
		// 应付股利 yfgl1
		Double yfgl1 = 0d;
		Double yfgl2 = 0d;
		yfgl1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应付股利') and belongLayer = 1",
						months);// 期初
		yfgl2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应付股利') and belongLayer = 1",
						months);// 期末
		balanceSheet.setYfgl1(yfgl1 == null ? 0 : yfgl1);
		balanceSheet.setYfgl2(yfgl2 == null ? 0 : yfgl2);
		// 其他应付款 qtyfk1
		Double qtyfk1 = 0d;
		Double qtyfk2 = 0d;
		qtyfk1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('其他应付款') and belongLayer = 1",
						months);// 期初
		qtyfk2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('其他应付款') and belongLayer = 1",
						months);// 期末
		balanceSheet.setQtyfk1(qtyfk1 == null ? 0 : qtyfk1);
		balanceSheet.setQtyfk2(qtyfk2 == null ? 0 : qtyfk2);
		// 一年内到期的非流动负债 fldfzyears1 ****
		Double fldfzyears1 = 0d;
		Double fldfzyears2 = 0d;
		balanceSheet.setFldfzyears1(fldfzyears1);
		balanceSheet.setFldfzyears2(fldfzyears2);

		// 其他流动负债 qtldfz1
		Double qtldfz1 = 0d;
		Double qtldfz2 = 0d;
		qtldfz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('其他流动负债') and belongLayer = 1",
						months);// 期初
		qtldfz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他流动负债') and belongLayer = 1",
						months);// 期末
		balanceSheet.setQtldfz1(qtldfz1 == null ? 0 : qtldfz1);
		balanceSheet.setQtldfz2(qtldfz2 == null ? 0 : qtldfz2);
		// ldfzhj1 流动负债合计
		Double ldfzhj1 = 0d;
		Double ldfzhj2 = 0d;
		ldfzhj1 = balanceSheet.getDqjk1() + balanceSheet.getJyxjrfz1()
				+ balanceSheet.getYfpj1() + balanceSheet.getYszk1()
				+ balanceSheet.getYfzgxc1() + balanceSheet.getYjsf1()
				+ balanceSheet.getYflx1() + balanceSheet.getYfgl1()
				+ balanceSheet.getQtyfk1() + fldfzyears1
				+ balanceSheet.getQtldfz1();
		ldfzhj2 = balanceSheet.getDqjk2() + balanceSheet.getJyxjrfz2()
				+ balanceSheet.getYfpj2() + balanceSheet.getYszk2()
				+ balanceSheet.getYfzgxc2() + balanceSheet.getYjsf2()
				+ balanceSheet.getYflx2() + balanceSheet.getYfgl2()
				+ balanceSheet.getQtyfk2() + fldfzyears1
				+ balanceSheet.getQtldfz2();
		balanceSheet.setLdfzhj1(ldfzhj1 == null ? 0 : ldfzhj1);
		balanceSheet.setLdfzhj2(ldfzhj2 == null ? 0 : ldfzhj2);
		/*************************** 非流动负债 ***************************************/
		// cqjk1 长期借款 *****
		Double cqjk1 = 0d;
		Double cqjk2 = 0d;
		cqjk1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('长期借款') and belongLayer = 1",
						months);// 期初
		cqjk2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('长期借款') and belongLayer = 1",
						months);// 期末
		balanceSheet.setCqjk1(cqjk1 == null ? 0 : cqjk1);
		balanceSheet.setCqjk2(cqjk2 == null ? 0 : cqjk2);
		// yfzq1 应付债券 *****
		Double yfzq1 = 0d;
		Double yfzq2 = 0d;
		yfzq1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('应付债券') and belongLayer = 1",
						months);// 期初
		yfzq2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应付债券') and belongLayer = 1",
						months);// 期末
		balanceSheet.setYfzq1(yfzq1 == null ? 0 : yfzq1);
		balanceSheet.setYfzq2(yfzq2 == null ? 0 : yfzq2);
		// cqyfk1 长期应付款
		Double cqyfk1 = 0d;
		Double cqyfk2 = 0d;
		cqyfk1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('长期应付款') and belongLayer = 1",
						months);// 期初
		cqyfk2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('长期应付款') and belongLayer = 1",
						months);// 期末
		balanceSheet.setCqyfk1(cqyfk1 == null ? 0 : cqyfk1);
		balanceSheet.setCqyfk2(cqyfk2 == null ? 0 : cqyfk2);
		// dysdsfz1 递延所得税负债
		Double dysdsfz1 = 0d;
		Double dysdsfz2 = 0d;
		dysdsfz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('递延所得税负债') and belongLayer = 1",
						months);// 期初
		dysdsfz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('递延所得税负债') and belongLayer = 1",
						months);// 期末
		balanceSheet.setDysdsfz1(dysdsfz1 == null ? 0 : dysdsfz1);
		balanceSheet.setDysdsfz2(dysdsfz2 == null ? 0 : dysdsfz2);
		// qtfldfz1 其他非流动负债
		Double qtfldfz1 = 0d;
		Double qtfldfz2 = 0d;
		qtfldfz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('其他非流动负债') and belongLayer = 1",
						months);// 期初
		qtfldfz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他非流动负债') and belongLayer = 1",
						months);// 期末
		balanceSheet.setQtfldfz1(dysdsfz1 == null ? 0 : dysdsfz1);
		balanceSheet.setQtfldfz2(dysdsfz2 == null ? 0 : dysdsfz2);
		// fldfzhj1 非流动负债合计
		Double fldfzhj1 = 0d;
		Double fldfzhj2 = 0d;
		fldfzhj1 = balanceSheet.getCqjk1() + balanceSheet.getYfzq1()
				+ balanceSheet.getCqyfk1() + balanceSheet.getDysdsfz1()
				+ balanceSheet.getQtfldfz1();
		fldfzhj2 = balanceSheet.getCqjk2() + balanceSheet.getYfzq2()
				+ balanceSheet.getCqyfk2() + balanceSheet.getDysdsfz2()
				+ balanceSheet.getQtfldfz2();
		balanceSheet.setFldfzhj1(fldfzhj1);
		balanceSheet.setFldfzhj2(fldfzhj2);
		// 负债合计 fzhj1
		Double fzhj1 = 0d;
		Double fzhj2 = 0d;
		fzhj1 = balanceSheet.getLdfzhj1() + balanceSheet.getFldfzhj1();
		fzhj2 = balanceSheet.getLdfzhj2() + balanceSheet.getFldfzhj2();
		balanceSheet.setFldfzhj1(fzhj1);
		balanceSheet.setFldfzhj2(fzhj2);
		/*************************** 所有者权益 *********************************************/
		// 实收资本 sszb1
		Double sszb1 = 0d;
		Double sszb2 = 0d;
		sszb1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowYearBegingMoney)-sum(lendYearBegingMoney)) from SubBudgetRate where bookKDate = ? and name in ('实收资本(或股本)')",
						months);// 期初
		sszb2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where bookKDate = ? and name in ('实收资本(或股本)')",
						months);// 期末
		balanceSheet.setSszb1(sszb1 == null ? 0 : sszb1);
		balanceSheet.setSszb2(sszb2 == null ? 0 : sszb2);
		// 资本公积 sszb1
		Double zbgj1 = 0d;
		Double zbgj2 = 0d;
		zbgj1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('资本公积')",
						months);// 期初
		zbgj2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('资本公积')",
						months);// 期末
		balanceSheet.setZbgj1(zbgj1 == null ? 0 : zbgj1);
		balanceSheet.setZbgj2(zbgj2 == null ? 0 : zbgj2);
		// 盈余公积 sszb1
		Double yygj1 = 0d;
		Double yygj2 = 0d;
		yygj1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('盈余公积')",
						months);// 期初
		yygj2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('盈余公积')",
						months);// 期末
		balanceSheet.setYygj1(yygj1 == null ? 0 : yygj1);
		balanceSheet.setYygj2(yygj2 == null ? 0 : yygj2);
		// wfplr1 未分配利润
		Double wfplr1 = 0d;
		Double wfplr2 = 0d;
		wfplr1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('利润分配') and belongLayer =1 ",
						months);// 期初
		wfplr1 = wfplr1 == null ? 0 : wfplr1;
		Double bnlrl = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowYearBegingMoney)-sum(lendYearBegingMoney) from SubBudgetRate where bookKDate = ? and name in ('本年利润')  and belongLayer =1",
						months);// 期初
		bnlrl = bnlrl == null ? 0 : bnlrl;
		Double bnlr2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('本年利润')  and belongLayer =1",
						months);// 期末
		bnlr2 = bnlr2 == null ? 0 : bnlr2;
		wfplr1 = bnlrl - wfplr1;
		wfplr2 = wfplr1 + (-bnlr2);
		balanceSheet.setWfplr1(wfplr1 == null ? 0 : wfplr1);
		balanceSheet.setWfplr2(wfplr2 == null ? 0 : wfplr2);
		// fzandsyzqyhj1 所有者权益合计
		Double fzandsyzqyhj1 = 0d;
		Double fzandsyzqyhj2 = 0d;
		fzandsyzqyhj1 = balanceSheet.getSszb1() + balanceSheet.getZbgj1()
				+ balanceSheet.getYygj1() + balanceSheet.getWfplr1();
		fzandsyzqyhj2 = balanceSheet.getSszb2() + balanceSheet.getZbgj2()
				+ balanceSheet.getYygj2() + balanceSheet.getWfplr2();
		balanceSheet
				.setFzandsyzqyhj1(fzandsyzqyhj1 == null ? 0 : fzandsyzqyhj1);
		balanceSheet
				.setFzandsyzqyhj2(fzandsyzqyhj2 == null ? 0 : fzandsyzqyhj2);
		totalDao.save(balanceSheet);
		/**
		 * 利润表
		 */
		ProfitSheet profitSheet = new ProfitSheet();
		profitSheet.setMonths(months);
		// 营业收入 ywsr
		Double ywsr = 0d;
		// 主营业务收入 zyywsr
		Double zyywsr = 0d;
		zyywsr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('主营业务收入') and belongLayer = 1",
						months);
		profitSheet.setZyywsr(zyywsr == null ? 0 : zyywsr);
		// 其他业务收入 qtywsr
		Double qtywsr = 0d;
		qtywsr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('其他业务收入 ') and belongLayer = 1",
						months);
		profitSheet.setQtywsr(qtywsr == null ? 0 : qtywsr);
		// 营业成本 ywcb
		Double ywcb = 0d;
		ywcb = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('主营业务成本','其他业务成本') and belongLayer = 1",
						months);
		profitSheet.setYwcb(ywcb == null ? 0 : ywcb);
		// 主营业务成本 zyywcb
		Double zyywcb = 0d;
		zyywcb = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('主营业务成本') and belongLayer = 1",
						months);
		profitSheet.setZyywcb(zyywcb == null ? 0 : zyywcb);
		// 其他业务成本 zyywcb
		Double qtywcb = 0d;
		qtywcb = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('其他业务成本') and belongLayer = 1",
						months);
		profitSheet.setQtywcb(qtywcb == null ? 0 : qtywcb);
		// ywsjandfj 营业税及附加
		Double ywsjandfj = 0d;
		ywsjandfj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('营业税金及附加') and belongLayer = 1",
						months);
		profitSheet.setYwsjandfj(ywsjandfj == null ? 0 : ywsjandfj);
		// 销售费用 xsfy
		Double xsfy = 0d;
		xsfy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('销售费用') and belongLayer = 1",
						months);
		profitSheet.setXsfy(xsfy == null ? 0 : xsfy);
		// 管理费用 glfy
		Double glfy = 0d;
		glfy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('管理费用') and belongLayer = 1",
						months);
		profitSheet.setGlfy(glfy == null ? 0 : glfy);
		// 业务招待费 ywzdf
		Double ywzdf = 0d;
		ywzdf = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('业务招待费') ",
						months);
		profitSheet.setYwzdf(ywzdf == null ? 0 : ywzdf);
		// 研究与开发费 yjykff
		Double yjykff = 0d;
		yjykff = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('研究与开发费 ') ",
						months);
		profitSheet.setYjykff(yjykff == null ? 0 : yjykff);
		// 财务费用 cwfy
		Double cwfy = 0d;
		cwfy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('财务费用') and belongLayer = 1",
						months);
		profitSheet.setCwfy(cwfy == null ? 0 : cwfy);
		// zcjzss 资产减值损失
		Double zcjzss = 0d;
		zcjzss = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('资产减值损失') and belongLayer = 1",
						months);
		profitSheet.setZcjzss(zcjzss == null ? 0 : zcjzss);
		// 公允价值变动损益 gyzbdsy
		Double gyzbdsy = 0d;
		gyzbdsy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('公允价值变动损益') and belongLayer = 1",
						months);
		profitSheet.setGyzbdsy(gyzbdsy == null ? 0 : gyzbdsy);
		// 投资损益 tzsy
		Double tzsy = 0d;
		tzsy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('投资损益') and belongLayer = 1",
						months);
		profitSheet.setTzsy(tzsy == null ? 0 : tzsy);
		ywsr = zyywsr + qtywsr;
		profitSheet.setYwsr(ywsr);
		// 营业外收入 ywsr1
		Double ywsr1 = 0d;
		ywsr1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('营业外收入') and belongLayer = 1",
						months);
		profitSheet.setYwsr1(ywsr1 == null ? 0 : ywsr1);
		// 营业外支出 ywzc
		Double ywzc = 0d;
		ywzc = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('营业外支出') and belongLayer = 1",
						months);
		profitSheet.setYwzc(ywzc == null ? 0 : ywzc);
		// ywlr；营业利润
		Double ywlr = profitSheet.getYwsr() - profitSheet.getYwcb()
				- profitSheet.getYwsjandfj() - profitSheet.getXsfy()
				- profitSheet.getGlfy() - profitSheet.getCwfy()
				+ profitSheet.getGyzbdsy() + profitSheet.getTzsy();
		profitSheet.setYwlr(ywlr);

		// sdsfy 所得税费用
		Double sdsfy = 0d;
		sdsfy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowMoney) from SubBudgetRate where bookKDate = ? and name in ('所得税费用') and belongLayer = 1",
						months);
		profitSheet.setSdsfy(sdsfy == null ? 0 : sdsfy);
		// 利润总额
		Double lrze = 0d;
		lrze = ywlr + profitSheet.getYwsr1() - profitSheet.getYwzc();
		profitSheet.setLrze(lrze);
		// 净利润;
		Double jlr = 0d;
		jlr = lrze - profitSheet.getSdsfy();
		profitSheet.setJlr(jlr);
		totalDao.save(profitSheet);
		/**
		 * 现金流动表
		 */
		CashFlow cashFlow = new CashFlow();
		cashFlow.setMonths(months);
		/******************* 一、经营活动产生的现金流量： ********************************/
		/* 1 销售商品、提供劳务收到的现金 jymoneyIn1 */
		Double jymoneyIn1 = 0d;
		// 营业收入；

		// 应交税费[应交增值税（销项税额）]
		Double xssr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name = '应交增值税' ) and name = '销项税额' ",
						months);
		xssr = xssr == null ? 0 : xssr;
		// 坏账准备
		Double hzzb = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('坏账准备')",
						months);//
		hzzb = hzzb == null ? 0 : hzzb;

		yspj_qichu = yspj_qichu == null ? 0 : yspj_qichu;
		yspj2 = yspj2 == null ? 0 : yspj2;
		yszk2 = yszk2 == null ? 0 : yszk2;
		yskx2 = yskx2 == null ? 0 : yskx2;
		jymoneyIn1 = ywsr + xssr + (yspj_qichu - ysgl2) + (yszk_qichu - yszk2)
				+ (yskx2 - yskx_qichu) - hzzb;
		cashFlow.setJymoneyIn1(jymoneyIn1);
		/* 2.收到的税费返还 */
		Double jymoneyIn2 = 0d;
		/* 3.收到的其他与营业活动有关的现金 */
		Double jymoneyIn3 = 0d;
		// 罚款收入
		Double fksr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('罚款收入')",
						months);
		fksr = fksr == null ? 0 : fksr;
		// 接受捐赠现金收入
		Double qzxjsr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('接受捐赠现金收入')",
						months);
		qzxjsr = qzxjsr == null ? 0 : qzxjsr;
		// 经营租赁固定资产收到的现金
		Double gdzcxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('经营租赁固定资产收到的现金')",
						months);
		gdzcxj = gdzcxj == null ? 0 : gdzcxj;
		// 投资性房地产收到的租金收入
		Double fdczj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('投资性房地产收到的租金收入')",
						months);
		fdczj = fdczj == null ? 0 : fdczj;
		// 出租和出借包装物的租金收入
		Double czzjsr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('出租和出借包装物的租金收入')",
						months);
		czzjsr = czzjsr == null ? 0 : czzjsr;
		// 逾期未退还出租和出借包装物没收的押金收入
		Double yqsr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('逾期未退还出租和出借包装物没收的押金收入')",
						months);
		yqsr = yqsr == null ? 0 : yqsr;
		// 流动资产损失中由个人赔偿的现金收入
		Double ldzcxjsr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('流动资产损失中由个人赔偿的现金收入')",
						months);
		ldzcxjsr = ldzcxjsr == null ? 0 : ldzcxjsr;
		// 政府补助收入
		Double zfbzsr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('政府补助收入')",
						months);
		jymoneyIn3 = fksr + qzxjsr + gdzcxj + gdzcxj + fdczj + czzjsr + yqsr
				+ yqsr + ldzcxjsr + zfbzsr;
		cashFlow.setJymoneyIn3(jymoneyIn3);
		/* 现金流入小计 */
		Double jymoneyInSum = jymoneyIn1 + jymoneyIn2 + jymoneyIn3;
		cashFlow.setJymoneyInSum(jymoneyInSum);
		/* 4.购买商品、接受劳务支付的现金 */
		Double jymoneyOut1 = 0d;
		// 营业成本
		Double yecb0 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('营业成本')",
						months);
		yecb0 = yecb0 == null ? 0 : yecb0;
		// 应交税费[应交增值税（进项税额）]
		Double jxse = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name = '应交增值税' ) and name = '进项税额' ",
						months);
		jxse = jxse == null ? 0 : jxse;
		ch1 = ch1 == null ? 0 : ch1;
		ch2 = ch2 == null ? 0 : ch2;
		yfzk1 = yfzk1 == null ? 0 : yfzk1;
		yfzk2 = yfzk2 == null ? 0 : yfzk2;
		yfpj2 = yfpj2 == null ? 0 : yfpj2;
		// 当期列入生产成本、制造费用的职工薪酬
		Double gz = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name = '制造费用' ) and name = '工资' ",
						months);
		gz = gz == null ? 0 : gz;
		// 当期列入生产成本、制造费用的折旧费
		Double zjf = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name = '制造费用' ) and name = '折旧费' ",
						months);
		zjf = zjf == null ? 0 : zjf;
		jymoneyOut1 = yecb0 + jxse + (ch2 - ch_qichu)
				+ (yingfuZK_qichu - yingfuZK2) + (yfpj_qichu - yfpj2)
				+ (yfzk2 - yfzk_qichu) - gz - zjf;
		cashFlow.setJymoneyOut1(jymoneyOut1);
		/* 5.支付给职工以及为职工支付的现金 */
		Double jymoneyOut2 = 0d;
		// 生产成本、制造费用、管理费用中职工薪酬
		Double zgxc = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('制造费用','管理费用','生产成本') ) and name = '工资' ",
						months);
		zgxc = zgxc == null ? 0 : zgxc;
		yfzgxc_qichu = yfzgxc_qichu == null ? 0 : yfzgxc_qichu;
		yfzgxc2 = yfzgxc2 == null ? 0 : yfzgxc2;
		// 应付职工薪酬（在建工程）
		Double zjgc_gz1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select ABS(sum(borrowQichuMoney)-sum(lendQichuMoney)) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('在建工程') ) and name = '工资' ",
						months);
		zjgc_gz1 = zjgc_gz1 == null ? 0 : zjgc_gz1;
		Double zjgc1_gz2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select  ABS(sum(borrowJieyuMoney)-sum(lendJieyuMoney)) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('在建工程') ) and name = '工资' ",
						months);
		zjgc1_gz2 = zjgc1_gz2 == null ? 0 : zjgc1_gz2;
		jymoneyOut2 = zgxc + (yfzgxc_qichu - yfzgxc2) - zjgc_gz1 - zjgc1_gz2;
		cashFlow.setJymoneyOut2(jymoneyOut2);
		/* 6.支付的各项税费 */
		Double jymoneyOut3 = 0d;
		sdsfy = sdsfy == null ? 0 : sdsfy;
		// 应交所得税
		Double yjsds1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowQichuMoney)-sum(lendQichuMoney) from SubBudgetRate where bookKDate = ? and name in ('应交所得税')",
						months);
		yjsds1 = yjsds1 == null ? 0 : yjsds1;
		Double yjsds2 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应交所得税')",
						months);
		yjsds2 = yjsds2 == null ? 0 : yjsds2;
		// 支付的营业税金及附加
		Double yysj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('应交所得税')",
						months);
		yysj = yysj == null ? 0 : yysj;
		yjsf2 = yjsf1 == null ? 0 : yjsf1;
		// 应交增值税(已交税金)
		Double yjsj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('应交增值税') ) and name = '已交税金' ",
						months);
		yjsj = yjsj == null ? 0 : yjsj;
		jymoneyOut3 = sdsfy + (yjsds1 - yjsds2) + yysj + yjsf2 - yjsj;
		cashFlow.setJymoneyOut3(jymoneyOut3);
		/* 7.支付的其他与经营活动有关的现金 */
		Double jymoneyOut4 = 0d;
		// 支付其他管理费用
		Double qtglfy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('管理费用') ) and name = '其他' ",
						months);
		qtglfy = qtglfy == null ? 0 : qtglfy;
		// 支付其他销售费用
		Double qtxsfy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('销售费用') ) and name = '其他' ",
						months);
		qtxsfy = qtxsfy == null ? 0 : qtxsfy;
		// 支付其他制造费用
		Double qtzzfy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('制造费用') ) and name = '其他' ",
						months);
		qtzzfy = qtzzfy == null ? 0 : qtzzfy;
		// 进行捐赠的现金支出
		Double jzxjzc = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('捐赠支出') ) and name = '现金' ",
						months);
		jzxjzc = jzxjzc == null ? 0 : jzxjzc;
		// 罚款支出
		Double fkzc = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('罚款支出')",
						months);
		fkzc = fkzc == null ? 0 : fkzc;
		jymoneyOut4 = qtglfy + qtxsfy + qtzzfy + jzxjzc + fkzc;
		/* 现金流出小计 */
		Double jymoneyOutSum = jymoneyOut4 + jymoneyOut3 + jymoneyOut2
				+ jymoneyOut1;
		cashFlow.setJymoneyOutSum(jymoneyOutSum);
		/* 经营活动产生的现金流量净额 */
		Double jymoneySum = jymoneyInSum - jymoneyOutSum;
		cashFlow.setJymoneySum(jymoneySum);
		/******************** 二、投资活动产生的现金流量： ********************************/
		/* 8.收回投资所收到的现金 */
		Double tzmoneyIn1 = 0d;
		tzmoneyIn1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('可供出售金融资产','长期股权投资','交易性金融资产') ) and name = '现金收入' ",
						months);
		cashFlow.setTzmoneyIn1(tzmoneyIn1 == null ? 0 : tzmoneyIn1);
		/* 9.取得投资收益所收到的现金 */
		Double tzmoneyIn2 = 0d;
		/* 10.处置固定资产、无形资产和其他长期资产所收回的现金净额 */
		Double tzmoneyIn3 = 0d;
		// 处置固定资产、无形资产和其他长期资产所收回的现金
		Double shxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('处置固定资产净收益','处置无须资产净收益','处置其他长期投资净收益')",
						months);
		shxj = shxj == null ? 0 : shxj;
		// 处置固定资产、无形资产和其他长期资产所支出的现金
		Double zcxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('处置固定资产净损失','处置无须资产净损失','处置其他长期投资净损失')",
						months);
		zcxj = zcxj == null ? 0 : zcxj;
		tzmoneyIn3 = shxj - zcxj;
		cashFlow.setTzmoneyIn3(tzmoneyIn3);
		/* 11.处置子公司及其他营业单位收到的现金净额 */
		Double tzmoneyIn4 = 0d;
		// 处置子公司及其他营业单位所得的现金
		Double zgsxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('处置子公司及其他营业单位所得的现金')",
						months);
		zgsxj = zgsxj == null ? 0 : zgsxj;
		// 处置子公司及其他营业单位所得的现金 相关处置费用
		Double zgsczfy = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('处置子公司及其他营业单位所得的现金') ) and name = '处置费用' ",
						months);
		zgsczfy = zgsczfy == null ? 0 : zgsczfy;
		// 子公司及其他营业单位持有的现金和现金等价物
		Double zgsxjdjw = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('子公司及其他营业单位持有的现金和现金等价物')",
						months);
		zgsxjdjw = zgsxjdjw == null ? 0 : zgsxjdjw;
		tzmoneyIn4 = zgsxj - zgsczfy - zgsxjdjw;
		cashFlow.setTzmoneyIn4(tzmoneyIn4);
		/* 12.收到的其他与投资活动有关的现金 */
		Double tzmoneyIn5 = 0d;
		tzmoneyIn5 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他与投资活动有关的现金流入')",
						months);
		cashFlow.setTzmoneyIn5(tzmoneyIn5);
		/* tzmoneyInSum 现金流入小计 */
		Double tzmoneyInSum = 0d;
		tzmoneyInSum = (tzmoneyIn5 == null ? 0 : tzmoneyIn5) + tzmoneyIn4
				+ tzmoneyIn3 + tzmoneyIn2 + tzmoneyIn1;
		cashFlow.setTzmoneyInSum(tzmoneyInSum);
		/* 13.购建固定资产、无形资产和其他长期资产所支付的现金 tzmoneyOut1 */
		Double tzmoneyOut1 = 0d;
		// 企业本期购买、建造固定资产、取得无形资产和其他长期资产的价款
		Double jiakuan = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('购建固定资产支付现金','购建无形资产支付现金','购建其他长期投资支付现金')",
						months);
		jiakuan = jiakuan == null ? 0 : jiakuan;
		// 税费
		Double suifei = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('税费')",
						months);
		suifei = jiakuan == null ? 0 : jiakuan;
		// 现金支付的应由在建工程和无形资产负担的职工薪酬
		Double zjgczgxc = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(lendJieyuMoney) from SubBudgetRate where subBudgetRate.id in ("
								+ " select id from SubBudgetRate where  bookKDate = ? and name in ('在建工程','无形资产') ) and name = '工资' ",
						months);
		zjgczgxc = zjgczgxc == null ? 0 : zjgczgxc;
		tzmoneyOut1 = jiakuan + suifei + zjgczgxc;
		cashFlow.setTzmoneyOut1(tzmoneyOut1);
		/* 14.投资所支付的现金 */
		Double tzmoneyOut2 = 0d;
		// 取得除现金等价物以外的交易性金融资产、持有至到期投资、可供出售金融资产、长期股权投资的现金
		Double tzzfxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('投资支付现金')",
						months);
		tzzfxj = tzzfxj == null ? 0 : tzzfxj;
		// 支付给券商的佣金、手续费等附加费用
		Double yjfjf = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('支付给券商的佣金、手续费等附加费用')",
						months);
		yjfjf = yjfjf == null ? 0 : yjfjf;
		tzmoneyOut2 = tzzfxj + yjfjf;
		cashFlow.setTzmoneyOut2(tzmoneyOut2);
		/* 15.取得子公司及其他营业单位支付的现金净额 */
		Double tzmoneyOut3 = 0d;
		// 企业购买子公司及其他营业单位支出的现金
		Double zgszcxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('购买子公司及其他营业单位支出的现金')",
						months);
		// 子公司及其他营业单位持有的现金和现金等价物
		Double xjdjw = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('子公司及其他营业单位持有的现金和现金等价物')",
						months);
		tzmoneyOut3 = zgszcxj - xjdjw;
		cashFlow.setTzmoneyOut3(tzmoneyOut3);
		/* 16.支付的其他与投资活动有关的现金 */
		Double tzmoneyOut4 = 0d;
		tzmoneyOut4 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他投资活动现金支出')",
						months);
		cashFlow.setTzmoneyOut4(tzmoneyOut4);
		/* 现金流出小计 */
		Double tzmoneyOutSum = 0d;
		tzmoneyOutSum = tzmoneyOut4 + tzmoneyOut3 + tzmoneyOut2 + tzmoneyOut1;
		cashFlow.setTzmoneyOutSum(tzmoneyOutSum);
		/* 投资活动产生的现金流量净额 */
		Double tzmoneySum = 0d;
		tzmoneySum = tzmoneyInSum - tzmoneyOutSum;
		cashFlow.setTzmoneySum(tzmoneySum);
		/************************** 筹资活动产生的现金流量： *******************************************************/
		/* 17.吸收投资收到的现金 */
		Double czmoneyIn1 = 0d;
		// 发起人投入的现金
		Double fqrtrdxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('发起人投入的现金')",
						months);
		fqrtrdxj = fqrtrdxj == null ? 0 : fqrtrdxj;
		// 已发行股票方式筹集的资金实际收到的股款净额；
		Double gkje = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('已发行股票方式筹集的资金实际收到的股款净额')",
						months);
		gkje = gkje == null ? 0 : gkje;
		czmoneyIn1 = czmoneyIn1 + gkje;
		cashFlow.setCzmoneyIn1(czmoneyIn1);
		/* 18.借款所收到的现金 */
		Double czmoneyIn2 = 0d;
		// 企业举借各种短期、长期借款而收到的现金
		Double jkxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('短期借款现金','长期借款现金')",
						months);
		jkxj = jkxj == null ? 0 : jkxj;
		// 发行债券收入
		Double zqsr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('发行债券收入')",
						months);
		zqsr = zqsr == null ? 0 : zqsr;
		// 委托其他单位发行债券所支付的佣金等发行费用
		Double qtdwfxzq = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('委托其他单位发行债券所支付的佣金等发行费用')",
						months);
		qtdwfxzq = qtdwfxzq == null ? 0 : qtdwfxzq;
		czmoneyIn2 = jkxj + zqsr - qtdwfxzq;
		cashFlow.setCzmoneyIn2(czmoneyIn2);
		/* 19.收到的其他与筹资活动有关的现金 */
		Double czmoneyIn3 = 0d;
		czmoneyIn3 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他与筹资活动有关的现金流入')",
						months);
		cashFlow.setCzmoneyIn3(czmoneyIn3);
		czmoneyIn3 = czmoneyIn3 == null ? 0 : czmoneyIn3;
		/* 现金流入小计 */
		Double czmoneyInSum = 0d;
		czmoneyInSum = czmoneyIn3 + czmoneyIn2 + czmoneyIn1;
		cashFlow.setCzmoneyInSum(czmoneyInSum);
		/* 20.czmoneyOut1 偿还债务所支付的现金 */
		Double czmoneyOut1 = 0d;
		czmoneyOut1 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('偿还银行借款本金','偿还金融机构借款本金','偿还债券本金')",
						months);
		cashFlow.setCzmoneyIn1(czmoneyIn1);
		czmoneyOut1 = czmoneyOut1 == null ? 0 : czmoneyOut1;
		/* 21.分配股利、利润或偿付利息所支付的现金 */
		Double czmoneyOut2 = 0d;
		// 企业实际支付的现金股利
		Double sjzfxjgl = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('实际支付的现金股利')",
						months);
		sjzfxjgl = sjzfxjgl == null ? 0 : sjzfxjgl;
		// 支付给其他投资单位的利润
		Double tzdwlr = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('支付给其他投资单位的利润')",
						months);
		tzdwlr = tzdwlr == null ? 0 : tzdwlr;
		// 用现金支付的借款利息
		Double jklx = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('用现金支付的借款利息')",
						months);
		jklx = jklx == null ? 0 : jklx;
		// 支付的债券利息
		Double zqlx = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('支付的债券利息')",
						months);
		zqlx = zqlx == null ? 0 : zqlx;
		czmoneyOut2 = sjzfxjgl + tzdwlr + jklx + zqlx;
		cashFlow.setCzmoneyOut2(czmoneyOut2);
		/* 22.支付的其他与筹资活动有关的现 */
		Double czmoneyOut3 = 0d;
		czmoneyOut3 = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他与筹资活动有关的现金流出')",
						months);
		czmoneyOut3 = czmoneyOut3 == null ? 0 : czmoneyOut3;
		cashFlow.setCzmoneyOut3(czmoneyOut3);
		/* 现金流出小计 */
		Double czmoneyOutSum = 0d;
		czmoneyOutSum = czmoneyOut3 + czmoneyOut2 + czmoneyOut1;
		cashFlow.setCzmoneyOutSum(czmoneyOutSum);
		/* 筹资活动产生的现金流量净额 */
		Double czmoneySum = 0d;
		czmoneySum = czmoneyInSum - czmoneyOutSum;
		cashFlow.setCzmoneySum(czmoneySum);
		/************************** 四、汇率变动对现金的影响： *******************************************************/
		/* 23.汇率变动对现金的影响 */
		Double hlyx = 0d;
		// 收入的外币现金
		Double wbxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('其他与筹资活动有关的现金流出')",
						months);
		wbxj = wbxj == null ? 0 : wbxj;
		// 期末汇率
		Double qmhl = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('期末汇率')",
						months);
		qmhl = qmhl == null ? 0 : qmhl;
		// 记账汇率
		Double jzhl = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('记账汇率')",
						months);
		jzhl = jzhl == null ? 0 : jzhl;
		// 支付的外币现金
		Double zfwbxj = (Double) totalDao
				.getObjectByConditionforDouble(
						"select sum(borrowJieyuMoney)-sum(borrowJieyuMoney) from SubBudgetRate where bookKDate = ? and name in ('支付的外币现金')",
						months);
		zfwbxj = zfwbxj == null ? 0 : zfwbxj;
		hlyx = wbxj * (qmhl - jzhl) - zfwbxj * (qmhl - jzhl);
		cashFlow.setHlyx(hlyx);
		totalDao.save(cashFlow);
		int count = totalDao
				.getCount(" from CodeTranslation where keyCode = '久其报表'");
		if (count > 0) {
			/**
			 * 应上交应弥补款项表
			 */
			Ysjymbkxb ysjymbkxb = new Ysjymbkxb();
			/**
			 * 劳资报表
			 */
			/**
			 * 基本情况表
			 */
			BasicSituation bs = new BasicSituation();
			bs.setCompanyNmae(Util.getLoginCompanyInfo().getName());// 单位名称
			Integer cyry = totalDao
					.getCount(" from Users where onWork in('实习','试用','在职')  and internal = '是'");
			bs.setCyry(cyry);// 从业人员数
			Integer zg = totalDao
					.getCount(" from Users where onWork in ('实习','试用','在职') and internal = '是' and userNature ='在岗'");
			bs.setZg(zg);// 在岗员工数
			Integer lwpq = totalDao
					.getCount(" from Users where onWork in ('实习','试用','在职') and internal = '是' and userNature ='劳务派遣'");
			bs.setLwpq(lwpq);// 劳务派遣人员数
			Integer qtcyry = totalDao
					.getCount(" from Users where onWork in ('实习','试用','在职') and internal = '是' and userNature not in ('在岗','劳务派遣')");
			bs.setQtcyry(qtcyry);// 其他从业人员数
			Integer pyltx = totalDao
					.getCount(" from Users where onWork in ('实习','试用','在职') and internal = '是' and userNature ='离退休人员' ");
			bs.setPyltx(pyltx);// 聘用的离退休人员
			Integer pywjgat = totalDao
					.getCount(" from Users where onWork in ('实习','试用','在职') and internal = '是' and userNature ='外籍、港澳台人员' ");
			bs.setPywjgat(pywjgat);// 外籍、港澳台人员
			Integer jz = totalDao
					.getCount(" from Users where onWork in ('实习','试用','在职') and internal = '是' and userNature ='非全日制、兼职、第二职业者' ");
			Integer lgblldgx = 0;
			bs.setLgblldgx(lgblldgx);// /离岗仍保留劳动关系的人员
			Integer nbtyey = totalDao
					.getCount(" from Users where onWork = '内退' and internal = '是' ");
			bs.setNbtyey(nbtyey);// 内部退养人员
			Integer lxry = totalDao
					.getCount(" from Users where onWork = '离休' and internal = '是' ");
			bs.setLxry(lxry);// 离休人员
			Integer txry = totalDao
					.getCount(" from Users where onWork = '退休' and internal = '是' ");
			bs.setTxry(txry);// 退休人员

			/**
			 * 管理费用明细表
			 */
			Management ma = new Management();
			Management oldma = (Management) totalDao
					.getObjectByConditionforDouble(
							" from Management where months = ?", lastyearmoths);
			if (oldma != null) {
				ma.setGlfy3(oldma.getGlfy1());
				ma.setZgxc3(oldma.getZgxc1());
				ma.setZgxc3(oldma.getGz1());
				ma.setBxf3(oldma.getBxf1());
				ma.setZjf3(oldma.getZjf1());
				ma.setXlf3(oldma.getXlf1());
				ma.setWxzctx1(oldma.getWxzctx1());
				ma.setChpk3(oldma.getChpk1());
				ma.setYwzdf3(oldma.getYwzdf3());
				ma.setYsf3(oldma.getYsf3());
				ma.setSdf3(oldma.getSdf1());
				ma.setQnf3(oldma.getQnf1());
				ma.setLtxqnf3(oldma.getLtxqnf1());
				ma.setClf3(oldma.getClf1());
				ma.setBgf3(oldma.getBgf1());
				ma.setHyf3(oldma.getHyf1());
				ma.setCgf3(oldma.getCgf1());
				ma.setPxf3(oldma.getPxf1());
				ma.setSsf3(oldma.getSsf1());
				ma.setZjjgf3(oldma.getZjf3());
				ma.setNdjssjf3(oldma.getNdjssjf1());
				ma.setZxf3(oldma.getZxf1());
				ma.setYjykff3(oldma.getYjykff1());
				ma.setJszrf3(oldma.getJszrf1());
				ma.setDshf3(oldma.getDshf1());
				ma.setPhf3(oldma.getPhf1());
				ma.setQt1(oldma.getQt1());
				ma.setLtxryjf3(oldma.getLtxryjf1());
				ma.setZgxcf3(oldma.getZgxc1());
				ma.setZlf3(oldma.getZlf1());
				ma.setFdkyfzc3(oldma.getFdkyfzc1());
				ma.setQt0_3(oldma.getQt0_1());

			}
			// 管理费用合计
			Double glfy1 = 0d;
			Double glfy2 = 0d;
			glfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '管理费用' and belongLayer = 1 and bookKDate = ? ",
							months);
			glfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '管理费用' and belongLayer = 1 and bookKDate = ? ",
							months);
			ma.setGlfy1(glfy1);
			ma.setGlfy2(glfy2);
			// 职工薪酬
			Double zgxc1 = 0d;
			Double zgxc2 = 0d;
			zgxc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '职工薪酬' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			zgxc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where  name = '职工薪酬' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			ma.setZgxc1(zgxc1);
			ma.setZgxc2(zgxc2);
			// 其中：工资
			Double gz1 = 0d;
			Double gz2 = 0d;
			gz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '工资' and fatherName = '职工薪酬' and bookKDate = ? ",
							months);
			gz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '工资' and fatherName = '职工薪酬'  and bookKDate = ? ",
							months);
			ma.setZgxc1(zgxc1);
			ma.setZgxc2(zgxc2);
			// 保险费
			Double bxf1 = 0d;
			Double bxf2 = 0d;
			bxf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '保险费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			bxf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '保险费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setBxf1(bxf1);
			ma.setBxf2(bxf2);
			// 折旧费
			Double zjf1 = 0d;
			Double zjf2 = 0d;
			zjf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '折旧费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			zjf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '折旧费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setZjf1(zjf1);
			ma.setZjf2(zjf2);
			// 修理费
			Double xlf1 = 0d;
			Double xlf2 = 0d;
			xlf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '修理费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			xlf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '修理费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setXlf1(xlf1);
			ma.setXlf2(xlf2);
			// 无形资产摊销
			Double wxzctx1 = 0d;
			Double wxzctx2 = 0d;
			wxzctx1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '无形资产摊销' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			wxzctx2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '无形资产摊销' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setWxzctx1(wxzctx1);
			ma.setWxzctx2(wxzctx2);
			// 存货盘亏
			Double chpk1 = 0d;
			Double chpk2 = 0d;
			chpk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '存货盘亏' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			chpk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '存货盘亏' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setChpk1(chpk1);
			ma.setChpk2(chpk2);
			// 业务招待费
			Double ywzdf1 = 0d;
			Double ywzdf2 = 0d;
			ywzdf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '业务招待费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			ywzdf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '业务招待费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setYwzdf1(ywzdf1);
			ma.setYwzdf2(ywzdf2);
			// 运输费
			Double ysf1 = 0d;
			Double ysf2 = 0d;
			ysf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '运输费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			ysf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '运输费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setYsf1(ysf1);
			ma.setYsf2(ysf2);
			// 水电费
			Double sdf1 = 0d;
			Double sdf2 = 0d;
			sdf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '水电费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			sdf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '水电费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setSdf1(sdf1);
			ma.setSdf2(sdf2);
			// 取暖费 qnf1
			Double qnf1 = 0d;
			Double qnf2 = 0d;
			qnf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '取暖费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			qnf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '取暖费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setQnf1(qnf1);
			ma.setQnf2(qnf2);
			// ltxqnf1 离退休人员取暖费
			Double ltxqnf1 = 0d;
			Double ltxqnf2 = 0d;
			ltxqnf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '离退休人员取暖费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			ltxqnf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '离退休人员取暖费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setLtxqnf1(ltxqnf1);
			ma.setLtxqnf2(ltxqnf2);
			// clf1 差旅费
			Double clf1 = 0d;
			Double clf2 = 0d;
			clf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '差旅费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			clf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '差旅费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setClf1(clf1);
			ma.setClf2(clf2);
			// bgf1 办公费
			Double bgf1 = 0d;
			Double bgf2 = 0d;
			bgf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '办公费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			bgf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '办公费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setBgf1(bgf1);
			ma.setBgf2(bgf2);
			// hyf1 会议费
			Double hyf1 = 0d;
			Double hyf2 = 0d;
			hyf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '会议费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			hyf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '会议费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setHyf1(hyf1);
			ma.setHyf2(hyf2);
			// cgf1 出国费
			Double cgf1 = 0d;
			Double cgf2 = 0d;
			cgf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '出国费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			cgf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '出国费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setCgf1(cgf1);
			ma.setCgf2(cgf2);
			// pxf1 培训费
			Double pxf1 = 0d;
			Double pxf2 = 0d;
			pxf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '培训费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			pxf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '培训费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setPxf1(pxf1);
			ma.setPxf2(pxf2);
			// ssf1 诉讼费
			Double ssf1 = 0d;
			Double ssf2 = 0d;
			ssf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '诉讼费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			ssf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '诉讼费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setSsf1(ssf1);
			ma.setSsf2(ssf2);
			// zjjgf1 聘请中介机构费
			Double zjjgf1 = 0d;
			Double zjjgf2 = 0d;
			zjjgf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '聘请中介机构费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			zjjgf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '聘请中介机构费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setZjjgf1(zjjgf1);
			ma.setZjjgf2(zjjgf2);
			// ndjssjf1 其中：年度决算审计费用
			Double ndjssjf1 = 0d;
			Double ndjssjf2 = 0d;
			ndjssjf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '年度决算审计费用' and fatherName = '聘请中介机构费' and bookKDate = ? ",
							months);
			ndjssjf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '年度决算审计费用' and fatherName = '聘请中介机构费'  and bookKDate = ? ",
							months);
			ma.setNdjssjf1(ndjssjf1);
			ma.setNdjssjf2(ndjssjf2);
			// zxf1 咨询费
			Double zxf1 = 0d;
			Double zxf2 = 0d;
			zxf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '咨询费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			zxf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '咨询费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setZxf1(zxf1);
			ma.setZxf2(zxf2);
			// yjykff1 研究与开发费
			Double yjykff1 = 0d;
			Double yjykff2 = 0d;
			yjykff1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '研究与开发费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			yjykff2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '研究与开发费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setYjykff1(yjykff1);
			ma.setYjykff2(yjykff2);
			// jszrf1 技术转让费
			Double jszrf1 = 0d;
			Double jszrf2 = 0d;
			jszrf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '技术转让费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			jszrf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '技术转让费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setJszrf1(jszrf1);
			ma.setJszrf2(jszrf2);
			// dshf1 董事会费
			Double dshf1 = 0d;
			Double dshf2 = 0d;
			dshf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '董事会费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			dshf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '董事会费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setDshf1(dshf1);
			ma.setDshf2(dshf2);
			// phf1 排污费
			Double phf1 = 0d;
			Double phf2 = 0d;
			phf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '排污费' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			phf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '排污费' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setPhf1(phf1);
			ma.setPhf2(phf2);
			// phf1 其他
			Double qt1 = 0d;
			Double qt2 = 0d;
			qt1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '其他' and fatherName = '管理费用' and bookKDate = ? ",
							months);
			qt2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '其他' and fatherName = '管理费用'  and bookKDate = ? ",
							months);
			ma.setQt1(qt1);
			ma.setQt2(qt2);
			// 其中：离退休人员经费
			Double ltxryjf1 = 0d;
			Double ltxryjf2 = 0d;
			ltxryjf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '离退休人员经费'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '管理费用' and belongLayer = 1)",
							months);
			ltxryjf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '离退休人员经费'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '管理费用' and belongLayer = 1) ",
							months);
			ma.setLtxryjf1(ltxryjf1);
			ma.setLtxryjf2(ltxryjf2);
			// zgxcf1;//政工宣传费
			Double zgxcf1 = 0d;
			Double zgxcf2 = 0d;
			zgxcf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '政工宣传费'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '管理费用' and belongLayer = 1)",
							months);
			zgxcf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '政工宣传费'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '管理费用' and belongLayer = 1) ",
							months);
			ma.setZgxcf1(zgxcf1);
			ma.setZgxcf2(zgxcf2);
			// zlf1;// 租赁费
			Double zlf1 = 0d;
			Double zlf2 = 0d;
			zlf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '租赁费'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '管理费用' and belongLayer = 1)",
							months);
			zlf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '租赁费'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '管理费用' and belongLayer = 1) ",
							months);
			ma.setZlf1(zlf1);
			ma.setZlf2(zlf2);
			// fdkyfzc1;//分担科研费支出（负数表示）
			Double fdkyfzc1 = 0d;
			Double fdkyfzc2 = 0d;
			fdkyfzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select ABS(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '租赁费'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '分担科研费支出' and belongLayer = 1)",
							months);
			fdkyfzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select ABS(borrowYearSumMoney -lendYearSumMoney)   from SubBudgetRate where name = '租赁费'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '分担科研费支出' and belongLayer = 1) ",
							months);
			ma.setFdkyfzc1(-fdkyfzc1);
			ma.setFdkyfzc2(-fdkyfzc2);
			// qt0_1;//其他
			Double qt0_1 = 0d;
			Double qt0_2 = 0d;
			qt0_1 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select ABS(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '其他'   and fatherName = '其他'  and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '分担科研费支出' and belongLayer = 1)",
							months);
			qt0_2 = (Double) totalDao
					.getObjectByConditionforDouble(
							"select ABS(borrowYearSumMoney -lendYearSumMoney)   from SubBudgetRate where name = 其他' and fatherName = '其他'   and bookKDate = ?  and rootId in  ( select id from  SubBudgetRate where name = '分担科研费支出' and belongLayer = 1) ",
							months);
			ma.setQt0_1(qt0_1);
			ma.setQt0_2(qt0_2);
			ma.setCompanyNmae(Util.getLoginCompanyInfo().getName());
			totalDao.save(ma);
			/**
			 * 销售费用及财务费用明细表
			 */
			XsfyjCwfyMx xcm = new XsfyjCwfyMx();
			XsfyjCwfyMx oldxcm = (XsfyjCwfyMx) totalDao
					.getObjectByConditionforDouble(
							" from XsfyjCwfyMx where months = ? ",
							lastyearmoths);
			if (oldxcm != null) {
				xcm.setXsfy3(oldxcm.getXsfy1());
				xcm.setYsf3(oldxcm.getYsf1());
				xcm.setZxf3(oldxcm.getZxf1());
				xcm.setCpbxf3(oldxcm.getCpbxf1());
				xcm.setWtdxsxf3(oldxcm.getWtdxsxf1());
				xcm.setGgf3(oldxcm.getGgf1());
				xcm.setZlf1(oldxcm.getZlf1());
				xcm.setZulinf3(oldxcm.getZulinf1());
				xcm.setBzf3(oldxcm.getBzf1());
				xcm.setXsfwf3(oldxcm.getXsfwf1());
				xcm.setZgxc3(oldxcm.getZgxc1());
				xcm.setGz3(oldxcm.getGz1());
				xcm.setClf3(oldxcm.getClf1());
				xcm.setBgf3(oldxcm.getBgf1());
				xcm.setZdf3(oldxcm.getZdf1());
				xcm.setZjf3(oldxcm.getZjf1());
				xcm.setSdf3(oldxcm.getSdf1());
				xcm.setXlf3(oldxcm.getXlf1());
				xcm.setWlxh3(oldxcm.getWlxh1());
				xcm.setDzyhptx1(oldxcm.getDzyhptx1());
				xcm.setCpsbss3(oldxcm.getCpsbss1());
				xcm.setQita3(oldxcm.getQita1());
				xcm.setCwfy3(oldxcm.getCwfy1());
				xcm.setLxzc3(oldxcm.getLxzc1());
				xcm.setCztx3(oldxcm.getCztx1());
				xcm.setLxsr3(oldxcm.getLxsr1());
				xcm.setZkss3(oldxcm.getZkss1());
				xcm.setHdjss3(oldxcm.getHdjss1());
				xcm.setHdsy3(oldxcm.getHdsy1());
				xcm.setJrjgsxf1(oldxcm.getJrjgsxf1());
				xcm.setQt3(oldxcm.getQt1());

			}
			/********* 一、销售费用合计 *******/
			Double xsfy1 = 0d;
			Double xsfy2 = 0d;
			xsfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '销售费用'  and belongLayer = 1  and bookKDate = ? ",
							months);
			xsfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '销售费用'  and belongLayer = 1  and bookKDate = ? ",
							months);
			xcm.setXsfy1(xsfy1);
			xcm.setXsfy2(xsfy2);
			// 运输费
			Double xsysf1 = 0d;
			Double xsysf2 = 0d;
			xsysf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '运输费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xsysf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '运输费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setYsf1(xsysf1);
			xcm.setYsf2(xsysf2);
			// 装卸费
			Double XS_zxf1 = 0d;
			Double XS_zxf2 = 0d;
			XS_zxf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '装卸费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			XS_zxf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '装卸费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setZxf1(XS_zxf1);
			xcm.setZxf2(XS_zxf2);
			// 产品保险费
			Double cpbxf1 = 0d;
			Double cpbxf2 = 0d;
			cpbxf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '产品保险费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			cpbxf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '产品保险费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setCpbxf1(cpbxf1);
			xcm.setCpbxf2(cpbxf2);
			// 委托代销手续费(包装物)
			Double wtdxsxf1 = 0d;
			Double wtdxsxf2 = 0d;
			wtdxsxf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '委托代销手续费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			wtdxsxf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '委托代销手续费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setWtdxsxf1(wtdxsxf1);
			xcm.setWtdxsxf2(wtdxsxf2);
			// 广告费
			Double ggf1 = 0d;
			Double ggf2 = 0d;
			ggf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '广告费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			ggf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '广告费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setGgf1(ggf1);
			xcm.setGgf2(ggf2);
			// 展览费
			Double xs_zlf1 = 0d;
			Double xs_zlf2 = 0d;
			xs_zlf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '展览费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xs_zlf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '展览费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setZlf1(xs_zlf1);
			xcm.setZlf2(xs_zlf2);
			// 租赁费
			Double zulinf1 = 0d;
			Double zulinf2 = 0d;
			zulinf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '租赁费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			zulinf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '租赁费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setZulinf1(zulinf1);
			xcm.setZulinf2(zulinf2);
			// 包装费
			Double bzf1 = 0d;
			Double bzf2 = 0d;
			bzf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '包装费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			bzf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '包装费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setBzf1(bzf1);
			xcm.setBzf2(bzf2);
			// 销售服务费用
			Double xsfwf1 = 0d;
			Double xsfwf2 = 0d;
			xsfwf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '销售服务费用'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xsfwf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '销售服务费用'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setXsfwf1(xsfwf1);
			xcm.setXsfwf2(xsfwf2);
			// 职工薪酬
			Double xszgxc1 = 0d;
			Double xszgxc2 = 0d;
			xszgxc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '职工薪酬'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xszgxc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '职工薪酬'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setZgxc1(xszgxc1);
			xcm.setZgxc2(xszgxc2);
			// 其中：工资
			Double xsgz1 = 0d;
			Double xsgz2 = 0d;
			xsgz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '工资'  and fatherName = '职工薪酬'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '销售费用' and  belongLayer = 1)",
							months);
			xsgz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '工资'  and fatherName = '职工薪酬'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '销售费用' and  belongLayer = 1) ",
							months);
			xcm.setGz1(xsgz1);
			xcm.setGz2(xsgz2);
			// 差旅费
			Double xsclf1 = 0d;
			Double xsclf2 = 0d;
			xsclf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '差旅费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xsclf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '差旅费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setClf1(xsclf1);
			xcm.setClf2(xsclf2);
			// 办公费
			Double xsbgf1 = 0d;
			Double xsbgf2 = 0d;
			xsbgf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '办公费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xsbgf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '办公费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setBgf1(xsbgf1);
			xcm.setBgf2(xsbgf2);
			// 招待费
			Double zdf1 = 0d;
			Double zdf2 = 0d;
			zdf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '招待费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			zdf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '招待费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setZdf1(zdf1);
			xcm.setZdf2(zdf2);
			// 折旧费
			Double xszjf1 = 0d;
			Double xszjf2 = 0d;
			xszjf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '折旧费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xszjf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '折旧费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setZjf1(xszjf1);
			xcm.setZjf2(xszjf2);
			// 取暖费
			Double xsqnf1 = 0d;
			Double xsqnf2 = 0d;
			xsqnf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '取暖费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xsqnf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '取暖费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setQnf1(xsqnf1);
			xcm.setQnf2(xsqnf2);
			// 水电费
			Double xssdf1 = 0d;
			Double xssdf2 = 0d;
			xssdf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '水电费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xssdf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '水电费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setSdf1(xssdf1);
			xcm.setSdf2(xssdf2);
			// 修理费
			Double xsxlf1 = 0d;
			Double xsxlf2 = 0d;
			xsxlf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '修理费'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			xsxlf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '修理费'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setXlf1(xsxlf1);
			xcm.setXlf2(xsxlf2);
			// 物料消耗
			Double wlxh1 = 0d;
			Double wlxh2 = 0d;
			wlxh1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '物料消耗'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			wlxh2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '物料消耗'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setWlxh1(wlxh1);
			xcm.setWlxh2(wlxh2);
			// dzyhptx1 低值易耗品摊销(包装物)
			Double dzyhptx1 = 0d;
			Double dzyhptx2 = 0d;
			dzyhptx1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '低值易耗品摊销'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			dzyhptx2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '低值易耗品摊销'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setDzyhptx1(dzyhptx1);
			xcm.setDzyhptx2(dzyhptx2);
			// cpsbss1 产品“三包”损失
			Double cpsbss1 = 0d;
			Double cpsbss2 = 0d;
			cpsbss1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '产品“三包”损失'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			cpsbss2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '产品“三包”损失'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setCpsbss1(cpsbss1);
			xcm.setCpsbss2(cpsbss2);
			// qita1 其 它
			Double qita1 = 0d;
			Double qita2 = 0d;
			qita1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '其它'  and fatherName = '销售费用'  and bookKDate = ? ",
							months);
			qita2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '其它'  and fatherName = '销售费用' and bookKDate = ? ",
							months);
			xcm.setQita1(qita1);
			xcm.setQita2(qita2);
			/*** 二、财务费用合计 ***************/
			Double cwfy1 = 0d;
			Double cwfy2 = 0d;
			cwfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '财务费用'   and belongLayer = 1 and bookKDate = ? ",
							months);
			cwfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '财务费用'   and belongLayer = 1 and bookKDate = ? ",
							months);
			xcm.setCwfy1(cwfy1);
			xcm.setCwfy2(cwfy2);
			// 利息支出
			Double lxzc1 = 0d;
			Double lxzc2 = 0d;
			lxzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '利息支出'   and fatherName = '财务费用'  and bookKDate = ? ",
							months);
			lxzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '利息支出'   and fatherName = '财务费用'   and bookKDate = ? ",
							months);
			xcm.setLxzc1(lxzc1);
			xcm.setLxzc2(lxzc2);
			// 减：财政贴息
			Double cztx1 = 0d;
			Double cztx2 = 0d;
			cztx1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '财政贴息'   and fatherName = '财务费用'  and bookKDate = ? ",
							months);
			cztx2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '财政贴息'   and fatherName = '财务费用'   and bookKDate = ? ",
							months);
			xcm.setCztx1(cztx1);
			xcm.setCztx2(cztx2);
			// //减：利息收入
			Double lxsr1 = 0d;
			Double lxsr2 = 0d;
			lxsr1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '利息收入'   and fatherName = '财务费用'  and bookKDate = ? ",
							months);
			lxsr2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '利息收入'   and fatherName = '财务费用'   and bookKDate = ? ",
							months);
			xcm.setLxsr1(lxsr1);
			xcm.setLxsr2(lxsr2);
			// 折扣损失
			Double zkss1 = 0d;
			Double zkss2 = 0d;
			zkss1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '折扣损失'   and fatherName = '财务费用'  and bookKDate = ? ",
							months);
			zkss2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '折扣损失'   and fatherName = '财务费用'   and bookKDate = ? ",
							months);
			xcm.setZkss1(zkss1);
			xcm.setZkss2(zkss2);
			// 汇兑净损失
			Double hdjss1 = 0d;
			Double hdjss2 = 0d;
			hdjss1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '汇兑净损失'   and fatherName = '财务费用'  and bookKDate = ? ",
							months);
			hdjss2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '汇兑净损失'   and fatherName = '财务费用'   and bookKDate = ? ",
							months);
			xcm.setHdjss1(hdjss1);
			xcm.setHdjss2(hdjss2);
			// 其中：汇兑收益
			Double hdsy1 = 0d;
			Double hdsy2 = 0d;
			hdsy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '汇兑收益'   and fatherName = '汇兑净损失'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '财务费用' and belongLayer = 1)",
							months);
			hdsy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '汇兑收益'   and fatherName = '汇兑净损失'   and bookKDate = ? and rootId in (select id from SubBudgetRate where name = '财务费用' and belongLayer = 1)  ",
							months);
			xcm.setHdsy1(hdsy1);
			xcm.setHdsy2(hdsy2);
			// 金融机构手续费
			Double jrjgsxf1 = 0d;
			Double jrjgsxf2 = 0d;
			jrjgsxf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '金融机构手续费'   and fatherName = '财务费用'  and bookKDate = ?  ",
							months);
			jrjgsxf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '金融机构手续费'   and fatherName = '财务费用'   and bookKDate = ?   ",
							months);
			xcm.setJrjgsxf1(jrjgsxf1);
			xcm.setJrjgsxf2(jrjgsxf2);
			// 其 它
			Double cwqt1 = 0d;
			Double cwqt2 = 0d;
			cwqt1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '其他'   and fatherName = '财务费用'  and bookKDate = ?  ",
							months);
			cwqt1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '其他'   and fatherName = '财务费用'   and bookKDate = ?   ",
							months);
			xcm.setQt1(cwqt1);
			xcm.setQt2(cwqt2);
			totalDao.save(xcm);
			/**
			 * 生产成本及制造费用明细表
			 */
			ProducAndMan pam = new ProducAndMan();

			ProducAndMan oldpam = (ProducAndMan) totalDao.getObjectByCondition(
					" from ProducAndMan where months = ? ", lastyearmoths);
			if (oldpam != null) {
				pam.setSccbhj3(oldpam.getSccbhj1());
				pam.setZjcl3(oldpam.getZjcl1());
				pam.setYcl3(oldpam.getYcl1());
				pam.setWgcfj3(oldpam.getWgcfj1());
				pam.setQtycL3(oldpam.getQtycL1());
				pam.setRldlf3(oldpam.getRldlf1());
				pam.setZjrg3(oldpam.getZjrg1());
				pam.setWbjgf3(oldpam.getWbjgf1());
				pam.setZzfy3(oldpam.getZzfy1());
				pam.setZxfy3(oldpam.getZxfy1());
				pam.setZygz3(oldpam.getZygz1());
				pam.setSyf3(oldpam.getSyf1());
				pam.setSccbqt3(oldpam.getSccbqt1());
				pam.setFpss3(oldpam.getFpss1());
				pam.setZcpzzbcpqc3(oldpam.getZcpzzbcpqc1());
				pam.setZcpzzbcpqm3(oldpam.getZcpzzbcpqm1());
				pam.setZzfy3(oldpam.getZzfy1());
				pam.setZgxc3(oldpam.getZgxc1());
				pam.setGz3(oldpam.getGz1());
				pam.setBgf3(oldpam.getBgf1());
				pam.setClf3(oldpam.getClf1());
				pam.setRldlf3(oldpam.getRldlf1());
				pam.setQnf3(oldpam.getQnf1());
				pam.setZjf3(oldpam.getZjf1());
				pam.setXlf3(oldpam.getXlf1());
				pam.setLdbhf3(oldpam.getLdbhf1());
				pam.setBxf3(oldpam.getBxf1());
				pam.setZhulinf3(oldpam.getZhulinf1());
				pam.setYsf3(oldpam.getYsf1());
				pam.setWbjgf_3(oldpam.getWbjgf_1());
				pam.setSjztf3(oldpam.getSjztf1());
				pam.setSyjyf3(oldpam.getSyjyf1());
				pam.setJszlf3(oldpam.getJszlf1());
				pam.setTgss3(oldpam.getTgss1());
				pam.setZfbt3(oldpam.getZfbt1());
				pam.setJkfyzbh3(oldpam.getJkfyzbh1());
				pam.setQita3(oldpam.getQita1());
				pam.setZzfzc3(oldpam.getZzfzc1());
				pam.setDzyhptx3(oldpam.getDzyhptx3());
			}
			// 生产成本合计：
			Double sccbhj1 = 0d;
			Double sccbhj2 = 0d;
			sccbhj1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '生产成本'  and belongLayer = 1   and bookKDate = ? ",
							months);
			sccbhj2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '生产成本'  and belongLayer = 1  and bookKDate = ?   ",
							months);
			pam.setSccbhj1(sccbhj1);
			pam.setSccbhj2(sccbhj2);
			// 一、直接材料
			Double zjcl1 = 0d;
			Double zjcl2 = 0d;
			zjcl1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '直接材料'  and bookKDate = ?  and fatherName = '生产成本' ",
							months);
			zjcl2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '直接材料' and fatherName = '生产成本'  and bookKDate = ?   ",
							months);
			pam.setZjcl1(zjcl1);
			pam.setZjcl2(zjcl2);
			// （一）原材料
			Double scycl1 = 0d;
			Double scycl2 = 0d;
			scycl1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '原材料'    and fatherName = '直接材料' and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			scycl2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '原材料' and fatherName = '直接材料'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setYcl1(scycl1);
			pam.setYcl2(scycl2);
			// 1.外购成附件
			Double wgcfj1 = 0d;
			Double wgcfj2 = 0d;
			wgcfj1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '外购成附件'    and fatherName = '原材料' and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			wgcfj2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '外购成附件' and fatherName = '原材料'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setWgcfj1(wgcfj1);
			pam.setWgcfj2(wgcfj2);
			// 2.其他原材料
			Double qtycL1 = 0d;
			Double qtycL2 = 0d;
			qtycL1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '其他原材料'    and fatherName = '原材料' and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			qtycL2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '其他原材料' and fatherName = '原材料'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setQtycL1(qtycL1);
			pam.setQtycL2(qtycL2);
			// 燃料和动力
			Double rlhdL1 = 0d;
			Double rlhdL2 = 0d;
			rlhdL1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '燃料和动力'    and fatherName = '直接材料' and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			rlhdL2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '燃料和动力' and fatherName = '直接材料'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setRlhdL1(rlhdL1);
			pam.setRlhdL2(rlhdL2);
			// 二、直接人工
			Double zjrg1 = 0d;
			Double zjrg2 = 0d;
			zjrg1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '直接人工'    and fatherName = '生产成本' and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			zjrg2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '直接人工' and fatherName = '生产成本'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setZjrg1(zjrg1);
			pam.setZjrg2(zjrg2);
			// 三、外部加工费
			Double wbjgf1 = 0d;
			Double wbjgf2 = 0d;
			wbjgf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '外部加工费'    and fatherName = '生产成本' and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			wbjgf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '外部加工费' and fatherName = '生产成本'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setWbjgf1(wbjgf1);
			pam.setWbjgf2(wbjgf2);
			// 四、制造费用
			Double zzfy1 = 0d;
			Double zzfy2 = 0d;
			zzfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '制造费用'     and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			zzfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '制造费用'   and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setZzfy1(zzfy1);
			pam.setZzfy2(zzfy2);
			// 五、专项费用
			Double zxfy1 = 0d;
			Double zxfy2 = 0d;
			zxfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '专项费用'     and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			zxfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '专项费用'   and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setZxfy1(zxfy1);
			pam.setZxfy2(zxfy2);
			// （一）专用工装
			Double zygz1 = 0d;
			Double zygz2 = 0d;
			zygz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '专用工装'   and  fatherName = '专项费用'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			zygz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '专用工装' and  fatherName = '专项费用'   and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setZygz1(zygz1);
			pam.setZygz2(zygz2);
			// syf1 试验费
			Double syf1 = 0d;
			Double syf2 = 0d;
			syf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '试验费'   and  fatherName = '专项费用'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			syf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '试验费'   and  fatherName = '专项费用'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setSyf1(syf1);
			pam.setSyf2(syf2);
			// sccbqt1 其他
			Double sccbqt1 = 0d;
			Double sccbqt2 = 0d;
			sccbqt1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '其他'   and  fatherName = '专项费用'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			sccbqt2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '其他'   and  fatherName = '专项费用'  and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setSccbqt1(sccbqt1);
			pam.setSccbqt2(sccbqt2);
			// fpss1 废品损失
			Double fpss1 = 0d;
			Double fpss2 = 0d;
			fpss1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '废品损失'    and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )",
							months);
			fpss2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name = '废品损失'     and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '生产成本' and  belongLayer = 1 )  ",
							months);
			pam.setSccbqt1(sccbqt1);
			pam.setSccbqt2(sccbqt2);
			// 加：在产品和自制半成品期初余额
			Double zcpzzbcpqc1 = 0d;
			Double zcpzzbcpqc2 = 0d;
			zcpzzbcpqc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowQichuMoney -lendQichuMoney   from SubBudgetRate where name in('在产品','自制半成品')     and bookKDate = ?   ",
							months);
			zcpzzbcpqc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name in('在产品','自制半成品')      and bookKDate = ?    ",
							months);
			pam.setZcpzzbcpqc1(zcpzzbcpqc1);
			pam.setZcpzzbcpqc2(zcpzzbcpqc2);
			// 减：在产品和自制半成品期末余额
			Double zcpzzbcpqm1 = 0d;
			Double zcpzzbcpqm2 = 0d;
			zcpzzbcpqm1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name in('在产品','自制半成品')     and bookKDate = ?   ",
							months);
			zcpzzbcpqm2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name in('在产品','自制半成品')      and bookKDate = ?    ",
							months);
			pam.setZcpzzbcpqm1(zcpzzbcpqm1);
			pam.setZcpzzbcpqm2(zcpzzbcpqm2);
			// 制造费用合计：
			Double zzfyhj1 = 0d;
			Double zzfyhj2 = 0d;
			zzfyhj1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='制造费用'     and bookKDate = ?   and belongLayer = 1 ",
							months);
			zzfyhj2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='制造费用'    and bookKDate = ? and belongLayer = 1   ",
							months);
			pam.setZcpzzbcpqm1(zcpzzbcpqm1);
			pam.setZcpzzbcpqm2(zcpzzbcpqm2);
			// 职工薪酬
			Double zgxc_zz1 = 0d;
			Double zgxc_zz2 = 0d;
			zgxc_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='职工薪酬'     and bookKDate = ?    and rootId in (select id from SubBudgetRate where name = '制造费用' and  belongLayer = 1 ) ",
							months);
			zgxc_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='职工薪酬'    and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '制造费用' and  belongLayer = 1 )   ",
							months);
			pam.setZgxc1(zgxc_zz1);
			pam.setZgxc2(zgxc_zz2);
			// 其中：工资
			Double gz_zz1 = 0d;
			Double gz_zz2 = 0d;
			gz_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='工资'     and bookKDate = ?    and rootId in (select id from SubBudgetRate where name = '制造费用' and  belongLayer = 1 ) ",
							months);
			gz_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='工资'    and bookKDate = ?  and rootId in (select id from SubBudgetRate where name = '制造费用' and  belongLayer = 1 )   ",
							months);
			pam.setGz1(gz_zz1);
			pam.setGz2(gz_zz2);
			// 办公费
			Double bgf_zz1 = 0d;
			Double bgf_zz2 = 0d;
			bgf_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='办公费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			bgf_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='办公费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setGz1(gz_zz1);
			pam.setGz2(gz_zz2);
			// 差旅费
			Double clf_zz1 = 0d;
			Double clf_zz2 = 0d;
			clf_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='差旅费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			clf_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='差旅费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setClf1(clf_zz1);
			pam.setClf2(clf_zz2);
			// 机物料消耗
			Double jwlxh1 = 0d;
			Double jwlxh2 = 0d;
			jwlxh1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='机物料消耗'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			jwlxh2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='机物料消耗'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setJwlxh1(jwlxh1);
			pam.setJwlxh2(jwlxh2);
			// 燃料动力费（含水电费）
			Double rldlf1 = 0d;
			Double rldlf2 = 0d;
			rldlf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='燃料动力费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			rldlf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='燃料动力费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setRldlf1(rldlf1);
			pam.setRldlf2(rldlf2);
			// 取暖费
			Double qnf_zz1 = 0d;
			Double qnf_zz2 = 0d;
			qnf_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='取暖费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			qnf_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='取暖费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setQnf1(qnf_zz1);
			pam.setQnf2(qnf_zz2);
			// 折旧费
			Double zjf_zz1 = 0d;
			Double zjf_zz2 = 0d;
			zjf_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='折旧费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			zjf_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='折旧费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setZjf1(zjf_zz1);
			pam.setZjf2(zjf_zz2);
			// 修理费
			Double xlf_zz1 = 0d;
			Double xlf_zz2 = 0d;
			xlf_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='修理费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			xlf_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='修理费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setXlf1(xlf_zz1);
			pam.setXlf2(xlf_zz2);
			// 低值易耗品摊销
			Double dzyhptx_zz1 = 0d;
			Double dzyhptx_zz2 = 0d;
			dzyhptx_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='低值易耗品摊销'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			dzyhptx_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='低值易耗品摊销'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setDzyhptx1(dzyhptx1);
			pam.setDzyhptx2(dzyhptx2);
			// 劳动保护费
			Double ldbhf1 = 0d;
			Double ldbhf2 = 0d;
			ldbhf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='劳动保护费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			ldbhf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='劳动保护费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setLdbhf1(ldbhf1);
			pam.setLdbhf2(ldbhf2);
			// 保险费
			Double bxf_zz1 = 0d;
			Double bxf_zz2 = 0d;
			bxf_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='保险费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			bxf_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='保险费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setBxf1(bxf_zz1);
			pam.setBxf2(bxf_zz2);
			// 租赁费
			Double zhulinf1 = 0d;
			Double zhulinf2 = 0d;
			zhulinf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='租赁费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			zhulinf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='租赁费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setZhulinf1(zhulinf1);
			pam.setZhulinf2(zhulinf2);
			// 运输费
			Double ysf_zz1 = 0d;
			Double ysf_zz2 = 0d;
			ysf_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='运输费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			ysf_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='运输费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setYsf1(ysf_zz1);
			pam.setYsf2(ysf_zz2);
			// 外部加工费
			Double wbjgf_1 = 0d;
			Double wbjgf_2 = 0d;
			wbjgf_1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='外部加工费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			wbjgf_2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='外部加工费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setWbjgf_1(wbjgf_1);
			pam.setWbjgf_2(wbjgf_2);
			// 设计制图费
			Double sjztf1 = 0d;
			Double sjztf2 = 0d;
			sjztf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='设计制图费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			sjztf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='设计制图费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setSjztf1(sjztf1);
			pam.setSjztf2(sjztf2);
			// 试验检验费
			Double syjyf1 = 0d;
			Double syjyf2 = 0d;
			syjyf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='试验检验费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			syjyf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='试验检验费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setSyjyf1(syjyf1);
			pam.setSyjyf2(syjyf2);
			// 技术资料费
			Double jszlf1 = 0d;
			Double jszlf2 = 0d;
			jszlf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='技术资料费'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			jszlf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='技术资料费'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setJszlf1(jszlf1);
			pam.setJszlf2(jszlf2);
			// 季节性和修理期间的停工损失
			Double tgss1 = 0d;
			Double tgss2 = 0d;
			tgss1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='季节性和修理期间的停工损失'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			tgss2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='季节性和修理期间的停工损失'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setTgss1(tgss1);
			pam.setTgss2(tgss2);
			// 住房补贴
			Double zfbt1 = 0d;
			Double zfbt2 = 0d;
			zfbt1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='住房补贴'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			zfbt2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='住房补贴'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setZfbt1(zfbt1);
			pam.setZfbt2(zfbt2);
			// 借款费用资本化金额
			Double jkfyzbh1 = 0d;
			Double jkfyzbh2 = 0d;
			jkfyzbh1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='借款费用资本化金额'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			jkfyzbh2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='借款费用资本化金额'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setJkfyzbh1(jkfyzbh1);
			pam.setJkfyzbh2(jkfyzbh2);
			// 其 他
			Double qita_zz1 = 0d;
			Double qita_zz2 = 0d;
			qita_zz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='其他'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			qita_zz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='其他'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setQita1(qita_zz1);
			pam.setQita2(qita_zz2);
			// 减：制造费用转出金额
			Double zzfzc1 = 0d;
			Double zzfzc2 = 0d;
			zzfzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='制造费用转出'     and bookKDate = ?    and  fatherName = '制造费用' ",
							months);
			zzfzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowYearSumMoney -lendYearSumMoney   from SubBudgetRate where name ='制造费用转出'    and bookKDate = ?  and  fatherName = '制造费用'  ",
							months);
			pam.setZzfzc1(zzfzc1);
			pam.setZzfzc2(zzfzc2);
			pam.setMonths(months);
			pam.setCompanyName(companyName);
			totalDao.save(pam);
			/**
			 * 基本情况表
			 */
			JiBenQingKuang jbqk = new JiBenQingKuang();
			/******** 一、财务指标 */
			JiBenQingKuang oldjbqk = (JiBenQingKuang) totalDao
					.getObjectByConditionforDouble(
							" from JiBenQingKuang where months =? ",
							lastyearmoths);
			if (oldjbqk != null) {
				jbqk.setGdzczj3(oldjbqk.getGdzczj2());
				jbqk.setTzsy3(oldjbqk.getTzsy2());
				jbqk.setCqgqtz3(oldjbqk.getCqgqtz2());
				jbqk.setGyzjrzc3(oldjbqk.getGyzjrzc2());
				jbqk.setCyzdrtz3(oldjbqk.getCyzdqtz2());
				jbqk.setKcsjrzc3(oldjbqk.getKcsjrzc2());
				jbqk.setQtsyxm3(oldjbqk.getQtsyxm2());
				jbqk.setZwcb3(oldjbqk.getZwcb2());
				jbqk.setFyhlx3(oldjbqk.getFyhlx2());
				jbqk.setZbhlx3(oldjbqk.getZbhlx2());
				jbqk.setGbzjzcxm3(oldjbqk.getGbzjzcxm2());
				jbqk.setGbzjze3(oldjbqk.getGbzjzcze2());
				jbqk.setJbtjjsbk3(oldjbqk.getJbtjjsbk2());
				jbqk.setKyszfbk3(oldjbqk.getKyszfbk2());
				jbqk.setGbzjzcze3(oldjbqk.getGbzjzcze2());
				jbqk.setJbtjjszc3(oldjbqk.getJbtjjszc2());
				jbqk.setKyszfzc3(oldjbqk.getKyszfzc2());
				jbqk.setQyzczbxzc3(oldjbqk.getQyzczbxzc2());
				jbqk.setJbtjjs3(oldjbqk.getJbtjjs2());
				jbqk.setQtzc3(oldjbqk.getQtzc2());
				jbqk.setQzzc3(oldjbqk.getQzzc2());
				jbqk.setZgpxfy3(oldjbqk.getZgpxfy2());
				jbqk.setYxzw3(oldjbqk.getYxzw2());
				jbqk.setDwdb3(oldjbqk.getDwdb2());
				jbqk.setDzyzc3(oldjbqk.getDzyzc2());
				jbqk.setHyfz3(oldjbqk.getHyfz2());
				jbqk.setYxg3(oldjbqk.getYxg2());
				jbqk.setYxzq3(oldjbqk.getYxzq2());
				jbqk.setYqzw3(oldjbqk.getYqzw2());
				jbqk.setYqyhjk3(oldjbqk.getYqyhjk2());
				jbqk.setYqyfzk3(oldjbqk.getYqyfzk2());
				jbqk.setYqdwdb3(oldjbqk.getYqdwdb2());
				jbqk.setZjqk3(oldjbqk.getZjqk2());
				jbqk.setZjjzd3(oldjbqk.getZjjzd2());
				jbqk.setCwgsgjzj3(oldjbqk.getCwgsgjzj2());
				jbqk.setZjjsgjzj3(oldjbqk.getZjjsgjzj2());
				jbqk.setYhcdze3(oldjbqk.getYhcdze2());
				jbqk.setGyzcz3(oldjbqk.getGyzcz2());
				jbqk.setLdsczz3(oldjbqk.getLdsczz2());
				jbqk.setGyxscz3(oldjbqk.getGyxscz2());
				jbqk.setXcpcz3(oldjbqk.getXcpcz2());
				jbqk.setJnjpf3(oldjbqk.getJnjpf2());
				jbqk.setScdde3(oldjbqk.getScdde2());
				jbqk.setGndd3(oldjbqk.getGndd2());
				jbqk.setJpdd3(oldjbqk.getJpdd2());
				jbqk.setMpdd3(oldjbqk.getMpdd2());
				jbqk.setGwdd3(oldjbqk.getGwdd2());
				jbqk.setJmdd3(oldjbqk.getJmdd2());
				jbqk.setGwmpdd3(oldjbqk.getGwmpdd2());
				jbqk.setGdzctze3(oldjbqk.getGdzctze2());
				jbqk.setCkcpxssr3(oldjbqk.getCkcpxssr2());
				jbqk.setYhjk3(oldjbqk.getYhjk2());
				jbqk.setYjsf3(oldjbqk.getYjsf2());
				jbqk.setYjzzs3(oldjbqk.getYjzzs2());
				jbqk.setYjxfs3(oldjbqk.getYjxfs2());
				jbqk.setYjsds3(oldjbqk.getYjsds2());
				jbqk.setYijsf3(oldjbqk.getYijsf2());
				jbqk.setYijzzs3(oldjbqk.getYijzzs2());
				jbqk.setYijxfs3(oldjbqk.getYijxfs2());
				jbqk.setYijsds3(oldjbqk.getYijsds2());
				jbqk.setTze3(oldjbqk.getTze2());
				jbqk.setKysctz3(oldjbqk.getKysctz2());
				jbqk.setJpkysctz3(oldjbqk.getJpkysctz2());
				jbqk.setZlxcpsr3(oldjbqk.getZlxcpsr2());
				jbqk.setGywhyfwsc3(oldjbqk.getGywhyfwsc2());
				jbqk.setLrzeyss3(oldjbqk.getLrzeyss2());
				jbqk.setJlryss3(oldjbqk.getJlryss2());
				jbqk.setQyyftr3(oldjbqk.getQyyftr2());
				jbqk.setZfbk3(oldjbqk.getZfbk2());
				jbqk.setQyzc3(oldjbqk.getQyzc2());
				jbqk.setJnjpzb3(oldjbqk.getJnjpzb2());
				jbqk.setJnjptrze3(oldjbqk.getJnjptrze2());
				jbqk.setJnzfbk3(oldjbqk.getJnzfbk2());
				jbqk.setJnqyzc3(oldjbqk.getJnqyzc2());
				jbqk.setWrczzhnh3(oldjbqk.getWrczzhnh2());
				jbqk.setDgzhnh3(oldjbqk.getDgzhnh2());
				jbqk.setGdhf3(oldjbqk.getGdhf2());
				jbqk.setJygdzc3(oldjbqk.getJygdzc2());
				jbqk.setSybxf3(oldjbqk.getSybxf2());
				jbqk.setSybxfsjzc3(oldjbqk.getSybxfsjzc2());
				jbqk.setCcxfy3(oldjbqk.getCcxfy2());
				jbqk.setCxfy3(oldjbqk.getCxfy2());
				jbqk.setRsxfy3(oldjbqk.getRsxfy2());
				jbqk.setLyezwyked3(oldjbqk.getLyezwyked2());
				jbqk.setNcjy3(oldjbqk.getNcjy2());
				jbqk.setDnxz3(oldjbqk.getDnxz2());
				jbqk.setDnzf3(oldjbqk.getDnzf2());
				jbqk.setDnjy3(oldjbqk.getDnjy2());
				jbqk.setYjxyzf3(oldjbqk.getYjxyzf2());
				jbqk.setCke3(oldjbqk.getCke2());
				jbqk.setJpcke3(oldjbqk.getJpcke2());
				jbqk.setMpcke3(oldjbqk.getMpcke2());
				jbqk.setJpyszk3(oldjbqk.getJpyszk2());
				jbqk.setJpyfzk3(oldjbqk.getJpyfzk2());
				jbqk.setJpch3(oldjbqk.getJpch2());
				jbqk.setCcp3(oldjbqk.getCcp2());
				jbqk.setSydwjy3(oldjbqk.getSydwjy2());
				jbqk.setKyxmjdwcjy3(oldjbqk.getKyxmjdwcjy2());
			}
			// （一）计提的固定资产折旧总额
			Double gdzczj1 = 0d;
			Double gdzczj2 = 0d;
			gdzczj1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name ='计提的固定资产折旧总额'     and bookKDate = ?     ",
							months);
			gdzczj2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name ='计提的固定资产折旧总额'    and bookKDate = ?    ",
							months);
			jbqk.setGdzczj1(gdzczj1);
			jbqk.setGdzczj2(gdzczj2);
			// 投资收益
			Double tzsy1 = 0d;
			Double tzsy2 = 0d;
			tzsy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '投资收益'    and bookKDate = ?     ",
							months);
			tzsy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '投资收益'   and bookKDate = ?    ",
							months);
			jbqk.setGdzczj1(gdzczj1);
			jbqk.setGdzczj2(gdzczj2);
			// 其中：长期股权投资
			Double cqgqtz_jb1 = 0d;
			Double cqgqtz_jb2 = 0d;
			cqgqtz_jb1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '长期股权投资'    and bookKDate = ?   and fathreName = '投资收益'  ",
							months);
			cqgqtz_jb2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '长期股权投资'   and bookKDate = ? and fathreName = '投资收益'   ",
							months);
			jbqk.setCqgqtz1(cqgqtz_jb1);
			jbqk.setCqgqtz2(cqgqtz_jb2);
			// 以公允价值计量且其变动计入当期损益的金融资产
			Double gyzjrzc1 = 0d;
			Double gyzjrzc2 = 0d;
			gyzjrzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '以公允价值计量且其变动计入当期损益的金融资产'    and bookKDate = ?   and fathreName = '投资收益'  ",
							months);
			gyzjrzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '以公允价值计量且其变动计入当期损益的金融资产'   and bookKDate = ? and fathreName = '投资收益'   ",
							months);
			jbqk.setGyzjrzc1(gyzjrzc1);
			jbqk.setGyzjrzc2(gyzjrzc2);
			// 以公允价值计量且其变动计入当期损益的金融负债
			Double gyzjrfz1 = 0d;
			Double gyzjrfz2 = 0d;
			gyzjrfz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '以公允价值计量且其变动计入当期损益的金融负债'    and bookKDate = ?   and fathreName = '投资收益'  ",
							months);
			gyzjrfz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '以公允价值计量且其变动计入当期损益的金融负债'   and bookKDate = ? and fathreName = '投资收益'   ",
							months);
			jbqk.setGyzjrfz1(gyzjrfz1);
			jbqk.setGyzjrfz2(gyzjrfz2);
			// 持有至到期投资
			Double cyzdqtz_jb1 = 0d;
			Double cyzdqtz_jb2 = 0d;
			cyzdqtz_jb1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '持有至到期投资'    and bookKDate = ?   and fathreName = '投资收益'  ",
							months);
			cyzdqtz_jb2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '持有至到期投资'   and bookKDate = ? and fathreName = '投资收益'   ",
							months);
			jbqk.setCyzdqtz1(cyzdqtz_jb1);
			jbqk.setCyzdqtz2(cyzdqtz_jb2);
			// 可供出售金融资产
			Double kcsjrzc1 = 0d;
			Double kcsjrzc2 = 0d;
			kcsjrzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '可供出售金融资产'    and bookKDate = ?   and fathreName = '投资收益'  ",
							months);
			kcsjrzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '可供出售金融资产'   and bookKDate = ? and fathreName = '投资收益'   ",
							months);
			jbqk.setKcsjrzc1(kcsjrzc1);
			jbqk.setKcsjrzc2(kcsjrzc2);
			// 其他收益项目
			Double qtsyxm1 = 0d;
			Double qtsyxm2 = 0d;
			qtsyxm1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '其他收益项目'    and bookKDate = ?   and fathreName = '投资收益'  ",
							months);
			qtsyxm2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '其他收益项目'   and bookKDate = ? and fathreName = '投资收益'   ",
							months);
			jbqk.setQtsyxm1(qtsyxm1);
			jbqk.setQtsyxm2(qtsyxm2);
			// （三）债务成本zwcb1
			Double zwcb1 = 0d;
			Double zwcb2 = 0d;
			zwcb1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '债务成本'    and bookKDate = ?   and   belongLayer = 1 ",
							months);
			zwcb2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '债务成本'   and bookKDate = ? and   belongLayer = 1   ",
							months);
			jbqk.setZwcb1(zwcb1);
			jbqk.setZwcb2(zwcb2);
			// 其中：费用化利息 fyhlx1
			Double fyhlx1 = 0d;
			Double fyhlx2 = 0d;
			fyhlx1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '费用化利息'    and bookKDate = ?   and  fathreName = '债务成本' ",
							months);
			fyhlx2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '费用化利息'   and bookKDate = ? and fathreName = '债务成本'  ",
							months);
			jbqk.setFyhlx1(fyhlx1);
			jbqk.setFyhlx2(fyhlx2);
			// 资本化利息 zbhlx1
			Double zbhlx1 = 0d;
			Double zbhlx2 = 0d;
			zbhlx1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '资本化利息'    and bookKDate = ?   and  fathreName = '债务成本' ",
							months);
			zbhlx2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '资本化利息'   and bookKDate = ? and fathreName = '债务成本'  ",
							months);
			jbqk.setZbhlx1(zbhlx1);
			jbqk.setZbhlx2(zbhlx2);
			// （四）国拨资金及自筹项目情况 gbzjzcxm1
			Double gbzjzcxm1 = 0d;
			Double gbzjzcxm2 = 0d;
			gbzjzcxm1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '国拨资金及自筹项目情况'    and bookKDate = ?  ",
							months);
			gbzjzcxm2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '国拨资金及自筹项目情况'   and bookKDate = ?  ",
							months);
			jbqk.setGbzjzcxm1(gbzjzcxm1);
			jbqk.setGbzjzcxm2(gbzjzcxm2);
			// 收到国拨资金总额 gbzjze1
			Double gbzjze1 = 0d;
			Double gbzjze2 = 0d;
			gbzjze1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '收到国拨资金总额'    and bookKDate = ?   ",
							months);
			gbzjze2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '收到国拨资金总额'   and bookKDate = ?   ",
							months);
			jbqk.setGbzjze1(gbzjze1);
			jbqk.setGbzjze2(gbzjze2);
			// 其中：基本条件建设拨款jbtjjsbk1
			Double jbtjjsbk1 = 0d;
			Double jbtjjsbk2 = 0d;
			jbtjjsbk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '基本条件建设拨款'    and bookKDate = ?  and fathreName = '收到国拨资金总额' ",
							months);
			jbtjjsbk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '基本条件建设拨款'   and bookKDate = ?  and fathreName = '收到国拨资金总额'  ",
							months);
			jbqk.setJbtjjsbk1(jbtjjsbk1);
			jbqk.setJbtjjsbk2(jbtjjsbk2);
			// 科研试制费拨款 kyszfbk1
			Double kyszfbk1 = 0d;
			Double kyszfbk2 = 0d;
			kyszfbk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '科研试制费拨款'    and bookKDate = ?  and fathreName = '收到国拨资金总额' ",
							months);
			kyszfbk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '科研试制费拨款'   and bookKDate = ?  and fathreName = '收到国拨资金总额'  ",
							months);
			jbqk.setKyszfbk1(kyszfbk1);
			jbqk.setKyszfbk2(kyszfbk2);
			// 国拨资金支出总额 gbzjzcze1
			Double gbzjzcze1 = 0d;
			Double gbzjzcze2 = 0d;
			gbzjzcze1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '国拨资金支出总额'    and bookKDate = ?   ",
							months);
			gbzjzcze2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '国拨资金支出总额'   and bookKDate = ?    ",
							months);
			jbqk.setGbzjzcze1(gbzjzcze1);
			jbqk.setGbzjzcze2(gbzjzcze2);
			// 其中：基本条件建设支出 jbtjjszc1
			Double jbtjjszc1 = 0d;
			Double jbtjjszc2 = 0d;
			jbtjjszc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '基本条件建设支出'    and bookKDate = ?   and  fathreName = '国拨资金支出总额' ",
							months);
			jbtjjszc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '基本条件建设支出'   and bookKDate = ?  and  fathreName = '国拨资金支出总额'    ",
							months);
			jbqk.setJbtjjszc1(jbtjjszc1);
			jbqk.setJbtjjszc2(jbtjjszc2);
			// 科研试制费支出 kyszfzc1
			Double kyszfzc1 = 0d;
			Double kyszfzc2 = 0d;
			kyszfzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '科研试制费支出'    and bookKDate = ?  and  fathreName = '国拨资金支出总额'  ",
							months);
			kyszfzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '科研试制费支出'   and bookKDate = ?   and  fathreName = '国拨资金支出总额'   ",
							months);
			jbqk.setKyszfzc1(kyszfzc1);
			jbqk.setKyszfzc2(kyszfzc2);
			// 企业自筹资本性支出 qyzczbxzc1
			Double qyzczbxzc1 = 0d;
			Double qyzczbxzc2 = 0d;
			qyzczbxzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '企业自筹资本性支出'    and bookKDate = ?   ",
							months);
			qyzczbxzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '企业自筹资本性支出'   and bookKDate = ?    ",
							months);
			jbqk.setQyzczbxzc1(qyzczbxzc1);
			jbqk.setQyzczbxzc2(qyzczbxzc2);
			// 其中：基本条件建设企业配套
			Double jbtjjs1 = 0d;
			Double jbtjjs2 = 0d;
			jbtjjs1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '基本条件建设企业配套'    and bookKDate = ?  and  fathreName = '企业自筹资本性支出'  ",
							months);
			jbtjjs2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '基本条件建设企业配套'   and bookKDate = ?  and  fathreName = '企业自筹资本性支出'    ",
							months);
			jbqk.setJbtjjs1(jbtjjs1);
			jbqk.setJbtjjs2(jbtjjs2);
			// 其他自筹项目 qtzc1
			Double qtzc1 = 0d;
			Double qtzc2 = 0d;
			qtzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '其他自筹项目'    and bookKDate = ?  and  fathreName = '企业自筹资本性支出'  ",
							months);
			qtzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '其他自筹项目'   and bookKDate = ?  and  fathreName = '企业自筹资本性支出'    ",
							months);
			jbqk.setQtzc1(qtzc1);
			jbqk.setQtzc2(qtzc2);
			// （五）对外捐赠支出总额
			Double qzzc1 = 0d;
			Double qzzc2 = 0d;
			qzzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '对外捐赠支出总额'    and bookKDate = ?    ",
							months);
			qzzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '对外捐赠支出总额'   and bookKDate = ?      ",
							months);
			jbqk.setQzzc1(qzzc1);
			jbqk.setQzzc2(qzzc2);
			// （六）职工培训费用 zgpxfy1
			Double zgpxfy1 = 0d;
			Double zgpxfy2 = 0d;
			zgpxfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowMoney -lendMoney   from SubBudgetRate where name = '职工培训费用 '    and bookKDate = ?    ",
							months);
			zgpxfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select borrowJieyuMoney -lendJieyuMoney   from SubBudgetRate where name = '职工培训费用 '   and bookKDate = ?      ",
							months);
			jbqk.setZgpxfy1(zgpxfy1);
			jbqk.setZgpxfy2(zgpxfy2);
			// 隐性债务 yxzw1
			Double yxzw1 = 0d;
			Double yxzw2 = 0d;
			yxzw1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name in('对外担保','抵质押资产','或有负债','优先股','永续债券')     and bookKDate = ?    ",
							months);
			yxzw2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name in('对外担保','抵质押资产','或有负债','优先股','永续债券')  and bookKDate = ?      ",
							months);
			jbqk.setYxzw1(yxzw1);
			jbqk.setYxzw2(yxzw2);
			// 对外担保 dwdb1
			Double dwdb1 = 0d;
			Double dwdb2 = 0d;
			dwdb1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name in('对外担保')     and bookKDate = ?    ",
							months);
			dwdb2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name in('对外担保')  and bookKDate = ?      ",
							months);
			jbqk.setDwdb1(dwdb1);
			jbqk.setDwdb2(dwdb2);
			// dzyzc1 2.抵质押资产
			Double dzyzc1 = 0d;
			Double dzyzc2 = 0d;
			dzyzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name in('抵质押资产')     and bookKDate = ?    ",
							months);
			dzyzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name in('抵质押资产')  and bookKDate = ?      ",
							months);
			jbqk.setDzyzc1(dzyzc1);
			jbqk.setDzyzc2(dzyzc2);
			// 或有负债 hyfz1
			Double hyfz1 = 0d;
			Double hyfz2 = 0d;
			hyfz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name in('或有负债')     and bookKDate = ?    ",
							months);
			hyfz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name in('或有负债')  and bookKDate = ?      ",
							months);
			jbqk.setHyfz1(hyfz1);
			jbqk.setHyfz2(hyfz2);
			// 优先股 yxg1
			Double yxg1 = 0d;
			Double yxg2 = 0d;
			yxg1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name in('优先股')     and bookKDate = ?    ",
							months);
			yxg2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name in('优先股')  and bookKDate = ?      ",
							months);
			jbqk.setYxg1(yxg1);
			jbqk.setYxg2(yxg2);
			// 永续债券 yxzq1
			Double yxzq1 = 0d;
			Double yxzq2 = 0d;
			yxzq1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name in('永续债券')     and bookKDate = ?    ",
							months);
			yxzq2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name in('永续债券')  and bookKDate = ?      ",
							months);
			jbqk.setYxzq1(yxzq1);
			jbqk.setYxzq2(yxzq2);
			// （八）逾期债务 yqzw1
			Double yqzw1 = 0d;
			Double yqzw2 = 0d;
			yqzw1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name in('逾期债务')     and bookKDate = ?    ",
							months);
			yqzw2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name in('逾期债务')  and bookKDate = ?      ",
							months);
			jbqk.setYqzw1(yqzw1);
			jbqk.setYqzw2(yqzw2);
			// 1.逾期银行借款 yqyhjk1
			Double yqyhjk1 = 0d;
			Double yqyhjk2 = 0d;
			yqyhjk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name in('逾期银行借款')     and bookKDate = ?    ",
							months);
			yqyhjk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name in('逾期银行借款')  and bookKDate = ?      ",
							months);
			jbqk.setYqzw1(yqzw1);
			jbqk.setYqzw2(yqzw2);
			// 2.逾期应付账款 yqyfzk1
			Double yqyfzk1 = 0d;
			Double yqyfzk2 = 0d;
			yqyfzk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '逾期应付账款'   and bookKDate = ?    ",
							months);
			yqyfzk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '逾期应付账款'  and bookKDate = ?      ",
							months);
			jbqk.setYqyfzk1(yqyfzk1);
			jbqk.setYqyfzk2(yqyfzk2);
			// 3.逾期对外担保 yqdwdb1
			Double yqdwdb1 = 0d;
			Double yqdwdb2 = 0d;
			yqdwdb1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '逾期对外担保'   and bookKDate = ?    ",
							months);
			yqdwdb2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '逾期对外担保'  and bookKDate = ?      ",
							months);
			jbqk.setYqdwdb1(yqdwdb1);
			jbqk.setYqdwdb2(yqdwdb2);
			// （九）资金情况 zjqk1
			Double zjqk1 = 0d;
			Double zjqk2 = 0d;
			zjqk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '资金情况'   and bookKDate = ?    ",
							months);
			zjqk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '资金情况'  and bookKDate = ?      ",
							months);
			jbqk.setZjqk1(zjqk1);
			jbqk.setZjqk2(zjqk2);
			// 1.资金集中度（%） zjjzd1
			Double zjjzd1 = 0d;
			Double zjjzd2 = 0d;
			zjjzd1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '资金集中度'   and bookKDate = ?    ",
							months);
			zjjzd2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '资金集中度'  and bookKDate = ?      ",
							months);
			jbqk.setZjjzd1(zjjzd1);
			jbqk.setZjjzd2(zjjzd2);
			// 其中：通过财务公司归集的资金 cwgsgjzj1
			Double cwgsgjzj1 = 0d;
			Double cwgsgjzj2 = 0d;
			cwgsgjzj1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '通过财务公司归集的资金'   and bookKDate = ?    ",
							months);
			cwgsgjzj2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '通过财务公司归集的资金'  and bookKDate = ?      ",
							months);
			jbqk.setCwgsgjzj1(cwgsgjzj1);
			jbqk.setCwgsgjzj2(cwgsgjzj2);
			// 通过资金结算中心归集的资金 zjjsgjzj1
			Double zjjsgjzj1 = 0d;
			Double zjjsgjzj2 = 0d;
			zjjsgjzj1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '通过资金结算中心归集的资金'   and bookKDate = ?    ",
							months);
			zjjsgjzj2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '通过资金结算中心归集的资金'  and bookKDate = ?      ",
							months);
			jbqk.setZjjsgjzj1(zjjsgjzj1);
			jbqk.setZjjsgjzj2(zjjsgjzj2);
			// 2.银行抽贷总额 yhcdze1
			Double yhcdze1 = 0d;
			Double yhcdze2 = 0d;
			yhcdze1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '银行抽贷总额'   and bookKDate = ?    ",
							months);
			yhcdze2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '银行抽贷总额'  and bookKDate = ?      ",
							months);
			jbqk.setYhcdze1(yhcdze1);
			jbqk.setYhcdze2(yhcdze2);
			/**** 二、其他指标 ****/
			// （一）工业总产值（现行价格）
			Double gyzcz1 = 0d;
			Double gyzcz2 = 0d;
			gyzcz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '工业总产值'   and bookKDate = ?    ",
							months);
			gyzcz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '工业总产值'  and bookKDate = ?      ",
							months);
			jbqk.setGyzcz1(gyzcz1);
			jbqk.setGyzcz2(gyzcz2);
			// （二）劳动生产总值（现行价格） ldsczz1
			Double ldsczz1 = 0d;
			Double ldsczz2 = 0d;
			ldsczz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '劳动生产总值'   and bookKDate = ?    ",
							months);
			ldsczz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '劳动生产总值'  and bookKDate = ?      ",
							months);
			jbqk.setLdsczz1(ldsczz1);
			jbqk.setLdsczz2(ldsczz2);
			// （三）工业销售产值 gyxscz1
			Double gyxscz1 = 0d;
			Double gyxscz2 = 0d;
			gyxscz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '工业销售产值'   and bookKDate = ?    ",
							months);
			gyxscz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '工业销售产值'  and bookKDate = ?      ",
							months);
			jbqk.setGyxscz1(gyxscz1);
			jbqk.setGyxscz2(gyxscz2);
			// （四）新产品产值 xcpcz1
			Double xcpcz1 = 0d;
			Double xcpcz2 = 0d;
			xcpcz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '新产品产值'   and bookKDate = ?    ",
							months);
			xcpcz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '新产品产值'  and bookKDate = ?      ",
							months);
			jbqk.setXcpcz1(xcpcz1);
			jbqk.setXcpcz2(xcpcz2);
			// （五）本期支出的节能减排费用 jnjpf1
			Double jnjpf1 = 0d;
			Double jnjpf2 = 0d;
			jnjpf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '节能减排费用'   and bookKDate = ?    ",
							months);
			jnjpf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '节能减排费用'  and bookKDate = ?      ",
							months);
			jbqk.setJnjpf1(jnjpf1);
			jbqk.setJnjpf2(jnjpf2);
			// 手持订单额 scdde1
			Double scdde1 = 0d;
			Double scdde2 = 0d;
			scdde1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '手持订单额'   and bookKDate = ?    ",
							months);
			scdde2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '手持订单额'  and bookKDate = ?      ",
							months);
			jbqk.setScdde1(scdde1);
			jbqk.setScdde2(scdde2);
			// 其中：国内订单 gndd1
			Double gndd1 = 0d;
			Double gndd2 = 0d;
			gndd1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '国内订单'   and bookKDate = ?  and fathreName = '手持订单额'   ",
							months);
			gndd2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '国内订单'  and bookKDate = ?   and fathreName = '手持订单额'    ",
							months);
			jbqk.setGndd1(gndd1);
			jbqk.setGndd2(gndd2);
			// /其中：军品订单 jpdd1
			Double jpdd1 = 0d;
			Double jpdd2 = 0d;
			jpdd1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '军品订单'   and bookKDate = ?  and fathreName = '国内订单'   ",
							months);
			jpdd2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '军品订单'  and bookKDate = ?   and fathreName = '国内订单'    ",
							months);
			jbqk.setJpdd1(jpdd1);
			jbqk.setJpdd2(jpdd2);
			// 民品订单 mpdd1
			Double mpdd1 = 0d;
			Double mpdd2 = 0d;
			mpdd1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '民品订单'   and bookKDate = ?  and fathreName = '国内订单'   ",
							months);
			mpdd2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '民品订单'  and bookKDate = ?   and fathreName = '国内订单'    ",
							months);
			jbqk.setMpdd1(mpdd1);
			jbqk.setMpdd2(mpdd2);
			// 国外订单 gwdd1
			Double gwdd1 = 0d;
			Double gwdd2 = 0d;
			gwdd1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '国外订单'   and bookKDate = ?  and fathreName = '手持订单额'   ",
							months);
			gwdd2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '国外订单'  and bookKDate = ?   and fathreName = '手持订单额'    ",
							months);
			jbqk.setGwdd1(gwdd1);
			jbqk.setGwdd2(gwdd2);
			// 其中：军贸订单
			Double jmdd1 = 0d;
			Double jmdd2 = 0d;
			jmdd1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '军贸订单'   and bookKDate = ?  and fathreName = '国外订单'   ",
							months);
			jmdd2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '军贸订单'  and bookKDate = ?   and fathreName = '国外订单'    ",
							months);
			jbqk.setJmdd1(jmdd1);
			jbqk.setJmdd2(jmdd2);
			// 民品订单 gwmpdd1
			Double gwmpdd1 = 0d;
			Double gwmpdd2 = 0d;
			gwmpdd1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '民品订单'   and bookKDate = ?  and fathreName = '国外订单'   ",
							months);
			gwmpdd2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '民品订单'  and bookKDate = ?   and fathreName = '国外订单'    ",
							months);
			jbqk.setGwmpdd1(gwmpdd1);
			jbqk.setGwmpdd2(gwmpdd2);
			// 七）固定资产投资额 gdzctze1
			Double gdzctze1 = 0d;
			Double gdzctze2 = 0d;
			gdzctze1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '固定资产投资额'   and bookKDate = ?    ",
							months);
			gdzctze2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '固定资产投资额'  and bookKDate = ?   ",
							months);
			jbqk.setGdzctze1(gdzctze1);
			jbqk.setGdzctze2(gdzctze2);
			// 出口产品销售收入 ckcpxssr1
			Double ckcpxssr1 = 0d;
			Double ckcpxssr2 = 0d;
			ckcpxssr1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '出口产品销售收入'   and bookKDate = ?    ",
							months);
			ckcpxssr2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '出口产品销售收入'  and bookKDate = ?   ",
							months);
			jbqk.setCkcpxssr1(ckcpxssr1);
			jbqk.setCkcpxssr2(ckcpxssr2);
			// 银行借款 yhjk1
			Double yhjk1 = 0d;
			Double yhjk2 = 0d;
			yhjk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '银行借款'   and bookKDate = ?    ",
							months);
			yhjk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '银行借款'  and bookKDate = ?   ",
							months);
			jbqk.setYhjk1(yhjk1);
			jbqk.setYhjk2(yhjk2);
			// 应交税费 yjsf1
			Double yjsf_jb1 = 0d;
			Double yjsf_jb2 = 0d;
			yjsf_jb1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '应交税费'   and bookKDate = ?  and belongLayer = 1  ",
							months);
			yjsf_jb2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '应交税费'  and bookKDate = ? and belongLayer = 1   ",
							months);
			jbqk.setYjsf1(yjsf_jb1);
			jbqk.setYjsf2(yjsf_jb2);
			// // 1.应交增值税 yjzzs1
			Double yjzzs1 = 0d;
			Double yjzzs2 = 0d;
			yjzzs1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '应交增值税'   and bookKDate = ?  and fathreName = '应交税费'  ",
							months);
			yjzzs2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '应交增值税'  and bookKDate = ? and fathreName = '应交税费'   ",
							months);
			jbqk.setYjzzs1(yjzzs1);
			jbqk.setYjzzs2(yjzzs2);
			// 2.应交消费税 yjxfs1
			Double yjxfs1 = 0d;
			Double yjxfs2 = 0d;
			yjxfs1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '应交消费税'   and bookKDate = ?  and fathreName = '应交税费'  ",
							months);
			yjxfs2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '应交消费税'  and bookKDate = ? and fathreName = '应交税费'   ",
							months);
			jbqk.setYjxfs1(yjxfs1);
			jbqk.setYjxfs2(yjxfs2);
			// 3.应交所得税 yjsds1
			Double yjsds_jb1 = 0d;
			Double yjsds_jb2 = 0d;
			yjsds_jb1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '应交所得税'   and bookKDate = ?  and fathreName = '应交税费'  ",
							months);
			yjsds_jb2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '应交消费税'  and bookKDate = ? and fathreName = '应交税费'   ",
							months);
			jbqk.setYjsds1(yjsds_jb1);
			jbqk.setYjsds2(yjsds_jb2);
			// （十一）已交税费 yijsf1
			Double yijsf1 = 0d;
			Double yijsf2 = 0d;
			yijsf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '已交税费'   and bookKDate = ?   and belongLayer = 1 ",
							months);
			yijsf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '已交税费'  and bookKDate = ? and belongLayer = 1   ",
							months);
			jbqk.setYijsf1(yijsf1);
			jbqk.setYijsf2(yijsf2);
			// 1.已交增值税 yijzzs1
			Double yijzzs1 = 0d;
			Double yijzzs2 = 0d;
			yijzzs1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '已交增值税'   and bookKDate = ?    and fathreName = '已交税费'  ",
							months);
			yijzzs2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '已交增值税'  and bookKDate = ?  and fathreName = '已交税费'   ",
							months);
			jbqk.setYijzzs1(yijzzs1);
			jbqk.setYijzzs2(yijzzs2);
			// 2.已交消费税 yijxfs1
			Double yijxfs1 = 0d;
			Double yijxfs2 = 0d;
			yijxfs1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '已交消费税'   and bookKDate = ?    and fathreName = '已交税费'  ",
							months);
			yijxfs2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '已交消费税'  and bookKDate = ?  and fathreName = '已交税费'   ",
							months);
			jbqk.setYijxfs1(yijxfs1);
			jbqk.setYijxfs2(yijxfs2);
			// 3.已交所得税 yijsds1
			Double yijsds1 = 0d;
			Double yijsds2 = 0d;
			yijsds1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '已交所得税'   and bookKDate = ?    and fathreName = '已交税费'  ",
							months);
			yijsds2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '已交所得税'  and bookKDate = ?  and fathreName = '已交税费'   ",
							months);
			jbqk.setYijsds1(yijsds1);
			jbqk.setYijsds2(yijsds2);
			// （十二）投资额 tze1
			Double tze1 = 0d;
			Double tze2 = 0d;
			tze1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '投资额'   and bookKDate = ?      ",
							months);
			tze2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '投资额'  and bookKDate = ?    ",
							months);
			jbqk.setTze1(tze1);
			jbqk.setTze2(tze2);
			// 其中：科研生产投资额 kysctz1
			Double kysctz1 = 0d;
			Double kysctz2 = 0d;
			kysctz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '科研生产投资额'   and bookKDate = ?   and fathreName = '投资额'   ",
							months);
			kysctz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '科研生产投资额'  and bookKDate = ?  and fathreName = '投资额 '  ",
							months);
			jbqk.setKysctz1(kysctz1);
			jbqk.setKysctz2(kysctz2);
			// 其中：军品科研生产投资额
			Double jpkysctz1 = 0d;
			Double jpkysctz2 = 0d;
			jpkysctz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '军品科研生产投资额'   and bookKDate = ?   and fathreName = '投资额'   ",
							months);
			jpkysctz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '军品科研生产投资额'  and bookKDate = ?  and fathreName = '投资额 '  ",
							months);
			jbqk.setJpkysctz1(jpkysctz1);
			jbqk.setJpkysctz2(jpkysctz2);
			// （十三）战略性新兴产业产品销售收入 zlxcpsr1
			Double zlxcpsr1 = 0d;
			Double zlxcpsr2 = 0d;
			zlxcpsr1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '战略性新兴产业产品销售收入'   and bookKDate = ?      ",
							months);
			zlxcpsr2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '战略性新兴产业产品销售收入'  and bookKDate = ?    ",
							months);
			jbqk.setZlxcpsr1(zlxcpsr1);
			jbqk.setZlxcpsr2(zlxcpsr2);
			// （十四）国防工业维护与服务市场收入 gywhyfwsc1
			Double gywhyfwsc1 = 0d;
			Double gywhyfwsc2 = 0d;
			gywhyfwsc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '国防工业维护与服务市场收入'   and bookKDate = ?      ",
							months);
			gywhyfwsc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '国防工业维护与服务市场收入'  and bookKDate = ?    ",
							months);
			jbqk.setGywhyfwsc1(gywhyfwsc1);
			jbqk.setGywhyfwsc2(gywhyfwsc2);
			// （十五）利润总额预算数 lrzeyss1
			Double lrzeyss1 = 0d;
			Double lrzeyss2 = 0d;
			lrzeyss1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '利润总额预算数'   and bookKDate = ?      ",
							months);
			lrzeyss2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '利润总额预算数'  and bookKDate = ?    ",
							months);
			jbqk.setLrzeyss1(lrzeyss1);
			jbqk.setLrzeyss2(lrzeyss2);
			// （十六）净利润预算数 jlryss1
			Double jlryss1 = 0d;
			Double jlryss2 = 0d;
			jlryss1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '利润总额预算数'   and bookKDate = ?      ",
							months);
			jlryss2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '利润总额预算数'  and bookKDate = ?    ",
							months);
			jbqk.setLrzeyss1(lrzeyss1);
			jbqk.setLrzeyss2(lrzeyss2);
			// （十七）企业研发投入 qyyftr1
			Double qyyftr1 = 0d;
			Double qyyftr2 = 0d;
			qyyftr1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '企业研发投入'   and bookKDate = ?      ",
							months);
			qyyftr2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '企业研发投入'  and bookKDate = ?    ",
							months);
			jbqk.setQyyftr1(qyyftr1);
			jbqk.setQyyftr2(qyyftr2);
			// 其中：政府拨款 zfbk1
			Double zfbk1 = 0d;
			Double zfbk2 = 0d;
			zfbk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '政府拨款'   and bookKDate = ?  and  fathreName = '政府拨款'    ",
							months);
			zfbk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '政府拨款'  and bookKDate = ? and  fathreName = '政府拨款'    ",
							months);
			jbqk.setZfbk1(zfbk1);
			jbqk.setZfbk2(zfbk2);
			// 企业自筹 qyzc1
			Double qyzc1 = 0d;
			Double qyzc2 = 0d;
			qyzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '企业自筹'   and bookKDate = ?  and  fathreName = '政府拨款'    ",
							months);
			qyzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '企业自筹'  and bookKDate = ? and  fathreName = '政府拨款'    ",
							months);
			jbqk.setQyzc1(qyzc1);
			jbqk.setQyzc2(qyzc2);
			// （十八）节能减排指标 jnjpzb1
			Double jnjpzb1 = 0d;
			Double jnjpzb2 = 0d;
			jnjpzb1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '节能减排指标'   and bookKDate = ?  ",
							months);
			jnjpzb2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '节能减排指标'  and bookKDate = ?  ",
							months);
			jbqk.setJnjpzb1(jnjpzb1);
			jbqk.setJnjpzb2(jnjpzb2);
			// 1.节能减排投入总额 jnjptrze1
			Double jnjptrze1 = 0d;
			Double jnjptrze2 = 0d;
			jnjptrze1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '节能减排投入总额'   and bookKDate = ?  and fathreName = '节能减排指标' ",
							months);
			jnjptrze2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '节能减排投入总额'  and bookKDate = ? and  fathreName = '节能减排指标'   ",
							months);
			jbqk.setJnjptrze1(jnjptrze1);
			jbqk.setJnjptrze2(jnjptrze2);
			// 其中：政府拨款 jnzfbk1
			Double jnzfbk1 = 0d;
			Double jnzfbk2 = 0d;
			jnzfbk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '政府拨款'   and bookKDate = ?  and fathreName = '节能减排投入总额' and rootId in (select id from SubBudgetRate where name = '节能减排指标' and belongLayer = 1 ) ",
							months);
			jnzfbk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '政府拨款'  and bookKDate = ? and  fathreName = '节能减排投入总额'  and rootId in (select id from SubBudgetRate where name = '节能减排指标' and belongLayer = 1 )   ",
							months);
			jbqk.setJnzfbk1(jnzfbk1);
			jbqk.setJnzfbk2(jnzfbk2);
			// 企业自筹 jnqyzc1
			Double jnqyzc1 = 0d;
			Double jnqyzc2 = 0d;
			jnqyzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '企业自筹'   and bookKDate = ?  and fathreName = '节能减排投入总额' and rootId in (select id from SubBudgetRate where name = '节能减排指标' and belongLayer = 1 ) ",
							months);
			jnqyzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '企业自筹'  and bookKDate = ? and  fathreName = '节能减排投入总额'  and rootId in (select id from SubBudgetRate where name = '节能减排指标' and belongLayer = 1 )   ",
							months);
			jbqk.setJnqyzc1(jnqyzc1);
			jbqk.setJnqyzc2(jnqyzc2);
			// 2.万元产值综合能耗（吨标煤） wrczzhnh1
			Double wrczzhnh1 = 0d;
			Double wrczzhnh2 = 0d;
			wrczzhnh1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '万元产值综合能耗（吨标煤）'   and bookKDate = ?  and fathreName = '节能减排指标'  ",
							months);
			wrczzhnh2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '万元产值综合能耗（吨标煤）'  and bookKDate = ? and  fathreName = '节能减排指标'   ",
							months);
			jbqk.setWrczzhnh1(wrczzhnh1);
			jbqk.setWrczzhnh2(wrczzhnh2);
			// 3.吨钢综合能耗（千克标煤／吨） dgzhnh1
			Double dgzhnh1 = 0d;
			Double dgzhnh2 = 0d;
			dgzhnh1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '吨钢综合能耗（千克标煤／吨）'   and bookKDate = ?  and fathreName = '节能减排指标'  ",
							months);
			dgzhnh2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '吨钢综合能耗（千克标煤／吨）'  and bookKDate = ? and  fathreName = '节能减排指标'   ",
							months);
			jbqk.setDgzhnh1(dgzhnh1);
			jbqk.setDgzhnh2(dgzhnh2);
			// 4.供电煤耗（克／千瓦时） gdhf1
			Double gdhf1 = 0d;
			Double gdhf2 = 0d;
			gdhf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '供电煤耗（克／千瓦时）'   and bookKDate = ?  and fathreName = '节能减排指标'  ",
							months);
			gdhf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '供电煤耗（克／千瓦时）'  and bookKDate = ? and  fathreName = '节能减排指标'   ",
							months);
			jbqk.setGdhf1(gdhf1);
			jbqk.setGdhf2(gdhf2);
			// （十九）军用固定资产 jygdzc1
			Double jygdzc1 = 0d;
			Double jygdzc2 = 0d;
			jygdzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '军用固定资产'   and bookKDate = ?    ",
							months);
			jygdzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '军用固定资产'  and bookKDate = ?    ",
							months);
			jbqk.setJygdzc1(jygdzc1);
			jbqk.setJygdzc2(jygdzc2);
			// （二十）商业保险费用全年预计 sybxf1
			Double sybxf1 = 0d;
			Double sybxf2 = 0d;
			sybxf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '商业保险费用全年预计'   and bookKDate = ?    ",
							months);
			sybxf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '商业保险费用全年预计'  and bookKDate = ?    ",
							months);
			jbqk.setSybxf1(sybxf1);
			jbqk.setSybxf2(sybxf2);
			// 商业保险费用实际支出 sybxfsjzc1
			Double sybxfsjzc1 = 0d;
			Double sybxfsjzc2 = 0d;
			sybxfsjzc1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '商业保险费用实际支出'   and bookKDate = ?  and  fathreName =  '商业保险费用全年预计' ",
							months);
			sybxfsjzc2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '商业保险费用实际支出'  and bookKDate = ?  and  fathreName =  '商业保险费用全年预计'   ",
							months);
			jbqk.setSybxfsjzc1(sybxfsjzc1);
			jbqk.setSybxfsjzc2(sybxfsjzc2);
			// 其中：财产险费用 ccxfy1
			Double ccxfy1 = 0d;
			Double ccxfy2 = 0d;
			ccxfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '财产险费用'   and bookKDate = ?  and  fathreName =  '商业保险费用实际支出'   ",
							months);
			ccxfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '财产险费用'  and bookKDate = ?   and  fathreName =  '商业保险费用实际支出'     ",
							months);
			jbqk.setCcxfy1(ccxfy1);
			jbqk.setCcxfy2(ccxfy2);
			// 车险费用 cxfy1
			Double cxfy1 = 0d;
			Double cxfy2 = 0d;
			cxfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '车险费用'   and bookKDate = ?  and  fathreName =  '商业保险费用实际支出'   ",
							months);
			cxfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '车险费用'  and bookKDate = ?   and  fathreName =  '商业保险费用实际支出'     ",
							months);
			jbqk.setCxfy1(cxfy1);
			jbqk.setCxfy2(cxfy2);
			// 人身险费用（不含社保五险） rsxfy1
			Double rsxfy1 = 0d;
			Double rsxfy2 = 0d;
			rsxfy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '车险费用'   and bookKDate = ?  and  fathreName =  '商业保险费用实际支出'   ",
							months);
			rsxfy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '车险费用'  and bookKDate = ?   and  fathreName =  '商业保险费用实际支出'     ",
							months);
			jbqk.setCxfy1(cxfy1);
			jbqk.setCxfy2(cxfy2);
			// （二十一）零余额账户用款额度 lyezwyked1
			Double lyezwyked1 = 0d;
			Double lyezwyked2 = 0d;
			lyezwyked1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '零余额账户用款额度'   and bookKDate = ?     ",
							months);
			lyezwyked2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '零余额账户用款额度'  and bookKDate = ?      ",
							months);
			jbqk.setLyezwyked1(lyezwyked1);
			jbqk.setLyezwyked2(lyezwyked2);
			// 年初结余（财政应返还额度年初数） ncjy1
			Double ncjy1 = 0d;
			Double ncjy2 = 0d;
			ncjy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '年初结余'   and bookKDate = ?  and   fathreName = '零余额账户用款额度' ",
							months);
			ncjy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '年初结余'  and bookKDate = ?  fathreName = '零余额账户用款额度'     ",
							months);
			jbqk.setNcjy1(ncjy1);
			jbqk.setNcjy2(ncjy2);
			// 当年新增 dnxz1
			Double dnxz1 = 0d;
			Double dnxz2 = 0d;
			dnxz1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '当年新增'   and bookKDate = ?  and   fathreName = '零余额账户用款额度' ",
							months);
			dnxz2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '当年新增'  and bookKDate = ?  fathreName = '零余额账户用款额度'     ",
							months);
			jbqk.setDnxz1(dnxz1);
			jbqk.setDnxz2(dnxz2);
			// 当年支付 dnzf1
			Double dnzf1 = 0d;
			Double dnzf2 = 0d;
			dnzf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '当年支付'   and bookKDate = ?  and   fathreName = '零余额账户用款额度' ",
							months);
			dnzf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '当年支付'  and bookKDate = ?  fathreName = '零余额账户用款额度'     ",
							months);
			jbqk.setDnzf1(dnzf1);
			jbqk.setDnzf2(dnzf2);
			// 当年结余 dnjy1
			Double dnjy1 = 0d;
			Double dnjy2 = 0d;
			dnjy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '当年结余'   and bookKDate = ?  and   fathreName = '零余额账户用款额度' ",
							months);
			dnjy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '当年结余'  and bookKDate = ?  fathreName = '零余额账户用款额度'     ",
							months);
			jbqk.setDnjy1(dnjy1);
			jbqk.setDnjy2(dnjy2);
			// 预计下月支付 yjxyzf1
			Double yjxyzf1 = 0d;
			Double yjxyzf2 = 0d;
			yjxyzf1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '预计下月支付'   and bookKDate = ?  and   fathreName = '零余额账户用款额度' ",
							months);
			yjxyzf2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '预计下月支付'  and bookKDate = ? and  fathreName = '零余额账户用款额度'     ",
							months);
			jbqk.setYjxyzf1(yjxyzf1);
			jbqk.setYjxyzf2(yjxyzf2);
			// （二十二）出口额
			Double cke1 = 0d;
			Double cke2 = 0d;
			cke1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '出口额'   and bookKDate = ?  ",
							months);
			cke2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '出口额'  and bookKDate = ?   ",
							months);
			jbqk.setCke1(cke1);
			jbqk.setCke2(cke2);
			// 其中：军品出口额 jpcke1
			Double jpcke1 = 0d;
			Double jpcke2 = 0d;
			jpcke1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '军品出口额'   and bookKDate = ?  and  fathreName = '出口额' ",
							months);
			jpcke2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '军品出口额'  and bookKDate = ? and  fathreName = '出口额'  ",
							months);
			jbqk.setJpcke1(jpcke1);
			jbqk.setJpcke2(jpcke2);
			// 民品出口额 mpcke1
			Double mpcke1 = 0d;
			Double mpcke2 = 0d;
			mpcke1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '民品出口额'   and bookKDate = ?  and  fathreName = '出口额' ",
							months);
			mpcke2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '民品出口额'  and bookKDate = ? and  fathreName = '出口额'  ",
							months);
			jbqk.setMpcke1(mpcke1);
			jbqk.setMpcke2(mpcke2);
			// 二十三）军品应收账款 jpyszk1
			Double jpyszk1 = 0d;
			Double jpyszk2 = 0d;
			jpyszk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '军品应收账款'   and bookKDate = ?   ",
							months);
			jpyszk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '军品应收账款'  and bookKDate = ?  ",
							months);
			jbqk.setJpyszk1(jpyszk1);
			jbqk.setJpyszk2(jpyszk2);
			// （二十四）军品应付账款 jpyfzk1
			Double jpyfzk1 = 0d;
			Double jpyfzk2 = 0d;
			jpyfzk1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '军品应付账款'   and bookKDate = ?   ",
							months);
			jpyfzk2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '军品应付账款'  and bookKDate = ?  ",
							months);
			jbqk.setJpyfzk1(jpyfzk1);
			jbqk.setJpyfzk2(jpyfzk2);
			// （二十五）军品存货 jpch1
			Double jpch1 = 0d;
			Double jpch2 = 0d;
			jpch1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '军品存货'   and bookKDate = ?   ",
							months);
			jpch2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '军品存货'  and bookKDate = ?  ",
							months);
			jbqk.setJpch1(jpch1);
			jbqk.setJpch2(jpch2);
			// 其中：产成品 ccp1
			Double ccp1 = 0d;
			Double ccp2 = 0d;
			jpch1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '产成品'   and bookKDate = ?  and  fathreName = '军品存货' ",
							months);
			jpch2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '产成品'  and bookKDate = ? and  fathreName = '军品存货' ",
							months);
			jbqk.setJpch1(jpch1);
			jbqk.setJpch2(jpch2);
			// （二十六）事业单位结余（科研事业单位填列） sydwjy1
			Double sydwjy1 = 0d;
			Double sydwjy2 = 0d;
			sydwjy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '事业单位结余'   and bookKDate = ?  ",
							months);
			sydwjy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '事业单位结余'  and bookKDate = ? ",
							months);
			jbqk.setSydwjy1(sydwjy1);
			jbqk.setSydwjy2(sydwjy2);
			// 科研项目阶段完成结余 kyxmjdwcjy1
			Double kyxmjdwcjy1 = 0d;
			Double kyxmjdwcjy2 = 0d;
			kyxmjdwcjy1 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowMoney -lendMoney)   from SubBudgetRate where name = '科研项目阶段完成结余'   and bookKDate = ? and fathreName = '事业单位结余' ",
							months);
			kyxmjdwcjy2 = (Double) totalDao
					.getObjectByConditionforDouble(
							" select sum(borrowJieyuMoney -lendJieyuMoney)   from SubBudgetRate where name = '科研项目阶段完成结余'  and bookKDate = ?  and fathreName = '事业单位结余' ",
							months);
			jbqk.setKyxmjdwcjy1(kyxmjdwcjy1);
			jbqk.setKyxmjdwcjy2(kyxmjdwcjy2);
			jbqk.setCompanyName(companyName);
			jbqk.setMonths(months);
			totalDao.save(jbqk);

		}

		// }
	}

	@Override
	public boolean addAccountsDate(AccountsDate accountsDate) {
		if (accountsDate != null) {
			return totalDao.save(accountsDate);
		}
		return false;
	}

	@Override
	public List<AccountsDate> FindAccountsDate() {
		return totalDao.query(" from AccountsDate");
	}

	@Override
	public boolean delAccountsDate(AccountsDate accountsDate) {
		if (accountsDate != null) {
			return totalDao.delete(accountsDate);
		}
		return false;
	}

	@Override
	public Object[] findALlCheckrecord(Checkrecord checkrecord, int pageNo,
			int pageSize, String tag) {
		if (checkrecord == null) {
			checkrecord = new Checkrecord();
		}
		Measuring measuring = checkrecord.getMeasuring();
		String hql_other = "";
		if (measuring != null) {
			hql_other = totalDao.criteriaQueries(measuring, null);
			if (hql_other != null && hql_other.length() > 0) {
				hql_other = " and MeasuringId in ( select id " + hql_other
						+ ")";
			}
		}
		String hql = totalDao.criteriaQueries(checkrecord, null, "measuring");
		if ("lj".equals(tag)) {
			hql += " and parClass = '量具'";
		} else if ("jj".equals(tag)) {
			hql += " and parClass = '检具'";
		} else if ("gj".equals(tag)) {
			hql += " and parClass = '工具'";
		}
		List<Checkrecord> list = totalDao.findAllByPage(hql + hql_other
				+ " order by id desc", pageNo, pageSize);
		int count = totalDao.getCount(hql + hql_other);
		if (list != null && list.size() > 0) {
			for (Checkrecord checkrecord2 : list) {
				Measuring measuring0 = (Measuring) totalDao.get(
						Measuring.class, checkrecord2.getMeasuringId());
				checkrecord2.setMeasuring(measuring0);
			}
		}

		return new Object[] { list, count };
	}

	@Override
	public Measuring getMeaById(Integer id) {
		if (id != null) {
			return (Measuring) totalDao.get(Measuring.class, id);
		}
		return null;
	}

}