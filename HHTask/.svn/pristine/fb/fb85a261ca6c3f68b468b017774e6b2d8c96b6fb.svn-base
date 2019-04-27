package com.task.ServerImpl;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.DownloadServer;
import com.task.entity.BrushCard;
import com.task.entity.Download;
import com.task.entity.Person;
import com.task.util.Util;

public class DownloadServerImpl implements DownloadServer {
	private TotalDao totalDao;

	@Override
	public String addDownload(Download download) {
		// TODO Auto-generated method stub
		if (download != null) {
			if (totalDao.save(download)) {
				return "true";
			}
		}
		return null;
	}

	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		DownloadServerImpl acc = new DownloadServerImpl();
		acc.setTotalDao(totalDao);
		return totalDao;
	}

	/**
	 * 添加打卡&汇总记录
	 * @param markId 卡号
	 * @param type 正常\出差
	 * @param address 打卡地方
	 * @param outIn 进出门
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String AddDownLoads(String markId,String type,String address,String outIn) {
		if (markId == null)
			return "卡号为空，打卡失败！";
		String nowdate = Util.getDateTime("yyyy-MM-dd");
		String nowTime = Util.getDateTime("HH:mm");
		TotalDao totalDao = createTotol();
		Integer numb = Integer.parseInt(markId);// String转int
		System.out.println("处理之后的卡号为：" + numb);
		List list = totalDao.query("from Person where card_No=? order by Person_ID desc", numb);
		if (list != null && list.size() > 0) {
			Person person = (Person) list.get(0);
			if (person != null) {
				// 查找打卡表中是否有当前分钟的打卡记录
				List listDown = totalDao.query("from Download where person_ID = ? and card_No = ? and brush_Time = ? and brush_Date= ? ",person.getId(), numb + "", nowTime, nowdate);
				if (listDown != null && listDown.size() > 0) {
					if (totalDao.save2(addDown(person, numb, nowdate, nowTime, outIn, type, address)))
						return markId+"当前已有打卡记录，不汇总。打卡成功！";
				} else {
					if (totalDao.save2(addDown(person, numb, nowdate, nowTime, outIn, type, address))) {
						// 添加BrushCard表信息
						List list2 = totalDao.query("from BrushCard where cardNo = ? and brushDate=? and personId=?",numb + "", nowdate, person.getId());
						if (list2 != null && list2.size() > 0) {
							BrushCard brushCard = (BrushCard) list2.get(0);
							if (brushCard != null) {
								String TimeNew = brushCard.getClockTime() + nowTime + " ";
								brushCard.setClockTime(TimeNew);
								totalDao.update2(brushCard);
							} else {
								totalDao.save2(addBrush(person, numb, nowdate, nowTime));
							}
						} else {
							totalDao.save2(addBrush(person, numb, nowdate, nowTime));
						}
						return "true";
					}
				}
			}
		}
		return markId+"不存在，打卡失败！";
	}
	
	/**
	 * 添加汇总记录
	 * @param person
	 * @param numb
	 * @param nowdate
	 * @param nowTime
	 * @return
	 */
	public static BrushCard addBrush(Person person, Integer numb, String nowdate, String nowTime){
		BrushCard brushCard2 = new BrushCard();
		brushCard2.setPersonId(person.getId());
		brushCard2.setCardNo(numb + "");
		brushCard2.setBrushDate(nowdate);
		brushCard2.setClockTime(nowTime+" ");
		return brushCard2;
	}
	
	/**
	 * 添加打卡记录
	 * @param person
	 * @param numb
	 * @param nowdate
	 * @param nowTime
	 * @param outIn
	 * @param type
	 * @param address
	 * @return
	 */
	public static Download addDown(Person person,Integer numb,String nowdate,String nowTime,String outIn,String type,String address){
		Download download = new Download();
		download.setPerson_ID(person.getId());// 职工卡号信息ID
		download.setCard_No(numb + "");// 卡号
		download.setBrush_Date(nowdate);
		download.setBrush_Time(nowTime);
		download.setMoc_No(outIn);
		download.setData_Flag("10");
		download.setBrush_DateTime(nowdate + " " + nowTime + ":00.000");
		download.setIs_Falsity(0);
		download.setDownType(type);
		download.setDownAddress(address);
		return download;
	}
	
	
	/**
	 * 添加断网打卡&汇总记录
	 * @param markId 卡号
	 * @param date 日期
	 * @param time 时间
	 * @param type 门禁类型
	 * @param address 门禁位置
	 * @param outIn 进/出门
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String AddDownLoad(String markId,String date,String time,String type,String address,String outIn) {
		if (markId == null)
			return "卡号为空，打卡失败！";
		String nowdate = date;
		String nowTime = time;
		if (nowdate == null || "".equals(nowdate))
			nowdate = Util.getDateTime("yyyy-MM-dd");
		if (nowTime == null || "".equals(nowTime))
			nowTime = Util.getDateTime("HH:mm");
		TotalDao totalDao = createTotol();
		Integer numb = Integer.parseInt(markId);// String转int
		System.out.println("处理之后的卡号为：" + numb);
		List list = totalDao.query("from Person where card_No=? order by Person_ID desc", numb);
		if (list != null && list.size() > 0) {
			Person person = (Person) list.get(0);
			if (person != null) {
				// 查找打卡表中是否有当前分钟的打卡记录
				List listDown = totalDao.query("from Download where person_ID = ? and card_No = ? and brush_Time = ? and brush_Date= ? ",person.getId(), numb + "", nowTime, nowdate);
				if (listDown != null && listDown.size() > 0) {
					if (totalDao.save2(addDown(person, numb, nowdate, nowTime, outIn, type, address)))
						return "true";
				} else {
					if (totalDao.save2(addDown(person, numb, nowdate, nowTime, outIn, type, address))) {
						// 添加BrushCard表信息
						List list2 = totalDao.query("from BrushCard where cardNo = ? and brushDate=? and personId=?",numb + "", nowdate, person.getId());
						if (list2 != null && list2.size() > 0) {
							BrushCard brushCard = (BrushCard) list2.get(0);
							if (brushCard != null) {
								String TimeNew = brushCard.getClockTime() + nowTime+ " ";
								brushCard.setClockTime(TimeNew);
								totalDao.update2(brushCard);
							} else {
								totalDao.save2(addBrush(person, numb, nowdate, nowTime));
							}
						} else {
							totalDao.save2(addBrush(person, numb, nowdate, nowTime));
						}
						return "true";
					}
				}
			}
		}
		return markId+"不存在，打卡失败！";
	}

	
	@Override
	public Map<Integer, Object> findDownLoadByCondition(Download download,
			int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
}
