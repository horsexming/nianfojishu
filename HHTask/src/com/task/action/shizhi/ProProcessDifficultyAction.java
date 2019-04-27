package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.ProProcessDifficultyServer;
import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.vo.shizhivo.ProProcessDifficultyVo;
import com.task.entity.vo.shizhivo.SkillTypeVo;

/**
 * 加工难点系数Action层
 * 
 * @author 唐晓斌
 * 
 */
public class ProProcessDifficultyAction {
	private ProProcessDifficultyServer proProcessDifficultyServer;// 加工难点服务
	private ProProcessDifficulty proProcessDifficulty;// 加工难点对象
	private List<ProProcessDifficultyVo> ppdVoList;// 加工难点Vo列表


	private List<SkillTypeVo> hadSTVoList;// 已绑定的技能分类Vo列表
	private List<SkillTypeVo> unHadSTVoList;// 未绑定的技能分类Vo列表

	private List<String> hadProList;// 已绑定的工序列表
	private List<String> unHadProList;// 未绑定的工序列表

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int count;// 绑定加工难点系数数量
	private int[] checkboxs;// 将要绑定的加工难点系数的id;
	private String[] checkboxs2;//将要绑定的工序的名称
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 8;

	/**
	 * 分页显示加工难点
	 * 
	 * @return
	 */
	public String showList() {
		if (proProcessDifficulty != null) {
			ActionContext.getContext().getSession().put("proProcessDifficulty",
					proProcessDifficulty);
		} else {// 用来保持分页时带着查询条件
			proProcessDifficulty = (ProProcessDifficulty) ActionContext
					.getContext().getSession().get("proProcessDifficulty");
		}
		Map<Integer, Object> map = proProcessDifficultyServer
				.findSkillTypesByCondition(proProcessDifficulty, Integer
						.parseInt(cpage), pageSize);
		ppdVoList = (List<ProProcessDifficultyVo>) map.get(1);// 显示页的技能系数列表
		if (ppdVoList != null & ppdVoList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("proProcessDifficultyAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "ppd_show";
	}

	/**
	 * 添加加工难点系数
	 * 
	 * @return
	 */
	public String add() {
		boolean b = proProcessDifficultyServer.add(proProcessDifficulty);
		if (b) {
			successMessage = "添加成功！";
			return showList();
		} else {
			errorMessage = "添加失败！";
			return "ppd_add";
		}
	}

	/**
	 * 跳往修改加工难点系数页面
	 * 
	 * @return
	 */
	public String toupdate() {
		ProProcessDifficulty proProcessDifficulty2 = proProcessDifficultyServer
				.getById(proProcessDifficulty.getId());
		if (proProcessDifficulty2 != null) {
			proProcessDifficulty = proProcessDifficulty2;
			return "ppd_update";
		} else {
			errorMessage = "修改失败,不存在该加工难点系数！";
		}
		return showList();
	}

	/**
	 * 修改加工难点系数
	 * 
	 * @return
	 */
	public String update() {
		boolean b = proProcessDifficultyServer.update(proProcessDifficulty);
		if (b) {
			successMessage = "修改成功！";
			proProcessDifficulty = null;// 控制查询结果为之前位置
			return showList();
		} else {
			errorMessage = "修改失败！";
			return "ppd_update";
		}
	}

	/**
	 * 删除加工难点系数
	 * 
	 * @return
	 */
	public String delete() {
		boolean b = proProcessDifficultyServer.deleteById(proProcessDifficulty
				.getId());
		if (b) {
			successMessage = "删除成功！";
		} else {
			errorMessage = "删除失败！";
		}
		proProcessDifficulty = null;
		return showList();
	}

	/**
	 * 展示技能分类信息
	 * 
	 * @return
	 */
	public String skillTypeView() {
		if (proProcessDifficulty != null) {
			ActionContext.getContext().getSession().put("proProcessDifficulty",
					proProcessDifficulty);
		} else {// 用来保持分页时带着查询条件
			proProcessDifficulty = (ProProcessDifficulty) ActionContext
					.getContext().getSession().get("proProcessDifficulty");
		}
		pageSize = 15;
		Map<Integer, Object> map = proProcessDifficultyServer
				.getSkillTypeVosMap(proProcessDifficulty.getId(), Integer
						.parseInt(cpage), pageSize);
		if (map != null) {
			hadSTVoList = (List<SkillTypeVo>) map.get(1);
			unHadSTVoList = (List<SkillTypeVo>) map.get(2);
			this.proProcessDifficulty = (ProProcessDifficulty) map.get(3);
			this.count = hadSTVoList.size();
			this.total = map.get(4).toString();
			this.url = "proProcessDifficultyAction_skillTypeView.action";
			return "ppd_typeview";
		}
		errorMessage = "查看失败,不存在该技能分类！";
		return this.showList();
	}

	/**
	 * 绑定技能分类
	 * 
	 * @return
	 */
	public String linkSkillType() {
		boolean b = proProcessDifficultyServer.linkSkillType(
				proProcessDifficulty.getId(), checkboxs);
		if (b) {
			successMessage = "绑定成功！";
		} else {
			errorMessage = "绑定失败！";
		}
		return this.skillTypeView();
	}


	// GET和SET方法
	public ProProcessDifficulty getProProcessDifficulty() {
		return proProcessDifficulty;
	}

	public void setProProcessDifficulty(
			ProProcessDifficulty proProcessDifficulty) {
		this.proProcessDifficulty = proProcessDifficulty;
	}

	public List<ProProcessDifficultyVo> getPpdVoList() {
		return ppdVoList;
	}

	public void setPpdVoList(List<ProProcessDifficultyVo> ppdVoList) {
		this.ppdVoList = ppdVoList;
	}

	public List<SkillTypeVo> getHadSTVoList() {
		return hadSTVoList;
	}

	public void setHadSTVoList(List<SkillTypeVo> hadSTVoList) {
		this.hadSTVoList = hadSTVoList;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[] getCheckboxs() {
		return checkboxs;
	}

	public void setCheckboxs(int[] checkboxs) {
		this.checkboxs = checkboxs;
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

	public ProProcessDifficultyServer getProProcessDifficultyServer() {
		return proProcessDifficultyServer;
	}

	public void setProProcessDifficultyServer(
			ProProcessDifficultyServer proProcessDifficultyServer) {
		this.proProcessDifficultyServer = proProcessDifficultyServer;
	}

	public List<SkillTypeVo> getUnHadSTVoList() {
		return unHadSTVoList;
	}

	public void setUnHadSTVoList(List<SkillTypeVo> unHadSTVoList) {
		this.unHadSTVoList = unHadSTVoList;
	}




	public List<String> getHadProList() {
		return hadProList;
	}

	public void setHadProList(List<String> hadProList) {
		this.hadProList = hadProList;
	}

	public List<String> getUnHadProList() {
		return unHadProList;
	}

	public void setUnHadProList(List<String> unHadProList) {
		this.unHadProList = unHadProList;
	}

	public String[] getCheckboxs2() {
		return checkboxs2;
	}

	public void setCheckboxs2(String[] checkboxs2) {
		this.checkboxs2 = checkboxs2;
	}

	

}
