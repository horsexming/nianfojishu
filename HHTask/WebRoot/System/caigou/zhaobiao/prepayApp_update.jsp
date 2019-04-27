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
					预付款申请单修改
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="zhaobiaoAction!updateyufu.action" method="post"
					onsubmit="return adddata()">
					<table class="table" align="center">
						<tr>
							<td align="right">
								预付项目名称：
							</td>
							<td align="left">
								<input type="text" name="prepayApp.yyName"
									value="${prepayApp.yyName}" />
							</td>
							<td align="right">
								采购总额：
							</td>
							<td align="left">
								<input type="text" id="allMoney" name="prepayApp.allMoney"
									value="${prepayApp.allMoney}"
									onblur="mustBeNumber('allMoney');changvalue2()"
									onkeyup="mustBeNumber('allMoney');changvalue2()" />
							</td>
						</tr>
						<tr>
							<td align="right">
								预付比例：
							</td>
							<td align="left">
								<input type="text" id="yfbl" name="prepayApp.yfbl"
									value="${prepayApp.yfbl}"
									onblur="mustBeNumber('yfbl');changvalue2()"
									onkeyup="mustBeNumber('yfbl');changvalue2()" />
								%
								<font color="red">*</font>
								<font id="show1" style="display: none;" color="red">
									预付款比例不能大于100%</font>
							</td>
							<td align="right">
								预付金额：
							</td>
							<td align="left">
								<input type="hidden" name="prepayApp.id" value="${prepayApp.id}" />
								<input type="text" id="yfMoney" name="prepayApp.yfMoney"
									value="${prepayApp.yfMoney}" readonly="readonly" />
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
								${prepayApp.poNumber}
							</td>
<%--							<td align="right">--%>
<%--								是否同步：--%>
<%--							</td>--%>
<%--							<td align="left">--%>
<%--								<input type="radio" id="isfou1" name="isfou" value="是" />--%>
<%--								<label for="isfou1">--%>
<%--									是--%>
<%--								</label>--%>
<%--								&nbsp;&nbsp;--%>
<%--								<input type="radio" id="isfou" name="isfou" value="否"--%>
<%--									checked="checked" />--%>
<%--								<label for="isfou">--%>
<%--									否--%>
<%--								</label>--%>
<%--								&nbsp;&nbsp;--%>
<%--								<br />--%>
<%--								(是否将预付款比例和付款周期同步至供应商信息)--%>
<%--							</td>--%>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit"
									style="width: 100px; height: 40px; margin-left: 70px;"
									value="修改(update)" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function adddata() {
	if (!validateText("yfbl", "预付比例")) {
		return false;
	}
	if (!validateText("expectedTime", "预计报销日期")) {
		return false;
	}
}function validateText(id, textname) {
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
			if(yfbl>100){
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
