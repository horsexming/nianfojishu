package com.task.action;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.BonusServer;
import com.task.Server.ContractBonusServer;
import com.task.Server.zgkh.AssessPersonnelServer;
import com.task.entity.Bonus;
import com.task.entity.Bonusmoney;
import com.task.entity.ContractBonus;
import com.task.entity.Teammembers;
import com.task.entity.Users;
import com.task.entity.UsersGroup;
import com.task.entity.WageStandard;

@SuppressWarnings( { "unchecked", "unused" })
public class BonusAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Bonus bonus;// 奖金分配明细表
	private Bonusmoney bonusmoney = new Bonusmoney();// 奖金总额表
	private BonusServer bonusServer;
	private ContractBonusServer contractBonusServer;// 承包奖金总额Server层
	private ContractBonus contractBonus;// 承包奖金总额
	private String errorMessage;
	private List<Bonus> list = new ArrayList<Bonus>();
	private List<WageStandard> wsList;// 工资模版集合
	private AssessPersonnelServer assessPersonnelServer;// Server层
	private List groupList;// 集合
	private int userGroupId;// 分组Id
	private String tag;
	private Integer[] detailSelect;// 选择补打数组,审批数组

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	// 根据登入人的部门查询出所对应班组的成员
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		list = bonusServer.finDept(user.getId());
		if (list.size() >= 0) {
			return "execute";
		} else {
			errorMessage = "没有找到你的成员信息,请先添加班组成员";
			return "error";
		}
	}

	// 根据登入人的id查询出所对应班组的成员
	public String findAllTeammembers() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		if ((Object) userGroupId != null && userGroupId > 0) {
			UsersGroup up = assessPersonnelServer
					.findUsersGroupById(userGroupId);
			if (up != null) {
				if (errorMessage != null && "chengbao".equals(errorMessage)) {
					contractBonus = contractBonusServer.findCBByMouthAndDept(
							user.getId(), null, "同意");// 查询该用户上个月状态为同意的承包总金额
					if (contractBonus != null) {
						list = bonusServer.finDept(userGroupId, "chengbao");// 查询出所有成员
						return "findAllTeammembers";
					} else {
						errorMessage = "本部门上个月的承包奖金总额尚未添加或则正在审核中!请等待!";
					}
				} else if (errorMessage != null && "bing".equals(errorMessage)) {
					wsList = bonusServer.finDept(userGroupId, "manage");// 查询出所有成员
					return "findAllTeammembers";
				}
			}
		} else {
			// 判断成员组是否大于1个
			groupList = assessPersonnelServer.findUsersGroupByUid();
			if (groupList != null && groupList.size() <= 1) {
				if (errorMessage != null && "chengbao".equals(errorMessage)) {
					contractBonus = contractBonusServer.findCBByMouthAndDept(
							user.getId(), null, "同意");// 查询该用户上个月状态为同意的承包总金额
					if (contractBonus != null) {
						list = bonusServer.finDept(0, "chengbao");// 查询出所有承包奖金分配成员
						return "findAllTeammembers";
					} else {
						errorMessage = "本部门上个月的承包奖金总额尚未添加或则正在审核中!请等待!";
					}
				} else if (errorMessage != null && "bing".equals(errorMessage)) {
					wsList = bonusServer.finDept(0, "manage");// 查询出所有长期病假成员的工资模版
					return "findAllTeammembers";
				}
			} else {
				return "findAllTeammembers";
			}
		}
		return ERROR;
	}

	private String[] quxuanName;
	private String todayDate;
	private Bonus newbonus = new Bonus();

	// 审批查询
	public String findExamList() {
		Object[] obj = bonusServer.findExamList(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("BonusAction!findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamListOK";
	}

	// 批量审批
	public String updateExamBonus() {
		try {
			if (bonusServer.updateExamBonus(detailSelect, tag)) {
				return "updateExamBonusOK";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	// 奖金分配前获得需要分配奖金人的信息
	public String saveBonusPeople() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		int r = Integer.parseInt(data.substring(8, 10));// 日
		int s = Integer.parseInt(data.substring(11, 13));// 时
		// if (r < 5 || r > 11) {
		// errorMessage = "对不起，你已超过了奖金分配的时间或者奖金分配时间没到";
		// return ERROR;
		// } else if (r == 5) {
		// if (s < 8) {
		// errorMessage = "对不起，你已超过了奖金分配的时间或者奖金分配时间没到";
		// return ERROR;
		// }
		// } else if (r == 11) {
		// if (s > 8) {
		// errorMessage = "对不起，你已超过了奖金分配的时间或者奖金分配时间没到";
		// return ERROR;
		// }
		// }
		if (todayDate != null && !todayDate.equals("")) {
			List listAull = bonusServer.findStatus(todayDate, user.getDuty());// 查询奖金分配的总额表
			if (listAull != null && listAull.size() > 0) {
				errorMessage = "该月的奖金分配已经同意,无需修改.请前往查询页面查看!谢谢!";
				return ERROR;
			}

			List datelist = bonusServer.finddate(todayDate);// 根据奖金分配的时间查询出提奖的总金额
			List listall = bonusServer.findzhiwei(todayDate, user.getDuty());// 根据时间和登入人的职位查询提奖明细是否存在
			if (datelist.size() > 0) {
				if (listall != null) {
					if (quxuanName != null) {
						String date = "";
						out: for (int i = 0; i < quxuanName.length; i++) {
							String id = quxuanName[i];
							Teammembers teammembers = bonusServer
									.findByID(Integer.parseInt(id));
							for (int j = 0; j < listall.size(); j++) {
								newbonus = (Bonus) listall.get(j);
								if (!"部留".equals(newbonus.getBonusteamname())
										&& newbonus
												.getBonusmembernumber()
												.equals(
														teammembers
																.getTeammembersmembernumber())) {
									continue out;
								}
							}
							if (teammembers != null) {
								date = new SimpleDateFormat(
										"yyyy-MM-dd HH:mm:ss")
										.format(new Date());
								newbonus.setBonusdata(todayDate); // 月份
								newbonus.setBonuscardnumber(teammembers
										.getTeammemberscardnumber()); // 成员卡号
								newbonus.setBonusmembernumber(teammembers
										.getTeammembersmembernumber());// 成员工号
								newbonus.setBonusovertimemealmoney(0F);// 加班费及饭贴
								newbonus.setBonusmembermoney(0F); // 成员奖金
								newbonus.setBonusteamname(teammembers
										.getTeammembersteamname());// 成员姓名
								newbonus.setBonusteam(user.getDuty());// 班组
								newbonus.setBonusdatatime(date);// 时间锥
								bonusServer.saveBonus(newbonus);
							}
						}
						List listmoney = bonusServer.findBonusmoney(todayDate,
								user.getDuty()); // 根据月份和班组判断奖金表中是否存在
						List bolist = bonusServer.findDpetbuliuMoney(todayDate,
								user.getDuty());// 根据月份和班组判断奖金明细表中是否存在 部留
						// Bonusmoney bonusmoney = (Bonusmoney)
						// listmoney.get(0);
						// String aaa = bonusmoney.getBonusmoneystatus();

						if (listmoney.size() <= 0 && bolist.size() <= 0) {
							// 添加部留
							Bonus newBonus = new Bonus();
							newBonus.setBonusmembermoney(0F);// 部留
							newBonus.setBonusovertimemealmoney(0F);// 加班费及饭贴
							newBonus.setBonusteamname("部留");// 名称
							newBonus.setBonusdata(todayDate);// 月份
							newBonus.setBonusdatatime(date);// 时间
							newBonus.setBonusteam(user.getDuty());// 班组
							bonusServer.saveBonus(newBonus);
							// 添加奖金分配总额表
							bonusmoney.setBonusmoneyteam(user.getDuty());// 班组
							bonusmoney.setBonusmoneymonth(todayDate);// 月份
							bonusmoney.setBonusmoneytotalmoney(0F);// 总金额
							bonusmoney.setBonusmoneystatus("审核中");// 状态
							bonusmoney.setBonusmoneydatatime(date);// 时间
							bonusmoney.setBonusmoneyname(user.getName());// 添加的用户名
							bonusmoney.setAddUserId(user.getId());// 添加人id
							bonusServer.saveBonusmoney(bonusmoney);
							pageStatus = "ptyh";
						} else {
							bonusServer.saveBonusmoney((Bonusmoney) listmoney
									.get(0));
						}
					} else {
						errorMessage = "请先选择需要分配奖金的人";
						return "error";
					}
				} else {
					newbonus.setBonusdata(todayDate);
					pageStatus = "ptyh";
				}
			} else {
				errorMessage = "提奖金额尚未算出,或者尚未审核请稍等";
				return "error";
			}
			// 根据添加的月份 的数值是5的时候发送邮件提醒加工经理审核
			List listdate = bonusServer.findDate(todayDate);
			Bonusmoney bonusmoney = (Bonusmoney) listdate.get(0);
			String aaa = bonusmoney.getBonusmoneystatus();
			if (aaa.equals("同意")) {
				errorMessage = "审批已同意，不能修改!";
				return "error";
			}

		} else {
			errorMessage = "选择时间不能为空";
			return "error";
		}
		pageStatus = "ptyh";
		return "saveBonusPeople";

	}

	private String dateyuefen;// 月份
	private List<Bonus> bonuslist = new ArrayList<Bonus>();
	private List<Bonusmoney> bonusmoneylist = new ArrayList<Bonusmoney>();

	// 根据月份显示 和班组显示相应的奖金分配信息
	public String findDateBonus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		List deptlulist = bonusServer.findDpetbuliuMoney(dateyuefen, user
				.getDuty());
		bonuslist = bonusServer.findDateDept(dateyuefen, user.getDuty());
		bonusmoney = bonusServer.findbonusmoney1(dateyuefen, user.getDuty());
		if (bonuslist != null && bonuslist.size() > 0 && deptlulist != null
				&& deptlulist.size() > 0) {
			bonus = (Bonus) deptlulist.get(0);
			return "findDateBonus";
		} else {
			errorMessage = "没有找到你的相关信息，请先选择班组成员";
			return "error";
		}
	}

	private Integer bonus_id;

	// 更新状态
	public void updateBus() {
		this.bonusServer.updateBus(bonus_id);
	}

	private Float chengyuangongzi;// 成员奖金
	private Float jbfctmoney;// 加班费及饭贴
	private String pageStatus;
	private Float deptYu;// 部留
	private Float zongjiangji;// 总金额
	private String gonghao;// 成员工号

	// 修改 奖金明细表和奖金总额表
	public String addBonus() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		List datelist = bonusServer.finddate(todayDate);// 根据奖金分配的时间查询出提奖的总金额
		List list = bonusServer.findbonusmoney(todayDate);// 根据时间查询出所对应的奖金总额表的总金额
		if (datelist != null && list != null && datelist.size() > 0
				&& list.size() > 0 && datelist.get(0) != null
				&& list.get(0) != null) {
			Float money = Float.parseFloat(datelist.get(0).toString());// 拿出提奖的总金额
			Float jiangjmoney = Float.parseFloat(list.get(0).toString());// 拿出奖金总额表的总金额
			if (jiangjmoney == null) {
				jiangjmoney = 0F;
			}
			// Float zje = zongjiangji + jiangjmoney;// 奖金的总额+根据月份所对应查出来的奖金总额
			if (deptYu < money) {// 判断部留的金额是否超过了提奖的总金额
				if (zongjiangji <= money) { // 判断奖金总额是否超过提奖总额
					List bonlist = bonusServer.fidnghdate(gonghao, todayDate,
							user.getDuty());// 根据工号和时间+班组查询奖金明细表是否存在
					if (bonlist != null && bonlist.size() > 0
							&& bonlist.get(0) != null) {
						// 修改 奖金明细
						bonus = (Bonus) bonlist.get(0);
						bonus.setBonusmembermoney(chengyuangongzi);// 成员奖金
						bonus.setBonusovertimemealmoney(jbfctmoney);// 加班费及饭贴
						bonusServer.updateBonus(bonus);

						// 修改部门 部留金额
						List buliulist = bonusServer.findDpetbuliuMoney(
								todayDate, bonus.getBonusteam());
						if (buliulist != null && buliulist.size() > 0) {
							bonus = (Bonus) buliulist.get(0);
							bonus.setBonusmembermoney(deptYu);// 部留
							bonusServer.updateBonus(bonus);
						}
						// 修改奖金总额表
						Bonusmoney bonusmoney = new Bonusmoney();
						List listobl = bonusServer.findBonusmoney(todayDate,
								bonus.getBonusteam());
						if (listobl != null && listobl.size() > 0) {
							bonusmoney = (Bonusmoney) listobl.get(0);
							bonusmoney.setBonusmoneytotalmoney(zongjiangji);// 总金额
							bonusServer.updateBonusmoney(bonusmoney);
							errorMessage = "success";
						}
					}
				} else {
					errorMessage = "成员的奖金过大或者加班费及饭贴,超过该月提奖总金额!请重新填写!";

				}
			} else {
				errorMessage = "部留的总金额过大,超过该月提奖总金额!请重新填写!";
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(errorMessage);
			response.getWriter().close();

		}
		return null;
	}

	// 修改部留的金额
	public String updateDeptlu() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		// 修改部门 部留金额
		List deptlulist = bonusServer.findDpetbuliuMoney(todayDate, user
				.getDuty());
		List listobl = bonusServer.findBonusmoney(todayDate, user.getDuty());
		if (deptlulist != null && deptlulist.size() > 0 && listobl != null
				&& listobl.size() > 0 && listobl.get(0) != null
				&& deptlulist.get(0) != null) {
			bonus = (Bonus) deptlulist.get(0);
			bonus.setBonusmembermoney(deptYu);// 部留
			bonusServer.updateBonus(bonus);
			// 修改奖金总额表
			Bonusmoney bonusmoney = new Bonusmoney();
			if (listobl != null && listobl.size() > 0) {
				bonusmoney = (Bonusmoney) listobl.get(0);
				bonusmoney.setBonusmoneytotalmoney(zongjiangji);// 总金额
				bonusServer.updateBonusmoney(bonusmoney);
				return "update";
			}
		}
		return null;
	}

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List<Object[]> summarylist;

	// 根据月份查询出奖金分配汇总
	public String summary() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (todayDate != null && todayDate.length() > 0) {
			List list = bonusServer.findzongjiaji(todayDate); // 判断选择的月份是否被总经理（财务）审核
			if (list != null && list.size() > 0) { // 如果审核通过
				summarylist = bonusServer.findSummary(todayDate, Integer
						.parseInt(cpage), pageSize);
				if (summarylist != null && summarylist.size() > 0
						&& summarylist.get(0) != null) {
					this.setUrl("BonusAction!summary.action?todayDate="
							+ todayDate);
					this.cpage = request.getParameter("cpage");
					if ("".equals(cpage) || null == cpage) {
						cpage = 1 + "";
					}

					int count = bonusServer.summaryCount(todayDate);
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					message = message;
					return "summary";
				} else {
					errorMessage = "没有找到你的相关信息";
					return "error";
				}
			} else {
				errorMessage = "你选择的月份尚未审核，请等待";
				return "error";
			}
		} else {
			errorMessage = "选择时间不能为空";
			return "error";
		}
	}

	private String message;

	// 根据月份 生成 EXCEL
	public String generateExcel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		// 生成xls表，然后下载
		try {
			// 获得存放EXCEL的路径并且打开文件
			String path = BonusAction.class.getResource("/").toString();
			path = path.substring(6, path.length() - 17);
			String excelName = user.getCode() + ""
					+ (int) (Math.random() * 10000 + 1) + ".xls";
			path = path.replace("%20", " ") + "/upload/sheet/" + excelName;
			message = excelName;// 获得path的路径截取sheet
			String root = path.substring(0, path.indexOf("/upload/sheet/"))
					+ "/upload/sheet/"; // 获得excel存放的路径只获得0到sheet
			File fileRoot = new File(root);// 创建文件流
			File files[] = fileRoot.listFiles();// 获得excel存放路径的全部文件并且存放到files数组中
			for (int i = 0; i < files.length; i++) {// 遍历files
				File file = files[i];// 拿出files里面的只赋值给file为下面做判断用
				String fileName = file.getName();// 拿出file的用户名赋值给fileName
				if (fileName.contains(user.getCode())) {// 拿出fileName的值做比较如果等于登入进入的用户名相同删除
					file.delete();// 删除
				}
			}
			File file = new File(path);// 创建文件流获得EXCEL的路径
			// 生成excel表
			WritableWorkbook book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("奖金分配汇总 ", 0);
			Label label = new Label(0, 0, "工号");// 0列0后面是行
			Label label2 = new Label(1, 0, "姓名");
			Label label3 = new Label(2, 0, "总金额");
			Label label4 = new Label(3, 0, "月份");
			sheet.addCell(label);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			List<Object[]> excellist = bonusServer.findexcelsummary(todayDate);
			if (excellist != null && excellist.size() > 0) {
				for (int i = 0; i < excellist.size(); i++) {
					String gonghao = (String) excellist.get(i)[0];
					String username = (String) excellist.get(i)[1];
					Float money = Float.parseFloat(excellist.get(i)[2]
							.toString());
					String date = (String) excellist.get(i)[3];
					Label label5 = new Label(0, i + 1, gonghao);
					Label label6 = new Label(1, i + 1, username);
					Label label7 = new Label(2, i + 1, money.toString());
					Label label8 = new Label(3, i + 1, date);
					sheet.addCell(label5);
					sheet.addCell(label6);
					sheet.addCell(label7);
					sheet.addCell(label8);
				}
			}
			// 写入数据并关闭文件
			book.write();// 写入数据
			book.close();// 关闭文件
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "generateExcel";
	}

	// 构造方法
	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}

	public BonusServer getBonusServer() {
		return bonusServer;
	}

	public void setBonusServer(BonusServer bonusServer) {
		this.bonusServer = bonusServer;
	}

	public List<Bonus> getList() {
		return list;
	}

	public void setList(List<Bonus> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public Bonusmoney getBonusmoney() {
		return bonusmoney;
	}

	public void setBonusmoney(Bonusmoney bonusmoney) {
		this.bonusmoney = bonusmoney;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public Float getDeptYu() {
		return deptYu;
	}

	public void setDeptYu(Float deptYu) {
		this.deptYu = deptYu;
	}

	public Float getZongjiangji() {
		return zongjiangji;
	}

	public void setZongjiangji(Float zongjiangji) {
		this.zongjiangji = zongjiangji;
	}

	public String[] getQuxuanName() {
		return quxuanName;
	}

	public void setQuxuanName(String[] quxuanName) {
		this.quxuanName = quxuanName;
	}

	public Bonus getNewbonus() {
		return newbonus;
	}

	public void setNewbonus(Bonus newbonus) {
		this.newbonus = newbonus;
	}

	public String getDateyuefen() {
		return dateyuefen;
	}

	public void setDateyuefen(String dateyuefen) {
		this.dateyuefen = dateyuefen;
	}

	public List<Bonus> getBonuslist() {
		return bonuslist;
	}

	public void setBonuslist(List<Bonus> bonuslist) {
		this.bonuslist = bonuslist;
	}

	public String getGonghao() {
		return gonghao;
	}

	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}

	public Float getChengyuangongzi() {
		return chengyuangongzi;
	}

	public void setChengyuangongzi(Float chengyuangongzi) {
		this.chengyuangongzi = chengyuangongzi;
	}

	public Float getJbfctmoney() {
		return jbfctmoney;
	}

	public void setJbfctmoney(Float jbfctmoney) {
		this.jbfctmoney = jbfctmoney;
	}

	public List<Object[]> getSummarylist() {
		return summarylist;
	}

	public void setSummarylist(List<Object[]> summarylist) {
		this.summarylist = summarylist;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ContractBonus getContractBonus() {
		return contractBonus;
	}

	public void setContractBonus(ContractBonus contractBonus) {
		this.contractBonus = contractBonus;
	}

	public List<WageStandard> getWsList() {
		return wsList;
	}

	public void setWsList(List<WageStandard> wsList) {
		this.wsList = wsList;
	}

	public ContractBonusServer getContractBonusServer() {
		return contractBonusServer;
	}

	public void setContractBonusServer(ContractBonusServer contractBonusServer) {
		this.contractBonusServer = contractBonusServer;
	}

	public AssessPersonnelServer getAssessPersonnelServer() {
		return assessPersonnelServer;
	}

	public void setAssessPersonnelServer(
			AssessPersonnelServer assessPersonnelServer) {
		this.assessPersonnelServer = assessPersonnelServer;
	}

	public List getGroupList() {
		return groupList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}

	public int getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	public List<Bonusmoney> getBonusmoneylist() {
		return bonusmoneylist;
	}

	public void setBonusmoneylist(List<Bonusmoney> bonusmoneylist) {
		this.bonusmoneylist = bonusmoneylist;
	}

	public Integer getBonus_id() {
		return bonus_id;
	}

	public void setBonus_id(Integer bonusId) {
		bonus_id = bonusId;
	}

}
