package com.task.ServerImpl.pro;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.pro.ProjectMaterialOrderServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Goods;
import com.task.entity.Sell;
import com.task.entity.Users;
import com.task.entity.project.ProjectManage;
import com.task.entity.project.ProjectMaterial;
import com.task.entity.project.ProjectMaterialOrder;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.system.CircuitRun;
import com.task.util.DateUtil;
import com.task.util.Util;

/***
 * 研发项目材料清单
 * 
 * @author txb
 * 
 */
public class ProjectMaterialOrderServerImpl implements
		ProjectMaterialOrderServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(ProjectMaterialOrder projectMaterialOrder,
			Set<ProjectMaterial> pmSet) throws Exception{
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		projectMaterialOrder.setUsercode(user.getCode());
		projectMaterialOrder.setUserName(user.getName());
		projectMaterialOrder.setUserId(user.getId());
		projectMaterialOrder.setAddTime(DateUtil.formatDate(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		projectMaterialOrder.setReceiveStatus("未领");
		int maxNo = 0;
		String qianzui1 = "PMO-" + DateUtil.formatDate(new Date(), "yyyyMMdd")
				+ "-";
		List<ProjectMaterialOrder> pmolist = findAll();
		if (pmolist != null) {// 设计订单号
			for (ProjectMaterialOrder p : pmolist) {
				if (p.getOrderNo() != null
						&& p.getOrderNo().startsWith(qianzui1)) {
					String numStr = p.getOrderNo().replaceFirst(qianzui1, "");
					try {
						int num = Integer.parseInt(numStr);
						if (maxNo < num) {
							maxNo = num;
						}
					} catch (Exception e) {
						return false;
					}
				}
			}
		}
		maxNo++;
		projectMaterialOrder.setOrderNo(qianzui1 + maxNo);
		projectMaterialOrder.setProjectMaterial(pmSet);
		projectMaterialOrder.setAduitStatus("未审批");
		boolean b= totalDao.save(projectMaterialOrder);
		if(b){
			Integer epId = CircuitRunServerImpl.createProcess("研发项目材料申请",
					ProjectMaterialOrder.class, projectMaterialOrder.getId(),
					"aduitStatus", "id",
					"projectMaterialOrderAction_pmdetail.action?projectMaterialOrder.id="
							+ projectMaterialOrder.getId(), "研发项目材料清单申请审批",
					true);
			if (epId != null) {
				projectMaterialOrder.setEpId(epId);
				return totalDao.update(projectMaterialOrder);
			}
		}
		return false;
	}


	@Override
	public boolean canBeChange(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		ProjectMaterialOrder pmo = getById(id);
		if (pmo != null) {
			boolean b = true;
			// Set<ProjectMaterial> pmSet=pmo.getProjectMaterial();
			// pmo.setProjectMaterial(null);
			// if(pmSet!=null&&pmSet.size()>0){
			// for(ProjectMaterial pm:pmSet){
			// pm.setProjectMaterialOrder(null);
			// b=b&totalDao.delete(pm);
			// }
			// }
			if(pmo.getEpId()!=null){
				CircuitRun cr=(CircuitRun) totalDao.getObjectById(CircuitRun.class, pmo.getEpId());
				if(cr!=null){
					b=b&totalDao.delete(cr);
				}
			}
			b = b & totalDao.delete(pmo);
			return b;
		}
		return false;
	}

	@Override
	public List<ProjectMaterialOrder> findAll() {
		// TODO Auto-generated method stub
		return (List<ProjectMaterialOrder>) totalDao
				.query("from ProjectMaterialOrder where 1=1");
	}

	@Override
	public Map<Integer, Object> findProjectMaterialOrdersByCondition(
			ProjectMaterialOrder projectMaterialOrder, int pageNo,
			int pageSize, String sql) {
		// TODO Auto-generated method stub
		if (projectMaterialOrder == null) {
			projectMaterialOrder = new ProjectMaterialOrder();
		}
		String hql = totalDao.criteriaQueries(projectMaterialOrder, sql, null);
		hql+=" and (isbaomi is null or isbaomi!='是') ";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	@Override
	public Map<Integer, Object> findbmProjectMaterialOrdersByCondition(
			ProjectMaterialOrder projectMaterialOrder, int pageNo,
			int pageSize, String sql,String isbaomi) {
		// TODO Auto-generated method stub
		if (projectMaterialOrder == null) {
			projectMaterialOrder = new ProjectMaterialOrder();
		}
		String hql = totalDao.criteriaQueries(projectMaterialOrder, sql, null);
		if(isbaomi==null || !"是".equals(isbaomi)){
			isbaomi="否";
		}
		hql+=" and isbaomi='"+isbaomi+"' ";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public ProjectMaterialOrder getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			return (ProjectMaterialOrder) totalDao.getObjectById(
					ProjectMaterialOrder.class, id);
		}
		return null;
	}

	@Override
	public boolean update(ProjectMaterialOrder projectMaterialOrder,
			Set<ProjectMaterial> pmSet) {
		// TODO Auto-generated method stub
		boolean b = true;
		ProjectMaterialOrder pmo = getById(projectMaterialOrder.getId());
		pmo.setProName(projectMaterialOrder.getProName());
		pmo.setRemark(projectMaterialOrder.getRemark());
		List<ProjectMaterial> pmList = findMaterialByOrderId(projectMaterialOrder
				.getId());
		for (ProjectMaterial pm1 : pmSet) {
			pm1.setProjectMaterialOrder(pmo);
		}
		if (pmList.size() > 0) {
			List<Integer> oldpmIdList = new ArrayList<Integer>();
			for (ProjectMaterial pm2 : pmSet) {
				oldpmIdList.add(pm2.getId());
			}
			for (ProjectMaterial pm3 : pmList) {
				if (!oldpmIdList.contains(pm3.getId())) {
					pm3.setProjectMaterialOrder(null);
					b = b & totalDao.delete(pm3);
				}
			}
			for (ProjectMaterial pm4 : pmSet) {
				if (pm4.getId() == null) {
					b = b & totalDao.save(pm4);
				} else {
					for (ProjectMaterial pm5 : pmList) {
						if (pm5.getId().equals(pm4.getId())) {
							BeanUtils.copyProperties(pm4, pm5,
									new String[] { "projectMaterialOrder" });
							pm5.setProjectMaterialOrder(pmo);
							b = b & totalDao.update(pm5);
						}
					}
				}
			}
		}
       if(pmo.getAduitStatus()!=null&&pmo.getAduitStatus().equals("打回")){
    	  b=b& CircuitRunServerImpl.updateCircuitRun(pmo.getEpId());
    	  if(b){
    		  pmo.setAduitStatus("未审批");
    	  }
       }
		b = b & totalDao.update(pmo);
		return b;
	}

	@Override
	public List<ProjectManage> findAllProjectManage() {
		// TODO Auto-generated method stub
		return (List<ProjectManage>) totalDao
				.query("from ProjectManage where 1=1");
	}

	@Override
	public List<ProjectMaterial> findMaterialByOrderId(Integer id) {
		// TODO Auto-generated method stub
		return (List<ProjectMaterial>) totalDao.query(
				"from ProjectMaterial where projectMaterialOrder.id=?", id);
	}

	@Override
	public Map<Integer, Object> toReceive(Integer id) {
		// TODO Auto-generated method stub
		ProjectMaterialOrder pmo = getById(id);
		if (pmo != null) {
			Set<ProjectMaterial> pmSet = pmo.getProjectMaterial();
			if (pmSet != null && pmSet.size() > 0) {
				Map<Integer, Object> map = new HashMap<Integer, Object>();
				List<ProjectMaterial> pmList = new ArrayList<ProjectMaterial>();
				Integer cannotreceive = 0;
				for (ProjectMaterial pm : pmSet) {
					Float needNumber = pm.getNeedNumber();
					if (needNumber != null && needNumber > 0) {
						if(pm.getGuige()!=null){//有规格表示为原材料
						String hqlGoods = " from Goods where goodsMarkId='"
								+ pm.getPaihao()
								+ "' and goodsFormat='"
								+ pm.getGuige()
								+ "' and goodsClass='原材料库' and goodsCurQuantity>0 order by goodsLotId asc";
						List<Goods> listG = totalDao.find(hqlGoods);
						String isGuanLiao="no";
						Float shifa=needNumber;
						if (listG.size() > 0) {
							for (Goods good : listG) {
								if(!good.getGoodsUnit().equals(pm.getUnit())
										&&good.getGoodsZhishu()!=null&&good.getGoodsZhishu()>0){//有支数
									isGuanLiao="yes";//是管料
									pm.setShifaUnit(good.getGoodsUnit());
									if (good.getGoodsZhishu() >= needNumber) {
										needNumber = 0f;
										break;
									} else {
										needNumber = needNumber
										- good.getGoodsZhishu();
									}
								}else if(good.getGoodsUnit().equals(pm.getUnit())){
									if (good.getGoodsCurQuantity() != null
											&& good.getGoodsCurQuantity() >= needNumber) {
										needNumber = 0f;
										break;
									} else {
										needNumber = needNumber
										- good.getGoodsCurQuantity();
									}
								}
							}
							if (needNumber == 0) {
								pm.setCanReceive("是"); // 库存足够可以领取
								pm.setShifa(shifa);
							} else {
								pm.setCanReceive("否"); // 库存不足
								cannotreceive++;
							}
						} else {
							pm.setCanReceive("否"); // 没有库存不可领取
							cannotreceive++;
						}
						pm.setIsGuanLiao(isGuanLiao);
					 }else{//没有规格表示为外购件
						 String hqlGoods = " from Goods where goodsMarkId='"
								+ pm.getMarkId()
								+ "' and goodsClass in('外购件库','中间库') and goodsCurQuantity>0 order by goodsLotId asc";
						List<Goods> listG = totalDao.find(hqlGoods);
						String isGuanLiao="no";
						Float shifa=needNumber;
						if (listG.size() > 0) {
							for (Goods good : listG) {
								if(good.getGoodsUnit().equals(pm.getUnit())){
									if (good.getGoodsCurQuantity() != null
											&& good.getGoodsCurQuantity() >= needNumber) {
										needNumber = 0f;
										break;
									} else {
										needNumber = needNumber
										- good.getGoodsCurQuantity();
									}
								}
							}
							if (needNumber == 0) {
								pm.setCanReceive("是"); // 库存足够可以领取
								pm.setShifa(shifa);
							} else {
								pm.setCanReceive("否"); // 库存不足
								cannotreceive++;
							}
						} else {
							pm.setCanReceive("否"); // 没有库存不可领取
							cannotreceive++;
						}
						pm.setIsGuanLiao(isGuanLiao);
					 }
						
					} else {
						pm.setCanReceive("是");// 没有需求数量，无所谓领取不领取
						pm.setShifa(0f);
					}
					pmList.add(pm);
				}
				map.put(1, pmo);
				map.put(2, pmList);
				map.put(3, cannotreceive);
				return map;
			}
		}
		return null;
	}

	@Override
	public boolean receiveMaterial(Integer id,String idshifa,Users user) throws Exception {
		// TODO Auto-generated method stub
		ProjectMaterialOrder pmo = getById(id);
		if (pmo != null) {
			if(pmo.getReceiveStatus()!=null&&pmo.getReceiveStatus().equals("已领")){
				return false;
			}
			boolean b = true;
			Set<ProjectMaterial> pmSet = pmo.getProjectMaterial();
			Users user2 = Util.getLoginUser();
			if (pmSet != null && pmSet.size() > 0) {
				String[] idshifas=idshifa.split(",");
				String[][]idshifas2=new String[idshifas.length][2];
				for(int i=0;i<idshifas.length;i++){
					String[] idshifas3=idshifas[i].split(":");
					idshifas2[i][0]=idshifas3[0];
					idshifas2[i][1]=idshifas3[1];
				}
				for (ProjectMaterial pm : pmSet) {
					Float shifa=0f;
					for(int j=0;j<idshifas.length;j++){
						Integer pmId=Integer.parseInt(idshifas2[j][0]);
						if(pmId.equals(pm.getId())){
							shifa=Float.parseFloat(idshifas2[j][1]);
						}
					}
					Float needNumber = pm.getNeedNumber();
					if (needNumber != null && needNumber > 0) {
						String hqlGoods =null;
						if(pm.getGuige()!=null){
							hqlGoods = " from Goods where goodsMarkId='"
								+ pm.getPaihao()
								+ "' and goodsFormat='"
								+ pm.getGuige()
								+ "' and goodsClass='原材料库' and goodsCurQuantity>0 order by goodsLotId asc";
						}else{
							hqlGoods = " from Goods where goodsMarkId='"
								+ pm.getMarkId()
								+ "' and goodsClass in('外购件库','中间库') and goodsCurQuantity>0 order by goodsLotId asc";
						}
						List<Goods> listG = totalDao.find(hqlGoods);
						if (listG.size() > 0) {
							for (Goods good : listG) {
								Sell sell = new Sell();
								sell.setSellArtsCard(good.getGoodsArtsCard());
								sell.setSellSupplier(good.getGoodsSupplier());
								sell.setSellFormat(good.getGoodsFormat());
								sell.setSellLot(good.getGoodsLotId());
								sell.setSellMarkId(good.getGoodsMarkId());
								sell.setSellAdminName(user2.getName());
								sell.setSellGoods(good.getGoodsFullName());
								sell.setSellDate(DateUtil.formatDate(
										new Date(), "yyyy-MM-dd"));
								sell.setSellTime(DateUtil.formatDate(
										new Date(), "yyyy-MM-dd"));
								sell.setSellWarehouse(good.getGoodsClass());
								sell.setSellUnit(good.getGoodsUnit());
								/***
								 * TODO //更具卡号查选
								 */
								sell.setSellCharger(user.getName());
								// --------------------------------
								sell.setSellProMarkId(pm.getMarkId());

								sell.setPrintStatus("NO");
								sell.setStyle("正常出库");
								sell.setGoodsId(good.getGoodsId());
								sell.setSellPeople("待确认");
								sell.setSellAdminName(user2.getName());
								if(!good.getGoodsUnit().equals(pm.getUnit())
										&&good.getGoodsZhishu()!=null&&good.getGoodsZhishu()>0){//有支数
									if (good.getGoodsCurQuantity() >= shifa&&shifa!=0) {
										sell.setSellCount(shifa);
										good.setGoodsCurQuantity(good
												.getGoodsCurQuantity()
												- shifa);
										shifa = 0f;
									} else if(shifa!=0){
										sell.setSellCount(good
												.getGoodsCurQuantity());
										shifa -= good.getGoodsCurQuantity();
										good.setGoodsCurQuantity(0f);
									}
									if(good.getGoodsZhishu()>=needNumber&&needNumber!=0){
										good.setGoodsZhishu(good.getGoodsZhishu()-needNumber);
										sell.setSellZhishu(needNumber);
										needNumber=0f;
									}else if(needNumber!=0){
										sell.setSellZhishu(good.getGoodsZhishu());
										needNumber -= good.getGoodsZhishu();
										good.setGoodsZhishu(0f);
									}
									b=b&totalDao.update(good);
									b=b&totalDao.save(sell);
									if(needNumber==0&&shifa==0){
										break;
									}
								}else if(good.getGoodsUnit().equals(pm.getUnit())){
									if (good.getGoodsCurQuantity() >= shifa) {
										sell.setSellCount(shifa);
										good.setGoodsCurQuantity(good
												.getGoodsCurQuantity()
												- shifa);
										shifa = 0f;
										needNumber=0f;
										b=b&totalDao.update(good);
										b=b&totalDao.save(sell);
										break;
									} else {
										sell.setSellCount(good
												.getGoodsCurQuantity());
										shifa -= good.getGoodsCurQuantity();
										good.setGoodsCurQuantity(0f);
										b=b&totalDao.update(good);
										b=b&totalDao.save(sell);
									}
								}
								
							}
							if (needNumber>0||shifa>0) {
								b=false;
								throw new Exception("库存不足！");
							}
						} else {
							throw new Exception("库存不足！");
						}
					} else {
						b = true;// 没有需求数量，无所谓领取不领取
					}
				}
			}
			if(b){
				pmo.setReceiveStatus("已领");
				pmo.setReceiver(user.getName());
			}
			return b;
		}
		return false;
	}
	@Override
	public boolean checkshifacount(String goodsMarkId,String goodsFormat, float shifa) {
		// TODO Auto-generated method stub
		List<Float> curQuantityList=totalDao.query("select goodsCurQuantity from Goods where goodsClass='原材料库' and goodsMarkId=? and goodsFormat=?",goodsMarkId,goodsFormat);
		if(curQuantityList.size()>0){
			for(Float c:curQuantityList){
				if(c>=shifa){
					return true;
				}else{
					shifa-=c;
				}
			}
		}
		return false;
	}

	@Override
	public Users getReceiver(String receiveuser) {
		// TODO Auto-generated method stub
		List list=totalDao.query("from Users where cardId=?", receiveuser);
		if(list.size()>0){
			return (Users)list.get(0);
		}
		return null;
	}

	@Override
	public void exprotqd(Integer id) {
		// TODO Auto-generated method stub
		ProjectMaterialOrder pmo= (ProjectMaterialOrder) totalDao.getObjectById(ProjectMaterialOrder.class, id);
		if(pmo==null){
			return ;
		}
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String((pmo.getOrderNo()+"物料清单").getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet(pmo.getOrderNo()+"物料清单", 0);
			ws.addCell(new Label(0, 0, "项目:"+pmo.getProName()));
			ws.addCell(new Label(2, 0, "配套数量:"+pmo.getPtCount()));
			ws.addCell(new Label(0, 1, "件号"));
			ws.addCell(new Label(1, 1, "名称"));
			ws.addCell(new Label(2, 1, "规格"));
			ws.addCell(new Label(3, 1, "物料类别"));
			ws.addCell(new Label(4, 1, "供料属性"));
			ws.addCell(new Label(5, 1, "需求数量"));
			ws.addCell(new Label(6, 1, "单位"));
			Set<ProjectMaterial> pmSet = pmo.getProjectMaterial();
			int i=2;
			for(ProjectMaterial pm:pmSet){
				ws.addCell(new Label(0, i ,pm.getMarkId()));//件号
				ws.addCell(new Label(1, i ,pm.getMaterialName()));//名称
				ws.addCell(new Label(2, i ,pm.getGuige()));//规格
				ws.addCell(new Label(3, i ,pm.getPaihao()));//物料类别
				ws.addCell(new Label(4, i ,pm.getKgliao()));//供料属性
				ws.addCell(new Label(5, i ,pm.getNeedNumber()+""));//需求数量
				ws.addCell(new Label(6, i ,pm.getUnit()));//单位
				i++;
			}
//			for (int i = 0; i < pList.size(); i++) {
//				ProcardTemplate p = (ProcardTemplate) pList.get(i);
//				ws.addCell(new Label(0, i + 1,p.getMarkId()));
//				ws.addCell(new Label(1, i + 1,p.getTuhao()));
//				ws.addCell(new Label(2, i + 1,p.getProName()));
//				ws.addCell(new Label(3, i + 1,p.getProcardStyle()));
//				ws.addCell(new Label(4, i + 1,p.getProductStyle()));
//				ws.addCell(new Label(5, i + 1,p.getRootMarkId()));
//				ws.addCell(new Label(6, i + 1,p.getYwMarkId()));
//				ws.addCell(new Label(7, i + 1,p.getBzStatus()));
//			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	
	
		
	}
}
