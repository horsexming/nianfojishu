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
<style type="text/css">
.bootstrap-frm {
margin-left:auto;
margin-right:auto;
max-width: 500px;
background: #FFF;
padding: 20px 30px 20px 30px;
font: 12px "Microsoft YaHei", Helvetica, Arial, sans-serif;
color: #888;
text-shadow: 1px 1px 1px #FFF;
border:1px solid #DDD;
border-radius: 5px;
-webkit-border-radius: 5px;
-moz-border-radius: 5px;
}
.bootstrap-frm h1 {
font: 25px "Helvetica Neue", Helvetica, Arial, sans-serif;
padding: 0px 0px 10px 40px;
display: block;
border-bottom: 1px solid #DADADA;
margin: -10px -30px 30px -30px;
color: #888;
}
.bootstrap-frm h1>span {
display: block;
font-size: 11px;
}
.bootstrap-frm label {
display: block;
margin: 0px 0px 5px;
}
.bootstrap-frm label>span {
float: left;
width: 20%;
text-align: right;
padding-right: 10px;
margin-top: 10px;
color: #333;
font-family: "Microsoft YaHei", Helvetica, Arial, sans-serif;
font-size:15px;
<%--font-weight: bold;--%>
}
.bootstrap-frm input[type="text"], .bootstrap-frm input[type="email"], .bootstrap-frm textarea, .bootstrap-frm select{
border: 1px solid #CCC;
color: #888;
height: 40px;
line-height:15px;
margin-bottom: 16px;
margin-right: 6px;
margin-top: 2px;
outline: 0 none;
padding: 5px 0px 5px 5px;
width: 70%;
border-radius: 4px;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
}
<%--.bootstrap-frm select {--%>
<%--background: #FFF url('down-arrow.png') no-repeat right;--%>
<%--background: #FFF url('down-arrow.png') no-repeat right;--%>
<%--appearance:none;--%>
<%---webkit-appearance:none;--%>
<%---moz-appearance: none;--%>
<%--text-indent: 0.01px;--%>
<%--text-overflow: '';--%>
<%--width: 70%;--%>
<%--height: 35px;--%>
<%--line-height:15px;--%>
<%--}--%>
.bootstrap-frm textarea{
height:100px;
padding: 5px 0px 0px 5px;
width: 70%;
}
.bootstrap-frm .button {
background: #FFF;
border: 1px solid #CCC;
padding: 10px 25px 10px 25px;
color: #333;
border-radius: 4px;
}
.bootstrap-frm .button:hover {
color: #333;
background-color: #EBEBEB;
border-color: #ADADAD;
}

		</style>
</head>
<body>
<form action="QuestionAction_add.action" method="post" class="bootstrap-frm">
<h1>提交问题
<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请完整填完下面信息</span>
</h1>
<label>
<span>标题:</span>
<input id="name" type="text" name="question.title" value="${question.title}"/>
</label>
<label>
<span>类型 :</span>
<input id="type" type="text" name="question.type" value="${question.type}"/>
</label>
<label>
<span>问题详情:</span>
<textarea id="detail" name="question.detail">${question.detail}</textarea>
</label>
<label>
<span>&nbsp;</span>
<input type="submit" class="button" value="提交" />
</label>
</form>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
	
	</SCRIPT>
</body>
</html>
