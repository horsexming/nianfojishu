package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.sun.faces.el.ELConstants;
import com.task.Dao.TotalDao;
import com.task.Server.WarehouseAreaServerDao;
import com.task.entity.GoodHouse;
import com.task.entity.Goods;
import com.task.entity.OaAppDetail;
import com.task.entity.Store;
import com.task.entity.WareHouse;
import com.task.entity.WarehouseArea;
import com.task.entity.WarehouseNumber;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.MarkStatusType;
import com.task.entity.menjin.WareBangGoogs;
import com.task.entity.sop.WaigouDeliveryDetail;
import com.task.util.Util;
import com.task.util.UtilTong;

@SuppressWarnings("unchecked")
public class WarehouseAreaServerDaoImpl implements WarehouseAreaServerDao{
	private TotalDao totalDao;
	private static Integer duankou = 8885;
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public String addWarehouseArea(List<GoodHouse> waList,String wareHouseName) {
		if (waList != null && waList.size() > 0) {
			StringBuffer msg = new StringBuffer("true");
			for (int i = 0; i < waList.size(); i++) {
				GoodHouse warehouseArea = waList.get(i);
				
				//计算剩余
				Double all=waList.get(i).getGoodAllArea();
				Double use=waList.get(i).getGoodIsUsedArea();
				Double leave=all-use;
				waList.get(i).setGoodLeaveArea(leave);
				

				String hql = " from GoodHouse where goodHouseName = ? and goodsStoreWarehouse=?";
				GoodHouse wa = (GoodHouse) totalDao.getObjectByCondition(hql,
						warehouseArea.getGoodHouseName(), wareHouseName);
				if (wa == null) {
					String hql_maxcode = "select max(goodHouseNum) from GoodHouse";
					String maxcode = (String) totalDao
							.getObjectByCondition(hql_maxcode);
					int Num = 1;
					if (maxcode != null && !"".equals(maxcode)) {
						Num = Integer.parseInt(maxcode) + 1;
					}
					warehouseArea.setGoodsStoreWarehouse(wareHouseName);
					warehouseArea.setGoodHouseNum(Num + "");
					if (!totalDao.save(warehouseArea)) {
						msg.append("添加失败");
						continue;
					}
				} else {
					msg.append(warehouseArea.getGoodsStoreWarehouse()
							+ "仓库下，已有仓区" + warehouseArea.getGoodHouseName()
							+ " 无需重复添加.");
				}
			}
			return msg.toString();
		}
		return "error";
	}

