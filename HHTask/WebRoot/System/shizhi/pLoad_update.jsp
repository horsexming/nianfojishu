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
					<a
						href="productivityLoadAction_update.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改产能负荷系数<br/>
					（update productivity load score）
				</h3>
				<form action="productivityLoadAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
					<tr>
							<th align="right">
								工序
								<br />
								（process）
							</th>
							<td>
							<input type="hidden" name="cpage" 
								value="<s:property value="cpage"/>" />
							<input type="hidden" name="productivityLoad.id" 
								value="<s:property value="productivityLoad.id"/>" />
								<input type="text" name="productivityLoad.processName" readonly="readonly"
								value="<s:property value="productivityLoad.processName"/>" />
								
							</td>
						</tr>
					
						
						
						<tr>
							<th align="right">
								当月客户需求数量
							<br />
							（The number of customers needs on the month）
							</th>
							<td>
								<input type="text" name="productivityLoad.curCusNeedNum" id="curCusNeedNum" 
								value="<s:property value="productivityLoad.curCusNeedNum"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								当月实际入库数量
							<br />
							（The actual number of storage on the month）
							</th>
							<td>
								<input type="text" name="productivityLoad.curMonInputNum" id="curMonInputNum" 
								value="<s:property value="productivityLoad.curMonInputNum"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								客户需求期限
							<br />
							（Customer demand deadline）
							</th>
							<td>
								<input type="text" name="productivityLoad.cusDeadline" id="cusDeadline" 
								value="<s:property value="productivityLoad.cusDeadline"/>"/>
							</td>
						</tr>
						
						<tr>
							<th align="right">
							超工作日工作时限
							<br />
							（Ultra-day delivery of the work time limit）
							</th>
							<td>
								<input type="text" name="productivityLoad.workTimeLimits" id="workTimeLimits" 
								value="<s:property value="productivityLoad.workTimeLimits"/>"/>
								<select name="daytype">
								<option value="1">
								           不修改
								</option>
								 <option value="1.5">
								                平时
								 </option>
								  <option value="2">
								                双休
								 </option>
								  <option value="3">
								               法定假日
								 </option>
								</select>
							</td>
						</tr>
						
						
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
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
		<script type="text/javascript">
function validate() {
	var curCusNeedNum = document.getElementById("curCusNeedNum").value;
	var curMonInputNum = document.getElementById("curMonInputNum").value;
	var cusDeadline = document.getElementById("cusDeadline").value;
	var workTimeLimits = document.getElementById("workTimeLimits").value;
	if (curCusNeedNum == "") {
		alert("请输入当月客户需求数量!");
		return false;
	}else if(isNaN(curCusNeedNum)){
	  alert("当月客户需求数量栏请输入数字!");
		return false;
	}
	if (curMonInputNum == "") {
		alert("请输入当月实际入库数量!");
		return false;
	}else if(isNaN(curMonInputNum)){
	  alert("当月实际入库数量栏请输入数字!");
		return false;
	}
	if (cusDeadline == "") {
		alert("请输入客户需求期限!");
		return false;
	}else if(isNaN(cusDeadline)){
	  alert("客户需求期限栏请输入数字!");
		return false;
	}
	
	if (workTimeLimits == "") {
		alert("请输入超工作日工作时限!");
		return false;
	}else if(isNaN(workTimeLimits)){
	  alert("超工作日工作时限栏请输入数字!");
		return false;
	}
	
}
</script>
	</body>
</html>
