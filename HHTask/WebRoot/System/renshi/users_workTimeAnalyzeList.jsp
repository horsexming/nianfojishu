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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h1>员工工作时长分析</h1>
				<form action="${url }" method="post" autocomplete="off">
					<table class="table">
						<tr>
							<td align="right">部门</td>
							<td>
								<SELECT id="dept" name="systemFile.department">
									<option  value="${systemFile.department }">${systemFile.department }</option>
								</SELECT>
								
							</td>
							<td align="right">工号</td>
							<td>
								<input type="text" name="users.code" value="${uses.code}" >
							</td>
							<td align="right">姓名</td>
							<td>
								<input type="text" name="users.name" value="${uses.name}" >
							</td>
						</tr>
						<tr>
							<td align="right">开始时间</td>
							<td>
								<input name="startTime" class="Wdate" id="startTime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td align="right">结束时间</td>
							<td>
								<input name="endTime" class="Wdate" id="endTime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" class="input" value="查询">
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr>
						<th>序号</th>
						<th>部门</th>
						<th>卡号</th>
						<th>工号</th>
						<th>姓名</th>
						<th>总工作时长</th>
						
						<th>操作</th>
					</tr>
					<s:iterator value="showList" id="sl" status="ps">
						<s:if test="#ps.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
							<td>${ps.index+1 }</td>
							<td>${sl[0] }</td>
							<td>${sl[1] }</td>
							<td>${sl[2] }</td>
							<td>${sl[3] }</td>
							<td>${sl[4] }</td>
							<td>
								<input type="button" value="查看进出明细" >
							</td>
						</tr>
					</s:iterator>
					<tr id="fenyeTr" align="right">
						<td class="text-right" colspan="20"> 
							第
							<font color="red" id="cpage">${cpage}</font> / <font id="total">${total}</font> 页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" /> 
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
