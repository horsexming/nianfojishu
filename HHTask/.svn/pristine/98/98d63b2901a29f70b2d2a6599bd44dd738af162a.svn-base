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
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>报废管理</h3>
				<br/>
				<form action="scrap_queryScrapByCondition.action" method="post" name="myForm"> 
				<table >
					<tr>
						<td>编号：<input type="text" name="vosc.number" value="${vosc.number}"/></td>
						<td>名称：<input type="text" name="vosc.name" value="${vosc.name}"/></td>
					</tr>
					<tr>
						<td>规格：<input type="text" name="vosc.standard" value="${vosc.standard}"/></td>
						<td>责任人:<input type="text" name="vosc.peopleName" value="${vosc.peopleName}"/>
							<input type="hidden" name="errorMessage" value="all"/></td>
						<td><input type="submit" value="查询" style="width: 80px; height: 50px;"/>
							<input type="button" value="导出Excel" style="width: 80px; height: 50px;" onclick="exportExcel();todisabledone(this)" data="downData"/>
						</td>
					</tr>
				</table>
				</form>
				<table class="table">
					<tr  bgcolor="#c0dcf2" height="50px" align="center">
						<th>序号</th>
						<th>编号</th>
						<th>名称</th>
						<th>规格</th>
						<th>数量</th>
						<th>责任人</th>
						<th>部门</th>
						<th>损坏日期</th>
						<th>责任人意见</th>
						<th>损失原因</th>
						<th>备注</th>
						<th></th>
						<th></th>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
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
									<td>${pageList.number}</td>
									<td>${pageList.matetag}</td>
									<td>${pageList.format}</td>
									<td>${pageList.amount}</td>
									<td>${pageList.username}</td>
									<td>${pageList.dept}</td>
									<td>${pageList.badDate}</td>
									<td>${pageList.badView}</td>
									<td>${pageList.more1}</td>
									<td>${pageList.more2}</td>
									<td>
										<a href="scrap_initUpdate.action?vosc.id=${pageList.id}">修改</a>
										<a href="scrap_del.action?vosc.id=${pageList.id}">删除</a>
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
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function exportExcel(){
				document.forms.myForm.action="scrap_export.action";
				document.forms.myForm.submit();
				document.forms.myForm.action="scrap_queryScrapByCondition.action";
			}
		</script>
	</body>
</html>
