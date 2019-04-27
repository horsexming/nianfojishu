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
				<form action="proTryMakeScoreAction_showList.action" method="post">
					<table class="table" align="center">
					<tr>
					       <td align="center">
								项目名称（project name）：
								<input type="text" name="proTryMakeScore.proName" value="<s:property value="proTryMakeScore.proName"/>" />
							</td>
							<td align="center">
								项目编号（project number）：
								<input type="text" name="proTryMakeScore.proNum" value="<s:property value="proTryMakeScore.proNum"/>" />
							</td>
						</tr>
						<tr>
					       <td align="center">
								零件号（project name）：
								<input type="text" name="partNum" value="<s:property value="partNum"/>" />
							</td>
							<td align="center">
								月份（project name）：
								<input type="text"  class="Wdate" name="proTryMakeScore.month" value="<s:property value="proTryMakeScore.month"/>"
								id="month" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
					       <td align="center">
								是否已还有订单（project name）：
								<select name="status">
								<s:if test="status==0">
								 <option value="0">
								  否
								 </option>
								 <option value="1">
								  是
								 </option>
								  <option value="-1">
								  全
								 </option>
								</s:if>
								<s:elseif test="status==-1">
								 <option value="-1">
								  全
								 </option>
								 <option value="0">
								  否
								 </option>
								 <option value="1">
								  是
								 </option>
								</s:elseif>
								<s:else>
								<option value="1">
								  是
								 </option>
								  <option value="0">
								  否
								 </option>
								  <option value="-1">
								  全
								 </option>
								</s:else>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
							 <input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加(add)" onclick="add()"/>
							</td>
						</tr>
					</table>
				</form>
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
						        完成率
						   <br />
						        （completion rate）
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
								${ptmsVo.completionrate}
							</td>
							<td>
								${ptmsVo.orderNo}
							</td>
							<td colspan="14">
							</td>
								<td  colspan="2">
								<!--
								<input type="button" value="修改项目(update)"
										style="width: 60px; height: 30px;"
										onclick="updatePro(${ptmsVo.id})" />
										  -->
									<input type="button" value="删除(delete)"
										style="width: 60px; height: 30px;"
										onclick="todelete(${ptmsVo.id},${cpage})" />
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
								${ptmsVo.completionrate}
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
										onclick="allBonus(${ptmsVo.id})" />
										<!--
								<input type="button" value="修改项目(update)"
										style="width: 60px; height: 30px;"
										onclick="updatePro(${ptmsVo.id})" />
										-->
									<input type="button" value="删除(delete)"
										style="width: 60px; height: 30px;"
										onclick="todelete(${ptmsVo.id},${cpage})" />
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

function todelete(id,cpage) {
	window.location.href = "proTryMakeScoreAction_delete.action?proTryMakeScore.id="
			+ id+"&cpage="+cpage;
}
function updatePro(id) {
	window.location.href = "proTryMakeScoreAction_toupdatePro.action?proTryMakeScore.id="
			+ id;
}
function allBonus(id) {
	window.location.href = "proTryMakeScoreAction_allBonus.action?proTryMakeScore.id="
			+ id;
}

function add() {
	window.location.href = "proTryMakeScoreAction_toadd.action"
}
</script>
	</body>
</html>
