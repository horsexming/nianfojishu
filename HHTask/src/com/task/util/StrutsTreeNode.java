package com.task.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Struts2树形结点类
 * 
 * @author 刘培
 * @since 2012-05-24
 * @version 2.0
 */
public class StrutsTreeNode implements StrutsTreeITreeNode {

	public static final String link = "--";// 父子结点之间的连接符
	private int id;// 本节点ID
	private int parentId;// 父节点ID,为0表示第一层结点
	private StrutsTreeNode parent;// 父节点,为null表示第一层结点
	private String name;// 结点名
	private String nameAll;// 结点全名
	private String iconFile;// 结点使用图片文件名,为空用缺省的
	private String url;// 结点点击对应的URL
	private String target;// 结点点击对应的target
	private int layers = 0;// 结点所在层数
	private boolean bckeck = false;// 是否需要check
	private int checkValue = 0; // 是否check,0-否,1-是
	private int fixup = 0;// 是否固定选择项,0-否,1-是
	private String data; // 数据
	private List<StrutsTreeNode> childrens = new ArrayList<StrutsTreeNode>();

	public boolean isBckeck() {
		return bckeck;
	}

	public void setBckeck(boolean bckeck) {
		this.bckeck = bckeck;
	}

	public int getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(int checkValue) {
		this.checkValue = checkValue;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getFixup() {
		return fixup;
	}

	public void setFixup(int fixup) {
		this.fixup = fixup;
	}

	public String getIconFile() {
		return iconFile;
	}

	public void setIconFile(String iconFile) {
		this.iconFile = iconFile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLayers() {
		return layers;
	}

	public void setLayers(int layers) {
		this.layers = layers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StrutsTreeNode getParent() {
		return parent;
	}

	public void setParent(StrutsTreeNode parent) {
		this.parent = parent;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<StrutsTreeNode> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<StrutsTreeNode> childrens) {
		this.childrens = childrens;
	}

	public void addChildren(StrutsTreeNode node) {
		this.childrens.add(node);
	}

	public StrutsTreeNode toTreeNode() {
		// TODO Auto-generated method stub
		return this;
	}
}
