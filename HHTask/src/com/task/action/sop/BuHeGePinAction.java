 package com.task.action.sop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.sop.BeHeGePinServer;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.DefectOfType;
import com.task.util.MKUtil;
import com.task.util.Util;

@SuppressWarnings("serial")
public class BuHeGePinAction extends ActionSupport {

	private BeHeGePinServer buhegepinServer;
	private String errorMessage;
	private String successMessage;
	private int pageSize = 15;
	private BuHeGePin buhegepin;
	private int id;
	private DefectOfType defType;
	private List<DefectOfType> defTypeList;
	private Integer [] selected;

	private String cpage = "1";
	private List<BuHeGePin> buhegepinlist;
	private String total;
	private String url;
	private String statue = "";
	private String tag;

	public String addBuHeGePin() {
		buhegepin.setWritePerson(Util.getLoginUser().getName());
		buhegepin.setWriteDate(Util.getDateTime().substring(0, 10));
		String bool = buhegepinServer.AddBuGePin(buhegepin);

		if ("true".equals(bool)) {
			successMessage = "添加成功";
			return "BuHeGePin_add";
		} else {
			errorMessage = "添加" + buhegepin.getCode() + buhegepin.getType()
					+ "失败！错误原因:" + bool;
			return ERROR;
		}
	}

	public String delBuHeGePin() {
		buhegepin = buhegepinServer.FindBuHeGePinByid(id);
		boolean bool = buhegepinServer.DelBuHeGePin(buhegepin);
		if (bool) {
			successMessage = "删除" + buhegepin.getType() + "成功！";
			return "BuHeGePin_del";
		}
		errorMessage = "删除" + buhegepin.getType() + "失败！";
		return ERROR;
	}

	public String updataBuHeGePin() {
		Object[] obj = new Object[2];
		obj[0] = buhegepin.getType();
		obj[1] = buhegepin.getId();
		System.out.println(obj[0] + "  " + obj[1]);
		boolean bool = buhegepinServer.UpdataBuHeGePin(obj);
		if (bool) {
			successMessage = "修改成功!";
			return "BuHeGePin_update";
		}
		return ERROR;
	}

	public String findAllBuHeGePin() {

		int count = buhegepinServer.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		buhegepinlist = buhegepinServer.FindAllBuHeGePin(Integer
				.parseInt(cpage), pageSize);
		buhegepin = null;
		if (buhegepinlist != null) {
			this.setUrl("BuHeGePinAction_findAllBuHeGePin.action");
			return "BuHeGePin_findAll";
		}
		errorMessage = "暂时还没有不合格品";
		return ERROR;
	}

	// 通过ID 查找对象
	public String findbuhegepinByid() {
		buhegepin = buhegepinServer.FindBuHeGePinByid(id);
		if (buhegepin != null) {
			return "BuHeGePin_Byid";
		}
		return ERROR;
	}

