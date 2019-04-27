<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<div>
			<div align="center" style="border: 1px solid #00000;">
				<div id="rootTemplateDiv">
					<div>
						<div align="center">
							<h3>
								<font color="red">${successMessage}</font>
								<font color="red">${errorMessage}</font>
							</h3>
						</div>
						<form action="WaigouwaiweiPlanAction!findDqrWlList.action"
							method="post" id="form">
							<input type="hidden" name="viewStatus" value="${viewStatus}" />
							<input type="hidden" name="pageStatus" value="${pageStatus}" />
							<input type="hidden" name="tag" value="${tag}" />
							<input type="hidden" name="operation" value="${operation}" />
							<table class="table">
								<tr>
									<th align="right">
										订单编号:
										<br />
										(内部):
									</th>
									<td>
										<input name="procard.orderNumber"
											value="${procard.orderNumber}" />
									</td>
									<th align="right">
										件号:
										<br />
										Part No. :
									</th>
									<td>
										<input name="procard.markId" value="${procard.markId}" />
									</td>
									<th align="right">
										业务件号:
										<br />
										Part No. :
									</th>
									<td>
										<input name="procard.ywMarkId" value="${procard.ywMarkId}" />
									</td>
								</tr>
								<tr>
									<th colspan="6">
										<input type="submit" value="查询" class="input" />
										<%--<input type="button" value="导出EXCEL" class="input"
											onclick="getExcel()" />
										--%>
									</th>
								</tr>
							</table>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									序号
									<br />
									No.
								</th>
								<th align="center">
									订单编号
									<br />
									(内部)
								</th>
								<th align="center">
									件号
									<br />
									Part No.
								</th>
								<th align="center">
									业务件号
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
									批次
									<br />
									Batch
								</th>
								<th align="center">
									单位
									<br>
									Unit
								</th>
								<th align="center">
									数量
									<br />
									Quantity
								</th>
								<th align="center">
									交付日期
									<br />
									Card time
								</th>
								<th align="center">
									申购日期
								</th>
								<th align="center">
									激活时间
								</th>
								<th align="center">
									状态
									<br />
									State
								</th>
								<th align="center" colspan="2">
									操作
									<br />
									Operation
								</th>
							</tr>
							<s:iterator value="procardList" id="pageProcard"
								status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
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
								<td align="left">
									${pageProcard.orderNumber}
								</td>
								<td align="left">
									${pageProcard.markId}
								</td>
								<td align="left">
									${pageProcard.ywMarkId}
								</td>
								<td align="left" style="width: 100px;">
									${pageProcard.proName}
								</td>
								<td>
									${pageProcard.procardStyle}
								</td>
								<td>
									${pageProcard.productStyle}
								</td>
								<td>
									${pageProcard.selfCard}
								</td>
								<td>
									${pageProcard.unit }
								</td>
								<td>
									${pageProcard.filnalCount}
								</td>
								<td>
									${pageProcard.jioafuDate}
								</td>
								<td>
									${pageProcard.wlqrtime}
								</td>
								<td>
									${pageProcard.mrpjihuoDate}
								</td>
								<td>
									${pageProcard.wlstatus}
								</td>
								<td>
									<s:if test="pageStatus=='dqr'">
										<a
											href="WaigouwaiweiPlanAction!findDqrWlDetailList.action?id=${pageProcard.id}">物料明细</a>
									</s:if>
									<s:elseif test="pageStatus=='all'">
										<a
											href="WaigouwaiweiPlanAction!findAllWlDetailList.action?id=${pageProcard.id}&tag=${tag}">物料追踪</a>
									</s:elseif>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="20" align="right">
									共
									<s:property value="total" />
									页 第
									<s:property value="cpage" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<SCRIPT type="text/javascript">
		function getExcel(){
		 var startDate=document.getElementById("startDate").value;
		 var endDate=document.getElementById("endDate").value;
		 if(startDate==""||endDate==""){
			 alert("请选择起始和结束时间");
			 return false;
		 }
		 var form=document.getElementById("form");
		 form.action="ProcardAction!geExcel.action?cpage=${cpage}";
		 form.submit();
		 form.action="ProcardAction!findAllProcards.action";
		}
		</SCRIPT>
	</body>
</html>
