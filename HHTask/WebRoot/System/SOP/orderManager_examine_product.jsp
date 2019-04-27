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
		<STYLE type="text/css">
.have {
	display: none;
}

.neworder {
	display: none;
}
</STYLE>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript">
//下载
function xiazai() {
	var orderFil = "${om.orderFil}";
	//var count1 =0;
	//count1 =  orderFil.lastIndexOf("\\");
	//alert(count1);
	//if(count1>0){
	//  orderFil = orderFil.substring(count1+1, orderFil.length());
	//} 
	//对中文进行加密
	var fileName1 = encodeURI(encodeURI(orderFil));
	location.href = "DownAction.action?directory=/upload/order/&fileName="
			+ fileName1;
<%--	location.href = "FileViewAction.action?FilePath=/upload/order/"--%>
<%--			+ fileName1;--%>

}
function updatedingdan(id, flag, cpage) {
	$("#xiugaiIframe").attr("src",
			"orderManager_initUpdate.action?id=" + id + "&flag=" + flag);
	chageDiv('block');
}

</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<h3>
					订单明细
				</h3>
				<table class="table" style="width: 40%;">
					<tr>
						<th align="right">
							客户名字：
						</th>
						<td align="left">
							<s:iterator id="cu" value="list1">
								<s:if test="#cu.id == om.custome.id">
												${cu.clientcompanyname}
										</s:if>
							</s:iterator>
						</td>
					</tr>
					<tr>
						<th align="right">
							订单编号(内部)：
							<br />
							(Internal Order Number):
						</th>
						<td align="left">
							${om.orderNum}
						</td>
					</tr>
					<tr>
						<th align="right">
							订单编号(外部)：
							<br />
							(External Order Number):
						</th>
						<td align="left">
							${om.outOrderNumber}
						</td>
					</tr>
					<tr>
						<th align="right">
							跟单人：
							<br />
							(With a single person):
						</th>
						<td align="left">
							${om.documentaryPeople}
						</td>
					</tr>
					<tr>
						<th align="right">
							开单人：
							<br />
							(Billing person):
						</th>
						<td align="left">
							${om.billingPeople}
						</td>
					</tr>
					<tr>
						<th align="right">
							订单类型：
							<br />
							(Order Type):
						</th>
						<td align="left">
							${om.orderType}
						</td>
					</tr>
					<tr>
						<th align="right">
							订单状态：
							<br />
							(Order Status):
						</th>
						<td align="left">
							${om.ep_statuts}
						</td>
					</tr>
					<s:if test="om.poHeaderId>0">
						<tr>
							<th align="right">
								B2B订单：
							</th>
							<td align="left">
								<a
									href="OrderForB2BAction!findPOById.action?orderForB2B.poHeaderId=${om.poHeaderId}&orderForB2B.poReleaseId=${om.poReleaseId}">B2B订单明细</a>
							</td>
						</tr>
					</s:if>
				</table>
				<input type="button" value="下载订单" style="width: 80px; height: 50px;"
					onclick="xiazai()" />
				<s:if test="tag=='KH'">
					<s:if test="om.ep_statuts=='同意' || om.ep_statuts=='审批中'">
						<%--						<input type="button" value="协商交付日期"--%>
						<%--							style="width: 120px; height: 50px;"--%>
						<%--							onclick="updatedingdan('${om.id}','${flag}','xieshang')" />--%>
					</s:if>
					<s:if test="om.dept=='客户'">
						<s:if test="om.ep_statuts=='同意' || om.ep_statuts=='审批中'">
							<input type="button" value="修改订单号"
								style="width: 120px; height: 50px;"
								onclick="updatedingdan('${om.id}','${flag}','dingdan')" />
						</s:if>
					</s:if>
				</s:if>
				<s:elseif test="om.dept!='客户'">
					<s:if test='om.ep_statuts=="同意" || om.ep_statuts!="审批中"'>
						<s:if test="strList1!=null && strList1.size()>0">
							<%--							<input type="button" value="协商交付日期${om.ep_statuts}"--%>
							<%--								style="width: 120px; height: 50px;"--%>
							<%--								onclick="updatedingdan('${om.id}','${flag}','xieshang')" />--%>

							<input type="button" value="修改订单号"
								style="width: 120px; height: 50px;"
								onclick="updatedingdan('${om.id}','${flag}','dingdan')" />

							<%--							<input type="button" value="删除订单"--%>
							<%--								style="width: 80px; height: 50px;"--%>
							<%--								onclick="deldingdan('${om.id}','${flag}','${cpage}','${tag}','${param.sz}')" />--%>
							<s:if test='om.ep_statuts=="计划完善"'>
								<input type="button" value="提交审核"
									style="width: 80px; height: 50px;"
									onclick="toAudit('${om.id}','${param.sz}')" />
								<input type="button" value="修改订单"
									style="width: 80px; height: 50px;"
									onclick="updatedingdan('${om.id}','${flag}','${param.sz}')" />
							</s:if>
						</s:if>
					</s:if>
					<s:if test="tags=='yes'">
						<input type="button" value="反审核" 
							onclick="fanshenhe('${om.id}')"
							style="width: 80px; height: 50px;"/>
					</s:if>
				</s:elseif>
				<s:if test="isallcx == 'halfcx'">
					<input type="button" value="打印订单（备货）"
						style="width: 120px; height: 50px;" onclick="printAdd('${bei}')" />
					<input type="button" value="打印订单（正式）"
						style="width: 120px; height: 50px;" onclick="printAdd('')" />
				</s:if>
				<s:else>
					<input type="button" value="打印订单"
						style="width: 80px; height: 50px;" onclick="printAdd('${bei}')" />
				</s:else>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								件号
							</td>
							<td align="center">
								业务件号
							</td>
							<td align="center">
								产品名称
							</td>
							<td align="center">
								数量
							</td>
							<td align="center">
								单位
							</td>
							<td align="center">
								不含税价
							</td>
							<td align="center">
								含税价
							</td>
							<td align="center">
								税率
							</td>
							<td align="center">
								总价
							</td>
							<td align="center">
								交付日期
							</td>
							<td align="center">
								关联订单
							</td>
							<td align="center">
								备注
							</td>
							<td align="center">
								状态
							</td>
							<td align="center">
								操作
							</td>
						</tr>
						<s:iterator value="list" id="pageList" status="pagepmStatus">
							<s:if test="#pagepmStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pagepmStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pagepmStatus.index+1" />
								</font>
							</td>
							<td>
								${pageList.pieceNumber}
							</td>
							<td>
								${pageList.ywMarkId}
							</td>
							<td>
								${pageList.name}
							</td>
							<td>
								${pageList.num}
							</td>
							<td>
								${pageList.danwei}
							</td>
							<td>
								<s:if test="strList!=null && strList.size()>0">
									<a href="javascript:;"
										onclick="tanchu('${pageList.priceId}','mingxi')"><fmt:formatNumber
											value="${pageList.bhsPrice}" pattern="#.0000"></fmt:formatNumber>
									</a>
								</s:if>
							</td>
							<td>
								<s:if test="strList!=null && strList.size()>0">
									<fmt:formatNumber value="${pageList.unit}" pattern="#.0000"></fmt:formatNumber>
								</s:if>
							</td>
							<td>
								<s:if test="strList!=null && strList.size()>0">
									<fmt:formatNumber value="${pageList.taxprice}" pattern="#.0000"></fmt:formatNumber>
									%
								</s:if>
							</td>
							<td>
								<s:if test="strList!=null && strList.size()>0">
									<fmt:formatNumber value="${pageList.bhsPrice*pageList.num}"
										pattern="#.00"></fmt:formatNumber>(不含税)
									<fmt:formatNumber value="${pageList.unit*pageList.num}"
										pattern="#.00"></fmt:formatNumber>（含税）
								</s:if>
							</td>
							<td id="td_paymentDate_${pageList.id}">
								<span id="span_paymentDate_${pageList.id}">${pageList.paymentDate}</span>
							</td>
							<td>
								<s:if test="#pageList.bhnumber!=null&&#pageList.bhnumber=='待关联'">
									<lable
										id="relateShow<s:property value="#pagepmStatus.index" />"></lable>
									<input type="button" value="关联备货"
										onclick="toRelateBh(<s:property value="#pagepmStatus.index" />,'${pageList.pieceNumber}',${pageList.id})">
								</s:if>
								<s:else>
									<font color="red">${pageList.bhnumber}</font>
								</s:else>
							</td>
							<td>
								${pageList.remark }
							</td>
							<td>
								${pageList.status}
							</td>
							<td>
								<s:if test="tag=='KH'">
									<s:if test="om.dept=='客户'">
										<a href="javascript:;"
											onclick="tanchu('${pageList.priceId}','mingxi')">价格明细</a>
									</s:if>
								</s:if>
								<s:elseif test="om.dept!='客户'">
									<a href="javscript:;" onclick="toupdatejq('${pageList.id}')">修改交期</a>
									<s:if test='#pageList.status=="计划完善"'>
										<a href="javascript:;" onclick="yhjh('${pageList.id}')">计划完善</a>
									</s:if>
									<s:else>
										<a href="javascript:;" onclick="yhjhView('${pageList.id}')">要货计划</a>
									</s:else>
									/<a href="javascript:;"
										onclick="shuaixin('${pageList.id}','${pageList.priceId}','${pageList.pieceNumber}')">刷新单价</a>
									<s:if test="#pageList.status!='取消'">
										/<a href="javascript:;"
											onclick="quxiao('${pageList.id}','${pageList.pieceNumber}')">取消</a>
									</s:if>
									/<a href="orderManager_JiuzhengMarkId.action?id=${pageList.id}">纠正件号</a>
								</s:elseif>
							</td>
							</tr>
						</s:iterator>

					</table>
					</div>
					<br>
					</div>
					<div id="showAddOrderdiv" style="display: none;">
						<hr />
						<div id="showAddOrder">
							<div align="center" style="font-size: 26px; height: 35px;">
								${companyInfo.name}
							</div>
							<div align="center" style="font-size: 14px; height: 35px;">
								${companyInfo.englishName}
							</div>
							<table class="table">
								<tr>
									<th colspan="10" align="center" style="font-size: 20px;">
										<%--										<s:if test="bei=='have'">--%>

										<%--										</s:if>--%>
										<%--										<s:else>--%>
										<%--											<s:if test='om.orderType=="正式"'>--%>

										<%--											</s:if>--%>
										<s:if test='om.orderType=="备货"||om.orderType=="预测"'>
											<span>预测订单新增通知单 </span>
										</s:if>
										<s:else>
											<span class="have">订单消备库通知单</span>
											<span class="neworder">订单新增通知单 </span>
										</s:else>
