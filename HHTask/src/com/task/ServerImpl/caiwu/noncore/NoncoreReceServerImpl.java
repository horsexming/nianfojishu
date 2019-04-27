package com.task.ServerImpl.caiwu.noncore;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.caiwu.noncore.NoncoreReceServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.caiwu.core.SupplierCorePayable;
import com.task.entity.caiwu.noncore.EnergyConsumption;
import com.task.entity.caiwu.noncore.FinancialReceipts;
import com.task.entity.caiwu.noncore.NonCoreReceivables;
import com.task.entity.caiwu.noncore.NonCoreReceivablesDetail;
import com.task.util.Util;
@SuppressWarnings("unchecked")
public class NoncoreReceServerImpl implements NoncoreReceServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String deleteNoncorereceiveById(Integer id) {
		// TODO Auto-generated method stub
		NonCoreReceivables coreReceivables = getNoncoreReveById(id);
		if(coreReceivables!=null){
			coreReceivables.setNonCoreReceivablesDetail(null);
			totalDao.update(coreReceivables);
			// 删附件
			if (coreReceivables.getHetongfujian() != null) {
				File oldFile = new File(ServletActionContext
						.getServletContext().getRealPath("")
						+ "/upload/file/feiZY/" + coreReceivables.getHetongfujian());
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			if(totalDao.delete(coreReceivables)){
				return "删除成功！";
			}
		}
		return "删除失败！";
	}

