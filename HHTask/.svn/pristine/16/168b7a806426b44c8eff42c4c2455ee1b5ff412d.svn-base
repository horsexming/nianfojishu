<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.util.Util"%>
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
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
			   <form action="zhaobiaoAction!chongxin.action" method="post"  theme="simple" enctype="multipart/form-data">
			   <%  String namee = request.getParameter("namee"); %>
			   <table class="table"><input type="hidden" id="zhaobiaoXi.id" name="zhaobiaoXi.id"  value="<%=namee %>"/>
				 
				    <tr><td>名称</td><td><input type="text" id="flowdetail.title" name="flowdetail.title" value="中标打回    二次评选"   /></td></tr> 
				    <tr><td>名称</td><td><input type="text" id="flowdetail.title" name="flowdetail.sqtype" value="二次评选"   /></td></tr> 
				 
				    <tr><td>审批人</td><td>
				    					<%  String name = Util.getLoginUser().getName(); %>
				    					<input type="text" id="flowdetail.sphj" name="flowdetail.spr" value="<%=name %>" readonly="readonly" /></td> </tr>   
				    <tr><td>名称</td><td><input type="radio" id="t1" name="flowdetail.sphj" value="Y"   />通过
				    					<input type="radio" id="t2" name="flowdetail.sphj" value="N"   />驳回	
				    				</td></tr> 
				   
				   
				    <tr><td>时间</td><td><input class="Wdate" type="text" id="flowdetail.sprq"
									name="flowdetail.sprq"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" /></td></tr>
				    <tr><td>附件</td><td>	<input type="file" name="shenhefujian" /></td></tr>      
					<tr><td><s:submit value="保存"/></td><td><input type="button" name="Submit2" value="取消"  class="right-buttons" onclick="window.history.go(-1);"/></td></tr>
			   </table>
		</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	  
	</SCRIPT>

</html>
