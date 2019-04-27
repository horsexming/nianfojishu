package com.task.Server.android;

import java.util.List;

import com.task.entity.android.pscs.Customer;
import com.task.entity.android.pscs.CustomerInformation;

public interface CustomerServer {

	public Customer findCustomerById(Integer id); // 根据Id查询客户信息

	public Object[] findCustomerAll(Customer customer, int pageNo, int pageSize);

	public Object[] findCustomerById(Customer customer);

	public void saveCustomer(Customer customer);

	public void updateCustomer(Customer customer);
	
	public String updateCustomer1(Customer customer);//修改个人信息
	
	public String updateCustomer2(Customer customer);//重设密码

	public void delCustomerById(Integer customerId);

	String saveCustomer1(Customer customer);

	/**
	 * 根据手机号码生成验证码
	 * @return
	 */
	public String addSecurity(Customer Customer);
	/**
	 * 根据手机号码生成验证码
	 * @return
	 */
	public String backSecurity(Customer Customer);
	/**
	 * 添加用户记录
	 * @return
	 */
	public String addCustomer(Customer Customer);
	/**
	 * 用户提交信息
	 * @return
	 */
	public String addCustomerInformation(CustomerInformation CustomerInformation,Integer id);
	/**
	 * 查询显示用户提交的角度和转速信息
	 * @return
	 */
	public List selectAngularSpeed(Integer id);
	/**
	 * 查询显示用户提交的提交信息
	 * @return
	 */
	public List selectCustomerInformation(Integer id);
	
}
