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
				<form action="JiaoXiaoKaoHeAction_findAllTamList.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								目标名称:
							</th>
							<th align="left">
								<input type="text" value="${tam.targetName}" name="tam.targetName"/>
							</th>
							<th align="right">
								部门名称:
							</th>
							<th align="left">
								<select name="tam.deptName" id="deptName">
									<option></option>
								</select>
							</th>
						</tr>
					</table>
					<input type="submit" value="查询" class="input"/>
					<input type="button" value="添加" class="input" onclick="tanchu(0)"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							部门目标
						</th>
						<th>
							部门名称
						</th>
						<th>
							加减分数
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
					<s:iterator value="tamList" id="pagetam" status="statussdf">
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
									${pagetam.targetName}
								</td>
								<td>
									${pagetam.deptName}
								</td>
								<td>
									${pagetam.marks}
								</td>
								<td>
									${pagetam.addTime}
								</td>
								<td>
									${pagetam.addUsersName}
								</td>
								<td>
									<a href="javaScritp:;" onclick="tanchu(${pagetam.id})">修改</a>/
									<a href="JiaoXiaoKaoHeAction_delTam.action?tam.id=${pagetam.id}" onclick="return confirm('确定要删除吗?')">删除</a>
								</td>
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
function tanchu(num){
	if(num>0){
		document.getElementById("xiugaiIframe").src = "JiaoXiaoKaoHeAction_findTamBy.action?tam.id="+num;
	}else{
		document.getElementById("xiugaiIframe").src = "./System/jxkh/tam_add.jsp";
	}
	chageDiv('block');
}

function findDudList(){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isbmzmb':1},
		dataType : "json",
		success : function(data) {
			$("#deptName").empty();
			$("#deptName").append('<option value="${tam.deptName}">${tam.deptName}</option>');
			if(data!=null){
				$(data).each(function(){
					$("#deptName").append('<option value="'+this.deptName+'">'+this.deptName+'</option>');
				})
			}
		}
	})
}
</SCRIPT>
	</body>
</html>
