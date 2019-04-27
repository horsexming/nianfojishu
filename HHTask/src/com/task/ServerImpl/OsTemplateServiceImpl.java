package com.task.ServerImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.OsTemplateService;
import com.task.entity.android.InsRecord;
import com.task.entity.android.InsRecordScope;
import com.task.entity.android.InsScope;
import com.task.entity.android.InsTemplate;
import com.task.entity.android.OsRecord;
import com.task.entity.android.OsRecordScope;
import com.task.entity.android.OsScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.ProcessTemplateFile;
import com.task.entity.sop.PurchasedPartsInput;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

public class OsTemplateServiceImpl implements OsTemplateService {
	private TotalDao totalDao;
	@Override
	public boolean add(OsTemplate template) {
		if(template.getPartNumber()!=null){
			String partNumber=template.getPartNumber().replaceAll(" ", "");
			template.setPartNumber(partNumber);
		}
		template.setScopes(new HashSet<OsScope>(template.getScope()));
		template.setUsername(Util.getLoginUser().getName());
		template.setCreateDate(Util.getDateTime());
		if(template.getXjbzId()!=null){
			String xjbz =	(String) totalDao.getObjectByCondition("select leixing from Waigoujianpinci where id =?  ", template.getXjbzId());
			template.setXjbz(xjbz);
		}
		return	totalDao.save(template);
	}
	

