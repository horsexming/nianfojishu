package com.task.ServerImpl.sop.ycl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.sop.ycl.PanelSizeServer;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.sop.ycl.PanelSize;

public class PanelSizeServerImpl implements PanelSizeServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String addPanelSize(PanelSize panelsize) {
		if(panelsize!=null){
			return	totalDao.save(panelsize)+"";
		}
		return null;
	}

	@Override
	public String delPanelSize(PanelSize panelsize) {
		if(panelsize!=null){
			return	totalDao.delete(panelsize)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllPanelSize(PanelSize panelsize, int pageNo,
			int pageSize, String status,Float houdu) {
		if(panelsize == null){
			panelsize = new PanelSize();
		}
		if("fenmo".equals(status)){
			panelsize.setType("复合粉末");
		}else{
			panelsize.setType("板材");
		}
		String hql = totalDao.criteriaQueries(panelsize,null);
		if(houdu!=null && houdu>0){
			hql+= " and "+houdu+" BETWEEN fristThickness AND endThickness";
		}
		List<PanelSize> panelSizeList =	totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		
		return new Object[] {panelSizeList,count};
	}

	@Override
	public PanelSize findPanelSizeById(Integer id) {
		if(id!=null){
			return	(PanelSize) totalDao.get(PanelSize.class, id);
		}
		return null;
	}

	@Override
	public String updatePanelSize(PanelSize panelsize) {
		if(panelsize!=null){
			return	totalDao.update(panelsize)+"";
		}
		return null;
	}


}
