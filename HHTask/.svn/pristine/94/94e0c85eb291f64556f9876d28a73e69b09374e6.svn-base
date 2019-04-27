<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">				
				<s:if test="%{tag=='query'}">
				<div style="float: left; width: 50%" align="left">
				
					<font color="#ffffff">编号：<s:property value="taHk.hkNum" /></font>
				</div>
				<div style="color: #ffffff; padding-right: 20px" align="right">
					<a href="javascript:showUpdate()" style="color: #ffffff"><span
							id="aUpdate">修改</span> </a> |
						<a
							href="javascript:if(confirm('确定要删除该数据?')){location.replace('huikuanAction!deleteHK.action?id=${taHk.id}&tag=${tag}');};"
							style="color: #ffffff"> 删除</a> |
							<s:iterator id="listObj" value="listObj" status="tt" >
<%--							<a href="DownAction.action?fileName=${listObj[0 ]}&directory=/upload/huikuan/"--%>
							<a href="FileViewAction.action?FilePath=/upload/huikuan/${listObj[0 ]}"
							style="color: #ffffff">下载文件<s:property value="#tt.index" />   </a> 
							</s:iterator>
						
				</div>
				</s:if>
				<s:elseif test="%{tag=='addZKPerson'}">
				<div style="float: left; width: 50%" align="left">
				
					<font color="#ffffff">编号：<s:property value="taHk.hkNum" /></font>
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
				</s:elseif>
				<s:elseif test="%{tag=='queryInvo'}">
				<div style="float: left; width: 50%" align="left">
				
					<font color="#ffffff">编号：<s:property value="taHk.hkNum" /></font>
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
				</s:elseif>
				<s:else>
				<div style="float: left; width: 50%" align="left">
				
					<font color="#ffffff">编号：<s:property value="taHk.hkNum" /></font>
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
				</s:else>
			</div>
			
			<div align="center">
				<s:if test="%{tag=='query'}">
				<table width="80%" id="showPrice" border="0" id="showPrice"
						style="padding: 0px; margin: 0px; display: block;">
						<tr>
							<td>
								<ul class="userCenter">
									<li>
										<span class="span1">客户 : </span> ${taHk.hkClientComp}
									</li>
									<li>
										<span class="span1">回款金额 : </span>${taHk.hkTrackerTotMoney}/${taHk.hkMoneyUnit}
									<li>
										<span class="span1">开票金额 : </span>${taHk.hkBillMoney}/${taHk.hkMoneyUnit}
										
									</li>
									<li>
										<span class="span1">申请开票时间  : </span> ${taHk.hkAppPayDate}
									</li>
									<li>
										<span class="span1">目前状态 : </span> ${taHk.hkStatus}
									</li>
								</ul>
							</td>
							<td>
								<ul class="userCenter">
									<li>
										<span class="span1">开票总数 : </span> ${taHk.hkApplyCount}
									</li>
									<li>
										<span class="span1">&nbsp;回款周期 :</span>${taHk.hkPayCycle}天
									</li>
									<li>
										<span class="span1">回款率 : </span> <s:if test="%{null!=taHk.hkTrackRate}" >${taHk.hkTrackRate}%</s:if>
									</li>
									<li>
										<span class="span1">追款人 : </span> ${taHk.hkZhuikuanren}
									</li>
									<li>
										<span class="span1">申 请 &nbsp;人 : </span>
										${taHk.hkApplier}
									</li>
								</ul>
							</td>
							<td>
								<ul class="userCenter">

									<li>
										<span class="span1">开票审批通过时间 : </span> ${taHk.hkNotOverTime}
									</li>
									<li>
										<span class="span1">开发票时间 : </span> ${taHk.hkBillTime}
									</li>
									<li>
										<span class="span1">客户负责人 : </span>
										${taHk.hkClientName}
									</li>
									<li>
										<span class="span1">开票负责人 : </span> ${taHk.hkBiller}
									</li>
									<li>
										<span class="span1">开票审核通过时间: </span> ${taHk.hkBillTime}
									</li>
								</ul>
							</td>
						</tr>
						<tr>
							<td colspan="3" style="padding-left: 38px;">
								<span class="span1">备注 : </span>${taHk.hkMore}
							</td>
						</tr>
					</table>
				</s:if>
				<s:elseif test="%{tag=='addZKPerson'}">
				<form action="huikuanAction!updateHK.action" method="post">
					<input type="hidden" name="taHk.id" value="${taHk.id }" />
					<input type="hidden" name="tag" value="${tag }" />
					<table width="80%" id="showPrice" border="0" id="showPrice"
						style="padding: 0px; margin: 0px; display: block;">
						<tr>
							<td>
								<ul class="userCenter">
									<li>
										<span class="span1">客户 : </span> ${taHk.hkClientComp}
									</li>
									<li>
										<span class="span1">回款金额 : </span>${taHk.hkTrackerTotMoney}/${taHk.hkMoneyUnit}
									<li>
										<span class="span1">开票金额 : </span>${taHk.hkBillMoney}/${taHk.hkMoneyUnit}
										
									</li>
									<li>
										<span class="span1">申请开票时间  : </span> ${taHk.hkAppPayDate}
									</li>
									<li>
										<span class="span1">目前状态 : </span> ${taHk.hkStatus}
									</li>
								</ul>
							</td>
							<td>
								<ul class="userCenter">
									<li>
										<span class="span1">开票总数 : </span> ${taHk.hkApplyCount}
									</li>
									<li>
										<span class="span1">&nbsp;回款周期 :</span>${taHk.hkPayCycle}天
									</li>
									<li>
										<span class="span1">回款率 : </span> <s:if test="%{null!=taHk.hkTrackRate}" >${taHk.hkTrackRate}*100+%</s:if>
									</li>
									<li>
										<span class="span1">追款人 : </span><input type="text" name="taHk.hkZhuikuanren" value="${taHk.hkZhuikuanren }" /> 
									</li>
									<li>
										<span class="span1">申 请 &nbsp;人 : </span>
										${taHk.hkApplier}
									</li>
								</ul>
							</td>
							<td>
								<ul class="userCenter">

									<li>
										<span class="span1">开票审批通过时间 : </span> ${taHk.hkNotOverTime}
									</li>
									<li>
										<span class="span1">开发票时间 : </span> ${taHk.hkBillTime}
									</li>
									<li>
										<span class="span1">客户负责人 : </span>
										${taHk.hkClientName}
									</li>
									<li>
										<span class="span1">开票负责人 : </span> ${taHk.hkBiller}
									</li>
									<li>
										<span class="span1">开票审核通过时间: </span> ${taHk.hkBillTime}
									</li>
								</ul>
							</td>
						</tr>
						<tr>
							<td colspan="3" style="padding-left: 38px;">
								<span class="span1">备注 : </span>${taHk.hkMore}
							</td>
						</tr>
						<tr>
							<td colspan="3" style="padding-left: 38px;text-align:center;">
								<span class="span1"><input type="submit" value="添 加" style="width: 60px; height: 40px;" align="top"> &nbsp;
								<input type="reset" value="取 消" style="width: 60px; height: 40px;" align="top">
								</span>
							</td>
						</tr>
					</table>
				</form>
				</s:elseif>
				<s:elseif test="%{tag=='queryInvo'}">
					<table style="width:98%;margin: 5 px">				
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>序号</td>
						<td>
							客户
						</td>
						<td>
							零件号
						</td>
						<td>
							发货数量
						</td>
						<td>
							开票金额
						</td>
						<td>
							发票号
						</td>
						<td>
							开票时间
						</td>
						
					</tr>
					<s:iterator value="invoiceArr" status="hk" id="invoice">
						<s:if test="#hk.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td><s:property value="#hk.index+1" /></td>
						<td>
							${taHk.hkClientComp}
						<td>
							<s:property value="hkInvoMarkId" />
						</td>
						<td>
							<s:property value="hkInvoCount" />
						</td>
						<td>
							<s:property value="hkInvoTaxMoney" />
						</td>
						<td>
							<s:property value="hkInvoInvoNum" />
						</td>
						<td>
							<s:property value="hkInvoBillTime" />
						</td>
						
						</tr>
					</s:iterator>
					<tr></tr></table>
				</s:elseif>
				<!-- 查看回款记录 -->
					<s:elseif test="%{tag=='querybackMoney'}">
					<table style="width:98%;margin: 5 px">				
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>序号</td>
						<td>
							客户
						</td>
						<td>
							关联人
						</td>
						<td>
							回款金额
						</td>
						<td>
							回款依据
						</td>
						<td>
							负责人
						</td>
						<td>
							回款时间时间
						</td>
					</tr>
					<s:iterator value="listbackMon" status="hk" id="back">
						<s:if test="#hk.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td><s:property value="#hk.index+1" /></td>
						<td>
							${taHk.hkClientComp}
						<td>
							<s:property value="hkbmClientUser" />
						</td>
						
						<td>
							<s:property value="hkbmMoney" />
						</td>
						<td>
							<s:property value="hkbmResult" />
						</td>
						<td>
							<s:property value="hkbmPerson" />
						</td>
						<td>
							<s:property value="hkbmDate" />
						</td>
						</tr>
					</s:iterator>
					<tr></tr></table>
				</s:elseif>
				<s:elseif test="%{tag=='noteStaDetail'}">
				<table style="width:98%;margin: 5 px">				
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>序号</td>
						<td>
							客户
						</td>
						<td>
							零件号
						</td>
						<td>
							发货数量
						</td>
						<td>单价</td>
						<td>
							送货单号
						</td>
						<td>
							订单号
						</td>
						<td>附件</td>
						<td style="width:15%">
							备注
						</td>
					</tr>
					<s:iterator value="listHkSellSta" status="hk" id="huikuan">
						<s:if test="#hk.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td><s:property value="#hk.index+1" /></td>
						<td>
							<s:property value="hkSellCumpanyName" />
						<td>
							<s:property value="hkSellMarkId" />
						</td>
						<td>
							<s:property value="hkSellCount" />
						</td>
						<td>
							<s:property value="hkSellPrice" />
						</td>
						<td>
							<s:property value="hkSellSendId" />
						</td>
						<td>
							<s:property value="hkSellOutOrderId" />
						</td>
