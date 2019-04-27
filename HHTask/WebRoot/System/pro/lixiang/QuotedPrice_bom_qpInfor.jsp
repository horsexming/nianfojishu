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
	<body style="height: 800px;">
		<center>
			<form id="processForm" action="QuotedPrice_updateProcess.action"
				style="margin: 0px; padding: 0px;" method="post">
				<input type="hidden" name="qpInfor.id" value="${qpInfor.id}" />
				<input type="hidden" id="errorMessage" value="${errorMessage}" />
				<input type="hidden" id="isOld" name="qpInfor.isOld"
					value="${qpInfor.isOld}">
				<input id="gxingbie" type="hidden" />
				<table class="table" style="width: 100%">
					<tr>
						<th colspan="8" align="center">
							工序处理
						</th>
					</tr>
					<tr>
						<td align="right">
							工序号:
						</td>
						<td>
							<input id="processNO" name="qpInfor.processNO"
								value="${qpInfor.processNO}" />
						</td>
						<td align="right">
							名 称:
						</td>
						<td>
							<select id="processName" name="qpInfor.processName"
								style="width: 150px;" onchange="getMachine(this.value)">
								<option value="${qpInfor.processName}">
									${qpInfor.processName}
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">
							设备:
						</td>
						<td>
							<select id="shebeiNo" name="qpInfor.shebeiId"
								style="width: 150px;">
								<option value="${qpInfor.shebeiId}">
									${qpInfor.shebeiName}
								</option>
							</select>
						</td>
						<td align="right">
							工装:
						</td>
						<td height="83px">
							<s:if test='qpInfor.isOld == "yes"'>
								<input type="button" id="luruNew" value="录入新工装"
									onclick="newGongZhuang()">
								<br />
								<select id="gongzhuang" name="qpInfor.gongzhuangId"
									style="width: 197px;">
									<option value="${qpInfor.gongzhuangId}">
										${qpInfor.gongzhuangNumber}(${qpInfor.gongzhuang})
									</option>
								</select>
								<div id="newGong" style="display: none;">
									编号:
									<input name="qpInfor.gongzhuangNumber">
									<br />
									名称:
									<input name="qpInfor.gongzhuang">
								</div>
							</s:if>
							<s:else>
								<input type="button" id="luruNew" value="选择工装库"
									onclick="newGongZhuang()">
								<br />
								<select id="gongzhuang" name="qpInfor.gongzhuangId"
									style="width: 197px; display: none;">
									<option></option>
								</select>
								<div id="newGong">
									编号:
									<input name="qpInfor.gongzhuangNumber"
										value="${qpInfor.gongzhuangNumber}">
									<br />
									名称:
									<input name="qpInfor.gongzhuang" value="${qpInfor.gongzhuang}">
								</div>
							</s:else>

						</td>
					</tr>
					<tr>
						<td align="right">
							生产类型:
						</td>
						<td>
							<select name="qpInfor.productStyle" style="width: 150px;">
								<option value="${qpInfor.productStyle}">
									${qpInfor.productStyle}
								</option>
								<option value="自制">
									自制
								</option>
								<option value="外委">
									外委
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<input type="submit" value="修改"
								style="width: 80px; height: 40px;" />
						</td>
					</tr>
				</table>
			</form>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//通过工序名称查询设备
function getMachine() {
	var processName = $("#processName").val();
	if (processName != "") {
		$.ajax( {
			type : "POST",
			url : "GzstoreAction!getMachineListByGongxuName.action",
			dataType : "json",
			data : {
				processName : processName
			},
			success : function(msg) {
				if (msg.success) {
					$("#shebeiNo").empty();
					$("#shebeiNo").append("<option value=''></option>");
					$.each(msg.data, function(i, n) {
						$("#shebeiNo").append(
								"<option value='" + n.id + "'>" + n.name + "("
										+ n.no + ")</option>");
					});
				} else {
					alert(msg.message);
				}
			}
		});
	} else {
		$("#shebeiNo").empty();
	}
}
//通过型别查询工装信息
function getGz() {
	var gxingbie = $("#gxingbie").val();
	$.ajax( {
		type : "POST",
		url : "GzstoreAction!getGzstoreListByXingbie.action",
		data : {
			'gzstore.xingbie' : gxingbie
		},
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				$("#gongzhuang").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#gongzhuang").append(
							"<option value='" + n.id + "'>" + n.no + "("
									+ n.name + ")</option>");
				});
			} else {
				alert(msg.message);
			}
		}
	});
}

//录入新工装
var newStatus = "${qpInfor.isOld}";
function newGongZhuang() {
	if (newStatus == "yes") {
		$("#newGong").show();
		$("#gongzhuang").hide();
		newStatus = "no";
		$("#luruNew").val("选择工装库");
	} else {
		$("#newGong").hide();
		$("#gongzhuang").show();
		newStatus = "yes";
		$("#luruNew").val("录入新工装");
	}
	$("#isOld").val(newStatus);
}

//查询所有工序
function getProcess() {
	$.ajax( {
		type : "POST",
		url : "processGzstoreAction_getProcessGzstoreListAllForSelect.action",
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				//$("#processName").empty();
				$("#processName").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#processName").append(
							"<option value='" + n.processName + "'>"
									+ n.processName + "</option>");
				});
			} else {
				alert(msg.message);
			}
		}
	});
}

//调用
getGz();
getProcess();
$(document).ready(function(){
	var msg=$("#errorMessage").val();
	if(msg!=null&&msg!=""){
		alert(msg);
	}
})
//getMachine();
</script>
	</body>
</html>
