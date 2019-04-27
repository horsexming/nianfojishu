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
		<script type="text/javascript">
			$(function(){
				$.ajax({
					type: "POST",
					url: "ProjectAuth_get.action",
					dataType : "json",
					data: "auth.root.id=" + $('#authRootId').val() ,
					success: function(msg){
						var auths = msg.type.split(", ");
						for(var k in  auths){
							$("#authForm input").each(function(i){
								if($("#authForm input").get(i).value == auths[k]){
									$("#authForm input").get(i).checked = true;
								}
							});
						}
					}
				});
			})
		</script>
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
				<div style="color: red" id="successMsg"></div>
				<form id="authForm" >
					<input id="authRootId" type="hidden" name="auth.root.id" value="${auth.root.id}"/>
					<input type="hidden" name="auth.user.id" value="${auth.user.id}"/>
					<table class="table" style="width: 70%">
						<tr>
							<th colspan="2">请勾选要给 <span id="nameSpan" style="color:red">${auth.user.name} </span>分配的权限</th>
						</tr>
						<tr>
							<th>成员管理</th>
							<td>
								<input type="checkbox" name="auth.type" value="cygl">成员管理
								<input type="checkbox" name="auth.type" value="qxfp">权限分配<br/>
							</td>
						</tr>
						<tr>
							<th>项目建议书</th>
							<td id="xmjys" >
								<input type="checkbox" name="auth.type" value="xmjys_add">添加建议书
								<input type="checkbox" name="auth.type" value="xmjys_edit" />修改项目建议书
								<input type="checkbox" name="auth.type" value="xmjys_check">审核建议书
							</td>
						</tr>
						<tr>
							<th>项目报价单</th>
							<td id="xmbjd">
								<input type="checkbox" name="auth.type" value="xmbjd_add">开启项目报价单
								<input type="checkbox" name="auth.type" value="xmbjd_list_add">添加报价单和目录
								<input type="checkbox" name="auth.type" value="xmbjd_ycl_add">添加原材料
								<input type="checkbox" name="auth.type" value="xmbjd_wgwx_add">添加外购外协
								<input type="checkbox" name="auth.type" value="xmbjd_zjrg_add">添加直接人工<br/>
								<input type="checkbox" name="auth.type" value="xmbjd_sbzj_add">添加设备折旧
								<input type="checkbox" name="auth.type" value="xmbjd_mjcb_add">添加模具成本
								<input type="checkbox" name="auth.type" value="xmbjd_wlfy_add">物流费用
								<input type="checkbox" name="auth.type" value="xmbjd_lr_add">利润<br/>
								<input type="checkbox" name="auth.type" value="xmbjd_jsbj">计算报价
								<input type="checkbox" name="auth.type" value="xmbjd_over">结束报价单
							</td>
						</tr>
						<tr>
							<th>项目启动</th>
							<td id="xmqds">
								<input type="checkbox" name="auth.type" value="xmqds_xmqds">项目启动书
								<input type="checkbox" name="auth.type" value="xmqds_jsfa">技术方案
								<input type="checkbox" name="auth.type" value="xmqds_xmys">项目预算
								<input type="checkbox" name="auth.type" value="xmqds_hqjy">会签纪要<br/>
								<input type="checkbox" name="auth.type" value="xmqds_xmzrs">项目责任书
								<input type="checkbox" name="auth.type" value="xmqds_xmjd">项目进度
								<input type="checkbox" name="auth.type" value="xmqds_xmcy">项目成员
								<input type="checkbox" name="auth.type" value="xmqds_xmyq">项目要求
							</td>
						</tr>
						<tr>
							<th>项目跟踪</th>
							<td id="xmgz">
								<input type="checkbox" name="auth.type" value="xmgz_xmgz">项目跟踪
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input id="okBtn" type="button"  value="确定"/>
								<input type="reset" value="重置" />
							</td>
						</tr>
					</table>
					<div id="hiddenDiv" style="display: none"></div>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			$(function(){
				$('#okBtn').bind('click', function(){
					$('#hiddenDiv').html('');
					$('#xmjys input[type=checkbox]').each(function(){
						if(this.checked == true){
							$('#hiddenDiv').append('<input type="checkbox" name="auth.type" value="xmjys" checked="true">');
							return false;
						}
					})
					$.ajax({
						type: "POST",
						url: "ProjectAuth_add.action",
						data: $('#authForm').serialize(),
						dataType:'json',
						success: function(msg){
							$('#successMsg').html($('#nameSpan').html() + '的' + msg.message);
						}
					});
				});
			})
		</script>
	</body>
</html>
