package com.task.entity.sop;
/**
 * 工序模板的辅料（ta_sop_w_ProcessFuLiaoTemplate）
 * @author txb
 *
 */
public class ProcessFuLiaoTemplate  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
 private Integer id;
 private Float quanzhi1;//权值1代表工序
 private Float quanzhi2;//权值2代表辅料
 private String name;//名称
 private String type;// 类别
 private String specification;// 规格
 private String unit;//单位
 private ProcessTemplate processTemplate;//工序
 
 public ProcessFuLiaoTemplate() {
	super();
	// TODO Auto-generated constructor stub
}
public ProcessFuLiaoTemplate(ProcessGzstoreFuLiao pfuliao1){
	 quanzhi1=pfuliao1.getQuanzhi1();//权值1代表工序
	 quanzhi2=pfuliao1.getQuanzhi2();//权值2代表辅料
	 name=pfuliao1.getName();//名称
	 type=pfuliao1.getType();// 类别(件号)
	 specification=pfuliao1.getSpecification();// 规格
	 unit=pfuliao1.getUnit();//单位
 }
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Float getQuanzhi1() {
	return quanzhi1;
}
public void setQuanzhi1(Float quanzhi1) {
	this.quanzhi1 = quanzhi1;
}
public Float getQuanzhi2() {
	return quanzhi2;
}
public void setQuanzhi2(Float quanzhi2) {
	this.quanzhi2 = quanzhi2;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getSpecification() {
	return specification;
}
public void setSpecification(String specification) {
	this.specification = specification;
}
public ProcessTemplate getProcessTemplate() {
	return processTemplate;
}
public void setProcessTemplate(ProcessTemplate processTemplate) {
	this.processTemplate = processTemplate;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
 
 
}
