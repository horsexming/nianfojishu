package com.task.ServerImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectTrackRecordService;
import com.task.entity.ProjectStartUser;
import com.task.entity.ProjectTrack;
import com.task.entity.ProjectTrackRecord;

public class ProjectTrackRecordServiceImpl implements ProjectTrackRecordService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectTrackRecord p) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		p.setAddTime(sdf.format(now));
		if(p.getRecordType().equals("待审批")){
			p.setChecked(false);
		}
		totalDao.save(p);
	}

	@Override
	public Object[] getByTrack(ProjectTrackRecord p, int pageNo, int pageSize) {
		String hql = "from ProjectTrackRecord p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, p.getRoot().getId());
		int count = totalDao.getCount(hql,p.getRoot().getId());
		Object[] o = { list, count };
		return o;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectTrackRecord get(ProjectTrackRecord p) {
		return (ProjectTrackRecord) totalDao.getObjectById(p.getClass(), p.getId());
	}
	
	@Override
	public ProjectTrackRecord get(ProjectTrack p) {
		String hql = "from ProjectTrackRecord where root.id = ?";
		return (ProjectTrackRecord) totalDao.getObjectByCondition(hql,p.getId());
	}


	@Override
	public void updateCheck(ProjectTrackRecord p) {
		ProjectTrackRecord p1 = (ProjectTrackRecord) totalDao.getObjectById(p.getClass(), p.getId());
		p1.setChecked(true);
		p1.setAgree(p.getAgree());
		p1.setAbout(p.getAbout());
		p1.setApproveMoney(p.getApproveMoney());
		if(p1.getAgree()){
			p1.setRecordType("同意");
		} else {
			p1.setRecordType("驳回");
		}
	}

	@Override
	public ProjectStartUser getBoss(ProjectTrackRecord p) {
		ProjectTrack  p2 = (ProjectTrack) totalDao.getObjectById(p.getRoot().getClass(), p.getRoot().getId());
		System.out.println(p2.getRoot().getId());
		String hql = "from ProjectStartUser p where root.root.id = ? and pGroup = ?";
		ProjectStartUser u = (ProjectStartUser) totalDao.getObjectByCondition(hql, p2.getRoot().getId(),"项目负责人");
		return u;
	}

	@Override
	public void updateComplete(ProjectTrackRecord p) {
		ProjectTrackRecord p1 = (ProjectTrackRecord) totalDao.getObjectById(ProjectTrackRecord.class, p.getId());
		System.out.println(p1.getId());
		p1.setRecordType("已完成");
		totalDao.update(p1);
	}

}
