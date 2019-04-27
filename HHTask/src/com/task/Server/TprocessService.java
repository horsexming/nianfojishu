package com.task.Server;

import java.util.List;

import com.task.entity.Project;
import com.task.entity.Tdetail;
import com.task.entity.Tprocess;

/**
 * 工序
 * @author 马凯
 *
 */
public interface TprocessService {
	
	public List<Tdetail> listDetail(Project p);

	public Tdetail getDetail(Tdetail detail);

	public void add(List<Tprocess> processes);
}
