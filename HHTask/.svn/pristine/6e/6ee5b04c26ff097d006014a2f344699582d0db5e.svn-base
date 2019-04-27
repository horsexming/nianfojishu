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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					选择产品数量明细
				</h3>
				<form action="saleBudgetAction!saveBudgetProduct.action"
					method="post" onsubmit="return validate()">
					<input type="hidden" name="id" value="${id}" />
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								件号
							</td>
							<td align="center">
								品名
							</td>
							<td align="center">
								型别
							</td>
							<td align="center">
								单价(不含税)
							</td>
							<td align="center">
								数量
							</td>
							
							<td>是否计入预算</td>
						</tr>
						<s:iterator value="list" id="pageList" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageList.partNumber}
							</td>
							<td>
								${pageList.name}
							</td>
							<td>
								${pageList.type}
							</td>
							<td>
								${pageList.bhsPrice}
								<input type="hidden" name="prices" value="${pageList.bhsPrice}" />
							</td>
							<td>
								<input type="hidden" name="priceSelect" value="${pageList.id}" />
								<input type="text" name="count" />
							</td>
								<td>
								<select name="isInclud" >
								<option value="yes">是</option>
								<option value="no">否</option>
								</select>
							</td>
							<td>
								<input type="button" value="删除"
									style="width: 60px; height: 30px;" onclick="del(this)" />
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="提交预算明细"
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
		<script type="text/javascript">
var ab = '${errorMessage}';
if (ab) {
	alert(ab);
}
function del(obj) {
	var tr = obj.parentNode.parentNode;
	var tab = tr.parentNode;
	if (confirm("确定要删除吗 ?")) {
		tab.removeChild(tr);
	}
}
function validate() {
	var num = document.getElementsByName("count");
	for ( var i = 0; i < num.length; i++) {
		if (num[i].value.length == 0 || num[i].value == "") {
			alert("数量不能为空!如果不需要则删除!");
			return false;
		}
	}
	return true;
}
</script>
	</body>
</html>
