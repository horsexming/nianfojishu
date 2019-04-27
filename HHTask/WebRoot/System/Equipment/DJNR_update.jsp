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
				<h3>添加设备点检内容</h3>
				<form action="DJNRAction_update.action" method="post" onsubmit="return check()">
					<table width="75%">
						<tr>
							<td align="center">
								点检内容:
								<input type="text" name="djnr.nr" value="${djnr.nr}"/>
							</td>
						</tr>
						<tr>
							<td  align="center">
								<input type="hidden" name="djnr.id" value="${djnr.id}"/>
								<input type="submit" value="提交"/>
								<input type="reset" value="重置"/>
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function check(){
	var nr=document.getElementById("nr");
	if(nr!=null&&nr.value==""){
		alert('点检内容不能为空!');
		nr.focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
	
}

$(function(){
	var rebeack=$("#rebeack").val();
		if(rebeack =="修改成功"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
})

</SCRIPT>
	</body>
</html>
