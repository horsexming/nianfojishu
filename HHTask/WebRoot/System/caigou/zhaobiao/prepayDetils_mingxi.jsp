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
					添加预付款单
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
					<table class="table" align="center">
						<tr>
							<td align="right">
								预付项目名称：
							</td>
							<td align="left">
								${prepayApp.yyName}
							</td>
							<td align="right">
								采购总额：
							</td>
							<td align="left">
							${prepayApp.allMoney}
<%--							</s:else>--%>
							</td>
						</tr>
						<tr>
							<td align="right">
								预付比例：
							</td>
							<td align="left">
								${prepayApp.yfbl}

								%
								
							</td>
							<td align="right">
								预付金额：
							</td>
							<td align="left">
								${prepayApp.yfMoney}
							</td>
						</tr>
						<tr>
							<td align="right">
								票据类别：
							</td>
							<td align="left">
								<SELECT name="prepayApp.pjType">
									<option value="">
										${prepayApp.pjType}
									</option>
									<option value="支票">
										支票
									</option>
									<option value="电汇">
										电汇
									</option>
								</SELECT>
							</td>
							<td align="right">
								预计报销日期：
							</td>
							<td align="left">
								${prepayApp.expectedTime}
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="right">
								订单编号：
							</td>
							<td align="left">
								${prepayApp.poNumber}
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
							项目名称
						</td>
						<td align="center">
							订单编号
						</td>
						<td align="center">
							采购总额
						</td>
						<td align="center">
							预付金额
						</td>
						<td align="center">
							预付比例
						</td>
					</tr>

					<s:iterator value="prepaymentsApplicationDetailsList" id="samples" status="pageStatus">
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
							${samples.yyName}
						</td>
						<td align="center">
							${samples.poNumber}
						</td>
						<td align="center">
							${samples.allMoney}
						</td>
						<td align="center">
						${samples.yfMoney}
						</td>
						<td align="center">
						${samples.yfbl}%
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
