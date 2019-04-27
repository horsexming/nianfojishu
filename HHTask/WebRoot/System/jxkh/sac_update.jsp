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
				<form action="JiaoXiaoKaoHeAction_updateSac.action" method="POST" onsubmit="return check()">
					<table class="table" style="width: 80%;">
						<tr>
							<th align="right">
								金额从:
							</th>
							<td>
								<input type="text" value="${sac.lowAmount}" name="sac.lowAmount" id="lowAmount"
								onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								到:
							</th>
							<td>
								<input type="text" value="${sac.ceilingAmount}" name="sac.ceilingAmount" id="ceilingAmount"
								onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								提取系数:
							</th>
							<td>
								<input type="text" value="${sac.coefficient}" name="sac.coefficient" id="coefficient"
								onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								单位:
							</th>
							<td>
								<select name="sac.danwei" id="danwei">
									<option value="${sac.danwei}">${sac.danwei}</option>
									<option value="元">元</option>
									<option value="千元">千元</option>
									<option value="万元">万元</option>
									<option value="亿元">亿元</option>
								</select>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${sac.id}" name="sac.id"/>
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
	}
})
function check(){
	var coefficient =$("#coefficient").val();
	if(coefficient == ''){
		alert('请填写提取系数');
		$("#coefficient").focus();
		return false;
	}
	$("#sub").attr('disabled','disabled');
	return true;
}
function changvalue(obj){
	var value = obj.value;
	if(value!=''){
		var  strs = value.split("_");
		$("#rankNo").val(strs[0]);
		$("#rank").val(strs[1]);
	}
}

</SCRIPT>
	</body>
</html>
