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
	<body>
		<%@include file="/util/sonTop.jsp"%>
			<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在进行手动下单修改操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								姓名
							</th>
							<td>
								<select name="yearimprove.name"></select>
							</td>
							<th align="right">
								月份
							</th>
							<td>
								<input type="text" name="yearimprove.months" id="months"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
						</tr>
					</table>
					<input type="button" " value="添加" class="input" onclick="tanchu()" />
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							部门
						</th>
						<th>
							姓名
						</th>
						<th>
							职务
						</th>
						<th>
							难度系数
						</th>
						<th>
							经营性
						</th>
						<th>
							挑战度
						</th>
						<th>
							月份
						</th>
						<th>
							添加时间
						</th>
						<th>
							添加人
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="yearimproveList" id="pageList"
						status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${pageList.dept}
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.post}
						</td>
						<td>
							${pageList.ndxs}
						</td>
						<td>
							${pageList.jyx}
						</td>
						<td>
							${pageList.tzd}
						</td>
						<td>
							${pageList.months}
						</td>
						<td>
							${pageList.addTime}
						</td>
						<td>
							${pageList.addUsersName}
						</td>
						<td>
							<a href="javaScript:;" onclick="tanchu('${pageList.id}')">修改</a>
							<a href="JiaoXiaoKaoHeAction_delyearimprove.action?yearimprove.id=${pageList.id}">删除</a>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	findDudList();
})
findDudList = ()=>{
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isgsx':1},
		dataType : "json",
		success : function(data) {
			$("#name").empty();
			$("#name").append('<option value=""></option>');
			if(data!=null){
				$(data).each(function(){
					$("#name").append('<option value='+this.leader+'>'+this.leader+'</option>');
				})
			}
		}
	})
}
function tanchu(num){
	if(num>0){
		document.getElementById("xiugaiIframe").src = "JiaoXiaoKaoHeAction_findyearimproveById.action?id="+num;
	}else{
		document.getElementById("xiugaiIframe").src = "./System/jxkh/yearimprove_add.jsp";
	}
	chageDiv('block');
}
</SCRIPT>
	</body>
</html>
