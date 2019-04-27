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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="ProcardAction!wwtzwgj.action" method="post">
					<input type="hidden" value="${id}" name="id">
					<table class="table">
						<tr>
					<th>序号</th>
					<th><input type="checkbox" id="checkAll" onchange="chageAllCheck()"></th>
					<th >件号
					</th>
					<th>批次</th>
					<th>规格</th>
					<th>图号</th>
					<th>供料属性</th>
					<th >名称</th>
					<th>数量</th>
					</tr>
					<s:iterator value="procardList" id="pageProcard" status="pstatus">
				<s:if test="#pstatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" style="height: 26px;"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" style="height: 26px;"
								onmouseout="outBgcolor(this,'')">
						</s:else>
					<td>${pstatus.index+1}</td>
				 <td>
					 	<input type="checkbox" name="selected" value="${pageProcard.id}"
									onchange="chageNum()" />
				 </td>
				 	<td  align="left">${pageProcard.markId}</td>
					<td  align="left">${pageProcardselfCard}</td>
					<td align="left">${pageProcard.specification}</td>
					<td align="left">${pageProcard.tuhao}</td>
					<td align="left">${pageProcard.kgliao}</td>
					<td  align="left" >${pageProcard.proName}</td>
					<td align="right">${pageProcard.filnalCount}</td>
				</tr>
				</s:iterator>
				<tr >
					<th align="center" colspan="9">
						<input id="htr2" type="submit"  value="确认" style="width: 80px;height: 40px;" onclick="todisabled(this)">
					</th>
				</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("selected");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("selected");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
</SCRIPT>
	</body>
</html>
