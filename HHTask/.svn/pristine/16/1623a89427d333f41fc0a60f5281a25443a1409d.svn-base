package com.task.ServerImpl.led;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.led.LEDServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.led.LED;
import com.task.entity.led.LEDLog;
import com.task.util.LedSendUtil;
import com.task.util.Util;

public class LEDServerImpl implements LEDServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Map<Integer, Object> findLEDsByCondition(LED lED, int pageNo,
			int pageSize) {
		if (lED == null) {
			lED = new LED();
		}
		String hql = totalDao.criteriaQueries(lED, null, new String[] {
				"iactionType", "ialignStyle", "iholdTime" })
				+ " order by stations";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean add(LED lED) {
		return totalDao.save(lED);
	}
	
	@Override
	public void copyAdd(Integer i) {
		LED led = getLEDById(i);
		if (led!=null) {
			LED led1 = new LED();
			BeanUtils.copyProperties(led, led1, new String[] { "id","lEDLog"});
			led1.setNumber(led.getNumber()+1);
			totalDao.save(led1);
		}
	}

	@Override
	public boolean update(LED lED, String pageStatus) {
		LED led2 = getLEDById(lED.getId());
		if (led2 != null) {
			if (pageStatus != null && pageStatus.equals("manage")) {
				BeanUtils.copyProperties(lED, led2, new String[] { "id",
						"dress","sendStatus","lEDLog" });
			}
			led2.setDress(lED.getDress());
			return totalDao.update(led2);
		}
		return false;
	}

	@Override
	public LED getLEDById(Integer id) {
		// TODO Auto-generated method stub
		return (LED) totalDao.getObjectById(LED.class, id);
	}

	@Override
	public boolean deleteLEDById(Integer id) {
		// TODO Auto-generated method stub
		LED lED = getLEDById(id);
		if (lED != null) {
			return totalDao.delete(lED);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findLEDLogsByCondition(LEDLog lEDLog,
			int pageNo, int pageSize, String sql) {
		// TODO Auto-generated method stub
		if (lEDLog == null) {
			lEDLog = new LEDLog();
		}
		String hql = totalDao.criteriaQueries(lEDLog, sql)
				+ " order by id desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean addLEDLog(Integer lEDId, String context) {
		totalDao.createQueryUpdate(
				"update LEDLog set status='历史' where led.id=" + lEDId, null);
		LEDLog lEDLog = new LEDLog();
		lEDLog.setContext(context);
		lEDLog.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		// lEDLog.setProductStatus("生产");
		lEDLog.setStatus("默认");
		LED led = (LED) totalDao.getObjectById(LED.class, lEDId);
		if (led != null) {
			// Set<LEDLog> ledLogSet = led.getlEDLog();
			// ledLogSet.add(lEDLog);
			// led.setlEDLog(ledLogSet);
			lEDLog.setLed(led);
			return totalDao.save(lEDLog);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addLEDLog(Integer lEDId, LEDLog pageLog) {
		LED led = (LED) totalDao.getObjectById(LED.class, lEDId);
		if (led != null) {
			String hql_loghistory = "from LEDLog where status='生产' and led.id=?";
			List<LEDLog> logList = totalDao.query(hql_loghistory, lEDId);
			for (LEDLog ledLog : logList) {
				ledLog.setStatus("历史");
				totalDao.update(ledLog);
			}
			StringBuffer sb = new StringBuffer();
			if (pageLog.getProcardId() != null
					&& pageLog.getProcessNo() != null) {
				String hql_logstatus = "from LEDLog where led.id=? and procardId =? and processNo=?";
				List<LEDLog> logStatusList = totalDao.query(hql_logstatus,
						lEDId, pageLog.getProcardId(), pageLog.getProcessNo());
				for (LEDLog ledLog : logStatusList) {
					ledLog.setProductStatus("完成");
					totalDao.update(ledLog);
//					totalDao.delete(ledLog);					
				}

				List<String> contextList = totalDao
						.query(
								"select context from LEDLog where led.id=? and productStatus='生产' ",
								lEDId);
				if (contextList.size() > 0) {
					for (String context : contextList) {
						sb.append(context + "\n");
					}
				}
				if (pageLog.getProductStatus() != null
						&& pageLog.getProductStatus().equals("生产")) {
					sb.append(pageLog.getContext());
				}
			}
			pageLog.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			pageLog.setStatus("默认");
			led.setSendStatus("待推送");
			pageLog.setLed(led);
			totalDao.save(pageLog);
			return sb.toString();
		}
		return "";
	}

	/****
	 * 推送led信息
	 * 
	 * @param led
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sendGongWeiMs(Integer ledId) {
		if (ledId != null) {
			LED led = (LED) totalDao.getObjectById(LED.class, ledId);
			if (led == null) {
				return;
			}

			String hql_ledLog = "from LEDLog where led.id=? and productStatus='生产' order by id desc ";
			List<LEDLog> logStatusList = totalDao
					.query(hql_ledLog, led.getId());
			String minFinishTime = "";// 倒计时
			StringBuffer sb = new StringBuffer();// 显示内容(64*32)
			StringBuffer sb2 = new StringBuffer();// 显示内容2(192*64)
			StringBuffer sb3 = new StringBuffer();// 显示内容3(128*64)
			for (LEDLog ledLog : logStatusList) {
				if (ledLog.getMinFinishTime() != null) {
					minFinishTime = ledLog.getMinFinishTime();
				}
				sb.append(ledLog.getContext());
				sb2.append(ledLog.getContext2());
				sb3.append(ledLog.getContext3());
			}
			String sendStatus = "";
			if (sb.length() > 0) {
				if ("1".equals(led.getStyle())) {
					// 64*32 连续上移 居左
					sendStatus = LedSendUtil.OnAddtextUp(led, sb.toString());
				} else if ("2".equals(led.getStyle())) {
					// 192*64 连续上移 居左 倒计时
					sendStatus = LedSendUtil.OnAddtextUp(led, new String[] {
							minFinishTime + ";" + led.getStations() + "   ",
							sb2.toString() });

				} else if ("3".equals(led.getStyle())) {
					// 128*64 连续上移 居左 倒计时
					sendStatus = LedSendUtil.OnAddtextUp(led, new String[] {
							minFinishTime + ";" + led.getStations() + "   ",
							sb3.toString() });

				}
			} else {
				// 推送工位
				sendStatus = LedSendUtil.OnAddtext(led.getNumber(), led
						.getStations(), 1, 1, led.getWidth().intValue(), led
						.getHigth().intValue(), led.getColor(), led
						.getEndfontSize());
			}
			if ("true".equals(sendStatus)) {
				led.setSendStatus("已推送");
				totalDao.update(led);
			}
		}
	}

	/****
	 * 门禁led推送服务
	 * 
	 * @param number
	 *            led编号
	 * @param context
	 *            发送内容
	 * @param color
	 *            颜色
	 * @return
	 */
	@Override
	public void sendOtherMs(Integer number, String context, Integer color) {
		if (number != null) {
			LED led = (LED) totalDao.getObjectByCondition(
					"from LED where number=?", number);
			if (led != null) {
				LedSendUtil.OnAddtext(led.getNumber(), context, 25,
						led.getId(), led.getWidth().intValue(), led.getHigth()
								.intValue(), color, led.getFontSize());
			}
		}
	}

	@Override
	public Integer oneLEDCount() {
		// TODO Auto-generated method stub
		return totalDao.getCount("from LED");
	}
}
