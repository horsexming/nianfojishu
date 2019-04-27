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
			function submit(){
				$.ajax({
					type: "POST",
					url: "board_add.action",
					data: $('#myform').serialize(),
					dataType : "json",
					success: function(json){
						alert(json.message);
						if(json.success){
							var board=json.data;
							window.location = 'board_findTitleAllByscrnId.action?board.scrnId='+board.scrnId;
						}
					}
				});

			}
			$(function(){
				$('#submitBtn').bind('click', function(){
					submit();
				});
			});
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
						style="color: #ffffff">刷新<br/>(Refresh)</a>
				</div>
			</div>
			
			<div align="center">
				<form id="myform">
					<table class="table" style="width: 80%">
						<tr>
							<th colspan="4">添加add</th>
						</tr>
						<tr>
							<td>发布标题<br/>(Released title)</td>
							<td><input  name="board.name"/></td>
							<td>屏幕名称<br/>(Screen Name)</td>
							<td><select id="scrnId" name="board.scrnId"></select></td>
						</tr>
						<tr>
							<th colspan="4"><input id="submitBtn" type="button" value="提交submit"/></th>
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
<script type="text/javascript">
$(function(){
	$.ajax( {
		url : "scrnAction!findAllScrnForSelect.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		async:false,
		data : {},
		success : function(data) {
			var scrnList=data.data;
			$("#scrnId").empty();//清空
			$("<option value=''>请选择</option>").appendTo("#scrnId");
			$(scrnList).each(function() {
				$("<option value='"+this.id+"'>"+this.name+"</option>").appendTo("#scrnId");
			});
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	$("#scrnId").find("option[value='${board.scrnId}']").attr("selected",true);
});
</script>
