<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<a href="mealAction!reKan.action" style="color: #ffffff">全部未通过审核查询页面/</a>
						<a href="mealAction!reKan3.action" style="color: #ffffff">全部通过审核查询页面</a>
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="center" 
			style="color:red;font-size:30px;;height:40px;" >全部查询页面</div>
			
			<div align="center">
						
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							来客姓名
						</th>
						<th align="center">
							申请人
						</th>
						<th align="center">
							职务
						</th>
						<th align="center">
							单位
						</th>
						<th align="center">
							总人数
						</th>
						<th align="center">
							审批人
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							添加/修改时间
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="mealtickets" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout=outBgcolor(this, '');>
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
							</s:if>
							<s:else>
								<font color="#c0dcf2"></font>
							</s:else>
							<s:property value="#pageStatus.index+1" />

						</td>
						<td>
							${mealtickets.name}
						</td>
						<td>
							${mealtickets.manage}
						</td>
						<td>
							${mealtickets.job}
						</td>
						<td>
							${mealtickets.company}
						</td>
						<td>
							${mealtickets.number}
						</td>
						<td>
							${mealtickets.checkname}
						</td>
						<td>
							${mealtickets.fuck}
						</td>
						<td>
							${mealtickets.addDate}
						</td>
						<td>
							<a href="mealAction!deefind.action?mealticket.id=${mealtickets.id}">详细</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="10" align="right">通过审批的总数为：${shu}</td>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="10" align="right">
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
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

		</script>
	</body>
</html>
