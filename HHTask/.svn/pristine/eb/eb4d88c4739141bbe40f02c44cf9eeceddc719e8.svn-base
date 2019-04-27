<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在付款申请进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
			</div>

			<div align="center">
					<h3>
						项目执行管理 
					</h3>
				<form action="kvpAssessAction_findEightBReport.action?tag=<s:property value="tag"/>"	 method="post">
				<table class="table">
					<tr>
						<td align="center">
							项目执行编号：
							<input type="text" name="executeKVP.executeNumber" />
						</td>
						<td align="center">
							执行时间：
							<input type="text" class="Wdate"
								name="executeKVP.executeDate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td  colspan="2" align="center">
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询" class="input" />
					</tr>
				</table>

				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							项目执行编号
						</td>
						<td align="center">
							成本结余
						</td>
							<td align="center">
							审批状态
						</td>
							<td align="center">
							执行时间
						</td>
						<td align="center">
							项目名称
						</td>
						<td align="center">
							8B状态
						</td>
						<td align="center">
							8B填报人
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="maps" id="pageList" status="pageStatus">
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
							${pageList.executeNumber}
						</td>
						<td>
							${pageList.costsavings}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
							${pageList.executeDate}
						</td>
						<td>
							${pageList.eightBReport.projectname}
						</td>
						<td>
							${pageList.eightBReport.status}
						</td>
						<td>
							${pageList.eightBReport.name}
						</td>
						<td>
						<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态</a>
						<a href="CircuitRunAction_findAduitPage.action?id=${pageList.eightBReport.epId}">8B审批动态</a>
						<s:if test="#pageList.eightBReport.status!=null">
							<s:if test="#pageList.eightBReport.status=='未审批'||#pageList.eightBReport.status=='打回'">
								<a href="kvpAssessAction_findEightBReportById.action?id=${pageList.id}&tag=<s:property value="tag"/>">修改8B报表</a>
								<a href="kvpAssessAction_delEightBReport.action?id=${pageList.id}&tag=<s:property value="tag"/>">删除</a>
								<a href="kvpAssessAction_findEightBReportById.action?id=${pageList.id}&tag1=1">查看8B报表</a>
							</s:if>
							<s:else>
								<a href="kvpAssessAction_findEightBReportById.action?id=${pageList.id}&tag1=1">查看8B报表</a>
							</s:else>
						</s:if>
						<s:else>
						<s:if test="#pageList.status=='同意'">
						<a href="<%=path %>/System/tvp/addEightBReport.jsp?id=${pageList.id}&tag=<s:property value="tag"/>">添加8B报表</a>
						</s:if>
						<a href="kvpAssessAction_delEightBReport.action?id=${pageList.id}">删除</a>
						</s:else>
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
				</table>

				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript" src="javascript/DatePicker/WdatePicker.js" >
	 
</script>
	</body>
</html>
