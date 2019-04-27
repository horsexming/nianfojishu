package com.task.action.fin;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.fin.EscrowServer;
import com.task.entity.fin.Escrow;
import com.task.entity.fin.EscrowMonth;
import com.task.util.MKUtil;
import com.task.util.Util;

/**
 * @author txb
 *
 */
public class EscrowAction extends ActionSupport{
	private List list;
	private String tag;
	private String message;
	private Escrow escrow;
	private Double heji;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private EscrowMonth escrowMonth;
	private String month;
	
	private EscrowServer escrowServer;
	private String pagestatus;//
	private String errorMessage;
	private String bwtCompany;
	private String spTime;//
	private int [] ids;
	private List<String> qianmingList;
	private List<String> zhiliaoList;
	
	public String selectEscrowPayCom(){
		String message = escrowServer.findEscrowType();
		MKUtil.writeJSON(message);
//		try {
//			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setCharacterEncoding("utf-8");
//			response.getWriter().write(message);
//			response.getWriter().close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "error";
	}
	
	public String showList(){
		if (escrow != null) {
			ActionContext.getContext().getSession().put("escrow", escrow);
		} else {
			escrow = (Escrow) ActionContext.getContext().getSession().get(
					"escrow");
		}
		Object[] obj = escrowServer.findFundApplyList(escrow, Integer
				.parseInt(cpage), pageSize, pagestatus);
		if (obj != null && obj.length > 0) {
			list = (List<Escrow>) obj[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) obj[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
					this.setUrl("EscrowAction_showList.action?pagestatus="+pagestatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		
		return "escrowList_show";
	}
	
	public String toPrint(){
		list = escrowServer.getPrintList(ids);
		bwtCompany="";
		heji = 0d;
		if(list !=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Escrow e = (Escrow)list.get(i);
				heji +=e.getYingfuJine();
				if(i==0){
					bwtCompany = e.getBwtCompany();
					 qianmingList = escrowServer.getqmList(e.getEpId());
					 zhiliaoList = escrowServer.getzbList();
					 spTime =e.getSpTime();
				}else if(!bwtCompany.equals(e.getBwtCompany())){
					list=null;
					break;
				}
			}
		}
		if(bwtCompany!=null&&!bwtCompany.equals("")){
			 return "escrowList_printrc";
		 }else{
			 return "escrowList_print";
		 }
	}
	
	public String print(){
		escrowServer.print(ids);
		return null;
	}
	/**
	 * 查询月度委托汇总
	 * @return
	 */
	public String escrowMonthShow(){
		if (escrowMonth != null) {
			ActionContext.getContext().getSession().put("escrowMonth", escrowMonth);
		} else {
			escrowMonth = (EscrowMonth) ActionContext.getContext().getSession().get(
					"escrowMonth");
		}
		Object[] obj = escrowServer.findEscrowMonthList(escrowMonth, Integer
				.parseInt(cpage), pageSize, pagestatus);
		if (obj != null && obj.length > 0) {
			list = (List<Escrow>) obj[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) obj[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
					this.setUrl("EscrowAction_escrowMonthShow.action?pagestatus="+pagestatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		
		return "escrowMonthList_show";
	
		
	}
	/**
	 * 前往添加月度委托汇总
	 * @return
	 */
	public String toApplyEscrowMonth(){
		Calendar cal = Calendar.getInstance();
		//默认获取上个月
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		month = Util.DateToString(cal.getTime(), "yyyy-MM");
		list = escrowServer.getPayComByMonth(month);
		return "escrowMonth_apply";
	}
	/**
	 * 获取某月的被委托方
	 */
	public void getPayComByMonth(){
		list = escrowServer.getPayComByMonth(month);
		MKUtil.writeJSON(list);
	}
	/**
	 * 添加月度委托汇总
	 * @return
	 */
	public String applyEscrowMonth(){
		String msg= escrowServer.applyEscrowMonth(escrowMonth);
		if(msg.equals("true")){
			errorMessage="申请成功";
			url = "EscrowAction_escrowMonthPrint.action?id="+escrowMonth.getId();
		}else{
			errorMessage = msg;
		}
		return "error";
	}
	/**
	 * 打印月度委托汇总
	 * @return
	 */
	public String escrowMonthPrint(){
		escrowMonth = escrowServer.getescrowMonthForPrint(id);
		if(escrowMonth!=null&&escrowMonth.getEscrowlist()!=null&&escrowMonth.getEscrowlist().size()>0){
			heji=0d;
			for(Escrow e:escrowMonth.getEscrowlist()){
				heji +=e.getYingfuJine();
			}
		}
		qianmingList = escrowServer.getqmList(escrowMonth.getEpId());
		bwtCompany = escrowMonth.getPayCom();
		spTime =escrowMonth.getSpTime();
		spTime = Util.DateToString(Util.StringToDate(spTime, "yyyy-MM-dd hh:mm:ss"), "yyyyMMddhhmmss");
		return "escrowMonth_print";
	}
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public EscrowServer getEscrowServer() {
		return escrowServer;
	}
	public void setEscrowServer(EscrowServer escrowServer) {
		this.escrowServer = escrowServer;
	}


	public Escrow getEscrow() {
		return escrow;
	}


	public void setEscrow(Escrow escrow) {
		this.escrow = escrow;
	}


	public String getPagestatus() {
		return pagestatus;
	}


	public void setPagestatus(String pagestatus) {
		this.pagestatus = pagestatus;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}




	public int[] getIds() {
		return ids;
	}




	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public String getBwtCompany() {
		return bwtCompany;
	}

	public void setBwtCompany(String bwtCompany) {
		this.bwtCompany = bwtCompany;
	}

	public List<String> getQianmingList() {
		return qianmingList;
	}

	public void setQianmingList(List<String> qianmingList) {
		this.qianmingList = qianmingList;
	}

	public String getSpTime() {
		return spTime;
	}

	public void setSpTime(String spTime) {
		this.spTime = spTime;
	}

	public Double getHeji() {
		return heji;
	}

	public void setHeji(Double heji) {
		this.heji = heji;
	}

	public EscrowMonth getEscrowMonth() {
		return escrowMonth;
	}

	public void setEscrowMonth(EscrowMonth escrowMonth) {
		this.escrowMonth = escrowMonth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<String> getZhiliaoList() {
		return zhiliaoList;
	}

	public void setZhiliaoList(List<String> zhiliaoList) {
		this.zhiliaoList = zhiliaoList;
	}
	
}
