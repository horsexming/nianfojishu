package com.task.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.WarehouseAreaServerDao;
import com.task.entity.GoodHouse;
import com.task.entity.WareHouse;
import com.task.entity.WarehouseNumber;
import com.task.entity.menjin.AccessEquipment;
import com.task.util.MKUtil;

@SuppressWarnings("unchecked")
public class WarehouseAreaAction extends ActionSupport {

	private GoodHouse warehouseArea;// 仓区对象
	private WarehouseNumber warehouseNumber;// 库位对象
	private List<GoodHouse> waList;
	private List<WarehouseNumber> wnList;
	private WarehouseAreaServerDao waserver;//

	private String wareHouseName;// 仓库名称
	private String wareHouseNO;// 库别编码
	private String cangqu;// 仓区；
	private String errorMessage;
	private String successMessage;
	private File addwarehouseNumber;
	private List<AccessEquipment> ipList;
	private List<AccessEquipment> fourlightIpList;
	private int id;
	private int size;

	private int pageSize = 15;
	private String cpage = "1";
	private String total;
	private String url;
	private String statue = "";
	private String tag ;

	public void getAllwarehouse() {
		try {
			List<WareHouse> wareHouseList = waserver.findAllWareHouse();
			MKUtil.writeJSON(wareHouseList);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}

	}
	
