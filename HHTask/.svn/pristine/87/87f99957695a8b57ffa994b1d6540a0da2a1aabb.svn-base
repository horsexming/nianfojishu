package com.task.ServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IMaintainService;
import com.task.entity.Maintain;
import com.task.entity.Store;
import com.task.entity.VOMaintain;

public class MaintainServiceImpl implements IMaintainService {
	
	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public void add(Maintain ma) {
		// TODO Auto-generated method stub
		totalDao.save(ma);
	}

	@Override
	public Maintain getMaintainById(int id) {
		// TODO Auto-generated method stub
		return (Maintain) totalDao.getObjectById(Maintain.class, id);
	}

	@Override
	public Object[] queryMaintain(Map map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Maintain m where 1=1 ";
		if (map != null) {
			if (map.get("number") != null) 
				hql += " and m.number like '%" + map.get("number") + "%'";
			if(map.get("name") != null)
				hql += " and m.matetag like '%" + map.get("name") + "%'";
			if(map.get("state")!=null)
				hql += " and m.state = '"+map.get("state")+"'";
		}
		hql += " order by m.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[]{list,count};
	}
	
	@Override
	public void exportExcel(Map map) {
		String hql = "from Maintain m where 1=1 ";
		if(map != null ){
			if(map.get("state")!=null){
				hql += " and m.state = '"+map.get("state")+"'";
			}
		}
		hql += " order by m.id desc";
		List list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
							+ new String("报检数据".getBytes("GB2312"), "8859_1")
							+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("报检数据", 0);
			ws.setColumnView(0, 18);
			ws.setColumnView(1, 24);
			ws.setColumnView(2, 24);
			ws.setColumnView(6, 16);
			ws.setColumnView(7, 16);
			ws.setColumnView(8, 24);
			ws.addCell(new Label(0,0,"编号"));
			ws.addCell(new Label(1,0,"名称"));
			ws.addCell(new Label(2,0,"规格"));
			ws.addCell(new Label(3,0,"单位"));
			ws.addCell(new Label(4,0,"数量"));
			ws.addCell(new Label(5,0,"状态"));
			ws.addCell(new Label(6,0,"报检时间"));
			ws.addCell(new Label(7,0,"修复时间"));
			ws.addCell(new Label(8,0,"备注"));
			for(int i = 0 ;i<list.size();i++){
				Maintain ma = (Maintain) list.get(i);
				ws.addCell(new Label(0,i+1,ma.getNumber()));
				ws.addCell(new Label(1,i+1,ma.getMatetag()));
				ws.addCell(new Label(2,i+1,ma.getFormat()));
				ws.addCell(new Label(3,i+1,ma.getUnit()));
				ws.addCell(new jxl.write.Number(4,i+1,ma.getAmount()!=null?ma.getAmount():1));
				ws.addCell(new Label(5,i+1,ma.getState()));
				ws.addCell(new Label(6,i+1,ma.getFixDate()!=null?ma.getFixDate().toString():""));
				ws.addCell(new Label(7,i+1,ma.getRestorDate()!=null?ma.getRestorDate().toString():""));
				ws.addCell(new Label(8,i+1,ma.getMore()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public String update(Maintain ma) {
		String msg = "";
		Maintain oldMaintain = getMaintainById(ma.getId());
		Store sto = oldMaintain.getStore();
		float amount = sto.getCurAmount()+oldMaintain.getAmount();
		if(amount >= ma.getAmount()){
			sto.setCurAmount(amount - ma.getAmount());
			BeanUtils.copyProperties(ma, oldMaintain, new String[] {"id","store","restorDate"});
			msg = "修改成功!";
			totalDao.update(oldMaintain);
		}else{
			msg = "修改失败!修改数量大于当前库存数量!当前库存量为:" + sto.getCurAmount();
		}
		return msg;
	}
	
	@Override
	public void del(Maintain ma) {
		totalDao.delete(ma);
	}

	@Override
	public String addRelation(VOMaintain vom) {
		String msg = "";
		Integer stoId = vom.getId();
		Store sto = (Store) totalDao.getObjectById(Store.class, stoId);
		if(sto != null){
			if(sto.getCurAmount() >= vom.getAmount()){
				sto.setCurAmount(sto.getCurAmount()-vom.getAmount());
				Maintain ma = new Maintain();
				BeanUtils.copyProperties(vom, ma,new String[]{"id"});
				sto.getMaintains().add(ma);
				ma.setStore(sto);
				add(ma);
			}else{
				msg = "当前可维修数量为: " + sto.getCurAmount();
			}
		}else{
			msg = "添加报修失败!";
		}
		return msg;
	}

	@Override
	public void delMaintainById(Integer id) {
		String msg = "";
		Maintain ma = getMaintainById(id);
		Store sto = ma.getStore();
		sto.setCurAmount(sto.getCurAmount()+ma.getAmount());
		sto.getMaintains().remove(ma);
		ma.setStore(null);
		del(ma);
	}

	@Override
	public void repair(Integer id) {
		Maintain ma = getMaintainById(id);
		Store st = ma.getStore();
		st.setCurAmount(st.getCurAmount()+ma.getAmount());
		ma.setState("已修复");
		ma.setRestorDate(new Date());
		totalDao.update(ma);
		totalDao.update(st);
	}
}
