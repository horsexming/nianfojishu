package com.task.ServerImpl.claim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.claim.CustomerClaimServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.ClientManagement;
import com.task.entity.Users;
import com.task.entity.claim.CustomerClaim;
import com.task.entity.shizhi.ProjectOrder;
import com.task.util.Util;

public class CustomerClaimServerImpl implements CustomerClaimServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public boolean add(CustomerClaim customerClaim) {
		String s = null;
		if (customerClaim.getAddTime() == null
				|| customerClaim.getAddTime().length() <= 0)
		customerClaim.setAddTime(Util.getDateTime());
			Users user = Util.getLoginUser();
			if (customerClaim.getAddTime() != null) {// 编写投诉单号
				String before = "claim"
						+ customerClaim.getAddTime().substring(0, 7);
				String cNumber = (String) totalDao.getObjectByCondition("select max(claimNumber) from CustomerClaim where claimNumber like '"
								+ before + "%'");
				int maxNo = 0;
				if (cNumber!=null) {
					String str= cNumber.substring(8);
							maxNo = Integer
									.parseInt(str);
							maxNo++;
							if (maxNo >=0 && maxNo < 10)
							{
								s = "000" + maxNo; // 1~9之间
							}
							else if (maxNo<100) {
								s = "00" + maxNo; // 10~99之间
							} else if (maxNo< 1000) {
								s = "0" + maxNo; // 100~999之间
							} else if (maxNo < 10000) {
								s = String.valueOf(maxNo); // 1000~9999之间
							}
					}else{
						s ="0001";
			}
				customerClaim.setClaimNumber(before+s);
		
			customerClaim.setUsername(user.getName());
			customerClaim.setStatus("未分析");
			return totalDao.save(customerClaim);
		}
		return false;
		}
	

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
	public Map<Integer, Object> findCustomerClaimsByCondition(
			CustomerClaim customerClaim, int pageNo, int pageSize, String tag) {
		if (customerClaim == null) {
			customerClaim = new CustomerClaim();
		}
		String sql = null;
		if (tag != null) {
			if (tag.equals("analysis")) {// 分析
				sql = " (status is null or status in('未分析','待审批','不合格'))";
			} else if (tag.equals("approval1")) {// 审批分析
				sql = " status ='待审批'";
			}
		}
		String hql = totalDao.criteriaQueries(customerClaim, sql, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public List<CustomerClaim> finddaifenxiList(){
		List<CustomerClaim> list = new ArrayList<CustomerClaim>();
		String hql="From CustomerClaim where status = '未分析'";
		list = totalDao.find(hql);
		return list;
	}
	
	@Override
	public boolean deleteById(Integer id)  {
		CustomerClaim c = getById(id);
		if (c != null) {
			return totalDao.delete(c);
		}
		return false;
	}

	@Override
	public CustomerClaim getById(Integer id) {
		 return (CustomerClaim) totalDao.getObjectById(CustomerClaim.class,
				id);
	}

	@Override
	public boolean update(CustomerClaim customerClaim, String tag) {
		
		if (customerClaim != null && customerClaim.getId() != null) {
			CustomerClaim old = getById(customerClaim.getId());
			if (old != null) {
				Users user = Util.getLoginUser();
				if (tag == null || tag.equals("all")) {
					old.setClaimNumber(customerClaim.getClaimNumber());
					old.setOurPerson(customerClaim.getOurPerson());
					old.setAddTime(customerClaim.getAddTime());
					old.setOtherPerson(customerClaim.getOtherPerson());
					old.setOtherPhone(customerClaim.getOtherPhone());
					old.setOtherCompany(customerClaim.getOtherCompany());
					old.setTitle(customerClaim.getTitle());
					old.setContext(customerClaim.getContext());
				} else if (tag.equals("analysis")) {// 分析
					if (customerClaim.getAnalysisFile() != null) {
						old.setAnalysisFile(customerClaim.getAnalysisFile());
					}
					old.setAnalysisTime(Util.getDateTime());
					old.setAnalysisText(customerClaim.getAnalysisText());
					old.setAnalystName(user.getName());
					old.setAnalystDept(user.getDept());
					old.setAnalystCode(user.getCode());
					String a = addapproval(old);
				} 
				return totalDao.update(old);
			}
		}
		return false;
	}
	public boolean updateApproval(CustomerClaim customerClaim){
		if (customerClaim != null && customerClaim.getId() != null) {
			CustomerClaim old = getById(customerClaim.getId());
			if (old != null) {
				Users user = Util.getLoginUser();
				old.setOtherCompany(customerClaim.getOtherCompany());
				old.setTitle(customerClaim.getTitle());
				old.setContext(customerClaim.getContext());
				old.setAnalysisText(customerClaim.getAnalysisText());
				String a = addapproval(old);
			}
				return totalDao.update(old);
		}
		return false;
	}
	public String addapproval(CustomerClaim claim) {
		Integer epId= null;
		if(claim.getStatus()!=null&&claim.getStatus().equals("打回")){
			CircuitRunServerImpl.updateCircuitRun(claim.getEpId());
		}else{//头次申请
			try {
				epId = CircuitRunServerImpl.createProcess("客户索赔评审流程", CustomerClaim.class, claim.getId(), "status", "id","customerClaimAction_detail.action?customerClaim.id="+claim.getId(), "客户索赔需求单申请审批", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			if(epId!=null){
				CustomerClaim old = getById(claim.getId());
				old.setEpId(epId);
				old.setStatus("未审批");
				totalDao.update(old);
			}else{
				return "审批流程有误，申请失败!";
		}
		return  "提交审批成功";
	}
}
	

