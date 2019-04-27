package com.task.Server.competencetype;

import java.util.Map;

import com.task.entity.CompetenceType;

public interface CompetenceTypeServer {
	public boolean delete(CompetenceType competenceType);
	public CompetenceType findById(Integer id);
	public boolean save(CompetenceType competenceType);
	public boolean update(CompetenceType competenceType);
	public Map<Integer, Object> findAll(CompetenceType competenceType, int pageNo, int pageSize);
}
