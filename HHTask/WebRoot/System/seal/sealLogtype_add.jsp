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
					<a href="AccessEquipmentAction_toadd.action"
						style="color: rgb(79, 77, 77)">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加印章
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="SealLogAction_addsealLogtype.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="center">
								印章名称
							</th>
							<td align="center">
								<input type="text" name="sealLogType.slname" id="slname" />
							</td>
							<th align="center">
								存放位置
							</th>
						</tr>
						<tr>
							<td colspan="8" align="center">
								<input type="submit" value="添加(Add)"
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
$(function() {
	$("#cunFangWeizhi").bind(
			"change",
			function() {
				if ($("#cunFangWeizhi").val() != "") {
					//显示所有档案设备名称
					$.ajax( {
						url : 'DanganAction_findacEJson.action',
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							aceName : $("#cunFangWeizhi").val()
						//要传过去的值
						},
						success : function(allAce) {
							$("#cunFangNum").empty();//清空数据
							$("<option value=''>请选择档案室门禁编号</option>").appendTo(
									"#cunFangNum");
							$(allAce).each(
									function() {
										$(
												"<option value='"
														+ this.equipmentNum
														+ "'>"
														+ this.equipmentNum
														+ "</option>")
												.appendTo("#cunFangNum");
									});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#cunFangNum").empty();//清空数据	
				}
			});
});
$(function() {
	$("#cunFangWeizhi").bind(
			"change",
			function() {
				if ($("#cunFangWeizhi").val() != "") {
					//显示所有档案设备名称
					$.ajax( {
						url : 'DanganAction_findacEJson.action',
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							aceName : $("#cunFangWeizhi").val()
						//要传过去的值
						},
						success : function(allAce) {
							$("#cunShiId").empty();//清空数据
							$("<option value=''>请选择档案室门禁ID</option>").appendTo(
									"#cunShiId");
							$(allAce).each(
									function() {
										$(
												"<option value='"
														+ this.equipmentIP
														+ "'>"
														+ this.equipmentIP
														+ "</option>")
												.appendTo("#cunShiId");
									});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#cunShiId").empty();//清空数据	
				}
			});
});
function daNameNull1() {
	if ($("#cunFangWeizhi").val() == "" || $("#cunFangWeizhi").val() == "") {
		alert("存档室名称不能为空！");
		return false;
	}
}
</script>
	</body>
</html>
