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
	<body onload="createDept('repairdept');">
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
					<a href="RepairAction!findAll.action?repair.name="
						style="color: #ffffff">查询报修单</a>
				</div>
			</div>
			
			<div align="center">
				<font color="red"> ${successMessage}</font>
				<form action="RepairAction!updaterepair.action" method="post">
					<input type="hidden" name="repair.id" value="${repair.id}" />
					<input type="hidden" name="repair.status" value="${repair.status}" />
					<input type="hidden" name="repair.userid" value="${repair.userid}" />
					<input type="hidden" name="repair.repairtime"
						value="${repair.repairtime}" />
					<input type="hidden" name="repair.name" value="${repair.name}" />
					<input type="hidden" name="repair.jobnumber"
						value="${repair.jobnumber}" />
					<input type="hidden" name="repair.department"
						value="${repair.department}" />
					<table border="1" width="100%" class="table">
						<tr>
							<td colspan="20" align="center"
								style="font-family: 微软雅黑; font-weight: bold;">
								修改报修申请单
							</td>
						</tr>

						<tr>
							<th>
								维修部门
							</th>
							<td>
								<select id="repairdept" name="repair.repairdept">
									<option selected="selected" value="${repair.repairdept}">
										${repair.repairdept}
									</option>
									<s:iterator id="cu" value="list">
										<option value="${cu.ta_dept}">
											${cu.ta_dept}
										</option>
									</s:iterator>
								</select>
							</td>
							<th>
								维修类别
							</th>

							<td>
								<select id="category" name="repair.category"
									style="width: 160px;">
									<option selected="selected" value="${repair.category}">
										${repair.category}
									</option>
								</select>
							</td>

							<s:if test='repair.status=="待指派"'>
							</s:if>
							<s:else>
								<th>
									维修人
								</th>
								<td>
									<select id="personalnominee" name="repair.personalnominee">
										<option selected="selected" value="${repair.personalnominee}">
											${repair.personalnominee}
										</option>
									</select>
								</td>

							</s:else>

						</tr>

						<tr>
							<th>
								报修设备
							</th>
							<td>
								<input type="text" name="repair.devicename"
									value="${repair.devicename}" />
							</td>

							<th>
								报修故障:
							</th>
							<td colspan="3">

								<input type="text" style="width: 250px; height: 80px;"
									name="repair.repairfailed" value="${repair.repairfailed}" />
							</td>

						</tr>


						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="修改"
									style="width: 100px; height: 50px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 100px; height: 50px;">
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
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js"></script>
		<script type="text/javascript">
		$(function(){
		getClass();
		getUser();
		//点击报修部门时触发的事件
		$("#repairdept").change(function(){
			getClass();
		});
		
	//点击报修类别时触发的事件
	$("#category").change(function(){
		getUser();//调用点击报修类别时触发的事件
		});
			
})
function getUser(){
		var category = $("#category").val();
		//var repairdepartment1 = "${repair.repairdept}";
		var repairdepartment1 = $("#repairdept").val();
		if(repairdepartment1==null||repairdepartment1==""){
			repairdepartment1="${repair.repairdept}";
		}
	$.ajax( {
		url : "RepairAction!isChange1.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {
			"category" : category,
			"repairdepartment":repairdepartment1
		},
		success : function(data) {
			$("#personalnominee").empty();//清空
			$.each(data.data, function(i) {
				var aaa = "${repair.personalnominee}";
				if(aaa==data.data[i].repairname){
					$("#personalnominee").append( "<option  selected='selected' value='${repair.personalnominee}'>${repair.personalnominee}</option>");
				}else{
					$("#personalnominee").append( "<option value='" + data.data[i].repairname + "' >"+ data.data[i].repairname + "</option>");
				}
			});
		},
		error : function() {
			alert("服务器异常!");
		}
	});
		
}



function getClass(){
		var repairdept = $("#repairdept").val();
		$.ajax( {
		url : "RepairAction!isChange.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {
			"repairdepartment" : repairdept
		},
		success : function(data) {
			$("#category").empty();//清空
			$.each(data.data, function(i) {
				var aaa = "${repair.category}";
				if(data.data[i].category==aaa){
				$("#category").append( "<option  selected='selected' value='${repair.category}'>${repair.category}</option>");
				}else{
				$("#category").append( "<option value='" + data.data[i].category + "' >"+ data.data[i].category + "</option>");
				}
				
			});
			getUser();
		},
		error : function() {
			alert("服务器异常!");
		}
	});
		
}
		 

</script>
	</body>
</html>
