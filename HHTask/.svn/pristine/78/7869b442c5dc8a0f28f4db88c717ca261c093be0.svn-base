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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看同一物品的历价格</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
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
					<a href="oaReimBursementAction!findBusinessList.action"
						style="color: #ffffff">报账明细</a> &nbsp;&nbsp;&nbsp;
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">

				<form
					action="oaReimBursementAction!findBusinessList.action?powerTag=${powerTag}"
					method="post">
					<table class="table">
						<tr>
							<th>
								物品编号<BR/>item number
							</th>
							<th>
								<select name="business.oaproductnumber" style="width: 130px;"
									id="oaproductnumber"
									onMouseOver="createDept('oaproductnumber','oaReimBursementAction!selectBusinessItem.action?tag=oaproductnumber&powerTag=${powerTag}')">
									<option value="">
										选择物品编号
									</option>
									<option value="${business.oaproductnumber}">
										${business.oaproductnumber}
									</option>
							</th>
							<th>
								物品名称<br/>item Name
							</th>
							<th>
								<select name="business.oaproductName" style="width: 130px;"
									id="oaproductName"
									onMouseOver="createDept('oaproductName','oaReimBursementAction!selectBusinessItem.action?tag=oaproductName&powerTag=${powerTag}')">
									<option value="">
										选择物品名称
									</option>
									<option value="${business.oaproductName}">
										${business.oaproductName}
									</option>
								</select>
							</th>
							<th>
								状态<br/>state
							</th>
							<th>
								<select name="business.oastatus" style="width: 130px;"
									id="oastatus"
									onMouseOver="createDept('oastatus','oaReimBursementAction!selectBusinessItem.action?tag=oastatus&powerTag=${powerTag}')">
									<option value="">
										选择状态
									</option>
									<option value="${business.oastatus}">
										${business.oastatus}
									</option>
								</select>
							</th>
							<th rowspan="3">
								<input type="button" value="查看(Query)" onclick="findHuizong(this.form)" style="width:60px;height:30px；;margin-top:5px;"/> &nbsp;					
								<input type="button" value="导出Excel(Export Excel)" onclick="exportExcel(this.form);todisabledone(this)" data="downData" >
							</th>
						</tr>
						<tr>
							<th>
								发票号<br/>invoice number
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="business.oainvoicenumber
									value="
									${business.oainvoicenumber }" size="80px" />
							</th>
							<th>
								规格<br/>Specifications
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="business.oaspecification"
									value="${business.oaspecification}" size="80px" />
							</th>
							<th>
								报账人<br/>Leader
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="business.oausername" value="${business.oausername}"
									size="80px" />
							</th>
						</tr>
						<tr>
							<th>
								厂家<br/>manufacturers
							</th>
							<th>
								<select name="business.oafactory" style="width: 130px;"
									id="oafactory"
									onMouseOver="createDept('oafactory','oaReimBursementAction!selectBusinessItem.action?tag=oafactory&powerTag=${powerTag}')">
									<option value="">
										选择厂家
									</option>
									<option value="${business.oafactory}">
										${business.oafactory}
									</option>
								</select>
							</th>
							<th>
								日期从<br/>Date from 
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
							<th>
								到<br/>To
							</th>
							<th>
								<input class="Wdate" type="text" name="endDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号<br>
									No.</th>
								<th align="center">
									物品编号
									 <br> item number 
								</th>
								<th align="center">
									物品名称
									<br> item Name
								</th>
								<th align="center">
									规格
									 <br> Specifications 
								</th>
								<th align="center">
									单位
									<br> units 
								</th>
								<th align="center">
									数量
									<br> number 
								</th>
								<th align="center">
									单价
									<br> Price
								</th>
								<th align="center">
									总金额
									 <br> total amount of 
								</th>
								<th align="center">
									状态
									<br> state 
								</th>
								<th align="center">
									负责人
									<br> Leader 
								</th>
								<th align="center">
									厂家
									<br> manufacturers 
								</th>
								<th align="center">
									发票号
									<br> invoice number
								</th>
								<th align="center">
									报账日期
									 <br> reimbursement date
								</th>
								<th align="center">
									合同编号
									 <br> Contract Number 
								</th>
								<th align="center">
									比价
								<br>Search 
								
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="busi">
									<s:if test="#se.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#se.index+1" />
									</td>
									<td>
										${busi.oaproductnumber}
									</td>
									<td>
										${busi.oaproductName}
									</td>
									<td>
										${busi.oaspecification}
									</td>
									<td>
										${busi.oaunit}
									</td>
									<td>
										${busi.oaquantity}
									</td>
									<td>
										${busi.oaunitprice}
									</td>
									<td>
										${busi.oatotalMon}
									</td>
									<td>
										${busi.oastatus}
									</td>
									<td>
										${busi.oausername}
									</td>
									<td>
										${busi.oafactory}
									</td>
									<td>
										${busi.oainvoicenumber}
									</td>
									<td>
										${busi.oadate}
									</td>
									<td>
										${busi.oahetongnumber}
									</td>
									<td><input type="button" id="" value="历史价格(Historical Prices)" onclick="bijia(${busi.id})" /></td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="16" align="right">
										共
										<s:property value="total" />
										页 第
										<s:property value="cpage" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />

									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td colspan="16" style="font-size: 15px; color: red;">
										对不起，没有查到相关的申报信息
									</td>
								</tr>
							</s:else>
						</table>

						</div>
						<br>
						</div>
						<%@include file="/util/foot.jsp"%>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
	
	
	function bijia(obj) {
	var id = obj.valueOf();
	$("#showProcess").attr("src",
			"oaReimBursementAction!findSameProductPrice.action?id=" + id);
	chageDiv('block');
	}
	function findHuizong(objForm) {	
			objForm.action="oaReimBursementAction!findBusList.action?tag=list";
			objForm.submit();	
			}
		
	function exportExcel(objForm) {	
		objForm.action="oaReimBursementAction!exportEXCEL.action?tag=excel";
		objForm.submit();	
			}
</script>
</html>
