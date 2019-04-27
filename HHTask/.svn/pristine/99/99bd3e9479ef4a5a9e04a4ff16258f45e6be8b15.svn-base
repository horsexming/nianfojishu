<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="RequisitionAction!findAll.action?requisition.name="
						style="color: #ffffff">返回</a>
				</div>
			</div>
			
			<div align="center">

				<form action="RepairAction!upremarkRepair.action" method="post">
					<input type="hidden" value="${id}" name="id" />
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="hidden" value="${status}" name="status">
					<table border="1" width="100%" class="table">
						<tr>
							<td colspan="20" align="center"
								style="font-family: 微软雅黑; font-weight: bold;">
								申请回复操作
							</td>
						</tr>
						<tr>
							<th>
								工号
							</th>
							<td>
								<input type="hidden" value="${repair.id}" name="repair.id" />
								<input type="text" disabled="disabled" type="hidden"
									name="repair.jobnumber" value="${repair.jobnumber}" />
							</td>

							<th>
								姓名
							</th>
							<td>

								<input type="text" disabled="disabled" type="hidden"
									name="repair.name" value="${repair.name}" />
							</td>
							<th>
								部门
							</th>
							<td>
								<input type="text" disabled="disabled" type="hidden"
									name="repair.department" value="${repair.department}" />
							</td>
						</tr>
						<tr>
							<th>
								报修设备
							</th>
							<td>
								<input type="text" disabled="disabled" type="hidden"
									name="repair.devicename" value="${repair.devicename}" />
							</td>

							<th>
								报修类别
							</th>

							<td>
								<input id="category1" type="text" disabled="disabled"
									type="hidden" name="repair.category" value="${repair.category}">


							</td>

							<th>
								修理人
							</th>
							<td>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
$(function() {
	var category1 = "${repair.category}";
	$
			.ajax( {
				url : "RepairAction!isChange1.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"category" : category1
				},
				success : function(data) {
					$("#personalnominee1").empty();//清空
				var aaa = "${repair.personalnominee}";
				//			$("#personalnominee1").append( "<option selected='selected' value='${repair.personalnominee}'>${repair.personalnominee}</option>");
				$
						.each(
								data.data,
								function(i) {
									if (aaa == data.data[i].repairname) {
										$("#personalnominee1")
												.append(
														"<option selected='selected' value='${repair.personalnominee}'>${repair.personalnominee}</option>");
									} else {
										$("#personalnominee1")
												.append(
														"<option value='"
																+ data.data[i].repairname
																+ "' >"
																+ data.data[i].repairname
																+ "</option>");
									}

								});
			},
			error : function() {
				alert("服务器异常!");
			}
			});

})
</script>
	</body>
</html>
