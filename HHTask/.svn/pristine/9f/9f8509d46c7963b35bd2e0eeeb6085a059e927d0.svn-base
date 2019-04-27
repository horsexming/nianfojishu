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
				<form action="PrintedOutAction_findAllPrintedOutList.action"
					method="post">
					<table class="table">
						<tr>
							<th>
								编号:
							</th>
							<td>
								<input type="text" name="printedOut.planNum"
									value="${printedOut.planNum}" />
							</td>
							<th>
								客户:
							</th>
							<td>
								<input type="text" name="printedOut.kehuNmae"
									value="${printedOut.kehuNmae}" />
							</td>
						</tr>
						<tr>
							<th>
								业务件号:
							</th>
							<td>
								<input type="text" name="printedOut.ywmarkId"
									value="${printedOut.ywmarkId}" />
							</td>
							<th>
								产品名称:
							</th>
							<td>
								<input type="text" name="printedOut.proNmae"
									value="${printedOut.proNmae}" />
							</td>
						</tr>
						<tr>
							<th>
								单据类型:
							</th>
							<td>
								<select name="printedOut.type">
									<option>
										${printedOut.type}
									</option>
									<option></option>
									<option value="销售出库单">
										销售出库单
									</option>
									<option value="外购入库单">
										外购入库单
									</option>
									<option value="外委入库单">
										外委入库单
									</option>
									<option value="产品入库单">
										产品入库单
									</option>
									<option value="生产领料单">
										生产领料单
									</option>
									<option value="调拨单">
										调拨单
									</option>
									<option value="其它出库单">
										其它出库单
									</option>
								</select>
							</td>
							<th>
								件号:
							</th>
							<td>
								<input type="text" name="printedOut.markId"
									value="${printedOut.markId}" />
							</td>
						</tr>
						<tr>
							<th>
								仓区:
							</th>
							<td>
								<select name="printedOut.cangqu" id="goodHouseName">
									<option></option>
								</select>
							</td> 
							<th>送货单号:</th>
							<td>
								<input type="text" name="printedOut.shPlanNum"
									value="${printedOut.shPlanNum}" />
							</td>
						</tr>
							<tr>
							<th>
								日期从:
							</th>
							<td>
								<input type="text" name="startTime"
									value="${startTime}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									class="Wdate"/>
							</td> 
							<th>止:</th>
							<td>
								<input type="text" name="endTime"
									value="${endTime}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									class="Wdate"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							业务件号
						</th>
						<th>
							件号
						</th>
						<th>
							编号
						</th>
						<th>
							产品名称
						</th>
						<th>
							规格型号
						</th>
						<th>
							数量
						</th>
						<th>
							客户
						</th>
						<th>
							单位
						</th>
						<th>
							仓区
						</th>
						<th>
							日期
						</th>
						<th>
							内部订单号
						</th>
						<th>
							外部订单号
						</th>
						<th>
							送货单号
						</th>
						<th>
							打印次数
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="pageList" value="printList"
						status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${pageList.ywmarkId}
						</td>
						<td>
							${pageList.markId}
						</td>
						<td>
							${pageList.planNum}
						</td>
						<td>
							${pageList.proNmae}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.num}
						</td>
						<td>
							${pageList.kehuNmae}
						</td>
						<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.cangqu}
						</td>
						<td>
							${pageList.printedOutOrder.riqi}
						</td>
						<td>
							${pageList.neiOrderNum}
						</td>
						<td>
							${pageList.waiOrderNum}
						</td>
						<td>
							${pageList.shPlanNum}
						</td>
						<td>
							${pageList.printcount}
						</td>
						<td>
							<a href="PrintedOutAction_findPoorandPo.action?id=${pageList.printedOutOrder.id}">补打</a>/
							<a href="PrintedOutAction_delPrintedOut.action?printedOut.id=${pageList.id}" onclick="return confirm('确定要删除吗？')">删除</a>
						</td>
					</s:iterator>
					<tr>
								<td colspan="20" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

								</td>
							</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	
	var 	wareHouseName = "成品库,备货库,半成品库,外协库,外购件库"
	
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:wareHouseName
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodHouseName").empty();
				$("#goodHouseName").append('<option value=""></option>');
				$(data).each(function(){
						$("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
				$("#goodHouseName").tinyselect();
			}
		}
	});
})

</SCRIPT>
	</body>
</html>
