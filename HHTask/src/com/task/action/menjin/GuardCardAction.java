package com.task.action.menjin;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.menjin.GuardCardServer;
import com.task.entity.menjin.GuardCard;
/**
 * 门卫卡
 * @author Li_Cong
 *
 */
public class GuardCardAction extends ActionSupport{
  private GuardCardServer guardCardServer;
  private GuardCard guardCard;
  private List<GuardCard> guardCardList;
  private String errorMessage;
  private String successMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15; 
//添加门卫卡
	public String addGuardCard(){
		boolean bool = this.guardCardServer.addGuardCard(guardCard);
		if (bool) {
			errorMessage = "添加成功!";
		} else {
			errorMessage = "数据异常，请重新添加!";
		}
		return "GuardCard_add";
	}
	
//跳转到添加页面
	public String toadd(){
		return "GuardCard_add";
	}
//查询
	public String findGuardCard(){
		if(guardCard!=null){
			ActionContext.getContext().getSession().put("GuardCard", guardCard);
		}else{
			guardCard=(GuardCard) ActionContext.getContext().getSession().get("GuardCard");
		}
		Map<Integer, Object> map=guardCardServer.findGuardCardByCondition(guardCard,Integer.parseInt(cpage),pageSize);
		guardCardList=(List<GuardCard>) map.get(1);
		if(guardCardList!=null&&guardCardList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount=(count + pageSize-1)/pageSize;
			this.setTotal(pageCount+"");
			this.setUrl("GuardCardAction_findGuardCard.action");
			errorMessage=null;
		}else{
			errorMessage="没有符合条件的信息，请检查后重试!";
		}
		return "GuardCard_find";
	}
	// 根据id获取
	public String getByIdGuardCard() {
		if(guardCard!=null){
		guardCard = this.guardCardServer.getByIdGuardCard(guardCard);
		}
		return "getByIdGuardCard";
		
	}

	// 修改
	public String updateGuardCard() {
		boolean bool = this.guardCardServer.updateGuardCard(guardCard);
		if (bool) {
			this.errorMessage = "修改成功!";
		} else {
			this.errorMessage = "修改失败!";
		}
		return "GuardCard_update";
	}
	//删除
	public String deleteGuardCard(){
		boolean b = this.guardCardServer.deleteGuardCard(guardCard);
		if (b) {
			return "GuardCard_delete";
		} else {
			return ERROR;
		}
		
	}
	public GuardCardServer getGuardCardServer() {
		return guardCardServer;
	}
	public void setGuardCardServer(GuardCardServer guardCardServer) {
		this.guardCardServer = guardCardServer;
	}
	public GuardCard getGuardCard() {
		return guardCard;
	}
	public void setGuardCard(GuardCard guardCard) {
		this.guardCard = guardCard;
	}
	public List<GuardCard> getGuardCardList() {
		return guardCardList;
	}
	public void setGuardCardList(List<GuardCard> guardCardList) {
		this.guardCardList = guardCardList;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
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
	
}
