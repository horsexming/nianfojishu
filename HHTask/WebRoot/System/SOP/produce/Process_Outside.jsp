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
		<SCRIPT type="text/javascript">
			$(function (){
				var procardStyle="${procard.procardStyle}"
				if(procardStyle=="总成"||procardStyle=="组合"||procardStyle=="自制"){
					$("#showZong").show();
				}else if(procardStyle=="自制"){
					$("#showZi").show();
				}
			});
		</SCRIPT>
	</head>
	<body>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<h1>
					工序外委管理
				</h1>
				<div>
					<form action="ProcardAction!waiwei.action" method="post">
						请刷流水卡片:
						<input id="runCard" name="cardNumber" value="${cardNumber}" />
					</form>
				</div>
				<div id="showZong" style="border: solid #000 1px; display: none;">
					<div align="center" style="border-bottom: solid #000 1px;">
						工 艺 流 水 卡 片
					</div>
					<div style="font-weight: bolder;">
						名称:${procard.proName} &nbsp;&nbsp;件号:${procard.markId}
						&nbsp;&nbsp; 批次:${procard.selfCard} &nbsp;&nbsp;
						卡片类型:${procard.procardStyle} &nbsp;&nbsp;
						产品类型:${procard.productStyle} &nbsp;&nbsp;
						车型:${procard.carStyle}&nbsp;&nbsp; 状态:
						<font color="red">${procard.status}</font>&nbsp;&nbsp;
					</div>
					<table class="table" style="width: 100%;">
						<tr>
							<th>
								最大数量
							</th>
							<th>
								${procard.maxCount} ${procard.unit}
							</th>
							<th>
								实际数量
							</th>
							<th>
								${procard.filnalCount}
							</th>
						</tr>
						<tr align="center">
							<td>
								零组件
							</td>
							<td>
								名称
							</td>
							<td>
								数量
							</td>
							<td>
								卡片类型
							</td>
						</tr>
						<s:iterator value="procardList" id="pageProcardTem">
							<tr align="center">
								<th>
									${pageProcardTem.markId}
								</th>
								<th>
									${pageProcardTem.proName}
								</th>
								<th>
									${pageProcardTem.corrCount}
								</th>
								<th>
									${pageProcardTem.procardStyle}
								</th>
							</tr>
						</s:iterator>
						<tr>
							<th>
								&nbsp;
							</th>
							<th>
								&nbsp;
							</th>
							<th>
								&nbsp;
							</th>
							<th>
								&nbsp;
							</th>
						</tr>
					</table>
					<form action="OSWorkAction!addOsw.action" method="post"
						onsubmit="return checkForm()">
						<table class="table">
							<tr>
								<th colspan="14" height="40px;">
									工序信息
								</th>
							</tr>
							<tr align="center">
								<th>
									选择
								</th>
								<th>
									工序号
								</th>
								<th>
									名称
								</th>
								<th>
									可领数量
								</th>
								<th>
									已领数量
								</th>
								<th>
									提交数量
								</th>
								<th>
									不合格量
								</th>
								<th>
									初领时间
								</th>
								<th>
									提交时间
								</th>
								<th>
									并行
								</th>
								<th>
									生产类型
								</th>
								<th>
									状态
								</th>
								<th>
									领取人
								</th>
							</tr>
							<s:iterator value="list" id="pageProcess">
								<tr align="center">
									<th height="30px">
										<s:if test="#pageProcess.productStyle!='自制'">
											<s:if test="#pageProcess.productStyle=='外委'">
												<s:if
													test="#pageProcess.status=='初始'||#pageProcess.status=='自检'||#pageProcess.status=='已领'">
													<s:if
														test="#pageProcess.totalCount != #pageProcess.submmitCount">
														<input type="checkbox" name="processIds"
															value="${pageProcess.id}" />
													</s:if>
												</s:if>
											</s:if>
										</s:if>
									</th>
									<th height="30px">
										${pageProcess.processNO}
									</th>
									<th>
										${pageProcess.processName}
									</th>
									<th>
										${pageProcess.totalCount}
									</th>
									<th>
										${pageProcess.applyCount}
									</th>
									</th>
									<th>
										${pageProcess.submmitCount}
									</th>
									<th>
										${pageProcess.breakCount}
									</th>
									<th>
										${pageProcess.firstApplyDate}
									</th>
									<th>
										${pageProcess.submitDate}
									</th>
									<th>
										${pageProcess.processStatus}
									</th>
									<th>
										${pageProcess.productStyle}
									</th>
									<th>
										${pageProcess.status}
									</th>
									<th>
										${pageProcess.usernames}
									</th>
								</tr>
							</s:iterator>
						</table>
						<input type="hidden" name="osWork.markID"
							value="${procard.markId}">
						<input type="hidden" name="osWork.partName"
							value="${procard.proName}">
						<input type="hidden" name="osWork.lotId"
							value="${procard.selfCard}">
						<input type="hidden" name="osWork.unit" value="${procard.unit}">
						<input type="hidden" name="osWork.status" value="外委">
						外委数量：
						<input type="text" id="outSourceCount"
							name="osWork.outSourceCount" value="${procard.filnalCount}">
						外委合同编号：
						<input id="contractNO" name="osWork.contractNO" />
						外委厂家：
						<input id="outScourceComp" name="osWork.outScourceComp" />
						外委说明：
						<input id="more" name="osWork.explain" style="width: 400px;" />
						<br />
						<input id="subIn" type="submit" value="添加" class="input" />
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	var markId = "${procard.markId}";
	if (markId != "") {
		$.ajax( {
			type : "POST",
			url : "PriceAction!findPriceByMarkId.action",
			dataType : "json",
			data : {
				markId : markId
			},
			success : function(msg) {
				if (msg != null) {
					$("#contractNO").val($(msg).attr("contractNumber"));
					$("#outScourceComp").val($(msg).attr("type"));
				} else {
					//alert("未找到该件号对应的合同信息!无法外委!");
			$("#contractNO").attr("disabled", "true");
			$("#outScourceComp").attr("disabled", "true");
			$("#more").attr("disabled", "true");
			$("#subIn").attr("disabled", "true");

		}
	}
		});
	}
})

function checkForm() {
	if ($("#outSourceCount").val() == "") {
		alert("请填写外委数量!");
		$("#outSourceCount").select();
		return false;
	} else if ($("#contractNO").val() == "") {
		alert("请填写外委合同编号!");
		$("#contractNO").select();
		return false;
	} else if ($("#outScourceComp").val() == "") {
		alert("请填写外委厂家!");
		$("#outScourceComp").select();
		return false;
	} else {
		return true;
	}
}
</script>
	</body>
</html>
