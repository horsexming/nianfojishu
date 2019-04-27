package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Becoming;
import com.task.entity.Promotion;
import com.task.entity.Template;
import com.task.entity.TemplateDetails;
import com.task.entity.Users;

public interface BecomingServer {
	/**
	 * 添加申请
	 * @param becoming
	 * @return
	 */
	public String add(Becoming becoming);
	/**
	 * 删除申请
	 * @param becoming
	 * @return
	 */
	public boolean del(Becoming becoming);
	/**
	 * 查询所有
	 */
	public List<Becoming> showAllBecomingList(int pageNo, int pageSize);
	
	/**
	 * 条件查询
	 */
	public Map<Integer, Object> findBecomingByCondition(Becoming bg,
			int pageNo, int pageSize,String status);
	
	/**
	 * 获得总条数
	 */
	public int getcont();
	
	/**
	 * 根据Id查询
	 */
	Becoming findBecomingbyId(Integer id);
	/**
	 * 修改
	 */
	public boolean update(Becoming becoming);
	/**
	 * 获得员工转正考核明细root
	 */
	public  List<TemplateDetails>  gettdlist();
	/**
	 * 获得员工转正考核明细
	 */
	public List<String> gettdlistbyId(Integer id);
	/**
	 * 根据userId 获得user信息
	 */
	public Users getUsersById(Integer id);
	/**
	 * 获得z转正模板
	 */
	Template gettemplateBy();
}
