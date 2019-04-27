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
function submit() {
	self.sync();
	chexkbox1();
	$.ajax( {
		type : "POST",
		url : "screen_add.action",
		data : $('#myform').serialize(),
		dataType : "json",
		success : function(json) {
			alert(json.message);
			if (json.success) {
				window.location = 'screen_listPage';
			}
		}
	});

}
KindEditor
		.ready(function(K) {
			var editor1 = K
					.create(
							'#descTa',
							{
								cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
								width : '510px',
								height : '200px',
								minWidth : 507,
								minHeight : 169,
								items : [ 'fontname', 'fontsize', '|',
										'forecolor', 'hilitecolor', 'bold',
										'italic', 'underline', 'removeformat',
										'|', 'justifyleft', 'justifycenter',
										'justifyright', 'insertorderedlist',
										'insertunorderedlist', '|',
										'emoticons', 'image', 'link' ],
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
var gongwei;
var index = 0;
var n=1;
$(function() {
	$
			.ajax( {
				type : "POST",
				url : "gongxuAction!getWorkStation.action",
				data : {},
				dataType : "json",
				success : function(json) {
					gongwei = json;
					var selector = $('#mytable select:last');
					for ( var i = 0; i < json.length; i++) {
				$("#test").append(
						'<input type="checkbox" value="' + json[i][0]
								+ '" name="checkbox1"/>' + json[i][1]
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				 if(n%6==0){
					$("#test").append('<br/><br/>')
				}
				 n++;
			}
		}
			});

	$('#submitBtn').bind('click', function() {
		submit();
	});
});

<%--function addSelect() {--%>
<%--	var td = $('#mytable td:last');--%>
<%--	td--%>
<%--			.append('<select name="screen.gongweiList[' + index++ + '].id"></select><br />');--%>
<%--	var selector = $('select:last');--%>
<%--	for ( var i = 0; i < gongwei.length; i++) {--%>
<%--		$('select').append(--%>
<%--				'<option value="' + gongwei[i][0] + '">' + gongwei[i][1]--%>
<%--						+ '</option>');--%>
<%--	}--%>
<%--}--%>
<%----%>
<%--function removeSelect() {--%>
<%--	if (index == 1) {--%>
<%--		alert("只剩一条啦，不要删啦！");--%>
<%--		return;--%>
<%--	}--%>
<%--	var td = $('#mytable td:last');--%>
<%--	$('#mytable select:last').remove();--%>
<%--	$('#mytable br:last').remove();--%>
<%--	index--;--%>
<%--}--%>

function chexkbox1() {
	var chexboxs = document.getElementsByName("checkbox1");
for(var i = 0; i < chexboxs.length; i++){
 		if(chexboxs[i].checked == true){
 			$("#test").append('<input type="hidden" value="'+chexboxs[i].value +'" name="screen.gongweiList['+ index++ +'].id">');
 		}
 	}
}
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新<br />(Refresh)</a>
				</div>
			</div>

			<div align="center">
				<form id="myform">
					<table id="mytable" class="table" style="width: 60%">
						<tr>
							<th colspan="2">
								添加电子屏幕
								<br />
								(Adding electronic screen)
							</th>
						</tr>
						<tr>
							<th>
								屏幕名称
								<br />
								(Screen Name)
							</th>
							<td>
								<input name="screen.name" />
							</td>
						</tr>
						<tr>
							<th>
								视频地址
								<br />
								(Video address)
							</th>
							<td>
								<input name="screen.screenUrl" />
							</td>
						</tr>
						<tr>
							<th>
								屏幕简介
								<br />
								(Screen Introduction)
							</th>
							<td>
								<textarea name="screen.description" rows="3" cols="15"></textarea>
							</td>
						</tr>
						<tr>
							<th>
								屏幕公告
								<br />
								(Screen Bulletin)
							</th>
							<td>
								<textarea id="descTa" name="screen.desc" cols="20" rows="8"
									style="width: 700px; height: 200px; visibility: hidden;"></textarea>
							</td>
						</tr>
						<tr>
							<th>
								绑定工位
								<br />
								(Binding station)
							</th>
							<td id="test">
								<%--								<select name="screen.gongweiList[0].id"></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
								<%--								<input type="button" onclick="addSelect();" value="追加add">--%>
								<%--								<input type="button" onclick="removeSelect();" value="删除delete"> <br/>--%>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<input id="submitBtn" type="button" value="确定submit" />
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
