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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>现金流量表</h2>
			<div >
				<ul style="width: 100%;">
					<li style="float: left; width: 33%;">单位:${companyInfo.name} </li>
					<li style="float: left; width: 33%"> 
							<input type="text" value="${months}" name="months" id="months"
							onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
							class="Wdate"
							/> 
						<input type="button" value="查询"  onclick="changvalue('months','${pageStauts}')"/>
					</li>
					<li style="float: left; width: 33%">金额单位:元</li>
				</ul>
			</div>
				<table class="table">
					<tr>
						<th>
							项目${pageStauts}
						</th>
						<th>
							行次
						</th>
						<th>
							本月金额
						</th>
						<th>
							本年累计
						</th>
					</tr>
					<tr>
						<td align="left">
							一、经营活动产生的现金流量：
						</td>
						<td>1</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							销售商品、提供劳务收到的现金
						</td>
						<td>2</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyIn1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj2&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyIn1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							收到的税费返还
						</td>
						<td>3</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyIn2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj3&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyIn2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							 收到的其他与经营活动有关的现金
						</td>
						<td>4</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyIn2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj4&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyIn2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="center">
							现金流入小计
						</td>
						<td>7</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyInSum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj7&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyInSum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							     购买商品、接受劳务支付的现金
						</td>
						<td>8</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyOut1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj8&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyOut1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							支付给职工以及为职工支付的现金
						</td>
						<td>9</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyOut2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj9&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyOut2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							    支付的各项税费
						</td>
						<td>10</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyOut3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj10&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyOut3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							支付的其他与经营活动有关的现金
						</td>
						<td>11</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyOut4}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj11&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyOut4}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="center">
						 	  现金流出小计
						</td>
						<td>12</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneyOutSum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj12&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneyOutSum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						  经营活动产生的现金流量净额
						</td>
						<td>13</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jymoneySum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj13&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jymoneySum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						二、投资活动产生的现金流量：
						</td>
						<td>14</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
						   	 收回投资所收到的现金
						</td>
						<td>15</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyIn1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj15&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyIn1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						   取得投资收益所收到的现金
						</td>
						<td>16</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyIn2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj16&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyIn2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							处置固定资产、无形资产和其他长期资产所收回的现金净额
						</td>
						<td>17</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyIn3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj17&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyIn3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							处置子公司及其他营业单位收到的现金净额
						</td>
						<td>18</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyIn4}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj18&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyIn4}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							收到的其他与投资活动有关的现金
						</td>
						<td>19</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyIn5}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj19&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyIn5}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="center">
							 现金流入小计
						</td>
						<td>20</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyInSum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj20&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyInSum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							购建固定资产、无形资产和其他长期资产所支付的现金
						</td>
						<td>21</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyOut1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj21&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyOut1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							投资所支付的现金
						</td>
						<td>22</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyOut2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj22&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyOut2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							取得子公司及其他营业单位支付的现金净额
						</td>
						<td>23</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyOut3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj23&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyOut3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 支付的其他与投资活动有关的现金
						</td>
						<td>24</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyOut4}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj24&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyOut4}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							现金流出小计
						</td>
						<td>25</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneyOutSum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj25&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneyOutSum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							投资活动产生的现金流量净额
						</td>
						<td>26</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzmoneySum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj26&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzmoneySum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							三、筹资活动产生的现金流量：
						</td>
						<td>28</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							吸收投资所收到的现金
						</td>
						<td>29</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneyIn1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj29&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneyIn1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							借款所收到的现金
						</td>
						<td>30</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneyIn2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj30&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneyIn2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							收到的其他与筹资活动有关的现金
						</td>
						<td>31</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneyIn3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj31&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneyIn3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="center">
							现金流入小计
						</td>
						<td>33</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneyInSum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj33&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneyInSum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 偿还债务所支付的现金
						</td>
						<td>34</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneyOut1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj34&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneyOut1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							分配股利、利润或偿付利息所支付的现金
						</td>
						<td>35</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneyOut2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj35&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneyOut2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							支付的其他与筹资活动有关的现金
						</td>
						<td>36</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneyOut3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj36&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneyOut3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="center">
							现金流出小计
						</td>
						<td>38</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneyOutSum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj38&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneyOutSum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							筹资活动产生的现金流量净额
						</td>
						<td>39</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.czmoneySum}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj39&months=${months}')" >
									<fmt:formatNumber value="${cashflow.czmoneySum}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							四、汇率变动对现金的影响
						</td>
						<td>40</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.hlyx}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj40&months=${months}')" >
									<fmt:formatNumber value="${cashflow.hlyx}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							投资活动产生的现金流量净额
						</td>
						<td>41</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.moneyadd}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj41&months=${months}')" >
									<fmt:formatNumber value="${cashflow.moneyadd}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							五、现金及现金等价物净增加额
						</td>
						<td>42</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.moneyadd}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj42&months=${months}')" >
									<fmt:formatNumber value="${cashflow.moneyadd}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							补充资料
						</td>
						<td>43</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							1、将净利润调节为经营活动现金流量：
						</td>
						<td>44</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 净利润
						</td>
						<td>45</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 加：计提的资产减值准备
						</td>
						<td>46</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jtzcjzzb}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj46&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jtzcjzzb}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							固定资产折旧
						</td>
						<td>47</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.gdzczj}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj47&months=${months}')" >
									<fmt:formatNumber value="${cashflow.gdzczj}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							 无形资产摊销
						</td>
						<td>48</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.wxzctx}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj48&months=${months}')" >
									<fmt:formatNumber value="${cashflow.wxzctx}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							处置固定资产、无形资产和其他长期资产的损失（减；收益）
						</td>
						<td>49</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.chuzizc}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj49&months=${months}')" >
									<fmt:formatNumber value="${cashflow.chuzizc}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							固定资产报废损失
						</td>
						<td>50</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.gdzcbfss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj50&months=${months}')" >
									<fmt:formatNumber value="${cashflow.gdzcbfss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							财务费用
						</td>
						<td>51</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.cwfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj51&months=${months}')" >
									<fmt:formatNumber value="${cashflow.cwfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							投资损失（减：收益）
						</td>
						<td>52</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.tzss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj52&months=${months}')" >
									<fmt:formatNumber value="${cashflow.tzss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							递延税款贷项（减：借项）
						</td>
						<td>53</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.dyskdx}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj53&months=${months}')" >
									<fmt:formatNumber value="${cashflow.dyskdx}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							 存货的减少（减：增加）
						</td>
						<td>54</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.chjs}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj54&months=${months}')" >
									<fmt:formatNumber value="${cashflow.chjs}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							经营性应收项目的减少(减:增加)
						</td>
						<td>55</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jyxmjs}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj55&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jyxmjs}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							经营性应付项目的增加(减:减少)
						</td>
						<td>56</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.jxyxmzj}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj56&months=${months}')" >
									<fmt:formatNumber value="${cashflow.jxyxmzj}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							  其他
						</td>
						<td>57</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.qita}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj57&months=${months}')" >
									<fmt:formatNumber value="${cashflow.qita}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							经营活动产生的现金流量净额
						</td>
						<td>58</td>
						<td align="right"><fmt:formatNumber value="${cashflow.jyhdMoney}" pattern="###,###,###.####"></fmt:formatNumber></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							2、不涉及现金收支的投资和筹资活动：
						</td>
						<td>59</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							债务转为资本
						</td>
						<td>60</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.zwzzb}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj60&months=${months}')" >
									<fmt:formatNumber value="${cashflow.zwzzb}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							一年内到期的可转换公司债券
						</td>
						<td>61</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.gzzqyears}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj61&months=${months}')" >
									<fmt:formatNumber value="${cashflow.gzzqyears}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 融资租入固定资产
						</td>
						<td>62</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.rzzrgdzc}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj62&months=${months}')" >
									<fmt:formatNumber value="${cashflow.rzzrgdzc}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							3、现金及现金等价物净增加情况
						</td>
						<td>63</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							现金的期末余额
						</td>
						<td>64</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.moneyqm}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj64&months=${months}')" >
									<fmt:formatNumber value="${cashflow.moneyqm}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							减：现金的期初余额
						</td>
						<td>65</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.moneyqc}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj65&months=${months}')" >
									<fmt:formatNumber value="${cashflow.moneyqc}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							加：现金等价物的期末余额
						</td>
						<td>66</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.moneydjwqm}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj66&months=${months}')" >
									<fmt:formatNumber value="${cashflow.moneydjwqm}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							减：现金等价物的期初余额
						</td>
						<td>67</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.moneydjwqc}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj67&months=${months}')" >
									<fmt:formatNumber value="${cashflow.moneydjwqc}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 现金及现金等价物净增加额
						</td>
						<td>68</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${cashflow.moneydj}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=xj68 &months=${months}')" >
									<fmt:formatNumber value="${cashflow.moneydj}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function changvalue(obj,tag){
	if(obj!=null && $("#"+obj).val()!=''){
		window.location.href='SubjectBudgetAction!findcashflowByMonths.action?months='+$("#"+obj).val()+"&pageStauts="+tag;
	}
}
</SCRIPT>
	</body>
</html>
