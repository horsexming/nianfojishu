package com.task.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.BonusmoneyServer;
import com.task.Server.WageServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.Bonus;
import com.task.entity.Bonusmoney;
import com.task.entity.Users;

public class BonusmoneyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private BonusmoneyServer bonusmoneyServer;
	private WageServer wageServer;// 工资模板Server层
	private Integer[] ids;

	public BonusmoneyServer getBonusmoneyServer() {
		return bonusmoneyServer;
	}

	public void setBonusmoneyServer(BonusmoneyServer bonusmoneyServer) {
		this.bonusmoneyServer = bonusmoneyServer;
	}

	private Bonusmoney bonusmoney;// 奖金总额表
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List<Bonusmoney> list = new ArrayList<Bonusmoney>();
	private String errorMessage;
	private String pageStatus;
	private String dept;
	private int countsize;

	// 批产奖金申请转入工资
	public String lpToFp() {
		bonusmoneyServer.lpToWage(ids);
		errorMessage="申请完成!";
		return ERROR;
	}

	// 根据登入人的部门查询出奖金总额的所有信息
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		if (pageStatus.equals("ptyh")) {
			list = bonusmoneyServer.finddept(user.getDuty(), Integer
					.parseInt(cpage), pageSize);
			dept = user.getDept();
			if (list.size() <= 0) {
				errorMessage = "没有找到你的相关信息";

				return "error";
			}
			this.setUrl("BonusmoneyAction.action?pageStatus=" + pageStatus);
			this.cpage = request.getParameter("cpage");
			if ("".equals(cpage) || null == cpage) {
				cpage = 1 + "";
			}

			int count = bonusmoneyServer.getcount(user.getDuty());
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			return "execute";
		} else {
			list = bonusmoneyServer.findAll(Integer.parseInt(cpage), pageSize);
			countsize = list.size();
			if (list.size() <= 0) {
				errorMessage = "没有找到你的相关信息";
				return "error";
			}
			this.setUrl("BonusmoneyAction.action?pageStatus=" + pageStatus);
			this.cpage = request.getParameter("cpage");
			if ("".equals(cpage) || null == cpage) {
				cpage = 1 + "";
			}

			int count = bonusmoneyServer.bonusmoneyCount();
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			return "execute";
		}

	}

	private String yuefen;
	private List<Bonus> bonuslist = new ArrayList<Bonus>();

	// 根据月份查询出奖金明细表信息
	public String finddate() throws UnsupportedEncodingException {
		banzu = java.net.URLDecoder.decode(banzu, "utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		bonuslist = bonusmoneyServer.findBonusAll(yuefen, banzu, Integer
				.parseInt(cpage), pageSize);
		if (bonuslist.size() <= 0) {
			errorMessage = "没有找到你的相关信息";
			return "error";
		}
		int count = bonusmoneyServer.countBonus(yuefen, banzu);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		String test = java.net.URLEncoder.encode(banzu, "utf-8");
		banzu = java.net.URLEncoder.encode(test, "utf-8");
		this.setUrl("BonusmoneyAction!finddate.action?yuefen=" + yuefen
				+ "&banzu=" + banzu + "&pageStatus=" + pageStatus);
		return "finddate";
	}

	// 条件查询
	public String conditionFindAll() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		if (bonusmoney != null) {
			ActionContext.getContext().getSession().put("bonusmoney",
					bonusmoney);
		} else {
			Bonusmoney bonusmoney = (Bonusmoney) ActionContext.getContext()
					.getSession().get("bonusmoney");
		}
		list = bonusmoneyServer.conditionFindAll(bonusmoney, Integer
				.parseInt(cpage), pageSize);
		if (list.size() <= 0) {
			errorMessage = "没有找到相关信息";
			return "error";
		}
		if (pageStatus.equals("ptyh")) {
			dept = user.getDuty();
		}
		this.setUrl("BonusmoneyAction!conditionFindAll.action?pageStatus="
				+ pageStatus);
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = bonusmoneyServer.countbonusmoney(bonusmoney);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "execute";
	}

	private int id;

	// 审核 同意
	public String audit() {
		// 审核判断
		String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		int r = Integer.parseInt(data.substring(8, 10));// 日
		int s = Integer.parseInt(data.substring(11, 13));// 时
		/*
		 * if (r < 11 || r > 13) { errorMessage = "对不起，你已经超过了审核时间或者审核时间没到";
		 * return ERROR; } else if (r == 11) { if (s < 8) { errorMessage =
		 * "对不起，你已经超过了审核时间或者审核时间没到"; return ERROR; } } else if (r == 13) { if (s
		 * > 14) { errorMessage = "对不起，你已经超过了审核时间或者审核时间没到"; return ERROR; } }
		 */
		bonusmoney = bonusmoneyServer.findByID(id);
		if (pageStatus.equals("jgjl")) {
			bonusmoney.setBonusmoneystatus("加工经理同意");
			bonusmoneyServer.update(bonusmoney);
			pageStatus = "jgjl";
			// 根据月份和状态为加工经理同意 SIZE等于5的时候发送邮件给生产副总
			List jiagongjllist = bonusmoneyServer.findMoney(bonusmoney
					.getBonusmoneymonth());
			if (jiagongjllist != null && jiagongjllist.size() > 0) {
				if (jiagongjllist.size() == 5) {
					AlertMessagesServerImpl.addAlertMessages(
							"查看奖金分配总金额（生产副总） ", bonusmoney.getBonusmoneymonth()
									+ "月份的奖金分配信息请您审核! ", "1");
				}
			}
			return "audit";
		} else if (pageStatus.equals("scfz")) {
			bonusmoney.setBonusmoneystatus("生产副总同意");
			bonusmoneyServer.update(bonusmoney);
			pageStatus = "scfz";
			// 根据月份和状态为加工经理同意 SIZE等于5的时候发送邮件给总经理
			List shengclist = bonusmoneyServer.findDate(bonusmoney
					.getBonusmoneymonth());
			if (shengclist != null && shengclist.size() > 0) {
				if (shengclist.size() == 5) {
					AlertMessagesServerImpl.addAlertMessages(
							"查看奖金分配总金额（总经理审核） ", bonusmoney
									.getBonusmoneymonth()
									+ "月份的奖金分配信息请您审核! ", "1");
				}
			}
			return "audit";
		} else if (pageStatus.equals("all")) {
			bonusmoney.setBonusmoneystatus("总经理同意");
			boolean bool = bonusmoneyServer.update(bonusmoney);
			pageStatus = "all";
			// 处理成功 则将奖金分配信息生成工资
			if (bool) {
				errorMessage = wageServer
						.wageBalance(bonusmoney.getAddUserId());
			}
			return "audit";
		}
		return null;
	}

	// 审核 打回
	public String auditDisagree() {
		// 审核判断
		String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		int r = Integer.parseInt(data.substring(8, 10));// 日
		int s = Integer.parseInt(data.substring(11, 13));// 时
		/*
		 * if (r < 11 || r > 13) { errorMessage = "对不起，你已经超过了审核时间或者审核时间没到";
		 * return ERROR; } else if (r == 11) { if (s < 8) { errorMessage =
		 * "对不起，你已经超过了审核时间或者审核时间没到"; return ERROR; } } else if (r == 13) { if (s
		 * > 14) { errorMessage = "对不起，你已经超过了审核时间或者审核时间没到"; return ERROR; } }
		 */
		bonusmoney = bonusmoneyServer.findByID(id);
		if (pageStatus.equals("jgjl")) {
			bonusmoney.setBonusmoneystatus("加工经理打回");
			bonusmoneyServer.update(bonusmoney);
			pageStatus = "jgjl";
			return "auditDisagree";
		} else if (pageStatus.equals("scfz")) {
			bonusmoney.setBonusmoneystatus("生产副总打回");
			bonusmoneyServer.update(bonusmoney);
			pageStatus = "scfz";
			return "auditDisagree";
		} else if (pageStatus.equals("all")) {
			bonusmoney.setBonusmoneystatus("总经理打回");
			bonusmoneyServer.update(bonusmoney);
			pageStatus = "all";
			return "auditDisagree";
		}
		return null;
	}

	private List<Bonus> listbonusmoney = new ArrayList<Bonus>();

	// 打回 过后修改 前
	public String auditDisagreeupdate() {
		listbonusmoney = bonusmoneyServer.print(yuefen, banzu);
		return "auditDisagreeupdate";
	}

	private String[] deptbanzu;// 班组
	private String[] chengyuanname; // 成员姓名
	private String[] chengyuangongnumer;// 成员工号
	private String[] chengyuankanumber; // 成员卡号
	private Float[] chengyuanjiangji;// 成员奖金
	private Float[] chengyuanjbfft; // 成员加班费及饭贴
	private String[] date;
	private Float zongjiangji;// 成员总金额
	private Float deptYu;// 部留

	// 打回 过后修改
	public String updateauditDisagree() {
		Bonus bonus = new Bonus();
		List tijianglist = bonusmoneyServer.findtijiang(date[0]);// 根据月份查询出提奖记录总额表总额
		List zongmoneylist = bonusmoneyServer.findmoney(date[0], deptbanzu[0]);// 根据月份查询出奖金总额表总额
		if (tijianglist != null && zongmoneylist != null
				&& tijianglist.size() > 0 && zongmoneylist.size() > 0
				&& tijianglist.get(0) != null && zongmoneylist.get(0) != null) {
			Float z = Float.parseFloat(zongmoneylist.get(0).toString());// 根据月份查询出奖金总额表总额
			Float z2 = Float.parseFloat(tijianglist.get(0).toString());// 根据月份查询出提奖记录总额表总额
			if (z == null) {
				z = 0F;
			}
			Float zongmoney = zongjiangji + deptYu; // 成员的奖金总金额+饭贴+部留+其他部门的一样月份的奖金总金额
			if (deptYu < z2) {
				if (zongmoney <= z2) {// 成员的奖金总金额+饭贴+部留+其他部门的一样月份的奖金总金额 和提奖总额对比
					for (int i = 0; i < deptbanzu.length; i++) {
						List list = bonusmoneyServer.findghkh(
								chengyuangongnumer[i], chengyuankanumber[i],
								date[i], deptbanzu[i]);
						if (list.size() > 0 && list != null
								&& list.get(0) != null) {
							bonus = (Bonus) list.get(0);
							bonus.setBonusmembermoney(chengyuanjiangji[i]);// 成员奖金
							bonus.setBonusovertimemealmoney(chengyuanjbfft[i]);// 加班费及饭贴
							// 修改奖金明细表
							bonusmoneyServer.updatebonus(bonus);
						}
					}
					// 修改部门 部留金额
					List buliulist = bonusmoneyServer.findddeptlu(date[0],
							deptbanzu[0]);
					if (buliulist != null && buliulist.size() > 0
							&& buliulist.get(0) != null) {
						bonus = (Bonus) buliulist.get(0);
						bonus.setBonusmembermoney(deptYu);// 部留
						bonusmoneyServer.updatebonus(bonus);
					}
					// 修改奖金总额表
					Bonusmoney bonusmoney = new Bonusmoney();
					List listobl = bonusmoneyServer.finddatedept(date[0],
							deptbanzu[0]);
					if (listobl != null && listobl.size() > 0
							&& listobl.get(0) != null) {
						bonusmoney = (Bonusmoney) listobl.get(0);
						bonusmoney
								.setBonusmoneytotalmoney(zongjiangji + deptYu);// 总金额
						bonusmoney.setBonusmoneystatus("审核中");
						bonusmoneyServer.update(bonusmoney);
						return "updateauditDisagree";
					}
				} else {
					errorMessage = "成员的奖金总金额过大,超过该月提奖总金额!请重新填写!";
					return "error";
				}
			} else {
				errorMessage = "部留的总金额过大,超过该月提奖总金额!请重新填写!";
				return "error";
			}
		} else {
			errorMessage = "提奖金额尚未提奖,或者尚未审核请稍等";
			return "error";
		}
		return null;
	}

	private List<Bonus> printlist = new ArrayList<Bonus>();
	private String banzu;
	private String tiaonumber;
	private String tiaoma;
	private String biannumber;

	// 根据月份查询出奖金分配明细 打印预览
	public String printPreview() throws UnsupportedEncodingException {
		if (banzu.equals("制消班1")) {
			tiaoma = "MA1";
		} else if (banzu.equals("制消班2")) {
			tiaoma = "MA2";
		} else if (banzu.equals("排气管班")) {
			tiaoma = "EP";
		} else if (banzu.equals("组件班")) {
			tiaoma = "CA";
		} else if (banzu.equals("零件班")) {
			tiaoma = "HP";
		}
		printlist = bonusmoneyServer.print(yuefen, banzu);
		tiaonumber = tiaoma + yuefen; // 条码
		String str = "shHD-SP";
		biannumber = str + yuefen; // 编号
		return "printPreview";
	}

	private Float jiangjinMoney;
	private Float tijiangMoney;

	// 查看 生成总额 和奖金分配总额
	public String findzongMoney() {
		List bonusmlist = bonusmoneyServer.findDateMoney(yuefen); // 根据月份查询出奖金分配总额
		List mentionrecordlist = bonusmoneyServer.findtijiang(yuefen); // 根据月份查询出提奖记录表总额
		jiangjinMoney = Float.parseFloat(bonusmlist.get(0).toString());
		tijiangMoney = Float.parseFloat(mentionrecordlist.get(0).toString());
		return "findzongMoney";
	}

	private String yuefen2;

	// 选择月份 显示个班组的比例 饼图
	public String findDate() {
		try {
			StringBuilder pieStr = new StringBuilder();
			HttpServletResponse response = ServletActionContext.getResponse();// 解决FusionCharts
			// 乱码
			response.setContentType("text/html; charset='GBK'");// 解决FusionCharts
			// 乱码
			response.setCharacterEncoding("GBK");// 解决FusionCharts 乱码
			PrintWriter out = response.getWriter();
			pieStr
					.append("<chart palette=\"4\" decimals=\"0\" baseFontSize=\"15\" enableSmartLabels=\"1\" enableRotation=\"0\" bgColor=\"99CCFF,FFFFFF\" bgAlpha=\"40,100\" bgRatio=\"0,100\" bgAngle=\"360\" showBorder=\"1\" startingAngle=\"70\">");
			List<Object[]> datelist = bonusmoneyServer
					.findDate(yuefen, yuefen2);
			if (datelist.size() > 0) {
				for (int i = 0; i < datelist.size(); i++) {
					pieStr.append("<set label=\"" + datelist.get(i)[0]
							+ "\" value=\"" + datelist.get(i)[1] + "\"/>");
				}
			} else {
				pieStr.append("<set label=\"0\" value=\"0\"/>");
			}
			pieStr.append("</chart>");
			out.print(pieStr.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Bonusmoney> deptlist = new ArrayList<Bonusmoney>();

	// 查询出 班组
	public String findTeam() {
		deptlist = bonusmoneyServer.findDept();
		return "findTeam";
	}

	private String type;

	// 根据班组查询出所对的信息 走势图
	public String findTeamMoney() {
		try {
			StringBuilder pieStr = new StringBuilder();
			HttpServletResponse response = ServletActionContext.getResponse();// 解决FusionCharts
			HttpServletRequest request = ServletActionContext.getRequest();
			String type2 = java.net.URLDecoder.decode(type, "utf-8");
			// 乱码
			response.setContentType("text/html; charset='GBK'");// 解决FusionCharts
			// 乱码
			response.setCharacterEncoding("GBK");// 解决FusionCharts 乱码
			PrintWriter out = response.getWriter();
			pieStr
					.append("<chart caption=\""
							+ type2
							+ "走势图\" subcaption=\" \" baseFontSize=\"15\" xAxisName=\"月份\" yAxisName=\"金额\" yAxisMinValue=\"15000\" numberPrefix=\"￥\" showValues=\"0\" alternateHGridColor=\"FCB541\" alternateHGridAlpha=\"20\" divLineColor=\"FCB541\" divLineAlpha=\"50\" canvasBorderColor=\"666666\" baseFontColor=\"666666\" lineColor=\"FCB541\">");
			List<Object[]> viewcurvelist = bonusmoneyServer.findTeam(type2);
			if (viewcurvelist.size() > 0) {
				for (int i = 0; i < viewcurvelist.size(); i++) {
					pieStr
							.append("<set label=\"" + viewcurvelist.get(i)[0]
									+ "\" value=\"" + viewcurvelist.get(i)[1]
									+ "\" />");
				}
			} else {
				pieStr.append("<set label=\"0\" value=\"0\"/>");
			}
			pieStr.append("<styles>");
			pieStr.append("<definition>");
			pieStr
					.append("<style name=\"Anim1\" type=\"animation\" param=\"_xscale\" start=\"0\" duration=\"1\" />");
			pieStr
					.append("<style name=\"Anim2\" type=\"animation\" param=\"_alpha\" start=\"0\" duration=\"0.6\"/>");
			pieStr
					.append("<style name=\"DataShadow\" type=\"Shadow\" alpha=\"40\" />");
			pieStr.append("</definition>");
			pieStr.append("<application>");
			pieStr.append("<apply toObject=\"DIVLINES\" styles=\"Anim1\" />");
			pieStr.append("<apply toObject=\"HGRID\" styles=\"Anim2\" />");
			pieStr
					.append("<apply toObject=\"DATALABELS\" styles=\"DataShadow,Anim2\" />");
			pieStr.append("</application>");
			pieStr.append("</styles>");
			pieStr.append("</chart>");
			out.print(pieStr.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Bonusmoney> huanbilist = new ArrayList<Bonusmoney>();

	// 查询出班组 用于显示班组环比走势图前
	public String findDepthuanbi() {
		huanbilist = bonusmoneyServer.findDepthbi();
		return "findDepthuanbi";
	}

	// 根据班组算出环比走势图
	public String findDepthuanbiview() {
		try {
			StringBuilder pieStr = new StringBuilder();
			HttpServletResponse response = ServletActionContext.getResponse();// 解决FusionCharts
			HttpServletRequest request = ServletActionContext.getRequest();
			String type2 = java.net.URLDecoder.decode(type, "utf-8");
			// 乱码
			response.setContentType("text/html; charset='GBK'");// 解决FusionCharts
			// 乱码
			response.setCharacterEncoding("GBK");// 解决FusionCharts 乱码
			PrintWriter out = response.getWriter();
			pieStr
					.append("<chart caption=\""
							+ type2
							+ "环比走势图\" subcaption=\" \" baseFontSize=\"15\" xAxisName=\"月份\" yAxisName=\"环比(%)\""
							+ " yAxisMinValue=\"15000\" numberPrefix=\"\" showValues=\"0\" alternateHGridColor=\"FCB541\" alternateHGridAlpha=\"20\""
							+ " divLineColor=\"FCB541\" divLineAlpha=\"50\" canvasBorderColor=\"666666\" baseFontColor=\"666666\" lineColor=\"FCB541\">");
			List<Object[]> viewcurvelist = bonusmoneyServer.findTeam(type2);
			if (viewcurvelist.size() > 0) {
				for (int i = 0; i < viewcurvelist.size(); i++) {
					String yuefen = (String) viewcurvelist.get(i)[0];
					int n = Integer.parseInt(yuefen.substring(0, 4));// 年
					int y = Integer.parseInt(yuefen.substring(5, 7));// 月
					if (y == 1) {
						y = 12;
						n--;
					} else {
						y--;
					}
					String date2 = n + "-" + y;
					if (y < 10) {
						date2 = yuefen.substring(0, 5) + "0" + y;
					}
					List listme = bonusmoneyServer.findDateMoney(date2, type2);
					if (listme != null && listme.size() > 0
							&& listme.get(0) != null) {
						Float money = (Float) viewcurvelist.get(i)[1];// 当前月份金额
						Float lastMoney = Float.valueOf(listme.get(0)
								.toString());// 上个月金额
						Float zongmoney = (money - lastMoney) / lastMoney * 100;// 环比
						pieStr.append("<set label=\"" + yuefen + "\" value=\""
								+ zongmoney + "\" />");
					} else {
						pieStr.append("<set label=\"" + yuefen
								+ "\" value=\"0\" />");
					}
				}
			}
			pieStr.append("<styles>");
			pieStr.append("<definition>");
			pieStr
					.append("<style name=\"Anim1\" type=\"animation\" param=\"_xscale\" start=\"0\" duration=\"1\" />");
			pieStr
					.append("<style name=\"Anim2\" type=\"animation\" param=\"_alpha\" start=\"0\" duration=\"0.6\"/>");
			pieStr
					.append("<style name=\"DataShadow\" type=\"Shadow\" alpha=\"40\" />");
			pieStr.append("</definition>");
			pieStr.append("<application>");
			pieStr.append("<apply toObject=\"DIVLINES\" styles=\"Anim1\" />");
			pieStr.append("<apply toObject=\"HGRID\" styles=\"Anim2\" />");
			pieStr
					.append("<apply toObject=\"DATALABELS\" styles=\"DataShadow,Anim2\" />");
			pieStr.append("</application>");
			pieStr.append("</styles>");
			pieStr.append("</chart>");
			out.print(pieStr.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 构造方法
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Bonusmoney getBonusmoney() {
		return bonusmoney;
	}

	public void setBonusmoney(Bonusmoney bonusmoney) {
		this.bonusmoney = bonusmoney;
	}

	public List<Bonusmoney> getList() {
		return list;
	}

	public void setList(List<Bonusmoney> list) {
		this.list = list;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getYuefen() {
		return yuefen;
	}

	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}

	public List<Bonus> getBonuslist() {
		return bonuslist;
	}

	public void setBonuslist(List<Bonus> bonuslist) {
		this.bonuslist = bonuslist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<Bonus> getPrintlist() {
		return printlist;
	}

	public void setPrintlist(List<Bonus> printlist) {
		this.printlist = printlist;
	}

	public String getBanzu() {
		return banzu;
	}

	public void setBanzu(String banzu) {
		this.banzu = banzu;
	}

	public String getTiaonumber() {
		return tiaonumber;
	}

	public void setTiaonumber(String tiaonumber) {
		this.tiaonumber = tiaonumber;
	}

	public String getTiaoma() {
		return tiaoma;
	}

	public void setTiaoma(String tiaoma) {
		this.tiaoma = tiaoma;
	}

	public String getBiannumber() {
		return biannumber;
	}

	public void setBiannumber(String biannumber) {
		this.biannumber = biannumber;
	}

	public List<Bonus> getListbonusmoney() {
		return listbonusmoney;
	}

	public void setListbonusmoney(List<Bonus> listbonusmoney) {
		this.listbonusmoney = listbonusmoney;
	}

	public String[] getDeptbanzu() {
		return deptbanzu;
	}

	public void setDeptbanzu(String[] deptbanzu) {
		this.deptbanzu = deptbanzu;
	}

	public String[] getChengyuanname() {
		return chengyuanname;
	}

	public void setChengyuanname(String[] chengyuanname) {
		this.chengyuanname = chengyuanname;
	}

	public String[] getChengyuangongnumer() {
		return chengyuangongnumer;
	}

	public void setChengyuangongnumer(String[] chengyuangongnumer) {
		this.chengyuangongnumer = chengyuangongnumer;
	}

	public String[] getChengyuankanumber() {
		return chengyuankanumber;
	}

	public void setChengyuankanumber(String[] chengyuankanumber) {
		this.chengyuankanumber = chengyuankanumber;
	}

	public Float[] getChengyuanjiangji() {
		return chengyuanjiangji;
	}

	public void setChengyuanjiangji(Float[] chengyuanjiangji) {
		this.chengyuanjiangji = chengyuanjiangji;
	}

	public Float[] getChengyuanjbfft() {
		return chengyuanjbfft;
	}

	public void setChengyuanjbfft(Float[] chengyuanjbfft) {
		this.chengyuanjbfft = chengyuanjbfft;
	}

	public String[] getDate() {
		return date;
	}

	public void setDate(String[] date) {
		this.date = date;
	}

	public Float getZongjiangji() {
		return zongjiangji;
	}

	public void setZongjiangji(Float zongjiangji) {
		this.zongjiangji = zongjiangji;
	}

	public Float getDeptYu() {
		return deptYu;
	}

	public void setDeptYu(Float deptYu) {
		this.deptYu = deptYu;
	}

	public String getYuefen2() {
		return yuefen2;
	}

	public void setYuefen2(String yuefen2) {
		this.yuefen2 = yuefen2;
	}

	public List<Bonusmoney> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(List<Bonusmoney> deptlist) {
		this.deptlist = deptlist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Bonusmoney> getHuanbilist() {
		return huanbilist;
	}

	public void setHuanbilist(List<Bonusmoney> huanbilist) {
		this.huanbilist = huanbilist;
	}

	public Float getJiangjinMoney() {
		return jiangjinMoney;
	}

	public void setJiangjinMoney(Float jiangjinMoney) {
		this.jiangjinMoney = jiangjinMoney;
	}

	public Float getTijiangMoney() {
		return tijiangMoney;
	}

	public void setTijiangMoney(Float tijiangMoney) {
		this.tijiangMoney = tijiangMoney;
	}

	public int getCountsize() {
		return countsize;
	}

	public void setCountsize(int countsize) {
		this.countsize = countsize;
	}

	public WageServer getWageServer() {
		return wageServer;
	}

	public void setWageServer(WageServer wageServer) {
		this.wageServer = wageServer;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

}
