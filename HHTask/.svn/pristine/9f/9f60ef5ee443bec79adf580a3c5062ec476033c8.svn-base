<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
						href="productUnPassKpAction_toupdate.action?productUnPassKp.id=${bonus.id}"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改开票未通过产品原因数量
				</h3>
					<s:if test="successMessage!=null">
					<h2>
				       <br/><font color="red">${successMessage}</font>
				    </h2>
					</s:if>
				
				<form action="productUnPassKpAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								订单编号<br/>（order number）
							</th>
							<td>
							<input type="hidden" name="cpage"  value="<s:property value="cpage"/>" />
							<input type="hidden" name="productUnPassKp.id"  value="<s:property value="productUnPassKp.id"/>" />
								<input type="text" value="<s:property value="productUnPassKp.odrerNumber"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								客户名称<br/>（customer Name）
							</th>
							<td>
								<input type="text"  value="<s:property value="productUnPassKp.cusName"/>"  />
							</td>
						</tr>
						<tr>
							<th align="right">
								件号<br/>（part number）
							</th>
							<td>
								<input type="text" value="<s:property value="productUnPassKp.markId"/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								总数量<br/>（total Count）
							</th>
							<td>
								<input type="text" id="totalCount"   value="<s:property value="productUnPassKp.totalCount"/>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								返修数量<br/>（rework Count）
							</th>
							<td>
								<input type="text"   name="productUnPassKp.reworkCount" value="<s:property value="productUnPassKp.reworkCount"/>"
								id="reworkCount"  onkeyup="mustBeNumber('reworkCount')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								重新生产数量<br/>（rebuild Count）
							</th>
							<td>
								<input type="text" name="productUnPassKp.rebuildCount" value="<s:property value="productUnPassKp.rebuildCount"/>"
								id="rebuildCount" onkeyup="mustBeNumber('rebuildCount')" />
							</td>
						</tr>
						<tr>
							<th align="right">
								废除订单数量<br/>（cut Count）
							</th>
							<td>
								<input type="text"   name="productUnPassKp.cutCount" value="<s:property value="productUnPassKp.cutCount"/>"
								id="cutCount"  onkeyup="mustBeNumber('cutCount')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								合格数量<br/>（pass Count）
							</th>
							<td>
								<input type="text"  name="productUnPassKp.okCount" value="<s:property value="productUnPassKp.okCount"/>"
								id="okCount"  onkeyup="mustBeNumber('okCount')"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
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
	var totalCount = document.getElementById("totalCount").value;
	var reworkCount = document.getElementById("reworkCount").value;
	var rebuildCount = document.getElementById("rebuildCount").value;
	var cutCount = document.getElementById("cutCount").value;
	var okCount = document.getElementById("okCount").value;
	var deleteCount=totalCount-reworkCount-rebuildCount-cutCount-okCount;
	if(deleteCount!=0){
		alert("总数量必须等于返修,重新生成,废单和合格品的和值!");
		return false;
	}

}
</script>
	</body>
</html>
