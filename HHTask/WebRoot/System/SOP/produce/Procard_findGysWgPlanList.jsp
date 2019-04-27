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
				<form action="ProcardAction!findGysWgPlanList.action" method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								件号（markId）：
								<input type="text" name="wwPlan.markId"
									value="<s:property value="purchasedPartsInput.markId"/>" />
							</td>
							<td align="center">
								批次（selfCard）：
								<input type="text" name="wwPlan.selfCard"
									value="<s:property value="purchasedPartsInput.selfCard"/>" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
					<table class="table">
						<tr>
							<th colspan="15"
								style="height: 35px; color: #ffffff; background-color: green;">
								外购件供货计划
							</th>
						</tr>
						<s:if test="successMessage!=null">
							<tr>
								<td colspan="15" align="center" style="color: red">
									${successMessage}
								</td>
							</tr>
						</s:if>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								供应商
							</th>

							<th align="center">
								件号
							</th>
							<th align="center">
								零件名称
							</th>
							<th align="center">
								总数量
							</th>
							<th align="center">
								可入库数量
							</th>
							<th align="center">
								已入库数量
							</th>
							<th>
								已入库次数
							</th>
							<th align="center">
								批次号
							</th>
							<th align="center">
								采购月份
							</th>
							<th align="center">
								计划单号
							</th>
							<th align="center">
								激活时间
							</th>
							<th align="center">
								应到货时间
							</th>
							<th align="center">
								实际到货时间
							</th>
							<th align="center">
								操作
							</th>
							
						</tr>
						<s:iterator value="wwPlanList" id="pageWgww2" status="pageStatus">
							<s:if test="#pageStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageWgww2.gysName}
							</td>
							<td>
								${pageWgww2.markId}
							</td>
							<td>
								${pageWgww2.proName}
							</td>
							<td>
								${pageWgww2.number}
							</td>
							<td>
								<s:if test="#pageWgww2.keruku==null">
						${pageWgww2.number}
						</s:if>
								<s:else>
									<s:property value="#pageWgww2.keruku" />
								</s:else>
							</td>
							<td>
								<s:if test="#pageWgww2.hasruku==null">
						0
						</s:if>
								<s:else>
									<s:property value="#pageWgww2.hasruku" />
								</s:else>
							</td>
							<td>
								<s:if test="#pageWgww2.rukuCount==null">
						0
						</s:if>
								<s:else>
									<s:property value="#pageWgww2.rukuCount" />
								</s:else>
							</td>
							<td>
								${pageWgww2.selfCard}
							</td>
							<td>
								${pageWgww2.caigouMonth}
							</td>
							<td>
								${pageWgww2.planNumber}
							</td>
							<td>
								${pageWgww2.jihuoTime}
							</td>
							<td>
								${pageWgww2.shArrivalTime}
							</td>
							<td>
								${pageWgww2.acArrivalTime}
							</td>
							<td>
								<a href="ProcardAction!gysTzview2.action?id=${pageWgww2.id}">查看图纸</a>.
							</td>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="15" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
								</td>
							</s:if>
							<s:else>
								<td colspan="15" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>
						</tr>
					</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		
		</SCRIPT>
	</body>
</html>
