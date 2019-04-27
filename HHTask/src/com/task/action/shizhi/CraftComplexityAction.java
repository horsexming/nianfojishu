package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.CraftComplexityServer;
import com.task.entity.shizhi.CraftComplexity;
import com.task.entity.vo.shizhivo.CraftComplexityVo;
import com.task.entity.vo.shizhivo.SkillTypeVo;

public class CraftComplexityAction {
	private CraftComplexityServer craftComplexityServer;
	private CraftComplexity craftComplexity;
	
	private List<CraftComplexityVo> ccVoList;//工艺复杂系数Vo列表
	private List<SkillTypeVo> hadSTVoList;//已绑定的技能分类Vo列表
	private List<SkillTypeVo> unHadSTVoList;//未绑定的技能分类Vo列表
	
	private List<String> hadProList;// 已绑定的工序列表
	private List<String> unHadProList;// 未绑定的工序列表


	
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int count;//绑定加工难点系数数量
	private int[] checkboxs;//将要绑定的加工难点系数的id;
	private String[] checkboxs2;//将要绑定的工序的名称
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 3;
	
	
	/**
	 * 分页显示工艺复杂系数
	 * @return
	 */
	public String showList(){
		if (craftComplexity != null) {
			ActionContext.getContext().getSession().put("craftComplexity",
					craftComplexity);
		    } else {//用来保持分页时带着查询条件
		    	craftComplexity = (CraftComplexity) ActionContext.getContext().getSession().get("craftComplexity");
		      }
		Map<Integer, Object> map = craftComplexityServer.findSkillTypesByCondition(
				craftComplexity, Integer.parseInt(cpage), pageSize);
		ccVoList = (List<CraftComplexityVo>) map.get(1);// 显示页的技能系数列表
			if (ccVoList != null & ccVoList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("craftComplexityAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "cc_show";
 }
	
	/**
	 * 添加工艺复杂系数
	 * @return
	 */
	 public String add(){
		 boolean b=craftComplexityServer.add(craftComplexity);
		 if(b){
			 successMessage="添加成功！";
			return showList();
		 }else{ 
			 errorMessage="添加失败！";
		  return "cc_add";
		 }
	 }
	 /**
	  * 跳往修改工艺复杂系数页面
	  * @return
	  */
	 public String toupdate(){
		 CraftComplexity craftComplexity2=craftComplexityServer.getById(craftComplexity.getId());
		if(craftComplexity2!=null){
			craftComplexity=craftComplexity2;
			 return "cc_update";			
		}else{
			errorMessage="修改失败,不存在该工艺复杂系数！";
		}
		return showList();
	 }
	 
	 /**
	  * 修改工艺复杂系数
	  * @return
	  */
	 public String update(){
		 boolean b=craftComplexityServer.update(craftComplexity);
		 if(b){
			 successMessage="修改成功！";
			 craftComplexity=null;//控制查询结果为之前位置
			 return showList();
		 }else{
			 errorMessage="修改失败！";
			 return "cc_update";
		 }
	 }
	 /**
	  * 删除工艺复杂系数
	  * @return
	  */
	 public String delete(){
		 boolean b=craftComplexityServer.deleteById(craftComplexity.getId());
		 if(b){
			 successMessage="删除成功！";
		 }else{
			 errorMessage="删除失败！";
		 }
		 craftComplexity=null;
		 return showList();
	 }
	 /**
	  * 展示技能分类信息
	  * @return
	  */
	 public String skillTypeView(){
		    this.pageSize=15;
			Map<Integer,Object>map=	craftComplexityServer.getSkillTypeVosMap(craftComplexity.getId(),Integer.parseInt(cpage), pageSize);
			if(map!=null){
				hadSTVoList=(List<SkillTypeVo>) map.get(1);
				unHadSTVoList=(List<SkillTypeVo>) map.get(2);
				this.craftComplexity=(CraftComplexity)map.get(3);
				this.count=hadSTVoList.size();
				this.total=map.get(4).toString();
				this.url="craftComplexityAction_skillScoreView.action";
				return "cc_typeview";
			}
			errorMessage="查看失败,不存在该技能分类！";
			return this.showList();
	 }
	 /**
	  * 绑定技能分类
	  * @return
	  */
	 public String linkSkillType(){
		boolean b= craftComplexityServer.linkSkillType(craftComplexity.getId(),checkboxs);
		if(b){
			successMessage="绑定成功！";
		}else{
			errorMessage="绑定失败！";
		}
		return this.skillTypeView();
	 }

	 
	//GET和SET方法
	public CraftComplexityServer getCraftComplexityServer() {
		return craftComplexityServer;
	}
	public void setCraftComplexityServer(CraftComplexityServer craftComplexityServer) {
		this.craftComplexityServer = craftComplexityServer;
	}
	public CraftComplexity getCraftComplexity() {
		return craftComplexity;
	}
	public void setCraftComplexity(CraftComplexity craftComplexity) {
		this.craftComplexity = craftComplexity;
	}
	public List<CraftComplexityVo> getCcVoList() {
		return ccVoList;
	}
	public void setCcVoList(List<CraftComplexityVo> ccVoList) {
		this.ccVoList = ccVoList;
	}
	public List<SkillTypeVo> getHadSTVoList() {
		return hadSTVoList;
	}
	public void setHadSTVoList(List<SkillTypeVo> hadSTVoList) {
		this.hadSTVoList = hadSTVoList;
	}
	public List<SkillTypeVo> getUnHadSTVoList() {
		return unHadSTVoList;
	}
	public void setUnHadSTVoList(List<SkillTypeVo> unHadSTVoList) {
		this.unHadSTVoList = unHadSTVoList;
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
