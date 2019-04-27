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
							<span id="title">您正在对材质管理进行操作</span>
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
	<div id="gongneng" align="center">
		</div>
		
		<div align="center">
			<h3>材质管理</h3>
				<form action="UnitManagerAction_findCaizhiManager.action" method="post">
				<table class="table" >
					<tr>
					<th>材质名称</th>
						<td align="left"> 
						<input type="text" name="manager.unitname" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
						<input type="submit" style="width: 100px; height: 40px;" value="查询"  class="input" />
						<input type="button" style="width: 100px; height: 40px;" value="添加"  class="input"  onclick="add()"/>
						 </td>
					</tr>
				</table>
			</form>
				
			<table  class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">序号</td>
					<td align="center">单位名称</td>
					<td align="center">添加时间</td>
					<td align="center">操作</td>
					<td></td>
				</tr>
				<s:iterator value="maps" id="cusList" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td><s:if test="#pageStatus.index%2==1">
							<font>
						</s:if> <s:else>
							<font color="#c0dcf2">
						</s:else> <s:property value="#pageStatus.index+1" /> </font></td>
					<td>${cusList.unitname}</td>
					<td>${cusList.unitdate}</td>
					<td>
					<a onclick="update(${cusList.id})">修改</a>/
					<a href="UnitManagerAction_delUnitManager.action?manager.id=${cusList.id}&&pageStatus=material" 	
					onClick="return window.confirm('确定要删除该单位?')">删除</a>
					</td>
				</s:iterator>
				</tr>
				<tr>
					<s:if test="errorMessage==null">
						<td colspan="12" align="right">第 <font color="red"><s:property
									value="cpage" /> </font> / <s:property value="total" /> 页 <fenye:pages
								cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page"
								theme="number" />
					</s:if>
					<s:else>
						<td colspan="11" align="center" style="color: red">
							${errorMessage}
					</s:else>
					</td>
				</tr>
			</table>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
	function add() {
		var url="UnitManagerAction_toadd.action?pageStatus=material";
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	function update(obj){
		var url = "${pageContext.request.contextPath}/UnitManagerAction_findUnitManagerById.action?unit_id="+obj;
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	 
</script>
</body>
</html>
