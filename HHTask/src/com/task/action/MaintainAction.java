package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IMaintainService;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.Maintain;
import com.task.entity.VOMaintain;

public class MaintainAction{
	private IMaintainService ims;
	private List list;
	private String errorMessage;
	private Maintain mai;
	private VOMaintain voma = new VOMaintain();
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String initQueryMaintain(){
		Object[] object = ims.queryMaintain(null, Integer.parseInt(cpage), pageSize);
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
	public String queryMaintainByCondition(){
		Map map = new HashMap();
		if(voma.getMatetag() != null && !voma.getMatetag().equals(""))
			map.put("name", voma.getMatetag());
		if(voma.getNumber() != null && !voma.getNumber().equals(""))
			map.put("number", voma.getNumber());
		if(voma.getState() != null &&!voma.getState().equals("")){
			if(!voma.getState().equals("状态选择")){
				map.put("state", voma.getState());
			}
		}
		if(map.size() > 0){
			ActionContext.getContext().getSession().put("main", map);
		}else{
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"main");
			} else
				ActionContext.getContext().getSession().remove("main");
		}
		Object[] object = ims.queryMaintain(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("maintain_queryMaintainByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}
	public String del(){
		if(voma.getId() != null){
			String msg = "";
			try{
				ims.delMaintainById(voma.getId());
				msg = "删除成功!";
			}catch(Exception e){
				e.printStackTrace();
				msg = "删除失败!";
			}
			ResponseUtil.write(ServletActionContext.getResponse(), msg, "maintain_initQueryMaintain.action", null);
			return null;
		}
		return "redirectList";
	}
	public String initUpdate(){
		if(voma.getId() != null){
			mai = ims.getMaintainById(voma.getId());
			if(mai == null){
				return "redirectList";
			}
			return "success"; 
		}
		return "redirectList";
	}
	public String update(){
		if(mai != null && mai.getId() != null){
			String msg = ims.update(mai);
			ResponseUtil.write(ServletActionContext.getResponse(), msg, "maintain_initQueryMaintain.action", null);
			return null;
		}
		return "redirectList";
	}
	public String add(){
		if(voma != null && voma.getId() != null){
			String msg = ims.addRelation(voma);
			if(msg.equals("")){
				return "redirectList";
			}else{
				ResponseUtil.write(ServletActionContext.getResponse(), msg, "store_initQueryStore.action", null);
				return null;
			}
		}
		return "redirectList";
	}
	public String repair(){
		if(voma!=null && voma.getId()!=null){
			ims.repair(voma.getId());
		}
		return "redirectList";
	}
	public String export(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(voma.getState() != null &&!voma.getState().equals("")){
			if(!voma.getState().equals("状态选择")){
				map.put("state", voma.getState());
			}
		}
		ims.exportExcel(map);
		return null;
	}
	
	public IMaintainService getIms() {
		return ims;
	}
	public void setIms(IMaintainService ims) {
		this.ims = ims;
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
	public Maintain getMai() {
		return mai;
	}
	public void setMai(Maintain mai) {
		this.mai = mai;
	}
	public VOMaintain getVoma() {
		return voma;
	}
	public void setVoma(VOMaintain voma) {
		this.voma = voma;
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
