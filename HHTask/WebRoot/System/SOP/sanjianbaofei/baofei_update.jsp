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
	<center>
		 <form action="caoZuoAction!updatebaofei.action" method="post"  theme="simple">
			   <table  border="1px" width="50%">
			   <tr><tH colspan="2">添加报废品报废原因</tH></tr>
			    <tr></tr><tr></tr>
				   <tr><th align="right">报废类型</th>
				  
				   <td>
				   <input type="hidden" id="baofei.id" name="baofei.id" value="${baofei.id}" />
				   	<select name="baofei.leixing" id="baofei.leixing" >
				   			<option value="${baofei.leixing}">${baofei.leixing}</option>
    						<option value="设备">设备</option>
                       		<option value="人员">人员</option>
							<option value="供应商">供应商</option>
				          </select>
				</td></tr>
				     <tr><th align="right">报废原因</th>
				   <td>
				   
				   
				   <input type="text" id="baofei.yuanyin" name="baofei.yuanyin"  value="${baofei.yuanyin}"/></td></tr>
				   
				   <tr><td colspan="2" align="center">
			  <input type="submit" value="保存" class="input" />
								<input type="button" name="Submit2" value="取消" class="input"
									class="right-buttons" onclick="window.history.go(-1);" /></td></tr>
			   </table>
		</center>
	</body>
		<script type="text/javascript">
	$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	
	})
	
	</script>
</html>
