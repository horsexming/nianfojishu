package com.task.action.zhuseroffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.zhuseroffer.ZhuserOfferServer;
import com.task.entity.Users;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.zhuseroffer.NoPriceprocess;
import com.task.entity.zhuseroffer.Sample;
import com.task.entity.zhuseroffer.SumProcess;
import com.task.entity.zhuseroffer.ZhuserOffer;
import com.task.util.Util;

public class ZhuserOfferAction extends ActionSupport {
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private ZhuserOfferServer zhuserOfferServer;
	private ZhuserOffer zhuserOffer;
	private List<ZhuserOffer> zhuserOfferList;
	private YuanclAndWaigj yuanclAndWaigj;
	private SumProcess sumProcess;
	private List<YuanclAndWaigj> yuanclAndWaigjList;// 原材料和外购件列表
	private Integer[] offerId;
	private Integer id;
	private String status;
	private String operate;
	private NoPriceprocess np;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List<Sample> sampleList;
	private Sample sample;
	private String nowdate;
	private String issong;

	/*
	 * 通过供应商查找所有的标价
	 */
	public String findListByZhUser() {
		Map<Integer, Object> map = zhuserOfferServer.findListByZhUser(
				zhuserOffer, Integer.parseInt(cpage), pageSize);
		zhuserOfferList = (List<ZhuserOffer>) map.get(1);
		if (zhuserOfferList != null & zhuserOfferList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_findListByZhUser.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findByZhUser_List";
	}

	public String showYuancail() {
		if (yuanclAndWaigj != null) {
			ActionContext.getContext().getSession().put("yuanclAndWaigj",
					yuanclAndWaigj);
		} else {// 用来保持分页时带着查询条件
			yuanclAndWaigj = (YuanclAndWaigj) ActionContext.getContext()
					.getSession().get("yuanclAndWaigj");
		}
		Map<Integer, Object> map = zhuserOfferServer.findyuancailByStatus(
				yuanclAndWaigj, Integer.parseInt(cpage), pageSize);
		yuanclAndWaigjList = (List<YuanclAndWaigj>) map.get(1);// 显示页的原材料和外购件列表
		if (yuanclAndWaigjList != null & yuanclAndWaigjList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_showYuancail.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "yuanCailiao_all";
	}

	public String daochutz() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录!";
			return "error";
		}
		Map<String, String> tzwzmap = zhuserOfferServer
				.findpicforSumProcess(sumProcess.getId());
		if (tzwzmap != null) {
			try {
				Set<String> keys = tzwzmap.keySet();
				if (keys != null && keys.size() > 0) {
					String path = ServletActionContext.getServletContext()
							.getRealPath("/upload/file/processTz");
					// ZIP打包图片
					// String zipName = procardTemplate.getMarkId() ;
					String zipName = zhuserOfferServer.findSumProcessByid(
							sumProcess.getId()).getYwmarkId();
					File zipFile = new File(path + "/" + zipName + ".zip");
					byte[] buf = new byte[1024];
					int len;
					ZipOutputStream zout = new ZipOutputStream(
							new FileOutputStream(zipFile));
					for (String filename : keys) {
						FileInputStream in = null;
						try {
							in = new FileInputStream(new File(path + "/"
									+ filename));
							if (in == null) {
								continue;
							}
							zout.putNextEntry(new ZipEntry(tzwzmap
									.get(filename))); // 导出名称
							while ((len = in.read(buf)) > 0) {
								zout.write(buf, 0, len);
							}
							zout.closeEntry();
							in.close();
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
					}
					try {
						zout.close();
					} catch (Exception e) {
						// TODO: handle exception
						errorMessage = "对不起没有找到实体图纸!";
						return "error";
					}

					// 下载图片
					FileInputStream zipInput = new FileInputStream(zipFile);
					HttpServletResponse response = ServletActionContext
							.getResponse();
					OutputStream out = response.getOutputStream();
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + zipName + ".zip");
					while ((len = zipInput.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					zipInput.close();
					out.flush();
					out.close();
					// 删除压缩包
					zipFile.delete();
					return null;
				} else {
					errorMessage = "对不起,没有找到图纸!";
					return "error";
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		errorMessage = "对不起没有找到图纸!";
		return "error";
	}

	public String getwgOrderTz() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录!";
			return "error";
		}
		if (zhuserOffer == null) {
			errorMessage = "没有找打对应的订单!";
			return "error";
		}
		Map<String, String> tzwzmap = zhuserOfferServer
				.findbjtzList(zhuserOffer.getId());
		if (tzwzmap != null) {
			try {
				Set<String> keys = tzwzmap.keySet();
				if (keys != null && keys.size() > 0) {
					String path = ServletActionContext.getServletContext()
							.getRealPath("/upload/file/processTz");
					// ZIP打包图片
					File zipFile = new File(path + "/"
							+ zhuserOffer.getMarkId() + ".zip");
					byte[] buf = new byte[1024];
					int len;
					ZipOutputStream zout = new ZipOutputStream(
							new FileOutputStream(zipFile));
					for (String filename : keys) {
						FileInputStream in = null;
						try {
							in = new FileInputStream(new File(path + "/"
									+ filename));
							if (in == null) {
								continue;
							}
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
						zout.putNextEntry(new ZipEntry(tzwzmap.get(filename))); // 导出名称
						while ((len = in.read(buf)) > 0) {
							zout.write(buf, 0, len);
						}
						zout.closeEntry();
						in.close();
					}
					zout.close();
					// 下载图片
					FileInputStream zipInput = new FileInputStream(zipFile);
					HttpServletResponse response = ServletActionContext
							.getResponse();
					OutputStream out = response.getOutputStream();
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition",
							"attachment; filename=images.zip");
					while ((len = zipInput.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					zipInput.close();
					out.flush();
					out.close();
					// 删除压缩包
					zipFile.delete();
					return null;
				} else {
					errorMessage = "对不起没有找到图纸!";
					return "error";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		errorMessage = "对不起没有找到图纸!";
		return "error";
	}

	public String getwgOrderTz1() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录!";
			return "error";
		}
		Map<String, String> tzwzmap = zhuserOfferServer
				.processTemplateFileList1(yuanclAndWaigj.getId());
		if (tzwzmap != null) {
			try {
				Set<String> keys = tzwzmap.keySet();
				if (keys != null && keys.size() > 0) {
					String path = ServletActionContext.getServletContext()
							.getRealPath("/upload/file/processTz");
					// ZIP打包图片
					File zipFile = new File(path + "/"
							+ yuanclAndWaigj.getMarkId() + ".zip");
					byte[] buf = new byte[1024];
					int len;
					ZipOutputStream zout = new ZipOutputStream(
							new FileOutputStream(zipFile));
					for (String filename : keys) {
						FileInputStream in = null;
						try {
							in = new FileInputStream(new File(path + "/"
									+ filename));
							if (in == null) {
								continue;
							}
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
						zout.putNextEntry(new ZipEntry(tzwzmap.get(filename))); // 导出名称
						while ((len = in.read(buf)) > 0) {
							zout.write(buf, 0, len);
						}
						zout.closeEntry();
						in.close();
					}
					zout.close();
					// 下载图片
					FileInputStream zipInput = new FileInputStream(zipFile);
					HttpServletResponse response = ServletActionContext
							.getResponse();
					OutputStream out = response.getOutputStream();
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition",
							"attachment; filename=images.zip");
					while ((len = zipInput.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					zipInput.close();
					out.flush();
					out.close();
					// 删除压缩包
					zipFile.delete();
					return null;
				} else {
					errorMessage = "对不起没有找到图纸!";
					return "error";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		errorMessage = "对不起没有找到图纸!";
		return "error";
	}

	public String getwgOrderTz2() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录!";
			return "error";
		}
		Integer idssss = zhuserOffer.getId();
		Map<String, String> tzwzmap = zhuserOfferServer
				.findprocessTemplateFileList2(zhuserOffer.getId());
		zhuserOffer = zhuserOfferServer.findZhOfferById(zhuserOffer.getId());
		if (tzwzmap != null) {
			try {
				Set<String> keys = tzwzmap.keySet();
				if (keys != null && keys.size() > 0) {
					String path = ServletActionContext.getServletContext()
							.getRealPath("/upload/file/processTz");
					// ZIP打包图片
					File zipFile = new File(path + "/"
							+ zhuserOffer.getMarkId()
							+ zhuserOffer.getProcessName() + ".zip");
					byte[] buf = new byte[1024];
					int len;
					ZipOutputStream zout = new ZipOutputStream(
							new FileOutputStream(zipFile));
					for (String filename : keys) {
						FileInputStream in = null;
						try {
							in = new FileInputStream(new File(path + "/"
									+ filename));
							if (in == null) {
								continue;
							}
							zout.putNextEntry(new ZipEntry(tzwzmap
									.get(filename))); // 导出名称
							while ((len = in.read(buf)) > 0) {
								zout.write(buf, 0, len);
							}
							zout.closeEntry();
							in.close();
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
					}
					zout.close();
					// 下载图片
					FileInputStream zipInput = new FileInputStream(zipFile);
					HttpServletResponse response = ServletActionContext
							.getResponse();
					OutputStream out = response.getOutputStream();
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition",
							"attachment; filename=images.zip");
					while ((len = zipInput.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					zipInput.close();
					out.flush();
					out.close();
					// 删除压缩包
					zipFile.delete();
					return null;
				} else {
					errorMessage = "对不起没有找到图纸!";
					return "error";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		errorMessage = "对不起没有找到图纸!";
		return "error";
	}

	public String findListByStatus() {
		Map<Integer, Object> map = zhuserOfferServer.findListByStatus(
				zhuserOffer, Integer.parseInt(cpage), pageSize);
		zhuserOfferList = (List<ZhuserOffer>) map.get(1);
		if (zhuserOfferList != null & zhuserOfferList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_findListByStatus.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		operate = "operate";
		return "findByZhUser_List";
	}

	// 供应商查看全部样品
	public String findSampleByZhUser() {
		Map<Integer, Object> map = zhuserOfferServer.findAllSampleByZhUser(
				sample, Integer.parseInt(cpage), pageSize);
		sampleList = (List<Sample>) map.get(1);
		if (sampleList != null & sampleList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_findSampleByZhUser.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllSampleByZhuser";
	}

	// 所有样品
	public String findAllSample() {
		Map<Integer, Object> map = zhuserOfferServer.findAllSample(sample,
				Integer.parseInt(cpage), pageSize);
		sampleList = (List<Sample>) map.get(1);
		if (sampleList != null & sampleList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_findAllSample.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllSample_List";
	}

	// 查询样品详情
	public String findSample() {
		if (zhuserOffer.getId() != null) {
			sample = zhuserOfferServer.findSampleBySam(zhuserOffer.getId());
			zhuserOffer = zhuserOfferServer
					.findZhOfferById(zhuserOffer.getId());
		} else {
			errorMessage = "没有找到该报价单";
			return "error";
		}
		return "findSample_Detail";
	}

	public String findSampleforGongxu() {
		if (zhuserOffer.getId() != null) {
			sample = zhuserOfferServer.findSampleBySam(zhuserOffer.getId());
			zhuserOffer = zhuserOfferServer
					.findZhOfferById(zhuserOffer.getId());
		} else {
			errorMessage = "没有找到该报价单";
			return "error";
		}
		return "yangpin_detail";
	}

	// 录入价格
	public String inputPrice() {
		if (yuanclAndWaigj.getId() != null) {

			errorMessage = zhuserOfferServer.inputPrice(yuanclAndWaigj);
		}

		return "error";
	}

	// 初检
	public String firstCheck() {
		if (sample.getId() != null) {
			errorMessage = zhuserOfferServer.updateSample(sample, status);
			this.status = "caigou";
			return "findSampleByCaigou";
		}
		errorMessage = "初检失败";
		return "error";
	}

	// public String shenpi(){
	// if(sample.getId()!=null){
	// sample = zhuserOfferServer.findSampleById(sample.getId());
	// errorMessage = zhuserOfferServer.addapproval(sample);
	// }
	// this.operate="operate";
	// return "findSampleByCaigou";
	// }
	public String findSampleByCaigou() {
		Map<Integer, Object> map = zhuserOfferServer.findAllSampleByCaiGou(
				sample, Integer.parseInt(cpage), pageSize, status);
		sampleList = (List<Sample>) map.get(1);
		if (sampleList != null & sampleList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_findSampleByZhUser.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		this.operate = "operate";
		return "findAllSample_List";
	}

	public String findQueRenListByZhUser() {
		Map<Integer, Object> map = zhuserOfferServer.findQueRenList(
				zhuserOffer, Integer.parseInt(cpage), pageSize);
		zhuserOfferList = (List<ZhuserOffer>) map.get(1);
		if (zhuserOfferList != null & zhuserOfferList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_findQueRenListByZhUser.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findQueRenByYw_List";
	}

	public String findDaiDayang() {
		Map<Integer, Object> map = zhuserOfferServer.findQueRenListByZhUser(
				zhuserOffer, Integer.parseInt(cpage), pageSize);
		zhuserOfferList = (List<ZhuserOffer>) map.get(1);
		if (zhuserOfferList != null & zhuserOfferList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_findQueRenListByZhUser.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findDaiDaYangByZhuers_List";
	}

	public String findDaiLuruListByZhUser() {
		Map<Integer, Object> map = zhuserOfferServer.findDailuRuListByZhUser(
				zhuserOffer, Integer.parseInt(cpage), pageSize);
		zhuserOfferList = (List<ZhuserOffer>) map.get(1);
		if (zhuserOfferList != null & zhuserOfferList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ZhuserOfferAction_findQueRenListByZhUser.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findQueRenByYw_List";
	}

	public String findZhOffer() {
		if (zhuserOffer.getId() != null) {
			zhuserOffer = zhuserOfferServer
					.findZhOfferById(zhuserOffer.getId());
			if (zhuserOffer != null) {
				nowdate = Util.getDateTime("yyyy-MM-dd");
				if (nowdate.compareTo(zhuserOffer.getEndDate()) < 0
						|| nowdate.compareTo(zhuserOffer.getEndDate()) == 0) {
					return "findOfferById_Detail";
				} else {
					errorMessage = "已经超过填写时间,截止日期为：" + zhuserOffer.getEndDate();
				}
			}else{
				errorMessage = "没有找到该报价单";
			}
		} else {
			errorMessage = "没有找到该报价单";
		}
		return "error";
	}

	public String findZhOfferForlook() {
		if (zhuserOffer.getId() != null) {
			zhuserOffer = zhuserOfferServer
					.findZhOfferById(zhuserOffer.getId());
			return "zhuserOffer_detail";
		} else {
			errorMessage = "没有找到该报价单";
			return "error";
		}
	}

	public String findZhOfferForDayang() {
		if (zhuserOffer.getId() != null) {
			zhuserOffer = zhuserOfferServer
					.findZhOfferById(zhuserOffer.getId());
		} else {
			errorMessage = "没有找到该报价单";
			return "error";
		}
		if ("gongxu".equals(status)) {
			return "dayang_process";
		} else {
			return "daYang_Detail";
		}
	}

	public String updateZhOffer() {
		if (zhuserOffer != null) {
			boolean b = zhuserOfferServer.updateZhOffer(zhuserOffer);
			if (b) {
				errorMessage = "报价成功";
				if ("operate".equals(operate)) {
					return "error";
				} else {
					return "error";
				}
			} else {
				errorMessage = "报价失败";
				return "error";
			}
		} else {
			errorMessage = "没有找到该报价单";
			return "error";
		}
	}

	public String qurenOffer() {
		if (offerId.length > 0 && offerId != null) {
			if ("是".equals(issong)) {
				errorMessage = zhuserOfferServer.updateQurenOffer(offerId, id);
				this
						.setUrl("yuanclAndWaigjAction_findAllZhOffer.action?yuanclAndWaigj.id="
								+ id);
				return "error";
			} else if ("否".equals(issong)) {
				if (offerId.length == 1) {
					// 直接进入审批
					errorMessage = zhuserOfferServer.shenpiZhoffer(offerId[0],
							null);
					return "error";
				} else {
					errorMessage = "只能选择一家供应商进行确认";
					return "error";
				}
			} else {
				errorMessage = "只能选择是否发送样品！";
				return "error";
			}
		} else {
			errorMessage = "没有选择供应商";
			return "error";
		}
	}

	public String addSample() {
		if (zhuserOffer.getId() != null) {
			zhuserOffer = zhuserOfferServer
					.findZhOfferById(zhuserOffer.getId());
			if (zhuserOffer != null) {
				errorMessage = zhuserOfferServer.addSample(zhuserOffer, sample,
						status);
				return "findQueRenListByZhUser";
			} else {
				errorMessage = "没有找到该报价单";
				return "error";
			}
		} else {
			errorMessage = "没有找到该报价单";
			return "error";
		}
	}

	/*
	 * 手动添加样品
	 */
	public String addSampleForSample() {
		List<Sample> sList = zhuserOfferServer.addSampleByself(sample);
		if (sList != null) {
			return "";
		} else {
			errorMessage = "没有查到详情列表！";
			return "error";
		}
	}

	// 确认样品
	public String passYangpin() {
		if (offerId.length > 0 || offerId.length < 2) {
			errorMessage = zhuserOfferServer.passYangpin(offerId,
					yuanclAndWaigj.getId());
			return "error";
		} else {
			errorMessage = "只能选择一家供应商进行确认";
			return "error";
		}
	}

	public String getNowdate() {
		return nowdate;
	}

	public void setNowdate(String nowdate) {
		this.nowdate = nowdate;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ZhuserOfferServer getZhuserOfferServer() {
		return zhuserOfferServer;
	}

	public void setZhuserOfferServer(ZhuserOfferServer zhuserOfferServer) {
		this.zhuserOfferServer = zhuserOfferServer;
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

	public ZhuserOffer getZhuserOffer() {
		return zhuserOffer;
	}

	public void setZhuserOffer(ZhuserOffer zhuserOffer) {
		this.zhuserOffer = zhuserOffer;
	}

	public List<ZhuserOffer> getZhuserOfferList() {
		return zhuserOfferList;
	}

	public void setZhuserOfferList(List<ZhuserOffer> zhuserOfferList) {
		this.zhuserOfferList = zhuserOfferList;
	}

	public Integer[] getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer[] offerId) {
		this.offerId = offerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Sample> getSampleList() {
		return sampleList;
	}

	public void setSampleList(List<Sample> sampleList) {
		this.sampleList = sampleList;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public YuanclAndWaigj getYuanclAndWaigj() {
		return yuanclAndWaigj;
	}

	public void setYuanclAndWaigj(YuanclAndWaigj yuanclAndWaigj) {
		this.yuanclAndWaigj = yuanclAndWaigj;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public List<YuanclAndWaigj> getYuanclAndWaigjList() {
		return yuanclAndWaigjList;
	}

	public void setYuanclAndWaigjList(List<YuanclAndWaigj> yuanclAndWaigjList) {
		this.yuanclAndWaigjList = yuanclAndWaigjList;
	}

	public String getIssong() {
		return issong;
	}

	public void setIssong(String issong) {
		this.issong = issong;
	}

	public NoPriceprocess getNp() {
		return np;
	}

	public void setNp(NoPriceprocess np) {
		this.np = np;
	}

	public SumProcess getSumProcess() {
		return sumProcess;
	}

	public void setSumProcess(SumProcess sumProcess) {
		this.sumProcess = sumProcess;
	}

}
