package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.SmsGroupService;
import com.task.Server.SmsUserService;
import com.task.entity.DataGrid;
import com.task.entity.SmsGroup;
import com.task.entity.SmsUser;
import com.task.entity.VSmsUser;

public class SmsUserServiceImpl implements SmsUserService {
	private TotalDao totalDao;
	private SmsGroupService smsGroupService;

	@Override
	public void save(VSmsUser smsUser) {
		SmsGroup temp = new SmsGroup();
		temp.setId(smsUser.getGroupId());
		SmsGroup group = smsGroupService.get(temp);
		SmsUser user = new SmsUser();
		BeanUtils.copyProperties(smsUser, user);
		user.setGroup(group);
		totalDao.save(user);
	}
	
	@Override
	public void update(VSmsUser smsUser) {
		SmsGroup group = smsGroupService.get(smsUser.getGroupId());
		SmsUser user = this.get(smsUser.getId());
		BeanUtils.copyProperties(smsUser, user);
		user.setGroup(group);
		totalDao.update(user);
	}

	@Override
	public DataGrid toDataGrid(VSmsUser sms) {
		DataGrid dg = new DataGrid();
		String hql = "from SmsUser";
		int count = 0;
		if (sms.getName() != null && !sms.getName().trim().equals("")) {
			hql += " where name like ?";
			count = totalDao.getCount(hql, "%" + sms.getName() + "%");
		} else {
			count = totalDao.getCount(hql);
		}
		if (sms.getSort() != null) {
			hql += " order by " + sms.getSort() + " " + sms.getOrder();
		}
		List<VSmsUser> vos = new ArrayList<VSmsUser>();
		List<SmsUser> users = (List) totalDao.findAllByPage(hql, sms.getPage(), sms.getRows(), sms.getName() != null && !sms.getName().trim().equals("") ? "%" + sms.getName() + "%" : null);
		for (SmsUser smsUser : users) {
			VSmsUser vo = new VSmsUser();
			vo.setId(smsUser.getId());
			vo.setName(smsUser.getName());
			vo.setPhone(smsUser.getPhone());
			if (smsUser.getGroup() != null) {
				vo.setGroupId(smsUser.getGroup().getId());
				vo.setGroup(smsUser.getGroup().getName());
			}
			vos.add(vo);
		}

		dg.setTotal(new Long(count));
		dg.setRows(vos);
		return dg;
	}
	

	@Override
	public void delete(VSmsUser smsUser) {
		SmsUser user = (SmsUser) totalDao.getObjectById(SmsUser.class, smsUser.getId());
		user.setGroup(null);
		totalDao.delete(user);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public SmsGroupService getSmsGroupService() {
		return smsGroupService;
	}

	public void setSmsGroupService(SmsGroupService smsGroupService) {
		this.smsGroupService = smsGroupService;
	}

	@Override
	public SmsUser get(int id) {
		return (SmsUser) totalDao.getObjectById(SmsUser.class, id);
	}

}
