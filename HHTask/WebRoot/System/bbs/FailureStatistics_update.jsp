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
<center>
				<form id="myForm" action="FailureStAction!UpdatefailureStatistics.action" method="post" enctype="multipart/form-data" onsubmit="return check();">
					<table>
					<tr><th colspan="2" align="center">修改不合格品</th></tr>
						<tr>
							<th align="right">
								日期:
							</th>
							<td align="left">
								<input class="Wdate" type="text" name="failureStatistics.dateTime"
								readonly="readonly"	onClick="WdatePicker({dateFmt:'yyyy年MM月dd日',skin:'whyGreen'})" value="${failureStatistics.dateTime}" />
							</td>
							<th align="right">
								零件号:
							</th>
							<td align="left">
								<input name="failureStatistics.markId" value="${failureStatistics.markId}" />
								<input name="failureStatistics.id" value="${failureStatistics.id}" type="hidden" />
							</td>
							<th align="right">
								客户:
							</th>
							<td align="left">
							 
								<select name="failureStatistics.client" style="width: 155px;">
								<option value="${failureStatistics.client}">
										${failureStatistics.client}
									</option>
									<option value="大众">
										大众
									</option>
									<option value="唐纳森">
										唐纳森
									</option>
									<option value="朗特">
										朗特
									</option>
									<option value="爱科">
										爱科
									</option>
									<option value="诺骋">
										诺骋
									</option>
								</select>
								
							</td>
						</tr>
						<tr>
							<th align="right">
								提交数量:
							</th>
							<td align="left">
								<input name="failureStatistics.submitCount" value="${failureStatistics.submitCount}" />
							</td>
							<th align="right">
								不合格品数量:
							</th>
							<td align="left">
								<input name="failureStatistics.failureCount" value="${failureStatistics.failureCount}" id="failureCount" readonly="readonly"/>
							</td>
							<th align="right">
								目标值PPM:
							</th>
							<td align="left">
								<input name="failureStatistics.targetPPM" value="${failureStatistics.targetPPM}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								焊接缺陷:
							</th>
							<td align="left">
								<input type="text" name="failureStatistics.weldingDefects" id="weldingDefects" value="${failureStatistics.weldingDefects}"  onkeyup="getshuliang();"/>
							</td>
							<th align="right">
								走向/尺寸:
							</th>
							<td align="left">
								<input name="failureStatistics.strikeSize" id="strikeSize" value="${failureStatistics.strikeSize}" onkeyup="getshuliang();" />
							</td>
							<th align="right">
								法兰平面度:
							</th>
							<td align="left">
								<input  name="failureStatistics.flangeFlatness" id="flangeFlatness" value="${failureStatistics.flangeFlatness}" onkeyup="getshuliang();" />
							</td>
						</tr>
						<tr>
							<th align="right">
								箭筒内异物:
							</th>
							<td align="left">
								<input name="failureStatistics.tfb" value="${failureStatistics.tfb}"  id="tfb"  onkeyup="getshuliang();"/>
							</td>
							<th align="right">
								气密:
							</th>
							<td align="left">
								<input name="failureStatistics.airtight" value="${failureStatistics.airtight}" id="airtight"  onkeyup="getshuliang();" />
							</td>
							<th align="right">
								外观:
							</th>
							<td align="left">
								<input name="failureStatistics.exterior" value="${failureStatistics.exterior}"  id="exterior" onkeyup="getshuliang();"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								其它(注明原因):
							</th>
							<td align="left">
								<input name="failureStatistics.other" value="${failureStatistics.other}"  id="other"  onkeyup="getshuliang();"/>
							</td>
							<th align="right">
								&nbsp;
							</th>
							<td align="left">
								&nbsp;
							</td>
							<th align="right">
								&nbsp;
							</th>
							<td align="left">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="修改" class="input" />
							</td>
						</tr>
					</table>
					
				</form>
			</div>
			<br>
		</div>
		
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function getshuliang(){
			var weldingDefects = document.getElementById("weldingDefects").value;
			
			var strikeSize = document.getElementById("strikeSize").value;
			var flangeFlatness = document.getElementById("flangeFlatness").value;
			var tfb = document.getElementById("tfb").value;
			var airtight = document.getElementById("airtight").value;
			var exterior = document.getElementById("exterior").value;
			var other = document.getElementById("other").value;
			var failureCount=parseFloat(weldingDefects)+parseFloat(strikeSize)+parseFloat(flangeFlatness)+parseFloat(tfb)+parseFloat(airtight)+parseFloat(exterior)+parseFloat(other);
			document.getElementById("failureCount").value=failureCount;
		}
		
		
		
					$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	})	
		
		</SCRIPT>
	</body>
</html>
