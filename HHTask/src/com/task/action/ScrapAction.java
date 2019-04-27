package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IScrapService;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.Scrap;
import com.task.entity.Store;
import com.task.entity.VOScrap;

public class ScrapAction{
	private IScrapService iss;
	private List list;
	private String errorMessage;
	private Scrap scr;
	private VOScrap vosc = new VOScrap();
	private Store sto;
	private boolean flag = false;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String initQueryScrap(){
		Object[] object = iss.queryScrap(null, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("scrap_initQueryScrap.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}
	public String queryScrapByCondition(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vosc.getName() != null && !vosc.getName().equals(""))
			map.put("name", vosc.getName());
		if(vosc.getStandard() != null && !vosc.getStandard().equals(""))
			map.put("standard", vosc.getStandard());
		if(vosc.getNumber() != null &&!vosc.getNumber().equals(""))
			map.put("number", vosc.getNumber());
		if(vosc.getPeopleName() != null &&!vosc.getPeopleName().equals(""))
			map.put("peopleName", vosc.getPeopleName());
		if(map.size() > 0){
			ActionContext.getContext().getSession().put("scrap", map);
		}else{
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"scrap");
			} else
				ActionContext.getContext().getSession().remove("scrap");
		}
		Object[] object = iss.queryScrap(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("scrap_queryScrapByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}
	public String del(){
		if(vosc.getId() != null){
			String msg = "";
			try{
				iss.delScrapById(vosc.getId());
				msg = "删除成功!";
			}catch(Exception e){
				e.printStackTrace();
				msg = "删除失败!";
			}
			ResponseUtil.write(ServletActionContext.getResponse(), msg, "scrap_initQueryScrap.action", null);
			return null;
		}
		return "redirectList";
	}
	public String initUpdate(){
		if(vosc.getId() != null){
			scr = iss.getScrapById(vosc.getId());
			if(scr == null){
				return "redirectList";
			}
			return "success"; 
		}
		return "redirectList";
	}
	public String update(){
		if(scr != null && scr.getId() != null){
			String msg = iss.update(scr);
			ResponseUtil.write(ServletActionContext.getResponse(), msg, "scrap_initQueryScrap.action", null);
			return null;
		}
		return "redirectList";
	}
	public String initAdd(){
		if(vosc.getId()!=null && !vosc.getId().equals("")){
			sto = iss.getStoreById(vosc.getId());
			if(sto == null){
				ResponseUtil.write(ServletActionContext.getResponse(), "报废失败", "store_initQueryStore.action", null);
				return null;
			}
		}else{
			return "redirectFrom";
		}
		return "success";
	}
	public String add(){
		if(scr != null && vosc.getId() != null){
			String msg = iss.add(scr,vosc.getId());
			if(msg.equals("")){
				return "redirectList";
			}else{
				ResponseUtil.write(ServletActionContext.getResponse(), msg, "store_initQueryStore.action", null);
				return null;
			}
		}
		return "redirectList";
	}
	
	public String export(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vosc.getName() != null && !vosc.getName().equals(""))
			map.put("name", vosc.getName());
		if(vosc.getPeopleName() != null &&!vosc.getPeopleName().equals(""))
			map.put("peopleName", vosc.getPeopleName());
		iss.exportExcel(map);
		return null;
	}
	/**
	 * 归还管理中 报废功能
	 * @Title: also
	 * @return String  
	 * @throws
	 */
	public String also(){
		if(scr != null){
			String msg = iss.alsoScrap(scr);
			if(!msg.equals("")){
				flag = true;
				vosc.setMsg(msg);
			}
		}
		return "giveBackMsg";
	}
	
	public IScrapService getIss() {
		return iss;
	}
	public void setIss(IScrapService iss) {
		this.iss = iss;
	}
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
	public Scrap getScr() {
		return scr;
	}
	public void setScr(Scrap scr) {
		this.scr = scr;
	}
	public VOScrap getVosc() {
		return vosc;
	}
	public void setVosc(VOScrap vosc) {
		this.vosc = vosc;
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Store getSto() {
		return sto;
	}
	public void setSto(Store sto) {
		this.sto = sto;
	}
	
}
