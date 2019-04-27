package com.task.action.kq;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AttendanceServer;
import com.task.Server.kq.IDepartmentService;
import com.task.Server.kq.IPersonService;
import com.task.Server.kq.IRegisterService;
import com.task.Server.kq.ITakeFundService;
import com.task.entity.Collect;
import com.task.entity.Person;
import com.task.entity.Register;
import com.task.util.Util;

/**
 * @author 曾建森
 * @FileNam RegisterAction.java
 * @Date 2012-10-9
 */
public class RegisterAction extends ActionSupport implements RequestAware,
		ServletRequestAware, ServletResponseAware {
	private AttendanceServer attendanceServer;// Server接口实现层
	private String upTime;
	private String downTime;
	private Map request;
	private HttpServletRequest httpReq;
	private HttpServletResponse httpRes;
	private float copies;
	private File myFile;
	private String myFileFileName;
	private Register reg;
	private IRegisterService irs;
	private IDepartmentService ids;
	private IPersonService ips;
	private ITakeFundService itf;
	private int[] selected;
	private List list;
	private String errorMessage;
	private String successMessage;
	private List deptList;
	private Integer deptID;
	private String reId;
	private Integer personId;
	private String cardNo;
	private String personName;
	private String deptName;
	private boolean bol;
	private String people;
	private String mark;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String nowDate;

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	/**
	 * 上传Excel文件,解析文件后充值金额
	 * 
	 * @return
	 */
	public String upload() {
		if(upTime != null && downTime != null){
		String upTimeStr = upTime.substring(0, 1);
		String endTimeStr = downTime.substring(0, 1);
		if (upTimeStr.equals("0")) {
			upTime = upTime.substring(1);
		}
		if (endTimeStr.equals("0")) {
			downTime = downTime.substring(1);
		}
		String resorceFile = irs.fileUpload(myFile, myFileFileName);
		Object[] o = irs.readCardNoByExcel(resorceFile, copies,
				upTime, downTime);
		if(o != null && o.length > 0){
			list = (List) o[0];
			successMessage = (String) o[1];
			deptID=(Integer) o[2];
		}
		}
		return "upload_supplyFund_success";
	}

	// 查询所有登记信息(分页)
	public String index() {
		Map map = new HashMap();
		if(people != null && !people.equals("")){
			map.put("people", people);
		}
		if(mark != null && !mark.equals("")){
			map.put("mark", mark);
		}
		if (map.size() > 0 && (upTime != null && downTime != null)) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
			} else
				ActionContext.getContext().getSession().remove("condition");
		}
		Object[] object = irs.queryAllRegister(map,Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_index.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index_success";
	}

	/**
	 * 添加登记信息
	 * 
	 * @return
	 */
	public String add() {
		irs.add(reg);
		return "success_operate";
	}

	/**
	 * 删除登记信息
	 * 
	 * @return
	 */
	public String del() {
		int id = Integer.parseInt(reId);
		irs.delById(id);
		return "success_operate";
	}

	/**
	 * 初始化登记信息修改页面
	 * 
	 * @return
	 */
	public String initUpdate() {
		int id = Integer.parseInt(reId);
		reg = irs.getRegisterById(id);
		return "register_initupdate";
	}

	/**
	 * 更改登记信息
	 * 
	 * @return
	 */
	public String update() {
		Register upReg = irs.getRegisterById(reg.getId());
		upReg.setBadgenumber(reg.getBadgenumber());
		upReg.setCardNo(reg.getCardNo());
		irs.update(upReg);
		return "success_operate";
	}

	/**
	 * 初始化汇总页面的部门下拉列表
	 * 
	 * @return
	 */
	public String initCollect() {
		deptList = ids.queryAllDepartment();
		return "register_collect";
	}

	/**
	 * 根据选择部门下拉列表初始化人员下拉列表
	 * 
	 * @return
	 */
	public String initPerson() {
		if (deptID != 0) {
			List<Person> perList = ips.queryPersonByDeptId(deptID);
			try {
				Gson gson = new Gson();
				String json = gson.toJson(perList);
				httpRes.setCharacterEncoding("utf-8");
				httpRes.getWriter().print(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据条件查询数据汇总
	 * 
	 * @return
	 */
	public String collect() {
		Map map = new HashMap();
		map.put("startStr", upTime);
		map.put("endStr", downTime);
		if (deptID != null && deptID != 0) {
			map.put("deptId", deptID);
		}
		if (personId != null && personId != 0) {
			map.put("personId", personId);
		}
		if (map.size() > 0 && (upTime != null && downTime != null)) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
			} else
				ActionContext.getContext().getSession().remove("condition");
		}
		deptList = ids.queryAllDepartment();
		Object[] object = irs.queryCollectByCondition(map, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_collect.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "register_collect";
	}

	/**
	 * 初始化取款页面
	 * 
	 * @return
	 */
	public String initWithdrawMon() {
		Map map = new HashMap();
		if (cardNo != null && !cardNo.equals("")) {
			map.put("cardNo", cardNo);
		}
		if (personName != null && !personName.equals("")) {
			map.put("personName", personName);
		}
		if (deptID != null && deptID != 0) {
			map.put("deptID", deptID);
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
			} else
				ActionContext.getContext().getSession().remove("condition");
		}
		Object[] object = itf.queryTakeFundByCondition(map, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			deptList = ids.queryAllDepartment();
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_initWithdrawMon.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "takeFund_index";
	}
//	/**
//	 * 查询当天的充值记录
//	 * @return
//	 */
//	public String findDailySupply(){
//		if(attendanceServer.sendAttendanceThree("1")){
//			list=itf.findDailySupply();
//			nowDate=Util.getDateTime("yyyy-MM-dd");
//			return "findDailySupply";}
////		}else{
////			return ERROR;
////		}
//		
//	}
	/**
	 * 根据ID取款
	 * 
	 * @return
	 */
	public String withdrawMon() {
		int id = Integer.parseInt(reId);
		bol = itf.addTakeFund(id);
		if(bol){
			try {
				httpRes.setContentType("text/html;charset=utf-8"); 
				httpRes.setCharacterEncoding("utf-8");
				httpRes.getWriter().print("<script>alert('取款成功！');window.location='register_initWithdrawMon.action'</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				httpRes.setContentType("text/html;charset=utf-8"); 
				httpRes.setCharacterEncoding("utf-8");
				httpRes.getWriter().print("<script>alert('取款失败！');window.location='register_initWithdrawMon.action'</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 批量取款
	 * 
	 * @return
	 */
	public String batchWithdrawMon() {
		StringBuffer str = new StringBuffer();
		str.append("取款失败：");
		if(selected != null && selected.length > 0){
			for (int i = 0; i < selected.length; i++) {
				int id = (selected[i]);
				boolean bol = itf.addTakeFund(id);
				if(!bol){
					Person per = ips.getPersonById(id);
					str.append(per.getName());
				}
			}
		}
		try {
			httpRes.setContentType("text/html;charset=utf-8"); 
			httpRes.setCharacterEncoding("utf-8");
			if(str.length() == 5 ){
				str.delete(0, str.length());
				str.append("取款成功!");
			}
			httpRes.getWriter().print("<script>alert('" + str.toString() + "');window.location='register_initWithdrawMon.action'</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 导出excel
	 * 
	 * @return
	 */
	public String export() {
		Map map = new HashMap();
		map.put("startStr", upTime);
		map.put("endStr", downTime);
		if (deptID != null && deptID != 0) {
			map.put("deptId", deptID);
		}
		List list = irs.queryCollectByCondition(map);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response
					.setHeader("Content-disposition", "attachment; filename="
							+ new String("Book1".getBytes("GB2312"), "8859_1")
							+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("汇总数据", 0);
			ws.setColumnView(4, 20);
			ws.setColumnView(3, 10);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 12);

			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);

			jxl.write.Label label0 = new Label(0, 0, "汇总数据", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 5, 0);

			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "姓名", wc));
			ws.addCell(new jxl.write.Label(2, 1, "卡号", wc));
			ws.addCell(new jxl.write.Label(3, 1, "部门", wc));
			ws.addCell(new jxl.write.Label(4, 1, "月份", wc));
			ws.addCell(new jxl.write.Label(5, 1, "充值金额", wc));
			ws.addCell(new jxl.write.Label(6, 1, "消费金额", wc));
			ws.addCell(new jxl.write.Label(7, 1, "余额(元)", wc));
			ws.addCell(new jxl.write.Label(8, 1, "应退(元)", wc));
			for (int i = 0; i < list.size(); i++) {
				Collect c = (Collect) list.get(i);
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, c.getName(), wc));
				ws.addCell(new Label(2, i + 2, c.getCardNo(), wc));
				ws.addCell(new Label(3, i + 2, c.getDeptName(), wc));
				ws.addCell(new Label(4, i + 2, c.getTime(), wc));
				ws
						.addCell(new jxl.write.Number(5, i + 2, c
								.getSupplyFun(), wc));
				ws
						.addCell(new jxl.write.Number(6, i + 2, c
								.getCosumeFund(), wc));
				ws.addCell(new jxl.write.Number(7, i + 2, c.getBalance(), wc));
				ws.addCell(new jxl.write.Number(8, i + 2, c.getRefund(), wc));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 导出excel
	 * 
	 * @return
	 */
	public String deptExport() {
		Map map = new HashMap();
		map.put("startStr", upTime);
		map.put("endStr", downTime);
		if (deptID != null && deptID != 0) {
			map.put("deptId", deptID);
		}
		List list = irs.queryCollectByCondition(map);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response
					.setHeader("Content-disposition", "attachment; filename="
							+ new String("Book1".getBytes("GB2312"), "8859_1")
							+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("汇总数据", 0);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 20);

			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);

			jxl.write.Label label0 = new Label(0, 0, "汇总数据", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 3, 0);

			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "部门", wc));
			ws.addCell(new jxl.write.Label(2, 1, "月份", wc));
			ws.addCell(new jxl.write.Label(3, 1, "充值金额(份)", wc));
			ws.addCell(new jxl.write.Label(4, 1, "消费金额(份)", wc));
			ws.addCell(new jxl.write.Label(5, 1, "余额(份)", wc));
			ws.addCell(new jxl.write.Label(6, 1, "应退(元)", wc));
			
			int number=1;
			String deptName="";
			String time="";
			int supplyFun=0;
			int cosumeFun=0;
			int balance=0;
			int refund=0;
			String upDeptName="";
			String upTime="";
			for (int i = 0; i < list.size(); i++) {
				Collect c = (Collect) list.get(i);
				deptName=c.getDeptName();
				time=c.getTime();
				if(i==0){
					upDeptName=deptName;
					upTime=time;
				}
				if(upDeptName.equals(deptName)){
					supplyFun+=c.getSupplyFun();
					cosumeFun+=c.getCosumeFund();
					balance+=c.getBalance();
					refund+=c.getRefund();
				}
				if(!upDeptName.equals(deptName)){
					ws.addCell(new jxl.write.Number(0, number+1, number, wc));
					ws.addCell(new Label(1, number+1, upDeptName, wc));
					ws.addCell(new Label(2, number+1, upTime, wc));
					ws.addCell(new jxl.write.Number(3, number+1, supplyFun, wc));
					ws.addCell(new jxl.write.Number(4, number+1, cosumeFun, wc));
					ws.addCell(new jxl.write.Number(5, number+1, balance, wc));
					ws.addCell(new jxl.write.Number(6, number+1, refund, wc));
					number++;
					upDeptName=deptName;
					upTime=time;
					supplyFun=c.getSupplyFun();
					cosumeFun=c.getCosumeFund();
					balance=c.getBalance();
					refund=c.getRefund();
				}
				if(i==list.size()-1){
					ws.addCell(new jxl.write.Number(0, number+1, number, wc));
					ws.addCell(new Label(1, number+1, upDeptName, wc));
					ws.addCell(new Label(2, number+1, upTime, wc));
					ws.addCell(new jxl.write.Number(3, number+1, supplyFun, wc));
					ws.addCell(new jxl.write.Number(4, number+1, cosumeFun, wc));
					ws.addCell(new jxl.write.Number(5, number+1, balance, wc));
					ws.addCell(new jxl.write.Number(6, number+1, refund, wc));
				}
				
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public void setIrs(IRegisterService irs) {
		this.irs = irs;
	}

	public void setCopies(float copies) {
		this.copies = copies;
	}

	public void setServletRequest(HttpServletRequest httpReq) {
		this.httpReq = httpReq;
	}

	public void setServletResponse(HttpServletResponse httpRes) {
		this.httpRes = httpRes;
	}

	public void setIds(IDepartmentService ids) {
		this.ids = ids;
	}

	public void setIps(IPersonService ips) {
		this.ips = ips;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public void setItf(ITakeFundService itf) {
		this.itf = itf;
	}

	public void setSelected(int[] selected) {
		this.selected = selected;
	}

	public void setReg(Register reg) {
		this.reg = reg;
	}

	public Register getReg() {
		return reg;
	}

	public void setRequest(Map request) {
		this.request = request;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getUpTime() {
		return upTime;
	}

	public String getDownTime() {
		return downTime;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public AttendanceServer getAttendanceServer() {
		return attendanceServer;
	}

	public void setAttendanceServer(AttendanceServer attendanceServer) {
		this.attendanceServer = attendanceServer;
	}

}
