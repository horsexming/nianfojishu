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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font id="msg_font" color="red" size="5"></font>
				<form action="JiaoXiaoKaoHeAction_updateRankCounts.action" method="POST" onsubmit="return check()">
					<table class="table">
						<tr>
							<th align="right">
								所属组别
							</th>
							<td>
								<input type="text" id="groups"  readonly="readonly"
								name="rankCounts.groups" value="${rankCounts.groups}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								名次
							</th>
							<td>
								<input type="text" id="ranking" name="rankCounts.ranking" value="${rankCounts.ranking}"
								 onchange="numyanzheng(this,'zhengshu')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								存在人次
							</th>
							<td>
								<input type="text" id="counts" value="${rankCounts.counts}"
								 name="rankCounts.counts" onchange="numyanzheng(this,'zhengshu')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								分配系数
							</th>
							<td>
								<input type="text" id="fenPeiXIshu" value="${rankCounts.fenPeiXIshu}"
								 name="rankCounts.fenPeiXIshu" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						
					</table>
					<input type="hidden" value="${rankCounts.id}" name="rankCounts.id" />
					<input type="submit" value="修改" class="input" id="sub"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if('${errorMessage}' == '修改成功!~'){
		alert('修改成功!~');
		parent.chageDiv('none');
		parent.window.location.reload();
	}else{
		$("#msg_font").html('${errorMessage}');
	}
})
function check(){
	var groups = $("#groups").val();
	var ranking =$("#ranking").val();
	var counts =$("#counts").val();
	if(groups == ''){
		alert('请填写所属组别!~')
		$("#groups").focus();
		return false;
	}else if(ranking == ''){
		alert('请填写名次')
		$("#ranking").focus();
		return false;
	}else if(counts == ''){
		alert('请填写该名次存在人次')
		$("#isZzPj").focus();
		return false;
	}
	$("#sub").attr('disabled','disabled');
	return true;
}

</SCRIPT>
	</body>
</html>
