package com.task.ServerImpl.sop.muju;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.BeanUtils;

import com.sun.jna.platform.win32.Netapi32Util.User;
import com.task.Dao.TotalDao;
import com.task.Server.ess.GoodsStoreServer;
import com.task.Server.sop.muju.MouldApplyOrderServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.AlertMessages;
import com.task.entity.Becoming;
import com.task.entity.Careertrack;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.project.ProjectWenJian;
import com.task.entity.sop.WaigouOrder;
import com.task.entity.sop.WaigouPlan;
import com.task.entity.sop.muju.MouldApplyOrder;
import com.task.entity.sop.muju.MouldDetail;
import com.task.entity.sop.muju.MouldPingGu;
import com.task.entity.system.CircuitRun;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;


public class MouldApplyOrderServerImpl implements MouldApplyOrderServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String addMoa(MouldApplyOrder moa,File[] attachment, String[] attachmentFileName,String [] otherNames) {
		Users user = Util.getLoginUser();
		if(user ==null){
			return "请先登录!";
		}
		if(moa!=null){
			moa.setAddtime(Util.getDateTime());
			moa.setAddUserName(user.getName());
			String mojuNo = "";
			if(moa.getMdList()!=null && moa.getMdList().size()>0){
				List<MouldDetail> mdList = moa.getMdList();
				Set<MouldDetail> mdSet = new HashSet<MouldDetail>();
				for(int i=0;i<mdList.size();i++){
					MouldDetail md = mdList.get(i);
					md.setMao(moa);
					mojuNo = md.getMojuNo();
					mdSet.add(md);
				}
				List<MouldPingGu> mpgList = moa.getMpgList();
				Set<MouldPingGu> mpgSet = new HashSet<MouldPingGu>();
				Integer [] userIds = new Integer [3];
				int count = 0;
				for (MouldPingGu mouldPingGu : mpgList) {
					if(mouldPingGu.getUsersId()!=null){
						Users pgusers =	(Users) totalDao.get(Users.class, mouldPingGu.getUsersId());
						userIds[count]=pgusers.getId();
						mouldPingGu.setUserName(pgusers.getName());
						mouldPingGu.setDept(pgusers.getDept());
					}
					mouldPingGu.setMao(moa);
					mpgSet.add(mouldPingGu);
					count++;
				}
				// 附件处理
				Set<ProjectWenJian> projectWenjianSet = new HashSet<ProjectWenJian>();
				if (attachment != null && attachment.length > 0) {
					for (int i = 0; i < attachment.length; i++) {
						ProjectWenJian projectWenjian = new ProjectWenJian();
						File file = attachment[i];
						String fileName = attachmentFileName[i];
						Upload upload = new Upload();
						String newFileName = upload.UploadFile(file, fileName,
								null, "/upload/file/MouldApplyOrder",
								"D:/WorkSpace/HHTask/WebRoot/upload/file/MouldApplyOrder");
						projectWenjian.setFileName(newFileName);
						projectWenjian.setOtherName(otherNames[i]);
						projectWenjian.setMao(moa);
						projectWenjianSet.add(projectWenjian);
						
					}
				}
				moa.setProjectWenJianSet(projectWenjianSet);
				moa.setMdSet(mdSet);
				moa.setMpgSet(mpgSet);
				moa.setMojuNo(mojuNo);
				moa.setStatus("完善磨具号");
				boolean 	bool =	totalDao.save(moa);
				List<Users> userList = totalDao.query(" from Users where dept = '工模'");
				for (Users users : userList) {
					AlertMessagesServerImpl.addAlertMessages("1", "完善模具号信息", new Integer[]{users.getId()}, "MouldApplyOrderAction_findMoaAndmdList.action?id="+moa.getId()+"&pageStatus=wanshan", true);
				}
				return  bool+"";
			}else{
				return "请至少添加一个具体的开模件号!";
			}
		}
		return null;
	}

	

	@Override
	public Object[] findMdList(MouldDetail md, Integer pageNo,
			Integer pageSize, String status) {
		if(md == null){
			md = new MouldDetail();
		}
		String other = "";
		if("dcg".equals(status)){//待采购  查出所有已同意的开模明细
			other = " mao.id in ( SELECT id from MouldApplyOrder where epstatus = '同意' and maketype = '外发')";
		}
		String hql =totalDao.criteriaQueries(md, other,"mao");
		if(md.getMao()!=null){
			MouldApplyOrder mao = md.getMao();
			hql+= " and mao.id in ( selcet id from MouldApplyOrder where  1=1 ";
			if(mao.getYwMarkId()!=null && mao.getYwMarkId().length()>0){
				hql+= " and ywMarkId like '%"+mao.getYwMarkId()+"%'";
			}
			if(mao.getKehu()!=null && mao.getKehu().length()>0){
				hql+= " and kehu like '%"+mao.getKehu()+"%'";
			}
			hql+= " )";
		}
		List<MouldDetail> mdList =totalDao.query(hql, null);
		for (MouldDetail mouldDetail : mdList) {
			MouldApplyOrder mao =	mouldDetail.getMao();
			mouldDetail.setMao(mao);
		}
		int count = totalDao.getCount(hql, null);
		return new Object[]{mdList,count};
	}

	@Override
	public Object[] findMoaAndmdList(Integer id,Integer pgId) {
		if(id!=null && id>0){
			MouldApplyOrder mao =	(MouldApplyOrder) totalDao.get(MouldApplyOrder.class, id);
			List<MouldDetail> mdList = totalDao.query(" from MouldDetail where mao.id = ?", id);
			String mpghql = " from MouldPingGu where mao.id = ?";
			if(pgId!=null ){
				mpghql+=" and id = "+pgId;
			}
			List<MouldPingGu> mpgList = totalDao.query(mpghql, id);
			String mjys = mao.getMujuyanshou();
			List<String> mjysList = new ArrayList<String>();
			if(mjys!=null && mjys.length()>0){
				String[] aa =	mjys.split("\\|");
				for (int i = 0; i < aa.length; i++) {
					mjysList.add(aa[i]);
				}
			}
			return new Object[]{mao,mdList,mpgList,mjysList};
			
		}
		return null;
	}

	@Override
	public MouldApplyOrder findMoaById(Integer id) {
		return (MouldApplyOrder) totalDao.get(MouldApplyOrder.class, id);
	}

	@Override
	public Object[] findMoaList(MouldApplyOrder moa, Integer pageNo,
			Integer pageSize, String status) {
		if(moa==null){
			moa = new MouldApplyOrder(); 
		}
		String hql =	totalDao.criteriaQueries(moa, null);
		if("caigou".equals(status)){
			hql+= " and epstatus = '同意" +
					"' and priceId is null ";
		}else if("xiadan".equals(status)){
			hql+= " and epstatus = '同意" +
			"' and priceId is not  null ";
		}
		List<MouldApplyOrder> moaList =	totalDao.findAllByPage(hql+"  order by id desc", pageNo, pageSize,null );
		for (MouldApplyOrder mouldApplyOrder : moaList) {
			List<MouldDetail> mdList = totalDao.query(" from MouldDetail where mao.id =?",mouldApplyOrder.getId() );
			mouldApplyOrder.setMdList(mdList);
			MouldPingGu mpg =	(MouldPingGu) totalDao.getObjectByCondition(" from MouldPingGu where mao.id=? and type=?", mouldApplyOrder.getId(),mouldApplyOrder.getStatus());
			if(mpg!=null){
				mouldApplyOrder.setUserId(mpg.getUsersId());
			}
		}
		int count =	totalDao.getCount(hql);
		return new Object[]{moaList,count};
	}

	@Override
	public String delMoa(MouldApplyOrder moa) {
		if(moa!=null){
			if("同意".equals(moa.getEpstatus()) ||
					"审批中".equals(moa.getEpstatus())){
				return "已经同意或正在审批中,不能修改";
			}
			MouldApplyOrder oldmao =	(MouldApplyOrder) totalDao.get(MouldApplyOrder.class, moa.getId());
			return	totalDao.delete(oldmao)+"";
		}
		return null;
	}

	@Override
	public String updateMoa(MouldApplyOrder moa,File[] attachment, String[] attachmentFileName,String [] otherNames,String pageStatus) {
		Users user =	Util.getLoginUser();
		if(user == null){
			return "请先登录!";
		}
		if(moa!=null){
			
			if("同意".equals(moa.getEpstatus()) ||
					"审批中".equals(moa.getEpstatus())){
				return "已经同意或正在审批中,不能修改";
			}
			MouldApplyOrder old =	(MouldApplyOrder) totalDao.get(MouldApplyOrder.class, moa.getId());
			if("pinggu".equals(pageStatus)){
				List<MouldPingGu> mpgList = moa.getMpgList();
				String more = "";
				String status = "";
				String qrstatus = "";
				if(mpgList!=null && mpgList.size()>0){
					status = mpgList.get(0).getType();
					more = mpgList.get(0).getMore();
					qrstatus = mpgList.get(0).getQrstatus();
				}
				
				MouldPingGu mpg =	(MouldPingGu) totalDao.getObjectByCondition(" from MouldPingGu where mao.id = ? and type = ?", old.getId(),status);
				if(mpg==null) {
					return "系统异常,没有找到可评估的模具申请.";
				}
				mpg.setMore(more);  
				mpg.setPinggutime(Util.getDateTime());
				mpg.setQrstatus(qrstatus);
				if("不同意".equals(qrstatus)){
					old.setStatus(status+"评估未通过!");
				}else{
					if(status.equals("市场需求评估")) {
						old.setStatus("产能需求评估");
					}
					if(status.equals("产能需求评估")) {
						old.setStatus("工艺实现评估");
					}
//					old.setStatus("评估中");
				}
				totalDao.update(mpg);
				int count =	totalDao.getCount(" from MouldPingGu where mao.id = ? and qrstatus = ?", old.getId(),"同意");
				if(count == 3){
					old.setStatus("评估完成");	
				}
			}else if("wanshan".equals(pageStatus)){
				old.setStatus("市场需求评估");
			}else{
			BeanUtils.copyProperties(moa, old, new String[]{"id","mpgSet","mdSet","mdList","mpgList",
					"userId","addtime","adddate","epId","epstatus"});
			Set<MouldDetail> mdSet = old.getMdSet();
			if(mdSet == null){
				mdSet =new HashSet<MouldDetail>();
			}
			List<MouldDetail> mdList = moa.getMdList();
			if(mdList!=null && mdList.size()>0){
				for(int i=0;i<mdList.size();i++){
					MouldDetail md = mdList.get(i);
					md.setMao(old);
					mdSet.add(md);
				}
			}
			List<MouldPingGu> mpgList = moa.getMpgList();
			if(mpgList!=null && mpgList.size()>0){
				for (MouldPingGu mouldPingGu : mpgList) {
					MouldPingGu  mpg = (MouldPingGu) totalDao.get(MouldPingGu.class, mouldPingGu.getId());
					if(mouldPingGu.getUsersId()!=null && !mpg.getUsersId().equals(mouldPingGu.getUsersId())){
						Users pgusers =	(Users) totalDao.get(Users.class, mouldPingGu.getUsersId());
						mpg.setUserName(pgusers.getName());
						mpg.setDept(pgusers.getDept());
						totalDao.update(mpg);
					}
				}
			}
			String  mujuyanshou =	MKUtil.saveFile(attachment, attachmentFileName, "MouldApplyOrder");
			old.setMujuyanshou(mujuyanshou);
			old.setMdSet(mdSet);
			}
			if("评估完成".equals(old.getStatus())){
				String processName = "模具开模申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							MouldApplyOrder.class, moa.getId(), "epstatus", "id",
							"",moa.getAddUserName() + "模具开模申请，请您审批", true);
									
									 
					if (epId != null && epId > 0) {
						old.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							old.setEpstatus("同意");
							old.setShUserName(user.getName());
							old.setAgreeUserName(user.getName());
							old.setMaketype(circuitRun.getZzopinion());
						} else {
							old.setEpstatus("未审批");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("市场需求评估".equals(pageStatus)){
				Set<MouldPingGu> mpgSet =	old.getMpgSet();
				for (MouldPingGu mpg : mpgSet) {
					AlertMessagesServerImpl.addAlertMessages("1", "开模评估:"+mpg.getType(), new Integer[]{mpg.getUsersId()}, "MouldApplyOrderAction_findMoaAndmdList.action?id="+moa.getId()+"&pageStatus=pinggu&id2="+mpg.getId(), true);
				}
			}
			old.setNum(moa.getNum());
			return	totalDao.update(old)+"";
			
		}
		return null;
	}

	@Override
	public String delmd(MouldDetail md) {
		if(md!=null){
			return totalDao.delete(md)+"";
		}
		return null;
	}

	@Override
	public String findMaxNo() {
		String nowTime =Util.getDateTime("yyyyMM");
		String maxNo = "";
		String hql_maxNo = "select max(planNumber) from MouldApplyOrder where  planNumber like 'MJ"+nowTime+"%'";
		String planNumber =	(String) totalDao.getObjectByCondition(hql_maxNo);
		if(planNumber!=null && planNumber.length()>0){
			String  str = 	planNumber.substring(8);
			Integer num=Integer.parseInt(str)+1+10000;
			String str_num=(num+"").substring(1);	
			maxNo="MJ"+nowTime+str_num;
		}else{
			maxNo="MJ"+nowTime+"0001";
		}
		return maxNo;
	}

	@Override
	public List<MouldApplyOrder> findmaoListNoPrice() {
		String hql = " from MouldApplyOrder where epstatus = '同意' and maketype = '外发'  and planNumber not in (select mjsqplanNum from Price where productCategory = '磨具封装')";
		return 	totalDao.query(hql);
	}

	@Override
	public MouldApplyOrder findMaoOne(String planNumber) {
		if(planNumber!=null && planNumber.length()>0){
			return 	(MouldApplyOrder) totalDao.getObjectByCondition(" from MouldApplyOrder where planNumber = ? ", planNumber);
		}
		return null;
	}

	@Override
	public List<Price> findPriceByNo(String planNumber) {
		if(planNumber!=null && planNumber.length()>0){
			String nowtime = Util.getDateTime();
			List<Price> priceList =	totalDao.query(" from Price where mjsqplanNum = ? and pricePeriodStart <='"+nowtime+"' and (pricePeriodEnd >= '"+nowtime+"' or pricePeriodEnd is  null or pricePeriodEnd  =''  )", planNumber);
			for (Price price : priceList) {
				if(price.getGysId()!=null){
					ZhUser zhuser = (ZhUser) totalDao.get(ZhUser.class, price.getGysId());
					price.setGys(zhuser.getCmp());
				}
			}
			return priceList;
		}
		return null;
	}

	@Override
	public String caigou(MouldApplyOrder moa, Integer id) {
		Users user = Util.getLoginUser();
		if(user == null){
			return "请先登录!";
		}
		if(moa!=null && id!=null){
			MouldApplyOrder	oldmoa = (MouldApplyOrder) totalDao.get(MouldApplyOrder.class, moa.getId());
			Price price = (Price) totalDao.get(Price.class, id);
			ZhUser zhuser = (ZhUser) totalDao.get(ZhUser.class, price.getGysId());
			
			oldmoa.setPriceId(price.getId());
			oldmoa.setGysId(zhuser.getId());
			oldmoa.setHsprice(price.getHsPrice().floatValue());
			oldmoa.setBhsprice(price.getBhsPrice().floatValue());
			totalDao.update(oldmoa);
		}
		return null;
	}

	
	private String osNumber() {
		String maxNumber = "MJ" + Util.getDateTime("yyyyMMdd");
		String hql_maxnumber = "select max(planNumber) from WaigouOrder where planNumber like '%"
				+ maxNumber + "%'";
		Object obj = totalDao.getObjectByCondition(hql_maxnumber);
		if (obj != null) {
			String maxNumber2 = obj.toString();
			int num2 = Integer.parseInt(maxNumber2.substring(maxNumber2
					.length() - 4, maxNumber2.length()));
			num2++;
			if (num2 < 10) {
				maxNumber += "000" + num2;
			} else if (num2 < 100) {
				maxNumber += "00" + num2;
			} else if (num2 < 1000) {
				maxNumber += "0" + num2;
			} else {
				maxNumber += num2 + "";
			}
		} else {
			maxNumber += "0001";
		}
		return maxNumber;
	}

	@Override
	public Integer getWtcNumber(String markIdS) {
		if(markIdS!=null && markIdS.length()>0){
			String ragex = "[\\r\\n\\t\040:：;；&$!~@^#,]+";
			markIdS = "'"+markIdS.replaceAll(ragex, "','")+"'";
			String hql = "select count(filnalCount) from Procard where markId in ("+markIdS+") and status = '初始' and jihuoStatua = '未激活'";
			Float wtcNumber =	(Float) totalDao.getObjectByCondition(hql);
			if(wtcNumber == null){
				return 0;
			}else{
				return wtcNumber.intValue();
			}
		}
		return 0;
	}

	
}
