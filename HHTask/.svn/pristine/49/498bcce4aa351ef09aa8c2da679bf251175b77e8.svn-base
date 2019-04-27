package com.task.action.iao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.iao.IAOServer;
import com.task.entity.iao.CarryGoods;
import com.task.entity.iao.IAOApply;

public class IAOAction extends ActionSupport {
	private IAOServer iaoServer;
	private IAOApply iaoApply;
	private List list;
	private List<CarryGoods> listCarryGoods;
	private String tag;
	private String crudTag;// 操作标识
	private String message;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间

	/** 添加出门申请单 **/
	public String saveIaoApp() {
		if (null != listCarryGoods) {
			iaoApply.setIaoCarryGoods(new HashSet(listCarryGoods));
		}
		if (iaoServer.saveIAO(iaoApply)) {
			return "saveIAOOK";
		}
		return ERROR;
	}

	/** 获取出门申请 **/
	public String getIaoAppById() {
		iaoApply = iaoServer.getIAOApplyById(id);
		listCarryGoods = new ArrayList(new HashSet(iaoApply.getIaoCarryGoods()));
		if ("update".equals(crudTag)) {
			return "showIAOApp";
		} else if ("delete".equals(crudTag)) {
			if (iaoServer.deleteIAO(iaoApply)) {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
				try {
					PrintWriter out = response.getWriter();
					out.print("<script>alert('删除成功！')</script>");
					out.print("<script>window.location.href='iaoAction!findIaoApp.action?tag=oneself'</script>");
					out.flush();
					out.close();
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				
			}else{
				return null;
			}			
		}else{
			return ERROR;
		}	
		
	}

	/** 修改出门申请 **/
	public String updateIaoApp() {
		if (null != iaoApply) {
			if (null != listCarryGoods) {
				iaoApply.setIaoCarryGoods(new HashSet(listCarryGoods));
			}
			if (iaoServer.updateIAOApp(iaoApply, "")) {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
				try {
					PrintWriter out = response.getWriter();
					out.print("<script>alert('修改成功！')</script>");
					out.print("<script>window.location.href='iaoAction!findIaoApp.action?tag=oneself'</script>");
					out.flush();
					out.close();
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return ERROR;
	}

	/** 批量审批 **/
	public String lotExamIaoApp() {
		return null;
	}

	/**
	 * 查询出门申请 tag(oneself 个人，exam：审批，manager：管理)
	 ***/
	public String findIaoApp() {
		this.pageSize = 15;
		this.setUrl("iaoAction!findIaoApp.action?tag=" + this.tag);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != iaoApply) {
			request.getSession().setAttribute("baoxiao", iaoApply);
		} else {
			iaoApply = (IAOApply) request.getSession().getAttribute("iaoApply");
		}
		if (null != startDate) {
			request.getSession().setAttribute("startDate", startDate);
		} else {
			startDate = (String) request.getSession().getAttribute("startDate");
		}
		if (null != endDate) {
			request.getSession().setAttribute("endDate", endDate);
		} else {
			endDate = (String) request.getSession().getAttribute("endDate");
		}
		Object[] obj = iaoServer.findIAOApply(iaoApply, startDate, endDate,
				Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findBaoxiaoOK";
	}

	/** 打印出门凭证 **/
	public String print() {

		return ERROR;
	}

	/** 获取下拉菜单 **/

	public String findIAOStyle() {
		String message = iaoServer.findIAOStyle(tag);
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
	/**数据导出**/
	public String exportEXCEL(){
		iaoServer.explorerEXL(iaoApply, startDate, endDate);
		return null;
	}
	public IAOServer getIaoServer() {
		return iaoServer;
	}

	public void setIaoServer(IAOServer iaoServer) {
		this.iaoServer = iaoServer;
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

	public IAOApply getIaoApply() {
		return iaoApply;
	}

	public void setIaoApply(IAOApply iaoApply) {
		this.iaoApply = iaoApply;
	}

	public List<CarryGoods> getListCarryGoods() {
		return listCarryGoods;
	}

	public void setListCarryGoods(List<CarryGoods> listCarryGoods) {
		this.listCarryGoods = listCarryGoods;
	}

	public String getCrudTag() {
		return crudTag;
	}

	public void setCrudTag(String crudTag) {
		this.crudTag = crudTag;
	}

}
