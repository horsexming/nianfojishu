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
	<SCRIPT type="text/javascript">
	</SCRIPT>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
		
			<div align="center">
				<h2>添加设备点检信息</h2>
			<form action="MachineDayYZSJAction_add1.action" id="submit" method="post">
				<table class="table" id="mytable">
				<tr align="center" >
						<td colspan="7">
						<input  type="hidden" value="${machine.id}" name="mdy.machine_id"/>
							设备名称:${machine.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							型号:${machine.type}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							设备编号:${machine.no}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%--							<input id="machineMonth" class="Wdate" type="text"--%>
<%--								name="price.pricePeriodStart"--%>
<%--								onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"--%>
<%--								value="${mmd.machineMonth}"  onchange="selectmachine(${machine.id})"/>--%>
<%--								${month}--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							工位:${machine.workPosition}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr align="center" >

						<td>
							序号
						</td>
						<td colspan="4">
							点检内容
						</td>
						<td colspan="2">
							操作
						</td>
					</tr>
					<s:iterator id="djnrtest" value="djnrList" status="statussdf">
   					<s:if test="#statussdf.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
					</s:else>	
<%--					<td>--%>
<%--						<input type="checkbox" id="${djnrtest.id}" value="${djnrtest.nr}" name="checkboxs"/>选择--%>
<%--					</td>--%>
					<td>
						<s:property value="#statussdf.index+1" />
					</td>
					<td colspan="4">
						${djnrtest.nr}
						<input type="hidden" value="${djnrtest.nr}" name="mdy.mddList[${statussdf.index}].machine_djnr"/>
					</td>
					<td colspan="2">
								<input type="radio" value="正常" name="mdy.mddList[${statussdf.index}].dj_status">正常
								<input type="radio" value="异常" name="mdy.mddList[${statussdf.index}].dj_status">异常
					</td>
					</s:iterator>
					</tr>
<%--					<tr>--%>
<%--						<td align="center">--%>
<%--						</td>--%>
<%--						<td align="center">--%>
<%--						</td>--%>
<%--						<td align="center">--%>
<%--							<input type="hidden" value="${size}" id="hid"/>--%>
<%--							<input type="button"  value="添加" onclick="addlin()"/>--%>
<%--						</td>--%>
<%--					</tr>--%>
					
					 <tr>
<%--				<td colspan="3" align="right">--%>
<%--								第--%>
<%--					<font color="red"><s:property value="cpage" /> </font> /--%>
<%--						<s:property value="total" />--%>
<%--							页--%>
<%--						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"--%>
<%--									styleClass="page" theme="number" />--%>
<%--									--%>
<%--				</td>--%>
			</tr>
				</table>
				<tr>
				<td colspan="7" align="center" >
					<input type="submit" value="添加" style="width: 80px;height: 35px" id="sub" >
					
				</td>
			</tr>
			<div id="mydiv"> </div>
			</form>
			<input type="hidden" value="${errorMessage}" id="rebeack"/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">




		</script>
	</body>
</html>
