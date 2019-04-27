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

				<form action="RepairAction!upremarkRepair.action?text=1" method="post">
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
								<input id="dept_ment" type="text" disabled="disabled" type="hidden"
									name="repair.department" value="${repair.department}" />
							</td>
						</tr>
						<tr>
							<th>
								报修物品
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
								<s:if test='test=="2"'>
									<input  type="text" disabled="disabled"
										value="${repair.personalnominee}">
								</s:if>
								<s:else>
									<select id="personalnominee1" name="repair.personalnominee">
										<option selected="selected" value="${repair.personalnominee}">
											${repair.personalnominee}
										</option>
									</select>

								</s:else>
							</td>
						</tr>
						<tr>
							<th>
								报修时间
							</th>

							<td>
								<input type="text" disabled="disabled" type="hidden"
									name="repair.repairtime" value="${repair.repairtime}" />
							</td>
							<s:if test='repair.status!="待确定" && repair.status!="待指派"'>
								<th>

								</th>

								<td>

								</td>
							</s:if>
						</tr>
						<tr>
							<td>
								报修故障:
							</td>
							<td>

								<input type="text" disabled="disabled" type="hidden"
									style="width: 250px; height: 80px;" name="repair.repairfailed"
									value="${repair.repairfailed}" />
							</td>
							<s:if test='repair.status!="待确定" && repair.status!="待指派"'>
								<td align="right">
									修复描述:
								</td>
								<td>
									<input type="text" style="width: 250px; height: 80px;"
										value="${repair.repairfeedback}" name="repair.repairfeedback" />
								</td>
								<td align="right">
									是否关联部门:
								</td>
								<td>
									<select id="selectdept">
									<option value="是" >是</option>
									<option value="否"  selected="selected">否</option>
									</select>
								</td>
							</s:if>
						</tr>
						</table>
						<!-- ------------------------------------- -->
						<div id="addProductPrice" >
						<table border="1" width="100%" class="table">
							<tr>
								<td colspan="20" align="center"
									style="font-family: 微软雅黑; font-weight: bold;">
									添加报修信息
								</td>
							</tr>
							<tr>
								<th>
									工号
								</th>
								<td>
									<input name="repair2.jobnumber" value="${Users.code}" />
								</td>

								<th>
									姓名
								</th>
								<td>
									<input name="repair2.name" value="${Users.name}" />

								</td>
								<th>
									部门
								</th>
								<td>

									<input name="repair2.department" value="${Users.dept}" />
								</td>
							</tr>
							<tr>
							<th>
									维修部门
								</th>
								<td>
					<select id="repairdepartment"  name="repair2.repairdept" >
							<option selected="selected" value="">
								选择部门
							</option>
							<s:iterator id="cu" value="maps">
								<option value="${cu.ta_dept}">
									${cu.ta_dept}
								</option>
							</s:iterator>
						</select>
								</td>
								<th>
									报修类别
								</th>
								<td>
								<select id="category" name="repair2.category" style="width: 160px;" >
									<option></option>
								</select>
								</td>
								<th>
									修理人
								</th>
								<td>
									<select id="personalnominee" name="repair2.personalnominee">
										<option></option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									报修物品
								</th>
								<td>
									<input type="text" name="repair2.devicename" />
								</td>
								<th>
									报修故障:
								</th>
								<td>
									<input type="text" style="width: 250px; height: 80px;" name="repair2.repairfailed" />
								</td>
							</tr>
							
<!--							<tr>-->
<!--							<td colspan="6" align="center">-->
<!--								<input type="submit" value="修改"-->
<!--									style="width: 100px; height: 50px;">-->
<!--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--								<input type="reset" value="重置"-->
<!--									style="width: 100px; height: 50px;">-->
<!--							</td>-->
<!--						</tr>-->
						</table>
				</div>
				<input type="submit" id="isok" value="修改"
									style="width: 100px; height: 50px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 100px; height: 50px;">
						<!-- ----------------------------------------- -->
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
	$("#isok").click(function(){
		var n = $("#repairdepartment").val();
		var selectdept = $("#selectdept").val();
			if(selectdept=='是'){
				if(n==null||n==""){
				alert("维修部门不能为空!");
				return false;
				}
			}
	});
	
		$("#addProductPrice").hide();
		getClass();
		getUser();
	$("#selectdept").change(function(){
		if($("#selectdept").val()=='是'){
			$("#addProductPrice").show();
		}else{
			$("#addProductPrice").hide();
		}
	});
	
	$("#repairdepartment").change(function(){
				getClass();
			});
				//点击报修类别时触发的事件
			$("#category").change(function(){
				getUser();//调用点击报修类别时触发的事件
			});
		
			
	var category1 = "${repair.category}";
	var dept_ment = "${repair.department}";
				if(dept_ment==null||dept_ment==""){
					dept_ment = $("#repairdepartment").val();
				}
	$.ajax( {
				url : "RepairAction!isChange1.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"category" : category1,
					"repairdepartment":dept_ment
				},
				success : function(data) {
					$("#personalnominee1").empty();//清空
				var aaa = "${repair.personalnominee}";
				//			$("#personalnominee1").append( "<option selected='selected' value='${repair.personalnominee}'>${repair.personalnominee}</option>");
				$.each(
								data.data,
								function(i) {
									if (aaa == data.data[i].repairname) {
										$("#personalnominee1").append("<option selected='selected' value='${repair.personalnominee}'>${repair.personalnominee}</option>");
									} else {
										$("#personalnominee1").append(
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

/////////////////////////////////////////////	
function getClass(){
			var repairdepartment = $("#repairdepartment").val();
			$.ajax( {
				url : "RepairAction!isChange.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"repairdepartment" : repairdepartment
				},
				success : function(data) {
				$("#category").empty();//清空
					$.each(data.data,function(i){
						$("#category").append("<option value='" + data.data[i].category+ "' >"+ data.data[i].category +"</option>");
					});
					getUser();
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
		
		//点击报修类别时触发的事件
		function getUser(){
			//var category1 = "${repair.category}";
			//var dept_ment = "${repair.department}";
			var category1 = $("#category").val();	 
			var dept_ment=$("#repairdepartment").val();
				$.ajax( {
				url : "RepairAction!isChange1.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"category" : category1,
					"repairdepartment":dept_ment
				},
				success : function(data) {
				$("#personalnominee").empty();//清空
				$("#personalnominee").append("<option value='' ></option>");
				$.each(data.data,function(i){
					if(data.data[i].repairname!=""){
					$("#personalnominee").append("<option value='" + data.data[i].repairname+ "' >"+ data.data[i].repairname +"</option>");
					}else{
						$("#personalnominee").append("<option value=''>请选修理人</option>");
					}
				});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}

/////////////////////////////////////////////
</script>
	</body>
</html>
