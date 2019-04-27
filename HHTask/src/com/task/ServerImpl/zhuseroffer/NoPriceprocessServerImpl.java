package com.task.ServerImpl.zhuseroffer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.zhuseroffer.NoPriceprocessServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.system.CircuitRun;
import com.task.entity.zhuseroffer.NoPriceprocess;
import com.task.entity.zhuseroffer.Sample;
import com.task.entity.zhuseroffer.SumProcess;
import com.task.entity.zhuseroffer.ZhuserOffer;
import com.task.util.Upload;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

public class NoPriceprocessServerImpl implements NoPriceprocessServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	public String bandZhuser(Integer[] zhUserId,Integer noPriceprocessId){
		if(zhUserId.length>0&&zhUserId!=null){
			for(Integer ids : zhUserId){
				String zOffer_hql = "from ZhuserOffer where zhUserId=? and processId =?";
				ZhuserOffer zOffer_old  = (ZhuserOffer)totalDao.getObjectByCondition(zOffer_hql,
																				ids,noPriceprocessId);
				ZhUser zhUser = (ZhUser)totalDao.get(ZhUser.class, ids);
				if(zOffer_old==null){
					ZhuserOffer zhuserOffer = new ZhuserOffer();
					//添加供应商属性
					zhuserOffer.setZhUser(zhUser);
					zhuserOffer.setZhUserId(zhUser.getId());
					zhuserOffer.setCmp(zhUser.getCmp());
					zhuserOffer.setUsercode(zhUser.getUsercode());
					zhuserOffer.setCperson(zhUser.getCperson());
					zhuserOffer.setCtel(zhUser.getCtel());
					//添加时间
					zhuserOffer.setJoinDate(Util.getDateTime("yyyy-MM-dd"));
					//添加工序号 工序名称 件号
					NoPriceprocess noPriceprocess=(NoPriceprocess)totalDao.get(NoPriceprocess.class, noPriceprocessId);
					if(noPriceprocess!=null){
						zhuserOffer.setMarkId(noPriceprocess.getMarkId());
						zhuserOffer.setProcessName(noPriceprocess.getProcessName());
						zhuserOffer.setProcessNO(noPriceprocess.getProcessNO());
						zhuserOffer.setProcessId(noPriceprocess.getId());
						zhuserOffer.setCycle(noPriceprocess.getCycle());
						zhuserOffer.setEndDate(noPriceprocess.getBjEndDate());
						zhuserOffer.setStatus("未报价");
						totalDao.save(zhuserOffer);
						noPriceprocess.setStutas("报价中");
						totalDao.update(noPriceprocess);
					}else{
						return "未找到需要报价的工序!";
					}
					}else{
						return "供应商("+zhUser.getCmp()+")已经参与报价！";
				}
			}
			return "报价成功！";
		}else{
			return "未选择供应商，无法添加！！";
		}
	}
	//供应商报价页面预备总成件号报价表头
	public List<SumProcess> findSumProcessForZhuser(){
		Users user = Util.getLoginUser();
		//usercode='"+user.getCode()+"' and
		String hql = "select sumProcessId from ZhuserOffer where usercode='"+user.getCode()+"' and  processId <> null and sumProcessId <>null group by sumProcessId";
		List<Integer> ids =  totalDao.query(hql);
		String id_str = "";
		for(Integer id : ids){
			if("".equals(id_str)){
				id_str +=id+"";
			}else{
				id_str += ","+id ;
			}
		}
		if(!"".equals(id_str)){
			String hql_sum = "from SumProcess where id in ("+id_str+") and stutas != '删除'";
			List<SumProcess> spList = totalDao.find(hql_sum);
			return spList;
		}
		return null;
	}
	
	public List<ZhuserOffer>  zhuserOfferFor(Integer id){
		Users user = Util.getLoginUser();
		String hql = "from ZhuserOffer where usercode ='"+user.getCode()+"' and processId <> null and sumProcessId = ?";
		List<ZhuserOffer> zoList = totalDao.query(hql, id);
		return zoList;
	}
	
	public List<NoPriceprocess>  zhuserOfferForQr(Integer id){
		String hql = "from NoPriceprocess where   sumProcessId = ?";
		List<NoPriceprocess> zoList = totalDao.query(hql, id);
		return zoList;
	}
	//供应商报价列表
	public Map<Integer, Object> baojiaForProcess(ZhuserOffer zhuserOffer,int pageNo, int pageSize){
		if (zhuserOffer == null) {
			zhuserOffer= new ZhuserOffer();
		}
		Users user = Util.getLoginUser();
		if(user!=null){
			String hql = "from ZhuserOffer where usercode ='"+user.getCode()+"' and processId <> null and sumProcessId = null";
			int count = totalDao.getCount(hql);
			List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map.put(1, objs);
			map.put(2, count);
			return map;
		}
		return null;
	}
	
	//供应商报价
	public String baojiaForZhuser(ZhuserOffer zhuserOffer){
		if(zhuserOffer!=null){
			ZhuserOffer zhuserOffer_old = (ZhuserOffer)totalDao.get(ZhuserOffer.class,zhuserOffer.getId());
			if(zhuserOffer_old!=null){
				zhuserOffer_old.setHsPrice(zhuserOffer.getHsPrice());
				zhuserOffer_old.setBhsPrice(zhuserOffer.getBhsPrice());
				zhuserOffer_old.setTaxprice(zhuserOffer.getTaxprice());
				Upload u = new Upload();
				String uploadPath = "/upload/file/biaojiaFile";
				File file1 = new File(uploadPath);
				if (!file1.exists()) {
					file1.mkdirs();// 如果不存在文件夹就创建
				}
				if (zhuserOffer.getBaojiaF()!=null&&!"".equals(zhuserOffer.getBaojiaFFileName())) {
					zhuserOffer_old.setBaojia(u.UploadFile(zhuserOffer.getBaojiaF(), zhuserOffer.getBaojiaFFileName(),null,uploadPath,null));
				} 
				zhuserOffer_old.setStatus("已报价"); 
				totalDao.update(zhuserOffer_old);
				return "报价成功";
			}else{
				return "找不到报价表";
			}
		}else{
			return "找不到报价表";
		}
	}
	
	public ZhuserOffer findzhuserOffer(Integer id){
		return (ZhuserOffer)totalDao.get(ZhuserOffer.class, id);
	}
	//确认报价页面
	public List<ZhuserOffer> querenYemian(Integer noPriceprocessId){
		if(noPriceprocessId!=null&&!"".equals(noPriceprocessId)){
			String hql = "from ZhuserOffer where processId = ?";
			List<ZhuserOffer> zList =totalDao.query(hql, noPriceprocessId);
			return zList;
		}else{
			return null;
		}
	}
	//确认报价
	public String querenPrice(Integer[] zhOfferId,Integer noPriceprocessId){
			NoPriceprocess noPriceprocess =(NoPriceprocess)totalDao.get(NoPriceprocess.class, noPriceprocessId);
			String nowdate = Util.getDateTime("yyyy-MM-dd");
			if(nowdate.compareTo(noPriceprocess.getBjEndDate())<0){
				return "未到确认期限，请到"+noPriceprocess.getBjEndDate()+"报价结束后在确定";
			}
			for(Integer ids :zhOfferId){
				ZhuserOffer z = (ZhuserOffer)totalDao.get(ZhuserOffer.class, ids);
				z.setStatus("打样");
				if(z!=null){
						totalDao.update(z);
					}else{
						return "没有该报价订单";
					}
				}
				String hql = "from ZhuserOffer where processId ="+noPriceprocess.getId()+" and status <>'打样' ";
				List<ZhuserOffer> s = totalDao.find(hql);
				for(ZhuserOffer z2 : s){
					z2.setStatus("结束");
					totalDao.update(z2);
				}
				noPriceprocess.setStutas("打样中");
				totalDao.update(noPriceprocess);
				if(noPriceprocess.getSumProcessId()!=null){
					String hql_np ="select stutas from NoPriceprocess where sumProcessId= ? group by stutas";
					List<String> hql_zh = totalDao.query(hql_np, noPriceprocess.getSumProcessId());
					if(hql_zh.size()==1&&hql_zh.get(0).equals("打样中")){
						SumProcess sp = (SumProcess)totalDao.get(SumProcess.class, noPriceprocess.getSumProcessId());
						sp.setStutas("打样中");
						totalDao.update(sp);
					}
				}
				return "确认完成，通知供应商发生样品！";
	}
	//确认样品
	public String passYangpin(Integer[] zhOfferId,Integer noPriceprocessId){
		NoPriceprocess noPriceprocess =(NoPriceprocess)totalDao.get(NoPriceprocess.class, noPriceprocessId);
		for(Integer ids :zhOfferId){
			ZhuserOffer z = (ZhuserOffer)totalDao.get(ZhuserOffer.class, ids);
			String hql = "from Sample where zhuserOffer.id=?";
			Sample s = (Sample)totalDao.getObjectByQuery(hql, z.getId());
			if("分析文档已提交".equals(s.getStatus())){
				String processName = "样品(工序)审批流程";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							Sample.class, s.getId(), "status",
							"id",
							"ZhuserOfferAction_findSampleforGongxu.action?zhuserOffer.id="+s.getId(), 
									"样品是否通过，请您审批", true);
					if (epId != null && epId > 0) {
						s.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							s.setStatus("同意");
						} else {
							s.setStatus("未审批");
						}
						z.setStatus("审批中");
						totalDao.update(z);
						noPriceprocess.setStutas("审批中");
						totalDao.update(noPriceprocess);
						if(noPriceprocess.getSumProcessId()!=null){
							String hql_np ="select stutas from NoPriceprocess where sumProcessId= ? group by stutas";
							List<String> hql_zh = totalDao.query(hql_np, noPriceprocess.getSumProcessId());
							if(hql_zh.size()==1&&hql_zh.get(0).equals("审批中")){
								SumProcess sp = (SumProcess)totalDao.get(SumProcess.class, noPriceprocess.getSumProcessId());
								sp.setStutas("审批中");
								totalDao.update(sp);
							}
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return totalDao.update(s)+"";
			
			}else{
				return "未找到原样品记录";
			}
		}
		return null;
	}
	//录入价格
	public String luruPrice(NoPriceprocess noPriceprocess){
		noPriceprocess = (NoPriceprocess)totalDao.get(NoPriceprocess.class, noPriceprocess.getId());
		if(noPriceprocess!=null){
			String hql = "from ZhuserOffer where processId ="+noPriceprocess.getId()+" and status = '同意' ";
			ZhuserOffer z = (ZhuserOffer)totalDao.getObjectByCondition(hql, null);
			if("同意".equals(z.getStatus())){
				Price p = new Price();
				p.setHsPrice(z.getHsPrice());
				p.setBhsPrice(z.getBhsPrice());
				p.setTaxprice(z.getTaxprice());
				p.setPartNumber(z.getMarkId());
				p.setGysId(z.getZhUserId());
				p.setGys(z.getCmp());
				p.setChargePerson(z.getCperson());
				p.setGongxunum(z.getProcessNO());
				p.setProcessNames(z.getProcessName());
				if(totalDao.save(p)){
					noPriceprocess.setStutas("有效");
					totalDao.update(noPriceprocess);
					if(noPriceprocess.getSumProcessId()!=null){
						String hql_np ="select stutas from NoPriceprocess where sumProcessId= ? group by stutas";
						List<String> hql_zh = totalDao.query(hql_np, noPriceprocess.getSumProcessId());
						SumProcess sp = (SumProcess)totalDao.get(SumProcess.class, noPriceprocess.getSumProcessId());
						if(hql_zh.size()==1&&hql_zh.get(0).equals("有效")){
							sp.setStutas("有效");
							totalDao.update(sp);
						}else{
							String hql_MarkId ="select markId from NoPriceprocess where sumProcessId= ? and stutas <> '有效'";
							List<String> sList = totalDao.query(hql_MarkId, noPriceprocess.getSumProcessId());
							String a =  "";
							for(String s :sList){
								a +=s+"未完成";
							}
							sp.setStutas(a);
							totalDao.update(sp);
						}
					}
				}
				return "价格录入成功！";
			}else{
				return "该报价未通过审批！";
			}
		}else{
			return "该工序不存在";
		}
	}
	public NoPriceprocess getById(Integer id) {
		return (NoPriceprocess) totalDao
				.getObjectById(NoPriceprocess.class, id);
	}
	public Map<Integer, Object> findAll(NoPriceprocess noPriceprocess,int pageNo, int pageSize,String s) {
		if (noPriceprocess == null) {
			noPriceprocess= new NoPriceprocess();
		}
		String hql_queren = ""; 
		if("queren".equals(s)){
			hql_queren =" stutas = '报价中'  and sumProcessId = null";
		}else if("baojia".equals(s)){
			hql_queren =" (stutas = '报价中' or stutas = null or stutas = '') and sumProcessId = null";
		}else if("yangpin".equals(s)){
			hql_queren ="stutas = '打样中'  and sumProcessId = null";
		}else if("luru".equals(s)){
			hql_queren ="stutas = '同意'  and sumProcessId = null";	
		}else if("chaxun".equals(s)){//总成件号报价查询所有
			hql_queren =" (stutas = null or stutas = '' or (stutas!='删除' and stutas!='作废')) and sumProcessId = null";
		}
		String hql = totalDao.criteriaQueries(noPriceprocess,hql_queren,null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public Map<Integer, Object> findAllBys(NoPriceprocess noPriceprocess,int pageNo, int pageSize){
		if (noPriceprocess == null) {
			noPriceprocess= new NoPriceprocess();
		}
		String hql = totalDao.criteriaQueries(noPriceprocess,null,null);
		hql=hql+" order by stutas";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public Map<Integer, Object> findZhuser(ZhUser zhUser,int pageNo, int pageSize){
		if(zhUser==null){
			zhUser = new ZhUser();
		}
		String hql =totalDao.criteriaQueries(zhUser,null,null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public NoPriceprocess findNoPriceprocessByid(Integer id) {
		return (NoPriceprocess)totalDao.get(NoPriceprocess.class, id);
	}
	
	public boolean addTime(Integer noPriceprocessId,String deadline){
		NoPriceprocess noPriceprocess =(NoPriceprocess)totalDao.get(NoPriceprocess.class, 
				noPriceprocessId);
		if(noPriceprocess!=null){
			Integer deadline_new =Integer.parseInt(deadline);
			noPriceprocess.setBjStartDate(Util.getDateTime("yyyy-MM-dd"));
			noPriceprocess.setCycle(deadline);
			noPriceprocess.setBjEndDate(Util.getSpecifiedDayAfter(Util.getDateTime("yyyy-MM-dd"),
					deadline_new));
			return totalDao.update(noPriceprocess);
		}
		return false;
	}
	/*
	 * 所有的 SumProcess用于显示
	 */
	public List<SumProcess> findAllSumProcess(String tag){
		String hql = "from SumProcess";
		if("baojia".equals(tag)){
			hql +=" where stutas = '待选择供应商' or stutas = '报价中'";
		}else if("yangpin".equals(tag)){
			hql +=" where stutas = '打样中'";
		}else if("jiage".equals(tag)){
			hql +=" where stutas = '同意' or stutas = '审批中'";
		}
		List<SumProcess> spList = totalDao.find(hql);
		return spList;
	}
	public List<NoPriceprocess> findSumProcessById(Integer sumProcessId){
		List<NoPriceprocess> spList = totalDao.query("from NoPriceprocess where sumProcessId = ?", sumProcessId);
		return spList;
	}
	/**
	 * 准备需要报价的工序for填写报价周期
	 * @param offerId
	 * @return
	 */
	public List<NoPriceprocess> zhouqiForMarkId(String offerId){
		//查询总成件号
//		String hql = "select rootMarkId from NoPriceprocess where id in ("+offerId+") group by rootMarkId";
//		List<String> isOne = totalDao.find(hql);
//		if(isOne.size()==1){
		if(offerId!=null){
			List<NoPriceprocess> npList = totalDao.query("from NoPriceprocess where id in ("+offerId+")");
			return npList;
		}else{
			return null;
		}
//		}else{
//			return null;
//		}
	}
	@Override
	public boolean addTimeForMarkId(String offerId, String deadline) {
		Integer deadline_new =Integer.parseInt(deadline);
		List<NoPriceprocess> npList = totalDao.query("from NoPriceprocess where id in ("+offerId+")");
		Integer count = totalDao.getCount(("from NoPriceprocess where id in ("+offerId+")"));
		if(npList!=null&&npList.size()>0){
			String markId =  "";
			int i = 0;
			for(NoPriceprocess np:npList){
				//拼接markId
				if(i==0){
					markId += np.getMarkId();
					
				}else{
					markId += ","+np.getMarkId();
				}
				i++;
			}
			//根据总成件号生产总表
			SumProcess sp = new SumProcess();
			Users u = Util.getLoginUser();
			String title = u.getName()+Util.getDateTime("yyyyMMdd");
			String hql_title ="from SumProcess where title like '%"+title+"%' and title is not null order by id DESC";
			List<SumProcess> spList = totalDao.query(hql_title,null);
			if(spList!=null&&spList.size()>0){
				String title_old = spList.get(0).getTitle();
				String title_new = title_old.substring(title_old.length()-4,title_old.length());
				int a = Integer
						.parseInt(title_new);
				a++;
				String s ="";
				if (a >=0 && a < 10){
					s = "00" + a; // 1~9之间
				}
				else if (a<100) {
					s = "0" + a; // 10~99之间
				}  else if (a < 1000) {
					s = String.valueOf(a); // 1000~9999之间
				}
				title +=s;
			}else{
				title +="000";
			}
			sp.setTitle(title);
			sp.setMarkId(markId);
			sp.setCount(count.toString());
			sp.setAddTime(Util.getDateTime("yyyy-MM-dd HH:MM:SS"));
			sp.setBjStartDate(Util.getDateTime("yyyy-MM-dd"));
			sp.setBjEndDate(Util.getSpecifiedDayAfter(Util.getDateTime("yyyy-MM-dd"),
					deadline_new));
			sp.setCycle(deadline);
			sp.setStutas("待选择供应商");
			totalDao.save(sp);
			for(NoPriceprocess np:npList){
				//添加报价周期
				if(np!=null){
					np.setBjStartDate(Util.getDateTime("yyyy-MM-dd"));
					np.setCycle(deadline);
					np.setBjEndDate(Util.getSpecifiedDayAfter(Util.getDateTime("yyyy-MM-dd"),
							deadline_new));
					np.setSumProcessId(sp.getId());
					totalDao.update(np);
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	public SumProcess findOneByid(Integer id){
		SumProcess sp = (SumProcess)totalDao.get(SumProcess.class, id);
		return sp;
	}
	/*
	 * 带总成件号的绑定供应商
	 */
	public String bandZhuserForMarkId(Integer[] zhUserId,Integer sumProcessId){
		if(sumProcessId!=null&&!"".equals(sumProcessId)){
			//获取和sumProcessId所有相关的NoPriceprocess
			List<NoPriceprocess> npList = totalDao.query("FROM NoPriceprocess where sumProcessId = ?", sumProcessId);
			String str = "";
			for(NoPriceprocess np : npList){
				for(Integer ids : zhUserId){
					//报价单是否存在
					String zOffer_hql = "from ZhuserOffer where zhUserId=? and processId =?";
					ZhuserOffer zOffer_old  = (ZhuserOffer)totalDao.getObjectByCondition(zOffer_hql,
							ids,np.getId());
					//获取供应商信息
					ZhUser zhUser = (ZhUser)totalDao.get(ZhUser.class, ids);
					if(zOffer_old==null){
						ZhuserOffer zhuserOffer = new ZhuserOffer();
						//添加供应商属性
						zhuserOffer.setZhUser(zhUser);
						zhuserOffer.setZhUserId(zhUser.getId());
						zhuserOffer.setCmp(zhUser.getCmp());
						zhuserOffer.setUsercode(zhUser.getUsercode());
						zhuserOffer.setCperson(zhUser.getCperson());
						zhuserOffer.setCtel(zhUser.getCtel());
						zhuserOffer.setJoinDate(Util.getDateTime("yyyy-MM-dd"));
						zhuserOffer.setMarkId(np.getMarkId());
						zhuserOffer.setProcessName(np.getProcessName());
						zhuserOffer.setProcessNO(np.getProcessNO());
						zhuserOffer.setProcessId(np.getId());
						zhuserOffer.setStatus("未报价");
						zhuserOffer.setSumProcessId(sumProcessId);
						totalDao.save(zhuserOffer);
						np.setStutas("报价中");
						totalDao.update(np);
						SumProcess sp = (SumProcess)totalDao.get(SumProcess.class, sumProcessId);
						sp.setStutas("报价中");
						totalDao.update(sp);
					}else{
						str += "供应商("+zhUser.getCmp()+")已经参与("+np.getMarkId()+")报价！" +"<br/>";
						
					}
				}
			}
			if("".equals(str)){
				return "报价成功";
			}else{
				return str;
			}
		}else{
			return "没有总成报价单";
		}
	}
	public String deleteSumProcess(Integer id){
		if(id!=null){
			SumProcess sp  = (SumProcess)totalDao.get(SumProcess.class, id);
			if(sp!=null){
				sp.setStutas("删除");
				if(totalDao.update(sp)){
					List<NoPriceprocess> npList  = totalDao.query("from NoPriceprocess where sumProcessId = ?", sp.getId());
					if(npList!=null&&npList.size()>0){
						for(NoPriceprocess np:npList){
							np.setStutas("删除");
							totalDao.update(np);
						}
					}else{
						return "数据异常";
					}
					List<ZhuserOffer> zoList  = totalDao.query("from ZhuserOffer where sumProcessId = ?", sp.getId());
					if(zoList!=null&&zoList.size()>0){
						for(NoPriceprocess np:npList){
							np.setStutas("删除");
							totalDao.update(np);
						}
					}else{
						return "数据异常";
					}
					return "删除成功";
				}else{
					return "删除失败";
				}
			}else{
				return "数据异常";
			}
		}else{
			return "数据异常";
		}
		
	}
}
