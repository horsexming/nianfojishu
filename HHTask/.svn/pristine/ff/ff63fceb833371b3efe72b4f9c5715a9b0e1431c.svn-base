package com.task.ServerImpl.caiwu;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Server.caiwu.AccountCheckService;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.task.Dao.TotalDao;
import com.task.Server.caiwu.CwVouchersServer;
import com.task.Server.caiwu.receivePayment.ReceiptServer;
import com.task.entity.Asset;
import com.task.entity.Users;
import com.task.entity.caiwu.CwUseDetail;
import com.task.entity.caiwu.CwVouchers;
import com.task.entity.caiwu.CwVouchersDetail;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.sop.ProcardTemplateBanBenApply;
import com.task.util.Upload;
import com.task.util.Util;

/***
 * 银行账户实现类
 * 
 * @author 刘培
 * 
 */
public class CwVouchersServerImpl implements CwVouchersServer {

	private TotalDao totalDao;
	private ReceiptServer receiptServer;
	private AccountCheckService accountCheckService;

	public ReceiptServer getReceiptServer() {
		return receiptServer;
	}

	public void setReceiptServer(ReceiptServer receiptServer) {
		this.receiptServer = receiptServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public AccountCheckService getAccountCheckService() {
		return accountCheckService;
	}

	public void setAccountCheckService(AccountCheckService accountCheckService) {
		this.accountCheckService = accountCheckService;
	}

	/***
	 * 查询凭证
	 */
	@Override
	public Map<Integer, Object> findCwVouchersByCondition(
			CwVouchers cwVouchers, int pageNo, int pageSize, String startTime,
			String endTime, String tages) {
		if (cwVouchers == null) {
			cwVouchers = new CwVouchers();
		}
		String hql = totalDao.criteriaQueries(cwVouchers, null,"zzStatus");
		if("pz".equals(tages)){
			if("已做账".equals(cwVouchers.getZzStatus()))
				hql +=" and zzStatus = '已做账'";
			else {
				hql +=" and (zzStatus is null or zzStatus = '' or zzStatus = '未做账')";
				cwVouchers.setZzStatus("未做账");
			}
		}
		if (endTime == null || endTime.equals("")) {
			endTime = Util.getDateTime();
		}
		if (startTime != null && !startTime.equals("")) {
			hql = hql + " and createtime between '" + startTime + "' and '"
					+ endTime + "'";
		}
		hql += "order by createtime desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	/**
	 * 通过财务凭证id获取其下分录
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<CwVouchersDetail> findDetailByVouchersById(int id) {
		List<CwVouchersDetail> list = totalDao.query(
				"from CwVouchersDetail where cwVouchers.id=?", id);
		return list;
	}

	/**
	 * 通过分录查询对应辅助明细
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<CwUseDetail> findCwUseDetailByCvdId(int id) {
		List<CwUseDetail> list = totalDao.query(
				"from CwUseDetail where cwVouchersDetail.id=?", id); 
		return list;
	}

	/****
	 * 根据科目id查询对应的借贷记录
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<CwVouchersDetail> findDetailBysubId(int id) {
		List<CwVouchersDetail> list = totalDao.query(
				"from CwVouchersDetail where subId=?", id);
		return list;
	}

	@Override
	public CwVouchers getCwVouchersById(int id) {
		// TODO Auto-generated method stub
		return (CwVouchers) totalDao.getObjectById(CwVouchers.class, id);
	}

	/********
	 * 固定资产月度折旧---计提
	 * 
	 * @return
	 */
	@Override
	public boolean accetMonthZhejiu() {
		String month = Util.getDateTime("yyyy-MM");
		/************************ 根据净值与月折旧额 生成凭证 *****************************/
		String hql = "select tausingobject,sum(tayuezhejiudepreciation) from Asset where taassetsNetworth>0 group by tausingobject ";
		List assetList = totalDao.query(hql);
		CwVouchers cwVouchers = new CwVouchers();
		// 生成编号
		String num = "P" + Util.getDateTime("yyyyMM");
		String hql_finMaxnum = "select max(number) from CwVouchers where number like '%"
				+ num + "%'";
		String maxfkNumber = (String) totalDao
				.getObjectByCondition(hql_finMaxnum);
		if (maxfkNumber != null && maxfkNumber.length() > 0) {
			String subnum = "";
			Integer maxnum = Integer.parseInt(maxfkNumber.substring(7,
					maxfkNumber.length())) + 1;
			if (maxnum < 10) {
				subnum += "0000" + maxnum;// 00009
			} else if (maxnum < 100) {
				subnum += "000" + maxnum;// 00099
			} else if (maxnum < 1000) {
				subnum += "00" + maxnum;// 00999
			} else if (maxnum < 10000) {
				subnum += "0" + maxnum;// 09999
			} else {
				subnum += "" + maxnum;
			}
			num += subnum;
		} else {
			num += "00001";
		}
		cwVouchers.setNumber(num);
		cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
		cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
		cwVouchers.setCreatetime(Util.getDateTime());
		cwVouchers.setUserName(Util.getLoginUser().getName());
		cwVouchers.setCreateCode(Util.getLoginUser().getCode());
		cwVouchers.setCreateName(Util.getLoginUser().getName());
		Double jieMoney = 0d;
		Double daiMoney = 0d;

		Set<CwVouchersDetail> cwVouchersDetailSet = new HashSet<CwVouchersDetail>();
		for (int i = 0; i < assetList.size(); i++) {
			Object[] assetobj = (Object[]) assetList.get(i);
			String dept = (String) assetobj[0];
			Double zhejiu_d = (Double) assetobj[1];
			Double zhejiufei = zhejiu_d;

			SubBudgetRate subBudgetRate_jie = null;
			if ("研发".equals(dept)) {
				subBudgetRate_jie = (SubBudgetRate) totalDao
						.getObjectByCondition("from SubBudgetRate where name='折旧费' and fatherName='费用化支出' "
								+ " and rootId=(select id from SubBudgetRate where name='研发支出' and belongLayer=1)");
			} else if ("总成班".equals(dept) || "加工".equals(dept)
					|| "设备".equals(dept) || "品质".equals(dept)
					|| "物流".equals(dept)) {
				subBudgetRate_jie = (SubBudgetRate) totalDao
						.getObjectByCondition("from SubBudgetRate where name='折旧费' and rootId=(select id from SubBudgetRate where name='制造费用' and belongLayer=1)");
			} else {
				subBudgetRate_jie = (SubBudgetRate) totalDao
						.getObjectByCondition("from SubBudgetRate where name='折旧费' and rootId=(select id from SubBudgetRate where name='管理费用' and belongLayer=1)");
			}

			/******************* 冲销 凭证分录 ***********************/

			// ***************** 凭证分录 --借方*********************
			CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
			Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

			// ***************** 分录--辅助明细********************
			CwUseDetail cwUseDetail = new CwUseDetail();
			cwUseDetail.setPayee(dept);
			cwUseDetail.setUseFor("计提" + month + "固定资产折旧");
			cwUseDetail.setUsemoney(zhejiufei);
			cwUseDetail.setAboutNum("");
			cwUseDetail.setPayNum("");
			cwUseDetail.setPayType("计提");
			cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
			cwUseDetailSet.add(cwUseDetail);

			cwVouchersDetail_jie.setRemark("计提" + month + "固定资产折旧");
			cwVouchersDetail_jie.setvClass("转");
			// 查找一级科目
			SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao
					.getObjectById(SubBudgetRate.class, subBudgetRate_jie
							.getRootId());
			cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
			cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
			// 明细科目
			cwVouchersDetail_jie.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate_jie.getId())
					+ "-" + dept);
			cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

			cwVouchersDetail_jie.setJieMoney(zhejiufei);
			cwVouchersDetail_jie.setDaiMoney(0d);
			cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
			cwVouchersDetail_jie.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_jie.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
			cwVouchersDetail_jie.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_jie);

			cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
			totalDao.save(cwVouchers);
			// 更新借方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(),
					zhejiufei.doubleValue(), 0D);
			jieMoney += zhejiufei;
			daiMoney += 0F;