	@Override
	public String addWarehouseNumber(List<WarehouseNumber> wnList,
			String cangqu, String wareHouseName) {
		if (wnList != null && wnList.size() > 0) {
			StringBuffer msg = new StringBuffer("");
			for (int i = 0; i < wnList.size(); i++) {
				WarehouseNumber warehouserNumber = wnList.get(i);
				String hql = "from WarehouseNumber where number = ?";
				WarehouseNumber wn = (WarehouseNumber) totalDao 
						.getObjectByCondition(hql, warehouserNumber.getNumber());
//				if(warehouserNumber.getIp()!=null&&warehouserNumber.getIp().length()>0){
//					String hql_ip = "select count(*) from WarehouseNumber where ip = ?";
//					Float ipnum =	(Float) totalDao.getObjectByCondition(hql_ip, warehouserNumber.getIp());
//					if(ipnum!=null && ipnum == 6){
//						msg.append(warehouserNumber.getIp()+"ip，已控制6个库位，超出最大控制数量。");
//						continue;
//					}
//					String hql_numid = " from  WarehouseNumber where ip = ? and numid =? ";
//					WarehouseNumber wn1 =	(WarehouseNumber) totalDao.getObjectByCondition(hql_numid,warehouserNumber.getIp(),warehouserNumber.getNumid() );
//					if(wn1!=null){
//						msg.append(warehouserNumber.getIp()+"ip，已有编号："+warehouserNumber.getNumid()+".");
//						continue;
//					}
//				}
//				if(warehouserNumber.getFourlightIp()!=null&&warehouserNumber.getFourlightIp().length()>0){
//					String hql_fourip = "select count(*) from WarehouseNumber where fourlightIp =? ";
//					Float fouripnum =	(Float) totalDao.getObjectByCondition(hql_fourip, warehouserNumber.getFourlightIp());
//					if(fouripnum!=null && fouripnum>0){
//						msg.append(warehouserNumber.getIp()+"ip，已控制6个四色灯，超出最大控制数量。");
//						continue;
//					}
//				}
				if (wn == null) {
					warehouserNumber.setBarCode(UUID.randomUUID().toString());// 条码
					warehouserNumber.setIp(warehouserNumber.getIp());// IP
					warehouserNumber.setHave("无");
					MarkStatusType mst = (MarkStatusType) totalDao.getObjectByCondition("from MarkStatusType where markTypt = '空'");
					if(mst!=null){
						warehouserNumber.setMarkTypt(mst);
						warehouserNumber.setMarkTyptName(mst.getMarkTypt());
					}else{
						warehouserNumber.setMarkTyptName("空");
					}
					
					warehouserNumber.setStatus("未满");
					warehouserNumber.setTotalNumber(0f);
					warehouserNumber.setNumid(warehouserNumber.getNumid());// id
					warehouserNumber.setAddTime(Util.getDateTime());// 添加时间
					warehouserNumber.setAddUser(Util.getLoginUser().getName());// 添加人
					warehouserNumber.setWarehouseArea(cangqu);
					warehouserNumber.setWareHouseName(wareHouseName);
					if("right".equals(warehouserNumber.getDirection())){
						warehouserNumber.setNumid(warehouserNumber.getNumid()==null ? 0 : UtilTong.guiDing_1(warehouserNumber.getNumid()));
					}
					if (!totalDao.save(warehouserNumber)) {
						msg.append("添加失败");
						continue;
					}
				} else {
					msg.append(wareHouseName + "仓库，" + cangqu + "仓区下，已有库位"
							+ warehouserNumber.getNumber() + "无需 重复添加。");
				}
			}
			String msg1 = msg.toString();
			if (msg1.length() > 0) {
				return msg1;
			} else {
				return "true";
			}
		}
		return "error";
	}
	

	@Override
	public boolean delWarehouseArea(GoodHouse warehouseArea) {
		if(warehouseArea!=null){
			warehouseArea = (GoodHouse) totalDao.get(GoodHouse.class, warehouseArea.getId());
			String hql_kw = "from WarehouseNumber where warehouseArea = ? and wareHouseName = ?";
			List<WarehouseNumber>  kwList =	totalDao.query(hql_kw, warehouseArea.getGoodHouseName(),warehouseArea.getGoodsStoreWarehouse());
			for (WarehouseNumber kw : kwList) {
				totalDao.delete(kw);
			}
			return totalDao.delete(warehouseArea);
		}
		return false;
	}

	@Override
	public boolean delWarehouseNumber(WarehouseNumber warehouserNumber) {
		if(warehouserNumber!=null){
			return totalDao.delete(warehouserNumber);
		}
		return false;
	}

	@Override
	public List<WarehouseArea> findAllWAByPage(int pageNo, int pageSize) {
		
		return null;
	}

	@Override
	public List<WarehouseNumber> findAllWNByPage(int pageNo, int pageSize) {
		String hql = "from WarehouseNumber order  by warehouseArea desc";
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public Map<Integer, Object> findWAByCondition(GoodHouse warehouseArea,
			int pageNo, int pageSize) {
		if(warehouseArea == null){
			warehouseArea = new GoodHouse();
		}
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		
		String hql=	totalDao.criteriaQueries(warehouseArea, null);
		
		int count=totalDao.getCount(hql);
		List<GoodHouse>  waList=(List<GoodHouse>)totalDao.findAllByPage(hql+" order by name desc", pageNo, pageSize);
		map.put(1, waList);
		map.put(2, count);
		return map;
	}
	
	
	@Override
	public boolean goodHouseCountByWG(GoodHouse warehouseArea) {
		if(warehouseArea == null){
			warehouseArea = new GoodHouse();
		}
		String hql="from GoodHouse where goodsStoreWarehouse=? and goodHouseName=?";
		Integer count=totalDao.getCount(hql,warehouseArea.getGoodsStoreWarehouse(),warehouseArea.getGoodHouseName());
		if(count>=1){
			return false;
		}
		return true;
	}

	@Override
	public Map<Integer, Object> findWNByCondition(
			WarehouseNumber warehouserNumber, int pageNo, int pageSize,String tag) {
		if(warehouserNumber == null){
			warehouserNumber = new WarehouseNumber();
		}
		if("show".equals(tag)) warehouserNumber.setMarkTyptName("空");
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(warehouserNumber, null)+" order by id asc";
		int count=totalDao.getCount(hql);
		List<WarehouseNumber>  wnList=(List<WarehouseNumber>)totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, wnList);
		map.put(2, count);
		return map;
	}

