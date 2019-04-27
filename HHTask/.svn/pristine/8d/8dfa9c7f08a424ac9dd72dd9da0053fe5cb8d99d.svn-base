package com.task.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.SopGongwei;
import com.task.entity.TaSopGongwei;
import com.task.util.MKUtil;

public class SopGongweiAction extends ActionSupport {
	private SopGongwei gongweiServer;
	private Integer id;
	private String tag;
	private TaSopGongwei gongwei;
	private List list;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	//查询工序
	public String selectGongxu() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		if(null==gongwei){
			gongwei=(TaSopGongwei)request.getSession().getAttribute("gongwei");
		}
		this.pageSize=15;		
		this.setUrl("gongxuAction!selectGongxu.action");
		Object[] obj=gongweiServer.selectGongxu
		(gongwei, Integer.parseInt(cpage), pageSize);
		request.getSession().setAttribute("gongwei", gongwei);
		int count=(Integer)obj[0];
		
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list=(List)obj[1];
		return "selectGongxuOK";		
	}
	//添加工序信息
	public String saveGongwei()throws Exception{
		gongweiServer.save(gongwei);
		return "saveGongweiOK";
	}
	//获得单个工序
	public String getOneGongwei()throws Exception{
		this.cpage=cpage;
		gongwei=gongweiServer.getGongwei(id);
		return "getGongweiOK";
	}
	//修改工序
	public String updateGongwei()throws Exception{
		if(gongweiServer.updateGongwei(gongwei)){
			this.cpage = cpage;
			return "updateGongweiOK";
		}
		return ERROR;
	}
	//删除工序
	public String deleteGongwei() throws Exception{
		gongwei=gongweiServer.getGongwei(id);
		this.cpage=cpage;
		if(gongweiServer.deleteGongwei(gongwei)){
			return "deleteGongweiOK";
		}
		return ERROR;
	}
	
	public String getWorkStation(){
		list = new ArrayList();
		list = gongweiServer.getWorkStations(id);
		MKUtil.writeJSON(list);
		return null;
	}

	public Integer getId() {
		return id;
	}
	public SopGongwei getGongweiServer() {
		return gongweiServer;
	}
	public void setGongweiServer(SopGongwei gongweiServer) {
		this.gongweiServer = gongweiServer;
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
	
	public TaSopGongwei getGongwei() {
		return gongwei;
	}
	public void setGongwei(TaSopGongwei gongwei) {
		this.gongwei = gongwei;
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
	
}
