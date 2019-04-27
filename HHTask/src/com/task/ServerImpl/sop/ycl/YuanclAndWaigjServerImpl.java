package com.task.ServerImpl.sop.ycl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
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
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.sop.ycl.YuanclAndWaigjServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.ess.ProcardBlServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Becoming;
import com.task.entity.Careertrack;
import com.task.entity.Category;
import com.task.entity.ChartNOGzType;
import com.task.entity.ChartNOSC;
import com.task.entity.Goods;
import com.task.entity.Store;
import com.task.entity.Users;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.sop.ManualOrderPlan;
import com.task.entity.sop.ManualOrderPlanDetail;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.WaigouDeliveryDetail;
import com.task.entity.sop.WaigouOrder;
import com.task.entity.sop.ycl.DosageTzDan;
import com.task.entity.sop.ycl.FenMoTzRecord;
import com.task.entity.sop.ycl.PanelSize;
import com.task.entity.sop.ycl.WaiGouJianLT;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.sop.ycl.YuanclAndWaigjAppli;
import com.task.entity.system.CircuitRun;
import com.task.entity.zhuseroffer.NoPriceprocess;
import com.task.entity.zhuseroffer.ZhuserOffer;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

public class YuanclAndWaigjServerImpl implements YuanclAndWaigjServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;
	private StringBuffer strb = new StringBuffer();

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	// 根据件号和库别查询
	@Override
	public List<YuanclAndWaigj> checkYuanclAndWaigjBycode(String markId,
			String wgType) {
		String hql = "from YuanclAndWaigj where markId=? and wgType = ?";
		return totalDao.query(hql, markId, wgType);
	}

	@Override
	public String add(YuanclAndWaigj yuanclAndWaigj) {
		Users user = Util.getLoginUser();
		if(user==null){
			return "请先登录！";
		}
		if (yuanclAndWaigj != null && yuanclAndWaigj.getMarkId() != null) {
			String markId = yuanclAndWaigj.getMarkId();
			String useguige = yuanclAndWaigj.getSpecification();
			// 技术规范号查找大类
			CodeTranslation codeTranslation = (CodeTranslation) totalDao
					.getObjectByCondition(
							"from CodeTranslation where keyCode=? and type='技术规范'",
							markId);
			String jsType = null;
			if (codeTranslation != null) {
				jsType = codeTranslation.getValueCode();
			}
			if (jsType != null && jsType.length() > 0) {
				String hasMarkId = (String) totalDao
						.getObjectByCondition("select markId from YuanclAndWaigj where (tuhao ='"
								+ markId
								+ "-"
								+ useguige
								+ "' or tuhao ='"
								+ markId
								+ useguige
								+ "'"
								+ " or (tuhao like '"
								+ markId
								+ "-%' and specification ='"
								+ useguige + "'))");
				if (hasMarkId != null && hasMarkId.length() > 0) {
					yuanclAndWaigj.setTuhao(markId + "-" + useguige);
					markId = hasMarkId;
				} else {
					if (jsType.length() == 4) {
						jsType = jsType + ".";
					}
					// 先去原材料外购件库里查询
					String dlType = "外购件";
					// if (hasMarkId == null) {
					hasMarkId = (String) totalDao
							.getObjectByCondition("select markId from ProcardTemplate where (dataStatus is null or dataStatus!='删除') and (tuhao ='"
									+ markId
									+ "-"
									+ useguige
									+ "' or tuhao ='"
									+ markId
									+ useguige
									+ "'"
									+ " or (tuhao like '"
									+ markId
									+ "-%' and specification ='"
									+ useguige
									+ "'))");
					// }
					if (hasMarkId != null && hasMarkId.length() > 0) {
						markId = hasMarkId;
					} else {
						Integer maxInteger = 0;
						// 需要新增
						String maxNo1 = (String) totalDao
								.getObjectByCondition("select max(markId) from YuanclAndWaigj where markId like'"
										+ jsType
										+ "%' and markId not like '"
										+ jsType + "%.%'");
						String maxNo2 = (String) totalDao
								.getObjectByCondition("select max(markId) from ProcardTemplate where  markId like'"
										+ jsType
										+ "%' and markId not like '"
										+ jsType + "%.%' ");// 因为当零件类型为待定时原材料外购件库里没有办法查询
						String maxNo3 = (String) totalDao
								.getObjectByCondition("select max(chartNO) from ChartNOSC where  chartNO like'"
										+ jsType
										+ "%' and chartNO not like '"
										+ jsType + "%.%'");
						String maxNo4 = (String) totalDao
								.getObjectByCondition("select max(markId) from ProcardTemplatesb where sbApplyId "
										+ "in(select id from ProcardTemplateBanBenApply where processStatus not in('设变发起','分发项目组','项目主管初评','资料更新','关联并通知生产','生产后续','关闭','取消'))"
										+ " and markId like'"
										+ jsType
										+ "%' and markId not like '"
										+ jsType
										+ "%.%' ");
						if (maxNo1 != null && maxNo1.length() > 0) {
							String[] maxStrs = maxNo1.split(jsType);
							if (maxStrs.length > 0 && maxStrs[1] != null) {
								try {
									Integer nowMax = Integer
											.parseInt(maxStrs[1]);
									if (maxInteger < nowMax) {
										maxInteger = nowMax;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
						if (maxNo2 != null && maxNo2.length() > 0) {
							String[] maxStrs = maxNo2.split(jsType);
							if (maxStrs.length > 0 && maxStrs[1] != null) {
								try {
									Integer nowMax = Integer
											.parseInt(maxStrs[1]);
									if (maxInteger < nowMax) {
										maxInteger = nowMax;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
						if (maxNo3 != null && maxNo3.length() > 0) {
							String[] maxStrs = maxNo3.split(jsType);
							if (maxStrs.length > 0 && maxStrs[1] != null) {
								try {
									Integer nowMax = Integer
											.parseInt(maxStrs[1]);
									if (maxInteger < nowMax) {
										maxInteger = nowMax;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
						if (maxNo4 != null && maxNo4.length() > 0) {
							String[] maxStrs = maxNo4.split(jsType);
							if (maxStrs.length > 0 && maxStrs[1] != null) {
								try {
									Integer nowMax = Integer
											.parseInt(maxStrs[1]);
									if (maxInteger < nowMax) {
										maxInteger = nowMax;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
						maxInteger++;
						List<String> list = new ArrayList<String>();
						yuanclAndWaigj.setTuhao(markId + "-" + useguige);
						if (jsType.length() == 5) {
							markId = jsType + String.format("%05d", maxInteger);

						} else if (jsType.length() == 6) {
							markId = jsType + String.format("%04d", maxInteger);
						}
						yuanclAndWaigj.setMarkId(markId);

					}
					// totalDao.clear();
				}
			}
			// 存放到编码库
			ChartNOSC chartnosc = (ChartNOSC) totalDao.getObjectByCondition(
					" from ChartNOSC where chartNO  =? ", markId);
			if (chartnosc == null && isChartNOGzType(markId)) {
				// 同步添加到编码库里
				chartnosc = new ChartNOSC();
				chartnosc.setChartNO(markId);
				chartnosc.setChartcode(Util.Formatnumber(markId));
				chartnosc.setIsuse("YES");
				chartnosc.setRemak("BOM导入");
				chartnosc.setSjsqUsers(user.getName());
				totalDao.save(chartnosc);
			}

		}
		if (yuanclAndWaigj.getMarkId() != null
				&& (yuanclAndWaigj.getMarkId().startsWith("DKBA0") || yuanclAndWaigj.getMarkId()
						.equals("DKBA3359"))) {

			return "技术规范类物料:" + yuanclAndWaigj.getMarkId()
					+ "需要编号,请前往物料编码添加该技术规范类对照数据!</br>";
		}
		if (yuanclAndWaigj != null && yuanclAndWaigj.getTuhao() != null
				&& yuanclAndWaigj.getTuhao().length() > 0) {
			Float size = (Float) totalDao
					.getObjectByCondition(
							"select count(*) from YuanclAndWaigj where markId!=? and tuhao=? and productStyle = ? ",
							yuanclAndWaigj.getMarkId(), yuanclAndWaigj
									.getTuhao(), yuanclAndWaigj
									.getProductStyle());
			if (size != null && size > 0) {
				return "对不起,此图号已被其他外购件占用";
			}
		}
		String sql = "from YuanclAndWaigj where markId=? and kgliao =? and (banbenStatus is null or banbenStatus!='历史') and productStyle = ? and status not in ('禁用')";
		List list = null;
		// 手动添加时不允许加入新版本数据，只需匹配件号和供料属性就可以了,新版本只能根据设变加入
		// if (yuanclAndWaigj.getBanbenhao() == null
		// || yuanclAndWaigj.getBanbenhao().length() == 0) {
		// sql +=
		// " and ( banbenhao is null or banbenhao ='') and productStyle = ? ";
		// list = totalDao.query(sql, yuanclAndWaigj.getMarkId(),
		// yuanclAndWaigj.getKgliao(), yuanclAndWaigj
		// .getProductStyle());
		// } else {
		// sql += " and banbenhao = ? and productStyle = ? ";
		// list = totalDao.query(sql, yuanclAndWaigj.getMarkId(),
		// yuanclAndWaigj.getKgliao(), yuanclAndWaigj.getBanbenhao(),
		// yuanclAndWaigj.getProductStyle());
		// }
		CodeTranslation codeTranslation = (CodeTranslation) totalDao
				.getObjectByCondition(
						"from CodeTranslation where keyCode=? and type='技术规范'",
						yuanclAndWaigj.getMarkId());
		if (codeTranslation != null) {
			return "件号:" + yuanclAndWaigj.getMarkId() + "为技术规范类，不允许手动添加。";
		}
		list = totalDao.query(sql, yuanclAndWaigj.getMarkId(), yuanclAndWaigj
				.getKgliao(), yuanclAndWaigj.getProductStyle());
		if (list.size() > 0) {
			return "该外购件已存在库中,添加失败!";
		}
		String banbenhaoSql = null;
		if(yuanclAndWaigj.getBanbenhao()==null||yuanclAndWaigj.getBanbenhao().length()==0){
			banbenhaoSql = " and (banbenhao is null or banbenhao='')";
		}else{
			if(yuanclAndWaigj.getTuhao()!=null&&(yuanclAndWaigj.getTuhao().startsWith("DKBA0")
					||yuanclAndWaigj.getTuhao().startsWith("dkba0"))){
				return "DKBA0类型外购件不允许有版本！";
			}
			banbenhaoSql = " and banbenhao='"+yuanclAndWaigj.getBanbenhao()+"'";
		}
		YuanclAndWaigj jy = (YuanclAndWaigj) totalDao.getObjectByCondition(" from YuanclAndWaigj where  markId=? and kgliao =? and status  in ('禁用') ", yuanclAndWaigj.getMarkId(), yuanclAndWaigj
				.getKgliao());
		if(jy!=null){
			return "该外购件已禁用,添加失败!";
		}
		boolean bb = false;
		yuanclAndWaigj.setStatus("使用");
		if ("试制".equals(yuanclAndWaigj.getProductStyle())) {
			int congt = totalDao
					.getCount(
							"from YuanclAndWaigj where markId = ? and productStyle='批产'",
							yuanclAndWaigj.getMarkId());
			if (congt > 0) {
				return "该外购件号已存在批产类型,试制类型无法添加!";
			}
		} else if ("批产".equals(yuanclAndWaigj.getProductStyle())) {
			int count = totalDao
					.getCount(
							"from YuanclAndWaigj where markId = ? and productStyle='试制'",
							yuanclAndWaigj.getMarkId());
			if (count > 0) {
				bb = true;
				YuanclAndWaigj andWaigj = (YuanclAndWaigj) totalDao
						.getObjectByCondition(
								"from YuanclAndWaigj where markId = ? and productStyle='试制'",
								yuanclAndWaigj.getMarkId());
				BeanUtils
						.copyProperties(andWaigj, yuanclAndWaigj, new String[] {
								"id", "productStyle", "banbenhao","kgliao","zhuserOfferSet" });
			}
			List<YuanclAndWaigj> deleteList =  totalDao
			.query(
					"from YuanclAndWaigj where markId = ? and productStyle='试制' and kgliao = ? " +banbenhaoSql,
					yuanclAndWaigj.getMarkId(),yuanclAndWaigj.getKgliao());
			if(deleteList!=null&&deleteList.size()>0){//批产新增之后试制的原有零件删除
				for(YuanclAndWaigj delete:deleteList){
					totalDao.delete(delete);
				}
			}
		}
		yuanclAndWaigj.setAddUserName(user.getName());// 添加人姓名
		yuanclAndWaigj.setAddUserCode(user.getCode());// 添加人工号
		yuanclAndWaigj.setAddTime(Util.getDateTime());// 添加时间
		boolean b = totalDao.save(yuanclAndWaigj);
		ChartNOSC chartnosc = (ChartNOSC) totalDao
				.getObjectByCondition("from ChartNOSC where chartNO = ? ",
						yuanclAndWaigj.getMarkId());
		if (chartnosc == null && isChartNOGzType(yuanclAndWaigj.getMarkId())) {
			// 同步添加到编码库里
			chartnosc = new ChartNOSC();
			chartnosc.setChartNO(yuanclAndWaigj.getMarkId());
			chartnosc.setChartcode(Util
					.Formatnumber(yuanclAndWaigj.getMarkId()));
			chartnosc.setIsuse("YES");
			chartnosc.setRemak("BOM导入");
			chartnosc.setSjsqUsers(user.getName());
			totalDao.save(chartnosc);
		}
		if (b) {
			if (bb)
				return "件号" + yuanclAndWaigj.getMarkId() + "批产类型。添加成功!"
						+ "由于该件号已有试制类型信息，本次添加 名称为：" + yuanclAndWaigj.getName()
						+ "规格为：" + yuanclAndWaigj.getSpecification() + "请检查！";
			else
				return "添加成功";
		} else {
			return "添加失败";
		}
	}

	@Override
	public String delete(YuanclAndWaigj yuanclAndWaigj) {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		if (user == null) {
			return "请先登录!";
		}
		YuanclAndWaigj old = (YuanclAndWaigj) totalDao.getObjectById(
				YuanclAndWaigj.class, yuanclAndWaigj.getId());
		if (old == null) {
			return "没有找到目标!";
		}

		String bbsql = null;
		if (old.getBanbenhao() != null && old.getBanbenhao().length() > 0) {
			bbsql = " and banbenhao ='" + old.getBanbenhao() + "'";
		} else {
			bbsql = " and (banbenhao is null or banbenhao='')";
		}
		String kgLiaoHql = null;
		if (old.getKgliao() != null && old.getKgliao().length() > 0) {
			kgLiaoHql = " and kgliao='" + old.getKgliao() + "'";
		} else {
			kgLiaoHql = " and (kgliao is null or kgliao ='')";
		}
		Float othercount = (Float) totalDao.getObjectByCondition(
				"select count(*) from YuanclAndWaigj where id !=? and markId=?"
						+ " and (banbenStatus is null or banbenStatus!='历史')"
						+ bbsql + kgLiaoHql, old.getId(), old.getMarkId());
		if (othercount != null && othercount > 0) {
			return totalDao.delete(old) + "";
		}
		if (old.getStatus() != null && old.getStatus().equals("已确认")) {
			return "该外购件已确认不允许删除!";
		}
		List list = null;
		// if (old.getClClass().equals("外购件")) {
		String sql = "select id from ProcardTemplate where procardStyle='外购' and markId=? and  kgliao=? and (banbenStatus is null or banbenStatus!='历史') and (dataStatus is null or dataStatus!='删除') ";
		// if(old.getSpecification()!=null&&old.getSpecification().length()>0){
		// sql += " and specification ='"+old.getSpecification()+"'";
		// }else{
		// sql +=" and (specification is null or specification='')";
		// }
		if (old.getBanbenhao() != null && old.getBanbenhao().length() > 0) {
			sql += " and banBenNumber ='" + old.getBanbenhao() + "'";
		} else {
			sql += " and (banBenNumber is null or banBenNumber='')";
		}
		list = totalDao.query(sql, old.getMarkId(), old.getKgliao());
		// if(old.getTuhao()==null||old.getTuhao().length()==0){
		// sql +=" and (tuhao is null or tuhao='')";
		// }else{
		// sql +=" and tuhao=?";
		// list=totalDao.query(sql, old.getMarkId(), old.getTuhao());
		// }

		// } else if (old.getClClass().equals("原材料")) {
		// String sql =
		// "select id from ProcardTemplate where markId = ? and specification=?";
		// if (old.getTuhao() == null || old.getTuhao().length() == 0) {
		// sql += " and (tuhao is null or ytuhao='')";
		// list = totalDao.query(sql, old.getMarkId(), old
		// .getSpecification());
		// } else {
		// sql += " and ytuhao=?";
		// list = totalDao.query(sql, old.getMarkId(), old
		// .getSpecification(), old.getTuhao());
		// }
		// }
		if (list != null && list.size() > 0) {
			return "此物料已在bom中被使用不能被删除!";
		}
		// 查询是否在物流库存中使用
		String wlSql = "from Goods where goodsMarkId=? and  kgliao =? ";
		List<Goods> wllist = null;
		if (old.getBanbenhao() != null && old.getBanbenhao().length() > 0) {
			wlSql += " and banBenNumber ='" + old.getBanbenhao() + "'";
		} else {
			wlSql += " and (banBenNumber is null or banBenNumber='')";
		}
		List bomlist = totalDao.query(wlSql, old.getMarkId(), old.getKgliao());
		if (bomlist != null && bomlist.size() > 0) {
			return "此物料已在库存中被使用不能被删除!";
		}
		return totalDao.delete(old) + "";
	}

	@Override
	public List<YuanclAndWaigj> findAll() {
		// TODO Auto-generated method stub
		return (List<YuanclAndWaigj>) totalDao.query("from YuanclAndWaigj");
	}

	@Override
	public String update(YuanclAndWaigj yuanclAndWaigj, String upstatus) {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		if (user == null) {
			return "请先登录!";
		}
		YuanclAndWaigj old = (YuanclAndWaigj) totalDao.getObjectById(
				YuanclAndWaigj.class, yuanclAndWaigj.getId());
		if (old == null) {
			return "没有找到目标";
		}
		if (old.getStatus() != null && old.getStatus().equals("已确认")) {
			if (!"delete".equals(upstatus)) {
				return "该外购件已确认不允许修改";
			}
		}
		if (!old.getMarkId().equals(yuanclAndWaigj.getMarkId())) {
			return "外购件号不允许修改！";
		}
		if (yuanclAndWaigj.getTuhao() != null
				&& yuanclAndWaigj.getTuhao().length() > 0) {
			Float size = (Float) totalDao
					.getObjectByCondition(
							"select count(*) from YuanclAndWaigj where id!=? and markId!=? and tuhao=? and status not in ('禁用')",
							yuanclAndWaigj.getId(), yuanclAndWaigj.getMarkId(),
							yuanclAndWaigj.getTuhao());
			if (size != null && size > 0) {
				return "对不起,此图号已被其他外购件占用";
			}
		}
		String sql1 = "from YuanclAndWaigj where id!=? and (banbenStatus is null or banbenStatus!='历史') and markId=? and kgliao =? ";
		int count = 0;
		if (yuanclAndWaigj.getBanbenhao() == null
				|| yuanclAndWaigj.getBanbenhao().length() == 0) {
			sql1 += " and ( banbenhao is null or banbenhao ='')   and productStyle = ?";
			count = totalDao.getCount(sql1, yuanclAndWaigj.getId(),
					yuanclAndWaigj.getMarkId(), yuanclAndWaigj.getKgliao(),
					yuanclAndWaigj.getProductStyle());
		} else {
			sql1 += " and banbenhao = ?  and productStyle = ?";
			count = totalDao.getCount(sql1, yuanclAndWaigj.getId(),
					yuanclAndWaigj.getMarkId(), yuanclAndWaigj.getKgliao(),
					yuanclAndWaigj.getBanbenhao(), yuanclAndWaigj
							.getProductStyle());
		}
		if (count > 0) {
			return "该外购件已存在库中,修改失败!";
		}
		List<YuanclAndWaigj> samewgjList = totalDao
				.query(
						"from YuanclAndWaigj where  id!=? and (banbenStatus is null or banbenStatus!='历史') and markId=?",
						yuanclAndWaigj.getId(), yuanclAndWaigj.getMarkId());
		// 查询是否在BOM模板中使用
		String bomsql = "from ProcardTemplate where markId=? " +
				"and (banbenStatus is null or banbenStatus !='历史') " +
				"and (dataStatus is null or dataStatus!='删除') and productStyle=?";
		List<ProcardTemplate> bomlist = null;
		if (yuanclAndWaigj.getBanbenhao() != null && yuanclAndWaigj.getBanbenhao().length() > 0) {
			if(yuanclAndWaigj.getTuhao()!=null&&(yuanclAndWaigj.getTuhao().startsWith("DKBA0")
					||yuanclAndWaigj.getTuhao().startsWith("dkba0"))){
				return "DKBA0类型外购件不允许有版本！";
			}
		}
		if (old.getBanbenhao() != null && old.getBanbenhao().length() > 0) {
			bomsql += " and banBenNumber ='" + old.getBanbenhao() + "'";
		} else {
			bomsql += " and (banBenNumber is null or banBenNumber='')";
		}
		// 查询是否在物流库存中使用
		String wlSql = "from Goods where goodsMarkId=? and  kgliao =?";
		List<Goods> wllist = null;
		if (old.getBanbenhao() != null && old.getBanbenhao().length() > 0) {
			wlSql += " and banBenNumber ='" + old.getBanbenhao() + "'";
		} else {
			wlSql += " and (banBenNumber is null or banBenNumber='')";
		}
		bomlist = totalDao.query(bomsql, old.getMarkId(),old.getProductStyle());
		// wllist = totalDao.query(wlSql, old.getMarkId(), old.getKgliao());
		if (old.getStatus() == null || old.getStatus().equals("使用")
				|| old.getStatus().equals("新增")) {
			// if (true) {
			if (bomlist != null && bomlist.size() > 0) {
				// 同步修改BOM模板的数据
				for (ProcardTemplate pt : bomlist) {
					if (pt.getBzStatus() != null
							&& pt.getBzStatus().equals("已批准")) {
						if (!"delete".equals(upstatus)) {
							return "对不起此物料已在BOM中使用并且编制状态为已批准不能修改!";
						}
					}
					pt.setMarkId(yuanclAndWaigj.getMarkId());
					pt.setProName(yuanclAndWaigj.getName());
					pt.setUnit(yuanclAndWaigj.getUnit());
					pt.setSpecification(yuanclAndWaigj.getSpecification());
					pt.setBanBenNumber(yuanclAndWaigj.getBanbenhao());
					pt.setLoadMarkId(yuanclAndWaigj.getZcMarkId());
					pt.setTuhao(yuanclAndWaigj.getTuhao());
					pt.setWgType(yuanclAndWaigj.getWgType());
					pt.setBili(yuanclAndWaigj.getBili());
					pt.setImportance(yuanclAndWaigj.getImportance());
					pt.setSunhao(yuanclAndWaigj.getSunhao());
					// pt.setSunhaoType(yuanclAndWaigj.getSunhaoType());
					// 当每公斤喷粉面积发生改变时 自动更新BOM上的权值
					if (yuanclAndWaigj.getAreakg() != null
							&& yuanclAndWaigj.getAreakg() > 0
							&& !yuanclAndWaigj.getAreakg().equals(
									old.getAreakg())) {
						ProcardTemplate fpt = pt.getProcardTemplate();
						Float mianji = 0f;
						if (fpt != null && fpt.getThisLength() != null
								&& fpt.getThisLength() > 0
								&& fpt.getThisWidth() != null
								&& fpt.getThisWidth() > 0) {
							mianji = fpt.getThisLength() * fpt.getThisWidth();
						}
						if (mianji > 0) {
							// 判断该粉末是否属于复合粉末中的一种;
							Integer mianshu = 2;
							List<PanelSize> fuhefenmoList = totalDao
									.query(" from PanelSize where type = '复合粉末' ");
							for (PanelSize panelSize : fuhefenmoList) {
								if (yuanclAndWaigj.getName().indexOf(
										panelSize.getFenmo1()) >= 0) {
									mianshu = panelSize.getMiancount1();
									break;
								} else if (yuanclAndWaigj.getName().indexOf(
										panelSize.getFenmo2()) >= 0) {
									mianshu = panelSize.getMiancount2();
									break;
								}
							}
							// quanzhi2 =
							// (mianji*fuhefenmo.getMiancount1()/fenmowgj1.getAreakg())
							// / 1000000;
							Float quanzhi2 = (mianji * mianshu / yuanclAndWaigj
									.getAreakg()) / 1000000;
							pt.setQuanzi2(quanzhi2);
						}
					}
					// pt.setProductStyle(yuanclAndWaigj.getProductStyle());
					totalDao.update(pt);
				}
			}
			// if (wllist != null && wllist.size() > 0) {
			// // 同步修改库存的数据
			// for (Goods g : wllist) {
			// g.setGoodsMarkId(yuanclAndWaigj.getMarkId());
			// g.setGoodsFullName(yuanclAndWaigj.getName());
			// g.setGoodsUnit(yuanclAndWaigj.getCkUnit());
			// g.setGoodsFormat(yuanclAndWaigj.getSpecification());
			// g.setBanBenNumber(yuanclAndWaigj.getBanbenhao());
			// g.setTuhao(yuanclAndWaigj.getTuhao());
			// g.setGoodsround(yuanclAndWaigj.getRound());
			// totalDao.update(g);
			// }
			// }
			old.setLtdengji(yuanclAndWaigj.getLtdengji());
			old.setLtuse(yuanclAndWaigj.getLtuse());
			old.setMinkc(yuanclAndWaigj.getMinkc());
			old.setCgcount(yuanclAndWaigj.getCgcount());
			old.setCgperiod(yuanclAndWaigj.getCgperiod());
			old.setPageObj(yuanclAndWaigj);
			old.setIszyl(yuanclAndWaigj.getIszyl());
			if (samewgjList != null && samewgjList.size() > 0) {
				for (YuanclAndWaigj samewgj : samewgjList) {
					samewgj.setLtdengji(yuanclAndWaigj.getLtdengji());
					samewgj.setLtuse(yuanclAndWaigj.getLtuse());
					samewgj.setMinkc(yuanclAndWaigj.getMinkc());
					samewgj.setCgcount(yuanclAndWaigj.getCgcount());
					samewgj.setCgperiod(yuanclAndWaigj.getCgperiod());
					samewgj.setPageObj2(yuanclAndWaigj);
					totalDao.update(samewgj);
				}
			}
			totalDao.update(old);
			return "修改成功";
		} else {
			if (bomlist != null && bomlist.size() > 0
					&& !"delete".equals(upstatus)) {
				return "对不起,此物料在库中状态为" + old.getStatus() + "并且已在BOM中使用不能修改!";
			} else if (wllist != null && wllist.size() > 0
					&& !"delete".equals(upstatus)) {
				return "对不起此物料在库中状态为" + old.getStatus() + "并且已在物流库存中使用不能修改!";
			} else {
				old.setLtdengji(yuanclAndWaigj.getLtdengji());
				old.setLtuse(yuanclAndWaigj.getLtuse());
				old.setMinkc(yuanclAndWaigj.getMinkc());
				old.setCgcount(yuanclAndWaigj.getCgcount());
				old.setCgperiod(yuanclAndWaigj.getCgperiod());
				old.setPageObj(yuanclAndWaigj);
				totalDao.update(old);
				if (samewgjList != null && samewgjList.size() > 0) {
					for (YuanclAndWaigj samewgj : samewgjList) {
						samewgj.setLtdengji(yuanclAndWaigj.getLtdengji());
						samewgj.setLtuse(yuanclAndWaigj.getLtuse());
						samewgj.setMinkc(yuanclAndWaigj.getMinkc());
						samewgj.setCgcount(yuanclAndWaigj.getCgcount());
						samewgj.setCgperiod(yuanclAndWaigj.getCgperiod());
						samewgj.setPageObj2(yuanclAndWaigj);
						totalDao.update(samewgj);
					}
				}
				return "修改成功";
			}
		}
		// } else if (old.getClClass() != null
		// && old.getClClass().equals("原材料")) {
		// if (yuanclAndWaigj.getTuhao() != null
		// && yuanclAndWaigj.getTuhao().length() > 0) {
		// Float size = (Float) totalDao
		// .getObjectByCondition(
		// "select count(*) from YuanclAndWaigj where (markId!=? or specification!=?) and tuhao=? and id!=?",
		// yuanclAndWaigj.getMarkId(), yuanclAndWaigj
		// .getSpecification(), yuanclAndWaigj
		// .getTuhao() == null ? ""
		// : yuanclAndWaigj.getTuhao(),
		// yuanclAndWaigj.getId());
		// if (size != null && size > 0) {
		// return "对不起,此图号已被其他原材料占用";
		// }
		// }
		// String yclSql =
		// "from YuanclAndWaigj where markId=? and specification=? and id!=?";
		// if (yuanclAndWaigj.getTuhao() != null
		// && yuanclAndWaigj.getTuhao().length() > 0) {
		// yclSql += " and tuhao='" + yuanclAndWaigj.getTuhao() + "'";
		// } else {
		// yclSql += " and (tuhao is null or tuhao='')";
		// }
		// List list = totalDao.query(yclSql, yuanclAndWaigj.getMarkId(),
		// yuanclAndWaigj.getSpecification(), yuanclAndWaigj.getId());
		// if (list.size() > 0) {
		// return "该原材料已存在库中,修改失败!";
		// }
		// String bomsql =
		// "from ProcardTemplate where markId = ? and specification=?";
		// String wlSql = "from Goods where goodsMarkId=? and goodsFormat=?";
		// if (old.getTuhao() != null && old.getTuhao().length() > 0) {
		// bomsql += " and ytuhao='" + old.getTuhao() + "'";
		// wlSql += " and tuhao='" + old.getTuhao() + "'";
		// } else {
		// bomsql += " and (ytuhao is null or ytuhao='')";
		// wlSql += " and (tuhao is null or tuhao='')";
		// }
		// List<ProcardTemplate> bomlist = null;
		// List<Goods> wllist = null;
		// bomlist = totalDao.query(bomsql, old.getMarkId(), old
		// .getSpecification());
		// wllist = totalDao.query(wlSql, old.getMarkId(), old
		// .getSpecification());
		// if (old.getStatus() == null || old.getStatus().equals("使用")) {
		// if (bomlist != null && bomlist.size() > 0) {
		// // 同步修改BOM模板的数据
		// for (ProcardTemplate pt : bomlist) {
		// pt.setTrademark(yuanclAndWaigj.getMarkId());
		// pt.setYuanName(yuanclAndWaigj.getName());
		// pt.setYuanUnit(yuanclAndWaigj.getUnit());
		// pt.setSpecification(yuanclAndWaigj.getSpecification());
		// // pt.setBanBenNumber(yuanclAndWaigj.getBanbenhao());
		// pt.setLoadMarkId(yuanclAndWaigj.getZcMarkId());
		// pt.setYtuhao(yuanclAndWaigj.getTuhao());
		// pt.setBili(yuanclAndWaigj.getBili());
		// totalDao.update(pt);
		// }
		// }
		// if (wllist != null && wllist.size() > 0) {
		// // 同步修改BOM模板的数据
		// for (Goods g : wllist) {
		// g.setGoodsMarkId(yuanclAndWaigj.getMarkId());
		// g.setGoodsFullName(yuanclAndWaigj.getName());
		// g.setGoodsUnit(yuanclAndWaigj.getCkUnit());
		// g.setGoodsFormat(yuanclAndWaigj.getSpecification());
		// g.setTuhao(yuanclAndWaigj.getTuhao());
		// // g.setBanBenNumber(yuanclAndWaigj.getBanbenhao());
		// // g.setLoadMarkId(yuanclAndWaigj.getZcMarkId());
		// g.setTuhao(yuanclAndWaigj.getTuhao());
		// g.setGoodsround(yuanclAndWaigj.getRound());
		// totalDao.update(g);
		// }
		// }
		// old.setPageObj(yuanclAndWaigj);
		// totalDao.update(old);
		// return "修改成功";
		// } else {
		// if (bomlist != null && bomlist.size() > 0) {
		// return "对不起此物料已在BOM中使用不能修改!";
		// } else if (wllist != null && wllist.size() > 0) {
		// return "对不起此物料已在物流库存中使用不能修改!";
		// } else {
		// old.setPageObj(yuanclAndWaigj);
		// totalDao.update(old);
		// return "修改成功";
		// }
		// }
		// } else if (old.getClClass() != null
		// && old.getClClass().equals("辅料")) {
		// old.setPageObj(yuanclAndWaigj);
		// totalDao.update(old);
		// return "修改成功";
		// } else {
		// return "不能识别材料类型,修改失败!";
		// }
	}

	@Override
	public Map<Integer, Object> findYuanclAndWaigjsByStatus(
			YuanclAndWaigj yuanclAndWaigj, int pageNo, int pageSize,
			String status,String kgliao) {
		String banbenStatus = "默认";
		if (yuanclAndWaigj == null) {
			yuanclAndWaigj = new YuanclAndWaigj();
		} else if (yuanclAndWaigj.getBanbenStatus() != null
				&& yuanclAndWaigj.getBanbenStatus().equals("历史")) {
			banbenStatus = "历史";
		}
		String sql = "1=1";
		// if ("invalid".equals(status)) {
		// sql = "pricestatus = '失效' order by id desc";
		// } else if ("newly".equals(status)) {
		if (banbenStatus.equals("默认")) {
			sql += " and (banbenStatus is null or  banbenStatus!='历史')";
		}
		if(kgliao!=null&&!"".equals(kgliao)){
			sql += " and kgliao = '"+kgliao+"'";
		}
		if ("newly".equals(status)) {
			sql += " and pricestatus in( '失效','新增') order by id desc";
		} else if ("ing".equals(status)) {
			sql += " and pricestatus in( '报价中') order by id desc";
		} else if ("dayang".equals(status)) {
			sql += " and pricestatus in('打样中' order by id desc)";
		} else if ("shenpi".equals(status)) {
			sql += " and pricestatus in( '未审批') order by id desc";
		} else if ("pass".equals(status)) {
			sql += " and pricestatus in( '同意') order by id desc";
		}
		
		String hql = totalDao.criteriaQueries(yuanclAndWaigj, sql, null);
		hql += " order by id desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	// public Map<Integer, Object> findwaigouwaiByStatus(
	// yuanclAndWaigj, int pageNo, int pageSize,
	// String status) {
	// }
	public Map<Integer, Object> findwaigouwaiByStatus(
			WaigouDeliveryDetail waigouDeliveryDetail, int pageNo,
			int pageSize, String status) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		return map;
	}

	@Override
	public Map<Integer, Object> findYuanclAndWaigjsByCondition(
			YuanclAndWaigj yuanclAndWaigj, int pageNo, int pageSize,
			String productStyle, String pageStatus) {
		String hql = null;
		String banbenStatus = "默认";
		if (yuanclAndWaigj == null) {
			yuanclAndWaigj = new YuanclAndWaigj();
		} else if (yuanclAndWaigj.getBanbenStatus() != null
				&& yuanclAndWaigj.getBanbenStatus().equals("历史")) {
			banbenStatus = "历史";
		}
		if (yuanclAndWaigj.getClClass() != null
				&& yuanclAndWaigj.getClClass().equals("所有")) {
			yuanclAndWaigj.setClClass(null);
		}
		String hql2 = "";
		String hql3 = "";
		if (yuanclAndWaigj.getStatus() == null
				|| yuanclAndWaigj.getStatus().length() == 0
				|| !yuanclAndWaigj.getStatus().equals("禁用")) {
			hql2 = " and (status is null or status!='禁用')";
			// yuanclAndWaigj.setStatus(null);
		}
		String str = "";
		String wgType = "";
		if (yuanclAndWaigj.getWgType() != null
				&& !"".equals(yuanclAndWaigj.getWgType())) {
			wgType = yuanclAndWaigj.getWgType();
			Category category = (Category) totalDao
					.getObjectByCondition(" from Category where name =? ",
							yuanclAndWaigj.getWgType());
			if (category != null) {
				getWgtype(category);
			}
			str = " and wgType in (" + strb.toString().substring(1) + ")";
			yuanclAndWaigj.setWgType(null);
		}
		hql = totalDao.criteriaQueries(yuanclAndWaigj, null);
		yuanclAndWaigj.setWgType(wgType);
		if (banbenStatus.equals("默认")) {
			hql += " and (banbenStatus is null or  banbenStatus!='历史')";
		}
		if ("pichan".equals(productStyle)) {
			hql3 = " and   productStyle='批产'";
		} else if ("shizhi".equals(productStyle)) {
			hql3 = " and  productStyle='试制'";
		} else if (productStyle == null) {
			hql3 = "";
		}
		Map<Integer, Object> map1 = new HashMap<Integer, Object>();
		if ("audit".equals(pageStatus)) {
			Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
					YuanclAndWaigj.class, false);
			if (map != null) {
				String hql1 = "from YuanclAndWaigj where id in (:entityId) order by markId desc";
				List list = totalDao.find(hql1, map, pageNo, pageSize);
				Long countLong = totalDao.count("select count(*) " + hql1, map);
				int count = Integer.parseInt(countLong.toString());
				map1.put(1, list);
				map1.put(2, count);
			} else {
				map1.put(1, null);
				map1.put(2, 0);
			}
		} else {
			if("yltz".equals(pageStatus)){
				str+=" and wgType = '粉末'";
			}
			hql = hql + hql2 + hql3 + str + " order by id desc";
			int count = totalDao.getCount(hql);
			List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map1.put(1, objs);
			map1.put(2, count);
		}
		strb = new StringBuffer();
		return map1;
	}

	private void getWgtype(Category category) {
		List<Category> list = totalDao.query(
				" from  Category where fatherId = ?", category.getId());
		if (list != null && list.size() > 0) {
			for (Category category2 : list) {
				getWgtype(category2);
			}
		} else {
			strb.append(",'" + category.getName() + "'");
		}
	}

	public void updatepriceStatus() {
		String hql = " from YuanclAndWaigj where pricestatus is null or pricestatus = ''";
		List<YuanclAndWaigj> yawList = totalDao.query(hql);
		String time = Util.getDateTime();
		for (YuanclAndWaigj yaw : yawList) {
			String hql_count = " from Price where partNumber = ? and kgliao=?";
			int count = 0;
			if (yaw.getBanbenhao() != null && yaw.getBanbenhao().length() > 0) {
				hql_count += " and banbenhao = ?";
				count = totalDao.getCount(hql_count, yaw.getMarkId(), yaw
						.getKgliao(), yaw.getBanbenhao());
			} else {
				count = totalDao.getCount(hql_count, yaw.getMarkId(), yaw
						.getKgliao());
			}
			if (count == 0) {
				yaw.setPricestatus("新增");
			} else {
				yaw.setPricestatus("失效");
				String hql_yx = " from Price where partNumber = ? and pricePeriodStart<='"
						+ time
						+ "' and(pricePeriodEnd >= '"
						+ time
						+ "' or pricePeriodEnd is null or pricePeriodEnd = '')";
				count = totalDao.getCount(hql_yx, yaw.getMarkId());
				if (count > 0) {
					yaw.setPricestatus("有效");
				}
			}
			totalDao.update(yaw);
		}
	}

	@Override
	public YuanclAndWaigj getById(Integer id) {
		// TODO Auto-generated method stub
		return (YuanclAndWaigj) totalDao
				.getObjectById(YuanclAndWaigj.class, id);
	}
	public Map<Integer, Object> findyuan(String ids) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String id_str ="";
		if(ids!=null){
			String hql = "select wgType from YuanclAndWaigj where id in ("+ids+") group by wgType";
			List<String> wgType_list = totalDao.find(hql);
			if(wgType_list.size()==1){
				List<YuanclAndWaigj> list = totalDao.find("from YuanclAndWaigj where id in ("+ids+")");
				if(list!=null&&list.size()>0){
					map.put(1,"ok");
					map.put(2, list);
					map.put(3, wgType_list.get(0));
				}else{
					map.put(1,"no");
				}
			}
		}
		return map;
	}

	@Override
	public List<YuanclAndWaigj> getAllNames(YuanclAndWaigj yuanclAndWaigj) {
		// TODO Auto-generated method stub
		if (yuanclAndWaigj.getMarkId() != null) {
			String[] strs = yuanclAndWaigj.getMarkId().split(":");
			// if (null == yuanclAndWaigj.getWgType()
			// || "".equals(yuanclAndWaigj.getWgType())) {
			// yuanclAndWaigj.setWgType(yuanclAndWaigj.getClClass());
			// }
			String kgliao = "";
			if (null != yuanclAndWaigj.getKgliao()
					&& !"".equals(yuanclAndWaigj.getKgliao())) {
				kgliao = "and kgliao ='" + yuanclAndWaigj.getKgliao() + "' ";
			}
			if (strs.length >= 2) {
				String markId = strs[0];
				String name = strs[1];
				String sql = "from YuanclAndWaigj where  markId like '%"
						+ markId + "%' and name like '%" + name + "%' "
						+ kgliao;
				return (List<YuanclAndWaigj>) totalDao.query(sql);
			} else {
				String sql = "from YuanclAndWaigj where (markId like '"
						+ yuanclAndWaigj.getMarkId() + "%' or name like '%"
						+ yuanclAndWaigj.getMarkId() + "%') " + kgliao;
				List<YuanclAndWaigj> yclList = totalDao
						.findAllByPage(sql, 1, 5);
				if (yclList != null && yclList.size() > 0) {
					for (YuanclAndWaigj ycl : yclList) {
						String cangqu = (String) totalDao
								.getObjectByCondition(
										" select goodHouseName from Goods where goodsMarkId = ? and goodsClass = '外购件库' order by id desc ",
										ycl.getMarkId());
						if (cangqu != null && cangqu.length() > 0) {
							ycl.setCangqu(cangqu);
						}
					}
				}
				return yclList;
			}
		}
		// if (yuanclAndWaigj.getClClass() != null
		// && yuanclAndWaigj.getClClass().equals("外购件")) {
		// if (yuanclAndWaigj.getMarkId() != null) {
		// String[] strs = yuanclAndWaigj.getMarkId().split(":");
		// if (strs.length >= 2) {
		// String markId = strs[0];
		// String name = strs[1];
		// String sql = "from YuanclAndWaigj where markId like '%"
		// + markId + "%' and name like '%" + name + "%' ";
		// return (List<YuanclAndWaigj>) totalDao.query(sql);
		// } else {
		// String sql = "from YuanclAndWaigj where (markId like '%"
		// + yuanclAndWaigj.getMarkId() + "%' or name like '%"
		// + yuanclAndWaigj.getMarkId() + "%') ";
		// return (List<YuanclAndWaigj>) totalDao.query(sql);
		//
		// }
		// }
		// } else if (yuanclAndWaigj.getClClass() != null
		// && yuanclAndWaigj.getClClass().equals("原材料")) {
		// if (yuanclAndWaigj.getMarkId() != null) {
		// String[] strs = yuanclAndWaigj.getMarkId().split(":");
		// if (strs.length >= 2) {
		// String trademark = strs[0];
		// String specification = strs[1];
		// String sql = "from YuanclAndWaigj where trademark like '%"
		// + trademark + "%' and specification like '%"
		// + specification + "%' ";
		// return (List<YuanclAndWaigj>) totalDao.query(sql);
		// } else {
		// String sql = "from YuanclAndWaigj where (trademark like '%"
		// + yuanclAndWaigj.getMarkId()
		// + "%' or specification like '%"
		// + yuanclAndWaigj.getMarkId() + "%') ";
		// return (List<YuanclAndWaigj>) totalDao.query(sql);
		//
		// }
		// }
		// } else if (yuanclAndWaigj.getClClass() != null
		// && yuanclAndWaigj.getClClass().equals("辅料")) {
		// String sql = "from YuanclAndWaigj where name like '%"
		// + yuanclAndWaigj.getName() + "%'";
		// return (List<YuanclAndWaigj>) totalDao.query(sql);
		// }
		return null;
	}

	@Override
	public boolean updateHad() {
		// TODO Auto-generated method stub
		List<ProcardTemplate> ptwgList = totalDao
				.query("from ProcardTemplate where procardStyle='外购' and markId not in(select markId from YuanclAndWaigj where clClass='外购件') order by markId");
		// List<Goods>
		// goodwgList=totalDao.query("from Goods where goodsClass='外购件库' and goodsMarkId not in(select markId from YuanclAndWaigj where clClass='外购件')");
		List<ProcardTemplate> ptyclList = totalDao
				.query("from ProcardTemplate where (procardStyle='自制' or danjiaojian='单交件') and trademark+specification+yuanUnit not in (select trademark+specification+unit from YuanclAndWaigj where clClass='原材料') order by trademark,specification");
		// List<Goods>
		// goodyclList=totalDao.query("from Goods where goodsClass='原材料' and goodsMarkId+goodsFormat+goodsUnit not in (select trademark+specification+unit from YuanclAndWaigj where clClass!='外购件')");
		List<Store> storeFuliaoList = totalDao
				.query("from Store where storehouse='综合库' and matetag+format+unit not in (select name+specification+unit from YuanclAndWaigj where clClass='辅料') order by matetag,format");
		boolean b = true;
		if (ptwgList.size() > 0) {
			List<ProcardTemplate> addptwgList = new ArrayList<ProcardTemplate>();
			List<String> addMarkId = new ArrayList<String>();
			for (ProcardTemplate ptwg1 : ptwgList) {
				if (ptwg1.getMarkId() != null
						&& !addMarkId.contains(ptwg1.getMarkId())) {
					addMarkId.add(ptwg1.getMarkId());
					addptwgList.add(ptwg1);
				}
			}
			for (ProcardTemplate ptwg2 : addptwgList) {
				YuanclAndWaigj wgj = new YuanclAndWaigj();
				wgj.setMarkId(ptwg2.getMarkId());
				wgj.setName(ptwg2.getProName());
				wgj.setUnit(ptwg2.getUnit());
				wgj.setClClass("外购件");
				b = b & totalDao.save(wgj);
			}
		}
		if (ptyclList.size() > 0) {
			List<ProcardTemplate> addptyclList = new ArrayList<ProcardTemplate>();
			List<String> addfalgString = new ArrayList<String>();
			for (ProcardTemplate ptycl1 : ptyclList) {
				if (ptycl1.getTrademark() != null
						&& ptycl1.getSpecification() != null
						&& !addfalgString.contains(ptycl1.getTrademark()
								+ ptycl1.getSpecification())) {
					addfalgString.add(ptycl1.getTrademark()
							+ ptycl1.getSpecification());
					addptyclList.add(ptycl1);
				}
			}
			for (ProcardTemplate ptycl2 : addptyclList) {
				YuanclAndWaigj wgj = new YuanclAndWaigj();
				wgj.setSpecification(ptycl2.getSpecification());
				wgj.setUnit(ptycl2.getYuanUnit());
				wgj.setMarkId(ptycl2.getMarkId());
				wgj.setClClass("原材料");
				b = b & totalDao.save(wgj);
			}
		}
		if (storeFuliaoList.size() > 0) {
			List<Store> addflList = new ArrayList<Store>();
			List<String> addfalgString = new ArrayList<String>();
			for (Store store1 : storeFuliaoList) {
				if (store1.getMatetag() != null
						&& store1.getFormat() != null
						&& !addfalgString.contains(store1.getMatetag()
								+ store1.getFormat())) {
					addfalgString.add(store1.getMatetag() + store1.getFormat());
					addflList.add(store1);
				}
			}
			for (Store store2 : addflList) {
				YuanclAndWaigj wgj = new YuanclAndWaigj();
				wgj.setName(store2.getMatetag());
				wgj.setUnit(store2.getUnit());
				wgj.setSpecification(store2.getFormat());
				wgj.setClClass("辅料");
				b = b & totalDao.save(wgj);
			}
		}
		// if(goodwgList.size()>0){
		// for(Goods goodwg:goodwgList){
		// YuanclAndWaigj wgj=new YuanclAndWaigj();
		// wgj.setMarkId(goodwg.getGoodsMarkId());
		// wgj.setName(goodwg.getGoodsFullName());
		// wgj.setUnit(goodwg.getGoodsUnit());
		// wgj.setClClass("外购件");
		// b=b&totalDao.save(wgj);
		// }
		// }
		// if(goodyclList.size()>0){
		// for(Goods goodycl:goodyclList){
		// YuanclAndWaigj wgj=new YuanclAndWaigj();
		// wgj.setSpecification(goodycl.getGoodsFormat());
		// wgj.setUnit(goodycl.getGoodsUnit());
		// wgj.setTrademark(goodycl.getGoodsMarkId());
		// wgj.setClClass("其他");
		// b=b&totalDao.save(wgj);
		// }
		// }
		return b;
	}

	@Override
	public String updateStatus(YuanclAndWaigj yuanclAndWaigj) {
		// TODO Auto-generated method stub
		YuanclAndWaigj old = (YuanclAndWaigj) totalDao.getObjectById(
				YuanclAndWaigj.class, yuanclAndWaigj.getId());
		if (old != null) {
			if (old.getStatus() == null || old.getStatus().equals("")
					|| old.getStatus().equals("使用")) {
				old.setStatus("已确认");
				return "确认成功";
			} else if (old.getStatus().equals("已确认")) {
				// old.setStatus("禁用");同意后自动变为禁用
				if ("禁用申请中".equals(old.getJystatus())) {
					return "此件号已申请禁用，请等待同意。";
				}
				return addYaWa(yuanclAndWaigj, old, "禁用");
			} else if (old.getStatus().equals("禁用")) {
				if (old.getBanbenStatus() == null
						|| old.getBanbenStatus().equals("默认")) {
					// old.setStatus("使用");
					if ("使用申请中".equals(old.getJystatus())) {
						return "此件号已申请使用，请等待同意。";
					}
					return addYaWa(yuanclAndWaigj, old, "使用");
				} else {
					return "此版本非当前可用版本不允许解禁成功";
				}
			}
			return null;
		} else {
			return "没有找到对应的物料,操作失败!";
		}
	}

	public String addYaWa(YuanclAndWaigj yuanclAndWaigj, YuanclAndWaigj old,
			String process) {
		Integer epId = null;
		YuanclAndWaigjAppli yawa = new YuanclAndWaigjAppli();
		BeanUtils.copyProperties(old, yawa, new String[] { "id", "epId" });
		Users us = Util.getLoginUser();
		if (us == null)
			return "请先登录";
		yawa.setAddTime(Util.getDateTime());
		yawa.setRemark(yuanclAndWaigj.getRemark());// 禁用备注
		yawa.setAddUserCode(us.getCode());
		yawa.setAddUserName(us.getName());
		yawa.setYuanId(old.getId());
		yawa.setStyle(process);
		totalDao.save(yawa);
		String processName = "外购件" + process + "申请";
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					YuanclAndWaigjAppli.class, yawa.getId(), "status", "id",
					null, us.getDept() + "部门  " + us.getName() + "的"
							+ yawa.getMarkId() + processName + "，请您审批！", true);
			if (epId != null && epId > 0) {
				yawa.setEpId(epId);
				CircuitRun circuitRun = (CircuitRun) totalDao.get(
						CircuitRun.class, epId);
				if ("同意".equals(circuitRun.getAllStatus())
						&& "审批完成".equals(circuitRun.getAuditStatus())) {
					yawa.setStatus("同意");
					old.setJystatus("同意");
					old.setStatus("禁用");
				} else {
					yawa.setStatus("未审批");
					old.setJystatus(process + "申请中");
				}
				if (totalDao.update(yawa)) {
					old.setJyepId(epId);
					totalDao.update(old);
					return "申请成功！";
				}
			} else {
				return processName + "流程不存在，申请失败！";
			}
		} catch (Exception e) {
			return e.toString();
		}
		return "申请失败！请重新申请";
	}

	@Override
	public YuanclAndWaigj getYWbytrademark(String markId, String specification) {
		if (!"".equals(markId)) {
			String hql = "from YuanclAndWaigj where markId=? ";
			YuanclAndWaigj yw = (YuanclAndWaigj) totalDao.getObjectByCondition(
					hql, markId);
			return yw;
		}
		return null;
	}

	@Override
	public void explorExcel(YuanclAndWaigj yw) {
		String hql = null;
		if (yw == null) {
			yw = new YuanclAndWaigj();

		}
		if (yw.getClClass() != null && yw.getClClass().equals("所有")) {
			yw.setClClass(null);
		}
		String hql2 = "";
		if (yw.getStatus() == null || yw.getStatus().length() == 0
				|| !yw.getStatus().equals("禁用")) {
			hql2 = " and (status is null or status!='禁用')";
		}

		hql = totalDao.criteriaQueries(yw, null);
		hql = hql
				+ hql2
				+ "  and (banbenStatus is null or banbenStatus!='历史')  order by id desc";
		List<YuanclAndWaigj> list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("原材料外购件库信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("原材料外购件库信息", 0);
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
			jxl.write.Label label0 = new Label(0, 0, "原材料外购件库信息汇总", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 15, 0);
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "件号", wc));
			// ws.addCell(new jxl.write.Label(2, 1, "牌号", wc));
			ws.addCell(new jxl.write.Label(2, 1, "生产类型", wc));
			ws.addCell(new jxl.write.Label(3, 1, "规格", wc));
			ws.addCell(new jxl.write.Label(4, 1, "图号", wc));
			ws.addCell(new jxl.write.Label(5, 1, "单位", wc));
			ws.addCell(new jxl.write.Label(6, 1, "比重", wc));
			ws.addCell(new jxl.write.Label(7, 1, "仓库单位", wc));
			ws.addCell(new jxl.write.Label(8, 1, "版本号", wc));
			ws.addCell(new jxl.write.Label(9, 1, "总成号", wc));
			ws.addCell(new jxl.write.Label(10, 1, "材料类型", wc));
			ws.addCell(new jxl.write.Label(11, 1, "材质类型", wc));
			ws.addCell(new jxl.write.Label(12, 1, "供料属性", wc));
			ws.addCell(new jxl.write.Label(13, 1, "状态", wc));
			ws.addCell(new jxl.write.Label(14, 1, "质检周期", wc));
			ws.addCell(new jxl.write.Label(15, 1, "添加人", wc));
			ws.addCell(new jxl.write.Label(16, 1, "添加时间", wc));
			ws.addCell(new jxl.write.Label(17, 1, "备注", wc));
			ws.addCell(new jxl.write.Label(18, 1, "名称", wc));
			for (int i = 0; i < list.size(); i++) {
				YuanclAndWaigj yj = (YuanclAndWaigj) list.get(i);
				float bili = 0;
				if (null != yj.getBili()) {
					bili = yj.getBili();
				}
				float round = 0;
				if (null != yj.getRound()) {
					round = yj.getRound();
				}
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, yj.getMarkId(), wc));

				// ws.addCell(new Label(2, i + 2, yj.getTrademark(), wc));
				ws.addCell(new Label(2, i + 2, yj.getProductStyle(), wc));

				ws.addCell(new Label(3, i + 2, yj.getSpecification(), wc));
				ws.addCell(new Label(4, i + 2, yj.getTuhao(), wc));
				ws.addCell(new Label(5, i + 2, yj.getUnit(), wc));
				ws.addCell(new jxl.write.Number(6, i + 2, bili, wc));
				ws.addCell(new Label(7, i + 2, yj.getCkUnit(), wc));
				ws.addCell(new Label(8, i + 2, yj.getBanbenhao(), wc));
				ws.addCell(new Label(9, i + 2, yj.getZcMarkId(), wc));
				// ws.addCell(new Label(10, i + 2, yj.getZcMarkId(),
				// wc));为啥和上面一样？
				ws.addCell(new Label(10, i + 2, yj.getClClass(), wc));
				ws.addCell(new Label(11, i + 2, yj.getWgType(), wc));
				ws.addCell(new Label(12, i + 2, yj.getKgliao(), wc));
				ws.addCell(new Label(13, i + 2, yj.getStatus(), wc));
				ws.addCell(new jxl.write.Number(14, i + 2, round, wc));
				ws.addCell(new Label(15, i + 2, yj.getAddUserName(), wc));
				ws.addCell(new Label(16, i + 2, yj.getAddTime(), wc));
				ws.addCell(new Label(17, i + 2, yj.getRemark(), wc));
				ws.addCell(new Label(18, i + 2, yj.getName(), wc));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}

	// 所有物料类别的供应商
	public Map<Integer, Object> checkZhUser(ZhUser zhUser, int pageNo,
			int pageSize, String wgType) {
		if (zhUser == null) {
			zhUser = new ZhUser();
		}
//		YuanclAndWaigj yuanclAndWaigj = (YuanclAndWaigj) totalDao.get(
//				YuanclAndWaigj.class, id);
		String hql1 = "id in (select z.id From  ZhUser z join z.categoryset c where c.name ='"
				+ wgType + "')";
		String hql = totalDao.criteriaQueries(zhUser, hql1, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	public String bandZhuser(Integer[] zhUserId, Integer yuanclAndWaigjId) {
		if (zhUserId.length > 0 && zhUserId != null) {
			for (Integer ids : zhUserId) {
				String zOffer_hql = "from ZhuserOffer where zhUserId=? and yuanclAndWaigj.id =?";
				ZhuserOffer zOffer_old = (ZhuserOffer) totalDao
						.getObjectByCondition(zOffer_hql, ids, yuanclAndWaigjId);
				ZhUser zhUser = (ZhUser) totalDao.get(ZhUser.class, ids);
				YuanclAndWaigj yuanclAndWaigj = (YuanclAndWaigj) totalDao.get(
						YuanclAndWaigj.class, yuanclAndWaigjId);
				if (zOffer_old == null) {
					ZhuserOffer zOffer = new ZhuserOffer();
					zOffer.setMarkId(yuanclAndWaigj.getMarkId());
					zOffer.setName(yuanclAndWaigj.getName());
					zOffer.setSpecification(yuanclAndWaigj.getSpecification());
					zOffer.setBanbenhao(yuanclAndWaigj.getBanbenhao());
					zOffer.setWgType(yuanclAndWaigj.getWgType());
					zOffer.setZhUserId(zhUser.getId());
					zOffer.setKgliao(yuanclAndWaigj.getKgliao());
					zOffer.setCmp(zhUser.getCmp());
					zOffer.setUsercode(zhUser.getUsercode());
					zOffer.setCperson(zhUser.getCperson());
					zOffer.setCtel(zhUser.getCtel());
					zOffer.setStatus("未报价");
					zOffer.setJoinDate(yuanclAndWaigj.getBjStartDate());
					zOffer.setEndDate(yuanclAndWaigj.getBjEndDate());
					zOffer.setYuanclAndWaigj(yuanclAndWaigj);
					totalDao.save(zOffer);
				} else {
					return "供应商(" + zhUser.getCmp() + ")已经参与报价！";
				}
			}
			return "报价成功！";
		} else {
			return "未选择供应商，无法添加！！";
		}
	}

	// 添加报价周期
	@Override
	public boolean saveZhUserByType(YuanclAndWaigj yuanclAndWaigj, String cycle) {
		if (yuanclAndWaigj != null) {
			if (yuanclAndWaigj.getWgType() != null) {
				Integer deadline = Integer.parseInt(cycle);
				// String hql =
				// "from ZhUser where id in (select z.id From  ZhUser z join z.categoryset c where c.name ='"
				// + yuanclAndWaigj.getWgType() + "')";
				// List<ZhUser> zhUserList = (List<ZhUser>) totalDao.query(hql);
				// if (zhUserList.size() > 0 && zhUserList != null) {
				// for (ZhUser z : zhUserList) {
				// ZhuserOffer zOffer = new ZhuserOffer();
				// zOffer.setMarkId(yuanclAndWaigj.getMarkId());
				// zOffer.setName(yuanclAndWaigj.getName());
				// zOffer.setSpecification(yuanclAndWaigj
				// .getSpecification());
				// zOffer.setBanbenhao(yuanclAndWaigj.getBanbenhao());
				// zOffer.setWgType(yuanclAndWaigj.getWgType());
				// zOffer.setZhUserId(z.getId());
				// zOffer.setKgliao(yuanclAndWaigj.getKgliao());
				// zOffer.setCmp(z.getCmp());
				// zOffer.setUsercode(z.getUsercode());
				// zOffer.setCperson(z.getCperson());
				// zOffer.setCtel(z.getCtel());
				// zOffer.setStatus("未报价");
				// zOffer.setJoinDate(Util.getDateTime("yyyy-MM-dd"));
				// zOffer.setCycle(cycle);
				// zOffer.setEndDate(Util.getSpecifiedDayAfter(Util
				// .getDateTime("yyyy-MM-dd"), deadline));
				// zOffer.setYuanclAndWaigj(yuanclAndWaigj);
				// totalDao.save(zOffer);
				// }
				yuanclAndWaigj.setPricestatus("报价中");
				yuanclAndWaigj.setBjStartDate(Util.getDateTime("yyyy-MM-dd"));
				yuanclAndWaigj.setBjEndDate(Util.getSpecifiedDayAfter(Util
						.getDateTime("yyyy-MM-dd"), deadline));
				return totalDao.update(yuanclAndWaigj);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<ZhuserOffer> findzhOfferById(YuanclAndWaigj yuanclAndWaigj) {
		if (yuanclAndWaigj != null) {
			String hql = "From ZhuserOffer  where yuanclAndWaigj.id ='"
					+ yuanclAndWaigj.getId() + "' order by hsPrice desc";
			List<ZhuserOffer> list = totalDao.query(hql);
			return list;
		}
		return null;
	}

	public boolean exportNoPrice(YuanclAndWaigj yuanclAndWaigj) {
		// TODO Auto-generated method stub
		try {
			if (yuanclAndWaigj == null) {
				yuanclAndWaigj = new YuanclAndWaigj();
			}
			String sql = " (banbenStatus is null or banbenStatus!='历史') and pricestatus ='新增' order by id desc";
			String hql = totalDao.criteriaQueries(yuanclAndWaigj, sql, null);
			List<YuanclAndWaigj> list = totalDao.find(hql);
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("未录入外购价格表".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("未录入外购价格表", 0);
			WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 11,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
			wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
			wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			wcf_title.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			ws.addCell(new Label(0, 0, "外购价格表", wcf_title));
			ws.mergeCells(0, 0, 16, 0);
			ws.addCell(new Label(0, 1, "序号"));
			ws.addCell(new Label(1, 1, "件号"));
			ws.addCell(new Label(2, 1, "名称"));
			ws.addCell(new Label(3, 1, "规格"));
			ws.addCell(new Label(4, 1, "税率"));
			ws.addCell(new Label(5, 1, "含税价"));
			ws.addCell(new Label(6, 1, "不含税价"));
			ws.addCell(new Label(7, 1, "型别"));
			ws.addCell(new Label(8, 1, "合同编号"));
			ws.addCell(new Label(9, 1, "供应商编号"));
			ws.addCell(new Label(10, 1, "开始时间"));
			ws.addCell(new Label(11, 1, "失效时间"));
			ws.addCell(new Label(12, 1, "订货量(从)"));
			ws.addCell(new Label(13, 1, "订货量(到)"));
			ws.addCell(new Label(14, 1, "采购比例"));
			ws.addCell(new Label(15, 1, "供料属性"));
			ws.addCell(new Label(16, 1, "版本号"));
			int i = 0;
			for (YuanclAndWaigj y : list) {
				ws.addCell(new Label(0, i + 2, i + 1 + ""));
				ws.addCell(new Label(1, i + 2, y.getMarkId()));
				ws.addCell(new Label(2, i + 2, y.getName()));
				ws.addCell(new Label(3, i + 2, y.getSpecification()));
				ws.addCell(new Label(4, i + 2, ""));
				ws.addCell(new Label(5, i + 2, ""));
				ws.addCell(new Label(6, i + 2, ""));
				ws.addCell(new Label(7, i + 2, ""));
				ws.addCell(new Label(8, i + 2, ""));
				ws.addCell(new Label(9, i + 2, ""));
				ws.addCell(new Label(10, i + 2, ""));
				ws.addCell(new Label(11, i + 2, ""));
				ws.addCell(new Label(12, i + 2, ""));
				ws.addCell(new Label(13, i + 2, ""));
				ws.addCell(new Label(14, i + 2, ""));
				ws.addCell(new Label(15, i + 2, y.getKgliao()));
				ws.addCell(new Label(16, i + 2, y.getBanbenhao()));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void KgLiaoPart() {
		// String hql = "from ProcardTemplate where procardstyle='外购' "
		// + "and markId not in (select markId from YuanclAndWaigj)";
		// List list = totalDao.query(hql);
		// for (int i = 0; i < list.size(); i++) {
		// ProcardTemplate pt = (ProcardTemplate) list.get(i);
		// YuanclAndWaigj newwgj = new YuanclAndWaigj();
		// newwgj.setMarkId(pt.getMarkId());// 件号
		// newwgj.setName(pt.getProName());// 名称
		// // this.trademark=pageObj.getTrademark();// 牌号
		// newwgj.setSpecification(pt.getSpecification());// 规格
		// newwgj.setUnit(pt.getUnit());// BOM用的单位
		// // newwgj.setCkUnit();//仓库用的单位
		// newwgj.setBanbenhao(pt.getBanBenNumber());// 版本号
		// newwgj.setZcMarkId(pt.getRootMarkId());// 总成号
		// newwgj.setClClass("外购件");// 材料类型（外购件,原材料,辅料）
		// // newwgj.setCaizhi();//材质（如：五金、塑胶。。。。）
		// // newwgj.setRemark();//备注
		// newwgj.setBili(pt.getBili());// 比重
		// newwgj.setTuhao(pt.getTuhao());// 图号
		// // newwgj.setRound();//质检周期;
		// newwgj.setWgType(pt.getWgType());// 物料类别
		// newwgj.setKgliao(pt.getKgliao());// 供料属性
		// newwgj.setPricestatus("新增");
		// newwgj.setProductStyle(pt.getProductStyle());
		// totalDao.save(newwgj);
		// }

		// TODO Auto-generated method stub
		List<String> list = totalDao
				.query("select distinct markId from ProcardTemplate where procardStyle='外购' "
						+ "and (banbenStatus is null or banbenStatus !='历史')");
		if (list != null && list.size() > 0) {
			for (String markId : list) {
				List<String> otherkgList = totalDao
						.query(
								"select distinct kgliao from ProcardTemplate where kgliao is not null and kgliao!='' and  markId=? and (banbenStatus is null or banbenStatus !='历史') "
										+ "and kgliao not in(select kgliao from YuanclAndWaigj where kgliao is not null and kgliao!='' and markId=? )",
								markId, markId);
				if (otherkgList != null && otherkgList.size() > 0) {
					String banen = (String) totalDao
							.getObjectByCondition(
									"select banBenNumber from ProcardTemplate where markId=? and (banbenStatus is null or banbenStatus !='历史')",
									markId);
					String banbenSql = null;
					if (banen == null || banen.length() == 0) {
						banbenSql = " and (banbenhao is null or banbenhao='') ";
					} else {
						banbenSql = " and banbenhao='" + banen + "'";
					}
					YuanclAndWaigj wgj = (YuanclAndWaigj) totalDao
							.getObjectByCondition(
									"from YuanclAndWaigj where markId=? "
											+ banbenSql + " order by id desc",
									markId);
					ProcardTemplate pt = null;
					if (wgj == null) {
						pt = (ProcardTemplate) totalDao
								.getObjectByCondition(
										"from ProcardTemplate where (banbenStatus is null or banbenStatus !='历史') and markId=?",
										markId);
					}
					for (String kgliao : otherkgList) {
						if (wgj != null) {
							if (wgj.getKgliao() == null
									|| wgj.getKgliao().length() == 0) {
								wgj.setKgliao(kgliao);
								totalDao.update(wgj);
							} else {
								YuanclAndWaigj newwgj = new YuanclAndWaigj();
								BeanUtils.copyProperties(wgj, newwgj,
										new String[] { "id", "kgliao",
												"zhuserOfferSet" });
								newwgj.setKgliao(kgliao);
								totalDao.save(newwgj);
							}
						} else if (pt != null) {
							YuanclAndWaigj newwgj = new YuanclAndWaigj();
							newwgj.setMarkId(pt.getMarkId());// 件号
							newwgj.setName(pt.getProName());// 名称
							// this.trademark=pageObj.getTrademark();// 牌号
							newwgj.setSpecification(pt.getSpecification());// 规格
							newwgj.setUnit(pt.getUnit());// BOM用的单位
							// newwgj.setCkUnit();//仓库用的单位
							newwgj.setBanbenhao(pt.getBanBenNumber());// 版本号
							newwgj.setZcMarkId(pt.getRootMarkId());// 总成号
							newwgj.setClClass("外购件");// 材料类型（外购件,原材料,辅料）
							// newwgj.setCaizhi();//材质（如：五金、塑胶。。。。）
							// newwgj.setRemark();//备注
							newwgj.setBili(pt.getBili());// 比重
							newwgj.setTuhao(pt.getTuhao());// 图号
							// newwgj.setRound();//质检周期;
							newwgj.setWgType(pt.getWgType());// 物料类别
							newwgj.setKgliao(kgliao);// 供料属性
							newwgj.setPricestatus("新增");
							newwgj.setProductStyle(pt.getProductStyle());
							totalDao.save(newwgj);
						}
					}
				}
			}
		}
	}

	@Override
	public String xgWgType(YuanclAndWaigj yuanclAndWaigj) {
		if (yuanclAndWaigj != null) {
			YuanclAndWaigj y = (YuanclAndWaigj) totalDao.get(
					YuanclAndWaigj.class, yuanclAndWaigj.getId());
			y.setNewwgType(yuanclAndWaigj.getWgType());
			Users user = Util.getLoginUser();
			String processName = "物料类别修改申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						YuanclAndWaigj.class, y.getId(), "epStatus", "id",
						"yuanclAndWaigjAction_toupdate.action?yuanclAndWaigj.id="
								+ y.getId() + "&status=showWgType", user
								.getDept()
								+ "部门 " + user.getName() + "物料类别修改申请，请您审批",
						true);
				if (epId != null && epId > 0) {
					y.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						y.setEpStatus("同意");
						// 更新 外购件库状态;
						// y.setWgType(yuanclAndWaigj.getWgType());
						// y.setNewwgType(null);
						// 同步更新BOM状态
						// String bomsql =
						// " from ProcardTemplate where markId=?  and  kgliao =? ";
						// String procardsql =
						// " from Procard where markId=?  and  kgliao =?  ";
						// String banbensql = "";
						// if (y.getBanbenhao() != null
						// && y.getBanbenhao().length() > 0) {
						// bomsql += " and banBenNumber = '"
						// + y.getBanbenhao() + "'";
						// procardsql += "  and banBenNumber = '"
						// + y.getBanbenhao() + "'";
						// banbensql = " and banben = '" + y.getBanbenhao()
						// + "'";
						// } else {
						// bomsql +=
						// " and (banBenNumber is null or banBenNumber='')";
						// procardsql +=
						// " and (banBenNumber is null or banBenNumber='')";
						// banbensql = " and (banben is null or banben='')";
						// }
						// List<ProcardTemplate> bomList =
						// totalDao.query(bomsql,
						// y.getMarkId(), y.getKgliao());
						// for (ProcardTemplate bom : bomList) {
						// bom.setWgType(yuanclAndWaigj.getWgType());
						// totalDao.update(bom);
						// }
						// List<Procard> procardList =
						// totalDao.query(procardsql,
						// y.getMarkId(), y.getKgliao());
						// for (Procard procard : procardList) {
						// procard.setWgType(yuanclAndWaigj.getWgType());
						// totalDao.update(procard);
						// }
						// // 修改物料需求上的数据
						// String hql_mop =
						// "from ManualOrderPlan where markId = ? and kgliao = ?  "
						// + banbensql;
						// List<ManualOrderPlan> mopList =
						// totalDao.query(hql_mop,
						// y.getMarkId(), y.getKgliao());
						// for (ManualOrderPlan mop : mopList) {
						// mop.setWgType(yuanclAndWaigj.getWgType());
						// totalDao.update(mop);
						// }
						//
						// // 修改物料需求明细上的数据
						// String hql_mod =
						// "from ManualOrderPlanDetail where markId = ? and kgliao = ?  "
						// + banbensql;
						// List<ManualOrderPlanDetail> modList = totalDao.query(
						// hql_mop, y.getMarkId(), y.getKgliao());
						// for (ManualOrderPlanDetail mod : modList) {
						// mod.setWgType(yuanclAndWaigj.getWgType());
						// totalDao.update(mod);
						// }
					} else {
						y.setEpStatus("未审批");
					}
					return totalDao.update(y) + "";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String addWaiGouJianLt(WaiGouJianLT lt) {
		if (lt != null) {
			Integer count = (Integer) totalDao.getCount(
					" from WaiGouJianLT where ltdengji =? ", lt.getLtdengji());
			if (count > 0) {
				return "已有LT等级为：" + lt.getLtdengji() + "无需重复添加.";
			}
			return totalDao.save(lt) + "";
		}
		return null;
	}

	@Override
	public Object[] findALlWaiGouJianLT(WaiGouJianLT lt, int pageNo,
			int pageSize, String status) {
		if (lt == null) {
			lt = new WaiGouJianLT();
		}
		String hql = totalDao.criteriaQueries(lt, null);
		List<WaiGouJianLT> ltList = totalDao.findAllByPage(hql, pageNo,
				pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { ltList, count };
	}

	@Override
	public String delWaiGouJianLt(WaiGouJianLT lt) {
		if (lt != null) {
			return totalDao.delete(lt) + "";
		}
		return null;
	}

	@Override
	public String updateWaiGouJianLt(WaiGouJianLT lt) {
		if (lt != null) {
			return totalDao.update(lt) + "";
		}
		return null;
	}

	@Override
	public WaiGouJianLT findWaiGouJianLTById(Integer id) {
		if (id != null && id > 0) {
			return (WaiGouJianLT) totalDao.get(WaiGouJianLT.class, id);
		}
		return null;
	}

	@Override
	public List<WaiGouJianLT> findAllltList() {
		return totalDao.query(" from WaiGouJianLT order by ltdengji");
	}

	@Override
	public String xgperiod(YuanclAndWaigj yuanclAndWaigj) {
		if (yuanclAndWaigj != null && yuanclAndWaigj.getId() != null) {
			YuanclAndWaigj oldyclAndwgj = (YuanclAndWaigj) totalDao.get(
					YuanclAndWaigj.class, yuanclAndWaigj.getId());
			oldyclAndwgj.setCgperiod(yuanclAndWaigj.getCgperiod());
			oldyclAndwgj.setCgcount(yuanclAndWaigj.getCgcount());
			oldyclAndwgj.setLtdengji(yuanclAndWaigj.getLtdengji());
			oldyclAndwgj.setLtuse(yuanclAndWaigj.getLtuse());
			oldyclAndwgj.setMinkc(yuanclAndWaigj.getMinkc());
			oldyclAndwgj.setMaxkc(yuanclAndWaigj.getMaxkc());
			oldyclAndwgj.setSunhaoType(yuanclAndWaigj.getSunhaoType());
			oldyclAndwgj.setSunhao(yuanclAndWaigj.getSunhao());
			return totalDao.update(oldyclAndwgj) + "";
		}
		return null;
	}

	@Override
	public String daoruperiod(File yclAndwgjfile) {

		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int insize = 0;
		try {
			FileCopyUtils.copy(yclAndwgjfile, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}
			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 1) {
				String msg1 = "";
				for (int i = 1; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);//
					String markId = cells[0].getContents();// 件号
					if (markId != null && markId.length() > 0) {
						String ltdengji_str = cells[1].getContents();// lt等级
						String ltuse_str = cells[2].getContents();// lt+?周
						Double minkc = 0d;
						Double cgcount = 0d;
						Double cgperiod = 0d;
						// if(cells[3].getType() == CellType.NUMBER){
						// NumberCell numberCell = (NumberCell) cells[3]; //
						// 工序点数
						// minkc =numberCell.getValue();
						// }
						// if(cells[4].getType() == CellType.NUMBER){
						// NumberCell numberCell = (NumberCell) cells[3]; //
						// 工序点数
						// cgcount =numberCell.getValue();
						// }
						// if(cells[5].getType() == CellType.NUMBER){
						// NumberCell numberCell = (NumberCell) cells[3]; //
						// 工序点数
						// cgperiod =numberCell.getValue();
						// }
						Integer ltdengji = 0;
						Integer ltadd = 0;
						Integer ltuse = 0;
						if (ltdengji_str != null && ltdengji_str.length() > 0) {
							ltdengji = Integer.parseInt(ltdengji_str);
						}
						if (ltuse_str != null && ltuse_str.length() > 0) {
							ltuse = Integer.parseInt(ltuse_str);
						}
						ltadd = ltuse - ltdengji;
						List<YuanclAndWaigj> yclAndWgjList = totalDao.query(
								" from YuanclAndWaigj where markId =?", markId);
						if (yclAndWgjList != null && yclAndWgjList.size() > 0) {
							for (YuanclAndWaigj yuanclAndWaigj : yclAndWgjList) {
								yuanclAndWaigj.setLtdengji(ltdengji);
								yuanclAndWaigj.setLtuse(ltuse);
								yuanclAndWaigj.setCgperiod(cgperiod
										.floatValue());
								yuanclAndWaigj.setCgcount(cgcount.floatValue());
								yuanclAndWaigj.setMinkc(minkc.floatValue());
								totalDao.update(yuanclAndWaigj);
							}
						}
					} else {
						continue;
					}
					if (i % 200 == 0) {
						totalDao.clear();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "true";

	}

	/*
	 * 导入外购件库
	 */
	public String importFile(File importFile) {
		String msg = "true";
		// boolean flag = true;
		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int i = 0;
		try {
			FileCopyUtils.copy(importFile, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}
			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				List<Integer> strList = new ArrayList<Integer>();
				List<String> tishiList = new ArrayList<String>();
				// key 存初始件号+规格；list 0:原材料,外购件；1：前缀；2：编号
				Map<String, List<String>> bmmap = new HashMap<String, List<String>>();
				for (i = 2; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					if (cells[2].getContents() != null
							&& cells[2].getContents() != "") {
						String a = cells[1].getContents();// 物料类别
						String b = cells[2].getContents();// 件号1
						String x = cells[3].getContents();// 生产类型
						String c = cells[4].getContents();// 名称
						String d = cells[5].getContents();// 规格1
						String e = cells[6].getContents();// 版本号1
						String f = cells[7].getContents();// 供料属性1
						if (f != null)
							f = f.trim();
						String g = cells[8].getContents();// 单位
						String h = cells[9].getContents();// 重要性
						String j = cells[10].getContents();// 材质
						String k = cells[11].getContents();// 图号
						String l = cells[12].getContents();// 单张重量
						String m = cells[13].getContents();// 密度
						String n = cells[14].getContents();// 每公斤喷粉面积
						String o = cells[15].getContents();// 仓库单位
						String p = cells[16].getContents();// 总成号
						String q = cells[17].getContents();// 质检周期
						String r = cells[18].getContents();// 备注
						String s = cells[19].getContents();// 可用状态
						String t = cells[20].getContents();// 物料状态
						YuanclAndWaigj yuanclAndWaigj_old = new YuanclAndWaigj();
						if ("".equals(b) && b == null && "".equals(d)
								&& d == null && "".equals(e) && e == null
								&& "".equals(f) && f == null && "".equals(x)
								&& x == null) {
							tishiList.add("判定外购件不满足（件号，规格，版本号，供料属性，生产类型）");
							strList.add(i + 1);
							continue;
						} else {
							String banbenSql = "";
							if (e == null || e.length() == 0) {
								banbenSql = " and (banbenhao is null or banbenhao !='')";
							} else {
								banbenSql = " and banbenhao='" + e + "'";
							}
							String yuanclAndWaigj_hql = "from YuanclAndWaigj where markId = ? and specification =? "
									+ banbenSql
									+ " and kgliao = ? and productStyle=? and (banbenStatus = null or banbenStatus ='' or banbenStatus ='默认')";
							yuanclAndWaigj_old = (YuanclAndWaigj) totalDao
									.getObjectByCondition(yuanclAndWaigj_hql,
											b, d, f, x);
							if (yuanclAndWaigj_old != null) {
								tishiList.add("外购件已经存在");
								strList.add(i + 1);
								continue;
							}
						}
						YuanclAndWaigj yuanclAndWaigj_new = new YuanclAndWaigj();
						if ("".equals(a) && a == null) {// 物料类别不为空
							tishiList.add("物料类别为空");
							strList.add(i + 1);
							continue;
						} else {
							String hql_wuliao = "from Category where name = ?";
							Category category = (Category) totalDao
									.getObjectByCondition(hql_wuliao, a);
							if (category != null) {// 物料类别存在
								yuanclAndWaigj_new.setWgType(a);
							} else {
								tishiList.add("物料类别不存在");
								strList.add(i + 1);
								continue;
							}
						}
						if ("".equals(b) && b == null) {// 件号不为空
							tishiList.add("件号为空");
							strList.add(i + 1);
							continue;
						} else {
							// 技术规范号查找大类
							List<String> sameMax = bmmap.get(b + d);
							if (sameMax != null) {
								c = sameMax.get(3);
								if (sameMax.get(1).length() == 5) {
									// if (sameMax.get(0).equals("原材料")) {
									// markId = sameMax.get(1)
									// + String.format("%05d", Integer
									// .parseInt(sameMax.get(2)));
									// } else {
									k = b;
									b = sameMax.get(1)
											+ String.format("%05d", Integer
													.parseInt(sameMax.get(2)));
									// }

								} else if (sameMax.get(1).length() == 6) {
									// if (sameMax.get(0).equals("原材料")) {
									// markId = sameMax.get(1)
									// + String.format("%04d", Integer
									// .parseInt(sameMax.get(2)));
									// } else {
									k = b;
									b = sameMax.get(1)
											+ String.format("%04d", Integer
													.parseInt(sameMax.get(2)));
									// }
								}
							} else {
								CodeTranslation codeTranslation = (CodeTranslation) totalDao
										.getObjectByCondition(
												"from CodeTranslation where keyCode=? and type='技术规范'",
												a);
								String jsType = null;
								if (codeTranslation != null) {
									jsType = codeTranslation.getValueCode();
								}
								if (jsType != null && jsType.length() > 0) {
									c = codeTranslation.getValueName();
									if (jsType.length() == 4) {
										jsType = jsType + ".";
									}
									// 先去原材料外购件库里查询
									String hasMarkId = (String) totalDao
											.getObjectByCondition(
													"select markId from YuanclAndWaigj where  productStyle='批产' and specification=? and  markId like'"
															+ jsType + "%'", d);
									if (hasMarkId == null) {
										hasMarkId = (String) totalDao
												.getObjectByCondition(
														"select markId from ProcardTemplate where showSize=? and markId like'"
																+ jsType + "%'",
														d);
									}
									// 需要新增
									Integer maxInteger = 0;
									// 先遍历map
									for (String key : bmmap.keySet()) {
										List<String> samjsType = bmmap.get(key);
										if (samjsType != null
												&& samjsType.get(1).equals(
														jsType)) {
											Integer nomax = Integer
													.parseInt(samjsType.get(2));
											if (maxInteger < nomax) {
												maxInteger = nomax;
											}
										}
									}
									if (maxInteger == 0) {
										String maxNo1 = (String) totalDao
												.getObjectByCondition("select max(markId) from YuanclAndWaigj where  productStyle='批产'  and markId like'"
														+ jsType + "%'");
										String maxNo2 = (String) totalDao
												.getObjectByCondition("select max(markId) from ProcardTemplate where  markId like'"
														+ jsType + "%' ");// 因为当零件类型为待定时原材料外购件库里没有办法查询
										String maxNo3 = (String) totalDao
												.getObjectByCondition("select max(chartNO) from ChartNOSC where  chartNO like'"
														+ jsType + "%' ");
										if (maxNo1 != null
												&& maxNo1.length() > 0) {
											String[] maxStrs = maxNo1
													.split(jsType);
											if (maxStrs.length > 0
													&& maxStrs[1] != null) {
												try {
													Integer nowMax = Integer
															.parseInt(maxStrs[1]);
													if (maxInteger < nowMax) {
														maxInteger = nowMax;
													}
												} catch (Exception e2) {
													// TODO: handle exception
												}
											}
										}
										if (maxNo2 != null
												&& maxNo2.length() > 0) {
											String[] maxStrs = maxNo2
													.split(jsType);
											if (maxStrs.length > 0
													&& maxStrs[1] != null) {
												try {
													Integer nowMax = Integer
															.parseInt(maxStrs[1]);
													if (maxInteger < nowMax) {
														maxInteger = nowMax;
													}
												} catch (Exception e2) {
													// TODO: handle exception
												}
											}
										}
										if (maxNo3 != null
												&& maxNo3.length() > 0) {
											String[] maxStrs = maxNo3
													.split(jsType);
											if (maxStrs.length > 0
													&& maxStrs[1] != null) {
												try {
													Integer nowMax = Integer
															.parseInt(maxStrs[1]);
													if (maxInteger < nowMax) {
														maxInteger = nowMax;
													}
												} catch (Exception e2) {
													// TODO: handle exception
												}
											}
										}
									}
									maxInteger++;
									List<String> list = new ArrayList<String>();
									list.add(jsType);
									list.add(maxInteger + "");
									list.add(c);
									bmmap.put(b + d, list);
									if (jsType.length() == 5) {
										b = jsType
												+ String.format("%05d",
														maxInteger);

									} else if (jsType.length() == 6) {
										b = jsType
												+ String.format("%04d",
														maxInteger);
									}
								}

							}
							if (b != null
									&& (b.startsWith("GB") || b
											.startsWith("gb"))) {
								tishiList.add("国标件:" + b + "无法在国标编码库中查到!");
								strList.add(i + 1);
								continue;
								// jymsg += "第" + (i + 1) + "行,国标件:" + markId
								// + "无法在国标编码库中查到!</br>";
							}

							if (b != null
									&& (b.startsWith("DKBA0") || b
											.equals("DKBA3359"))) {
								tishiList.add("技术规范类物料:" + b + "需要编号!");
								strList.add(i + 1);
								continue;
								// jymsg += "第" + (i + 1) + "行,技术规范类物料:" + b
								// + "需要编号!</br>";
							}
							yuanclAndWaigj_new.setMarkId(b);
						}
						if ("试制".equals(x) || "批产".equals(x)) {// 生产类型
							yuanclAndWaigj_new.setProductStyle(x);
						} else {
							tishiList.add("生产类型不正确");
							strList.add(i + 1);
							continue;
						}
						if ("".equals(c) && c == null) {// 名称不为空
							tishiList.add("名称为空");
							strList.add(i + 1);
							continue;
						} else {
							yuanclAndWaigj_new.setName(c);
						}
						if ("".equals(d) && d == null) {// 规格不为空
							tishiList.add("规格为空");
							strList.add(i + 1);
							continue;
						} else {
							yuanclAndWaigj_new.setSpecification(d);
						}
						if ("".equals(e) && e == null) {// 版本号不为空
							tishiList.add("版本号为空");
							strList.add(i + 1);
							continue;
						} else {
							yuanclAndWaigj_new.setBanbenhao(e);
						}
						if ("".equals(f) && f == null) {// 供料属性不为空
							tishiList.add("供料属性为空");
							strList.add(i + 1);
							continue;
						} else {
							yuanclAndWaigj_new.setKgliao(f);
						}
						if ("".equals(g) && g == null) {// 单位不为空
							tishiList.add("单位为空");
							strList.add(i + 1);
							continue;
						} else {
							yuanclAndWaigj_new.setUnit(g);
						}
						yuanclAndWaigj_new.setImportance(h);
						yuanclAndWaigj_new.setCaizhi(j);
						yuanclAndWaigj_new.setTuhao(k);
						try {
							yuanclAndWaigj_new.setBili(Float.parseFloat(l));
							yuanclAndWaigj_new.setDensity(Float.parseFloat(m));
							yuanclAndWaigj_new.setAreakg(Float.parseFloat(n));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if ("".equals(o) && o == null) {// 仓库单位不为空
							tishiList.add("仓库单位为空");
							strList.add(i + 1);
							continue;
						} else {
							yuanclAndWaigj_new.setCkUnit(o);
						}
						yuanclAndWaigj_new.setZcMarkId(p);
						try {
							yuanclAndWaigj_new.setRound(Float.parseFloat(q));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						yuanclAndWaigj_new.setRemark(r);
						if ("".equals(s) && s == null) {// 可用状态不为空
							tishiList.add("可用状态为空");
							strList.add(i + 1);
							continue;
						} else {
							yuanclAndWaigj_new.setStatus(s);
						}
						if ("".equals(t) && t == null) {// 物料状态不为空
							tishiList.add("物料状态为空");
							strList.add(i + 1);
							continue;
						} else {
							yuanclAndWaigj_new.setPricestatus(t);
						}
						totalDao.save(yuanclAndWaigj_new);
					}

				}
				is.close();// 关闭流
				wk.close();// 关闭工作薄
				if (strList != null && strList.size() > 0) {
					msg = "失败" + strList.size() + "个，失败（行数/原因）为：\\n";
					for (int j = 0; j < strList.size(); j++) {
						msg += "第" + strList.get(j) + "行 -原因："
								+ tishiList.get(j) + "\\n";

					}
				}
			} else {
				msg = "没有获取到行数";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "系统异常";

		}
		return msg;
	}

	@Override
	public List<YuanclAndWaigj> checkYuanclAndWaigjBycode(
			YuanclAndWaigj yuanclAndWaigj) {
		// String queries = totalDao.criteriaQueries(yuanclAndWaigj, null);
		String hql = "from YuanclAndWaigj where clClass=? and markId=? and kgliao =? ";
		return totalDao.query(hql, yuanclAndWaigj.getClClass(), yuanclAndWaigj
				.getMarkId(), yuanclAndWaigj.getKgliao());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YuanclAndWaigj> checkYuanclAndWaigjBycode(
			YuanclAndWaigj yuanclAndWaigj, String tags) {
		String queries = totalDao.criteriaQueries(yuanclAndWaigj, null);
		// String hql =
		// "from YuanclAndWaigj where clClass=? and markId=? and kgliao =? ";
		queries += " and markId = '" + yuanclAndWaigj.getMarkId() + "' ";
		return totalDao.query(queries);
	}

	@Override
	public String auditYclWgl(Integer[] ids, Integer num) {

		String mess = "";
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				YuanclAndWaigj ycl = (YuanclAndWaigj) totalDao.getObjectById(
						YuanclAndWaigj.class, id);
				if (ycl != null) {
					if (num == 1) {// 同意
						mess = circuitRunServer.updateExeNodeByCirId(ycl
								.getEpId(), true, "", true, null, true);
					} else if (num == 2) {// 打回
						mess = circuitRunServer.updateExeNodeByCirId(ycl
								.getEpId(), false, "", true, null, true);
					}
					CircuitRun circuitRun = (CircuitRun) totalDao
							.getObjectById(CircuitRun.class, ycl.getEpId());
					if (circuitRun.getAllStatus().equals("同意")) {
						ycl.setEpStatus("同意");
					} else {
						ycl.setEpStatus("打回");
					}
					totalDao.update(ycl);
				}
			}
		} else {
			return "数据异常!";
		}
		return mess;

	}

	@Override
	public String noUpdateYuan(String tag) {
		// TODO Auto-generated method stub
		if (!"delete".equals(tag) && ProcardBlServerImpl.SystemShezhi("外购禁止修改"))
			return "noUpdate";
		return tag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findMarkIdByMRPCount(
			YuanclAndWaigj yuanclAndWaigj, Integer pageSize, Integer pageNo,
			String pageStatus) {
		if (yuanclAndWaigj == null) {
			yuanclAndWaigj = new YuanclAndWaigj();
		}
		String formSql = totalDao.criteriaQueries(yuanclAndWaigj, null);
		String hql = " select markId " + formSql;
		List<YuanclAndWaigj> list = totalDao.findAllByPage(formSql, pageNo,
				pageSize);
		for (YuanclAndWaigj wgj : list) {
			/***************** MRP计算(库存量&&&&&占用量) *****************/

			String kgsql = "";
			if (wgj.getKgliao() != null && wgj.getKgliao().length() > 0) {
				kgsql += " and kgliao ='" + wgj.getKgliao() + "'";
			}
			// String goodsClassSql =
			// " and ((goodsClass in ('外购件库','中间库') "
			// + kgsql + " ) or goodsClass = '备货库')";
			String goodsClassSql = " and goodsClass in ('外购件库') " + kgsql;
			String banben_hql = "";
			String banben_hql2 = "";
			if (wgj.getBanbenhao() != null && wgj.getBanbenhao().length() > 0) {
				banben_hql = " and banBenNumber='" + wgj.getBanbenhao() + "'";
				banben_hql2 = " and banben='" + wgj.getBanbenhao() + "'";
			}
			String specification_sql = "";
			// if (wgj.getSpecification() != null
			// && wgj.getSpecification().length() > 0) {
			// specification_sql = " and specification = '"
			// + wgj.getSpecification() + "' ";
			// }

			// 库存量(件号+版本+供料属性+库别)
			String hqlGoods = "";
			hqlGoods = "select sum(goodsCurQuantity) from Goods where goodsMarkId=? "
					+ goodsClassSql
					+ " and goodsCurQuantity>0 "
					+ banben_hql
					+ " and (fcStatus is null or fcStatus='可用')";
			Float kcCount = (Float) totalDao.getObjectByCondition(hqlGoods, wgj
					.getMarkId());
			if (kcCount == null || kcCount < 0) {
				kcCount = 0f;
			}

			/****************** 占用量=生产占用量+导入占用量 ******************************/
			// 系统占用量(含损耗)(已计算过采购量(1、有库存 2、采购中)，未领料)
			String zyCountSql = "select sum(hascount) from Procard where markId=? and kgliao=? "//
					+ banben_hql
					+ " and jihuoStatua='激活' and (status='已发卡' or (oldStatus='已发卡' and status='设变锁定')) and procardStyle='外购' and (lingliaostatus='是' or lingliaostatus is null ) "
					+ " and (sbStatus<>'删除' or sbStatus is null ) ";
			Double zyCountD = (Double) totalDao.getObjectByConditionforDouble(
					zyCountSql, wgj.getMarkId(), wgj.getKgliao());
			if (zyCountD == null || zyCountD < 0) {
				zyCountD = 0d;
			}
			Float zyCount = zyCountD.floatValue();


			// // 导入占用量(系统切换时导入占用量)
			// String hqlGoods_zy =
			// "select sum(goodsCurQuantity) from Goods where goodsMarkId=?"
			// + banben_hql
			// +
			// " and goodsClass in ('占用库') and kgliao=? and goodsCurQuantity>0 and (fcStatus is null or fcStatus='可用')";
			// Float kcCount_zy = (Float) totalDao.getObjectByCondition(
			// hqlGoods_zy, procard.getMarkId(), procard
			// .getKgliao());
			// if (kcCount_zy == null || kcCount_zy < 0) {
			// kcCount_zy = 0f;
			// }
			// zyCount += kcCount_zy;
			// if (zyCount < 0) {
			// zyCount = 0F;
			// }
			/****************** 结束 占用量=生产占用量+导入占用量 结束 ******************************/
			/****************** 在途量=采购在途量+导入在途量 ******************************/
			// 系统在途量(已生成采购计划，未到货)
			// String hql_zc =
			// "select sum(cgNumber-dhNumber) from  Procard where markId=? and productStyle=? "
			// + banben_hql
			// +
			// " and kgliao=? and jihuoStatua='激活' and status='已发卡' and procardStyle='外购'"
			// +
			// " and cgNumber >0 and dhNumber is not null and (sbStatus is null or sbStatus!='删除')";
			// Float ztCount = (Float) totalDao.getObjectByCondition(
			// hql_zc, procard.getMarkId(), procard
			// .getProductStyle(), procard.getKgliao());

			// 系统在途量(已生成物料需求信息，未到货)
			String hql_zc0 = "select sum(number-ifnull(rukuNum,0)) from ManualOrderPlan where markId = ?  and kgliao=?"// 
					+ banben_hql2
					+ " and (number>rukuNum or rukuNum is null) and (status<>'取消' or status is null) "
					+ specification_sql;
			Double ztCountd = (Double) totalDao.getObjectByCondition(hql_zc0,
					wgj.getMarkId(), wgj.getKgliao());
			if (ztCountd == null) {
				ztCountd = 0D;
			}
			Float ztCount = ztCountd.floatValue();

			// // 导入在途量(系统切换时导入在途量)
			// String hqlGoods_zt =
			// "select sum(goodsCurQuantity) from Goods where goodsMarkId=?"
			// + banben_hql
			// +
			// " and kgliao=? and goodsClass in ('在途库') and goodsCurQuantity>0 and (fcStatus is null or fcStatus='可用')";
			// Float kcCount_zt = (Float) totalDao.getObjectByCondition(
			// hqlGoods_zt, procard.getMarkId(), procard
			// .getKgliao());
			// if (kcCount_zt == null || kcCount_zt < 0) {
			// kcCount_zt = 0f;
			// }
			// ztCount += kcCount_zt;
			// if (ztCount < 0) {
			// ztCount = 0F;
			// }
			/****************** 结束 在途量=采购在途量+导入在途量 结束 ******************************/
			// (库存量+在途量(已生成采购，未到货))-占用量=剩余可用库存量
			Float daizhiCount = (kcCount + ztCount) - zyCount;
			wgj.setKcCount(kcCount);
			wgj.setZyCount(zyCount);
			wgj.setZtCount(ztCount);
			wgj.setDzCount(daizhiCount);
		}
		Integer count = totalDao.getCount(hql);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", list);
		return map;
	}

	private boolean isChartNOGzType(String markId) {
		boolean flag = false;
		if (markId == null || "".equals(markId)) {
			return flag;
		}
		// 查出编码库所有编码规则的字符长度
		List<Integer> lenList = totalDao
				.query("SELECT distinct	LENGTH(type) FROM ChartNOGzType  where LENGTH(type)>0 ");
		int markId_len = markId.length();
		if (lenList != null && lenList.size() > 0) {
			out: // 跳出外循环的标志
			for (Integer len : lenList) {
				// 判断传过来的件号的字符长度与编码规则的字符长度是否相等
				if (len == markId_len) {
					// 根据该字符长度查出所有等于改长度所有编码规则
					List<ChartNOGzType> gzTypeList = totalDao.query(
							" from ChartNOGzType where  LENGTH(type) =?", len);
					if (gzTypeList != null && gzTypeList.size() > 0) {
						// 截取该字符前面两个字符
						String beforMarkId = markId.substring(0, 2);
						// 遍历所有改长度的编码规则
						for (ChartNOGzType gzType : gzTypeList) {
							String type = gzType.getType();
							// 判断该字符最前面两个是否属于在规则类型前两个
							if (type.indexOf(beforMarkId) == 0) {
								// 字符串转char数组
								char[] markIds = markId.toCharArray();
								char[] types = type.toCharArray();
								// 分别记录.出现的下标是多少；就是.所处的位置是在哪的
								String type_index = "";
								String markId_index = "";
								for (int i = 0; i < types.length; i++) {
									if ('.' == types[i]) {
										type_index += i;
									}
									if ('.' == markIds[i]) {
										markId_index += i;
									}
								}
								// 比较两则的下标是否完全一致；
								if (markId_index.equals(type_index)) {
									// 1 字符长度一致;
									// 2 前两位一致;
									// 3 分割点.所处位置一致
									// 则说明该件号所用的规则为编码规则返回true;
									flag = true;
									break out;
								}
							}
						}
					}
				}
			}
		} else {// lenList 为null 或者size（）==0说明该公司不用编码库编码直接返回false;
			flag = false;
		}
		return flag;

	}

	@Override
	public String fenMoTzSq(FenMoTzRecord fmtr) {
		Users user = Util.getLoginUser();
		if(user == null){
			return "请先登录!~";
		}
		if(fmtr!=null){
			fmtr.setSqUserName(user.getName());
			fmtr.setSqTime(Util.getDateTime());
			totalDao.save(fmtr);
			String processName = "粉末用量调整申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						FenMoTzRecord.class, fmtr.getId(), "epStatus", "id",
						""
								,user.getDept() + "部门 "
								+ user.getName() + "粉末用量调整申请，请您审批", true);
				if (epId != null && epId > 0) {
					fmtr.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						fmtr.setEpStatus("同意");
					} else {
						fmtr.setEpStatus("未审批");
					}
					return totalDao.update(fmtr)+"";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Object[] findAllListfmtr(FenMoTzRecord fmtr, Integer pageSize,
			Integer pageNO, String status) {
			if(fmtr ==null){
				fmtr = new FenMoTzRecord();
			}
			String str = "";
			if(fmtr.getDtd()!=null){
				if(fmtr.getDtd().getId()!=null){
					str = "  dtd.id = "+fmtr.getDtd().getId();
				}
			}
			String hql =	totalDao.criteriaQueries(fmtr, str,"dtd");
			int count = totalDao.getCount(hql);
			List<FenMoTzRecord > fmtrList =	totalDao.findAllByPage(hql+" order by id desc", pageNO, pageSize);
		return new Object[]{count,fmtrList};
	}

	@Override
	public String tzylPL(Integer[] ids, List<FenMoTzRecord> fmtrList) {
		Users user = Util.getLoginUser();
		if(user == null){
			return "请先登录!~";
		}
		if(ids!=null && ids.length>0){
			DosageTzDan dtd = new DosageTzDan();
			dtd.setSqUsersName(user.getName());
			dtd.setSqTime(Util.getDateTime());
			Set<FenMoTzRecord> fmtrSet = new HashSet<FenMoTzRecord>();
			for (int i = 0; i < ids.length; i++) {
				YuanclAndWaigj ycl =(YuanclAndWaigj) totalDao.get(YuanclAndWaigj.class, ids[i]);
				FenMoTzRecord fmtr = fmtrList.get(i);
				fmtr.setMarkId(ycl.getMarkId());
				fmtr.setName(ycl.getName());
				fmtr.setAreakg0(ycl.getAreakg());
				fmtr.setSqUserName(user.getName());
				fmtr.setSqTime(Util.getDateTime());
				fmtr.setDtd(dtd);
				fmtrSet.add(fmtr);
			}
			dtd.setFmtrSet(fmtrSet);
			totalDao.save(dtd);
			String processName = "粉末用量批量调整申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						DosageTzDan.class, dtd.getId(), "epStatus", "id",
						"yuanclAndWaigjAction_findAllListfmtr.action?fmtzr.dtd.id="+dtd.getId()
								,user.getDept() + "部门 "
								+ user.getName() + "粉末用量批量调整申请，请您审批", true);
				if (epId != null && epId > 0) {
					dtd.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						dtd.setEpStatus("同意");
					} else {
						dtd.setEpStatus("未审批");
					}
					return totalDao.update(dtd)+"";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}