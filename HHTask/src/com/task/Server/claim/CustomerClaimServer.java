package com.task.Server.claim;

import java.util.List;
import java.util.Map;

import com.task.entity.claim.CustomerClaim;



public interface CustomerClaimServer {
	
	Map<Integer, Object> findCustomerClaimsByCondition(
			CustomerClaim customerClaim, int parseInt, int pageSize,
			String tag);
	
	boolean add(CustomerClaim customerClaim);
	
	List findOrderByClient();
	
	boolean deleteById(Integer id);

	CustomerClaim getById(Integer id);
	
	boolean update(CustomerClaim customerClaim, String tag);
	
	public List<CustomerClaim> finddaifenxiList();
	
	public String addapproval(CustomerClaim customerClaim);
	
	public boolean updateApproval(CustomerClaim customerClaim);
		
}
