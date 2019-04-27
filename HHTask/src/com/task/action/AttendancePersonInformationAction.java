package com.task.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AtPersonInforServer;
import com.task.entity.AttendancePersonInformation;
import com.task.entity.Department;
import com.task.entity.Person;

@SuppressWarnings("serial")
public class AttendancePersonInformationAction extends ActionSupport{
	private AtPersonInforServer atPersonInforServer;// 导入接口
	private AttendancePersonInformation attendancePersonInformation;// 考勤人员信息实体对象
	private List<AttendancePersonInformation> listInfor;// 考勤人员信息列表
	private Person person;//卡人员信息对象
	private Department department;
	private List list;
	private Integer atInforId;
	private String classNo;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态
	private Integer id;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	//分页显示，条件查询
	@SuppressWarnings("unchecked")
	public String selectAllAtPersonInforPage(){
		this.pageSize = 15;
		this.setUrl("AttendancePersonInformationAction!selectAllAtPersonInforPage.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (attendancePersonInformation != null) {
			request.getSession()
					.setAttribute("attendancePersonInformation", attendancePersonInformation);
		} else {
			attendancePersonInformation = (AttendancePersonInformation)request.getSession().getAttribute("attendancePersonInformation");
		}
		//姓名，卡号
		if (person != null) {
			request.getSession()
					.setAttribute("person", person);
		} else {
			person = (Person)request.getSession().getAttribute("person");
		}
		//部门
		if (department != null) {
			request.getSession()
					.setAttribute("department", department);
		} else {
			department = (Department)request.getSession().getAttribute("department");
		}
		Object[] obj = atPersonInforServer.selectAllByAtPersonInforPage(attendancePersonInformation,person,department, Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List)obj[0];
		return "person_selectAllAtPersonInforPage";
	}
	//获得修改的考勤人员对象
	public String getKQPersonId(){
		Object[] obj=atPersonInforServer.getKQObj(id);
		attendancePersonInformation=(AttendancePersonInformation)obj[0];
		person=(Person)obj[1];
		department=(Department)obj[2];
		return "person_getKQPersonId";
	}
	//更新考勤人员信息
	public String updateKQPerson(){
		if(atPersonInforServer.updateKQPerson(id,attendancePersonInformation,person,department)){
			return "person_updateKQPerson";
		}
		return ERROR;
	}
	//根据Id删除个人信息
	public String deleteInfor(){
		if(id>0){
			atPersonInforServer.deleteAtPersonInfor(id);
		}else{
			errorMessage = "不存在您要删除的信息!请检查!";
		}
		return "person_deleteInfor";
	}
	//下拉部门
	public String findSTPDept(){
		String message = atPersonInforServer.findSTPDept(tag);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}
	
	//getter和setter方法
	public AtPersonInforServer getAtPersonInforServer() {
		return atPersonInforServer;
	}
	public void setAtPersonInforServer(AtPersonInforServer atPersonInforServer) {
		this.atPersonInforServer = atPersonInforServer;
	}
	public AttendancePersonInformation getAttendancePersonInformation() {
		return attendancePersonInformation;
	}
	public void setAttendancePersonInformation(
			AttendancePersonInformation attendancePersonInformation) {
		this.attendancePersonInformation = attendancePersonInformation;
	}
	public List<AttendancePersonInformation> getListInfor() {
		return listInfor;
	}
	public void setListInfor(List<AttendancePersonInformation> listInfor) {
		this.listInfor = listInfor;
	}
	public Integer getAtInforId() {
		return atInforId;
	}
	public void setAtInforId(Integer atInforId) {
		this.atInforId = atInforId;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
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
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
