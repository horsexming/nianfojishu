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
	<table class="table">
	<tr>
					<th align="center">
						<h3>
							量具信息
						</h3>
					</th>
				</tr>
				<tr>
					<td  align="center">
				<form method="post" action="processGzstoreAction_findALLmMeasList.action">
						<b>本厂编号:</b>&nbsp;<input type="text" name="measuring.measuring_no" value="${measuring.measuring_no}">
						<input type="hidden" name="id" value="${id}">
						<b>名称:</b>&nbsp;<input type="text" name="measuring.matetag" value="${measuring.matetag}">
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询">
				</form>
					</td>
				</tr>
	</table>
		<form id="xform"
			action="processGzstoreAction_savePAM.action"
			method="post" onsubmit="return validate()">
			<input type="hidden" name="id" value="${id}" />
			<table class="table">
				
				<s:iterator value="measuringList" id="pageMeasuring" status="pageIndex">
				<s:if test="#pageIndex.index%3==0">
				 <tr>
				</s:if>
				  <td><input type="checkbox" name="ids" onchange="chageNum()" value="${pageMeasuring.id}"> ${pageMeasuring.measuring_no}、(${pageMeasuring.matetag})</td>
				<s:if test="(#pageIndex.index+1)%3==0">
				</tr>
				</s:if>
				</s:iterator>
				<tr>
					<td colspan="4" align="center">
						<input type="checkbox" id="checkAll" onchange="chageAllCheck()">全选<input type="submit" value="绑定" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage == "true") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
		//				parent.newUrl();//调用其父页面的方法
	}
});
function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("ids");
	var peopleLabel = document.getElementById("peopleLabel");
	var peopleLabel2 = document.getElementById("peopleLabel2");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
		peopleLabel.innerHTML = checkboxs.length;
		peopleLabel2.innerHTML = checkboxs.length;
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
		peopleLabel.innerHTML = 0;
		peopleLabel2.innerHTML = 0;
	}

}

</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
