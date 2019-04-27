package com.task.ServerImpl.sop;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ess.GoodsStoreServer;
import com.task.Server.sop.SCTuiliaoSqDanServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.GoodsStore;
import com.task.entity.Users;
import com.task.entity.sop.DefectiveProduct;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.SCTuiliaoSqDan;
import com.task.entity.sop.WaigouDeliveryDetail;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

public class SCTuiliaoSqDanServerImpl implements SCTuiliaoSqDanServer {
	private TotalDao totalDao;
	private GoodsStoreServer goodsStoreServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public GoodsStoreServer getGoodsStoreServer() {
		return goodsStoreServer;
	}

	public void setGoodsStoreServer(GoodsStoreServer goodsStoreServer) {
		this.goodsStoreServer = goodsStoreServer;
	}

	@Override
	public boolean addSctuiliaoSqDan(SCTuiliaoSqDan sqd) {
		if (sqd != null) {
			Users user = Util.getLoginUser();
			sqd.setAddTime(Util.getDateTime());
			sqd.setAdduser(user.getName());
			sqd.setAddusercode(user.getCode());
			totalDao.save(sqd);
			//把procard上的hascount 加回去;
			if(sqd.getProcardId()!=null){
				Procard procard =	(Procard) totalDao.get(Procard.class, sqd.getProcardId());
				Procard fathrProcard = (Procard) totalDao.get(Procard.class,procard.getFatherId());
				
				Float hascount = procard.getHascount();
				if(hascount == null){
					hascount = 0f;
				}
				procard.setHascount(hascount+sqd.getTlNumber());
				Float quanzi2 = procard.getQuanzi2();
				if(quanzi2 == null){
					quanzi2 = 1f;
				}
				Float fathrhascount = fathrProcard.getHascount();
				if(fathrhascount == null){
					fathrhascount = 0f;
				}
				fathrProcard.setHascount(fathrhascount+(sqd.getTlNumber()/quanzi2));
				List<ProcessInfor> processList =	totalDao.query(" from ProcessInfor where procard = ?", fathrProcard.getId());
				for (ProcessInfor processInfor : processList) {
					processInfor.setTotalCount(processInfor.getTotalCount()-(sqd.getTlNumber()/quanzi2));
					totalDao.update(processInfor);
				}
				totalDao.update(fathrProcard);
				totalDao.update(procard);
			}
		
			String processName = "生产退料申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						SCTuiliaoSqDan.class, sqd.getId(), "epStatus", "id",
						"SCTuiliaoSqDanAction_findSctuiliaoSqDanById.action?id="
								+ sqd.getId() + "&pageStatus=show", user
								.getDept()
								+ "部门 " + user.getName() + "生产退料申请，请您审批", true);
				if (epId != null && epId > 0) {
					sqd.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						sqd.setEpStatus("同意");
						// 同意后，把退料数量放入不合格品库,并创建不良品处理单；
//						GoodsStore goodsStore = new GoodsStore();
//						goodsStore.setGoodsStoreMarkId(sqd.getMarkId());// 件号
//						goodsStore.setGoodsStoreWarehouse("不合格品库");// 库别
//						goodsStore.setBanBenNumber(sqd.getBanBenNumber());// 版本
//						goodsStore.setKgliao(sqd.getKgliao());// 供料属性
//						goodsStore.setGoodsStoreLot(sqd.getExamineLot());// 批次
//						goodsStore.setHsPrice(sqd.getHsPrice());// 含税单价
//						goodsStore.setGoodsStoreGoodsName(sqd.getProName());// 名称
//						goodsStore.setGoodsStoreFormat(sqd.getSpecification());// 规格
//						goodsStore.setWgType(sqd.getWgType());// 物料类别
//						goodsStore.setGoodsStoreUnit(sqd.getUnit());// 单位
//						goodsStore.setGoodsStoreCount(sqd.getTlNumber());// 数量
//						goodsStore.setGoodsStorePrice(sqd.getBhsPrice());// 不含税单价
//						// 总额
//						goodsStore.setPriceId(sqd.getPriceId());// 价格Id
//						goodsStore.setGoodsStoreSupplier(sqd.getGys());// 供应商名称
//						goodsStore.setGysId(sqd.getZhuserId()+"");// 供应商Id
//						goodsStore
//								.setGoodsStoreDate(Util.getDateTime("yyyy-MM-dd"));
//						goodsStore.setGoodsStoreTime(Util.getDateTime());
//						String hql_wgdd = " from  WaigouDeliveryDetail where markId = ? and examineLot = ?";
//						WaigouDeliveryDetail wgdd =	(WaigouDeliveryDetail) totalDao.getObjectByCondition(hql_wgdd, sqd.getMarkId(),sqd.getExamineLot());
//						String cgOrderNum = "暂无";
//						String shOrderNum = "暂无";
//						String gysName = "暂无";
//						String gysId = "0";
//						float hsprice = 0;
//						float bhsprice = 0;
//						double taxprice = 0;
//						int priceId =0;
//						int gysuserId=0;
//						int waiorderId = 0;
//						if(wgdd!=null){
//							goodsStore.setWwddId(wgdd.getWaigouDelivery().getId());// 送货单Id
//							goodsStore.setGoodsStoreSendId(wgdd.getWaigouDelivery().getPlanNumber());// 送货单号
//							goodsStore.setGoodsStoreSupplier(wgdd.getGysName());// 供应商名称
//							goodsStore.setGysId(wgdd.getGysId()+"");// 供应商Id
//							cgOrderNum = wgdd.getCgOrderNum();
//							shOrderNum = wgdd.getWaigouDelivery().getPlanNumber();
//							gysName = wgdd.getGysName();
//							gysId = wgdd.getGysId()+"";
//							priceId = wgdd.getPriceId();
//							hsprice = wgdd.getHsPrice();
//							bhsprice = wgdd.getPrice();
//							taxprice = wgdd.getTaxprice(); 
//							gysuserId = wgdd.getWaigouDelivery().getUserId();
//							waiorderId = wgdd.getWaigouDelivery().getId();
//							goodsStore.setTaxprice(taxprice);// 税率
//						}
//						goodsStore.setTuhao(sqd.getTuhao());// 图号
//						goodsStoreServer.saveSgrk(goodsStore);
//						//判断同件号，同检验批次是否存在不良品处理单;
//						DefectiveProduct dp = null;
//						if(sqd.getMarkId()!=null && sqd.getMarkId().length()>0
//								&& sqd.getExamineLot()!=null && sqd.getExamineLot().length()>0){
//							String hql = " from DefectiveProduct where markId = ? and examineLot = ? and status = '待确认' and type = '外购在库不良' ";
//							 dp =	(DefectiveProduct) totalDao.getObjectByCondition(hql, sqd.getMarkId(),sqd.getExamineLot());
//						}
//						if(dp!=null){
//							dp.setDbNumber(sqd.getTlNumber()+dp.getDbNumber());
//							totalDao.update(dp);
//						}else{
//							dp = new DefectiveProduct();
//							dp.setCgOrderNum(cgOrderNum);//采购订单号
//							dp.setShOrderNum(shOrderNum);//送货单号;
//							dp.setMarkId(sqd.getMarkId());;//件号
//							dp.setKgliao(sqd.getKgliao());//供料属性（自购、指定、客供）
//							dp.setBanben(sqd.getBanBenNumber());//版本
//							dp.setTuhao(sqd.getTuhao());//图号
//							dp.setProName(sqd.getProName());//零件名称、
//							dp.setWgType(sqd.getWgType());//物料类别
//							dp.setSpecification(sqd.getSpecification());//规格
//							dp.setUnit(sqd.getUnit());//单位
//							dp.setHsPrice(hsprice);//含税单价
//							dp.setPrice(bhsprice);//不含税单价
//							dp.setTaxprice(taxprice);//税率
//							dp.setPriceId(priceId);//价格Id
//							dp.setGysName(gysName);//供应商名称
//							dp.setGysId(Integer.parseInt(gysId));//供应商Id;
//							dp.setGysUsersId(gysuserId);//供应商UserId
//							dp.setExamineLot(sqd.getExamineLot());//检验批次
//							dp.setWgddId(waiorderId);//送货单Id
//							dp.setType("外购在库不良");
//							dp.setStatus("待确认");
//							dp.setDbNumber(sqd.getTlNumber());//调拨数量
//							dp.setLlNumber(sqd.getLlNumber());//来料数量
//							dp.setJyNumber(sqd.getLlNumber());//检验数量
//							dp.setJyhgNumber(sqd.getLlNumber()-sqd.getTlNumber());//检验合格数量
//							dp.setJybhgNumber(sqd.getTlNumber());//检验不合格数量
//							dp.setJyuserId(user.getId());
//							dp.setJyuserCode(user.getCode());
//							dp.setJyuserName(user.getName());
//							dp.setAddTime(Util.getDateTime());//添加时间
//							dp.setAddUser(Util.getLoginUser().getName());//添加人
//							totalDao.save(dp);
//						}

					} else {
						sqd.setEpStatus("未审批");
					}
					return totalDao.update(sqd);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delSctuiliaoSqDan(SCTuiliaoSqDan sqd) {
		if (sqd != null) {
			sqd = (SCTuiliaoSqDan) totalDao.get(SCTuiliaoSqDan.class, sqd
					.getId());
			if ("未审批".equals(sqd.getEpStatus())
					|| "打回".equals(sqd.getEpStatus())) {
				return totalDao.delete(sqd);
			}
		}
		return false;
	}

	@Override
	public Object[] findAllSctuiliaoSqDan(SCTuiliaoSqDan sqd, int pageNo,
			int pageSize, String pageStatus) {
		if (sqd == null) {
			sqd = new SCTuiliaoSqDan();
		}
		String hql = totalDao.criteriaQueries(sqd, null);
		List<SCTuiliaoSqDan> sqdList = totalDao.findAllByPage(hql, pageNo,
				pageSize);
		int count = totalDao.getCount(hql);
		Object[] obj = { sqdList, count };
		return obj;
	}

	@Override
	public SCTuiliaoSqDan findSctuiliaoSqDanById(Integer id) {
		if (id != null) {
			return (SCTuiliaoSqDan) totalDao.get(SCTuiliaoSqDan.class, id);
		}
		return null;
	}

	@Override
	public boolean updateSctuiliaoSqDan(SCTuiliaoSqDan sqd) {

		return false;
	}

	@Override
	public Procard findProcardOne(String markId, String selfCard) {
		if (markId != null && markId.length() > 0 && selfCard != null
				&& selfCard.length() > 0) {
			String hql = " from Procard where markId = ? and selfCard = ?  ";
			return (Procard) totalDao.getObjectByCondition(hql, markId,
					selfCard);
		}
		return null;
	}

	@Override
	public String addmoresqd(SCTuiliaoSqDan sqd, SCTuiliaoSqDan[] sqdArrays) {
		if (sqd != null) {
			if (sqdArrays != null && sqdArrays.length > 0) {
				boolean bool = true;
				for (int i = 0; i < sqdArrays.length; i++) {
					SCTuiliaoSqDan s = sqdArrays[i];
					BeanUtils.copyProperties(sqd, s,new String[]{"llNumber","tlNumber","examineLot"});
					if (!addSctuiliaoSqDan(s)) {
						bool = false;
						break;
					}
				}
				return bool + "";
			} else {
				return "请先选择物料追踪批次!";
			}
		}
		return "请刷新后重试，谢谢!";
	}

}
