package com.task.ServerImpl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.connection.UserSuppliedConnectionProvider;
import org.springframework.beans.BeanUtils;

import sun.misc.BASE64Decoder;
import sun.security.x509.IssuerAlternativeNameExtension;

import com.task.Dao.TotalDao;
import com.task.Server.IntelligentDiagnosisServer;
import com.task.entity.CampanyName;
import com.task.entity.CompanyVIP;
import com.task.entity.IntelligentDiagnosis;
import com.task.entity.ProcessName;
import com.task.entity.QRCodeKu;
import com.task.entity.Users;
import com.task.entity.diaoyan.ResearchReport;
import com.task.util.Util;

public class IntelligentDiagnosisServerImpl implements
		IntelligentDiagnosisServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean del(CampanyName cn) {
		if (cn != null) {
			CampanyName oldcn = (CampanyName) totalDao.get(CampanyName.class,
					cn.getId());
			Set<IntelligentDiagnosis> isset = oldcn.getIsset();
			for (IntelligentDiagnosis is : isset) {
				totalDao.delete(is);
			}
			oldcn.setIsset(null);
			return totalDao.delete(oldcn);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findcnListCondition(CampanyName cn, int pageNo,
			int pageSize,String pagestatus) {
		if (cn == null) {
			cn = new CampanyName();
		}
		String groups_hql = " and (groups <> '调研' or groups is null or groups = '' ) ";
		if(pagestatus!=null && "person".equals(pagestatus)){
			Users user = Util.getLoginUser();
			cn.setUserId(user.getId());
		}else if("diaoyan".equals(pagestatus)){
			groups_hql = " and groups = '调研'";
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(cn, null);
		int count = totalDao.getCount(hql+groups_hql+" order by id desc");
		List<CampanyName> isList = (List<CampanyName>) totalDao.findAllByPage(
				hql+groups_hql+" order by id desc", pageNo, pageSize);
		map.put(1, isList);
		map.put(2, count);
		return map;
	}

	@Override
	public CampanyName findIsbyid(Integer id) {
		if (id != null && id > 0) {
			return (CampanyName) totalDao.get(CampanyName.class, id);
		}
		return null;
	}

	@Override
	public boolean update(CampanyName cn) {
		if (cn != null) {
			CampanyName oldcn = (CampanyName) totalDao.get(CampanyName.class,
					cn.getId());
			Set<IntelligentDiagnosis> isset = oldcn.getIsset();
			for (IntelligentDiagnosis is : isset) {
				totalDao.delete(is);
			}
			BeanUtils.copyProperties(cn, oldcn, new String[] { "id" });
			List<IntelligentDiagnosis> isList = cn.getIslist();
			Set<IntelligentDiagnosis> isset1 = new HashSet<IntelligentDiagnosis>();
			Float total = 0f;
			Float totalman = 0f;
			Float totalpaci = 0f;
			for (IntelligentDiagnosis is1 : isList) {
				if (is1.getPostnum() > 0 && is1.getPcai() > 0) {
					is1.setTcai(is1.getPostnum() * is1.getPcai());
				}
				totalman += is1.getPostnum();
				total += is1.getTcai();
				isset1.add(is1);
			}
			oldcn.setTotalman(totalman);
			oldcn.setTotal(total);
			if (totalman > 0 && totalpaci > 0) {
				oldcn.setTotalpaci(total / totalman);
			}
			oldcn.setIsset(isset1);
			return totalDao.update(oldcn);
		}
		return false;
	}

	@Override
	public boolean addMore(CampanyName cn) {
		if (cn != null) {
			String hql = "select ceshiNo from CampanyName where campanyname=?";
			String ceshiNo1 = (String) totalDao.getObjectByCondition(hql, cn
					.getCampanyname());
			if (ceshiNo1 == null || ceshiNo1.equals(cn.getCampanyname())) {
				List<IntelligentDiagnosis> islist = cn.getIslist();
				Set<IntelligentDiagnosis> isset = new HashSet<IntelligentDiagnosis>();
				Float total = 0f;
				Float totalman = 0f;
				for (IntelligentDiagnosis is : islist) {
					if(is.getIsxuyao()==null){
						is.setIsxuyao("no");
					}
					if (is.getPostnum() > 0 && is.getPcai() > 0) {
						is.setTcai(is.getPostnum() * is.getPcai());
					} else {
						is.setTcai(0F);
					}
					totalman += is.getPostnum();
					total += is.getTcai();
					isset.add(is);
				}
				cn.setTotalman(totalman);
				cn.setTotal(total);
				if (totalman > 0 && total > 0) {
					cn.setTotalpaci(total / totalman);
				}
				cn.setIsset(isset);
				return totalDao.save2(cn);

			}
		}
		return false;
	}

	@Override
	public List<CompanyVIP> findAllcompanyviplist() {
		String hql = "from CompanyVIP ";
		return totalDao.find(hql);
	}

	@Override
	public String findMaceshiNO() {
		String hql = " select max(ceshiNo) from  CampanyName ";
		String ceshiNo = (String) totalDao.getObjectByCondition(hql);
		if (ceshiNo != null) {
			Integer ceshiNonum = Integer.parseInt(ceshiNo) + 1000001;
			ceshiNo = ceshiNonum.toString().substring(1, 7);
		} else {
			ceshiNo = "000001";
		}
		return ceshiNo;
	}

	@Override
	public List<IntelligentDiagnosis> findislistByid(Integer id) {
		String hql = "from IntelligentDiagnosis where campanyname = " + id
				+ " order by id ";
		return totalDao.find(hql);
	}

	@Override
	public boolean addpn(ProcessName pn) {
		if (pn != null) {
			return totalDao.save(pn);
		}
		return false;
	}

	@Override
	public boolean delpn(ProcessName pn) {
		if (pn != null) {
			return totalDao.delete(pn);
		}
		return false;
	}

	@Override
	public List<ProcessName> findAllpnlist() {
		String hql = "from ProcessName ";
		return totalDao.find(hql);
	}

	@Override
	public Map<Integer, Object> findpnListCondition(ProcessName pn, int pageNo,
			int pageSize) {
		if (pn == null) {
			pn = new ProcessName();
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(pn, null);
		int count = totalDao.getCount(hql);
		List<ProcessName> isList = (List<ProcessName>) totalDao.findAllByPage(
				hql, pageNo, pageSize);
		map.put(1, isList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean updatepn(ProcessName pn) {
		if (pn != null) {
			return totalDao.update(pn);
		}
		return false;
	}

	@Override
	public String addpnlist(List<ProcessName> pnlList) {
		if (pnlList != null && pnlList.size() > 0) {
			boolean bool = true;
			String msg = "";
			for (ProcessName pn : pnlList) {
				String hql = "from ProcessName where name=?";
				List<ProcessName> oldpnList =  totalDao.query(hql, pn
						.getName());
				if (oldpnList == null || oldpnList.size()>0) {
					if (!totalDao.save(pn)) {
						bool = false;
					}
				} else {
					return pn.getName() + "之前已添加过，无需添加";
				}

			}
			return bool + "";
		}
		return false + "";
	}

	@Override
	public ProcessName getpnByid(Integer id) {
		if (id != null && id > 0) {
			return (ProcessName) totalDao.get(ProcessName.class, id);
		}
		return null;
	}

	@Override
	public List findHz(Integer id) {
		if (id != null && id > 0) {
			CampanyName campanyName = (CampanyName) totalDao.get(
					CampanyName.class, id);
			if (campanyName != null) {
				// 每年总成本(需要&人工)
				String hql = "select sum(postnum*pcai) from IntelligentDiagnosis where campanyname.id = ? and isxuyao='yes' and isman='yes'";
				Float allCb = (Float) totalDao.getObjectByCondition(hql,
						campanyName.getId());
				// 每年总节省(需要&人工&节省)
				String hql_js = "select sum(postnum*pcai) from IntelligentDiagnosis where campanyname.id = ? and isxuyao='yes' and isman='yes' and iseconomize='yes' ";
				Float alljs = (Float) totalDao.getObjectByCondition(hql_js,
						campanyName.getId());
				List list = new ArrayList();
				list.add(allCb);
				list.add(allCb - alljs);
				list.add(alljs);
				return list;

			}
		}
		return null;
	}

	@Override
	public Users getuserByid(Integer id) {
		if(id!=null && id>0){
			return (Users) totalDao.get(Users.class, id);
		}
		return null;
	}

	@Override
	public QRCodeKu getqrcodekuByNo(String No) {
		String hql = "from QRCodeKu where No=?";
		return (QRCodeKu) totalDao.getObjectByCondition(hql, No);
	}

	@Override
	public boolean isqrcodeByNo(String No) {
		String hql = "from QRCodeKu where No=?";
		List<QRCodeKu> qrcodekuList = (List<QRCodeKu>) totalDao.query(hql, No);
		if(qrcodekuList!=null && qrcodekuList.size()==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean addQRCode(QRCodeKu qrcode,String strImg) {
		//进行Base64解码并生成图片  
		if(qrcode!=null && qrcode.getUserId()>0){
			String hql = "from QRCodeKu where userId =?";
			QRCodeKu oldqrCodeKu =(QRCodeKu) totalDao.getObjectByCondition(hql, qrcode.getUserId());
			if(oldqrCodeKu!=null){
				updateQRCode(oldqrCodeKu,qrcode,strImg);
			}else{
				if (strImg == null) {
					return false;  
				}// 图像数据为空  
				
				BASE64Decoder decoder = new BASE64Decoder(); 
				boolean bool = false;
				try {  
				// Base64解码  
				byte[] bytes = decoder.decodeBuffer(strImg);  
				for (int i = 0; i < bytes.length; ++i) {  
				if (bytes[i] < 0) {// 调整异常数据  
				bytes[i] += 256;  
				}  
				}  
				// 生成jpeg图片  
				String fileName = Util.getDateTime("yyyymmddHHMMss")+qrcode.getNo()+".jpg";
				
				String imgFilePath = ServletActionContext
				.getServletContext().getRealPath("/upload/file")
				+ "/" + fileName;
				// 备份到项目
				String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file"
						+ "/" + fileName;
				OutputStream out = new FileOutputStream(imgFilePath);  
				OutputStream out1 = new FileOutputStream(beiFenfileRealPath);
				out.write(bytes);  
				out.flush();  
				out.close();  
				out1.write(bytes);  
				out1.flush();  
				out1.close();  
				qrcode.setName(fileName);
				 bool = true;
				} catch (Exception e) { 
					e.printStackTrace();
				return false;  
				}
				if(bool){
					qrcode.setIschild("no");
					if(qrcode.getFatherNo()!=null && !"".endsWith(qrcode.getFatherNo())){
						QRCodeKu fqrcodeku =	getqrcodekuByNo(qrcode.getFatherNo());
						if(fqrcodeku!=null){
							qrcode.setFphoneNumber(fqrcodeku.getPhoneNumber());
							qrcode.setFuserName(fqrcodeku.getUserName());
						}
					}
					qrcode.setAddtime(Util.getDateTime());
					return totalDao.save(qrcode);
				}
			}
		}
		return false;  
		
	}

	@Override
	public boolean updateQRCode(QRCodeKu oldqrcopde,QRCodeKu qrcode,String strImg) {
		if(qrcode!=null && oldqrcopde!=null){
			if (strImg == null) {
				return false;  
			}// 图像数据为空  
			
			BASE64Decoder decoder = new BASE64Decoder(); 
			boolean bool = false;
			try {  
			// Base64解码  
			byte[] bytes = decoder.decodeBuffer(strImg);  
			for (int i = 0; i < bytes.length; ++i) {  
			if (bytes[i] < 0) {// 调整异常数据  
			bytes[i] += 256;  
			}  
			}  
			// 生成jpeg图片  
			String fileName = Util.getDateTime("yyyymmddHHMMss")+oldqrcopde.getNo();
			
			String imgFilePath = ServletActionContext
			.getServletContext().getRealPath("/upload/file/QRCodeKu")
			+ "/" + fileName;
			// 备份到项目
			String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file/QRCodeKu"
					+ "/" + fileName;
			OutputStream out = new FileOutputStream(imgFilePath);  
			OutputStream out1 = new FileOutputStream(beiFenfileRealPath);
			out.write(bytes);  
			out.flush();  
			out.close();  
			out1.write(bytes);  
			out1.flush();  
			out1.close();  
			oldqrcopde.setName(fileName);
			 bool = true;
			} catch (Exception e) {  
			return false;  
			}
			if(bool){
				totalDao.update(oldqrcopde);
			}
			
		}
		return false;
	}

	@Override
	public String findMaxCodeNO() {
		String hql = " select max(code) from  Users where code like '%H%'";
		String code = (String) totalDao.getObjectByCondition(hql);
		if (code != null) {
			Integer codeNonum =0;
			try {
				codeNonum = Integer.parseInt(code.substring(1)) + 1001;
			} catch (Exception e) {
				e.printStackTrace();
				codeNonum = 1001;
			}
			code = codeNonum.toString().substring(1, 4);
			code = "H"+code;
		} else {
			code = "H"+"001";
		}
		return code;
	}

	@Override
	public Map<Integer, Object> findAllQRCodeList(QRCodeKu qrcodeku, int pageNo,
			int pageSize, String status) {
		if (qrcodeku == null) {
			qrcodeku = new QRCodeKu();
		}
		Users user =	Util.getLoginUser();
		
		String other = "";
		if("person".equals(status)){
			QRCodeKu logQRCodeKu =	findQRCodeKuByuserId(user.getId());
			if(logQRCodeKu!=null && logQRCodeKu.getNo()!=null && !"".equals(logQRCodeKu.getNo())){
				other = " fatherNo ='"+logQRCodeKu.getNo()+"'";
			}
			
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(qrcodeku, other);
		int count = totalDao.getCount(hql);
		List<QRCodeKu> isList = (List<QRCodeKu>) totalDao.findAllByPage(
				hql, pageNo, pageSize);
		map.put(1, isList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean updateQRCode1(QRCodeKu qrcodeku) {
		if(qrcodeku!=null && qrcodeku.getId()!=null){
			qrcodeku =	(QRCodeKu) totalDao.get(QRCodeKu.class, qrcodeku.getId());
			qrcodeku.setIschild("yes");
			return totalDao.update(qrcodeku);
		}
		return false;
	}

	@Override
	public QRCodeKu findQRCodeKuByuserId(Integer userId) {
		if(userId == null){
			Users user =Util.getLoginUser();
			userId = user.getId();
		}
		String hql = "from QRCodeKu where userId=?";
		
		return (QRCodeKu) totalDao.getObjectByCondition(hql, userId);
	}

	@Override
	public List<ProcessName> findAllpnlist(int pageNo, int pageSize) {
		String hql = "from ProcessName";
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public int getcount() {
		String hql = "from ProcessName";
		return totalDao.getCount(hql);
	}

	@Override
	public String addResearchReport(CampanyName cn) {
		Users user =	Util.getLoginUser();
		if(user == null){
			return "请先登录!~";
		}
		if(cn!=null){
			List<ResearchReport> rrList =cn.getRrList();
			Set<ResearchReport> rrSet = new HashSet<ResearchReport>();
			if(rrList!=null && rrList.size()>0){
				for (ResearchReport rr : rrList) {
					rr.setAddTime(Util.getDateTime());
					rr.setAddUsersCode(user.getCode());
					rr.setAddUsersName(user.getName());
					rr.setCampanyName(cn);
					rrSet.add(rr);
				}
			}
			cn.setRrSet(rrSet);
			return totalDao.save(cn)+"";
		}
		return null;
	}

	@Override
	public List<ResearchReport> findrrLisById(Integer id) {
		if(id!=null){
			return totalDao.query(" from ResearchReport where campanyName.id =?  order by dept", id);
		}
		return null;
	}

	@Override
	public String updateRr(List<ResearchReport> rrList,Integer id) {
		Users user =	Util.getLoginUser();
		if(user == null){
			return "请先登录!~";
		}
		if(rrList!=null && rrList.size()>0){
			CampanyName cn =	(CampanyName) totalDao.get(CampanyName.class, id);
			for (ResearchReport rr : rrList) {
				if(rr.getId()!=null){
					ResearchReport  oldrr = (ResearchReport) totalDao.get(ResearchReport.class, rr.getId());
					oldrr.setCases(rr.getCases());
					oldrr.setDept(rr.getDept());
					oldrr.setExpectedEffec(rr.getExpectedEffec());
					oldrr.setSolution(rr.getSolution());
					totalDao.update(oldrr);
				}else{
					rr.setAddTime(Util.getDateTime());
					rr.setAddUsersCode(user.getCode());
					rr.setAddUsersName(user.getName());
					rr.setCampanyName(cn);
					totalDao.save(rr);
				}
			}
			return "true";
		}
		return null;
	}

	@Override
	public boolean delRr(ResearchReport rr) {
		if(rr!=null && rr.getId()!=null){
			return totalDao.delete(rr);
		}
		return false;
	}


	

}
