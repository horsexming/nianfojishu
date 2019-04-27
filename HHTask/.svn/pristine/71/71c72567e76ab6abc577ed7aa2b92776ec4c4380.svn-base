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
		<div id="gongneng" >
			<div align="center">
				<h3>
					2015年06月-2015年09月焊工时间统计<br/>
				</h3>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>
						</td>
						<td align="center">
							类型<br/>
						</td>
						<td align="center">
							姓名<br/>
						</td>
						<td align="center">
							工号<br/>
						</td>
						<td align="center">
							部门<br/>
						</td>
						<td align="center">
							出勤天数<br/>
						</td>
						<td align="center">
							高温费(总)<br/>
						</td>
						<td align="center">
							缺勤扣费<br/>
						</td>
						<td align="center">
							高温费（实发）<br/>
						</td>
						<td align="center">
							月份<br/>
						</td>
					</tr>
					<s:iterator value="list" id="hgtj" status="pageStatus">
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
						<td>
							${hgtj.type}
						</td>
						<td>
							${hgtj.name}
						</td>
						<td>
							${hgtj.code}
						</td>
						<td>
							${hgtj.dept}
						</td>
						<td>
							${hgtj.cqCount}
						</td>
						<td>
							${hgtj.money}
						</td>
						<td>
							${hgtj.qqMoney}
						</td>
						<td>
							${hgtj.sfMoney}
						</td>
						<td>
							${hgtj.month}
						</td>
					</tr>
					</s:iterator>
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="6" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
