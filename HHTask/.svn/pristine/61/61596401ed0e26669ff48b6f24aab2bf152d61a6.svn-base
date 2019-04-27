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
		<div id="gongneng" >
			<div align="center">
				<h3>
					技能分类管理<br/>(Skill Coefficient Management)
				</h3>
				<form action="skillTypeAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								技能类别（skill type）：
								<input type="text" name="skillType.name" value="<s:property value="skillType.name"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加技能分类(add)" onclick="add()"/>
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
							技能分类<br/>(skill lv)
						</td>
						<td align="center">
							技能最小分值<br/>（skill min score）
						</td>
						<td align="center">
							技能最大分值<br/>（skill max score）
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="sTypeList" id="sType" status="pageStatus">
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
							${sType.name }
						</td>
						<td>
							${sType.minscore}
						</td>
						<td>
							${sType.maxscore}
						</td>
						<td  colspan="2">
						 <input type="button" value="查看技能系数(view)"
								style="width: 60px; height: 30px;"
								onclick="view(${sType.id})" />
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${sType.id})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${sType.id })" />
						</td>

					</s:iterator>
					</tr>
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
function view(id) {
	window.location.href = "skillTypeAction_skillScoreView.action?skillType.id=" + id;
}
function update(id) {
	window.location.href = "skillTypeAction_toupdate.action?skillType.id=" + id;
}
function todelete(id) {
	window.location.href = "skillTypeAction_delete.action?skillType.id=" + id;
}
function add() {
	window.location.href = "<%=path%>/System/shizhi/skilltype_add.jsp";
}
</script>
	</body>
</html>
