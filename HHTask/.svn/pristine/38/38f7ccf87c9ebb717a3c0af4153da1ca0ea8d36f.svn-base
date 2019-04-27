package com.task.ServerImpl.fin.budget;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.fin.budget.SubMonthMoneyServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.fin.budget.SaleBudget;
import com.task.entity.fin.budget.SubMonthMoney;
import com.task.entity.singlecar.SingleCar;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;
import com.tast.entity.zhaobiao.KaoQin;

/**
 * 科目预算金额serverImpl
 * 
 * @author 刘培
 * 
 */
public class SubMonthMoneyServerImpl implements SubMonthMoneyServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 查询所有月度科目预算总金额(根层)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] saveSmmByCondition(SubMonthMoney smm, int pageNo,
			int pageSize) {
		if (smm == null) {
			smm = new SubMonthMoney();
		}
		// 处理预算明细的实际金额
		/*
		 * String sql =
		 * "update ta_fin_subMonthMoney set monthrealmoney=(select sum(realmoney) from "
		 * + "ta_fin_deptMonthBudget where fk_smdmid=ta_fin_subMonthMoney.id )";
		 */
		// String sql =
		// "update ta_fin_subMonthMoney set monthrealmoney=(select sum(realmoney) from  ta_fin_deptMonthBudget "
		// + "where fk_smdmid=ta_fin_subMonthMoney.id and jhStatus='nei'),"
		// +
		// "waiMonthRealMoney=(select sum(realmoney) from  ta_fin_deptMonthBudget "
		// + "where fk_smdmid=ta_fin_subMonthMoney.id and jhStatus='wai')";
		// this.totalDao.createQueryUpdate(null, sql, null);
		//
		// String sql5 =
		// "update ta_fin_subMonthMoney set realSubjectRate=(monthrealmoney+waiMonthRealMoney)/monthBudgetMoney where monthBudgetMoney is not null and monthBudgetMoney>0";
		// this.totalDao.createQueryUpdate(null, sql5, null);
		//
		// // 处理月度预算总额
		// String sql1 =
		// "select distinct(fk_smMoneyid) fk_smMoneyid from ta_fin_subMonthMoney where fk_smMoneyid is not null  order by fk_smMoneyid desc ";
		// List<Map> list2 = (List<Map>) this.totalDao.findBySql(sql1);
		// for (int i = 0; i < list2.size(); i++) {
		// Map smm1 = (Map) list2.get(i);
		// Integer fk_smMoneyid1 = (Integer) smm1.get("fk_smMoneyid");
		// String sql2 =
		// "update ta_fin_subMonthMoney set monthrealmoney=(select sum(monthrealmoney) from ta_fin_subMonthMoney "
		// + "where fk_smMoneyid="
		// + fk_smMoneyid1
		// +
		// "),waiMonthRealMoney=(select sum(waiMonthRealMoney) from ta_fin_subMonthMoney "
		// + "where fk_smMoneyid="
		// + fk_smMoneyid1
		// + ") where id="
		// + fk_smMoneyid1 + "";
		// this.totalDao.createQueryUpdate(null, sql2, null);
		// String sql3 =
		// "update ta_fin_subMonthMoney set realSubjectRate=(monthrealmoney+waiMonthRealMoney)/monthBudgetMoney where  monthBudgetMoney is not null and monthBudgetMoney>0 and id="
		// + fk_smMoneyid1 + "";
		// this.totalDao.createQueryUpdate(null, sql3, null);
		// }
		// 查询根目录月度预算
		String hql = totalDao.criteriaQueries(smm, null) + " and belongLayer=0";
		if (!"".equals(smm.getName()) && smm.getName() != null) {
			hql += " and name like '%" + smm.getName() + "%'";
		}
		if (!"".equals(smm.getBudgetMonth()) && smm.getBudgetMonth() != null) {
			hql += " and budgetMonth like '%" + smm.getBudgetMonth() + "%'";
		}
		List list = totalDao.findAllByPage(hql + " order by budgetMonth desc",
				pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 根据rootId查询科目结构
	 * 
	 * @param rootId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SubMonthMoney> findSmmByRootId(Integer rootId, String pageStatus) {
		if (rootId != null && rootId > 0) {
			String hql = "from SubMonthMoney where rootId=?";
			if ("dept".equals(pageStatus)) {
				Users loginUser = Util.getLoginUser();
				hql += " and sbRateId in "
						+ "(select s.id from SubBudgetRate s join s.deptNumberSet d where d.id=(select id from DeptNumber where dept='"
						+ loginUser.getDept() + "' ))";
			}
			return totalDao.query(hql, rootId);
		}
		return null;
	}

	/***
	 * 根据id查询月度科目总额
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public SubMonthMoney findSmmById(Integer id) {
		if (id != null && id > 0) {
			return (SubMonthMoney) totalDao.getObjectById(SubMonthMoney.class,
					id);
		}
		return null;
	}

	/***
	 * 添加部门填报明细
	 * 
	 * @param deptMonthBudget
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addDeptMonthBudget(DeptMonthBudget deptMonthBudget,
			Integer fatherId) {
		Boolean bool = false;
		if (deptMonthBudget != null && fatherId != null) {
			SubMonthMoney subMonthMoney = findSmmById(fatherId);// 月度预算总额
			Users loginUser = Util.getLoginUser();
			if (subMonthMoney != null) {
				deptMonthBudget.setSubMonthMoney(subMonthMoney);
				deptMonthBudget.setBudgetMonth(subMonthMoney.getBudgetMonth());
				deptMonthBudget.setStatus("审核");
				deptMonthBudget.setRealMoney(0d);
				deptMonthBudget.setUsername(loginUser.getName());
				deptMonthBudget.setUserDept(loginUser.getDept());
				deptMonthBudget.setSubTime(Util.getDateTime());
				deptMonthBudget.setBorcode(Util.getDateTime("yyyyMMddHHssmm"));
				deptMonthBudget.setSubMonthMoney(subMonthMoney);
				bool = totalDao.save(deptMonthBudget);
				if (bool) {
					Integer epId;
					Users user = Util.getLoginUser();
					String processName = "";
					try {
						if ("nei".equals(deptMonthBudget.getJhStatus())) {
							processName = "部门计划内预算审核";
							epId = CircuitRunServerImpl.createProcess(
									processName, DeptMonthBudget.class,
									deptMonthBudget.getId(), "status", "id",
									user.getDept() + "部门预算计划内审核请您审核!", true,
									null);

							// epId =
							// CircuitRunServerImpl.createProcess(228,DeptMonthBudget.class,
							// deptMonthBudget.getId(), "status", "id",
							// user.getDept()
							// + "部门预算计划内审核请您审核!", true,null);
						} else {
							processName = "部门计划外预算审核";
							epId = CircuitRunServerImpl.createProcess(
									processName, DeptMonthBudget.class,
									deptMonthBudget.getId(), "status", "id",
									user.getDept() + "部门预算计划外审核请您审核!", true,
									null);
							// epId =
							// CircuitRunServerImpl.createProcess(230,DeptMonthBudget.class,
							// deptMonthBudget.getId(), "status", "id",
							// user.getDept()
							// + "部门预算计划外审核请您审核!", true,null);
						}
						if (epId != null && epId > 0) {
							deptMonthBudget.setEpId(epId);
							totalDao.update(deptMonthBudget);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
			Float accountMoney = 0F;
			// 查询该科目下所有部门填报总额
			String hql = "select sum(accountMoney) from DeptMonthBudget where subMonthMoney.id=? and budgetMonth =? and jhStatus='nei' and status='审核'";
			List<Float> list = totalDao.query(hql, fatherId, deptMonthBudget
					.getBudgetMonth());
			if (list != null && list.size() > 0) {
				accountMoney = list.get(0);
			}
			// 判断填报金额是否超过科目总金额
			if (accountMoney > subMonthMoney.getMonthBudgetMoney()) {
				String auditTime = Util.getDateTime();
				// 查询该科目下所有部门
				String hql2 = "select userDept from DeptMonthBudget where subMonthMoney.id=? and budgetMonth =? and jhStatus='nei'  and status='审核'";
				List<Float> list2 = totalDao.query(hql2, fatherId,
						deptMonthBudget.getBudgetMonth());
				String auditResult = "竞争部门:" + list2.toString();
				if (subMonthMoney.getMonthBudgetMoney() <= 0) {
					auditResult += ",虽然这个科目总预算就0元";
				}
				// 将所有部门填报的申请状态更改为"打回"
				String sql = "update  DeptMonthBudget  set status='打回',AuditTime='"
						+ auditTime
						+ "', auditResult='"
						+ auditResult
						+ "' where subMonthMoney.id=? and budgetMonth =? and jhStatus='nei' and status='审核'";
				totalDao.createQueryUpdate(sql, null, fatherId, deptMonthBudget
						.getBudgetMonth());
			}
		}
		return bool;
	}

	/***
	 * 查询该月份填报明细(计划内是否存在)
	 * 
	 * @param budgetMonth
	 * @param fatherId
	 * @param jhStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptMonthBudget> findDeptMonthBu(String budgetMonth,
			Integer fatherId, String jhStatus) {
		if ("wai".equals(jhStatus)) {
			return null;
		}
		Users loginUser = Util.getLoginUser();
		String hql = "from DeptMonthBudget where userDept=? and budgetMonth =? and subMonthMoney.id=?";
		if (jhStatus != null && jhStatus.length() > 0) {
			hql += " and jhStatus='" + jhStatus + "'";
		}
		hql += " order by jhStatus";
		return totalDao.query(hql, loginUser.getDept(), budgetMonth, fatherId);
	}

	/***
	 * 查询待审核的部门填报信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findAuditDmB(DeptMonthBudget deptMonthBudget, int pageNo,
			int pageSize, Integer rootId, String pageStatus) {
		if (deptMonthBudget == null) {
			deptMonthBudget = new DeptMonthBudget();
		}
		// 审核字符转换
		if (deptMonthBudget.getStatus() != null) {
			if ("audit".equals(deptMonthBudget.getStatus())) {
				deptMonthBudget.setStatus("审核");
			}
		}
		// 部门查看
		if (pageStatus != null && "dept".equals(pageStatus)) {
			Users loginUser = Util.getLoginUser();
			deptMonthBudget.setUserDept(loginUser.getDept());
		}
		String hql = totalDao.criteriaQueries(deptMonthBudget, null);
		if (rootId != null && rootId > 0) {
			hql += " and rootId=" + rootId;
		}
		List list = totalDao.findAllByPage(hql + " order by budgetMonth desc",
				pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 审批操作
	 * 
	 * @param submmIds
	 * @param pageStatus
	 * @return
	 */
	@Override
	public boolean updateAudit(Integer[] submmIds, String pageStatus) {
		boolean bool = false;
		if (submmIds != null && submmIds.length > 0) {
			if (pageStatus != null && pageStatus.length() > 0) {
				if ("ok".equals(pageStatus)) {
					pageStatus = "同意";
				} else {
					pageStatus = "打回";
				}
			} else {
				return bool;
			}
			String auditTime = Util.getDateTime();
			for (Integer submmId : submmIds) {
				DeptMonthBudget deptMonthBudget = (DeptMonthBudget) totalDao
						.getObjectById(DeptMonthBudget.class, submmId);
				if (deptMonthBudget != null) {
					deptMonthBudget.setStatus(pageStatus);
					deptMonthBudget.setAuditTime(auditTime);
					bool = totalDao.update(deptMonthBudget);
				}

			}
		}
		return bool;
	}

	/***
	 * 通过id查询部门填报明细信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public DeptMonthBudget findDeptMonthBudgetById(Integer id) {
		if (id != null && id > 0) {
			return (DeptMonthBudget) totalDao.getObjectById(
					DeptMonthBudget.class, id);
		}
		return null;
	}

	/***
	 * 修改部门填报明细信息
	 * 
	 * @param dmBudget
	 * @return
	 */
	@Override
	public boolean updateDeptMonthBudget(DeptMonthBudget dmBudget) {
		if (dmBudget != null) {
			return totalDao.update(dmBudget);
		}
		return false;
	}

	/***
	 * 修改部门填报明细信息
	 * 
	 * @param dmBudget
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateDeptMonthBudget(DeptMonthBudget dmBudget,
			DeptMonthBudget oldDmBudget) {
		boolean bool = false;
		if (dmBudget != null) {
			oldDmBudget.setAccountMoney(dmBudget.getAccountMoney());
			oldDmBudget.setBudgetDetail(dmBudget.getBudgetDetail());
			oldDmBudget.setStatus("审核");
			bool = totalDao.update(oldDmBudget);
			if (bool = false) {
				return bool;
			}
			Float accountMoney = 0F;
			// 查询该科目下所有部门填报总额
			String hql = "select sum(accountMoney) from DeptMonthBudget where subMonthMoney.id=? and budgetMonth =? and jhStatus='nei'";
			List<Float> list = totalDao.query(hql, oldDmBudget
					.getSubMonthMoney().getId(), oldDmBudget.getBudgetMonth());
			if (list != null && list.size() > 0) {
				accountMoney = list.get(0);
			}
			// 判断填报金额是否超过科目总金额
			if (accountMoney > oldDmBudget.getSubMonthMoney()
					.getMonthBudgetMoney()) {
				String auditTime = Util.getDateTime();
				// 将所有部门填报的申请状态更改为"打回"
				String sql = "update  DeptMonthBudget  set status='打回',AuditTime='"
						+ auditTime
						+ "' where subMonthMoney.id=? and budgetMonth =? and jhStatus='nei' ";
				totalDao.createQueryUpdate(sql, null, oldDmBudget
						.getSubMonthMoney().getId(), oldDmBudget
						.getBudgetMonth());
			}
			bool = true;
		}
		return bool;
	}

	/***
	 * 删除部门填报明细信息
	 * 
	 * @param dmBudget
	 * @return
	 */
	@Override
	public boolean delDeptMonthBudget(DeptMonthBudget dmBudget) {
		if (dmBudget != null) {
			return totalDao.delete(dmBudget);
		}
		return false;
	}

	/*
	 * 
	 * 到处Excl(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.fin.budget.SubMonthMoneyServer#exportExcel(java.lang.
	 * String)
	 */
	@Override
	public void exportExcel(String budgetMonth) {
		// where budgetMonth like '%" + budgetMonth + "%'
		String hql = " from SubMonthMoney";
		if (budgetMonth != null && !"".equals(budgetMonth)) {
			hql = hql + " " + " where budgetMonth like '%" + budgetMonth + "%'";
		}
		List list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("月度预算总额汇总".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("月度预算总额汇总", 0);
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
			jxl.write.Label label0 = new Label(0, 0, "月度预算报表", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 8, 0);
			wf = new WritableFont(WritableFont.ARIAL, 18, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);

			// //定义标题单元格样式1
			// WritableFont wf1 = new WritableFont(WritableFont.ARIAL); // 定义格式
			// WritableCellFormat wcf1 = new WritableCellFormat(wf1); // 单元格定义
			// wcf1.setAlignment(jxl.format.Alignment.JUSTIFY); // 设置对齐方式
			// wcf1.setBorder(jxl.format.Border.ALL,
			// jxl.format.BorderLineStyle.THIN);// 添加细边框样式
			//				
			// // 定义单元格样式 定义格式 字体 下划线 颜色 斜体 粗体
			WritableCellFormat wcf2 = new WritableCellFormat(new WritableFont(
					WritableFont.ARIAL, 10)); // 单元格定义
			wcf2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);// 添加细边框样式
			// 创建一个样式
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "编号", wcf2));
			ws.addCell(new jxl.write.Label(1, 1, "名称", wcf2));
			ws.addCell(new jxl.write.Label(2, 1, "月份", wcf2));
			ws.addCell(new jxl.write.Label(3, 1, "科目所占比例", wcf2));
			ws.addCell(new jxl.write.Label(4, 1, "预算金额", wcf2));
			ws.addCell(new jxl.write.Label(5, 1, "实际支出金额", wcf2));
			ws.addCell(new jxl.write.Label(6, 1, "计划内金额", wcf2));
			ws.addCell(new jxl.write.Label(7, 1, "计划外金额", wcf2));
			ws.addCell(new jxl.write.Label(8, 1, "实际所占比例", wcf2));

			// 格式
			ws.setRowView(1, 1000);// 第二行的高度
			ws.setColumnView(0, 10);// 第一列的宽度
			ws.setColumnView(1, 20);// 第二列的宽度
			ws.setColumnView(2, 10);// 第三列的宽度
			ws.setColumnView(3, 15);// 第四列的宽度
			ws.setColumnView(4, 15);// 第五列的宽度
			ws.setColumnView(5, 15);// 第六列的宽度
			ws.setColumnView(6, 15);// 第七列的宽度
			ws.setColumnView(7, 15);// 第八列的宽度
			ws.setColumnView(8, 15);// 第九列的宽度
			ws.setColumnView(9, 15);// 第十列的宽度
			ws.setColumnView(10, 15);// 第十一列的宽度

			// 把水平对齐方式指定为居中
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
					WritableFont.BOLD);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			format1.setAlignment(jxl.format.Alignment.CENTRE);

			DecimalFormat fnum = new DecimalFormat("##0.00"); // 保留两位小数
			for (int i = 0; i < list.size(); i++) {
				ws.setRowView(i + 2, 500);// 循环调整每行的高度（从第二行开始）
				SubMonthMoney go = (SubMonthMoney) list.get(i);
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wcf2));
				ws.addCell(new Label(1, i + 2, go.getName(), wcf2));
				ws.addCell(new Label(2, i + 2, go.getBudgetMonth(), wcf2));
				if (go.getSubjectRate() == null) {
					go.setSubjectRate(0F);
				}
				ws.addCell(new Label(3, i + 2,
						fnum.format(go.getSubjectRate()), wcf2));
				// ws.addCell(new jxl.write.Number(3, i+2,
				// Float.parseFloat(String.format("%.2f",
				// Float.parseFloat(fnum.format(go.getSubjectRate())))),wcf2));
				if (go.getMonthBudgetMoney() == null) {
					go.setMonthBudgetMoney(0F);
				}
				ws.addCell(new Label(4, i + 2, fnum.format(go
						.getMonthBudgetMoney()), wcf2));
				// ws.addCell(new jxl.write.Number(4, i +
				// 2,Float.parseFloat(String.format("%.2f",go.getMonthBudgetMoney())),wcf2));
				if (go.getMonthRealMoney() == null) {
					go.setMonthRealMoney(0F);
				}
				if (go.getWaiMonthRealMoney() == null) {
					go.setWaiMonthRealMoney(0F);
				}
				ws.addCell(new Label(5, i + 2, fnum.format(go
						.getMonthRealMoney()
						+ go.getWaiMonthRealMoney()), wcf2));
				ws.addCell(new Label(6, i + 2, fnum.format(go
						.getMonthRealMoney()), wcf2));
				ws.addCell(new Label(7, i + 2, fnum.format(go
						.getWaiMonthRealMoney()), wcf2));
				// ws.addCell(new jxl.write.Number(5, i + 2,
				// Float.parseFloat(String.format("%.2f",go.getMonthRealMoney()+go.getWaiMonthRealMoney())),wcf2));
				// ws.addCell(new jxl.write.Number(6, i +
				// 2,Float.parseFloat(String.format("%.2f",go.getMonthRealMoney())),wcf2));
				// ws.addCell(new jxl.write.Number(7, i +
				// 2,Float.parseFloat(String.format("%.2f",go.getWaiMonthRealMoney())),wcf2));
				if (go.getRealSubjectRate() == null) {
					go.setRealSubjectRate(0F);
				}
				ws.addCell(new Label(8, i + 2, fnum.format(go
						.getRealSubjectRate()), wcf2));
				// ws.addCell(new jxl.write.Number(8, i +
				// 2,Float.parseFloat(String.format("%.2f",go.getRealSubjectRate())),wcf2));
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
	public void exportExcel1(String budgetMonth) {
		// TODO Auto-generated method stub
		String hql = "select userDept,name,budgetMonth,accountMoney,(select sum(accountMoney) from DeptMonthBudget "
				+ "where userDept=t1.userDept and jhStatus='wai' and budgetMonth=t1.budgetMonth and  name=t1.name), realMoney "
				+ "from DeptMonthBudget t1  where status='同意' and jhStatus='nei'";
		if (budgetMonth != null && !"".equals(budgetMonth)) {
			hql = hql + " and budgetMonth='" + budgetMonth + "'";
		}
		hql = hql + " order by budgetMonth,userDept";
		List list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("部门月度科目预算汇总".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("部门月度科目预算汇总", 0);
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
			jxl.write.Label label0 = new Label(0, 0, "部门月度科目预算报表", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 8, 0);
			wf = new WritableFont(WritableFont.ARIAL, 18, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);

			// 定义单元格样式 定义格式 字体 下划线 颜色 斜体 粗体
			WritableCellFormat wcf2 = new WritableCellFormat(new WritableFont(
					WritableFont.ARIAL, 10)); // 单元格定义
			wcf2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);// 添加细边框样式
			// 创建一个样式
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "编号", wcf2));
			ws.addCell(new jxl.write.Label(1, 1, "部门", wcf2));
			ws.addCell(new jxl.write.Label(2, 1, "科目", wcf2));
			ws.addCell(new jxl.write.Label(3, 1, "月份", wcf2));
			ws.addCell(new jxl.write.Label(4, 1, "计划内金额", wcf2));
			ws.addCell(new jxl.write.Label(5, 1, "计划外金额", wcf2));
			ws.addCell(new jxl.write.Label(6, 1, "实际花费金额", wcf2));

			// 格式
			ws.setRowView(1, 1000);// 第二行的高度
			ws.setColumnView(0, 10);// 第一列的宽度
			ws.setColumnView(1, 15);// 第二列的宽度
			ws.setColumnView(2, 20);// 第三列的宽度
			ws.setColumnView(3, 15);// 第四列的宽度
			ws.setColumnView(4, 15);// 第五列的宽度
			ws.setColumnView(5, 15);// 第六列的宽度
			ws.setColumnView(6, 15);// 第七列的宽度

			// 把水平对齐方式指定为居中
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
					WritableFont.BOLD);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			format1.setAlignment(jxl.format.Alignment.CENTRE);

			DecimalFormat fnum = new DecimalFormat("##0.00"); // 保留两位小数
			for (int i = 0; i < list.size(); i++) {
				ws.setRowView(i + 2, 500);// 循环调整每行的高度（从第二行开始）
				Object[] obj = (Object[]) list.get(i);
				// DeptMonthBudget go = (DeptMonthBudget) list.get(i);
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wcf2));
				ws.addCell(new Label(1, i + 2, (String) obj[0], wcf2));
				ws.addCell(new Label(2, i + 2, (String) obj[1], wcf2));
				ws.addCell(new Label(3, i + 2, (String) obj[2], wcf2));
				if (obj[3] == null) {
					obj[3] = 0F;
				}
				ws.addCell(new Label(4, i + 2, fnum.format(obj[3]), wcf2));
				if (obj[4] == null) {
					obj[4] = 0F;
				}
				ws.addCell(new Label(5, i + 2, fnum.format(obj[4]), wcf2));

				Float a = Float.parseFloat(obj[4].toString());
				Float b = Float.parseFloat(obj[3].toString());
				Float c = a + b;
				// if(obj[5]==null){
				// obj[5]=0F;
				// }
				if (c == null) {
					c = 0F;
				}
				ws.addCell(new Label(6, i + 2, fnum.format(c), wcf2));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 月度预算审核列表
	 * 
	 * @see com.task.Server.fin.budget.SubMonthMoneyServer#findExamList(int,
	 * int)
	 */
	@Override
	public Object[] findExamList(int parseInt, int pageSize, DeptMonthBudget dmb) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				DeptMonthBudget.class, false);
		if (map != null) {
			if (dmb == null) {
				dmb = new DeptMonthBudget();
			}
			String hql = totalDao.criteriaQueries(dmb, null)
					+ " and id in (:entityId)";
			List list = totalDao.find(hql + " order by userDept", map,
					parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	/*
	 * 月度预算审核列表(计划外)(non-Javadoc)
	 * 
	 * @see com.task.Server.fin.budget.SubMonthMoneyServer#findExamList1(int,
	 * int)
	 */
	@Override
	public Object[] findExamList1(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				DeptMonthBudget.class, false);
		if (map != null) {
			String hql = "from DeptMonthBudget where id in (:entityId) and jhStatus='wai'";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	/*
	 * 审批(通过、驳回)(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.fin.budget.SubMonthMoneyServer#updateExamBonus(java.lang
	 * .Integer[], java.lang.String)
	 */
	@Override
	public boolean updateExamBonus(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				DeptMonthBudget detail = (DeptMonthBudget) totalDao
						.getObjectById(DeptMonthBudget.class, detailSelect[i]);
				CircuitRun circuitRun = circuitRunServer
						.findCircuitRunById(detail.getEpId());
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", true, null, false);
					if ("同意".equals(circuitRun.getAllStatus())) {
						// 审批同意后做后续操作
					}
					// 更改明细状态
				} else {// 打回
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, true);
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

}
