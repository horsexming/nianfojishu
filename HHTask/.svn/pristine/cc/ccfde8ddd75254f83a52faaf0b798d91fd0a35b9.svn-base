package com.task.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.QualityccServer;
import com.task.entity.Qualitycheckta;
import com.task.entity.Qualitycheckto;
import com.task.entity.Qualityta;
import com.task.entity.Qualityto;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Util;

public class QualityccAction extends ActionSupport {
	private Qualitycheckto qualitycheckto;
	private Qualitycheckta qualitycheckta;
	private Qualityto qualityto;
	private Qualityta qualityta;
	private QualityccServer qualityccServer;
	private List list;
	private String ygcode;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private String pageStatus;

	// 创建检验产品类型
	public String addQualitycheckto() {
		qualityccServer.addQualitycheckto(qualitycheckto);// boolean bo =
		return "qualitychecktolist";
	}

	// 分页查询检验产品类型
	public String qualitychecktoList() {
		list = qualityccServer.findQualitychecktolist(Integer.parseInt(cpage),
				pageSize);
		this.setUrl("QualityccAction!qualitychecktoList.action");
		int count = qualityccServer.qualitychecktoCount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "qualitychecktolist0";
	}

	// 删除检验产品类型
	public String deleteQualitycheckto() {
		qualityccServer.deleteQualitycheckto(qualitycheckto);
		return "qualitychecktolist";
	}

	// 查询检验产品项目
	public String qualitychecktaList() {
		qualitycheckto = qualityccServer.findQualitychecktoById(qualitycheckto
				.getId());
		list = qualityccServer.findQualitychecktas(qualitycheckto.getId());
		return "addQualitycheckta";
	}

	// 添加检验产品项目
	public String addQualitycheckta() {
		qualitycheckto = qualityccServer.findQualitychecktoById(qualitycheckto
				.getId());
		qualitycheckta.setQualitycheckto(qualitycheckto);
		qualityccServer.addQualitycheckta(qualitycheckta);
		list = qualityccServer.findQualitychecktas(qualitycheckto.getId());
		return "addQualitycheckta";
	}

	// 删除检验项目
	public String deleteQualitycheckta() {
		qualityccServer.deleteQualitycheckta(qualitycheckta);
		qualitycheckto = qualityccServer.findQualitychecktoById(qualitycheckto
				.getId());
		list = qualityccServer.findQualitychecktas(qualitycheckto.getId());
		return "addQualitycheckta";
	}

