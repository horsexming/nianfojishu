package com.task.Server.menjin;

import java.util.Map;

import com.task.entity.menjin.DoorBangDing;

public interface DoorBangDingServer {

	//public boolean addDoorBangDing(DoorBangDing doorBangDing);

	public Map<Integer, Object> findGuardCardByCondition(DoorBangDing doorBangDing,
			int parseInt, int pageSize);

	public boolean updateDoorBangDing(DoorBangDing doorBangDing);

	//public boolean deleteDoorBangDing(DoorBangDing doorBangDing);

	public DoorBangDing getByIdDoorBangDing(DoorBangDing doorBangDing);

	

	

}
