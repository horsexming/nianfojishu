package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.InsuranceGoldServer;
import com.task.Server.ProductPriceServer;
import com.task.Server.WageStandardServer;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.entity.DTOProcess;
import com.task.entity.DataGrid;
import com.task.entity.InsuranceGold;
import com.task.entity.ProductPrice;
import com.task.entity.ProductProcess;
import com.task.entity.SpareParts;
import com.task.entity.TaSopGongwei;
import com.task.entity.Tijiang;
import com.task.entity.VOProductTree;
import com.task.entity.WageStandard;

public class ProductPriceServerImpl implements ProductPriceServer {

	private TotalDao totalDao;
	private WageStandardServer wss; // 工资模板
	private InsuranceGoldServer igs; // 五险一金Server层
	private static final Double SECONDS = 619200.0;

	public Object[] queryProductPrice(ProductPrice priceProduct, Integer cpage,
			Integer pageSize) {
		String hql = "from ProductPrice order by style asc";
		if (null != priceProduct) {
			hql = totalDao.criteriaQueries(priceProduct, "", null);
		}
		Object[] productAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		productAarr[0] = count;
		productAarr[1] = list;
		/**
		 * 临时处理借还管理
		 */
		/*
		 * String hqlAlso="from Also"; List li=totalDao.find(hqlAlso); for(int
		 * i=0;i<li.size();i++){ Also also=(Also)li.get(i); String
		 * hqlB="from Borrow where mix='"
		 * +also.getMix()+"' and peopleName='"+also
		 * .getPeopleName()+"' and cardNum='"
		 * +also.getCardNum()+"' and num="+also.getProcessQuantity()
		 * +" and state='未归还'"; String
		 * hqlBB="from Borrow where mix='"+also.getMix
		 * ()+"' and peopleName='"+also
		 * .getPeopleName()+"' and cardNum='"+also.getCardNum
		 * ()+"' and num>"+also.getProcessQuantity() +" and state='未归还'";
		 * 
		 * List liB=totalDao.find(hqlB); List liBB=totalDao.find(hqlBB);
		 * if(liB!=null && liB.size()>0){ Borrow borrow=(Borrow)liB.get(0);
		 * also.setBorrow(borrow); borrow.setState("已归还");
		 * borrow.setGiveBackNum(0); totalDao.update(borrow);
		 * totalDao.update(also);
		 * 
		 * }else{ if(liBB!=null && liBB.size()>0){ Borrow
		 * borrow=(Borrow)liB.get(0); also.setBorrow(borrow);
		 * borrow.setGiveBackNum
		 * (borrow.getGiveBackNum()-also.getProcessQuantity());
		 * totalDao.update(borrow); totalDao.update(also);
		 * 
		 * }else{ Borrow borrow =new Borrow();
		 * borrow.setCardNum(also.getCardNum());
		 * borrow.setCarType(also.getCarType()); borrow.setDate(also.getDate());
		 * borrow.setDept(also.getDept()); borrow.setFormat(also.getFormat());
		 * borrow.setGiveBackNum(0); borrow.setMatetag(also.getName());
		 * borrow.setMix(also.getMix());
		 * borrow.setNum(also.getProcessQuantity());
		 * borrow.setNumber(also.getNumber());
		 * borrow.setPeopleName(also.getName());
		 * borrow.setProcessPieceNum(also.getProcessPieceNum());
		 * borrow.setState("已归还"); borrow.setStorehouse(also.getStorehouse());
		 * borrow.setStore(also.getStore()); borrow.setUnit(also.getUnit());
		 * also.setBorrow(borrow); totalDao.save(borrow); totalDao.update(also);
		 * } } }
		 */
		return productAarr;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean saveProduct(ProductPrice productPrice) {
		// TODO Auto-generated method stub
		if (productPrice != null) {
			productPrice.setOnePrice(0.0);
			productPrice.setEntryDate(totalDao.getDateTime("yyyy-MM-dd"));
			return totalDao.save(productPrice);
		}
		return false;
	}

	@Override
	public ProductPrice getProductPrice(Integer id) {
		// TODO Auto-generated method stub
		return (ProductPrice) totalDao.getObjectById(ProductPrice.class, id);
	}

	@Override
	public boolean updateProductPrice(ProductPrice productPrice) {
		// TODO Auto-generated method stub
		// 处理提奖单价
		/*
		 * 处理产品单价
		 */
		// ProductPrice productPrice=spareParts.getProduct();
		ProductPrice product = (ProductPrice) totalDao.getObjectById(
				ProductPrice.class, productPrice.getId());
		// 复制相同属性
		BeanUtils.copyProperties(productPrice, product,
				new String[] { "spareParts" });
		String hql = "select sum(allLaborcost) from SpareParts where product.id="
				+ productPrice.getId();
		// 修改交付量
		// ==========================
		Set<SpareParts> sparr = product.getSpareParts();
		// 遍历出工序信息
		for (Iterator<SpareParts> iterator = sparr.iterator(); iterator
				.hasNext();) {
			SpareParts sparts = iterator.next();
			Set<ProductProcess> productProcessArr = sparts.getProductProcess();
			for (Iterator<ProductProcess> iteratorP = productProcessArr
					.iterator(); iteratorP.hasNext();) {
				ProductProcess productProcess = iteratorP.next();
				productProcess.setOPjiaofu(product.getDailyoutput());
				totalDao.update(productProcess);
			}
		}
		// System.out.println("======="+totalDao.query(hql).size());
		/*
		 * if(null!=totalDao.query(hql) && totalDao.query(hql).size()>0){ Float
		 * realRengong=(Float) totalDao.query(hql).get(0);
		 * product.setOnePrice((product
		 * .getLaborcost()*product.getDailyoutput()-realRengong
		 * )/((double)product.getDailyoutput())); totalDao.update(product); }
		 */
		totalDao.update(product);
		return true;
	}

	/***
	 * 根据ID查找零组件信息
	 */
	@SuppressWarnings("unchecked")
	public List findSparePartsById(Integer id) {
		if (id != null && id > 0) {
			String hql = "select * from ta_sop_tj_SpareParts where productPrice_Id=?";
			List list = totalDao.createQuerySelect(null, hql, id);
			List sList = new ArrayList();
			for (int i = 0, fen = list.size(); i < fen; i++) {
				Object[] sps = (Object[]) list.get(i);
				// [12030010S00, 前排气管总成, 0.0, 0.0, null, 2013-04-24, null,41]
				SpareParts sp = new SpareParts();
				sp.setId((Integer) sps[0]);
				sp.setSpmarkId(sps[1].toString());
				sp.setSpgoodsName(sps[2].toString());
				sp.setAllWorkHours(Float.parseFloat(sps[3].toString()));
				sp.setAllLaborcost((Double) sps[4]);
				sp.setEntryDate(sps[6].toString());
				sp.setMore(sps[7] == null ? null : sps[7].toString());
				sList.add(sp);
			}
			return sList;
		}
		return null;
	}

	@Override
	public boolean saveSpareParts(SpareParts spareParts, Integer id) {
		// TODO Auto-generated method stub
		ProductPrice productPrice = (ProductPrice) totalDao.getObjectById(
				ProductPrice.class, id);
		spareParts.setProduct(productPrice);
		spareParts.setEntryDate(totalDao.getDateTime("yyyy-MM-dd"));
		spareParts.setAllWorkHours(0f);
		spareParts.setAllLaborcost(0.0);
		return totalDao.save(spareParts);

	}

	@Override
	public List findProductProcessById(Integer id) {
		// TODO Auto-generated method stub
		// SpareParts spareparts=(SpareParts)totalDao.getId(SpareParts.class,
		// id);
		String hql = "from ProductProcess where spareParts.id=" + id;
		return totalDao.query(hql);
	}

	@Override
	public SpareParts getSpareParets(Integer id) {
		// TODO Auto-generated method stub
		return (SpareParts) totalDao.getObjectById(SpareParts.class, id);
	}

	@Override
	public boolean saveProductProcess(ProductProcess productProcess, Integer id) {
		// TODO Auto-generated method stub
		// System.out.println("=================="+id);
		SpareParts spareParts = (SpareParts) totalDao.getObjectById(
				SpareParts.class, id);
		productProcess.setSpareParts(spareParts);
		productProcess.setRealJIEPAI(0f);
		productProcess.setProcessMomey(0.0);
		if (null != productProcess.getOPCouldReplaceRate()
				&& 0 != productProcess.getOPCouldReplaceRate()) {
			productProcess.setOPnoReplaceRate(1 / productProcess
					.getOPCouldReplaceRate());
		}
		if (null != productProcess.getGZCouldReplaceRate()
				&& 0 != productProcess.getGZCouldReplaceRate()) {
			productProcess.setGZnoReplaceRate(1 / productProcess
					.getGZCouldReplaceRate());
		}

		TaSopGongwei tt = new TaSopGongwei();
		String hql = "from TaSopGongwei where gongweihao=? and shebeiCode=? ";
		if (totalDao.query(hql, productProcess.getGongwei(),
				productProcess.getShebeiNo()).size() > 0) {
			tt = (TaSopGongwei) totalDao.query(hql,
					productProcess.getGongwei(), productProcess.getShebeiNo())
					.get(0);
			productProcess.setSopGongwei(tt);
		}
		totalDao.save(productProcess);
		return true;
	}

	@Override
	public boolean deleteProductProces(ProductProcess productProcess) {
		// TODO Auto-generated method stub
		/*
		 * 修改零件人工费 修改提奖单价
		 */
		boolean tag = false;
		if (null != productProcess) {
			SpareParts spareParts = productProcess.getSpareParts();
			ProductPrice productPrice = spareParts.getProduct();
			TaSopGongwei gongwei = productProcess.getSopGongwei();
			spareParts.setAllLaborcost(spareParts.getAllLaborcost()
					- productProcess.getProcessMomey());
			spareParts.setAllWorkHours(spareParts.getAllWorkHours()
					- productProcess.getRealJIEPAI());
			productProcess.getSpareParts().getProductProcess().remove(
					productProcess);
			gongwei.getProductProcess().remove(productProcess);
			productProcess.setSpareParts(null);
			productProcess.setSopGongwei(null);
			totalDao.update(spareParts);
			totalDao.update(gongwei);
			totalDao.delete(productProcess);
			/*
			 * String
			 * hql="select sum(allLaborcost) from SpareParts where product.id="
			 * +productPrice.getId(); if(null!=totalDao.query(hql) &&
			 * null!=totalDao.query(hql).get(0) ){ Float realRengong=(Float)
			 * totalDao.query(hql).get(0);
			 * productPrice.setOnePrice(productPrice.getOnePrice()-realRengong);
			 * totalDao.update(productPrice); }
			 */
			tag = true;
		}
		return tag;

	}

	@Override
	public ProductProcess getProductProcess(Integer id) {
		// TODO Auto-generated method stub
		return (ProductProcess) totalDao
				.getObjectById(ProductProcess.class, id);
	}

	@Override
	public boolean updateProductProcee(ProductProcess productProcess) {
		// TODO Auto-generated method stub
		/*
		 * 修改工序信息 更新零件人工费 更新产品单价
		 */
		if (null != productProcess) {
			/*
			 * 计算人工费用
			 */
			boolean returnTag = true;
			ProductProcess DBproductProcess = (ProductProcess) totalDao
					.getObjectById(ProductProcess.class, productProcess.getId());
			BeanUtils.copyProperties(productProcess, DBproductProcess,
					new String[] { "spareParts", "sopGongwei" });
			DBproductProcess.setOPnoReplaceRate(1 / productProcess
					.getOPCouldReplaceRate());
			DBproductProcess.setGZnoReplaceRate(1 / productProcess
					.getGZCouldReplaceRate());
			TaSopGongwei tt = new TaSopGongwei();
			String hql = "from TaSopGongwei where gongweihao=? and shebeiCode=? ";
			if (totalDao.query(hql, productProcess.getGongwei(),
					productProcess.getShebeiNo()).size() > 0) {
				tt = (TaSopGongwei) totalDao.query(hql,
						productProcess.getGongwei(),
						productProcess.getShebeiNo()).get(0);
				productProcess.setSopGongwei(tt);
			}
			totalDao.update(DBproductProcess);
			/*
			 * String codeGroup=productProcess.getOperatorCode();
			 * codeGroup.replace("；", ";");//抛出中文字符 String[]
			 * codeArr=codeGroup.split(";");
			 * 
			 * float allCodeWage=0f; if(codeArr.length>0){//遍历操作者工号 for(int
			 * i=0;i<codeArr.length;i++){ String code=codeArr[i];
			 * //根据工号查询工作模板表找到该员工工资模版 String
			 * hqlWageStand="from WageStandard where code='"
			 * +code+"' and standardStatus='默认' and processStatus='同意'";
			 * if(totalDao.query(hqlWageStand).size()>0){//工资模版存在 WageStandard
			 * wageStand=(WageStandard)totalDao.query(hqlWageStand).get(0);
			 * //计算每月总人工费用,根据员工户口性质查找企业上缴福利系数 String
			 * StringGold="from InsuranceGold where personClass=?";
			 * InsuranceGold
			 * insuranceGold=(InsuranceGold)totalDao.query(StringGold,
			 * wageStand.getPersonClass()).get(0);
			 * //企业为个人缴纳总额=岗位工资+统筹经+医疗保险+事业保险++工伤+生育保险+公积金 Float
			 * gerenTotalWage=wageStand.getGangweigongzi()+
			 * wageStand.getSsBase()
			 * *100*(insuranceGold.getQYoldageInsurance()+insuranceGold
			 * .getQYinjuryInsurance
			 * ()+insuranceGold.getQYmaternityInsurance()+insuranceGold
			 * .getQYmedicalInsurance
			 * ()+insuranceGold.getQYunemploymentInsurance())/10000
			 * +wageStand.getGjjBase
			 * ()*100*(insuranceGold.getQYhousingFund())/10000;
			 * allCodeWage+=gerenTotalWage; }else{//工资模版 不存在 String
			 * message="员工工资未处理"; returnTag=false; } } if(allCodeWage>0){ double
			 * processMoney=(allCodeWage/(21.5*8*3600));
			 * DBproductProcess.setProcessMomey(processMoney); }
			 * //DBproductProcess.setSpareParts(spareParts);
			 * totalDao.update(DBproductProcess); }
			 */
			/*
			 * 处理零件人工费
			 */
			/*
			 * SpareParts spareParts=DBproductProcess.getSpareParts();
			 * ProductPrice productPrice=spareParts.getProduct(); String
			 * hqlSpareParts=
			 * "select sum(processMomey) as money,sum(realJIEPAI) as workHours from ProductProcess where spareParts.id="
			 * +spareParts.getId(); if(null!=totalDao.listSub(hqlSpareParts) &&
			 * null!=totalDao.listSub(hqlSpareParts).get(0) ){ //Float[]
			 * all=(Object[])totalDao.listSub(hqlSpareParts).get(0);
			 * List<Object[]> listAll=new ArrayList<Object[]>();
			 * 
			 * listAll=totalDao.listSub(hqlSpareParts); Object[]
			 * all=listAll.get(0); double allLaborcost=(Double)all[0]; float
			 * allWorkHours=(float)Math.round((Double)all[1]) ;
			 * spareParts.setAllLaborcost(allLaborcost);
			 * spareParts.setAllWorkHours(allWorkHours);
			 * spareParts.setProduct(productPrice);
			 * spareParts.setProduct(productPrice); totalDao.update(spareParts);
			 * }
			 */
			/*
			 * 处理产品单价
			 */
			/*
			 * String
			 * hql="select sum(allLaborcost) from SpareParts where product.id="
			 * +productPrice.getId(); if(null!=totalDao.query(hql) &&
			 * null!=totalDao.query(hql).get(0) ){ Float realRengong=(Float)
			 * totalDao.query(hql).get(0);
			 * productPrice.setOnePrice((productPrice
			 * .getLaborcost()*productPrice
			 * .getDailyoutput()-realRengong*productPrice
			 * .getDailyoutput())/(double)productPrice.getDailyoutput());
			 * totalDao.update(productPrice); } return returnTag;
			 */
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String findUserName(String code) {
		// TODO Auto-generated method stub
		if (null != code && !"".equals(code)) {
			code = code.replace("；", ";");// 抛出中文字符
			// String []codeArr=code.split(";");
			code = "'" + code + "'";
			code = code.replace(";", "','");// 抛出中文字符

			String hql = "from WageStandard where code in(" + code
					+ ") and standardStatus='默认' and processStatus='同意'";
			String message = "";
			if (totalDao.query(hql).size() > 0) {
				for (int i = 0; i < totalDao.query(hql).size(); i++) {
					if (i > 0) {
						message = message
								+ ";"
								+ ((WageStandard) (totalDao.query(hql).get(i)))
										.getUserName();
					} else {
						message = message
								+ ((WageStandard) (totalDao.query(hql).get(i)))
										.getUserName();
					}

				}
			}
			return message;
		}
		return "";
	}

	@Override
	public Integer deleteSpareParts(Integer id) {
		// 删除零件操作
		SpareParts sp = (SpareParts) totalDao.getObjectById(SpareParts.class,
				id);
		int id2 = sp.getProduct().getId();
		if (totalDao.delete(sp)) {
			return id2;
		}
		return null;
	}

	@Override
	public Integer updateSpareParts(SpareParts spareParts) {
		// TODO Auto-generated method stub
		// 修改零组件信息
		SpareParts sp = (SpareParts) totalDao.getObjectById(SpareParts.class,
				spareParts.getId());
		BeanUtils.copyProperties(spareParts, sp, new String[] { "product",
				"productProcess", "allLaborcost", "username" });
		int id = sp.getProduct().getId();
		if (totalDao.update(sp)) {
			return id;
		}
		return null;
	}

	@Override
	public SpareParts getOneSpareParts(Integer id) {
		// TODO Auto-generated method stub
		return (SpareParts) totalDao.getObjectById(SpareParts.class, id);
	}

	@Override
	public boolean deleteProductPrice(Integer id) {
		// TODO Auto-generated method stub
		ProductPrice pp = (ProductPrice) totalDao.getObjectById(
				ProductPrice.class, id);
		return totalDao.delete(pp);
	}

	@Override
	public String findGongwei(String str) {
		// TODO Auto-generated method stub
		// 字符串可传班组信息
		String message = "";
		String hql = "select distinct(gongweihao) from TaSopGongwei ";
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			message += d.toString() + "|";
		}
		return message;

	}

	public List findGongwei1(String tag) {
		String hql = "from TaSopGongwei  where  1=1";
		if (tag != null && tag.length() > 0) {
			hql = hql + " and  shebeiCode='" + tag + "' ";
		}
		List list = totalDao.query(hql + " order by gongweihao");
		return list;
	}

	@Override
	public String findShebeiCode(String gongwei) {
		String message = "";
		String hql = "select shebeiCode from TaSopGongwei where gongweihao='"
				+ gongwei + "'";
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			message += d.toString() + "|";
		}
		return message;
	}

	@Override
	public TaSopGongwei findGongweiAndOth(String gongweihao, String shebeiCode) {
		TaSopGongwei tt = new TaSopGongwei();
		String hql = "from TaSopGongwei where shebeiCode=? ";
		return (TaSopGongwei) totalDao.getObjectByCondition(hql, shebeiCode);
	}

	public boolean jisuanDanjianBonus(Integer[] peoductPriceId) {
		// TODO Auto-generated method stub
		boolean bool = false;
		if (peoductPriceId.length > 0) {// 选择的总成件号条数存在
			for (int i = 0; i < peoductPriceId.length; i++) {
				// 获取总成
				ProductPrice productPrice = (ProductPrice) totalDao
						.getObjectById(ProductPrice.class, peoductPriceId[i]);
				float baojiaRengong = productPrice.getLaborcost();// 报价直接人工费
				float fenpeiXishu = productPrice.getFenpeiRate();// 分配可调系数
				float richanliang = productPrice.getDailyoutput();// 入库量
				List<ProductProcess> list = new ArrayList<ProductProcess>();
				/*
				 * 操作过程变量累计
				 */
				float circleGongzi = 0f;// 零件周期总工时工资
				float sumOPfuhe = 0f; // 负荷指数总数
				float sumOPjineng = 0f;// 技能指数总数
				float sumOPNoRelace = 0f;// 不可替换指数累计
				float sumOPRengongjiepai = 0f;// 人工节拍累计
				float sumOPShenbeijiepai = 0f;// 设备节拍累计
				// float sumOPzongheqiangdu=0f;//综合强度
				/*
				 * 准备过程变量累计
				 */
				float sumGZfuhu = 0f;// 负荷指数总额
				float sumGZjineng = 0f;// 技能指数总额
				float sumGZNoreplace = 0f;// 不可替换总额
				float sumGZjipai = 0f;// 操作节拍总额
				// float sumGZzongheqiangdu=0f;//综合强度
				// 获取零件信息
				Set<SpareParts> sparr = productPrice.getSpareParts();
				// 遍历出工序信息
				for (Iterator<SpareParts> iterator = sparr.iterator(); iterator
						.hasNext();) {
					// System.out.println("==============");
					SpareParts sparts = iterator.next();
					Set<ProductProcess> productProcessArr = sparts
							.getProductProcess();
					for (Iterator<ProductProcess> iteratorP = productProcessArr
							.iterator(); iteratorP.hasNext();) {
						ProductProcess productProcess = iteratorP.next();
						// 把工序放进总成对应的list 中
						list.add(productProcess);
						// 计算秒工时工资
						// 查询工资模版
						String codeGroup = productProcess.getOperatorCode();
						codeGroup.replace("；", ";");// 抛出中文字符
						String[] codeArr = codeGroup.split(";");
						float allCodeWage = 0f;
						if (codeArr.length > 0) {// 遍历操作者工号
							for (int ii = 0; ii < codeArr.length; ii++) {
								String code = codeArr[ii];
								// 根据工号查询工作模板表找到该员工工资模版
								String hqlWageStand = "from WageStandard where code='"
										+ code
										+ "' and standardStatus='默认' and processStatus='同意'";
								if (totalDao.query(hqlWageStand).size() > 0) {// 工资模版存在
									WageStandard wageStand = (WageStandard) totalDao
											.query(hqlWageStand).get(0);
									// 计算每月总人工费用,根据员工户口性质查找企业上缴福利系数
									/*
									 * String StringGold =
									 * "from InsuranceGold where personClass=?";
									 * InsuranceGold insuranceGold =
									 * (InsuranceGold) totalDao
									 * .query(StringGold,
									 * wageStand.getPersonClass()) .get(0);
									 */
									String StringGold = "from InsuranceGold where personClass=? and localOrField=? and cityOrCountryside=?";
									InsuranceGold insuranceGold = (InsuranceGold) totalDao
											.query(
													StringGold,
													wageStand.getPersonClass(),
													wageStand.getLocalOrField(),
													wageStand
															.getCityOrCountryside())
											.get(0);
									// 企业为个人缴纳总额=岗位工资+统筹经+医疗保险+事业保险++工伤+生育保险+公积金
									Float gerenTotalWage = wageStand
											.getGangweigongzi()
											+ wageStand.getSsBase()
											* 100
											* (insuranceGold
													.getQYoldageInsurance()
													+ insuranceGold
															.getQYinjuryInsurance()
													+ insuranceGold
															.getQYmaternityInsurance()
													+ insuranceGold
															.getQYmedicalInsurance() + insuranceGold
													.getQYunemploymentInsurance())
											/ 10000
											+ wageStand.getGjjBase()
											* 100
											* (insuranceGold.getQYhousingFund())
											/ 10000;
									allCodeWage += gerenTotalWage;
								} else {// 工资模版 不存在
									String message = "员工工资未处理";
									bool = false;
								}
							}
							if (allCodeWage > 0) {
								double processOnegongxuMoney = (allCodeWage / (21.5 * 8 * 3600));
								// 计算周期工资
								// richanliang
								// productProcess.getOPjiaofu() 交付量

								circleGongzi += processOnegongxuMoney
										* (richanliang
												* (productProcess
														.getOPcaozuojiepai() + productProcess
														.getOPshebeijiepai()) + (productProcess
												.getGZzhunbeijiepai() * productProcess
												.getGZzhunbeicishu()));
								/*
								 * 计算各列总额 并把工序放进对象 判断所要累加的量不为空
								 */
								if (null != productProcess.getOPfuheRate()) {
									sumOPfuhe += productProcess.getOPfuheRate();
								}
								if (null != productProcess
										.getOPtechnologyRate()) {
									sumOPjineng += productProcess
											.getOPtechnologyRate();
								}
								if (null != productProcess.getOPnoReplaceRate()) {
									sumOPNoRelace += productProcess
											.getOPnoReplaceRate();
								}
								if (null != productProcess.getOPcaozuojiepai()) {
									sumOPRengongjiepai += productProcess
											.getOPcaozuojiepai();
								}
								// if(null!=productProcess.getOPshebeijiepai()){sumOPShenbeijiepai+=productProcess.getOPshebeijiepai();}
								if (null != productProcess.getGZfuheRate()) {
									sumGZfuhu += productProcess.getGZfuheRate();
								}
								if (null != productProcess
										.getGZtechnologyRate()) {
									sumGZjineng += productProcess
											.getGZtechnologyRate();
								}
								if (null != productProcess.getGZnoReplaceRate()) {
									sumGZNoreplace += productProcess
											.getGZnoReplaceRate();
								}
								if (null != productProcess.getGZzhunbeijiepai()) {
									sumGZjipai += productProcess
											.getGZzhunbeijiepai();
								}

								/*
								 * if(null!=productProcess.getOPfuheRate()){;}
								 * if(null!=productProcess.getOPfuheRate()){;}
								 * if(null!=productProcess.getOPfuheRate()){;}
								 * if(null!=productProcess.getOPfuheRate()){;}
								 */

							}
							// 计算单工序节拍，准备人工节拍
							// 求员工秒工资
						}

						// 遍历工序
					}
					// 遍历零件
				}
				/*
				 * 计算该总成的提奖单价 分配工序奖金额
				 */
				List<ProductProcess> listGongxuDanjia = new ArrayList<ProductProcess>();
				float sumJiangjin = baojiaRengong * fenpeiXishu * richanliang
						- circleGongzi;// 总分配奖金
				float zongchengDanjia = sumJiangjin / richanliang;
				productPrice.setOnePrice((double) zongchengDanjia);
				totalDao.update(productPrice);
				bool = true;
				float sumOPZongheqiangdu = 0f;// 操作过程综合强度
				float sumGZzongheqiangdu = 0f;// 准备过程综合强度
				float sumOPzonghexishu = 0f;// 操作过程综合系数和
				float sumGZzonghexishu = 0f;// 准备过程综合系数和
				float sumOPzonge = 0f;// 操作过程分配总额
				float sumGZzonge = 0f;// 准备过程分配总额
				if (list.size() > 0) {
					for (int iii = 0; iii < list.size(); iii++) {
						ProductProcess pp = list.get(iii);
						if (null != pp.getOperatorCode()
								&& !"".equals(pp.getOperatorCode())) {
							if (null != pp.getOPfuheRate()) {
							}
							// 计算操作综合系数
							float OPzonghexishu = 0f;
							if (0 != pp.getOPfuheRate() && sumOPfuhe > 0) {
								OPzonghexishu += pp.getOPfuheRate() / sumOPfuhe;
							}
							if (0 != pp.getOPcaozuojiepai()
									&& sumOPRengongjiepai > 0) {
								OPzonghexishu += pp.getOPcaozuojiepai()
										/ sumOPRengongjiepai;
							}
							if (0 != pp.getOPnoReplaceRate()
									&& sumOPNoRelace > 0) {
								OPzonghexishu += pp.getOPnoReplaceRate()
										/ sumOPNoRelace;
							}
							if (0 != pp.getOPtechnologyRate()
									&& sumOPjineng > 0) {
								OPzonghexishu += pp.getOPtechnologyRate()
										/ sumOPjineng;
							}
							sumOPzonghexishu += OPzonghexishu;
							// 操作综合强度
							float OPzongheqiangdu = 0f;
							if (OPzonghexishu > 0 && 0 != pp.getOPjiaofu()
									&& pp.getOPjiaofu() > 0) {
								OPzongheqiangdu = OPzonghexishu
										* pp.getOPjiaofu();
							}

							sumOPZongheqiangdu += OPzongheqiangdu;
							// 计算准备综合系数
							float GZzonghexishu = 0f;
							if (0 != pp.getGZfuheRate() && sumGZfuhu > 0) {
								GZzonghexishu += pp.getGZfuheRate() / sumGZfuhu;
							}
							if (0 != pp.getGZnoReplaceRate()
									&& sumGZNoreplace > 0) {
								GZzonghexishu += pp.getGZnoReplaceRate()
										/ sumGZNoreplace;
							}
							if (0 != pp.getGZtechnologyRate()
									&& sumGZjineng > 0) {
								GZzonghexishu += pp.getGZtechnologyRate()
										/ sumGZjineng;
							}
							if (0 != pp.getGZzhunbeijiepai() && sumGZjipai > 0) {
								GZzonghexishu += pp.getGZzhunbeijiepai()
										/ sumGZjipai;
							}

							sumGZzonghexishu += GZzonghexishu;
							// 准备综合强度
							float GZzongheqiangdu = 0f;
							if (GZzonghexishu > 0 && pp.getGZzhunbeicishu() > 0) {
								GZzongheqiangdu = GZzonghexishu
										* pp.getGZzhunbeicishu();
							}

							sumGZzongheqiangdu += GZzongheqiangdu;
							pp.setOPzonghezhishu(OPzonghexishu);
							pp.setGZzonghezhishu(GZzonghexishu);
							pp.setOPzongheqiangdu(OPzongheqiangdu);
							pp.setGZzongheqiangdu(GZzongheqiangdu);
							listGongxuDanjia.add(pp);
							totalDao.update(pp);
							bool = true;
						}
					}
				}
				// 工序奖金分配
				if (listGongxuDanjia.size() > 0) {
					for (ProductProcess pgongxu : listGongxuDanjia) {
						// 操作总奖金额
						sumOPzonge = 0f;
						if (sumOPZongheqiangdu > 0) {
							sumOPzonge = sumJiangjin
									* (sumOPZongheqiangdu / (sumOPZongheqiangdu + sumGZzongheqiangdu));
						}

						sumGZzonge = 0f;
						if (sumGZzongheqiangdu > 0) {
							sumGZzonge = sumJiangjin
									* (sumGZzongheqiangdu / (sumOPZongheqiangdu + sumGZzongheqiangdu));
						}

						if (null != pgongxu.getOperatorCode()
								&& !"".equals(pgongxu.getOperatorCode())) {
							// 单工序总额
							float gongxuzonge = 0f;
							if (pgongxu.getOPzonghezhishu() > 0
									&& sumOPzonghexishu > 0) {
								gongxuzonge += sumOPzonge
										* (pgongxu.getOPzonghezhishu() / sumOPzonghexishu);
							}
							if (pgongxu.getGZzonghezhishu() > 0
									&& sumGZzonghexishu > 0) {
								gongxuzonge += +sumGZzonge
										* (pgongxu.getGZzonghezhishu() / sumGZzonghexishu);
							}

							// 单价=============================================
							pgongxu.setPicizonge(gongxuzonge);
							pgongxu.setProcessMomey((double) gongxuzonge
									/ richanliang);
							totalDao.update(pgongxu);
							bool = true;
						}
					}
				}
				// 遍历总成
			}

		}
		return bool;
	}

	/**
	 * 
	 * @param startDate
	 *            试算开始日期
	 * @param endDate
	 *            试算截止日期
	 * @param peoductPriceId
	 *            试算的件号ID集合
	 * @return
	 */
	public boolean shizhijisuan(String startDate, String endDate,
			Integer[] peoductPriceId) {
		if (peoductPriceId.length > 0) {// 选择的总成件号条数存在
			for (int i = 0; i < peoductPriceId.length; i++) {
				// 获取总成
				ProductPrice productPrice = (ProductPrice) totalDao
						.getObjectById(ProductPrice.class, peoductPriceId[i]);
				float baojiaRengong = productPrice.getLaborcost();// 报价直接人工费
				float fenpeiXishu = productPrice.getFenpeiRate();// 分配可调系数
				float richanliang = 0;// 入库量
				String hql = "select sum(goodsStoreCount) from GoodsStore where goodsStoreMarkId='"
						+ productPrice.getMarkId()
						+ "' and goodsStoreWarehouse='成品库' and goodsStoreTime between '"
						+ startDate + "' and '" + endDate + "' ";
				List obj = totalDao.query(hql);
				if (null != obj && null != obj.get(0)) {
					richanliang = (Float) obj.get(0);
					productPrice.setDailyoutput(richanliang);

				}
				// 计算单价
				float price = 0f;
				float circleGongzi = 0f;// 零件周期总工时工资
				Set<SpareParts> sparr = productPrice.getSpareParts();
				// 遍历出工序信息
				for (Iterator<SpareParts> iterator = sparr.iterator(); iterator
						.hasNext();) {
					// System.out.println("==============");
					SpareParts sparts = iterator.next();
					Set<ProductProcess> productProcessArr = sparts
							.getProductProcess();

					for (Iterator<ProductProcess> iteratorP = productProcessArr
							.iterator(); iteratorP.hasNext();) {
						ProductProcess productProcess = iteratorP.next();
						// ===========
						// 计算秒工时工资
						// 查询工资模版
						String codeGroup = productProcess.getOperatorCode();
						codeGroup.replace("；", ";");// 抛出中文字符
						String[] codeArr = codeGroup.split(";");
						float allCodeWage = 0f;
						if (codeArr.length > 0) {// 遍历操作者工号
							for (int ii = 0; ii < codeArr.length; ii++) {
								String code = codeArr[ii];
								// 根据工号查询工作模板表找到该员工工资模版
								String hqlWageStand = "from WageStandard where code='"
										+ code
										+ "' and standardStatus='默认' and processStatus='同意'";
								if (totalDao.query(hqlWageStand).size() > 0) {// 工资模版存在
									WageStandard wageStand = (WageStandard) totalDao
											.query(hqlWageStand).get(0);
									// 计算每月总人工费用,根据员工户口性质查找企业上缴福利系数
									String StringGold = "from InsuranceGold where personClass=? and localOrField=? and cityOrCountryside=?";
									InsuranceGold insuranceGold = (InsuranceGold) totalDao
											.query(
													StringGold,
													wageStand.getPersonClass(),
													wageStand.getLocalOrField(),
													wageStand
															.getCityOrCountryside())
											.get(0);
									// 企业为个人缴纳总额=岗位工资+统筹经+医疗保险+事业保险++工伤+生育保险+公积金
									Float gerenTotalWage = wageStand
											.getGangweigongzi()
											+ wageStand.getSsBase()
											* 100
											* (insuranceGold
													.getQYoldageInsurance()
													+ insuranceGold
															.getQYinjuryInsurance()
													+ insuranceGold
															.getQYmaternityInsurance()
													+ insuranceGold
															.getQYmedicalInsurance() + insuranceGold
													.getQYunemploymentInsurance())
											/ 10000
											+ wageStand.getGjjBase()
											* 100
											* (insuranceGold.getQYhousingFund())
											/ 10000;
									allCodeWage += gerenTotalWage;
								} else {// 工资模版 不存在
									String message = "员工工资未处理";
									// bool=false;
								}
							}
						}
						// 计算单工序人工费用
						if (allCodeWage > 0) {
							double processOnegongxuMoney = (allCodeWage / (21.5 * 8 * 3600));
							// 计算周期工资
							// richanliang
							// productProcess.getOPjiaofu() 交付量

							circleGongzi += processOnegongxuMoney
									* (richanliang
											* (productProcess
													.getOPcaozuojiepai() + productProcess
													.getOPshebeijiepai()) + (productProcess
											.getGZzhunbeijiepai() * productProcess
											.getGZzhunbeicishu()));
						}

						// ==========

					}
				}
				price = (baojiaRengong * fenpeiXishu * richanliang - circleGongzi)
						/ richanliang;
				// System.out.println("==========================================="+price);
				productPrice.setOnePrice((double) price);
				totalDao.update(productPrice);
				// 添加提奖审批
				String markID = productPrice.getMarkId();
				String xingbie = productPrice.getStyle();
				float tijiange = price * richanliang;
				tijiange = (Math.round(tijiange * 100) / 100);
				String tj_month = endDate.substring(0, 7);

				// 判断存在否
				String hqlTijiang = "from Tijiang where tjstyle='" + xingbie
						+ "' and tjmarkId='" + markID + "' and tjtimer='"
						+ tj_month + "'";
				if (totalDao.query(hqlTijiang).size() > 0) {
					Tijiang tijing = (Tijiang) totalDao.query(hqlTijiang)
							.get(0);
					if ("试制奖金".equals(tijing.getTjformat())) {
						// System.out.println("============该月份已经提过将！！");
					} else {
						tijing.setTjmore("审核");
						tijing.setTjformat("试制奖金");
						tijing.setTjmoney(tijiange);
						totalDao.update(tijing);
					}

				} else {
					Tijiang tijing = new Tijiang();
					tijing.setTjmore("审核");
					tijing.setTjformat("试制奖金");
					tijing.setTjcount(richanliang);
					tijing.setTjmoney(tijiange);
					tijing.setTjtimer(tj_month);
					tijing.setTjmonth(tj_month);
					tijing.setTjmarkId(markID);
					tijing.setTjstyle(xingbie);
					totalDao.save(tijing);
				}

			}// 计算单个件号提奖

		}
		return true;
	}

	public String packageProduct(Integer id, Double mentioningAwardPrice) {
		ProductPrice pp = (ProductPrice) totalDao.getObjectById(
				ProductPrice.class, id);
		DataGrid dg = new DataGrid();
		VOProductTree data = new VOProductTree(pp.getId(), pp.getMarkId(), pp
				.getGoodsName(), pp.getDailyoutput().doubleValue(), pp
				.getStyle(), pp.getCarStyle(), mentioningAwardPrice, pp.getId());
		dg.getRows().add(data);
		dg.setTotal(new Long(1));
		String resultStr = JSON.toJSONString(dg);
		return resultStr;
	}

	public String packageData(Integer id, Map map) {
		int count = 1;
		DataGrid dg = new DataGrid();
		int length = 1;
		ProductPrice pp = (ProductPrice) totalDao.getObjectById(
				ProductPrice.class, id);
		length += pp.getSpareParts().size();
		for (SpareParts sp : pp.getSpareParts()) {
			VOProductTree part = new VOProductTree(count++, sp.getSpmarkId(),
					sp.getSpgoodsName(), sp.getAllWorkHours().doubleValue(),
					null, sp.getId());
			length += sp.getProductProcess().size();
			List<ProductProcess> list = findProductProcessById(sp.getId());
			if (list != null && list.size() > 0) {
				for (ProductProcess proP : list) {
					Double OPLabourBeat = 0.0; // 人工节拍
					Double OPEquipmentBeat = 0.0; // 设备节拍
					Double PRLabourBeat = 0.0; // 人工节拍
					Double PRPrepareIndex = 0.0; // 准备次数
					if (proP.getOPcaozuojiepai() != null)
						OPLabourBeat = proP.getOPcaozuojiepai().doubleValue();
					if (proP.getOPshebeijiepai() != null)
						OPEquipmentBeat = proP.getOPshebeijiepai()
								.doubleValue();
					if (proP.getGZzhunbeijiepai() != null)
						PRLabourBeat = proP.getGZzhunbeijiepai().doubleValue();
					if (proP.getGZzhunbeicishu() != null)
						PRPrepareIndex = proP.getGZzhunbeicishu().doubleValue();
					VOProductTree process = null;
					if (map != null && map.size() > 0) {
						DTOProcess dto = (DTOProcess) map.get(proP.getId());
						process = new VOProductTree(count++, proP
								.getProcessName(), proP.getProcessNo(), proP
								.getShebeiNo(), dto.getOPLabourBeat(), dto
								.getOPEquipmentBeat(), dto.getPRLabourBeat(),
								dto.getPRPrepareTIme(), dto.getHandlers(), proP
										.getProcessMomey(), part.getId(), proP
										.getId(), "PR", dto.getSumMoney(), dto
										.getUnitPrice(), dto.getJobNum());
					} else {
						process = new VOProductTree(count++, proP
								.getProcessName(), proP.getProcessNo(), proP
								.getShebeiNo(), OPLabourBeat, OPEquipmentBeat,
								PRLabourBeat, PRPrepareIndex, proP
										.getOperatorName(), proP
										.getProcessMomey(), part.getId(), proP
										.getId(), "PR", null, null, proP
										.getOperatorCode());
					}
					part.getChildren().add(process);
				}
			}
			dg.getRows().add(part);
		}
		dg.setTotal(new Long(length));
		String jsonStr = JSON.toJSON(dg).toString();
		// System.out.println(jsonStr);
		return jsonStr;
	}

	/**
	 * @Title: trial
	 * @Description: 试算数据
	 * @param id
	 *            成品ID
	 * @return String 前台数据
	 * @throws
	 */
	public String trial(Integer id) {
		Map map = trialMentioningAwardPrice(id);
		String url = null;
		if (map != null && map.size() > 0)
			url = packageData(id, map);
		return url;
	}

	/**
	 * @Title: trialMentioningAwardPrice
	 * @Description: 试算
	 * @param id
	 * @return Map
	 * @throws
	 */
	public Map trialMentioningAwardPrice(Integer id) {
		Map session = ActionContext.getContext().getSession();
		ProductPrice productPrice = getProductPrice(id); // 查询成品,根据ID
		List<SpareParts> subassemblyLis = findSparePartsById(id); // 根据成品ID查询组件
		/**
		 * 遍历所有组件
		 */
		Double allProcessWages = 0.0; // 此组件工序工资
		Double allOPSynthesizeStrength = 0.0; // 操作过程综合强度
		Double allPRSynthesizeStrength = 0.0; // 准备过程综合强度

		Double allOPSynthesizeCoefficient = 0.0; // 操作过程综合系数(add)
		Double allPRSynthesizeCoefficient = 0.0; // 操作过程综合系数(add)

		/**
		 * 操作过程
		 */
		Double OPSkillIndex = 0.0; // 操作技能指数
		Double OPNotReplaceCoefficient = 0.0; // 不可替换系数
		Double OPLoadIndex = 0.0; // 负荷指数
		Double OPLabourBeat = 0.0; // 人工节拍
		/**
		 * 准备过程
		 */
		Double PRSkillIndex = 0.0;// 技能指数
		Double PRNotReplaceCoefficient = 0.0; // 不可替换系数
		Double PRLoadIndex = 0.0; // 负荷指数
		Double PRLabourBeat = 0.0; // 人工节拍
		Map map = new HashMap(); // 存储数据
		/**
		 * 遍历算 所有工序工资 求出技能指数 、可替换人数、负荷指数、人工节拍各总和
		 */
		for (SpareParts subassembly : subassemblyLis) {
			List<ProductProcess> processLis = findProductProcessById(subassembly
					.getId()); // 此组件需要的工序
			/**
			 * 遍历一个组件所需要的工序
			 */
			for (ProductProcess productProcess : processLis) {
				DTOProcess dto = (DTOProcess) session.get(productProcess
						.getId()); // 从Session中取出存储的前台填写数据
				String jobNumStr = null;
				if (dto != null) {
					jobNumStr = dto.getJobNum(); // 获取完成此工序的人员工号
				} else {
					jobNumStr = productProcess.getOperatorCode(); // 获取完成此工序的人员工号
				}
				if (jobNumStr == null || jobNumStr.equals(""))
					continue;
				String[] allJobNum = jobNumStr.split(";");
				Double workingHoursWages = 0.0; // 工序工时工资
				for (String jobNum : allJobNum) { // 统计工序中基本工时工资
					WageStandard wageStandard = wss.findWSByUser(jobNum); // 根据工号查询工资模板
					InsuranceGold insuranceGold = igs.findInsuranceGoldBylc(
							wageStandard.getLocalOrField(), wageStandard
									.getCityOrCountryside(), wageStandard
									.getPersonClass()); // 福利系数
					workingHoursWages = workingHoursWages
							+ (wageStandard.getGangweigongzi()
									+ wageStandard.getSsBase()
									* (insuranceGold.getQYoldageInsurance()
											+ insuranceGold
													.getQYmedicalInsurance()
											+ insuranceGold
													.getQYunemploymentInsurance()
											+ insuranceGold
													.getQYinjuryInsurance() + insuranceGold
											.getQYmaternityInsurance()) / 100 + wageStandard
									.getGjjBase()
									* insuranceGold.getQYhousingFund() / 100);
				}
				Double basicWorkingHoursWages = workingHoursWages / SECONDS; // 工序中基本工时工资
				Double processWages = 0.0;
				if (dto != null)
					processWages = basicWorkingHoursWages
							* ((dto.getOPLabourBeat() + dto
									.getOPEquipmentBeat())
									* productPrice.getDailyoutput() + (dto
									.getPRLabourBeat() * dto.getPRPrepareTIme())); // 基本工时工资(单个工序工资)
				else
					processWages = basicWorkingHoursWages
							* ((productProcess.getOPcaozuojiepai() + productProcess
									.getOPshebeijiepai())
									* productPrice.getDailyoutput() + (productProcess
									.getGZzhunbeijiepai() * productProcess
									.getGZzhunbeicishu())); // 基本工时工资(单个工序工资)
				allProcessWages = allProcessWages + processWages; // 所有工序工资

				/**
				 * 操作过程统计
				 */
				if (productProcess.getOPtechnologyRate() != null)
					OPSkillIndex += productProcess.getOPtechnologyRate(); // 统计技能指数
				if (productProcess.getOPnoReplaceRate() != null)
					OPNotReplaceCoefficient += productProcess
							.getOPnoReplaceRate(); // 统计不可替换系数
				if (productProcess.getOPfuheRate() != null)
					OPLoadIndex += productProcess.getOPfuheRate(); // 统计负荷指数
				if (dto != null) {
					if (dto.getOPLabourBeat() != null)
						OPLabourBeat += dto.getOPLabourBeat(); // 统计人工节拍 前台替换
				} else {
					if (productProcess.getOPcaozuojiepai() != null)
						OPLabourBeat += productProcess.getOPcaozuojiepai(); // 统计人工节拍
				}
				/**
				 * 准备过程统计
				 */
				if (productProcess.getGZtechnologyRate() != null)
					PRSkillIndex += productProcess.getGZtechnologyRate(); // 统计技能指数
				if (productProcess.getGZnoReplaceRate() != null)
					PRNotReplaceCoefficient += productProcess
							.getGZnoReplaceRate(); // 统计不可替换系数
				if (productProcess.getGZfuheRate() != null)
					PRLoadIndex += productProcess.getGZfuheRate(); // 统计负荷指数
				if (dto != null) {
					if (dto.getOPLabourBeat() != null)
						PRLabourBeat += dto.getPRLabourBeat(); // 统计人工节拍
				} else {
					if (productProcess.getGZzhunbeijiepai() != null)
						PRLabourBeat += productProcess.getGZzhunbeijiepai(); // 统计人工节拍
				}
			}
		}
		/**
		 * 遍历算出综合强度
		 */
		for (SpareParts subassembly : subassemblyLis) {
			List<ProductProcess> processLis = findProductProcessById(subassembly
					.getId()); // 此组件需要的工序
			/**
			 * 遍历一个组件所需要的工序
			 */
			for (ProductProcess productProcess : processLis) {
				if (productProcess.getOPjiaofu() == null)
					continue;
				DTOProcess dto = (DTOProcess) session.get(productProcess
						.getId());
				Double dtoOPla = 0.0;// 操作人工节拍
				Double dtoPRla = 0.0;// 准备过程人工节拍
				if (dto != null) {
					if (dto.getOPLabourBeat() != null)
						dtoOPla = dto.getOPLabourBeat();
					if (dto.getPRLabourBeat() != null)
						dtoPRla = dto.getPRLabourBeat();
				} else {
					if (productProcess.getOPcaozuojiepai() != null)
						dtoOPla = productProcess.getOPcaozuojiepai()
								.doubleValue();
					if (productProcess.getGZzhunbeijiepai() != null)
						dtoPRla = productProcess.getGZzhunbeijiepai()
								.doubleValue();
				}
				Double OPcannot = 0.0;// 操作不可替换系数
				Double PRcannot = 0.0;// 准备过程不可替换系数
				if (productProcess.getOPnoReplaceRate() != null)
					OPcannot = productProcess.getOPnoReplaceRate()
							.doubleValue();
				if (productProcess.getGZnoReplaceRate() != null)
					PRcannot = productProcess.getGZnoReplaceRate()
							.doubleValue();
				// 操作过程 综合系数
				Double OPSynthesizeCoefficient = ConvertNumber.isNum(
						productProcess.getOPtechnologyRate().doubleValue(),
						OPSkillIndex)
						+ ConvertNumber
								.isNum(OPcannot, OPNotReplaceCoefficient)
						+ ConvertNumber.isNum(productProcess.getOPfuheRate()
								.doubleValue(), OPLoadIndex)
						+ ConvertNumber.isNum(dtoOPla, OPLabourBeat);
				// 统计操作综合系数
				allOPSynthesizeCoefficient += OPSynthesizeCoefficient;
				// 准备过程 综合系数
				Double PRSynthesizeCoefficient = ConvertNumber.isNum(
						productProcess.getGZtechnologyRate().doubleValue(),
						PRSkillIndex)
						+ ConvertNumber
								.isNum(PRcannot, PRNotReplaceCoefficient)
						+ ConvertNumber.isNum(productProcess.getGZfuheRate()
								.doubleValue(), PRLoadIndex)
						+ ConvertNumber.isNum(dtoPRla, PRLabourBeat);
				// 统计准备综合系数
				allPRSynthesizeCoefficient += PRSynthesizeCoefficient;
				// 操作过程 综合强度 = 综合系数 * 交付数量
				Double OPsynthesizeStrength = OPSynthesizeCoefficient
						* productProcess.getOPjiaofu();
				allOPSynthesizeStrength += OPsynthesizeStrength; // 统计单个零件操作过程所有工序强度
				// 准备过程 综合强度 = 综合系数 * 准备次数
				Double PRSynthesizeStrength = 0.0;
				if (dto != null) {
					PRSynthesizeStrength = PRSynthesizeCoefficient
							* dto.getPRPrepareTIme();
				} else {
					PRSynthesizeStrength = PRSynthesizeCoefficient
							* productProcess.getGZzhunbeicishu();
				}
				allPRSynthesizeStrength += PRSynthesizeStrength; // 统计单个零件准备过程所有工序强度
			}
		}

		Double mentioningAwardPrice = (productPrice.getDailyoutput()
				* productPrice.getLaborcost() * productPrice.getFenpeiRate() - allProcessWages)
				/ productPrice.getDailyoutput(); // ((日产量 * 报价费用 * 可调系数) -
		// 所有工序周期工资) / 日产量=提奖价
		Double distributeBonus = mentioningAwardPrice
				* productPrice.getDailyoutput(); // 可分配奖金(元/月) = 累计入库量件/月 *
		// 单件计价奖金
		session.put("mentioningAwardPrice", mentioningAwardPrice);

		/**
		 * 操作过程
		 */
		Double OPdistributeProportion = allOPSynthesizeStrength
				/ (allOPSynthesizeStrength + allPRSynthesizeStrength); // 分配比例 =
		// sum(综合强度)
		// /
		// (sum(操作综合强度)
		// +
		// sum(准备综合强度))
		Double OPdistributeTotal = distributeBonus * OPdistributeProportion; // 分配总额
		// =
		// 可分配奖金(元/月)
		// *
		// 分配比例
		/**
		 * 准备过程
		 */
		Double PRdistributeProportion = allPRSynthesizeStrength
				/ (allOPSynthesizeStrength + allPRSynthesizeStrength); // 分配比例 =
		// sum(综合强度)
		// /
		// (sum(操作综合强度)
		// +
		// sum(准备综合强度))
		Double PRdistributeTotal = distributeBonus * PRdistributeProportion; // 分配总额
		// =
		// 可分配奖金(元/月)
		// *
		// 分配比例

		/**
		 * 循环取出各个工序该分配的金额
		 */
		for (SpareParts subassembly : subassemblyLis) {
			List<ProductProcess> processLis = findProductProcessById(subassembly
					.getId()); // 此组件需要的工序

			/**
			 * 遍历一个组件所需要的工序
			 */
			for (ProductProcess productProcess : processLis) {
				DTOProcess newDto = null;
				if (productProcess.getOPjiaofu() == null
						|| productProcess.getOPjiaofu().equals("")) {
					Double newOPLabourBeat = 0.0;
					Double newOPEquipmentBeat = 0.0;
					Double newPRLabourBeat = 0.0;
					Double newPRPrepareTIme = 0.0;
					if (productProcess.getOPcaozuojiepai() != null)
						newOPLabourBeat = productProcess.getOPcaozuojiepai()
								.doubleValue();
					if (productProcess.getOPshebeijiepai() != null)
						newOPEquipmentBeat = productProcess.getOPshebeijiepai()
								.doubleValue();
					if (productProcess.getGZzhunbeijiepai() != null)
						newPRLabourBeat = productProcess.getGZzhunbeijiepai()
								.doubleValue();
					if (productProcess.getGZzhunbeicishu() != null)
						newPRPrepareTIme = productProcess.getGZzhunbeicishu()
								.doubleValue();
					newDto = new DTOProcess(productProcess.getId(), null,
							newOPLabourBeat, newOPEquipmentBeat,
							newPRLabourBeat, newPRPrepareTIme, null, 0.0, 0.0);
					map.put(productProcess.getId(), newDto);
					continue;
				}
				DTOProcess dto = (DTOProcess) session.get(productProcess
						.getId());// 从前台获取保存的工序对象数据
				Double dtoOPla = 0.0;
				Double dtoPRla = 0.0;
				if (dto != null) {
					if (dto.getOPLabourBeat() != null)
						dtoOPla = dto.getOPLabourBeat();
					if (dto.getPRLabourBeat() != null)
						dtoPRla = dto.getPRLabourBeat();
				} else {
					if (productProcess.getOPcaozuojiepai() != null)
						dtoOPla = productProcess.getOPcaozuojiepai()
								.doubleValue();
					if (productProcess.getGZzhunbeijiepai() != null)
						dtoPRla = productProcess.getGZzhunbeijiepai()
								.doubleValue();
				}
				Double OPcannot = 0.0; // 不可替换系数
				Double PRcannot = 0.0;
				if (productProcess.getOPnoReplaceRate() != null)
					OPcannot = productProcess.getOPnoReplaceRate()
							.doubleValue();
				if (productProcess.getGZnoReplaceRate() != null)
					PRcannot = productProcess.getGZnoReplaceRate()
							.doubleValue();
				// 操作过程 综合系数
				Double OPSynthesizeCoefficient = ConvertNumber.isNum(
						productProcess.getOPtechnologyRate().doubleValue(),
						OPSkillIndex)
						+ ConvertNumber
								.isNum(OPcannot, OPNotReplaceCoefficient)
						+ ConvertNumber.isNum(productProcess.getOPfuheRate()
								.doubleValue(), OPLoadIndex)
						+ ConvertNumber.isNum(dtoOPla, OPLabourBeat);
				// 准备过程 综合系数
				Double PRSynthesizeCoefficient = ConvertNumber.isNum(
						productProcess.getGZtechnologyRate().doubleValue(),
						PRSkillIndex)
						+ ConvertNumber
								.isNum(PRcannot, PRNotReplaceCoefficient)
						+ ConvertNumber.isNum(productProcess.getGZfuheRate()
								.doubleValue(), PRLoadIndex)
						+ ConvertNumber.isNum(dtoPRla, PRLabourBeat);
				// Double OPMoney = OPdistributeTotal * OPSynthesizeCoefficient
				// / OPdistributeProportion; //操作过程 工序该分配金额
				// Double PRMoney = PRdistributeTotal * PRSynthesizeCoefficient
				// / PRdistributeProportion; //准备过程 工序该分配金额
				Double OPMoney = OPdistributeTotal * OPSynthesizeCoefficient
						/ allOPSynthesizeCoefficient; // 操作过程 工序该分配金额 = 操作分配总额 *
				// 综合系数 / sum(综合系数)
				Double PRMoney = PRdistributeTotal * PRSynthesizeCoefficient
						/ allPRSynthesizeCoefficient; // 准备过程 工序该分配金额 = 准备分配总额 *
				// 综合指数 / sum(综合指数)
				Double sumMoney = OPMoney + PRMoney; // 单工序分配总额
				Double unitPrice = sumMoney / productProcess.getOPjiaofu(); // 每个工序分配金额
				// =
				// 分配总额
				// /
				// 交付数量
				if (dto != null) {
					newDto = new DTOProcess(productProcess.getId(), dto
							.getJobNum(), dto.getOPLabourBeat(), dto
							.getOPEquipmentBeat(), dto.getPRLabourBeat(), dto
							.getPRPrepareTIme(), dto.getHandlers(), sumMoney,
							unitPrice);
				} else {
					newDto = new DTOProcess(productProcess.getId(),
							productProcess.getOperatorCode(), productProcess
									.getOPcaozuojiepai().doubleValue(),
							productProcess.getOPshebeijiepai().doubleValue(),
							productProcess.getGZzhunbeijiepai().doubleValue(),
							productProcess.getGZzhunbeicishu().doubleValue(),
							productProcess.getOperatorName(), sumMoney,
							unitPrice);
				}
				map.put(productProcess.getId(), newDto);
				if (dto != null)
					session.remove(productProcess.getId());
			}
		}
		return map;
	}

	public Double getBonus(Integer id) {
		Map session = ActionContext.getContext().getSession();
		ProductPrice productPrice = getProductPrice(id); // 查询成品,根据ID
		List<SpareParts> subassemblyLis = findSparePartsById(id); // 根据成品ID查询组件
		/**
		 * 遍历所有组件
		 */
		Double allProcessWages = 0.0; // 此组件工序工资
		/**
		 * 遍历算 所有工序工资 求出技能指数 、可替换人数、负荷指数、人工节拍各总和
		 */
		for (SpareParts subassembly : subassemblyLis) {
			List<ProductProcess> processLis = findProductProcessById(subassembly
					.getId()); // 此组件需要的工序
			/**
			 * 遍历一个组件所需要的工序
			 */
			for (ProductProcess productProcess : processLis) {
				DTOProcess dto = (DTOProcess) session.get(productProcess
						.getId()); // 从Session中取出存储的前台填写数据
				String jobNumStr = null;
				if (dto != null) {
					jobNumStr = dto.getJobNum(); // 获取完成此工序的人员工号
				} else {
					jobNumStr = productProcess.getOperatorCode(); // 获取完成此工序的人员工号
				}
				if (jobNumStr == null || jobNumStr.equals(""))
					continue;
				String[] allJobNum = jobNumStr.split(";");
				Double workingHoursWages = 0.0; // 工序工时工资
				for (String jobNum : allJobNum) { // 统计工序中基本公时工资
					WageStandard wageStandard = wss.findWSByUser(jobNum); // 根据工号查询工资模板
					InsuranceGold insuranceGold = igs.findInsuranceGoldBylc(
							wageStandard.getLocalOrField(), wageStandard
									.getCityOrCountryside(), wageStandard
									.getPersonClass()); // 福利系数
					workingHoursWages = workingHoursWages
							+ (wageStandard.getGangweigongzi()
									+ wageStandard.getSsBase()
									* (insuranceGold.getQYoldageInsurance()
											+ insuranceGold
													.getQYmedicalInsurance()
											+ insuranceGold
													.getQYunemploymentInsurance()
											+ insuranceGold
													.getQYinjuryInsurance() + insuranceGold
											.getQYmaternityInsurance()) / 100 + wageStandard
									.getGjjBase()
									* insuranceGold.getQYhousingFund() / 100);
				}
				Double basicWorkingHoursWages = workingHoursWages / SECONDS; // 工序中基本工时工资
				Double processWages = 0.0;
				if (dto != null)
					processWages = basicWorkingHoursWages
							* ((dto.getOPLabourBeat() + dto
									.getOPEquipmentBeat())
									* productPrice.getDailyoutput() + (dto
									.getPRLabourBeat() * dto.getPRPrepareTIme())); // 基本工时工资
				else
					processWages = basicWorkingHoursWages
							* ((productProcess.getOPcaozuojiepai() + productProcess
									.getOPshebeijiepai())
									* productPrice.getDailyoutput() + (productProcess
									.getGZzhunbeijiepai() * productProcess
									.getGZzhunbeicishu())); // 基本工时工资
				allProcessWages = allProcessWages + processWages; // 所有工序工资
			}
		}
		Double mentioningAwardPrice = (productPrice.getDailyoutput()
				* productPrice.getLaborcost() * productPrice.getFenpeiRate() - allProcessWages)
				/ productPrice.getDailyoutput(); // ((日产量 * 报价费用 * 可调系数) -
		// 所有工序周期工资) / 日产量 .. 提奖价
		return mentioningAwardPrice;
	}

	// 通过件号查询总成
	public ProductPrice findProductPriceByMkId(String mkId) {
		if (mkId != null && mkId.length() > 0) {
			String hql = "select id from ta_sop_tj_ProductPrice where ProductPrice_markId=? and ProductPrice_goodsname not like '%试制%'";
			List list = totalDao.createQuerySelect(null, hql, mkId);
			if (list != null && list.size() > 0) {
				ProductPrice pPrice = new ProductPrice();
				pPrice.setId(Integer.parseInt(list.get(0).toString()));
				return pPrice;
			}
		}
		return null;
	}

	public WageStandardServer getWss() {
		return wss;
	}

	public void setWss(WageStandardServer wss) {
		this.wss = wss;
	}

	public InsuranceGoldServer getIgs() {
		return igs;
	}

	public void setIgs(InsuranceGoldServer igs) {
		this.igs = igs;
	}

}
