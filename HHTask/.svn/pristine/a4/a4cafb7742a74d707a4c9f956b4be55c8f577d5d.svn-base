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
			<font color="red" size="5mm">${errorMessage}</font>
				<form action="yuanclAndWaigjAction_updatelt.action" method="post" onsubmit="return check()">
					<table>
						<tr>
							<th align="right">
								LT等级(周)
							</th>
							<td>
								<input type="text" value="${lt1.ltdengji}"  name="lt1.ltdengji" id="ltdengji" onchange="fuzhi()" readonly="readonly"
								onblur="numyanzheng(this,'zhengshu')" onkeyup="numyanzheng(this,'zhengshu')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								LT+?(周)
							</th>
							<td>
								<input type="text" value="${lt1.ltadd}"  name="lt1.ltadd" id="ltadd"  onchange="fuzhi()"
								onblur="numyanzheng(this,'zhengshu')" onkeyup="numyanzheng(this,'zhengshu')"/>
								<input type="hidden" name="lt1.ltuse" id="ltuse"  value="${lt1.ltuse}" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<input type="hidden" value="${lt1.id}" name="lt1.id"/>
								<input type="submit" value="修改"  id="sub" onclick="todisabled(this)" class="input"/>
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if('${errorMessage}' == "true"){
		alert("修改成功！~");
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
function check(){
	var ltdengji = $("#ltdengji").val();
	var  ltadd = $("#ltadd").val();
	if(ltdengji == ''){
		alert("请先输入LT等级");
		$("#sub").removeAttr("disabled");
		return false;
	} else if(ltadd == ''){
		alert("请先输入LT+?");
		$("#sub").removeAttr("disabled");
		return false;
	}
}

function fuzhi(){
	var ltdengji = $("#ltdengji").val();
	var  ltadd = $("#ltadd").val();
	if(ltdengji!='' && ltadd!=''){
		ltdengji = parseInt(ltdengji);
		ltadd = parseInt(ltadd);
		$("#ltuse").val(ltdengji+ltadd);
	}
}
</SCRIPT>
	</body>
</html>
