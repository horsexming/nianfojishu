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
		<style type="text/css">
			@media screen {
	.notprint {
		display: inline;
		cursor: pointer;
	}
	.notprintTd {
		background-color: #d0d0d0;
	}
	.notprintTr {
		background-color: #d0d0d0;
	}
}

.text1 {
	width: 120px;
	overflow: hidden;
	text-overflow: ellipsis;
}

.text2 {
	width: 80px;
	overflow: hidden;
	text-overflow: ellipsis;
}

<%--
#shenhe { --%> <%--
	position: absolute; --%> <%--
	top: 50px; --%> <%--
	right: 10px; --%> <%--
	z-index: 10; --%> <%--
	transform: rotate(12deg); --%> <%--
	opacity: 1.0;
	--%>
	<%--
}

--%>
.table th,.table td {
	border: solid #000;
	border-width: 1 1px 1px 1;
	padding: 0px;
}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="PrintedOutAction_addprint.action" method="post" >
					<p style="font-size: 20px;">
						${companyInfo.name}
					</p>
					<p style="font-size: 20px;">
						${companyInfo.englishName}
					</p>
					<p style="font-size: 20px;">
						${poor.type}
						<input type="hidden" value="${poor.type}" name="poor.type"/>
					</p>
					<br/>
					<table class="table">
						<tr>
							<th colspan="8" align="left"style="border:hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REV:01
							</th>
							<th colspan="5" align="left" style="border:hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test='poor.type =="外购入库单"'>
									QPWL01000-p
								</s:if>
								<s:elseif test='poor.type =="外委入库单"'>
									QPWL01000-P
								</s:elseif>
								<s:elseif test='poor.type =="生产领料单"'>
									QPWL01000-Y
								</s:elseif>
								<s:elseif test='poor.type =="产品入库单"'>
									QPWL01000-O
								</s:elseif>
								<s:elseif test='poor.type =="辅料入库单"'>
									QPWL01000-P
								</s:elseif>
								<s:elseif test='poor.type =="生产退料单"'>
									QPWL01000-P
								</s:elseif>
							</th>
							
						</tr>
						<tr>
							<th colspan="8" align="left"style="border:hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								${poor.planNum}
								<input type="hidden" value="${poor.planNum}" name="poor.planNum"/>
							</th>
							<s:if test='poor.type =="产品入库单" '>
							<th colspan="5" align="left" style="border:hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;人:
								<input type="text" value="${poor.rukuUsers}" name="poor.rukuUsers" />
							</th>
							</s:if>
							<s:elseif test='poor.type =="外购入库单" || poor.type =="外委入库单" || poor.type =="辅料入库单"
							|| poor.type =="生产退料单"'>
								<th colspan="5" align="left" style="border-right: hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;付款日期
								<input type="text" value="${poor.fkriqi}"
									name="poor.fkriqi" />
								</th>
							</s:elseif>
						</tr>
						<tr>
						<s:if test='poor.type =="产品入库单" '>
							<th colspan="4" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;交货单位:
								<input type="text" value="${poor.jhdw}"
									 name="poor.jhdw" />
							</th>
							<th colspan="4" align="left" style="border-left:hidden;border-top: hidden;border-right: hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;制表日期:
								<input type="text" value="${poor.riqi}" class="Wdate"
									name="poor.riqi" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</s:if>
						<s:elseif test='poor.type =="外购入库单" || poor.type =="外委入库单" || poor.type =="辅料入库单"
						|| poor.type =="生产退料单"'>
							<th colspan="8" align="left" style="border-left: hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;供应商编码: 
								<input type="text" value="${poor.gyscode}" 
									 name="poor.gyscode" />
							</th>
						</s:elseif>
						
						
							<s:if test='poor.type =="产品入库单" '>
								<th colspan="5" align="left" style="border-right: hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;入库班组:
								<input type="text" value="${poor.rukuGroup}"
									name="poor.rukuGroup" />
								</th>
							</tr>
							</s:if>
							<s:elseif test='poor.type =="外购入库单" || poor.type =="外委入库单" 
							|| poor.type =="辅料入库单"|| poor.type =="生产退料单"'>
								<th colspan="5" align="left" style="border:hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;收料仓库:
								<input type="text" value="${poor.rukuGroup}"
									name="poor.rukuGroup" />
								</th>
								</tr>
								<tr >
									<th colspan="4" align="left" style="border-right: hidden; border-top: hidden;border-left: hidden; " >
										&nbsp;&nbsp;&nbsp;&nbsp;供&nbsp;&nbsp;&nbsp;应&nbsp;&nbsp;&nbsp;商:
									<input type="text" value="${poor.gysname}"
									name="poor.gysname" />
									</th>
									<th colspan="4" align="left" style="border-left:hidden;border-top: hidden;border-right: hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;制表日期:
								<input type="text" value="${poor.riqi}" class="Wdate"
									name="poor.riqi" readonly="readonly"/>
								</th>
									<th colspan="5" align="left" style="border-right: hidden; border-top: hidden;border-left: hidden; " >
										&nbsp;&nbsp;&nbsp;&nbsp;送货单号:
									<input type="text" value="${poor.shPlanNum}"
									name="poor.shPlanNum" />
									</th>
								</th>
								</tr>
							</s:elseif>
						<tr>
							<th>
								序号
							</th>
							<s:if test='poor.type =="产品入库单"' >
								<th>
									外部订单号
								</th>
								<th>
									内部订单号
								</th>
								<th>
									业务件号
								</th>
							</s:if>
							<s:else>
								<th>
									采购订单号
								</th>
							</s:else>
							<th>
								件号
							</th>
							<th>
								生产批次
							</th>
							<th>
								产品名称
							</th>
							<s:if test='poor.type =="外委入库单" || poor.type =="生产退料单"' >
								<th>
									工序名称
								</th>
							</s:if>
							<th>
								规格型号
							</th>
							<th>
								版本
							</th>
							<th>
								图号
							</th>
							<th>
								单位
							</th>
							<th>
								数量
							</th>
							<th>
								仓区
							</th>
							<s:if test="noLook==true">
							<th>
								单价（不含税）
							</th>
							<th>
								税额
							</th>
							<th>
								税率
							</th>
							<th>总价</th>
							</s:if>
							<th>
								备注
							</th>
						</tr>
						<s:iterator value="listIn" id="pageList" status="statusSdf">
							<tr>
								<th>
									${statusSdf.index+1}
									<input type="hidden" value="${pageList.entiyId}" name="poor.printedOutList[${statusSdf.index}].entiyId" />
								</th>
								<s:if test='poor.type =="产品入库单"' >
								<th>
									${pageList.neiOrderNum}
									<input type="hidden" value="${pageList.neiOrderNum}" name="poor.printedOutList[${statusSdf.index}].neiOrderNum" />
								</th>
								<th>
									${pageList.waiOrderNum}
									<input type="hidden" value="${pageList.waiOrderNum}" name="poor.printedOutList[${statusSdf.index}].waiOrderNum" />
								</th>
								<th>
									${pageList.ywmarkId}
									<input type="hidden" value="${pageList.ywmarkId}" name="poor.printedOutList[${statusSdf.index}].ywmarkId" />
								</th>
								</s:if>
								<s:else>
									<th>
										${pageList.cgoderNumber}
										<input type="hidden" value="${pageList.cgoderNumber}" name="poor.printedOutList[${statusSdf.index}].cgoderNumber" />
									</th>
								</s:else>
								<th>
									${pageList.markId}
									<input type="hidden" value="${pageList.markId}" name="poor.printedOutList[${statusSdf.index}].markId" />
								</th>
								<th>
									${pageList.selfCard}
									<input type="hidden" value="${pageList.selfCard}" name="poor.printedOutList[${statusSdf.index}].selfCard" />
								</th>
								<th>
									${pageList.proNmae}
									<input type="hidden" value="${pageList.proNmae}" name="poor.printedOutList[${statusSdf.index}].proNmae" />
								</th>
								<s:if test='poor.type =="外委入库单" || poor.type =="生产退料单"' >
									<th>
										${pageList.processName}
									<input type="hidden" value="${pageList.processName}" name="poor.printedOutList[${statusSdf.index}].processName" />
									</th>
								</s:if>
								<th>
									${pageList.format}
									<input type="hidden" value="${pageList.format}" name="poor.printedOutList[${statusSdf.index}].format" />
								</th>
								<th>
									${pageList.banbenNum}
									<input type="hidden" value="${pageList.banbenNum}" name="poor.printedOutList[${statusSdf.index}].banbenNum" />
								</th>
								<th>
									${pageList.tuhao}
									<input type="hidden" value="${pageList.tuhao}" name="poor.printedOutList[${statusSdf.index}].tuhao" />
								</th>
								
								<th>
									${pageList.unit}
									<input type="hidden" value="${pageList.unit}" name="poor.printedOutList[${statusSdf.index}].unit" />
								</th>
								<th>
									${pageList.num}
									<input type="hidden" value="${pageList.num}" name="poor.printedOutList[${statusSdf.index}].num" />
								</th>
								<th>
									${pageList.cangqu}
									<input type="hidden" value="${pageList.cangqu}" name="poor.printedOutList[${statusSdf.index}].cangqu" />
								</th>
								<s:if test="noLook==true">
									<th>
										${pageList.bhsPrice}
										<input type="hidden" value="${pageList.hsPrice}" name="poor.printedOutList[${statusSdf.index}].hsPrice"	/>
										<input type="hidden" value="${pageList.bhsPrice}" name="poor.printedOutList[${statusSdf.index}].bhsPrice"	/>	
									</th>
									<th>
										${pageList.shuie}
										<input type="hidden" value="${pageList.shuie}" name="poor.printedOutList[${statusSdf.index}].shuie"	/>							
									</th>
									<th>
										${pageList.taxprice}
										<input type="hidden" value="${pageList.taxprice}" name="poor.printedOutList[${statusSdf.index}].taxprice"	/>							
									</th>
									<th>
										${pageList.hsTotalPrice}
										<input type="hidden" value="${pageList.hsTotalPrice}" name="poor.printedOutList[${statusSdf.index}].hsTotalPrice"	/>							
									</th>
								</s:if>
								<th>
									<input type="text" value=""
										name="poor.printedOutList[${statusSdf.index}].rmeak" />
								</th>
							</tr>
						</s:iterator>
						<s:if test="noLook==true">
							<tr>
								<th colspan="14" align="right">合计：</th>
								<th colspan="10"><input type="hidden" name="poor.totalMoney" value="${poor.totalMoney}"/>${poor.totalMoney}</th>
							</tr>
						</s:if>
						<tr style="border-left: hidden; border-right: hidden;">
							<th align="left" colspan="20" style="border-right: hidden;">
								<s:if test='poor.type =="产品入库单"'>
									物料员:<input type="text" value="" name="poor.wlUsers" />
								</s:if>
								<s:else>
									采购员:<input type="text" value="${poor.cgUserName}" name="poor.cgUserName" />
								</s:else>
								
								&nbsp;&nbsp;&nbsp;&nbsp;部门主管:
								<input type="text" value="" name="poor.bmzg" />
								&nbsp;&nbsp;&nbsp;&nbsp; 仓管:
								<input type="text" value="${poor.cgName}" name="poor.cgName" />
								&nbsp;&nbsp;&nbsp;&nbsp; 品管:
								<input type="text" value="${poor.pgName}" name="poor.pgName" />
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<s:if test='poor.type =="产品入库单"'>
									制单:
									<input type="text" value="${Users.name}" name="poor.addUsers" />
								</s:if>
								<s:else>
									制单:
									<input type="text" value="${poor.addUsers}" name="poor.addUsers" />
								</s:else>
							</th>
						</tr>
						<tr style="border: hidden;">
							<th colspan="20" align="left" style="border-right: hidden;">
									第一白联：仓管
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								第二红联：财务&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test='poor.type =="产品入库单"'>
									第三黄联：领料员
								</s:if>
								<s:else>
									第三黄联：仓管
								</s:else>
								
							</th>
						</tr>
					</table>
					<input type="hidden" value="${poor.className}" name="poor.className"/>
					<input type="submit" value="打印" class="input" onclick="todisabled(this)"/>
				</div>
				</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			$(function(){
				$("th").dblclick(function(){
			        var tdclass =	$(this).attr("class")
					var cellIndex =	this.cellIndex;
			       if(cellIndex>=3){
			    	   if(tdclass!='notprintTd'){
							$(this).addClass("notprintTd");
							$(this).parent().parent().find("tr th").each(function(){
								if(this.cellIndex === cellIndex ){
									$(this).addClass("notprintTd");
								}
							})
						}else{
							$(this).removeClass();
							$(this).parent().parent().find("tr th").each(function(){
								if(this.cellIndex === cellIndex ){
									$(this).removeClass();
								}
							})
						}
			       }
				
			    });
			});
		</script>
	</body>
</html>
