<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<center>
			<form action="YusuantianbaobiaoAction!addBiaoDan.action?id=${yusuantianbaototal.id}"
			 method="post" onsubmit="return check()">
				<table class="table">
					<tr>
						<th colspan="4" align="center">添加 
						<span style="color: red">${yusuantianbaototal.xiangmumingda}</span>
						预算明细</th>
					</tr>
					<tr>
						<th align="right">
							项目名称或者代码：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.xiangmumingda"
								id="xiangmumingda"/>
						</td>
						<th align="right">
							固定资产：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.gudingzichan"
							id="gudingzichan"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							人力成本增加：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.renlichengbenzengjia"
							id="renlichengbenzengjia"/>
						</td>
						<th align="right">
							试验费用：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shiyanfeiyong"
							id="shiyanfeiyong"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							其他费用：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.qitafeiyong"
							id="qitafeiyong"/>
						</td>
						<th align="right">
							收益金额：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shouyijine"
							id="shouyijine"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							收益年限：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shouyinianxian"
							id="shouyinianxian"/>
						</td>
						<th align="right">
							预算类型：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.yusuanleixing"
							id="yusuanleixing"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							年度：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.niandu"
							value="${yusuantianbaototal.niandu}" readonly/>
						</td>
						<th align="right">
						
						</th>
						<td>
							
						</td>
					</tr>
					<tr>	
						<td colspan="4" align="center">	
							<input type="submit" value="确定"/>
							<input type="reset" value="重置 "/>
							
						</td>					
					</tr>
				</table>
			</form>			
		</center>
		<script type="text/javascript">
		 function check(){
		 	var testValue1=document.getElementById("xiangmumingda").value;
		 	if(testValue1==""){
 				alert("请填写项目名称或者代码!");
 				return false;
 			}
 			var testValue2=document.getElementById("gudingzichan").value;
		 	if(testValue2==""){
 				alert("请填写固定资产!");
 				return false;
 			}
 			var testValue3=document.getElementById("renlichengbenzengjia").value;
		 	if(testValue3==""){
 				alert("请填写人力成本增加!");
 				return false;
 			}
 			var testValue4=document.getElementById("shiyanfeiyong").value;
		 	if(testValue4==""){
 				alert("请填写试验费用!");
 				return false;
 			}
 			var testValue5=document.getElementById("qitafeiyong").value;
		 	if(testValue5==""){
 				alert("请填写其他费用!");
 				return false;
 			}
 			var testValue6=document.getElementById("shouyijine").value;
		 	if(testValue6==""){
 				alert("请填写收益金额!");
 				return false;
 			}
 			var testValue7=document.getElementById("shouyinianxian").value;
		 	if(testValue7==""){
 				alert("请填写收益年限!");
 				return false;
 			}
 			var testValue8=document.getElementById("yusuanleixing").value;
		 	if(testValue8==""){
 				alert("预算类型!");
 				return false;
 			}
 			return true;
		 }
</script>
	</body>
</html>