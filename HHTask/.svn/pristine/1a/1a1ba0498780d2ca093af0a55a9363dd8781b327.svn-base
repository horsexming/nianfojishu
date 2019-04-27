<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px;" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<s:if test="osa.machineFile!=null&&osa.machineFile!=''">
							<a
<%--								href="DownAction.action?fileName=${osa.machineFile}&directory=/upload/osa/machine/"><font>维修报价单</font>--%>
								href="FileViewAction.action?FilePath=/upload/osa/machine/${osa.machineFile}"><font>维修报价单</font>
							</a>
						</s:if>
						<s:if test="osa.othersLackFile!=null&&osa.othersLackFile!='' ">
							<a
<%--								href="DownAction.action?fileName=${osa.othersLackFile}&directory=/upload/osa/othersLack/"><font>其他原因文件</font>--%>
								href="FileViewAction.action?FilePath=/upload/osa/othersLack/${osa.othersLackFile}"><font>其他原因文件</font>
							</a>
						</s:if>
						<s:if test="osa.ospriceFile!=null&&osa.ospriceFile!=''">
							<a
<%--								href="DownAction.action?fileName=${osa.ospriceFile}&directory=/upload/osa/infor/"><font>外委报价单</font>--%>
								href="FileViewAction.action?FilePath=/upload/osa/infor/${osa.ospriceFile}"><font>外委报价单</font>
							</a>
						</s:if>
						<s:if
							test="osa.addNewMachineFile!=null&&osa.addNewMachineFile!=''">
							<a