<%--						<td><a href="DownAction.action?fileName=<s:property value="hkSellFile" />&directory=/upload/huikuan/invoice/">--%>
<%--						下载文件</a> </td>--%>
						<td><a href="FileViewAction.action?FilePath=/upload/huikuan/invoice/<s:property value="hkSellFile" />">
						查看文件</a> </td>
						<td>
							<s:property value="hkSellMore" />
						</td>
						</tr>
					</s:iterator>
					<tr></tr></table>
				</s:elseif>
				<s:elseif test="%{tag=='LDnoteStaDetail'}">
				<table style="width:98%;margin: 5 px">				
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>序号</td>
						<td>
							客户
						</td>
						<td>
							零件号
						</td>
						<td>
							发货数量
						</td>
						<td>单价</td>
						<td>
							送货单号
						</td>
						<td>
							订单号
						</td>
						<td>附件</td>
						<td>
							备注
						</td>
						<td>操作</td>
					</tr>
					<s:iterator value="listHkSellSta" status="hk" id="huikuan">
						<s:if test="#hk.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td><s:property value="#hk.index+1" /></td>
						<td>
							<s:property value="hkSellCumpanyName" />
						</td>
						<td>
							<s:property value="hkSellMarkId" />
						</td>
						<td>
							<s:property value="hkSellCount" />
						</td>
						<td>
							<s:property value="hkSellPrice" />
						</td>
						<td>
							<s:property value="hkSellSendId" />
						</td>
						<td>
							<s:property value="hkSellOutOrderId" />
						</td>
<%--						<td><a href="DownAction.action?fileName=<s:property value="hkSellFile" />&directory=/upload/huikuan/invoice/">--%>
<%--						下载文件</a> </td>--%>
						<td><a href="FileViewAction.action?FilePath=/upload/huikuan/invoice/<s:property value="hkSellFile" />">
						查看文件</a> </td>
						<td>
							<s:property value="hkSellMore" />
						</td>
						<td>
						<s:if test="%{'开票申请打回'==taHkHuikuan.hkStatus || '开票申请'==taHkHuikuan.hkStatus}">
							<a href="huikuanAction!getoneSta.action?id=<s:property value='id' />">修改</a>
						</s:if>						
						</td>
						</tr>
					</s:iterator>
					<tr></tr></table>
				</s:elseif>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
	</body>




</html>
