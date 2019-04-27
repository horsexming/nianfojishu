package com.task.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.CategoryServer;
import com.task.entity.Category;
import com.task.entity.WarehouseNumber;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.util.MKUtil;

public class CategoryAction {
	private CategoryServer cyServer;
	private Category category;
	private List<Category> cylist;
	private String type;
	private Integer id;
	private Integer cateId;// 页面传值

	private String errorMessage;
	private String successMessage;
	private int pageSize = 15;
	private String cpage = "1";
	private String total;
	private String url;
	private String status = "";
	private String tag = "";

	public String addcategory() {
		errorMessage = cyServer.add(cylist, type);
		if ("true".equals(errorMessage)) {
			errorMessage = "添加成功";
		}
		return "category_add";
	}

	public String add() {
		category.setFatherId(id);
		errorMessage = cyServer.add(category, tag);
		if ("true".equals(errorMessage)) {
			errorMessage = "添加成功";
		}
		return "category_update";
	}

	public String findAllcylist() {
		if (category != null) {
			ActionContext.getContext().getSession().put("category", category);
		} else {
			category = (Category) ActionContext.getContext().getSession().get(
					"category");
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map = cyServer.findByCategoryCondition(category, Integer
				.parseInt(cpage), pageSize);
		cylist = (List<Category>) map.get(1);
		if (cylist != null && cylist.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CategoryAction_findAllcylist.action");
			return "category_List";
		}
		errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "error";
	}

	public String findcategoryById() {
		if (id != null && id > 0) {
			category = cyServer.getcategoryById(id);
		}
		return "category_update";
	}

	public String updatecategory() {
		category.setId(id);
		errorMessage = cyServer.update(category, status);
		if ("true".equals(errorMessage)) {
			errorMessage = "修改成功";
		}
		return "category_update";
	}

	public String delcategory() {
		errorMessage = cyServer.isdelCategory(category);
		if (errorMessage == null || errorMessage.length() == 0) {
			errorMessage = cyServer.delCategory(category);
			category.setFatherId(id);
			if ("true".equals(errorMessage)) {
				errorMessage = "删除成功!";
				return "category_update";
			}
		}
		return "category_update";
	}

	public void findcylist() {
		cylist = cyServer.findCategoryByType(type);
		try {
			MKUtil.writeJSON(cylist);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(e);
		}
	}

	public void findcylist1() {
		// cyServer.xiugaiUserCateByUser(id);
		cylist = cyServer.findCategoryByType(type, id);
		try {
			MKUtil.writeJSON(cylist);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(e);
		}
	}

	/**
	 * 添加第一个大类，物料类别
	 */
	public void addfirst() {
		category = cyServer.addfrist(status);
		try {
			if (category != null) {
				MKUtil.writeJSON(true, "true", category.getId());
			} else {
				MKUtil.writeJSON(false, "error", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据rootId查找所有的物料类别;
	 */
	public void findcyListByrootId() {
		cylist = cyServer.findCategoryByrootId(id, status, tag);
		if (cylist == null || cylist.size() == 0) {
			cyServer.addfrist(status);
			cylist = cyServer.findCategoryByrootId(id, status, tag);
		}
		try {
			MKUtil.writeJSON(cylist);
		} catch (Exception e) {
		}
	}

	public String updateUserCate() {
		boolean b = cyServer.updateUserCate(id, cateId);
		MKUtil.writeJSON(b);
		return null;
	}

	public String test() {
		errorMessage = cyServer.test(id);
		return "error";
	}

	// 判断是否为最低层
	public void islow() {
		boolean bool = cyServer.islow(id);
		MKUtil.writeJSON(bool);
	}

	public String test0() {
		try {
			cyServer.yclround();
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("---又------------出-----------------bug--------------了。"
							+ e);
		}

		System.out
				.println(" ------------------------总--------------算-------------------运-------------------行-------------------------完---------------------------------了。");
		return "error";
	}

	public CategoryServer getCyServer() {
		return cyServer;
	}

	public void setCyServer(CategoryServer cyServer) {
		this.cyServer = cyServer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCylist() {
		return cylist;
	}

	public void setCylist(List<Category> cylist) {
		this.cylist = cylist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