	@Override
	public List<GoodHouse> findwaListBywareHouseNo(String wareHouseName) {
		if(wareHouseName!=null && !"".equals(wareHouseName)){
			String hql_cq = " and goodsStoreWarehouse in (";
				String str = "";
				String[] cangqus = wareHouseName.split(",");
				if (cangqus != null && cangqus.length > 0) {
					for (int i = 0; i < cangqus.length; i++) {
						if(!"".equals(cangqus[i])){
							str += ",'" + cangqus[i] + "'";
						}
					}
					if (str.length() >= 1) {
						str = str.substring(1);
					}
					hql_cq += str;
				}
				hql_cq += ")";
			String hql = "from GoodHouse where 1=1 "+hql_cq+" order by goodsStoreWarehouse,goodHouseName ";
			return totalDao.query(hql);
		}
		return null;
	}

	@Override
	public List<WarehouseNumber> findwnList(String wareHouseName, String warehouseArea) {
		if(wareHouseName!=null && !"".equals(wareHouseName) && warehouseArea!=null && !"".equals(warehouseArea) ){
			String hql = "from WarehouseNumber where  wareHouseName = ? and warehouseArea = ?";
			return	totalDao.query(hql, wareHouseName,warehouseArea);
		}
		return null;
	}

	@Override
	public String updateWarehouseArea(GoodHouse warehouseArea) {
		if(warehouseArea!=null){
			GoodHouse wa = (GoodHouse) totalDao.get(GoodHouse.class,warehouseArea.getId());
			String hql = " from GoodHouse where goodHouseName = ? and goodsStoreWarehouse=?";
			GoodHouse wa1 =	(GoodHouse) totalDao.getObjectByCondition(hql, warehouseArea.getGoodHouseName(),warehouseArea.getGoodsStoreWarehouse());
			if(wa1 == null){
				String hql_kw = "from WarehouseNumber where warehouseArea = ? and wareHouseName = ?";
				List<WarehouseNumber>  kwList =	totalDao.query(hql_kw, wa1.getGoodHouseName(),wa1.getGoodsStoreWarehouse());
				for (WarehouseNumber kw : kwList) {
					kw.setWarehouseArea(warehouseArea.getGoodHouseName());
					kw.setWareHouseName(warehouseArea.getGoodsStoreWarehouse());
					totalDao.update(kw);
				}
				wa.setGoodsStoreWarehouse(warehouseArea.getGoodsStoreWarehouse());
				wa.setGoodHouseName(warehouseArea.getGoodHouseName());
				wa.setGoodHouseNum(warehouseArea.getGoodHouseNum());
				
				return  totalDao.update(wa)+"";
			}
			return warehouseArea.getGoodsStoreWarehouse()+"仓库下，已有仓区"+warehouseArea.getGoodHouseName()+"修改失败。";
		}
		return "error";
	}

	@Override
	public String updateWarehouseNumber(WarehouseNumber warehouserNumber) {
		if(warehouserNumber!=null){
			String hql = "from WarehouseNumber where  wareHouseName =? and warehouseArea = ? and number = ?";
			WarehouseNumber wn1 = (WarehouseNumber) totalDao.getObjectByCondition(hql, warehouserNumber.getWareHouseName(),warehouserNumber.getWarehouseArea(),warehouserNumber.getNumber());
			if(wn1 == null){
				WarehouseNumber wn = (WarehouseNumber) totalDao.get(WarehouseNumber.class,warehouserNumber.getId());
				wn.setWarehouseArea(warehouserNumber.getWarehouseArea());
				wn.setWareHouseName(warehouserNumber.getWareHouseName());
				wn.setNumber(warehouserNumber.getNumber());
				return totalDao.update(wn)+"";
			}
			return 	warehouserNumber.getWareHouseName()+"仓库，"+warehouserNumber.getWarehouseArea()+"仓区下，已有库位"+warehouserNumber.getNumber()+"修改失败。";
			
		}
		return "error";
	}