	@Override
	public List<OsTemplate> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] list(OsTemplate t,Integer rows, Integer size,String status) {
		if (t == null) {
			t = new OsTemplate();
		}
//		String hql = totalDao.criteriaQueries(zhtoubiao, null, null)+" and tkong7='同意'  and tkong10="+id;
//		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
//		List list = totalDao.findAllByPage(hql, cpage, PageSize);
//		int count = totalDao.getCount(hql);
//		Object[] o = { list, count };
//		return o;
		
		//String hql = "from OsTemplate order by id desc";
		String str = "";
		if("ww".equals(status)){
			t.setZhonglei("外委检验");
		}else if("ty".equals(status)){
			t.setIspublic("是");
		}else if("wg".equals(status)){
			t.setZhonglei("外购件检验");
			str = " or zhonglei is null";
		}else if("fl".equals(status)){
			t.setZhonglei("辅料");
		}
		String hql = totalDao.criteriaQueries(t, null, null)+ str+" order by cmodel desc";
		List list = totalDao.findAllByPage(hql, rows, size);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	public void deleteOsTemplate(OsTemplate os) {
		delOsRecord(os.getId());
		os.setScopes(null);
		totalDao.delete(os);
		
	}
	public void delOsRecord(Integer osId){
		String hql = "from OsRecord where template.id=?";
		List<OsRecord> osrList = totalDao.query(hql, osId);
		if(osrList!=null && osrList.size()>0){
			for (OsRecord osRecord : osrList) {
				osRecord.setTemplate(null);
				totalDao.delete(osRecord);
				for (OsRecordScope rs : osRecord.getRecordScope()) {
					totalDao.delete(rs);
				}
			}
		}
	}
	public OsTemplate byIdOsTemplate(Integer id) {
		return (OsTemplate) totalDao.getObjectById(OsTemplate.class, id);
	}
	
	public OsScope byIdOsScope(Integer id) {
		return (OsScope) totalDao.getObjectById(OsScope.class, id);
	}
	public boolean updateOsTemplate(OsTemplate os) {
		//OsTemplate oldos = (OsTemplate) totalDao.get(OsTemplate.class, os.getId());
		
//		if(os.getXjtype()!=null && !"".equals(os.getXjtype())){
//			oldos.setXjtype(os.getXjtype());
//		}
//		if(os.getXjcheckpc()!=null && !"".equals(os.getXjcheckpc())){
//			oldos.setXjcheckpc(os.getXjcheckpc());
//		}
//		if(os.getFilename()!=null && !"".equals(os.getFilename())){
//			oldos.setFilename(os.getFilename());
//		}
		if(os.getXjbzId()!=null){
			String xjbz =	(String) totalDao.getObjectByCondition("select leixing from Waigoujianpinci where id =?  ", os.getXjbzId());
			os.setXjbz(xjbz);
		}
		return	totalDao.update(os);
	}
	
	public boolean UpdateOsScope(OsScope  os) {
		OsScope oldos =	(OsScope) totalDao.get(os.getClass() ,os.getId());
		BeanUtils.copyProperties(os,oldos,new String[]{"id","osTemplate"});
		return	totalDao.update(oldos);
	}
	public void deleteOsScope(OsScope os) {
		totalDao.delete(os);
	}
//	@Override
//	public OsTemplate byIdOsTemplate(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<OsTemplate> getData() {
		String hql = "from OsTemplate where ctype1 = '外购件' order by id desc";
		List<OsTemplate> list = totalDao.find(hql);
		for (OsTemplate insTemplate : list) {
			Set<OsScope> l = insTemplate.getScopes();
			for (OsScope insScope : l) {
				insScope.getId();
			}
		}
		return list;
	}

	@Override
	public List<OsScope> getScopes(OsTemplate t) {
		String hql = "from OsTemplate where id = ?";
		OsTemplate t1 = (OsTemplate) totalDao.get(t.getClass(), t.getId());
		for (OsScope iter : t1.getScopes()) {
			iter.getId();
		}
		List<OsScope> list = new ArrayList<OsScope>(t1.getScopes());
		return list;
	}
	@Override             //检查项                       外购件
	public void addOsScope(OsScope tt, OsTemplate t) {
//		t.setScopes((Set<OsScope>) tt);
//		totalDao.update(tt);
		OsTemplate t1 = (OsTemplate) totalDao.get(t.getClass(), t.getId());
		t1.getScopes().add(tt);
		totalDao.update(t1);
	}


	@Override
	public List<PurchasedPartsInput> cheackMarkId(String markIds) {
		// TODO Auto-generated method stub
		String hql="from PurchasedPartsInput where markId in("+markIds+") and (status is null or status='未检测') ";
		return totalDao.query(hql);
	}


	@Override
	public List<OsTemplate> getApplyMarkId() {
		// TODO Auto-generated method stub
		String hql="from  OsTemplate where  partNumber in(select markId from PurchasedPartsInput where status is null or status='未检测')";
		List<OsTemplate> list = totalDao.find(hql);
		for (OsTemplate insTemplate : list) {
			Set<OsScope> l = insTemplate.getScopes();
			for (OsScope insScope : l) {
				insScope.getId();
			}
		}
		return list;
	}


	@Override
	public ProcardTemplate getGongjianxinxi(Integer id ) {
		String hql="from ProcardTemplate where id=(select procardTemplate from ProcessTemplate where id="+id+")";
		List<ProcardTemplate> list=totalDao.find(hql);
		return list.get(0);
	}
	

	@Override
	public OsTemplate getOsTemplate1(String partNumber, String gongxuNum) {
		String hql="from OsTemplate where partNumber=? and gongxuNum=?";
		Object[] params={partNumber,gongxuNum};
		List<OsTemplate> OsTemplateLsit=totalDao.find(hql, params);
		if(OsTemplateLsit!=null&&OsTemplateLsit.size()>0){
			return OsTemplateLsit.get(0);
		}
		return null;
	}


	@Override
	public void addOsScope1(List<OsScope> listOsScope, OsTemplate t) {
		OsTemplate t1 = (OsTemplate) totalDao.get(t.getClass(), t.getId());
		if(t1!=null && listOsScope!=null && listOsScope.size()>0){
			Set<OsScope> os =t1.getScopes();
				for (OsScope osScope : listOsScope) {
					os.add(osScope);
			}
			t1.setScopes(os);
		}
		
		
			totalDao.update(t1);
			
		
	}


	@Override
	public ProcessGzstore getProcessGzstorebyid(Integer id) {
		String hql="from ProcessGzstore where processName=(select processName from ProcessTemplate where id=?)";
		ProcessGzstore pg = (ProcessGzstore) totalDao.getObjectByCondition(hql, id);
		return pg;
	}


	@Override
	public List<OsTemplate> getOsTemplate(String partNumber, String gongxuNum) {
		String hql="from OsTemplate where partNumber=? and gongxuNum=?";
		Object[] params={partNumber,gongxuNum};
		List<OsTemplate> OsTemplateLsit=totalDao.find(hql, params);
		if(OsTemplateLsit!=null&&OsTemplateLsit.size()>0){
			return OsTemplateLsit;
		}
		return null;
	}


	@Override
	public boolean shuaxinsScope(List<OsScope> listOsScope, List<OsTemplate> ts) {
	String msg = "";	
	List strList = new ArrayList();
		if(listOsScope!=null && listOsScope.size()>0 && ts!=null && ts.size()>0){
		boolean bool =false;
			for (int i = 0; i < ts.size(); i++) {
				OsTemplate t = ts.get(i);	
				Set<OsScope> os = t.getScopes();
			List<OsScope> list =new ArrayList<OsScope>();
			for (OsScope osScope : os) {
				for(OsScope osScope1 : listOsScope){
					if(osScope.getId()-osScope1.getId()==0){
						list.add(osScope1);//用来储存中间需要删除的重复对象;
					}
				}
			}
			for(OsScope os2 : list){
				listOsScope.remove(os2);
			}
			
			for (OsScope os3 : listOsScope) {
				os.add(os3);
			}
			t.setScopes(os);
			if(totalDao.update(t)){
				strList.add(i);
			}
		}
	}	
		if(strList!=null && strList.size()-ts.size()==0){
			return true;
		}
		return false;
	}


	@Override
	public boolean deleteOsScope1(OsTemplate ost, OsScope os) {
		if(ost!=null && os!=null){
			OsTemplate oldost = (OsTemplate) totalDao.get(OsTemplate.class, ost.getId());
			OsScope oldos = (OsScope) totalDao.get(OsScope.class, os.getId());
			if(oldost!=null && oldos !=null){
				Set<OsScope> osSet = oldost.getScopes();
				osSet.remove(oldos);
				oldost.setScopes(osSet);
				return totalDao.update(oldost);
			}
		}
		
		return false;
	}


	@Override
	public boolean addAll() {
		String hql_Inst = "from InsTemplate ";
		List<InsTemplate> insList=	totalDao.find(hql_Inst);
		if(insList!=null && insList.size()>0){
		for (InsTemplate ins : insList) {
			if(ins.getProcessNumber()!=null&&!"".equals(ins.getProcessNumber())
					&& ins.getProcessNumber().split(" ").length>1){
				String [] strarray = ins.getProcessNumber().split(" ");
				for (int i = 0; i < strarray.length; i++) {
					OsTemplate ost = new OsTemplate();
					ost.setGongxuNum(strarray[i]);
					ost.setZhonglei("巡检");
					ost.setGongweiNum(ins.getWorkStation());
					ost.setXjtype(ins.getType());
					ost.setUsername(ins.getUsername());
					if(ins.getCreateDate()!=null){
						ost.setCreateDate(Util.DateToString(ins.getCreateDate(), "yyyy-MM-dd hh:mm:ss"));
					}
					ost.setProductType(ins.getProductType());
					ost.setPartNumber(ins.getPartNumber());
					//同步巡检项
					Set<OsScope> ossSet =new HashSet<OsScope>();
					Set<InsScope> inss = ins.getScope();
					List<InsScope> inssList = new ArrayList<InsScope>();
					
					for (InsScope insScope : inss) {
						inssList.add(insScope);
					}
					List<Integer> iList = new ArrayList<Integer>();
					for (InsScope insScope : inssList) {
						if(iList.contains(insScope.getId())){
							continue;
						}
						OsScope  oss = new OsScope();
						iList.add(insScope.getId());
						oss.setContent(insScope.getContent());
						oss.setType(insScope.getType());
						ossSet.add(oss);
					}
					ost.setScopes(ossSet);
					if(!totalDao.save(ost)){
						return false;
					}
				}
			}else{
			OsTemplate ost = new OsTemplate();
			ost.setGongxuNum(ins.getProcessNumber());
			ost.setZhonglei("巡检");
			ost.setGongweiNum(ins.getWorkStation());
			ost.setXjtype(ins.getType());
			ost.setUsername(ins.getUsername());
			if(ins.getCreateDate()!=null){
				ost.setCreateDate(Util.DateToString(ins.getCreateDate(), "yyyy-MM-dd hh:mm:ss"));
			}
			ost.setProductType(ins.getProductType());
			ost.setPartNumber(ins.getPartNumber());
			//同步巡检项
			Set<OsScope> ossSet =new HashSet<OsScope>();
			Set<InsScope> inss = ins.getScope();
			List<InsScope> inssList = new ArrayList<InsScope>();
			for (InsScope insScope : inss) {
				inssList.add(insScope);
			}
			List<Integer> iList = new ArrayList<Integer>();
			for (InsScope insScope : inssList) {
				if(iList.contains(insScope.getId())){
					continue;
				}
				OsScope  oss = new OsScope();
				iList.add(insScope.getId());
				oss.setContent(insScope.getContent());
				oss.setType(insScope.getType());
				ossSet.add(oss);
			}
			ost.setScopes(ossSet);
			if(!totalDao.save(ost)){
				return false;
			}
			}
		}
		return true;
		}
		return false;
	}


	@Override
	public List<String> findAllmarkidlist() {
		String hql = "select markId from ProcardTemplate where markId <> '' and markId is not null" +
		"  and id in (select procardTemplate from ProcessTemplate where processNO is not null) order by markId";
		return totalDao.find(hql);
	}


	@Override
	public String updatetuzhi() {
		String sql = "SELECT a.fileName fileNamem,a.processNO processNo ,a.markId markId  FROM ta_sop_w_ProcessTemplateFile a JOIN  (SELECT partNumber,gongxuNum FROM ta_m_OsTemplate  WHERE  zhonglei = '巡检' and( filename  is  NULL or filename = '')) b ON  a.markId=b.partNumber AND a.processNO = b.gongxuNum WHERE a.processNO is not NULL   and status = '默认' and markId = 'M002389' and processNO = 5  and type = '工艺规范'";
		List<Map> list = 		totalDao.findBySql(sql);
		if(list!=null && list.size()>0 ){
				try {
					for(int i=0;i<list.size();i++){
						Map map = list.get(i);
						String markId = (String) map.get("markId");
						Integer processNo = (Integer) map.get("processNo");
						String filename = (String) map.get("fileNamem");
					String hql = "from OsTemplate where zhonglei = ? and gongxuNum=? and partNumber=?";
						OsTemplate ost =	(OsTemplate) totalDao.getObjectByCondition(hql, "巡检",processNo+"",markId);
						if(ost!=null){
							File f = new File(ServletActionContext.getServletContext().getRealPath(
									"/upload/file/processTz/" )
									+ filename);
					 		File[] fs = new File [] {f};
							String [] fileNames = new String [] {filename};
							String newfilename =		MKUtil.saveFile(fs, fileNames, "OsTemplate");
							ost.setFilename(newfilename);
							
							if(!totalDao.update(ost)){
								return "没有成功！";
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "出错了啊!"+e;
				}
			return "true";
		}
		return "error";
	}


	@Override
	public List<ProcardTemplate> findbanbenBymarkId(String markId) {
		if(markId!=null && markId.length()>0){
			String hql = "  from ProcardTemplate  where markId = ? and banBenNumber  not in (select banbenNumber from OsTemplate where partNumber = ? ) ";
			List<ProcardTemplate> banbenList =	totalDao.query(hql, markId,markId);
			return banbenList;
		}
		return null;
	}


	@Override
	public String updateXjbz(Integer id, Integer xjbzId) {
		if(id!=null){
			OsTemplate ot =	(OsTemplate) totalDao.get(OsTemplate.class, id);
			if(xjbzId!=null){
				String xjbz =	(String) totalDao.getObjectByCondition("select leixing from Waigoujianpinci where id =?  ", xjbzId);
				ot.setXjbzId(xjbzId);
				ot.setXjbz(xjbz);
			}
			return totalDao.update(ot)+"";
		}
		return null;
	}


	

	

}
