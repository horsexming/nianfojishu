<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=basePath%>javascript/calendar/css/mainstructure.css">
		<link rel="stylesheet" href="<%=basePath%>javascript/calendar/css/maincontent.css">
		<script type="text/javascript"src="<%=basePath%>javascript/calendar/js/jquery-ui-1.8.6.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>javascript/calendar/js/jquery-ui-timepicker-addon.js"></script>
		<link rel="stylesheet" href="<%=basePath%>javascript/calendar/css/redmond/jquery-ui-1.8.1.custom.css">
		<script type="text/javascript" src="<%=basePath%>javascript/zTree/js/jquery.ztree.excheck-3.5.js"></script>
		<!-- 表单验证CSS -->
		<script	src="<%=basePath%>javascript/calendar/js/formValidator/js/jquery.validationEngine.js" type="text/javascript"></script>
		<script	src="<%=basePath%>javascript/calendar/js/formValidator/js/jquery.validationEngine-en.js" type="text/javascript"></script>
		<link rel="stylesheet"	href="<%=basePath%>javascript/calendar/js/formValidator/css/validationEngine.jquery.css"
			type="text/css" media="screen" charset="utf-8" />

		<!-- FullCalender -->

		<link rel='stylesheet' type='text/css'	href='<%=basePath%>javascript/calendar/fullcalendar/fullcalendar.css' />
		<link	href='<%=basePath%>javascript/calendar/fullcalendar/fullcalendar.print.css'rel='stylesheet' media='print' />
		<script type='text/javascript'	src='<%=basePath%>javascript/calendar/fullcalendar/fullcalendar.min.js'></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobiscroll.core-2.5.2.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script>  
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
		<style type="text/css">
			.ellipsis{
				margin: 0;
				padding: 0;	
			}
			#showUsers{
				padding-left: 20px;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<ul class="nav nav-tabs">
					<li class="col-md-6 active">
						<a href="#banci" data-toggle="tab">
							<p class="ellipsis">批量排班</p>
							<p class="ellipsis">按照班次排班</p>
						</a>
					</li>
					<li class="col-md-6">
						<a href="#self" data-toggle="tab" onclick="loadDept()">
							<p class="ellipsis">个人排班</p>
							<p class="ellipsis">对个别员工排班</p>
						</a>
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="tab-content">
						<!-- 班次排班 -->
						<div class="tab-pane fade in active" id="banci">
							<ul class="nav nav-tabs">
								<s:iterator value="banciList" id="bl" status="ps">
									<li ><a href="#" onclick="loadCalendar(${bl.id})" id="banci_${bl.id}">${bl.name }</a></li>
								</s:iterator>
							</ul>
							<div class="row col-md-12" id="rili">
							</div>
						</div>
						<!-- 个人排班 -->
						<div class="tab-pane fade" id="self">
							<div class="row">
								<div class="col-md-12 panel panel-primary ">
									<div class="panel-heading">
										<h3 class="panel-title">
											筛选员工
											<span style="float: right;font-size: 12px;cursor: pointer;" onclick="closeFiltrate(this);">关闭</span>
										</h3>
									</div>
									<div class="panel-body row"  id="filtrate">
										<div class=" col-md-4 row">
											<div class="form-group">
					                        	<input type="text" id="searchDeptInput" class="form-control" 
														  placeholder="搜索部门" onkeyup="searchDept()" />
											</div>
											<div class="form-group">
						                        <select id="userGroup" multiple  class="form-control" size="15" onchange="changeDept()">
													<option value="">
														选择部门
													</option>
													
												</select>
											</div>
										</div>
										<div class="col-md-4 row">
											<div class="form-group">
												<input type="text" placeholder="检索人员" onkeyup="changeDept()" 
														id="searchperson" class="form-control">
											</div>
											<div class="form-group">
						                        <select id="person" multiple  class="form-control" size="15" onchange="checkUsersList()">
													<option value="">
														选择人员
													</option>
												</select>
											</div>
										</div>
										<div class="col-md-4 row">
<!-- 											<div class="col-md-8 row  panel panel-default"> -->
<!-- 							                	<div class="panel-head" > -->
							                		
<!-- 											    </div> -->
<!-- 							                	<div class="panel-body" id="showUsers"> -->
<!-- 											    </div> -->
<!-- 							                </div> -->
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="row col-md-12" id="checkUsersList"></div>
									<div class="row col-md-12" id="showSelfRili"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row col-md-12" style="height: 50px;">
				
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			function loadCalendar(id){
				$("#rili").load("${pageContext.request.contextPath}/System/banCi/SchedultCalendar.jsp?id="+id);	
			}
			function loadDept(){
				$.ajax( {
					type : "post",
					url : "GzstoreAction_getdept.action",
					dataType : "json",
					success : function(data) {
						//填充部门信息
						$(data).each(function() {
							var html = "";
							if (this.dept == "${Users.dept}") {
								html = "<option selected='selected' value='"
										+ this.id + "'>" + this.dept + "</option>";
							} else {
								html = "<option value='" + this.id + "'>"
										+ this.dept + "</option>";
							}
							$(html).appendTo("#userGroup");
						});
						changeDept();
					}
				});
			}
			
			function changeDept(){
				var deptIds = $("#userGroup").val();
				var searchperson = $("#searchperson").val();
				if(deptIds==null){
					return ;
				}
				
				$("#person").empty();
				$("#person").append("<option>选择人员</option>");
				for(var i=0; i<deptIds.length;i++){
					var id = deptIds[i];
					$.ajax( {
						type : "post",
						url : "GzstoreAction_getusers.action",
						dataType : "json",
						data : {
							"id" : id,
							"variable":searchperson
						},
						async : false, 
						success : function(data) {
							//填充部门信息
							$(data).each(function() {
								if(this!=null&& this.name!=null && this.name!=""){
									var html = "<option value='" + this.id + "'>"
										+ this.name + "</option>";
									$(html).appendTo("#person");
								}
							});
				
						}
					});
				}
			}
			
			
			function closeFiltrate(obj){
				var text = $(obj).text();
				if(text=="关闭"){
					$(obj).text("打开");
					$("#filtrate").hide();
				}else{
					$(obj).text("关闭");
					$("#filtrate").show();
				}
			}
			
			function checkUsersList(){
				$("#checkUsersList").empty();
				var usersIds=[];
				$("#person option:selected").each(function(){
					var userId = $(this).val();
					var userName = $(this).text();
					usersIds.push(userId);
					$("#checkUsersList").append("<input type='button' class='btn btn-default' value='"+userName+"'>");
				});
				loadUsersCalendar(usersIds);
			}
			function loadUsersCalendar(usersIds){
				$("#showSelfRili").load("${pageContext.request.contextPath}/System/banCi/SchedultCalendar_self.jsp?usersId="+usersIds);
			}
			
			function searchDept(){
				var searchDeptInput = $("#searchDeptInput").val();
				$.ajax( {
					type : "post",
					url : "GzstoreAction_getdept.action",
					dataType : "json",
					data:{
						"variable":searchDeptInput
					},
					success : function(data) {
						$("#userGroup").empty();
						$("#userGroup").append("<option>搜索部门</option>");
						//填充部门信息
						$(data).each(function() {
							var html = "";
							if (this.dept == "${Users.dept}") {
								html = "<option selected='selected' value='"
										+ this.id + "'>" + this.dept + "</option>";
							} else {
								html = "<option value='" + this.id + "'>"
										+ this.dept + "</option>";
							}
							$(html).appendTo("#userGroup");
						});
						changeDept();
					}
				});
			}
		</script>
	</body>
</html>
