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
				<form action="SpcControlAction_findAllMachine.action" method="post" >
					<table class="table">
						<tr>
							<th align="right">工位号</th>
							<td>
								<input type="text" name="machine.workPosition" value="${machine.workPosition}"/>
							</td>
							<th align="right">设备编号</th>
							<td>
								<input type="text" name="spcControl.no" value="${machine.no}"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							工位号
						</th>
						<th>
							设备编码
						</th>
						<th>
							设备类型
						</th>
						<th>
							设备名称
						</th>
						<th>
							标准差
						</th>
						<th>
							CP
						</th>
						<th>
							Ca
						</th>
						<th>
							Cpk
						</th>
						<th>
							SPC数据
						</th>
					</tr>
					<s:iterator value="machineList" id="pagemachine" status="statussdf">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
							<td align="left">${pagemachine.workPosition}</td>
							<td align="left">${pagemachine.no}</td>
							<td align="left">${pagemachine.type}</td>
							<td align="left">${pagemachine.name}</td>
							<td align="right">${pagemachine.stdev}</td>
							<td align="right">${pagemachine.cp}</td>
							<td align="right">${pagemachine.ca}</td>
							<td align="right">${pagemachine.cpk}</td>
							<td>
								<a href="javascript:;" onclick="window.open('SpcControlAction_findAllSpcControlList.action?spcControl.shebeiNo=${pagemachine.no}')">SPC数据</a>
							</td>
						</tr>
					</s:iterator>
					<tr>
								<td colspan="30" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

								</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
