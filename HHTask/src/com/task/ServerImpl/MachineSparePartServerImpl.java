package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import com.task.Dao.TotalDao;
import com.task.Server.MachineSparePartServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Machine;
import com.task.entity.MachineSparePart;
import com.task.entity.MachineSparePartVo;
import com.task.entity.OaAppDetail;
import com.task.entity.OaMessageAlerm;
import com.task.entity.OaPrepareApply;
import com.task.entity.Users;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.util.Upload;
import com.task.util.Util;

public class MachineSparePartServerImpl implements MachineSparePartServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public Map<Integer, Object> findMachineSparePartsByCondition(
			MachineSparePart machineSparePart, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (machineSparePart == null) {
			machineSparePart = new MachineSparePart();
		}
		if(machineSparePart.getStorehouse()!=null&&machineSparePart.getStorehouse().equals("选择性质")){
			machineSparePart.setStorehouse(null);
		}
		
		String sql=null;
		if(machineSparePart.getMachine()!=null&&machineSparePart.getMachine().getName()!=null&&!machineSparePart.getMachine().getName().equals("")){
			sql="machine.name like '%"+machineSparePart.getMachine().getName()+"%'";
		}
		if(machineSparePart.getSafeStatus()!=null){
			if(machineSparePart.getSafeStatus().equals("安全")){
				if(sql==null){
					sql =" safeCount<=curCount";
				}else{
					sql +=" and safeCount<=curCount";
				}
			}else if(machineSparePart.getSafeStatus().equals("缺少")){
				if(sql==null){
					sql =" safeCount>curCount";
				}else{
					sql +=" and safeCount>curCount";
				}
			}
			machineSparePart.setSafeStatus(null);
		}
		machineSparePart.setMachine(null);
		machineSparePart.setPicF(null);
		machineSparePart.setPicFContentType(null);
		machineSparePart.setPicFFileName(null);
		String hql = totalDao.criteriaQueries(machineSparePart, sql, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		List<MachineSparePartVo> mspVoList=new ArrayList<MachineSparePartVo>();
		if(objs.size()>0){
			for(Object o:objs){
				MachineSparePartVo mspVo=new MachineSparePartVo((MachineSparePart)o);
				mspVoList.add(mspVo);
			}
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, mspVoList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean add(MachineSparePart machineSparePart) {
		// TODO Auto-generated method stub
		if(machineSparePart==null){
			return false;
		}
		Upload u = new Upload();
		String uploadPath = "/upload/file/Msp";
		File file1 = new File(uploadPath);
		if (!file1.exists()) {
			file1.mkdirs();// 如果不存在文件夹就创建
		}
		if(machineSparePart.getPicF()!=null&&!"".equals(machineSparePart.getPicFFileName())){
			String end_str = u.UploadFile(machineSparePart.getPicF(), machineSparePart.getPicFFileName(),null,uploadPath,null);  
			if(!"上传文件失败!".equals(end_str)&&!"".equals(end_str)){
				machineSparePart.setPic(end_str);
			}else{
				return false;
			}
		}
		if(machineSparePart.getMachine()!=null){
			Machine machine=(Machine) totalDao.getObjectById(Machine.class, machineSparePart.getMachine().getId());
			//machineSparePart.setAddtime(Util.getDateTime());//目前添加时间由人工输入
			//设置库存量
			List<Float> list=totalDao.query("select sum(curAmount) from Store where number=? and matetag=? and format=?", machineSparePart.getNumber(),machineSparePart.getMatetag(),machineSparePart.getFormat());
			if(list.size()>0){
				machineSparePart.setCurCount(list.get(0));
			}
			if(machine!=null){
				Set<MachineSparePart> mspSet=machine.getMachineSpareParts();
				if(mspSet==null){
					mspSet=new HashSet<MachineSparePart>();
				}
				mspSet.add(machineSparePart);
				machineSparePart.setMachine(machine);
				return totalDao.update(machine);
			}else{
				return totalDao.save(machineSparePart);
			}
		}
		return false;
	}

	@Override
	public List<Machine> getAllKeyMachines() {
		// TODO Auto-generated method stub
		return totalDao.query("from Machine where isKey='yes'");
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		MachineSparePart m=getById(id);
		if(m!=null){
			return totalDao.delete(m);
		}
		return false;
	}

	@Override
	public MachineSparePart getById(Integer id) {
		// TODO Auto-generated method stub
         return (MachineSparePart) totalDao.getObjectById(MachineSparePart.class, id); 		
	}

	@Override
	public boolean update(MachineSparePart machineSparePart) {
		// TODO Auto-generated method stub
		if(machineSparePart!=null){
			MachineSparePart m=getById(machineSparePart.getId());
			if(m==null){
				return false;
			}
			//machineSparePart.setAddtime(m.getAddtime());//添加时间不能修改
			//设置库存量
			List<Float> list=totalDao.query("select sum(curAmount) from Store where number=? and matetag=? and format=?", machineSparePart.getNumber(),machineSparePart.getMatetag(),machineSparePart.getFormat());
			if(list.size()>0){
				machineSparePart.setCurCount(list.get(0));
			}
			BeanUtils.copyProperties(machineSparePart, m, new String[]{"machine"});
			Machine machine1=machineSparePart.getMachine();
			Machine machine2=null;
			if(m.getMachine()!=null){
				machine2=(Machine) totalDao.getObjectById(Machine.class, m.getMachine().getId());
			}
			if(machine1!=null&&machine1.getId()!=0&&machine2==null){//原不绑定设备，修改后绑定设备
				Machine machine3=(Machine) totalDao.getObjectById(Machine.class, machineSparePart.getMachine().getId());
				if(machine3!=null){
					Set<MachineSparePart> mspSet=machine3.getMachineSpareParts();
					if(mspSet==null){
						mspSet=new HashSet<MachineSparePart>();
					}
					mspSet.add(m);
					m.setMachine(machine3);
				}
			}if((machine1==null||machine1.getId()==0)&&machine2!=null){//原绑定设备，修改后不绑定设备
				Machine machine3=(Machine) totalDao.getObjectById(Machine.class, m.getMachine().getId());
				Set<MachineSparePart> mspSet=machine3.getMachineSpareParts();
				mspSet.remove(m);
				machine3.setMachineSpareParts(mspSet);
				m.setMachine(null);
				totalDao.update(machine3);
			}else if(machine1!=null&&machine1.getId()!=0&&machine2!=null){//原绑定设备，修改后绑定设备
				if(!machine1.getId().equals(machine2.getId())){//前后设备不一致
					Machine machine3=(Machine) totalDao.getObjectById(Machine.class, m.getMachine().getId());
					Set<MachineSparePart> mspSet=machine3.getMachineSpareParts();
					mspSet.remove(m);
					machine3.setMachineSpareParts(mspSet);
					Machine machine4=(Machine) totalDao.getObjectById(Machine.class, machineSparePart.getMachine().getId());
					if(machine3!=null){
						Set<MachineSparePart> mspSet2=machine3.getMachineSpareParts();
						if(mspSet2==null){
							mspSet2=new HashSet<MachineSparePart>();
						}
						mspSet2.add(m);
						m.setMachine(machine4);
					}
					totalDao.update(machine3);
					totalDao.update(machine4);
				}
			}
			return totalDao.update(m);
		}
		return false;
	}

	@Override
	public MachineSparePartVo getByVoId(Integer id) {
		// TODO Auto-generated method stub
		MachineSparePart msp=getById(id);
		if(msp!=null){
			return new MachineSparePartVo(msp);
		}
		return null;
	}

	@Override
	public boolean updateCurrCount() {
		// TODO Auto-generated method stub
		List<MachineSparePart> mspList=totalDao.query("from MachineSparePart");
		if(mspList.size()>0){
		 for(MachineSparePart msp:mspList){	
			 boolean b=true;
			 List<Float> list=totalDao.query("select sum(curAmount) from Store where number=? and matetag=? and format=?", msp.getNumber(),msp.getMatetag(),msp.getFormat());
				if(list.size()>0){
					msp.setCurCount(list.get(0));
					b=b&totalDao.update(msp);
				}
		 }
		}
		return true;
	}

	@Override
	public String nextMonthOa(OaAppDetail oadetail) throws Exception {
		// TODO Auto-generated method stub
		List<MachineSparePart> mspList=totalDao.query("from MachineSparePart where safeCount>curCount");
		if(mspList.size()==0){
			return "true";
		}
		String addMessage = "true";
			int number = 0;
				// 获得部门申报对象
				// 预算月份，申报类型，项目编号
				String budgetMonth = oadetail.getBudgetMonth();
				float sumMoney = 0f;// 申报预算总金额
				// if (null != dms.getRealMoney()) {
				// budgetMoney = budgetMoney - dms.getRealMoney();
				// }

				// 物品编号 部门编号+年月
				Users user = Util.getLoginUser();
				/*
				 * String curdate = Util.getDateTime("yyyyMMdd"); int mm =
				 * Integer.parseInt(curdate.substring(4, 6)) + 1; int yy =
				 * Integer.parseInt(curdate.substring(0, 4)); if (mm == 13) { mm
				 * = 1; yy += 1; } String monm = String.valueOf(mm);// 月份 if (mm
				 * < 10) { monm = 0 + "" + mm; }
				 */
				// String planMon = yy + "-" + monm;// 计划月份
				// String ordNumber = user.getPassword().getDeptNumber() + yy +
				// monm;// 标识
				//String planMon = Util.getNextMonth("yyyy-MM");// 计划月份
				String planMon=oadetail.getDetailAppDate();
				String ordNumber = user.getPassword().getDeptNumber() + planMon;// 标识
				String hql = "from OaPrepareApply where appPlanMon=? and appDept=?";
				List list = totalDao.query(hql, planMon, user.getDept());
				OaPrepareApply opa = null; // 预申请表
				if (list.size() > 0 && list != null) {
					opa = (OaPrepareApply) list.get(0);// 获取对象
				} else {
					opa = new OaPrepareApply();// 创建对象
					opa.setAppApplier(user.getName());
					opa.setAppDept(user.getDept());
					opa.setAppTimer(Util.getDateTime());
					opa.setAppPlanMon(planMon.substring(0, 7));// 计划月份
					opa.setAppOrdnumber(ordNumber);// 编号
					opa.setAppBarcode(user.getPassword().getDeptNumber()
							+ Util.getDateTime("yyyyMM"));
					// appNumber
					String hqlNum = "select max(appNumber) from OaPrepareApply";
					List listNum = totalDao.query(hqlNum);
					if (listNum.size() > 0 && null != listNum.get(0)) {// 在原来的基础上加1
						String Maxbianhao = (String) listNum.get(0);
						opa.setAppNumber(bianhao(Maxbianhao));
					} else {// 初始化
						opa.setAppNumber("PD-P0-0000001");
					}
					totalDao.save(opa);
				}
					// 遍历结束，
					if (number == 0) {
							float sumD = 0f;// 申报金额
							for (MachineSparePart msp:mspList) {
									String childClass = msp.getParClass();// 获得物品类别
									String format = msp.getFormat();// 获得物品规格
									Float count =msp.getSafeCount()-msp.getCurCount();// 获得申报数量
									//String yiju = cells[5].getContents();// 获得计划依据
									//String arrivalDate = cells[6].getContents();// 获得到货期限
									Float price = msp.getPrice();// 获得预算单价
									String unit = msp.getUnit();// 获得单位
//									String isjiaji = "否";// 获得是否加急
									String name=msp.getMatetag();//物品名称
									sumD += count * price;
									// 保存明细表
									OaAppDetail detail = new OaAppDetail();
									detail.setBudgetMonth(budgetMonth);// 预算月份
									detail.setDetailClass("普通申购");// 申购类型
									detail.setDetailAppDept(user.getDept());// 部门
									detail.setDetailAppName(name);// 物品名称
									detail.setDetailChildClass(childClass);// 物品类型
									detail.setDetailFormat(format);// 规格
									detail.setDetailCount(count);// 数量
									detail.setDetailAppDate(planMon);// 申请时间
									detail.setDetailBudgetMoney(price);// 预算单价
									detail.setDetailPlanAcco(oadetail.getDetailPlanAcco());// 计划依据
									detail.setDetailIsBusy(oadetail.getDetailIsBusy());// 是否加急
									detail.setDetailUnit(unit);// 单位
									detail.setDetailOrdNumber(ordNumber);// 编号
									detail.setDetailPlanMon(planMon);// 计划月份
									detail.setDetailArrDate(oadetail.getDetailArrDate());// 获得到货期限
									detail.setDetailStatus("预申请");
									detail.setStatus("未入完");
									detail.setDeptMonthBudgetID(oadetail.getDeptMonthBudgetID());
									detail.setPrepareApply(opa);
									// 生成物品编号
									String seqNum = "";
									String bianhao = retuGodsId(childClass)
											+ planMon.replaceAll("-", "");// 部门编码+月份
									String hqlMaxId = "select max(detailSeqNum) from OaAppDetail where detailSeqNum like '"
											+ bianhao + "%'";
									List listD = totalDao.query(hqlMaxId);
									if (listD.size() > 0
											&& null != listD.get(0)) {
										String maxId = String
												.valueOf(Integer
														.parseInt(((String) listD
																.get(0))
																.substring(10)) + 1);
										if (maxId.length() == 1) {
											maxId = "00" + maxId;
										} else if (maxId.length() == 2) {
											maxId = "0" + maxId;
										}
										seqNum = bianhao + maxId;
									} else {
										seqNum = bianhao + "001";
									}
									detail.setDetailSeqNum(seqNum);// 物品编号

									totalDao.save(detail);
									// 判断审批流程
									
										String processName = user.getDept()
												+ "普通申购个人采购审批流程";
//										if ("A".equals(oadetail.getAppayTag())) {
//											processName += "个人采购审批流程";
//										} else if ("B".equals(oadetail
//												.getAppayTag())) {
//											processName += "经理采购审批流程";
//										} else if ("C".equals(oadetail
//												.getAppayTag())) {
//											processName += "副总经理采购审批流程";
//										}
										// 添加审批流程
										Integer epId = CircuitRunServerImpl
												.createProcess(processName,
														OaAppDetail.class,
														detail.getId(),
														"detailStatus", "id",
														null);// false
										// 是否发手机短信消息，邮件
										if (epId != null && epId > 0) {
											detail.setEpId(epId);
											totalDao.update(detail);
											// 判断发送提醒消息
											Integer userIds[] = CircuitRunServerImpl
													.findAuditUserId(epId, 1);
											for (int j = 0; j < userIds.length; j++) {
												Integer userId = userIds[j];
												Users acessUser = (Users) totalDao
														.getObjectById(
																Users.class,
																userId);
												// 判断发送提醒消息
												String sendDate = Util
														.getDateTime("yyyy-MM-dd");
												String hqlMessage = "from OaMessageAlerm where oaUserName='"
														+ user.getName()
														+ "' and sendDate='"
														+ sendDate
														+ "' and accessPhone=?";
												String phone = acessUser
														.getPassword()
														.getPhoneNumber();
												if (totalDao.getCount(
														hqlMessage, phone) <= 0) {
													String msg = user.getDept()
															+ "部门" + planMon
															+ "月采购计划请您审批！";
													// 发送短信提醒
													OaMessageAlerm mess = new OaMessageAlerm();
													mess.setOaUserName(user
															.getName());
													mess.setContent(msg);
													mess.setAccessPhone(phone);
													mess.setRealName(user
															.getName());
													mess.setSendDate(sendDate);
													totalDao.save(mess);
													AlertMessagesServerImpl
															.addAlertMessages(
																	"采购审批",
																	msg,
																	"采购审批",
																	acessUser
																			.getCode(),
																	phone);
												}
											}
										}

									
							}
							// 修改预算表
							// dms.setRealMoney(dms.getRealMoney() + sumD);
							// totalDao.update(dms);
							// 判断发送提醒消息
							/*
							 * String sendDate = Util.getDateTime("yyyy-MM-dd");
							 * String hqlMessage =
							 * "from OaMessageAlerm where oaUserName='" +
							 * user.getDept() + "' and sendDate='" + sendDate +
							 * "'"; if (totalDao.query(hqlMessage).size() > 0) {
							 * } else { String msg = user.getDept() + "部门" +
							 * planMon + "月采购计划请您审批！--上海红湖排气系统有限公司"; // 发送短信提醒
							 * OaMessageAlerm mess = new OaMessageAlerm();
							 * mess.setOaUserName(user.getName());
							 * mess.setContent(msg);
							 * mess.setRealName(user.getName());
							 * mess.setSendDate(sendDate); totalDao.save(mess);
							 * String messagesss = AlertMessagesServerImpl
							 * .addAlertMessages("采购审批", user .getDept() +
							 * "采购审批", "1"); }
							 */
					}

			return addMessage;
	}
	// 返回申报编号
	String bianhao(String num) {
		String t1 = num.substring(6);
		Integer t2 = Integer.parseInt(t1);
		t2 += 1;
		String t3 = String.valueOf(t2);
		int length = t1.length() - t3.length();
		String t4 = "0000000";
		String t5 = t4.substring(0, length);
		return "PD-P0-" + t5 + t3;
	}
	// 返回物品编号
	public String retuGodsId(String str) {
		String godsId = "";
		if ("金属五交材料".equals(str)) {
			godsId = "SMT";
		} else if ("备件".equals(str)) {
			godsId = "SPT";
		} else if ("杂品".equals(str)) {
			godsId = "OTT";
		} else if ("办公用品".equals(str)) {
			godsId = "OFT";
		} else if ("包装物".equals(str)) {
			godsId = "LPT";
		} else if ("工具".equals(str)) {
			godsId = "TOT";
		} else if ("工装".equals(str)) {
			godsId = "FKT";
		} else if ("金属五交".equals(str)) {
			godsId = "SMT";
		} else if ("五金".equals(str)) {
			godsId = "SMT";
		}
		return godsId;
	}

	@Override
	public boolean preSaveOa() {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		String hql = " from DeptMonthBudget where status='同意' and userDept='"
				+ user.getDept() + "' and accountMoney>realMoney";
		List list = totalDao.find(hql);
		if (list.size() > 0 && list != null) {
			return true;
		}
		return false;
	}
}
