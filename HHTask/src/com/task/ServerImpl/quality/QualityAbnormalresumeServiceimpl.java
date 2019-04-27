package com.task.ServerImpl.quality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.quality.QualityAbnormalresumeService;
import com.task.entity.Taskmanager;
import com.task.entity.quality.QualityAbnormalresume;

public class QualityAbnormalresumeServiceimpl implements
		QualityAbnormalresumeService {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String addQualityAbnormalresume(
			QualityAbnormalresume qualityAbnormalresume) {
		if(qualityAbnormalresume.getType().indexOf(",")!=-1){
			qualityAbnormalresume.setType(qualityAbnormalresume.getType().substring(qualityAbnormalresume.getType().indexOf(",")+1));
		}
		if (totalDao.save(qualityAbnormalresume)) {
			return "true";
		}
		return "false";
	}

	@Override
	public String deleteQualityAbnormalresume(Integer id) {
		QualityAbnormalresume obj = findQualityAbnormalresume(id);
		if (null != obj && totalDao.delete(obj)) {
			return "true";
		}
		return "false";
	}

	@Override
	public String updateQualityAbnormalresume(
			QualityAbnormalresume qualityAbnormalresume) {
		QualityAbnormalresume o_QAR = findQualityAbnormalresume(qualityAbnormalresume
				.getId());
		qualityAbnormalresume.setAttachmentPath(o_QAR.getAttachmentPath()); 
		BeanUtils.copyProperties(qualityAbnormalresume, o_QAR,
				new String[] { "id" });
		if (qualityAbnormalresume != null) {
			if (totalDao.update(o_QAR)) {
				return "true";
			} else
				return "false";
		}
		return "false";
	}

	@Override 
	public QualityAbnormalresume findQualityAbnormalresume(Integer id) {
		QualityAbnormalresume q = (QualityAbnormalresume) totalDao
				.getObjectById(QualityAbnormalresume.class, id);
		if (q != null) {
			return q;
		}
		return null;
	}

	@Override
	public Object[] findallQualityAbnormalresume(
			QualityAbnormalresume qualityAbnormalresume, int pageNo,
			int pageSize) {
		if (qualityAbnormalresume == null) {
			qualityAbnormalresume = new QualityAbnormalresume();
		}
		String hql = totalDao.criteriaQueries(qualityAbnormalresume, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	/**
	 * 质量异常图表数据
	 */
	public Map<String,Object> findqaforPic(String tag,Integer count_tag){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql ="";
		if("chejian".equals(tag)){
			hql ="SELECT count(*),responsibleCo,trackPeople from QualityAbnormalresume GROUP BY responsibleCo ORDER BY count(*) DESC";
		}else if("bianma".equals(tag)){
			hql = "SELECT count(*),code,trackPeople from QualityAbnormalresume GROUP BY code ORDER BY count(*) DESC";
		}else if("leibie".equals(tag)){
			hql = "SELECT count(*),typeOfProblem,trackPeople from QualityAbnormalresume GROUP BY typeOfProblem ORDER BY count(*) DESC ";
		}
		if(hql!=null&&!"".equals(hql)){
			List<Object[]> list = totalDao.query(hql,null);
			List<Object[]> list_top = new ArrayList<Object[]>();
			List<Object[]> list_last =new ArrayList<Object[]>();
			if(count_tag<list.size()){
				list_top = list.subList(0, count_tag);
				list_last = list.subList(count_tag, list.size());
			}else{
				list_top= list;
			}
			if(list_last!=null&&list_last.size()>0){
				String name="其他";
				Long count_other =0L;
				for(int f = 0;f < list_last.size(); f ++){
					count_other += (Long)list_last.get(f)[0];
				}
				Object arr[]=new Object[]{count_other,name,0};
				list_top.add(arr);
			}
			int count_all = totalDao.getCount("from QualityAbnormalresume");
			for(int i = 0;i < list_top.size(); i ++){
				int j=i+1;
				Long leijibuxiang=0L;
				for(int q = 0;q < j; q ++){
					leijibuxiang += (Long)list_top.get(q)[0];
				}
				float f = Float.parseFloat(String.valueOf(count_all));
				list_top.get(i)[2]=(leijibuxiang/f)*100;
			}
			map.put("dateList", list_top);
			map.put("count", count_all);
		}
		return map;
	}

}
