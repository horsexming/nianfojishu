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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="WaigouwaiweiPlanAction!findAllGysMarkIdCkolse.action" method="post">
					<table class="table" >
						<tr>
							<th align="right">
								供应商:
							</th>
							<td>
								<input type="text" value="${gysClose.gysCmp}" name="gysClose.gysCmp"/>
							</td>
							<th align="right">
								件号:
							</th>
							<td>
								<input type="text" value="${gysClose.markId}" name="gysClose.markId"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input">
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							供应商
						</th>
						<th>
							供应商编号
						</th>
						<th>
							供应商简称
						</th>
						<th>
							件号
						</th>
						<th>
							是否检验提醒
						</th>
						<th>
							是否关闭送货
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="pagelist" value="gysCloseList"
								status="statussdf">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>
									${pagelist.gysCmp}
								</td>
								<td>
									${pagelist.gysCode}
								</td>
								<td>
									${pagelist.gysName}
								</td>
								<td>
									${pagelist.markId}
								</td>
								<td>
								 	<s:if test="#pagelist.isjytx == 0">
								 		是
									 </s:if>
									 <s:else>
									 	否
									 </s:else>
								</td>
								<td>
									<s:if test="#pagelist.isClose == 0">
								 		是
									 </s:if>
									 <s:else>
									 	否
									 </s:else>
								</td>
								<td>
									<a href="WaigouwaiweiPlanAction!updateGysMarkIdCkolse.action?gysClose.id=?${pagelist.id}">恢复送货</a>
								</td>
							</tr>
					</s:iterator>
					<tr>
								<td colspan="30" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

								</td>
							</tr>
					
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
