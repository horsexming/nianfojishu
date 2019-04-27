<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<body >
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; hight:  900px;display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
				<table style="width: 100%">
					<tr>
						<td>
							
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				</center>
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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
	            <table class="table" style="width: 50%;">
					<tr><th colspan="2">招标信息</th></tr>
					<tr><th align="right">招标题目:</th>
						<td>${zhaobiao.title}</td>
					</tr>
					<tr>
						<th align="right">招标负责人:</th>
						<td>${zhaobiao.fuze}</td>
					</tr>
					<tr>
						<th align="right">负责人电话:</th>
						<td>${zhaobiao.phone}</td>
					</tr>
					<tr>
						<th align="right">开始时间:</th>
						<td>${zhaobiao.moban}</td>
					</tr>
					<tr>
						<th align="right">结束时间:</th>
						<td>${zhaobiao.kongxian}</td>
					</tr>
					<tr>
						<th align="right">招商简介:</th>
						<td colspan="2" height="100px">${zhaobiao.loc}
						<input type="hidden" id="id" value="${zhaobiao.id}" name="zhaobiao.id" >
						
						</td>
					</tr>
				</table>
				<br />
					<table class="table" style="width: 100%">
					<tr align="right"><td colspan="5"></td><th ><a onclick="addzhaobiaoXi()">添加</a></th></tr>
						<tr  bgcolor="#c0dcf2">
							<th>序号</th>
							<th>物料名称</th>
							<th>使用模版</th>
							<th>数量/单位</th>
							<th>规格要求</th>
							
							<th>操作</th>
						</tr>
				<s:iterator value="list" id="zhaobiaoXi"  status="pageIndex">
						<tr  align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb');">
							<td>${pageIndex.index+1}</td>
							<td>${zhaobiaoXi.t6}</td>
							<td>${zhaobiaoXi.zhmoban.name}</td>
							<td>${zhaobiaoXi.t2}/${zhaobiaoXi.t3}</td>
							<td>${zhaobiaoXi.t5}</td>
							
							
							<td>
							<a href="zhaobiaoAction!deletezhaobiaoXi.action?zhaobiaoXi.id=${zhaobiaoXi.id}">删除</a>
							<a onclick="updatezhaobiaoXi(${zhaobiaoXi.id})">修改</a>
						</td>
						</tr>
						</s:iterator>
						</table>
						<br/><br/>
						
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function updatezhaobiaoXi(id){
				var url=encodeURI(encodeURI("zhaobiaoAction!updatezhaobiaoXijuti.action?zhaobiaoXi.id="+id));
			$("#showProcess").attr("src", url);	
			chageDiv('block');
		}
	
	function addzhaobiaoXi(){
	var id=document.getElementById("id").value;
			var url=encodeURI(encodeURI("zhaobiaoAction!addzhaobiaoXidahui.action?zhaobiao.id="+id));
			$("#showProcess").attr("src", url);	
			chageDiv('block');
		}
</script>
	</body>
</html>
