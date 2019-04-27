package com.task.ServerImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.EquipmentService;
import com.task.entity.Econdition;
import com.task.entity.Machine;
import com.task.entity.Maintenance;
import com.task.entity.OaAppDetail;
import com.task.entity.OaApplyForm;
import com.task.entity.Parts;
import com.task.entity.ProcardEss;
import com.task.entity.Responsibilities;
import com.task.entity.Users;
import com.task.util.Util;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;
import com.tast.entity.zhaobiao.Nianlilv;

public class EquipmentServiceImpl implements EquipmentService {
	private TotalDao totalDao;

	@SuppressWarnings("deprecation")
	public boolean add(Maintenance maintenance, String pageStatus) {
		if (maintenance != null) {
			if (maintenance.getRepairMan() != null
					&& maintenance.getRepairMan().length() > 0) {
				maintenance.setStatus("故障");
			} else {
				maintenance.setStatus("故障指派");
			}
			Date date = new Date();
			maintenance.setAlarmTime(date);
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			maintenance.setUserid(user.getId());
			maintenance.setAlermMan(user.getName());
			maintenance.setClassGroup(user.getDept());
			maintenance.setAlermDept(user.getDept());
			maintenance.setMore("加急");
			String mouth = new SimpleDateFormat("yyyyMM").format(new Date());
			int yy = Integer.parseInt(mouth.substring(0, 4));
			int mm = Integer.parseInt(mouth.substring(4, 6));
			if (mm < 10) {
				mouth = yy + "0" + mm;
			} else {
				mouth = yy + "" + mm;
			}
			String hql2 = "select max(barcode) from Maintenance where barcode like '%MD"
					+ mouth + "%'";
			Object object = (Object) totalDao.getObjectByCondition(hql2);
			String barcode = "";
			if (object != null) {
				String sm = object.toString();
				sm = sm.substring(2, sm.length());
				Long nextSeqNum = Long.parseLong(sm) + 1;// 当前最大流水卡片
				barcode = "MD" + nextSeqNum.toString();
			} else {
				barcode = "MD" + mouth + "0001";
			}

			maintenance.setBarcode(barcode);
			// maintenance.setBarcode(Util.getDateTime("yyyyMMddHHmmss"));

			boolean bool = totalDao.save(maintenance);

			// 如果报修成功
			if (bool) {
				if (pageStatus != null && pageStatus.length() > 0
						&& "barcode".equals(pageStatus)) {
					// 处理设备信息状态为故障
					String sql = "update machine set machine_status=? where machine_workArea=? and machine_workPosition=? and machine_no=?";
					int count = totalDao.createQueryUpdate(null, sql,
							maintenance.getStatus(), maintenance.getWorkArea(),
							maintenance.getWorkPosition(), maintenance.getNo());
					if (count <= 0) {
						return false;
					}
				}

				// 处理提醒消息
				if (maintenance.getRepairMan() != null
						&& maintenance.getRepairMan().length() > 0) {
					String[] peoples = maintenance.getRepairMan().split(",");
					for (String people : peoples) {
						String hql = "from Responsibilities where repairname=?";
						Responsibilities rb = (Responsibilities) totalDao
								.getObjectByCondition(hql, people);
						if (rb != null) {
							// 发送给指定人员
							AlertMessagesServerImpl
									.addAlertMessages("设备维修管理 ", user.getDept()
											+ "部门有报修设备,请及时维修!", "1", rb
											.getEmployeenumber(), rb.getPhone());
						}
					}
				} else {
					// 发送给指派人员
					AlertMessagesServerImpl.addAlertMessages("设备指派人员管理", user
							.getDept()
							+ "部门有报修设备,请及时指派维修人员维修!", "1");
				}
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public boolean delete(Maintenance maintenance) {
		boolean bool = totalDao.delete(maintenance);
		if (bool) {
			// 处理设备信息状态为故障
			String sql = "update machine set machine_status='正常' where machine_workArea=? and machine_workPosition=? and machine_no=?";
			int count = totalDao.createQueryUpdate(null, sql, maintenance
					.getWorkArea(), maintenance.getWorkPosition(), maintenance
					.getNo());
		}
		return bool;
	}

	public Object[] findAll(Maintenance maintenance, int pageNo, int pageSize,
			String status, Date date1, Date date2) {
		if (maintenance == null) {
			maintenance = new Maintenance();

		}
		String message = "";
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		if ("guzhang".equals(status) || "weixiuzhong".equals(status)) {
			if (user == null) {
				return null;
			}
		}
		if (status != null) {
			if ("guzhang".equals(status)) {
				maintenance.setStatus("故障");
				maintenance.setRepairMan(user.getName());
			} else if ("guzhangzhipai".equals(status)) {
				maintenance.setStatus("故障指派");
			} else if ("weixiuzhong".equals(status)) {
				maintenance.setStatus("维修中");
				maintenance.setRepairMan(user.getName());
			} else if ("weixiuzhong".equals(status)) {
				maintenance.setStatus("修复待验证");
			} else if ("xiufudaiyanzheng".equals(status)) {
				maintenance.setStatus("正常");
			} else if ("findallMaintenance".equals(status)) {
				maintenance.setStatus("正常");
			} else if ("findByCon".equals(status)) {
				message = "findByCon";
			} else {
				message = "findById";
			}
		} else {
			message = "findById";
		}
		String hql = "";
		if ("findByCon".equals(message)) {
			hql = totalDao.criteriaQueries(maintenance, "alarmTime",
					new String[] {
							Util.DateToString(date1, "yyyy-MM-dd HH:mm:ss"),
							Util.DateToString(date2, "yyyy-MM-dd HH:mm:ss") },
					"");
		} else {
			hql = totalDao.criteriaQueries(maintenance, null);
		}
		if ("findById".equals(message)) {
			if (hql.indexOf("where") > 0) {
				hql += " and userid=" + user.getId();
			} else {
				hql += " where userid=" + user.getId();
			}

		}
		hql += " order by alarmTime desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public List findAll(String status) {
		if (status != null && status.length() > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			String hql = "from Maintenance where status =? and userid=? order by alarmTime desc";
			return totalDao.query(hql, status, user.getId());

		}
		return null;
	}

	public Maintenance findAssetById(int id) {
		return (Maintenance) totalDao.getObjectById(Maintenance.class, id);
	}

	public boolean update(ProcardEss procard) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		procard.setTestMan(user.getName());
		return totalDao.update(procard);
	}

	public ProcardEss findAssetBId(int id) {

		return (ProcardEss) totalDao.getObjectById(ProcardEss.class, id);
	}

	@Override
	public boolean update(Maintenance maintenance) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		Date date = new Date();
		// maintenance.setAlarmTime(date);
		boolean bool = totalDao.update(maintenance);
		if (bool && !"正常".equals(maintenance.getStatus())) {
			String hql1 = "from  Parts where barcode=?";
			List list = totalDao.query(hql1, maintenance.getBarcode());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Parts parts = (Parts) list.get(i);
					if (bool) {
						if (parts.getNum() != null
								&& !"".equals(parts.getNum())) {
							/***
							 * 插入数据到 oa_OaAppDetail，oa_OaApplyForm表中
							 */
							// 处理申报执行单数据
							String hql3 = "from OaApplyForm where appBarcode=?";
							String mouth = new SimpleDateFormat("yyyyMM")
									.format(new Date());
							// String appBarcode = "shMD2014011";
							// // String appBarcode = "shMD" + mouth;
							String appBarcode = user.getPassword()
									.getDeptNumber()
									+ mouth;// 标识
							OaApplyForm oaApplyForm = (OaApplyForm) totalDao
									.getObjectByCondition(hql3, appBarcode);
							if (oaApplyForm == null) {
								oaApplyForm = new OaApplyForm();
								oaApplyForm.setAppFormDept(user.getDept());
								oaApplyForm.setAppFormApplier(user.getName());
								oaApplyForm
										.setAppFormDate(new SimpleDateFormat(
												"yyyy-MM-dd")
												.format(new Date()));
								oaApplyForm.setAppFormStatus("采购中");
								oaApplyForm.setAppPlanMon(new SimpleDateFormat(
										"yyyy-MM").format(new Date()));
								oaApplyForm.setAppFormUsers(user.getCode());
								oaApplyForm.setAppBarcode(appBarcode);
								oaApplyForm.setAplyFormOrdnumber(appBarcode);
								totalDao.save(oaApplyForm);
							}

							// 处理申报明细数据
							// OaAppDetail oaAppDetail = new OaAppDetail();
							// oaAppDetail.setDetailOrdNumber(appBarcode);
							// oaAppDetail.setDetailAppName(parts.getPartName());//
							// 物品名称
							// oaAppDetail.setDetailFormat(parts.getPictureNo());//
							// 规格
							// oaAppDetail.setDetailUnit(parts.getUnit());// 单位
							// oaAppDetail.setDetailCount(Float.parseFloat(parts
							// .getNum().trim()));
							// oaAppDetail.setDetailBudgetMoney(Float.parseFloat(parts
							// .getMore()));
							// oaAppDetail.setDetailIsBusy("加急");
							// oaAppDetail.setDetailStatus("同意");
							// oaAppDetail.setDetailArrDate(new
							// SimpleDateFormat(
							// "yyyy-MM-dd").format(new Date()));
							// oaAppDetail.setDetailPlanMon(new
							// SimpleDateFormat(
							// "yyyy-MM").format(new Date()));
							// oaAppDetail.setDetailClass("普通申购");
							// oaAppDetail.setDetailChildClass("备件");
							// oaAppDetail.setDetailAppDept(user.getDept());
							// oaAppDetail.setDetailPlanAcco(parts.getBarcode());
							//							
							// // 生成物品编号
							// int yy = Integer.parseInt(mouth.substring(0, 4));
							// int mm = Integer.parseInt(mouth.substring(4, 6));
							// if (mm < 10) {
							// mouth = yy + "0" + mm;
							// } else {
							// mouth = yy + "" + mm;
							// }
							// String hql2 =
							// "select max(detailSeqNum) from OaAppDetail where detailSeqNum like '%SB"
							// + mouth + "%'";
							// Object object = (Object) totalDao
							// .getObjectByCondition(hql2);
							// String detailSeqNum = "";
							// if (object != null) {
							// String sm = object.toString();
							// sm = sm.substring(2, sm.length());
							// Long nextSeqNum = Long.parseLong(sm) + 1;//
							// 当前最大流水卡片
							// detailSeqNum = "SB" + nextSeqNum.toString();
							// } else {
							// detailSeqNum = "SB" + mouth + "0001";
							// }
							// oaAppDetail.setDetailSeqNum(detailSeqNum);
							// oaAppDetail.setDetailBarcode(appBarcode);
							// totalDao.save(oaAppDetail);
						}
					}
				}
			}
		}
		if (bool) {
			// 处理设备信息状态为故障
			String sql = "update machine set machine_status=? where machine_workArea=? and machine_workPosition=? and machine_no=?";
			int count = totalDao.createQueryUpdate(null, sql, maintenance
					.getStatus(), maintenance.getWorkArea(), maintenance
					.getWorkPosition(), maintenance.getNo());
		}
		// // 处理提醒消息
		// if (maintenance.getRepairMan() != null
		// && maintenance.getRepairMan().length() > 0) {
		// String[] peoples = maintenance.getRepairMan().split(",");
		// for (String people : peoples) {
		// String hql = "from Responsibilities where repairname=?";
		// Responsibilities rb = (Responsibilities) totalDao
		// .getObjectByCondition(hql, people);
		// if (rb != null) {
		// // 发送给指定人员
		// AlertMessagesServerImpl.addAlertMessages("设备维修管理 ", user
		// .getDept()
		// + "部门有报修设备,请及时维修!", "1", rb.getEmployeenumber(), rb
		// .getPhone());
		// }
		// }
		// } else {
		// // 发送给指派人员
		// AlertMessagesServerImpl.addAlertMessages("设备指派人员管理", user.getDept()
		// + "部门有报修设备,请及时指派维修人员维修!", "1");
		// }

