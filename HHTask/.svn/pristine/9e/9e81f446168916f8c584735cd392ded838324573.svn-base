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
			 <form action="zhaobiaoAction!quedinghuikuang.action" method="post"  theme="simple">
           <table border="0" style="margin-left: 0px;" class="t1" cellpadding="0" cellspacing="0">
    	<tr>
    		<td>名称：</td>
    		<td>
    			添加回款方式
    		</td>
    	</tr>
    	<tr>
    		<td>方式：</td>
    		<td>
    		
    			<s:iterator id="mf" value="#request.list"  status="pageIndex">
								  
					  <tr>
						 <td colspan="2">
						 <input type="radio" name="zhaobiaoXi.t11" value="${mf}">${mf}
					  </tr>
							         
							</s:iterator>
    			<input type="hidden" value="${zhaobiaoXi.id}" name="zhaobiaoXi.id" id="zhaobiaoXi.id">
    		</td>
    	</tr>
    </table>
    	<input type="hidden" id="ids" name="ids">
    	<input type="submit" value="保存">
    	<input type="button" value="返回" class="btn"  onclick="window.history.go(-1);">
    </form>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	 
	</SCRIPT>

</html>
