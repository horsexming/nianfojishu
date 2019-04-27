package com.task.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeData {
	public static Map<Integer, TreeData> catMap = new HashMap<Integer, TreeData>();

	public static TreeData getById(int id) {
		return catMap.get(id);
	}

	private int id;
	private String name;
	private List<TreeData> children;
	private boolean toggle;

	public TreeData(int id, String name, TreeData... children) {
		this.id = id;
		this.name = name;
		this.children = new ArrayList<TreeData>();
		for (TreeData child : children) {
			this.children.add(child);
		}
		catMap.put(id, this);
	}

	public TreeData() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeData> getChildren() {
		return children;
	}

	public void setChildren(List<TreeData> children) {
		this.children = children;
	}

	public void toggle() {
		toggle = !toggle;
	}

	public boolean isToggle() {
		return toggle;
	}
}
