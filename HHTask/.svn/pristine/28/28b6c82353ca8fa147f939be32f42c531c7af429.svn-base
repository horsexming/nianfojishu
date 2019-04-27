package com.task.action;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.TeammembersServer;
import com.task.entity.Teammembers;
import com.task.entity.Users;

public class TeammembersAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TeammembersServer teammembersServer;

	public TeammembersServer getTeammembersServer() {
		return teammembersServer;
	}

	public void setTeammembersServer(TeammembersServer teammembersServer) {
		this.teammembersServer = teammembersServer;
	}

	private Teammembers teammembers; // 班组成员表
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String str;
	private String name;
	private String errorMessage;

	// 添加班组成员
	@Override
	public String execute() throws Exception {
		boolean bool = teammembersServer.saveTeammembers(teammembers);
		if (bool) {
			name = teammembers.getTeammembersmembernumber();
			str = "123";
			return "execute";
		} else
			errorMessage = "添加下属成员失败!请检查后重试!";

		// Teammembers oldTeammembers = teammembersServer
		// .findTeammembersBYAddUser(teammembers
		// .getTeammembersmembernumber(), null);
		// if (oldTeammembers == null) {
		// boolean bool = teammembersServer.saveTeammembers(teammembers);
		// if (bool) {
		// name = teammembers.getTeammembersmembernumber();
		// str = "123";
		// return "execute";
		// } else
		// errorMessage = "添加下属成员失败!请检查后重试!";
		// } else
		// errorMessage = "添加下属成员失败!该成员已经存在于成员组里!请检查!";
		return ERROR;

	}

	// 根据工号查询出卡号和姓名
	public String findgongnumber() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		String gongnumber = java.net.URLDecoder.decode(request
				.getParameter("gongnumber"), "utf-8");
		String gongnumber2 = java.net.URLDecoder.decode(gongnumber, "utf-8");
		List orderlist = teammembersServer.findmembernumber(gongnumber2);
		if (orderlist != null && orderlist.size() > 0) {
			Users users = (Users) orderlist.get(0);
			if (users != null) {
				String message = users.getName() + "|" + users.getCardId();
				response.getWriter().write(message);
			} else {
				response.getWriter().write("");
			}
			response.getWriter().close();
		}
		return null;
	}

	private List<Teammembers> list = new ArrayList<Teammembers>();

	// 根据登录的人的id查询出所对应班组成员的信息
	public String findDept() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		list = teammembersServer.findTeammembersBYAddUser(user.getId(), Integer
				.parseInt(cpage), pageSize);
		if (list.size() <= 0) {
			errorMessage = "没有找到你的相关信息，请先添加班组成员";
			return "error";
		}
		this.setUrl("TeammembersAction!findDept.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = teammembersServer.getcountBYAddUser(user.getId());
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "findDept";
	}

	private int id;

	// 删除班组成员
	public String delete() {
		teammembers = teammembersServer.findByID(id);
		teammembersServer.deleteTeammembers(teammembers);
		return "delete";
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

	public Teammembers getTeammembers() {
		return teammembers;
	}

	public void setTeammembers(Teammembers teammembers) {
		this.teammembers = teammembers;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Teammembers> getList() {
		return list;
	}

	public void setList(List<Teammembers> list) {
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
