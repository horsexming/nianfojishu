package com.task.ServerImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.MachineDayYZSJServer;
import com.task.Server.MachineMonthDjServer;
import com.task.entity.DJNR;
import com.task.entity.Machine;
import com.task.entity.MachineDayDj;
import com.task.entity.MachineDayYZSJ;
import com.task.entity.MachineMonthDj;
import com.task.util.Util;

public class MachineDayYZSJServerImpl implements MachineDayYZSJServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		MachineDayYZSJServerImpl acc = new MachineDayYZSJServerImpl();
		acc.setTotalDao(totalDao);
		return totalDao;
	}
	@Override
	public boolean add(MachineDayYZSJ mdy) {
		if (mdy != null) {
			String month = Util.getDateTime("yyyy-MM");// 月份
			int monthday = Util.getMonthofday(null);
			String day = Util.getDateTime("yyyy-MM-dd");// 日期
			MachineMonthDj mmd = (MachineMonthDj) totalDao
					.getObjectByCondition(
							"from MachineMonthDj where machine_id=? and machineMonth=?",
							mdy.getMachine_id(), month);
			Machine machine = (Machine) totalDao.get(Machine.class, mdy
					.getMachine_id());
			// 判断设备是不是第一次添加稼动率，如果是则设置;
			if (machine != null && machine.getJdlfirstdate() == null) {
				machine.setJdlfirstdate(Util.getDateTime());
				machine.setJiadonglv(0f);
				machine.setMachine_yzsj(0f);
				totalDao.update(machine);
			}
			// 判断当月该设备是不是第一次点检 ，如果是 ，则新插入一条;
			if (mmd == null) {
				MachineMonthDj mmd1 = new MachineMonthDj();
				mmd1.setMachine_id(mdy.getMachine_id());// 设备Id;
				mmd1.setMachineMonth(month);// 月份
				mmd1.setMachineNo(machine.getNo());// 设备编号
				mmd1.setMachineZTZSJ(monthday * 8d);// 月停转时间
				mmd1.setMachineZWXSJ(0d);// 月维修时间
				mmd1.setMachineZYZSJ(0d);// 月运行时间
				mmd1.setZjiadonglv(0 + "%");// 月稼动率
				totalDao.save(mmd1);
			}
			// 判断当天该设备是不是第一次点检 ，如果是 ，则新插入一条;
			MachineDayYZSJ mdy1 = (MachineDayYZSJ) totalDao
					.getObjectByCondition(
							"from MachineDayYZSJ where machine_id=? and machineday=?",
							mdy.getMachine_id(), day);
			if (mdy1 == null) {
				List<MachineDayDj> mddList = mdy.getMddList();
				Set<MachineDayDj> mdd = new HashSet<MachineDayDj>();
				/* save日点检内容 */
				boolean bool = false;
				if (mddList != null && mddList.size() > 0) {
					for (MachineDayDj m : mddList) {
						if (m.getDj_status() != null
								|| !"".equals(m.getDj_status())) {
							bool = true;
						}
						m.setMachine_id(mdy.getMachine_id());// 设备Id
						m.setMachineDay(Util.getDateTime("yyyy-MM-dd"));// 点检日期
						m.setMachineMonth(Util.getDateTime("yyyy-MM"));// 点检月份
						m.setMachineNo(machine.getNo());// 设备编号
						mdd.add(m);
					}
				}
				mdy.setMachineTZSJ(8d);// 天停转时长;
				mdy.setMachineWXSJ(0d);// 天维修时长;
				mdy.setMachineYZSJ(0d);// 天运行时长
				mdy.setJiadonglv(0 + "%");// 天稼动率
				mdy.setMachineMonth(month);// 月份
				mdy.setMachineday(day);// 日期
				if (bool) {
					mdy.setDj_status("已点检");
				}
				mdy.setMdd(mdd);// 点检内容和对应的状态
				mdy.setMachineNo(machine.getNo());// 设备编号
				return totalDao.save(mdy);
			} else {
				List<MachineDayDj> mddList = mdy.getMddList();
				Set<MachineDayDj> mdd = new HashSet<MachineDayDj>();
				/* update日点检内容 */
				boolean bool = false;
				if (mddList != null && mddList.size() > 0) {
					for (MachineDayDj m : mddList) {
						if (m.getDj_status() != null
								|| !"".equals(m.getDj_status())) {
							bool = true;
						}
						m.setMachine_id(mdy.getMachine_id());// 设备Id
						m.setMachineDay(Util.getDateTime("yyyy-MM-dd"));// 点检日期
						m.setMachineMonth(Util.getDateTime("yyyy-MM"));// 点检月份
						m.setMachineNo(machine.getNo());// 设备编号
						mdd.add(m);
					}
				}
				if (bool) {
					mdy1.setDj_status("已点检");
				}
				mdy1.setMdd(mdd);// 点检内容和对应的状态
				return totalDao.update(mdy1);
			}
		}
		return false;

	}

	@Override
	public boolean del(MachineDayYZSJ mdy) {
		if (mdy != null) {
			return totalDao.delete(mdy);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findDJNRByCondition(MachineDayYZSJ mdy,
			int pageNo, int pageSize) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(mdy, null);
		int count = totalDao.getCount(hql);
		List<MachineDayYZSJ> mdyList = (List<MachineDayYZSJ>) totalDao
				.findAllByPage(hql, pageNo, pageSize);
		map.put(1, mdyList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean update(MachineDayYZSJ mdy, Float sc, String status) {
		if (mdy != null) {
			mdy = (MachineDayYZSJ) totalDao.get(MachineDayYZSJ.class, mdy
					.getId());
			BigDecimal b = new BigDecimal(sc);
			sc = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();// 保留两位小数;
			// 更新当天运转时长和维修时长;
			Double WXSJ = mdy.getMachineWXSJ();
			String month = Util.getDateTime("yyyy-MM");// 月份
			int monthday = Util.getMonthofday(null);
			String day = Util.getDateTime("yyyy-MM-dd");// 日期
			Double YZSJ = mdy.getMachineYZSJ();// 之前的运转时间
			if ("weixiu".equals(status)) {
				mdy.setMachineWXSJ(WXSJ + sc);
				if (mdy.getMachineWXSJ() >= 8) {
					mdy.setMachineWXSJ(8d);
				}
			} else {
				mdy.setMachineYZSJ(YZSJ + sc);
				if (mdy.getMachineYZSJ() >= 8) {
					mdy.setMachineYZSJ(8d);
				}
				mdy.setMachineTZSJ(mdy.getMachineTZSJ() - sc);// 停转时间=之前的停转时间-当前的维修时间（运转时间）
				BigDecimal b1 = new BigDecimal(((YZSJ + sc) / 8) * 100);
				double jdl = b1.setScale(3, BigDecimal.ROUND_HALF_UP)
						.doubleValue();

				mdy.setJiadonglv(jdl + "%");// 稼动率
			}
			// 更新当月的运转时长

			MachineMonthDj mmd = (MachineMonthDj) totalDao
					.getObjectByCondition(
							"from MachineMonthDj where machine_id=? and machineMonth=?",
							mdy.getMachine_id(), month);
			Double ZYZSJ = mmd.getMachineZYZSJ();// 之前当月总运转时间;
			Double ZWXSJ = mmd.getMachineZWXSJ();// 之前当月总维修时间
			if ("weixiu".equals(status)) {
				mmd.setMachineZWXSJ(ZWXSJ + sc);
			} else {
				mmd.setMachineZYZSJ(ZYZSJ + sc);
				if (mmd.getMachineZYZSJ() >= (monthday * 8)) {
					mmd.setMachineZYZSJ((monthday * 8d));
				}
				mmd.setMachineZTZSJ(mmd.getMachineZTZSJ() - sc);// 停转时间=之前的停转时间-当前的维修时间（运转时间）
				BigDecimal b2 = new BigDecimal(
						((ZYZSJ + sc) / (monthday * 8)) * 100);
				double jdl = b2.setScale(3, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				mmd.setZjiadonglv(jdl + "%");
			}
			// 更新设备的总稼动率;
			Machine machine = (Machine) totalDao.get(Machine.class, mdy
					.getMachine_id());
			if (machine != null) {
				machine.setMachine_yzsj(machine.getMachine_yzsj() + sc);
				BigDecimal b3 = new BigDecimal(
						Util.getWorkTime1(machine.getJdlfirstdate(), Util
								.getDateTime()) / 1000 / 60 / 60);
				Float machine_sc = b3.setScale(3, BigDecimal.ROUND_HALF_UP)
						.floatValue();
				BigDecimal b4 = new BigDecimal(machine.getMachine_yzsj()
						/ machine_sc*100);
				Float jdl = b4.setScale(3, BigDecimal.ROUND_HALF_UP)
						.floatValue();
				machine.setJiadonglv(jdl);
			}
			if (totalDao.update(mdy) && totalDao.update(mmd)
					&& totalDao.update(machine)) {
				return true;
			}

		}
		return false;
	}
	public static boolean update1(MachineDayYZSJ mdy, Float sc, String status) {
		if (mdy != null) {
			TotalDao totalDao1 = createTotol();

			List<MachineDayYZSJ> mdyList =  totalDao1.query("from MachineDayYZSJ where id=?", mdy.getId());
			if(mdyList!=null && mdyList.size()>0){
				mdy = mdyList.get(0);
			}
			sc = Util.MacthRound(sc.doubleValue(), 2).floatValue();
			// 更新当天运转时长和维修时长;
			Double WXSJ = mdy.getMachineWXSJ();
			String month = Util.getDateTime("yyyy-MM");// 月份
			int monthday = Util.getMonthofday(null);
			String day = Util.getDateTime("yyyy-MM-dd");// 日期
			Double YZSJ = mdy.getMachineYZSJ() == null?0d:mdy.getMachineYZSJ();// 之前的运转时间
			if ("weixiu".equals(status)) {
				mdy.setMachineWXSJ(WXSJ + sc);
				if (mdy.getMachineWXSJ() >= 8) {
					mdy.setMachineWXSJ(8d);
				}
			} else {
				YZSJ+=sc;
				mdy.setMachineYZSJ(Util.MacthRound(YZSJ, 2));
				if (mdy.getMachineYZSJ() >= 8) {
					mdy.setMachineYZSJ(8d);
				}
				
				BigDecimal b1 = new BigDecimal(((YZSJ + sc) / 8) * 100);
				double jdl =Util.MacthRound((YZSJ/8)*100, 3);
				
				mdy.setJiadonglv(jdl + "%");// 稼动率
			}
			mdy.setMachineTZSJ(Util.MacthRound((8-mdy.getMachineYZSJ()), 3));// 停转时间=之前的停转时间-当前的维修时间（运转时间）
			if(mdy.getMachineTZSJ()<0){
				mdy.setMachineTZSJ(0d);
			}
			// 更新当月的运转时长
			
			List<MachineMonthDj> mmdList =  totalDao1
			.query(
					"from MachineMonthDj where machine_id=? and machineMonth=?",
					mdy.getMachine_id(), month);
			MachineMonthDj mmd = null;
			if(mmdList!=null && mmdList.size()>0){
				 mmd = mmdList.get(0);
			
			Double ZYZSJ = mmd.getMachineZYZSJ();// 之前当月总运转时间;
			Double ZWXSJ = mmd.getMachineZWXSJ();// 之前当月总维修时间
			if ("weixiu".equals(status)) {
				mmd.setMachineZWXSJ(Util.MacthRound(ZWXSJ + sc, 2));
			} else {
				mmd.setMachineZYZSJ(Util.MacthRound(ZYZSJ + sc, 2));
				if (mmd.getMachineZYZSJ() >= (monthday * 8)) {
					mmd.setMachineZYZSJ((monthday * 8d));
				}
				if(mmd.getMachineZTZSJ()<0){
					mmd.setMachineZTZSJ(0d);
				}
				BigDecimal b2 = new BigDecimal(
						((ZYZSJ + sc) / (monthday * 8)) * 100);
				double jdl = b2.setScale(3, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
				mmd.setZjiadonglv(jdl + "%");
			}
			mmd.setMachineZTZSJ(Util.MacthRound((monthday * 8d)-mmd.getMachineZYZSJ(), 3));// 停转时间 = 总时间-运行时间；
			if(mmd.getMachineZTZSJ()<0){
				mmd.setMachineZTZSJ(0d);
			}
			}
			// 更新设备的总稼动率;
			
			List<Machine> machieList =  totalDao1.query("from Machine where id=?", mdy.getMachine_id());
			Machine machine = null;
			if(machieList!=null && machieList.size()>0){
				machine = machieList.get(0);
			}
			if (machine != null) {
				machine.setMachine_yzsj(machine.getMachine_yzsj() + sc);
				if(machine.getJdlfirstdate() == null ||"".equals(machine.getJdlfirstdate() )){
					machine.setJdlfirstdate(machine.getAddTime());
				}
				String time= Util.getDateTime();
				double a = Util.getWorkTime1(machine.getJdlfirstdate(),time );
					a = a/1000/60/60;
				BigDecimal b3 = new BigDecimal(a);
						
				Float machine_sc = b3.setScale(3, BigDecimal.ROUND_HALF_UP)
				.floatValue();
				BigDecimal b4 = new BigDecimal(machine.getMachine_yzsj()
						/ machine_sc*100);
				Float jdl = b4.setScale(3, BigDecimal.ROUND_HALF_UP)
				.floatValue();
				machine.setJiadonglv(jdl);
			}
			if (totalDao1.update2(mdy) && totalDao1.update2(mmd)
					&& totalDao1.update2(machine)) {
				return true;
			}
			
		}
		return false;
	}

	@Override
	public List<MachineDayYZSJ> findmdy(Integer id, String month) {
		if (id != null && id > 0) {
			String hql = "from MachineDayYZSJ where machine_id=" + id;
			if(month!=null && month.length()>0){
				hql +=" and machineMonth = '"+month+"'";
			}
			return (List<MachineDayYZSJ>) totalDao.find(hql);
		}
		return null;
	}

	@Override
	public List<String> getdjnrofmonth(Integer id, String month) {
		List<String> strList = new ArrayList<String>();
		if (id != null && id > 0) {
			if (month == null || "".equals(month)) {
				month = Util.getDateTime("yyyy-MM");
			}
			String hql = "from MachineDayYZSJ where machine_id=" + id
					+ " and machineMonth='" + month + "'";
			List<MachineDayYZSJ> mdyList = totalDao.find(hql);
			Set<String> strSet = new HashSet<String>();
			if (mdyList != null && mdyList.size() > 0) {
				for (MachineDayYZSJ mdy1 : mdyList) {
					List<MachineDayDj> mdd = totalDao
							.find("from MachineDayDj where machine_id="
									+ mdy1.getMachine_id()
									+ " and machineMonth='" + month + "'");
					for (MachineDayDj mdd1 : mdd) {
						strSet.add(mdd1.getMachine_djnr());
					}
				}
			}
			for (String str : strSet) {
				strList.add(str);
			}
		}
		return strList;
	}

	@Override
	public List<MachineDayYZSJ> findmdyofmonth(Integer id, String month) {
		if (id != null && id > 0) {
			if (month == null || "".equals(month)) {
				month = Util.getDateTime("yyyy-MM");
			}
			String hql = "select machineday,machineYZSJ,machineTZSJ,machineWXSJ,jiadonglv from MachineDayYZSJ where machine_id="
					+ id + " and machineMonth='" + month + "'";
			return totalDao.find(hql);
		}
		return null;
	}

	@Override
	public MachineDayYZSJ findmdybyno(String No, String date) {
		if (No != null && No.length() > 0) {
			if (date == null || "".equals(date)) {
				date = Util.getDateTime();
			}
			String hql = "from MachineDayYZSJ where machineNo=? and machineday=?";
			return (MachineDayYZSJ) totalDao
					.getObjectByCondition(hql, No, date);
		}
		return null;
	}

	@Override
	public MachineDayYZSJ getmdybymachineId(Integer id, String date) {
		if (id != null && id > 0) {
			if (date == null || "".equals(date)) {
				date = Util.getDateTime("yyyy-MM-dd");
			}
			String hql = "from MachineDayYZSJ where machine_id=? and machineday=?";
			return (MachineDayYZSJ) totalDao
					.getObjectByCondition(hql, id, date);
		}
		return null;
	}

	@Override
	public Machine getMachine(Integer id) {
		if (id != null && id > 0) {
			return (Machine) totalDao.get(Machine.class, id);
		}
		return null;
	}

	@Override
	public Machine getMachinebyproessId(Integer id) {
		if (id != null && id > 0) {
			String hql = "from Machine where no=(select shebeiNo from ProcessInfor where id=?) "
					+ "and workPosition =(select gongwei from ProcessInfor where id=?)";
			return (Machine) totalDao.getObjectByCondition(hql, id, id);
		}
		return null;
	}

}
