package com.task.ServerImpl.sop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.sop.PurchasedPartsInputServer;
import com.task.entity.Users;
import com.task.entity.sop.PurchasedPartsInput;
import com.task.entity.sop.WaigouWaiweiPlan;
import com.task.util.Util;

public class PurchasedPartsInputServerImpl implements PurchasedPartsInputServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public String add(List<WaigouWaiweiPlan> wwPlanList,String acArrivalTime) {
		// TODO Auto-generated method stub
		if(wwPlanList!=null&&wwPlanList.size()>0){
			boolean b=true;
			for(WaigouWaiweiPlan wwPlan:wwPlanList){
				if(wwPlan.getId()!=null&&wwPlan.getHasruku()!=null&&!wwPlan.getHasruku().equals(0)){
					WaigouWaiweiPlan old=(WaigouWaiweiPlan) totalDao.getObjectById(WaigouWaiweiPlan.class, wwPlan.getId());
					//修改可入库数量
					Float keruku=0f;
					if(old.getKeruku()==null){
						keruku=old.getNumber()-wwPlan.getHasruku();
					}else{
						keruku=old.getKeruku()-wwPlan.getHasruku();
					}
					if(keruku<0){
						return "外购件"+old.getMarkId()+"申请入库数量超额";
					}
					old.setKeruku(keruku);
					String time=old.getAcArrivalTime();
					if(time==null||time.equals("")){
						if(acArrivalTime==null||acArrivalTime.equals("")){
							time=Util.getDateTime();
						}else{
							time=acArrivalTime;
						}
					}else{
						if(acArrivalTime==null||acArrivalTime.equals("")){
							time=","+Util.getDateTime();
						}else{
							time=","+acArrivalTime;
						}
					}
					old.setAcArrivalTime(time);
					if(old.getComeCount()==null){
						old.setComeCount(1);
					}else{
						old.setComeCount(old.getComeCount()+1);
					}
					b=b&totalDao.update(old);
//入库的时候再做这些操作
//					//计算该订单总入库数量
//					List<Float> allInCountList=totalDao.query("select sum(inCount) from PurchasedPartsInput where orderId=?", old.getId());
//					Float allInCount=wwPlan.getHasruku();
//					if(allInCountList.size()>0){
//						allInCount+=allInCountList.get(0);
//					}
//					if(old.getNumber()==null||old.getNumber()<allInCount){
//						return "外购件"+old.getMarkId()+"入库超额!";
//					}
//					b=b& totalDao.update(old);
//					if(old.getRukuCount()==null){
//						old.setRukucount(*);
//					}
//					old.setRukuCount(old.getRukuCount()+1);
					//添加外购件入库记录
					PurchasedPartsInput ppInput=new PurchasedPartsInput();
					ppInput.setOrderId(old.getId());
					ppInput.setNumber(Util.getDateTime("yyyyMMdd"));//订单号
					ppInput.setInNumber(Util.getDateTime("yyyyMMdd"));//送货单号
					ppInput.setTime(Util.getDateTime());//入库时间
					ppInput.setName(old.getProName());//名称
					ppInput.setMarkId(old.getMarkId());//件号
					Users user=Util.getLoginUser();
					ppInput.setUserName(user.getName());//经办人名字
					ppInput.setUserCode(user.getCode());//经办人工号
					ppInput.setUserDept(user.getDept());//经办人部门
					ppInput.setCompany(old.getGysName());//供货厂商
					ppInput.setInCount(wwPlan.getHasruku());//入库数量
					ppInput.setSelfCard(old.getSelfCard());//批次号
					ppInput.setStatus("未检测");//状态
					b=b&totalDao.save(ppInput);
				}
			}
			return b+"";
		}
		return "未找到对应的外购件采购计划或没有欲入库的外购件!";
	}

	@Override
	public Map<Integer, Object> findPurchasedPartsInputsByCondition(
			PurchasedPartsInput purchasedPartsInput, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (purchasedPartsInput == null) {
			purchasedPartsInput = new PurchasedPartsInput();
		}
		String hql = totalDao.criteriaQueries(purchasedPartsInput, " 1=1 order by id desc", null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public PurchasedPartsInput getById(Integer id) {
		// TODO Auto-generated method stub
		return (PurchasedPartsInput) totalDao.getObjectById(PurchasedPartsInput.class, id);
	}

	@Override
	public boolean updateCount(PurchasedPartsInput purchasedPartsInput) {
		// TODO Auto-generated method stub
		PurchasedPartsInput old=getById(purchasedPartsInput.getId());
		if(old!=null){
			Integer orderId=old.getOrderId();
			if(orderId!=null){
				WaigouWaiweiPlan wwp=(WaigouWaiweiPlan) totalDao.getObjectById(WaigouWaiweiPlan.class, orderId);
				if(wwp!=null){//将数量还给外购外委采购计划单
					if(wwp.getKeruku()!=null){
						wwp.setKeruku(wwp.getKeruku()-old.getInCount()-purchasedPartsInput.getInCount());
					}
					totalDao.update(wwp);
				}
			}
			old.setInCount(purchasedPartsInput.getInCount());
			return totalDao.update(old);
		}
		return false;
	}

	@Override
	public boolean delete(PurchasedPartsInput purchasedPartsInput) {
		// TODO Auto-generated method stub
		PurchasedPartsInput old=getById(purchasedPartsInput.getId());
		if(old!=null){
			Integer orderId=old.getOrderId();
			if(orderId!=null){
				WaigouWaiweiPlan wwp=(WaigouWaiweiPlan) totalDao.getObjectById(WaigouWaiweiPlan.class, orderId);
				if(wwp!=null){//将数量还给外购外委采购计划单
					if(wwp.getComeCount()!=null){
						wwp.setComeCount(wwp.getComeCount()-1);
					}
					if(wwp.getKeruku()!=null){
						wwp.setKeruku(wwp.getKeruku()+old.getInCount());
					}
					totalDao.update(wwp);
				}
			}
			old.setInCount(purchasedPartsInput.getInCount());
			return totalDao.delete(old);
		}
		return false;
	}

}