<%--										</s:else>--%>
									</th>
								</tr>
								<tr>
									<%--									<s:if test="bei=='have'">--%>
									<td align="left" colspan="7" class="have">
										REV:01
									</td>
									<td colspan="2" class="have">
										QP720100-D
									</td>
									<%--									</s:if>--%>
									<%--									<s:else>--%>
									<s:if test='om.orderType=="正式"'>
										<td align="left" colspan="7" class="neworder">
											REV:01
										</td>
										<td colspan="2" class="neworder">
											QPYW02000-A
										</td>
									</s:if>
									<s:elseif test='om.orderType=="备货"'>
										<td align="left" colspan="7" class="neworder">
											REV:01
										</td>
										<td colspan="2" class="neworder">
											QP720100-A
										</td>
									</s:elseif>
									<s:elseif test='om.orderType=="试制"'>
										<td align="left" colspan="7" class="neworder">
											REV:01
										</td>
										<td colspan="2" class="neworder">
											QP720100-A
										</td>
									</s:elseif>
									<%--									</s:else>--%>

								</tr>
								<tr>
									<td align="left" colspan="5">
										客户名称:${om.clientName}
									</td>
									<td align="right">
										客户下单人:
									</td>
									<td colspan="3">
										${om.clientFzr}
									</td>
								</tr>
								<tr>
									<td align="left" colspan="5">
										订单编号: ${om.outOrderNumber}
									</td>
									<td align="right"eft">
										订单编码:
									</td>
									<td colspan="3"></td>
								</tr>
								<tr>
									<td align="left" colspan="5">
										下单日期: ${om.addTime}
									</td>
									<td align="right"eft">
										客户地址:
									</td>
									<td colspan="3">
										${om.clientDz}
									</td>
								</tr>
								<tr>
									<td align="left" colspan="5">
										销售单号: ${om.orderNum}
									</td>
									<td align="right"eft">
										订单别:
									</td>
									<td colspan="3"></td>
								</tr>
							</table>
							<table class="table">
								<tr>
									<th style="width: 10px;">
										序号
									</th>
									<th>
										产品编码
									</th>
									<th style="width: 45px;">
										产品
										<Br />
										版本号
									</th>
									<th style="width: 45px;">
										是否
										<br />
										消备库
									</th>
									<th>
										产品描述
									</th>
									<th style="width: 45px;">
										数量
									</th>
									<th style="width: 45px;">
										单位
									</th>
									<th style="width: 45px;">
										单价(元)
									</th>
									<th>
										交货期
									</th>
								</tr>
								<s:iterator value="list" id="pageList" status="pageStatus">
									<s:if test="#pageStatus.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#pageStatus.index+1" />
									</td>
									<td>
										<s:if test="#pageList.ywMarkId==null||#pageList.ywMarkId==''">
										${pageList.pieceNumber}
										</s:if>
										<s:else>${pageList.ywMarkId}</s:else>
									</td>
									<td>
										${pageList.banben}
									</td>
									<td>
										<s:if test="#pageList.bhnumber!=null&&#pageList.bhnumber!=''">
											是
										</s:if>
									</td>
									<td>
										${pageList.name}
									</td>
									<td style="width: 45px;">
										<SPAN class="zs">${pageList.num-pageList.cxCount}</SPAN>
										<span class="bh">${pageList.cxCount}</span>
									</td>
									<td>
										${pageList.danwei}
									</td>
									<td>
									</td>
									<td style="width: 80px;">
										${pageList.paymentDate}
									</td>

								</s:iterator>
								<%--								<s:if test="bei=='have'">--%>
								<tr style="height: 20px;" class="have">
									<td colspan="9">
										备注:冲销备库:销售单号 &nbsp;&nbsp;
										<s:iterator value="list" id="pageLists" status="pageStatus">
												${pageLists.bhnumber}<br />
										</s:iterator>
									</td>
								</tr>
								<%--								</s:if>--%>
								<%--								<s:else>--%>
								<tr class="neworder">
									<td colspan="3">
										<input type="checkbox">
										NPI订单
										<s:if test='om.orderType=="试制"'>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="checkbox">
												试制订单
											</s:if>
										<s:elseif test='om.orderType=="正式"'>
										</s:elseif>
									</td>
									<td colspan="6">
										确认:
									</td>
								</tr>
								<%--								</s:else>--%>
								<tr>
									<td colspan="2">
										检验方式:
									</td>
									<td colspan="3">

									</td>
									<td colspan="2">
										送货地址:
									</td>
									<td colspan="2">

									</td>
								</tr>
								<%--								<s:if test="bei=='have'">--%>
								<%--								</s:if>--%>
								<%--								<s:else>--%>
								<tr class="neworder">
									<th rowspan="14">
										订
										<br />
										单
										<br />
										说
										<br />
										明
										<br />
									</th>
									<td>
										部门
									</td>
									<td colspan="5">
										评审意见
									</td>
									<td colspan="2">
										评审人(日期)
									</td>
								</tr>
								<tr class="neworder">
									<td rowspan="3">
										PMC部
									</td>
									<td colspan="5">
										意外灾害风险评估对交期是否有影响? 是
										<input type="checkbox">
										否
										<input type="checkbox">
									</td>
									<td rowspan="3" colspan="2"></td>
								</tr>
								<tr class="neworder">
									<td colspan="5">
										生产能力(数量、质量、交期、运输方式)是否具备? 是
										<input type="checkbox">
										否
										<input type="checkbox">
									</td>
								</tr>
								<tr class="neworder">
									<td colspan="5" style="height: 20px;">
										备注:${om.remark}
									</td>
								</tr>
								<tr class="neworder">
									<td rowspan="4">
										工程部
									</td>
									<td colspan="5">
										是否完成从研发到工艺的各种(包括图纸。BOM、技术要求)准备? 是
										<input type="checkbox">
										否
										<input type="checkbox">
									</td>
									<td rowspan="4" colspan="2"></td>
								</tr>
								<tr class="neworder">
									<td colspan="5">
										是否有客户变更? 是
										<input type="checkbox">
										否
										<input type="checkbox">
										<br />
										是否需工艺优化? 是
										<input type="checkbox">
										否
										<input type="checkbox">
									</td>
								</tr>
								<tr class="neworder">
									<td colspan="5">
										是否含有特殊专用物料? 是
										<input type="checkbox">
										否
										<input type="checkbox">
										<br />
										是否要FAI验证? 是
										<input type="checkbox">
										否
										<input type="checkbox">
									</td>
								</tr>
								<tr class="neworder">
									<td colspan="5" style="height: 20px;">
										备注:
									</td>
								</tr>
								<tr class="neworder">
									<td rowspan="2">
										生产部
									</td>
									<td colspan="5">
										生产路径、标准是否明确? 是
										<input type="checkbox">
										否
										<input type="checkbox">
										<br />
										各种工装、夹具是否齐全 是
										<input type="checkbox">
										否
										<input type="checkbox">
									</td>
									<td rowspan="2" colspan="2"></td>
								</tr>
								<tr class="neworder">
									<td colspan="5" style="height: 20px;">
										备注:
									</td>
								</tr>
								<tr class="neworder">
									<td rowspan="2">
										品质部
									</td>
									<td colspan="5">
										检验人员是否具备? 是
										<input type="checkbox">
										否
										<input type="checkbox">
										<br />
										检测设备是否具备? 是
										<input type="checkbox">
										否
										<input type="checkbox">
									</td>
									<td rowspan="2" colspan="2"></td>
								</tr>
								<tr class="neworder">
									<td colspan="5" style="height: 20px;">
										备注:
									</td>
								</tr>
								<%--							</s:else>--%>
								<tr>
									<th colspan="9" style="height: 30px; font-size: 20px;"
										align="left">
										&nbsp;&nbsp;&nbsp;&nbsp;${pageList.remark}
									</th>
								</tr>
								<tr>
									<th colspan="9" align="left"
										style="height: 40px; font-size: 20px;">
										<div style="height: 40px; padding-top: 10px">
											<div style="width: 40%; float: left;">
												&nbsp;&nbsp;批准:
											</div>
											<div style="width: 30%; float: left;">
												审核:
											</div>
											<div style="width: 30%; float: right;">
												制单:&nbsp;&nbsp;${om.billingPeople}
											</div>
										</div>
									</th>
									<!--									<th colspan="3" align="left">-->
									<!--										&nbsp;批准:-->
									<!--									</th>-->
									<!--									<th colspan="1" align="left">-->
									<!--										审核:-->
									<!--									</th>-->
									<!--									<th colspan="5" align="left">-->
									<!--										制单:${om.billingPeople}-->
									<!--									</th>-->
								</tr>
							</table>
					<div>
						备注:
						<br />
						1、无总经理批准的订单任何人不得安排生产。
						<br />
						2、本订单原件必须本部门存档、财务存档、PMC存档。
						<br />
						3、公司内部需要使用订单则由PMC复印下发。
						<br />
						4、此订单评审时,请不要填写英文.
					</div>
					</div>
					<div align="center">
						<input value="打印" type="button"
							onclick="pagePrint('showAddOrder')" class="input" />
					</div>
					</div>
					<%@include file="/util/foot.jsp"%>
					</center>
					<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
					<SCRIPT type="text/javascript">
