<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong" >
			</div>
			
			<div align="center">
				<h3>
					技能系数管理<br/>(Skill Coefficient Management)
				</h3>
				<form action="skillScoreAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								技能等级（skill lv）：
								<input type="text" name="skillScore.name" value="<s:property value="skillScore.name"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加技能系数(add)" onclick="add()"/>
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(num)
						</td>
						<td align="center">
							技能等级<br/>(skill lv)
						</td>
						<td align="center">
							技能分值<br/>（skill score）
						</td>
						<td align="center">
							总体技能分值或系数<br/>（total skill score）
						</td>
						<td align="center">
							强度或复杂系数<br/>（difficult score）
						</td>
						<td align="center">
							综合系数<br/>（total score）
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="sScoreList" id="sScore" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${sScore.name }
						</td>
						<td>
							${sScore.score}
						</td>
						<td>
							${sScore.totalscore}
						</td>
						<td>
							${sScore.difficultScore}
						</td>
						<td>
							${sScore.total}
						</td>
						
						<td  colspan="2">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${sScore.id})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="if(window.confirm('您将删除数据是否继续？')){todelete(${sScore.id },${cpage})};" />
						</td>
					</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function update(id) {
	window.location.href = "skillScoreAction_toupdate.action?skillScore.id=" + id;
}
function todelete(id,cpage) {
	window.location.href = "skillScoreAction_delete.action?skillScore.id=" + id+"&cpage="+cpage;
}
function add() {
	window.location.href = "<%=path%>/System/shizhi/skillscore_add.jsp";
}
</script>
	</body>
</html>
