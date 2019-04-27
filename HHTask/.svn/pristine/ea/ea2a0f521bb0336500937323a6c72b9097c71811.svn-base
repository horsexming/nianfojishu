package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.jfree.data.xy.YWithXInterval;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.InsRecordService;
import com.task.Server.ess.GoodsStoreServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.action.sop.WaigouwaiweiPlanAction;
import com.task.entity.Becoming;
import com.task.entity.Careertrack;
import com.task.entity.GoodsStore;
import com.task.entity.Users;
import com.task.entity.android.InsRecord;
import com.task.entity.android.InsRecordScope;
import com.task.entity.android.InsScope;
import com.task.entity.android.InsTemplate;
import com.task.entity.android.OsRecord;
import com.task.entity.android.OsRecordScope;
import com.task.entity.android.OsScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.gzbj.ProcessAndMeasuring;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.shizhi.SkillScore;
import com.task.entity.sop.BreakSubmit;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.DefectiveProduct;
import com.task.entity.sop.FailureSSOnDay;
import com.task.entity.sop.FailureSSOnMonth;
import com.task.entity.sop.FailureSSOnWeek;
import com.task.entity.sop.FailureStatistics;
import com.task.entity.sop.FailureStatisticsDetail;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessInforReceiveLog;
import com.task.entity.sop.WaigouDeliveryDetail;
//import com.task.entity.sop.FailureStatisticsDetail;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.qd.CheckAlert;
import com.task.entity.sop.qd.LogoStickers;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;
import com.tast.entity.zhaobiao.Waigoujianpinci;
import com.tast.entity.zhaobiao.WaigoujianpinciZi;
import com.tast.entity.zhaobiao.ZhUser;

public class InsRecordServiceImpl implements InsRecordService {
	private TotalDao totalDao;
	private GoodsStoreServer goodsStoreServer;
	
	public GoodsStoreServer getGoodsStoreServer() {
		return goodsStoreServer;
	}

	public void setGoodsStoreServer(GoodsStoreServer goodsStoreServer) {
		this.goodsStoreServer = goodsStoreServer;
	}

	@Override
	public void add(List<InsRecord> list) {
		for (InsRecord insRecord : list) {
			totalDao.save(insRecord);
		}
	}

	@Override
	public List<InsRecord> get(InsRecord insTemplate) {
		String hql = "from InsRecord i where i.root.id = ? and i.groupDate = ?";
		List<InsRecord> list = totalDao.find(hql, new Object[] {
				insTemplate.getRoot().getId(), insTemplate.getGroupDate() });
		return list;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] list(Integer size, Integer page) {
		List<InsRecord> l = null;
		int count = 0;
		try {
			String hql = "select b.groupDate, a.productType, a.partNumber, a.processNumber, a.workStation, COUNT(b.groupDate), b.root.id from InsRecord b join b.root a  GROUP BY b.root.id, b.groupDate, a.partNumber, a.productType, a.processNumber, a.workStation order by b.groupDate desc";

			List query = totalDao.findAllByPage(hql, page, size);
			List<Object[]> ll = query;
			l = new ArrayList<InsRecord>();
			for (Object[] object : ll) {
				InsRecord r = new InsRecord();
				InsTemplate t = new InsTemplate();
				r.setRoot(t);
				r.setGroupDate(object[0].toString());
				t.setProductType(object[1].toString());
				t.setPartNumber(object[2].toString());
				t.setProcessNumber(object[3].toString());
				t.setWorkStation(object[4].toString());
				r.setDateCount(object[5].toString());
				t.setId((Integer) object[6]);
				l.add(r);
			}
			String sql = "select count(*) from mytable";
			List l1 = totalDao.createQuerySelect(null, sql);
			count = (Integer) l1.get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		Object[] o = { l, count };
		return o;

	}

	@Override
	public Map<Integer, Object> getToXjList(ProcessInfor processInfor,
			String startDate, String endDate, int cpage, int pageSize) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				"Users");
		if (processInfor == null) {
			processInfor = new ProcessInfor();
		}
		String hql = "from ProcessInfor where status ='已领' and productStyle='自制' "
				+ " and gongwei in (select m.workPosition from Machine m  join m.userset u where u.id="
				+ user.getId() + ") ";
		if (processInfor.getUsernames() != null
				&& processInfor.getUsernames().length() > 0) {
			hql += " and usernames='" + processInfor.getUsernames() + "'";
		}
		if (processInfor.getProcessName() != null
				&& processInfor.getProcessName().length() > 0) {
			hql += " and processName like '%" + processInfor.getProcessName()
					+ "%'";
		}
		if (processInfor.getProcard() != null) {
			if (processInfor.getProcard().getMarkId() != null
					&& processInfor.getProcard().getMarkId().length() > 0) {
				hql += " and procard.markId like '%"
						+ processInfor.getProcard().getMarkId() + "%'";
			}
			if (processInfor.getProcard().getSelfCard() != null
					&& processInfor.getProcard().getSelfCard().length() > 0) {
				hql += " and procard.selfCard like '%"
						+ processInfor.getProcard().getSelfCard() + "%'";
			}
		}
		int count = totalDao.getCount(hql);
		List<ProcessInfor> objs = totalDao.findAllByPage(hql, cpage, pageSize);
		if (objs != null && objs.size() > 0) {
			for (ProcessInfor pro : objs) {
				if (pro.getProcard() != null) {
					String markId = pro.getProcard().getMarkId();
				}
			}
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;

	}

