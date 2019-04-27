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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<s:if test="flag!='view'">
					<a href="proTryMakeScoreAction_toApprovalOrPrint.action"
						style="color: #ffffff">刷新<br />(reflesh)</a>
						</s:if>
						<s:else>
						<a href="proTryMakeScoreAction_toApprovalOrPrint.action?flag=view&month=${month}"
						style="color: #ffffff">刷新<br />(reflesh)</a>
						</s:else>
				</div>
			</div>

			<div align="center">
			<table  class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									序号
									<br />
									No.
								</th>
								<th align="center">
									件号
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
								<s:if test="pageStatus=='noCard'">
									<th align="center">
										产品开始时间
									</th>
									<th align="center">
										入库时间
									</th>
								</s:if>
								<s:else>
									<th align="center">
										制卡时间
										<br />
										Card time
									</th>
								</s:else>
								<th align="center">
									数量
									<br />
									Quantity
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
							<s:iterator value="list" id="needJihuoPro" status="pageIndex">
								<s:if test="#pageIndex.first">
									<tr>
										<th colspan="11" bgcolor="red" style="color: #ffffff">
											待激活产品信息
										</th>
									</tr>
								</s:if>
								<s:if test="#pageIndex.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 50px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageIndex.index+1" />
								</td>
								<td>
									<a title="查看工序"
										href="ProcardAction!findProcardByRunCard.action?id=${needJihuoPro.id}&pageStatus=history">${needJihuoPro.markId}</a>
								</td>
								<td>
									${needJihuoPro.proName}
								</td>
								<td>
									${needJihuoPro.procardStyle}
								</td>
								<td>
									${needJihuoPro.productStyle}
								</td>
								<td>
									${needJihuoPro.selfCard}
								</td>
								<s:if test="pageStatus=='noCard'">
									<td>
										${needJihuoPro.jihuoDate}
									</td>
									<td>
										${needJihuoPro.needFinalDate}
									</td>
								</s:if>
								<s:else>
									<td>
										${needJihuoPro.procardTime}
									</td>
								</s:else>
								<td>
									${needJihuoPro.filnalCount}
								</td>
								<td>
									${needJihuoPro.status}
								</td>
								<td colspan="2">
									<a target="_toJh"
										href="ProcardAction!findPeople.action?id=${needJihuoPro.rootId}&pageStatus=jhBd">前往激活</a>
									<s:if
										test="#needJihuoPro.procardStyle=='总成'&&#needJihuoPro.status!='入库'">
										<s:if test="rootId!=null">
											<a
												onclick="if(window.confirm('本操作将还原计划数量,并删除整个bom数据,是否继续？')){window.location.href = 'ProcardAction!deleteprocardtree.action?id=${needJihuoPro.rootId}&pageStatus=${pageStatus}&viewStatus=${viewStatus}'};"
												target="showProView">删除</a>
										</s:if>
										<s:else>
											<a
												onclick="if(window.confirm('本操作将还原计划数量,并删除整个bom数据,是否继续？')){window.location.href = 'ProcardAction!deleteprocardtree.action?id=${needJihuoPro.id}&pageStatus=${pageStatus}&viewStatus=${viewStatus}'};"
												target="showProView">删除</a>
										</s:else>
									</s:if>
								</td>
								</tr>
								<s:if test="#pageIndex.last">
									<tr>
										<th colspan="11" bgcolor="green" style="color: #ffffff">
											生产中产品
										</th>
									</tr>
								</s:if>
							</s:iterator>
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
								<td>
									<a title="查看工序"
										href="ProcardAction!findProcardByRunCard.action?id=${pageProcard.id}&pageStatus=history">${pageProcard.markId}</a>
								</td>
								<td>
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
								<s:if test="pageStatus=='noCard'">
									<td>
										${pageProcard.jihuoDate}
									</td>
									<td>
										${pageProcard.needFinalDate}
									</td>
								</s:if>
								<s:else>
									<td align="center">
										${pageProcard.procardTime}
									</td>
								</s:else>
								<td>
									${pageProcard.filnalCount}
								</td>
								<td>
									${pageProcard.status}
								</td>
								<td colspan="2">
									<s:if test="list!=null">
										<s:if
											test="#pageProcard.procardStyle=='总成'&&#pageProcard.status!='入库'">
											<a
												href="ProcardAction!findWgWwPlan.action?id=${pageProcard.rootId}"
												target="showPlanView">采购计划</a>/</s:if>
										<a
											href="ProcardAction!findProcardView.action?id=${pageProcard.rootId}&pageStatus=history&viewStatus=${viewStatus}"
											target="showProView">生产进度</a>
										<s:if test="rootId!=null">
											<a
												onclick="if(window.confirm('本操作将还原计划数量,并删除整个bom数据,是否继续？')){window.location.href = 'ProcardAction!deleteprocardtree.action?id=${pageProcard.rootId}&pageStatus=${pageStatus}&viewStatus=${viewStatus}'};"
												target="showProView">删除</a>
										</s:if>
										<s:else>
											<a
												onclick="if(window.confirm('本操作将还原计划数量,并删除整个bom数据,是否继续？')){window.location.href = 'ProcardAction!deleteprocardtree.action?id=${pageProcard.id}&pageStatus=${pageStatus}&viewStatus=${viewStatus}'};"
												target="showProView">删除</a>
										</s:else>
									</s:if>
									<s:else>
										<a
											href="ProcardAction!findProcardForCard.action?id=${pageProcard.rootId}">已发卡号</a>
										<br />
										<a
											href="ProcardAction!findProcardView.action?id=${pageProcard.rootId}&pageStatus=history&viewStatus=${viewStatus}"
											target="showProView">生产进度</a>
										<s:if
											test="#pageProcard.procardStyle=='总成'&&#pageProcard.status!='入库'">
											<s:if test="#pageProcard.rootId!=null">
												<a
													onclick="if(window.confirm('本操作将还原计划数量,并删除整个bom数据,是否继续？')){window.location.href = 'ProcardAction!deleteprocardtree.action?id=${pageProcard.rootId}&pageStatus=${pageStatus}&viewStatus=${viewStatus}'};"
													target="showProView"> 删除</a>
											</s:if>
											<s:else>
												<a
													onclick="if(window.confirm('本操作将还原计划数量,并删除整个bom数据,是否继续？')){window.location.href = 'ProcardAction!deleteprocardtree.action?id=${pageProcard.id}&pageStatus=${pageStatus}&viewStatus=${viewStatus}'};"
													target="showProView"> 删除</a>
											</s:else>
										</s:if>
									</s:else>
									<s:if test="#pageProcard.jihuoDate!=null">
										<a href="ProcardAction!findPeople.action?id=${pageProcard.id}">
											领取人员</a>
									</s:if>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="11" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="11" align="center" style="color: red">
								</s:else>
								</td>
							</tr>
						</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function approval(month) {
	window.location.href = "proTryMakeScoreAction_approvalOneMonth.action?month=" + month;
}
</script>
	</body>
</html>
