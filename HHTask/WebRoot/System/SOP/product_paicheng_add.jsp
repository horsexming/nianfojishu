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
				<table class="table" style="width: 50%;">
					<tr>
						<td align="right">
							件号:
						</td>
						<td>
							${pm.pieceNumber }
						</td>
					</tr>
					<tr>
						<td align="right">
							业务件号:
						</td>
						<td>
							${pm.ywMarkId}
						</td>
					</tr>
					<tr>
						<td align="right">
							品名:
						</td>
						<td>
							${pm.name}
						</td>
					</tr>
					<tr>
						<td align="right">
							数量:
						</td>
						<td>
							${pm.num}
						</td>
					</tr>
					<tr>
						<td align="right">
							冲销申请中数量:
						</td>
						<td>
							${pm.cxApplyCount}
						</td>
					</tr>
					<tr>
						<td align="right">
							冲销数量:
						</td>
						<td>
							${pm.cxCount}
						</td>
					</tr>
					<tr>
						<td align="right">
							总交付日期:
						</td>
						<td>
							${pm.paymentDate}
						</td>
					</tr>
					<tr>
						<td align="right">
							备注:
						</td>
						<td>
							${pm.remark }
						</td>
					</tr>
				</table>
				<br />
				<form action="internalOrder_yaohuoPlanForIod.action" method="post">
					<input type="hidden" name="om.id" value="${pm.orderManager.id }">
					<input type="hidden" name="iod.id" value="${pm.id }">
					<table class="table">
						<tr>
							<th>
								日期:
							</th>
							<s:iterator id="pageDate" value="strList">
								<th>
									${pageDate}
								</th>
							</s:iterator>
							<th colspan="100"></th>
						</tr>
						<tr>
							<th>
								数量:
							</th>
							<s:iterator id="pageDate" value="strList" status="pageIndex">
								<th>
									<input type="hidden"
										name="iodList[${pageIndex.index}].paymentDate"
										value="${pageDate}" style="width: 75px;" />
									<input type="hidden"
										name="iodList[${pageIndex.index}].productManagerId"
										value="${pm.id}" style="width: 75px;" />
									<input name="iodList[${pageIndex.index}].num"
										style="width: 75px;" />
								</th>
							</s:iterator>
							<th colspan="100"></th>
						</tr>
						<s:if test="strList!=null&&strList.size()>0">
							<tr>
								<td  colspan="2" align="center">
									<input type="submit" value="确定"
										style="height: 50px;" onclick="todisabled(this)">
								</td>
								<td colspan="100" align="center">
									<input type="submit" value="确定" style="height: 50px;" onclick="todisabled(this)">
								</td>
								<td align="center">
									<input type="submit" value="确定" style="height: 50px;" onclick="todisabled(this)">
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td colspan="100" align="center">
									<font color="red">交付日期必须在${pm.paymentDate}之后才能完善计划！请修改后重试。</font>
								</td>
							</tr>
						</s:else>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