<%--								href="DownAction.action?fileName=${osa.addNewMachineFile}&directory=/upload/osa/abilityLack"><font>新增设备报价单</font>--%>
								href="FileViewAction.action?FilePath=/upload/osa/abilityLack/${osa.addNewMachineFile}"><font>新增设备报价单</font>
							</a>
						</s:if>

						<a
							href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
							style="color: #ffffff">外委产品申报单</a>
					</div>
				</div>
				<div align="center">
					<div id="printDiv">
						<table width="100%" border="0" class="table">
							<tr>
								<th colspan="6" align="right">
									<span style="font-size: 20px; font-weight: bold;">
										外委产品信息登记 </span>
									<img
										src="<%=request.getContextPath()%>/barcode.action?msg=${osa.number}&type=code128"
										height="50px" width="360px" />
								</th>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									基本信息
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									申报部门:
								</th>
								<td>
									${osa.dept}
								</td>
								<th align="right">
									客户名称:
								</th>
								<td>
									${osa.customer}
								</td>
								<th align="right">
									加急:
								</th>
								<td>
									${osa.isJiaji}

								</td>

							</tr>
							<tr>

								<th align="right">
									零件号:
								</th>
								<td>
									${osa.markID}
								</td>
								<th align="right">
									工序号:
								</th>
								<td>
									${osa.processNO}
								</td>
								<th align="right">
									交付数量:
								</th>
								<td>
									${osa.deliveryCount}
								</td>
							</tr>
							<tr>

								<th align="right">
									交付时间:
								</th>
								<td>
									${osa.deliveryDate}
								</td>

								<th align="right">
									订单号:
								</th>
								<td>
									${osa.orderId}
								</td>
								<th align="right">
									预测产量:
								</th>
								<td>
									${osa.yuceCount}
								</td>


							</tr>
							<tr>

								<th align="right">
									加工周期:
								</th>
								<td>
									${osa.procardCycle}
								</td>

								<th align="right">
									产品生命周期:
								</th>
								<td>
									${osa.productCysle}
								</td>



							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									申报原因 （${osa.timeLimit}）
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									故障原因:
								</th>
								<td>
									${osa.machineFail}
								</td>
								<th align="right">
									报警单号:
								</th>
								<td>
									${osa.alertNum}
								</td>
								<th align="right">
									维修周期:
								</th>
								<td>
									${osa.repairCycle}
									<font color="red"> /天</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									维修预算:
								</th>
								<td>
									${osa.repairBudget}
								</td>
								<th align="right">
									产能不足:
								</th>
								<td>
									${osa.abilityLack}
								</td>

								<th align="right">
									其他原因:
								</th>
								<td>
									${osa.othersLack}
								</td>
							</tr>

							<tr>
								<th colspan="6" align="left">
									<br />
									成本核算
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									外委单件人工:
								</th>
								<td>
									${osa.osOneRengong}
									<font color="red"> /元</font>
								</td>
								<th align="right">
									单件材料:
								</th>
								<td>
									${osa.osOneMater}
									<font color="red"> /元</font>
								</td>
								<th align="right">
									单件其他:
								</th>
								<td>
									${osa.osOneOthsers}
									<font color="red"> /元</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									自制所需人数:
								</th>
								<td>
									${osa.selfRenshu}
									<font color="red"> /元</font>
								</td>
								<th align="right">
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
								<th align="right">
									单件人工:
								</th>
								<td>
									<fmt:formatNumber type="number"
										value="${osa.selfOneRengong*osa.procardCycle/(21.75*osa.deliveryCount) } "
										maxFractionDigits="2" />

									<font color="red"> 元/件</font>
								</td>

							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									自制新增成本
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									增加人数:
								</th>
								<td>
									${osa.addWorker}
								</td>
								<th align="right">
									新增人工成本:
								</th>
								<td>
									${osa.oneWorkerMoney}
									<font color="red"> 元/月</font>
								</td>
								<th align="right">
									新增单件人工:
								</th>
								<td>
									<fmt:formatNumber type="number"
										value="${osa.oneWorkerMoney*osa.procardCycle/(21.75*osa.deliveryCount) } "
										maxFractionDigits="2" />
									<font color="red"> 元/件</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									增加设备成本:
								</th>
								<td>
									${osa.addMachineCost}
									<font color="red"> /元</font>
								</td>
								<th align="right">
									设备折旧成本:
								</th>
								<td>
									${osa.zhejiuCost}
									<font color="red"> /元</font>
								</td>
								<th align="right">
									单件折旧成本:
								</th>
								<td>
									<fmt:formatNumber type="number"
										value="${osa.zhejiuCost*osa.procardCycle/(21.75*osa.deliveryCount) } "
										maxFractionDigits="2" />

									<font color="red"> 元/件</font>
								</td>
							</tr>


							<tr>
								<th align="right">
									增加辅助人数:
								</th>
								<td>
									${osa.addAssistWorker}
								</td>
								<th align="right">
									辅助人工成本:
								</th>
								<td>
									${osa.oneAssistWorkerMoney}
									<font color="red"> 元/月</font>
								</td>
								<th align="right">
									单件辅助成本:
								</th>
								<td>

									<font color="red"> /元</font>
								</td>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									盈亏平衡数据分析
									<br />
								</th>
							</tr>
							<tr>
								<th>
									外委单件合计
								</th>
								<th>
									${osa.osOneHeji}/元
								</th>
								<th>
									自制单件合计
								</th>
								<th>
									${osa.selfOneHeji}/元
								</th>
								<th>
									单件差额
								</th>
								<th>
									<s:if test="osa.selfOneHeji!=null">
										<fmt:formatNumber type="number"
											value="${osa.selfOneHeji-osa.osOneHeji } "
											maxFractionDigits="2" />
									</s:if>
								</th>
							</tr>
							<tr>
								<th>
									批次差额
								</th>
								<th>
									<s:if test="osa.selfOneHeji!=null">
										<fmt:formatNumber type="number"
											value="${(osa.selfOneHeji-osa.osOneHeji)*osa.deliveryCount } "
											maxFractionDigits="2" />
									</s:if>
								</th>
								<th>
									外委平衡数
								</th>
								<th>
									${osa.addChengMinBalanceCount}/件
								</th>
								<td colspan="2">
									<font color="red"> 注：外委交付数超过外委盈亏平衡数量时，<br />不允许外委，不提供打印服务!</font>
								</td>
							</tr>
							<s:if test="%{osa.deliveryCount<osa.addChengMinBalanceCount}">
								<tr>
									<th colspan="6">
										<br />
										<br />
										相关部门会签：_________________________________________________
										生产副总核准：_______________ 审批：_______________
									</th>
								</tr>
							</s:if>
						</table>
					</div>
					<s:if test="%{osa.deliveryCount<osa.addChengMinBalanceCount}">
						<input type="button" value="打印" class="button"
							onclick="pagePrint('printDiv')">
					</s:if>

					<div>

					</div>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
