package com.task.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.MentionrecordServer;
import com.task.entity.Mentionrecord;
import com.task.entity.Tijiang;

/*
 * 
 * 提奖记录ACTION
 */
public class MentionrecordAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MentionrecordServer mentionrecordServer;
	private Mentionrecord mentionrecord;// 提奖记录表
	private String pageStatus;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private String errorMessage;
	private List list;
	
	
	

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

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

	public MentionrecordServer getMentionrecordServer() {
		return mentionrecordServer;
	}

	public void setMentionrecordServer(MentionrecordServer mentionrecordServer) {
		this.mentionrecordServer = mentionrecordServer;
	}

	// 查询出提奖记录表所有信息
	@SuppressWarnings("unchecked")
	private List mentionrecolist = new ArrayList();

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		mentionrecolist = mentionrecordServer.findMentionrecordAll(Integer
				.parseInt(cpage), pageSize);
		this.setUrl("MentionrecordAction.action?pageStatus=" + pageStatus);
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}

		int count = mentionrecordServer.countMentionrecord();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return super.execute();
	}
	
	// 审批查询
	public String findExamList() {
		Object[] obj = mentionrecordServer.findExamList(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("mentionrecordServer!findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamListOK";
	}

	// 条件查询出提奖记录信息
	public String conditiontFindAll() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (mentionrecord != null) {
			ActionContext.getContext().getSession().put("mentionrecord",
					mentionrecord);
		} else {
			mentionrecord = (Mentionrecord) ActionContext.getContext()
					.getSession().get("mentionrecord");
		}
		mentionrecolist = mentionrecordServer.conditiontMentionrecord(
				mentionrecord, Integer.parseInt(cpage), pageSize);
		this.setUrl("MentionrecordAction!conditiontFindAll.action?pageStatus="
				+ pageStatus);
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = mentionrecordServer.countAll(mentionrecord);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "conditiontFindAll";
	}

	private int id;

	// 总经理审核 提奖记录信息 修改状态为可提奖
	public String updateMentionrecord() {
		mentionrecord = mentionrecordServer.findByID(id);
		mentionrecord.setMentionstatus("可提奖");
		mentionrecordServer.updateMentionrecord(mentionrecord);
		pageStatus = "all";
		return "updateMentionrecord";
	}
	
	// 批量审批
	public String updateMentionrecord1() {
		try {
			if (mentionrecordServer.updateExamTemplate(detailSelect, tag)) {
				pageStatus = "all";
				return "updateMentionrecord";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	// 财务审核 提奖记录信息 修改状态为已提奖 (前)
	public String updateFind() {
		mentionrecord = mentionrecordServer.findByID(id);
		return "updateFind";
	}

	// 财务审核 提奖记录信息 修改状态为已提奖
	public String financialupdate() {
		Mentionrecord oldMentionrecord = mentionrecordServer.findByID(id);
		oldMentionrecord.setMentionactualMoney(mentionrecord
				.getMentionactualMoney());
		oldMentionrecord.setMentionstatus("已提奖");
		mentionrecordServer.updateMentionrecord(oldMentionrecord);
		mentionrecord = oldMentionrecord;
		pageStatus = "dept";
		return "financialupdate";
	}

	// 查询出提奖记录表的走势图 曲线图
	public String mentionrecordCurve() {
		try {
			StringBuilder pieStr = new StringBuilder();
			HttpServletResponse response = ServletActionContext.getResponse();// 解决FusionCharts
			// 乱码
			response.setContentType("text/html; charset='GBK'");// 解决FusionCharts
			// 乱码
			response.setCharacterEncoding("GBK");// 解决FusionCharts 乱码
			PrintWriter out = response.getWriter();
			pieStr
					.append("<chart caption=\"提奖总额走势图\" subcaption=\" \" baseFontSize=\"15\" xAxisName=\"月份\" yAxisName=\"金额\" yAxisMinValue=\"15000\" numberPrefix=\"￥\" showValues=\"0\" alternateHGridColor=\"FCB541\" alternateHGridAlpha=\"20\" divLineColor=\"FCB541\" divLineAlpha=\"50\" canvasBorderColor=\"666666\" baseFontColor=\"666666\" lineColor=\"FCB541\">");
			List viewcurvelist = mentionrecordServer.findViewcurve();
			if (viewcurvelist.size() > 0) {
				for (int i = 0; i < viewcurvelist.size(); i++) {
					mentionrecord = (Mentionrecord) viewcurvelist.get(i);
					pieStr.append("<set label=\""
							+ mentionrecord.getMentionMonth() + "\" value=\""
							+ mentionrecord.getMentionshallMoney() + "\" />");
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

	// 提奖总额 环比 走势图
	public String viewChain() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();// 解决FusionCharts
			// 乱码
			response.setContentType("text/html; charset='GBK'");// 解决FusionCharts
			// 乱码
			response.setCharacterEncoding("GBK");// 解决FusionCharts 乱码
			PrintWriter out = response.getWriter();
			StringBuilder pieStr = new StringBuilder();
			pieStr
					.append("<chart caption=\"提奖总额环比走势图\" subcaption=\" \" baseFontSize=\"15\" xAxisName=\"月份\" yAxisName=\"环比(%)\"" +
							" yAxisMinValue=\"15000\" numberPrefix=\"\" showValues=\"0\" alternateHGridColor=\"FCB541\" alternateHGridAlpha=\"20\"" +
							" divLineColor=\"FCB541\" divLineAlpha=\"50\" canvasBorderColor=\"666666\" baseFontColor=\"666666\" lineColor=\"FCB541\">");
			List viewcurvelist = mentionrecordServer.findViewcurve();
			if (viewcurvelist != null && viewcurvelist.size() > 0) {
				for (int i = viewcurvelist.size(); i >0; i--) {
					mentionrecord = (Mentionrecord) viewcurvelist.get(i-1);
					String yuefen = mentionrecord.getMentionMonth();
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
					List listme = mentionrecordServer.findDate(date2);
					if (listme.size() > 0) {
						Float money = mentionrecord.getMentionshallMoney();// 当前月份金额
						Mentionrecord me2 = (Mentionrecord) listme.get(0);
						Float lastMoney = me2.getMentionshallMoney();// 上个月金额
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

	private String yuefen1;
	private String yuefen2;

	// 月份的型别比例 饼图
	public String viewPiechatMonth() {
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
			List<Object[]> datelist = mentionrecordServer.mentionPrizelist(
					yuefen1, yuefen2);
			if (datelist.size() > 0) {
				for (int i = 0; i < datelist.size(); i++) {
					pieStr.append("<set label=\"" + datelist.get(i)[0]
							+ "\" value=\"" + datelist.get(i)[1] + "\"/>");
				}
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

	private List<Tijiang> typelist = new ArrayList<Tijiang>();

	// 查询出型别
	public String Type() {
		typelist = mentionrecordServer.typeList();
		return "Type";
	}

	private String type;

	// 根据型别查询所对的信息 走势图
	public String findType() {
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
			List<Object[]> typelistAll = mentionrecordServer.findType(type2);
			if (typelistAll.size() > 0) {
				for (int i = 0; i < typelistAll.size(); i++) {
					pieStr.append("<set label=\"" + typelistAll.get(i)[0]
							+ "\" value=\"" + typelistAll.get(i)[1] + "\" />");
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
	public Mentionrecord getMentionrecord() {
		return mentionrecord;
	}

	public void setMentionrecord(Mentionrecord mentionrecord) {
		this.mentionrecord = mentionrecord;
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

	public List getMentionrecolist() {
		return mentionrecolist;
	}

	public void setMentionrecolist(List mentionrecolist) {
		this.mentionrecolist = mentionrecolist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getYuefen1() {
		return yuefen1;
	}

	public void setYuefen1(String yuefen1) {
		this.yuefen1 = yuefen1;
	}

	public String getYuefen2() {
		return yuefen2;
	}

	public void setYuefen2(String yuefen2) {
		this.yuefen2 = yuefen2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Tijiang> getTypelist() {
		return typelist;
	}

	public void setTypelist(List<Tijiang> typelist) {
		this.typelist = typelist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
