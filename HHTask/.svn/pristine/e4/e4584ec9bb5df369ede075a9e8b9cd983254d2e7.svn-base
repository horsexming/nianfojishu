package com.task.ServerImpl.android;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.aop.ThrowsAdvice;

import com.google.gson.annotations.Until;
import com.task.Dao.TotalDao;
import com.task.Server.android.CustomerServer;
import com.task.ServerImpl.SmsServiceImpl;
import com.task.entity.android.pscs.AngularSpeed;
import com.task.entity.android.pscs.Customer;
import com.task.entity.android.pscs.CustomerInformation;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.parking.SimCarkTel;
import com.task.util.Util;

public class CustomerServerImpl implements CustomerServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * 
	 * 根据编号查询(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.CustomerServer#findCustomerById(java.lang.Integer
	 * )
	 */
	@Override
	public Customer findCustomerById(Integer id) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) this.totalDao.getObjectById(
				Customer.class, id);
		return customer;
	}

	/*
	 * 
	 * 查询所有客户信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.CustomerServer#findCustomerAll(com.task.entity
	 * .android.pscs.Customer, int, int)
	 */
	@Override
	public Object[] findCustomerAll(Customer customer, int pageNo, int pageSize) {
		if (customer == null) {
			customer = new Customer();
		}
		String sql = "select * from ta_customer  where 1=1";
		if (!"".equals(customer.getCustomer_name())
				&& customer.getCustomer_name() != null) {
			sql += " and customer_name like '%" + customer.getCustomer_name()
					+ "%'";
		}
		if (!"".equals(customer.getCustomer_phone())
				&& customer.getCustomer_phone() != null) {
			sql += " and customer_phone like '%" + customer.getCustomer_phone()
					+ "%'";
		}
		if (!"".equals(customer.getCart_ype())
				&& customer.getCart_ype() != null) {
			sql += " and cart_ype like '%" + customer.getCart_ype() + "%'";
		}
		if (!"".equals(customer.getCustomer_state())
				&& customer.getCustomer_state() != null) {
			sql += " and customer_state ='" + customer.getCustomer_state()
					+ "'";
		}
		String sql1 = sql + "order by customer_id desc";
		List list1 = totalDao.findBySql(sql1, pageNo, pageSize);
		int count = totalDao.findBySql(sql).size();// 总行数
		Object[] o = { list1, count };
		return o;
	}

	/*
	 * 添加客户信息 (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.CustomerServer#saveCustomer(com.task.entity.android
	 * .pscs.Customer)
	 */
	@Override
	public void saveCustomer(Customer customer) {
		String s = customer.getStartdate();
		Date sdate = Util.StringToDate(s, "yyyy-MM-dd");
		String date1 = Util.getDateTime("yyyy-MM-dd");// 获得系统当前日期
		Date sysdate = Util.StringToDate(date1, "yyyy-MM-dd");
		Integer proid = customer.getPeriod();// 周期
		String state = customer.getCustomer_state();// 状态
		try {
			if (!"永久".equals(state)) {
				if (!"".equals(s)) {
					customer.setStartdate(s);
					float day = proid * 30.42F;// 计算天数
					Math.ceil(day);// 四舍五入加以
					int dateLong = (int) Math.ceil(day);// 转化为整数
					Date enddate = Util.getCalendarDate(sdate, dateLong);
					customer.setEnddate(Util
							.DateToString(enddate, "yyyy-MM-dd"));
					this.totalDao.save(customer);
				} else {
					customer.setStartdate(date1);
					float day = proid * 30.42F;// 计算天数
					Math.ceil(day);// 四舍五入加以
					int dateLong = (int) Math.ceil(day);// 转化为整数
					Date enddate = Util.getCalendarDate(sysdate, dateLong);
					customer.setEnddate(Util
							.DateToString(enddate, "yyyy-MM-dd"));
					this.totalDao.save(customer);
				}

			} else {
				this.totalDao.save(customer);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * 修改客户信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.CustomerServer#updateCustomer(com.task.entity
	 * .android.pscs.Customer)
	 */
	@Override
	public void updateCustomer(Customer customer) {
		Customer customer2 = (Customer) this.totalDao.getObjectById(
				Customer.class, customer.getCustomer_id());
		customer2.setCustomer_name(customer.getCustomer_name());
		customer2.setCompany_name(customer.getCompany_name());
		customer2.setCustomer_phone(customer.getCustomer_phone());
		customer2.setCart_ype(customer.getCart_ype());
		customer2.setCustomer_state(customer.getCustomer_state());
		customer2.setPeriod(customer.getPeriod());
		customer2.setStartdate(customer.getStartdate());
		this.totalDao.update(customer2);
	}

	/*
	 * 
	 * 删除客户信息 (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.CustomerServer#delCustomerById(java.lang.Integer)
	 */
	@Override
	public void delCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) this.totalDao.getObjectById(
				Customer.class, customerId);
		this.totalDao.delete(customer);
	}

	/*
	 * 
	 * 根据手机号查询对象接口(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.CustomerServer#findCustomerById(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findCustomerById(Customer customer) {
		// TODO Auto-generated method stub
		String hql = "from Customer where customer_phone=? and customer_passWord=? order by customer_id desc";
		List<Customer> customers = this.totalDao.query(hql,customer.getCustomer_phone(),customer.getCustomer_passWord());
		if (customers != null && customers.size() > 0) {
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Customer c = customers.get(0);
			String message = "";
			boolean bool = true;
			try {
				if (c.getStartdate() != null && c.getEnddate() != null) {
					// String startdate1 =
					// Util.getDateTime("yyyy-MM-dd");//获得系统当前日期
					Date startdate = fmt.parse(c.getEnddate());
					Date startdate1 = fmt.parse(Util.getDateTime("yyyy-MM-dd"));
					if ("试用".equals(c.getCustomer_state())) {
						if (startdate.getTime() - startdate1.getTime() < 0) {
							//试用期已过期
							message = "002";
							bool = false;
						} else {
							//登陆成功！
							message = "001";
							bool = true;
						}
					} else if ("正式".equals(c.getCustomer_state())) {
						if (startdate.getTime() - startdate1.getTime() < 0) {
							//正式期已过期
							message = "003";
							bool = false;
						} else {
							//登陆成功！
							message = "001";
							bool = true;
						}
					} else if ("永久".equals(c.getCustomer_state())) {
						//登陆成功！
						message = "001";
						bool = true;
					}
					if (bool) {
						c.setCustomer_passWord("");
						c.setRandomNum("");
						c.setSecurityTel("");
						c.setSecurityCode("");
						c.setCustomerInformations(null);
						return new Object[] { message, c, true };
					} else {
						return new Object[] { message, null, false };
					}
				} else {
					//登陆成功！
					return new Object[] { "001", c, true };
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String hql1 = "from Customer where customer_phone=? order by customer_id desc";
			List<Customer> customers1 = this.totalDao.query(hql1,customer.getCustomer_phone());
			if (customers1 != null && customers1.size() > 0) {
				return new Object[] { "006", null, false };//密码有误
			}
			//该手机号不存在!
			return new Object[] { "004", null, false };
		}
		return null;
	}

	/*
	 * 
	 * 安卓端添加客户信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.android.CustomerServer#saveCustomer1(com.task.entity.
	 * android.pscs.Customer)
	 */
	@Override
	public String saveCustomer1(Customer customer) {
		String sql = "from Customer where customer_phone=?";
		int count = totalDao.getCount(sql, customer.getCustomer_phone());
		String message="";
		if (count > 1) {
			//该手机号已注册！
			message="003";
		} else {
			Customer customer1 = (Customer) totalDao
			.getObjectByCondition(
					"from Customer where securityTel = ? and securityCode = ? and failTime > ?",
					 customer.getCustomer_phone(), customer
							.getSecurityCode(), Util.getDateTime());
			if (customer1!=null) {
				customer1.setCustomer_state("试用");
				customer1.setPeriod(1);// 插入周期为1个月天定格
				customer1.setCustomer_passWord(customer.getCustomer_passWord());//登陆密码
				customer1.setCustomer_phone(customer.getCustomer_phone());//登陆手机号码
				String sdate = Util.getDateTime("yyyy-MM-dd");// 获得当前日期
				customer1.setStartdate(sdate);
				customer1.setEnddate(Util.getSpecifiedDayAfter(sdate, 30));//获得当前日期30天后的时间
				if(totalDao.save(customer1)) message="001";//注册成功!
				else message="002";//注册失败!
			}else {
				message="验证码不存在或已过期！请重新获取。";
			}
		}
		return message;
	}

	@Override
	public String addCustomer(Customer Customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addSecurity(Customer customer) {
		// TODO Auto-generated method stub
		if (customer != null) {
			//先判断手机号码有没有绑定过有的话提示手机号码已绑定，
			Customer customer1 = (Customer) totalDao.getObjectByCondition(
					"from Customer where customer_phone = ?", customer.getCustomer_phone());
			if (customer1 != null) {
				if ("永久".equals(customer1.getCustomer_state())) {
					return "您已注册过，请登录";
				}else {//对比失效日期
					if (Util.getDateTime("yyyy-MM-dd").compareTo(customer1.getFailTime())>0) {
						return "您的试用期已过，请联系负责人开通权限。";
					}
				}
//				customer1.setUpdateTime(Util.getDateTime());
//				customer1.setFailTime(Util.getSpecifiedminuteAfter(Util
//						.getDateTime(), 30));
//				customer1.setSecurityTel(customer.getCustomer_phone());
//				int i = 1;
//				if (customer1.getSecurityshu() == null) {
//				} else {
//					i = customer1.getSecurityshu() + 1;
//				}
//				customer1.setSecurityshu(i);
//				String num2 = Util.yanNumber(2);
//				customer1.setRandomNum(num2);
//				String num6 = Util.yanNumber(6);
//				customer1.setSecurityCode(num6);
//				customer.setRandomNum(num2);
//				if (totalDao.update2(customer1)) {
//					// 生成验证码的同时将短信发送至手机号码
//					SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
//					smsServiceImpl.send(customer1.getSecurityTel(),
//							"您正在进行动声控制系统找回密码操作。验证码为：" + num6 + " 验证码有效期30分钟， 验证码编号为："+num2+"。");
//					return "true";
//				}
			} else {
				Customer customer3 = new Customer();
				customer3.setAddTime(Util.getDateTime());
				customer3.setFailTime(Util.getSpecifiedminuteAfter(Util
						.getDateTime(), 30));
				customer3.setSecurityTel(customer.getCustomer_phone());
				customer3.setSecurityshu(1);
				String num2 = Util.yanNumber(2);
				customer3.setRandomNum(num2);
				String num6 = Util.yanNumber(6);
				customer3.setSecurityCode(num6);
				customer.setRandomNum(num2);
				if (totalDao.save2(customer3)) {
					// 生成验证码的同时将短信发送至手机号码
					SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
					smsServiceImpl.send(customer3.getSecurityTel(),
							"您正在进行动声控制系统注册操作。验证码为：" + num6 + " 验证码有效期30分钟， 验证码编号为："+num2+"。");
					return "true";
				}
			}
		}
		return "验证码获取失败，请重新获取。";
	}


	@Override
	public String backSecurity(Customer customer) {
		// TODO Auto-generated method stub
		if (customer != null) {
			//先判断手机号码有没有绑定过有的话提示手机号码已绑定，
			Customer customer1 = (Customer) totalDao.getObjectByCondition(
					"from Customer where customer_phone = ?", customer.getCustomer_phone());
			if (customer1 != null) {
				customer1.setUpdateTime(Util.getDateTime());
				customer1.setFailTime(Util.getSpecifiedminuteAfter(Util
						.getDateTime(), 30));
				customer1.setSecurityTel(customer.getCustomer_phone());
				int i = 1;
				if (customer1.getSecurityshu() == null) {
				} else {
					i = customer1.getSecurityshu() + 1;
				}
				customer1.setSecurityshu(i);
				String num2 = Util.yanNumber(2);
				customer1.setRandomNum(num2);
				String num6 = Util.yanNumber(6);
				customer1.setSecurityCode(num6);
				customer.setRandomNum(num2);
				if (totalDao.update2(customer1)) {
					// 生成验证码的同时将短信发送至手机号码
					SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
					smsServiceImpl.send(customer1.getSecurityTel(),
							"您正在进行动声控制系统找回密码操作。验证码为：" + num6 + " 验证码有效期30分钟， 验证码编号为："+num2+"。");
					return "true";
				}
			} else {
				if ("永久".equals(customer.getCustomer_state())) {
					return "您已注册过，请登录";
				}else {//对比失效日期
					if (Util.getDateTime("yyyy-MM-dd").compareTo(customer.getFailTime())>0) {
						return "您的试用期已过，请联系负责人开通权限。";
					}
				}
			}
		}
		return "验证码获取失败，请重新获取。";
	}
	
	
	@Override
	public String updateCustomer1(Customer customer) {
		// TODO Auto-generated method stub
		if (customer!=null&&customer.getCustomer_id()!=null&&customer.getCustomer_id()>0) {
			Customer customer2 = (Customer) this.totalDao.getObjectById(
					Customer.class, customer.getCustomer_id());
			if (customer2!=null) {
				customer2.setCustomer_name(customer.getCustomer_name());//客户姓名
				customer2.setCompany_name(customer.getCompany_name());//公司名称
				customer2.setCart_ype(customer.getCart_ype());//车型
				if (totalDao.update(customer2)) {
					return "true";
				}else {
					return "修改失败！";
				}
			}
		}
		return "对象为空！修改失败！";
	}

	@Override
	public String updateCustomer2(Customer customer) {
		// TODO Auto-generated method stub
		if (customer!=null&&customer.getCustomer_phone()!=null&&customer.getCustomer_phone().length()>10&&customer.getSecurityCode()!=null&&customer.getSecurityCode().length()==6) {
			Customer customer2 = (Customer) this.totalDao.getObjectByCondition("from Customer where customer_phone = ? and securityCode = ?", customer.getCustomer_phone(),customer.getSecurityCode());
			if (customer2!=null) {
				customer2.setCustomer_passWord(customer.getCustomer_passWord());
				if (totalDao.update(customer2)) return "true";
				else return "修改失败！";
			}
		}
		return "数据异常！修改失败！";
	}

	@Override
	public String addCustomerInformation(CustomerInformation customerInformation,Integer id) {
		// TODO Auto-generated method stub
		Customer customer = findCustomerById(id);
		if (customer==null) return "用户不存在！";
		String list = customerInformation.getAngularspeedList();
		try {
			JSONArray array = new JSONArray(list); 
//			System.out.println(array.toString());
			if (array != null && array.length()== 0) return "数据为空！";
			for (int i = 0 ;i< array.length();i++){
				JSONObject object = array.getJSONObject(i);
//                System.out.println("object"+object);
                AngularSpeed angularspeed = new AngularSpeed();
                angularspeed.setAngular(object.getString("angle"));
                angularspeed.setSpeed(object.getString("rev"));
                angularspeed.setCustomerInformation(customerInformation);
                totalDao.save(angularspeed); 
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customerInformation.setCustomer(customer);
		customerInformation.setC_time(Util.getDateTime());
		if (totalDao.save(customerInformation)) {
			return "true";
		}
		return "保存失败！";
	}

	@Override
	public List selectAngularSpeed(Integer id) {
		// TODO Auto-generated method stub
		return totalDao.query("from AngularSpeed where customerInformation.id = ?",id);
	}

	@Override
	public List selectCustomerInformation(Integer id) {
		// TODO Auto-generated method stub
		return totalDao.query("from CustomerInformation where customer.customer_id = ?",id);
	}
	

}
