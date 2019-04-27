package com.task.Server;

import java.util.List;

import com.task.entity.InternalOrder;
import com.task.entity.InternalOrderDetail;

public interface IInternalOrderDetailService {
	public void add(InternalOrderDetail io);
	public void delInternalObjectById(int id);
	public void del(InternalOrderDetail io);
	public void update(InternalOrderDetail io);
	public InternalOrderDetail getInternalOrderDetailById(int id);
	public List queryInternalOrderDetailById(int id);

}
