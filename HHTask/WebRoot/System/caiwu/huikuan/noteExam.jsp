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
			<form action="huikuanAction!hkExam.action?tag=<s:property value='tag' />"
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
								<input type="text" style="width: 150px;" name="tahkSellSta.hkSellOutOrderId" value="${tahkSellSta.hkSellOrderId}">
							</td>
							<td align="right">
								负责人:
							</td>
							<td>
								<input type="text" style="width: 150px;" name="taHk.hkApplier" value="${taHk.hkApplier}">
							</td>
							<td align="right">
								状态:
							</td>
							<td>
								<select style="width: 150px;" name="taHk.hkStatus">
									<option></option>
									<option value="${taHk.hkStatus}">${taHk.hkStatus}</option>
									<option value="开票申请">
										开票申请
									</option>
									<option value="可开票">
										可开票
									</option>
									<option value="回款中">
										回款中
									</option>
									
									<option value="回款完成">
										回款完成
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								开票编号
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="taHk.hkNum" value="${taHk.hkNum}">
							</td>
							<td colspan="4" rowspan="5" align="center">
								<input type="submit" value="查询"
									style="width: 100px; height: 60px">
								<input type="reset" value="重置"
									style="width: 100px; height: 60px">
								<s:if test="%{tag=='LDquery' || tag=='zkquery' }">
								
								</s:if>
								<s:else>
								<input type="button"  style="width: 90px; height: 60px;"
									value="导出" class="input" onclick="daochuExec();todisabledone(this)" data="downData" />
								<a href="huikuanAction!hkExam.action?tag=all">查看全部</a>
								<s:if test="%{tag=='FDInvoice' || tag=='invoFD' }">
								<a href="huikuanAction!hkExam.action?tag=invoHistory">查看开票记录</a>
								</s:if>
								</s:else>
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
			<!-- 总经理管理界面 -->
			<s:if test="%{tag=='manager'}">
			<table width="99%" class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<td>
								序号
							</td>
							<td>
								客户名称
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
								回款周期
							</td>
							<td>
								倒计时
							</td>
							<td>回款率</td>
							<td>
								操作
							</td>
						</tr>
						 <tr bgcolor="green">
								<th colspan="9" align="center">
									<font color="#ffffff">回款未完成明细</font>
								</th>
							</tr>
						<s:iterator id="listAlerm" value="listAlerm" status="alert">
						<s:if test="#alert.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
						<td>
								<s:property value="#alert.index+1" />
							</td>
							<td>
								<s:property value="hkClientComp" />
							</td>							
							<td>
								${hkBillMoney}
							</td>
							<td align="left" width="60px">
								${hkClientName}
							</td>
							<td align="left" width="60px">
								${hkStatus}
							</td>
							<td>								
								<s:if test="%{null!=hkPayCycle}">
								${hkPayCycle }天
								</s:if>
							</td>
							<td>${hkCountDown }天</td>
							<td><s:if test="%{null!=hkTrackRate}" >${hkTrackRate}%</s:if></td>
							<td>
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=query"
									target="_blank">查看明细</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=noteStaDetail"
									target="_blank">送货清单</a>
								
								
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=queryInvo"
									target="_blank">查看发票信息</a>
									<s:if test="%{0.0!=hkTrackRate}">	
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=querybackMoney"
									target="_blank">回款记录</a>				
								</s:if>				
							
								
							</td>
						</tr>
						</s:iterator>
						<!-- 
						 <tr bgcolor="green">
								<th colspan="9" align="center">
									<font color="#ffffff">回款已完成明细</font>
								</th>								
							</tr>
						<s:iterator id="listcomp" value="listcomp" status="comp">
						<s:if test="#comp.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#comp.index+1" />
							</td>
							<td>
								<s:property value="hkClientComp" />
							</td>							
							<td>
								${hkBillMoney}
							</td>
							<td align="left" width="60px">
								${hkClientName}
							</td>
							<td align="left" width="60px">
								${hkStatus}
							</td>
							<td>								
								<s:if test="%{null!=hkPayCycle}">
								${hkPayCycle }天
								</s:if>
							</td>
							<td>${hkCountDown }天</td>
							<td><s:if test="%{null!=hkTrackRate}" >${hkTrackRate}%</s:if></td>
							<td>
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=query"
									target="_blank">查看明细</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=noteStaDetail"
									target="_blank">送货清单</a>
								
								
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=queryInvo"
									target="_blank">查看发票信息</a>
								<s:if test="%{0.0!=hkTrackRate}">	
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=querybackMoney"
									target="_blank">回款记录</a>				
								</s:if>				
							
								
							</td>
							</s:iterator>
						 -->	
						<tr bgcolor="green">
								<th colspan="9" align="center">
									<font color="#ffffff">回款审批中和回款完成的记录</font>
								</th>
							</tr>
						<s:iterator id="listhk" value="listhk" status="statussdf">
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
								${hkBillMoney}
							</td>
							<td align="left" width="60px">
								${hkClientName}
							</td>
							<td align="left" width="60px">
								${hkStatus}
							</td>
							<td>								
								<s:if test="%{null!=hkPayCycle}">
								${hkPayCycle }天
								</s:if>
							</td>
							<td>
							<s:if test="%{null!=hkCountDown}">
							${hkCountDown }天
							</s:if>
							</td>
							<td><s:if test="%{null!=hkTrackRate}" >${hkTrackRate}%</s:if></td>
							<td>
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=query"
									target="_blank">查看明细</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=noteStaDetail"
									target="_blank">送货清单</a>
								<s:if test="%{0.0!=hkTrackRate}">	
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=queryInvo"
									target="_blank">查看发票信息</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=querybackMoney"
									target="_blank">回款记录</a>				
								</s:if>	
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=hkOVER" >关闭</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=hkRepeatInvo">重开发票</a>
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
			</s:if>
			<!-- 财务操作 -->
			<s:elseif test="%{tag=='backMoney'}">
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
						<s:iterator id="listhk" value="listhk" status="statussdf">
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
								${hkApplyDate}
							</td>
							<td>
								${hkBillMoney}
							</td>
							<td align="left" width="60px">
								${hkClientName}
							</td>
							<td align="left" width="60px">
								${hkStatus}
							</td>
							<td>
								${hkNum}
							</td>
							<td><s:if test="%{null!=hkTrackRate}" >${hkTrackRate}%</s:if></td>
							<td>
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=query"
									target="_blank">查看明细</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=noteStaDetail"
									target="_blank">送货清单</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=queryInvo"
									target="_blank">查看发票信息</a>
								/<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=addBackMoney"
									target="_blank">添加回款记录</a>	
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
			</s:elseif>
			<!-- 市场操作 -->			
			<s:else>
			
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
							
							<td>
								操作
							</td>
						</tr>
						<s:iterator id="listhk" value="listhk" status="statussdf">
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
								${hkApplyDate}
							</td>
							<td>
								${hkBillMoney}
							</td>
							<td align="left" width="60px">
								${hkClientName}
							</td>
							<td align="left" width="60px">
								${hkStatus}
							</td>
							<td>
								${hkNum}
							</td>
							
							<td>
							<s:if test="%{tag=='invoHistory'}">
								<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=RepeatInvoice"
									target="_blank">修改开票</a>/
							</s:if>
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=query"
									target="_blank">查看明细</a>/
							<s:if test="%{tag=='LDquery'}">
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=LDnoteStaDetail"
									target="_blank">送货清单</a>/
							</s:if>
							<a href="CircuitRunAction_findAduitPage.action?id=${epId}">审批动态</a>
							<s:else>
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=noteStaDetail"
									target="_blank">送货清单</a>/
							</s:else>
							<s:if test="hkStatus=='打回'||hkStatus=='开票申请'">
							 		<a href="huikuanAction!deleteHK.action?id=<s:property value='id' />" >删除</a>
							</s:if>
							<s:if test="%{tag=='retuMD'}">
							
							<!-- 判断有无追款人 -->
							<s:if test="%{null==hkZhuikuanren}">
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=addZKPerson" target="_blank">更改追款人</a>/
							</s:if>						
							</s:if>
							<s:elseif test="%{tag=='FDInvoice'}">							
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=invoiceStaDetail">开票</a>/
							</s:elseif>							
							<s:elseif test="%{tag=='all'}">							
							</s:elseif>		
							<s:if test="%{tag=='zkquery' || tag=='invoFD'">		
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=queryInvo"
									target="_blank">查看发票信息</a>/
							</s:if>		
							<s:if test="%{tag=='zkquery' || tag=='retuMD'}">	
							<a href="huikuanAction!showOneHK.action?id=<s:property value='id' />&tag=querybackMoney"
									target="_blank">回款记录</a>	/
							</s:if>		
							<s:if test="%{tag=='noteLD' || tag=='noteFD'}">							
								<a href="huikuanAction!ExamIdea.action?id=<s:property value='id' />&tag=<s:property value='tag' />&idea=OK">通过</a>
								/
								<a href="huikuanAction!ExamIdea.action?id=<s:property value='id' />&tag=<s:property value='tag' />&idea=NO">打回</a>/
							</s:if>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
			</s:else>
					
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
