<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
				系统异常
				</h3>
					<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
							序号
						</td>
						<td align="center">
							责任人
						</td>
						<td align="center">
							出错内容
						</td>
						<td align="center">
							查看图片
						</td>
						<td align="center">
							描述
						</td>
						<td align="center">
							状态
						</td>
					
					</tr>
					<s:iterator value="checkNoteList" id="list"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list.firstPerson}
						</td>
						<td align="center">
							${list.checkType.type}----${list.checkType.name}
						</td>
						<td align="center">
							<img alt="${list.checkType.type}----${list.checkType.name}" src="<%=basePath%>upload/file/sixJian/${list.url}" style="width:121px; height:162px">
						</td>
						<td align="center">
							${list.describe}
						</td>
						<td align="center">
						<s:if test="#list.status=='待审批'">
							<font color="red">未开始处理</font>
						</s:if>
						<s:else>
							<font color="yellow">${list.status}</font>
						</s:else>
						</td>
					
					</s:iterator>
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
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function myrefresh() 
		{ 
			window.location.reload(); 
		} 
		setTimeout('myrefresh()',20000); //指定20秒刷新一次 
		</script>
	</body>
</html>
