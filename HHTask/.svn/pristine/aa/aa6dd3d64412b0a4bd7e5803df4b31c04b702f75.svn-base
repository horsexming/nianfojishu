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
		<div id="gongneng">
			<div align="center">
				<h3> 
					气密记录
					<br />
				</h3>
				<form action="airtightLogAction_showList.action" method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								<input type="hidden" name="pageStatus"
									value="<s:property value='pageStatus'/>" />
								件号
								<input type="text" name="airtightLog.markId"
									value="<s:property value="airtightLog.markId"/>" />
							</td>
							<td align="center">
								产品序号
								<input type="text" name="airtightLog.number"
									value="<s:property value="airtightLog.number"/>" />
							</td>
							</tr>
							<tr>
							<td align="center">
								条形码
								<input type="text" name="airtightLog.context"
									value="<s:property value="airtightLog.context"/>" />
							</td>
							<td align="center">
								其他标识
								<input type="text" name="airtightLog.otherContext"
									value="<s:property value="airtightLog.otherContext"/>" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
								<input type="reset" style="width: 100px; height: 40px;"
									value="重置" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(NO)
						</td>
						<td align="center">
							件号
							<br />
							(markId)
						</td>
						<td align="center">
							产品序号
							<br />
							(number)
						</td>
						<td align="center">
							泄漏量
							<br />
							（leakage）
						</td>
						<td align="center">
							压力值
							<br />
							（pressure value）
						</td>
						<td align="center">
							条码内容
							<br />
							（context）
						</td>
						<td align="center">
							添加时间
							<br />
							（addtime）
						</td>
						<td align="center">
							其他标识
							<br />
							（other tag）
						</td>
						<td align="center">
							操作者
							<br />
							(operator)
						</td>
						<td align="center" colspan="2">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<s:iterator value="airtightLogList" id="airtightLogPage"
						status="pageStatus">
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
							${airtightLogPage.markId}
						</td>
						<td>
							${airtightLogPage.number}
						</td>
						<td>
							${airtightLogPage.xielou}
						</td>
						<td>
							${airtightLogPage.yali}
						</td>
						<td>
							${airtightLogPage.context}
						</td>
						<td>
							${airtightLogPage.addtime}
						</td>
						<td>
							${airtightLogPage.otherContext}
						</td>
						<td>
							${airtightLogPage.operator}
						</td>
						<td colspan="2">
							<input type="button" value="补打(print)"
								style="width: 60px; height: 30px;"
								onclick="window.location.href='<%=basePath%>System/airtightlog/airtightlog_print.jsp?context=${airtightLogPage.context}'" />
<%--							<input type="button" value="删除(delete)"--%>
<%--								style="width: 60px; height: 30px;"--%>
<%--								onclick="todelete(${airtightLogPage.id })" />--%>
						
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

function todelete(id) {
	if(confirm("确定删除？")){
		window.location.href = "airtightLogAction_deleteLog.action?airtightLog.id="
			+ id;
		return true;
	}
}
</script>
	</body>
</html>

