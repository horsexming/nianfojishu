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
			<h2>未投产数量统计</h2>
				<form action="ProcardAction!findAllWtcProcard.action" method="POST">
					<table class="table" style="width: 75%;">
						<tr>
							<th align="right">
								件号:
							</th>
							<td>
								<input type="text" value="${procard.markId}"
									name="procard.markId" />
							</td>
							<th align="right">
								名称:
							</th>
							<td>
								<input type="text" value="${procard.proName}"
									name="procard.proName" />
							</td>
						</tr>
						<tr>
							<th align="right">
								版本:
							</th>
							<td>
								<input type="text" value="${procard.banBenNumber}"
									name="procard.banBenNumber" />
							</td>
							<th align="right">
								卡片类型:
							</th>
							<td>
									<select name="procard.procardStyle">
										<option value="${procard.procardStyle}">${procard.procardStyle}</option>
										<option></option>
										<option value="总成">总成</option>
										<option value="自制">自制</option>
										<option value="外购">外购</option>
									</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								大于数量:
							</th>
							<td>
								<input type="text" value="${procard.filnalCount}" name="procard.filnalCount" onchange="numyanzheng(this)"/>
							</td>
							<th align="right">
							</th>
							<td>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							类型
						</th>
						<th>
							件号
						</th>
						<th>
							名称
						</th>
						<th>
							版本
						</th>
						<th>
							数量
						</th>
					</tr>
					<s:iterator id="pageList" value="list" status="statussdf">
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
							${pageList[0]}
						</td>
						<td align="left">
							${pageList[1]}
						</td>
						<td align="left">
							${pageList[2]}
						</td>
						<td align="left">
							${pageList[3]}
						</td>
						<td align="right">
							<fmt:formatNumber value="${pageList[4]}" pattern="###,###"></fmt:formatNumber>	
						</td>
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