	// 查询全部模板表 android
	public void findQualitytoAll() {
		list = qualityccServer.findQualitycheckto();
		List newList = new ArrayList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Qualitycheckto qt = (Qualitycheckto) list.get(i);
				qt.setBeizhu("赵铭赵大爷");
				qt.setQualitycheckta(null);
				if(qt.getRenyuangh()!=null&&ygcode.equals(qt.getRenyuangh())){
					newList.add(qt);
				}		
			}
			MKUtil.writeJSON(true, "111", newList);
		}
	}

	// 分页查询审查表
	public String findQualitytoList() {
		list = qualityccServer.findQualityByPage(Integer.parseInt(cpage),
				pageSize);
		this.setUrl("QualityccAction!findQualitytoList.action");
		int count = qualityccServer.qualitytoCount();
		Integer pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "qualityto";
	}

	// 分页查询审查表审核
	public String findQualitytoLists() {
		list = qualityccServer.findQualityByPage(Integer.parseInt(cpage),
				pageSize);
		this.setUrl("QualityccAction!findQualitytoList.action");
		int count = qualityccServer.qualitytoCount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");

		return "qualitytos";
	}

	// 删除审查表
	public String deleteQualityto() {
		qualityccServer.deleteQualityto(qualityto);
		return "qualitytolist";
	}

	// 查询选择质检磨具
	public void findQualitycheckto() {
		list = qualityccServer.findQualitycheckto();
		List newList = new ArrayList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Qualitycheckto qc = (Qualitycheckto) list.get(i);
				qc.setQualitycheckta(null);
				newList.add(qc);
			}
		}
		MKUtil.writeJSON(newList);
	}

	// 查看需要质检的项目 android
	public void addYeMian() {
		// qualityto = qualityccServer.findQualitytoById(qualityto.getId());
		qualitycheckto = qualityccServer.findQualitychecktoById(qualitycheckto
				.getId());
		list = qualityccServer.findQualitychecktas(qualitycheckto.getId());
		List newList = new ArrayList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Qualitycheckta qc = (Qualitycheckta) list.get(i);
				qc.setQualitycheckto(null);
				qc.setBeizhu("赵铭赵大爷");
				newList.add(qc);
			}
		}
		MKUtil.writeJSON(true, null, newList);
	}

	// 保存质检结果
	public void addQualityta() {
		// String url2=null;
		// try {
		// url2=new String(url.getBytes("iso-8859-1"),"utf-8");
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		List<Qualityta> lista = JSON.parseArray(url, Qualityta.class);
		qualityta = lista.get(0);
		int qid = qualityta.getQualitychecktoid();
		qualitycheckta = qualityccServer.findQualitychecktaById(qid);
		qualitycheckto = qualitycheckta.getQualitycheckto();
		qualityto = new Qualityto();
		qualityccServer.addQualityto(qualityto, qualitycheckto);
		int t = 0;
		for (int i = 0; i < lista.size(); i++) {
			Qualityta qa = lista.get(i);
			qa.setQualityto(qualityto);
			int q = qa.getQualitychecktoid();
			Qualitycheckta qta = qualityccServer.findQualitychecktaById(q);
			int a = qta.getXishuz();
			int b = qa.getShuzi();
			t = t + a * b;
			qualityccServer.addQualityta(qa, qta);
		}
		t = 100 - t / 5;
		qualityto.setKbz("" + t);
		qualityccServer.updateQualityto(qualityto);

	}

	// 进入质量表最后添加
	public String findQualityta() {
		qualityto = qualityccServer.findQualitytoById(qualityto.getId());
		list = qualityccServer.findQualitytas(qualityto.getId());
		return "qualitytoo";
	}

	// 查看完整质量表
	public String lookQualityta() {
		qualityto = qualityccServer.findQualitytoById(qualityto.getId());
		list = qualityccServer.findQualitytas(qualityto.getId());
		return "qualitytoox";
	}

	// 审批查看弹出层
	public String lookQualitytat() {
		qualityto = qualityccServer.findQualitytoById(qualityto.getId());
		list = qualityccServer.findQualitytas(qualityto.getId());

		return "qualitytooxt";
	}

	// 最后保存质量表
	public String updateQualitytoo() {
		Qualityto qo = new Qualityto();
		qo = qualityccServer.findQualitytoById(qualityto.getId());
		qo.setBianhao(qualityto.getBianhao());
		qo.setPici(qualityto.getPici());
		qo.setJlun(qualityto.getJlun());
		qo.setFaan(qualityto.getFaan());
		qo.setShuoming1(qualityto.getShuoming1());
		qo.setShuoming2(qualityto.getShuoming2());
		qo.setStatu("已完成");
		qo.setShy(Util.getLoginUser().getName());
		qualityccServer.updateQualityto(qo);
		return "lookqualityto";
	}

	// 审批
	public String updateQualitytoos() {
		Qualityto qo = new Qualityto();
		qo = qualityccServer.findQualitytoById(qualityto.getId());
		qualitycheckto = qualityccServer.findQualitychecktoByAddtime(qo
				.getJcbh());
		qualitycheckto.setShenpi("已审批");
		qualitycheckto.setPici(null);
		qualitycheckto.setRenyuan(null);
		qualitycheckto.setRenyuangh(null);
		qualityccServer.updateQualitycheckto(qualitycheckto);
		qo.setStatu("已审批");
		qo.setShr(Util.getLoginUser().getName());
		qualityccServer.updateQualityto(qo);
		return "qualitytooxts";
	}
	//进入指定人批次界面
	public String inQualitytoRp(){
		qualitycheckto= qualityccServer.findQualitychecktoById(qualitycheckto.getId());
		return "qualitychecktorp";
	}
	//保存指定的人批
	public String updateQualitychecktoRp(){
		Qualitycheckto qo = new Qualitycheckto();
		qo=qualityccServer.findQualitychecktoById(qualitycheckto.getId());
		qo.setRenyuan(qualitycheckto.getRenyuan());
		qo.setRenyuangh(qualitycheckto.getRenyuangh());
		qo.setPici(qualitycheckto.getPici());
		qualityccServer.updateQualitycheckto(qo);
		return "qualitychecktolist";
	}
	public Qualitycheckto getQualitycheckto() {
		return qualitycheckto;
	}

	public void setQualitycheckto(Qualitycheckto qualitycheckto) {
		this.qualitycheckto = qualitycheckto;
	}

	public Qualitycheckta getQualitycheckta() {
		return qualitycheckta;
	}

	public void setQualitycheckta(Qualitycheckta qualitycheckta) {
		this.qualitycheckta = qualitycheckta;
	}

	public Qualityto getQualityto() {
		return qualityto;
	}

	public void setQualityto(Qualityto qualityto) {
		this.qualityto = qualityto;
	}

	public Qualityta getQualityta() {
		return qualityta;
	}

	public void setQualityta(Qualityta qualityta) {
		this.qualityta = qualityta;
	}

	public QualityccServer getQualityccServer() {
		return qualityccServer;
	}

	public void setQualityccServer(QualityccServer qualityccServer) {
		this.qualityccServer = qualityccServer;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public void setYgcode(String ygcode) {
		this.ygcode = ygcode;
	}

	public String getYgcode() {
		return ygcode;
	}
}
