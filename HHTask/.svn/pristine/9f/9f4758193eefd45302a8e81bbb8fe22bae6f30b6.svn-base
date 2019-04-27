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
				<br />
				<table class="table" style="line-height: 30px;" align="center">
					<tr>
						<td colspan="3" align="center">
							<font style="font-size: 16px; font-weight: bolder;">记账凭证</font>
						</td>
					</tr>
					<tr>
						<th>
							摘要
						</th>
						<th>
							科目
						</th>
						<th>
							金额
						</th>
					</tr>
					<tr>
						<td>
							${cwPingZheng.subject}
						</td>
						<td>
							<select id="dept" style="width: 200px;"
								onMouseOver="sbrSelect(this)" name="cwZWAndSbrList[1].fk_sbrId">
								<option>
									选择科目
								</option>
							</select>
						</td>
						<td>
							${cwPingZheng.money}
						</td>
					</tr>
				</table>
				<br />
				<br />
				<table style="width: 95%">
					<tr>
						<td align="left" colspan="1">
							制单人:${session.Users.name}
						</td>
						<td colspan="2" align="right">
							凭证编号:
							<input name="" />
							日期:
							<input class="Wdate" type="text" name=""
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function sbrSelect(obj) {
	var selected = obj.id;//获取到当前列的id
	$.ajax( {
		type : "POST",
		url : "SubjectBudgetAction!findAllSBRate.action",
		data : {},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, n) {
				$(obj)
						.append(
								"<option value='" + n.id + "' >" + n.name
										+ "</option>");
			})
		}
	});

}
</script>
	</body>
</html>
