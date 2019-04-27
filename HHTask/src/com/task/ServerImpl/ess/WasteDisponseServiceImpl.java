package com.task.ServerImpl.ess;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.Flat3Map;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.ess.WasteDisponseService;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Goods;
import com.task.entity.approval.Signature;
import com.task.entity.ess.WasteDisponsal;
import com.task.entity.ess.WasteDisponsalTotal;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.ExecutionNode;
import com.task.util.Util;

public class WasteDisponseServiceImpl implements WasteDisponseService{

	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public boolean createGeneratorManage(List<WasteDisponsal> wasteList,WasteDisponsalTotal total) {
		boolean flag = false;
		float totalMoney = 0.0f;
		int totalCount = 0;
		for (WasteDisponsal temp : wasteList) {
			if(null==temp.getDisposePrice()){
				temp.setDisposePrice(0f);
			}
			totalMoney+=temp.getDisposePrice()*temp.getDisposeNum();
			totalCount+=1;
		}
		total.setTotalCount(totalCount);
		total.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		total.setCreateDept(Util.getLoginUser().getDept());
		total.setCreateUserCode(Util.getLoginUser().getCode());
		total.setCreateUserName(Util.getLoginUser().getName());
		total.setEpStatus("未审批");
		total.setTotalMoney(totalMoney);
		String date = Util.getDateTime("yyyyMMdd");
		String maxNumber = (String) totalDao.getObjectByCondition("select max(serialNumber) from WasteDisponsalTotal where serialNumber like '" + date + "%'");
		if (maxNumber != null && !"".equals(maxNumber)) {
			long number2 = Long.parseLong(maxNumber) + 1;
			String number3 = Long.toString(number2);
			total.setSerialNumber(number3);
		} else {
			String number2 = date + "001";
			total.setSerialNumber(number2);
		}
		/*total.setSellToName("");
		total.setExplain("");//申请说明*/
		flag = totalDao.save(total);
		if(flag){
			for (WasteDisponsal waste : wasteList) {
				WasteDisponsal wasteDisponsal = (WasteDisponsal) totalDao.getObjectById(WasteDisponsal.class, waste.getId());
				
				Goods goods = (Goods) totalDao.getObjectById(Goods.class, wasteDisponsal.getGoodsId());
				//之前的数量小于当前的数量+
				if((float)wasteDisponsal.getDisposeNum()!= (float)waste.getDisposeNum()){
					float difference = wasteDisponsal.getDisposeNum()- waste.getDisposeNum();
					goods.setGoodsCurQuantity(goods.getGoodsCurQuantity()+difference);
					totalDao.update(goods);
				}
				wasteDisponsal.setDisposeNum(waste.getDisposeNum());
				wasteDisponsal.setDisposePrice(waste.getDisposePrice());
				wasteDisponsal.setWasteDisponsalTotal(total);
				flag = totalDao.update(wasteDisponsal);
			}
			//保存废品单之后
			String processName = "报废品处理申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						WasteDisponsalTotal.class, total.getTotalId(),
						"epStatus", "totalId",
						"wasteDisposeAction!selectWDDetail.action?wasteDisponsalTotal.totalId="
								+ total.getTotalId(), 
								"部门 :"+Util.getLoginUser().getDept()+"，姓名:"
								+ Util.getLoginUser().getName()
								 + "，"+processName+"请您审批", true);
				if (epId != null && epId > 0) {
					total.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						total.setEpStatus("同意");
					} else {
						total.setEpStatus("未审批");
					}
					boolean update = totalDao.update(total);
					if(update){
						flag = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.toString());
			}
		}
		return flag;
	}

	//根据条件查找废品单总数
	@Override
	public int showWDCount(String proposer, String strDate, String endDate,String tag) {
		String[] between = new String[2];
		if (null != strDate && null != endDate && !"".equals(endDate)
				&& !"".equals(strDate)) {
			between[0] = strDate;
			between[1] = endDate;
		}
		String orderHql = "";
		if("caiwu".equals(tag)){
			orderHql = " and epStatus='同意' and caiwuEpStatus='同意' and attachmentName is not null";
		}else{
			orderHql =" and caiwuEpStatus is null";
		}
		String hql = totalDao.criteriaQueries(new WasteDisponsalTotal(), "create_time", between, "");
		int count = 0;
		if(null!=proposer &&proposer.length()>0){
			hql = totalDao.criteriaQueries(new WasteDisponsalTotal(), "create_time", between, " create_user_name =?");
			count = totalDao.getCount(hql+orderHql, proposer);
		}else{
			count = totalDao.getCount(hql+orderHql);
		}
		return count;
	}

	//根据条件查询废品单并分页
	@SuppressWarnings("unchecked")
	@Override
	public List<WasteDisponsalTotal> showwdtList(int pageNo, int pageSize,
			String proposer, String strDate, String endDate,String tag) {
		String[] between = new String[2];
		if (null != strDate && null != endDate && !"".equals(endDate) && !"".equals(strDate)) {
			between[0] = strDate;
			between[1] = endDate;
		}
		String orderHql=null;
		if("caiwu".equals(tag)){
			orderHql = "  epStatus='同意' and caiwuEpStatus='同意' and attachmentName is not null";
		}else{
			orderHql ="  caiwuEpStatus is null";
		}
		String hql = totalDao.criteriaQueries(new WasteDisponsalTotal(), "create_time", between, orderHql);
		List<WasteDisponsalTotal> findAllByPage = null;
		
		
		if(null!=proposer &&proposer.length()>0){
			hql = totalDao.criteriaQueries(new WasteDisponsalTotal(), "create_time", between, "create_user_name =? order by total_id desc");
			findAllByPage =totalDao.findAllByPage(hql, pageNo, pageSize, proposer);
		}else{
			hql+=" order by total_id desc";
			findAllByPage = totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return findAllByPage;
	}

	//根据废品单id查询报废单详细列表
	@SuppressWarnings("unchecked")
	@Override
	public List<WasteDisponsal> findwdListByTotalId(Integer id) {
		return totalDao.query("from WasteDisponsal where total_id=?",id);
	}

	@Override
	public WasteDisponsalTotal getWDTByEpId(Integer epId) {
		return (WasteDisponsalTotal) totalDao.query("from WasteDisponsalTotal where epId = ?", epId);
	}

	//根据id查找废品单
	@Override
	public WasteDisponsalTotal getwdtById(Integer totalId) {
		WasteDisponsalTotal wasteDisponsalTotal=(WasteDisponsalTotal) totalDao.getObjectById(WasteDisponsalTotal.class, totalId);
		wasteDisponsalTotal.setWasteDisponsals(wasteDisponsalTotal.getWasteDisponsals());
		return wasteDisponsalTotal;
	}

	//删除废品单
	@Override
	public boolean deletewdt(Integer totalId) {
		WasteDisponsalTotal total = (WasteDisponsalTotal) totalDao.getObjectById(WasteDisponsalTotal.class, totalId);
		return totalDao.delete(total);
	}

	//根据goodsId获得最新的库存数量
	@Override
	public Float getGoodsCurquantity(Integer goodsId) {
		Object object = totalDao.getObjectByCondition("select goodsCurQuantity from Goods where goodsId=?", goodsId);
		float parseFloat = 0.0f;
		if(null!=object ){
			String fa = object.toString();
			parseFloat = Float.parseFloat(fa);
		}
		return parseFloat;
	}

	//重新申请报废单
	@Override
	public boolean reapplywd(List<WasteDisponsal> wasteList,WasteDisponsalTotal total) {
		//获得报废单wasteDisponseTotal
		//WasteDisponsalTotal total =(WasteDisponsalTotal)totalDao.getObjectById(WasteDisponsalTotal.class, totalId);
		
		boolean flag = false;
		float totalMoney = 0.0f;	//报废品总金额
		int totalCount = 0;   //报废品总数
		for (WasteDisponsal temp : wasteList) {
			totalMoney+=temp.getDisposePrice()*temp.getDisposeNum();
			totalCount+=1;
		}
		total.setTotalCount(totalCount);
		total.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		total.setTotalMoney(totalMoney);
		//处理单号
		String date = Util.getDateTime("yyyyMMdd");
		String maxNumber = (String) totalDao.getObjectByCondition("select max(serialNumber) from WasteDisponsalTotal where serialNumber like '" + date + "%'");
		if (maxNumber != null && !"".equals(maxNumber)) {
			long number2 = Long.parseLong(maxNumber) + 1;
			String number3 = Long.toString(number2);
			total.setSerialNumber(number3);
		} else {
			String number2 = date + "001";
			total.setSerialNumber(number2);
		}
		//total.setEpStatus("未审批");
		total.setCreateUserCode(Util.getLoginUser().getCode());
		total.setCreateUserName(Util.getLoginUser().getName());
		total.setCreateDept(Util.getLoginUser().getDept());
		boolean totalFlag = totalDao.update(total);
		if(totalFlag){
			try {
				for (WasteDisponsal temp : wasteList) {
					WasteDisponsal wasteDisponsal = (WasteDisponsal) totalDao.getObjectById(WasteDisponsal.class, temp.getId());
					Goods good = (Goods) totalDao.getObjectById(Goods.class, wasteDisponsal.getGoodsId());  //获得goods
					//判断需要生成报废的数量是否大于实际数量
					if(good.getGoodsId().equals(wasteDisponsal.getGoodsId()) && good.getGoodsCurQuantity()>=temp.getDisposeNum()){
						wasteDisponsal.setGoodsChangeTime(good.getGoodsChangeTime());
						wasteDisponsal.setDisposePrice(temp.getDisposePrice());
						wasteDisponsal.setGoodsCurQuantity(good.getGoodsCurQuantity());
						wasteDisponsal.setDisposeNum(temp.getDisposeNum());
						wasteDisponsal.setFcStatus(good.getFcStatus());
						flag = totalDao.update(wasteDisponsal);
						//修改成功
						if(flag){
							good.setGoodsCurQuantity(good.getGoodsCurQuantity()-temp.getDisposeNum());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//保存报废单之后
		try {
			if(CircuitRunServerImpl.updateCircuitRun(total.getEpId())){
				total.setEpStatus("未审批");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//签名
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Object> findPayExecutionNode(Integer id) {
		//FundApply fundApply = (FundApply) this.totalDao.getObjectById(FundApply.class, id);
		WasteDisponsalTotal total = (WasteDisponsalTotal) totalDao.getObjectById(WasteDisponsalTotal.class, id);
		String nodeHql = "from ExecutionNode where auditStatus='同意' and  circuitRun.id=? order by auditLevel desc";
		//List<ExecutionNode> list1 = this.totalDao.query(hql, fundApply.getEpId());
		List<ExecutionNode> nodeList = totalDao.query(nodeHql ,total.getEpId());
		List<Signature> list = new ArrayList<Signature>();
		for (int i = 0; i < nodeList.size(); i++) {
			ExecutionNode executionNode = nodeList.get(i);
			String username = executionNode.getAuditUserName();
			String hql = "from Signature where name=? and default_address=?";
			Signature signature = (Signature) totalDao.getObjectByCondition(hql,username,"默认");
			Signature signature2 = new  Signature();
			BeanUtils.copyProperties(signature, signature2);
			
			String auditDateTime = executionNode.getAuditDateTime();
			if (auditDateTime != null) {
				auditDateTime = auditDateTime.replaceAll("-", "");
				auditDateTime = auditDateTime.replaceAll(":", "");
				auditDateTime = auditDateTime.replaceAll(" ", "");
			}
			executionNode.setAuditDateTime(auditDateTime);
			list.add(signature2);
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);  //签名
		map.put(2, nodeList);//审批节点
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WasteDisponsal> findWasteDisponsals(Integer totalId) {
		return totalDao.query("from WasteDisponsal where wasteDisponsalTotal.totalId = ?", totalId);
	}

	@Override
	public Goods getGoodsByGoodsId(Integer goodsId) {
		return (Goods) totalDao.getObjectById(Goods.class, goodsId);
	}

	//添加废品单详细
	@SuppressWarnings("unchecked")
	@Override
	public String addWasteDisponse(WasteDisponsal wasteDisponsal,
			Integer goodsId) {
		boolean flag = false;
		//先查询当前用户想订单详细列表
		String hql = "from WasteDisponsal where wasteDisponsalTotal.totalId is null and userCode =?";
		if(null==wasteDisponsal){
			return "没有找到正确的参数";
		}
		if(null != goodsId || !"".equals(goodsId) || goodsId!=0 ){
			Goods good = (Goods) totalDao.getObjectById(Goods.class, goodsId);
			List<WasteDisponsal> wasteDisPonsaList = totalDao.query(hql, Util.getLoginUser().getCode());
			for (WasteDisponsal waste : wasteDisPonsaList) {
				if(Integer.parseInt(waste.getGoodsId().toString())==goodsId){
					if(Float.parseFloat(waste.getDisposePrice().toString()) != wasteDisponsal.getDisposePrice()){
						return "查找到相同的库存并处理价格不同";
					}else{
						waste.setDisposeNum(waste.getDisposeNum()+wasteDisponsal.getDisposeNum());
						totalDao.update(waste);
						good.setGoodsCurQuantity(good.getGoodsCurQuantity()-wasteDisponsal.getDisposeNum());
						totalDao.update(good);
						return "true";
					}
				}else{
					
				}
			}
			
			//没有相同的库存执行添加
			StringBuffer buffer = new StringBuffer(good.getGoodsMarkId());
			if(good.getProcessNo()!= null ){
				buffer.append("(");
				buffer.append(good.getProcessNo());
				buffer.append(")");
			}
			if(good.getYwmarkId()!=null ){
				buffer.append("(");
				buffer.append(good.getYwmarkId());
				buffer.append(")");
			}
			String partNumber = buffer.toString();
			wasteDisponsal.setPartNumber(partNumber);
			wasteDisponsal.setUserCode(Util.getLoginUser().getCode());
			wasteDisponsal.setGoodsWgType(good.getWgType());
			wasteDisponsal.setGoodsUnit(good.getGoodsUnit());
			wasteDisponsal.setGoodsStyle(good.getGoodsStyle());
			wasteDisponsal.setGoodssHouseName(good.getGoodHouseName());
			wasteDisponsal.setGoodsPosition(good.getGoodsPosition());
			wasteDisponsal.setGoodsLotId(good.getGoodsLotId());
			wasteDisponsal.setGoodsKgliao(good.getKgliao());
			wasteDisponsal.setGoodsId(good.getGoodsId());
			wasteDisponsal.setGoodsFullName(good.getGoodsFullName());
			wasteDisponsal.setGoodsChangeTime(good.getGoodsChangeTime());
			wasteDisponsal.setGoodsFormat(good.getGoodsFormat());
			wasteDisponsal.setDisposePrice(wasteDisponsal.getDisposePrice());
			wasteDisponsal.setGoodsCurQuantity(good.getGoodsCurQuantity());
			wasteDisponsal.setFcStatus(good.getFcStatus());
			wasteDisponsal.setGoodsPrice(good.getGoodsPrice());
			//wasteDisponsal.setTotalId(total.getTotalId());
			//wasteDisponsal.setWasteDisponsalTotal(total);
			flag = totalDao.save(wasteDisponsal);
			//添加成功
			if(flag){
				good.setGoodsCurQuantity(good.getGoodsCurQuantity()-wasteDisponsal.getDisposeNum());
				totalDao.update(good);
				return "true";
			}
			
		}
		return "添加失败";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WasteDisponsal> showWasteApply() {
		String hql = "from WasteDisponsal where wasteDisponsalTotal.totalId is null and userCode =?";
		List<WasteDisponsal> wasteDisPonsaList = totalDao.query(hql, Util.getLoginUser().getCode());
		return wasteDisPonsaList;
	}

	@Override
	public boolean deleteDisposal(Integer id) {
		WasteDisponsal wasteDisponsal = (WasteDisponsal) totalDao.getObjectById(WasteDisponsal.class, id);
		
		if(totalDao.delete(wasteDisponsal)){
			Goods goods = (Goods) totalDao.getObjectById(Goods.class, wasteDisponsal.getGoodsId());
			goods.setGoodsCurQuantity(goods.getGoodsCurQuantity()+wasteDisponsal.getDisposeNum());
			boolean flag = totalDao.update(goods);
			if(flag){
				return true;
			}
		}
		return false;
	}

	@Override
	public String updatePrice(List<WasteDisponsal> list, File[] attachment,String[] attachmentFileName) {
		// 上传附件
		String attachmentName = "";
		if (attachment != null && attachment.length > 0) {
			for (int i = 0; i < attachment.length; i++) {
				String fileName =Util.getDateTime("yyyyMMddHHmmss") + (attachmentFileName[i] .substring(attachmentFileName[i]
										.lastIndexOf(".")));
				if (i > 0) {
					attachmentName += "|" + fileName;
				} else {
					attachmentName += fileName;
				}
				attachmentName.trim();
				// 上传到服务器
				String fileRealPath = ServletActionContext .getServletContext().getRealPath("/upload/file")
						+ "\\" + fileName;
				File file = new File(fileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], file);
				} catch (Exception e) {
					return "文件出错!";
				}

				// 备份到项目
				String beiFenfileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/file/"+fileName);
				File beiFenFile = new File(beiFenfileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], beiFenFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			attachmentName = Util.getDateTime();
			if (attachment.length==0) {
				return "请上传附件";
			}
		}
		float money = 0.0f;
		int totalId = 0;
		for (WasteDisponsal wasteDisponsal : list) {
			WasteDisponsal disponsal= (WasteDisponsal) totalDao.getObjectById(WasteDisponsal.class, wasteDisponsal.getId());
			
			disponsal.setDisposePrice(wasteDisponsal.getDisposePrice());
			totalDao.update(disponsal);
			money+=wasteDisponsal.getDisposePrice()*disponsal.getDisposeNum();
			totalId = disponsal.getWasteDisponsalTotal().getTotalId();
		}
		WasteDisponsalTotal wasteDisponsalTotal = (WasteDisponsalTotal) totalDao.getObjectById(WasteDisponsalTotal.class, totalId);
		wasteDisponsalTotal.setTotalMoney(money);
		wasteDisponsalTotal.setAttachmentName(attachmentName);
		if(totalDao.update(wasteDisponsalTotal)){
			String processName = "财务确认流程";
			Integer caiwuEpId = null;
			try {
				caiwuEpId = CircuitRunServerImpl.createProcess(processName,
						WasteDisponsalTotal.class, wasteDisponsalTotal.getTotalId(),
						"caiwuEpStatus", "totalId",
						"wasteDisposeAction!selectWDDetail.action?wasteDisponsalTotal.totalId="
								+ wasteDisponsalTotal.getTotalId(), 
								"部门 :"+Util.getLoginUser().getDept()+"，姓名:"
								+ Util.getLoginUser().getName()
								 + "，"+processName+"请您审批", true);
				if (caiwuEpId != null && caiwuEpId > 0) {
					wasteDisponsalTotal.setCaiwuEpId(caiwuEpId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, caiwuEpId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						wasteDisponsalTotal.setCaiwuEpStatus("同意");
					} else {
						wasteDisponsalTotal.setCaiwuEpStatus("未审批");
					}
					boolean update = totalDao.update(wasteDisponsalTotal);
					if(update){
						return "true";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			return "设定价格失败";
		}else{
			return "操作失败";
		}	
	}

	@Override
	public List<WasteDisponsalTotal> findBePriceNotEp() {
		String hql = totalDao.criteriaQueries(new WasteDisponsalTotal(), "");
		hql+=" and attachmentName is null and caiwuEpStatus is null and epStatus='同意' order by totalId desc";
		return totalDao.query(hql);
	}

	@Override
	public List<WasteDisponsalTotal> findNotPriceAndEP() {
		String hql = totalDao.criteriaQueries(new WasteDisponsalTotal(), "");
		hql+=" and attachmentName is not null and caiwuEpStatus <> '同意' and epStatus='同意' order by totalId desc";
		return totalDao.query(hql);
	}

}
