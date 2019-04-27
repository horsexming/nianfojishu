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
							销售费用及财务费用明细表
						</th>
					</tr>
					<tr>
						<td>编制单位：${xcm.companyNmae}</td>
						<td></td>
						<td>${xcm.months}</td>
						<td></td>
						<td>金额单位：万元</td>
					</tr>
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</th>
						<td>行次</td>
						<td>本月数</td>
						<td>本年累计数</td>
						<td>上年同期数</td>
					</tr>
					<tr>
						<td><b>一、销售费用合计:</b></td>
						<td align="center">1</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xsfy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy1&months=${months}')" >
									<fmt:formatNumber value="${xcm.xsfy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xsfy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy1&months=${months}')" >
									<fmt:formatNumber value="${xcm.xsfy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xsfy3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy1&months=${months}')" >
									<fmt:formatNumber value="${xcm.xsfy3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;运输费</td>
						<td align="center">2</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.ysf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy2&months=${months}')" >
									<fmt:formatNumber value="${xcm.ysf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.ysf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy2&months=${months}')" >
									<fmt:formatNumber value="${xcm.ysf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.ysf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy1&months=${months}')" >
									<fmt:formatNumber value="${xcm.ysf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;装卸费</td>
						<td align="center">3</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zxf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${xcm.zxf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zxf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${xcm.zxf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zxf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy3&months=${months}')" >
									<fmt:formatNumber value="${xcm.zxf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;产品保险费</td>
						<td align="center">4</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cpbxf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${xcm.cpbxf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cpbxf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${xcm.cpbxf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cpbxf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy4&months=${months}')" >
									<fmt:formatNumber value="${xcm.cpbxf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;委托代销手续费(包装物)</td>
						<td align="center">5</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.wtdxsxf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${xcm.wtdxsxf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.wtdxsxf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${xcm.wtdxsxf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.wtdxsxf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy5&months=${months}')" >
									<fmt:formatNumber value="${xcm.wtdxsxf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;广告费</td>
						<td align="center">6</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.ggf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6&months=${months}')" >
									<fmt:formatNumber value="${xcm.ggf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.ggf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6months=${months}')" >
									<fmt:formatNumber value="${xcm.ggf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.ggf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy6&months=${months}')" >
									<fmt:formatNumber value="${xcm.ggf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;展览费</td>
						<td align="center">7</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zlf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7&months=${months}')" >
									<fmt:formatNumber value="${xcm.zlf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zlf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7months=${months}')" >
									<fmt:formatNumber value="${xcm.zlf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zlf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy7&months=${months}')" >
									<fmt:formatNumber value="${xcm.zlf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;租赁费</td>
						<td align="center">8</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zulinf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8&months=${months}')" >
									<fmt:formatNumber value="${xcm.zulinf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zulinf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8months=${months}')" >
									<fmt:formatNumber value="${xcm.zulinf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zulinf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy8&months=${months}')" >
									<fmt:formatNumber value="${xcm.zulinf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;包装费</td>
						<td align="center">9</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.bzf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${xcm.bzf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.bzf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${xcm.bzf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.bzf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy9&months=${months}')" >
									<fmt:formatNumber value="${xcm.bzf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&bnsp;&bnsp;销售服务费用</td>
						<td align="center">10</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xsfwf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${xcm.xsfwf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xsfwf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${xcm.xsfwf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xsfwf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy10&months=${months}')" >
									<fmt:formatNumber value="${xcm.xsfwf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;职工薪酬</td>
						<td align="center">11</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zgxc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${xcm.zgxc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zgxc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${xcm.zgxc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zgxc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy11&months=${months}')" >
									<fmt:formatNumber value="${xcm.zgxc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;其中：工资</td>
						<td align="center">12</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.gz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${xcm.gz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.gz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${xcm.gz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.gz3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy12&months=${months}')" >
									<fmt:formatNumber value="${xcm.gz3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;差旅费</td>
						<td align="center">13</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.clf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${xcm.clf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.clf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${xcm.clf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.clf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy13&months=${months}')" >
									<fmt:formatNumber value="${xcm.clf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;办公费</td>
						<td align="center">14</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.bgf1}"
								 pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${xcm.bgf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.bgf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${xcm.bgf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.bgf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy14&months=${months}')" >
									<fmt:formatNumber value="${xcm.bgf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;招待费</td>
						<td align="center">15</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zdf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${xcm.zdf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zdf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${xcm.zdf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zdf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy15&months=${months}')" >
									<fmt:formatNumber value="${xcm.zdf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;折旧费</td>
						<td align="center">16</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zjf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${xcm.zjf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zjf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${xcm.zjf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zjf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy16&months=${months}')" >
									<fmt:formatNumber value="${xcm.zjf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;取暖费</td>
						<td align="center">17</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qnf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${xcm.qnf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qnf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${xcm.qnf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qnf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy17&months=${months}')" >
									<fmt:formatNumber value="${xcm.qnf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;水电费</td>
						<td align="center">18</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.sdf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${xcm.sdf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.sdf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${xcm.sdf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.sdf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy18&months=${months}')" >
									<fmt:formatNumber value="${xcm.sdf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;修理费</td>
						<td align="center">19</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xlf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${xcm.xlf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xlf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${xcm.xlf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.xlf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy19&months=${months}')" >
									<fmt:formatNumber value="${xcm.xlf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;物料消耗</td>
						<td align="center">20</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.wlxh1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${xcm.wlxh1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.wlxh2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${xcm.wlxh2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.wlxh3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy20&months=${months}')" >
									<fmt:formatNumber value="${xcm.wlxh3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;低值易耗品摊销(包装物)</td>
						<td align="center">21</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.dzyhptx1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${xcm.dzyhptx1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.dzyhptx2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${xcm.dzyhptx2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.dzyhptx3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy21&months=${months}')" >
									<fmt:formatNumber value="${xcm.dzyhptx3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;产品“三包”损失</td>
						<td align="center">22</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cpsbss1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${xcm.cpsbss1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cpsbss2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${xcm.cpsbss2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cpsbss3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy22&months=${months}')" >
									<fmt:formatNumber value="${xcm.cpsbss3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;其&nbsp;&nbsp;它</td>
						<td align="center">23</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qita1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${xcm.qita1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qita2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${xcm.qita2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qita3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy23&months=${months}')" >
									<fmt:formatNumber value="${xcm.qita3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="left">二、财务费用合计：</th>
						<td align="center">24</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cwfy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${xcm.cwfy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cwfy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${xcm.cwfy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cwfy3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy24&months=${months}')" >
									<fmt:formatNumber value="${xcm.cwfy3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;利息支出</td>
						<td align="center" >25</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.lxzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${xcm.lxzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.lxzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${xcm.lxzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.lxzc3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy25&months=${months}')" >
									<fmt:formatNumber value="${xcm.lxzc3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;减：财政贴息</td>
						<td align="center">26</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cztx1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${xcm.cztx1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cztx2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${xcm.cztx2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.cztx3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy26&months=${months}')" >
									<fmt:formatNumber value="${xcm.cztx3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;减：利息收入</td>
						<td align="center">27</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.lxsr1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${xcm.lxsr1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.lxsr2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${xcm.lxsr2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.lxsr3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy27&months=${months}')" >
									<fmt:formatNumber value="${xcm.lxsr3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;折扣损失</td>
						<td align="center">28</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zkss1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${xcm.zkss1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zkss2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${xcm.zkss2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.zkss3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy28&months=${months}')" >
									<fmt:formatNumber value="${xcm.zkss3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;汇兑净损失
						</td>
						<td align="center">29</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.hdjss1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${xcm.hdjss1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.hdjss2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${xcm.hdjss2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.hdjss3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy29&months=${months}')" >
									<fmt:formatNumber value="${xcm.hdjss3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 其中：汇兑收益
						</td>
						<td align="center">30</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.hdsy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${xcm.hdsy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.hdsy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${xcm.hdsy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.hdsy3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy30&months=${months}')" >
									<fmt:formatNumber value="${xcm.hdsy3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						金融机构手续费
						</td>
						<td align="center">31</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.jrjgsxf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${xcm.jrjgsxf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.jrjgsxf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${xcm.jrjgsxf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.jrjgsxf3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy31&months=${months}')" >
									<fmt:formatNumber value="${xcm.jrjgsxf3}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
						&nbsp;&nbsp;
						其&nbsp;&nbsp;他
						</td>
						<td align="center">32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qt1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${xcm.qt1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qt2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${xcm.qt2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${xcm.qt3}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=glfy32&months=${months}')" >
									<fmt:formatNumber value="${xcm.qt3}" pattern="###,###,###.####"></fmt:formatNumber>
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
