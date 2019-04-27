package com.task.action;

import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AtPersonServer;
import com.task.entity.Department;
import com.task.entity.Person;

/**
 * @author 刘晓霆
 * @Date 2014-04-30
 * 
 */
@SuppressWarnings("serial")
public class PersonAction extends ActionSupport {
	private AtPersonServer atPersonServer;// Server层
	private Person person;// 员工信息实体对象
	private Department department;// 部门实体对象
	private List<Person> listPerson;// 列表集合
	private List<Department> listDept;// 部门列表
	private int Id;// Id
	private String name;// 姓名
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 按Id查询考勤人员信息
	@SuppressWarnings({ "unchecked" })
	public String selectPersonId() {
		Object[] object = atPersonServer.selectId(person.getId());
		if (object != null && object.length > 0) {
			person = (Person) object[0];
			department = (Department) object[1];
			listDept = (List<Department>) object[2];
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "selectOk";
	}

	// 分页显示考勤人员信息
	@SuppressWarnings("unchecked")
	public String selectPersonPage() {
		if (person != null) {
			ActionContext.getContext().getSession().put("person", person);
		} else {
			person = (Person) ActionContext.getContext().getSession()
					.get("person");
		}
		if (pageStatus != null && "findAll".equals(pageStatus)) {
			System.out.println("findAllOk!!!");
		}
		Object[] object = atPersonServer.selectAllByAtPersonPage(person,
				Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			listPerson = (List<Person>) object[0];
			if (listPerson != null && listPerson.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("PersonAction!selectPersonPage.action?pageStatus="
						+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		if (pageStatus != null && "findAll".equals(pageStatus)) {
			return "findAllOk";
		} else {
			return "successPage";
		}
	}

	// 分页显示考勤信息
	@SuppressWarnings("unchecked")
	public String selectPersonAndDeptPage() {
		if (person != null && department != null) {
			ActionContext.getContext().getSession().put("person", person);
			ActionContext.getContext().getSession()
					.put("department", department);
		} else {
			person = (Person) ActionContext.getContext().getSession()
					.get("person");
			department = (Department) ActionContext.getContext().getSession()
					.get("department");
		}
		if (pageStatus != null && "findAll".equals(pageStatus)) {
			return "";
		}
		Object[] object = atPersonServer.selectAllByAtPersonAndDeptPage(person,
				department, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			listPerson = (List<Person>) object[0];
			if (listPerson != null && listPerson.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("PersonAction!selectPersonAndDeptPage.action?pageStatus="
						+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		if (pageStatus != null && "findAll".equals(pageStatus)) {
			return "findAllOk";
		} else {
			return "successPage";
		}
	}

	// 修改考勤人员信息
	public String updatePerson() {
		// 修改之前，先根据Id查询，返回数组
		Object[] object = atPersonServer.selectId(person.getId());
		// 从数组中取出数据赋值给oldPerson
		Person oldPerson = (Person) object[0];
		if (object != null && object.length > 0) {
			atPersonServer.updatePerson(person, oldPerson);
		} else {
			errorMessage = "不存在您要修改的信息!请检查!";
		}
		return "updateOk";
	}

	/*
	 * *******************************************************************************************
	 */
	// 按Id差考勤人员信息
	public String personIdOne() {
		Object[] object = atPersonServer.selectOneId(person.getId());
		if (object != null && object.length > 0) {
			person = (Person) object[0];
			department = (Department) object[1];
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "selectOneOk";
	}

	// 删除考勤人员信息
	public String deletePerson() {
		int atId = person.getId();
		if (atId > 0) {
			atPersonServer.deletePerson(atId);
		} else {
			errorMessage = "不存在您要删除的信息!请检查!";
		}
		return "deleteOk";
	}

	// getter和setter方法
	public AtPersonServer getAtPersonServer() {
		return atPersonServer;
	}

	public void setAtPersonServer(AtPersonServer atPersonServer) {
		this.atPersonServer = atPersonServer;
	}

	public List<Person> getListPerson() {
		return listPerson;
	}

	public void setListPerson(List<Person> listPerson) {
		this.listPerson = listPerson;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Department> getListDept() {
		return listDept;
	}

	public void setListDept(List<Department> listDept) {
		this.listDept = listDept;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