		return bool;
	}

	/***
	 * 更新申报明细状态为"采购中"
	 * 
	 * @param barcode
	 * @return
	 */
	@Override
	public boolean updateOaAppDetail(String barcode) {
		String hql = "from OaAppDetail where detailPlanAcco=?";
		List list = totalDao.query(hql, barcode);
		boolean bolean = false;
		for (int i = 0; i < list.size(); i++) {
			OaAppDetail oaAppDetail = (OaAppDetail) list.get(i);
			if (oaAppDetail != null) {
				oaAppDetail.setDetailStatus("采购中");
				bolean = totalDao.update(oaAppDetail);
			}
		}
		return bolean;
	}

	@Override
	public List findAllwarehousing(String barcode) {
		if (barcode != null && barcode.length() > 0) {
			String hql = "from ProcardEss where barcode=? and proName=? ";
			return totalDao.query(hql, barcode, "终检");
		}
		return null;
	}

	@Override
	public List findAllparts(String barcode) {
		if (barcode != null && barcode.length() > 0) {
			String hql = "from Parts where barcode=? ";
			return totalDao.query(hql, barcode);
		}
		return null;
	}

	@Override
	public List findAllByStatus(String barcode) {
		if (barcode != null && barcode.length() > 0) {
			String hql = "from Machine where barcode = ? ";
			return totalDao.query(hql, barcode);
			// String barcodes[] = barcode.split("O");
			// if (barcodes.length == 2) {
			// String hql = "from Machine where workPosition = ? and no=?";
			// return totalDao.query(hql, barcodes[0], barcodes[1]);
			// }
		}
		return null;
	}

	@Override
	public Machine findAllByStatus(Integer id) {
		if (id != null && id > 0) {
			return (Machine) totalDao.getObjectById(Machine.class, id);
		}
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List findAllByStatuss(String status) {
		if (status != null && status.length() > 0) {

			String hql = "from Maintenance where status =? order by alarmTime desc";
			return totalDao.query(hql, status);

		}
		return null;

	}

	@Override
	public int findAlly(Maintenance maintenance) {
		if (maintenance != null) {
			String hql = "from Maintenance where workArea=? and workPosition=? and no=? and status not in ('正常','修复待验证')";
			return totalDao.getCount(hql, maintenance.getWorkArea(),
					maintenance.getWorkPosition(), maintenance.getNo());
		}
		return 0;
	}

	@Override
	public boolean addParts(Parts parts) {
		if (parts != null) {
			Date date = new Date();
			parts.setAlarmTime(date);
			return totalDao.save(parts);
		}
		return false;
	}

	@Override
	public List selectPeopleForZhipai(String repairMan) {
		String hql = "from Responsibilities where repairresponsibilitiesl='设备维修'";
		return totalDao.query(hql);
	}

	@Override
	public List findAllbarcoder(String barcode) {
		if (barcode != null && barcode.length() > 0) {
			String hql = "from Maintenance where barcode = ? and status not in ('正常','修复待验证')";
			return totalDao.query(hql, barcode);
		}
		return null;
	}

	@Override
	public int findAlls(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			String hql = "from Maintenance ";
			return totalDao.getCount(hql);
		} else {
			String hql = "from Maintenance where alarmTime>=? and alarmTime<=?";
			try {
				return totalDao.getCount(hql, date1, date2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	@Override
	public int findAllss(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			String hql = "from Maintenance where status in ('正常','修复待验证')";
			return totalDao.getCount(hql);
		} else {
			String hql = "from Maintenance where status in ('正常','修复待验证') and alarmTime>=? and alarmTime<=?";
			try {
				return totalDao.getCount(hql, date1, date2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	@Override
	public Object[] findAllys(Econdition econdition, String month1,
			String month2, int pageNo, int pageSize, String status) {
		if (month1 == null && month2 == null) {
			String hql = "from Econdition ";
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		} else {
			if (month1.length() >= 5 && month2.length() <= 0) {
				month2 = month1;
			} else if (month2.length() >= 5 && month1.length() <= 0) {
				month1 = month2;
			}

			String hql = "from Econdition where 1=1";
			if (month1 != null && month1.length() > 0) {
				hql += " and month>='" + month1 + "'";
			}
			if (month2 != null && month2.length() > 0) {
				hql += " and month<='" + month2 + "'";
			}
			if (status != null && status.length() > 0) {
				hql += " and status>='" + status + "'";
			}
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);

			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}

	}

	@Override
	public Object[] findPartsByCondition(Parts parts, Date date1, Date date2,
			int pageNo, int pageSize) {
		if (parts == null) {
			parts = new Parts();
		}
		// parts.setBarcode(parts.getBarcode().trim());
		String hql = totalDao.criteriaQueries(parts, "alarmTime", new String[] {
				Util.DateToString(date1, "yyyy-MM-dd HH:mm:ss"),
				Util.DateToString(date2, "yyyy-MM-dd HH:mm:ss") }, "");
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 *excel
	 */
	@Override
	public String excelMaintenance(Date date1, Date date2) {
		try {
			String fileName = "" + Util.getDateTime("yyyyMMddhhmmss") + ".xls";
			String excelRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/sheet")
					+ "/equipment/" + fileName;
			File target2 = new File(excelRealPath);// 创建文件流获得EXCEL的路径
			// 创建excel表
			WritableWorkbook book = Workbook.createWorkbook(target2);
			// 创建名为“月维修设备汇总”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("月度维修设备汇总 ", 0);
			Label label0 = new Label(0, 0, "序号");// 0列0后面是行
			Label label1 = new Label(1, 0, "报修条码");// 0列0后面是行
			Label label2 = new Label(2, 0, "工区");
			Label label3 = new Label(3, 0, "工位");
			Label label4 = new Label(4, 0, "设备编号");
			Label label5 = new Label(5, 0, "设备名称");
			Label label6 = new Label(6, 0, "报修人员");
			Label label7 = new Label(7, 0, "所在部门");
			Label label8 = new Label(8, 0, "维修人员");
			Label label9 = new Label(9, 0, "报修时间");
			Label label10 = new Label(10, 0, "修复时间");
			Label label11 = new Label(11, 0, "报修原因");
			Label label12 = new Label(12, 0, "修理原因说明");
			Label label13 = new Label(13, 0, "设备状态");
			sheet.setColumnView(1, 18);// 设置第1行的宽度
			sheet.setColumnView(5, 12);// 设置第5行的宽度
			sheet.setColumnView(9, 20);// 设置第9行的宽度
			sheet.setColumnView(10, 20);// 设置第10行的宽度
			sheet.setColumnView(11, 20);// 设置第11行的宽度
			sheet.setColumnView(12, 20);// 设置第12行的宽度
			sheet.setColumnView(13, 12);// 设置第13行的宽度
			sheet.addCell(label0);
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			sheet.addCell(label7);
			sheet.addCell(label8);
			sheet.addCell(label9);
			sheet.addCell(label10);
			sheet.addCell(label11);
			sheet.addCell(label12);
			sheet.addCell(label13);
			List excellist = new ArrayList();
			if (date1 == null && date2 == null) {
				String hql = "from Maintenance ";
				excellist = totalDao.query(hql);
			} else {

				String hql = "from Maintenance where alarmTime>=? and alarmTime<=? order by alarmTime";
				excellist = totalDao.query(hql, date1, date2);
				// String hql =
				// "from Maintenance where alarmTime between ? and ? order by alarmTime";
				// excellist = totalDao.query(hql, date1, date2);

			}
			if (excellist != null && excellist.size() > 0) {
				// ===================================================零件明细样式
				WritableFont wf3 = new WritableFont(WritableFont.ARIAL, 10,
						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
						jxl.format.Colour.BLACK);
				WritableCellFormat wcf3 = new WritableCellFormat(wf3); // 单元格定义
				int lengNum = 1;
				for (int i = 0; i < excellist.size(); i++) {
					Maintenance maintenance = (Maintenance) excellist.get(i);
					Label contlabel0 = new Label(0, lengNum, (i + 1) + "", wcf3);
					Label contlabel1 = new Label(1, lengNum, maintenance
							.getBarcode(), wcf3);
					Label contlabel2 = new Label(2, lengNum, maintenance
							.getWorkArea(), wcf3);
					Label contlabel3 = new Label(3, lengNum, maintenance
							.getWorkPosition(), wcf3);
					Label contlabel4 = new Label(4, lengNum, maintenance
							.getNo(), wcf3);
					Label contlabel5 = new Label(5, lengNum, maintenance
							.getName(), wcf3);
					Label contlabel6 = new Label(6, lengNum, maintenance
							.getAlermMan(), wcf3);
					Label contlabel7 = new Label(7, lengNum, maintenance
							.getClassGroup(), wcf3);
					Label contlabel8 = new Label(8, lengNum, maintenance
							.getRepairMan(), wcf3);
					Label contlabel9 = new Label(9, lengNum, Util.DateToString(
							maintenance.getAlarmTime(), "yyyy-MM-dd HH:mm:ss"),
							wcf3);
					Label contlabel10 = new Label(10, lengNum, maintenance
							.getPersontime(), wcf3);
					Label contlabel11 = new Label(11, lengNum, maintenance
							.getFaultDetail(), wcf3);
					Label contlabel12 = new Label(12, lengNum, maintenance
							.getFaultReason(), wcf3);
					Label contlabel13 = new Label(13, lengNum, maintenance
							.getStatus(), wcf3);
					sheet.addCell(contlabel0);
					sheet.addCell(contlabel1);
					sheet.addCell(contlabel2);
					sheet.addCell(contlabel3);
					sheet.addCell(contlabel4);
					sheet.addCell(contlabel5);
					sheet.addCell(contlabel6);
					sheet.addCell(contlabel7);
					sheet.addCell(contlabel8);
					sheet.addCell(contlabel9);
					sheet.addCell(contlabel10);
					sheet.addCell(contlabel11);
					sheet.addCell(contlabel12);
					sheet.addCell(contlabel13);
					// 处理更换零件信息
					String hql2 = "from Parts where barcode=?";
					List partslist = totalDao.query(hql2, maintenance
							.getBarcode());
					int partsCount = 0;
					if (partslist != null && partslist.size() > 0) {
						// ==================================================更换备件明细样式
						WritableFont wf = new WritableFont(WritableFont.ARIAL); // 定义格式
						WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
						wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
						wcf
								.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

						partsCount = partslist.size();// 零件总数
						Label part0 = new Label(0, lengNum + 1, "第" + (i + 1)
								+ "条数据的设备更换备件明细", wcf);
						sheet.mergeCells(0, lengNum + 1, 1, lengNum
								+ partsCount);// 合并"更换备件明细"行数
						sheet.addCell(part0);
						for (int j = 1; j <= partsCount; j++) {
							lengNum += 1;// 累加总行数
							Parts parts = (Parts) partslist.get(j - 1);
							Label part1 = new Label(2, lengNum, j + "、");
							Label part2 = new Label(3, lengNum, "零件名称");
							Label part3 = new Label(4, lengNum, parts
									.getPartName());
							sheet.mergeCells(4, lengNum, 5, lengNum);// 合并4到5列
							Label part4 = new Label(6, lengNum, "零件规格");
							Label part5 = new Label(7, lengNum, parts
									.getPictureNo());
							sheet.mergeCells(7, lengNum, 8, lengNum);// 合并7到8列
							Label part6 = new Label(9, lengNum, "零件数量");
							Label part7 = new Label(10, lengNum, parts.getNum());

							Label part8 = new Label(11, lengNum, "零件单位");
							Label part9 = new Label(12, lengNum, parts
									.getUnit());

							Label part10 = new Label(13, lengNum, "零件价格");
							Label part11 = new Label(14, lengNum, parts
									.getMore());

							sheet.addCell(part1);
							sheet.addCell(part2);
							sheet.addCell(part3);
							sheet.addCell(part4);
							sheet.addCell(part5);
							sheet.addCell(part6);
							sheet.addCell(part7);
							sheet.addCell(part8);
							sheet.addCell(part9);
							sheet.addCell(part10);
							sheet.addCell(part11);
						}
					}
					lengNum += 1;// 累加总行数
				}
			}
			// 写入数据并关闭文件
			book.write();// 写入数据
			book.close();// 关闭文件
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "no";
	}

	/***
	 * 
	 */
	public Object[] listresponsibilities(Responsibilities responsibilities,
			int parseInt, int pageSize) {
		if (responsibilities == null) {
			responsibilities = new Responsibilities();
		}
		String hql = totalDao.criteriaQueries(responsibilities, null, null)
				+ "  and repairresponsibilitiesl='设备维修'";
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public void addresponsibilities(Responsibilities r) {
		totalDao.save(r);
	}

	public void deleteresponsibilities(Responsibilities responsibilities) {
		totalDao.delete(responsibilities);
	}

	public Responsibilities ByIdResponsibilities(Integer id) {
		return (Responsibilities) totalDao.getObjectById(
				Responsibilities.class, id);
	}

	public void updateresponsibilities(Responsibilities responsibilities) {
		totalDao.update(responsibilities);
	}

	@Override
	public String calculateUpdateTime() {
		// TODO Auto-generated method stub
		List<Maintenance> list = totalDao
				.query("from Maintenance  where status in('正常') and timetorepair is not null and  updateTime is null");
		if (list != null && list.size() > 0) {
			for (Maintenance mt : list) {
				try {
					String start = Util.DateToString(mt.getAlarmTime(),
							"yyyy-MM-dd HH:mm:ss");
					String end = mt.getPersontime();
					Long updateTimeLong = Util.getYouXiaoTime(start, end);// 秒
					Float updateTime = (float) updateTimeLong / 3600 / 1000;
					mt.setUpdateTime(updateTime);
					totalDao.update(mt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}