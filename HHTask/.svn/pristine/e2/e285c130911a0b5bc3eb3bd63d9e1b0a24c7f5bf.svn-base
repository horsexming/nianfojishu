package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.SmsGroupService;
import com.task.entity.SmsGroup;
import com.task.entity.SmsUser;
import com.task.entity.VSmsGroup;
import com.task.entity.VSmsUser;

public class SmsGroupServiceImpl implements SmsGroupService {
	private TotalDao totalDao;
	@Override
	public boolean add(SmsGroup group) {
		return totalDao.save(group);
	}

	@Override
	public boolean delete(SmsGroup smsGroup) {
		return totalDao.delete(smsGroup);
	}
	
	public SmsGroup get(SmsGroup smsGroup){
		String hql = "from SmsGroup where id = ?";
		SmsGroup group = (SmsGroup) totalDao.getObjectByCondition(hql, smsGroup.getId()); 
		return group;
	}
	
	public List<VSmsGroup> getAll(){
		String hql = "from SmsGroup";
		List<SmsGroup> groups = totalDao.query(hql);
		List<VSmsGroup> vGroups = new ArrayList<VSmsGroup>();
		for (SmsGroup group : groups) {
			VSmsGroup v = new VSmsGroup();
			v.setId(group.getId());
			v.setName(group.getName());
			vGroups.add(v);
		}
		return vGroups;
	}
	
	@Override
	public List<VSmsGroup> findAll() {
		String hql = "from SmsGroup";
		List<SmsGroup> list = totalDao.query(hql);
		List<VSmsGroup> groups = new ArrayList<VSmsGroup>();
		for (SmsGroup smsGroup : list) {
			VSmsGroup group = new VSmsGroup();
			group.setId(smsGroup.getId());
			group.setText(smsGroup.getName());
			if(smsGroup.getUsers() != null && smsGroup.getUsers().size() > 0){
				group.setChildren(new HashSet<VSmsUser>());
				group.setState("closed");
				for (SmsUser smsUser : smsGroup.getUsers()) {
					VSmsUser user = new VSmsUser();
					user.setId(smsUser.getId());
					user.setText(smsUser.getName());
					user.setIconCls("icon-user");
					Map<String, Object> attribute = new HashMap<String, Object>();
					attribute.put("phone", smsUser.getPhone());
					attribute.put("email", smsUser.getEmail());
					user.setAttributes(attribute);
					group.getChildren().add(user);
				}
			}
			group.setIconCls("icon-floderUser");
			groups.add(group);
		}
		
		return groups;
	}
	@Override
	public boolean update(SmsGroup smsGroup) {
		return totalDao.update(smsGroup);
	}
	

	@Override
	public SmsGroup get(int groupId) {
		SmsGroup group = new SmsGroup();
		group.setId(groupId);
		return get(group);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
