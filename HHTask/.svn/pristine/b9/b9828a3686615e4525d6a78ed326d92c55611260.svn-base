<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong" style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; " align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div><font color="red">${successMessage}${errorMessage}</font></div>
				<div>
					<table class="table" width="100%" border="0" style="border-collapse: collapse;">
						<tr bgcolor="#c0dcf2" height="50px">
							<th style="width: 35px" align="center">
								序号
							</th>
							<th style="padding: 0 10 px;">
								流程ID
							</th>
							<th>
								流程介绍
							</th>
							<th style="width: 70px" align="center">
								流程状态
							</th>
							<th style="width: 70px" align="center">
								选择流程
							</th>
						</tr>
						<s:iterator value="flows" id="f" status="st">
							<tr bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
								<td align="center">
									${st.index+1}
								</td>
								<td style="padding: 0 10 px;">
									${f.fid}
								</td>
								<td>
									${f.fdsp}
								</td>
								<td align="center">
									<s:if test="#f.statu.equals('new')">
										新创建
									</s:if>
									<s:elseif test="#f.statu.equals('check')">
										开始
									</s:elseif>
									<s:elseif test="#f.statu.equals('finish')">
										结束
									</s:elseif>
								</td>
								<td align="center">
									<s:if test="#f.statu.equals('new')">
										<a href="Metric_addInput.action?flow.fid=${f.fid}&product.id=${product.id}">选择流程</a>
									</s:if>
									<s:elseif test="#f.statu.equals('check')">
										已启动
									</s:elseif>
									<s:elseif test="#f.statu.equals('finish')">
										<a href="Metric_addInput.action?flow.fid=${f.fid}&product.id=${product.id}">选择流程 </a>
									</s:elseif>
	
								</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="5" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page" theme="number" />
								</td>
							</s:if>
							<s:else>
								<td colspan="5" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
