package com.task.entity.sop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.util.Util;
/**
 * 设变修改内容 (ta_sop_w_ProcardTemplatesbDifference)
 * @author txb
 *
 */
public class ProcardTemplatesbDifference  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer sbApplyId;//
	private String sbNumber;//
	private String markId;//主题零件件号
	private String dataSource;//数据来源(原有,新增)
	private String ywmarkId;//
	private String rootMarkId;//
	
	private List<ProcardTemplatesbDifferenceDetail> ptsbddList;//设变修改内容明细
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSbApplyId() {
		return sbApplyId;
	}
	public void setSbApplyId(Integer sbApplyId) {
		this.sbApplyId = sbApplyId;
	}
	public String getSbNumber() {
		return sbNumber;
	}
	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getYwmarkId() {
		return ywmarkId;
	}
	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}
	public String getRootMarkId() {
		return rootMarkId;
	}
	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}
	public List<ProcardTemplatesbDifferenceDetail> getPtsbddList() {
		return ptsbddList;
	}
	public void setPtsbddList(List<ProcardTemplatesbDifferenceDetail> ptsbddList) {
		this.ptsbddList = ptsbddList;
	}
	
	public static String addProcardTemplatesbDifference(TotalDao totalDao,ProcardTemplatesb ptsb,Object obj,String dataType,String opType,Float scyyongliang,Float scnyongliang,ProcardTemplateBanBen ptbb){
		ProcardTemplatesbDifference old = (ProcardTemplatesbDifference) totalDao.getObjectByCondition("from ProcardTemplatesbDifference where sbApplyId =? and markId=? ", ptsb.getSbApplyId(),ptsb.getMarkId());
		boolean had=false;
		if(old==null){
			old = new ProcardTemplatesbDifference();
			old.setSbApplyId(ptsb.getSbApplyId());//
			old.setSbNumber(ptsb.getSbNumber());//
			old.setMarkId(ptsb.getMarkId());//主题零件件号
			Float hadcount = (Float) totalDao.getObjectByCondition("select count(*) from ProcardTemplate where markId=? and  (dataStatus is null or dataStatus!='删除')", ptsb.getMarkId()); 
			if(hadcount==null||hadcount==0){
				old.setDataSource("新增");//数据来源(原有,新增)
			}else {
				old.setDataSource("原有");//数据来源(原有,新增)
			}
			old.setYwmarkId(ptsb.getYwMarkId());//
			old.setRootMarkId(ptsb.getRootMarkId());//
			totalDao.save(old);
		}else{
			had =true;
		}
		if(dataType.equals("子件")){
			//看看是不是已经有下层的增加记录了
			ProcardTemplatesb son = (ProcardTemplatesb)obj;
			ProcardTemplatesbDifferenceDetail psbdDetail =null;
			if(had){
				psbdDetail=(ProcardTemplatesbDifferenceDetail) totalDao.getObjectByCondition("from ProcardTemplatesbDifferenceDetail where markId=? and ptsbdId=? and datatype='子件'",son.getMarkId(), old.getId());
			}
			if(psbdDetail==null){
				psbdDetail = new  ProcardTemplatesbDifferenceDetail();
				psbdDetail.setZbMarkId(ptsb.getMarkId());//主表件号
				psbdDetail.setDatatype(dataType);//数据类型(本身，下层，工序，图纸)
				psbdDetail.setOptype(opType);//操作类型
				psbdDetail.setMarkId(son.getMarkId());//子件件号
				psbdDetail.setDataId(son.getId());//数据id
				psbdDetail.setProname(son.getProName());//名称
				psbdDetail.setBanbenNumber(son.getBanBenNumber());
				psbdDetail.setBanci(son.getBanci());
//				psbdDetail.setSxName(sxName);//变值属性名称
//				psbdDetail.setoldData;//原值
//				psbdDetail.setnewData;//新值
//				//文件数据
//				psbdDetail.setfileName;//
//				psbdDetail.setoldfileName;//
				psbdDetail.setPtsbdId(old.getId());//主表Id (ProcardTemplatesbDifference)
				totalDao.save(psbdDetail);
			}
			//子件添加的涉及零件记录
			Float yl = null;
			yl = scnyongliang;
			if(son.getProcardStyle().equals("外购")){
				try {
					yl = scnyongliang*Util.Floatdiv(son.getQuanzi2(), son.getQuanzi1(),4);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(son.getMarkId()+"用量异常!");
				}
			}else{
				yl = scnyongliang*son.getCorrCount();
			}
			ProcardTemplateAboutsb ptasb1= (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and markId=? ",ptbb.getProcardTemplateBanBenApply().getId(),son.getMarkId());
			if(ptasb1==null){
				//生成设变涉及相关零件主表
				ptasb1 = new ProcardTemplateAboutsb();
				ptasb1.setSbApplyId(ptbb.getProcardTemplateBanBenApply().getId());//设变单id
				ptasb1.setSbNumber(ptbb.getProcardTemplateBanBenApply().getSbNumber());//设变单号
				ptasb1.setProcardStyle(son.getProcardStyle());//生产类型
				ptasb1.setProductStyle(son.getProductStyle());//生产类型
				ptasb1.setMarkId(son.getMarkId());//件号
				ptasb1.setProName(son.getProName());//名称
				ptasb1.setBanben(son.getBanBenNumber());//版本
				ptasb1.setBanci(son.getBanci());
				ptasb1.setWgType(son.getWgType());//物料类别
				ptasb1.setKgliao(son.getKgliao());//客供属性
				ptasb1.setSpecification(son.getSpecification());//规格
				ptasb1.setUnit(son.getUnit());// 单位(自制)
				ptasb1.setTuhao(son.getTuhao());//图号
				ptasb1.setYongliao(yl);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
				totalDao.save(ptasb1);
			}else{
				ptasb1.setYongliao(Util.Floatadd(ptasb1.getYongliao(), yl));//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
//				ptasbd1 = totalDao.getObjectByCondition("", agr)
			}
			//生成设变相关零件子表
			
			ProcardTemplateAboutsbDetail ptasbd1 =  new ProcardTemplateAboutsbDetail();
			ptasbd1.setSbptId(ptbb.getId());//设变明细Id(表明是由于哪个设变零件到导致的)
			ptasbd1.setSbptMarkId(ptbb.getMarkId());//设变明细件号
			if(son.getProcardTemplatesb()!=null){
				ptasbd1.setScmarkId(son.getProcardTemplatesb().getMarkId());//上层零件件号
			}
			ptasbd1.setProcardStyle(son.getProcardStyle());//生产类型
			ptasbd1.setProductStyle(son.getProductStyle());//生产类型
			ptasbd1.setMarkId(son.getMarkId());//件号
			ptasbd1.setProName(son.getProName());//名称
			ptasbd1.setBanben(son.getBanBenNumber());//版本
			ptasbd1.setBanci(son.getBanci());
			ptasbd1.setWgType(son.getWgType());//物料类别
			ptasbd1.setKgliao(son.getKgliao());//客供属性
			ptasbd1.setSpecification(son.getSpecification());//规格
			ptasbd1.setUnit(son.getUnit());// 单位(自制)
			ptasbd1.setTuhao(son.getTuhao());//图号
			ptasbd1.setYongliao(yl);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
			ptasbd1.setPtasbId(ptasb1.getId());
			totalDao.save(ptasbd1);
			if(!son.getProcardStyle().equals("外购")){//下层零件生成涉及零件记录
				Set<ProcardTemplatesb> thissonDownSet = son.getProcardsbTSet();
				if(thissonDownSet!=null&&thissonDownSet.size()>0){
					for(ProcardTemplatesb thissonDown:thissonDownSet){
						Float yl2 = yl;
						if(thissonDown.getProcardStyle().equals("外购")){
							try {
								yl2 = yl*Util.Floatdiv(thissonDown.getQuanzi2(), thissonDown.getQuanzi1(),4);
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new RuntimeException(thissonDown.getMarkId()+"用量有误!");
							}
						}else{
							yl2 = yl*thissonDown.getCorrCount();
						}
						addsbxglj(thissonDown,ptbb,yl2,totalDao);
					}
				}
			}
			
		}
		if(dataType.equals("工序")){
			
		}
		if(dataType.equals("图纸")){
			if(opType.equals("添加")){
				ProcessTemplateFilesb filesb = (ProcessTemplateFilesb)obj;
				ProcardTemplatesbDifferenceDetail psbdDetail = null;
				if(had){
					psbdDetail=(ProcardTemplatesbDifferenceDetail) totalDao.getObjectByCondition("from ProcardTemplatesbDifferenceDetail where fileName=? and ptsbdId=?",filesb.getFileName(), old.getId());
				}
				if(psbdDetail==null){
					psbdDetail = new ProcardTemplatesbDifferenceDetail();
					psbdDetail.setZbMarkId(ptsb.getMarkId());//主表件号
					psbdDetail.setDatatype(dataType);//数据类型(本身，下层，工序，图纸)
					psbdDetail.setOptype(opType);//操作类型
//				psbdDetail.setMarkId(son.getMarkId());//子件件号
//				psbdDetail.setDataId(son.getId());//数据id
//				psbdDetail.setProname(son.getProName());//名称
					psbdDetail.setBanbenNumber(filesb.getBanBenNumber());
					psbdDetail.setBanci(filesb.getBanci());
//				psbdDetail.setSxName(sxName);//变值属性名称
//				psbdDetail.setoldData;//原值
//				psbdDetail.setnewData;//新值
//				//文件数据
					psbdDetail.setMonth(filesb.getMonth());
					psbdDetail.setFileName(filesb.getFileName());//
					psbdDetail.setOldfileName(filesb.getOldfileName());//
					psbdDetail.setPtsbdId(old.getId());//主表Id (ProcardTemplatesbDifference)
					totalDao.save(psbdDetail);
				}
			}else{
				ProcessTemplateFile ptfile = (ProcessTemplateFile)obj;
				ProcardTemplatesbDifferenceDetail psbdDetail = null;
				if(had){
					psbdDetail=(ProcardTemplatesbDifferenceDetail) totalDao.getObjectByCondition("from ProcardTemplatesbDifferenceDetail where fileName=? and ptsbdId=?",ptfile.getFileName(), old.getId());
				}
				if(psbdDetail==null){
					psbdDetail = new ProcardTemplatesbDifferenceDetail();
					psbdDetail.setZbMarkId(ptsb.getMarkId());//主表件号
					psbdDetail.setDatatype(dataType);//数据类型(本身，下层，工序，图纸)
					psbdDetail.setOptype(opType);//操作类型
//				psbdDetail.setMarkId(son.getMarkId());//子件件号
//				psbdDetail.setDataId(son.getId());//数据id
//				psbdDetail.setProname(son.getProName());//名称
					psbdDetail.setBanbenNumber(ptfile.getBanBenNumber());
					psbdDetail.setBanci(ptfile.getBanci());
//				psbdDetail.setSxName(sxName);//变值属性名称
//				psbdDetail.setoldData;//原值
//				psbdDetail.setnewData;//新值
//				//文件数据
					psbdDetail.setMonth(ptfile.getMonth());
					psbdDetail.setFileName(ptfile.getFileName());//
					psbdDetail.setOldfileName(ptfile.getOldfileName());//
					psbdDetail.setPtsbdId(old.getId());//主表Id (ProcardTemplatesbDifference)
					totalDao.save(psbdDetail);
				}
			}
		}
		
		return null;
	}
	private static void addsbxglj(ProcardTemplatesb son,ProcardTemplateBanBen ptbb,Float thisyl,TotalDao totalDao) {
		// TODO Auto-generated method stub
		ProcardTemplateAboutsb ptasb1= (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and markId=? ",ptbb.getProcardTemplateBanBenApply().getId(),son.getMarkId());
		if(ptasb1==null){
			//生成设变涉及相关零件主表
			ptasb1 = new ProcardTemplateAboutsb();
			ptasb1.setSbApplyId(ptbb.getProcardTemplateBanBenApply().getId());//设变单id
			ptasb1.setSbNumber(ptbb.getProcardTemplateBanBenApply().getSbNumber());//设变单号
			ptasb1.setProcardStyle(son.getProcardStyle());//生产类型
			ptasb1.setProductStyle(son.getProductStyle());//生产类型
			ptasb1.setMarkId(son.getMarkId());//件号
			ptasb1.setProName(son.getProName());//名称
			ptasb1.setBanben(son.getBanBenNumber());//版本
			ptasb1.setBanci(son.getBanci());
			ptasb1.setWgType(son.getWgType());//物料类别
			ptasb1.setKgliao(son.getKgliao());//客供属性
			ptasb1.setSpecification(son.getSpecification());//规格
			ptasb1.setUnit(son.getUnit());// 单位(自制)
			ptasb1.setTuhao(son.getTuhao());//图号
			ptasb1.setYongliao(thisyl);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
			totalDao.save(ptasb1);
		}else{
			ptasb1.setYongliao(Util.Floatadd(ptasb1.getYongliao(), thisyl));//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
//			ptasbd1 = totalDao.getObjectByCondition("", agr)
		}
		//生成设变相关零件子表
		
		ProcardTemplateAboutsbDetail ptasbd1 =  new ProcardTemplateAboutsbDetail();
		ptasbd1.setSbptId(ptbb.getId());//设变明细Id(表明是由于哪个设变零件到导致的)
		ptasbd1.setSbptMarkId(ptbb.getMarkId());//设变明细件号
		if(son.getProcardTemplatesb()!=null){
			ptasbd1.setScmarkId(son.getProcardTemplatesb().getMarkId());//上层零件件号
		}
		ptasbd1.setProcardStyle(son.getProcardStyle());//生产类型
		ptasbd1.setProductStyle(son.getProductStyle());//生产类型
		ptasbd1.setMarkId(son.getMarkId());//件号
		ptasbd1.setProName(son.getProName());//名称
		ptasbd1.setBanben(son.getBanBenNumber());//版本
		ptasbd1.setBanci(son.getBanci());
		ptasbd1.setWgType(son.getWgType());//物料类别
		ptasbd1.setKgliao(son.getKgliao());//客供属性
		ptasbd1.setSpecification(son.getSpecification());//规格
		ptasbd1.setUnit(son.getUnit());// 单位(自制)
		ptasbd1.setTuhao(son.getTuhao());//图号
		ptasbd1.setYongliao(thisyl);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
		ptasbd1.setPtasbId(ptasb1.getId());
		totalDao.save(ptasbd1);
		if(!son.getProcardStyle().equals("外购")){//下层零件生成涉及零件记录
			Set<ProcardTemplatesb> thissonDownSet = son.getProcardsbTSet();
			if(thissonDownSet!=null&&thissonDownSet.size()>0){
				for(ProcardTemplatesb thissonDown:thissonDownSet){
					Float yl2 = thisyl;
					if(thissonDown.getProcardStyle().equals("外购")){
						try {
							yl2 = thisyl*Util.Floatdiv(thissonDown.getQuanzi2(), thissonDown.getQuanzi1(),4);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException(thissonDown.getMarkId()+"用量有误!");
						}
					}else{
						yl2 = thisyl*thissonDown.getCorrCount();
					}
					addsbxglj(thissonDown,ptbb,yl2,totalDao);
				}
			}
		}
	}
	/**
	 * 比对设变bz后的零件与设变编制前零件的差异
	 * @param ptsb
	 * @param pt
	 * @param scyyongliang上层原用量
	 * @param scnyongliang上层新用量
	 * @return
	 */
	public static String addProcardTemplatesbDifference(TotalDao totalDao,ProcardTemplatesb ptsb,
			ProcardTemplate pt,Float scyyongliang,Float scnyongliang,ProcardTemplateBanBen ptbb) {
		// TODO Auto-generated method stub
		ProcardTemplatesbDifference old = (ProcardTemplatesbDifference) totalDao.getObjectByCondition("from ProcardTemplatesbDifference where sbApplyId =? and markId=? ", ptsb.getSbApplyId(),ptsb.getMarkId());
		boolean hadcompare =false;//是否已经比对过
		if(old==null){
			old = new ProcardTemplatesbDifference();
			old.setSbApplyId(ptsb.getSbApplyId());//
			old.setSbNumber(ptsb.getSbNumber());//
			old.setMarkId(ptsb.getMarkId());//主题零件件号
			old.setDataSource("原有");//数据来源(原有,新增)
			old.setYwmarkId(ptsb.getYwMarkId());//
			old.setRootMarkId(ptsb.getRootMarkId());//
			totalDao.save(old);
		}else{
			Float had = (Float) totalDao.getObjectByCondition("select count(*) from ProcardTemplatesbDifferenceDetail where datatype='本身' and ptsbdId=?", old.getId());
			if(had!=null&&had>0){
				hadcompare =true;
			}
		}
		if(!hadcompare){
			if(pt.getMarkId().equals("1.01.10028")){
				System.out.println(ptsb.getQuanzi2()+"!!!"+pt.getQuanzi2());
			}
			 diffrentsx(totalDao,ptsb.getMarkId(), pt.getMarkId(), "件号",  old.getId(),0);
			 diffrentsx(totalDao,ptsb.getYwMarkId(), pt.getYwMarkId(), "业务件号", old.getId(),1);
			 diffrentsx(totalDao,ptsb.getBanBenNumber(), pt.getBanBenNumber(), "版本号", old.getId(),2);
			 diffrentsx(totalDao,ptsb.getProName(), pt.getProName(), "名称", old.getId(),3);
			 diffrentsx(totalDao,ptsb.getSpecification(), pt.getSpecification(), " 规格", old.getId(),4);
			 diffrentsx(totalDao,ptsb.getCaizhi(), pt.getCaizhi(), "材质", old.getId(),5);
			 diffrentsx(totalDao,ptsb.getCorrCount(), pt.getCorrCount(), "权值", old.getId(),6);
			 diffrentsx(totalDao,ptsb.getQuanzi1(), pt.getQuanzi1(), "权值1", old.getId(),6);
			 diffrentsx(totalDao,ptsb.getQuanzi2(), pt.getQuanzi2(), "权值2", old.getId(),6);
			 diffrentsx(totalDao,ptsb.getUnit(), pt.getUnit(), "单位", old.getId(),7);
			 diffrentsx(totalDao,ptsb.getProcardStyle(), pt.getProcardStyle(), "零件类型", old.getId(),8);
			 diffrentsx(totalDao,ptsb.getWgType(), pt.getWgType(), "物料类别", old.getId(),9);
			 diffrentsx(totalDao,ptsb.getKgliao(), pt.getKgliao(), "供料属性", old.getId(),10);
			 diffrentsx(totalDao,ptsb.getZzjihuo(), pt.getZzjihuo(), "自制件激活模式", old.getId(),11);
			 diffrentsx(totalDao,ptsb.getJihuoType(), pt.getJihuoType(), "激活类型", old.getId(),12);
			 diffrentsx(totalDao,ptsb.getClType(), pt.getClType(), "材料类型", old.getId(),13);
			 diffrentsx(totalDao,ptsb.getBiaochu(), pt.getBiaochu(), "表处", old.getId(),14);
			 diffrentsx(totalDao,ptsb.getTuhao(), pt.getTuhao(), "图号", old.getId(),15);
			 diffrentsx(totalDao,ptsb.getShowSize(), pt.getShowSize(), "尺寸", old.getId(),16);
			 diffrentsx(totalDao,ptsb.getLingliaostatus(), pt.getLingliaostatus(), "是否领料", old.getId(),17);
			 diffrentsx(totalDao,ptsb.getNeedProcess(), pt.getNeedProcess(), "外购件是否半成品", old.getId(),18);
			 diffrentsx(totalDao,ptsb.getLingliaoType(), pt.getLingliaoType(), "领料方式", old.getId(),19);
			 diffrentsx(totalDao,ptsb.getThisLength(), pt.getThisLength(), "长", old.getId(),20);
			 diffrentsx(totalDao,ptsb.getThisWidth(), pt.getThisWidth(), "宽", old.getId(),21);
			 diffrentsx(totalDao,ptsb.getThisHight(), pt.getThisHight(), "高", old.getId(),22);
			 diffrentsx(totalDao,ptsb.getCalculateType(), pt.getCalculateType(), "计算方式", old.getId(),23);
			 diffrentsx(totalDao,ptsb.getImportance(), pt.getImportance(), "重要度", old.getId(),24);
		}
		boolean hadsc=false;
		ProcardTemplateAboutsb ptasb1 =null;
		ProcardTemplateAboutsb ptasb2=null;
		if(!hadsc&&!Util.isEquals(ptsb.getProcardStyle(), pt.getProcardStyle())){
			ptasb1= (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and procardStyle=? and markId=? ",ptbb.getProcardTemplateBanBenApply().getId(),ptsb.getProcardStyle(),ptsb.getMarkId());
			ptasb2 = (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and procardStyle=? and markId=? ",ptbb.getProcardTemplateBanBenApply().getId(),pt.getProcardStyle(),pt.getMarkId());
			hadsc=true;
		}
		if(!hadsc&&ptsb.getProcardStyle().equals("外购")){
			if(!Util.isEquals(ptsb.getKgliao(), pt.getKgliao())){
				String kgliaoSql2 = null;
				if(pt.getKgliao()==null||pt.getKgliao().length()==0){
					kgliaoSql2 = " and (kgliao is null or kgliao='')";
				}else{
					kgliaoSql2 = " and kgliao ='"+pt.getKgliao()+"'";
				}
				if(ptasb1==null){
					ptasb1= (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and kgliao=? and markId=? ",ptbb.getProcardTemplateBanBenApply().getId(),ptsb.getKgliao(),ptsb.getMarkId());
				}
				if(ptasb2==null){
					ptasb2 = (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=?  and markId=? " +kgliaoSql2,ptbb.getProcardTemplateBanBenApply().getId(),pt.getMarkId());
				}
				hadsc = true;
			}
		}
		if(!hadsc&&!Util.isEquals(ptsb.getBanBenNumber(), pt.getBanBenNumber())){
			String banbanSql1 = null;
			String banbanSql2 = null;
			if(ptsb.getBanBenNumber()==null||ptsb.getBanBenNumber().length()==0){
				banbanSql1 = " and (banben is null or banben='')";
			}else{
				banbanSql1 = " and banben ='"+ptsb.getBanBenNumber()+"'";
			}
			if(pt.getBanBenNumber()==null||pt.getBanBenNumber().length()==0){
				banbanSql2 = " and (banben is null or banben='')";
			}else{
				banbanSql2 = " and banben ='"+pt.getBanBenNumber()+"'";
			}
			
			if(ptasb1==null){
				ptasb1= (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and markId=? " +banbanSql1,ptbb.getProcardTemplateBanBenApply().getId(),ptsb.getMarkId());
			}
			if(ptasb2==null){
				ptasb2 = (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and markId=? " +banbanSql2,ptbb.getProcardTemplateBanBenApply().getId(),pt.getMarkId());
			}
			hadsc = true;
		}
		if(hadsc){
			ProcardTemplateAboutsbDetail ptasbd1=null;
			ProcardTemplateAboutsbDetail ptasbd2=null;
			Float yl1 = scnyongliang;
			Float yl2 = scyyongliang;
			if(ptasb1==null){
				//生成设变涉及相关零件主表
				ptasb1 = new ProcardTemplateAboutsb();
				ptasb1.setSbApplyId(ptbb.getProcardTemplateBanBenApply().getId());//设变单id
				ptasb1.setSbNumber(ptbb.getProcardTemplateBanBenApply().getSbNumber());//设变单号
				ptasb1.setProcardStyle(ptsb.getProcardStyle());//生产类型
				ptasb1.setProductStyle(ptsb.getProductStyle());//生产类型
				ptasb1.setMarkId(ptsb.getMarkId());//件号
				ptasb1.setProName(ptsb.getProName());//名称
				ptasb1.setBanben(ptsb.getBanBenNumber());//版本
				ptasb1.setBanci(ptsb.getBanci());
				ptasb1.setWgType(ptsb.getWgType());//物料类别
				ptasb1.setKgliao(ptsb.getKgliao());//客供属性
				ptasb1.setSpecification(ptsb.getSpecification());//规格
				ptasb1.setUnit(ptsb.getUnit());// 单位(自制)
				ptasb1.setTuhao(ptsb.getTuhao());//图号
				
				if(ptsb.getProcardStyle().equals("外购")){
					yl1 = yl1*Util.Floatdelete(ptsb.getQuanzi2(), ptsb.getQuanzi1());
				}else if(ptsb.getProcardStyle().equals("自制")){
					yl1 = yl1*ptsb.getCorrCount();
				}
				ptasb1.setYongliao(yl1);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
				totalDao.save(ptasb1);
			}else{
				if(ptsb.getProcardStyle().equals("外购")){
					try {
						yl1 = yl1*Util.Floatdiv(ptsb.getQuanzi2(), ptsb.getQuanzi1(),4);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException(ptsb.getMarkId()+"用量有误!");
					}
				}else if(ptsb.getProcardStyle().equals("自制")){
					yl1 = yl1*ptsb.getCorrCount();
				}
				ptasb1.setYongliao(Util.Floatadd(ptasb1.getYongliao(), yl1));//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
//				ptasbd1 = totalDao.getObjectByCondition("", agr)
			}
			//生成设变相关零件子表
			ptasbd1 = new ProcardTemplateAboutsbDetail();
			ptasbd1.setSbptId(ptbb.getId());//设变明细Id(表明是由于哪个设变零件到导致的)
			ptasbd1.setSbptMarkId(ptbb.getMarkId());//设变明细件号
			if(ptsb.getProcardTemplatesb()!=null){
				ptasbd1.setScmarkId(ptsb.getProcardTemplatesb().getMarkId());//上层零件件号
			}
			ptasbd1.setProcardStyle(ptsb.getProcardStyle());
			ptasbd1.setProductStyle(ptsb.getProductStyle());//生产类型
			ptasbd1.setMarkId(ptsb.getMarkId());//件号
			ptasbd1.setProName(ptsb.getProName());//名称
			ptasbd1.setBanben(ptsb.getBanBenNumber());//版本
			ptasbd1.setBanci(ptsb.getBanci());
			ptasbd1.setWgType(ptsb.getWgType());//物料类别
			ptasbd1.setKgliao(ptsb.getKgliao());//客供属性
			ptasbd1.setSpecification(ptsb.getSpecification());//规格
			ptasbd1.setUnit(ptsb.getUnit());// 单位(自制)
			ptasbd1.setTuhao(ptsb.getTuhao());//图号
			ptasbd1.setYongliao(yl1);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
			ptasbd1.setPtasbId(ptasb1.getId());
			totalDao.save(ptasbd1);
			if(ptasb2==null){
				//生成设变涉及相关零件主表
				ptasb2 = new ProcardTemplateAboutsb();
				ptasb2.setSbApplyId(ptbb.getProcardTemplateBanBenApply().getId());//设变单id
				ptasb2.setSbNumber(ptbb.getProcardTemplateBanBenApply().getSbNumber());//设变单号
				ptasb2.setProcardStyle(pt.getProcardStyle());
				ptasb2.setProductStyle(pt.getProductStyle());//生产类型
				ptasb2.setMarkId(pt.getMarkId());//件号
				ptasb2.setProName(pt.getProName());//名称
				ptasb2.setBanben(pt.getBanBenNumber());//版本
				ptasb2.setBanci(pt.getBanci());
				ptasb2.setWgType(pt.getWgType());//物料类别
				ptasb2.setKgliao(pt.getKgliao());//客供属性
				ptasb2.setSpecification(pt.getSpecification());//规格
				ptasb2.setUnit(pt.getUnit());// 单位(自制)
				ptasb2.setTuhao(pt.getTuhao());//图号
				
				if(pt.getProcardStyle().equals("外购")){
					try {
						yl2 = yl2*Util.Floatdiv(pt.getQuanzi2(), pt.getQuanzi1(),4);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						yl2=0f;
					}
				}else if(pt.getProcardStyle().equals("自制")){
					yl2 = yl2*pt.getCorrCount();
				}
				ptasb2.setYongliao(-yl2);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
				totalDao.save(ptasb2);
			}else{
				if(pt.getProcardStyle().equals("外购")){
					try {
						yl2 = yl2*Util.Floatdiv(pt.getQuanzi2(), pt.getQuanzi1(),4);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						yl2=0f;
					}
				}else if(pt.getProcardStyle().equals("自制")){
					yl2 = yl2*pt.getCorrCount();
				}
				ptasb2.setYongliao(Util.Floatdelete(ptasb2.getYongliao(), yl2));//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
			}
			//生成设变相关零件子表
			ptasbd2 = new ProcardTemplateAboutsbDetail();
			ptasbd2.setSbptId(ptbb.getId());//设变明细Id(表明是由于哪个设变零件到导致的)
			ptasbd2.setSbptMarkId(ptbb.getMarkId());//设变明细件号
			if(pt.getProcardTemplate()!=null){
				ptasbd2.setScmarkId(pt.getProcardTemplate().getMarkId());//上层零件件号
			}
			ptasbd2.setProcardStyle(pt.getProcardStyle());//生产类型
			ptasbd2.setProductStyle(pt.getProductStyle());//生产类型
			ptasbd2.setMarkId(pt.getMarkId());//件号
			ptasbd2.setProName(pt.getProName());//名称
			ptasbd2.setBanben(pt.getBanBenNumber());//版本
			ptasbd2.setBanci(pt.getBanci());
			ptasbd2.setWgType(pt.getWgType());//物料类别
			ptasbd2.setKgliao(pt.getKgliao());//客供属性
			ptasbd2.setSpecification(pt.getSpecification());//规格
			ptasbd2.setUnit(pt.getUnit());// 单位(自制)
			ptasbd2.setTuhao(pt.getTuhao());//图号
			ptasbd2.setYongliao(-yl1);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
			ptasbd2.setPtasbId(ptasb2.getId());
			totalDao.save(ptasbd2);
		}
		if(!hadsc){//用量比对
			Float yl1 = scnyongliang;
			Float yl2 = scyyongliang;
			if(ptsb.getProcardStyle().equals("外购")){
				try {
					yl1 = yl1*Util.Floatdiv(ptsb.getQuanzi2(), ptsb.getQuanzi1(),4);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException(ptsb.getMarkId()+"用量有误!");
				}
				try {
					yl2 = yl2*Util.Floatdiv(pt.getQuanzi2(), pt.getQuanzi1(),4);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					yl2=0f;
				}
			} else {
				if(!ptsb.getId().equals(ptsb.getRootId())){
					try {
						yl1 = yl1 * ptsb.getCorrCount();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException(ptsb.getMarkId() + "用量有误!");
					}
					if(pt.getCorrCount()==null){
						yl2=0f;
					}else{
						yl2 = yl2 * pt.getCorrCount();
					}
				}
			}
			Float cj = yl1-yl2;
			if(Math.abs(cj)>0.05||(yl2<1&&(cj/yl2)>0.01)){//用量有改变
				ptasb1= (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and markId=? ",ptbb.getProcardTemplateBanBenApply().getId(),ptsb.getMarkId());
				if(ptasb1==null){
					//生成设变涉及相关零件主表
					ptasb1 = new ProcardTemplateAboutsb();
					ptasb1.setSbApplyId(ptbb.getProcardTemplateBanBenApply().getId());//设变单id
					ptasb1.setSbNumber(ptbb.getProcardTemplateBanBenApply().getSbNumber());//设变单号
					ptasb1.setProcardStyle(ptsb.getProcardStyle());//生产类型
					ptasb1.setProductStyle(ptsb.getProductStyle());//生产类型
					ptasb1.setMarkId(ptsb.getMarkId());//件号
					ptasb1.setProName(ptsb.getProName());//名称
					ptasb1.setBanben(ptsb.getBanBenNumber());//版本
					ptasb1.setBanci(ptsb.getBanci());
					ptasb1.setWgType(ptsb.getWgType());//物料类别
					ptasb1.setKgliao(ptsb.getKgliao());//客供属性
					ptasb1.setSpecification(ptsb.getSpecification());//规格
					ptasb1.setUnit(ptsb.getUnit());// 单位(自制)
					ptasb1.setTuhao(ptsb.getTuhao());//图号
					ptasb1.setYongliao(cj);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
					totalDao.save(ptasb1);
				}else{
					if(ptsb.getProcardStyle().equals("外购")){
						try {
							yl1 = yl1*Util.Floatdiv(ptsb.getQuanzi2(), ptsb.getQuanzi1(),4);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException(ptsb.getMarkId()+"用量有误!");
						}
					}else if(ptsb.getProcardStyle().equals("自制")){
						yl1 = yl1*ptsb.getCorrCount();
					}
					ptasb1.setYongliao(Util.Floatadd(ptasb1.getYongliao(), cj));//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
//					ptasbd1 = totalDao.getObjectByCondition("", agr)
				}
				//生成设变相关零件子表
				ProcardTemplateAboutsbDetail ptasbd1 = new ProcardTemplateAboutsbDetail();
				ptasbd1.setSbptId(ptbb.getId());//设变明细Id(表明是由于哪个设变零件到导致的)
				ptasbd1.setSbptMarkId(ptbb.getMarkId());//设变明细件号
				if(ptsb.getProcardTemplatesb()!=null){
					ptasbd1.setScmarkId(ptsb.getProcardTemplatesb().getMarkId());//上层零件件号
				}
				ptasbd1.setProcardStyle(ptsb.getProcardStyle());//生产类型
				ptasbd1.setProductStyle(ptsb.getProductStyle());//生产类型
				ptasbd1.setMarkId(ptsb.getMarkId());//件号
				ptasbd1.setProName(ptsb.getProName());//名称
				ptasbd1.setBanben(ptsb.getBanBenNumber());//版本
				ptasbd1.setBanci(ptsb.getBanci());
				ptasbd1.setWgType(ptsb.getWgType());//物料类别
				ptasbd1.setKgliao(ptsb.getKgliao());//客供属性
				ptasbd1.setSpecification(ptsb.getSpecification());//规格
				ptasbd1.setUnit(ptsb.getUnit());// 单位(自制)
				ptasbd1.setTuhao(ptsb.getTuhao());//图号
				ptasbd1.setYongliao(cj);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
				ptasbd1.setPtasbId(ptasb1.getId());
				totalDao.save(ptasbd1);
			}
		}
		
		
		return  null;	
	}
	private static int diffrentsx(TotalDao totalDao,Float v1, Float v2,
			String sxName, Integer id, Integer i) {
		// TODO Auto-generated method stub
		if(sxName.equals("权值2")){
			Float a=v1;
			Float b=v2;
			if(v1==null){
				a = 0f;
			}
			if(v2==null){
				b = 0f;
			}
			Float cy = Math.abs(a-b);
			if((a==0&&b!=0)||(b==0&&a!=0)
					&&cy>1||(cy/a>0.005)||(cy/b>0.005)){
				ProcardTemplatesbDifferenceDetail ptsbdd = new ProcardTemplatesbDifferenceDetail();
//				ptsbdd.setzbMarkId;//主表件号
				ptsbdd.setDatatype("本身");//数据类型(本身，下层，工序，图纸)
				ptsbdd.setOptype("修改");//操作类型
				
				//本身使用
				ptsbdd.setSxName(sxName);//变值属性名称
				ptsbdd.setOldData(v2+"");//原值
				ptsbdd.setNewData(v1+"");//新值
				ptsbdd.setXuhao(i);//序号（显示时排序使用）
				ptsbdd.setPtsbdId(id);
				totalDao.save(ptsbdd);
				return 1;
			}
			
		}else if(!Util.isEquals(v1, v2)){
			ProcardTemplatesbDifferenceDetail ptsbdd = new ProcardTemplatesbDifferenceDetail();
//			ptsbdd.setzbMarkId;//主表件号
			ptsbdd.setDatatype("本身");//数据类型(本身，下层，工序，图纸)
			ptsbdd.setOptype("修改");//操作类型
			
			//本身使用
			ptsbdd.setSxName(sxName);//变值属性名称
			ptsbdd.setOldData(v2+"");//原值
			ptsbdd.setNewData(v1+"");//新值
			ptsbdd.setXuhao(i);//序号（显示时排序使用）
			ptsbdd.setPtsbdId(id);
			totalDao.save(ptsbdd);
			return 1;
		}
		return 0;
	}
	private static int diffrentsx(TotalDao totalDao,String v1, String v2,
			String sxName, Integer id, Integer i) {
		// TODO Auto-generated method stub
		if(sxName.equals("是否领料")){
			int a=0;
			int b=0;
			if(v1!=null&&v1.equals("否")){
				a = 1;
			}
			if(v2!=null&&v2.equals("否")){
				b = 1;
			}
			if(a!=b){
				ProcardTemplatesbDifferenceDetail ptsbdd = new ProcardTemplatesbDifferenceDetail();
//				ptsbdd.setzbMarkId;//主表件号
				ptsbdd.setDatatype("本身");//数据类型(本身，下层，工序，图纸)
				ptsbdd.setOptype("修改");//操作类型
				
				//本身使用
				ptsbdd.setSxName(sxName);//变值属性名称
				ptsbdd.setOldData(v2);//原值
				ptsbdd.setNewData(v1);//新值
				ptsbdd.setXuhao(i);//序号（显示时排序使用）
				ptsbdd.setPtsbdId(id);
				totalDao.save(ptsbdd);
				return 1;
			}
			
		}else if(!Util.isEquals(v1, v2)){
			ProcardTemplatesbDifferenceDetail ptsbdd = new ProcardTemplatesbDifferenceDetail();
//			ptsbdd.setzbMarkId;//主表件号
			ptsbdd.setDatatype("本身");//数据类型(本身，下层，工序，图纸)
			ptsbdd.setOptype("修改");//操作类型
			
			//本身使用
			ptsbdd.setSxName(sxName);//变值属性名称
			ptsbdd.setOldData(v2);//原值
			ptsbdd.setNewData(v1);//新值
			ptsbdd.setXuhao(i);//序号（显示时排序使用）
			ptsbdd.setPtsbdId(id);
			totalDao.save(ptsbdd);
			return 1;
		}
		return 0;
	}
	//比对下层工序
	public static int compareSbAndptprocess(TotalDao totalDao,ProcardTemplatesb ptsb, ProcardTemplate pt) {
		// TODO Auto-generated method stub
		//0表示没有改变
		//1表示有改变
		int returni=0;
		List<ProcessTemplatesb> processsbList= totalDao.query("from ProcessTemplatesb where procardTemplatesb.id=? and (dataStatus is null or dataStatus!='删除') order by processNO", ptsb.getId());
		List<ProcessTemplate> processptList= totalDao.query("from ProcessTemplate where procardTemplate.id=? and (dataStatus is null or dataStatus!='删除') order by processNO", pt.getId());
		if((processsbList!=null&&processsbList.size()>0)
				&&(processptList==null||processptList.size()==0)){//全增加
			returni= 1;
		}else if((processptList!=null&&processptList.size()>0)
				&&(processsbList==null||processsbList.size()==0)){//全删除
			returni= 1;
		}else{
			for(ProcessTemplatesb processsb:processsbList){
				boolean had=false;
				for(ProcessTemplate processpt:processptList){
					if(processsb.getProcessNO().equals(processpt.getProcessNO())){
						had=true;
						if(!processsb.getProcessName().equals(processpt.getProcessName())){
							returni= 1;
						}
						break;
					}
				}
				if(!had){
					returni= 1;
					break;
				}
			}
			for(ProcessTemplate processpt:processptList){
				boolean had=false;
				for(ProcessTemplatesb processsb:processsbList){
					if(processsb.getProcessNO().equals(processpt.getProcessNO())){
						had=true;
						if(!processsb.getProcessName().equals(processpt.getProcessName())){
							returni= 1;
						}
						break;
					}
				}
				if(!had){
					returni= 1;
					break;
				}
			}
		}
		if(returni>0){
			ProcardTemplatesbDifference old = (ProcardTemplatesbDifference) totalDao.getObjectByCondition("from ProcardTemplatesbDifference where sbApplyId =? and markId=? ", ptsb.getSbApplyId(),ptsb.getMarkId());
			boolean hadcompare =false;//是否已经比对过
			if(old==null){
				old=new ProcardTemplatesbDifference();
				old.setSbApplyId(ptsb.getSbApplyId());//
				old.setSbNumber(ptsb.getSbNumber());//
				old.setMarkId(ptsb.getMarkId());//主题零件件号
				old.setDataSource("原有");//数据来源(原有,新增)
				old.setYwmarkId(ptsb.getYwMarkId());//
				old.setRootMarkId(ptsb.getRootMarkId());//
				totalDao.save(old);
			}else{
				Float had = (Float) totalDao.getObjectByCondition("select count(*) from ProcardTemplatesbDifferenceDetail where datatype='工序' and ptsbdId=?", old.getId());
				if(had!=null&&had>0){
					hadcompare =true;
				}
			}
			if(!hadcompare){
				ProcardTemplatesbDifferenceDetail ptsbdd = new ProcardTemplatesbDifferenceDetail();
//				ptsbdd.setzbMarkId;//主表件号
				ptsbdd.setDatatype("工序");//数据类型(本身，下层，工序，图纸)
				ptsbdd.setOptype("修改");//操作类型
				
				//本身使用
				ptsbdd.setSxName("工序");//变值属性名称
				StringBuffer processno0 =new StringBuffer();
				StringBuffer processname0 = new StringBuffer();
				StringBuffer processno1 =new StringBuffer();
				StringBuffer processname1 = new StringBuffer();
				if(processsbList!=null&&processsbList.size()>0){
					for(ProcessTemplatesb processsb:processsbList){
						if(processno0.length()==0){
							processno0.append(processsb.getProcessNO());
							processname0.append(processsb.getProcessName());
						}else{
							processno0.append(";"+processsb.getProcessNO());
							processname0.append(";"+processsb.getProcessName());
						}
					}
				}
				if(processptList!=null&&processptList.size()>0){
					for(ProcessTemplate processtsb:processptList){
						if(processno1.length()==0){
							processno1.append(processtsb.getProcessNO());
							processname1.append(processtsb.getProcessName());
						}else{
							processno1.append(";"+processtsb.getProcessNO());
							processname1.append(";"+processtsb.getProcessName());
						}
					}
				}
				
				ptsbdd.setProcessno(processno0.toString());
				ptsbdd.setProcessname(processname0.toString());
				ptsbdd.setProcessno1(processno1.toString());
				ptsbdd.setProcessname1(processname1.toString());
				ptsbdd.setPtsbdId(old.getId());
				totalDao.save(ptsbdd);
				
			}
		}
		return returni;
	}
	//比对图纸
	public static void compareSbAndptyz(TotalDao totalDao,
			ProcardTemplatesb ptsb, ProcardTemplate pt,Float scyyongliang,Float scnyongliang) {
		// TODO Auto-generated method stub
		List<ProcessTemplateFilesb> sbFileList =  totalDao.query("from ProcessTemplateFilesb where sbApplyId=? and markId=? and banci=? ", ptsb.getSbApplyId(),ptsb.getMarkId(),ptsb.getBanci());
		List<ProcessTemplateFile> ptFileList = null;
		String bancisql = null;
		if(pt.getBanci()==null||pt.getBanci()==0){
			bancisql = " and (banci is null or banci =0)";
		}else{
			bancisql = " and banci = "+pt.getBanci();
		}
		if(pt.getProductStyle().equals("试制")){
			ptFileList= totalDao.query(" from ProcessTemplateFile where markId=? " +
					"and ((processNO is null and glId=? "+bancisql+") or " +
							"(processNO is not null and glId in(select id from ProcessTemplate where procardTemplate.id=?) "+bancisql+") )", pt.getMarkId(),pt.getId(),pt.getId());
		}else{
			ptFileList= totalDao.query("from ProcessTemplateFile where markId=? and glId is null "+bancisql, pt.getMarkId());
		}
		if((ptFileList==null||ptFileList.size()==0)||(sbFileList==null||sbFileList.size()==0)){
			//全没有
		}if((sbFileList!=null&&sbFileList.size()>0)
				&&(ptFileList==null||ptFileList.size()==0)){//全新增
			for(ProcessTemplateFilesb filesb:sbFileList){
				ProcardTemplatesbDifference.addProcardTemplatesbDifference(totalDao, ptsb,  filesb, "图纸", "添加",scyyongliang,scnyongliang,null);
			}
		}else if((ptFileList!=null&&ptFileList.size()>0)
				&&(sbFileList==null||sbFileList.size()==0)){//全删除
			for(ProcessTemplateFile ptfile:ptFileList){
				ProcardTemplatesbDifference.addProcardTemplatesbDifference(totalDao, ptsb,  ptfile, "图纸", "删除",scyyongliang,scnyongliang,null);
			}
		}else{
			List<String> filenameList1= new ArrayList<String>();
			List<String> filenameList2= new ArrayList<String>();
			for(ProcessTemplateFilesb filesb:sbFileList){
				boolean b=false;
				for(ProcessTemplateFile ptfile:ptFileList){
					try {
						if(filesb.getFileName().equals(ptfile.getFileName())){
							b=true;
							break;
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						throw new RuntimeException("含有空文件名文件!");
					}
					
				}
				if(!b&&!filenameList1.contains(filesb.getFileName())){
					filenameList1.add(filesb.getFileName());
					ProcardTemplatesbDifference.addProcardTemplatesbDifference(totalDao, ptsb,  filesb, "图纸", "添加",scyyongliang,scnyongliang,null);
				}
			}
			for(ProcessTemplateFile ptfile:ptFileList){
				boolean b=false;
				for(ProcessTemplateFilesb filesb:sbFileList){
					if(filesb.getFileName().equals(ptfile.getFileName())){
						b=true;
						break;
						
					}
				}
				if(!b&&!filenameList2.contains(ptfile.getFileName())){
					filenameList2.add(ptfile.getFileName());
					ProcardTemplatesbDifference.addProcardTemplatesbDifference(totalDao, ptsb,  ptfile, "图纸", "删除",scyyongliang,scnyongliang,null);
				}
			}
		}
		
		
	}
	public static void addProcardTemplatesbDifference(TotalDao totalDao,ProcardTemplatesb ptsb,
			ProcardTemplate pt, ProcardTemplate son, String dataType,
			String opType,Float yyongliang,Float nyongliang,ProcardTemplateBanBen ptbb) {//记录删除子件
		ProcardTemplatesbDifference old = (ProcardTemplatesbDifference) totalDao.getObjectByCondition("from ProcardTemplatesbDifference where sbApplyId =? and markId=? ", ptsb.getSbApplyId(),ptsb.getMarkId());
		boolean had=false;
		if(old==null){
			old=new ProcardTemplatesbDifference();
			old.setSbApplyId(ptsb.getSbApplyId());//
			old.setSbNumber(ptsb.getSbNumber());//
			old.setMarkId(ptsb.getMarkId());//主题零件件号
			Float hadcount = (Float) totalDao.getObjectByCondition("select count(*) from ProcardTemplate where markId=? and  (dataStatus is null or dataStatus!='删除')", ptsb.getMarkId()); 
			if(hadcount==null||hadcount==0){
				old.setDataSource("新增");//数据来源(原有,新增)
			}else {
				old.setDataSource("原有");//数据来源(原有,新增)
			}
			old.setYwmarkId(ptsb.getYwMarkId());//
			old.setRootMarkId(ptsb.getRootMarkId());//
			totalDao.save(old);
		}else{
			had =true;
		}
		if(dataType.equals("子件")){
			//看看是不是已经有下层的删除记录了
			ProcardTemplatesbDifferenceDetail psbdDetail =null;
			if(had){
				psbdDetail=(ProcardTemplatesbDifferenceDetail) totalDao.getObjectByCondition("from ProcardTemplatesbDifferenceDetail where markId=? and ptsbdId=? and datatype='子件'",son.getMarkId(), old.getId());
			}
			if(psbdDetail==null){
				psbdDetail = new ProcardTemplatesbDifferenceDetail();
				psbdDetail.setZbMarkId(ptsb.getMarkId());//主表件号
				psbdDetail.setDatatype(dataType);//数据类型(本身，下层，工序，图纸)
				psbdDetail.setOptype(opType);//操作类型
				psbdDetail.setMarkId(son.getMarkId());//子件件号
				psbdDetail.setDataId(son.getId());//数据id
				psbdDetail.setProname(son.getProName());//名称
				psbdDetail.setBanbenNumber(son.getBanBenNumber());
				psbdDetail.setBanci(son.getBanci());
//				psbdDetail.setSxName(sxName);//变值属性名称
//				psbdDetail.setoldData;//原值
//				psbdDetail.setnewData;//新值
//				//文件数据
//				psbdDetail.setfileName;//
//				psbdDetail.setoldfileName;//
				psbdDetail.setPtsbdId(old.getId());//主表Id (ProcardTemplatesbDifference)
				totalDao.save(psbdDetail);
			}
			
			//子件添加的涉及零件记录
			Float yl = null;
			yl = yyongliang;
			if(son.getProcardStyle().equals("外购")){
				try {
					yl = yyongliang*Util.Floatdiv(son.getQuanzi2(), son.getQuanzi1(),4);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					yl=0f;
				}
			}else{
				System.out.println(son.getFatherId());
				System.out.println(son.getMarkId());
				System.out.println(son.getId());
				yl = yyongliang*son.getCorrCount();
			}
			ProcardTemplateAboutsb ptasb1= (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and markId=? ",ptbb.getProcardTemplateBanBenApply().getId(),son.getMarkId());
			if(ptasb1==null){
				//生成设变涉及相关零件主表
				ptasb1 = new ProcardTemplateAboutsb();
				ptasb1.setSbApplyId(ptbb.getProcardTemplateBanBenApply().getId());//设变单id
				ptasb1.setSbNumber(ptbb.getProcardTemplateBanBenApply().getSbNumber());//设变单号
				ptasb1.setProcardStyle(son.getProcardStyle());
				ptasb1.setProductStyle(son.getProductStyle());//生产类型
				ptasb1.setMarkId(son.getMarkId());//件号
				ptasb1.setProName(son.getProName());//名称
				ptasb1.setBanben(son.getBanBenNumber());//版本
				ptasb1.setBanci(son.getBanci());
				ptasb1.setWgType(son.getWgType());//物料类别
				ptasb1.setKgliao(son.getKgliao());//客供属性
				ptasb1.setSpecification(son.getSpecification());//规格
				ptasb1.setUnit(son.getUnit());// 单位(自制)
				ptasb1.setTuhao(son.getTuhao());//图号
				ptasb1.setYongliao(-yl);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
				totalDao.save(ptasb1);
			}else{
				ptasb1.setYongliao(Util.Floatdelete(ptasb1.getYongliao(), yl));//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
//				ptasbd1 = totalDao.getObjectByCondition("", agr)
			}
			//生成设变相关零件子表
			ProcardTemplateAboutsbDetail ptasbd1 = new ProcardTemplateAboutsbDetail();
			ptasbd1.setSbptId(ptbb.getId());//设变明细Id(表明是由于哪个设变零件到导致的)
			ptasbd1.setSbptMarkId(ptbb.getMarkId());//设变明细件号
			if(son.getProcardTemplate()!=null){
				ptasbd1.setScmarkId(son.getProcardTemplate().getMarkId());//上层零件件号
			}
			ptasbd1.setProcardStyle(son.getProcardStyle());//生产类型
			ptasbd1.setProductStyle(son.getProductStyle());//生产类型
			ptasbd1.setMarkId(son.getMarkId());//件号
			ptasbd1.setProName(son.getProName());//名称
			ptasbd1.setBanben(son.getBanBenNumber());//版本
			ptasbd1.setBanci(son.getBanci());
			ptasbd1.setWgType(son.getWgType());//物料类别
			ptasbd1.setKgliao(son.getKgliao());//客供属性
			ptasbd1.setSpecification(son.getSpecification());//规格
			ptasbd1.setUnit(son.getUnit());// 单位(自制)
			ptasbd1.setTuhao(son.getTuhao());//图号
			ptasbd1.setYongliao(-yl);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
			ptasbd1.setPtasbId(ptasb1.getId());
			totalDao.save(ptasbd1);
			if(!son.getProcardStyle().equals("外购")){//下层零件生成涉及零件记录
				Set<ProcardTemplate> thissonDownSet = son.getProcardTSet();
				if(thissonDownSet!=null&&thissonDownSet.size()>0){
					for(ProcardTemplate thissonDown:thissonDownSet){
						Float yl2 = yl;
						if(thissonDown.getProcardStyle().equals("外购")){
							try {
								yl2 = yl*Util.Floatdiv(thissonDown.getQuanzi2(), thissonDown.getQuanzi1(),4);
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new RuntimeException(thissonDown.getMarkId()+"用量有误!");
							}
						}else{
							yl2 = yl*thissonDown.getCorrCount();
						}
						addsbxglj(thissonDown,ptbb,yl2,totalDao);
					}
				}
			}
		}
		
	}
	private static void addsbxglj(ProcardTemplate son,
			ProcardTemplateBanBen ptbb, Float thisyl, TotalDao totalDao) {
		// TODO Auto-generated method stub
		ProcardTemplateAboutsb ptasb1= (ProcardTemplateAboutsb) totalDao.getObjectByCondition("from ProcardTemplateAboutsb where sbApplyId=? and markId=? ",ptbb.getProcardTemplateBanBenApply().getId(),son.getMarkId());
		if(ptasb1==null){
			//生成设变涉及相关零件主表
			ptasb1 = new ProcardTemplateAboutsb();
			ptasb1.setSbApplyId(ptbb.getProcardTemplateBanBenApply().getId());//设变单id
			ptasb1.setSbNumber(ptbb.getProcardTemplateBanBenApply().getSbNumber());//设变单号
			ptasb1.setProcardStyle(son.getProcardStyle());
			ptasb1.setProductStyle(son.getProductStyle());//生产类型
			ptasb1.setMarkId(son.getMarkId());//件号
			ptasb1.setProName(son.getProName());//名称
			ptasb1.setBanben(son.getBanBenNumber());//版本
			ptasb1.setBanci(son.getBanci());
			ptasb1.setWgType(son.getWgType());//物料类别
			ptasb1.setKgliao(son.getKgliao());//客供属性
			ptasb1.setSpecification(son.getSpecification());//规格
			ptasb1.setUnit(son.getUnit());// 单位(自制)
			ptasb1.setTuhao(son.getTuhao());//图号
			ptasb1.setYongliao(-thisyl);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
			totalDao.save(ptasb1);
		}else{
			ptasb1.setYongliao(Util.Floatdelete(ptasb1.getYongliao(), thisyl));//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
//			ptasbd1 = totalDao.getObjectByCondition("", agr)
		}
		//生成设变相关零件子表
		ProcardTemplateAboutsbDetail ptasbd1 = new ProcardTemplateAboutsbDetail();
		ptasbd1.setSbptId(ptbb.getId());//设变明细Id(表明是由于哪个设变零件到导致的)
		ptasbd1.setSbptMarkId(ptbb.getMarkId());//设变明细件号
		if(son.getProcardTemplate()!=null){
			ptasbd1.setScmarkId(son.getProcardTemplate().getMarkId());//上层零件件号
		}
		ptasbd1.setProcardStyle(son.getProcardStyle());//生产类型
		ptasbd1.setProductStyle(son.getProductStyle());//生产类型
		ptasbd1.setMarkId(son.getMarkId());//件号
		ptasbd1.setProName(son.getProName());//名称
		ptasbd1.setBanben(son.getBanBenNumber());//版本
		ptasbd1.setBanci(son.getBanci());
		ptasbd1.setWgType(son.getWgType());//物料类别
		ptasbd1.setKgliao(son.getKgliao());//客供属性
		ptasbd1.setSpecification(son.getSpecification());//规格
		ptasbd1.setUnit(son.getUnit());// 单位(自制)
		ptasbd1.setTuhao(son.getTuhao());//图号
		ptasbd1.setYongliao(-thisyl);//单台用量增减（表示生产一个总成的用量增减 正数为增负数为减）
		ptasbd1.setPtasbId(ptasb1.getId());
		totalDao.save(ptasbd1);
		if(!son.getProcardStyle().equals("外购")){//下层零件生成涉及零件记录
			Set<ProcardTemplate> thissonDownSet = son.getProcardTSet();
			if(thissonDownSet!=null&&thissonDownSet.size()>0){
				for(ProcardTemplate thissonDown:thissonDownSet){
					Float yl2 = thisyl;
					if(thissonDown.getProcardStyle().equals("外购")){
						try {
							yl2 = thisyl*Util.Floatdiv(thissonDown.getQuanzi2(), thissonDown.getQuanzi1(),4);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException(thissonDown.getMarkId()+"用量有误!");
						}
					}else{
						yl2 = thisyl*thissonDown.getCorrCount();
					}
					addsbxglj(thissonDown,ptbb,yl2,totalDao);
				}
			}
		}
	}
	
	
}