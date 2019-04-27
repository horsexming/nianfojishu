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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<h2>导入<span id="ziti">外购</span>价格</h2>
			<form action="PriceAction!Pladdprice.action" method="post"
							enctype="multipart/form-data">
						<select name="price.produceType" onchange="changvalue(this)" style="width: 100px;">
							<option value="外购">外购</option>
							<option value="外委">外委</option>
						</select><br/>
						选择导入文件:
							<input type="file" name="addprice" >
							<a href="<%=basePath%>/upload/file/download/wgprice.xls" id="muban">导入模版下载</a>
							<a href="FileViewAction.action?FilePath=/upload/file/download/wgprice.xls&Refresh=true" id="muban2">/预览</a>
							<input type="submit" value="批量导入" id="sub">
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
	function changvalue(obj){
		if(obj!=null && obj.value!=""){
			if(obj.value == "外购"){
				$("#muban").attr("href","<%=basePath%>/upload/file/download/wgprice.xls");
				$("#muban2").attr("href","FileViewAction.action?FilePath=/upload/file/download/wgprice.xls&Refresh=true");
				$("#ziti").html("外购");
			}else if(obj.value == "外委"){
				$("#muban").attr("href","<%=basePath%>/upload/file/download/wwprice.xls");
				$("#muban2").attr("href","FileViewAction.action?FilePath=/upload/file/download/wwprice.xls&Refresh=true");
				$("#ziti").html("外委");
			}
		}
	}
	
	
	</SCRIPT>
	</body>
</html>
