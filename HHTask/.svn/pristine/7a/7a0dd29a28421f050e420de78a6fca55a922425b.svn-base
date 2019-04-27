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
				<table class="table">
					<tr>
						<th colspan="13">
							排产列表
						</th>
					</tr>
					<tr align="center">
						<td>
							全选
							<input type="checkbox" id="checkAll" onchange="chageAllCheck()">
							序号
						</td>
						<td>
							件号
						</td>
						<td>
							业务件号
						</td>
						<td>
							名称
						</td>
						<td>
							产品类型
						</td>
						<td>
							排产数量
						</td>
						<td>
							已领料数量
						</td>
						<td>
							单位
						</td>
						<td>
							添加时间
						</td>
						<td>
							投产日期
						</td>
						<td>
							可领料日期
						</td>
						<td>
							实际领料日期
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:set name="jydclIndex" value="0"></s:set>
					<s:iterator value="procardBlList" id="pageProcardbl1"
						status="blStatus1">
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
								<input type="text" name="peiqiCount"
									id="peiqiCount<s:property value="#jydclIndex"/>"
									value="${pageProcardbl1.procard.thisAlertCount}"
									style="width: 40px; display: none;" disabled="disabled">
								<input type="checkbox" name="checkboxs"
									value="${pageProcardbl1.id}" onchange="chageNum()" />
								<s:set name="jydclIndex" value="#jydclIndex+1"></s:set>
							</s:if>
							${blStatus1.index+1}
						</td>
						<td>
							${pageProcardbl1.markId}
						</td>
						<td>
							${pageProcardbl1.ywMarkId}
						</td>
						<td>
							${pageProcardbl1.proName}
						</td>
						<td>
							${pageProcardbl1.procardStyle}
						</td>
						<td align="right">
							${pageProcardbl1.pcCount}
						</td>
						<td>
							${pageProcardbl1.ylCount}
						</td>
						<td>
							${pageProcardbl1.unit}
						</td>
						<td>
							${pageProcardbl1.addtime}
						</td>
						<td>
							${pageProcardbl1.ylingliaoTime}
						</td>
						<td style="background-color: yellow;">
							${pageProcardbl1.yjiagongTime}
						</td>
						<td>
							${pageProcardbl1.rlingliaoTime}
						</td>
						<td>
							<s:if test="#pageProcardbl1.lingliaoStatus=='yes'">
								<a target="ll${pageProcardbl1.rootId}" href="procardBlAction_findProcardllDetailbyRootId.action?id=${pageProcardbl1.procardId}&rootId=${pageProcardbl1.rootId}">前往领料</a>
							</s:if>
							<s:else>
								<font color="red">未到领料时间</font>
							</s:else>
						</td>
						</tr>
					</s:iterator>
					</div>
					</div>
					<%@include file="/util/foot.jsp"%>
					<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
					<SCRIPT type="text/javascript">
</SCRIPT>
	</body>
</html>
