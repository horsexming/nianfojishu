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
				<table class="table">
					<tr >
						<th colspan="5" style="font-size: x-large;">
							基本情况表
						</th>
					</tr>
					<tr>
						<td>编制单位：${jbqk.companyName}</td>
						<td></td>
						<td>${jbqk.months}</td>
						<td></td>
						<td>金额单位：万元</td>
					</tr>
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</th>
						<td>行次</td>
						<td>本月变动</td>
						<td>本期数</td>
						<td>上年同期数</td>
					</tr>
					<tr>
						<th align="left">一、财务指标</th>
						<td align="center">0</td>
						<td align="right">
							1
						</td>
						<td align="right">
							2
						</td>
						<td align="right">
							3
						</td>
					</tr>
					<tr>
						<td>（一）计提的固定资产折旧总额</td>
						<td align="center">1</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gdzczj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy2&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gdzczj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gdzczj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy2&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gdzczj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gdzczj3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy1&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gdzczj3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>（二）投资收益</td>
						<td align="center">2</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.tzsy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${jbqk.tzsy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.tzsy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${jbqk.tzsy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.tzsy3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${jbqk.tzsy3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;其中：长期股权投资</td>
						<td align="center">3</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cqgqtz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${jbqk.cqgqtz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cqgqtz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${jbqk.cqgqtz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cqgqtz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${jbqk.cqgqtz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以公允价值计量且其变动计入当期损益的金融资产</td>
						<td align="center">5</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzjrzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzjrzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzjrzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzjrzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzjrzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzjrzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以公允价值计量且其变动计入当期损益的金融负债</td>
						<td align="center">6</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzjrfz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzjrfz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzjrfz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzjrfz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzjrfz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzjrfz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;持有至到期投资</td>
						<td align="center">7</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cyzdqtz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7&months=${months}')" >
									<fmt:formatNumber value="${jbqk.cyzdqtz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cyzdqtz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7months=${months}')" >
									<fmt:formatNumber value="${jbqk.cyzdqtz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cyzdrtz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7&months=${months}')" >
									<fmt:formatNumber value="${jbqk.cyzdrtz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可供出售金融资产</td>
						<td align="center">8</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kcsjrzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kcsjrzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kcsjrzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8months=${months}')" >
									<fmt:formatNumber value="${jbqk.kcsjrzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kcsjrzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kcsjrzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他收益项目</td>
						<td align="center">9</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qtsyxm1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qtsyxm1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qtsyxm2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qtsyxm2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qtsyxm3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qtsyxm3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>（三）债务成本</td>
						<td align="center">10</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zwcb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zwcb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zwcb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zwcb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zwcb3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zwcb3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;其中：费用化利息</td>
						<td align="center">11</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.fyhlx1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${jbqk.fyhlx1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.fyhlx2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${jbqk.fyhlx2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.fyhlx3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${jbqk.fyhlx3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;资本化利息</td>
						<td align="center">12</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zbhlx1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zbhlx1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zbhlx2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zbhlx2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zbhlx3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zbhlx3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>（四）国拨资金及自筹项目情况</td>
						<td align="center">13</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjzcxm1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjzcxm1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjzcxm2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjzcxm2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjzcxm3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjzcxm3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;收到国拨资金总额</td>
						<td align="center">14</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjze1}"
								 pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjze1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjze2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjze2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjze3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjze3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其中：基本条件建设拨款</td>
						<td align="center">15</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjsbk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjsbk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjsbk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjsbk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjsbk3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjsbk3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						科研试制费拨款</td>
						<td align="center">16</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kyszfbk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kyszfbk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kyszfbk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kyszfbk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kyszfbk3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kyszfbk3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;国拨资金支出总额</td>
						<td align="center">17</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjzcze1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjzcze1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjzcze2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjzcze2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gbzjzcze3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gbzjzcze3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其中：基本条件建设支出</td>
						<td align="center">18</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjszc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjszc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjszc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjszc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjszc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjszc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;科研试制费支出</td>
						<td align="center">19</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kyszfzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kyszfzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kyszfzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kyszfzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kyszfzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kyszfzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;企业自筹资本性支出</td>
						<td align="center">20</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qyzczbxzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qyzczbxzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qyzczbxzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qyzczbxzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qyzczbxzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qyzczbxzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其中：基本条件建设企业配套</td>
						<td align="center">21</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjs1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjs1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjs2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjs2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jbtjjs3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jbtjjs3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 其他自筹项目</td>
						<td align="center">22</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qtzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qtzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qtzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qtzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qtzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qtzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>（五）对外捐赠支出总额</td>
						<td align="center">23</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qzzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qzzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qzzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qzzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.qzzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${jbqk.qzzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="left">（六）职工培训费用</th>
						<td align="center">24</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zgpxfy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zgpxfy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zgpxfy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zgpxfy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zgpxfy3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zgpxfy3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>（七）隐性债务</td>
						<td align="center" >25</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxzw1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxzw1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxzw2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxzw2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxzw3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxzw3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;1.对外担保</td>
						<td align="center">26</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.dwdb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${jbqk.dwdb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.dwdb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${jbqk.dwdb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.dwdb3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${jbqk.dwdb3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;2.抵质押资产</td>
						<td align="center">27</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.dzyzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${jbqk.dzyzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.dzyzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${jbqk.dzyzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.dzyzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${jbqk.dzyzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;3.或有负债</td>
						<td align="center">28</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.hyfz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${jbqk.hyfz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.hyfz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${jbqk.hyfz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.hyfz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${jbqk.hyfz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.优先股
						</td>
						<td align="center">29</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxg1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxg1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxg2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxg2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxg3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxg3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						5.永续债券
						</td>
						<td align="center">30</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxzq1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxzq1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxzq2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxzq2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yxzq3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yxzq3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（八）逾期债务
						</td>
						<td align="center">31</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqzw1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqzw1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqzw2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqzw2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqzw3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqzw3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 1.逾期银行借款
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqyhjk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqyhjk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqyhjk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqyhjk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqyhjk3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqyhjk3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						2.逾期应付账款
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqyfzk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqyfzk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqyfzk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqyfzk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqyfzk3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqyfzk3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						3.逾期对外担保
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqdwdb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqdwdb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqdwdb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqdwdb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yqdwdb3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yqdwdb3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（九）资金情况
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjqk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjqk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjqk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjqk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjqk3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjqk3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						1.资金集中度（%）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjjzd1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjjzd1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjjzd2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjjzd2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjjzd3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjjzd3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  其中：通过财务公司归集的资金
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cwgsgjzj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.cwgsgjzj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cwgsgjzj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.cwgsgjzj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.cwgsgjzj3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.cwgsgjzj3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						通过资金结算中心归集的资金
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjjsgjzj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjjsgjzj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjjsgjzj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjjsgjzj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zjjsgjzj3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zjjsgjzj3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						2.银行抽贷总额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yhcdze1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yhcdze1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yhcdze2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yhcdze2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yhcdze3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yhcdze3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="left">
						二、其他指标
						</th>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（一）工业总产值（现行价格）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzcz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzcz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzcz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzcz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyzcz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyzcz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（二）劳动生产总值（现行价格）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.ldsczz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.ldsczz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.ldsczz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.ldsczz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.ldsczz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.ldsczz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（三）工业销售产值
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyxscz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyxscz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyxscz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyxscz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gyxscz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gyxscz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（四）新产品产值
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.xcpcz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.xcpcz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.xcpcz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.xcpcz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.xcpcz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.xcpcz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（五）本期支出的节能减排费用
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jnjpf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jnjpf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jnjpf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jnjpf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jnjpf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jnjpf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（六）手持订单额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.scdde1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.scdde1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.scdde2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.scdde2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.scdde3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.scdde3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						其中：国内订单
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gndd1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gndd1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gndd2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gndd2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gndd3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gndd3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						其中：军品订单
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jpdd1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jpdd1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jpdd2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jpdd2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jpdd3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jpdd3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						 民品订单
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.mpdd1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.mpdd1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.mpdd2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.mpdd2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.mpdd3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.mpdd3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						国外订单
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gwdd1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gwdd1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gwdd2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gwdd2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gwdd3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gwdd3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 其中：军贸订单
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jmdd1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jmdd1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jmdd2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jmdd2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jmdd3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jmdd3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						民品订单
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gwmpdd1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gwmpdd1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gwmpdd2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gwmpdd2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gwmpdd3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gwmpdd3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（七）固定资产投资额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gdzctze1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gdzctze1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gdzctze2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gdzctze2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.gdzctze3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.gdzctze3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（八）出口产品销售收入
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.ckcpxssr1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.ckcpxssr1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.ckcpxssr2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.ckcpxssr2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.ckcpxssr3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.ckcpxssr3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（九）银行借款
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yhjk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yhjk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yhjk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yhjk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yhjk3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yhjk3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十）应交税费
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjsf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjsf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjsf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjsf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjsf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjsf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						  1.应交增值税
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjzzs1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjzzs1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjzzs2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjzzs2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjzzs3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjzzs3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						2.应交消费税
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjxfs1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjxfs1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjxfs2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjxfs2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjxfs3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjxfs3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						3.应交所得税
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjsds1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjsds1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjsds2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjsds2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yjsds3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yjsds3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十一）已交税费
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijsf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijsf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijsf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijsf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijsf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijsf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						 1.已交增值税
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijzzs1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijzzs1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijzzs2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijzzs2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijzzs3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijzzs3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						2.已交消费税
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijxfs1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijxfs1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijxfs2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijxfs2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijxfs3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijxfs3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						3.已交所得税
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijsds1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijsds1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijsds2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijsds2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.yijsds3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.yijsds3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十二）投资额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.tze1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.tze1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.tze2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.tze2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.tze3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.tze3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						其中：科研生产投资额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kysctz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kysctz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kysctz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kysctz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.kysctz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.kysctz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						其中：军品科研生产投资额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jpkysctz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jpkysctz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jpkysctz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jpkysctz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.jpkysctz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.jpkysctz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十三）战略性新兴产业产品销售收入
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zlxcpsr1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zlxcpsr1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.zlxcpsr2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.zlxcpsr2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十四）国防工业维护与服务市场收入
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十五）利润总额预算数
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十六）净利润预算数
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十七）企业研发投入
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 其中：政府拨款
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 企业自筹
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十八）节能减排指标
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 1.节能减排投入总额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 其中：政府拨款
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						企业自筹
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						2.万元产值综合能耗（吨标煤）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						3.吨钢综合能耗（千克标煤／吨）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						4.供电煤耗（克／千瓦时）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（十九）军用固定资产
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（二十）商业保险费用全年预计
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						商业保险费用实际支出
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						其中：财产险费用
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 车险费用
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						人身险费用（不含社保五险）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（二十一）零余额账户用款额度
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 年初结余（财政应返还额度年初数）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						当年新增
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						当年支付
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						当年结余
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						预计下月支付
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（二十二）出口额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 其中：军品出口额
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						民品出口额 
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（二十三）军品应收账款
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（二十四）军品应付账款
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（二十五）军品存货
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 其中：产成品
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						（二十六）事业单位结余（科研事业单位填列）
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						其中：科研项目阶段完成结余
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${jbqk.}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
