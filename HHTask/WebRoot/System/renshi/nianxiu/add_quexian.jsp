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
			   <form action="queXianAction!addqueXian.action" method="post"  theme="simple">
			   <table >
				    <tr><td>类型</td><td>
				    				<select name="queXian.leixing" id="queXian.leixing" style="width:200px;"  >
				    							<option value="人">人</option>
				                       			<option value="机">机</option>
				                       			<option value="法">法</option>
				                       			<option value="环">环</option>
				                       			<option value="料">料</option>
				                        </select>
				    
				    
				    </td> </tr>   
				    <tr><td>描述</td><td><input type="text" id="queXian.loc" name="queXian.loc" style="width:200px; height: 50px;"/></td></tr>
				    <tr><td>备注</td><td><input type="text" id="queXian.beizhu" name="queXian.beizhu"  style="width:200px; height: 50px;" /></td></tr>      
					 <tr><td><s:submit value="保存" cssClass="input"/></td><td><input type="button" name="Submit2" value="取消"  class="input" onclick="window.history.go(-1);"/></td></tr>
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