	@Override
	public OsTemplate updateOsTemplate(Integer id) {
		ProcessInfor processInfor = (ProcessInfor) totalDao.getObjectById(
				ProcessInfor.class, id);
		if (processInfor != null) {
			OsTemplate otp = (OsTemplate) totalDao
					.getObjectByCondition(
							"from OsTemplate  where partNumber=? and gongxuNum=? and zhonglei = '巡检' ",
							processInfor.getProcard().getMarkId(), processInfor
									.getProcessNO()
									+ "");
			if (otp == null) {
				String hql_pg = "from ProcessGzstore where processName=?";
				ProcessGzstore pg = (ProcessGzstore) totalDao
						.getObjectByCondition(hql_pg, processInfor
								.getProcessName());
				Set<OsScope> osSet = new HashSet<OsScope>();
				if (pg != null) {
					osSet = pg.getOs();
				}

				Set<OsScope> osSet1 = new HashSet<OsScope>();
				if(osSet!=null && osSet.size()>0){
					otp = new OsTemplate();
					otp.setPartNumber(processInfor.getProcard().getMarkId());
					otp.setGongxuNum(processInfor.getProcessNO() + "");
					otp.setGongxuName(processInfor.getProcessName());
					otp.setCmodel(processInfor.getProcard().getCarStyle());
					otp.setName(processInfor.getProcard().getProName());
					otp.setCtype1(processInfor.getProcard().getProcardStyle());
					otp.setZhonglei("巡检");
					otp.setXjtype("按时间");
					otp.setXjcheckpc(2);
					otp.setCreateDate(Util.getDateTime());
					for (OsScope osScope : osSet) {
						OsScope osc = new OsScope();
						osc.setContent(osScope.getContent());
						osc.setJcff(osScope.getJcff());
						osc.setZltz(osScope.getZltz());
						osc.setType(osScope.getType());
						osc.setOsTemplate(otp);
						osSet1.add(osc);
					}
					otp.setScopes(osSet1);
					totalDao.save(otp);
				}
				
			}
			return otp;
		}
		return null;
	}

	@Override
	public ProcessInfor getProcessById(Integer id,BreakSubmit breaksubmit) {
		// TODO Auto-generated method stub
		ProcessInfor processInfor = (ProcessInfor) totalDao.getObjectById(
				ProcessInfor.class, id);
		if(breaksubmit!=null && breaksubmit.getId()!=null){
			breaksubmit = (BreakSubmit) totalDao.get(BreakSubmit.class,breaksubmit.getId());
			if("上工序不合格".equals(breaksubmit.getBreakgroup())){
				processInfor = (ProcessInfor) totalDao.getObjectByCondition(" from ProcessInfor where procard.id =? and processNO <? order by processNO desc ", processInfor.getProcard().getId(),processInfor.getProcessNO());
			}
		}
		if(processInfor!=null && processInfor.getProcard()!=null){
			String markId = processInfor.getProcard().getMarkId();
		}
			return processInfor;
	}

	@Override
	public List<OsScope> getcheckList(Integer id) {
		// TODO Auto-generated method stub
		ProcessInfor processInfor = (ProcessInfor) totalDao.getObjectById(
				ProcessInfor.class, id);
		if (processInfor != null) {

			return totalDao
					.query(
							"from OsScope where id in( select s.id from OsTemplate ot join ot.scopes s where ot.zhonglei = '巡检' and ot.partNumber=? and ot.gongxuNum=? ) order by id",
							processInfor.getProcard().getMarkId(), processInfor
									.getProcessNO()
									+ "");
		}
		return null;
	}

