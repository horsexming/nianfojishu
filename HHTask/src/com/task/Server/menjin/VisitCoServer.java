package com.task.Server.menjin;

import com.task.entity.menjin.VisitCo;

@SuppressWarnings("unchecked")
public interface VisitCoServer {

	String addVisitCo(VisitCo visitco);

	VisitCo byIdVisitCo(Integer id);

	String deleteVisitCo(Integer id);

	Object[] findVisitco();

	String updateVisitCo(VisitCo visitco);


}
