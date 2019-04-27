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
		<form method="post" action="ReliabilityTestAction!findRTPByCondition.action">
			<table class="table">
				<tr>
					<th colspan="6" style="font-size: 20px;">可靠性测试项目列表</th>
				</tr>
	<!-- 			<tr> -->
	<!-- 				<th ></th> -->
	<!-- 				<td></td> -->
	<!-- 			</tr> -->
				<tr>
					<td align="center"colspan="6">
						<input type="hidden" value="" name="pro.proName" >
						<input type="hidden" value="" name="pro.id" >
						<input type="submit" value="查询所有" class="input">
						<input type="button" value="添加" class="input"
						onclick = "location.href='ReliabilityTestAction!toaddRTP.action'">
					</td>
				</tr>
			</table>
		</form>
		<table class="table">
		
			<tr>
				<th>序号</th>
				<th>名称</th>
				<th>操作</th>
			</tr>
			<s:if test="proList!=null&&proList.size()>0">
				<s:iterator id="pro1" value="proList" status="pageStatus">
				<s:if test="#pageStatus.index%2==1">
					<tr align="center" bgcolor="#e6f3fb"
						onmouseover="chageBgcolor(this)"
						onmouseout="outBgcolor(this,'#e6f3fb')">
				</s:if>
				<s:else>
					<tr align="center" onmouseover="chageBgcolor(this)"
						onmouseout="outBgcolor(this,'')">
				</s:else>
					<td>${pageStatus.index+1 }</td>
					<td>${pro1.proName }</td>
					<td>
						<input type="button" value="删除" onclick="del(${pro1.id })">
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="15" align="right">
					第
					<font color="red"><s:property value="cpage" /> </font> /
					<s:property value="total" />
					页
					<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
						styleClass="page" theme="number" />
				</td>
			</tr>
			
			</s:if>
			<s:else>
				<tr>
					<td colspan="20" style="color: red;">没有记录</td>
				</tr>
			</s:else>
		</table>
	</body>
	<script type="text/javascript">
		function del(id){
			if(confirm('确定删除吗?')){
				 location.href='${pageContext.request.contextPath}/ReliabilityTestAction!deleteRTP.action?id='+id
				
			}
		}
	</script>
</html>
