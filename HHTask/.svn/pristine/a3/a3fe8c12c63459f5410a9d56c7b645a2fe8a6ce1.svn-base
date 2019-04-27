package com.task.ServerImpl.TbBarcode;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.TbBarcode.TbBarcodeLockNoticeServer;
import com.task.entity.TbBarcode.TbBarcodeLockNotice;

public class TbBarcodeLockNoticeServerImpl implements TbBarcodeLockNoticeServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] findAlltblnList(TbBarcodeLockNotice tbln, Integer pageNo,
			Integer pageSize, String status) {
		if (tbln == null) {
			tbln = new TbBarcodeLockNotice();
		}
		String hql = totalDao.criteriaQueries(tbln, null)+" order by id desc";
		List<TbBarcodeLockNotice> tblnList = totalDao.findAllByPage(hql,
				pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { tblnList, count };
	}

	@Override
	public String updatetbln(TbBarcodeLockNotice tbln) {
		if (tbln != null) {
			TbBarcodeLockNotice old = (TbBarcodeLockNotice) totalDao.get(
					TbBarcodeLockNotice.class, tbln.getId());
			old.setStatus("已处理");
			old.setFileName(tbln.getFileName());
			return totalDao.update(old) + "";
		}
		return null;
	}

	@Override
	public TbBarcodeLockNotice findtblnById(Integer id) {
		if (id != null && id > 0) {
			return (TbBarcodeLockNotice) totalDao.get(
					TbBarcodeLockNotice.class, id);
		}
		return null;
	}

}