	@Override
	public String xJProcess(ProcessInfor processInfor,
			List<OsRecord> osRecordList, Float jcCount,BreakSubmit breaksubmit) {
		// TODO Auto-generated method stub
		String measuring_no ="";
		if(processInfor.getMeasuring_no()!=null
				&& processInfor.getMeasuring_no().length()>0){
			 measuring_no = processInfor.getMeasuring_no();
		}
		String measuringMatetag ="";
		if(measuring_no!=null
				&& measuring_no.length()>0){
			 measuring_no = "'"+measuring_no.replaceAll(";", "','")+"'";
			List<String> strList =	totalDao.query("select matetag from Measuring where measuring_no in ("+measuring_no+")");
			for (String str : strList) {
				measuringMatetag+=";"+str;
			}
			if(measuringMatetag.length()>1){
				measuringMatetag =measuringMatetag.substring(1);
			}
		}
		ProcessInfor old = (ProcessInfor) totalDao.getObjectById(
				ProcessInfor.class, processInfor.getId());
//		String[] codes = processInfor.getUsercodes().split(";");
//		Users tjusers =  (Users) totalDao.getObjectByCondition(" from Users where code = ? ", codes[0]);
		Procard procard =	old.getProcard();
		Float 	quantity = old.getApplyCount();
		String gongwei = old.getGongwei();
		String proName = procard.getProName();
		Procard wgprocard = null;
		String type = "自制";
		if(breaksubmit!=null && breaksubmit.getId()!=null){
			breaksubmit  = (BreakSubmit) totalDao.get(BreakSubmit.class, breaksubmit.getId());
			quantity = breaksubmit.getTjbreakcount();
			gongwei = breaksubmit.getGongwei();
				if("外购件不合格".equals(breaksubmit.getType())){
					wgprocard =	(Procard) totalDao.getObjectByCondition(" from Procard where fatherId = ? and markId = ? ",breaksubmit.getProcardId(),breaksubmit.getWgmarkId() );
					type = "外购";
				}
		}
		List<CheckAlert> caList = totalDao.query("from CheckAlert where markId=? and selfCard =? and processNo=? and status !='完成'", procard.getMarkId(),procard.getSelfCard(),old.getProcessNO());
		if(caList!=null&&caList.size()>0){
			for(CheckAlert ca :caList){
				ca.setStatus("完成");
				ca.setEndTime(Util.getDateTime());
				totalDao.update(ca);
			}
		}
		String ywmarkId = "";
		String orderNumber = "";
		String selfCard = "";
		String lingliaoDetail = "";
		String [] lingliaoDetails =null;
		Integer size =1;
		boolean iswgprocard = false;
		if(procard!=null){
			ywmarkId = procard.getYwMarkId();
			orderNumber = procard.getOrderNumber();
			selfCard = procard.getSelfCard();
			if(wgprocard!=null){
				iswgprocard = true;
				selfCard = wgprocard.getSelfCard();
				proName = wgprocard.getProName();
				lingliaoDetail = wgprocard.getLingliaoDetail();
				if(lingliaoDetail!=null && lingliaoDetail.length()>0){
					lingliaoDetails = lingliaoDetail.split(",");
					size = lingliaoDetails.length;
				}
			}
		}
		if (old == null) {
			return "没有找到目标！";
		}
		Users user = Util.getLoginUser();
		if (user == null) {
			return "请先登录!";
		}
		if (old.getCheckUsersId() == null
				|| old.getCheckUsersId().length() == 0) {
			old.setCheckUsers(user.getName());
			old.setCheckDate(Util.getDateTime("yyyy-MM-dd"));
			old.setCheckUsersCode(user.getCode());
			old.setCheckUsersId(user.getId() + "");

		} else if (old.getCheckUsersCode().contains(user.getCode())) {
		} else {
			old.setCheckUsers(old.getCheckUsers() + user.getName());
			old.setCheckDate(old.getCheckDate()
					+ Util.getDateTime("yyyy-MM-dd"));
			old.setCheckUsersCode(old.getCheckDate() + user.getCode());
			old.setCheckUsersId(old.getCheckDate() + user.getId() + "");
		}
		if (osRecordList != null && osRecordList.size() > 0) {
			// 统计不合格报表
			FailureStatistics failureSt = new FailureStatistics();
			failureSt.setDateTime(Util.getDateTime("yyyy年MM月dd日"));
			failureSt.setMarkId(old.getProcard().getMarkId()
					.replaceAll(" ", ""));
			int week = 0;
			try {
				week = Util.getNowWeek(Util.StringToDate(failureSt
						.getDateTime(), "yyyy年MM月dd日"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String weeks = Util.getDateTime("yyyy") + "年" + week + "周";
			failureSt.setWeekds(weeks);
			failureSt.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			failureSt.setSubmitCount(jcCount);
			failureSt.setGongwei(gongwei);
			failureSt.setSelfcard(selfCard);
			failureSt.setProcessNo(old.getProcessNO());
			failureSt.setProcessName(old.getProcessName());
			failureSt.setProName(proName);
			Set<FailureStatisticsDetail> fsdSet = new HashSet<FailureStatisticsDetail>();
			int i = 0;
			float buhege = 0F;
			Set<OsRecord> osrSet = new HashSet<OsRecord>();
			for (OsRecord osRecord : osRecordList) {
				osRecord.setZhonglei("巡检");
				osRecord.setMarkId(procard.getMarkId());
				osRecord.setProName(procard.getProName());
				osRecord.setBanbenNumber(procard.getBanBenNumber());
				osRecord.setBanci(procard.getBanci());
				osRecord.setSpecification(procard.getSpecification());
				osRecord.setUnit(procard.getUnit());
				osRecord.setJcpc(procard.getSelfCard());// 检查批次
				osRecord.setQuantity(quantity);// 本批数量
				osRecord.setJyNumber(jcCount);
				osRecord.setQuantity((jcCount));// 本批检验数量
				osRecord.setNowDate(Util.getDateTime());// 当前时间
				osRecord.setUsername(user.getName());// 审核人
				osRecord.setYwmakrId(ywmarkId);//业务件号
				osRecord.setNeiordeNum(orderNumber);//内部订单号
				osRecord.setSelfCard(selfCard);//生产批次
				osRecord.setSeeDate(Util.getDateTime("yyyy-MM-dd"));
				osRecord.setMeasuring_no(measuring_no);//量具编号
				osRecord.setMeasuringMatetag(measuringMatetag);//量具名称
				osRecord.setBanbenNumber(procard.getBanBenNumber());
				osRecord.setGongwei(old.getGongwei());
//				OsTemplate ot = (OsTemplate) totalDao
//						.getObjectByCondition(
//								"from OsTemplate where id in (select ot.id from OsTemplate ot join ot.scopes s where s.id=?)",
//								osRecord.getTemplateId());
				OsTemplate ot = (OsTemplate) totalDao.get(OsTemplate.class, osRecord.getTemplateId());
				osRecord.setTemplateId(ot.getId());
				osRecord.setTemplate(ot);
				if ("不合格".equals(osRecord.getVerification())
						&& osRecord.getBuhegeId() != null
						&& osRecord.getBuhegeId() > 0) {
					BuHeGePin bhgp = (BuHeGePin) totalDao.getObjectById(
							BuHeGePin.class, osRecord.getBuhegeId());
					if (bhgp != null) {
						String code = osRecord.getCode() + bhgp.getCode()
								+ osRecord.getBhgclass();
						code=code.replaceAll(" ", "");
						// 不良品类型明细
						boolean bool = true;
						for (FailureStatisticsDetail f : fsdSet) {
							if(f.getBuhegeId().equals(bhgp.getId())){
								f.setBadNumber(f.getBadNumber()+1);
								bool = false;
							}
						}
						if(bool){
							FailureStatisticsDetail fsd = new FailureStatisticsDetail();
							fsd.setBuhegeId(bhgp.getId());
							fsd.setCode(code);
							fsd.setType(bhgp.getType());
							fsd.setBadNumber(1F);
							fsd.setFailureStatistics(failureSt);
							fsdSet.add(fsd);
						}
						// 检验明细不良缺陷赋值
						osRecord.setBhgTypeNum(1);
						osRecord.setCode(code);
						osRecord.setType(bhgp.getType());
					}
					i++;
					buhege++;
				}
				Set<OsRecordScope> recordScope = new HashSet<OsRecordScope>();
				for (OsRecordScope ors : osRecord.getScopes()) {
					OsScope os = (OsScope) totalDao.getObjectById(
							OsScope.class, ors.getScopeId());
					if (os == null) {
						return "没有找到对应的检查项!";
					}
					ors.setScope(os);
					ors.setTitle(os.getTitle());
					ors.setOsRecord(osRecord);
					recordScope.add(ors);
				}
				osRecord.setRecordScope(recordScope);
				osRecord.setHgNumber(jcCount - i);
				totalDao.save(osRecord);
				osrSet.add(osRecord);
			}
			if(breaksubmit!=null && breaksubmit.getId()!=null){
				if(wgprocard!=null){
					buhege = buhege*(wgprocard.getQuanzi2()/wgprocard.getQuanzi1());
				}
				breaksubmit.setQrbreakcount(buhege);//确认不合格数量
				breaksubmit.setQrUsersName(user.getName());//确认人姓名
				breaksubmit.setQrTime(Util.getDateTime());//确认时间
				breaksubmit.setQrUsersId(user.getId());//确认人Id
				for (OsRecord osRecord2 : osrSet) {
					osRecord2.setBreaksubmit(breaksubmit);
				}
				if(buhege == 0f){
					breaksubmit.setClResult("正常");
				}
				breaksubmit.setOsrSet(osrSet);
				totalDao.update(breaksubmit);
			}
			if(buhege>0){
				if(breaksubmit == null){
					breaksubmit = new BreakSubmit();
					breaksubmit.setProcardId(procard.getId());//自制件Id
					breaksubmit.setProName(procard.getProName());
					breaksubmit.setProcessId(old.getId());//工序Id
					breaksubmit.setType("零件损坏");
					breaksubmit.setBreakgroup("上工序不合格");
					breaksubmit.setProcessNo(old.getProcessNO());//工序号
					breaksubmit.setProcessName(old.getProcessName());//工序名
					breaksubmit.setMarkId(procard.getMarkId());//件号
					breaksubmit.setTjbreakcount(buhege);//提交不合格数量
					breaksubmit.setQrbreakcount(buhege);//确认不合格数量
					breaksubmit.setTjUsersId(user.getId());//提交人Id
					breaksubmit.setTjUsersName(user.getName());//提交人姓名
					breaksubmit.setQrUsersId(user.getId());//确认人Id
					breaksubmit.setQrUsersName(user.getName());//确认人姓名
					breaksubmit.setTjTime(Util.getDateTime());//提交时间
					breaksubmit.setQrTime(Util.getDateTime());//确认时间
					breaksubmit.setSelfcard(procard.getSelfCard());
					breaksubmit.setYwmarkId(procard.getYwMarkId());
					breaksubmit.setTjtype("巡检检验");
					String prologId = "";
					List<ProcessInforReceiveLog> proLogList = totalDao.query(" from ProcessInforReceiveLog where fk_processInforId = ?  and status = '提交'  ",old.getId() );
					for (ProcessInforReceiveLog processInforReceiveLog : proLogList) {
						prologId+=","+processInforReceiveLog.getId();
					}
					if(prologId!=null && prologId.length()>=1){
						prologId = prologId.substring(1);
					}
					breaksubmit.setPrologId(prologId);
					for (OsRecord osRecord2 : osrSet) {
						osRecord2.setBreaksubmit(breaksubmit);
					}
					breaksubmit.setOsrSet(osrSet);
					totalDao.save(breaksubmit);
				}
				 String processName = "现场不良品处理申请";
					Integer epId = null;
					try {
						epId = CircuitRunServerImpl.createProcess(processName,
								BreakSubmit.class, breaksubmit.getId(), "epstatus", "id",
								"ProcardAction!findbreaksubmitById.action?id="+breaksubmit.getId()
										,user.getDept() + "部门 "
										+ user.getName() + "现场不良品处理申请，请您审批", true);
						if (epId != null && epId > 0) {
							breaksubmit.setEpId(epId);
							CircuitRun circuitRun = (CircuitRun) totalDao.get(
									CircuitRun.class, epId);
							if ("同意".equals(circuitRun.getAllStatus())
									&& "审批完成".equals(circuitRun.getAuditStatus())) {
								breaksubmit.setEpstatus("同意");
								breaksubmit.setClResult("返修");
							} else {
								breaksubmit.setEpstatus("未审批");
							}
							totalDao.update(breaksubmit);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				
				
				//入不合格品库
				for (int j = 0; j <size; j++) {
					Float qrbreakcount = breaksubmit.getQrbreakcount();
					if(lingliaoDetails!=null){
						if( lingliaoDetails[j]!=null && lingliaoDetails[j].length()>0){
							String[] lotandnums =	lingliaoDetails[j].split(":");
							if(lotandnums!=null && lotandnums.length == 2){
								selfCard = lotandnums[0];
								qrbreakcount =Float.parseFloat( lotandnums[1]);
							}
						}
					}
					
					
					GoodsStore goodsStore = new GoodsStore();
					goodsStore.setGoodsStoreMarkId(breaksubmit.getMarkId());//件号
					goodsStore.setGoodsStoreWarehouse("不合格品库");//库别
					goodsStore.setBanBenNumber(procard.getBanBenNumber());//版本号
					goodsStore.setKgliao(procard.getKgliao());//供料属性
					goodsStore.setGoodsStoreLot(procard.getSelfCard());//批次
					goodsStore.setGoodsStoreGoodsName(proName);//名称
					goodsStore.setGoodsStoreFormat(procard.getSpecification());//规格
					goodsStore.setWgType(procard.getWgType());//物料类别
					goodsStore.setGoodsStoreUnit(procard.getUnit());//单位
					goodsStore.setGoodsStoreCount(qrbreakcount);//入库数量
					goodsStore.setGoodsStoreTime(Util.getDateTime());//入库时间
					goodsStore.setGoodsStorePerson(user.getName());//负责人
					goodsStore.setGoodsStoreProMarkId(procard.getRootMarkId());//总成件号
					goodsStore.setTuhao(procard.getTuhao());//图号
					goodsStore.setInputSource("在库不良品提交");//
					goodsStore.setStatus("入库");
					goodsStore.setStyle("不合格品入库");//入库类型
					goodsStore.setPrintStatus("YES");//打印状态
					goodsStore.setProcessNo(old.getProcessNO());//工序号
					goodsStore.setProcessName(old.getProcessName());//工序号
					goodsStoreServer.saveSgrk(goodsStore);
					if(iswgprocard){
						//判断同件号，同检验批次是否存在不良品处理单;
						DefectiveProduct dp = null;
						WaigouDeliveryDetail wgdd = (WaigouDeliveryDetail) totalDao.getObjectByCondition(" from WaigouDeliveryDetail where markId = ? and examineLot =?  ", breaksubmit.getWgmarkId(),selfCard);
						if(wgdd!=null){
							dp = new DefectiveProduct();
							dp.setCgOrderNum(wgdd.getCgOrderNum());//采购订单号
							dp.setShOrderNum(wgdd.getWaigouDelivery().getPlanNumber());//送货单号;
							dp.setMarkId(wgdd.getMarkId());;//件号
							dp.setKgliao(wgdd.getKgliao());//供料属性（自购、指定、客供）
							dp.setBanben(wgdd.getBanben());//版本
							dp.setTuhao(wgdd.getTuhao());//图号
							dp.setProName(wgdd.getProName());//零件名称、
							dp.setWgType(wgdd.getWgType());//物料类别
							dp.setSpecification(wgdd.getSpecification());//规格
							dp.setUnit(wgdd.getUnit());//单位
							dp.setHsPrice(wgdd.getHsPrice().floatValue());//含税单价
							dp.setPrice(wgdd.getPrice().floatValue());//不含税单价
							dp.setTaxprice(wgdd.getTaxprice());//税率
							dp.setPriceId(wgdd.getPriceId());//价格Id
							dp.setGysName(wgdd.getGysName());//供应商名称
							dp.setGysId(wgdd.getGysId());//供应商Id;
							ZhUser zhuser = (ZhUser) totalDao.get(ZhUser.class, wgdd.getGysId());
							if(zhuser!=null){
								dp.setGysUsersId(zhuser.getUserid());//供应商UserId
							}
							dp.setExamineLot(selfCard);//检验批次
							dp.setWgddId(wgdd.getId());//送货单Id
							dp.setType("外购在库不良");
							dp.setStatus("待确认");
							dp.setDbNumber(qrbreakcount);//调拨数量
							dp.setLlNumber(qrbreakcount);//来料数量
							dp.setJyNumber(qrbreakcount);//检验数量
							dp.setJyhgNumber(qrbreakcount-buhege);//检验合格数量
							dp.setJybhgNumber(buhege);//检验不合格数量
							dp.setJyuserId(user.getId());
							dp.setJyuserCode(user.getCode());
							dp.setJyuserName(user.getName());
							dp.setAddTime(Util.getDateTime());//添加时间
							dp.setAddUser(Util.getLoginUser().getName());//添加人
							dp.setWlType("外购");
							totalDao.save(dp);
						}
				}
				}
				if(iswgprocard){
					if(wgprocard.getHascount()!=null){
						wgprocard.setHascount(wgprocard.getHascount()+breaksubmit.getQrbreakcount());
					}else {
						wgprocard.setHascount(breaksubmit.getQrbreakcount());
					}
					wgprocard.setKlNumber(wgprocard.getKlNumber()==null?breaksubmit.getQrbreakcount():wgprocard.getKlNumber()+breaksubmit.getQrbreakcount());
					totalDao.update(wgprocard); 
					//控制最后一道工序的可领数量
					Float maxbreakscount = (Float) totalDao.getObjectByCondition(" select max(hascount*(quanzi1/quanzi2))  from Procard where fatherId=? and  procardStyle = '外购' ", breaksubmit.getProcardId());
					if(maxbreakscount == null){
						maxbreakscount =0f;
					}
					ProcessInfor  finalprocess = (ProcessInfor) totalDao.getObjectByCondition(" from ProcessInfor where procard.id = ? order by processNO desc", procard.getId());
					finalprocess.setTotalCount(finalprocess.getTotalCount()-maxbreakscount);
					totalDao.update(finalprocess);
					//更新自制件的剩余数量和可领数量
					if(procard.getHascount()!=null){
						procard.setHascount(procard.getHascount()+maxbreakscount);
					}else{
						procard.setHascount(maxbreakscount);
					}
					if(procard.getKlNumber()!=null){
						procard.setKlNumber(procard.getKlNumber()+maxbreakscount);
					}else{
						procard.setKlNumber(maxbreakscount);
					}
					totalDao.update(procard);
				}else{
					//更新工序的不合格数量
					old.setBreakCount(old.getBreakCount()+buhege);
					totalDao.update(old);
					/**
					 * 更新后面工序的已领数量
					 */
					String afterhql = " from ProcessInfor where procard.id = ? and processNO >?";
					List<ProcessInfor>	 processList =totalDao.query(afterhql, procard.getId(),old.getProcessNO());
					if(processList!=null && processList.size()>0){
						for (ProcessInfor processInfor1 : processList) {
							processInfor1.setApplyCount(old.getBreakCount());
							totalDao.update(processInfor1);
						}
				}
				}
				
				
				
				//不良品检验记录
				String days = Util.getDateTime("yyyy年MM月dd日");
				String months = Util.getDateTime("yyyy年MM月");
				failureSt.setFailureCount(buhege);
				failureSt.setAdddays(days);
				failureSt.setAddmonths(months);
				failureSt.setFsdSet(fsdSet);
				failureSt.setType(type);
				Float nowberakcount =	(Float) totalDao.getObjectByCondition("select sum(failureCount) from FailureStatistics where selfcard = ? and markId = ? ", failureSt.getSelfcard(),failureSt.getMarkId()); 
				failureSt.setNowberakcount(nowberakcount+buhege);//记录当前时间节点该批次件号不合格品总数
				totalDao.save(failureSt);
				Set<FailureStatisticsDetail> fsdSet1 =	failureSt.getFsdSet();
				//统计日报表
				String gongweisql = "";
				if(gongwei!=null && !"".equals(gongwei)){
					gongweisql = " and gongwei = '"+gongwei+"'";
				}else{
					gongweisql = " and (gongwei is null or gongwei = '')";
				}
				FailureSSOnDay fsday  =	(FailureSSOnDay) totalDao.getObjectByCondition("  from FailureSSOnDay where days = ? "+gongweisql, days);
				if(fsday!=null){
					Set<FailureStatisticsDetail>  fsddaySet = 	fsday.getFsdSet();
					for (FailureStatisticsDetail f : fsdSet1) {
						FailureStatisticsDetail  fsd =  (FailureStatisticsDetail) totalDao.getObjectByCondition(" from FailureStatisticsDetail where failureSSOnDay.id =? and buhegeId = ?", fsday.getId(),f.getBuhegeId());
						if(fsd!=null){
							fsd.setBadNumber(fsd.getBadNumber()+f.getBadNumber());
							totalDao.update(fsd);
						}else{
							if(fsddaySet == null){
								fsddaySet = new HashSet<FailureStatisticsDetail>();
							}
							fsd = new FailureStatisticsDetail();
							fsd.setBadNumber(f.getBadNumber());
							fsd.setBuhegeId(f.getBuhegeId());
							fsd.setCode(f.getCode());
							fsd.setType(f.getType());
							fsd.setFailureSSOnDay(fsday);
							fsddaySet.add(fsd);
						}
					}
					fsday.setOneDayFc(fsday.getOneDaySc()+failureSt.getFailureCount());//每日不合格总数 某工位
					fsday.setOneDaySc(fsday.getOneDaySc()+failureSt.getSubmitCount());//每日检验总数 某工位
					fsday.setFsdSet(fsddaySet);
					totalDao.update(fsday);
				}else{
					fsday = new FailureSSOnDay();
					fsday.setOneDayFc(failureSt.getFailureCount());//每日不合格总数 某工位
					fsday.setOneDaySc(failureSt.getSubmitCount());//每日检验总数 某工位
					fsday.setAddTime(Util.getDateTime());
					fsday.setGongwei(failureSt.getGongwei());
					fsday.setDays(days);
					fsday.setMonths(months);
					fsday.setWeekds(weeks);
					Set<FailureStatisticsDetail>  fsddaySet = 	fsday.getFsdSet();
					for (FailureStatisticsDetail f : fsdSet1) {
						FailureStatisticsDetail	fsd = new FailureStatisticsDetail();
							fsd.setBadNumber(f.getBadNumber());
							fsd.setBuhegeId(f.getBuhegeId());
							fsd.setCode(f.getCode());
							fsd.setType(f.getType());
							fsd.setFailureSSOnDay(fsday);
							if(fsddaySet == null){
								fsddaySet = new HashSet<FailureStatisticsDetail>();
							}
							fsddaySet.add(fsd);
					}
					fsday.setFsdSet(fsddaySet);
					totalDao.save(fsday);
				}
				//统计周报表
				FailureSSOnWeek fsweek  =	(FailureSSOnWeek) totalDao.getObjectByCondition("  from FailureSSOnWeek where  weekds = ? "+gongweisql, weeks);
				if(fsweek!=null){
					Set<FailureStatisticsDetail>  fsdweekSet = 	fsweek.getFsdSet();
					for (FailureStatisticsDetail f : fsdSet1) {
						FailureStatisticsDetail  fsd =  (FailureStatisticsDetail) totalDao.getObjectByCondition(" from FailureStatisticsDetail where failureSSOnWeek.id =? and buhegeId = ?", fsweek.getId(),f.getBuhegeId());
						if(fsd!=null){
							fsd.setBadNumber(fsd.getBadNumber()+f.getBadNumber());
							totalDao.update(fsd);
						}else{
							if(fsdweekSet == null){
								fsdweekSet = new HashSet<FailureStatisticsDetail>();
							}
							fsd = new FailureStatisticsDetail();
							fsd.setBadNumber(f.getBadNumber());
							fsd.setBuhegeId(f.getBuhegeId());
							fsd.setCode(f.getCode());
							fsd.setType(f.getType());
							fsd.setFailureSSOnWeek(fsweek);
							fsdweekSet.add(fsd);
						}
					}
					fsweek.setOneWeekFc(fsweek.getOneWeekFc()+failureSt.getFailureCount());//每周不合格总数 某工位
					fsweek.setOneWeekSc(fsweek.getOneWeekSc()+failureSt.getSubmitCount());//每周检验总数 某工位
					fsweek.setFsdSet(fsdweekSet);
					totalDao.update(fsweek);
				}else{
					fsweek = new FailureSSOnWeek();
					fsweek.setOneWeekFc(failureSt.getFailureCount());//每周不合格总数 某工位
					fsweek.setOneWeekSc(failureSt.getSubmitCount());//每周检验总数 某工位
					fsweek.setAddTime(Util.getDateTime());
					fsweek.setGongwei(failureSt.getGongwei());
					fsweek.setWeekds(weeks);
					Set<FailureStatisticsDetail>  fsdweekSet = 	fsweek.getFsdSet();
					for (FailureStatisticsDetail f : fsdSet1) {
						FailureStatisticsDetail	fsd = new FailureStatisticsDetail();
							fsd.setBadNumber(f.getBadNumber());
							fsd.setBuhegeId(f.getBuhegeId());
							fsd.setCode(f.getCode());
							fsd.setType(f.getType());
							fsd.setFailureSSOnWeek(fsweek);
							if(fsdweekSet == null){
								fsdweekSet = new HashSet<FailureStatisticsDetail>();
							}
							fsdweekSet.add(fsd);
					}
					fsweek.setFsdSet(fsdweekSet);
					totalDao.save(fsweek);
				}
				//统计月报表
				FailureSSOnMonth fsdmonth  =	(FailureSSOnMonth) totalDao.getObjectByCondition("  from FailureSSOnMonth where months = ? "+gongweisql, months);
				if(fsdmonth!=null){
					Set<FailureStatisticsDetail>  fsdmonthSet = 	fsweek.getFsdSet();
					for (FailureStatisticsDetail f : fsdSet1) {
						FailureStatisticsDetail  fsd =  (FailureStatisticsDetail) totalDao.getObjectByCondition(" from FailureStatisticsDetail where failureSSOnWeek.id =? and buhegeId = ?", fsweek.getId(),f.getBuhegeId());
						if(fsd!=null){
							fsd.setBadNumber(fsd.getBadNumber()+f.getBadNumber());
							totalDao.update(fsd);
						}else{
							if(fsdmonthSet == null){
								fsdmonthSet = new HashSet<FailureStatisticsDetail>();
							}
							fsd = new FailureStatisticsDetail();
							fsd.setBadNumber(f.getBadNumber());
							fsd.setBuhegeId(f.getBuhegeId());
							fsd.setCode(f.getCode());
							fsd.setType(f.getType());
							fsd.setFailureSSOnMonth(fsdmonth);
							fsdmonthSet.add(fsd);
						}
					}
					fsdmonth.setOneMonthSc(fsdmonth.getOneMonthSc()+failureSt.getFailureCount());//每月不合格总数 某工位
					fsdmonth.setOneMonthFc(fsdmonth.getOneMonthFc()+failureSt.getSubmitCount());//每月检验总数 某工位
					fsdmonth.setFsdSet(fsdmonthSet);
					totalDao.update(fsdmonth);
				}else{
					fsdmonth = new FailureSSOnMonth();
					fsdmonth.setOneMonthSc(failureSt.getFailureCount());//每月不合格总数 某工位
					fsdmonth.setOneMonthFc(failureSt.getSubmitCount());//每月检验总数 某工位
					fsdmonth.setAddTime(Util.getDateTime());
					fsdmonth.setGongwei(failureSt.getGongwei());
					fsdmonth.setWeekds(weeks);
					Set<FailureStatisticsDetail>  fsdmonthSet = 	fsdmonth.getFsdSet();
					for (FailureStatisticsDetail f : fsdSet1) {
						FailureStatisticsDetail	fsd = new FailureStatisticsDetail();
							fsd.setBadNumber(f.getBadNumber());
							fsd.setBuhegeId(f.getBuhegeId());
							fsd.setCode(f.getCode());
							fsd.setType(f.getType());
							fsd.setFailureSSOnMonth(fsdmonth);
							if(fsdmonthSet == null){
								fsdmonthSet = new HashSet<FailureStatisticsDetail>();
							}
							fsdmonthSet.add(fsd);
					}
					fsdmonth.setFsdSet(fsdmonthSet);
					totalDao.save(fsdmonth);
				}
			}else{
				CheckAlert ca = new CheckAlert();
//				ca.setJyId(sticker.getId());//首检或者巡检的id：LogoStickers
				ca.setMarkId(procard.getMarkId());//件号
				ca.setSelfCard(procard.getSelfCard());
				ca.setProName(procard.getProName());//零件名称
				ca.setProcessNo(old.getProcessNO());//工序号
				ca.setProcessName(old.getProcessName());//工序名称
				ca.setGongwei(old.getGongwei());
				ca.setShebeino(old.getShebeiNo());
				String time = Util.getDateTime();
				ca.setAddTime(time);//添加时间
				ca.setUserId(user.getId());//检验员Id
				ca.setUserCode(user.getCode());//检验员工号
				ca.setUserName(user.getName());//检验员名字
				String time2=null;
				try {
					time2 = Util.aftertime(time, 2*3600*1000l);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ca.setCheckTime(time2);//检验时间
				ca.setStatus("待通知");//(待通知，已通知，完成)
				totalDao.save(ca);
			}
				
		}
		return totalDao.update(old) + "";
	}

	@Override
	public List<Waigoujianpinci> findAllxjbzlist() {
		String hql = "from Waigoujianpinci where type = '巡检'";
		return totalDao.query(hql);
	}

	@Override
	public String Ostbdxjbz(OsTemplate ost) {
		if (ost != null && ost.getId()!=null) {
			OsTemplate oldost = (OsTemplate) totalDao.get(OsTemplate.class, ost
					.getId());
			Waigoujianpinci xjbz = (Waigoujianpinci) totalDao.get(
					Waigoujianpinci.class, ost.getXjbzId());
			oldost.setXjbzId(xjbz.getId());
			oldost.setXjbz(xjbz.getLeixing());
			return totalDao.update(oldost)+"";
		}
		return "没有巡检模板!";
	}

	@Override
	public WaigoujianpinciZi findxunjianpici(ProcessInfor processInfor,
			OsTemplate osTemplate) {
		if (processInfor != null && osTemplate != null &&osTemplate.getXjbzId()!=null ) {
			Float number = processInfor.getApplyCount();
			String hql =  " from WaigoujianpinciZi where waigoujianpinciId = "+osTemplate.getXjbzId()+" and  caigoushuliang1<= "+number+" and  caigoushuliang2 >= "+number+" ";
 			return (WaigoujianpinciZi) totalDao.getObjectByCondition(hql);
		}

		return null;
	}
	@Override
	public BreakSubmit findbreaksubmitById(Integer id) {
		if(id!=null){
			return (BreakSubmit) totalDao.get(BreakSubmit.class, id);
		}
		return null;
	}

	@Override
	public Object[]  getOsTemplate(Integer breakId ) {
		if(breakId!=null){
			BreakSubmit breaksubmit =	(BreakSubmit) totalDao.get(BreakSubmit.class, breakId);
			Procard wgprocard =	(Procard) totalDao.getObjectByCondition(" from Procard where fatherId = ? and markId = ? ",breaksubmit.getProcardId(),breaksubmit.getWgmarkId() );
			
			String hql = " from OsTemplate where partNumber = ? and zhonglei = ?";
			if(wgprocard.getBanBenNumber()!=null && wgprocard.getBanBenNumber().length()>0){
				hql+=" and banbenNumber = '"+wgprocard.getBanBenNumber()+"' ";
			}else{
				hql+=" and (banbenNumber is null or banbenNumber = '')";
			}
			OsTemplate ost=  (OsTemplate) totalDao.getObjectByCondition(hql, wgprocard.getMarkId(),"外购件检验");
			List<OsScope> ossList=null;
			WaigoujianpinciZi xujianpingci = null;
			if(ost==null){
				// 读取通用模版
				String hql_ty = "from OsTemplate where ispublic='是' and zhonglei='外购件检验' ";
					ost = (OsTemplate) totalDao.getObjectByCondition(hql_ty);
			}
			if(ost!=null){
				 ossList = totalDao.query(" from OsScope where osTemplate.id = ?", ost.getId());
				 if(ost.getXjbzId()!=null){
					 Float number = 0f;
					 number = breaksubmit.getTjbreakcount();
					 number =number/ wgprocard.getQuanzi2();
					 String hql1 =  " from WaigoujianpinciZi where waigoujianpinciId = ? and  caigoushuliang1<= ? and  caigoushuliang2 >= ? ";
					  xujianpingci =	 (WaigoujianpinciZi) totalDao.getObjectByCondition(hql1,ost.getXjbzId(),number,number);
					 xujianpingci.setChoujian(number);
				 }
				 
			}
		
			return new Object[]{breaksubmit,wgprocard,ost,ossList,xujianpingci};
		}
		return null;
	}

	@Override
	public List<ProcessAndMeasuring> pamListByProcessName(String processNames) {
		if(processNames!=null && processNames.length()>0){
			return 	totalDao.query(" from ProcessAndMeasuring where processName =?",processNames);
		}
		return null;
	}

}
