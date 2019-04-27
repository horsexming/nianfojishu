package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import net.sf.morph.wrap.Bean;

import com.task.Dao.TotalDao;
import com.task.Server.QuestionnairePersonServer;
import com.task.entity.QuestionnairePerson;
import com.task.entity.QuestionnaireUse;
import com.task.util.Util;

public class QuestionnairePersonServerImpl implements QuestionnairePersonServer{

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String add(QuestionnairePerson qp) {
		if(qp!=null){
			List<QuestionnaireUse> qulist = qp.getQulist();
			Set<QuestionnaireUse> quset = new HashSet<QuestionnaireUse>();
			for (QuestionnaireUse qu : qulist) {
				quset.add(qu);
			}
			qp.setDctime(Util.getDateTime());
			qp.setQueset(quset);
			return totalDao.save(qp)+"";
		}
		return "error";
	}

	@Override
	public boolean del(QuestionnairePerson qp) {
		if(qp!=null){
			qp = (QuestionnairePerson) totalDao.get(QuestionnairePerson.class, qp.getId());
			Set<QuestionnaireUse> quset = qp.getQueset();
			for (QuestionnaireUse qu : quset) {
				totalDao.delete(qu);
			}
			qp.setQueset(null);
			return totalDao.delete(qp);
			
		}
		return false;
	}

	@Override
	public List<QuestionnairePerson> findAllQUlist(int pageNo, int pageSize) {
		String hql = "from QuestionnairePerson order by id desc";
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public Map<Integer, Object> findQUlistByCondition(QuestionnairePerson qp,
			int pageNo, int pageSize, String statue) {
		if (qp == null) {
			qp = new QuestionnairePerson();
		}
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(qp, null);
		int count = totalDao.getCount(hql);
		List<QuestionnairePerson> qpList = (List<QuestionnairePerson>) totalDao
				.findAllByPage(hql+" order by id desc", pageNo, pageSize);
		map.put(1, qpList);
		map.put(2, count);
		return map;
	}

	@Override
	public Map<Integer, Object>  findQpByid(Integer id) {
		Map<Integer, Object>  map = null;
		if(id!=null && id>0){
			  map = new HashMap<Integer, Object>();
			QuestionnairePerson qp=	(QuestionnairePerson) totalDao.get(QuestionnairePerson.class, id);
			if(qp!=null){
				map.put(1,qp);
				String hql = "from QuestionnaireUse where qpn="+qp.getId()+" order by id";
				List<QuestionnaireUse> qulist = totalDao.find(hql);
				if(qulist!=null && qulist.size()>0){
					map.put(2, qulist);
				}
			}
		}

		return map;
	}

	@Override
	public boolean update(QuestionnairePerson qp) {
		if(qp!=null){
			QuestionnairePerson 	oldqp =	(QuestionnairePerson) totalDao.get(QuestionnairePerson.class,qp.getId());
			Set<QuestionnaireUse> oldquset = oldqp.getQueset();
			for (QuestionnaireUse qu : oldquset) {
				totalDao.delete(qu);
			}
			BeanUtils.copyProperties(qp, oldqp,new String[]{"id"});
			List<QuestionnaireUse> qulist = qp.getQulist();
			Set<QuestionnaireUse> quset = new HashSet<QuestionnaireUse>();
			for (QuestionnaireUse q : qulist) {
				quset.add(q);
			}
			oldqp.setQueset(quset);
			return totalDao.update(oldqp);
		} 
		return false;
	}

	@Override
	public int getcont() {
		String hql = " from QuestionnairePerson";
		return totalDao.getCount(hql);
	}
	
	
}
