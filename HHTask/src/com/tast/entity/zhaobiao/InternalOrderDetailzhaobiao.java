package com.tast.entity.zhaobiao;

import java.io.Serializable;

/***
 * 内部计划详细(表名:ta_crm_internalOrderDetailzhaobiao)
 * 
 * @author 刘培
 * 
 */
public class InternalOrderDetailzhaobiao implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String pieceNumber;// 件号
	private Float num;// 计划数量
	private Float quantityCompletion;// 完成数量
	private Float turnCount;// 已转数量
	private String remark;
	private InternalOrderzhaobiao internalOrderzhaobiao;

	public InternalOrderDetailzhaobiao() {
		super();
	}

	public InternalOrderDetailzhaobiao(String name, String pieceNumber, Float num,
			Float turnCount, String remark, Float quantityCompletion) {
		super();
		this.name = name;
		this.pieceNumber = pieceNumber;
		this.num = num;
		this.turnCount = turnCount;
		this.remark = remark;
		this.quantityCompletion = quantityCompletion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(String pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	public InternalOrderzhaobiao getInternalOrderzhaobiao() {
		return internalOrderzhaobiao;
	}

	public void setInternalOrderzhaobiao(InternalOrderzhaobiao internalOrderzhaobiao) {
		this.internalOrderzhaobiao = internalOrderzhaobiao;
	}

	public Float getQuantityCompletion() {
		return quantityCompletion;
	}

	public void setQuantityCompletion(Float quantityCompletion) {
		this.quantityCompletion = quantityCompletion;
	}

	public Float getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(Float turnCount) {
		this.turnCount = turnCount;
	}

}