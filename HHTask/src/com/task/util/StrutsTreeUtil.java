package com.task.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrutsTreeUtil {
	/**
	 * 计算树对象层数、结构的方法，并组成全名
	 * 
	 * @param treelist
	 *            树结构列表
	 * @return 带树对象层数的列表
	 */
	private static List<StrutsTreeNode> compLayer(List<StrutsTreeNode> treelist) {
		if (treelist != null) {
			List<StrutsTreeNode> rtreelist = new ArrayList<StrutsTreeNode>();
			// 把树对象放入HashMap映射表中
			Map<Integer, StrutsTreeNode> map = new HashMap<Integer, StrutsTreeNode>(
					treelist.size());
			for (int i = 0; i < treelist.size(); i++) {
				StrutsTreeNode treenode = treelist.get(i);
				map.put(Integer.valueOf((treenode).getId()), treenode);
			}

			// 计算层数
			for (int i = 0; i < treelist.size(); i++) {
				StrutsTreeNode treenode = treelist.get(i);
				treenode.setNameAll(treenode.getName());
				StrutsTreeNode node = map
						.get(Integer.valueOf(treenode.getId()));
				int layer = 1; // 第一层
				while (node != null && node.getParentId() >= 0) {
					node = map.get(Integer.valueOf(node.getParentId()));
					if (node != null) {
						treenode.setNameAll(node.getName()
								+ StrutsTreeNode.link + treenode.getNameAll());
						layer++;
						// 在父结点中加入子结点
						if (layer == 2) {
							node.addChildren(treenode);
						}
					} else {
						break;
					}
				}
				// 设置层数
				treenode.setLayers(layer);

				// 放置对象回List
				rtreelist.add(treenode);
			}
			return rtreelist;
		}

		return treelist;
	}

	/**
	 * 得到除了结点ID及其子结点的其它所有结点
	 * 
	 * @param treelist
	 *            树结构列表
	 * @param id
	 * @return 带树对象层数的列表
	 */
	private static List<StrutsTreeNode> getParents(
			List<StrutsTreeNode> treelist, int id) {
		if (treelist != null) {
			List<StrutsTreeNode> rtreelist = new ArrayList<StrutsTreeNode>();
			// 把树对象放入HashMap映射表中
			Map<Integer, StrutsTreeNode> map = new HashMap<Integer, StrutsTreeNode>(
					treelist.size());
			for (int i = 0; i < treelist.size(); i++) {
				StrutsTreeNode treenode = treelist.get(i);
				map.put(Integer.valueOf((treenode).getId()), treenode);
			}

			// 计算父结点及其上结点，如果等于id，则不加入结果列表中
			for (int i = 0; i < treelist.size(); i++) {
				StrutsTreeNode treenode = treelist.get(i);

				// 是id结点，跳过
				if (treenode.getId() == id) {
					continue;
				}

				StrutsTreeNode node = treenode;
				boolean isChild = false;
				while (node != null && node.getId() > 0 && !isChild) {
					if (node.getId() == id) {
						isChild = true;
					} else {
						node = map.get(Integer.valueOf(node.getParentId()));
					}
				}

				if (!isChild) {
					// 放置对象回List
					rtreelist.add(treenode);
				}

			}
			return rtreelist;
		}

		return treelist;
	}

	/**
	 * 把给定的数组按树形结构的排序，
	 * 
	 * @param list
	 *            需排序的数组,元素为TreeNode
	 * @return List 排序后的数组
	 */
	private static List<StrutsTreeNode> sortListToTree(List<StrutsTreeNode> list) {
		if (list == null) {
			return null;
		}
		try {

			StrutsTreeNode tn1 = null, tn2 = null;

			int count = list.size();
			int i = 0, j = 0, k = 0;

			boolean bl = false;
			if (count > 0) {
				for (i = 0; i < count; i++) {
					bl = false;
					for (j = i + 1; j < count; j++) {
						tn1 = list.get(i);
						tn2 = list.get(j);
						if (tn1.getParentId() == tn2.getId()) {
							tn1.setParent(tn2);
							list.set(i, tn2);
							list.set(j, tn1);
							bl = true;
						}
					}
					if (bl) {
						i--;
					}
				} // for i count

				for (i = 0; i < count; i++) {
					k = i;
					for (j = i + 1; j < count; j++) {
						tn1 = list.get(i);
						tn2 = list.get(j);
						if (tn2.getParentId() == tn1.getId()) {
							tn2.setParent(tn1);
							list.remove(j);
							list.add(k + 1, tn2);
							k++;
						} // if
					} // for j count
				} // for i count
			} // if count > 0
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return list;
	}

	/**
	 * 排序得到树结构列表
	 * 
	 * @param list
	 * @return list
	 */
	public static List<StrutsTreeNode> getTreeList(
			List<StrutsTreeITreeNode> list) {
		List<StrutsTreeNode> slist = toTreeList(list);

		return compLayer(sortListToTree(slist));
	}

	/**
	 * 转换ITreeNode结点数组为StrutsTreeNode结点数组
	 * 
	 * @param list
	 * @return
	 */
	private static List<StrutsTreeNode> toTreeList(
			List<StrutsTreeITreeNode> list) {
		List<StrutsTreeNode> slist = new ArrayList<StrutsTreeNode>();
		for (int i = 0; i < list.size(); i++) {
			StrutsTreeITreeNode iTreeNode = list.get(i);
			StrutsTreeNode treeNode = iTreeNode.toTreeNode();
			slist.add(treeNode);
		}
		return slist;
	}

	/**
	 * 排序得到树根结点列表
	 * 
	 * @param list
	 * @return list
	 */
	public static StrutsTreeNode getTreeRoot(List<StrutsTreeITreeNode> list) {
		if (list == null) {
			return null;
		}
		List<StrutsTreeNode> slist = toTreeList(list);
		StrutsTreeNode root = new StrutsTreeNode();
		root.setId(0);
		root.setParentId(-1);
		root.setName("");
		slist.add(0, root);
		List<StrutsTreeNode> treelist = compLayer(sortListToTree(slist));
		return treelist.get(0);
	}

	/**
	 * 得到除了结点ID及其子结点的其它所有结点
	 * 
	 * @param list
	 *            结构列表
	 * @param id
	 * @return 带树对象层数的列表
	 */
	public static List<StrutsTreeNode> getTreeList(
			List<StrutsTreeITreeNode> list, int id) {
		// 排序
		List<StrutsTreeNode> treelist = getTreeList(list);

		return getParents(treelist, id);
	}
}
