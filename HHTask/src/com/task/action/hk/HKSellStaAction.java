package com.task.action.hk;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.HKSellStaServer;
import com.task.entity.TaHkSellSta;

public class HKSellStaAction extends ActionSupport {
	private HKSellStaServer hkSellStaserver;
	private TaHkSellSta tahkSellSta;
	private String message;
	private String tag;//手动添加记录的标记
	private Integer id;//主键
	private List list;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 20;
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	//管理送货申请信息
	public String findHKSellSta(){
		this.pageSize = 15;
		this.setUrl("HKSellStaAction!findHKSellSta.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if(null!=tahkSellSta){			
			request.getSession().setAttribute("tahkSellSta",tahkSellSta);
		}else{
			tahkSellSta=(TaHkSellSta)request.getSession().getAttribute("tahkSellSta");
		}
		Object[] obj = hkSellStaserver.findHKSellSta(tahkSellSta, startDate, endDate,
				Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];	
		return "finHkSellStaOK";
	}
	//获得送货申请明细
	public String getHKSellSta(){		
		tahkSellSta=hkSellStaserver.getHKSellSta(id);
		return "getHKsellSta";
	}
	//更新送货申请信息
	public String updateHKSellSta(){
		if(hkSellStaserver.updateHKSellSta(tahkSellSta)){
			return "updateStaOK";
		}
		return INPUT;
	}
	//删除送货申请信息
	public String deleteHKSellSta(){
		this.cpage=cpage;
		if(hkSellStaserver.deleteHKSellSta(id)){
			return "deleteOK";
		}
		return INPUT;
	}
	//选择下拉条码
	public String selectItem(){
		String message = hkSellStaserver.selectItem(tag);
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
	public TaHkSellSta getTahkSellSta() {
		return tahkSellSta;
	}
	public void setTahkSellSta(TaHkSellSta tahkSellSta) {
		this.tahkSellSta = tahkSellSta;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public HKSellStaServer getHkSellStaserver() {
		return hkSellStaserver;
	}
	public void setHkSellStaserver(HKSellStaServer hkSellStaserver) {
		this.hkSellStaserver = hkSellStaserver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
}
