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
					<a href="proTryMakeScoreAction_showList.action"
						style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					项目试制评审
					<br />
					(project try make  Management)
				</h3>
				<table width="100%" border="1" style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							项目小组<br />
							(Group)
						</td>
						<td align="center">
							项目名称
							<br />
							(project name)
						</td>
						<td align="center">
							月份<br />
							(Group)
						</td>
						<td align="center">
							项目编号
							<br />
							（project number）
						</td>
						<td align="center">
							客户名称
							<br />
							（customer name）
						</td>
						<td align="center">
							试制订单号
							<br />
							（try make order）
						</td>
						<td align="center">
							零件号
							<br />
							（part number）
						</td>
						<td align="center">
							入库数量
							<br />
							（input number）
						</td>
						<td align="center">
							零件名称
							<br />
							（part name）
						</td>
						<td align="center">
							项目阶段
							<br />
							（project status）
						</td>
						<td align="center">
							工艺负荷分值
							<br />
							（Craft load score ）
						</td>
						<td align="center">
						             占比
							<br />
							（rate）
						</td>
						<td align="center">
							产能负荷系数
							<br />
							（Productivity load score）
						</td>
						<td align="center">
							占比
							<br />
							（rate）
						</td>
						<td align="center">
							奖金金负荷系数
							<br />
							（Bonus load score）
						</td>
						<td align="center">
							占比
							<br />
							（rate）
						</td>
						<td align="center">
							客户重要系数
							<br />
							（Customer importance score）
						</td>
						<td align="center">
							占比
							<br />
							（rate）
						</td>
						<td align="center">
						   总比得分
							<br />
							（total score）
						</td>
						<td align="center">
							奖金额
							<br />
							（Bonus）
						</td>
						
						<td align="center" colspan="3">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<s:iterator value="ptmsVoList" id="ptmsVo" status="pageStatus1">
					
					  <s:if test="#ptmsVo.tryMakeVo==null">
					  <s:if test="#pageStatus1.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus1.index+1" />
							</td>
							<td>
								${ptmsVo.groupName}
							</td>
							<td>
								${ptmsVo.proName}
							</td>
							<td>
								${ptmsVo.month}
							</td>
							<td>
								${ptmsVo.proNum}
							</td>
							<td>
								${ptmsVo.cusName}
							</td>
							<td>
								${ptmsVo.orderNo}
							</td>
							<td colspan="14">
							</td>
								<td  colspan="2">
									<input type="button" value="删除(delete)"
										style="width: 60px; height: 30px;"
										onclick="todelete(${ptmsVo.id })" />
								</td>
							</tr>
					  </s:if>
					  
					  <s:iterator value="#ptmsVo.tryMakeVo" id="tmVo" status="pageStatus2">
					  <s:if test="#pageStatus1.index%2==1&#pageStatus2.index==0">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<s:if test="#pageStatus2.index==0">
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								<s:property value="#pageStatus1.index+1"/>
							</td>
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								${ptmsVo.groupName}
							</td>
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								${ptmsVo.proName}
							</td>
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								${ptmsVo.month}
							</td>
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								${ptmsVo.proNum}
							</td>
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								${ptmsVo.cusName}
							</td>
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								${ptmsVo.orderNo}
							</td>
							</s:if>
							<td>
							${tmVo.markId}
							</td>
							<td>
							${tmVo.inputNum}
							</td>
							<td>
							${tmVo.partName}
							</td>
							<td>
							${tmVo.projectStatu}
							</td>
							<td>
							${tmVo.craftLoad}
							</td>
							<td>
							${tmVo.cLoadRate}
							</td>
							<td>
							${tmVo.productivityLoad}
							</td>
							<td>
							${tmVo.pLoadRate}
							</td>
							<td>
							${tmVo.bonusLoad}
							</td>
							<td>
							${tmVo.bLoadRate}
							</td>
							<td>
							${tmVo.cusImportance}
							</td>
							<td>
							${tmVo.cImRate}
							</td>
							<td>
							${tmVo.totalRate}
							</td>
							<td>
							${tmVo.bonus}
							</td>
							<s:if test="#pageStatus2.index==0">
								<td  rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>" colspan="3">
								<input type="button" value="所有月奖金额(all bonus)"
										style="width: 60px; height: 30px;"
										onclick="allBonus(${ptmsVo.proName})" />
								<input type="button" value="修改项目(update)"
										style="width: 60px; height: 30px;"
										onclick="updatePro(${ptmsVo.id })" />
									<input type="button" value="删除(delete)"
										style="width: 60px; height: 30px;"
										onclick="todelete(${ptmsVo.id })" />
								</td>
								</s:if>
							</tr>
					    
					  </s:iterator>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="23" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="23" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="23" align="center" style="color: red">
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
		<script type="text/javascript">

function todelete(id) {
	window.location.href = "proTryMakeScoreAction_delete.action?proTryMakeScore.id="
			+ id;
}
function updatePro(id) {
	window.location.href = "proTryMakeScoreAction_toupdatePro.action?proTryMakeScore.id="
			+ id;
}
function allBonus(proName) {
	window.location.href = "proTryMakeScoreAction_allBonus.action?proTryMakeScore.proName="
			+ proName;
}

function add() {
	window.location.href = "proTryMakeScoreAction_toadd.action"
}
</script>
	</body>
</html>
