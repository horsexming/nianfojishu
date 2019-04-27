package com.task.ServerImpl.iao;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.iao.IAOServer;
import com.task.entity.Users;
import com.task.entity.iao.CarryGoods;
import com.task.entity.iao.IAOApply;
import com.task.util.Util;

public class IAOServerImpl implements IAOServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	/**添加出入申请**/
	public boolean saveIAO(IAOApply iaoApply) {
		Users user = (Users) Util.getLoginUser();
		if("他人(外部)".equals(iaoApply.getIaoPersonTyle())){
			iaoApply.setIaoPingzheng("barcode");			
			SimpleDateFormat yy=new SimpleDateFormat("yyyyMM");
			String barcodeStart=user.getPassword().getDeptNumber()+yy.format(new Date());
			String hql="select max(barcode) from IAOApply where barcode like '%"+barcodeStart+"%'";
			//String barcode=barcodeStart+"0001";
			List listmax=totalDao.find(hql);
			if(null!=listmax && listmax.size()>0 && null!=listmax.get(0)){
				String maxBarcode=(String)listmax.get(0);
				String num=maxBarcode.substring(barcodeStart.length());
				Integer intHao = Integer.parseInt(num);
			    intHao++;
			    String strHao = intHao.toString();
			    while (strHao.length() < 4){
			        strHao = "0" + strHao;
			    }
			    barcodeStart+=strHao;
			}else{
				barcodeStart+="0001";
			}
			iaoApply.setBarcode(barcodeStart);
			iaoApply.setIaoPingzheng("barcode");
			iaoApply.setPrintStatus("NO");
		}else{
			iaoApply.setIaoPingzheng("card");
			iaoApply.setBarcode(iaoApply.getOutCard());
			iaoApply.setDept(user.getDept());
		}
		iaoApply.setSubmitTime(totalDao.getDateTime(null));
		iaoApply.setStatus("申请");
		iaoApply.setCode(user.getCode());
		iaoApply.setApplyName(user.getName());
		iaoApply.setIaoStatus("初始 ");
		//处理审批环节
		return totalDao.save(iaoApply);
	}
	/**查询出入申请***/
	public Object[] findIAOApply(IAOApply iaoApply, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		Users user = (Users) Util.getLoginUser();
		String hql="";
		if("oneself".equals(tag)){//查看个人申请历史记录
			hql=" from IAOApply where code='"+user.getCode()+"' ";
		}else if("manager".equals(tag)){//查看所有历史记录
			hql=" from IAOApply where 1=1  ";
		}
		String subhql="";
		//拼接字符串
		if(null!=iaoApply){
			if(null!=iaoApply.getUsername() && !"".equals(iaoApply.getUsername())){
				subhql+=" and username='"+iaoApply.getUsername()+"'";
			}
			if(null!=iaoApply.getIaoStyle() && !"".equals(iaoApply.getIaoStyle())){
				subhql+=" and iaoStyle='"+iaoApply.getIaoStyle()+"'";
			}
			if(null!=iaoApply.getDept() && !"".equals(iaoApply.getDept())){
				subhql+=" and dept='"+iaoApply.getDept()+"'";
			}
			if(null!=iaoApply.getStatus() && !"".equals(iaoApply.getStatus())){
				subhql+=" and status='"+iaoApply.getStatus()+"'";
			}
			if(null!=iaoApply.getPlateNum() && !"".equals(iaoApply.getPlateNum())){
				subhql+=" and plateNum='"+iaoApply.getPlateNum()+"'";
			}
			if(null!=iaoApply.getIaoPingzheng() && !"".equals(iaoApply.getIaoPingzheng())){
				subhql+=" and iaoPingzheng='"+iaoApply.getIaoPingzheng()+"'";
			}
		}
		if(null!=startDate && !"".equals(startDate) && null!=endDate && !"".equals(endDate)){
			subhql+=" and submitTime between '"+startDate+"' and '"+endDate+"' ";
		}
		hql=hql+subhql+" order by submitTime desc";
		Object[] iaoAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		iaoAarr[0] = count;
		iaoAarr[1] = list;
		return iaoAarr;
	}
	@Override
	public String findIAOStyle(String tag) {
		String message="";
		if(null!=tag && !"".equals(tag)){
			String hql="select distinct("+tag+") from BaoxiaoDan";
			List<String> list = totalDao.query(hql);	
		    for(String d:list){
		    	message+=d.toString()+"|";
		    }
		}
		return message;
	}
	@Override
	public IAOApply getIAOApplyById(Integer id) {
		if(null!=id){
			return (IAOApply)totalDao.getObjectById(IAOApply.class, id);
		}
		return null;
	}
	/**更新出入申请信息**/
	public boolean updateIAOApp(IAOApply iaoApply, String tag) {
		int id=iaoApply.getId();
		IAOApply iao=(IAOApply)totalDao.getObjectById(IAOApply.class, id);
		BeanUtils.copyProperties(iaoApply,iao,new String[]{"code","code","applyName","submitTime","status","barcode","iaoStatus","ascTime","printStatus"});
		return totalDao.update(iao);
	}
	@Override
	public boolean deleteIAO(IAOApply iaoApply) {
		if(null!=iaoApply){
			return totalDao.delete(iaoApply);
		}
		return false;
	}
	@Override
	/***导出EXL**/
	public void explorerEXL(IAOApply iaoApply, String startDate, String endDate) {
		String hql=" from IAOApply where 1=1  ";
		String subhql="";
		//拼接字符串
		if(null!=iaoApply){
			if(null!=iaoApply.getUsername() && !"".equals(iaoApply.getUsername())){
				subhql+=" and username='"+iaoApply.getUsername()+"'";
			}
			if(null!=iaoApply.getIaoStyle() && !"".equals(iaoApply.getIaoStyle())){
				subhql+=" and iaoStyle='"+iaoApply.getIaoStyle()+"'";
			}
			if(null!=iaoApply.getDept() && !"".equals(iaoApply.getDept())){
				subhql+=" and dept='"+iaoApply.getDept()+"'";
			}
			if(null!=iaoApply.getStatus() && !"".equals(iaoApply.getStatus())){
				subhql+=" and status='"+iaoApply.getStatus()+"'";
			}
			if(null!=iaoApply.getPlateNum() && !"".equals(iaoApply.getPlateNum())){
				subhql+=" and plateNum='"+iaoApply.getPlateNum()+"'";
			}
			if(null!=iaoApply.getIaoPingzheng() && !"".equals(iaoApply.getIaoPingzheng())){
				subhql+=" and iaoPingzheng='"+iaoApply.getIaoPingzheng()+"'";
			}
		}
		if(null!=startDate && !"".equals(startDate) && null!=endDate && !"".equals(endDate)){
			subhql+=" and submitTime between '"+startDate+"' and '"+endDate+"' ";
		}
		hql=hql+subhql+" order by submitTime desc";
		List list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("出入管理数据信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("出入管理数据信息", 0);
			ws.setColumnView(4, 20);
			ws.setColumnView(3, 10);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 12);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			jxl.write.Label label0 = new Label(0, 0, "出入管理数据", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 17, 0);
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "进出人姓名", wc));
			ws.addCell(new jxl.write.Label(2, 1, "进出对象", wc));
			ws.addCell(new jxl.write.Label(3, 1, "进出凭证", wc));
			ws.addCell(new jxl.write.Label(4, 1, "进出条码", wc));
			ws.addCell(new jxl.write.Label(5, 1, "类型", wc));
			ws.addCell(new jxl.write.Label(6, 1, "状态", wc));
			ws.addCell(new jxl.write.Label(7, 1, "申请人", wc));
			ws.addCell(new jxl.write.Label(8, 1, "申请人部门", wc));
			ws.addCell(new jxl.write.Label(9, 1, "申请时间", wc));
			ws.addCell(new jxl.write.Label(10, 1, "申请出门时间", wc));
			ws.addCell(new jxl.write.Label(11, 1, "预计返回时间", wc));
			ws.addCell(new jxl.write.Label(12, 1, "实际出门时间", wc));
			ws.addCell(new jxl.write.Label(13, 1, "实际返回时间", wc));
			ws.addCell(new jxl.write.Label(14, 1, "随从人员", wc));
			ws.addCell(new jxl.write.Label(15, 1, "出门原因描述", wc));
			ws.addCell(new jxl.write.Label(16, 1, "车牌号", wc));			
			for (int i = 0; i < list.size(); i++) {
				IAOApply iao = (IAOApply) list.get(i);
				String wupin = "";
				Set<CarryGoods> pset = iao.getIaoCarryGoods();
				for (CarryGoods p : pset) {
					wupin = wupin + p.getGoodsName() + ";";
				}
				String pingzheng="条码";
				if("card".equals(iao.getIaoPingzheng())){
					pingzheng="员工卡";
				}
				
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, iao.getUsername(), wc));
				ws.addCell(new Label(2, i + 2, iao.getIaoPersonTyle(), wc));
				ws.addCell(new Label(3, i + 2, pingzheng, wc));
				ws.addCell(new Label(4, i + 2, iao.getBarcode(), wc));
				ws.addCell(new Label(5, i + 2, iao.getIaoStyle(), wc));
				ws.addCell(new Label(6, i + 2, iao.getStatus(), wc));
				ws.addCell(new Label(7, i + 2, iao.getApplyName(), wc));
				ws.addCell(new Label(8, i + 2, iao.getDept(), wc));
				ws.addCell(new Label(9, i + 2, iao.getSubmitTime(), wc));
				ws.addCell(new Label(10, i + 2, iao.getApplyOutTime(), wc));
				ws.addCell(new Label(11, i + 2, iao.getApplyInTime(), wc));
				ws.addCell(new Label(12, i + 2, iao.getGetOutTime(), wc));
				ws.addCell(new Label(13, i + 2, iao.getGetInTime(), wc));
				ws.addCell(new Label(14, i + 2, iao.getFollowPerson(), wc));
				ws.addCell(new Label(15, i + 2, iao.getResult(), wc));
				ws.addCell(new Label(16, i + 2, iao.getPlateNum(), wc));				
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
}
