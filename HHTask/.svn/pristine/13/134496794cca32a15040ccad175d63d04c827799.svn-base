package com.task.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.UniversalServer;
import com.task.entity.UniversalCategory;
import com.task.entity.UniversalType;
import com.task.util.MKUtil;

public class UniversalAction {

	
	private String cpage = "1";
	private String errorMessage;
	private Integer pageSize=15;
	private String total;
	private String url;
	private String pageStatus;
	private String tag;
	private String categoryName;
	private Integer id;
	private String code;
	
	
	private UniversalCategory category;
	private List<UniversalCategory> categoryList;
	
	private UniversalType type;
	private List<UniversalType> typeList;
	private UniversalServer universalServer;
	
	public String toAddType() {
		if(category!=null && category.getType()!=null && !category.getType().equals("")) {
			category = universalServer.getcategoryByType(category.getType());
			categoryList = new ArrayList<UniversalCategory>();
			categoryList.add(category);
		}else {
			//获得所有的类别
			if(pageStatus!=null && pageStatus.equals("all")) {
				Map<String, Object> categoryMap = universalServer.findCategoryByCon(null, 0, 0, pageStatus);
				if(categoryMap!=null) {
					categoryList = (List<UniversalCategory>) categoryMap.get("list");
				}
			}
		}
		return "universal_addType";
	}
	public String addType() {
		errorMessage = universalServer.addType(type, pageStatus);
		
		setUrl("universalAction!showTypeList.action?pageStatus="+pageStatus
				+"&cpage="+cpage+"&tag="+tag+"&categoryName="+categoryName+"&category.type="+category.getType());
		return "error";
	}
	
	public String showTypeList() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(type!=null) {
			session.put("showTypeList", type);
		}else {
			type = (UniversalType) session.get("showTypeList");
		}
		if(type!=null) {
			if(type.getCategory()!=null) {
				session.put("typeCategory",type.getCategory());
			}else {
				type.setCategory((UniversalCategory) session.get("typeCategory"));
				
			}
		}
		if(category!=null) {
			session.put("category", category);
		}else {
			category = (UniversalCategory) session.get("category");
		}
		
		//获得所有的类别
		if(pageStatus!=null && pageStatus.equals("all")) {
			Map<String, Object> categoryMap = universalServer.findCategoryByCon(null, 0, 0, pageStatus);
			if(categoryMap!=null) {
				categoryList = (List<UniversalCategory>) categoryMap.get("list");
			}
		}else {
			if(category!=null && category.getType()!=null) {
				if(type==null) {
					type = new UniversalType();
				}
				category = universalServer.getcategoryByType(category.getType());
				type.setCategory(category);
				categoryList = new ArrayList<UniversalCategory>();
				categoryList.add(category);
			}
		}
		
		Map<String, Object> map = universalServer.findTypeByCon(type, Integer.parseInt(cpage), pageSize, pageStatus);
		if(map!=null) {
			typeList = (List<UniversalType>) map.get("list");
			Integer count = (Integer) map.get("count");
			setUrl("universalAction!showTypeList.action?pageStatus="+pageStatus
						+"&tag="+tag+"&categoryName="+categoryName);
			Integer pageCount=(count + pageSize - 1) / pageSize;
			setTotal(pageCount+"");
		}
		return "universal_showTypeList";
	}
	
	public String addCategory() {
		errorMessage = universalServer.addCategory(category, pageStatus);
		
		setUrl("universalAction!showCategoryList.action?pageStatus="+pageStatus
				+"&cpage="+cpage+"&tag="+tag);
		return "error";
	}
	
	public String showCategoryList() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(category!=null) {
			session.put("showCategoryList", category);
		}else {
			category = (UniversalCategory) session.get("showCategoryList");
		}
		
		Map<String, Object> map = universalServer.findCategoryByCon(category, Integer.parseInt(cpage), pageSize, pageStatus);
		if(map!=null) {
			categoryList = (List<UniversalCategory>) map.get("list");
			Integer count = (Integer) map.get("count");
			setUrl("universalAction!showCategoryList.action?pageStatus="+pageStatus
					+"&tag="+tag);
			Integer pageCount=(count + pageSize - 1) / pageSize;
			setTotal(pageCount+"");
		}
		return "universal_showCategoryList";
	}
	
	
	public void deleteCategory() {
		errorMessage = universalServer.deleteCategoryById(id, pageStatus);
		MKUtil.writeJSON(errorMessage);
	}
	
	public void deleteType() {
		errorMessage = universalServer.deleteTypeById(id);
		MKUtil.writeJSON(errorMessage);
	}
	
	public String getCategoryById() {
		category = universalServer.getCategoryById(id);
		if(pageStatus!=null && pageStatus.equals("update")) {
			return "universal_addCategoryList";
		}else {
			MKUtil.writeJSON(category);
		}
		return null;
	}
	
	public String getTypeById() {
		type = universalServer.getTypeById(id);
		if(tag!=null && tag.equals("update")) {
			categoryList = new ArrayList<UniversalCategory>();
			if(category!=null && category.getType()!=null && !category.getType().equals("")) {
				category = universalServer.getcategoryByType(category.getType());
				categoryList.add(category);
			}else {
				//获得所有的类别
//				if(pageStatus!=null && pageStatus.equals("all")) {
//					Map<String, Object> categoryMap = universalServer.findCategoryByCon(null, 0, 0, pageStatus);
//					if(categoryMap!=null) {
//						categoryList = (List<UniversalCategory>) categoryMap.get("list");
//					}
//				}
				categoryList.add(type.getCategory());
			}
			return "universal_addType";
		}else {
			MKUtil.writeJSON(type);
		}
		return null;
	}
	
	public String getAllCategory() {
		Map<String, Object> categoryMap = universalServer.findCategoryByCon(null, 0, 0, pageStatus);
		if(categoryMap!=null) {
			categoryList = (List<UniversalCategory>) categoryMap.get("list");
		}
		if(categoryList!=null) {
			for (UniversalCategory universalCategory : categoryList) {
				universalCategory.setTypeSet(null);
			}
		}
		if(pageStatus!=null && pageStatus.equals("ajax")) {
			MKUtil.writeJSON(categoryList);
			return null;
		}else {
			return "";
		}
	}
	
	
	/**
	 * ajax 根据类别编码获得所有类型
	 * @return
	 */
	public void findCategoryByType() {
		type = new UniversalType();
		category = new UniversalCategory();
		category.setType(code);
		type.setCategory(category);
		Map<String, Object> map = universalServer.findTypeByCon(type, 0, 0, pageStatus);
		if(map!=null) {
			typeList = (List<UniversalType>) map.get("list");
			for (UniversalType ut : typeList) {
				ut.setCategory(null);
			}
			MKUtil.writeJSON(typeList);
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public UniversalCategory getCategory() {
		return category;
	}
	public void setCategory(UniversalCategory category) {
		this.category = category;
	}
	
	public List<UniversalCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<UniversalCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public UniversalType getType() {
		return type;
	}
	public void setType(UniversalType type) {
		this.type = type;
	}
	public List<UniversalType> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<UniversalType> typeList) {
		this.typeList = typeList;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public UniversalServer getUniversalServer() {
		return universalServer;
	}

	public void setUniversalServer(UniversalServer universalServer) {
		this.universalServer = universalServer;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