	@Override
	public int getcountWarehouseArea() {
		String hql = "from WarehouseArea ";
		
		return  totalDao.getCount(hql);
	}

	@Override
	public int getcountWarehouseNumber() {
		String hql = "from WarehouseNumber ";
		return  totalDao.getCount(hql);
	}

	@Override
	public List<WareHouse> findAllWareHouse() {
		String hql = "from WareHouse ";
		return totalDao.find(hql);
	}

	@Override
	public WarehouseNumber getwarehouserNumberById(int id) {
		return (WarehouseNumber) totalDao.get(WarehouseNumber.class, id);
	}

	@Override
	public List<WarehouseNumber> findAllWNList() {
		String hql = " from WarehouseNumber where id<40 order by number  ";
		return 	totalDao.find(hql);
	}
	
	@Override
	public List<WarehouseNumber> findAllWNList_1() {
		return 	totalDao.query("from WarehouseNumber where status = '未满' order by id desc");
	}

	@Override
	public String addplwarehouseNumber(File addwarehouseNumber) {

		String msg = "true";
		boolean flag = true;
		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int i = 0;
		try {
			FileCopyUtils.copy(addwarehouseNumber, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}

			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				StringBuffer strb = new StringBuffer();
				Integer errorCount = 0;// 错误数量
				Integer cfCount = 0;// 重复数量
				Integer successCount = 0;// 成功数量
				for (i = 2; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					String a = cells[1].getContents();// 名称
					String b = cells[2].getContents();// 批次
					String c = cells[3].getContents();// 规格
					String hql = "from WarehouseNumber where  wareHouseName =? and warehouseArea = ? and number = ?";
					WarehouseNumber wn =	(WarehouseNumber) totalDao.getObjectByCondition(hql, a,b,c);
					if(wn!=null){
						cfCount++;
						strb.append("第"+(i+1)+"行已导入过，无需重复导入。\\n");
						continue;
					}
					WarehouseNumber warehouseNumber = new WarehouseNumber();
					warehouseNumber.setWareHouseName(a);
					warehouseNumber.setWarehouseArea(b);
					warehouseNumber.setNumber(c);
					warehouseNumber.setBarCode(UUID.randomUUID().toString());
					
						if(!totalDao.save(warehouseNumber)){
							errorCount++;
							strb.append("第"+(i+1)+"导入失败。\\n");
						}else{
							successCount++;
						}
					
					if (i % 400 == 0) {
						totalDao.clear();
					}
				}

				is.close();// 关闭流
				wk.close();// 关闭工作薄
				msg = "已成功导入" + successCount + "个，失败" + errorCount + "个，重复"
				+ cfCount + "个，失败的行数分别为：" + strb.toString();
			} else {
				msg = "没有获取到行数";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "导入失败,出现异常" + e;

		}
		return msg;
	
	
	}

	@Override
	public Object[] findIpList() {
		Object [] obj = new Object[2];
		String hql_kuweiIp = " from AccessEquipment where equipmentDaoType = '库位'   and equipmentIP <> '' and equipmentIP is not null order by id desc";
		String hql_fourlightIp = "from AccessEquipment where equipmentDaoType = '四色灯' and equipmentIP <> '' and equipmentIP is not null ";
		List<AccessEquipment> kuweiIpList =	totalDao.query(hql_kuweiIp);
		List<AccessEquipment> fourlightIpList =	totalDao.query(hql_fourlightIp);
		obj[0] =kuweiIpList;
		obj[1] =fourlightIpList;
		return obj;
	}

