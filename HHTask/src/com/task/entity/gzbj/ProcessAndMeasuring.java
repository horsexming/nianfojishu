package com.task.entity.gzbj;

import java.io.Serializable;

/**
 * 
 * @author 王晓飞
 *工序库与量、检具中间表 :(ta_ProcessAndMeasuring)
 */
public class ProcessAndMeasuring implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer processId;//工序库Id
	private Integer measuringId;//量、检具Id
    private String 	processName;// 工序名
    private String 	measNumber;//量具编号
    private String 	matetag;//量具名称
    private String 	measuring_no;//本厂编号
    private Measuring measuring;//量、检具对象;
    
    public ProcessAndMeasuring() {
		super();
	}
	public ProcessAndMeasuring(Integer processId, Integer measuringId,
			String processName, String measNumber, String matetag,
			String measuringNo) {
		super();
		this.processId = processId;
		this.measuringId = measuringId;
		this.processName = processName;
		this.measNumber = measNumber;
		this.matetag = matetag;
		this.measuring_no = measuringNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public Integer getMeasuringId() {
		return measuringId;
	}
	public void setMeasuringId(Integer measuringId) {
		this.measuringId = measuringId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getMeasNumber() {
		return measNumber;
	}
	public void setMeasNumber(String measNumber) {
		this.measNumber = measNumber;
	}
	public String getMatetag() {
		return matetag;
	}
	public void setMatetag(String matetag) {
		this.matetag = matetag;
	}
	public String getMeasuring_no() {
		return measuring_no;
	}
	public void setMeasuring_no(String measuringNo) {
		measuring_no = measuringNo;
	}
	public Measuring getMeasuring() {
		return measuring;
	}
	public void setMeasuring(Measuring measuring) {
		this.measuring = measuring;
	}
    
    
    
    
    }
