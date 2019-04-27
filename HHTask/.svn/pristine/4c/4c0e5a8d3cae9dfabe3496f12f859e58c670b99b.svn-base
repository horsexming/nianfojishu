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
</script>

	</head>
	<body>
		<form id="xform" action="GzstoreAction_addProcess.action?id=${id1}	"  method="post" onsubmit="return validate()">
			<input type="hidden" name="process_id"  value="${process_id}"  />
			<table class="table">
				<tr>
					<th colspan="2" align="center">
						<h3>
							添加工装信息
						</h3>
					</th>
				</tr>
				<tr>
					<th align="right">
						名称：
					</th>
					<td>
					<select name="gzstore.id">
					<s:iterator value="list"  id="gzbj" status="pageIndex">
						 <option value="${gzbj.id}">${gzbj.matetag}(${gzbj.number})(${gzbj.xingbie})</option>
					</s:iterator>
					</select>
					</td>
				</tr>
				<tr>
					<th align="right"></th>
					<td>
						<input type="submit" value="添加" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
		$(function(){
			var errorMessage='${errorMessage}';
			if(errorMessage!=""){
				alert(errorMessage);
				parent.location.reload(true);//刷新父页面
//				parent.newUrl();//调用其父页面的方法
			}
		});
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
