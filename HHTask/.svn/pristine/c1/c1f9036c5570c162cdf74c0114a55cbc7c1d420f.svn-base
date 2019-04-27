package com.task.ServerImpl;

import java.util.Arrays;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.TclaimsRecordService;
import com.task.entity.Tclaimform;
import com.task.entity.TclaimsRecord;

public class TclaimsRecordServiceImpl implements TclaimsRecordService {
	private TotalDao totalDao;

	@Override
	public List<TclaimsRecord> get(Tclaimform tclaimform) {
		String hql = "from TclaimsRecord t where t.root.id = ?";
		return totalDao.find(hql,new Object[]{tclaimform.getId()});
	}

	@Override
	public void updateAll(Integer[] ids, String reason, String name, String filename, String responsibility) {
		if(ids == null || ids.length == 0){
			throw new RuntimeException("请至少选中一项记录进行分析");
		}
		String params = Arrays.toString(ids).substring(1, Arrays.toString(ids).length()-1);
		String hql = "from TclaimsRecord where id in (" + params + ")";
		List<TclaimsRecord> list = totalDao.find(hql);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setReason(reason);
			list.get(i).setReasonPerson(name);
			list.get(i).setReasonFilename(filename);
			list.get(i).setResponsibility(responsibility);
			if(list.get(i).getResponsibility().equals("供应商责任")){
				list.get(i).setStatus("未通知");
			} else {
				list.get(i).setStatus("未整改");
			}
		}
		
		String hql1 = "select count(*) from TclaimsRecord t where t.status=? and t.root.id = ? ";
		long k = totalDao.count(hql1, new Object[]{null, list.get(0).getRoot().getId()});
	}

	@Override
	public List<TclaimsRecord> getNotification(Tclaimform root) {
		String hql = "from TclaimsRecord t where t.root.id = ? and t.status = ?";
		return totalDao.find(hql,new Object[]{root.getId(), "未通知"});
	}


	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void updateHandle(Integer[] ids, String handle, String name, String fileName) {
		if(ids == null || ids.length == 0){
			throw new RuntimeException("请至少选中一项记录进行处理");
		}
		String params = Arrays.toString(ids).substring(1, Arrays.toString(ids).length()-1);
		String hql = "from TclaimsRecord where id in (" + params + ")";
		
		List<TclaimsRecord> list = totalDao.find(hql);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setHandle(handle);
			list.get(i).setHandleFilename(fileName);
			list.get(i).setHandlePerson(name);
			list.get(i).setStatus("已整改");
		}
		
		String hql1 = "select count(*) from TclaimsRecord t where t.status=? and t.root.id = ? ";
		long k = totalDao.count(hql1, new Object[]{"已分析", list.get(0).getRoot().getId()});
		if(k == 0){
			list.get(0).getRoot().setStatus("处理完成");
		}
	}
	@Override
	public TclaimsRecord get(Integer integer) {
		TclaimsRecord t = (TclaimsRecord) totalDao.get(TclaimsRecord.class, integer);
		t.getRoot().getId();
		return t;
	}

}
