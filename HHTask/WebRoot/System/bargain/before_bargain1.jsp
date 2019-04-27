<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>

	<body>
		<form action="bargainAction_jumpaddBargain1.action" method="post"
			onsubmit="return check()">
			<table align="center" class="table">
				<tr>
					<td align="center" colspan="2">
						<h1>
							合同缘由
						</h1>
					</td>
				</tr>
				<tr>
					<th>
						来源
					</th>
					<th align="left">
						<select id="source" name="contract_source">
							<option value="">
								--请选择来源--
							</option>
							<option value="OA">
								项目采购
							</option>
							<option value="SB">
								设备维修
							</option>
							<%--    				<option value="KVP">KVP</option>--%>
							<option value="XBYJ">
								询比议价
							</option>
							<%--    				<option value="QT">其他</option>--%>
							<option value="紧急采购">
								紧急采购
							</option>
							<option value="零部件及工序外委采购">
								零部件及工序外委采购
							</option>
							<option value="原材料采购">
								原材料采购
							</option>
							<option value="设备采购">
								设备采购
							</option>
						</select>
					</th>
				</tr>
				<tr>
					<th>
						单号
					</th>
					<th align="left">
						<select id="number" name="contract_num1"></select>
						<select id="number1" style="display: none;" name="contract_num2">
							<option value="">
								--请选择外委评审单号--
							</option>
						</select>
					</th>
				</tr>
				<br />
				<tr>
					<th colspan="2">
						<input type="submit" value=" 下一步">
					</th>
				</tr>
			</table>
		</form>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$("#source").change(function() {
	getClass();
});
/*$("#number").change(function(){
	getNumber();
});*/
function getClass() {
	var source = $("#source").val();
	$
			.ajax( {
				url : "bargainAction_findbargainSource1.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"source" : source
				},
				success : function(data) {
					$("#number").empty();//清空
					if (data != null && data.length > 0) {
						$
								.each(
										data,
										function(i, n) {
											if (source == 'OA') {
												if (i == 0) {
													$("#number")
															.append(
																	"<option value=''>--请选择OA单号--</option>");
												}
												$("#number")
														.append(
																"<option value='"
																		+ n.detailSeqNum
																		+ "'>"
																		+ n.detailSeqNum
																		+ "("
																		+ n.detailFormat
																		+ ")</option>");
											}
											if (source == 'SB') {
												if (i == 0) {
													$("#number")
															.append(
																	"<option value=''>--请选择设备单号--</option>");
												}
												$("#number").append(
														"<option value='"
																+ n.barcode
																+ "'>"
																+ n.barcode
																+ "</option>");
											}
											if (source == 'KVP') {
												if (i == 0) {
													$("#number")
															.append(
																	"<option value=''>--请选择KVP单号--</option>");
												}
												$("#number").append(
														"<option value='" + n
																+ "'>" + n
																+ "</option>");
											}
											if (source == 'XBYJ') {
												if (i == 0) {
													$("#number")
															.append(
																	"<option value=''>--请选择询比议价单号--</option>");
												}
												$("#number")
														.append(
																"<option value='"
																		+ n.bargain_number
																		+ "'>"
																		+ n.bargain_number
																		+ "</option>");
											}
											if (source == '紧急采购') {
												$("#number").append(
														"<option value='" + n
																+ "'>" + n
																+ "</option>");
											}
											if (source == '原材料采购') {
												$("#number").append(
														"<option value='" + n
																+ "'>" + n
																+ "</option>");
											}
											if (source == '零部件及工序外委采购') {
												$("#number").append(
														"<option value='"
																+ n.osaNO
																+ "'>"
																+ n.osaNO
																+ "</option>");
											}
										})
					} else {
						$("#number").append(
								"<option value='" + source + "'>" + source
										+ "</option>");
					}

				},
				error : function() {
					alert("服务器异常!");
				}
			});
}
//查询委外评审单号
/* function getNumber(){
	 var number = $("#number").val();
	 if(number=="零部件及工序外委采购"){
		 $("#number1").attr('style','block');
		  $.ajax( {
		url : "bargainAction_findbargainNumber.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {},
		success : function(data) {
			$("#number1").empty();//清空
			$.each(data,function(i,n){
				if(i==0){
			   $("#number1").append("<option value=''>--请选择外委评审单号--</option>");
				}
					$("#number1").append("<option value='"+n.osaNO+"'>"+n.osaNO+"</option>");
			})
		},
		error : function() {
			alert("服务器异常!");
		}
	});
		 
	 }
	
 }*/

function check() {
	var source = document.getElementById("source");
	var number = document.getElementById("number");
	if (source.value == "") {
		alert("请选择议价来源!");
		source.focus();
		return false;
	}
	if (number.value == "") {
		alert("请选择单号!");
		number.focus();
		return false;
	}

}
</script>

</html>