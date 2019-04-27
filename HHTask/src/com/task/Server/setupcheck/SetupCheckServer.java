package com.task.Server.setupcheck;

import java.util.List;
import java.util.Map;
import com.task.entity.setupcheck.SetupCheck;
import com.task.entity.setupcheck.TrackRecord;

public interface SetupCheckServer {
	
	public boolean save(SetupCheck setupCheck);
	
	public boolean delete(SetupCheck setupCheck);
	
	public boolean update(SetupCheck setupCheck);
	
	public boolean update1(SetupCheck setupCheck);
	
	public boolean update2(SetupCheck setupCheck);
	
	public SetupCheck findById(Integer id);
	
	public Map<Integer, Object> findAll(SetupCheck setupCheck, int pageNo, int pageSize);
	
	public List<SetupCheck> findWaitDeal(SetupCheck setupCheck,String fStatus);
	
	public Map<Integer, Object> findAllByUser(SetupCheck setupCheck, int pageNo,
			int pageSize);
	
	public String findSamename(Integer id);
	
	public String updateGenzong(Integer id);
	
	public Map<Integer, Object> findAllForZhuizong(SetupCheck setupCheck,
			int pageNo, int pageSize);
	public boolean save(TrackRecord trackRecord);
	public List<TrackRecord> findtrackRecordByid(Integer id);
}