	@Override
	public Object[] findNoncoreReceiveDetailList(
			NonCoreReceivablesDetail nonCoreReceiveables, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] findNoncoreReceiveList(NonCoreReceivables nonCoreReceive,
			String startDate, String endDate, Integer cpage, Integer pageSize,
			String tag) {
		// TODO Auto-generated method stub
		if (nonCoreReceive == null) {
			nonCoreReceive = new NonCoreReceivables();
		}
		String sql = "";
		Users u = Util.getLoginUser();
		if("dept".equals(tag)){
			sql += " addUserDept = '"+u.getDept()+"'";
		}else if("all".equals(tag)||"administratorse".equals(tag)){
		}else {
			sql += " addUserId = '"+u.getId()+"'";
		}
		String hql = totalDao.criteriaQueries(nonCoreReceive, sql);
		if(endDate!=null&&!"".equals(endDate)){
			hql += " and youxiaoqi <= '"+endDate+"'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public NonCoreReceivablesDetail getNoncoreReceiveDetail(Integer id) {
		// TODO Auto-generated method stub
		return (NonCoreReceivablesDetail) totalDao.getObjectById(NonCoreReceivablesDetail.class, id);
	}

	@Override
	public NonCoreReceivables getNoncoreReveById(Integer id) {
		// TODO Auto-generated method stub
		return (NonCoreReceivables) totalDao.getObjectById(NonCoreReceivables.class, id);
	}

	@Override
	public String saveNoncoreReceve(NonCoreReceivables nonCoreReceive) {
		// TODO Auto-generated method stub
		Users u = Util.getLoginUser();
		if(u!=null){
			nonCoreReceive.setAddUserName(u.getName());//添加人
			nonCoreReceive.setAddUserDept(u.getDept());//添加人
			nonCoreReceive.setAddUserId(u.getId());//添加人Id
		}
		String newDate = Util.getDateTime();
		if(newDate.compareTo(nonCoreReceive.getYouxiaoqiStart())>0){//当前时间在开始时间之前
			nonCoreReceive.setTypeStatus("未到");
		}else if(newDate.compareTo(nonCoreReceive.getYouxiaoqi())<0){//当前时间在开始时间之后、结束时间之前
			nonCoreReceive.setTypeStatus("有效");
		}else {
			nonCoreReceive.setTypeStatus("过期");
		}
		nonCoreReceive.setAddTime(newDate);//添加时间
		nonCoreReceive.setStatus("未审批");
		if(totalDao.save(nonCoreReceive)){
			/****** 添加收款汇总 ***********/
			SupplierCorePayable scp = addSup(nonCoreReceive,0f);
			nonCoreReceive.setScpId(scp.getId());
			totalDao.update(nonCoreReceive);
			/****** 添加收款汇总 ***********/
			return "添加成功！";
		}else {
			return "添加失败！";
		}
	}

	/**
	 * 添加收款汇总 
	 * @param nonCoreReceive
	 * @return
	 */
	private SupplierCorePayable addSup(NonCoreReceivables nonCoreReceive,Float yingshou) {
		SupplierCorePayable scp = (SupplierCorePayable) totalDao
				.getObjectByCondition(
						"from SupplierCorePayable where supplierName=? and coreType='非主营' and payableType = '收款'",
						nonCoreReceive.getChengzufang());
		Float addYfkmoney = yingshou;
		if (scp != null) {
			scp.setYingfukuanJine(scp.getYingfukuanJine()
							+ addYfkmoney);
			if(scp.getRealfukuanJine()==null)
				scp.setRealfukuanJine(0f);
			scp.setWeifukuanJine(scp.getYingfukuanJine()
					- scp.getRealfukuanJine());
			totalDao.update(scp);
		} else {
			scp = new SupplierCorePayable();
			scp.setCoreType("非主营");
			scp.setPayableType("收款");
			scp.setSupplierName(nonCoreReceive.getChengzufang());
			// scp.setSupplierId(corePayable.getSupplierId());
			scp.setYingfukuanJine(addYfkmoney);
			scp.setRealfukuanJine(0F);
			scp.setWeifukuanJine(addYfkmoney);
			scp.setAddTime(Util.getDateTime());
			totalDao.save(scp);
		}
		return scp;
	}
	
	//处理结果
	private SupplierCorePayable addSup(NonCoreReceivablesDetail nonCoreReceive) {
		SupplierCorePayable scp = (SupplierCorePayable) totalDao
		.getObjectByCondition(
				"from SupplierCorePayable where supplierName=? and coreType='非主营' and payableType = '收款'",
				nonCoreReceive.getNonCoreReceivables().getChengzufang());
		Float addYfkmoney = nonCoreReceive.getYingfuJine();
		if (scp != null) {
			scp.setYingfukuanJine(scp.getYingfukuanJine()
					+ addYfkmoney);
			if(scp.getRealfukuanJine()==null)
				scp.setRealfukuanJine(0f);
			scp.setWeifukuanJine(scp.getYingfukuanJine()
					- scp.getRealfukuanJine());
			totalDao.update(scp);
		}
//		else {
//			scp = new SupplierCorePayable();
//			scp.setCoreType("非主营");
//			scp.setPayableType("收款");
//			scp.setSupplierName(nonCoreReceive.getChengzufang());
//			 scp.setSupplierId(corePayable.getSupplierId());
//			scp.setYingfukuanJine(addYfkmoney);
//			scp.setRealfukuanJine(0F);
//			scp.setWeifukuanJine(addYfkmoney);
//			scp.setAddTime(Util.getDateTime());
//			totalDao.save(scp);
//		}
		return scp;
	}

	@Override
	public String saveReceveDetail(NonCoreReceivables nonCoreReceive, NonCoreReceivablesDetail nonCoreReceiveables) {
		// TODO Auto-generated method stub
		NonCoreReceivables coreReceivables = getNoncoreReveById(nonCoreReceive.getId());
		if(coreReceivables!=null){
			Users u = Util.getLoginUser();
			nonCoreReceiveables.setShangchuanTime(Util.getDateTime());
			nonCoreReceiveables.setNonCoreReceivables(coreReceivables);
			if("水费".equals(nonCoreReceiveables.getKemu())||"电费".equals(nonCoreReceiveables.getKemu())){
				EnergyConsumption consumption = (EnergyConsumption) totalDao.getObjectByCondition("from EnergyConsumption where type = ?", nonCoreReceiveables.getKemu());
				if(consumption!=null){
					nonCoreReceiveables.setEffectivePrice(consumption.getUnitPrice());
				}
				nonCoreReceiveables.setBiaoshu(nonCoreReceiveables.getThisbiaoshu()-nonCoreReceiveables.getLastbiaoshu());
				nonCoreReceiveables.setYingfuJine(nonCoreReceiveables.getEffectivePrice()*(nonCoreReceiveables.getThisbiaoshu()-nonCoreReceiveables.getLastbiaoshu()));
			}else {
				//设备和地产，价格页面获得
			}
			if("已收".equals(nonCoreReceiveables.getZhuangtai())){
				nonCoreReceiveables.setRealfuJine(nonCoreReceiveables.getYingfuJine());//应收金额
			}else {
				nonCoreReceiveables.setRealfuJine(0f);
			}
			if(u!=null){
				nonCoreReceiveables.setSaveUser(u.getName());
				nonCoreReceiveables.setSaveCode(u.getCode());
			}
			nonCoreReceiveables.setStatus("未审批");
			nonCoreReceiveables.setFujian2Status("待上传");
			if(totalDao.save(nonCoreReceiveables)){
				/****** 添加收款汇总 ***********/
				SupplierCorePayable scp = addSup(coreReceivables,nonCoreReceiveables.getYingfuJine());
				nonCoreReceiveables.setScpId(scp.getId());
				totalDao.update(nonCoreReceiveables);
				/****** 添加收款汇总 ***********/
				
				String processName = "非主营业务应收现金收讫章申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							NonCoreReceivablesDetail.class, nonCoreReceiveables.getId(),
							"status", "id",
							"NoncoreReceAction!toselectDe.action?nonCoreReceivablesDetail.id="
									+ nonCoreReceiveables.getId(), u.getDept() + "部门 "
									+ u.getName()
									+ " 非主营业务应收现金收讫章申请，请您审批！", true);
					if (epId != null && epId > 0) {
						nonCoreReceiveables.setEpId(epId);
						if(totalDao.update(nonCoreReceiveables)){
							return "添加成功！";
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "申请失败！";
			}
		}
		return "添加失败！";
	}

	@Override
	public String updateNoncoreRecevc(NonCoreReceivables nonCoreReceive) {
		// TODO Auto-generated method stub
		NonCoreReceivables coreReceivables = getNoncoreReveById(nonCoreReceive.getId());
		if(coreReceivables!=null){
			BeanUtils.copyProperties(nonCoreReceive, coreReceivables,
					new String[] { "id", "addTime", "addUserName", "epId",
					"nonCoreReceivablesDetail","addUserId","addUserDept","scpId","fujianQueren","fujian2Status"});
			if(totalDao.update(coreReceivables)){
				return "修改成功！";
			}
		}
		return "修改失败！";
	}

	@Override
	public String updateReceiveDetail(
			NonCoreReceivablesDetail nonCoreReceiveables) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findNonCoreReceivables(String tag) {
		// TODO Auto-generated method stub
		if("1".equals(tag)){
			System.out.println("水费");
		}else {
			System.out.println("电费");
		}
		return totalDao.query("from NonCoreReceivables");
	}

	@Override
	public Object[] findNonCoreReceivablesDetail(
			NonCoreReceivablesDetail nonc, Integer id, Integer cpage, Integer pageSize,
			String tag) {
		// TODO Auto-generated method stub
		if (nonc == null) {
			nonc = new NonCoreReceivablesDetail();
		}
		String sql = null;
		if(id!=null){
			sql = " nonCoreReceivables.id = "+id;
		}else{
			sql = " zhuangtai = '已收'";
		}
		String hql = totalDao.criteriaQueries(nonc, sql);
		hql += " order by zhuangtai asc,id desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}
	@Override
	public Object[] findNonCoreReceivablesDetailDqr(
			NonCoreReceivablesDetail nonc, Integer cpage, Integer pageSize,
			String tag) {
		// TODO Auto-generated method stub
		if (nonc == null) {
			nonc = new NonCoreReceivablesDetail();
		}
		String sql = "";
		if("dqr".equals(tag)){
			sql = " zhuangtai = '待确认'";
		}else {
			sql = " zhuangtai = ''";
		}
		String hql = totalDao.criteriaQueries(nonc, sql,"chengzufang");
		if(nonc.getChengzufang()!=null&&!"".equals(nonc.getChengzufang())){
			hql += " and nonCoreReceivables.id in (select id from NonCoreReceivables where chengzufang like '%"+nonc.getChengzufang()+"%')";
		}
		hql += " order by jiluTime asc,id desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
//		for (Object object : list) {
//			NonCoreReceivablesDetail detail = (NonCoreReceivablesDetail) object;
//			detail.setNonCoreReceivables(detail.getNonCoreReceivables());
//		}
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public List findNonCoreReceivablesDetailCai(String s) {
		// TODO Auto-generated method stub
		return totalDao.query("from NonCoreReceivablesDetail where zhuangtai = ? ",s);
	}
	
	@Override
	public List findNonCoreReceivablesDetailwsc() {
		// TODO Auto-generated method stub
		return totalDao.query("from NonCoreReceivablesDetail where status = '同意' and fujian2Status = '待上传' and zhuangtai <> '未收'");
	}

	@Override
	public String updateReceiveDetailCai(
			NonCoreReceivablesDetail non) {
		// TODO Auto-generated method stub
		NonCoreReceivablesDetail nonl =getNoncoreReceiveDetail(non.getId());
		if(nonl!=null){
			Float syyf = 0f;//剩余应收
			Float cici = non.getRealfuJine();//此次收款金额
			if(nonl.getRealfuJine()!=null&&nonl.getRealfuJine()>0&&"未收清".equals(nonl.getZhuangtai())){
				syyf = nonl.getYingfuJine()-nonl.getRealfuJine();
				if(syyf-cici>0){
					nonl.setZhuangtai("未收清");
				}else {
					if(syyf-cici<0){
						return "确认失败！实际金额不能大于应收金额";
					}
					nonl.setZhuangtai("已收");
				}
				nonl.setRealfuJine(nonl.getRealfuJine()+cici);
			}else {
				syyf = nonl.getYingfuJine();
				if(syyf-cici>0){
					nonl.setZhuangtai("未收清");
				}else {
					if(syyf-cici<0){
						return "确认失败！实际金额不能大于应收金额";
					}
					nonl.setZhuangtai("已收");
				}
				nonl.setRealfuJine(non.getRealfuJine());
			}
			String time = Util.getDateTime();
			Users users = Util.getLoginUser();
			nonl.setQueren(users.getName());
			nonl.setQuerenTime(non.getQuerenTime());
			if(totalDao.update(nonl)){
				addFina(non, nonl, cici, time, users);//添加收款明细
				updateSup(nonl, cici);//确认收款操作
				return "确认成功！";
			}
		}
		return "确认失败！";
	}

	/**
	 * 确认收款
	 * @param nonl
	 * @param cici
	 */
	private void updateSup(NonCoreReceivablesDetail nonl, Float cici) {
		SupplierCorePayable scp = (SupplierCorePayable) totalDao
				.getObjectById(SupplierCorePayable.class, nonl.getNonCoreReceivables().getScpId());
		if (scp != null) {
			scp.setRealfukuanJine(scp.getRealfukuanJine()==null ? cici:scp.getRealfukuanJine()+ cici);
			scp.setWeifukuanJine(scp.getYingfukuanJine()
					- scp.getRealfukuanJine());
			scp.setFkCount(scp.getFkCount()==null ? 1:scp.getFkCount()+1);
			totalDao.update(scp);
		}
	}

	/**
	 * 添加收款明细
	 * @param non
	 * @param nonl
	 * @param cici
	 * @param time
	 * @param users
	 */
	private void addFina(NonCoreReceivablesDetail non,
			NonCoreReceivablesDetail nonl, Float cici, String time, Users users) {
		FinancialReceipts fr = new FinancialReceipts();
		fr.setTiJiaoTime(time);
		fr.setCoreReceivablesDetailId(nonl.getId());
		fr.setKemu(nonl.getKemu());
		fr.setQuerenMoney(cici);
		fr.setJiluTime(non.getQuerenTime());//实际收款时间
		fr.setZhangqi(non.getQuerenTime().substring(0, 7));//收款月份
		fr.setSaveCode(users.getCode());
		fr.setSaveUser(users.getName());
		fr.setSaveUserId(users.getId());
		fr.setJiezTime(nonl.getJiluTime());//账期
		totalDao.save(fr);
	}

	@Override
	public String deleteNoncoreReceiveDetailById(Integer id) {
		// TODO Auto-generated method stub
		NonCoreReceivablesDetail non = getNoncoreReceiveDetail(id);
		if(non!=null){
			if("已收".equals(non.getZhuangtai()))
				return "此条信息已收款，无法删除！";
			// 删附件
			if (non.getPhotoPath() != null) {
				File oldFile = new File(ServletActionContext
						.getServletContext().getRealPath("")
						+ "/upload/file/shuiDian/" + non.getPhotoPath());
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			if(totalDao.delete(non)){
				return "删除成功！";
			}
		}
		return "删除失败！";
	}

	@Override
	public Object[] findFinancialReceipts(FinancialReceipts financialReceipts,
			Integer cpage, Integer pageSize) {
		// TODO Auto-generated method stub
		if (financialReceipts == null) {
			financialReceipts = new FinancialReceipts();
		}
		String sql = null;
		String hql = totalDao.criteriaQueries(financialReceipts, sql);
		//List<Float> addup = totalDao.findAllByPage("select sum(querenMoney)"+hql, cpage, pageSize);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		Float a = 0f;
		for (Object object : list) {
			FinancialReceipts f = (FinancialReceipts) object;
			if(f.getQuerenMoney()==null)continue;
			a+=f.getQuerenMoney();
		}
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count , Util.towWei(a).toString()};
		return o;
	}

	@Override
	public String toaddNon(Integer id) {
		// TODO Auto-generated method stub
		if(id==null){
			return "信息为空";
		}
		int non = totalDao.getCount("from NonCoreReceivablesDetail where nonCoreReceivables.id = ? and status = '同意' and fujian2Status = '待上传'", id);
		if(non>0){
			return "该主营业务尚有未上传加盖财务章的确认收款文件，请先上传！";
		}
		return "true";
	}
	
	@Override
	public String update2(NonCoreReceivablesDetail detail) {
		// TODO Auto-generated method stub
		if (detail != null) {
			NonCoreReceivablesDetail log = getNoncoreReceiveDetail(detail.getId());
			if (log != null) {
				log.setFujianQueren(detail.getFujianQueren());
				log.setFujian2Status("已上传");
				if (totalDao.update(log))
					return "上传成功";
			}
		}
		return "上传失败";
	}

	@Override
	public void Text() {
		// TODO Auto-generated method stub
//		List<NonCoreReceivables> coreReceivables = totalDao.query("from NonCoreReceivables");
//		for (NonCoreReceivables nonCoreReceivables : coreReceivables) {
			
//			NonCoreReceivables nonCoreReceive = getNoncoreReveById(nonCoreReceivables.getId());
//			if(nonCoreReceive==null)
//				return ;
			List<SupplierCorePayable> corePayables = totalDao.query("from SupplierCorePayable where payableType = '收款' and coreType = '非主营' and id in (select scpId from NonCoreReceivablesDetail)");
			for (SupplierCorePayable scp : corePayables) {
				List<NonCoreReceivablesDetail> coreReceivablesDetails = totalDao.query("from NonCoreReceivablesDetail where scpId = ?",scp.getId());
				if(coreReceivablesDetails!=null&&coreReceivablesDetails.size()>0){
					
					if(scp!=null){
						scp.setYingfukuanJine(0F);
						scp.setRealfukuanJine(0F);
						scp.setWeifukuanJine(0F);
						for (NonCoreReceivablesDetail nonDetail : coreReceivablesDetails) {
							if(nonDetail!=null){
								scp.setYingfukuanJine(scp.getYingfukuanJine()
										+ nonDetail.getYingfuJine());
								scp.setRealfukuanJine(scp.getRealfukuanJine()
										+nonDetail.getRealfuJine());
								scp.setWeifukuanJine(scp.getYingfukuanJine()
										- scp.getRealfukuanJine());
								nonDetail.setScpId(scp.getId());
								totalDao.update(nonDetail);
							}
						}
						totalDao.update(scp);
					}
				}
			}
//			List<NonCoreReceivablesDetail> coreReceivablesDetails = totalDao.query("from NonCoreReceivablesDetail where nonCoreReceivables.id = ?",nonCoreReceivables.getId());
//			if(coreReceivablesDetails!=null&&coreReceivablesDetails.size()>0){
//				SupplierCorePayable scp = (SupplierCorePayable) totalDao
//				.getObjectByCondition(
//						"from SupplierCorePayable where supplierName=? and coreType='非主营' and payableType = '收款'",
//						nonCoreReceive.getChengzufang());
//			}
//			SupplierCorePayable scp1 = addSup(nonCoreReceive);
//		}
	}

	@Override
	public void jisuanNCR() {
		// TODO Auto-generated method stub
		String newDate = Util.getDateTime();
		//处理过期的应收信息
		List<NonCoreReceivables> receivablesShixiao = totalDao.query(
				"from NonCoreReceivables where (typeStatus not in ('失效','过期') or typeStatus is null) and youxiaoqi < ?",newDate);
		if(receivablesShixiao!=null&&receivablesShixiao.size()>0){
			for (NonCoreReceivables gq : receivablesShixiao) {
				gq.setTypeStatus("过期");
				totalDao.update2(gq);
			}
		}
		//处理未到的应收信息
		List<NonCoreReceivables> receivablesWeidao = totalDao.query(
				"from NonCoreReceivables where (typeStatus = '未到' or typeStatus is null) and youxiaoqiStart < ? and youxiaoqi >= ?",newDate,newDate);
		if(receivablesShixiao!=null&&receivablesShixiao.size()>0){
			for (NonCoreReceivables wd : receivablesWeidao) {
				wd.setTypeStatus("有效");
				totalDao.update2(wd);
			}
		}
		
		//处理有效的应收信息
		List<NonCoreReceivables> receivables = totalDao.query(
				"from NonCoreReceivables where (typeStatus not in ('失效','过期') or typeStatus is null) and youxiaoqiStart < ? and youxiaoqi >= ?",newDate,newDate);
		if(receivables!=null){
			for (NonCoreReceivables nr : receivables) {
				if("地产租赁".equals(nr.getReceiveType())){
					adddesc(newDate, nr, nr.getReceiveType());
					if("是".equals(nr.getIsNeeddaitijiaofei())){
						addShuiDian(newDate, nr);
					}
				}else if ("水电代缴".equals(nr.getReceiveType())) {
					addShuiDian(newDate, nr);
				}else {//其他、设备租赁
					adddesc(newDate, nr, nr.getReceiveType());
				}
			}
		}
	}

	private void adddesc(String newDate, NonCoreReceivables nr, String shui) {
		NonCoreReceivablesDetail detail = (NonCoreReceivablesDetail) totalDao.getObjectByCondition(
				"from NonCoreReceivablesDetail where nonCoreReceivables.id = ? and kemu = ? order by jiluTime desc", nr.getId(),shui);
		if(detail==null){//如果一条都没有，就根据缴租周期和合同开始时间生成截止至当前日期的记录，可能会有多条。
			addDetail(newDate, nr, nr.getYouxiaoqiStart(),shui);
		}else {//如果有判断是否还在该缴租周期内，在就忽略，不在就根据缴租周期和上一条的截止日期生成新的待确认收款记录。
			if(detail.getJiluTime().compareTo(newDate.substring(0, 10))<0){//截止日期小于当前日期，需要生成
				addDetail(newDate, nr, detail.getJiluTime(),shui);
			}
		}
	}

	private void addShuiDian(String newDate, NonCoreReceivables nr) {
		String shui = "水费";
		adddesc(newDate, nr, shui);
		shui = "电费";
		adddesc(newDate, nr, shui);
	}

	/**
	 * 根据财务应收主表 生成应收明细(地产租赁/设备租赁/其他)
	 * @param newDate 当前时间
	 * @param nr 应收主表
	 * @param jiezhiDate 收款截止日期(初始为合同起始日期|上一次收款截止日期)
	 */
	private void addDetail(String newDate, NonCoreReceivables nr,String jiezhiDate,String kemu) {
		String time = jiezhiDate;//记录生成的截止日期，用于和当前日期对比(初始为合同起始日期)
		do {
			NonCoreReceivablesDetail detail2 = new NonCoreReceivablesDetail();
			detail2.setKemu(kemu);//科目
			detail2.setZhuangtai("待确认");
			if("水费".equals(kemu)||"电费".equals(kemu)){
				detail2.setYingfuJine(0f);//水费电费需要完善金额
			}else {
				detail2.setYingfuJine(nr.getZujin());//单周期内应收金额
			}
			time = Util.getCalendarModified(time, 2, Integer.parseInt(nr.getJiaozuzhouqi())).substring(0,10);//得到本期应收截止日期
			if(time.compareTo(nr.getYouxiaoqi())>0){
				time = nr.getYouxiaoqi();//如果截止日期超过合同终止日期将合同截止日期赋值为JiluTime
			}
			detail2.setJiluTime(time);//费用收取至~日期
			detail2.setStatus("未审批");
			detail2.setFujian2Status("待上传");
			detail2.setRealfuJine(0f);
			detail2.setShangchuanTime(Util.getDateTime());
			detail2.setNonCoreReceivables(nr);
			totalDao.save2(detail2);
		} while (time.compareTo(newDate.substring(0, 10))<0);
	}

	@Override
	public String updateReceiveDetailqr(
			NonCoreReceivablesDetail non) {
		// TODO Auto-generated method stub
		NonCoreReceivablesDetail nonl =getNoncoreReceiveDetail(non.getId());
		if(nonl!=null){
			nonl.setZhuangtai("未收");
			nonl.setYingfuJine(non.getYingfuJine());//应收金额
			nonl.setBiaoshu(non.getBiaoshu());//读表数
			Users u = Util.getLoginUser();
			if(u!=null){
				nonl.setSaveUser(u.getName());
				nonl.setSaveCode(u.getCode());
			}
			if(totalDao.update(nonl)){
				return "确认成功！";
			}
		}
		return "确认失败！";
	}
	@Override
	public String updateReceiveDetailqx(
			NonCoreReceivablesDetail non) {
		// TODO Auto-generated method stub
		NonCoreReceivablesDetail nonl =getNoncoreReceiveDetail(non.getId());
		if(nonl!=null){
			nonl.setZhuangtai("取消");
			Users u = Util.getLoginUser();
			if(u!=null){
				nonl.setSaveUser(u.getName());
				nonl.setSaveCode(u.getCode());
			}
			if(totalDao.update(nonl)){
				return "取消成功！";
			}
		}
		return "取消失败！";
	}

}
