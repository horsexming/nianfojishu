package com.task.Server;

import com.task.entity.Provision;

/**
 * 合同条款Server
 * 
 * @author 刘培
 * 
 */
public interface ProvisionServer {

	public boolean addProvision(Provision provision);// 添加条款

	public boolean delProvision(Provision provision);// 添加条款

	public boolean updateProvision(Provision provision);// 添加条款
	public boolean updateProvision(Provision provision,int p);

	public Object[] findAllProvision(Provision provision, int pageNo,
			int pageSize);// 查询所有合同条款(分页+条件查询)
	public Object[] findAllProvisionMsd(Provision provision, int pageNo,
			int pageSize);// 查询所有合同条款(分页+条件查询)

	// 通过Id查询条款
	public Provision findProvisionById(Integer id);
	// 通过num查询条款
	public Provision findUpProvisionByNum(Integer num);

}
