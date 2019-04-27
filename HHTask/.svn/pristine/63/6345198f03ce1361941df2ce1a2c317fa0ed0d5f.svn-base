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
<script type="text/javascript">
	$(function(){
		$('#allCheckBox').bind('click', function(){
			if($('#allCheckBox').attr('checked')) {
				$('#mytable input[name="ids"]').attr('checked',$('#allCheckBox').attr('checked'));	
			} else {
				$('#mytable input[name="ids"]').removeAttr('checked');
			}
		});
	})
</script>
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
			<form action="tclaimsRecord_handle.action" method="post" enctype="multipart/form-data">
				<table id="mytable" width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center"> <input id="allCheckBox" type="checkbox" /> </th>
						<th align="center">生产日期</th>
						<th align="center">件号</th>
						<th align="center">名称</th>
						<th align="center">数量</th>
						<th align="center">故障描述</th>
						<th align="center">原因分析</th>
						<th align="center">状态</th>
					</tr>
					<s:iterator value="tclaimsRecords" id="r">
						<tr onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
							<td align="center">
								<s:if test="'未整改'.equals(#r.status)">
									<input name="ids" type="checkbox" value="${r.id}" />
								</s:if><s:else>
									<input disabled="disabled" type="checkbox" />
								</s:else>
							</td>
							<td align="center">${r.productionDate}</td>
							<td align="center">${r.partNumber}</td>
							<td align="center">${r.name}</td>
							<td align="center">${r.quantity}</td>
							<td align="center">${r.description}</td>
							<td align="center">${r.reason}</td>
							<td align="center">
								<s:if test="'未整改'.equals(#r.status)">
									<font color="red">未整改</font>
								</s:if><s:else>
									<font color="green">${r.status}</font>
								</s:else>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td align="center" colspan="3">整改处理</td>
						<td align="center" colspan="5">
							<textarea id="mytextarea" name="tclaimsRecord.handle" rows="5" cols="40"></textarea>
							<input type="file" name="attachment" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="8">
							<input type="submit" value="确定" />
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
