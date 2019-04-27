package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.TequipmentService;
import com.task.entity.Tequipment;
import com.task.util.MKUtil;

/**
 * 项目管理_设备
 * @author 马凯
 *
 */
public class TequipmentAction extends ActionSupport {
	private Tequipment equipment;
	private List<Tequipment> equipments;
	private TequipmentService tequipmentService;
	
	public String addInput(){
		return "addInput";
	}
	
	public String add(){
		tequipmentService.addAll(equipments);
		return "addOk";
	}
	
	public String selector(){
		MKUtil.writeJSON(tequipmentService.selector(equipment));
		return null;
	}

	public Tequipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Tequipment equipment) {
		this.equipment = equipment;
	}

	public List<Tequipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Tequipment> equipments) {
		this.equipments = equipments;
	}

	public TequipmentService getTequipmentService() {
		return tequipmentService;
	}

	public void setTequipmentService(TequipmentService tequipmentService) {
		this.tequipmentService = tequipmentService;
	}

}
