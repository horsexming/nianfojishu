package com.task.ServerImpl.menjin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.menjin.VisitServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.menjin.Access;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.Visit;
import com.task.entity.menjin.VisitCo;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class VisitServerImpl implements VisitServer {
	private TotalDao totalDao;

	// 添加来访登记
	public boolean addVisit(Visit visit) {
		String visitsLic = "";
		if (visit.getVisitsLic() != null) {
			visitsLic = visit.getVisitsLic().trim().replaceAll("O", "0")
					.replaceAll("I", "1").replaceAll("o", "0").replaceAll("i",
							"1").replaceAll(" ", "");
		}
		visit.setVisitsLic(visitsLic);
		visit.setVisitStatus("未审批");
		visit.setVisit_laiStatus("未进门");// 生成申请记录，未审批
		visit.setAddTime(Util.getDateTime());
		boolean b = this.totalDao.save(visit);

		// 调用审批流程
		// String processName = visit.getShouFangDept() + "部门来访申请";
		String processName = "来访申请";
		Integer epId = null;
		try {
			epId = CircuitRunServerImpl.createProcess(processName, Visit.class,
					visit.getId(), "visitStatus", "id", "", visit
							.getShouFangDept()
							+ "部门    "
							+ visit.getVisitsName()
							+ " 来访 "
							+ visit.getShouFangName() + " ，请您审批！", true);
			if (epId != null && epId > 0) {
				visit.setEpId(epId);
				totalDao.update(visit);

				// 申请成功之后将此车辆之前进门已识别记录更新为已申请
				List<AccessRecords> list = totalDao
						.query(
								"from AccessRecords where recordContents=? and recordStatus='已识别' and addTime>? and recordType ='车牌' and openType='进门'",
								visit.getVisitsLic(), Util
										.getDateTime("yyyy-MM-dd"));
				if (list != null && list.size() > 0) {
					for (AccessRecords accessRecords : list) {
						accessRecords.setRecordStatus("已申请");
						totalDao.update(accessRecords);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * if (b) { try { String access =
		 * AccessServerImpl.createAccessRecord(visit .getVerifyManner(),
		 * visit.getVisitsName(), visit .getVisitstime(), visit.getVisitsLic(),
		 * Visit.class, visit.getId()); if (access != null &&
		 * !"".equals(access)) { visit.setVisitsCode(access);
		 * totalDao.update(visit); } } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
		return b;
	}

	@Override
	public boolean addVisitwithoutApply(Visit visit) {
		String visitsLic = "";
		if (visit.getVisitsLic() != null) {
			visitsLic = visit.getVisitsLic().trim().replaceAll("O", "0")
					.replaceAll("I", "1").replaceAll("o", "0").replaceAll("i",
							"1").replaceAll(" ", "");
		}
		//
		// Users users=Util.getLoginUser();
		visit.setApplyName("门卫");
//		visit.setCode(users.getCode());
		visit.setVisitsName(visit.getVisitsLic());
		//
		visit.setVerifycar("是");
		visit.setVerifyManner("车牌");
		visit.setVisitsLic(visitsLic);
		visit.setVisitStatus("同意");
		visit.setVisit_laiStatus("未进门");// 生成申请记录，未审批
		visit.setAddTime(Util.getDateTime());
		boolean b = this.totalDao.save(visit);

		// ------------------------------------
		// 申请成功之后将此车辆之前进门已识别记录更新为已申请
		List<AccessRecords> list = totalDao
				.query(
						"from AccessRecords where recordContents=? and recordStatus='已识别' and addTime>? and recordType ='车牌' and openType='进门'",
						visit.getVisitsLic(), Util.getDateTime("yyyy-MM-dd"));
		if (list != null && list.size() > 0) {
			for (AccessRecords accessRecords : list) {
				accessRecords.setRecordStatus("已申请");
				totalDao.update(accessRecords);
			}
		}

		// ------------------------------------
		if (visit != null) {
			Access access1 = (Access) totalDao.getObjectByCondition(
					"from Access where entityName=? and entityId=?", "Visit",
					visit.getId());
			if (access1 == null) {
				Access access = null;
				try {
					access = AccessServerImpl.createAccessRecord(visit
							.getVerifycar(), visit.getVerifyManner(), visit
							.getVisitsName(), visit.getVisitstime(), visit
							.getVisitsLic(), null, visit.getVisitsTel(),
							Visit.class, visit.getId());
					if (access != null) {
						if ("车牌".equals(access.getYanzheng())) {
							visit.setVisitsCode(access.getCarPai());
						} else if ("手机".equals(access.getYanzheng())) {
							visit.setVisitsCode(access.getYanzhengnum());
						}
						visit.setVisitsAllCode(access.getYanzhengnum());
						visit.setVisit_laiStatus("进门中");// 审批已同意 进门中
						totalDao.update(visit);
						// 触发摄像头识别
						// 获取最后一条 来访进门记录 申请来访时间为今天（小于明天）的就触发一次摄像头
						Access access2 = (Access) totalDao
								.getObjectByCondition("from Access where outInDoor='进门' and yanzheng ='车牌' order by id desc");
						if (access2 != null) {
							if (Util.getDateDiff(Util.getDateTime(),
									"yyyy-MM-dd HH:mm:ss", access2
											.getVisitstime(), "yyyy-MM-dd") < 0) {// 第二数减去第一数
								if (AccessServerImpl.RriggerCmd(access2
										.getAccess_EquIp()) != null) {
									System.out.println("触发成功");
								} else {
									System.out.println("触发失败");
								}
							} else {
								System.out.println("不是今天进门，不用触发");
							}
						}
						Access access3 = (Access) totalDao
						.getObjectByCondition("from Access where outInDoor='出门' and yanzheng ='车牌' order by id desc");
						if (access3 != null) {
							if (Util.getDateDiff(Util.getDateTime(),
									"yyyy-MM-dd HH:mm:ss", access3
									.getVisitstime(), "yyyy-MM-dd") < 0) {// 第二数减去第一数
								if (AccessServerImpl.RriggerCmd(access2
										.getAccess_EquIp()) != null) {
									System.out.println("触发成功");
								} else {
									System.out.println("触发失败");
								}
							} else {
								System.out.println("不是今天进门，不用触发");
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					access = null;
					e.printStackTrace();
				}
			}
		}
		// ------------------------------------

		// TODO Auto-generated method stub
		try {
			String access = AccessServerImpl.updateAccessRecordwithoutApply(
					Visit.class, visit.getId(), visit.getVisitsTel());

			if (access != null && !"".equals(access)) {
				visit.setVisitsCode(access);
				visit.setVisit_laiStatus("出门中");
				totalDao.update(visit);
				// 触发摄像头识别
				// 获取最后一条 来访出门记录 申请来访时间为今天（小于明天）的就触发一次摄像头
				Access access2 = (Access) totalDao
						.getObjectByCondition(
								"from Access where outInDoor='出门' and addTime>? order by id desc",
								Util.getDateTime("yyyy-MM-dd"));
				if (access2 != null) {
					System.out.println(access2.getId());
					if (AccessServerImpl.RriggerCmd(access2.getAccess_EquIp()) != null) {
						System.out.println("触发成功");
					} else {
						System.out.println("触发失败");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return b;
	}

	// 查询
	public Object[] findVisitByCondition(Visit visit, int parseInt,
			int pageSize, String taga) {
		if (visit == null) {
			visit = new Visit();
		}

		String sql = "";
		if ("dept".equals(taga)) {
			Users users = Util.getLoginUser();
			sql = " shouFangDept='" + users.getDept() + "'";
		} else {
		}
		String hql = totalDao.criteriaQueries(visit, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 通过ID来查询来访登记
	public Visit salvisitByid(Integer integer) {
		return (Visit) this.totalDao.getObjectById(Visit.class, integer);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 根据id 生成出门信息
	@Override
	public String backOut(Visit visit) {
		// TODO Auto-generated method stub
		try {
			String access = AccessServerImpl.updateAccessRecord(Visit.class,
					visit.getId(), visit.getVisitsTel());
			if (access != null && !"".equals(access)) {
				visit.setVisitsCode(access);
				visit.setVisit_laiStatus("出门中");
				totalDao.update(visit);
				// 触发摄像头识别
				// 获取最后一条 来访出门记录 申请来访时间为今天（小于明天）的就触发一次摄像头
				Access access2 = (Access) totalDao
						.getObjectByCondition(
								"from Access where outInDoor='出门' and addTime>? order by id desc",
								Util.getDateTime("yyyy-MM-dd"));
				if (access2 != null) {
					System.out.println(access2.getId());
					if (AccessServerImpl.RriggerCmd(access2.getAccess_EquIp()) != null) {
						System.out.println("触发成功");
					} else {
						System.out.println("触发失败");
					}
				}
				return "来访已结束";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "出门失败";
	}

	@Override
	public List allWeisb() {
		// TODO Auto-generated method stub
		// List list = totalDao
		// .query(
		// "select distinct recordContents from AccessRecords where recordStatus <> '已识别' and addTime>? and recordType ='车牌' and openType='进门'",
		// Util.getDateTime("yyyy-MM-dd"));
		// String accessRecord = "'"
		// + list.toString().replaceAll("\\[", "").replaceAll("\\]", "")
		// .replaceAll(",", "','").replaceAll(" ", "") + "'";

		String hql = "select recordContents from AccessRecords where recordStatus='已识别' and addTime>? and recordType ='车牌' order by id asc";//and openType='进门'
		List list1 = totalDao.query(hql, Util.getDateTime(-10));// 当天已识别的车牌，去重复
		return list1;
	}

	@Override
	public String agreen(Visit visit) {
		// TODO Auto-generated method stub
		if (visit != null) {
			if ("车牌".equals(visit.getVerifyManner())) {
				Visit visit3 = new Visit();
				BeanUtils.copyProperties(visit, visit3, new String[] { "id",
						"applyName", "code", "addTime", "visitstime",
						"visitStatus", "visit_laiStatus" });
				visit3.setAddTime(Util.getDateTime());
				visit3.setVisitstime(Util.getDateTime("yyyy-MM-dd"));
				Users users = Util.getLoginUser();
				visit3.setApplyName(users.getName());
				visit3.setCode(users.getCode());
				visit3.setVisitStatus("未审批");
				visit3.setVisit_laiStatus("未进门");
				if (totalDao.save(visit3)) {
					// 调用离职申请审批流程
					String processName = visit3.getShouFangDept() + "部门来访申请";
					Integer epId = null;
					try {
						epId = CircuitRunServerImpl.createProcess(processName,
								Visit.class, visit3.getId(), "visitStatus",
								"id", "", visit3.getShouFangDept() + "部门    "
										+ visit3.getVisitsName() + " （再次）来访 "
										+ visit3.getShouFangName() + " ，请您审批！",
								true);
						if (epId != null && epId > 0) {
							visit3.setEpId(epId);
							totalDao.update(visit3);
							// 申请成功之后将此车辆之前进门已识别记录更新为已申请
							List<AccessRecords> list1 = totalDao
									.query(
											"from AccessRecords where recordContents=? and recordStatus='已识别' and addTime>? and recordType ='车牌' and openType='进门'",
											visit.getVisitsLic(), Util
													.getDateTime("yyyy-MM-dd"));
							if (list1 != null && list1.size() > 0) {
								for (AccessRecords accessRecords : list1) {
									accessRecords.setRecordStatus("已申请");
									totalDao.update(accessRecords);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return "true";
				}
			}
		}
		return null;
	}

	@Override
	public int visitTel(Visit visit) {
		// TODO Auto-generated method stub
		// 加上手机号码不重复验证
		int visit2 = totalDao
				.getCount(
						"from Visit where visit_laiStatus <> '已出门' and verifyManner = '手机' and visitsTel = ? and visitstime= ? ",
						visit.getVisitsTel(), visit.getVisitstime());
		return visit2;
	}

	@Override
	public boolean deleteVisit(Visit visit) {
		// TODO Auto-generated method stub
		if (visit != null) {
			if (CircuitRunServerImpl.deleteCircuitRun(visit.getEpId()))
				return totalDao.delete(visit);
		}
		return false;
	}

	@Override
	public List findVisitS() {
		// TODO Auto-generated method stub Util.getDateTime("yyyy-MM-dd")
		return totalDao
				.query(
						"from Visit where visit_laiStatus in ('进门中','出门中') and visitstime = ?",
						Util.getDateTime("yyyy-MM-dd"));
	}

	@Override
	public int visitCarpai(String car) {
		// TODO Auto-generated method stub
		return totalDao
				.getCount(
						"from InEmployeeCarInfor where nplates = ? and carInCangType = '内部'",
						car);
	}

	@Override
	public List<VisitCo> findvisitColist() {
		List<VisitCo> list = totalDao.query("from VisitCo", null);
		return list;
	}

	@Override
	public String addVisitOut(Visit visit) {
		// TODO Auto-generated method stub
		Visit visit2 = new Visit();
		Users u = Util.getLoginUser();
		visit2.setApplyName(u.getName());
		visit2.setCode(u.getCode());
		visit2.setVisitsLic("沪C3L415");
		visit2.setVisitsCode("沪C3L415");
		visit2.setVerifyManner("车牌");
		visit2.setVisitStatus("未审批");
		visit2.setVisit_laiStatus("未出门");// 生成申请记录，未审批
		visit2.setAddTime(Util.getDateTime());
		visit2.setVisitstime(Util.getDateTime("yyyy-MM-dd"));
		boolean b = this.totalDao.save(visit2);

		// 调用审批流程
		String processName = "内部车辆出门申请";
		Integer epId = null;
		try {
			epId = CircuitRunServerImpl.createProcess(processName, Visit.class,
					visit2.getId(), "visitStatus", "id", "", visit2
							.getVisitsLic()
							+ " 出门申请，请您审批   ", true);
			if (epId != null && epId > 0) {
				visit2.setEpId(epId);
				b = totalDao.update(visit2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "申请失败！";
		}
		if (b) {
			return "true";
		} else {
			return "申请失败！";
		}
	}

	@Override
	public void findUsers() {
		// TODO Auto-generated method stub
		List list = totalDao
				.query("from Users where onWork in ('在职','试用','实习') and "
						+ "dept not in ('供应商','内退','客户','病休','工具（承包）','零组件班','上海彤庆','昆山惠明','苏州恒昌','上海璨坤','上海庆霆不锈钢制品有限公司') and password.censusNature = '农业'");
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("农业户口人员汇总".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("人员汇总", 0);
			ws.setColumnView(1, 15);
			ws.setColumnView(2, 10);
			ws.setColumnView(4, 18);
			ws.setColumnView(5, 15);
			// ws.setColumnView(6, 20);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);

			jxl.write.Label label0 = new Label(0, 0, "人员汇总", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 5, 0);

			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "姓名", wc));
			ws.addCell(new jxl.write.Label(2, 1, "工号", wc));
			ws.addCell(new jxl.write.Label(3, 1, "部门", wc));
			ws.addCell(new jxl.write.Label(4, 1, "户籍性质", wc));
			ws.addCell(new jxl.write.Label(5, 1, "员工性质", wc));
			// ws.addCell(new jxl.write.Label(6, 1, "当月充值金额(元)", wc));

			for (int i = 0; i < list.size(); i++) {
				Users go = (Users) list.get(i);
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, go.getName(), wc));
				ws.addCell(new Label(2, i + 2, go.getCode(), wc));
				ws.addCell(new Label(3, i + 2, go.getDept(), wc));
				ws.addCell(new Label(4, i + 2, go.getPassword()
						.getCensusNature(), wc));
				ws.addCell(new Label(5, i + 2, go.getPassword()
						.getStaffNature(), wc));
				// ws.addCell(new jxl.write.Number(6, i + 2,
				// go.getSumNumberZong(), wc));
			}
			wwb.write();
			wwb.close();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
