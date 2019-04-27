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
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
							<table style="width: 70%" class="table">
							<tr><th align="right">工位编号:</th><td>${zhShebei.gongweiId}</td></tr>
							<tr><th align="right">设备编号:</th><td>${zhShebei.shebeiId}</td></tr>
							<tr><th align="right">设备名称:</th><td>${zhShebei.shebeiname}</td></tr>
							<tr><th align="right">设备工序名称:</th><td>${zhShebei.shebeigongxuName}</td></tr>
					</table>
					<br/><br/><br/>
									<table class="table">
					<tr  bgcolor="#c0dcf2">
						<th>序号</th>
						<th>本设备操作等级</th>
						<th>操作</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>${pageIndex.index+1}</th>
							<th>${zhUser1.caozuodengji}</th>
							<th> <a href="caoZuoAction!deletezhCaozuoDengji.action?zhCaozuoDengji.id=${zhUser1.id}&zhShebei.id=${zhShebei.id}">删除</a></th>
						</tr>
					</s:iterator>
					</table>
					<br/><br/><br/><br/><br/>
					
					
					
							<form action="caoZuoAction!addzhCaozuoDengji.action" method="post" theme="simple">
							<table class="table">
							<tr><th colspan="3"><font style="color: red;">添加操作等级</font></th></tr>
							<tr><th>添加操作内容</th>
							<td >
							<input type="hidden" value="${zhShebei.id}" name="zhCaozuoDengji.shebeiId" >
					       <input type="text" style="width: 500px;height: 100px;" id="caozuodengji" name="zhCaozuoDengji.caozuodengji">
						</td><td>
						<s:submit value="添加" cssClass="input"  align="center"/>
						</td></tr></table>
				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<SCRIPT type="text/javascript">
	
	</SCRIPT>

</html>
