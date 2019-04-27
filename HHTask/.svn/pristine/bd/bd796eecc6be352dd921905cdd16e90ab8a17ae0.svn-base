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
				<form action="procardBlAction_findProcardForbeiliao.action" method="post">
				<table class="table">
				<tr>
					<td>全选<input type="checkbox" id="checkAll" onchange="chageAllCheck()">序号</td>
					<td>件号</td>
					<td>批次</td>
					<td>名称</td>
					<td>类型</td>
					<td>预领料日期</td>
					<td>实际领料日期</td>
					<td>批次数量</td>
					<td>包工包料数量</td>
					<td>待配齐数量</td>
					<td>单位</td>
				</tr>
				<tr>
					<th align="center" colspan="10">可领<input type="submit"  value="确定" style="width: 80px;height: 40px;"></th>
				</tr>
				<s:set name="jydclIndex" value="0"></s:set>
				<s:iterator value="procardBlList" id="pageProcardbl1" status="blStatus1">
				<s:if test="#blStatus1.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
				 <td>
				 	<s:if test="#pageProcardbl1.procard.thisAlertCount>0">
				 	<input type="text" name="peiqiCount" id="peiqiCount<s:property value="#jydclIndex"/>" value="${pageProcardbl1.procard.thisAlertCount}" style="width:40px; display: none;" disabled="disabled">
				 	<input type="checkbox" name="checkboxs" value="${pageProcardbl1.id}"
								onchange="chageNum()" />
					<s:set name="jydclIndex" value="#jydclIndex+1"></s:set>
				 	</s:if>
				 	${blStatus1.index+1}
				 </td>
				 	<td>${pageProcardbl1.procard.markId}</td>
					<td>${pageProcardbl1.procard.selfCard}</td>
					<td>${pageProcardbl1.procard.proName}</td>
					<td>${pageProcardbl1.procard.procardStyle}</td>
					<td>${pageProcardbl1.ylingliaoTime}</td>
					<td>${pageProcardbl1.rlingliaoTime}</td>
					<td>${pageProcardbl1.procard.filnalCount}</td>
					<td>${pageProcardbl1.procard.wwblCount}</td>
					<td>${pageProcardbl1.procard.thisAlertCount}</td>
					<td>${pageProcardbl1.procard.unit}</td>
				</tr>
				</s:iterator>
				<tr id="htr2"  style="display: none;">
					<th align="center" colspan="13">
						<input type="submit"  value="确定" style="width: 80px;height: 40px;">
					</th>
				</tr>
				<tr>
					<th align="center" colspan="10">未到可领时间</th>
				</tr>
				<s:iterator value="procardBlList2" id="pageProcardbl2" status="blStatus2">
				<s:if test="#blStatus2.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
				 <td>
				 	${blStatus2.index+1}
				 </td>
				 	<td>${pageProcardbl2.procard.markId}</td>
					<td>${pageProcardbl2.procard.selfCard}</td>
					<td>${pageProcardbl2.procard.proName}</td>
					<td>${pageProcardbl2.procard.procardStyle}</td>
					<td>${pageProcardbl2.ylingliaoTime}</td>
					<td>${pageProcardbl2.rlingliaoTime}</td>
					<td>${pageProcardbl2.procard.filnalCount}</td>
					<td>${pageProcardbl2.procard.hascount}</td>
					<td>${pageProcardbl2.procard.unit}</td>
				</tr>
				</s:iterator>
				<tr>
					<th align="center" colspan="10">已配齐</th>
				</tr>
				<s:iterator value="procardBlList3" id="pageProcardbl3" status="blStatus3">
				<s:if test="#blStatus3.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
				 <td>
				 	${blStatus3.index+1}
				 </td>
				 	<td>${pageProcardbl3.procard.markId}</td>
					<td>${pageProcardbl3.procard.selfCard}</td>
					<td>${pageProcardbl3.procard.proName}</td>
					<td>${pageProcardbl3.procard.procardStyle}</td>
					<td>${pageProcardbl3.ylingliaoTime}</td>
					<td>${pageProcardbl3.rlingliaoTime}</td>
					<td>${pageProcardbl3.procard.filnalCount}</td>
					<td>${pageProcardbl3.procard.hascount}</td>
					<td>${pageProcardbl3.procard.unit}</td>
				</tr>
				</s:iterator>
<%--				<tr id="htr1" style="display: none;">--%>
<%--					<th align="center" colspan="10">--%>
<%--						刷卡:<input type="text"  style="width: 150px;">--%>
<%--					</th>--%>
<%--				</tr>--%>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
			$("#peiqiCount"+i).show();
			$("#peiqiCount"+i).removeAttr("disabled");
		}
<%--		$("#htr1").show();--%>
		$("#htr2").show();
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
			$("#peiqiCount"+i).hide()
			$("#peiqiCount"+i).attr("disabled","disabled");
		}
<%--		$("#htr1").hide();--%>
		$("#htr2").hide();
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
			$("#peiqiCount"+i).hide()
			$("#peiqiCount"+i).attr("disabled","disabled");
		} else {
			$("#peiqiCount"+i).show();
			$("#peiqiCount"+i).removeAttr("disabled");
			count++;
		}
	}
	if(count>0){
<%--		$("#htr1").show();--%>
		$("#htr2").show();
	}else{
<%--		$("#htr1").hide();--%>
		$("#htr2").hide();
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
</SCRIPT>
	</body>
</html>