	// 条件查询;
	public String findBuHeGePin() {
		if (buhegepin != null) {
			ActionContext.getContext().getSession().put("buhegepin", buhegepin);
		} else {
			buhegepin = (BuHeGePin) ActionContext.getContext().getSession()
					.get("buhegepin");
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();

		map = buhegepinServer.findBuHeGePinByCondition(buhegepin, Integer
				.parseInt(cpage), pageSize);
		buhegepinlist = (List<BuHeGePin>) map.get(1);
		int count = (Integer) map.get(2);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("BuHeGePinAction_findBuHeGePin.action?statue=find");
		return "BuHeGePin_findAll";
	}

	// 判断是否有某个缺陷类型；
	public boolean IfType() {
		boolean flag = false;
		List<String> typeList = buhegepinServer.findBuHeGePinType();
		if (typeList != null && typeList.size() > 0) {
			for (int i = 0; i < typeList.size(); i++) {
				if (typeList.get(i).equals(buhegepin.getType())) {
					flag = true;
				}
			}
		}
		return flag;
	}

	// 查询所有不合格品类型
	public void findAllbuhegepinlist() {
		List<String> typeList = buhegepinServer.findBuHeGePinType();
		try {
			MKUtil.writeJSON(typeList);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	//添加缺陷大类
	public String adddefType(){
		errorMessage =	buhegepinServer.adddDefectOfType(defType);

		if ("true".equals(errorMessage)) {
			successMessage = "添加成功";
			return "BuHeGePin_add";
		} else {
			successMessage = "添加失败!~";
			return ERROR;
		}
	}
	public void ajax_findAlldefType(){
		try {
			defTypeList =	buhegepinServer.findAllDefTypeList();
			MKUtil.writeJSON(defTypeList);
		} catch (Exception e) {
			MKUtil.writeJSON("error");
			e.printStackTrace();
		}
	}
	//更新缺陷大类
	public String updateDefType(){
		errorMessage =	buhegepinServer.updateDefectOfType(defType);
		if ("true".equals(errorMessage)) {
			errorMessage = "修改成功";
			return "defType_update";
		} else {
			successMessage = "添加失败!~";
			return ERROR;
		}
	}
	//查询缺陷大类
	public String findAllDefTypeList(){
		if (defType != null) {
			ActionContext.getContext().getSession().put("defType", defType);
		} else {
			defType = (DefectOfType) ActionContext.getContext().getSession()
					.get("defType");
		}
		Object[] obj=	buhegepinServer.findDefType(defType, statue, pageSize, Integer.parseInt(cpage));
		
		defTypeList = (List<DefectOfType>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("BuHeGePinAction_findAllDefTypeList.action?statue="+statue);
		return "defType_List";
	}
	public void ajaxfindAllDefTypeList(){
		defTypeList = buhegepinServer.findAllDefTypeList();
		MKUtil.writeJSON(defTypeList);
	}
	//根据缺陷大类查询相关的缺陷类型
	public void ajax_findBhgByDefId(){
		try {
			buhegepinlist =	buhegepinServer.findbhgpListByDefId(id,statue,null);
			MKUtil.writeJSON(buhegepinlist);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	public void findBhgByDefName(){
		try {
			buhegepinlist =	buhegepinServer.findbhgpListByDefName(statue);
			MKUtil.writeJSON(buhegepinlist);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	public String findBhgByDefId(){
		if (buhegepin != null) {
			ActionContext.getContext().getSession().put("buhegepin", buhegepin);
		} else {
			buhegepin = (BuHeGePin) ActionContext.getContext().getSession()
					.get("buhegepin");
		}
		defType = buhegepinServer.getdefTypeById(id);
		buhegepinlist =	buhegepinServer.findbhgpListByDefId(id,statue,buhegepin);
		if("update".equals(tag)){
			return "defType_update";
		}
		return "BuHeGePin_findAll";
	}
	public String delDefType(){
		boolean bool =	buhegepinServer.delDefectOfType(defType);
		if(bool){
			url = "BuHeGePinAction_findAllDefTypeList.action?cpage="+cpage;
		}else{
			errorMessage="删除失败!~";
		}
		return "error";
	}
	public String bangdingbhg(){
		errorMessage =	buhegepinServer.bangdingbhg(id, selected);
		if("true".equals(errorMessage)){
			url = "BuHeGePinAction_findBhgByDefId.action?id=1&statue=ybd";
		}
		return "error";
	}
	
	public BeHeGePinServer getBuhegepinServer() {
		return buhegepinServer;
	}

	public void setBuhegepinServer(BeHeGePinServer buhegepinServer) {
		this.buhegepinServer = buhegepinServer;
	}

	public BuHeGePin getBuhegepin() {
		return buhegepin;
	}

	public void setBuhegepin(BuHeGePin buhegepin) {
		this.buhegepin = buhegepin;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public List<BuHeGePin> getBuhegepinlist() {
		return buhegepinlist;
	}

	public void setBuhegepinlist(List<BuHeGePin> buhegepinlist) {
		this.buhegepinlist = buhegepinlist;
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

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public DefectOfType getDefType() {
		return defType;
	}

	public void setDefType(DefectOfType defType) {
		this.defType = defType;
	}

	public List<DefectOfType> getDefTypeList() {
		return defTypeList;
	}

	public void setDefTypeList(List<DefectOfType> defTypeList) {
		this.defTypeList = defTypeList;
	}

	public Integer[] getSelected() {
		return selected;
	}

	public void setSelected(Integer[] selected) {
		this.selected = selected;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
