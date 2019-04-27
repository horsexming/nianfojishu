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
		<br><%@include file="/util/sonTop.jsp"%>
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
			
			<div align="center">
				<form action="register_upload.action" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table>
						<tr>
							<th align="center" colspan="4">
								<h3>
									上传考勤文件
								</h3>
							</th>
						</tr>
						<tr>
							<td>
								充值份数:
							</td>
							<td>
								<input type="text" name="copies" id="copies" />
							</td>
							<td>
								考勤文件路径:
							</td>
							<td>
								<input type="file" name="myFile" id="myFile" />
							</td>
						</tr>
						<tr>
							<td>
								开始时间:
							</td>
							<td>
								<input class="Wdate" type="text" name="upTime" id="upTime"
									onClick="
	WdatePicker( {
		dateFmt : 'HH:mm',
		skin : 'whyGreen'
	});" />
							</td>
							<td align="right">
								结束时间:
							</td>
							<td>
								<input class="Wdate" type="text" name="downTime" id="downTime"
									onClick="
	WdatePicker( {
		dateFmt : 'HH:mm',
		skin : 'whyGreen'
	});" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="提交"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="清空"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<hr>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var validate = /^[0-9]{1,}$/;
	var copies = document.getElementById("copies").value;
	var valiTime = /^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/;
	var uptime = document.getElementById("upTime").value;
	var uptimeNOblank = uptime.replace(/(^\s+)|(\s+$)|(\s+)/g, ""); //去除前后中所有空
	var downtime = document.getElementById("downTime").value;
	var downtimeNoblank = downtime.replace(/(^\s+)|(\s+$)|(\s+)/g, "");
	var file = document.getElementById("myFile").value;
	if (validate.test(copies)) {
		if (valiTime.test(uptimeNOblank) && valiTime.test(downtimeNoblank)) {
			if (file != "") {
				return true;
			} else {
				alert("请选择上传文件");
				return false;
			}
		} else {
			alert("时间格式    小时:分钟    中间以:隔开");
			return false;
		}
	} else {
		alert("份数请填写整数!");
		return false;
	}
}
</script>
	</body>
</html>