	@Override
	public String ColseWNById(Integer id) {
		if(id!=null && id>0){
			WarehouseNumber wn = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, id);
			String message = UtilTong.OCKuWei(wn.getIp(), duankou, false, wn.getOneNumber(), wn.getNumid());//关门操作
			if ("true".equals(message)) {
				wn.setKwStatus("关");
				wn.setCzUserId(null);
				if(totalDao.update(wn)){//更新库位状态
				}else totalDao.update2(wn);
				return "true";
			}
			return "连接异常，关门失败！";
		}
		return "未获取到数据";
	}

	@Override
	public String OpenWNById(Integer id) {
		if(id!=null && id>0){
			WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, id);
			String message = UtilTong.OCKuWei(wN.getIp(), duankou, true, wN.getOneNumber(), wN.getNumid());
			if ("true".equals(message)) {
				wN.setKwStatus("开");
				wN.setCzUserId(Util.getLoginUser().getId());
				if(totalDao.update(wN)){//更新库位状态
				}else totalDao.update2(wN);
				return "true";
			}else {
				return "连接异常，开门失败！";
			}
		}
		return "未获取到数据";
	}
	
	@Override
	public String CongzhiLight(Integer id) {
		if(id!=null && id>0){
			WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, id);
			//灭灯
			UtilTong.closeLight(wN.getIp(), duankou, wN.getOneNumber(), wN.getNumid());
			//亮灯的颜色
			String message = UtilTong.openColorLight(wN.getIp(), duankou, true, true, wN.getOneNumber(), wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
			if ("true".equals(message)) {
				return "true";
			}else {
				return "连接异常，重置失败！";
			}
		}
		return "未获取到数据";
	}
	
	@Override
	public String Congzhipinmu(Integer id) {
		if(id!=null && id>0){
			WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, id);
			//发送屏幕信息
			AttendanceTowServerImpl.sendPin_1(wN);
			//String message = UtilTong.pingKuWei(wN.getIp(), duankou, wN.getOneNumber(),wN.getNumid(), wN.getNumber());
//			if ("true".equals(message)) {
				return "true";
//			}else {
//				return "连接异常，重置屏幕失败！";
//			}
		}
		return "未获取到数据";
	}
	@Override
	public String sendKuWei(Integer id) {
		if(id!=null && id>0){
			WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, id);
			//发送库位码
			String message = UtilTong.sendKuWeiCode(wN.getIp(), duankou, wN.getOneNumber(), wN.getNumid(), wN.getBarCode());
			if ("true".equals(message)) {
				// 发送屏幕信息
				AttendanceTowServerImpl.sleeps(1500);
				UtilTong.pingKuWei(wN.getIp(), duankou, wN.getOneNumber(), wN.getNumid(), wN.getNumber());
				return "true";
			}else {
				return "连接异常，重置屏幕失败！";
			}
		}
		return "未获取到数据";
	}
	@Override
	public String sendZhuYe(Integer id) {
		if(id!=null && id>0){
			WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, id);
			//发送库位码
			String message = UtilTong.backTowMa(wN.getIp(), duankou, wN.getOneNumber());
			if ("true".equals(message)) {
				// 发送回到主页信息
				return "true";
			}else {
				return "连接异常，重置屏幕失败！";
			}
		}
		return "未获取到数据";
	}
	@Override
	public String shansuo(Integer id) {
		if(id!=null && id>0){
			WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, id);
			//闪烁
			String message = UtilTong.openColorLight(wN.getIp(), duankou, true, false, wN.getOneNumber(), wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
			if ("true".equals(message)) {
				return "true";
			}else {
				return "连接异常，重置屏幕失败！";
			}
		}
		return "未获取到数据";
	}

	@Override
	public List<GoodHouse> getAllWalist() {
		
		return  totalDao.query(" from GoodHouse");
	}

	@Override
	public String getWarebgnding(Integer id) {
		// TODO Auto-generated method stub
		WarehouseNumber wn = getwarehouserNumberById(id);
		if (wn==null) return null;
		List<WareBangGoogs> wbg =  totalDao.query("from WareBangGoogs where fk_ware_id = ?", id);
		if(wbg!=null&&wbg.size()>0){
			StringBuffer s = new StringBuffer();
			for (WareBangGoogs wb: wbg) {
				if (wb.getFk_waigouDeliveryDetail_id()!=null) {
					WaigouDeliveryDetail wg = (WaigouDeliveryDetail) totalDao.getObjectById(WaigouDeliveryDetail.class, wb.getFk_waigouDeliveryDetail_id());
					if(wg!=null){
						s.append("件号："+wg.getMarkId() + "  ");// 件号
						s.append("批次："+wg.getExamineLot()+ "\n");// 批次
						s.append("数量："+wb.getNumber() + "  ");// 数量
						s.append("库位状态："+wn.getMarkTyptName()+ "\n");// 库位状态
						s.append("供应商："+wg.getGysName() + "\n\n");// 供应商
					}
				}else {
					if (wb.getFk_oadetail_id()!=null) {
						OaAppDetail deli = (OaAppDetail) totalDao.getObjectById(OaAppDetail.class, wb.getFk_oadetail_id());
						if(deli!=null){
							s.append("工装号："+deli.getDetailAppName()+"  ");//件号
							s.append("规格："+deli.getDetailFormat()+"\n");//批次
							s.append("数量："+wb.getNumber()+"  ");//数量
							s.append("库位状态："+wn.getMarkTyptName()+"\n");//库位状态
							s.append("供应商："+deli.getDetailAppDept()+"\n\n");//供应商
						}
					}else {
						if (wb.getFk_store_id()!=null) {
							Store store = (Store) totalDao.getObjectById(Store.class, wb.getFk_store_id());
							if(store!=null){
								s.append("工装号："+store.getMatetag()+"  ");//件号
								s.append("规格："+store.getFormat()+"\n");//批次
								s.append("数量："+wb.getNumber()+"  ");//数量
								s.append("库位编号："+wb.getNumber()+"\n");//库位编号
								s.append("库位状态："+wn.getMarkTyptName()+"\n\n");//库位状态
							}
						}else{
							if(wb.getFk_good_id()!=null){
								Goods goods = (Goods) totalDao.getObjectById(Goods.class, wb.getFk_good_id());
								if(goods!=null){
									s.append("件号："+goods.getGoodsMarkId()+"  ");//件号
									s.append("批次："+goods.getGoodsLotId()+"\n");//批次
									s.append("数量："+wb.getNumber()+"  ");//数量
									s.append("库位编号："+wb.getNumber()+"\n");//库位编号
									s.append("供应商："+goods.getGoodsSupplier()+"\n\n");//库位状态
								}
							}
						}
					}
				}
			}
			return s.toString();
		}
		return null;
	}

	@Override
	public void addGoodsBandDing() {
		// TODO Auto-generated method stub
		List<Goods> goods = totalDao.query("from Goods where goodsPosition in ('A2-01-1','A2-02-1','A2-03-1','A2-04-1','A2-05-1','B2-01-1','B2-02-1','B2-03-1','B2-04-1','B2-05-1','B2-06-1','B2-07-1','B2-08-1','B2-09-1','B2-10-1') and goodsClass = '外购件库' and goodsCurQuantity >0 order by goodsPosition asc");
		if(goods!=null&&goods.size()>0){
			for (Goods goods2 : goods) {
				WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectByCondition("from WarehouseNumber where number = ?", goods2.getGoodsPosition());
				if(wN!=null){
					//添加中间表 建立关系
					WareBangGoogs bwg = new WareBangGoogs();
					bwg.setFk_good_id(goods2.getGoodsId());//库存ID
					bwg.setFk_ware_id(wN.getId());//库位ID
					bwg.setNumber(goods2.getGoodsCurQuantity());//可操作数量
					bwg.setTowCode(UUID.randomUUID().toString());
					bwg.setStatus("入库");
					if(totalDao.save(bwg)){//添加成功后 更新库位状态。
						updateWN(wN, "合格品");
						wN.setHave("有");
						totalDao.update(wN);
					}
				}
			}
		}
	}
	/**
	 * 更改状态
	 */
	public WarehouseNumber updateWN(WarehouseNumber wn, String s) {
		// 改变库位中物品的状态
		MarkStatusType mst = (MarkStatusType) totalDao.getObjectByCondition(
				"from MarkStatusType where markTypt = ?", s);
		if (mst != null) {
			wn.setMarkTyptName(mst.getMarkTypt());
			wn.setMarkTypt(mst);
		} else {
			wn.setMarkTyptName(s);
			wn.setMarkTypt(null);
		}
		return wn;
	}

	@Override
	public String cangquIsExistArea(String wareHouseName, String cangquName) {
		String message="";
		GoodHouse goodHouse=(GoodHouse)totalDao.getObjectByCondition("from GoodHouse where goodsStoreWarehouse=? and goodHouseName=? and goodAllArea>0 and goodIsUsedArea>=0", wareHouseName,cangquName);	
		if(goodHouse!=null){
			if(goodHouse.getGoodAllArea()<=goodHouse.getGoodIsUsedArea()){
				message="full";
			}else{
				message="canUse";
			}
		}
 		return message;
	}

	
	

}
