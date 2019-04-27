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
			<s:if test="flag!='view'">
				<h3>
					项目试制评审
					<br />
					(project try make  Management)
				</h3>
				<form action="proTryMakeScoreAction_toApprovalOrPrint.action" method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								月份（project name）：
								<input type="text"  class="Wdate" name="month" value="<s:property value="month"/>"
								id="month" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
							<td align="center" colspan="">
							 <input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							<s:if test="epId!=null">
							 <input type="button" value="该月审批动态" style="width: 100px; height: 40px;" onclick="window.location.href='CircuitRunAction_findAduitPage.action?id=${epId}'">
							</s:if>
							</td>
						</tr>
					</table>
				</form>
				</s:if>
				<div id="printDiv">
				<table width="100%" border="1" style="border-collapse: collapse;">
				<s:if test="successMessage!=null">
						<tr>
							<td colspan="23" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
					<tr bgcolor="#c0dcf2" height="50px">
						<s:if test="flag!='print'&&flag!='view'">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							项目小组<br />
							(Group)
						</td>
						</s:if>
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
							申请数量
							<br />
							（approval number）
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
						<s:if test="flag!='view'">
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
						</s:if>
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
							状态
							<br />
							(status)
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
							<s:if test="flag!='print'&&flag!='view'">
							<td>
								<s:property value="#pageStatus1.index+1" />
							</td>
							<td>
								${ptmsVo.groupName}
							</td>
							</s:if>
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
								${ptmsVo.approvalStatus}
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
							<s:if test="flag!='print'&&flag!='view'">
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								<s:property value="#pageStatus1.index+1"/>
							</td>
							<td rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>">
								${ptmsVo.groupName}
							</td>
							</s:if>
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
							<a href="proTryMakeScoreAction_showProduct.action?id=${tmVo.id}">${tmVo.markId}</a>
							</td>
							<td>
							${tmVo.inputNum}
							</td>
							<td>
							${tmVo.approvalNum}
							</td>
							<td>
							${tmVo.partName}
							</td>
							<td>
							${tmVo.projectStatu}
							</td>
							<s:if test="flag!='view'">
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
							</s:if>
							<td>
							${tmVo.totalRate}
							</td>
							<td>
							${tmVo.bonus}
							</td>
							<s:if test="#pageStatus2.index==0">
								<td  rowspan="<s:property value="#ptmsVo.tryMakeVo.size()"/>" colspan="3">
								${ptmsVo.approvalStatus}
								</td>
								</s:if>
							</tr>
					    
					  </s:iterator>
					</s:iterator>
					<s:if test="flag=='print'">
					<tr>
							<td colspan="23" align="center">签名：
					<s:if test="qianmingList!=null">
                     <s:iterator value="qianmingList" id="qmUrl">
				    <img src="<%=path%><s:property value="#qmUrl"/>" style="width: 50px;height: 50px"/>
                     </s:iterator>
                    </s:if>
                    </td>
                    </tr>
                    </s:if>
						<s:if test="errorMessage!=null">
					<tr>
							<td colspan="23" align="center" style="color: red">
								${errorMessage}
						</td>
					</tr>
						</s:if>
					
				</table>
				</div>
				<table class="table" align="center">
				<tr><td align="center">
				<s:if test="flag=='approval'">
				<input type="button" value="申请审批" onclick="approval('${month}')"/>
				</s:if>
				<s:elseif test="flag=='print'">
				<input type="button" value="打印" onclick="pagePrint('printDiv','sy')"/>
				<input type="button" value="奖金分配" onclick="distributionBonus()"/>
				</s:elseif>
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
		function distributionBonus(){
			window.location.href = "proTryMakeScoreAction_distributionBonus.action?month=${month}";
		}
</script>
	</body>
</html>
