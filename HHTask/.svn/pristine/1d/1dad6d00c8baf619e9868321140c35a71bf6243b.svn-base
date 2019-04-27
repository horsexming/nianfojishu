package com.task.ServerImpl.barandqr;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.barandqr.AirtightLogServer;
import com.task.entity.barandqr.AirMachine;
import com.task.entity.barandqr.AirProduct;
import com.task.entity.barandqr.AirtightLog;
import com.task.util.Util;

public class AirtightLogServerImpl implements AirtightLogServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addBarCode(AirtightLog pageAirtightLog) {
		// TODO Auto-generated method stub
		// String before="shhh"+Util.getDateTime("yyyy-MM-dd")+"001_";
		String otherContext = null;
		if (pageAirtightLog.getOtherContext() != null) {
			String otherContext2 = pageAirtightLog.getOtherContext()
					.replaceAll("（", "(");
			String[] strs = otherContext2.split("\\(S\\)");
			if (strs.length == 2) {
				int index = strs[1].length() - 4;
				otherContext = strs[1].substring(0, index);
			} else if (strs.length == 1) {
				otherContext = strs[0];
			}
			List<String> otherContextList = totalDao
					.query(
							"select otherContext from AirtightLog where otherContext=? and  markId in (select markId from  AirProduct where isNeedOtherContext='yes' and markId =?)",
							otherContext, pageAirtightLog.getMarkId());
			if (otherContextList.size() > 0) {
				return "had";
			}
		}
		try {
			AirProduct airProduct = (AirProduct) totalDao.getObjectByCondition(
					"from AirProduct where markId=?", pageAirtightLog
							.getMarkId());
			if (airProduct == null) {
				return "noOk";
			}
			String czIp = airProduct.getCzIp();// 192.168.19.141
			Long testTime = airProduct.getTestTime();

			// ======================获得开关的信号
			// 1.建立客户端socket连接，指定服务器位置及端口
			Socket socket = new Socket(czIp, 8899);
			BufferedInputStream bis = new BufferedInputStream(socket
					.getInputStream());
			System.out.println("获得气密信号成功!");
			// ======================获得开关的信号
			boolean bool = true;
			String data1 = "";
			while (bool) {
				// 每次接收的数据
				byte[] data = new byte[19];
				bis.read(data);// 读取数据
				String dataString = byteArrayToHexString(data);
				Integer xlsz = Integer
						.valueOf(dataString.substring(22, 26), 16);
				Float xl = findXlValue(xlsz);
				xl = Float.parseFloat(String.format("%.2f", xl));
				System.out.println("当前泄漏量:" + xl + "=========" + xlsz);
				if (xl >= 0F && xl <= 0.02F) {
					data1 = "00";
				}
				if ("00".equals(data1) && xl > 0.02) {
					data1 = "02";
				}
				System.out.println("当前状态值:" + data1);
				if ("02".equals(data1)) {// 打开气密开关成功
					bis.close();
					socket.close();
					bool = false;
					System.out.println("开始倒计时!");
					Thread.sleep(testTime + 4000);// 比测试时间多5秒钟
					System.out.println("倒计时结束");

					Socket socket2 = new Socket(czIp, 8899);
					BufferedInputStream bis2 = new BufferedInputStream(socket2
							.getInputStream());
					System.out.println("获得气密信号成功!");
					boolean bool3 = true;
					while (bool3) {
						// 每次接收的数据
						byte[] data2 = new byte[19];
						bis2.read(data2);// 读取数据
						String dataString2 = byteArrayToHexString(data2);
						Integer xlsz2 = Integer.valueOf(dataString2.substring(
								22, 26), 16);
						Integer ylsz2 = Integer.valueOf(dataString2.substring(
								30, 34), 16);
						Float xl2 = findXlValue(xlsz2);
						Float yl2 = findYlValue(ylsz2);
						xl2 = Float.parseFloat(String.format("%.2f", xl2));
						yl2 = Float.parseFloat(String.format("%.1f", yl2));
						System.out.println("\n泄露量:" + xl2 + "压力值" + yl2);
						bis2.close();
						socket2.close();
						boolean qmstatus = true;
						if (xl2 >= airProduct.getXielou() || xl2 <= 0) {
							qmstatus = false;
						}
						if (yl2 <= airProduct.getYali()) {
							qmstatus = false;
						}
						if (qmstatus == false) {
							return "noOk";
						}

						// 查询条形码是否存在----SAGC29000171
						String before = "SAGC29";
						String hql = "select max(context) from AirtightLog where context like '"
								+ before + "%' and type='条形码'";
						String maxNumber = (String) totalDao
								.getObjectByCondition(hql);
						if (maxNumber != null && maxNumber.length() > 0) {
							maxNumber = "1"
									+ maxNumber.substring(
											maxNumber.length() - 6, maxNumber
													.length());
							int num = Integer.parseInt(maxNumber);

							maxNumber = (num + 1) + "";
						} else {
							maxNumber = "1000001";
						}
						String code = (maxNumber + "").substring(1, 7);
						String barcontext = before + code;
						// 查询该条码是否已经存在
						String hql_context = "from AirtightLog where context =? and type='条形码'";
						Integer number_context = totalDao.getCount(hql_context,
								barcontext);
						if (number_context == 0) {
							AirtightLog airtightLog = new AirtightLog();
							airtightLog.setOperator(Util.getLoginUser()
									.getName());
							airtightLog.setMarkId(pageAirtightLog.getMarkId());

							airtightLog.setOtherContext(otherContext);
							airtightLog.setAddtime(Util.getDateTime());
							airtightLog.setNumber(code);
							// airtightLog.setBarcode(code);
							airtightLog.setXielou(xl2);
							airtightLog.setYali(yl2);
							airtightLog.setType("条形码");
							airtightLog.setContext(barcontext);
							// 再次查询该条码是否已经存在
							number_context = totalDao.getCount(hql_context,
									barcontext);
							if (number_context == 0) {
								if (totalDao.save(airtightLog)) {
									return barcontext;
								}
							}
						}
						return "error";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static final String HEX_CODE = "0123456789ABCDEF";

	/***
	 * 十六进制转换为String类型
	 * 
	 * @param bs
	 * @return
	 */
	public static String byteArrayToHexString(byte[] bs) {
		int _byteLen = bs.length;
		StringBuilder _result = new StringBuilder(_byteLen * 2);
		for (int i = 0; i < _byteLen; i++) {
			int n = bs[i] & 0xFF;
			_result.append(HEX_CODE.charAt(n >> 4));
			_result.append(HEX_CODE.charAt(n & 0x0F));
		}
		return String.valueOf(_result);
	}

	/***
	 * 查表获取泄露量
	 * 
	 * @param xlZhi泄露数值
	 * @return
	 */
	public static Float findXlValue(Integer xlZhi) {
		Float xlNum = 0F;
		xlZhi -= 2400;
		System.out.println(xlZhi);
		if (xlZhi < 15500) {// (0L/min)
			xlNum = 0F;
		} else if (xlZhi >= 15500 && xlZhi < 27600) {// (0-0.16L/min)
			xlNum = (xlZhi - 15500F) / (27600F - 15500F) * 0.16F + 0F;
		} else if (xlZhi >= 27600 && xlZhi < 29650) {// (0.16-0.24L/min)
			xlNum = (xlZhi - 27600F) / (29650F - 27600F) * 0.08F + 0.16F;
		} else if (xlZhi >= 29650 && xlZhi < 31760) {// (0.24-0.0.32L/min)
			xlNum = (xlZhi - 29650F) / (31760F - 29650F) * 0.08F + 0.24F;
		} else if (xlZhi >= 31760 && xlZhi < 33970) {// (0.32-0.4L/min)
			xlNum = (xlZhi - 31760F) / (33970F - 31760F) * 0.08F + 0.32F;
		} else if (xlZhi >= 33970 && xlZhi < 36600) {// (0.4-0.48L/min)
			xlNum = (xlZhi - 33970F) / (36600F - 33970F) * 0.08F + 0.4F;
		} else if (xlZhi >= 36600 && xlZhi < 37947) {// (0.48-0.56L/min)
			xlNum = (xlZhi - 36600F) / (37947F - 36600F) * 0.08F + 0.48F;
		} else if (xlZhi >= 37947 && xlZhi < 39467) {// (0.56-0.64L/min)
			xlNum = (xlZhi - 37947F) / (39467F - 37947F) * 0.08F + 0.56F;
		} else if (xlZhi >= 39467 && xlZhi < 40800) {// (0.64-0.72L/min)
			xlNum = (xlZhi - 39467F) / (40800F - 39467F) * 0.08F + 0.64F;
		} else if (xlZhi >= 40800 && xlZhi < 42600) {// (0.72-0.8L/min)
			xlNum = (xlZhi - 40800F) / (42600F - 40800F) * 0.08F + 0.72F;
		} else if (xlZhi >= 42600 && xlZhi < 44200) {// (0.8-0.88L/min)
			xlNum = (xlZhi - 42600F) / (44200F - 42600F) * 0.08F + 0.8F;
		} else if (xlZhi >= 44200 && xlZhi < 45850) {// (0.88-0.96L/min)
			xlNum = (xlZhi - 44200F) / (45850F - 44200F) * 0.08F + 0.88F;
		} else if (xlZhi >= 45850 && xlZhi < 47102) {// (0.96-1.04L/min)
			xlNum = (xlZhi - 45850F) / (47102F - 45850F) * 0.08F + 0.96F;
		} else if (xlZhi >= 47102 && xlZhi < 48750) {// (1.04-1.12L/min)
			xlNum = (xlZhi - 47102F) / (48750F - 47102F) * 0.08F + 1.04F;
		} else if (xlZhi >= 48750 && xlZhi < 50437) {// (1.12-1.2L/min)
			xlNum = (xlZhi - 48750F) / (50437F - 48750F) * 0.08F + 1.12F;
		} else if (xlZhi >= 50437 && xlZhi < 52079) {// (1.2-1.28L/min)
			xlNum = (xlZhi - 50437F) / (52079F - 50437F) * 0.08F + 1.2F;
		} else if (xlZhi >= 52079 && xlZhi < 53352) {// (1.28-1.36L/min)
			xlNum = (xlZhi - 52079F) / (53352F - 52079F) * 0.08F + 1.28F;
		} else if (xlZhi >= 53352 && xlZhi < 54330) {// (1.36-1.44L/min)
			xlNum = (xlZhi - 53352F) / (54330F - 53352F) * 0.08F + 1.36F;
		} else if (xlZhi >= 54330 && xlZhi < 55646) {// (1.44-1.52L/min)
			xlNum = (xlZhi - 54330F) / (55646F - 54330F) * 0.08F + 1.44F;
		} else if (xlZhi >= 55646 && xlZhi < 57707) {// (1.52-1.6L/min)
			xlNum = (xlZhi - 55646F) / (57707F - 55646F) * 0.08F + 1.52F;
		} else if (xlZhi >= 57707 && xlZhi <= 65535) {// (1.6-6L/min)
			xlNum = (xlZhi - 57707F) / (65535F - 57707F) * 3.4F + 1.6F;
		}
		return xlNum;
	}

	/***
	 * 查表获取压力值
	 * 
	 * @param ylZhi泄露数值
	 * @return
	 */
	public static Float findYlValue(Integer ylZhi) {
		Float ylNum = 0F;
		if (ylZhi < 100) {// 0Kpa
			ylNum = 0F;
		} else if (ylZhi >= 100 && ylZhi < 3335) {// 0-5Kpa
			ylNum = (ylZhi - 100F) / (3335F - 100F) * 5F + 0F;
		} else if (ylZhi >= 3335 && ylZhi < 16675) {// 5-25Kpa
			ylNum = (ylZhi - 3335F) / (16675F - 3335F) * 20F + 5F;
		} else if (ylZhi >= 16675 && ylZhi < 20010) {// 25-30Kpa
			ylNum = (ylZhi - 16675F) / (20010F - 16675F) * 5F + 25F;
		} else if (ylZhi >= 20010 && ylZhi < 20200) {// 30-31Kpa
			ylNum = (ylZhi - 20010F) / (20200F - 20010F) * 1F + 30F;
		} else if (ylZhi >= 20200 && ylZhi < 20870) {// 31-32Kpa
			ylNum = (ylZhi - 20200F) / (20870F - 20200F) * 1F + 31F;
		} else if (ylZhi >= 20870 && ylZhi <= 23335) {// 32-35Kpa
			ylNum = (ylZhi - 20870F) / (23335F - 20870F) * 3F + 32F;
		} else if (ylZhi > 23335) {
			ylNum = 35F;
		}
		return ylNum;
	}

	@Override
	public Map<Integer, Object> findAirtightLogsByCondition(
			AirtightLog airtightLog, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (airtightLog == null) {
			airtightLog = new AirtightLog();
		}
		String hql = totalDao.criteriaQueries(airtightLog, null)
				+ " order by number desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public AirProduct getAirProductByCode(String content) {
		// TODO Auto-generated method stub
		return (AirProduct) totalDao
				.getObjectByCondition(
						"from AirProduct where markId in(select markId from AirtightLog where context=?)",
						content);
	}

	@Override
	public List<String> getProductMarkId() {
		// TODO Auto-generated method stub
		return (List<String>) totalDao.query("select markId from AirProduct where (syStatus <> '停止' or syStatus is  null) ");
	}

	@Override
	public AirProduct getChecked() {
		// TODO Auto-generated method stub
		return (AirProduct) totalDao
				.getObjectByCondition("from AirProduct where status='yes'");
	}

	@Override
	public boolean checkMarkId(String markId) {
		// TODO Auto-generated method stub
		int count = -1;
		count = totalDao.createQueryUpdate(
				"update AirProduct set status='yes' where markId=? ", null,
				markId);
		count += totalDao.createQueryUpdate(
				"update AirProduct set status='no' where markId!=? ", null,
				markId);
		if (count >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public Long getTestTimeByMarkId(String markId) {
		// TODO Auto-generated method stub
		return (Long) totalDao.getObjectByCondition(
				"select testTime from AirProduct where markId=?", markId);
	}

	@Override
	public Map<Integer, Object> findAirProductsByCondition(
			AirProduct airProduct, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (airProduct == null) {
			airProduct = new AirProduct();
		}
		String hql = totalDao.criteriaQueries(airProduct, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public String addProduct(AirProduct airProduct) {
		// TODO Auto-generated method stub
		List<Integer> idList = totalDao.query(
				"select id from AirProduct where markId=?", airProduct
						.getMarkId());
		if (idList.size() > 0) {
			return "该件号已存在添加失败!";
		} else {
			airProduct.setStatus("no");
			airProduct.setAddTime(Util.getDateTime());
			return totalDao.save(airProduct) + "";
		}
	}

	@Override
	public AirProduct getProductById(Integer id) {
		// TODO Auto-generated method stub
		return (AirProduct) totalDao.getObjectById(AirProduct.class, id);
	}

	@Override
	public String updateProduct(AirProduct airProduct) {
		// TODO Auto-generated method stub
		List<Integer> idList = totalDao.query(
				"select id from AirProduct where markId=? and id!=?",
				airProduct.getMarkId(), airProduct.getId());
		if (idList.size() > 0) {
			return "该件号已经存在，修改失败";
		} else {
			return totalDao.update(airProduct) + "";
		}
	}

	@Override
	public boolean deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		AirProduct ap = getProductById(id);
		if (ap != null) {
			return totalDao.delete(ap);
		}
		return false;
	}

	@Override
	public boolean deleteLog(Integer id) {
		// TODO Auto-generated method stub
		AirtightLog log = (AirtightLog) totalDao.getObjectById(
				AirtightLog.class, id);
		if (log != null) {
			return totalDao.delete(log);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findAirMachinesByCondition(
			AirMachine airMachine, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (airMachine == null) {
			airMachine = new AirMachine();
		}
		String hql = totalDao.criteriaQueries(airMachine, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean addMachine(AirMachine airMachine) {
		// TODO Auto-generated method stub
		airMachine.setAddTime(Util.getDateTime());
		airMachine.setAddUser(Util.getLoginUser().getName());
		return totalDao.save(airMachine);
	}

	@Override
	public boolean updateMachine(AirMachine airMachine) {
		// TODO Auto-generated method stub
		if (airMachine != null && airMachine.getId() != null) {
			AirMachine old = (AirMachine) totalDao.getObjectById(
					AirMachine.class, airMachine.getId());
			BeanUtils.copyProperties(airMachine, old, new String[] { "addTime",
					"addUser" });
			return totalDao.update(old);
		}
		return false;
	}

	@Override
	public boolean deleteMachine(Integer id) {
		// TODO Auto-generated method stub
		AirMachine old = getAirMachieById(id);
		if (old != null) {
			return totalDao.delete(old);
		}
		return false;
	}

	@Override
	public AirMachine getAirMachieById(Integer id) {
		// TODO Auto-generated method stub
		return (AirMachine) totalDao.getObjectById(AirMachine.class, id);
	}

	@Override
	public AirProduct getAirProductByMarkId(String markId) {
		// TODO Auto-generated method stub
		return (AirProduct) totalDao.getObjectByCondition(
				"from AirProduct where markId=?", markId);
	}

	@Override
	public Map<Integer, Object> findAirtightLogsByCondition1(
			AirtightLog airtightLog, int parseInt, int pageSize) {
		if (airtightLog == null) {
			airtightLog = new AirtightLog();
		}
		String hql = totalDao.criteriaQueries(airtightLog, null)
				+ " order by number desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, parseInt, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

}
