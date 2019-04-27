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
	<body >
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					月结明细
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
					<table class="table" align="center">
						<tr>
							<td align="right">
								编号
							</td>
							<td align="left">
								${payMonth.recNumber}
							</td>
							<td align="right">
								供应商（请购项目）
							</td>
							<td align="left">
							${payMonth.supplierName}（${payMonth.proName}）
<%--							</s:else>--%>
							</td>
						</tr>
						<tr>
							<td align="right">
								添加时间：
							</td>
							<td align="left">
								${payMonth.saveTime}
							</td>
							<td align="right">
								金额：
							</td>
							<td align="left">
								${payMonth.jine}（${payMonth.jineOfChiness}）
							</td>
						</tr>
						<tr>
							<td align="right">
								付款方式：
							</td>
							<td align="left">
								${payMonth.fukuanfs}
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td align="center" colspan="4">--%>
<%--								<input type="submit"--%>
<%--									style="width: 100px; height: 40px; margin-left: 70px;"--%>
<%--									value="添加(add)" />--%>
<%--							</td>--%>
<%--						</tr>--%>
					</table>
				
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							费用摘要说明
						</td>
						<td align="center">
							金额
						</td>
					</tr>

					<s:iterator value="pmdList" id="samples" status="pageStatus">
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
							${samples.illustrate}
						</td>
						<td align="center">
							${samples.jine}
						</td>
						
					</s:iterator>
					
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
