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
				</div>
			</div>
			
			<div align="center">
				<form action="proToolingAction!updateProTooling.action" method="post"
					style="">
					<input type="hidden" id="id" name="proTooling.id" value="${proTooling.id}"/> 
					<input type="hidden" id="proId" name="proTooling.proId" value="${proTooling.proId}"/> 
					<br>
					<table border="0" width="100%" class="table">
						<tr>
							<td align="right">
								工装名称:
							</td>
							<td>
								<input type="text" id="name" name="proTooling.name" value="${proTooling.name}"/>
							</td>
							<td align="right">
								工装号:
							</td>
							<td>
								<input type="text" id="numb" name="proTooling.numb" value="${proTooling.numb}"/>
							</td>
							<td align="right">
								金额:
							</td>
							<td>
								<input type="text" id="amount" name="proTooling.amount" value="${proTooling.amount}"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								规格:
							</td>
							<td>
								<input type="text" id="specification" name="proTooling.specification" value="${proTooling.specification}"/>
							</td>
							<td align="right">
								状态:
							</td>
							<td>
								<select id="status" name="proTooling.status">
									<option value="已购">已购</option>
									<option value="新购">新购</option>
								</select>
							</td>
							<td align="right">
								备注:
							</td>
							<td>
								<input type="text" id="explain" name="proTooling.explain" value="${proTooling.explain}"/>
							</td>	
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="提交"
									style="width: 100px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
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
	</body>
</html>
<script type="text/javascript">
$(function(){
	$("#status").find("option[value='${proTooling.status}']").attr("selected",true);
});
</script>
