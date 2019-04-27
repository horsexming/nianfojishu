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
	<style type="text/css">
	
		body{text-align:center;}		
		
	</style>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
			<h3>
					开票未通过产品管理<br/>(unpass bill product Management)
				</h3>
				<s:if test="successMessage!=null">
					<h2>
				       <br/><font color="red">${successMessage}</font>
				    </h2>
					</s:if>
			<form action="huikuanAction!checkBillCountShow.action"
					method="post">
					<table width="99%" class="table">
						<tr>
							<td align="right">
								客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户:
							</td>
							<td>
								<input type="text" style="width: 150px;" name="taHk.hkClientComp" value="${taHk.hkClientComp}">
							</td>
							<td align="right">
								零&nbsp;&nbsp;&nbsp;&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
							</td>
							<td>
								<input type="text" style="width: 150px;" name="tahkSellSta.hkSellMarkId" value="${tahkSellSta.hkSellMarkId}">
							</td>
							<td align="right">
								送货单号:
							</td>
							<td>
								<input type="text" name="tahkSellSta.hkSellSendId" value="${tahkSellSta.hkSellSendId}"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								订单号:
							</td>
							<td>
								<input type="text" style="width: 150px;" name="tahkSellSta.hkSellOrderId" value="${tahkSellSta.hkSellOrderId}">
							</td>
							<td align="right">
								负责人:
							</td>
							<td>
								<input type="text" style="width: 150px;" name="taHk.hkApplier" value="${taHk.hkApplier}">
							</td>
							<td align="right">
								开票编号
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="taHk.hkNum" value="${taHk.hkNum}">
							</td>
						</tr>
						<tr>
							<td align="right">
								申请开票开始时间
							</td>
							<td>
								<div>
									<input class="Wdate" type="text" name="startDate" id="startDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</div>
							</td>
							
							<td colspan="4" rowspan="2" align="center">
								<input type="submit" value="查询"
									style="width: 100px; height: 60px">
								<input type="reset" value="重置"
									style="width: 100px; height: 60px">
							</td>
						</tr>
						<tr>
							<td align="right">
								申请开票结束时间
							</td>
							<td>
								<div>
									<input class="Wdate" type="text" name="endDate" id="endDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</div>
							</td>
						</tr>
					</table>
				</form>
				<br>
			</div>
			<br>
			<center>
					<table width="99%" class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<td>
								序号
							</td>
							<td>
								客户名称
							</td>
							<td>
								开票时间
							</td>
							<td>
								开票金额
							</td>
							<td width="60px">
								关联人
							</td>
							<td width="60px">
								状态 
							</td>							
							<td>
								开票编号
							</td>
							<td>回款率</td>
							<td>
								操作
							</td>
						</tr>
						<s:iterator id="hk" value="listhk" status="statussdf">
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
								<s:property value="hkClientComp" />
							</td>
							<td>
								${hk.hkApplyDate}
							</td>
							<td>
								${hk.hkBillMoney}
							</td>
							<td align="left" width="60px">
								${hk.hkClientName}
							</td>
							<td align="left" width="60px">
								${hk.hkStatus}
							</td>
							<td>
								${hk.hkNum}
							</td>
							<td><s:if test="%{null!=#hk.hkTrackRate}" >${hk.hkTrackRate}%</s:if></td>
							<td>
							<a href="huikuanAction!showOneHK.action?id=<s:property value='#hk.id' />&tag=query"
									target="_blank">查看明细</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='#hk.id' />&tag=noteStaDetail"
									target="_blank">送货清单</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='#hk.id' />&tag=queryInvo"
									target="_blank">查看发票信息</a>
								/<a href="huikuanAction!toCheckBillCount.action?taHk.id=<s:property value='#hk.id' />"
									target="_blank">核对开票数量</a>	
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
					
				</center>
		</div>
		<%@include file="/util/foot.jsp"%>
	
	</body>
		<SCRIPT type="text/javascript">
	function daochuExec(){
		 var startDate=document.getElementById("startDate").value;
		 var endDate=document.getElementById("endDate").value;
		 window.location.href="huikuanAction!exportExcel.action?startDate="+startDate+"&endDate="+endDate+"";
	}
	</SCRIPT>
</html>
