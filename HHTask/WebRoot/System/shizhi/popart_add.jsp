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
		<center>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="projectOrderAction_add.action" style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加项目需求单零件
					<br />
					（add project order part）
				</h3>
				<form action="projectOrderPartAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								项目名称
								<br />
								(project name)
							</th>
							<td>
								<SELECT name="projectOrder.id" id="proName"
									onchange="selectMark()" >
									<option value="0">
										未选择
									</option>
									<s:iterator value="pOrderList" id="pOrder">
										<option value="<s:property value='#pOrder.id'/>">
											<s:property value='#pOrder.proName' />::<s:property value='#pOrder.month' />
										</option>
									</s:iterator>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								零件号
								<br />
								（part number）：
							</th>
							<td>
								<SELECT name="tryMake.id" id="markId">
									<option value="0">
										请先选择项目
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								零件数量
								<br />
								（part count）：
							</th>
							<td>
								<input type="text" name="projectOrderPart.partNum" id="partNum" />
							</td>
						</tr>
						<tr>
							<th align="right">
								类型
								<br />
								（type）：
							</th>
							<td>
							<select name="projectOrderPart.type">
							<option>首件</option>
							<option>小批量</option>
							</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								说明
								<br />
								（remark）：
							</th>
							<td>
								<input type="text" name="projectOrderPart.remark" id="remark" />
							</td>
						</tr>

						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" value="<s:property value='errorMessage'/>" id="errorMessage">
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		$(document).ready(function(){ 
			var errorMessage=$("#errorMessage").val();
			if(errorMessage!=""){
　　                          alert(errorMessage);
　　                          }
      }); 
	function selectMark() {
	var itemId = $("#proName").val();
	$.ajax( {
		type : "POST",
		url : "projectOrderPartAction_getTryMakeByPorderId.action",
		data : {
			projectOrderId : itemId
		},
		dataType : 'json',
		success : function(data) {
			$("#markId").empty();
			$(data).each(
					function() {
						$("<option value='" + this.id + "'>"
										+ this.markId
										+ "</option>").appendTo(
								"#markId");
					});
		}
	});

}
	function validate() {
	var proName = document.getElementById("proName").value;
	if (proName == "" || proName == 0) {
		alert("请选择项目名称");
		return false;
	}
	var markId = document.getElementById("markId").value;
	if (markId == "" || markId == 0) {
		alert("请选择零件号");
		return false;
	}
	var partNum = document.getElementById("partNum").value;
    if(partNum==""){
		partNum=0;
	}else if(isNaN(partNum)){
		alert("零件数量请输入数字");
		return false;
	}
	}
</script>
	</body>
</html>
