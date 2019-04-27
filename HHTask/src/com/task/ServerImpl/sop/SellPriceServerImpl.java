package com.task.ServerImpl.sop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.sop.SellPriceServer;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.banci.BanCi;
import com.task.entity.renshi.InterviewLog;
import com.task.entity.sop.SellPrice;
import com.task.util.Util;

public class SellPriceServerImpl implements SellPriceServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加销售价格

	@Override
	public boolean addSellPrice(SellPrice sellPrice, File[] attachment,
			String[] attachmentFileName, String fatherPartNumber) {
		if (sellPrice != null) {
			// 上传附件
			String attachmentName = "";

			if (attachment != null && attachment.length > 0) {
				for (int i = 0; i < attachment.length; i++) {
					String fileName = sellPrice.getContractNumber().replaceAll(
							"/", "").trim()
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date())
							+ (attachmentFileName[i]

							.substring(attachmentFileName[i].lastIndexOf(".")));
					if (i > 0) {
						attachmentName += "|" + fileName;
					} else {
						attachmentName += fileName;
					}
					attachmentName.trim();
					// 上传到服务器
					String fileRealPath = ServletActionContext
							.getServletContext().getRealPath(
									"/upload/file/sellPrice")
							+ "/" + fileName;
					File file = new File(fileRealPath);
					try {
						FileCopyUtils.copy(attachment[i], file);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}

					// 备份到项目
					String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file"
							+ "/" + fileName;
					File beiFenFile = new File(beiFenfileRealPath);
					try {
						FileCopyUtils.copy(attachment[i], beiFenFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

			sellPrice.setAttachmentName(attachmentName);// 把附件保存下来

		}

		if (sellPrice.getFileNumber() != null
				&& !sellPrice.getFileNumber().equals("")) {
			sellPrice.setFileNumber("yes");
		} else {
			sellPrice.setFileNumber("no");

		}
		// 为属性赋值
		sellPrice.setAddtime(Util.getDateTime());// 添加时间
		// 其他没有件号
		if (sellPrice.getPartNumber() != null
				&& sellPrice.getPartNumber().length() > 0) {
			sellPrice.setPartNumber(sellPrice.getPartNumber().replaceAll(" ",
					""));// 件号去除所有空格
		}
		return totalDao.save(sellPrice);
	}

	// 分页查找所有销售价格
	@Override
	public List findAllSellPrice(int parseInt, int pageSize) {
		String hql = "from SellPrice order by addtime desc";
		return totalDao.findAllByPage(hql, parseInt, pageSize);
	}

	// 通过ID查找销售价格
	@Override
	public SellPrice findSellPriceById(int id) {
		if ((Object) id != null && id > 0) {
			return (SellPrice) totalDao.getObjectById(SellPrice.class, id);
		}
		return null;
	}

	// 获得总数量
	@Override
	public int getCount() {
		String hql = "from SellPrice";
		return totalDao.getCount(hql);
	}

	// 查询所有
	@Override
	public Object[] findSellPriceByCondition(SellPrice sellPrice, int parseInt,
			int pageSize) {
		if (sellPrice == null) {
			sellPrice = new SellPrice();
		}
		String hql = totalDao.criteriaQueries(sellPrice, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 删除
	@Override
	public boolean deleteSellPrice(SellPrice sellPrice) {

		if (sellPrice != null) {
			return totalDao.delete(sellPrice);
		}
		return false;
	}

	// 修改销售价格
	@Override
	public boolean updateSellPrice(SellPrice sellPrice, File[] attachment,
			String[] attachmentFileName, String fatherPartNumber) {
		SellPrice sellPrice2 = (SellPrice) this.totalDao.getObjectById(
				SellPrice.class, sellPrice.getId());
		if (sellPrice != null) {
			// 上传附件
			String attachmentName = "";

			if (attachment != null && attachment.length > 0) {
				for (int i = 0; i < attachment.length; i++) {
					String fileName = sellPrice.getContractNumber().replaceAll(
							"/", "").trim()
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date())
							+ (attachmentFileName[i]

							.substring(attachmentFileName[i].lastIndexOf(".")));
					if (i > 0) {
						attachmentName += "|" + fileName;
					} else {
						attachmentName += fileName;
					}
					attachmentName.trim();
					// 上传到服务器
					String fileRealPath = ServletActionContext
							.getServletContext().getRealPath(
									"/upload/file/sellPrice")
							+ "/" + fileName;
					File file = new File(fileRealPath);
					try {
						FileCopyUtils.copy(attachment[i], file);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}

					// 备份到项目
					String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file"
							+ "/" + fileName;
					File beiFenFile = new File(beiFenfileRealPath);
					try {
						FileCopyUtils.copy(attachment[i], beiFenFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(sellPrice2.getAttachmentName()!=null){
					File file2 = new File(ServletActionContext
							.getServletContext().getRealPath(
							"/upload/file/sellPrice")
					+ "/" + sellPrice2.getAttachmentName());
					if (file2.exists()) {
						file2.delete();// 将原图片删掉
					}
				}
				sellPrice2.setAttachmentName(attachmentName);// 把附件保存下来
			}

		}

		if (sellPrice.getFileNumber() != null
				&& !sellPrice.getFileNumber().equals("")) {
			sellPrice2.setFileNumber("yes");
		} else {
			sellPrice2.setFileNumber("no");

		}
		// 为属性赋值
		sellPrice2.setAddtime(Util.getDateTime());// 添加时间
		// 其他没有件号
		if (sellPrice.getPartNumber() != null
				&& sellPrice.getPartNumber().length() > 0) {
			sellPrice2.setPartNumber(sellPrice.getPartNumber().replaceAll(" ",
					""));// 件号去除所有空格
		}
		sellPrice2.setAddtime(Util.getDateTime());
		sellPrice2.setStarttime(sellPrice.getStarttime());
		sellPrice2.setEndtime(sellPrice.getEndtime());
		sellPrice2.setClientManagement(sellPrice.getClientManagement());
		sellPrice2.setContractNumber(sellPrice.getContractNumber());
		sellPrice2.setFileNumber(sellPrice.getFileNumber());
		sellPrice2.setHair(sellPrice.getHair());
		sellPrice2.setName(sellPrice.getName());
		sellPrice2.setPartNumber(sellPrice.getPartNumber());
		sellPrice2.setCdStatus(sellPrice.getCdStatus());
		sellPrice2.setBhsPrice(sellPrice.getBhsPrice());
		sellPrice2.setHsPrice(sellPrice.getHsPrice());
		sellPrice2.setProduceType(sellPrice.getProduceType());
		sellPrice2.setType(sellPrice.getType());
		sellPrice2.setProductCategory(sellPrice.getProductCategory());
		sellPrice2.setRmarks(sellPrice.getRmarks());
		boolean b = this.totalDao.update(sellPrice2);
		return b;
	}

	@Override
	public List<String> findClientNameList() {
		// TODO Auto-generated method stub
		return totalDao.query("select clientcompanyname from ClientManagement where clientcompanyname is not null");
	}

}
