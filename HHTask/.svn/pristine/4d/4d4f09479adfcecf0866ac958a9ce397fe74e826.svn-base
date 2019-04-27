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
	<body >
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					添加预付款单
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="zhaobiaoAction!addyufu.action" method="post"
					onsubmit="return adddata()">
					<table class="table" align="center">
						<tr>
							<td align="right">
								预付项目名称：
							</td>
							<td align="left">
								<input type="text" name="prepayApp.yyName" id="yyName"
									value="${prepayApp.yyName}" />
							</td>
							<td align="right">
								采购总额：
							</td>
							<td align="left">
<%--							<s:if test="prepayApp==null">--%>
<%--								<input type="text" id="allMoney" name="prepayApp.allMoney"--%>
<%--									onblur="mustBeNumber('allMoney');changvalue2()" value="${prepayApp.allMoney}"--%>
<%--									onkeyup="mustBeNumber('allMoney');changvalue2()"--%>
<%--									 />--%>
<%--							</s:if>--%>
<%--							<s:else>--%>
								<input type="text" id="allMoney" name="prepayApp.allMoney"
									value="${waigouOrder.allMoney}" 
									 />
<%--							</s:else>--%>
							</td>
						</tr>
						<tr>
							<td align="right">
								预付比例：
							</td>
							<td align="left">
								<input type="text" id="yfbl" name="prepayApp.yfbl"
									onblur="mustBeNumber('yfbl');changvalue2()"
									onkeyup="mustBeNumber('yfbl');changvalue2()" />
								%
								<font color="red">*</font>
								<font id="show1" color="red" style="display: none">
									预付款比例需小于${coun}%</font>
							</td>
							<td align="right">
								预付金额：
							</td>
							<td align="left">
								<input type="text" id="yfMoney" name="prepayApp.yfMoney"
								 readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="right">
								票据类别：
							</td>
							<td align="left">
								<SELECT>
									<option value="">
										票据类别
									</option>
									<option value="支票">
										支票
									</option>
									<option value="电汇">
										电汇
									</option>
								</SELECT>
							</td>
							<td align="right">
								预计报销日期：
							</td>
							<td align="left">
								<input class="Wdate" type="text" id="expectedTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
 									name="prepayApp.expectedTime" value="${prepayApp.expectedTime}" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="right">
								订单编号：
							</td>
							<td align="left">
								${waigouOrder.planNumber}
								<input type="hidden" name="id1" value="${waigouOrder.id}" />
								<input type="hidden" name="prepayApp.poNumber" value="${waigouOrder.planNumber}" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit"
									style="width: 100px; height: 40px; margin-left: 70px;"
									value="添加(add)" />
							</td>
						</tr>
					</table>
				</form>
				
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							预付款单编号
						</td>
						<td align="center">
							项目名称
						</td>
						<td align="center">
							采购总额
						</td>
						<td align="center">
							预付金额
						</td>
						<td align="center">
							预付比例
						</td>
						<td align="center">
							预计报销日期
						</td>
						<td align="center">
							申请状态
						</td>
						<td align="center">
							添加时间
						</td>
					</tr>
					<s:iterator value="prepayAppList" id="samples" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.number}
						</td>
						<td align="center">
							${samples.yyName}
						</td>
						<td align="center">
							${samples.allMoney}
						</td>
						<td align="center">
							${samples.yfMoney}
						</td>
						<td align="center">
							${samples.yfbl}%
						</td>
						<td align="center">
							${samples.expectedTime}
						</td>
						<td align="center">
							${samples.status}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		parent.location.reload(true);//刷新父页面
	}
});
function adddata() {
	if (!validateText("yyName", "预付项目名称")) {
		return false;
	}
	if (!validateText("allMoney", "采购总额")) {
		return false;
	}
	if (!validateText("yfbl", "预付比例")) {
		return false;
	}
	if (!validateText("expectedTime", "预计报销日期")) {
		return false;
	}
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function changvalue2(){
	var allJin = $("#allMoney").val();
	var yfbl = $("#yfbl").val();
	var coun = Number('${coun}');
	
	if(yfbl>=coun){
		//alert("预付款比例不能大于100%");
		$("#show1").show();
		$("#yfMoney").val("");
		$("#yfbl").val("");
		return false;
	}else{
		$("#show1").hide();
	}
	var yfJin = parseFloat(allJin)*parseFloat(yfbl)/100;
	$("#yfMoney").val(yfJin);
}
</script>
	</body>
</html>
