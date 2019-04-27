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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					外购件采购汇总
				</h3>
				<form action="WaigouwaiweiPlanAction!findZaiTuwl.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								订单号：
								<input type="text" name="procard.orderNumber" value="<s:property value="procard.orderNumber"/>" />
							</td>
							<td align="center">
								内部计划单号：
								<input type="text" name="procard.planOrderNum" value="<s:property value="procard.planOrderNum"/>" />
							</td>
							<td align="center">
								件号：
								<input type="text" name="procard.markId" value="<s:property value="procard.markId"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="3">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							供料属性
						</td>
						<td align="center">
							总需
						</td>
						<td align="center">
							到货
						</td>
						<td align="center">
							下单
						</td>
					</tr>
					<s:iterator value="list" id="str" status="pageStatus">
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
							${str[0]}
						</td>
						<td>
							${str[1]}
						</td>
						<td>
							<fmt:formatNumber value="${str[2]}" pattern="#.####"></fmt:formatNumber>	
						</td>
						<td>
							<fmt:formatNumber value="${str[3]}" pattern="#.####"></fmt:formatNumber>	
						</td>
						<td>
							<fmt:formatNumber value="${str[4]}" pattern="#.####"></fmt:formatNumber>	
						</td>
					</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="10" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="10" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="10" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
