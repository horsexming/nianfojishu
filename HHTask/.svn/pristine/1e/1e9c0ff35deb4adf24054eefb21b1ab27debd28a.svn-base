package com.task.ServerImpl.renshi;

/*
 * 李聪   2015-05-08
 * 
 */
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.renshi.InterviewQuizzesServer;
import com.task.entity.renshi.InterviewLog;
import com.task.entity.renshi.InterviewQuizzes;
import com.task.util.Util;

public class InterviewQuizzesServerImpl implements InterviewQuizzesServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean addInterviewQuizzes(List<InterviewQuizzes> interviewQuizzes,
			InterviewLog interviewLog) {
		// TODO Auto-generated method stub
		boolean b = false;
		if (interviewLog != null) {
			interviewLog = (InterviewLog) totalDao.getObjectById(
					InterviewLog.class, interviewLog.getId());
			if (interviewLog != null) {
				if (interviewQuizzes != null && interviewQuizzes.size() > 0) {
					for (InterviewQuizzes interQ : interviewQuizzes) {
						interQ.setAddTime(Util.getDateTime());
						interQ.setInterviewLog(interviewLog);
						b = totalDao.save(interQ);
					}
					if (b) {
						interviewLog.setInter_status("待评价");
					}
				}
			}
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InterviewQuizzes> getinterviewQuizzesById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null && id > 0) {
			String hql = "from InterviewQuizzes where interviewLog.id=?";
			List all = totalDao.query(hql, id);
			if (all.size() > 0) {
				return (List<InterviewQuizzes>) all;
			}
		}
		return null;
	}

}
