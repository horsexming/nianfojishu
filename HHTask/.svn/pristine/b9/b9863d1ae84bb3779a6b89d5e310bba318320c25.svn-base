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
		<div align="center">
			<form action="ProcardTemplateAction!checkIn.action" method="post">
				<table align="center" class="table">
					<tr>
						<th colspan="2">
							<font size="4">查询多件号是否存在</font>
						</th>
					</tr>

					<tr>
						<th>
							件号：
						</th>

						<td>
							<textarea name="jianhaoSet" cols="100" rows="20">${jianhaoSet}</textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" value="查询" class="input" />
						</td>
					</tr>
					<tr>
						<td align="left" colspan="4">
							输入件号或业务件号查询其在BOM中是否存在,多个件号请以分号 ;分割
						</td>
					</tr>
				</table>
			</form>
			<s:if test="procardTemplateList!=null">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
							<br />
							No.
						</th>
						<th align="center">
							件号/图号
							<br />
							Part No.
						</th>
						<th align="center">
							名称
							<br />
							Name
						</th>
						<th align="center">
							卡片类型
							<br />
							Card Type
						</th>
						<th align="center">
							产品类型
							<br />
							Product Type
						</th>
						<th align="center">
							总成件号/业务件号
							<br />
							Assembly Member
						</th>
						<th align="center">
							编制状态
							<br />
							edit status
						</th>
						<s:if test="pageStatus!=null">
							<th align="center">
								单件价格
								<br />
								Single price
							</th>
						</s:if>
						<th align="center">
							操作
							<br />
							Operation
						</th>
					</tr>
					<s:iterator value="procardTemplateList" id="pageProcardTem"
						status="pageindex">
						<s:if test="#pageindex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageindex.index+1" />
						</td>
						<td>
							<a href="javascript:;"
								onclick="toshowPro('${pageProcardTem.id}')">
								${pageProcardTem.markId} </a>
							<s:if
								test="#pageProcardTem.tuhao!=null&&#pageProcardTem.tuhao!=''">
								<br />(${pageProcardTem.tuhao} )
									</s:if>
						</td>
						<td style="width: 180px;" align="left">
							${pageProcardTem.proName}
						</td>
						<td>
							${pageProcardTem.procardStyle}
						</td>
						<td>
							${pageProcardTem.productStyle}
						</td>
						<td align="left">
							<a href="javascript:;"
								onclick="toshowPro('${pageProcardTem.rootId}')">
								${pageProcardTem.rootMarkId}<s:if
									test="#pageProcardTem.ywMarkId!=null&&#pageProcardTem.ywMarkId!=''">
									<br />
									<font color="green" style="font-weight: bolder;">(${pageProcardTem.ywMarkId})</font>
								</s:if> </a>
						</td>
						<td>
							${pageProcardTem.bzStatus}
						</td>
						<s:if test="pageStatus!=null">
							<td>
								${pageProcardTem.onePrice}
							</td>
						</s:if>
						<td align="right">
							<div align="right">
								<div style="float: left; margin: 0xp; padding: 0px;">
									<input type="button" value="BOM查看"
										onclick="showgongxu('<%=basePath%>','${pageProcardTem.id}')" />
								</div>
							</div>
						</td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
		</div>

		<SCRIPT type="text/javascript">
</SCRIPT>
	</body>
</html>