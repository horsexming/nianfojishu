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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="airtightLogAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					气密机器<br/>
				</h3>
				<form action="airtightLogAction_showMachineList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								开关IP
								<input type="text" name="airMachine.switchIp" value="<s:property value="airMachine.switchIp"/>" />
							</td>
							<td align="center">
								开关端口
								<input type="text" name="airMachine.switchPort" value="<s:property value="airMachine.switchPort"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								传值IP
								<input type="text" name="airMachine.valueIp" value="<s:property value="airMachine.valueIp"/>" />
							</td>
							<td align="center">
								传值端口
								<input type="text" name="airMachine.valuePort" value="<s:property value="airMachine.valuePort"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								名称：
								<input type="text" name="airMachine.name" value="<s:property value="airMachine.name"/>" />
							</td>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
								<input type="button" style="width: 100px; height: 40px;" onclick="add()"
									value="添加" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							机器名称
						</td>
						<td align="center">
							开关Ip
						</td>
						<td align="center">
							开关端口
						</td>
						<td align="center">
							传值Ip
						</td>
						<td align="center">
							传值端口
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							添加人
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="airMachineList" id="airMachinePage" status="pageStatus">
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
							${airMachinePage.name}
						</td>
						<td>
							${airMachinePage.switchIp}
						</td>
						<td>
							${airMachinePage.switchPort}
						</td>
						<td>
							${airMachinePage.valueIp}
						</td>
						<td>
							${airMachinePage.valuePort}
						</td>
						<td>
							${airMachinePage.addTime}
						</td>
						<td>
							${airMachinePage.addUser}
						</td>
						<td  colspan="2">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${airMachinePage.id})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${airMachinePage.id})" />
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function update(id) {
	window.location.href = "airtightLogAction_toUpdateMachine.action?id=" + id+"&cpage=${cpage}";
}
function todelete(id) {
	if(window.confirm("您将删除此记录,是否继续?")){
	window.location.href = "airtightLogAction_deleteMachineById.action?id=" + id+"&cpage=${cpage}";
	}
}
function add(){
	window.location.href = "airtightLogAction_toAddMachine.action";
}

</script>
	</body>
</html>

