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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href=""
						style="color: rgb(79, 77, 77)"><br />
					</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加特殊日期
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="SpecialDateAction_add.action"
					method="post" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="center">
									班次
									<input id="banciId" type="hidden" name="specialDate.banciId"
										/>
									<input id="banciName" type="hidden" name="specialDate.banciName"
										/>
								</th>
								<td colspan="1" align="center">
									<SELECT onclick="banciguan()" id="banciselect"
									 style="width: 153px;">
										<option value="">
											请选择班次
										</option>
										<s:iterator value="banciList" id="banci" status="pageStatus">
											<option value="${banci.name}|${banci.id}">
												${banci.name}
											</option>
										</s:iterator>
									</SELECT>
								</td>
							<th align="center">
								日期
							</th>
							<td align="center">
								<input type="text" name="specialDate.date" class="Wdate" id="date1"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									/>
							</td>
						</tr>
						<tr>
							<th align="center">
								工作类型
							</th>
							<td align="center">
								<SELECT name="specialDate.specialType" id="specialType" style="width: 153px;">
									<option value="">请选择工作类型</option>
									<option value="上班">上班</option>
									<option value="放假">放假</option>
								</SELECT>
							</td>
							<td colspan="2" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 80px; height: 30px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("banciId", "班次名称")) {
		return false;
	}
	if (!validateText("banciName", "班次名称")) {
		return false;
	}
	if (!validateText("date1", "日期")) {
		return false;
	}
	if (!validateText("specialType", "工作类型")) {
		return false;
	}
	if (!validateText("banciId", "班次名称")) {
		return false;
	}
	if (!validateText("banciName", "班次名称")) {
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
function banciguan() {
	var name = $("#banciselect").val();
	var usersData = name.split("|");
	var banciid = usersData[1];
	var banciname = usersData[0];
	$("#banciId").val(banciid);
	$("#banciName").val(banciname);
}
</script>
	</body>
</html>
