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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<hr style="height:3px;border:none;border-top:3px double red;"/>
					<table class="table">
						<tr>
						<th>件号</th>
						<th>名称</th>
						<th>批次</th>
						<th>申请人</th>
						<th>申请时间</th>
						<th>备注</th>
						</tr>
						<tr>
						<td align="center">${pwwApply.markId}</td>
						<td align="center">${pwwApply.proName}</td>
						<td align="center">${pwwApply.selfCard}</td>
						<td align="center">${pwwApply.userName}</td>
						<td align="center">${pwwApply.addTime}</td>
						<td align="center">${pwwApply.remarks}</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<table class="table">
								<tr>
									<th>序号</th>
									<th>供应商</th>
									<th>件号</th>
									<th>零件名称</th>
									<th>版本</th>
									<th>版次</th>
									<th>批次</th>
									<th>工序号</th>
									<th>工序名称</th>
									<th>外委类型</th>
									<th>数量</th>
									<th>添加人</th>
									<th>添加时间</th>
								</tr>
									<s:iterator value="pwwApply.detailList" id="pagedetail" status="step1">
							<s:if test="#step1.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="center">
								<s:property value="#step1.index+1" />
							</td>
							<td align="center">
								<s:if test="#pagedetail.gysId==null">
									<font color="red">尚未匹配到合同</font>
								</s:if>
								<s:else>
								${pagedetail.gysName}
								</s:else>
							</td>
							<td align="center">
								${pagedetail.markId}
							</td>
							<td align="center">
								${pagedetail.proName}
							</td>
							<td align="center">
								${pagedetail.banbenNumber}
							</td>
							<td align="center">
								${pagedetail.banci}
							</td>
							<td align="center">
								${pagedetail.selfCard}
							</td>
							<td align="center">
								${pagedetail.processNOs}
							</td>
							<td align="center">
								${pagedetail.processNames}
							</td>
							<td align="center">
								${pagedetail.wwType}
							</td>
							<td align="center">
								${pagedetail.applyCount}
							</td>
							<td align="center">
								${pagedetail.userName}
							</td>
							<td align="center">
								${pagedetail.addTime}
							</td>
							</tr>
							</s:iterator>
								</table>
							</td>
						</tr>
					</table>
					<hr style="height:3px;border:none;border-top:3px double red;"/>
					<br/>
					<br/>
					<br/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">

</SCRIPT>
	</body>
</html>
