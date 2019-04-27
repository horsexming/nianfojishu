package com.task.action.zgkh;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.zgkh.ScoreStatisticsServer;
import com.task.entity.zgkh.ScoreStatistics;

/**
 * 主管考核总分Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class ScoreStatisticsAction extends ActionSupport {

	private ScoreStatisticsServer ssServer;// Server层
	private ScoreStatistics scoreStatistics;// 对象
	private List<ScoreStatistics> scoreStatisticsList;// 集合
	private List list;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 查询所有总分信息
	@SuppressWarnings("unchecked")
	public String findAllSS() {
		if (scoreStatistics != null) {
			ActionContext.getContext().getSession().put("scoreStatistics",
					scoreStatistics);
		} else {
			scoreStatistics = (ScoreStatistics) ActionContext.getContext()
					.getSession().get("scoreStatistics");
			//scoreStatistics.setScoreStatus("完成");
		}
		Object[] object = ssServer.findAllSS(scoreStatistics, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			scoreStatisticsList = (List<ScoreStatistics>) object[0];
			if (scoreStatisticsList != null && scoreStatisticsList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("ScoreStatisticsAction!findAllSS.action?scoreStatistics.code=");
				errorMessage = null;
				if (id > 0) {
					list = ssServer.findScoreBySSId(id);
				}
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findAllSS";

	}

	// 通过总分id查询所有成绩
	public String findScoreBySSId() {
		list = ssServer.findScoreBySSId(id);
		return "findAllSS";
	}

	public ScoreStatisticsServer getSsServer() {
		return ssServer;
	}

	public void setSsServer(ScoreStatisticsServer ssServer) {
		this.ssServer = ssServer;
	}

	public ScoreStatistics getScoreStatistics() {
		return scoreStatistics;
	}

	public void setScoreStatistics(ScoreStatistics scoreStatistics) {
		this.scoreStatistics = scoreStatistics;
	}

	public List<ScoreStatistics> getScoreStatisticsList() {
		return scoreStatisticsList;
	}

	public void setScoreStatisticsList(List<ScoreStatistics> scoreStatisticsList) {
		this.scoreStatisticsList = scoreStatisticsList;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
