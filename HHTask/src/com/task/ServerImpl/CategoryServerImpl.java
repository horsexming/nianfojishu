package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.CategoryServer;
import com.task.entity.Category;
import com.task.entity.GoodHouse;
import com.task.entity.Users;
import com.task.entity.sop.ycl.YuanclAndWaigj;

public class CategoryServerImpl implements CategoryServer {
	private StringBuffer strbu = new StringBuffer();
	private String str = "";
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String add(List<Category> cyList, String type) {
		StringBuffer strb = new StringBuffer("");
		if (cyList != null && cyList.size() > 0 && type != null
				&& !"".equals(type)) {
			for (Category category : cyList) {
				String hql = "from Category where name=? and type=?";
				String hql_count = " from Category ";
				Integer count = totalDao.getCount(hql_count);
				Category cy = null;
				if (count == 0) {
					cy = new Category();
					cy.setName("物料类别");
					cy.setType("物料");
					totalDao.save(cy);
					cy.setRootId(cy.getId());
					totalDao.update(cy);
				} else {
					cy = (Category) totalDao
							.getObjectByCondition("from Category where name = '物料类别' and fatherId is null");
				}
				Category cy1 = (Category) totalDao.getObjectByCondition(hql,
						category.getName(), type);
				if (cy1 == null) {
					category.setType(type);
					category.setRootId(cy.getId());
					category.setFatherId(cy.getId());
					if (!totalDao.save(category)) {
						strb.append("添加" + type + "类型" + category.getName()
								+ "类别。失败! \\n");
					}
				} else {
					strb.append(type + "类型下已有" + category.getName()
							+ "类别,无需重复添加。\\n");
				}
			}
			if (strb.length() == 0) {
				return "true";
			} else {
				return strb.toString();
			}
		}
		return "error";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String delCategory(Category category) {
		if (category != null) {
			if (category.getId() != null) {
				List<Category> cyList = totalDao.query(
						" from Category where fatherId = ?", category.getId());
				if (cyList != null && cyList.size() > 0) {
					for (Category category2 : cyList) {
						delCategory(category2);
					}
				}
				Category category_old = (Category) totalDao.getObjectById(
						Category.class, category.getId());
				return totalDao.delete(category_old) + "";

			}
		}
		return "error";
	}

	public String isdelCategory(Category category) {
		if (str != null && str.length() > 0) {
			return str;
		}
		if (category != null) {
			if (category.getId() != null) {
				List<Category> cyList = totalDao.query(
						" from Category where fatherId = ?", category.getId());
				if (cyList != null && cyList.size() > 0) {
					for (Category category2 : cyList) {
						isdelCategory(category2);
					}
				}
				Category category_old = (Category) totalDao.getObjectById(
						Category.class, category.getId());
				String hql = " from ZhUser A join A.categoryset B where B.id =? ";
				Integer count = totalDao.getCount(hql, category.getId());
				if (count > 0) {
					str = "该类别下已有绑定的供应商，不能够删除!";
				}

			}
		}
		return str;

	}

	@Override
	public Map<Integer, Object> findByCategoryCondition(Category category,
			int pageNo, int pageSize) {
		if (category == null) {
			category = new Category();
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(category, null);
		int count = totalDao.getCount(hql);
		List<Category> cyList = (List<Category>) totalDao.findAllByPage(hql
				+ " order by name desc", pageNo, pageSize);
		map.put(1, cyList);
		map.put(2, count);
		return map;
	}

	@Override
	public List<Category> findCategoryByType(String type) {
		String hql = "from Category where type = ?";
		return totalDao.query(hql, type);
	}

	@Override
	public List<Category> findCategoryByType(String type, Integer userid) {
		String hql = "from Category where type = ?";
		List<Category> list = totalDao.query(hql, type);
		String hql_id = "select cg.id from Category cg join cg.userSet u where u.id=?";
		List listid = totalDao.query(hql_id, userid);
		if (listid != null && listid.size() > 0) {
			for (Category category : list) {
				for (int i = 0, len = listid.size(); i < len; i++) {
					Integer cgid = (Integer) listid.get(i);
					if (category.getId().equals(cgid)) {
						category.setChecked(true);
					}
				}
			}
		}
		return list;
	}

	@Override
	public Category getcategoryById(Integer id) {
		if (id != null && id > 0) {
			return (Category) totalDao.get(Category.class, id);
		}
		return null;
	}

	@Override
	public String update(Category category, String status) {
		if (category != null) {
			Category cy = (Category) totalDao.get(Category.class, category
					.getId());
			Integer count = 0;
			if (category.getCode() != null && !"".equals(category.getCode())
					&& !category.getCode().equals(cy.getCode())) {
				count = totalDao.getCount(" from  Category where code=?",
						category.getCode());
			}
			if (count > 0) {
				return "已存在物料编码" + category.getCode() + "，修改失败!";
			}
			Integer count1 = 0;
			if (category.getName() != null && !"".equals(category.getName())
					&& !category.getName().equals(cy.getName())) {
				count1 = totalDao.getCount(" from  Category where name=?",
						category.getName());
			}
			if (count1 > 0) {
				return "已存在物料类别" + category.getName() + "，修改失败!";
			}
			cy.setName(category.getName());
			cy.setCode(category.getCode());
			cy.setAvgProductionTakt(category.getAvgProductionTakt());
			cy.setAvgDeliveryTime(category.getAvgDeliveryTime());
			cy.setSunhao(category.getSunhao());
			cy.setSunhaoType(category.getSunhaoType());
			cy.setRangeOfReceipt(category.getRangeOfReceipt());
			cy.setRound(category.getRound());
			if ("updatycl".equals(status)) {
				updateYclAndWgj(cy);
			}
			return totalDao.update(cy) + "";
		}
		return "error";
	}

	@Override
	public List<Category> findCategoryByrootId(Integer rootId, String status,
			String tag) {
		if ("物料类别".equals(status)) {
			status = "物料";
		}
		String hql = " from Category where  type = '" + status + "'";
		if ("fl".equals(tag)) {
			hql += " and rootId in (select id from Category where name like '%辅料编码%')";
		}else{
			hql += " and rootId not in (select id from Category where name like '%辅料编码%')";
		}
		return totalDao.query(hql);

	}

	@Override
	public String add(Category category, String tag) {
		if (category != null) {
			Category cy = (Category) totalDao.get(Category.class, category
					.getFatherId());
			if ("物料".equals(cy.getType())) {
				Integer count = 0;
				if (null != category.getCode()
						&& !"".equals(category.getCode())) {
					count = totalDao.getCount(" from Category where code = ?",
							category.getCode());
				}
				Integer count1 = 0;
				if (category.getName() != null
						&& !"".equals(category.getName())) {
					count1 = totalDao.getCount(" from Category where name =? ",
							category.getName());
				}
				if (count1 > 0) {
					return "已存在物料名称" + category.getName() + "，添加失败!";
				}
				if (count > 0) {
					return "已存在物料编码" + category.getCode() + "，添加失败!";
				}
			}
			if ("tongcheng".equals(tag)) {
				category.setType(cy.getType());
				if (!cy.getId().equals(cy.getRootId())) {
					category.setFatherId(cy.getFatherId());
					category.setRootId(cy.getRootId());
					return totalDao.save(category) + "";
				} else {
					category.setFatherId(null);
					totalDao.save(category);
					category.setRootId(category.getId());
					return totalDao.update(category) + "";
				}
			} else if ("xiacheng".equals(tag)) {
				category.setType(cy.getType());
				category.setRootId(cy.getRootId());
				category.setFatherId(cy.getId());
				return totalDao.save(category) + "";
			}

		}
		return "添加失败";
	}

	/*
	 * 绑定员工物料类别
	 */
	@Override
	public boolean updateUserCate(Integer userId, Integer cateId) {
		if (userId != null && cateId != null && userId > 0 && cateId > 0) {
			Category c = (Category) totalDao.getObjectById(Category.class,
					cateId);
			if (c != null) {
				Users u = (Users) totalDao.getObjectById(Users.class, userId);
				if (u != null) {
					Set<Users> userSet = c.getUserSet();
					if (userSet.contains(u)) {
						userSet.remove(u);
					} else {
						userSet.add(u);
						c.setUserSet(userSet);
					}
					return totalDao.update(c);
				}
			}
		}
		return false;
	}

	/*
	 * 设置选中
	 */
	public void xiugaiUserCateByUser(Integer userId) {
		if (userId != null && userId > 0) {
			// 清空科目表选中状态
			String sql = "update ta_Category set checked=0";
			totalDao.createQueryUpdate(null, sql);
			// 更新对应部门的科目状态为选中
			String sql2 = "update ta_Category set checked=1 where id in (select ta_categoryId from ta_category_user where ta_userId=? )";
			totalDao.createQueryUpdate(null, sql2, userId);
		}

	}

	@Override
	public Category addfrist(String type) {
		String hql_count = " from Category where type = '" + type + "'";
		int count = totalDao.getCount(hql_count);
		if (count == 0) {
			Category cy = new Category();
			if ("物料".equals(type)) {
				cy.setName("物料类别");
				cy.setType(type);
				totalDao.save(cy);
				cy.setRootId(cy.getId());
				if (totalDao.update(cy)) {
					Category cy1 = new Category();
					cy1.setName("外购件");
					cy1.setType(type);
					cy1.setCode("wgj");
					cy1.setFatherId(cy.getId());
					cy1.setRootId(cy.getId());
					totalDao.save(cy1);
					Category cy2 = new Category();
					cy2.setName("原材料");
					cy2.setType(type);
					cy2.setCode("ycl");
					cy2.setFatherId(cy.getId());
					cy2.setRootId(cy.getId());
					totalDao.save(cy2);
					Category cy3 = new Category();
					cy3.setName("辅料");
					cy3.setType(type);
					cy3.setCode("fl");
					cy3.setFatherId(cy.getId());
					cy3.setRootId(cy.getId());
					totalDao.save(cy3);
				}
			} else {
				cy.setName("图号编码");
				cy.setType(type);
				totalDao.save(cy);
				Category cy1 = new Category();
				cy1.setName("临时归档编码");
				cy1.setType(type);
				totalDao.save(cy1);
				Category cy2 = new Category();
				cy2.setName("批量归档编码");
				cy2.setType(type);
				totalDao.save(cy2);
				cy.setRootId(cy.getId());
				totalDao.update(cy);
				cy1.setRootId(cy1.getId());
				totalDao.update(cy1);
				cy2.setRootId(cy2.getId());
				totalDao.update(cy2);
			}
			return cy;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unused")
	private List<String> getStrNo(Category category) {
		List<String> strList = new ArrayList<String>();
		if (category != null) {
			strList.add(category.getCode());
			if (category.getFatherId() != null) {
				Category fatehrcategory = (Category) totalDao.get(
						Category.class, category.getFatherId());
				getStrNo(fatehrcategory);
			}
		}
		return strList;
	}

	@Override
	public String test(Integer id) {
		String str = "";
		if (id != null) {
			Category category = (Category) totalDao.get(Category.class, id);
			List<String> strList = getStrNo(category);
			if (strList != null && strList.size() > 0) {
				for (int i = (strList.size() - 1); i >= 0; i--) {
					str += strList.get(i);
				}
			}
			strList = new ArrayList<String>();
		}
		return str;
	}

	@Override
	public boolean islow(Integer id) {
		if (id != null) {
			Category category = (Category) totalDao.get(Category.class, id);
			int count = totalDao.getCount(" from Category where fatherId = ? ",
					category.getId());
			if (count == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String updateYclAndWgj(Category category) {
		if (category != null) {
			// private Integer avgProductionTakt;//生产节拍
			// private Float avgDeliveryTime;//配送时长
			List<YuanclAndWaigj> yclAndWgjList = totalDao
					.query(" from YuanclAndWaigj where 1=1 and (banbenStatus is null or banbenStatus <> '历史' )");
			if (yclAndWgjList != null && yclAndWgjList.size() > 0) {
				for (YuanclAndWaigj yclAndWgj : yclAndWgjList) {
					category = (Category) totalDao.getObjectByCondition(
							"from Category where  name=?", yclAndWgj
									.getWgType());
					yclAndWgj.setAvgProductionTakt(category
							.getAvgProductionTakt() == null ? 0 : category
							.getAvgProductionTakt());
					yclAndWgj
							.setAvgDeliveryTime(category.getAvgDeliveryTime() == null ? 0
									: category.getAvgDeliveryTime());
					yclAndWgj.setSunhao(category.getSunhao());
					yclAndWgj.setSunhaoType(category.getSunhaoType());
					if(yclAndWgj.getRound() == null){
						yclAndWgj.setRound(category.getRound());
					}
					totalDao.update(yclAndWgj);
				}
			}
		}
		return null;
	}

	public String yclround() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("塑胶类", 4 * 30);
		map.put("五金", 6 * 30);
		map.put("包材类", 6 * 30);
		map.put("电镀件", 24 * 30);
		map.put("氧化件", 24 * 30);
		map.put("电子类", 12 * 30);
		map.put("板材", 6 * 30);
		map.put("半成品", 24 * 30);
		map.put("成品", 3 * 30);
		map.put("CO2焊丝", 12 * 30);
		Set<String> keySet = map.keySet();
		for (String wgType : keySet) {
			String hql = " from YuanclAndWaigj where 1=1 ";
			Category category = (Category) totalDao.getObjectByCondition(
					" from Category where name =? ", wgType);
			if (category != null) {
				getWgtype(category);
				hql += " and wgType in (" + strbu.toString().substring(1) + ")";
				strbu.setLength(0);// 清空
				List<YuanclAndWaigj> yclAndWgjList = totalDao.query(hql);
				if (yclAndWgjList != null && yclAndWgjList.size() > 0) {
					for (YuanclAndWaigj yclAndWgj : yclAndWgjList) {
						yclAndWgj.setRound(map.get(wgType).floatValue());
						totalDao.update(yclAndWgj);
					}
				}
			}

		}
		return "";
	}

	private void getWgtype(Category category) {
		List<Category> list = totalDao.query(
				" from  Category where fatherId = ?", category.getId());
		if (list != null && list.size() > 0) {
			for (Category category2 : list) {
				getWgtype(category2);
			}
		} else {
			strbu.append(",'" + category.getName() + "'");
		}
	}

}
