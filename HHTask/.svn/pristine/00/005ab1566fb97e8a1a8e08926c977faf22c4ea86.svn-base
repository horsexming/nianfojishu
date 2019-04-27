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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
<STYLE type="text/css">
@media screen {
	.noprint {
		display: inline;
		cursor: hand;
	}
}
@media print {
	.noprint {
		display: none;
	}
}
</STYLE>	
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
					<h2 style="font-size: large">${companyInfo.name}</h2>
					<h2 style="font-size: large">${companyInfo.englishName}</h2>
					<h2 style="font-size: large">补料申请单</h2>
				<table style="width: 98%;">
					<tr>
						<th align="left">
							业务件号:${csblorder.ywMarkId}
						</th>
						<td width="20%">&nbsp;</td>
						<th align="left">
							内部订单号:${csblorder.orderNumber}
						</th>
					</tr>
					<tr>
						<th align="left">
							总成件号:${csblorder.rootMarkId}
						</th>
						<td width="20%">&nbsp;</td>
						<th align="left">
							总成批次:${csblorder.rootSelfCard}
						</th>
					</tr>
					<tr>
						<th align="left">
							申请部门:${csblorder.sqdept}
						</th>
						<td width="20%">&nbsp;</td>
						<th align="left">
							申请人:${csblorder.sqUsersName}
						</th>
					</tr>
					<tr>
						<th align="left">
							申请日期:${csblorder.sqdate}
						</th>
						<td width="20%">&nbsp;</td>
						<th align="left">
							补料总额:${csblorder.sumPrice}
						</th>
					</tr>
				</table>
				<div>
				<div align="left" style="float: left; width: 2%">&nbsp;</div>
				<table class="table" style="font-size: 1px;width: 78%;" align="left">
						<tr align="center">
							<th>
								序号
							</th>
							<th>
								卡片类型
							</th>
							<th>
								件号
							</th>
							<th>
								名称
							</th>
<%--							<th>--%>
<%--								规格--%>
<%--							</th>--%>
<%--							<th>--%>
<%--								供料属性--%>
<%--							</th>--%>
<%--							<th>--%>
<%--								物料类别--%>
<%--							</th>--%>
							<th>
								补料数量
							</th>
<%--							<th>--%>
<%--								含税单价--%>
<%--							</th>--%>
<%--							<th>--%>
<%--								不含税单价--%>
<%--							</th>--%>
<%--							<th>--%>
<%--								税率--%>
<%--							</th>--%>
							<th>
								补料金额(含税)
							</th>
							<th>
								单位
							</th>
							<th>
								采购单号
							</th>
						</tr>
						<s:iterator id="pagecsbl" value="csblList"
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
									<input type="hidden" value="${statussdf.index+1}" id="xuhao_${pageProcard.id}"/>
								</td>
								<td>
									${pagecsbl.procardStyle}
								</td>
								<td>
									${pagecsbl.markId}
								</td>
								<td>
									${pagecsbl.proName}
								</td>
<%--								<td>--%>
<%--									${pagecsbl.specification}--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									${pagecsbl.kgliao}--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									${pagecsbl.wgtype}--%>
<%--								</td>--%>
								<td>
									${pagecsbl.blNum}
								</td>
<%--								<td>--%>
<%--									${pagecsbl.hsprice}--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									${pagecsbl.bhsprice}--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									${pagecsbl.taxprice}--%>
<%--								</td>--%>
								<td>
									${pagecsbl.sumPrice}
								</td>
								<td>
									${pagecsbl.unit}
								</td>
								<td>
									${pagecsbl.cgOrderNum}
								</td>
						</s:iterator>
					</table>
				</div>
					<div align="left">
						<span style="float: left;width:8%;"><b>&nbsp;</b></span>
						<span style="float: left;width:30%;"><b>批准:</b></span>
						<span style="float: left;width:30%;"><b>审核:</b></span>
						<span style="float: left;width:30%;"><b>制表:${csblorder.sqUsersName}</b></span>
						<br/>
						<p>
						<span style="float: left;width:8%;"><b>&nbsp;</b></span>
						<span style="float: left;width:22%;"><b>分发单位:</b></span>
						<span style="float: left;width:22%;"><b>第一联(白)---PMC</b></span>
						<span style="float: left;width:22%;"><b>第二联(红)---仓库</b></span>
						<span style="float: left;width:22%;"><b>第三联(黄)---财务</b></span>
						</p>
					</div>
					<input type="button" class="noprint" style="height: 35px;width: 70px;" value="打印" onclick="window.print()"/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
