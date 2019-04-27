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
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					特殊日期查询
				</h3>
				<form action="SpecialDateAction_showList.action"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 10%">
								日期
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="specialDate.date" />
							</td>
							<th align="center" style="width: 10%">
								班次
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="specialDate.banciName" />
							</td>
							<td align="center" style="width: 30%">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							班次名称
						</td>
						<td align="center">
							特殊日期
						</td>
						<td align="center">
							上班类型
						</td>
						<td align="center">
							添加人
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="specialDateList" id="specialDates"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${specialDates.banciName}
						</td>
						<td align="center">
							${specialDates.date}
						</td>
						<td align="center">
							${specialDates.specialType}
						</td>
						<td align="center">
							${specialDates.addPName}
						</td>
						<td align="center">
							${specialDates.addTime}
						</td>
						<td align="center">
							<a onclick="return window.confirm('您将删除数据，是否继续?')" 
								href="SpecialDateAction_delete.action?id=${specialDates.id}">删除</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="7" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="7" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>
