package com.task.ServerImpl.zhaobiao;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.zhaobiao.NianXiuServer;
import com.task.entity.AskForLeave;
import com.task.entity.Attendance;
import com.task.entity.Overtime;
import com.task.entity.Users;
import com.task.entity.banci.BanCi;
import com.task.util.Util;
import com.tast.entity.zhaobiao.KaoQin;

@SuppressWarnings("unchecked")
public class NianXiuServerImpl implements NianXiuServer {
	private TotalDao totalDao;

	public KaoQin ById(Integer id) {
		String hql1 = " from  KaoQin  where id=?";
		KaoQin kaoQin = (KaoQin) totalDao.getObjectByCondition(hql1, id);
		return kaoQin;
	}

	@SuppressWarnings("deprecation")
	public void exportExcel(String yuefen) {
		String hql = " from KaoQin where yuefen like '%" + yuefen + "%' order by dept";
		List list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String((yuefen+"考勤汇总").getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("考勤汇总", 0);
			ws.setColumnView(4, 20);
			ws.setColumnView(3, 10);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 12);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 25,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			jxl.write.Label label0 = new Label(0, 0, yuefen+"考勤报表", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 2, 0);
			wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);

			// 创建一个样式
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "编号"));
			ws.addCell(new jxl.write.Label(1, 1, "员工卡号"));
			ws.addCell(new jxl.write.Label(2, 1, "姓名"));
			ws.addCell(new jxl.write.Label(3, 1, "部门"));
			ws.addCell(new jxl.write.Label(4, 1, "当月班次天数"));
			ws.addCell(new jxl.write.Label(5, 1, "实际出勤天数"));
			ws.addCell(new jxl.write.Label(6, 1, "迟到"));
			ws.addCell(new jxl.write.Label(7, 1, "早退"));
			ws.addCell(new jxl.write.Label(8, 1, "事假"));
			ws.addCell(new jxl.write.Label(9, 1, "缺勤"));
			ws.addCell(new jxl.write.Label(10, 1, "病假"));
			ws.addCell(new jxl.write.Label(11, 1, "年休"));
			ws.addCell(new jxl.write.Label(12, 1, "调休.换休"));
			ws.addCell(new jxl.write.Label(13, 1, "公休"));
			ws.addCell(new jxl.write.Label(14, 1, "产假/陪护假"));
			ws.addCell(new jxl.write.Label(15, 1, "婚/丧假"));
			ws.addCell(new jxl.write.Label(16, 1, "其他"));
			ws.addCell(new jxl.write.Label(17, 1, "加班 /小时"));
			ws.addCell(new jxl.write.Label(18, 1, "公出"));
			ws.addCell(new jxl.write.Label(19, 1, "说明"));
			ws.addCell(new jxl.write.Label(20, 1, "备注"));
			ws.addCell(new jxl.write.Label(21, 1, "有效考勤天数"));
			ws.addCell(new jxl.write.Label(22, 1, "对比天数"));

			// 格式
			ws.setRowView(1, 1000);// 第二行的高度
			ws.setColumnView(0, 10);// 第一列的宽度

			for (int i = 0; i < list.size(); i++) {
				KaoQin go = (KaoQin) list.get(i);
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1));
				ws.addCell(new Label(1, i + 2, go.getCarId()));
				ws.addCell(new Label(2, i + 2, go.getName()));
				ws.addCell(new Label(3, i + 2, go.getDept()));
				ws.addCell(new jxl.write.Number(4, i + 2, Float
								.parseFloat(String.format("%.2f", go
										.getYingchuqin()))));
				ws.addCell(new jxl.write.Number(5, i + 2, Float
						.parseFloat(String
								.format("%.2f", go.getChuqintianshu()))));
				ws.addCell(new jxl.write.Number(6, i + 2, Float
						.parseFloat(String.format("%.2f", go.getLateTime()))));
				ws.addCell(new jxl.write.Number(7, i + 2, Float
						.parseFloat(String.format("%.2f", go.getEarlyTime()))));
				ws.addCell(new jxl.write.Number(8, i + 2, Float
						.parseFloat(String.format("%.2f", go.getShijia()))));
				ws.addCell(new jxl.write.Number(9, i + 2, Float
						.parseFloat(String.format("%.2f", go.getKuanggong()))));
				ws.addCell(new jxl.write.Number(10, i + 2, Float
						.parseFloat(String.format("%.2f", go.getBingjia()))));
				ws
						.addCell(new jxl.write.Number(11, i + 2, Float
								.parseFloat(String.format("%.2f", go
										.getNianxiujia()))));

				ws.addCell(new jxl.write.Number(12, i + 2, Float
						.parseFloat(String.format("%.2f", go.getTiaoxiu()))));
				ws.addCell(new jxl.write.Number(13, i + 2, Float
						.parseFloat(String.format("%.2f", go.getGongxiu()))));
				ws.addCell(new jxl.write.Number(14, i + 2, Float
						.parseFloat(String.format("%.2f", go.getChanjia()))));
				ws
						.addCell(new jxl.write.Number(15, i + 2, Float
								.parseFloat(String.format("%.2f", go
										.getHuncangjia()))));
				ws.addCell(new jxl.write.Number(16, i + 2, Float
						.parseFloat(String.format("%.2f", go.getQita()))));

				ws.addCell(new jxl.write.Number(17, i + 2, Float
						.parseFloat(String.format("%.2f", go.getJiaban()))));
				ws.addCell(new jxl.write.Number(18, i + 2, Float
						.parseFloat(String.format("%.2f", go.getGongchu()))));
				String shou = go.getShuoming().replaceAll("<font color='red'>缺勤：</font>", "缺勤：").replaceAll("<font color='#0000FF'>加班：</font>", "加班：");
				ws.addCell(new Label(19, i + 2, shou));
				ws.addCell(new Label(20, i + 2, go.getBeizhu()));
				ws.addCell(new jxl.write.Number(21, i + 2, Float
						.parseFloat(String.format("%.2f", go.getChuqintianshuYouxiao()))));
				ws.addCell(new jxl.write.Number(22, i + 2, Float
						.parseFloat(String.format("%.2f", go.getBijiao()))));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public void addKaoqin(String yuefen) {
		// 得到应该出勤天数
		String hqlDate = "from KQDate  where  kqDate like '%" + yuefen + "%'";
		List listDate = totalDao.query(hqlDate);
		Integer yingchuqin = listDate.size();
		// 插入每个人的考勤数据
		String hqlSro = "from Users where banci_name is not null and banci_name <> '' and cardId <> ''";
		List listSyo = totalDao.query(hqlSro);
		for (int i = 0; i < listSyo.size(); i++) {
			Users users = (Users) listSyo.get(i);
			String shuoming = "";
			// 更具Person的工号得到个人信息
			// Users user = Util.getLoginUser();
			if (users.getCardId() != null && users.getCardId().length() > 0) {
				// 将个人信息插入KaoQin
				KaoQin newkaoQin = new KaoQin();
				newkaoQin.setCarId(users.getCardId());

				newkaoQin.setName(users.getName());
				newkaoQin.setDept(users.getDept());
				// 出勤天数
				// ta_hr_Attendance---------------------------------------------------------------------------------------------------------------
				// String hql2 =
				// "from   Attendance where personName=?  and  datetime like '%"
				// + yuefen + "%' and morningStatus=?";
				// Integer counttianshu2 = totalDao.getCount(hql2, zhUser
				// .getName(), "正常");
				// String s2 = counttianshu2 + "";
				// newkaoQin.setChuqintianshu(Float.parseFloat(s2));
				// ta_AskForLeave
				// ------------------------------------------------------------------------------------
				String hql3 = "from AskForLeave where leavePerson=? and leaveEndDate like '%"
						+ yuefen
						+ "%' and leaveTypeOf=? and leavePersonDept=? and leaveUserCardId=? and approvalStatus = '同意'";
				// 事假---------------------------------------------------------------------------------------------------------------------------------------
				List list3 = totalDao.query(hql3, users.getName(), "事假", users
						.getDept(), users.getCardId());
				Float count3 = 0F;
				if (list3 == null || list3.size() <= 0) {
					newkaoQin.setShijia(0F);
				} else {
					// for (int j = 0; j < list3.size(); j++) {
					// AskForLeave ask3 = new AskForLeave();
					// ask3 = (AskForLeave) list3.get(j);
					// // exitTime;//出门时间 returnTime;//回门时间
					// long chashi =(( Util.StringToDate(ask3.getReturnTime(),
					// "yyyy-MM-dd").getTime()
					// - Util.StringToDate(ask3.getExitTime(),
					// "yyyy-MM-dd").getTime())/1000/60/60/24);
					// Float shijian = Float.parseFloat(String.valueOf(chashi));
					// count=count+shijian;
					shuoming = shuoming + "事假：";
					for (int j = 0; j < list3.size(); j++) {
						AskForLeave ask3 = new AskForLeave();
						ask3 = (AskForLeave) list3.get(j);
						shuoming = shuoming + ask3.getLeaveStartDate() + "--"
								+ ask3.getLeaveEndDate() + ":"
								+ ask3.getLeaveDays() + "天"
								+ ask3.getLeaveHours() + "小时" + "|";
						// exitTime;//出门时间 returnTime;//回门时间
						Float shijian3 = (Float) (ask3.getLeaveDays() + ask3
								.getLeaveHours() / 8);
						count3 = count3 + shijian3;
					}
					newkaoQin.setShijia(count3);
				}

				// 矿工-----------------------------------------------------------------------------------------------------------------
				String hql4 = "from Attendance where personName=? and datetime like '%"
						+ yuefen
						+ "%' and deptName=? and cardNo=? and queqinTime > 1";
				newkaoQin.setKuanggong(0F);
				List<Attendance> list4 = totalDao.query(hql4, users.getName(),
						users.getDept(), users.getCardId());
				if (list4 == null || list4.size() <= 0) {
				} else {
					for (int j = 0; j < list4.size(); j++) {
						newkaoQin.setKuanggong(newkaoQin.getKuanggong()
								+ list4.get(j).getQueqinTime().floatValue()
								/ list4.get(j).getWorkBiaoTime().floatValue());
						shuoming = shuoming + " 缺勤：";
						Attendance newa = (Attendance) list4.get(j);
						shuoming = shuoming
								+ newa.getDateTime()
								+ " "
								+ Util.formatDuring(list4.get(j)
										.getQueqinTime().longValue() * 60000)
								+ "|";
					}
				}

				// ---------------------------------------------------------------------------------------------------------------------------
				// Integer counttianshu4 = totalDao.getCount(hql3,
				// users.getName(),
				// "矿工");
				// String s4 = counttianshu4 + "";
				// newkaoQin.setKuanggong(Float.parseFloat(s4));
				// 病假
				// Integer counttianshu5 = totalDao.getCount(hql3,
				// users.getName(),
				// "病假");
				// String s5 = counttianshu5 + "";
				// newkaoQin.setBingjia(Float.parseFloat(s5));
				List list5 = totalDao.query(hql3, users.getName(), "病假", users
						.getDept(), users.getCardId());
				Float count5 = 0F;
				if (list5 == null || list5.size() <= 0) {
					newkaoQin.setBingjia(0F);
				} else {
					shuoming = shuoming + "  病假:";
					for (int j = 0; j < list5.size(); j++) {
						AskForLeave ask5 = new AskForLeave();
						ask5 = (AskForLeave) list5.get(j);
						// exitTime;//出门时间 returnTime;//回门时间
						Float shijian5 = (Float) (ask5.getLeaveDays() + ask5
								.getLeaveHours() / 8);
						count5 = count5 + shijian5;
						shuoming = shuoming + ask5.getLeaveStartDate() + "--"
								+ ask5.getLeaveEndDate() + ":"
								+ ask5.getLeaveDays() + "天"
								+ ask5.getLeaveHours() + "小时" + "|";

					}
					// String s3 = shijia + "";
					newkaoQin.setBingjia(count5);
				}
				// 年休假--------------------------------------------------------------------------------------------------------------
				// Integer counttianshu6 = totalDao.getCount(hql3,
				// users.getName(),
				// "年休");
				// String s6 = counttianshu6 + "";
				// newkaoQin.setNianxiujia(Float.parseFloat(s6));
				List list6 = totalDao.query(hql3, users.getName(), "年休", users
						.getDept(), users.getCardId());
				Float count6 = 0F;
				if (list6 == null || list6.size() <= 0) {
					newkaoQin.setNianxiujia(0F);
				} else {
					shuoming = shuoming + "  年休:";

					for (int j = 0; j < list6.size(); j++) {
						AskForLeave ask6 = new AskForLeave();
						ask6 = (AskForLeave) list6.get(j);
						// exitTime;//出门时间 returnTime;//回门时间
						Float shijian6 = (Float) (ask6.getLeaveDays() + ask6
								.getLeaveHours() / 8);
						count6 = count6 + shijian6;
						shuoming = shuoming + ask6.getLeaveStartDate() + "--"
								+ ask6.getLeaveEndDate() + ":"
								+ ask6.getLeaveDays() + "天"
								+ ask6.getLeaveHours() + "小时" + "|";
					}
					// String s3 = shijia + "";
					newkaoQin.setNianxiujia(count6);
				}
				// 调/换休------------------------------------------------------------------------------------------------------------------------------
				// Integer counttianshu7 = totalDao.getCount(hql3,
				// users.getName(),
				// "换休");
				// String s7 = counttianshu7 + "";
				// newkaoQin.setTiaoxiu(Float.parseFloat(s7));
				List list7 = totalDao.query(hql3, users.getName(), "换休", users
						.getDept(), users.getCardId());
				Float count7 = 0F;
				if (list7 == null || list7.size() <= 0) {
					newkaoQin.setTiaoxiu(0F);
				} else {
					shuoming = shuoming + "  换休:";
					for (int j = 0; j < list7.size(); j++) {
						AskForLeave ask7 = new AskForLeave();
						ask7 = (AskForLeave) list7.get(j);
						// exitTime;//出门时间 returnTime;//回门时间
						Float shijian7 = (Float) (ask7.getLeaveDays() + ask7
								.getLeaveHours() / 8);
						count7 = count7 + shijian7;
						shuoming = shuoming + ask7.getLeaveStartDate() + "--"
								+ ask7.getLeaveEndDate() + ":"
								+ ask7.getLeaveDays() + "天"
								+ ask7.getLeaveHours() + "小时" + "|";

					}
					// String s3 = shijia + "";
					newkaoQin.setTiaoxiu(count7);
				}

				// -------------------------------------------------公-----------休-----------------------------------------------
				// Integer counttianshu8 = totalDao.getCount(hql3,
				// users.getName(),
				// "公休");
				// String s8 = counttianshu8 + "";
				// newkaoQin.setGongxiu(Float.parseFloat(s8));
				List list8 = totalDao.query(hql3, users.getName(), "公休", users
						.getDept(), users.getCardId());
				Float count8 = 0F;
				if (list8 == null || list8.size() <= 0) {
					newkaoQin.setGongxiu(0F);
				} else {
					shuoming = shuoming + "  公休:";
					for (int j = 0; j < list8.size(); j++) {
						AskForLeave ask8 = new AskForLeave();
						ask8 = (AskForLeave) list8.get(j);
						// exitTime;//出门时间 returnTime;//回门时间
						Float shijian8 = (Float) (ask8.getLeaveDays() + ask8
								.getLeaveHours() / 8);
						count8 = count8 + shijian8;
						shuoming = shuoming + ask8.getLeaveStartDate() + "--"
								+ ask8.getLeaveEndDate() + ":"
								+ ask8.getLeaveDays() + "天"
								+ ask8.getLeaveHours() + "小时" + "|";
					}
					// String s3 = shijia + "";
					newkaoQin.setGongxiu(count8);
				}
				// 产假/陪护假-------------------------------------------------------------------------------------------------------
				// Integer counttianshu9 = totalDao.getCount(hql3,
				// users.getName(),
				// "公休");
				// String s9 = counttianshu9 + "";
				// newkaoQin.setChanjia(Float.parseFloat(s9));
				List list9 = totalDao.query(hql3, users.getName(), "产假", users
						.getDept(), users.getCardId());
				Float count9 = 0F;
				if (list9 == null || list9.size() <= 0) {
					newkaoQin.setChanjia(0F);
				} else {
					shuoming = shuoming + "  产假:";
					for (int j = 0; j < list9.size(); j++) {
						AskForLeave ask9 = new AskForLeave();
						ask9 = (AskForLeave) list9.get(j);
						// exitTime;//出门时间 returnTime;//回门时间
						Float shijian9 = (Float) (ask9.getLeaveDays() + ask9
								.getLeaveHours() / 8);
						count9 = count9 + shijian9;
						shuoming = shuoming + ask9.getLeaveStartDate() + "--"
								+ ask9.getLeaveEndDate() + ":"
								+ ask9.getLeaveDays() + "天"
								+ ask9.getLeaveHours() + "小时" + "|";
					}
					// String s3 = shijia + "";
					newkaoQin.setChanjia(count9);
				}
				// 婚/丧假-------------------------------------------------------------------------------------------------------------------
				// Integer counttianshu10 = totalDao.getCount(hql3,
				// users.getName(),
				// "丧假");
				// String s10 = counttianshu10 + "";
				// Float f1 = Float.parseFloat(s10);
				//
				// Integer counttianshu11 = totalDao.getCount(hql3,
				// users.getName(),
				// "婚假");
				// String s11 = counttianshu11 + "";
				// Float f2 = Float.parseFloat(s11);
				//
				// newkaoQin.setHuncangjia(f1 + f2);
				String hql45 = "from AskForLeave where leavePerson=? and leaveEndDate like '%"
						+ yuefen + "%' and leaveTypeOf in ('丧假','婚假')";
				List list10 = totalDao.query(hql45, users.getName());
				Float count10 = 0F;
				if (list10 == null || list10.size() <= 0) {
					newkaoQin.setHuncangjia(0F);
				} else {
					shuoming = shuoming + "  婚/丧假:";
					for (int j = 0; j < list10.size(); j++) {
						AskForLeave ask10 = new AskForLeave();
						ask10 = (AskForLeave) list10.get(j);
						// exitTime;//出门时间 returnTime;//回门时间
						Float shijian10 = (Float) (ask10.getLeaveDays() + ask10
								.getLeaveHours() / 8);
						count10 = count10 + shijian10;
						shuoming = shuoming + ask10.getLeaveStartDate() + "--"
								+ ask10.getLeaveEndDate() + ":"
								+ ask10.getLeaveDays() + "天"
								+ ask10.getLeaveHours() + "小时" + "|";

					}
					// String s3 = shijia + "";
					newkaoQin.setHuncangjia(count9);
				}
				// 其 它

				// 公出--------------------------------------------------------------------------------------------------
				// Integer counttianshu12 = totalDao.getCount(hql3,
				// users.getName(),
				// yuefen, "公出");
				// String s12 = counttianshu12 + "";
				// newkaoQin.setGongchu(Float.parseFloat(s12));
				List list11 = totalDao.query(hql3, users.getName(), "公出", users
						.getDept(), users.getCardId());
				Float count11 = 0F;
				if (list11 == null || list11.size() <= 0) {
					newkaoQin.setGongchu(0F);
				} else {
					shuoming = shuoming + "  公出:";
					for (int j = 0; j < list11.size(); j++) {
						AskForLeave ask11 = new AskForLeave();
						ask11 = (AskForLeave) list11.get(j);
						// exitTime;//出门时间 returnTime;//回门时间
						Float shijian11 = (Float) (ask11.getLeaveDays() + ask11
								.getLeaveHours() / 8);
						count11 = count11 + shijian11;
						shuoming = shuoming + ask11.getLeaveStartDate() + "--"
								+ ask11.getLeaveEndDate() + ":"
								+ ask11.getLeaveDays() + "天"
								+ ask11.getLeaveHours() + "小时" + "|";

					}
					// String s3 = shijia + "";
					newkaoQin.setGongchu(count11);
				}
				// 加班 小时 夜班
				// （天）Overtime-----------------------------------------------------------------------------------------------------
				String hql12 = " from Overtime  where overtimeName=? and overtimeDept=? and overtimeCardId=? and startDate like '%"
						+ yuefen
						+ "%' and status not in ('打回','审批中','未审批') and ep_id is not null";
				List list12 = totalDao.query(hql12, users.getName(), users
						.getDept(), users.getCardId());
				Float count12 = 0F;
				if (list12 == null || list12.size() <= 0) {
					newkaoQin.setJiaban(0F);
				} else {
					shuoming = shuoming + "  加班:";
					for (int j = 0; j < list12.size(); j++) {
						Overtime ask12 = new Overtime();
						ask12 = (Overtime) list12.get(j);
						// exitTime;//出门时间 returnTime;//回门时间
						long chashi12 = (Util.getWorkTime1(
								ask12.getStartDate(), ask12.getEndDate()));
						Float shijian12 = Float.parseFloat(String
								.valueOf(chashi12));
						count12 = count12 + shijian12;
						shuoming = shuoming + ask12.getStartDate() + "--"
								+ ask12.getEndDate() + ":" + shijian12 + "小时"
								+ "|";
					}
					newkaoQin.setJiaban(count12);
				}
				// 其他
				newkaoQin.setQita(0F);
				// 夜班
				newkaoQin.setYeban(0F);
				// 应出勤天数
				newkaoQin.setYingchuqin(yingchuqin + 0F);
				// 实际出勤天数
				Float shiji = newkaoQin.getYingchuqin() - newkaoQin.getShijia()
						- newkaoQin.getKuanggong() - newkaoQin.getBingjia()
						- newkaoQin.getNianxiujia() - newkaoQin.getTiaoxiu()
						- newkaoQin.getGongxiu() - newkaoQin.getChanjia()
						- newkaoQin.getHuncangjia() - newkaoQin.getQita();
				newkaoQin.setChuqintianshu(shiji);
				// 月份
				newkaoQin.setYuefen(yuefen);
				// 说明
				newkaoQin.setShuoming(shuoming);
				// 状态
				try {
					// 当前时间
					String time = Util.getDateTime("yyyy-MM");
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
					Date nowtime = (Date) f.parseObject(time);
					// 传入的月份
					Date yuefentime = (Date) f.parseObject(yuefen);
					if (yuefentime.getTime() < nowtime.getTime()) {
						newkaoQin.setStatus("不可更新");
					} else {
						newkaoQin.setStatus("可更新");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				totalDao.save(newkaoQin);
			} else {

			}
		}
	}

	// 月度汇总sum语句
	public void addKaoqin_1(String yuefen) {
		// 1、首先查找班次
		List<BanCi> banCiList = totalDao.query("from BanCi");// 得到应该出勤天数
		for (BanCi banCi : banCiList) {
			Float banciTime = banCi.getGzTime() * 1f;
			int listDate = totalDao.getCount(
					"from KQDate where banci_Id = ? and kqDate like '%"
							+ yuefen + "%'", banCi.getId());
			// 查询班次内每个人的考勤汇总数据
			List<Users> listSyo = totalDao
					.query(
							"from Users where banci_id = ? and onWork in ('在职','试用','实习','离职中')",
							banCi.getId());
			for (Users users : listSyo) {
				if (users.getCardId() != null && users.getCardId().length() > 0) {
					// Float shijichu = 0f;
					// Float queqin = 0f;//旷工
					// Float late = 0f;//迟到
					// Float early = 0f;//早退
					// Float qingjia = 0f;
					// Float jiaban = 0f;
					Float shijia = 0f;
					Float binjia = 0f;
					Float sangjia = 0f;
					Float hunjia = 0f;
					Float huanxiu = 0f;
					Float nianxiu = 0f;
					Float gongchu = 0f;
					StringBuffer buffer = new StringBuffer();// 请假
					StringBuffer buffer1 = new StringBuffer();// 说明
					KaoQin newkaoQin = new KaoQin();
					newkaoQin.setYingchuqin(listDate + 0F);
					newkaoQin.setCarId(users.getCardId());
					newkaoQin.setName(users.getName());
					newkaoQin.setDept(users.getDept());
					List<Float> workTime = totalDao.query(
							"select sum(workTime) from Attendance where dateTime like '%"
									+ yuefen + "%' and userId = ?", users
									.getId());
					newkaoQin.setChuqintianshu(workTime.get(0).floatValue()
							/ banciTime);
					List<Float> lateTime = totalDao.query(
							"select sum(lateTime) from Attendance where dateTime like '%"
									+ yuefen + "%' and userId = ?", users
									.getId());
					newkaoQin.setLateTime(lateTime.get(0).floatValue()
							/ banciTime);
					List<Float> queqinTime = totalDao.query(
							"select sum(queqinTime) from Attendance where dateTime like '%"
									+ yuefen + "%' and userId = ?", users
									.getId());
					newkaoQin.setKuanggong(queqinTime.get(0).floatValue()
							/ banciTime);
					List<Float> earlyTime = totalDao.query(
							"select sum(earlyTime) from Attendance where dateTime like '%"
									+ yuefen + "%' and userId = ?", users
									.getId());
					newkaoQin.setEarlyTime(earlyTime.get(0).floatValue()
							/ banciTime);
					List<Float> qingjiaTime = totalDao.query(
							"select sum(qijiaTime) from Attendance where dateTime like '%"
									+ yuefen + "%' and userId = ?", users
									.getId());
					newkaoQin.setQingjiaTime(qingjiaTime.get(0) / banciTime);
					List<Float> jiabanTime = totalDao.query(
							"select sum(jiaBTime) from Attendance where dateTime like '%"
									+ yuefen + "%' and userId = ?", users
									.getId());
					newkaoQin.setJiaban(jiabanTime.get(0) / banciTime);
					List<Attendance> attendancelist = totalDao
							.query(
									"from Attendance where userId = ? and dateTime like '%"
											+ yuefen
											+ "%' and mingXiJi is not null and mingXiJi <> ''",
									users.getId());
					for (Attendance attendance : attendancelist) {
						buffer.append(attendance.getMingXiJi());
						buffer1.append(attendance.getMingXiLiu());
					}
					if (!"".equals(buffer.toString())) {
						String[] qingjiaType = buffer.toString().split(";");
						for (int i = 0; i < qingjiaType.length; i++) {
							String[] jiaType = qingjiaType[i].split(":");
							if ("病假".equals(jiaType[0])) {
								binjia += Float.valueOf(jiaType[1]) / banciTime;
							} else if ("事假".equals(jiaType[0])) {
								shijia += Float.valueOf(jiaType[1]) / banciTime;
							} else if ("换休".equals(jiaType[0])) {
								huanxiu += Float.valueOf(jiaType[1])
										/ banciTime;
							} else if ("公出".equals(jiaType[0])) {
								gongchu += Float.valueOf(jiaType[1])
										/ banciTime;
							} else if ("年休".equals(jiaType[0])) {
								nianxiu += Float.valueOf(jiaType[1])
										/ banciTime;
							} else if ("丧假".equals(jiaType[0])) {
								sangjia += Float.valueOf(jiaType[1])
										/ banciTime;
							} else if ("婚假".equals(jiaType[0])) {
								hunjia += Float.valueOf(jiaType[1]) / banciTime;
							}
						}
					}
					newkaoQin.setShijia(shijia);
					newkaoQin.setBingjia(binjia);
					newkaoQin.setNianxiujia(nianxiu);
					newkaoQin.setTiaoxiu(huanxiu);
					newkaoQin.setGongchu(gongchu);
					newkaoQin.setGongxiu(0f);
					newkaoQin.setQita(0f);
					newkaoQin.setYeban(0f);
					newkaoQin.setChanjia(0f);
					newkaoQin.setJiaban(0f);

					newkaoQin.setHuncangjia(hunjia);
					newkaoQin.setChuqintianshu(newkaoQin.getChuqintianshu()
							+ gongchu);
					// 月份
					newkaoQin.setYuefen(yuefen);
					// 说明
					newkaoQin.setShuoming(buffer1.toString());
					totalDao.save(newkaoQin);
				}
			}
		}
	}

	/**
	 * 月度汇总根据天数汇总
	 * @param 月份
	 */
	public void addKaoqin_2(String yuefen) {
		// 1、首先查找班次
		List<BanCi> banCiList = totalDao.query("from BanCi");// 得到所有班次应该出勤天数
		for (BanCi banCi : banCiList) {
			//得到该班次当月应出勤天数
			int listDate = totalDao.getCount("from KQDate where banci_Id = ? and kqDate like '%"+ yuefen + "%'", banCi.getId());
			// 查询班次内每个人的考勤汇总数据
			List<Users> listSyo = totalDao
					.query("from Users where banci_id = ? and onWork in ('在职','试用','实习','离职中')",banCi.getId());
			for (Users users : listSyo) {
				onePeople(yuefen, listDate, users);
			}
		}
	}

	@Override
	/**
	 * 更新单个用户考勤月报
	 * @param yuefen 月份
	 * @param listDate 当月该班次应出勤天数
	 * @param users 用户信息
	 */
	public void onePeople(String yuefen, int listDate, Users users) {
		if (users.getCardId() != null && users.getCardId().length() > 0) {
			// 查询该员工当月所有汇总记录
			List<Attendance> attendancelist = totalDao.query(
					"from Attendance where userId = ? and dateTime like '%"
							+ yuefen + "%' order by dateTime", users.getId());
			Float shijichu = 0f;
			Float queqin = 0f;// 旷工
			Float late = 0f;// 迟到
			Float early = 0f;// 早退
			Float qingjia = 0f;
			Float jiaban = 0f;
			Float shijia = 0f;
			Float binjia = 0f;
			Float sangjia = 0f;
			Float hunjia = 0f;
			Float huanxiu = 0f;
			Float nianxiu = 0f;
			Float gongchu = 0f;
			Float chanjia = 0f;
			Float gongxiu = 0f;
			StringBuffer buffer = new StringBuffer();// 说明
			StringBuffer buffer1 = new StringBuffer();// 缺勤说明
			boolean boo = false;//
			KaoQin newkaoQin = (KaoQin) totalDao
					.getObjectByCondition(
							"from KaoQin where carId = ? and name = ? and dept = ? and yuefen = ?",
							users.getCardId(), users.getName(), users
									.getDept(), yuefen);
			if (newkaoQin != null){
				boo = true;
				if(newkaoQin.getStatusInt()==1)
					return;
			}else{
				newkaoQin = new KaoQin();
			}
			newkaoQin.setStatus("不可更新");
			// 该班次应出勤天数
			newkaoQin.setYingchuqin(listDate + 0F);
			newkaoQin.setCarId(users.getCardId());
			newkaoQin.setName(users.getName());
			newkaoQin.setDept(users.getDept());
			for (Attendance attendance : attendancelist) {
				System.out.println(attendance.getWorkBiaoTime()
						+ attendance.getDateTime()
						+ attendance.getPersonName()
						+ "+++++++++++++++++++++");
				Float work = attendance.getWorkBiaoTime().floatValue();
				shijichu += attendance.getWorkTime().floatValue()
						/ work;
				queqin += attendance.getQueqinTime().floatValue()
						/ work;
				late += attendance.getLateTime().floatValue() / work;
				early += attendance.getEarlyTime().floatValue() / work;
				qingjia += attendance.getQijiaTime().floatValue()
						/ work;
				jiaban += attendance.getJiaBTime().floatValue()
				/ work;
				if (attendance.getMingXiJi() != null
						&& !"".equals(attendance.getMingXiJi())) {
					String minXi = attendance.getMingXiJi().replaceAll(
							"：", ":").replaceAll("；", ";");
					String[] qingjiaType = minXi.split(";");
					for (int i = 0; i < qingjiaType.length; i++) {
						if (!"".equals(qingjiaType[i])) {
							String[] jiaType = qingjiaType[i]
									.split(":");
							if ("病假".equals(jiaType[0])) {
								binjia += Float.valueOf(jiaType[1])
										/ work;
							} else if ("事假".equals(jiaType[0])) {
								shijia += Float.valueOf(jiaType[1])
										/ work;
							} else if ("换休".equals(jiaType[0])) {
								huanxiu += Float.valueOf(jiaType[1])
										/ work;
							} else if ("公出".equals(jiaType[0])) {
								gongchu += Float.valueOf(jiaType[1])
										/ work;
							} else if ("年休".equals(jiaType[0])) {
								nianxiu += Float.valueOf(jiaType[1])
										/ work;
							} else if ("丧假".equals(jiaType[0])) {
								sangjia += Float.valueOf(jiaType[1])
										/ work;
							} else if ("婚假".equals(jiaType[0])) {
								hunjia += Float.valueOf(jiaType[1])
										/ work;
							} else if ("产假".equals(jiaType[0])) {
								chanjia += Float.valueOf(jiaType[1])
										/ work;
							} else if ("公假".equals(jiaType[0])) {
								gongxiu += Float.valueOf(jiaType[1])
										/ work;
							}
						}
					}
					buffer.append(attendance.getMingXiLiu());
				}
				if (attendance.getJiaBTime()>0) {
					buffer1.append("<font color='#0000FF'>加班：</font>"+ attendance.getDateTime()+ " "
							+ Util.formatDuring(attendance.getJiaBTime().longValue() * 60000)
							+ " | ");
				}
				if ("正常".equals(attendance.getAttendanceStatus())) {
					buffer1.append("正常："+ attendance.getDateTime()+ " "
							+ Util.formatDuring(attendance.getWorkTime().longValue() * 60000)
							+ " | ");
				}
				if (attendance.getQueqinTime() > 0)
					buffer1.append("<font color='red'>缺勤：</font>"+ attendance.getDateTime()+ " "
							+ Util.formatDuring(attendance.getQueqinTime().longValue() * 60000)
							+ " | ");
			}
			int i = 4;
			newkaoQin.setBijiao(Util.FomartFloat(shijichu+queqin+shijia+binjia+nianxiu+huanxiu+gongchu+gongxiu+chanjia+late+early,i));//比较天数
			newkaoQin.setQingjiaTime(Util.FomartFloat(qingjia,i));
			newkaoQin.setKuanggong(Util.FomartFloat(queqin,i));
			newkaoQin.setShijia(Util.FomartFloat(shijia,i));
			newkaoQin.setBingjia(Util.FomartFloat(binjia,i));
			newkaoQin.setNianxiujia(Util.FomartFloat(nianxiu,i));
			newkaoQin.setTiaoxiu(Util.FomartFloat(huanxiu,i));
			newkaoQin.setGongchu(Util.FomartFloat(gongchu,i));
			newkaoQin.setGongxiu(Util.FomartFloat(gongxiu,i));
			newkaoQin.setQita(0f);
			newkaoQin.setJiaban(Util.FomartFloat(jiaban,i));
			newkaoQin.setYeban(0f);
			newkaoQin.setChanjia(Util.FomartFloat(chanjia,i));
			newkaoQin.setLateTime(Util.FomartFloat(late,i));
			newkaoQin.setEarlyTime(Util.FomartFloat(early,i));
			newkaoQin.setHuncangjia(Util.FomartFloat(hunjia+sangjia,i));
			// 实际出勤天数 为工作时间+公出时间
			newkaoQin.setChuqintianshu(Util.FomartFloat(shijichu + gongchu,i));
			// 实际出勤有效天数 为工作时间+公出时间+年休+换休
			newkaoQin.setChuqintianshuYouxiao(Util.FomartFloat(shijichu + gongchu + huanxiu + nianxiu,i));
			// 月份
			newkaoQin.setYuefen(yuefen);
			// 说明
			newkaoQin.setShuoming(buffer.toString() + " "
					+ buffer1.toString());
			newkaoQin.setAddTime(Util.getDateTime());
			if (boo){
				totalDao.update(newkaoQin);
			}else{
				newkaoQin.setStatusInt(0);
				newkaoQin.setUserId(users.getId());
				totalDao.save(newkaoQin);
			}
		}
	}

	/**
	 * 新汇总算法
	 * */
	public void addKaoqin_4(String yuefen) {
		// 1、首先查找班次
		List<BanCi> banCiList = totalDao.query("from BanCi");// 得到所有班次应该出勤天数
		for (BanCi banCi : banCiList) {
			//获得当前班次当月应出勤天数
			int listDate = totalDao.getCount("from KQDate where banci_Id = ? and kqDate like '%"+ yuefen + "%'", banCi.getId());
			// 查询班次内每个人的考勤汇总数据
			List<Users> listSyo = totalDao
					.query("from Users where banci_id = ? and onWork in ('在职','试用','实习','离职中')",banCi.getId());
			for (Users users : listSyo) {
				if (users.getCardId() != null && users.getCardId().length() > 0) {
					// 查询该员工当月所有汇总记录
					List<Attendance> attendancelist = totalDao.query(
							"from Attendance where userId = ? and dateTime like '%"
									+ yuefen + "%'", users.getId());
					Float shijichu = 0f;
					Float queqin = 0f;// 旷工
					Float late = 0f;// 迟到
					Float early = 0f;// 早退
					Float qingjia = 0f;
					Float jiaban = 0f;
					Float shijia = 0f;
					Float binjia = 0f;
					Float sangjia = 0f;
					Float hunjia = 0f;
					Float huanxiu = 0f;
					Float nianxiu = 0f;
					Float gongchu = 0f;
					Float chanjia = 0f;
					Float gongxiu = 0f;
					StringBuffer buffer = new StringBuffer();// 说明
					StringBuffer buffer1 = new StringBuffer();// 缺勤说明
					boolean boo = false;//
					KaoQin newkaoQin = (KaoQin) totalDao
							.getObjectByCondition(
									"from KaoQin where carId = ? and name = ? and dept = ? and yuefen = ?",
									users.getCardId(), users.getName(), users
											.getDept(), yuefen);
					if (newkaoQin != null){
						boo = true;
						if(newkaoQin.getStatusInt()==1)
							continue;
					}else{
						newkaoQin = new KaoQin();
					}
					newkaoQin.setStatus("不可更新");
					// 该班次应出勤天数
					newkaoQin.setYingchuqin(listDate + 0F);
					newkaoQin.setCarId(users.getCardId());
					newkaoQin.setName(users.getName());
					newkaoQin.setCode(users.getCode());
					newkaoQin.setDept(users.getDept());
					for (Attendance attendance : attendancelist) {
						System.out.println(attendance.getWorkBiaoTime()
								+ attendance.getDateTime()
								+ attendance.getPersonName()
								+ "+++++++++++++++++++++");
						Float work = attendance.getWorkBiaoTime().floatValue();
						shijichu += attendance.getWorkTime().floatValue() / work;
						queqin += attendance.getQueqinTime().floatValue() / work;
						late += attendance.getLateTime().floatValue() / work;
						early += attendance.getEarlyTime().floatValue() / work;
						qingjia += attendance.getQijiaTime().floatValue() / work;
						if (attendance.getMingXiJi() != null
								&& !"".equals(attendance.getMingXiJi())) {
							String minXi = attendance.getMingXiJi().replaceAll(
									"：", ":").replaceAll("；", ";");
							String[] qingjiaType = minXi.split(";");
							for (int i = 0; i < qingjiaType.length; i++) {
								if (!"".equals(qingjiaType[i])) {
									String[] jiaType = qingjiaType[i] .split(":");
									if ("病假".equals(jiaType[0])) {
										binjia += Float.valueOf(jiaType[1]) / work;
									} else if ("事假".equals(jiaType[0])) {
										shijia += Float.valueOf(jiaType[1]) / work;
									} else if ("换休".equals(jiaType[0])) {
										huanxiu += Float.valueOf(jiaType[1]) / work;
									} else if ("公出".equals(jiaType[0])) {
										gongchu += Float.valueOf(jiaType[1]) / work;
									} else if ("年休".equals(jiaType[0])) {
										nianxiu += Float.valueOf(jiaType[1]) / work;
									} else if ("丧假".equals(jiaType[0])) {
										sangjia += Float.valueOf(jiaType[1]) / work;
									} else if ("婚假".equals(jiaType[0])) {
										hunjia += Float.valueOf(jiaType[1]) / work;
									} else if ("产假".equals(jiaType[0])) {
										chanjia += Float.valueOf(jiaType[1]) / work;
									} else if ("公假".equals(jiaType[0])) {
										gongxiu += Float.valueOf(jiaType[1]) / work;
									}
								}
							}
							buffer.append(attendance.getMingXiLiu());
						}
						if (attendance.getQueqinTime() > 0)
							buffer1.append("缺勤："
									+ attendance.getDateTime()
									+ " "
									+ Util
											.formatDuring(attendance
													.getQueqinTime()
													.longValue() * 60000)
									+ " | ");
					}
					newkaoQin.setQingjiaTime(qingjia);
					newkaoQin.setKuanggong(queqin);
					newkaoQin.setShijia(shijia);
					newkaoQin.setBingjia(binjia);
					newkaoQin.setNianxiujia(nianxiu);
					newkaoQin.setTiaoxiu(huanxiu);
					newkaoQin.setGongchu(gongchu);
					newkaoQin.setGongxiu(gongxiu);
					newkaoQin.setQita(0f);
					newkaoQin.setJiaban(jiaban);
					newkaoQin.setYeban(0f);
					newkaoQin.setChanjia(chanjia);
					newkaoQin.setLateTime(late);
					newkaoQin.setEarlyTime(early);
					newkaoQin.setHuncangjia(hunjia+sangjia);
					// 实际出勤天数 为工作时间+公出时间
					newkaoQin.setChuqintianshu(shijichu + gongchu);
					// 月份
					newkaoQin.setYuefen(yuefen);
					// 说明
					newkaoQin.setShuoming(buffer.toString() + " "
							+ buffer1.toString());
					if (boo)
						totalDao.update(newkaoQin);
					else{
						newkaoQin.setAddTime(Util.getDateTime());
						newkaoQin.setStatusInt(0);
						newkaoQin.setUserId(users.getId());
						totalDao.save(newkaoQin);
					}
				}
			}
		}
	}

	public List Byyufen(String yuefen) {
		String hql5 = "from  KaoQin where status='可更新' and  yuefen=?";
		List list5 = totalDao.query(hql5, yuefen);
		if (list5 != null || list5.size() > 0) {
			for (int i = 0; i < list5.size(); i++) {
				KaoQin oldKaoqin = new KaoQin();
				oldKaoqin = (KaoQin) list5.get(i);
				totalDao.delete(oldKaoqin);
			}
		}
		String hql = "from  KaoQin where  yuefen=?";
		List list = totalDao.query(hql, yuefen);
		return list;
	}

	public Object[] listnianxiu(KaoQin kaoQin, Integer parseInt,
			Integer pageSize) {
		if (kaoQin == null) {
			kaoQin = new KaoQin();
		}
		String hql = totalDao.criteriaQueries(kaoQin, null)
				+ "  order by yuefen";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String updateKaoqin(KaoQin kaoQin) {
		// TODO Auto-generated method stub
		KaoQin kaoQin2 = ById(kaoQin.getId());
		if (kaoQin2 == null)
			return "数据为空，修改失败！";
		BeanUtils.copyProperties(kaoQin, kaoQin2, new String[] { "id",
				"addTime", "userId", "carId", "name", "code", "dept", "yingchuqin", "statusInt",
				"yuefen" });
		kaoQin2.setStatusInt(1);
		kaoQin2.setStatus("不可更新");
		kaoQin2.setAddTime(Util.getDateTime());
		if(totalDao.update(kaoQin2))
			return "修改成功";
		else
			return "修改失败";
	}

	@Override
	public String shanchuById(KaoQin kaoQin) {
		// TODO Auto-generated method stub
		KaoQin kaoQin2 = ById(kaoQin.getId());
		if(kaoQin2!=null){
			if(totalDao.delete(kaoQin2))
				return "删除成功！";
		}
		return "删除失败！";
	}

	@Override
	public String jiSuanById(KaoQin kaoQin) {
		// TODO Auto-generated method stub
		KaoQin kaoQin2 = ById(kaoQin.getId());
		if(kaoQin2!=null){
			Users users = (Users) totalDao.getObjectById(Users.class, kaoQin2.getUserId());
			if(users!=null){
				int listDate = totalDao.getCount("from KQDate where banci_Id = ? and kqDate like '%"+ kaoQin2.getYuefen() + "%'", users.getBanci_id());
				onePeople(kaoQin2.getYuefen(), listDate, users);
				return "更新成功！";
			}else {
				return "用户不存在。更新失败！";
			}
		}
		return "更新失败！";
	}

}
