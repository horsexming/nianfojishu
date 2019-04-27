package com.task.ServerImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.UniversalServer;
import com.task.entity.UniversalCategory;
import com.task.entity.UniversalType;

public class UniversalServerImpl implements UniversalServer {
	private TotalDao totalDao;
	

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String addCategory(UniversalCategory category, String pageStatus) {
		if(category!=null && category.getCategoryName()!=null && !category.getCategoryName().equals("")) {
			
			boolean save=false;
			if(category.getId()!=null) {
				save = totalDao.update(category);
			}else {
				String categoryName = category.getCategoryName();
				Integer count = totalDao.getCount("from UniversalCategory where categoryName=?", categoryName);
				if(count>0) {
					return "类别名称重复，不能重复录入";
				}
				save = totalDao.save(category);
			}
			
			if(save) {
				return  "保存成功";
			}else {
				return  "保存失败";
			}
			
			
		}
		
		return "请类别名称";
	}

	@Override
	public String addType(UniversalType type, String pageStatus) {
		if(type!=null && type.getCategory()!=null && (type.getCategory().getId()!=null || type.getCategory().getType()!=null) ) {
			String name = type.getName();
			if(name==null || name.equals("")) {
				return "名称不能为空";
			}
			
			boolean save=false;
			if(type.getId()!=null) {
				save  = totalDao.update(type);
			}
			save = totalDao.save(type);
			if(save) {
				return  "保存成功";
			}else {
				return  "保存失败";
			}
		}else {
			return "请选择类别";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findCategoryByCon(UniversalCategory category, Integer pageNo, Integer pageSize,
			String pageStatus) {
		if (category==null) {
			category = new UniversalCategory();
		}
		
		String hql = totalDao.criteriaQueries(category, null);
		List<UniversalCategory> list = totalDao.findAllByPage(hql, pageNo, pageSize, null);
		if(list!=null && list.size()>0) {
			Integer count = totalDao.getCount(hql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("count", count);
			return map;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findTypeByCon(UniversalType type, Integer pageNo, Integer pageSize, String pageStatus) {
		if (type==null) {
			type = new UniversalType();
		}
		String categoryHql = "";
		if(type.getCategory()!=null) {
			UniversalCategory category = type.getCategory();
			type.setCategory(null);
			if(category.getId()!=null) {
				String criteriaQueries = totalDao.criteriaQueries(category, null);
				List<UniversalCategory> categoryList= totalDao.query(criteriaQueries);
				if(categoryList!=null && categoryList.size()>0) {
					for (UniversalCategory universalCategory : categoryList) {
						categoryHql +=" and category.id ="+universalCategory.getId();
					}
				}
			}
			if((pageStatus==null || !pageStatus.equals("all")) && category.getType()!=null) {
				categoryHql +=" and category.type = '"+category.getType()+"'";
			}
		}
		String hql = totalDao.criteriaQueries(type, null)+categoryHql;
		List<UniversalType> list = totalDao.findAllByPage(hql, pageNo, pageSize, null);
		if(list!=null && list.size()>0) {
			for (UniversalType universalType : list) {
				System.out.println(universalType.getCategory());//不要删除
			}
			Integer count = totalDao.getCount(hql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("count", count);
			return map;
		}
		return null;
	}

	@Override
	public String deleteTypeById(Integer id) {
		if(id!=null) {
			UniversalType type = (UniversalType) totalDao.getObjectById(UniversalType.class, id);
			if(type!=null) {
				boolean delete = totalDao.delete(type);
				if(delete) {
					return "删除成功";
				}else {
					return "删除失败";
				}
			}
		}
		
		return "系统异常，Id为空或对象不存在";
	}

	@Override
	public String deleteCategoryById(Integer id, String pageStatus) {
		if(id!=null) {
			UniversalCategory category = (UniversalCategory) totalDao.getObjectById(UniversalCategory.class, id);
			if(category!=null) {
				
				Set<UniversalType> typeSet = category.getTypeSet();
				if(typeSet!=null && typeSet.size()>0) {
					return "请先删除"+category.getCategoryName()+"类的类型";
				}
				
				boolean delete = totalDao.delete(category);
				if(delete) {
					return "删除成功";
				}else {
					return "删除失败";
				}
			}
		}
		
		return "系统异常，Id为空或对象不存在";
	}

	@Override
	public UniversalCategory getCategoryById(Integer id) {
		if(id==null) {
			return null;
		}
		UniversalCategory category = (UniversalCategory) totalDao.getObjectById(UniversalCategory.class, id);
		return category;
	}

	@Override
	public UniversalType getTypeById(Integer id) {
		if(id==null) {
			return null;
		}
		UniversalType type = (UniversalType) totalDao.getObjectById(UniversalType.class, id);
		if(type!=null) {
			System.out.println(type.getCategory());
		}
		return type;
	}

	@Override
	public UniversalCategory getcategoryByType(String type) {
		
		return (UniversalCategory) totalDao.getObjectByCondition("from UniversalCategory where type=? ",type);
	}

	/**
	 * 静态方法获取所有类型
	 * @param type 类型编码
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public static List<UniversalType> findTypeByCategoryType(String type){
		if(type!=null && type.length()>0) {
			TotalDao totalDao = TotalDaoImpl.findTotalDao();
			List<UniversalType> list = totalDao.query("from UniversalType where category.type=?", type);
			return list;
		}
		
		return null;
	}
	
	
}
