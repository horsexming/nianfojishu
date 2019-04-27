package com.task.ServerImpl.opinion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.opinion.CustomerOpinionServer;
import com.task.entity.ClientManagement;
import com.task.entity.Users;
import com.task.entity.opinion.CustomerOpinion;
import com.task.util.Util;

public class CustomerOpinionServerImpl implements CustomerOpinionServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Map<Integer, Object> findCustomerOpinionsByCondition(
			CustomerOpinion customerOpinion, int pageNo, int pageSize,
			String tag) {
		// TODO Auto-generated method stub
		if (customerOpinion == null) {
			customerOpinion = new CustomerOpinion();
		}
		String sql = null;
		if (tag != null) {
			if (tag.equals("analysis")) {// 分析
				sql = " (status is null or status in('未分析','分析完成','不合格'))";
				// }else if(tag.equals("improve")){//改进
				// sql = " status in ('分析通过','改进不合格')";
			} else if (tag.equals("approval1")) {// 审批分析
				sql = " status ='分析完成'";
				// }else if(tag.equals("approval2")){//审批改进
				// sql = " status in('改进中' ,'改进完成')";
			}
		}
		String hql = totalDao.criteriaQueries(customerOpinion, sql, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean add(CustomerOpinion customerOpinion) {
		// TODO Auto-generated method stub
		if (customerOpinion != null) {
			Users user = Util.getLoginUser();
			if (customerOpinion.getAddTime() != null) {// 编写投诉单号
				String before = "opinion"
						+ customerOpinion.getAddTime().substring(0, 10);
				List<String> opinionNumberList = totalDao
						.query("select opinionNumber from CustomerOpinion where opinionNumber like '"
								+ before + "-%'");
				int maxNo = 1;
				if (opinionNumberList != null) {
					for (String opinionNumber : opinionNumberList) {
						try {
							int no = Integer
									.parseInt(opinionNumber.split("-")[1]);
							if (no > maxNo) {
								maxNo = no;
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				customerOpinion.setOpinionNumber(before + "-" + maxNo);

			}
			customerOpinion.setUsername(user.getName());
			customerOpinion.setStatus("未分析");
			if (customerOpinion.getAddTime() == null
					|| customerOpinion.getAddTime().length() <= 0)
				customerOpinion.setAddTime(Util.getDateTime());
			return totalDao.save(customerOpinion);
		}
		return false;
	}

	@Override
	public CustomerOpinion getById(Integer id) {
		// TODO Auto-generated method stub
		return (CustomerOpinion) totalDao.getObjectById(CustomerOpinion.class,
				id);
	}

	/****
	 * 根据登录用户查询对应客户，并查询其对应订单、产品等信息
	 * 
	 * @return
	 */
	@Override
	public List findOrderByClient() {
		Users loginUser = Util.getLoginUser();
		if (loginUser != null) {
			ClientManagement custome = (ClientManagement) totalDao
					.getObjectByCondition(
							"from ClientManagement where userId=?", loginUser
									.getId());
			if (custome != null) {
				String hql = "from OrderManager where custome.id=?";
				return totalDao.query(hql, custome.getId());
			}
		}
		return null;
	}

	@Override
	public boolean update(CustomerOpinion customerOpinion, String tag) {
		// TODO Auto-generated method stub
		if (customerOpinion != null && customerOpinion.getId() != null) {
			CustomerOpinion old = getById(customerOpinion.getId());
			if (old != null) {
				Users user = Util.getLoginUser();
				if (tag == null || tag.equals("all")) {
					old.setOpinionNumber(customerOpinion.getOpinionNumber());
					old.setOurPerson(customerOpinion.getOurPerson());
					old.setAddTime(customerOpinion.getAddTime());
					old.setOtherPerson(customerOpinion.getOtherPerson());
					old.setOtherPhone(customerOpinion.getOtherPhone());
					old.setOtherCompany(customerOpinion.getOtherCompany());
					old.setTitle(customerOpinion.getTitle());
					old.setContext(customerOpinion.getContext());
				} else if (tag.equals("analysis")) {// 分析
					if (customerOpinion.getAnalysisFile() != null) {
						old.setAnalysisFile(customerOpinion.getAnalysisFile());
					}
					old.setAnalysisText(customerOpinion.getAnalysisText());
					// old.setAnalysisTime(Util.getDateTime());
					old.setAnalystName(user.getName());
					old.setAnalystDept(user.getDept());
					old.setAnalystCode(user.getCode());
					old.setStatus("分析完成");
					// }else if(tag.equals("improve")){//改进
					// if(customerOpinion.getExecutiveFile()!=null){
					// old.setExecutiveFile(customerOpinion.getExecutiveFile());
					// }
					// old.setExecutiveText(customerOpinion.getExecutiveText());
					// // old.setExecutiveTime(Util.getDateTime());
					// old.setExecutiveCodes(user.getCode());
					// old.setStatus("改进完成");
				} else if (tag.equals("approval1") || tag.equals("approval2")) {// 审批分析
					old.setStatus(customerOpinion.getStatus());
				}
				return totalDao.update(old);
			}
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		CustomerOpinion c = getById(id);
		if (c != null) {
			return totalDao.delete(c);
		}
		return false;
	}

}
