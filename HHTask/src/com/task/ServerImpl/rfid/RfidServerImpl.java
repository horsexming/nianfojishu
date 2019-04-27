package com.task.ServerImpl.rfid;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.rfid.RfidServer;
import com.task.entity.Users;
import com.task.entity.rfid.Rfid;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class RfidServerImpl implements RfidServer {
	private TotalDao totalDao;

	@Override
	public String addRfid(Rfid rfie) {
		// TODO Auto-generated method stub
		Users users = Util.getLoginUser();
		if(users!=null){
			rfie.setAddRenName(users.getName());
			rfie.setAddRenId(users.getId());
		}
		rfie.setStatus("待发送");
		rfie.setStoreInfor("初始");
		rfie.setAddTime(Util.getDateTime());
		if(totalDao.save(rfie)){
			return "添加成功！";
		}
		return "对象为空，添加失败！";
	}

	@Override
	public Rfid byIdRfid(Integer id) {
		// TODO Auto-generated method stub
		return (Rfid) totalDao.getObjectById(Rfid.class, id);
	}

	@Override
	public String deleteRfid(Integer id) {
		// TODO Auto-generated method stub
		Rfid obje = byIdRfid(id);
		if (obje != null) {
//			if (totalDao.delete(obje)) return "删除成功！";
//			else return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Object [] findRfid(Rfid rfid, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (rfid == null) {
			rfid = new Rfid();
		}
		String hql = totalDao.criteriaQueries(rfid, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public String updateRfid(Rfid rfid) {
		// TODO Auto-generated method stub
		Rfid rfid2 = byIdRfid(rfid.getId());
		if (rfid2 != null) {
			BeanUtils.copyProperties(rfid, rfid2, new String[] {
					"id", "addTime", "addRenName", "addRenId"});
			if (totalDao.update(rfid2))
				return "修改成功！";
			else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}

	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void sendRfid() {
		// TODO Auto-generated method stub
		//String rsidMess = Util.SendRfid("192.168.0.208", 8235);
		
		Socket s;
		String mess = "true";
		try {
			s = new Socket("192.168.0.208", 8235);
			int i = 0;
			PrintStream out = null;
			InputStream in = null;
			BufferedInputStream bis = null;
			while(i<20){
				out = new PrintStream(s.getOutputStream());
				out.write(new byte[]{(byte) 0xAA, 0x00, 0x22, 0x00, 0x00, 0x22, (byte) 0x8E});
				out.flush();
				in = s.getInputStream();
				bis = new BufferedInputStream(in);
				// 先接收接收第一个字符 用做标识u
				i++;
				byte[] one = new byte[1];// 
				bis.read(one);// 读取数据
				String mes = Util.byteArrayToHexString(one);
				if("AA".equals(mes)){
					byte[] tow = new byte[1];//
					bis.read(tow);// 读取数据
					String mes1 = Util.byteArrayToHexString(tow);
					if("02".equals(mes1)){
						byte[] three1 = new byte[6];//
						Thread.sleep(10);
						bis.read(three1);// 读取数据
						byte[] three = new byte[12];//
						Thread.sleep(12);
						bis.read(three);// 读取数据
						mess = Util.byteArrayToHexString(three);
						System.out.println("----------"+mess+"----------");
						Rfid rfid = (Rfid) totalDao.getObjectByCondition("from Rfid where identifies = ? and status = '待发送'", mess);
						if(rfid!=null){
							rfid.setStatus("已发送");
							//将storeInfor信息发送给芯片
							break;
						}
					}else {
						byte[] three = new byte[6];//
						Thread.sleep(6);
						bis.read(three);// 读取数据
						System.out.println("----------"+Util.byteArrayToHexString(three)+"----------");
					}
				}
			}
			out.close();
			bis.close();
			in.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
