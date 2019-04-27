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
				<h2>利润表</h2>
			<div >
				<ul style="width: 100%;">
					<li style="float: left; width: 33%;">单位:${companyInfo.name} </li>
					<li style="float: left; width: 33%">
						<input type="text" value="${months}" name="months" id="months"
							onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
							class="Wdate"
							onchange="changvalue(this)"/> 
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
							上年同期数
						</th>
						<th>
							本月数
						</th>
						<th>
							项目
						</th>
						<th>
							行次
						</th>
						<th>
							上年同期数
						</th>
						<th>
							本月数
						</th>
					</tr>
					<tr>
						<th align="left">
							一、营业收入
						</th>
						<td>1</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.ywsr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr1&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.ywsr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.ywsr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr1&months=${months}')" >
									<fmt:formatNumber value="${profit.ywsr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							二、营业利润（亏损以“－”号填列）
						</th>
						<td>21</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.ywlr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr21&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.ywlr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.ywlr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr21&months=${months}')" >
									<fmt:formatNumber value="${profit.ywlr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							其中：主营业务收入
						</td>
						<td>2</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.zyywsr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr2&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.zyywsr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.zyywsr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr2&months=${months}')" >
									<fmt:formatNumber value="${profit.zyywsr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							加：营业外收入
						</td>
						<td>22</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.ywsr1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr22&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.ywsr1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.ywsr1}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr22&months=${months}')" >
									<fmt:formatNumber value="${profit.ywsr1}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							  其他业务收入
						</td>
						<td>3</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.qtywsr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr3&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.qtywsr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.qtywsr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr3&months=${months}')" >
									<fmt:formatNumber value="${profit.qtywsr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							    其中：非流动资产处置利得
						</td>
						<td>23</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.fldzcdl}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr23&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.fldzcdl}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.fldzcdl}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr23&months=${months}')" >
									<fmt:formatNumber value="${profit.fldzcdl}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							 减：营业成本
						</td>
						<td>4</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.ywcb}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr4&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.ywcb}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.ywcb}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr4&months=${months}')" >
									<fmt:formatNumber value="${profit.ywcb}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							非货币性资产交换利得（非货币性交易收益）
						</td>
						<td>24</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.fhbzcdl}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr24&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.fhbzcdl}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.fhbzcdl}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr24&months=${months}')" >
									<fmt:formatNumber value="${profit.fhbzcdl}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  其中：主营业务成本
						</td>
						<td>5</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.zyywcb}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr5&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.zyywcb}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.zyywcb}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr5&months=${months}')" >
									<fmt:formatNumber value="${profit.zyywcb}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  政府补助(补贴收入)
						</td>
						<td>25</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.zfbz}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr25&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.zfbz}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.zfbz}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr25&months=${months}')" >
									<fmt:formatNumber value="${profit.zfbz}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  其他业务成本
						</td>
						<td>6</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.qtywcb}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr6&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.qtywcb}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.qtywcb}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr6&months=${months}')" >
									<fmt:formatNumber value="${profit.qtywcb}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							债务重组利得
						</td>
						<td>26</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.zwczdl}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr26&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.zwczdl}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.zwczdl}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr26&months=${months}')" >
									<fmt:formatNumber value="${profit.zwczdl}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							   营业税金及附加
						</td>
						<td>7</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.ywsjandfj}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr7&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.ywsjandfj}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.ywsjandfj}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr7&months=${months}')" >
									<fmt:formatNumber value="${profit.ywsjandfj}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;
							 减：营业外支出
						</td>
						<td>27</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.ywzc}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr27&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.ywzc}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.ywzc}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr27&months=${months}')" >
									<fmt:formatNumber value="${profit.ywzc}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							     销售费用
						</td>
						<td>8</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.xsfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr8&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.xsfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.xsfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr8&months=${months}')" >
									<fmt:formatNumber value="${profit.xsfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							其中：非流动资产处置损失
						</td>
						<td>28</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.fldzcss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr28&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.fldzcss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.fldzcss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr28&months=${months}')" >
									<fmt:formatNumber value="${profit.fldzcss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							      管理费用
						</td>
						<td>9</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.glfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr9&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.glfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.glfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr9&months=${months}')" >
									<fmt:formatNumber value="${profit.glfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							非货币性资产交换损失（非货币性交易损失）
						</td>
						<td>29</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.fhbzcss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr29&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.fhbzcss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.fhbzcss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr29&months=${months}')" >
									<fmt:formatNumber value="${profit.fhbzcss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							      其中：业务招待费
						</td>
						<td>10</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.ywzdf}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr10&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.ywzdf}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.ywzdf}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr10&months=${months}')" >
									<fmt:formatNumber value="${profit.ywzdf}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;
							 债务重组损失
						</td>
						<td>30</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.zwczss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr30&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.zwczss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.zwczss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr30&months=${months}')" >
									<fmt:formatNumber value="${profit.zwczss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							      研究与开发费
						</td>
						<td>11</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.yjykff}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr11&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.yjykff}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.yjykff}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr11&months=${months}')" >
									<fmt:formatNumber value="${profit.yjykff}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							三、利润总额（亏损总额以“－”号填列）
						</th>
						<td>31</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.lrze}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr31&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.lrze}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lrze}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr31&months=${months}')" >
									<fmt:formatNumber value="${profit.lrze}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						  财务费用
						</td>
						<td>12</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.cwfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr12&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.cwfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.cwfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr12&months=${months}')" >
									<fmt:formatNumber value="${profit.cwfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							减：所得税费用
						</td>
						<td>32</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.sdsfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr32&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.sdsfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.sdsfy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr32&months=${months}')" >
									<fmt:formatNumber value="${profit.sdsfy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;
						  其中：利息支出
						</td>
						<td>13</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.lxzc}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr13&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.lxzc}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lxzc}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr13&months=${months}')" >
									<fmt:formatNumber value="${profit.lxzc}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							加：＃* 未确认的投资损失
						</td>
						<td>33</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.wqrtzss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr33&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.wqrtzss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.wqrtzss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr33&months=${months}')" >
									<fmt:formatNumber value="${profit.wqrtzss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    利息收入
						</td>
						<td>14</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.lxsr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr14&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.lxsr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lxsr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr14&months=${months}')" >
									<fmt:formatNumber value="${profit.lxsr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							四、净利润（净亏损以“－”号填列）
						</th>
						<td>34</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.jlr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr34&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.jlr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.jlr}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr34&months=${months}')" >
									<fmt:formatNumber value="${profit.jlr}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    汇兑净损失（汇兑净收益以“－”）
						</td>
						<td>15</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.hdjss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr15&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.hdjss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.hdjss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr15&months=${months}')" >
									<fmt:formatNumber value="${profit.hdjss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;
							 减：* 少数股东损益
						</td>
						<td>35</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.littlegdsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr35&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.littlegdsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.littlegdsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr35&months=${months}')" >
									<fmt:formatNumber value="${profit.littlegdsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						&nbsp;&nbsp;&nbsp;
						    资产减值损失
						</td>
						<td>16</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.zcjzss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr16&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.zcjzss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.zcjzss}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr16&months=${months}')" >
									<fmt:formatNumber value="${profit.zcjzss}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							五、归属于母公司所有者的净利润
						</th>
						<td>36</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.parentCompanyLR}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr36&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.parentCompanyLR}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.parentCompanyLR}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr36&months=${months}')" >
									<fmt:formatNumber value="${profit.parentCompanyLR}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
						</td>
						<td>17</td>
						<td></td>
						<td></td>
						<th align="left">
							六、每股收益：
						</th>
						<td>37</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							加：公允价值变动收益（损失以“－”
						</td>
						<td>18</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.gyzbdsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr18&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.gyzbdsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.gyzbdsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr18&months=${months}')" >
									<fmt:formatNumber value="${profit.gyzbdsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							&nbsp;&nbsp;  基本每股收益
						</th>
						<td>38</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.jbmgsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr38&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.jbmgsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.jbmgsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr38&months=${months}')" >
									<fmt:formatNumber value="${profit.jbmgsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;  投资收益（损失以“－”号填列）
						</td>
						<td>19</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.tzsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr19&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.tzsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.tzsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr19&months=${months}')" >
									<fmt:formatNumber value="${profit.tzsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
							&nbsp;&nbsp;稀释每股收益
						</th>
						<td>39</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.xsmgsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr39&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.xsmgsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.xsmgsy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr39&months=${months}')" >
									<fmt:formatNumber value="${profit.xsmgsy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;  其中：对联营企业和合营企业的投资收益
						</td>
						<td>20</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lastYearProfit.lyqyAndhyqysy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr20&months=${months}')" >
									<fmt:formatNumber value="${profit.lastYearProfit.lyqyAndhyqysy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<td align="right">
							<s:if test="pageStauts == 'yw'">
								<fmt:formatNumber value="${profit.lyqyAndhyqysy}" pattern="###,###,###.####"></fmt:formatNumber>
							</s:if>
							<s:else>
								<a  href="javascript:;" onclick="window.open('SubjectBudgetAction!getjisunGS.action?tag=Lr20&months=${months}')" >
									<fmt:formatNumber value="${profit.lyqyAndhyqysy}" pattern="###,###,###.####"></fmt:formatNumber>
								</a>
							</s:else>
						</td>
						<th align="left">
						</th>
						<td>40</td>
						<td></td>
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
		window.location.href='SubjectBudgetAction!findprofitByMonths.action?months='+$("#"+obj).val()+"&pageStauts=";
	}
}

</SCRIPT>
	</body>
</html>
