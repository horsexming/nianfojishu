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
		<script type="text/javascript">

</script>

	</head>
	<body>
	<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
				<table style="width: 100%">
					<tr>
						<td>
							
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				</center>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
	
	
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="center" style="border: 1px solid #00000;">
				<form action="EquipmentAction!listresponsibilities.action"
					method="post">
					<table class="table">
						<tr>
							<th colspan="6">
								设备保修人员维护
							</th>
						</tr>
						<tr>
							<th>
								工号:
							</th>
							<td>
								<input name="responsibilities.employeenumber"
									value="${responsibilities.employeenumber}" />
							</td>
							<th>
								姓名:
							</th>
							<td>
									<input name="responsibilities.repairname"
									value="${responsibilities.repairname}" />
							</td>
							<th rowspan="2">
								<input type="submit" value="查询(Query)" class="input" />
								<input type="button" value="添加"   class="input" onclick="add()"/>	
							</th>
						</tr>
						<tr>
							<th>
								部门:
							</th>
							<td>
									<input name="responsibilities.repairdepartment"
									value="${responsibilities.repairdepartment}" />
							</td>
							<th>
								手机号:
							</th>
							<td>
							<input name="responsibilities.phone"
									value="${responsibilities.phone}" />
							</td>
						</tr>
					</table>
				</form>

				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					
					
					<form action="zhaobiaoAction!banding.action"
					method="post">
					
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								姓名
							</th>
						
							<th align="center">
								职责
							</th>
							<th align="center">
								手机号
							</th>
							<th align="center">
								邮箱
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td style="width: 180px;">
								${pageProcardTem.employeenumber}
							</td>
							<td>
								${pageProcardTem.repairname}
							</td>
							
							<td align="center">
								${pageProcardTem.repairresponsibilitiesl}
							
							</td>
								<td align="center">
								${pageProcardTem.phone}
							
								<td align="center">
								${pageProcardTem.mailbox}
							</td>
							<td align="center">
							<a onclick="toUpdatezhaobiao(${pageProcardTem.id})">修改</a>
							
									<a href="EquipmentAction!deleteresponsibilities.action?responsibilities.id=${pageProcardTem.id}">删除</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							</td>
					</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function toUpdatezhaobiao(id){
			var url=encodeURI(encodeURI("EquipmentAction!toUpdateresponsibilities.action?responsibilities.id="+id));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
		function add(){
			var url=encodeURI(encodeURI("${pageContext.request.contextPath}/System/Equipment/responsibilities_add.jsp"));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
		</script>
	</body>
</html>
