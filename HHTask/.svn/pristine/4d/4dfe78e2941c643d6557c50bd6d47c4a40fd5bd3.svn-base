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
						href="<%=path%>/System/shizhi/skillscore_add.jsp"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					添加气密产品<br/>
					（add product）
				</h3>
				<form action="airtightLogAction_updateProduct.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								件号<br/>（part number）
							</th>
							<td> 
								<input type="hidden" name="airProduct.id"  value="<s:property value="airProduct.id"/>"/>
								<input type="hidden" name="airProduct.status"  value="<s:property value="airProduct.status"/>"/>
								<input type="text" name="airProduct.markId" id="markId" value="<s:property value="airProduct.markId"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								客户编号<br/>（customer number）：
							</th>
							<td>
								<input type="text" name="airProduct.customerNumber" id="customerNumber" value="<s:property value="airProduct.customerNumber"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								类型<br/>（type）：
							</th>
							<td>
								<input type="text" name="airProduct.type" id="type" value="<s:property value="airProduct.type"/>"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								是否需要其他标识<br/>（is need other tag）：
							</th>
							<td>
							<SELECT name="airProduct.isNeedOtherContext">
							<s:if test="airProduct.isNeedOtherContext=='yes'">
							<option value="yes">是</option>
							  <option value="no">否</option>
							</s:if>
							<s:else>
							<option value="no">否</option>
							<option value="yes">是</option>
							</s:else>
							  
							</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								测试时长/毫米<br/>（is need other tag/ms）：
							</th>
							<td>
								<input type="text" name="airProduct.testTime" id="testTime" value="<s:property value="airProduct.testTime"/>" onblur="mustBeNumber('testTime')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								泄漏量标准（L）<br/>(Leakage Standard)：
							</th>
							<td>
								<input type="text" name="airProduct.xielou" id="xielou" value="<s:property value="airProduct.xielou"/>" onkeyup="mustBeNumber('xielou')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								压力值标准（KPa）<br/>(Pressure Standard)：
							</th>
							<td>
								<input type="text" name="airProduct.yali" id="yali" value="<s:property value="airProduct.yali"/>" onkeyup="mustBeNumber('yali')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								开关IP：
							</th>
							<td>
								<input type="text" name="airProduct.kgIp" id="kgIp" value="<s:property value="airProduct.kgIp"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								传值IP：
							</th>
							<td>
								<input type="text" name="airProduct.czIp" id="czIp" value="<s:property value="airProduct.czIp"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								使用状态：
							</th>
							<td>
								<s:if test="airProduct.syStatus != '停止'">
									<input type="radio" name="airProduct.syStatus" id="syStatus"  value="使用" checked="checked"/>使用
									<input type="radio" name="airProduct.syStatus" id="syStatus"  value="停止" />停止
								</s:if>
								<s:else>
									<input type="radio" name="airProduct.syStatus" id="syStatus"  value="使用"/>使用
									<input type="radio" name="airProduct.syStatus" id="syStatus"  value="停止"  checked="checked"/>停止
								</s:else>
								
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
	var markId = document.getElementById("markId").value;
	var customerNumber = document.getElementById("customerNumber").value;
	var type = document.getElementById("type").value;
	var testTime = document.getElementById("testTime").value;
	if (markId== "") {
		alert("请输入件号!");
		return false;
	}
	if (customerNumber=="") {
		alert("请输入客户编号!");
		return false;
	}
	if (type=="") {
		alert("请输入类型!");
		return false;
	}
	if (testTime=="") {
		alert("请输入测试时长!");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
