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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="center" style="border: 1px solid #00000;">
				<div id="rootTemplateDiv">
					<form action="ProcardAction!addNewzj.action" method="post">
						<input name="id" value="${id}" type="hidden" />
						<table width="100%" border="0" style="border-collapse: collapse;"
							class="table">
							<tr>
								<th colspan="12">
									处理报废品
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									<input type="checkbox"  onclick="chageAllCheck(this)">
									选择
								</th>
								<th align="center">
									序号
								</th>
								<th align="center">
									件号
								</th>
								<th align="center">
									名称
								</th>
								<th align="center">
									卡片类型
								</th>
								<th align="center">
									产品类型
								</th>
								<th align="center">
									批次
								</th>
								<th align="center">
									计划单号
								</th>
								<th align="center">
									制卡时间
								</th>
								<th align="center" width="90px;">
									已发卡号
								</th>
							</tr>
							<s:iterator value="procardList" id="pageProcard"
								status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<input type="checkbox" name="processIds"
										value="${pageProcard.id}" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td>
									${pageProcard.markId}
								</td>
								<td>
									${pageProcard.proName}
								</td>
								<td>
									${pageProcard.procardStyle}
								</td>
								<td>
									${pageProcard.productStyle}
								</td>
								<td>
									${pageProcard.selfCard}
								</td>
								<td>
									${pageProcard.planOrderNum}
								</td>
								<td>
									${pageProcard.procardTime}
								</td>
								<td>
									<span id="sendCard${pageStatus.index+1}">
										${pageProcard.cardNum}</span>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<th colspan="5" align="right">
									负责人:
								</th>
								<td colspan="5">
									<input name="responsible" />
								</td>
							</tr>
							<tr>
								<td colspan="13">
									<input type="submit" value="确定"
										style="width: 80px; height: 50px;">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//添加组件/原材料流水卡片
function submitForm(formId, index, oldCard) {
	if ($("#cardNumber" + index).val() == "") {
		alert("请刷卡!");
		$("#cardNumber" + index).select();
		return false;
	} else {
		if ($("#cardNumber" + index).val() == oldCard) {
			alert("重新发卡的卡号不能和已发的卡号一致!您可更换另一张卡重试!");
			$("#cardNumber" + index).select();
		} else {
			$.ajax( {
				type : "POST",
				url : "ProcardAction!bangCard.action",
				dataType : "json",
				data : $("#" + formId + index).serialize(),
				success : function(msg) {
					if (msg.success) {
						alert(msg.message);
						$("#cardNumber" + index).select();
						if (msg.message == "发卡成功!") {
							$("#sendCard" + index).html(
									$("#cardNumber" + index).val());
						}
					} else {
						alert(msg.message);
					}
				}
			});
		}
	}
}
</script>
	</body>
</html>
