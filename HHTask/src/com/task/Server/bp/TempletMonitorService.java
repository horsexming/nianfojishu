package com.task.Server.bp;

import com.task.entity.bp.Templet;

public interface TempletMonitorService {

	boolean update(Templet templet);
	
	boolean agree(Templet templet);
}
