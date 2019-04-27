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
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="center" style="border: 1px solid #00000;">
			<!-- ****************************************初始填写**************************************** -->
					<s:if test='zhUser.gongzi==null'>
					  <form action="markIdAction!updateZhusers.action" method="post"  theme="simple">
					<table class="table" style="width: 40%">
					<tr>
						<th align="right">公司:</th>
						<td>
						<input type="hidden" name="zhUser.id" value="${zhUser.id}" />
					<input type="text" name="zhUser.name" value="${zhUser.name}" readonly="readonly"/>
						</td>
						</tr><tr>
						<th align="right">人均月平均工资:</th>
						<td><input type="text" name="zhUser.gongzi" value="${zhUser.gongzi}"/></td>
						</tr>
						<tr><th align="right"><font style="color: red;">*注:</font> </th>
						<td style="height: 50px">此处工资为人均月平均工资，请结合实际情况填写！此处月工资影响后续报价，修改时需上报申请！！！</td>
						</tr>
						 <tr><td align="center" colspan="2"><s:submit value="保存" cssClass="input"/>
					      <input type="button" name="Submit2" value="取消"  class="input" onclick="window.history.go(-1);"/></td></tr>
					</table>
					</form>
					</s:if>
					
					<!-- *********************************提交修改申请*********************************************** -->
						<s:if test='zhUser.gongzi!=null&&zhUser.gongziStatus==null'>	
						  <form action="markIdAction!shenqingongzi.action" method="post"  theme="simple">
					<table class="table" style="width: 40%">
					<tr>
						<th align="right">公司:</th>
						<td>
						<input type="hidden" name="zhUser.id" value="${zhUser.id}" />
					<input type="text" name="zhUser.name" value="${zhUser.name}" readonly="readonly"/>
						</td>
						</tr><tr>
						<th align="right">人均月平均工资:</th>
						<td><input type="text" name="zhUser.gongzi" value="${zhUser.gongzi}" readonly="readonly"/></td>
						</tr>
						<tr><th align="right"> <font style="color: red;">*注:</font></th>
						<td style="height: 50px">此处工资为人均月平均工资，请结合实际情况填写！此处人均月平均工资影响后续报价，修改时需上报申请！！！</td>
						</tr>
						 <tr><td align="center" colspan="2"><s:submit value="提交修改申请" cssClass="input"/>
					      <input type="button" name="Submit2" value="取消"  class="input" onclick="window.history.go(-1);"/></td></tr>
					</table>
					</form>
					
					</s:if>
				<!-- *************************************审核中用于查看******************************************* -->
								<s:if test='zhUser.gongziStatus=="审核中"'>	
					<table class="table" style="width: 40%">
					<tr>
						<th align="right">公司:</th>
						<td>
						<input type="hidden" name="zhUser.id" value="${zhUser.id}" />
					<input type="text" name="zhUser.name" value="${zhUser.name}" readonly="readonly"/>
						</td>
						</tr><tr>
						<th align="right">人均月平均工资:</th>
						<td><input type="text" name="zhUser.gongzi" value="${zhUser.gongzi}" readonly="readonly"/></td>
						</tr>
						<tr><th align="right"> <font style="color: red;">*注:</font></th>
						<td style="height: 50px">此处工资为人均月平均工资，请结合实际情况填写！此处人均月平均工资影响后续报价，修改时需上报申请！！！</td>
						</tr>
						 <tr><td align="center" colspan="2">申请审核中
					     </td></tr>
					</table>
						</s:if>
	<!-- ********************************审核同意用于修改工资************************************************ -->
						
								<s:if test='zhUser.gongziStatus=="同意"'>	
						  <form action="markIdAction!updateZhusergongzi.action" method="post"  theme="simple">
					<table class="table" style="width: 40%">
					<tr>
						<th align="right">公司:</th>
						<td>
						<input type="hidden" name="zhUser.id" value="${zhUser.id}" />
					<input type="text" name="zhUser.name" value="${zhUser.name}" readonly="readonly"/>
						</td>
						</tr><tr>
						<th align="right">人均月平均工资:</th>
						<td><input type="text" name="zhUser.gongzi" value="${zhUser.gongzi}" /></td>
						</tr>
						<tr><th align="right"> <font style="color: red;">*注:</font></th>
						<td style="height: 50px">此处工资为人均月平均工资，请结合实际情况填写！此处人均月平均工资影响后续报价，修改时需上报申请！！！</td>
						</tr>
						 <tr><td align="center" colspan="2"><s:submit value="修改" cssClass="input"/>
					      <input type="button" name="Submit2" value="取消"  class="input" onclick="window.history.go(-1);"/></td></tr>
					</table>
					</form>
						</s:if>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		
		</script>
	</body>
</html>