	public void getAllwaList(){
		try {
			waList = waserver.getAllWalist();
			MKUtil.writeJSON(waList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// 添加仓区
	public String addwarehouseArea() {
		if (waList != null && waList.size() > 0) {
			errorMessage = waserver.addWarehouseArea(waList, wareHouseName);
			if (errorMessage.equals("true")) {
				errorMessage = "添加成功!";
			}
		}
		return "error";
	}

	// 添加库位
	public String addwarehouseNumber() {
		if (wnList != null && wnList.size() > 0) {
			errorMessage = waserver.addWarehouseNumber(wnList, cangqu,
					wareHouseName);
			if (errorMessage.equals("true")) {
				errorMessage = "添加成功!";
			}
		}
		return "error";
	}

	// 根据库别编号查询仓区
	public void findwaListByNO() {
		try {
			if (wareHouseName != null && !"".equals(wareHouseName)) {
				waList = waserver.findwaListBywareHouseNo(wareHouseName);
			
				MKUtil.writeJSON(waList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}

	// 根据库别编号，仓区 查询库位
	public void findwnListByNO() {
		try {
			if (wareHouseName != null && !"".equals(wareHouseName)
					&& cangqu != null && !"".equals(cangqu)) {
				wnList = waserver.findwnList(wareHouseName, cangqu);
				MKUtil.writeJSON(wnList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(e);
		}

	}

	// 条件查询所有仓区
	public String findwaList() {
		if (warehouseArea != null) {
			ActionContext.getContext().getSession().put("warehouseArea",
					warehouseArea);
		} else {
			warehouseArea = (GoodHouse) ActionContext.getContext().getSession()
					.get("warehouseArea");
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map = waserver.findWAByCondition(warehouseArea,
				Integer.parseInt(cpage), pageSize);
		waList = (List<GoodHouse>) map.get(1);
		if (waList != null && waList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("WarehouseAreaAction_findwaList.action");
			return "warehouseArea_List";
		}
		errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return ERROR;
	}

	public void mingXi() {
		errorMessage = waserver.getWarebgnding(id);
		if (errorMessage!=null)
			MKUtil.writeJSON(true, errorMessage, null);
		else
			MKUtil.writeJSON(false, "获取失败！", null);
	}
	
	// 条件查询库位
	public String findwnList() {
		if (warehouseNumber != null) {
			ActionContext.getContext().getSession().put("warehouseNumber",
					warehouseNumber);
		} else {
			warehouseNumber = (WarehouseNumber) ActionContext.getContext()
					.getSession().get("warehouseNumber");
		}
		if ("dykwm".equals(statue)) {
			pageSize = 0;
			cpage = "0";
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map = waserver.findWNByCondition(warehouseNumber, Integer
				.parseInt(cpage), pageSize,tag);
		wnList = (List<WarehouseNumber>) map.get(1);
		if (wnList != null && wnList.size() > 0) {
			int count = (Integer) map.get(2);
			if ("dykwm".equals(statue)) {
				pageSize = 15;
			}
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("WarehouseAreaAction_findwnList.action?tag="+tag);
			if ("dykwm".equals(statue)) {
				size = wnList.size();
				return "WarehouseNumber_plkuma";
			}
			return "WarehouseNumber_list";
		}
		errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "WarehouseNumber_list";

	}

	// 删除仓区
	public String delwarehouseArea() {
		if (warehouseArea != null) {
			waserver.delWarehouseArea(warehouseArea);
		}
		return "findwaList";
	}

	// 删除库位
	public String delwarehouseNumber() {
		if (warehouseNumber != null) {
			waserver.delWarehouseNumber(warehouseNumber);
		}
		return "findwnList";
	}

	// 修改仓区
	public String updatewarehouseArea() {
		if (warehouseArea != null) {
			waserver.updateWarehouseArea(warehouseArea);
		}
		return "";
	}

	// 修改库位
	public String updatewarehouseNumber() {
		if (warehouseNumber != null) {
			errorMessage = waserver.updateWarehouseNumber(warehouseNumber);
			if ("true".equals(errorMessage)) {
				errorMessage = "修改成功";
			}
		}
		return "WarehouseNumber_update";
	}

	// 查询所有库位信息
	public String findAllwnList() {
		wnList = waserver.findAllWNList();
		if (wnList != null && wnList.size() > 0) {
			size = wnList.size();
		}
		return "WarehouseNumber_plkuma";
	}
	
	// 查询所有库位信息licong
	public void findAllWNList_1() {
		MKUtil.writeJSON(waserver.findAllWNList_1());
	}
	// 查询所有库位信息licong
	public void findAllWNList_2() {
		waserver.addGoodsBandDing();
	}

	// 根据id 得到库位
	public String getwarehouseNumberById() {
		warehouseNumber = waserver.getwarehouserNumberById(id);
		return "WarehouseNumber_update";
	}

	// 批量导入库位
	public String addplwarehouseNumber() {
		errorMessage = waserver.addplwarehouseNumber(addwarehouseNumber);
		return "error";
	}
	//获得所有库位和四色灯的IP信息;
	public void findIpList(){
		Object [] obj = waserver.findIpList();
		if(obj!=null && obj.length ==2){
			try {
				MKUtil.writeJSON(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//判断某个仓区在仓库下是否存在
	public void ajaxIsExsitHouseName(){
		
		try {
			GoodHouse warehouseArea=new GoodHouse();
			warehouseArea.setGoodsStoreWarehouse(wareHouseName);//仓库名称
			warehouseArea.setGoodHouseName(cangqu);//仓区名称
			boolean bool= waserver.goodHouseCountByWG(warehouseArea);
			MKUtil.writeJSON(bool);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 打开库位门
	 * @return
	 */
	public String OpenWNById(){
		errorMessage =	waserver.OpenWNById(id);
		if("true".equals(errorMessage)){
			errorMessage = "开门成功!";
			url = "WarehouseAreaAction_findwnList.action?tag="+tag+"&cpage="+cpage;
		}
		return "error";
	}
	
	public void OpenWNById_1(){
		errorMessage =	waserver.OpenWNById(id);
		if("true".equals(errorMessage)){
			MKUtil.writeJSON(true, null, null);
		}else {
			MKUtil.writeJSON(false, "开门失败", null);
		}
	}
	/**
	 * 关闭库位门
	 * @return
	 */
	public String ColseWNById(){
		errorMessage =	waserver.ColseWNById(id);
		if("true".equals(errorMessage)){
			errorMessage = "关门成功!";
			url = "WarehouseAreaAction_findwnList.action?tag="+tag+"&cpage="+cpage;
		}
		return "error";
	}
	
	public void ColseWNById_1(){
		errorMessage =	waserver.ColseWNById(id);
		if("true".equals(errorMessage)){
			MKUtil.writeJSON(true, null, null);
		}else {
			MKUtil.writeJSON(false, "开门失败", null);
		}
	}
	
	/**
	 *灯 
	 */
	public void czlight(){
		errorMessage =	waserver.CongzhiLight(id);
		if("true".equals(errorMessage))MKUtil.writeJSON(true, "发送成功", null);
		else MKUtil.writeJSON(false, errorMessage, null);
	}
	/**
	 * 屏幕
	 */
	public void czpinmu(){
		errorMessage =	waserver.Congzhipinmu(id);
		if("true".equals(errorMessage))MKUtil.writeJSON(true, "发送成功", null);
		else MKUtil.writeJSON(false, errorMessage, null);
	}
	/**
	 * 库位码
	 */
	public void sendKuWei(){
		errorMessage =	waserver.sendKuWei(id);
		if("true".equals(errorMessage))MKUtil.writeJSON(true, "发送成功", null);
		else MKUtil.writeJSON(false, errorMessage, null);
	}
	/**
	 * 主页
	 */
	public void sendZhuYe(){
		errorMessage =	waserver.sendZhuYe(id);
		if("true".equals(errorMessage))MKUtil.writeJSON(true, "发送成功", null);
		else MKUtil.writeJSON(false, errorMessage, null);
	}
	/**
	 * 闪灯
	 */
	public void shansuo(){
		errorMessage =	waserver.shansuo(id);
		if("true".equals(errorMessage))MKUtil.writeJSON(true, "发送成功", null);
		else MKUtil.writeJSON(false, errorMessage, null);
	}
	
	
	
	/**
	 *根据仓库名和仓区名 查询仓区对象及面积
	 * @return
	 */
	public void ajaxSelectCangQuArea(){
		try{
			if(wareHouseName!=null && cangqu!=null){
				String message=waserver.cangquIsExistArea(wareHouseName, cangqu);
				MKUtil.writeJSON(message);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public WarehouseNumber getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(WarehouseNumber warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}

	public GoodHouse getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(GoodHouse warehouseArea) {
		this.warehouseArea = warehouseArea;
	}

	public List<GoodHouse> getWaList() {
		return waList;
	}

	public void setWaList(List<GoodHouse> waList) {
		this.waList = waList;
	}

	public List<WarehouseNumber> getWnList() {
		return wnList;
	}

	public void setWnList(List<WarehouseNumber> wnList) {
		this.wnList = wnList;
	}

	public WarehouseAreaServerDao getWaserver() {
		return waserver;
	}

	public void setWaserver(WarehouseAreaServerDao waserver) {
		this.waserver = waserver;
	}

	public String getCangqu() {
		return cangqu;
	}

	public void setCangqu(String cangqu) {
		this.cangqu = cangqu;
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

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getWareHouseNO() {
		return wareHouseNO;
	}

	public void setWareHouseNO(String wareHouseNO) {
		this.wareHouseNO = wareHouseNO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public File getAddwarehouseNumber() {
		return addwarehouseNumber;
	}

	public void setAddwarehouseNumber(File addwarehouseNumber) {
		this.addwarehouseNumber = addwarehouseNumber;
	}

	public List<AccessEquipment> getIpList() {
		return ipList;
	}

	public void setIpList(List<AccessEquipment> ipList) {
		this.ipList = ipList;
	}

	public List<AccessEquipment> getFourlightIpList() {
		return fourlightIpList;
	}

	public void setFourlightIpList(List<AccessEquipment> fourlightIpList) {
		this.fourlightIpList = fourlightIpList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