			// --*****************凭证分录-贷方********************
			CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
			cwVouchersDetail_dai.setvClass("转");
			cwVouchersDetail_dai.setRemark("计提" + month + "固定资产折旧");

			// 明细科目需根据科目矩阵图查询
			SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
					.getObjectByCondition("from SubBudgetRate where name='本月提取折旧费' and rootId=(select id from SubBudgetRate where name='累计折旧' and belongLayer=1)");

			// 查找一级科目
			SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao.getObjectById(
					SubBudgetRate.class, subBudgetRate.getRootId());
			cwVouchersDetail_dai.setSub(oneLevelsub.getName());
			cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

			// 明细科目
			cwVouchersDetail_dai.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate.getId())
					+ "-" + dept);
			cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
			cwVouchersDetail_dai.setJieMoney(0d);
			cwVouchersDetail_dai.setDaiMoney(zhejiufei);
			cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
			cwVouchersDetail_dai.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_dai.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_dai.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_dai);
			// 更新贷方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D,
					zhejiufei);
			jieMoney += 0F;
			daiMoney += zhejiufei;
		}
		cwVouchers.setJieMoney(jieMoney);
		cwVouchers.setDaiMoney(daiMoney);
		totalDao.save(cwVouchers);

		/***************** 设备折旧--设备净值-月折旧额 ********************************/
		String hql_asset = "from Asset where taassetsNetworth>0";
		List assetLists = totalDao.query(hql_asset);
		for (int i = 0; i < assetLists.size(); i++) {
			Asset asset = (Asset) assetLists.get(i);
			Float assetsNetworth = asset.getTaassetsNetworth()
					- asset.getTayuezhejiudepreciation();
			if (assetsNetworth < 0) {
				assetsNetworth = 0F;
			}
			asset.setTaassetsNetworth(assetsNetworth);
			totalDao.update(asset);
		}
		return false;
	}

	@Override
	public String queren(int id) {
		// TODO Auto-generated method stub
		CwVouchers cwVouchers = getCwVouchersById(id);
		if(cwVouchers!=null){
			cwVouchers.setZzStatus("已做账");
			if(totalDao.update(cwVouchers)){
				return "true";
			}else{
				return "保存失败！";
			}
		}else {
			return "数据异常，请刷新后重试！";
		}
	}
	
	@Override
	public String shangchuan(CwVouchers cwVoucherss, File proofFile,
			String fileName) {
		// TODO Auto-generated method stub
		CwVouchers cwVouchers = getCwVouchersById(cwVoucherss.getId());
		if(cwVouchers!=null){
			cwVouchers.setZzStatus("已做账");
			cwVouchers.setZzPizNumber(cwVoucherss.getZzPizNumber());//凭证编号
			cwVouchers.setZzTime(Util.getDateTime());
			// 上传凭证附件
			String realFilePath = "/upload/file/payPz";
			String path = ServletActionContext.getServletContext()
					.getRealPath(realFilePath);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就新建
			}
			Upload upload = new Upload();
			String filenewname = upload.UploadFile(proofFile, fileName,
					null, realFilePath, null);
			cwVouchers.setZzFile(filenewname);//附件名称
			if(totalDao.update(cwVouchers)){
				//更新对账单
				accountCheckService.checkCwVouchersState(cwVouchers.getFk_receiptLogId());
				return "上传成功";
			}else{
				return "上传失败！";
			}
		}else {
			return "数据异常，请刷新后重试！";
		}
	}


	@Override
	public CwVouchers findCwVouchersByReceiptLogId(Integer id){
		CwVouchers cwVouchers= (CwVouchers) totalDao.getObjectByCondition("from CwVouchers where fk_receiptLogId=?",id);
		return cwVouchers;
	}
	
	
	/**
	 * 手动添加凭证明细新
	 */
	public String insertCwVouchersDetail(List<CwVouchersDetail> cwVouchersDetailList,String createTime){
		Double jieMoney = 0.00;
		Double daiMoney = 0.00;
		SubBudgetRate subBudgetRate = new SubBudgetRate();
		CwVouchers cwVouchers = new CwVouchers();
		String yc = "";
	    if(cwVouchersDetailList !=null&&cwVouchersDetailList.size()>1&&createTime !=null){
	    	try{
		    	for(CwVouchersDetail cwVouchersDetail1 : cwVouchersDetailList){
		    		if(cwVouchersDetail1 !=null){
		    			// 明细科目需根据科目矩阵图查询
		    			if(cwVouchersDetail1.getDetailSub()==null||cwVouchersDetail1.getDetailSub().equals("")){
		    				yc = "手动添加出错,科目未选择";
		                	throw new Exception("添加出错");
		    			}		    			
		        		subBudgetRate = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,Integer.parseInt(cwVouchersDetail1.getDetailSub()));		        		
		        		SubBudgetRate subBudgetRate2 = (SubBudgetRate) totalDao.getObjectByCondition("from SubBudgetRate where rootId=?",subBudgetRate.getRootId());	        		
		        		CwVouchersDetail cwVouchersDetail = new CwVouchersDetail();         		
		        		cwVouchersDetail.setRemark(cwVouchersDetail1.getRemark());				        		
		        		cwVouchersDetail.setSub(subBudgetRate2.getName());// 一级科目
		        		cwVouchersDetail.setSubId(subBudgetRate2.getId());		        		
		        		cwVouchersDetail.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate.getId()));// 明细科目
		        		cwVouchersDetail.setDetailSubId(subBudgetRate.getId());					        		
						if(cwVouchersDetail1.getJieMoney()==null||cwVouchersDetail1.getJieMoney()==0){
							cwVouchersDetail.setJieMoney(0.00);
						}else{
							cwVouchersDetail.setJieMoney((double)cwVouchersDetail1.getJieMoney());
						}								
						if(cwVouchersDetail1.getDaiMoney()==null||cwVouchersDetail1.getDaiMoney()==0){
							cwVouchersDetail.setDaiMoney(0.00);
						}else{
							cwVouchersDetail.setDaiMoney((double)cwVouchersDetail1.getDaiMoney());
						}				
						cwVouchersDetail.setCreateTime(Util.getDateTime());
						cwVouchersDetail.setCreateName(Util.getLoginUser().getName());
						cwVouchersDetail.setCreateCode(Util.getLoginUser().getCode());
						cwVouchersDetail.setCwVouchers(cwVouchers);	
						//更新科目余额
						//receiptServer.updatesubBudgetRate(subBudgetRate.getId(),
							//	cwVouchersDetail.getJieMoney().doubleValue(), cwVouchersDetail.getJieMoney().doubleValue());
						if(cwVouchersDetail1.getJieMoney()!=null&&cwVouchersDetail1.getJieMoney()>0){
							if(cwVouchersDetail1.getDaiMoney()==null||cwVouchersDetail1.getDaiMoney().equals("")||cwVouchersDetail.getDaiMoney()==0){}
							receiptServer.updatesubBudgetRate(subBudgetRate.getId(),
									cwVouchersDetail.getJieMoney().doubleValue(), 0D);
						}else if(cwVouchersDetail1.getDaiMoney()!=null&&cwVouchersDetail1.getDaiMoney()>0){
							if(cwVouchersDetail1.getJieMoney()==null||cwVouchersDetail1.getJieMoney().equals("")||cwVouchersDetail.getJieMoney()==0){}
							receiptServer.updatesubBudgetRate(subBudgetRate.getId(),
									0D,cwVouchersDetail.getDaiMoney().doubleValue());
						}else{
							yc = "手动添加出错,请检查格式或输入金额是否有误";
							throw new Exception("添加出错");
						}		
		                totalDao.save(cwVouchersDetail);		                
		                jieMoney = doubleAdd(jieMoney,cwVouchersDetail.getJieMoney());
						daiMoney = doubleAdd(daiMoney,cwVouchersDetail.getDaiMoney());
		                if(cwVouchersDetail.getJieMoney()>0&&cwVouchersDetail.getDaiMoney()>0){
		                	yc = "手动添加出错,请检查格式或输入金额是否有误";
		                	throw new Exception("添加出错");
						}
		    			if(cwVouchersDetail1.getCwUseDetailList()!= null&&cwVouchersDetail1.getCwUseDetailList().size()>0){
		    				for(CwUseDetail cwUseDetail1 :cwVouchersDetail1.getCwUseDetailList()){
		    					if(cwUseDetail1.getUseFor()!=null&&!(cwUseDetail1.getUseFor().equals(""))){
		    						cwUseDetail1.setCwVouchersDetail(cwVouchersDetail);
	                                totalDao.save(cwUseDetail1);
		    					}else{
		    						yc = "手动添加出错,辅助明细摘要必须填写";
		    						throw new Exception("添加出错");
		    					}	    					
		    				}
		    			}	    			
		    		}
		    	}
		    	//验证借方金额之和等于贷方金额之和
	    		if(jieMoney.equals(daiMoney)){
	    		}else{
	    			yc = "手动添加出错,请检查格式或输入金额是否有误";
	    			throw new Exception("添加出错");
	    		} 		
		    	String num = shengcNum(); //生成凭证编号	
				cwVouchers.setNumber(num);//财务凭证
				cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
				cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
				cwVouchers.setCreatetime(createTime);
				cwVouchers.setUserName(Util.getLoginUser().getName());
				cwVouchers.setCreateCode(Util.getLoginUser().getCode());
				cwVouchers.setCreateName(Util.getLoginUser().getName());				
				cwVouchers.setJieMoney(jieMoney);
				cwVouchers.setDaiMoney(daiMoney);
				boolean cvjg =  totalDao.save(cwVouchers);
				if(cvjg){	
				}else{
					throw new Exception("添加失败");
				}	
		    } catch (Exception e) {
				e.printStackTrace();
			    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
	    	if(yc.length()>0){
	    		return yc.toString();	    		
	    	}else{
	    		 return "添加成功";
	    	}
	    }	
		return null;
	}
	
	/**
	 * 根据id查询需要修改的凭证明细信息
	 * @param id
	 * @return
	 */
	public List<CwVouchersDetail> findCwVouchersDetailById(Integer id){
		List<CwVouchersDetail> cwVouDetaillist = new ArrayList<CwVouchersDetail>();
		List<CwUseDetail>  cwUseDetailList = new ArrayList<CwUseDetail>();
		if(id !=null&&!(id.equals(""))){
			cwVouDetaillist= totalDao.query("from CwVouchersDetail where cwVouchers.id=?", id);
			if(cwVouDetaillist !=null){
				for(CwVouchersDetail cwVouchersDetail:cwVouDetaillist){
					cwUseDetailList = totalDao.query("from CwUseDetail where cwVouchersDetail.id=?", cwVouchersDetail.getId()); 
					if(cwUseDetailList !=null){
						cwVouchersDetail.setCwUseDetailList(cwUseDetailList);
					}
				}
			}
		}
		return cwVouDetaillist;	
	}
	/**
	 * 手动修改凭证明细
	 */
	public String updateCwVouchersDetail(List<CwVouchersDetail>  cwVouchersDetailList,Integer cwVouChersId){
		SubBudgetRate subBudgetRate = new SubBudgetRate();
		SubBudgetRate subBudgetRateReturn = new SubBudgetRate();
		CwVouchers cwVouchers = new CwVouchers();
		
		//List<CwUseDetail> cwUseDetailListUsed = new ArrayList<CwUseDetail>();
		List<CwVouchersDetail> cwVouchersDetailListUsed = new ArrayList<CwVouchersDetail>();
		Double jieMoney = 0.00;
		Double daiMoney = 0.00;
		String yc= "";
		try{
			if(cwVouchersDetailList !=null&&cwVouChersId !=null){
				cwVouchersDetailListUsed = totalDao.query("from CwVouchersDetail where cwVouchers.id=?",cwVouChersId);
				cwVouchers = (CwVouchers)totalDao.getObjectByCondition("from CwVouchers where id=?",cwVouChersId);
				for(CwVouchersDetail cwVouchersDetail : cwVouchersDetailList){//循环新信息判断是新增还是修改还是删除
					if(cwVouchersDetail !=null){	
						//明细科目需根据科目矩阵图查询
						subBudgetRate = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,Integer.parseInt(cwVouchersDetail.getDetailSub()));		
		        		SubBudgetRate subBudgetRate2 = (SubBudgetRate) totalDao.getObjectByCondition("from SubBudgetRate where rootId=?",subBudgetRate.getRootId());
		        		
						if(cwVouchersDetail != null&&cwVouchersDetail.getId() !=null&&!(cwVouchersDetail.getId().equals(-1))){//修改凭证明细操作
							//查询旧凭证明细的科目以及借贷金额和新凭证进行比较(判断接下来的操作)
							CwVouchersDetail cwVouchersDetail1 = (CwVouchersDetail)totalDao.getObjectByCondition("from CwVouchersDetail where id=?",cwVouchersDetail.getId());
							List<CwUseDetail> cwUseDetailList = totalDao.query("from CwUseDetail where cwVouchersDetail.id=?",cwVouchersDetail1.getId());
							if(cwVouchersDetail1 !=null){
								SubBudgetRate subBudgetRate3 = new SubBudgetRate();
								//(方法2)不管新旧是否一致 都将旧的科目金额回返 再给新的科目余额变更
								if(cwVouchersDetail.getJieMoney()!=null&&!(cwVouchersDetail.getJieMoney().equals(""))&&cwVouchersDetail.getJieMoney()>0){								
	    							if(cwVouchersDetail.getDaiMoney()==null||cwVouchersDetail.getDaiMoney().equals("")||cwVouchersDetail.getDaiMoney()==0){
	    								subBudgetRate3 = (SubBudgetRate)totalDao.getObjectById(SubBudgetRate.class,cwVouchersDetail1.getDetailSubId());
	    								//科目金额回返
	    								//判断旧凭证明细的借贷金额属于借还是属于贷(根据不同回返不同的科目余额)
	    								if(cwVouchersDetail1.getJieMoney()>0&&cwVouchersDetail1.getDaiMoney()==0){
	    									receiptServer.updatesubBudgetRate(subBudgetRate3.getId(),
		        									-(cwVouchersDetail1.getJieMoney().doubleValue()), 0D);
	    								}else if(cwVouchersDetail1.getDaiMoney()>0&&cwVouchersDetail1.getJieMoney()==0){
	    									receiptServer.updatesubBudgetRate(subBudgetRate3.getId(),
	    											0D,-(cwVouchersDetail1.getDaiMoney().doubleValue()));
	    								}
	        							   							
	        							//科目余额更改新
	    								receiptServer.updatesubBudgetRate(subBudgetRate.getId(),
	        									cwVouchersDetail.getJieMoney().doubleValue(), 0D);     								
	    								cwVouchersDetail1.setJieMoney(cwVouchersDetail.getJieMoney());
	        							cwVouchersDetail1.setDaiMoney(0.00);
	    							}    							
	    						}else if(cwVouchersDetail.getDaiMoney()!=null&&!(cwVouchersDetail.getDaiMoney().equals(""))&&cwVouchersDetail.getDaiMoney()>0){
	    							if(cwVouchersDetail.getJieMoney()==null||cwVouchersDetail.getJieMoney().equals("")||cwVouchersDetail.getJieMoney()==0){}							
		    							subBudgetRate3 = (SubBudgetRate)totalDao.getObjectById(SubBudgetRate.class,cwVouchersDetail1.getDetailSubId());
		    							//科目金额回返旧
		    							//判断旧凭证明细的借贷金额属于借还是属于贷(根据不同回返不同的科目余额)
	    								if(cwVouchersDetail1.getDaiMoney()>0&&cwVouchersDetail1.getJieMoney()==0){
	    									receiptServer.updatesubBudgetRate(subBudgetRate3.getId(),
	    											0D,-(cwVouchersDetail1.getDaiMoney().doubleValue()));
	    								}else if(cwVouchersDetail1.getJieMoney()>0&&cwVouchersDetail1.getDaiMoney()==0){
	    									receiptServer.updatesubBudgetRate(subBudgetRate3.getId(),
		        									-(cwVouchersDetail1.getJieMoney().doubleValue()), 0D);
	    								}
		    							//科目余额更改新
		    							receiptServer.updatesubBudgetRate(subBudgetRate.getId(),
		    									0D,cwVouchersDetail.getDaiMoney().doubleValue());    	    							
		    							cwVouchersDetail1.setJieMoney(0.00);
	        							cwVouchersDetail1.setDaiMoney(cwVouchersDetail.getDaiMoney());
	    						}else{
	    							yc = "修改出错,请检查格式或输入金额是否有误";
	    							throw new Exception("修改出错");
	    						}
							}
							cwVouchersDetail1.setRemark(cwVouchersDetail.getRemark());
			        		cwVouchersDetail1.setSub(subBudgetRate2.getName());//明细科目
							cwVouchersDetail1.setSubId(subBudgetRate2.getId());
							cwVouchersDetail1.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate.getId()));
							cwVouchersDetail1.setDetailSubId(subBudgetRate.getId());
							totalDao.update(cwVouchersDetail1);
							//循环判断辅助明细(修改/新增/删除)
							if(cwVouchersDetail.getCwUseDetailList() !=null&&cwVouchersDetail.getCwUseDetailList().size()>0){//判断是新增还是修改
								for(CwUseDetail cwUseDetail: cwVouchersDetail.getCwUseDetailList()){
									CwUseDetail cwUseDetail1 = new CwUseDetail();	
									if(cwUseDetail !=null){
										if(cwUseDetail.getId()!=null&&!(cwUseDetail.getId().equals(""))&&!(cwUseDetail.getId().equals(-1))&&cwUseDetail.getUseFor()!=null&&!(cwUseDetail.getUseFor().equals(""))){//修改操作
		                            		cwUseDetail1 = (CwUseDetail)totalDao.getObjectByCondition("from CwUseDetail where id=?",cwUseDetail.getId());                          		
		                            		cwUseDetail1.setUseFor(cwUseDetail.getUseFor());
		                            		if(cwUseDetail.getPayee()!=null&&!(cwUseDetail.getPayee().equals(""))){
		                            			cwUseDetail1.setPayee(cwUseDetail.getPayee());
		    								}else{
		    									cwUseDetail1.setPayee(null);
		    								}
		    								if(cwUseDetail.getUsemoney()!=null&&!(cwUseDetail.getUsemoney().equals(""))){
		    									cwUseDetail1.setUsemoney(cwUseDetail.getUsemoney());
		    								}else{
		    									cwUseDetail1.setUsemoney(null);
		    								}
		                            		totalDao.update(cwUseDetail1);//修改辅助明细
		                            		
		                            	}else if(cwUseDetail.getId().equals(-1)&&cwUseDetail.getUseFor()!=null&&!(cwUseDetail.getUseFor().equals(""))){//新增操作
		                            		cwUseDetail1.setCwVouchersDetail(cwVouchersDetail1);
		                            		cwUseDetail1.setUseFor(cwUseDetail.getUseFor());  
		                            		if(cwUseDetail.getPayee()!=null&&!(cwUseDetail.getPayee().equals(""))){
		                            			cwUseDetail1.setPayee(cwUseDetail.getPayee());
		    								}else{
		    									cwUseDetail1.setPayee(null);
		    								}
		    								if(cwUseDetail.getUsemoney()!=null&&!(cwUseDetail.getUsemoney().equals(""))){
		    									cwUseDetail1.setUsemoney(cwUseDetail.getUsemoney());
		    								}else{
		    									cwUseDetail1.setUsemoney(null);
		    								}
		                            		totalDao.save(cwUseDetail1);
		                            		
		                            	}else if(cwUseDetail.getUseFor()==null||cwUseDetail.getUseFor().equals("")){//
		                            		yc =  "修改出错,辅助明细摘要必须填写";
		        							throw new Exception("修改凭证明细时添加辅助明细摘要未填写出错");
		                            	}
									}
								}
								//此处再进行判断在新增和修改过程中是否有删除
								if(cwUseDetailList !=null&&cwUseDetailList.size()>0){//旧数据大于0
									for(CwUseDetail cwUseDetail3 : cwUseDetailList){
										Integer count = 0;
										for(CwUseDetail cwUseDetail: cwVouchersDetail.getCwUseDetailList()){
											if(cwUseDetail !=null){
												if(cwUseDetail3.getId().equals(cwUseDetail.getId())){
													count = count+1;
												}
											}										
										}
										if(count==0){
											totalDao.delete(cwUseDetail3);
										}
									}
								}
							}else{//判断是删除还是未操作
								if(cwUseDetailList !=null&&cwUseDetailList.size()>0){//旧数据大于0
									for(CwUseDetail cwUseDetail3 : cwUseDetailList){
										totalDao.delete(cwUseDetail3);
									}
								}else{
									//此处不做操作
								}
							}
														
							if(cwVouchersDetail1.getJieMoney()>0&&cwVouchersDetail1.getDaiMoney()>0){
			                	yc = "修改出错,请检查格式或输入金额是否有误";
			                	throw new Exception("修改凭证明细借贷金额都大于0出错");
							}
							jieMoney = doubleAdd(jieMoney,cwVouchersDetail1.getJieMoney());   //计算总额
							daiMoney = doubleAdd(daiMoney,cwVouchersDetail1.getDaiMoney());							
						}else if(cwVouchersDetail != null&&cwVouchersDetail.getId() !=null&&cwVouchersDetail.getId().equals(-1)){//新增凭证明细操作
							CwVouchersDetail cwVouchersDetail2 = new CwVouchersDetail();
							cwVouchersDetail2.setRemark(cwVouchersDetail.getRemark());				        		
							cwVouchersDetail2.setSub(subBudgetRate2.getName());// 一级科目
							cwVouchersDetail2.setSubId(subBudgetRate2.getId());		        		
							cwVouchersDetail2.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate.getId()));// 明细科目
							cwVouchersDetail2.setDetailSubId(subBudgetRate.getId());
			        		if(cwVouchersDetail.getJieMoney()!=null&&!(cwVouchersDetail.getJieMoney().equals(""))&&cwVouchersDetail.getJieMoney()>0){								
								if(cwVouchersDetail.getDaiMoney()==null||cwVouchersDetail.getDaiMoney().equals("")||cwVouchersDetail.getDaiMoney()==0){							
	    							//科目余额更改新
									receiptServer.updatesubBudgetRate(subBudgetRate.getId(),
	    									cwVouchersDetail.getJieMoney().doubleValue(), 0D);     								
									cwVouchersDetail2.setJieMoney(cwVouchersDetail.getJieMoney());
									cwVouchersDetail2.setDaiMoney(0.00);
								}    							
							}else if(cwVouchersDetail.getDaiMoney()!=null&&!(cwVouchersDetail.getDaiMoney().equals(""))&&cwVouchersDetail.getDaiMoney()>0){
								if(cwVouchersDetail.getJieMoney()==null||cwVouchersDetail.getJieMoney().equals("")||cwVouchersDetail.getJieMoney()==0){}							
	    							//科目余额更改新
	    							receiptServer.updatesubBudgetRate(subBudgetRate.getId(),
	    									0D,cwVouchersDetail.getDaiMoney().doubleValue());    	    							
	    							cwVouchersDetail2.setJieMoney(0.00);
	    							cwVouchersDetail2.setDaiMoney(cwVouchersDetail.getDaiMoney());
							}else{
								yc = "修改出错,请检查格式或输入金额是否有误";
								throw new Exception("新增凭证明金额出错");
							}
			        		cwVouchersDetail2.setCreateTime(Util.getDateTime());
			        		cwVouchersDetail2.setCreateName(Util.getLoginUser().getName());
							cwVouchersDetail2.setCreateCode(Util.getLoginUser().getCode());
			        		cwVouchersDetail2.setCwVouchers(cwVouchers);
			        		totalDao.save(cwVouchersDetail2);	
							if(cwVouchersDetail2.getJieMoney()>0&&cwVouchersDetail2.getDaiMoney()>0){//可有可无
			                	yc = "手动添加出错,请检查格式或输入金额是否有误";
			                	throw new Exception("新增凭证明细借贷金额都大于0出错");
							}
							jieMoney = doubleAdd(jieMoney,cwVouchersDetail2.getJieMoney());   //计算总额
							daiMoney = doubleAdd(daiMoney,cwVouchersDetail2.getDaiMoney());
							//判断辅助明细有无
							if(cwVouchersDetail.getCwUseDetailList()!=null&&cwVouchersDetail.getCwUseDetailList().size()>0){//新增辅助明细
								for(CwUseDetail cwUseDetai2 :cwVouchersDetail.getCwUseDetailList()){
									if(cwUseDetai2 !=null&&cwUseDetai2.getUseFor()!=null&&!(cwUseDetai2.getUseFor().equals(""))){
										cwUseDetai2.setCwVouchersDetail(cwVouchersDetail2);
										totalDao.save(cwUseDetai2);
									}else{
										yc = "修改出错,新加辅助明细摘要必须填写";
						    			throw new Exception("新增凭证明细时添加辅助明细摘要未填写出错");
									}
								}
							}
						}
					}
				}
				//循环并判断旧的凭证在新的凭证中是否存在(不存在就删除)
				for(CwVouchersDetail cwVouchersDetail : cwVouchersDetailListUsed){
					SubBudgetRate subBudgetRate4 = new SubBudgetRate();
					Integer count = 0;
					for(CwVouchersDetail cwVouchersDetail2 : cwVouchersDetailList){
						if(cwVouchersDetail2 !=null){
							if(cwVouchersDetail.getId().equals(cwVouchersDetail2.getId())){
								count=count+1;
							}
						}						
					}
					if(count ==0){//执行删除
						//判断是否存在辅助明细(若存在先删除辅助明细)
						if(cwVouchersDetail.getCwUseDetailList()!=null&&cwVouchersDetail.getCwUseDetailList().size()>0){
							for(CwUseDetail cwUseDetail : cwVouchersDetail.getCwUseDetailList()){
								if(cwUseDetail !=null){
									totalDao.delete(cwUseDetail);
								}
							}
						}
						//将科目余额进行回返
						if(cwVouchersDetail.getJieMoney()!=null&&!(cwVouchersDetail.getJieMoney().equals(""))&&cwVouchersDetail.getJieMoney()>0){								
							if(cwVouchersDetail.getDaiMoney()==null||cwVouchersDetail.getDaiMoney().equals("")||cwVouchersDetail.getDaiMoney()==0){
								subBudgetRate4 = (SubBudgetRate)totalDao.getObjectById(SubBudgetRate.class,cwVouchersDetail.getDetailSubId());
								//科目金额回返
    							receiptServer.updatesubBudgetRate(subBudgetRate4.getId(),
    									-(cwVouchersDetail.getJieMoney().doubleValue()), 0D);   							

							}    							
						}else if(cwVouchersDetail.getDaiMoney()!=null&&!(cwVouchersDetail.getDaiMoney().equals(""))&&cwVouchersDetail.getDaiMoney()>0){
							if(cwVouchersDetail.getJieMoney()==null||cwVouchersDetail.getJieMoney().equals("")||cwVouchersDetail.getJieMoney()==0){}							
							subBudgetRate4 = (SubBudgetRate)totalDao.getObjectById(SubBudgetRate.class,cwVouchersDetail.getDetailSubId());
    							//科目金额回返旧
    							receiptServer.updatesubBudgetRate(subBudgetRate4.getId(),
    									0D,-(cwVouchersDetail.getDaiMoney().doubleValue()));   	
						}
						if(!(totalDao.delete(cwVouchersDetail))){
							yc = "修改失败";
							throw new Exception("删除凭证明细出错");
						}else{
							System.out.println("修改成功");
						}
					}									
				}
				//验证借方金额之和等于贷方金额之和
	    		if(jieMoney.equals(daiMoney)){
	    		}else{
	    			yc = "修改出错,请检查格式或输入金额是否有误";
	    			throw new Exception("总金额不一致出错");
	    		} 		
				//cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
				//cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
				//cwVouchers.setCreatetime(Util.getDateTime());
				//cwVouchers.setUserName(Util.getLoginUser().getName());
				//cwVouchers.setCreateCode(Util.getLoginUser().getCode());
				//cwVouchers.setCreateName(Util.getLoginUser().getName());				
				cwVouchers.setJieMoney(jieMoney);
				cwVouchers.setDaiMoney(daiMoney);
				if(totalDao.update(cwVouchers)){	
				}else{
					throw new Exception("修改主数据出错");
				}	
			}				
		} catch (Exception e) {
			e.printStackTrace();			
		    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		   // return "修改失败,请完整填写";
		}
		if(yc.length()>0){
    		return yc.toString();	    		
    	}else{
    		//return "修改成功";
    	}		
		return "修改成功";
	} 
	
	
	
	/**
	 * 删除凭证
	 */
	public boolean deleteCwVouchers(Integer id){
		List<CwVouchersDetail> cwVouchersDetailList = new ArrayList<CwVouchersDetail>();
		List<CwUseDetail> cwUseDetailList = new ArrayList<CwUseDetail>();
		if(id !=null){
			CwVouchers cwVouchers = (CwVouchers)totalDao.getObjectByCondition("from CwVouchers where id=?",id);
			if(cwVouchers !=null){
				cwVouchersDetailList = totalDao.query("from CwVouchersDetail where cwVouchers.id=?",cwVouchers.getId());
				if(cwVouchersDetailList.size()>0){
					for(CwVouchersDetail cwVouchersDetail : cwVouchersDetailList){
						if(cwVouchersDetail !=null){
							cwUseDetailList = totalDao.query("from CwUseDetail where cwVouchersDetail.id=?",cwVouchersDetail.getId());
							if(cwUseDetailList.size()>0){
								for(CwUseDetail cwUseDetail : cwUseDetailList){
									if(cwUseDetail !=null){
										totalDao.delete(cwUseDetail);
									}
								}
							}
							totalDao.delete(cwVouchersDetail);
						}	
					}
				}	
				boolean dcw =  totalDao.delete(cwVouchers);
				if(dcw){
					return true;
				}
			}
		}	
		return false;
	}
	
	
	
	
	/**
	 * 生成应付凭证编号
	 * 
	 * @return
	 */
	private String shengcNum() {
		// 生成编号
		String num = "P" + Util.getDateTime("yyyyMM");
		String hql_finMaxnum = "select max(number) from CwVouchers where number like '%"
				+ num + "%'";
		String maxfkNumber = (String) totalDao
				.getObjectByCondition(hql_finMaxnum);
		if (maxfkNumber != null && maxfkNumber.length() > 0) {
			String subnum = "";
			Integer maxnum = Integer.parseInt(maxfkNumber.substring(7,
					maxfkNumber.length())) + 1;
			if (maxnum < 10) {
				subnum += "0000" + maxnum;// 00009
			} else if (maxnum < 100) {
				subnum += "000" + maxnum;// 00099
			} else if (maxnum < 1000) {
				subnum += "00" + maxnum;// 00999
			} else if (maxnum < 10000) {
				subnum += "0" + maxnum;// 09999
			} else {
				subnum += "" + maxnum;
			}
			num += subnum;
		} else {
			num += "00001";
		}
		return num;
	}
	/**   
	  * 提供精確的加法運算   
	  * @param args   
	  */   
	  public static double doubleAdd(double v1, double v2) {   
	  BigDecimal b1 = new BigDecimal(Double.toString(v1));   
	  BigDecimal b2 = new BigDecimal(Double.toString(v2));   
	  return b1.add(b2).doubleValue();   
	  } 
}
