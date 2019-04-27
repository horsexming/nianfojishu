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
			<font id="zi_font" size="5" color="red">${errorMessage}</font>
				<h2>${qt.name}问卷</h2>
				<form action="QuestionnairePersonAction_addqp.action" method="post" onsubmit="return check()">
					<table class="table">
					<tr>
						<td align="right">
							姓名
						</td>
						<td>
							<input type="text" name="qp.usename" id="usename"/>
						</td>
						<td>
						</td>
					</tr>
					<s:iterator value="qeList" id="pageList" status="pageStatus">
					<tr >
						<td align="right">
							第<s:property value="#pageStatus.index+1" />条            
						</td>
						<td align="left"> 
							<SPAN>${pageList.content }</SPAN>
							<input type="hidden" value="${pageList.content}" name="qp.qulist[${pageStatus.index}].content"/>
						</td> 
						<td align="left">
							<input type="radio" name="qp.qulist[${pageStatus.index}].status" value="yes" checked="checked"/>是
							<input type="radio" name="qp.qulist[${pageStatus.index}].status" value="no"/>否
						</td>                                                                                                                                                                                   
					</tr>
					</s:iterator>
					<tr>
						<td colspan="3" align="center">
							<input type="hidden" value="${ qt.name}" name="qp.qename"/>
							<input type="submit" value="提交" id="sub" style="width: 75px;height: 35px;">
						</td>
					</tr>
				</table>    
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function check(){
	var usename = document.getElementById("usename");
	if(usename!=null && usename.value == ""){
		$("#zi_font").html("请填写姓名");
		usename.focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}

</script>
	</body>
</html>
