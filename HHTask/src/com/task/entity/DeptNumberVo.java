package com.task.entity;

import java.io.Serializable;
import java.util.Set;

import com.task.entity.fin.budget.SubBudgetRate;
import com.task.util.StrutsTreeITreeNode;
import com.task.util.StrutsTreeNode;

/**
 * 部门Vo用来ajax显示时判断用户是否用过该部门权限
 * 
 * @author txb
 */
public class DeptNumberVo  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dept;// 名称
	private String deptNumber;// 编号
	private Integer fatherId;// 父类Id
	private Integer belongLayer;// 当前层
    private Boolean isHad;//是否拥有该部门权限

	// 转换为节点类
	public StrutsTreeNode toTreeNode() {
		StrutsTreeNode treenode = new StrutsTreeNode();
		treenode.setId(getId());// 对象id
		treenode.setParentId(getFatherId());// 父类id
		treenode.setName(getDept());// 对象名称
		treenode.setLayers(getBelongLayer());// 对象所在层
		return treenode;
	}
    public void SetDeptContext(DeptNumber deptNumber){
    	if(deptNumber!=null){
    		this.id=deptNumber.getId();
    		this.fatherId=deptNumber.getFatherId();
    		this.dept=deptNumber.getDept();
    		this.belongLayer=deptNumber.getBelongLayer();
    		this.deptNumber=deptNumber.getDeptNumber();
    	}
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public Integer getBelongLayer() {
		return belongLayer;
	}

	public void setBelongLayer(Integer belongLayer) {
		this.belongLayer = belongLayer;
	}
	public Boolean getIsHad() {
		return isHad;
	}
	public void setIsHad(Boolean isHad) {
		this.isHad = isHad;
	}


}