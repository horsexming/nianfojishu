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
				<h3>以旧换新管理</h3>
				<br/>
				<form action="renew_queryRenewByCondition.action" method="post"> 
				<table border="1">
					<tr>
						<td>旧申报编号：<input type="text" name="vore.number" value="${vore.number}"/></td>
						<td>旧备件名称：<input type="text" name="vore.name" value="${vore.name}"/></td>
						<td>旧备件规格：<input type="text" name="vore.standard" value="${vore.standard}"/></td>
					</tr>
					<tr>
						<td>旧备件库别：<input type="text" name="vore.storehouse" value="${vore.storehouse }"/></td>
						<td>换用人工号：<input type="text" name="vore.jobNum" value="${vore.jobNum}"/></td>
						<td>换用人姓名：<input type="text" name="vore.jobName" value="${vore.jobName}"/></td>
					
					</tr>
					<tr>
						<td>更换设备名：<input type="text" name="vore.devicename" value="${vore.devicename}"/></td>
						<td>旧件放置地：<input type="text" name="vore.place" value="${vore.place}"/></td>
						<td>旧备件类别：<input type="text" name="vore.category" value="${vore.category}"/></td>
					</tr>
					<tr>
							<td>开始日期：<input style="width: 155px" class="Wdate"
									type="text" name="vore.startTime" value="${vore.startTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
							<td>结束日期：<input style="width: 155px" class="Wdate"
									type="text" name="vore.endTime" value="${vore.endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td><input type="hidden" name="errorMessage" value="all"/>
							<input type="submit" value="查询" style="width: 80px; height: 50px;"/>
						</td>
					</tr>
				</table>
				</form>
				<table class="table" >
					<tr  bgcolor="#c0dcf2" height="50px" align="center">
						<td>序号</td>
						<td>旧申报编号</td>
						<td>旧备件名称</td>
						<td>旧备件规格</td>
						<td>旧备件库别</td>
						<td>旧备件类别</td>
						<td>旧备件单位</td>
						<td>旧备件数量</td>
						<td>更换设备名</td>
						<td>旧件放置地</td>
						<td>换用人姓名</td>
						<td>备件更换时间</td>
						<td>备件更换缘由</td>
						<td width="40px;"></td>
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
									<td>${pageList.exAppNumber}</td>
									<td>${pageList.exMetatag}</td>
									<td>${pageList.exFormat}</td>
									<td>${pageList.exStore}</td>
									<td>${pageList.exClass}</td>
									<td>${pageList.exUnit}</td>
									<td>${pageList.exCount}</td>
									<td>${pageList.exObj}</td>
									<td>${pageList.exPosition}</td>
									<td>${pageList.exUser}</td>
									<td>${pageList.exDate}</td>
									<td>${pageList.exResult}</td>
									<td>
										<a href="renew_initUpdate.action?vore.id=${pageList.id}">修改</a>
										<a href="renew_del.action?vore.id=${pageList.id}" onclick="return del()">删除</a>
									</td>
						</s:iterator>
						</tr>
							<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="14" align="center" style="color: red">
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
			function del(){
				if(confirm("确定删除吗?")){
					return;
				}else{
					return false;
				}
			}
		</script>
	</body>
</html>
