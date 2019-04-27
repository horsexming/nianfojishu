<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">	
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>
		<div align="center">
		<form id="reviewForm" action="QuestionAction_update.action" method="post" >
				<table class="table">
					<tr>
						<th colspan="6">
						<input type="hidden" name="question.id" value="${question.id}"/>
							添加问题
						</th>
					</tr>
					<tr>
					<th>问题标题</th>
					<td> ${question.title}</td>
					<th>区域类型</th>
					<td>${question.type}</td>
					</tr>
					<tr>
					<th>问题详情
					 </th>
					 <td colspan="3">
					 	${question.detail}
					 </td>
					</tr>
					<tr>
					<th>回答
					 </th>
					 <td colspan="3">
					 <div style="position: relative;left: 0px;top: 0px;">
					<p>
						
							<textarea id="content" name="question.answer" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;">${question.answer}</textarea>	
					</p>
					</div>
<%--					 <textarea rows="4" cols="100" name="question.answer" id="answer">${question.answer}</textarea>--%>
					 </td>
					</tr>
					<tr>
						<td align="center" colspan="8">
							<input type="submit" value="提交">
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
	<SCRIPT type="text/javascript">
	KindEditor.ready(function(K) {
		var editor1 = K.create('#content', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '550px',
			height: '100px',
			minWidth : 400,
			minHeight: 100,
			uploadJson : 'affixAction!upload.action',
            allowFileManager : false,
			items : [ 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
			afterCreate : function() {
				self = this;
			}
		});
	});
	var editor2;
	var html;
	var pId;
	$('#reviewButton').bind('click', function(){
		submit();
	});
	$("#container span[data-boardReview]").bind('click',function(){
		var parentId=$(this).attr("data-boardReview");
		if(pId){
			$("#"+pId+'boardReviewII').remove();
			$("span[data-boardReview='"+pId+"']").show();
			pId=parentId;
		}else{
			pId=parentId;
		}
		//var id=new Date().getMilliseconds();
		html='<div id="'+parentId+'boardReviewII"><textarea id="'+parentId+'content" name="boardReview.content" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;"></textarea>';
		html+='<p style="text-align: left;"><input type="button" style="background:#3CB371;cursor: pointer;" value="回复" data-boardReviewButton="'+parentId+'"/></p><div>';
		
		$(this).before($(html));
		editor2 = KindEditor.create('#'+parentId+'content', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '550px',
			height: '100px',
			minWidth : 400,
			minHeight: 100,
			uploadJson : 'affixAction!upload.action',
            allowFileManager : false,
			items : [  'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
			afterCreate : function() {
			}
		});
		$(this).hide();
		$("#container input[data-boardReviewButton='"+parentId+"']").bind('click',boardReviewButton);
	});
	

	</SCRIPT>
</body>
</html>
