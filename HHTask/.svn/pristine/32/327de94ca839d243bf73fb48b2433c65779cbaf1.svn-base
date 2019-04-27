package com.task.action.gzbj;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.bcel.AtAjAttributes;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.gzbj.ProcessGzstoreServer;
import com.task.entity.android.OsScope;
import com.task.entity.gzbj.Measuring;
import com.task.entity.gzbj.ProcessAndMeasuring;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.util.MKUtil;

public class ProcessGzstoreAction extends ActionSupport{
	private ProcessGzstoreServer processGzstoreServer;
	private ProcessGzstore processGzstore;
	private List<Map> maps;
	private List<ProcessAndMeasuring> pamList;
	private Measuring measuring;
	private List<Measuring> measuringList;
	
	private String tag;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private  List<OsScope> osList;
	private Integer id ;
	private Integer id0 ;
	private String processName;
	private Integer [] ids;
	
	private Integer  size; 
	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
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

	public ProcessGzstoreServer getProcessGzstoreServer() {
		return processGzstoreServer;
	}

	public void setProcessGzstoreServer(
			ProcessGzstoreServer processGzstoreServer) {
		this.processGzstoreServer = processGzstoreServer;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ProcessGzstore getProcessGzstore() {
		return processGzstore;
	}

	public void setProcessGzstore(ProcessGzstore processGzstore) {
		this.processGzstore = processGzstore;
	}

	// 查询所有工序
	public String findGX() {
		if (processGzstore != null) {
			ActionContext.getContext().getSession().put("processGzstore",
					processGzstore);
		} else {
			processGzstore = (ProcessGzstore) ActionContext.getContext()
					.getSession().get("processGzstore");
		}
		Object[] object = this.processGzstoreServer.findGxAll(processGzstore,
				Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			// list = (List<Gzstore>) object[0];
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("processGzstoreAction_findGX.action?tag="+tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findGX";//progx.jsp
	}

	/***
	 * 获得所有的工序信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getProcessGzstoreListAllForSelect() {
		List<ProcessGzstore> processGzstoreList = (List<ProcessGzstore>) this.processGzstoreServer
				.getProcessGzstoreListAllForSelect(this.processGzstore);
		for (ProcessGzstore processGzstore : processGzstoreList) {
			processGzstore.setProcessgzstores(null);
			processGzstore.setProcessgzstores1(null);
			processGzstore.setOs(null);
			processGzstore.setMore(null);
			processGzstore.setUsers(null);

		}
		MKUtil.writeJSON(true, "操作成功", processGzstoreList);
		return null;
	}

	public String getProcessGzstoreById() {
		ProcessGzstore processGzstore = this.processGzstoreServer
				.getProcessGzstoreById(this.processGzstore.getId());
		MKUtil.writeJSON(true, "操作成功", processGzstore);
		return null;
	}
	public String getosListbyid(){
		osList = processGzstoreServer.getOsListbyId(id);
		processGzstore = processGzstoreServer.getProcessGzstoreById(id);
		size = osList.size();
		return "OsScope_bdlist";
	}
	
	public void bdOsScope(){
	boolean bool=processGzstoreServer.bdOsScope(id, osList);
		errorMessage="绑定失败";
		if(bool){
			errorMessage="绑定成功";
		}
		try {
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}
	public void delOsScope(){
		boolean bool=processGzstoreServer.delOsScope(processGzstore.getId(), id);
		if(bool){
			errorMessage="删除成功!";
			try {
				MKUtil.writeJSON(errorMessage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public  String shuaxin(){
	  errorMessage=	processGzstoreServer.shuaixn(processName);
		if("true".equals(errorMessage)){
			try {
				MKUtil.writeJSON("更新成功");
				return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MKUtil.writeJSON(e);
			}
		}
		return "error";
	}
	
	public void findProcessByName(){
		processGzstore =	processGzstoreServer.getproceGzstorebyname(processName);
		if(processGzstore!=null){
			processGzstore.setProcessgzstores(null);
			processGzstore.setProcessgzstores1(null);
			processGzstore.setOs(null);
			processGzstore.setMore(null);
			processGzstore.setUsers(null);
			
			try {
				MKUtil.writeJSON(processGzstore);
			} catch (Exception e) {
				e.printStackTrace();
				MKUtil.writeJSON(e);
			}
		}
	}
	/**
	 * 申请特殊工序
	 */
	public void applySpecial(){
		String msg=processGzstoreServer.applySpecial(id);
		MKUtil.writeJSON(msg);
	}
	
	//根据某个工序查询所有绑定的量、检具
	public String findAllPamList(){
		pamList =processGzstoreServer.findAllPamList(id);
		return "PamList_show";
	}
	//根据工序查询该工序所有未绑定的量、检具
	public String findALLmMeasList(){
		measuringList =	processGzstoreServer.findALLmMeasList(id, measuring);
		return "measuringList_bangding";
	}
	public String savePAM(){
		errorMessage =	processGzstoreServer.savePAM(id, ids);
		return "measuringList_bangding";
	}
	//解除工序绑定量、检具
	public String delPAM(){
		errorMessage =	processGzstoreServer.delPAM(id);
		url ="processGzstoreAction_findAllPamList.action?id="+id0;
		return "error";
	}
	
	
	public List<OsScope> getOsList() {
		return osList;
	}

	public void setOsList(List<OsScope> osList) {
		this.osList = osList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<ProcessAndMeasuring> getPamList() {
		return pamList;
	}

	public void setPamList(List<ProcessAndMeasuring> pamList) {
		this.pamList = pamList;
	}

	public Measuring getMeasuring() {
		return measuring;
	}

	public void setMeasuring(Measuring measuring) {
		this.measuring = measuring;
	}

	public List<Measuring> getMeasuringList() {
		return measuringList;
	}

	public void setMeasuringList(List<Measuring> measuringList) {
		this.measuringList = measuringList;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getId0() {
		return id0;
	}

	public void setId0(Integer id0) {
		this.id0 = id0;
	}

	
	

	

}
