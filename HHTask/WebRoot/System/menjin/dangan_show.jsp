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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					档案存档管理
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							档案名称
						</td>
						<td align="center">
							档案编号
						</td>
						<td align="center">
							存档室
						</td>
						<td align="center">
							申请人
						</td>
						<td align="center">
							申请人部门
						</td>
						<td align="center">
							申请时间
						</td>
						<td align="center">
							审核状态
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="dangAnList" id="samples" status="pageStatus">
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
							${samples.daName}
						</td>
						<td align="center">
							${samples.daNum}
						</td>
						<td align="center">
							${samples.cdAceName}
						</td>
						<td align="center">
							${samples.sqName}
						</td>
						<td align="center">
							${samples.sqDept}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
						<td align="center">
							${samples.status}
						</td>
						<td align="center" colspan="2">
							<s:if test="#samples.status=='未审批'||#samples.status=='打回'">
								<a
									href="DanganAction_toupdate.action?dangAn.id=${samples.id}">修改/</a>
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="DanganAction_delete.action?dangAn.id=${samples.id}">删除/</a>
							</s:if>
							<s:if test="#samples.epId!=null">
								<a
									href="CircuitRunAction_findAduitPage.action?id=${samples.epId}">审批动态</a>
							</s:if>
							<s:if test="#samples.daFiles!=null">
								<a
									href="<%=path%>${samples.daFiles}">查看附件</a>
							</s:if>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="9" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="9" align="center" style="color: red">
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

</script>
	</body>
</html>
