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
			var self;
			function submit(){
				self.sync();
				$.ajax({
					type: "POST",
					url: "board_update.action",
					data: $('#myform').serialize(),
					dataType : "json",
					success: function(json){
						alert(json.message);
						if(json.success){
							var board=json.data;
							window.location = 'board_findBoardAllByparentId.action?board.parentId='+board.parentId;
						}
					}
				});

			}
			KindEditor.ready(function(K) {
				var editor1 = K.create('#descTa', {
					cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
					width : '510px',
					height: '200px',
					minWidth : 700,
					minHeight: 169,
					uploadJson : 'affixAction!upload.action',
            		allowFileManager : false,
					items : [ 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
					afterCreate : function() {
						self = this;
						K.ctrl(document, 13, function() {
							submit();
						});
						K.ctrl(self.edit.doc, 13, function() {
							submit();
						});
					}
				});
				prettyPrint();
			});
			
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
					<input type="hidden" name="board.id" value="${board.id}">
					<input type="hidden" id="parentId" name="board.parentId" value="${board.parentId}"/>
					<table class="table" style="width: 80%">
						<tr>
							<th colspan="4">修改update</th>
						</tr>
						<%--<tr>
							<td>发布标题</td>
							<td><input name="board.name" value="${board.name}"/></td>
							<td>屏幕名称</td>
							<td><select id="scrnId" name="board.scrnId" value="${board.scrnId}"></select></td>
						</tr>
						--%><tr>
							<td>发布内容<br/>(Publish content)</td>
							<td colspan="3"><textarea id="descTa" name="board.content" cols="20" rows="8"  style="width:700px;height:200px;visibility:hidden;">${board.content}</textarea></td>
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
