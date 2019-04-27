package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.QuestionTemplateServer;
import com.task.entity.Careertrack;
import com.task.entity.QuestionTemplate;
import com.task.entity.Questionnaire;
import com.task.entity.Users;
import com.task.util.Util;

public class QuestionTemplateServerImpl implements QuestionTemplateServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(QuestionTemplate qt) {
		if (qt != null) {
			if (qt.getQeList() != null && qt.getQeList().size() > 0) {
				List<Questionnaire> qeList = qt.getQeList();
				Set<Questionnaire> qeset = new HashSet<Questionnaire>();
				for (Questionnaire qe : qeList) {
					qeset.add(qe);
				}
				Users user = Util.getLoginUser();
				qt.setAdddept(user.getDept());
				qt.setAddtime(Util.getDateTime());
				qt.setAdduserId(user.getId());
				qt.setAddusername(user.getName());
				qt.setQuestionnaire(qeset);
			}
			return totalDao.save(qt);
		}
		return false;
	}

	@Override
	public boolean del(QuestionTemplate qt) {
		if (qt != null) {
			qt = (QuestionTemplate) totalDao.get(QuestionTemplate.class, qt
					.getId());
			Set<Questionnaire> qeset = qt.getQuestionnaire();
			for (Questionnaire qe : qeset) {
				totalDao.delete(qe);
			}
			qt.setQuestionnaire(null);
			return totalDao.delete(qt);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findqtListByCondition(QuestionTemplate qt,
			int pageNo, int pageSize, String statue) {
		if (qt == null) {
			qt = new QuestionTemplate();
		}
		if ("dept".equals(statue)) {
			Users user = Util.getLoginUser();
			qt.setAdddept(user.getDept());
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(qt, null);
		int count = totalDao.getCount(hql);
		List<QuestionTemplate> ckList = (List<QuestionTemplate>) totalDao
				.findAllByPage(hql+" order by id desc", pageNo, pageSize);
		map.put(1, ckList);
		map.put(2, count);
		return map;
	}

	@Override
	public QuestionTemplate findqtbyId(Integer id) {
		if (id != null && id > 0) {
			return (QuestionTemplate) totalDao.get(QuestionTemplate.class, id);
		}
		return null;
	}

	@Override
	public List<QuestionTemplate> showAllqtList(int pageNo, int pageSize) {
		String hql = "from QuestionTemplate order by id desc";
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public boolean update(QuestionTemplate qt) {
		if (qt != null) {
			QuestionTemplate oldqt = (QuestionTemplate) totalDao.get(
					QuestionTemplate.class, qt.getId());
			Set<Questionnaire> oldqeSet = oldqt.getQuestionnaire();
			for (Questionnaire oldqe : oldqeSet) {
				totalDao.delete(oldqe);
			}
			BeanUtils.copyProperties(qt, oldqt, new String[] { "id" });
			List<Questionnaire> qelist = qt.getQeList();
			Set<Questionnaire> qeSet = new HashSet<Questionnaire>();
			for (Questionnaire qe : qelist) {
				qeSet.add(qe);
			}
			oldqt.setQuestionnaire(qeSet);
			
			return totalDao.update(oldqt);
		}
		return false;
	}

	@Override
	public int getcont() {
		String hql = "from QuestionTemplate";

		return totalDao.getCount(hql);
	}

	@Override
	public Map<Integer, Object> findqeList(Integer id) {
		QuestionTemplate qt = (QuestionTemplate) totalDao.get(
				QuestionTemplate.class, id);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		if (qt != null) {
			map.put(1, qt);
			String hql = "from Questionnaire where questionTemplate = "+qt.getId()+" order by id ";
			List<Questionnaire> qelist=	totalDao.find(hql);
			if(qelist!=null && qelist.size()>0){
				map.put(2, qelist);
			}
		}
		return map;
	}

	@Override
	public boolean delqe(Questionnaire qe) {
		if(qe!=null){ 
			qe = (Questionnaire) totalDao.get(Questionnaire.class,qe.getId());
			qe.setQuestionTemplate(null);
			return 	totalDao.delete(qe);
		}
		return false;
	}

	@Override
	public boolean updateqe(Questionnaire qe) {
		if(qe!=null){
			Questionnaire oldqe=(Questionnaire) totalDao.get(qe.getClass(), qe.getId());
			BeanUtils.copyProperties(qe, oldqe,new String[]{"id,questionTemplate"});
			return totalDao.update(oldqe);
		}
		return false;
	}

	@Override
	public Questionnaire findqebyId(Integer id) {
		if(id!=null && id>0){
			return (Questionnaire) totalDao.get(Questionnaire.class, id);
		}
		return null;
	}

}
