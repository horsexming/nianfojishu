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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="IntelligentDiagnosisAction_finaAllcnList.action"
					method="post">
					<strong>公司名称:</strong>
					<input type="text" name="cn.campanyname" />
					<input type="hidden" value="${status}" name="status"/>
					<input type="submit" value="查询" style="width: 75px; height: 35px;">
					<input type="button" value="添加" onclick="window.open('./System/xinxi/diaoyan/rr_addcn.jsp')" style="width: 75px; height: 35px;">
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>
							序号
						</th>
						<th>
							企业名称
						</th>
						<th>
							企业规模
						</th>
						<th>
							企业性质
						</th>
						<th>
							负责人
						</th>
						<th>
							联系电话
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="cnList" id="pageList" status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageList.campanyname}
						</td>
						<td>
							${pageList.guimo}
						</td>
						<td>
							${pageList.type}
						</td>
						<td>
							${pageList.lianxiren}
						</td>
						<td>
							${pageList.phone}
						</td>
						<td>
						<a href="javascript:;" onclick="window.open('IntelligentDiagnosisAction_findHzByid.action?id='+${pageList.id}+'&status=diaoyan')">详情</a>/
							<a href="javascript:;" onclick="window.open('IntelligentDiagnosisAction_findHzByid.action?id='+${pageList.id}+'&status=diaoyan&tag=xiugai')">修改</a>/
							<a
								href="IntelligentDiagnosisAction_delcn.action?cn.id=${pageList.id}&status=${status}"
								onclick="confirm('确定要删除吗')">删除</a>
						</td>
					</s:iterator>
					<s:if test="errorMessage ==null || errorMessage == ''">
						<tr>

							<td colspan="13" align="right">
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
							<td colspan="12" align="center">
								<font color="red" size="5">${errorMessage}</font>
							</td>

						</tr>
					</s:else>
				</table>


			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">

		</SCRIPT>

	</body>
</html>
