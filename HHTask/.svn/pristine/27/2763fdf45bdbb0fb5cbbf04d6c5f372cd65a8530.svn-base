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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>
			
			<div align="center">
			  <form action="proAction!findAllPro.action" method="post"  theme="simple">
				<table class="table">
				<tr><td>项目名称:</td><td><input type="text" id="pro.name" name="pro.name" /></td>
					<td>项目编号:</td><td><input type="text" id="pro.code" name="pro.code" /></td>
					<td rowspan="2"><input type="submit" value="查询"   class="input"/>	</td>
				</tr>
				<tr><td>创建人:</td><td><input type="text" id="pro.createUserName" name="pro.createUserName" /></td>
					<td>创建时期:</td><td><input class="Wdate" type="text" id="pro.createDate"
									name="pro.createDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
				</tr>
				</table>
				<table  class="table">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td align="center">序号</td>
						<td align="center">项目名称</td>
						<td align="center">项目编号</td>
						<td align="center">创建人</td>
						<td align="center">创建时间</td>
						<td align="center">客户</td>
						<td align="center">预算</td>
						<td align="center">结束日期</td>
						<td align="center">状态</td>
						<td align="center">操作</td>
					</tr>
					<s:iterator value="list" id="p" status="pageIndex">
						<tr>
							<td>${pageIndex.index + 1}</td>
							<td>${p.name}</td>
							<td>${p.code}</td>
							<td>${p.createUserName}</td>
							<td><s:date name="createDate" format="yyyy-MM-dd HH:mm"/></td>
							<td>${p.clientName}</td>
							<td>${p.budget}</td>
							<td><s:date name="finishDate" format="yyyy-MM-dd"/></td>
							<td></td>
							<td>
								<a  href="proAction!getProUpdatePage.action?pro.id=${p.id}">修改</a>
								<a  onclick="return window.confirm('确认要删除该项目记录?')" href="proAction!deletePro.action?pro.id=${p.id}">删除</a>
								<a href="proAction!getProDetailPage.action?pro.id=${p.id}">查看详细</a>
							</td>
							
						</tr>
					</s:iterator>
					
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="10" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="10" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
