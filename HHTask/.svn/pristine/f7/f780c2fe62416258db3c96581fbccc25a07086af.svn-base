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
					<a href="InsTemplate_addInput.action" style="color: #ffffff">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>产品型别</th>
						<th>零件号</th>
						<th>工序号</th>
						<th>工位号</th>
						<th>检查类型</th>
						<th>检查频次</th>
						<th>创建时间</th>
						<th>创建人</th>
						<th>操作</th>
					</tr>
					<s:iterator value="ts" status="st" id="tt">
						<s:if test="#detailsStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
							<th>${st.index +1 }</th>
							<td>${tt.productType}</td>
							<td>${tt.partNumber}</td>
							<td>${tt.processNumber}</td>
							<td>${tt.workStation}</td>
							<td>${tt.checkpc}</td>
							<td>${tt.type}</td>
							<td><s:date name="#tt.createDate" format="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${tt.username}</td>
							<td>
								<a href="InsScope_get.action?t.id=${tt.id}">需检项</a>&nbsp;
								<a href="InsTemplate_updateInput.action?t.id=${tt.id}">修改</a>
								 <a href="InsTemplate_delete.action?t.id=${tt.id}" onclick="return confirm('确定要删除吗?')">删除</a> 
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
							</td>
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
