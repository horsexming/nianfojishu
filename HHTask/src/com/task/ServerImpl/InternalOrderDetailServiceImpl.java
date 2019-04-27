package com.task.ServerImpl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.IInternalOrderDetailService;
import com.task.Server.IInternalOrderService;
import com.task.entity.InternalOrder;
import com.task.entity.InternalOrderDetail;
import com.task.entity.OrderManager;
import com.task.entity.ProductManager;
import com.task.entity.sop.ProcardTemplate;

public class InternalOrderDetailServiceImpl implements
		IInternalOrderDetailService {

	private TotalDao totalDao;
	private IInternalOrderService ios;

	public void add(InternalOrderDetail io) {
		totalDao.save(io);
	}

	public void del(InternalOrderDetail io) {
		totalDao.delete(io);
	}

	public void delInternalObjectById(int id) {
		InternalOrderDetail obj = getInternalOrderDetailById(id);
		totalDao.delete(obj);
	}

	public InternalOrderDetail getInternalOrderDetailById(int id) {
		return (InternalOrderDetail) totalDao.getObjectById(
				InternalOrderDetail.class, id);
	}

	public void update(InternalOrderDetail io) {
		InternalOrderDetail oldIo = getInternalOrderDetailById(io.getId());
		if (oldIo.getNum().floatValue() == io.getNum().floatValue()) {
			BeanUtils.copyProperties(io, oldIo, new String[] { "id", "name",
					"pieceNumber", "internalOrder" });
			totalDao.update(oldIo);
			return;
		}
		int ioId = io.getInternalOrder().getId();
		InternalOrder interOrder = ios.getInternalOrderById(ioId);
		Float assignedNum = oldIo.getNum();
		/**
		 * 循环把原来的分配的数量先还原回去
		 */
		for (Iterator<OrderManager> it = interOrder.getOuterOrders().iterator(); it
				.hasNext();) {
			OrderManager om = it.next();
			if (assignedNum == 0) {
				break;
			}
			for (Iterator<ProductManager> pmIt = om.getProducts().iterator(); pmIt
					.hasNext();) {
				ProductManager pm = pmIt.next();
				if (io.getPieceNumber().equals(pm.getPieceNumber())) {
					Float sumNum = assignedNum + pm.getAllocationsNum();
					if (sumNum.floatValue() <= pm.getNum().floatValue()) {
						pm.setAllocationsNum(sumNum);
						assignedNum = pm.getNum() - sumNum;
						assignedNum = 0f;
					} else {
						assignedNum = sumNum - pm.getNum();
						pm.setAllocationsNum(pm.getNum());
					}
					totalDao.update(pm);
				}
			}
		}
		/**
		 * 再进行新分配数量的修改
		 */
		Float undistributed = io.getNum();
		for (Iterator<OrderManager> it = interOrder.getOuterOrders().iterator(); it
				.hasNext();) {
			OrderManager om = it.next();
			if (undistributed == 0) {
				break;
			}
			for (Iterator<ProductManager> pmIt = om.getProducts().iterator(); pmIt
					.hasNext();) {
				ProductManager pm = pmIt.next();
				if (pm.getPieceNumber().equals(io.getPieceNumber())) {
					if (undistributed.floatValue() <= pm.getAllocationsNum().floatValue()) {
						Float undistributedNum = pm.getAllocationsNum()
								- undistributed;
						pm.setAllocationsNum(undistributedNum);
						undistributed = 0f;
					} else {
						undistributed = undistributed - pm.getAllocationsNum();
						pm.setAllocationsNum(0f);
					}
					totalDao.update(pm);
				}
			}
		}
		if (undistributed == 0) {
			BeanUtils.copyProperties(io, oldIo, new String[] { "id", "name",
					"pieceNumber", "internalOrder" });
			totalDao.update(oldIo);
			// totalDao.createSession().merge(oldIo);
		}

	}

	public List queryInternalOrderDetailById(int id) {
		String hql = "from InternalOrderDetail i where i.internalOrder.id = ? and  productManagerId in "
				+ "(select id from ProductManager where id in "
				+ "(select productManagerId from InternalOrderDetail where internalOrder.id = ?) and (status<>'取消' or status is null))";
		List list = totalDao.query(hql, id, id);
		// if(list != null && list.size() > 0){
		// for (int i = 0; i < list.size(); i++) {
		// InternalOrderDetail iod=(InternalOrderDetail) list.get(i);
		// if(iod.getYwMarkId()==null){
		// iod.setYwMarkId(iod.getPieceNumber());
		// }
		// String
		// hql_bomSelf="from ProcardTemplate where (markId=? or ywMarkId=?) and productStyle='批产'";
		// ProcardTemplate pt=(ProcardTemplate)
		// totalDao.getObjectByCondition(hql_bomSelf,
		// iod.getPieceNumber(),iod.getYwMarkId());
		// if(pt!=null){
		// iod.setRemark("单批最大数量:"+pt.getMaxCount()+"<br/>");
		// }
		// }
		// return list;
		// }
		return list;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public IInternalOrderService getIos() {
		return ios;
	}

	public void setIos(IInternalOrderService ios) {
		this.ios = ios;
	}

}
