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
			<div align="center">
				<h3>
					修改档案存档申请
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="DanganAction_update.action" method="post" enctype="multipart/form-data"
					onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="right" style="width: 300px;">
								档案名称
								<input type="hidden" name="dangAn.id" value="${dangAn.id}"/>
							</th>
							<td align="left">
								<input type="text" name="dangAn.daName" id="daName" value="${dangAn.daName}"/>
							</td>
							<th align="right">
								档案编号
							</th>
							<td align="left">
								<input type="text" name="dangAn.daNum" id="daNum" value="${dangAn.daNum}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								存档室名称
							</th>
							<td align="left">
								<select name="dangAn.cdAceName" id="cdAceName"
									style="width: 156px;">
									<option value="${dangAn.cdAceName}">
										${dangAn.cdAceName}
									</option>
									<option value="四楼档案室">
										四楼档案室
									</option>
								</select>
							</td>
							<th align="right">
								存档室门禁编号
							</th>
							<td align="left">
								<select name="dangAn.cdAceNum" id="cdAceNum"
									style="width: 156px;" >
									<option value="${dangAn.cdAceNum}">
										${dangAn.cdAceNum}
									</option>
									<option value="DA001">
										DA001
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								申请人手机号
							</th>
							<td align="left" colspan="1">
								<input type="text" name="dangAn.sqTel" id="sqTel" value="${dangAn.sqTel}"/>
							</td><th align="right">
								档案附件
							</th>
							<td align="left" colspan="1">
							<a href="<%=path%>${dangAn.daFiles}">查看附件</a>
								<input type="file" name="fujian" id="daFiles" />
								<input type="hidden" name="dangAn.daFiles"
									id="carFiles" value="${dangAn.daFiles}"/>
							</td>
						</tr>
						<tr>
						<th align="right">存档柜位置</th>
						<td colspan="3"><input type="text" name="dangAn.daAddress" id="daAddress" value="${dangAn.daAddress}"/></td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="修改(update)"
									style="width: 100px; height: 50px;" />
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
	if (!validateText("daName", "档案名称")) {
		return false;
	}
	if (!validateText("daNum", "档案编号")) {
		return false;
	}
	if (!validateText("cdAceName", "存档室名称")) {
		return false;
	}
	if (!validateText("cdAceNum", "存档室门禁编号")) {
		return false;
	}
	if (!validateText("sqTel", "申请人手机号")) {
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

</script>
	</body>
</html>
