package com.task.Server;

import java.util.Map;

import com.task.entity.UniversalCategory;
import com.task.entity.UniversalType;

public interface UniversalServer{
	
	String addCategory(UniversalCategory category,String pageStatus);
	
	String addType(UniversalType type,String pageStatus);
	
	Map<String, Object > findCategoryByCon(UniversalCategory category,Integer pageNo,Integer pageSize,String pageStatus);
	
	Map<String, Object> findTypeByCon(UniversalType type,Integer pageNo,Integer pageSize,String pageStatus);
	
	String deleteTypeById(Integer id);
	
	String deleteCategoryById(Integer id,String pageStatus);

	UniversalCategory getCategoryById(Integer id);
	
	UniversalType getTypeById(Integer id);
	//UniversalCategory getCategoryById();

	UniversalCategory getcategoryByType(String type);
	
}
