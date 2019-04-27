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
		
		<SCRIPT type="text/javascript">
			$(function(){
				
				
				$('#usercode').bind('blur',function(){
					$.ajax({
						type: "POST",
						url: "UsersAction!findNameByCode.action",
						data: "user.code=" + $('#usercode').val(),
						dataType:"json",
						success: function(msg){
							if(!msg.success){
								alert(msg.message);
							}
							$('#nameFont').html(msg.data);
							$('#username').val(msg.data);
						}
					});
				});
				$('#submitBtn').bind('click',function(){
					$.ajax({
						type: "POST",
						url: "WareHouseAuth!add.action",
						data: $('#myform').serialize(),
						dataType:"json",
						success: function(msg){
							alert(msg.message);
							$('#nameFont').html(msg.data);
						}
					});
				});
				
				
				function changeSelect(){
					var cbox = $('#myform [type=checkbox]');
					if($('#authSelect').val() == "出库"){
						for(var i = 0; i < cbox.length; i++){
							if(i%5==1){
								cbox[i].checked = true;
							} else {
								cbox[i].checked = false;
							}
						}
					} else if($('#authSelect').val() == "入库"){
						for(var i = 0; i < cbox.length; i++){
							if(i%5==0){
								cbox[i].checked = true;
							} else {
								cbox[i].checked = false;
							}
						}
					} else if($('#authSelect').val() == "查看"){
						for(var i = 0; i < cbox.length; i++){
							if(i%5==2){
								cbox[i].checked = true;
							} else {
								cbox[i].checked = false;
							}
						}
					}else if($('#authSelect').val() == "修改"){
						for(var i = 0; i < cbox.length; i++){
							if(i%5==3){
								cbox[i].checked = true;
							} else {
								cbox[i].checked = false;
							}
						}
					}else if($('#authSelect').val() == "删除"){
						for(var i = 0; i < cbox.length; i++){
							if(i%5==4){
								cbox[i].checked = true;
							} else {
								cbox[i].checked = false;
							}
						}
					}else if($('#authSelect').val() == "管理员"){
						for(var i = 0; i < cbox.length; i++){
							cbox[i].checked = true;
						}
					}
				}

				
				
				//---------
				$.ajax({
					type: "POST",
					url: "WareHouse_select.action",
					data: {},
					dataType:"json",
					success: function(msg){
						for(var i=0; i<msg.length; i++){
							$('#mytable tr:last-child').before('<tr><th>' + msg[i].name + '</th><td><input name="auth.auth" type="checkbox" value="'+ msg[i].code +'_in"/>入库</td>' +
							'<td><input name="auth.auth" type="checkbox" value="'+ msg[i].code +'_out"/>出库</td>' +
							'<td><input name="auth.auth" type="checkbox" value="'+ msg[i].code +'_view"/>查看</td>' +
							'<td><input name="auth.auth" type="checkbox" value="'+ msg[i].code +'_edit"/>修改</td>' +
							'<td><input name="auth.auth" type="checkbox" value="'+ msg[i].code +'_del"/>删除</td></tr>');
						}
						$('#authSelect').bind('change', function(){
							changeSelect();
						});
						changeSelect();
					}
				});
				
			});
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
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
				<form id="myform" action="">
					<table id="mytable" class="table" style="width: 70%">
						<tr>
							<th colspan="6">权限管理</th>
						</tr>
						<tr>
							<th>权限组</th>
							<td>
								<select name="auth.group" id="authSelect">
									<option>入库</option>
									<option>出库</option>
									<option>查看</option>
									<option>修改</option>
									<option>删除</option>
									<option>管理员</option>
								</select>
							</td>
							<th>工号</th>
							<td colspan="3">
								
								<input id="username" type="hidden" name="auth.username"/>
								<input id="usercode" name="auth.usercode"/>
								<font id="nameFont" color="red"></font>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input id="submitBtn" type="button" value="提交"/>
							</th>
						</tr>
					</table>
				
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
