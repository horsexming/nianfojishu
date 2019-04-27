package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IRenewService;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.Renew;
import com.task.entity.Store;
import com.task.entity.VORenew;

public class RenewAction{
	private IRenewService irs;
	private List list;
	private String errorMessage;
	private Renew renew;
	private VORenew vore = new VORenew();
	private Store sto;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String initQueryRenew(){
		Object[] object = irs.queryRenew(null, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("renew_initQueryRenew.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}
	public String queryRenewByCondition(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vore.getName() != null && !vore.getName().equals(""))
			map.put("name", vore.getName());
		if(vore.getStandard() != null && !vore.getStandard().equals(""))
			map.put("standard", vore.getStandard());
		if(vore.getNumber() != null &&!vore.getNumber().equals(""))
			map.put("number", vore.getNumber());
		if(vore.getStorehouse() != null && !vore.getStorehouse().equals(""))
			map.put("storehouse", vore.getStorehouse());
		if(vore.getJobNum() != null && !vore.getJobNum().equals(""))
			map.put("jobNum", vore.getJobNum());
		if(vore.getJobName() != null && !vore.getJobName().equals(""))
			map.put("jobName", vore.getJobName());
		if(vore.getDevicename() != null && !vore.getDevicename().equals(""))
			map.put("devicename", vore.getDevicename());
		if(vore.getPlace() != null && !vore.getPlace().equals(""))
			map.put("place", vore.getPlace());
		if(vore.getCategory() != null && !vore.getCategory().equals(""))
			map.put("category", vore.getCategory());
		if(vore.getStartTime() != null && !vore.getStartTime().equals(""))
			map.put("startTime", vore.getStartTime());
		if(vore.getEndTime() != null && !vore.getEndTime().equals(""))
			map.put("endTime", vore.getEndTime());
		if(map.size() > 0){
			ActionContext.getContext().getSession().put("renew", map);
		}else{
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"renew");
			} else
				ActionContext.getContext().getSession().remove("renew");
		}
		Object[] object = irs.queryRenew(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("renew_queryRenewByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}
	public String del(){
		if(vore.getId() != null){
			String msg = "";
			try{
				irs.delRenewById(vore.getId());
				msg = "删除成功!";
			}catch(Exception e){
				e.printStackTrace();
				msg = "删除失败!";
			}
			ResponseUtil.write(ServletActionContext.getResponse(), msg, "renew_initQueryRenew.action", null);
			return null;
		}
		return "redirectList";
	}
	public String initUpdate(){
		if(vore.getId() != null){
			renew = irs.getRenewById(vore.getId());
			if(renew == null){
				return "redirectList";
			}
			return "success"; 
		}
		return "redirectList";
	}
	public String update(){
		if(renew != null && renew.getId() != null){
			String msg = irs.update(renew);
			ResponseUtil.write(ServletActionContext.getResponse(), msg, "renew_initQueryRenew.action", null);
			return null;
		}
		return "redirectList";
	}
	

	public String initAdd(){
		if(vore.getId()!=null && !vore.getId().equals("")){
			sto = irs.getStoreById(vore.getId());
			if(sto == null){
				ResponseUtil.write(ServletActionContext.getResponse(), "以旧换新失败", "store_initQueryStore.action", null);
				return null;
			}
		}else{
			return "redirectFrom";
		}
		return "success";
	}
	//以旧换新添加
	public String add(){
		if(renew != null && vore.getId() != null){
			String msg = irs.add(renew,vore.getId());
			if(msg.equals("")){
				return "redirectList";
			}else{
				ResponseUtil.write(ServletActionContext.getResponse(), msg, "store_initQueryStore.action", null);
				return null;
			}
		}
		return "redirectList";
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
	public IRenewService getIrs() {
		return irs;
	}
	public void setIrs(IRenewService irs) {
		this.irs = irs;
	}
	public Renew getRenew() {
		return renew;
	}
	public void setRenew(Renew renew) {
		this.renew = renew;
	}
	public VORenew getVore() {
		return vore;
	}
	public void setVore(VORenew vore) {
		this.vore = vore;
	}
	public Store getSto() {
		return sto;
	}
	public void setSto(Store sto) {
		this.sto = sto;
	}
	
}