function tanchu(id,statue){
	$("#xiugaiIframe").removeAttr("src");
	$("#xiugaiIframe").attr("src", "PriceAction!findPriceById.action?id="+id+"&statue="+statue);
	chageDiv('block');
}	

function yhjh(id){
	$("#xiugaiIframe").removeAttr("src");
	$("#xiugaiIframe").attr("src", "orderManager_getBetweenDate.action?id="+id);
	chageDiv('block');
}	
function yhjhView(id){
	$("#xiugaiIframe").removeAttr("src");
	$("#xiugaiIframe").attr("src", "internalOrder_findIodByProductId.action?id="+id);
	chageDiv('block');
	$("#xiugaiIframe").css("height","1200px");
}	

function quxiao(id,pieceNumber){
	if(window.confirm("是否要取消该订单下的"+pieceNumber+"产品?")){
		window.location.href = "orderManager_removeProduct.action?pm.id="+id+"&id=${id}&status=${status}&flag=${status}";
	}
}
function updatedingdan(id,flag,status) {
	$("#xiugaiIframe").attr("src","orderManager_initUpdate.action?id=" + id +"&flag="+flag+"&status="+status);
	chageDiv('block');
}

function fanshenhe(id){
	window.location.href = "orderManager_fanshen.action?id="+id+"&tags=${tags}";
}

