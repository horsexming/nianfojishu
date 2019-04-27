package com.task.Server.kq;

import java.util.Date;
import java.util.List;

import com.task.entity.Consume;



/**
 * @author 曾建森
 * @FileNam IConsumeService.java
 * @Date 2012-10-9
 */
public interface IConsumeService {
	public void add(Consume cs);
	public void delById(int id);
	public void del(Consume cs);
	public void update(Consume cs);
	public List<Consume> queryAllConsume();
	public Consume getConsumeById(int id);
	public Consume getConsumeByPersonId(int personId);
	public int getConsumebeByDateAndId(int id,Date startTime, Date endTime);
	public int getConsumebeById(int id);
}
