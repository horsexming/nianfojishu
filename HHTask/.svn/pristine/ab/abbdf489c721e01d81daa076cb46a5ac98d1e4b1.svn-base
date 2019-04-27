package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.TemplateDetailsServer;
import com.task.entity.Template;
import com.task.entity.TemplateDetails;

public class TemplateDetailsServerImpl implements TemplateDetailsServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加模板明细
	public boolean addTemplateDetails(TemplateDetails templateDetails,
			Template template) {
		boolean bool = false;
		if (templateDetails != null && template != null) {
			if (templateDetails.getOnLayer() != null
					&& "".equals(templateDetails.getOnLayer())) {
				templateDetails.setOnLayer("root");
			}
			templateDetails.setTemplate(template);// 所属模板

			bool = totalDao.save(templateDetails);// 添加模板明细
		}
		return bool;
	}

	// 查询明细中是否已经存在明细内容
	public boolean findOldTemplateDetaild(TemplateDetails templateDetails,
			Template template) {
		boolean bool = false;
		if (templateDetails != null && template != null) {
			String hql = "from TemplateDetails ts where ts.template.id=? and project=? and customScore=? and layer=? and onLayer=?";
			List list = totalDao.query(hql, template.getId(), templateDetails
					.getProject(), templateDetails.getCustomScore(),
					templateDetails.getLayer(), templateDetails.getOnLayer());
			if (list == null || list.size() <= 0) {
				return true;
			}
		}
		return false;
	}

	// 通过id查找模板明细
	public TemplateDetails findTemDetailsById(int id) {
		if ((Object) id != null && id != 0) {
			return (TemplateDetails) totalDao.getObjectById(
					TemplateDetails.class, id);
		}
		return null;
	}

	// 通过模板id和上层查询总分
	public Float findSumScore(int templateId, String onLayer) {
		if ((Object) templateId != null && templateId > 0 && !onLayer.isEmpty()) {
			String sql = "select sum(td.customScore) from TemplateDetails td where td.template.id="
					+ templateId + " and td.onLayer='" + onLayer + "'";
			List sumList = totalDao.createQuerySelect(sql, null);
			if (sumList != null && sumList.size() > 0 && sumList.get(0) != null) {
				String sumScoreString = sumList.get(0).toString();
				Float sumScore = Float.valueOf(sumScoreString);
				if (sumScore == null) {
					sumScore = 0F;
				}
				return sumScore;
			}
		}
		return 0F;
	}

	// 通过上层操作明细
	public List findTemplateDetailsByOnLayer(String onLayer) {
		if (!onLayer.isEmpty() && onLayer.length() > 0) {
			String hql = "feom TemplateDetails where layer=?";
			return totalDao.query(hql, onLayer);
		}
		return null;
	}

	// 级联删除模板明细
	public Template delTemplateDetails(TemplateDetails templateDetails) {
		if (templateDetails != null) {
			Set<TemplateDetails> allSet = templateDetails.getTemplate()
					.getTemplateDetails();
			List allTemsList = new ArrayList();
			for (TemplateDetails tds : allSet) {
				allTemsList.add(tds);
			}
			allTemsList.remove(templateDetails);// 删除当前明细

			if (delTdsById(templateDetails, allTemsList)) {
				allSet = new HashSet<TemplateDetails>();
				for (int i = 0, len = allTemsList.size(); i < len; i++) {
					TemplateDetails tms = (TemplateDetails) allTemsList.get(i);
					allSet.add(tms);
				}
				templateDetails.getTemplate().setTemplateDetails(allSet);
				return templateDetails.getTemplate();// 删除
			}
		}
		return null;
	}

	// 将集合中所有相关的明细都删除
	private boolean delTdsById(TemplateDetails templateDetails, List allTemsList) {
		try {
			String onLayer = String.valueOf(templateDetails.getId());
			// Map map = new HashMap();
			for (int i = 0; i < allTemsList.size(); i++) {
				TemplateDetails tems = (TemplateDetails) allTemsList.get(i);
				String listOnLayer = tems.getOnLayer();// 明细上层
				if (!onLayer.isEmpty() && onLayer.equals(listOnLayer)) {
					allTemsList.remove(tems);
					delTdsById(tems, allTemsList);// 回调
					i--;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 修改模板明细
	public boolean updateTems(TemplateDetails tems) {
		if (tems != null) {
			return totalDao.update(tems);
		}
		return false;
	}

	// 修改模板明细(更改上层分数)
	public boolean chageTdsScore(TemplateDetails tds) {
		boolean bool = true;
		int layer = tds.getLayer();// 当前层
		for (int i = layer; i > 1; i--) {
			String onLayer = tds.getOnLayer();
			tds = findTemDetailsById(Integer.parseInt(tds.getOnLayer()));// 查询出上一层的项目
			// 限制只能最后一层能填写分数
			// if (i == layer && !"yes".equals(tds.getIsSroce())) {
			// break;
			// }
			Float sumScore = 0F;
			// 判断是否为打分项
			if (tds != null && "yes".equals(tds.getIsSroce())) {
				sumScore = findMaxScore(tds.getTemplate().getId(), onLayer);// 查询出本层最大分数
			} else {
				sumScore = findSumScore(tds.getTemplate().getId(), onLayer);// 查询出本层总分
			}

			tds.setCustomScore(sumScore);// 更改上一层的分数
			bool = totalDao.update(tds);// 更新上一层
		}
		return bool;
	}

	// 通过模板id和上层查询最大分
	public Float findMaxScore(int templateId, String onLayer) {
		if ((Object) templateId != null && templateId > 0 && !onLayer.isEmpty()) {
			String sql = "select max(td.customScore) from TemplateDetails td where td.template.id="
					+ templateId + " and td.onLayer='" + onLayer + "'";
			List sumList = totalDao.createQuerySelect(sql, null);
			if (sumList != null && sumList.size() > 0 && sumList.get(0) != null) {
				String sumScoreString = sumList.get(0).toString();
				Float sumScore = Float.valueOf(sumScoreString);
				if (sumScore == null) {
					sumScore = 0F;
				}
				return sumScore;
			}
		}
		return 0F;
	}

}