function shuaixin(productId,priceId,pieceNumber){
	if(confirm("是否要刷新"+pieceNumber+"产品的单价?")){
		$.ajax({
		 type:"POST", 
  		url:"orderManager_shuaxin.action",
  		data:{'pm.id':productId,id:priceId},
  		dataType:"json",
  		success:function(data){
  			if(data=="刷新单价成功!"){
  					alert(data);
  					window.location.reload();
  				}else{
  					alert(data);
  				}
  			}
  		})
	}
	
}
function xiazai() {
	var orderFil = "${om.orderFil}";
	//var count1 =0;
	//count1 =  orderFil.lastIndexOf("\\");
	//alert(count1);
	//if(count1>0){
	//  orderFil = orderFil.substring(count1+1, orderFil.length());
	//} 
	//对中文进行加密
	var fileName1 = encodeURI(encodeURI(orderFil));
<%--	location.href = "DownAction.action?directory=/upload/order/&fileName="--%>
<%--			+ fileName1;--%>
	location.href = "FileViewAction.action?FilePath=/upload/order/"
			+ fileName1;

}
function printAdd(str){
	if (str == "") {
		$(".neworder").show();
		$(".have").hide();
		$(".bh").hide();
		$(".zs").show();
	} else if (str == "have") {
		$(".neworder").hide();
		$(".have").show();
		$(".zs").hide();
		$(".bh").show();
	}
	$("#showAddOrderdiv").show();
}
function deldingdan(id, flag, cpage, tag, sz) {
	if (window.confirm('确定删除该订单吗?')) {
		window.location = "orderManager_del.action?id=" + id + "&flag=" + flag
				+ "&cpage=" + cpage + "&tag=" + tag + "&status=" + sz;
	}
}
function toAudit(id,sz) {
	if (window.confirm('确定提交该订单进行审核吗?')) {
		window.location = "orderManager_updateOrderForsubmit.action?id=" + id+"&status=" + sz ;
	}
}

function toRelateBh(index,markId,id){
	$("#xiugaiIframe").attr(
				"src",
				"orderManager_toRelateBh.action?markId="+markId+"&index="+index+"&tag=update&id="+id);
	chageDiv('block');
}

function toupdatejq(id){
	$("#td_paymentDate_"+id).html('<input type="text" style="width:100px;" id="input_paymentDate_'+id+'" value='+$('#span_paymentDate_'+id).html()+' onClick="WdatePicker({dateFmt:&apos;yyyy-MM-dd&apos;,skin:&apos;whyGreen&apos;})"  class="Wdate"  />' +
	' <input type="button"  value = "修改" onclick="updatejq('+id+')" />');
	
}
function updatejq(id){
	var paymentDate =	$("#input_paymentDate_"+id).val();
	if(paymentDate == ''){
		alert('请先填写交付日期;')
	}else{
		$.ajax( {
		type : "POST",
		url : "orderManager_updatejq.action",
		data : {
				id:id,
				endTime:paymentDate
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#td_paymentDate_"+id).html('<span id="span_paymentDate_'+id+'">'+data.paymentDate+'</span>');
			}else{
				alert('修改交期失败!')
			}
		}
	})
	}
	
}
	</SCRIPT>
	</body>
</html>
