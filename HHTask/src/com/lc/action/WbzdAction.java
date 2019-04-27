package com.lc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.lc.entity.zw.LSwbzd;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.lc.WbzdServer;

/**
 * 外币字典action
 * 
 * @author jhh
 * 
 */
public class WbzdAction extends ActionSupport {
	private WbzdServer wbzdServer;
	private LSwbzd wbzd;// 外币字典
	private List list;
	private String tag;
	private String message;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;

	public WbzdServer getWbzdServer() {
		return wbzdServer;
	}

	public void setWbzdServer(WbzdServer wbzdServer) {
		this.wbzdServer = wbzdServer;
	}

	public LSwbzd getWbzd() {
		return wbzd;
	}

	public void setWbzd(LSwbzd wbzd) {
		this.wbzd = wbzd;
	}

	private int pageSize = 15;

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

	/**
	 * 查询管理报销单
	 * 
	 * @return
	 */
	public String findWDZD() {
		this.pageSize = 15;
		this.setUrl("WbzdAction!findWDZD.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		// BaoxiaoDan baoxiao=new BaoxiaoDan();

		Object[] obj = wbzdServer.findWBZD(Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findOK";
	}

}
