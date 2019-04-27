package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.WareHouseService;
import com.task.entity.WareHouse;
import com.task.util.MKUtil;

/**
 * 仓库Action
 * @author 马凯
 *
 */
public class WareHouseAction extends ActionSupport {
	private List<WareHouse> wareHouses;
	private WareHouseService wareHouseService;
	private WareHouse house;
	
	
	public String addInput(){
		return "addInput";
	}
	
	public String add(){
		try {
			wareHouseService.add(house);
			MKUtil.writeJSON(true, "添加成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String updateInput(){
		return "updateInput";
	}
	
	public String list(){
		wareHouses = wareHouseService.getAll();
		return "list";
	}
	
	public String delete(){
		try {
			wareHouseService.delete(house);
			MKUtil.writeJSON(true, "删除成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "删除失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	/**
	 * 返回JSON数组
	 * @return
	 */
	public String select(){
		wareHouses = wareHouseService.getAll();
		MKUtil.writeJSON(wareHouses);
		return null;
	}
	
	public String testData(){
		WareHouse w = new WareHouse();
		w.setId(1);
		w.setName("原材料库");
		w.setCode("yclk");

		
		WareHouse w1 = new WareHouse();
		w1.setId(2);
		w1.setName("外购件库");
		w1.setCode("wgjk");

		WareHouse w2 = new WareHouse();
		w2.setId(3);
		w2.setName("中间库");
		w2.setCode("zjk");
		
		WareHouse w3 = new WareHouse();
		w3.setId(4);
		w3.setName("成品库");
		w3.setCode("cpk");
		
		WareHouse w5 = new WareHouse();
		w5.setId(6);
		w5.setName("珠海红湖");
		w5.setCode("zhhh");
		
		wareHouseService.add(w);
		wareHouseService.add(w1);
		wareHouseService.add(w2);
		wareHouseService.add(w3);
		wareHouseService.add(w5);
		
		return null;
	}

	public List<WareHouse> getWareHouses() {
		return wareHouses;
	}

	public void setWareHouses(List<WareHouse> wareHouses) {
		this.wareHouses = wareHouses;
	}

	public WareHouseService getWareHouseService() {
		return wareHouseService;
	}

	public void setWareHouseService(WareHouseService wareHouseService) {
		this.wareHouseService = wareHouseService;
	}

	public WareHouse getHouse() {
		return house;
	}

	public void setHouse(WareHouse house) {
		this.house = house;
	}



}
