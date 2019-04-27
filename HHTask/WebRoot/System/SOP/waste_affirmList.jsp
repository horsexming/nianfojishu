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
		<div id="gongneng">
		</div>

		<div align="center">
			<h2>报废品处理申请</h2>
			<form action="wasteDisposeAction!findWhereWaste.action?tag=${tag}" method="post">
				<table class="table" style="width: 95%;">
					<tr>
						<th align="right">申请日期:</th>
						<td>
							<input class="Wdate" type="text" name="startDate" value="${startDate}" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								到
							<input class="Wdate" type="text" name="endDate" value="${ endDate}" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
						<th align="right">申&nbsp;&nbsp;请&nbsp;&nbsp;人:</th>
						<td>
							<input type="text" name="proposer" value="${proposer}" />
						</td>
						<th rowspan="3">
							<input type="submit" value="查找"
								style="width: 65px; height: 35px; margin-top: 5px;" />&nbsp;
						</th>
					</tr>
				</table>
			</form>
			<table class="table" style="width: 95%;">
				<tr bgcolor="#c0dcf2" height="30px"
					style="border-collapse: separate;">
					<th align="center">
						序号
					</th>
					<th>处理单号</th>
					<th align="center">
						申请人
					</th>
					<th align="center">
						申请人部门
					</th>
					<th align="center">
						申请日期
					</th>
					<th align="center">
						申请报废数量
					</th>
					<th align="center">
						申请总价格
					</th>
					<th>处理单位</th>
					<th align="center">
						财务审批状态
					</th>
					<th align="center">
						操作
					</th>
				</tr>
					
					
						<tr>
							<td colspan="14" align="center" bgcolor="#FF6666"
							style="font-weight:bold;">待添加价格待审批</td>
						</tr>
						<s:iterator value="bePriceNotEp" status="see" id="sdt">
							<s:if test="#see.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#see.index+1" />
							</td>
							<td>${sdt.totalId}</td>
							<td>
								${sdt.createUserName}
							</td>
							<td>
								${sdt.createDept}
							</td>
							<td>
								${sdt.createTime}
							</td>
							<td>
								${sdt.totalCount}
							</td>
							<td>
								${sdt.totalMoney}
							</td>
							<td>
								${sdt.sellToName}
							</td>
							<td>
								<a href="CircuitRunAction!findAduitPage.action?id=${sdt.caiwuEpId}">${sdt.caiwuEpStatus}</a>
							</td>
							<td align="char">
								<input type="button" onclick="location.href='wasteDisposeAction!selectWDDetail.action?wasteDisponsalTotal.totalId=${sdt.totalId}'" value="查看明细"/>
								<s:if test="#sdt.epStatus=='打回'">
									<input type="button" onclick="location.href='wasteDisposeAction!reapplywd.action?wasteDisponsalTotal.totalId=${sdt.totalId}'" value="重新申请"/>
								</s:if>
								<s:if test="#sdt.caiwuEpStatus=='同意'">
									<input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}'" value="签名/打印"/>
								</s:if>
								<s:else>
									<s:if test="#sdt.epStatus=='同意'||#sdt.epStatus=='未审批'">
										<!-- <input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}'" value="签名/打印"/> -->
										<input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}&tag=confirmPrice'" value="设定价格" />
									</s:if>
									<s:else>
										<input type="button" onclick="alert('该申请正在审批中或审批完成不能删除')" value="删除"/>
									</s:else>
								</s:else>
								
								
							</td>
						</tr>
					</s:iterator>
						
						<tr>
							<td colspan="14" align="center" bgcolor="#99CC66"
							style="font-weight:bold;">已添加价格待审批</td>
						</tr>
						<s:iterator value="notPriceAndEp" status="see" id="sdt">
							<s:if test="#see.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#see.index+1" />
							</td>
							<td>${sdt.totalId}</td>
							<td>
								${sdt.createUserName}
							</td>
							<td>
								${sdt.createDept}
							</td>
							<td>
								${sdt.createTime}
							</td>
							<td>
								${sdt.totalCount}
							</td>
							<td>
								${sdt.totalMoney}
							</td>
							<td>
								${sdt.sellToName}
							</td>
							<td>
								<a href="CircuitRunAction!findAduitPage.action?id=${sdt.caiwuEpId}">${sdt.caiwuEpStatus}</a>
							</td>
							<td align="char">
								<input type="button" onclick="location.href='wasteDisposeAction!selectWDDetail.action?wasteDisponsalTotal.totalId=${sdt.totalId}'" value="查看明细"/>
								<s:if test="#sdt.epStatus=='打回'">
									<input type="button" onclick="location.href='wasteDisposeAction!reapplywd.action?wasteDisponsalTotal.totalId=${sdt.totalId}'" value="重新申请"/>
								</s:if>
								<s:if test="#sdt.caiwuEpStatus=='同意'">
									<input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}'" value="签名/打印"/>
								</s:if>
								<s:else>
									<s:if test="#sdt.epStatus=='同意'">
										<!-- <input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}'" value="签名/打印"/> -->
										<input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}&tag=confirmPrice'" value="修改价格" />
									</s:if>
									<s:else>
										<input type="button" onclick="alert('该申请正在审批中或审批完成不能删除')" value="删除"/>
									</s:else>
								</s:else>
								
								
							</td>
						</tr>
					</s:iterator>
					
						<tr>
							<td colspan="14" align="center" bgcolor="#FF9999"
							style="font-weight:bold;">已添加价格已审批</td>
						</tr>
					<s:if test="showwdtList.size()>0">
						<s:iterator value="showwdtList" status="see" id="sdt">
							<s:if test="#see.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#see.index+1" />
							</td>
							<td>${sdt.totalId}</td>
							<td>
								${sdt.createUserName}
							</td>
							<td>
								${sdt.createDept}
							</td>
							<td>
								${sdt.createTime}
							</td>
							<td>
								${sdt.totalCount}
							</td>
							<td>
								${sdt.totalMoney}
							</td>
							<td>
								${sdt.sellToName}
							</td>
							<td>
								<a href="CircuitRunAction!findAduitPage.action?id=${sdt.caiwuEpId}">${sdt.caiwuEpStatus}</a>
							</td>
							<td align="char">
								<input type="button" onclick="location.href='wasteDisposeAction!selectWDDetail.action?wasteDisponsalTotal.totalId=${sdt.totalId}'" value="查看明细"/>
								<s:if test="#sdt.epStatus=='打回'">
									<input type="button" onclick="location.href='wasteDisposeAction!reapplywd.action?wasteDisponsalTotal.totalId=${sdt.totalId}'" value="重新申请"/>
								</s:if>
								<s:if test="#sdt.caiwuEpStatus=='同意'">
									<input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}'" value="签名/打印"/>
								</s:if>
								<s:else>
									<s:if test="#sdt.epStatus=='同意'">
										<!-- <input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}'" value="签名/打印"/> -->
										<input type="button" onclick="location.href='wasteDisposeAction!signatureAndPrint.action?totalId=${sdt.totalId}&tag=confirmPrice'" value="设定价格" />
									</s:if>
									<s:else>
										<input type="button" onclick="alert('该申请正在审批中或审批完成不能删除')" value="删除"/>
									</s:else>
								</s:else>
								
								
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="14" align="right">
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
							<td colspan="21" style="font-size: 15px; color: red;">
								对不起，没有查到相关的报废单信息
							</td>
						</tr>
					</s:else>
			</table>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
