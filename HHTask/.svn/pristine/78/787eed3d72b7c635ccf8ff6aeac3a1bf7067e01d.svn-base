<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
function chageProcess() {
	var provisionList = "${provisionList}";
	var addDiv = document.getElementById("addProvision");
	var updateDiv = document.getElementById("upadteProvision");
	var showAllProvision = document.getElementById("showAllProvision");
	if (provisionList == "") {
		showAllProvision.style.display = "none";
		updateDiv.style.display = "block";
	} else {
		addDiv.style.display = "block";
	}
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="chageProcess()">
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
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">管理条款
						<br/>
						Management Terms</a>
				</div>
			</div>
			
			<div id="addProvision" align="center" style="display: none">
				<form action="ProvisionAction!addProvision.action" method="post">
					<input type="hidden" name="provision.provisionStatus"
						value="${provision.provisionStatus}" />
					<table class="table">
						<tr>
							<th colspan="2" align="center">
								添加条款
								<br/>
								Adding Terms
							</th>
						</tr>
						<tr>
							<th align="right">
								请填位置:
								<br/>
								Please fill out the terms content:
							</th>
							<td align="left">
								<input type="text" name="provision.num" id="num">*请填写阿拉伯数字
							</td>
						</tr>
						<tr>
							<th align="right">
								请填写条款内容:
								<br/>
								Please fill out the terms content:
							</th>
							<td align="left">
								<textarea rows="5" cols="80" name="provision.content"></textarea>
							</td>
						</tr>
						<tr>
							<th colspan="2" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 80px; height: 50px">
								<input type="reset" value="重置(Add)" style="width: 80px; height: 50px">
							</th>
						</tr>
					</table>
				</form>
			</div>
			<div id="upadteProvision" align="center" style="display: none">
				<form action="ProvisionAction!updateProvision.action" method="post">
					<input type="hidden" name="id" value="${provision.id}">
					<input type="hidden" name="provision.id" value="${provision.id}">
					<input type="hidden" name="provision.provisionStatus"
						value="${provision.provisionStatus}" />
					<table class="table">
						<tr>
							<th colspan="2" align="center">
								修改合同条款 ————
								<br/>
								Modify contract terms
								<font color="red">${provision.content}</font>
							</th>
						</tr>
						<tr>
							<th align="right">
								请填位置:
								<br/>
								Please fill out the terms content:
							</th>
							<td align="left">
								<input type="hidden" name="num" value="${provision.num}" >
								<input type="text" name="provision.num" value="${provision.num}" >*请填写阿拉伯数字
							</td>
						</tr>
						<tr>
							<th align="right">
								请填写合同条款内容:
								<br/>
								Please fill in the terms of the contract content:
							</th>
							<td align="left">
								<textarea rows="5" cols="80" name="provision.content">${provision.content}</textarea>
							</td>
						</tr>
						<tr>
							<th colspan="2" align="center">
								<input type="submit" value="修改(Modify)"
									style="width: 80px; height: 50px">
								<input type="reset" value="重置(Reset)" style="width: 80px; height: 50px">
							</th>
						</tr>
					</table>
				</form>
			</div>
			<div id="showAllProvision" align="center">
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号<br/>
							No.
						</th>
						<th align="center" width="85%">
							内容 
							<br/>
							Content
						</th>
						<th align="center" width="85%">
							位置
							<br/>
							Content
						</th>
						<th align="center">
							操作<br/>
							Operation
						</th>
					</tr>
					<s:iterator value="provisionList" id="pageProvision"
						status="pageStatus">
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
								<font color="red">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="left">
							${pageProvision.content}
						</td>
						<td>
							${pageProvision.num}
						</td>
						<td>
							<a
								href="ProvisionAction!findProvisionById.action?id=${pageProvision.id}">修改(Modify)</a>/
							<a
								href="ProvisionAction!delProvision.action?id=${pageProvision.id}">删除(Delete)</a>
								<s:if test="#pageProvision.num!=1">
							<a href="ProvisionAction!upById.action?id=${pageProvision.id}">上移</a>
								</s:if>
								
								<s:if test="#pageProvision.num!=abc">
							<a href="ProvisionAction!downById.action?id=${pageProvision.id}">下移</a>
								</s:if>
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
					</tr>
				</table>
			</div>
			<div id="message">
				<font color="red"> ${successMessage} ${errorMessage}</font>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
