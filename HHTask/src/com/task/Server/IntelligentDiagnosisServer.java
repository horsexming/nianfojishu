package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.CampanyName;
import com.task.entity.CompanyVIP;
import com.task.entity.IntelligentDiagnosis;
import com.task.entity.ProcessName;
import com.task.entity.QRCodeKu;
import com.task.entity.Users;
import com.task.entity.diaoyan.ResearchReport;

public interface IntelligentDiagnosisServer {
	/**
	 * 修改
	 */
	public boolean update(CampanyName cn);
	/**
	 * 删除
	 */
	public boolean del(CampanyName cn);
	/**
	 * 查询所有(条件)
	 */
	public Map<Integer, Object> findcnListCondition(CampanyName cn,
			int pageNo, int pageSize,String pagestatus);
	/**
	 * 根据Id查询智能诊断系统
	 */
	public CampanyName findIsbyid(Integer id);
	
	/**
	 * 添加多个
	 */
	public boolean addMore(CampanyName cn);
	/**
	 * 查询所有企业会员
	 */
	public List<CompanyVIP> findAllcompanyviplist();
	/**
	 * 查询最大的测试编号
	 */
	public String findMaceshiNO();
	/**
	 * 根据测试公司Id查询测试详情;
	 */
	public List<IntelligentDiagnosis> findislistByid(Integer id);
	
	/**
	 * 添加流程内容
	 */
	public boolean addpn(ProcessName pn);
	/**
	 * 修改流程内容
	 */
	public boolean updatepn(ProcessName pn);
	/**
	 * 删除流程内容
	 */
	public boolean delpn(ProcessName pn);
	/**
	 * 查询所有
	 */
	public List<ProcessName> findAllpnlist();
	/**
	 * 查询所有（分页）
	 */
	public List<ProcessName> findAllpnlist(int pageNo, int pageSize);
	/**
	 * 条件查询所有(分页)
	 */
	public Map<Integer, Object> findpnListCondition(ProcessName pn,
			int pageNo, int pageSize);
	/**
	 * 添加多个模板;
	 */
	public String addpnlist(List<ProcessName> pnlList);
	/**
	 * 根据获得流程内容;
	 */
	public ProcessName getpnByid(Integer id);
	List findHz(Integer id);
	
	/**
	 * 根据Id获得user;
	 */
	public Users getuserByid(Integer id);
	
	/**
	 * 根据二维码编号获取二维码
	 */
	public QRCodeKu getqrcodekuByNo(String No);
	
	/**
	 * 检验一个编号只能产生一个二维码
	 */
	boolean isqrcodeByNo(String No);
	/**
	 * 添加二维码表信息
	 */
	boolean addQRCode(QRCodeKu qrcode,String strImg );
	/**
	 * 修改二维码表信息
	 */
	boolean updateQRCode(QRCodeKu oldqrcopde,QRCodeKu qrcode,String strImg);
	/**
	 * 查找最大工号
	 */
	String  findMaxCodeNO();
	/**
	 * 查询所有二维码信息；
	 */
	Map<Integer, Object> findAllQRCodeList(QRCodeKu qrcodeku,
			int pageNo, int pageSize,String status);
	/**
	 * 成为可发展下家的
	 */
	boolean updateQRCode1(QRCodeKu qrcodeku);
	/**
	 * 根据userId 查询二维码信息
	 */
	QRCodeKu findQRCodeKuByuserId(Integer userId);
	/**
	 * 
	 */
	int getcount();
	/**
	 * 添加调研报告
	 */
	String	addResearchReport(CampanyName cn);
	/**
	 * 根据调研公司Id查询所有相关的调研问题
	 */
	List<ResearchReport> findrrLisById(Integer id);
	
	String updateRr(List<ResearchReport> rrList,Integer id);
	boolean	delRr(ResearchReport rr);
}
