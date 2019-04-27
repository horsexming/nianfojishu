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
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>资产负债表</h2>
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
							项目
						</th>
						<th>
							行次
						</th>
						<th>
							年初数
						</th>
						<th>
							期末数
						</th>
						<th>
							项目
						</th>
						<th>
							行次
						</th>
						<th>
							年初数
						</th>
						<th>
							期末数
						</th>
					</tr>
					<tr>
						<th align="left">
							流动资产：
						</th>
						<td>1</td>
						<td></td>
						<td></td>
						<th align="left">
							流动负债：
						</th>
						<td>49</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							   货币资金
						</td>
						<td>2</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cash1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z2&months=${months}')" >
									<fmt:formatNumber value="${balance.cash1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cash2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z2&months=${months}')" >
									<fmt:formatNumber value="${balance.cash2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							  短期借款
						</td>
						<td>50</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.dqjk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z50&months=${months}')" >
									<fmt:formatNumber value="${balance.dqjk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.dqjk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z50&months=${months}')" >
									<fmt:formatNumber value="${balance.dqjk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 交易性金融资产
						</td>
						<td>3</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.jyxjrzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z3&months=${months}')" >
									<fmt:formatNumber value="${balance.jyxjrzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.jyxjrzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z3&months=${months}')" >
									<fmt:formatNumber value="${balance.jyxjrzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							    交易性金融负债
						</td>
						<td>51</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.jyxjrfz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z51&months=${months}')" >
									<fmt:formatNumber value="${balance.jyxjrfz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.jyxjrfz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z51&months=${months}')" >
									<fmt:formatNumber value="${balance.jyxjrfz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							 应收票据
						</td>
						<td>4</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yspj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z4&months=${months}')" >
									<fmt:formatNumber value="${balance.yspj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yspj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z4&months=${months}')" >
									<fmt:formatNumber value="${balance.yspj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							应付票据
						</td>
						<td>52</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfpj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z52&months=${months}')" >
									<fmt:formatNumber value="${balance.yfpj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfpj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z52&months=${months}')" >
									<fmt:formatNumber value="${balance.yfpj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							  应收帐款
						</td>
						<td>5</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yszk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z5&months=${months}')" >
									<fmt:formatNumber value="${balance.yszk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yszk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z5&months=${months}')" >
									<fmt:formatNumber value="${balance.yszk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							  应付账款
						</td>
						<td>53</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yingfuZK1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z53&months=${months}')" >
									<fmt:formatNumber value="${balance.yingfuZK1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yingfuZK2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z53&months=${months}')" >
									<fmt:formatNumber value="${balance.yingfuZK2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							预付帐款
						</td>
						<td>6</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfzk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z6&months=${months}')" >
									<fmt:formatNumber value="${balance.yfzk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfzk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z6&months=${months}')" >
									<fmt:formatNumber value="${balance.yfzk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							预收款项
						</td>
						<td>54</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yskx1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z54&months=${months}')" >
									<fmt:formatNumber value="${balance.yskx1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yskx2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z54&months=${months}')" >
									<fmt:formatNumber value="${balance.yskx2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							   应收利息
						</td>
						<td>7</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yslx1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z7&months=${months}')" >
									<fmt:formatNumber value="${balance.yslx1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yslx2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z7&months=${months}')" >
									<fmt:formatNumber value="${balance.yslx2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 应付职工薪酬
						</td>
						<td>55</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfzgxc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z55&months=${months}')" >
									<fmt:formatNumber value="${balance.yfzgxc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfzgxc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z55&months=${months}')" >
									<fmt:formatNumber value="${balance.yfzgxc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							    应收股利
						</td>
						<td>8</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ysgl1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z8&months=${months}')" >
									<fmt:formatNumber value="${balance.ysgl1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ysgl2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z8&months=${months}')" >
									<fmt:formatNumber value="${balance.ysgl2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							其中：应付工资
						</td>
						<td>56</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfgz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z56&months=${months}')" >
									<fmt:formatNumber value="${balance.yfgz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfgz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z56&months=${months}')" >
									<fmt:formatNumber value="${balance.yfgz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							       其他应收款
						</td>
						<td>9</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtysk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z9&months=${months}')" >
									<fmt:formatNumber value="${balance.qtysk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtysk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z9&months=${months}')" >
									<fmt:formatNumber value="${balance.qtysk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							应付福利费
						</td>
						<td>57</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfflf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z57&months=${months}')" >
									<fmt:formatNumber value="${balance.yfflf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfflf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z57&months=${months}')" >
									<fmt:formatNumber value="${balance.yfflf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							存货
						</td>
						<td>10</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ch1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z10&months=${months}')" >
									<fmt:formatNumber value="${balance.ch1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ch2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z10&months=${months}')" >
									<fmt:formatNumber value="${balance.ch2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 应交税费
						</td>
						<td>58</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yjsf1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z58&months=${months}')" >
									<fmt:formatNumber value="${balance.yjsf1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yjsf2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z58&months=${months}')" >
									<fmt:formatNumber value="${balance.yjsf2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  其中：原材料
						</td>
						<td>11</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ycl1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z11&months=${months}')" >
									<fmt:formatNumber value="${balance.ycl1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ycl2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z11&months=${months}')" >
									<fmt:formatNumber value="${balance.ycl2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 其中：应交税金
						</td>
						<td>59</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yjsj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z59&months=${months}')" >
									<fmt:formatNumber value="${balance.yjsj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yjsj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z59&months=${months}')" >
									<fmt:formatNumber value="${balance.yjsj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						   库存商品（产成品)
						</td>
						<td>12</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.kcsp1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z12&months=${months}')" >
									<fmt:formatNumber value="${balance.kcsp1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.kcsp2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z12&months=${months}')" >
									<fmt:formatNumber value="${balance.kcsp2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							 应付利息
						</td>
						<td>60</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yflx1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z60&months=${months}')" >
									<fmt:formatNumber value="${balance.yflx1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yflx2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z60&months=${months}')" >
									<fmt:formatNumber value="${balance.yflx2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						 一年内到期的非流动资产
						</td>
						<td>13</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fldzcyears1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z13&months=${months}')" >
									<fmt:formatNumber value="${balance.fldzcyears1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fldzcyears2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z13&months=${months}')" >
									<fmt:formatNumber value="${balance.fldzcyears2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							 应付股利（应付利润）
						</td>
						<td>61</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfgl1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z61&months=${months}')" >
									<fmt:formatNumber value="${balance.yfgl1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfgl2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z61&months=${months}')" >
									<fmt:formatNumber value="${balance.yfgl2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						   其他流动资产
						</td>
						<td>14</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qitaldzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z14&months=${months}')" >
									<fmt:formatNumber value="${balance.qitaldzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qitaldzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z14&months=${months}')" >
									<fmt:formatNumber value="${balance.qitaldzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							 其他应付款
						</td>
						<td>62</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtyfk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z62&months=${months}')" >
									<fmt:formatNumber value="${balance.qtyfk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtyfk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z62&months=${months}')" >
									<fmt:formatNumber value="${balance.qtyfk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="center">
						 流动资产合计
						</th>
						<td>15</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.liudonghj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z15&months=${months}')" >
									<fmt:formatNumber value="${balance.liudonghj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.liudonghj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z15&months=${months}')" >
									<fmt:formatNumber value="${balance.liudonghj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							    一年内到期的非流动负债
						</td>
						<td>63</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fldfzyears1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z63&months=${months}')" >
									<fmt:formatNumber value="${balance.fldfzyears1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fldfzyears2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z63&months=${months}')" >
									<fmt:formatNumber value="${balance.fldfzyears2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						非流动资产：
						</td>
						<td>16</td>
						<td></td>
						<td></td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							其他流动负债
						</td>
						<td>64</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtldfz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z64&months=${months}')" >
									<fmt:formatNumber value="${balance.qtldfz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtldfz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z64&months=${months}')" >
									<fmt:formatNumber value="${balance.qtldfz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							 可供出售金融资产
						</td>
						<td>17</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.kgcsjrzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z17&months=${months}')" >
									<fmt:formatNumber value="${balance.kgcsjrzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.kgcsjrzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z17&months=${months}')" >
									<fmt:formatNumber value="${balance.kgcsjrzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							流动负债合计
						</th>
						<td>65</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ldfzhj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z65&months=${months}')" >
									<fmt:formatNumber value="${balance.ldfzhj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ldfzhj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z65&months=${months}')" >
									<fmt:formatNumber value="${balance.ldfzhj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							   持有至到期投资
						</td>
						<td>18</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cyzdqtz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z18&months=${months}')" >
									<fmt:formatNumber value="${balance.cyzdqtz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cyzdqtz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z18&months=${months}')" >
									<fmt:formatNumber value="${balance.cyzdqtz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							非流动负债：
						</td>
						<td>66</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							长期应收款
						</td>
						<td>19</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqysk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z19&months=${months}')" >
									<fmt:formatNumber value="${balance.cqysk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqysk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z19&months=${months}')" >
									<fmt:formatNumber value="${balance.cqysk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							长期借款
						</td>
						<td>67</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqjk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z67&months=${months}')" >
									<fmt:formatNumber value="${balance.cqjk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqjk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z67&months=${months}')" >
									<fmt:formatNumber value="${balance.cqjk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;  
							长期股权投资
						</td>
						<td>20</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqgqtz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z20&months=${months}')" >
									<fmt:formatNumber value="${balance.cqgqtz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqgqtz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z20&months=${months}')" >
									<fmt:formatNumber value="${balance.cqgqtz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;  
						应付债券
						</td>
						<td>68</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfzq1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z68&months=${months}')" >
									<fmt:formatNumber value="${balance.yfzq1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yfzq2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z68&months=${months}')" >
									<fmt:formatNumber value="${balance.yfzq2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp; 
							   投资性房地产
						</td>
						<td>21</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.tzxfdc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z21&months=${months}')" >
									<fmt:formatNumber value="${balance.tzxfdc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.tzxfdc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z21&months=${months}')" >
									<fmt:formatNumber value="${balance.tzxfdc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp; 
							长期应付款
						</td>
						<td>69</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqyfk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z69&months=${months}')" >
									<fmt:formatNumber value="${balance.cqyfk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqyfk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z69&months=${months}')" >
									<fmt:formatNumber value="${balance.cqyfk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							  固定资产原价
						</td>
						<td>22</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gizcyj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z22&months=${months}')" >
									<fmt:formatNumber value="${balance.gizcyj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gizcyj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z22&months=${months}')" >
									<fmt:formatNumber value="${balance.gizcyj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							专项应付款
						</td>
						<td>70</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.zxyfk1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z70&months=${months}')" >
									<fmt:formatNumber value="${balance.zxyfk1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.zxyfk2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z70&months=${months}')" >
									<fmt:formatNumber value="${balance.zxyfk2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    减：累计折旧
						</td>
						<td>23</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.zcljzj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z23&months=${months}')" >
									<fmt:formatNumber value="${balance.zcljzj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.zcljzj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z23&months=${months}')" >
									<fmt:formatNumber value="${balance.zcljzj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							      预计负债
						</td>
						<td>71</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yjfz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z71&months=${months}')" >
									<fmt:formatNumber value="${balance.yjfz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yjfz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z71&months=${months}')" >
									<fmt:formatNumber value="${balance.yjfz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							  固定资产净值
						</td>
						<td>24</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gdzcjz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z24&months=${months}')" >
									<fmt:formatNumber value="${balance.gdzcjz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gdzcjz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z24&months=${months}')" >
									<fmt:formatNumber value="${balance.gdzcjz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							   递延所得税负债
						</td>
						<td>72</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.dysdsfz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z72&months=${months}')" >
									<fmt:formatNumber value="${balance.dysdsfz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.dysdsfz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z72&months=${months}')" >
									<fmt:formatNumber value="${balance.dysdsfz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  减：固定资产减值准备
						</td>
						<td>25</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gdzcjzzb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z25&months=${months}')" >
									<fmt:formatNumber value="${balance.gdzcjzzb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gdzcjzzb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z25&months=${months}')" >
									<fmt:formatNumber value="${balance.gdzcjzzb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							其他非流动负债
						</td>
						<td>73</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtfldfz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z73&months=${months}')" >
									<fmt:formatNumber value="${balance.qtfldfz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtfldfz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z73&months=${months}')" >
									<fmt:formatNumber value="${balance.qtfldfz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							固定资产净额
						</td>
						<td>26</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gdzcje1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z26&months=${months}')" >
									<fmt:formatNumber value="${balance.gdzcje1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gdzcje2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z26&months=${months}')" >
									<fmt:formatNumber value="${balance.gdzcje2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							其中：特准储备基金
						</td>
						<td>74</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.tzcbj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z74&months=${months}')" >
									<fmt:formatNumber value="${balance.tzcbj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.tzcbj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z74&months=${months}')" >
									<fmt:formatNumber value="${balance.tzcbj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							    在建工程
						</td>
						<td>27</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.zjgc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z27&months=${months}')" >
									<fmt:formatNumber value="${balance.zjgc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.zjgc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z27&months=${months}')" >
									<fmt:formatNumber value="${balance.zjgc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th >
							非流动负债合计
						</th>
						<td>75</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fldfzhj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z75&months=${months}')" >
									<fmt:formatNumber value="${balance.fldfzhj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fldfzhj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z75&months=${months}')" >
									<fmt:formatNumber value="${balance.fldfzhj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							  工程物资
						</td>
						<td>28</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gchz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z28&months=${months}')" >
									<fmt:formatNumber value="${balance.gchz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gchz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z28&months=${months}')" >
									<fmt:formatNumber value="${balance.gchz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							负 债 合 计
						</th>
						<td>76</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fzhj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z76&months=${months}')" >
									<fmt:formatNumber value="${balance.fzhj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fzhj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z76&months=${months}')" >
									<fmt:formatNumber value="${balance.fzhj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							       固定资产清理
						</td>
						<td>29</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gdzcql1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z29&months=${months}')" >
									<fmt:formatNumber value="${balance.gdzcql1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gdzcql2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z29&months=${months}')" >
									<fmt:formatNumber value="${balance.gdzcql2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							所有者权益（或股东权益）：
						</th>
						<td>77</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						生产性生物资产
						</td>
						<td>30</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.scxswzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z30&months=${months}')" >
									<fmt:formatNumber value="${balance.scxswzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.scxswzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z30&months=${months}')" >
									<fmt:formatNumber value="${balance.scxswzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 实收资本（股本）
						</td>
						<td>78</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.sszb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z78&months=${months}')" >
									<fmt:formatNumber value="${balance.sszb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.sszb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z78&months=${months}')" >
									<fmt:formatNumber value="${balance.sszb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							  油气资产
						</td>
						<td>31</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yqzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z31&months=${months}')" >
									<fmt:formatNumber value="${balance.yqzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yqzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z31&months=${months}')" >
									<fmt:formatNumber value="${balance.yqzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							  国家资本
						</td>
						<td>79</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gjzb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z79&months=${months}')" >
									<fmt:formatNumber value="${balance.gjzb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gjzb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z79&months=${months}')" >
									<fmt:formatNumber value="${balance.gjzb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						   无形资产
						</td>
						<td>32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wxzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z32&months=${months}')" >
									<fmt:formatNumber value="${balance.wxzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wxzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z32&months=${months}')" >
									<fmt:formatNumber value="${balance.wxzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							集体资本
						</td>
						<td>80</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.jtzb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z80&months=${months}')" >
									<fmt:formatNumber value="${balance.jtzb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.jtzb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z80&months=${months}')" >
									<fmt:formatNumber value="${balance.jtzb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 	其中：土地使用权
						</td>
						<td>33</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.tdsyq1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z33&months=${months}')" >
									<fmt:formatNumber value="${balance.tdsyq1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.tdsyq2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z33&months=${months}')" >
									<fmt:formatNumber value="${balance.tdsyq2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							法人资本
						</td>
						<td>81</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.frzb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z81&months=${months}')" >
									<fmt:formatNumber value="${balance.frzb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.frzb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z81&months=${months}')" >
									<fmt:formatNumber value="${balance.frzb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						  开发支出
						</td>
						<td>34</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.kfzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z34&months=${months}')" >
									<fmt:formatNumber value="${balance.kfzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.kfzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z34&months=${months}')" >
									<fmt:formatNumber value="${balance.kfzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  其中：国有法人资本
						</td>
						<td>82</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gyfrzb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z82&months=${months}')" >
									<fmt:formatNumber value="${balance.gyfrzb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.gyfrzb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z82&months=${months}')" >
									<fmt:formatNumber value="${balance.gyfrzb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td >
						&nbsp;&nbsp;&nbsp;&nbsp;
						 商誉
						</td>
						<td>35</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.shangyu1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z35&months=${months}')" >
									<fmt:formatNumber value="${balance.shangyu1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.shangyu2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z35&months=${months}')" >
									<fmt:formatNumber value="${balance.shangyu2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="center">
							    集体法人资本
						</td>
						<td>83</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.jtfrzb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z83&months=${months}')" >
									<fmt:formatNumber value="${balance.jtfrzb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.jtfrzb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z83&months=${months}')" >
									<fmt:formatNumber value="${balance.jtfrzb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						长期待摊费用（递延资产）
						</td>
						<td>36</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqdtfy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z36&months=${months}')" >
									<fmt:formatNumber value="${balance.cqdtfy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.cqdtfy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z36&months=${months}')" >
									<fmt:formatNumber value="${balance.cqdtfy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							  个人资本
						</td>
						<td>84</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.grzb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z84&months=${months}')" >
									<fmt:formatNumber value="${balance.grzb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.grzb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z84&months=${months}')" >
									<fmt:formatNumber value="${balance.grzb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							递延所得税资产
						</td>
						<td>37</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.dysdsc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z37&months=${months}')" >
									<fmt:formatNumber value="${balance.dysdsc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.dysdsc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z37&months=${months}')" >
									<fmt:formatNumber value="${balance.dysdsc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							外商资本
						</td>
						<td>85</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wszb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z85&months=${months}')" >
									<fmt:formatNumber value="${balance.wszb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wszb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z85&months=${months}')" >
									<fmt:formatNumber value="${balance.wszb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							   其他非流动资产（其他长期资产）
						</td>
						<td>38</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtfldzc1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z38&months=${months}')" >
									<fmt:formatNumber value="${balance.qtfldzc1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.qtfldzc2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z38&months=${months}')" >
									<fmt:formatNumber value="${balance.qtfldzc2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							资本公积
						</td>
						<td>86</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.zbgj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z86&months=${months}')" >
									<fmt:formatNumber value="${balance.zbgj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.zbgj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z86&months=${months}')" >
									<fmt:formatNumber value="${balance.zbgj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							其中：特准储备物资
						</td>
						<td>39</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.tzcbwz1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z39&months=${months}')" >
									<fmt:formatNumber value="${balance.tzcbwz1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.tzcbwz2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z39&months=${months}')" >
									<fmt:formatNumber value="${balance.tzcbwz2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							  减：库存股
						</td>
						<td>87</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.kcg1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z87&months=${months}')" >
									<fmt:formatNumber value="${balance.kcg1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.kcg2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z87&months=${months}')" >
									<fmt:formatNumber value="${balance.kcg2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th >
							非流动资产合计
						</th>
						<td>40</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fldzchj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z40&months=${months}')" >
									<fmt:formatNumber value="${balance.fldzchj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fldzchj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z40&months=${months}')" >
									<fmt:formatNumber value="${balance.fldzchj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;  
						盈余公积
						</td>
						<td>88</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yygj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z88&months=${months}')" >
									<fmt:formatNumber value="${balance.yygj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.yygj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z88&months=${months}')" >
									<fmt:formatNumber value="${balance.yygj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						</td>
						<td>41</td>
						<td></td>
						<td></td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;  
						  一般风险准备
						</td>
						<td>89</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ybfxzb1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z89&months=${months}')" >
									<fmt:formatNumber value="${balance.ybfxzb1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ybfxzb2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z89&months=${months}')" >
									<fmt:formatNumber value="${balance.ybfxzb2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						</td>
						<td>42</td>
						<td></td>
						<td></td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;  
						 未确认的投资损失（以“-”号填列）
						</td>
						<td>90</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wqrdtzss1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z90&months=${months}')" >
									<fmt:formatNumber value="${balance.wqrdtzss1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wqrdtzss2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z90&months=${months}')" >
									<fmt:formatNumber value="${balance.wqrdtzss2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						</td>
						<td>43</td>
						<td></td>
						<td></td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;  
						 未分配利润
						</td>
						<td>91</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wfplr1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z91&months=${months}')" >
									<fmt:formatNumber value="${balance.wfplr1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wfplr2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z91&months=${months}')" >
									<fmt:formatNumber value="${balance.wfplr2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						</td>
						<td>44</td>
						<td></td>
						<td></td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; 
						  其中：现金股利
						</td>
						<td>92</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.xjgl1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z92&months=${months}')" >
									<fmt:formatNumber value="${balance.xjgl1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.xjgl2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z92&months=${months}')" >
									<fmt:formatNumber value="${balance.xjgl2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						</td>
						<td>45</td>
						<td></td>
						<td></td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;  
						   外币报表折算差额
						</td>
						<td>93</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wbbbzsce1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z93&months=${months}')" >
									<fmt:formatNumber value="${balance.wbbbzsce1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.wbbbzsce2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z93&months=${months}')" >
									<fmt:formatNumber value="${balance.wbbbzsce2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						</td>
						<td>46</td>
						<td></td>
						<td></td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;  
						归属于母公司所有者权益合计
						</td>
						<td>94</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.mgsszqy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z94&months=${months}')" >
									<fmt:formatNumber value="${balance.mgsszqy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.mgsszqy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z94&months=${months}')" >
									<fmt:formatNumber value="${balance.mgsszqy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						</td>
						<td>47</td>
						<td></td>
						<td></td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;  
						 少数股东权益
						</td>
						<td>95</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ssgdqy1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z95&months=${months}')" >
									<fmt:formatNumber value="${balance.ssgdqy1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.ssgdqy2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z95&months=${months}')" >
									<fmt:formatNumber value="${balance.ssgdqy2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="center">
							所有者权益合计
						</th>
						<td>48</td>
						<td></td>
						<td></td>
						<th align="center">
							所有者权益合计
						</th>
						<td>96</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.syzqyhj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z96&months=${months}')" >
									<fmt:formatNumber value="${balance.syzqyhj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.syzqyhj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z96&months=${months}')" >
									<fmt:formatNumber value="${balance.syzqyhj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="center">
						</th>
						<td>49</td>
						<td></td>
						<td></td>
						<th align="center">
							负债和所有者权益合计
						</th>
						<td>97</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fzandsyzqyhj1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z97&months=${months}')" >
									<fmt:formatNumber value="${balance.fzandsyzqyhj1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${balance.fzandsyzqyhj2}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else> 
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=z97&months=${months}')" >
									<fmt:formatNumber value="${balance.fzandsyzqyhj2}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function changvalue(obj,tag){
	if(obj!=null && $("#"+obj).val()!=''){
		window.location.href='SubjectBudgetAction!findbalanceByMonths.action?months='+$("#"+obj).val()+"&pageStauts="+tag;
	}
}


</script>
	</body>
</html>
